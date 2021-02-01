package com.wenlincheng.pika.promotion.enums.use;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 优惠券跳转目标
 *
 *  单品优惠券跳转至商品详情页
 *  多商品优惠券跳转至可用券商品列表页
 *  店铺优惠券（商家设置的订单优惠券）跳转至店铺首页
 *  平台优惠券（平台设置的优惠券）跳转至小程序商城首页
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum CouponTargetEnum implements IEnum<String> {

    ITEM_DETAIL("ITEM_DETAIL", "/itemDetail?ruleId=", "商品详情页"),
    ITEM_LIST("ITEM_LIST", "/itemList?ruleId=", "商品列表页"),
    SHOP("SHOP", "/shop?shopId=", "店铺首页"),
    MALL("MALL", "/mall", "商城首页");

    private String value;
    private String name;
    private String help;

    CouponTargetEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
