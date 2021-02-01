package com.wenlincheng.pika.inventory.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 出库状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum OutStockStatusEnum implements IEnum {

    NO_OUT("NO_OUT", "未出库", "未出库"),
    PART_OUT("PART_OUT", "部分出库", "部分出库"),
    OUT("OUT", "已出库", "已出库");

    private String value;
    private String name;
    private String help;

    OutStockStatusEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
