package com.wenlincheng.pika.auth.handler;

import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.auth.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.wenlincheng.pika.auth.exception.AuthErrorCodeEnum.UNAUTHORIZED;

/**
 * 未登录处理器
 * 解决匿名用户访问无权限资源时的异常
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtil.out(response, Result.fail(UNAUTHORIZED, "请登录后操作"));
    }
}
