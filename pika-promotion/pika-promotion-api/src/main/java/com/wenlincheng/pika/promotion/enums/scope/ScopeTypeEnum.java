package com.wenlincheng.pika.promotion.enums.scope;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 使用作用域
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum ScopeTypeEnum implements IEnum<String> {

    SHOP_CODE("SHOP_CODE", "店铺", "店铺"),
    ITEM_ID("ITEM_ID", "商品", "商品"),
    SKU_ID("SKU_ID", "SKU", "SKU"),
    LABEL_ID("LABEL_ID", "商品标签", "商品标签"),
    CATEGORY_ID("CATEGORY_ID", "类目", "类目"),
    TIME_DURATION("TIME_DURATION", "时间段", "时间段");

    private String value;
    private String name;
    private String help;

    ScopeTypeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
