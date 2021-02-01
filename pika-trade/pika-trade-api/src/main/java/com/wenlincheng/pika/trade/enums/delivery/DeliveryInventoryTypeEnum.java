package com.wenlincheng.pika.trade.enums.delivery;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 库存发货类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum DeliveryInventoryTypeEnum implements IEnum<String> {

    NO_INVENTORY("NO_INVENTORY", "无库存发货", "无库存发货"),
    TO_INVENTORY("TO_INVENTORY", "发货推送至仓库", "发货推送至仓库")
    ;
    private String value;

    private String name;

    private String help;

    DeliveryInventoryTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
