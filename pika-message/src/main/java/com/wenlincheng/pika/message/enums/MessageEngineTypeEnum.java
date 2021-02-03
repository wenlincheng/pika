package com.wenlincheng.pika.message.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 消息引擎类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum MessageEngineTypeEnum implements IEnum<String> {

    MAIL_SEND("MAIL_SEND", "站内信", "发送站内信"),
    APP_PUSH("PUSH", "APP推送", "推送APP消息"),
    EMAIL_SEND("EMAIL_SEND", "邮件", "发送邮件"),
    SMS_SEND("SMS_SEND", "短信", "发送手机短信"),
    WECHAT_PUSH("WECHAT_PUSH", "微信推送", "微信公众号推送"),
    THIRD_PUSH("THIRD_PUSH","第三方应用推送","第三方应用推送"),
    ;

    private String value;
    private String name;
    private String help;

    MessageEngineTypeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
