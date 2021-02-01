package com.wenlincheng.pika.logistics.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 价格计算法方式
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum PriceDimensionEnum implements IEnum<String> {

    BY_NUMBER("BY_NUMBER", "按件数", "按件数"),
    BY_WEIGHT("BY_WEIGHT", "按重量", "按重量"),
    BY_VOLUME("BY_VOLUME", "按体积", "按体积")
    ;
    private String value;

    private String name;

    private String help;

    PriceDimensionEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
