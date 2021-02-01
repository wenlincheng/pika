package com.wenlincheng.pika.item.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 产品类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum ProductTypeEnum implements IEnum<String> {

    OUTSOURCING("OUTSOURCING", "外购", "外购"),
    SELF("SELF", "自制", "自制"),
    OUT_SOURCING("OUT_SOURCING", "委外", "委外"),
    CONFIGURATION("CONFIGURATION", "配置", "配置"),
    ASSETS("ASSETS", "资产", "资产"),
    FEATURE("FEATURE", "特征", "特征"),
    EXPENSE("EXPENSE", "费用", "费用"),
    VIRTUAL("VIRTUAL", "虚拟", "虚拟"),
    SERVICE("SERVICE", "服务", "服务"),
    ONCE("ONCE", "一次性", "一次性"),
    MODEL("MODEL", "模型", "模型"),
    SERIES("SERIES", "产品系列", "产品系列"),
    ;

    private String value;

    private String name;

    private String help;

    ProductTypeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
