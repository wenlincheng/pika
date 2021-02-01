package com.wenlincheng.pika.common.core.exception;

import lombok.Getter;

/**
 * 基础异常
 *
 * @author  wenlincheng
 * @date    2019/11/24 12:37 下午
 * @version 1.0
 */
@Getter
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -8264841079576589792L;

    /**
     * 错误类型
     */
    private final ErrorCode errorCode;

    /**
     * 系统异常
     */
    public BaseException() {
        this.errorCode = SystemErrorCodeEnum.SYSTEM_ERROR;
    }

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }


}
