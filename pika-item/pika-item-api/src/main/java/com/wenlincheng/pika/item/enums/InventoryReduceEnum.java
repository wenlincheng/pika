package com.wenlincheng.pika.item.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 扣减库存策略
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum InventoryReduceEnum implements IEnum<String> {

    NONE("NONE", "不扣减库存", "不扣减库存"),
    BY_ORDER("BY_ORDER", "下单减库存", "下单减库存"),
    BY_PAID("BY_PAID", "付款扣减库存", "付款扣减库存"),
    ;
    private String value;

    private String name;

    private String help;

    InventoryReduceEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
