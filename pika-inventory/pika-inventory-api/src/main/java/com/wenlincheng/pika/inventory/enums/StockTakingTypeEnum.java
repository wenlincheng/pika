package com.wenlincheng.pika.inventory.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 库存盘点类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum StockTakingTypeEnum implements IEnum<String> {

    INIT("INIT", "初始化库存", "初始化库存"),
    STOCK_TAKING("STOCK_TAKING", "日常盘点", "日常盘点");

    private String value;
    private String name;
    private String help;

    StockTakingTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
