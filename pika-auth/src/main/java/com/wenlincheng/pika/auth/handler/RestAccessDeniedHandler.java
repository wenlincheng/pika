package com.wenlincheng.pika.auth.handler;

import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.auth.utils.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.wenlincheng.pika.auth.exception.AuthErrorCodeEnum.UNAUTHORIZED;

/**
 * 没有访问权限处理器
 * 解决认证过的用户访问无权限资源时的异常
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        ResponseUtil.out(response, Result.fail(UNAUTHORIZED));
    }

}
