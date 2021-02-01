package com.wenlincheng.pika.logistics.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 包邮策略(包邮/自费)
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum IsFreeShippingEnum implements IEnum<String> {

    FREE("FREE", "包邮", "包邮"),
    CUSTOM("CUSTOM", "自定义运费", "自定义运费")
    ;
    private String value;

    private String name;

    private String help;

    IsFreeShippingEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
