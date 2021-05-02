package com.wenlincheng.pika.common.core.enums;

import lombok.Getter;

/**
 * 数据状态枚举
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/4/25 2:28 下午
 */
@Getter
public enum DataStatusEnum implements IEnum<String> {

    ENABLE("ENABLE", "启用", "启用"),
    DISABLE("DISABLE", "禁用", "禁用");

    private String value;
    private String name;
    private String help;

    DataStatusEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
