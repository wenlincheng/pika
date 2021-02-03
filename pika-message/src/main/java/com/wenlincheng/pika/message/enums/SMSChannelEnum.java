package com.wenlincheng.pika.message.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 短信渠道
 *
 * @author : Pikaman
 * @version : 1.0.0
 * @date : 2021/1/24 9:02 下午
 */
@Getter
public enum SMSChannelEnum implements IEnum<String> {

    ALIYUN("ALIYUN","阿里云","阿里云"),
    ALIYUN_NOTIFY("ALIYUN_NOTIFY","阿里云通知类","阿里云通知类"),
    CUSTOM("CUSTOM","自定义","自定义"),
    ;

    private String value;
    private String name;
    private String help;

    SMSChannelEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
