package com.wenlincheng.pika.auth.controller;

import com.wenlincheng.pika.auth.entity.AuthUser;
import com.wenlincheng.pika.auth.entity.ValidateCode;
import com.wenlincheng.pika.auth.service.AuthService;
import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.common.core.constant.SecurityConstants;
import com.wenlincheng.pika.common.core.exception.PikaException;
import com.wenlincheng.pika.common.core.redis.RedisUtils;
import com.wenlincheng.pika.common.core.util.UUIDUtils;
import com.wf.captcha.ArithmeticCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.concurrent.TimeUnit;

import static com.wenlincheng.pika.common.core.constant.SecurityConstants.VALIDATE_CODE_REDIS_KEY;


/**
 * 认证鉴权
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 用户退出登录
     *
     * @param request   请求
     * @return Result<Boolean>
     */
    @GetMapping(value = "/token/logout")
    public Result<Boolean> logout(HttpServletRequest request) throws PikaException {
        String token = request.getHeader(SecurityConstants.JWT_TOKEN_HEADER);
        if (StringUtils.isBlank(token) || !token.startsWith(SecurityConstants.JWT_TOKEN_PREFIX)) {
            return Result.success(true);
        }
        return Result.success(authService.logout(token));
    }

    /**
     * 请求鉴权获取授权用户信息
     *
     * @param uri       请求路径
     * @param method    请求方法
     * @param request   请求
     * @return Result<AuthUser>
     */
    @GetMapping(value = "/token/permit")
    public Result<AuthUser> authDecide(HttpServletRequest request, @RequestParam String uri, @RequestParam String method) {
        AuthUser authUser = authService.authDecide(new HttpServletRequestWrapper(request), uri, method);
        return Result.success(authUser);
    }

    /**
     * 根据令牌查询用户信息
     *
     * @param request 请求
     * @return Result<AuthUser>
     */
    @GetMapping(value = "/token/user/info")
    public Result<AuthUser> getAuthUser(HttpServletRequest request) {
        // 获取请求头中的令牌
        String token = request.getHeader(SecurityConstants.JWT_TOKEN_HEADER);
        // 令牌不存在或格式不对，直接返回FALSE
        if (StringUtils.isBlank(token) || !token.startsWith(SecurityConstants.JWT_TOKEN_PREFIX)) {
            return Result.success(null);
        }
        AuthUser authUser = authService.getUserInfoByToken(token);
        return Result.success(authUser);
    }

    /**
     * 获取验证码
     *
     * @return Result<ValidateCode>
     */
    @GetMapping(value = "/code")
    public Result<ValidateCode> getValidateCodeCode() {
        return Result.success(authService.getValidateCode());
    }

    public static void main(String[] args) {
        String password = "123456";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
        String enPassword = encoder.encode(password);
        System.out.println(enPassword);
    }

}
