package com.wenlincheng.pika.promotion.enums.use;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 计算规则库
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum AggregationAlgorithmEnum implements IEnum<String> {

    SUM("SUM", "求和", "求和"),
    AVG("AVG", "求平均值", "求平均值"),
    MAX("MAX", "最大值", "最大值"),
    MIN("MIN", "最小值", "最小值");

    private String value;
    private String name;
    private String help;

    AggregationAlgorithmEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
