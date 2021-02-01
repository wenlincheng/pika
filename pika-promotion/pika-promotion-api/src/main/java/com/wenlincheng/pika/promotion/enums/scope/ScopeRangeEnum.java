package com.wenlincheng.pika.promotion.enums.scope;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 作用域范围
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum ScopeRangeEnum implements IEnum<String> {

    INCLUDE("INCLUDE", "限定", "限定"),
    EXCLUDE("EXCLUDE", "排除", "排除"),;

    private String value;
    private String name;
    private String help;

    ScopeRangeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
