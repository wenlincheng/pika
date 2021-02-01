package com.wenlincheng.pika.logistics.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 签收状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum DeliveryOrderSignStatusEnum implements IEnum<String> {

    WAIT_SIGN("WAIT_SIGN", "待签收", "待签收"),
    SIGNED("SIGNED", "已签收", "已签收"),
    SIGNED_WAIT_AUDIT("SIGNED_WAIT_AUDIT", "已签收, 待审批", "已签收, 待审批"),
    REJECT_SIGN("REJECT_SIGN", "拒签", "拒签"),
    ;

    private String value;

    private String name;

    private String help;

    DeliveryOrderSignStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
