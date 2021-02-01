package com.wenlincheng.pika.promotion.enums.rule;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 发放/领用条件
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum ReceiveConditionEnum implements IEnum<String> {

    NUM_LIMIT("NUM_LIMIT", "数量限制", "领取/发放数量限制"),
    TIME_LIMIT("TIME_LIMIT", "时间频率限制", "领取/发放时间频率限制"),
    AMOUNT_LIMIT("AMOUNT_LIMIT", "金额限制", "领取/发放金额限制");

    private String value;
    private String name;
    private String help;

    ReceiveConditionEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
