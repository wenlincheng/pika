package com.wenlincheng.pika.logistics.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 配运单状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum TmsOrderStatusEnum implements IEnum<String> {

    WAIT_DELIVERY("WAIT_DELIVERY", "待发货", "待发货"),
    DELIVERED("DELIVERED", "已发货", "已发货"),
    ;

    private String value;

    private String name;

    private String help;

    TmsOrderStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
