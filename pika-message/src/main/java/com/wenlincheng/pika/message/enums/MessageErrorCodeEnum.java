package com.wenlincheng.pika.message.enums;

import com.wenlincheng.pika.common.core.exception.ErrorCode;
import com.wenlincheng.pika.common.core.exception.ErrorTypeEnum;
import lombok.Getter;

/**
 * 消息错误码
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum MessageErrorCodeEnum implements ErrorCode {

    SMS_SEND_ERROR(ErrorTypeEnum.BIZ_ERROR,40100, "短信发送失败"),
    EMAIL_SEND_ERROR(ErrorTypeEnum.BIZ_ERROR,40101, "邮件发送失败"),
    ;

    private ErrorTypeEnum type;
    private int code;
    private String msg;

    MessageErrorCodeEnum(ErrorTypeEnum type, int code, String msg) {
        this.type = type;
        this.code = code;
        this.msg = msg;
    }
}
