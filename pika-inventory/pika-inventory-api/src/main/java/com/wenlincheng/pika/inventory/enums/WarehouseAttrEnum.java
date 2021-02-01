package com.wenlincheng.pika.inventory.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 仓库属性
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum WarehouseAttrEnum implements IEnum<String> {

    FINISHED("FINISHED", "成品仓", "成品仓"),
    NON_GOOD("NON_GOOD", "不良品仓", "不良品仓"),
    SCRAP("SCRAP", "次品仓", "次品仓"),
    FRONT("FRONT", "前置仓", "前置仓");

    private String value;

    private String name;

    private String help;

    WarehouseAttrEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
