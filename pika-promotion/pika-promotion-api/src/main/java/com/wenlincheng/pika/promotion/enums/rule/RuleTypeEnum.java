package com.wenlincheng.pika.promotion.enums.rule;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 规则类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum RuleTypeEnum implements IEnum<String> {

    COUPON("COUPON", "优惠券", "优惠券"),
    FULL_SUB("FULL_SUB", "满减", "满减"),
    FULL_GWP("FULL_GWP", "满赠", "满赠"),
    SPECIAL_DISCOUNT("SPECIAL_DISCOUNT", "限时折扣", "限时折扣"),
    FULL_FREE_SHIPPING("FULL_FREE_SHIPPING", "满包邮", "满包邮");

    private String value;
    private String name;
    private String help;

    RuleTypeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
