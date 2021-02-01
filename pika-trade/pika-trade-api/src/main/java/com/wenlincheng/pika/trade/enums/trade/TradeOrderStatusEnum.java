package com.wenlincheng.pika.trade.enums.trade;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 订单状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum TradeOrderStatusEnum implements IEnum<String> {

    WAIT_CHECK("WAIT_CHECK", "待审核", "待审核"),
    WAIT_PAY("WAIT_PAY", "待支付", "待支付"),
    DEPOSIT_PAID("DEPOSIT_PAID", "已付定金", "已付定金"),
    WAIT_ALLOC("WAIT_ALLOC", "待配货", "待配货"),
    WAIT_DELIVER("WAIT_DELIVER", "待发货", "已付款,待发货"),
    PART_DELIVERED("PART_DELIVERED", "部分发货", "部分发货"),
    DELIVERED("DELIVERED", "已发货", "已发货"),
    CLOSED("CLOSED", "订单关闭", "订单关闭"),
    SUCCESS("SUCCESS", "交易成功", "交易成功"),
    REVERSING("REVERSING", "售后中", "售后中"),
    REVERSED("REVERSED", "售后完成", "售后完成"),
    ;

    private String value;

    private String name;

    private String help;

    TradeOrderStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
