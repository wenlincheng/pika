package com.wenlincheng.pika.inventory.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 仓库类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum WarehouseTypeEnum implements IEnum<String> {

    PHYSICAL("PHYSICAL", "实体仓库", "实体仓库"),
    VIRTUAL("VIRTUAL", "虚拟仓库", "虚拟仓库")
    ;
    private String value;

    private String name;

    private String help;

    WarehouseTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
