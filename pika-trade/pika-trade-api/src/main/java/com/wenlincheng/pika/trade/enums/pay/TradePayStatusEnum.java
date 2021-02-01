package com.wenlincheng.pika.trade.enums.pay;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 支付状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum TradePayStatusEnum implements IEnum<String> {

    WAIT_PAY("WAIT_PAY", "待付款", "待付款"),
    DEPOSIT_PAID("DEPOSIT_PAID", "已付定金", "已付定金"),
    PAID_PART("PAID_PART", "部分支付", "部分支付"),
    PAID("PAID", "支付成功", "支付成功"),
    PAY_FAILED("PAID_FAILED", "支付失败", "支付失败"),
    REFUND_FINISH("REFUND_FINISH", "退款成功", "退款成功"),
    CLOSED("CLOSED", "支付关闭", "支付关闭")
    ;

    private String value;

    private String name;

    private String help;

    TradePayStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
