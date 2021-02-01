package com.wenlincheng.pika.common.file.exception;

/**
 * 阿里云OSS异常
 *
 * @author  wenlincheng
 * @date  2019-09-06 13:21
 * @version 1.0
 */
public class OssApiException extends GlobalFileException {

    public OssApiException(String message) {
        super(message);
    }

    public OssApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
