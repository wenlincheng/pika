package com.wenlincheng.pika.upms.exception;


import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.common.core.exception.DefaultGlobalExceptionHandlerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@RestControllerAdvice
public class UpmsGlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public Result<Object> userNotFound(UserNotFoundException e) {
        log.error("msg:{} exception:{}",e.getMessage(), e.getStackTrace());
        return Result.fail(e);
    }
}