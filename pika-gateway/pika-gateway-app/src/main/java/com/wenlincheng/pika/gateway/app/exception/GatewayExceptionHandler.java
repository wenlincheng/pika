package com.wenlincheng.pika.gateway.app.exception;

import com.wenlincheng.pika.common.core.base.vo.Result;
import io.netty.channel.ConnectTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 自动异常处理
 * @author wenlincheng
 */
@Slf4j
public class GatewayExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<?> handle(NotFoundException ex) {
        log.error("not found exception:{}", ex.getMessage());
        return Result.fail(GatewayErrorCodeEnum.GATEWAY_NOT_FOUND_SERVICE);
    }

    @ExceptionHandler(value = {ResponseStatusException.class})
    public Result<?> handle(ResponseStatusException ex) {
        log.error("response status exception:{}", ex.getMessage());
        return Result.fail(GatewayErrorCodeEnum.GATEWAY_ERROR);
    }

    @ExceptionHandler(value = {ConnectTimeoutException.class})
    public Result<?> handle(ConnectTimeoutException ex) {
        log.error("connect timeout exception:{}", ex.getMessage());
        return Result.fail(GatewayErrorCodeEnum.GATEWAY_CONNECT_TIME_OUT);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handle(RuntimeException ex) {
        log.error("runtime exception:{}", ex.getMessage());
        return Result.fail();
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handle(Exception ex) {
        log.error("exception:{}", ex.getMessage());
        return Result.fail();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handle(Throwable throwable) {
        Result<?> result = Result.fail();
        log.error("exception:{}", throwable.getMessage());
        if (throwable instanceof NotFoundException) {
            result = handle((NotFoundException) throwable);
        } else if (throwable instanceof ResponseStatusException) {
            result = handle((ResponseStatusException) throwable);
        } else if (throwable instanceof ConnectTimeoutException) {
            result = handle((ConnectTimeoutException) throwable);
        } else if (throwable instanceof RuntimeException) {
            result = handle((RuntimeException) throwable);
        } else if (throwable instanceof Exception) {
            result = handle((Exception) throwable);
        }
        return result;
    }
}
