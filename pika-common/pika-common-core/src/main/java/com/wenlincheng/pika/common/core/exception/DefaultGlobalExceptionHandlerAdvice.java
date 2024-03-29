package com.wenlincheng.pika.common.core.exception;


import com.wenlincheng.pika.common.core.base.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

/**
 * 全局异常处理
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
public class DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public <E>Result<E> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("msg:{} exception:{}",e.getMessage(), e);
        return Result.fail(SystemErrorCodeEnum.ARGUMENT_NOT_VALID);
    }

    @ExceptionHandler(value = {MultipartException.class})
    public <E>Result<E> uploadFileLimitException(MultipartException e) {
        log.error("msg:{} exception:{}",e.getMessage(), e);
        return Result.fail(SystemErrorCodeEnum.UPLOAD_FILE_SIZE_LIMIT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Result<String> serviceException(MethodArgumentNotValidException e) {
        log.error("msg:{} exception:{}",e.getMessage(), e);
        return Result.fail(SystemErrorCodeEnum.ARGUMENT_NOT_VALID, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = {DuplicateKeyException.class})
    public <E>Result<E> duplicateKeyException(DuplicateKeyException e) {
        log.error("msg:{} exception:{}",e.getMessage(), e);
        return Result.fail(SystemErrorCodeEnum.DUPLICATE_PRIMARY_KEY);
    }

    @ExceptionHandler(value = {PikaException.class})
    public <E>Result<E> pikaException(PikaException e) {
        log.error("msg:{} exception:{}",e.getMessage(), e);
        return Result.fail(e);
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public <E>Result<E> exception(Exception e) {
        log.error("msg:{} exception:{}", e.getMessage(), e);
        return Result.fail();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public <E>Result<E> throwable() {
        return Result.fail();
    }
}