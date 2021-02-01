package com.wenlincheng.pika.promotion.enums.rule;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 领用类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum RuleReceiveTypeEnum implements IEnum<String> {

    ONE_TIME("ONE_TIME", "一次性券", "一次性券"),
    MULTI_TIME("MULTI_TIME", "多次券", "多次券");

    private String value;
    private String name;
    private String help;

    RuleReceiveTypeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
