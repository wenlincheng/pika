package com.wenlincheng.pika.auth.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wenlincheng.pika.auth.exception.ValidateCodeException;
import com.wenlincheng.pika.auth.handler.LoginFailureHandler;
import com.wenlincheng.pika.common.core.redis.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.wenlincheng.pika.common.core.constant.SecurityConstants.LOGIN_URL;
import static com.wenlincheng.pika.common.core.constant.SecurityConstants.VALIDATE_CODE_REDIS_KEY;

/**
 * TODO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/3/21 5:01 下午
 */
@Component
public class VerifyCodeFilter extends OncePerRequestFilter {

    @Bean
    public VerifyCodeFilter getVerifyCodeFilter() {
        return new VerifyCodeFilter();
    }
    @Autowired
    LoginFailureHandler loginFailureHandler;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equals(LOGIN_URL, request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            // 1. 进行验证码的校验
            String body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
            String validateCode = null, uuid = null;
            if (org.springframework.util.StringUtils.hasText(body)) {
                JSONObject jsonObj = JSON.parseObject(body);
                validateCode = jsonObj.getString("validateCode");
                uuid = jsonObj.getString("uuid");
            }
            try {
                if (validateCode == null || uuid == null) {
                    throw new ValidateCodeException("验证码为空，请重新输入");
                }
                String code = redisUtils.get(VALIDATE_CODE_REDIS_KEY + uuid);
                if (StringUtils.isBlank(code)) {
                    throw new ValidateCodeException("验证码已过期，请重新获取");
                }
                code = code.equals("0.0") ? "0" : code;
                if (!StringUtils.equals(code, validateCode)) {
                    throw new ValidateCodeException("验证码不正确，请重新输入");
                }
                redisUtils.delete(VALIDATE_CODE_REDIS_KEY + uuid);
            } catch (AuthenticationException e) {
                loginFailureHandler.onAuthenticationFailure(request, response, e);
            } finally {
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }

    }
}
