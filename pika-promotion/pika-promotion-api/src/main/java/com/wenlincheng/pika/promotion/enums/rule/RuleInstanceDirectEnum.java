package com.wenlincheng.pika.promotion.enums.rule;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 规则使用方向
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum RuleInstanceDirectEnum implements IEnum<String> {

    INCREASE_USABLE("INCREASE_USABLE", "增加可用", "增加可用"),
    REDUCE_USABLE("REDUCE_USABLE", "减少可用", "减少可用"),
    FREEZE_USABLE("FREEZE_USABLE", "冻结可用", "冻结可用"),
    CANCEL_FREEZE("CANCEL_FREEZE", "释放冻结", "释放冻结"),
    REDUCE_FREEZE("REDUCE_FREEZE", "扣除冻结", "扣除冻结");

    private String value;
    private String name;
    private String help;

    RuleInstanceDirectEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
