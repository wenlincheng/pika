package com.wenlincheng.pika.promotion.enums.rule;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 规则状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum RuleStatusEnum implements IEnum<String> {

    DRAFT("DRAFT", "草稿", "草稿"),
    PUBLICITY("PUBLICITY", "公示中", "公示中"),
    SIGN_UP("SIGNUP", "报名中", "报名中"),
    WARM_UP("WARMUP", "预热中", "预热中"),
    REVIEW("REVIEW", "审核中", "审核中"),
    PREPARE("PREPARE", "未开始", "未开始"),
    RELEASE("RELEASE", "已发布", "已发布"),
    PROCESSING("PROCESSING", "进行中", "进行中"),
    STOP_SEND("STOP_SEND", "停止发放", "停止发放"),
    FINISH("FINISH", "已结束", "已结束");

    private String value;
    private String name;
    private String help;

    RuleStatusEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
