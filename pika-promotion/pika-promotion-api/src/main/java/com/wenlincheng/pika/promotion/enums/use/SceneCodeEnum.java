package com.wenlincheng.pika.promotion.enums.use;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 优惠计算场景
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum SceneCodeEnum implements IEnum<String> {

    SINGLE_TIEM("SINGLE","单个商品", "单个商品"),
    MULTIPLE_ITEM("MULTIPLE","多个商品", "多个商品"),
    TRADE_ORDER("TRADE_ORDER","订单", "订单");

    private String value;
    private String name;
    private String help;

    SceneCodeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
