package com.wenlincheng.pika.item.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 类目类别
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum CategoryTypeEnum implements IEnum<String> {

    PLATFORM("PLATFORM", "平台类目", "平台类目"),
    SHOP("SHOP", "店铺类目", "店铺类目"),
    CUSTOM("CUSTOM", "自定义类目", "自定义类目")
    ;
    private String value;

    private String name;

    private String help;

    CategoryTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
