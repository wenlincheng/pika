package com.wenlincheng.pika.evaluation.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 评价筛选类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum EvaluationFilterTypeEnum implements IEnum<String> {

    ALL("ALL", "全部", "全部评价"),
    ADDITIONAL("ADDITIONAL", "追评", "追加评价"),
    PICTURE("PICTURE", "有图", "有图评价"),
    POSITIVE("POSITIVE", "好评", "好评"),
    MEDIUM("MEDIUM", "中评", "中评"),
    NEGATIVE("NEGATIVE", "差评", "差评"),
    ;
    private String value;

    private String name;

    private String help;

    EvaluationFilterTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
