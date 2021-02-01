package com.wenlincheng.pika.trade.enums.reverse;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 售后单处理状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum ProcessStatusEnum implements IEnum<String> {

    PENDING_SELLER("PENDING_SELLER", "待卖家审核", "待卖家审核"),
    WAIT_SEND_BACK("WAIT_SEND_BACK", "待用户寄回", "待用户寄回"),
    WAIT_SELLER_RECEIVED("WAIT_SELLER_RECEIVED", "待卖家签收", "待卖家签收"),
    REFUNDING("REFUNDING", "退款中", "退款中"),
    SUCCESS("SUCCESS", "退款成功", "退款成功"),
    CLOSED("CLOSED", "退款关闭", "退款关闭")
    ;
    private String value;

    private String name;

    private String help;

    ProcessStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
