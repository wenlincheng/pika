package com.wenlincheng.pika.trade.enums.reverse;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 退款状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum RefundStatusEnum implements IEnum<String> {

    NO_REFUND("NO_REFUND", "无需退款", "无需退款"),
    WAIT_REFUND("WAIT_REFUND", "等待退款", "等待退款"),
    REFUNDING("REFUNDING", "退款中", "退款中"),
    REFUND_PART("REFUND_PART", "部分退款", "部分退款"),
    REFUND_FAILED("REFUND_FAILED", "退款失败", "退款失败"),
    ALL_REFUND("ALL_REFUND", "退款成功", "退款成功"),
    ;

    private String value;

    private String name;

    private String help;

    RefundStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
