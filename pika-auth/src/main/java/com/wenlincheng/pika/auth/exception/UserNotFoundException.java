package com.wenlincheng.pika.auth.exception;

import com.wenlincheng.pika.common.core.exception.PikaException;

/**
 * 用户未找到异常
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class UserNotFoundException extends PikaException {
    public UserNotFoundException() {
        super(AuthErrorCodeEnum.USER_NOT_FOUND);
    }
}
