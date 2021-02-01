package com.wenlincheng.pika.inventory.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 三无包裹关联状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum AnonymousPkgStatusEnum implements IEnum<String> {

    RELATION("RELATION", "已关联", "已关联"),
    NO_RELATION("NO_RELATION", "未关联", "未关联");

    private String value;
    private String name;
    private String help;

    AnonymousPkgStatusEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
