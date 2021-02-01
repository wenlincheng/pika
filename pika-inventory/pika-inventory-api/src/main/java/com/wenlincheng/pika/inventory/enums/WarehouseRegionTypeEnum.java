package com.wenlincheng.pika.inventory.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 库区类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum WarehouseRegionTypeEnum implements IEnum<String> {

    GENERAL("GENERAL", "常规库区", "常规库区"),
    SPECIAL("SPECIAL", "特殊库区", "特殊库区");

    private String value;

    private String name;

    private String help;

    WarehouseRegionTypeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
