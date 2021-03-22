package com.wenlincheng.pika.auth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 图片验证码异常
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/3/21 4:46 下午
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
