package com.wenlincheng.pika.trade.enums.reverse;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 买家售后单状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum BuyerReverseStatusEnum implements IEnum<String> {

    APPLYING("APPLYING", "待审核", "待审核"),
    REJECT("REJECT", "审核驳回", "审核驳回"),
    WAIT_SEND_BACK("BUYER_SEND_BACK", "待用户寄回", "待用户寄回"),
    WAIT_RECEIVED("WAIT_RECEIVED", "待商家签收", "待商家签收"),
    REFUSE_RECEIVED("REFUSE_RECEIVED", "商家拒绝签收", "商家拒绝签收"),
    REFUNDING("REFUNDING", "退款中", "退款中"),
    DONE("DONE", "退款完成", "退款完成"),
    CLOSE("CLOSE", "退款关闭", "退款关闭"),
    ;
    private String value;

    private String name;

    private String help;

    BuyerReverseStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
