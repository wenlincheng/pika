package com.wenlincheng.pika.promotion.enums.rule;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 规则模版类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum AlgorithmTypeEnum implements IEnum<String> {

    FULL_REDUCE("FULL_REDUCE", "满减", "fullreduce.drl"),
    AMOUNT_RATE("AMOUNT_RATE", "赠金", "amountrate.drl"),
    AMOUNT_RATE_AND_GOODS("AMOUNT_RATE_AND_GOODS", "赠金兑物", "amountrateandgoods.drl");

    private String value;
    private String name;
    private String help;


    AlgorithmTypeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }

}
