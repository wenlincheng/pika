package com.wenlincheng.pika.common.resource.file.exception;

/**
 * 全局文件异常
 *
 * @author  wenlincheng
 * @date  2019-09-06 13:21
 * @version 1.0
 */
public class GlobalFileException extends RuntimeException {
    public GlobalFileException() {
        super();
    }

    public GlobalFileException(String message) {
        super(message);
    }

    public GlobalFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlobalFileException(Throwable cause) {
        super(cause);
    }

    protected GlobalFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
