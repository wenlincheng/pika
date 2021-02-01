package com.wenlincheng.pika.item.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 物流方式
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum PickupTypeEnum implements IEnum {

    LOGISTICS("LOGISTICS", "快递", "快递"),
    SELF("SELF", "自提", "自提"),
    ;
    private String value;

    private String name;

    private String help;

    PickupTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
