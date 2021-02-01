package com.wenlincheng.pika.promotion.enums.rule;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 规则使用类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum RuleInstanceUseTypeEnum implements IEnum<String> {
    GRANT("GRANT", "发放", "发放"),
    CONSUME("CONSUME", "消费", "消费"),
    FREEZE("FREEZE", "冻结", "冻结"),
    EXPIRE("EXPIRE", "过期", "过期");

    private String value;
    private String name;
    private String help;

    RuleInstanceUseTypeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
