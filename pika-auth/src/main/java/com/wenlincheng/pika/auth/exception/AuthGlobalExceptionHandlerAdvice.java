package com.wenlincheng.pika.auth.exception;


import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.common.core.exception.DefaultGlobalExceptionHandlerAdvice;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.wenlincheng.pika.auth.exception.AuthErrorCodeEnum.TOKEN_EXPIRED;

/**
 * 全局异常处理
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@RestControllerAdvice
@Slf4j
public class AuthGlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public Result<?> userNotFound(UserNotFoundException ex) {
        log.error(ex.getMessage());
        return Result.fail(ex.getErrorCode());
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public Result<?> tokenExpired(ExpiredJwtException ex) {
        log.warn(ex.getMessage());
        return Result.fail(TOKEN_EXPIRED);
    }
}