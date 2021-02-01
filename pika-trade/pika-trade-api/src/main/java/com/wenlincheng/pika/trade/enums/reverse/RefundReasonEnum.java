package com.wenlincheng.pika.trade.enums.reverse;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 退款原因
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum RefundReasonEnum implements IEnum<String> {

    UNNECESSARY("UNNECESSARY", "多买/买错/不想要", "多买/买错/不想要"),
    NO_EXPRESS_RECORDS("NO_EXPRESS_RECORDS", "快递无记录", "快递无记录"),
    EMPTY_PACKAGE("EMPTY_PACKAGE", " 少货/空包裹", " 少货/空包裹"),
    DELIVERY_TIMEOUT("DELIVERY_TIMEOUT", "未按约定时间发货", "未按约定时间发货"),
    EXPRESS_TIMEOUT("EXPRESS_TIMEOUT", "快递一直未送达", "快递一直未送达"),
    OTHER("OTHER", "其他", "其他");

    private String value;

    private String name;

    private String help;

    RefundReasonEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
