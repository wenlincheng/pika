package com.wenlincheng.pika.promotion.enums.use;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 优惠类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum PromotionTypeEnum implements IEnum<String> {

    SHOP_PROMOTION("SHOP_PROMOTION", "店铺活动", "店铺活动"),
    SINGLE_ITEM_COUPON("SINGLE_ITEM_COUPON", "单品优惠券", "单品优惠券"),
    SHOP_COUPON("SHOP_COUPON", "店铺优惠券", "店铺优惠券"),
    PLATFORM_COUPON("PLATFORM_COUPON", "平台优惠券", "平台优惠券"),
    PLATFORM_PROMOTION("PLATFORM_PROMOTION", "平台满减", "平台满减");

    private String value;
    private String name;
    private String help;

    PromotionTypeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
