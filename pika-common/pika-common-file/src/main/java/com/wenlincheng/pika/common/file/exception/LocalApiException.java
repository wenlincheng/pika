package com.wenlincheng.pika.common.file.exception;

/**
 * 本地文件异常
 *
 * @author  wenlincheng
 * @date  2019-09-06 13:21
 * @version 1.0
 */
public class LocalApiException extends GlobalFileException {
    public LocalApiException() {
        super();
    }

    public LocalApiException(String message) {
        super(message);
    }

    public LocalApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocalApiException(Throwable cause) {
        super(cause);
    }
}
