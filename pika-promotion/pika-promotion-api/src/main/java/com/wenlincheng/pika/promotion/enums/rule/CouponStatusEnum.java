package com.wenlincheng.pika.promotion.enums.rule;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 优惠券状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum CouponStatusEnum implements IEnum<String> {

    RECEIVED("RECEIVED", "已领取", "已领取"),
    USED("USED", "已使用", "已使用"),
    CANCELLED("CANCELLED", "已作废", "已作废"),
    EXPIRED("EXPIRED", "已过期", "已过期");

    private String value;
    private String name;
    private String help;

    CouponStatusEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
