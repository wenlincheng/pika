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
public enum DiscountTypeEnum implements IEnum<String> {

    DECREASE_MONEY("DECREASE_MONEY", "减金额", "减金额"),
    PERCENT_DISCOUNT("PERCENT_DISCOUNT", "折扣", "折扣"),
    FIX_PRICE("FIX_PRICE", "价格调整", "限时特价"),
    GIFT("GIFT", "赠品", "赠品"),
    FREE_SHIPPING("FREE_SHIPPING", "免邮费", "免邮费");


    private String value;
    private String name;
    private String help;

    DiscountTypeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
