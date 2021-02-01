package com.wenlincheng.pika.promotion.enums.coupon;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 优惠券对象所有者
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum CouponOwnerEnum implements IEnum<String> {

    PLATFORM("PLATFORM", "平台", "平台"),
    SHOP("SHOP", "店铺", "店铺"),
    ITEM("ITEM", "商品", "商品");

    private String value;
    private String name;
    private String help;

    CouponOwnerEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
