package com.wenlincheng.pika.trade.enums.delivery;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 履约单状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum DeliveryOrderStatusEnum implements IEnum<String> {

    PRE_PARING("PRE_PARING", "备货中", "备货中"),
    WAIT_DELIVER("WAIT_DELIVER", "等待发货", "等待发货"),
    PART_DELIVERED("PART_DELIVERED", "部分发货", "部分发货"),
    DELIVERED("DELIVERED", "已发货", "已发货"),
    SIGNED("SIGNED", "已签收", "已签收"),
    RETURNED("RETURNED", "已退回", "已退回"),
    CLOSED("CLOSED", "订单关闭", "订单关闭"),
    ;

    private String value;
    private String name;
    private String help;

    DeliveryOrderStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
