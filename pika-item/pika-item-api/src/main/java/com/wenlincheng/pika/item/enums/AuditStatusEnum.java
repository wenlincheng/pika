package com.wenlincheng.pika.item.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 审核状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum AuditStatusEnum implements IEnum<String> {
    PENDING_AUDIT("PENDING_AUDIT", "待审核", "待审核"),
    AUDITING("AUDITING", "审核中", "审核中"),
    SUCCESS("SUCCESS", "审核通过", "审核通过"),
    FAILURE("FAILURE", "审核不通过", "审核不通过"),
    REDIRECT("REDIRECT", "已转交", "已转交"),
    CANCEL("CANCEL", "已撤销", "已撤销");

    private String value;

    private String name;

    private String help;

    AuditStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
