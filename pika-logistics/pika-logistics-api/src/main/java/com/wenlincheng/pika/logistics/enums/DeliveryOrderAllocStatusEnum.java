package com.wenlincheng.pika.logistics.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 订单配货状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum DeliveryOrderAllocStatusEnum implements IEnum<String> {

    WAIT_ALLOC("WAIT_ALLOC", "待配货", "待配货"),
    PART_ALLOC("PART_ALLOC", "部分配货", "部分配货"),
    ALLOCED("ALLOCED", "已配货", "已配货"),
    ;

    private String value;

    private String name;

    private String help;

    DeliveryOrderAllocStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
