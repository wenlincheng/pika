package com.wenlincheng.pika.promotion.enums.activity;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 活动状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum ActivityStatusEnum implements IEnum<String> {

    UNSET("UNSET", "未设置", "未设置"),
    PREPARE("PREPARE", "未开始", "未开始"),
    PROCESSING("PROCESSING", "进行中", "进行中"),
    PAUSE("PAUSE", "已暂停", "已暂停"),
    FINISH("FINISH", "已结束", "已结束"),
    CANCELED("CANCELED", "已作废", "已作废"),
    SETTLED("SETTLED", "已结算", "已结算");

    private String value;
    private String name;
    private String help;

    ActivityStatusEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
