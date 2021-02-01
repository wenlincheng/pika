package com.wenlincheng.pika.trade.enums.trade;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 订单商品类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum TradeItemTypeEnum implements IEnum<String> {

    ENTITY("ENTITY", "实物", "实物"),
    VIRTUAL("VIRTUAL", "虚拟", "虚拟")
    ;
    private String value;

    private String name;

    private String help;

    TradeItemTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
