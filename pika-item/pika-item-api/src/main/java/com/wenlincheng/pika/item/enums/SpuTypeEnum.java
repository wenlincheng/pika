package com.wenlincheng.pika.item.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * SPU类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum SpuTypeEnum implements IEnum<String> {

    FINISHED("FINISHED", "成品", "成品"),
    SEMI_FINISHED("SEMI_FINISHED", "半成品", "半成品"),
    MATERIAL("MATERIAL", "原材料", "原材料")
    ;
    private String value;

    private String name;

    private String help;

    SpuTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
