package com.wenlincheng.pika.item.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * SKU组合方式
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum SkuTypeEnum implements IEnum<String> {

    NORMAL("NORMAL", "普通SKU", "普通SKU"),
    PARENT("PARENT", "父子组合", "父子组合"),
    GROUPING("GROUPING", "组合SKU", "组合SKU")
    ;
    private String value;

    private String name;

    private String help;

    SkuTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
