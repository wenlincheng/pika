package com.wenlincheng.pika.message.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 连接加密方案
 *
 * @author : Pikaman
 * @version : 1.0.0
 * @date : 2021/1/24 3:40 下午
 */
@Getter
public enum  EmailSenderSecurityTypeEnum implements IEnum<String> {
    NONE("NONE", "NONE", "SMTP 对话明文完成"),
    STARTTLS("STARTTLS", "TLS(STARTTLS)", "SMTP 开始时使用TLS"),
    SSL("SSL", "SSL/TLS", "SMTP对话通过专用端口SSL/TLS加密 默认端口：465");

    private String value;
    private String name;
    private String help;

    EmailSenderSecurityTypeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
