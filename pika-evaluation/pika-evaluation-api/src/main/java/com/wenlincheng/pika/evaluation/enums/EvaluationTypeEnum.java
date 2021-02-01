package com.wenlincheng.pika.evaluation.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 评价类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum EvaluationTypeEnum implements IEnum<String> {

    NORMAL("NORMAL", "普通", "正常评价"),
    ADDITIONAL("ADDITIONAL", "追评", "追加评价")
    ;
    private String value;

    private String name;

    private String help;

    EvaluationTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
