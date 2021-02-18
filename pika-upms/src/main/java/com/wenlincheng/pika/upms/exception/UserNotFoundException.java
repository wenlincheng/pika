package com.wenlincheng.pika.upms.exception;

import com.wenlincheng.pika.common.core.exception.PikaException;
import com.wenlincheng.pika.upms.enums.UpmsErrorCodeEnum;

/**
 * 用户未找到异常
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class UserNotFoundException extends PikaException {
    public UserNotFoundException() {
        super(UpmsErrorCodeEnum.USER_NOT_FOUND);
    }

}
