package com.wenlincheng.pika.inventory.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 库存变动方向
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum DirectEnum implements IEnum<String> {

    IN("IN", "入库", "入库"),
    OUT("OUT", "出库", "出库"),
    SELF("SELF", "自操作", "自操作(冻结转可用)");

    private String value;

    private String name;

    private String help;

    DirectEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
