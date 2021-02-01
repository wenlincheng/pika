package com.wenlincheng.pika.promotion.enums.activity;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 活动范围
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum ActivityScopeEnum implements IEnum<String> {

    SHOP_REPORT("SHOP_REPORT", "全店提报", "全店提报"),
    ITEM_REPORT("ITEM_REPORT", "商品提报", "商品提报");

    private String value;
    private String name;
    private String help;

    ActivityScopeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
