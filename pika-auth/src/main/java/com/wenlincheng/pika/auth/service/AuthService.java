package com.wenlincheng.pika.auth.service;

import com.wenlincheng.pika.auth.entity.AuthUser;
import com.wenlincheng.pika.auth.entity.ValidateCode;
import io.jsonwebtoken.JwtException;

import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 提供给网关的鉴权接口
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public interface AuthService {

    /**
     * 退出登录
     *
     * @param token 令牌
     * @return boolean
     */
    boolean logout(String token);

    /**
     * 获取token中的用户信息
     *
     * @param token 令牌
     * @return AuthUser
     */
    AuthUser getUserInfoByToken(String token);

    /**
     * 判断是否有权限
     *
     * @param requestWrapper 请求
     * @param uri 请求路径
     * @param method 请求方法
     * @throws JwtException
     * @return AuthUser
     */
    AuthUser authDecide(HttpServletRequestWrapper requestWrapper, String uri, String method) throws JwtException;

    /**
     * 获取验证码
     *
     * @return ValidateCode
     */
    ValidateCode getValidateCode();
}
