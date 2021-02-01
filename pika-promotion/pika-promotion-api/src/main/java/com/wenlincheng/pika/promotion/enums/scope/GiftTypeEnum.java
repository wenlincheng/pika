package com.wenlincheng.pika.promotion.enums.scope;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 赠品类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum GiftTypeEnum implements IEnum<String> {

    COUPON("COUPON", "优惠券", "优惠券"),
    SKU("SKU", "商品SKU", "商品SKU");

    private String value;
    private String name;
    private String help;

    GiftTypeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
