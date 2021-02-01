package com.wenlincheng.pika.promotion.enums.activity;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 活动所属者类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum ActivityOwnerTypeEnum implements IEnum<String> {

    PLATFORM("PLATFORM", "平台", "平台"),
    SELLER("SELLER", "卖家", "卖家");

    private String value;
    private String name;
    private String help;

    ActivityOwnerTypeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
