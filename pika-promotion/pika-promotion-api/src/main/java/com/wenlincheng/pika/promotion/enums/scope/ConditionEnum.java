package com.wenlincheng.pika.promotion.enums.scope;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 使用条件
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum ConditionEnum implements IEnum<String> {

    NO_LIMIT("NO_LIMIT", "不限制", "不限制"),
    AMOUNT("AMOUNT", "满金额", "满金额"),
    NUMS("NUMS", "满件数", "满件数");

    private String value;
    private String name;
    private String help;

    ConditionEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
