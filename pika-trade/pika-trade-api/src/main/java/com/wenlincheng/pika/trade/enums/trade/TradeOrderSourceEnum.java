package com.wenlincheng.pika.trade.enums.trade;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 订单下单方式
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum TradeOrderSourceEnum implements IEnum<String> {

    CART("CART", "购物车", "购物车"),
    ITEM("ITEM", "商品直接下单", "商品直接下单")
    ;
    private String value;

    private String name;

    private String help;

    TradeOrderSourceEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
