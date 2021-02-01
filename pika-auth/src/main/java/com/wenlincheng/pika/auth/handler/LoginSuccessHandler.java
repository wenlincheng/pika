package com.wenlincheng.pika.auth.handler;

import com.wenlincheng.pika.auth.config.JwtProperties;
import com.wenlincheng.pika.auth.manager.JwtTokenManager;
import com.wenlincheng.pika.auth.security.AuthUserDetails;
import com.wenlincheng.pika.auth.utils.ResponseUtil;
import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.common.core.constant.SecurityConstants;
import com.wenlincheng.pika.common.core.redis.RedisUtils;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.wenlincheng.pika.auth.exception.AuthErrorCodeEnum.TOKEN_CREATE_FAILED;

/**
 * 登陆认证成功处理器
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private JwtTokenManager jwtTokenManager;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 用户认证成功后 生成token并返回
     *
     * @param request
     * @param response
     * @param authentication
     * @throws
     * @return void
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        AuthUserDetails authUserDetails = (AuthUserDetails)authentication.getPrincipal();
        Long userId = authUserDetails.getId();
        // 清除旧Token
        String oldToken = redisUtils.get(SecurityConstants.JWT_TOKEN_REDIS_PREFIX + userId);
        if(!StringUtils.isBlank(oldToken)){
            redisUtils.delete(SecurityConstants.JWT_TOKEN_REDIS_PREFIX + userId);
        }

        try {
            String accessToken = jwtTokenManager.createAccessJwtToken(authUserDetails);
            redisUtils.setEx(SecurityConstants.JWT_TOKEN_REDIS_PREFIX + userId, accessToken, jwtProperties.getTokenExpiration() + 300, TimeUnit.SECONDS);
            Map<String,String> map = new HashMap<>(1);
            map.put("accessToken",accessToken);
            ResponseUtil.out(response, Result.success(map));
        } catch (JwtException e) {
            log.error("令牌创建失败 {}", e.getMessage());
            ResponseUtil.out(response, Result.fail(TOKEN_CREATE_FAILED));
        }

    }

}
