package com.wenlincheng.pika.common.leaf.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 序列号类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum SequenceTypeEnum implements IEnum<String> {

    SEQ("SEQ", "SEQ", "自增流水号"),
    ORDERLY_SEQ("ORDERLY_SEQ", "ORDERLY_SEQ", "自增强有序流水号"),
    DATE_SEQ("DATE_SEQ", "DATE_SEQ", "日期流水号"),
    DATE_ORDERLY_SEQ("DATE_ORDERLY_SEQ", "DATE_ORDERLY_SEQ", "日期强有序流水号"),
    DATE("DATE", "DATE", "日期"),
    UUID("UUID", "UUID", "UUID"),
    DISTRIBUTION("DISTRIBUTION", "分布式ID", "分布式ID"),
    ;

    private String value;
    private String name;
    private String help;

    SequenceTypeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }

}
