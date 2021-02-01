package com.wenlincheng.pika.trade.enums.reverse;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 售后类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum ReverseTypeEnum implements IEnum<String> {

    CANCEL("CANCEL", "取消", "取消订单"),
    RETURN_TRANSFER("RETURN_TRANSFER", "退换货", "退换货"),
    REFUND_MONEY("REFUND_MONEY", "仅退款", "仅退款"),
    COMPENSATION("COMPENSATION", "赔偿", "赔偿"),
    RETURN_MONEY_GOODS("RETURN_MONEY_GOODS", "退货退款", "退货退款"),
    ;

    private String value;

    private String name;

    private String help;

    ReverseTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
