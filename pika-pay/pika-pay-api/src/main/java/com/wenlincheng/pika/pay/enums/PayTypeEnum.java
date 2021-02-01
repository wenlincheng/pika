package com.wenlincheng.pika.pay.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 支付类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum PayTypeEnum implements IEnum<String> {

    TRADE_PAY("TRADE_PAY", "交易支付", "交易支付"),
    TRADE_REFUND("TRADE_REFUND", "交易退款", "交易退款"),
    ;
    private String value;

    private String name;

    private String help;

    PayTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
