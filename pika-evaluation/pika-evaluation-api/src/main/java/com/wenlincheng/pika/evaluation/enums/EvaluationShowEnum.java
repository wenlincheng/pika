package com.wenlincheng.pika.evaluation.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 评价展示类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum EvaluationShowEnum implements IEnum<String> {

    SHOW("SHOW", "展示", "展示评价"),
    HIDE("HIDE", "置底", "置底评价")
    ;
    private String value;

    private String name;

    private String help;

    EvaluationShowEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
