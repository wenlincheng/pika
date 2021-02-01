package com.wenlincheng.pika.trade.enums.pay;

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
public enum TradePayTypeEnum implements IEnum<String> {

    PAY("PAY", "支付", "支付"),
    MERGE_PAY("MERGE_PAY", "合并付款", "合并付款"),
    ;
    private String value;

    private String name;

    private String help;

    TradePayTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
