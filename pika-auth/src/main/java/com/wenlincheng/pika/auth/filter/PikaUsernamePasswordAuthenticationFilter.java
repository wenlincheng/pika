package com.wenlincheng.pika.auth.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wenlincheng.pika.auth.exception.ValidateCodeException;
import com.wenlincheng.pika.common.core.redis.RedisUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.wenlincheng.pika.common.core.constant.SecurityConstants.LOGIN_URL;
import static com.wenlincheng.pika.common.core.constant.SecurityConstants.VALIDATE_CODE_REDIS_KEY;

/**
 * 重写用户form登陆方式  采用json方式登陆
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class PikaUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private RedisUtils redisUtils;

    public PikaUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failHandler, RedisUtils redisUtils) {
        // 指定登录路径
        super(new AntPathRequestMatcher(LOGIN_URL, "POST"));
        // 设置登入处理方式
        this.setAuthenticationManager(authenticationManager);
        // 设置登陆成功处理
        this.setAuthenticationSuccessHandler(successHandler);
        // 设置登入失败处理
        this.setAuthenticationFailureHandler(failHandler);
        this.redisUtils = redisUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        // 1、获取请求体 数据流只能读取一次
        String body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        String username = "", password = "", validateCode = "", uuid = "";
        if (StringUtils.hasText(body)) {
            JSONObject jsonObj = JSON.parseObject(body);
            username = jsonObj.getString("username");
            password = jsonObj.getString("password");
            validateCode = jsonObj.getString("validateCode");
            uuid = jsonObj.getString("uuid");
        }
        username = username.trim();
        password = password.trim();
        validateCode = validateCode.trim();
        uuid = uuid.trim();
        if (Strings.isBlank(username) || Strings.isBlank(password)) {
            throw new ValidateCodeException("用户名密码不能为空");
        }

        // 2、验证码校验
        if (Strings.isBlank(validateCode) || Strings.isBlank(uuid)) {
            throw new ValidateCodeException("验证码为空，请重新输入");
        }
        String code = redisUtils.get(VALIDATE_CODE_REDIS_KEY + uuid);
        if (org.apache.commons.lang3.StringUtils.isBlank(code)) {
            throw new ValidateCodeException("验证码已过期，请重新获取");
        }
        code = code.equals("0.0") ? "0" : code;
        if (!org.apache.commons.lang3.StringUtils.equals(code, validateCode)) {
            throw new ValidateCodeException("验证码不正确，请重新输入");
        }
        redisUtils.delete(VALIDATE_CODE_REDIS_KEY + uuid);

        request.setAttribute("username", username);
        /*
            将登陆请求提交给认证 AuthenticationManager管理模块下的authenticate方法
            再由authenticate具体的实现类完成认证服务,使用默认提供的DaoAuthenticationProvider这个用户信息查询及存储实现类
        */
        return this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}
