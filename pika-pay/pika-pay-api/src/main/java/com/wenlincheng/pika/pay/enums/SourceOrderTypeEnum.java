package com.wenlincheng.pika.pay.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 支付单来源单类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum SourceOrderTypeEnum implements IEnum<String> {

    TRADE_ORDER("TRADE_ORDER", "交易订单", "交易订单"),
    STATEMENT("STATEMENT", "对账单", "对账单"),
    ;

    private String value;

    private String name;

    private String help;

    SourceOrderTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
