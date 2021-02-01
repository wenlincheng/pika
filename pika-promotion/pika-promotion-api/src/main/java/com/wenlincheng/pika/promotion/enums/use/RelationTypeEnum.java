package com.wenlincheng.pika.promotion.enums.use;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 互斥类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum RelationTypeEnum implements IEnum<String> {

    AND("AND", "互斥", "互斥"),
    OR("OR", "互容", "互容");

    private String value;
    private String name;
    private String help;

    RelationTypeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
