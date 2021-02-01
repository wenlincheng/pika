package com.wenlincheng.pika.promotion.enums.coupon;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 优惠券日志状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum CouponLogTypeEnum implements IEnum<String> {

    USE("USE", "使用", "使用"),
    BACK("BACK", "回退", "回退");

    private String value;
    private String name;
    private String help;

    CouponLogTypeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
