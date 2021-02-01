package com.wenlincheng.pika.item.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 商品属性类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum AttrTypeEnum implements IEnum<String> {

    SALE("SALE", "销售属性", "销售属性"),
    BOXGAUGE("BOXGAUGE", "箱规属性", "箱规属性"),
    NORMAL("NORMAL", "普通属性", "普通属性"),
    LOGISTICS("LOGISTICS", "物流属性", "物流属性"),
    SERVICE("SERVICE", "服务属性", "服务属性"),
    POLICY("POLICY", "政策信息", "政策信息")
    ;

    private String value;
    private String name;
    private String help;

    AttrTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
