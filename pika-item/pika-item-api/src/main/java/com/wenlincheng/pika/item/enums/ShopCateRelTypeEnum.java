package com.wenlincheng.pika.item.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 店铺导航关联类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum ShopCateRelTypeEnum implements IEnum<String> {

    ITEM("ITEM", "商品", "商品"),
    LABEL("LABEL", "标签", "标签"),
    CUSTOM_PAGE("CUSTOM_PAGE", "自定义页面", "自定义页面")
    ;
    private String value;

    private String name;

    private String help;

    ShopCateRelTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
