package com.wenlincheng.pika.trade.enums.reverse;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 退款单处理状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum RefundHandleStatusEnum implements IEnum<String> {

    WAIT_HANDLE("WAIT_HANDLE", "待处理", "待处理"),
    HANDLING("HANDLING", "处理中", "处理中"),
    ACCEPT("ACCEPT", "同意退款", "同意退款"),
    REJECT("REJECT", "拒绝退款", "拒绝退款"),
    ;

    private String value;

    private String name;

    private String help;

    RefundHandleStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
