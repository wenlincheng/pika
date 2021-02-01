package com.wenlincheng.pika.inventory.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 入库状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum InStockStatusEnum implements IEnum<String> {

    NO_IN("NO_IN", "未入库", "未入库"),
    PART_IN("PART_IN", "部分入库", "部分入库"),
    IN("IN", "已入库", "已入库");

    private String value;

    private String name;

    private String help;

    InStockStatusEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
