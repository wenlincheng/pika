package com.wenlincheng.pika.trade.enums.delivery;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 履约类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum DeliveryTypeEnum implements IEnum<String> {

    FORWARD("FORWARD", "正向履约单", "正向履约单"),
    RETURN("RETURN", "逆向履约单", "逆向履约单");

    private String value;

    private String name;

    private String help;

    DeliveryTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
