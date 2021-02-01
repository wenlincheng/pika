package com.wenlincheng.pika.trade.enums.delivery;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 履约单发货状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum DeliveryStatusEnum implements IEnum<String> {

    WAIT_DELIVERY("WAIT_DELIVERY", "待发货", "待发货"),
    PART_DELIVERY("PART_DELIVERY", "部分发货", "部分发货"),
    DELIVERED("DELIVERED", "已发货", "已发货"),
    ;

    private String value;

    private String name;

    private String help;

    DeliveryStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
