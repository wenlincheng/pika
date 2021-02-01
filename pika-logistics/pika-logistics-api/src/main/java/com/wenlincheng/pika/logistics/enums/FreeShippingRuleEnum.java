package com.wenlincheng.pika.logistics.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 免邮费规则
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum FreeShippingRuleEnum implements IEnum<String> {

    BY_QUANTITY("QUANTITY", "件数", "件数"),
    BY_AMOUNT("AMOUNT", "金额", "金额"),
    BY_QUANTITY_AMOUNT("QUANTITY_AMOUNT", "件数+金额", "件数+金额")
    ;

    private String value;

    private String name;

    private String help;

    FreeShippingRuleEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
