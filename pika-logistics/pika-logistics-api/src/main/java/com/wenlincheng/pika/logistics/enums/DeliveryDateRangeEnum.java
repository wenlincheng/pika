package com.wenlincheng.pika.logistics.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 交付期限
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum DeliveryDateRangeEnum implements IEnum<String> {

    IN_ONE_DAY("IN_ONE_DAY", "1天内", "1天内"),
    IN_TWO_DAYS("IN_TWO_DAYS", "2天内", "2天内");

    private String value;

    private String name;

    private String help;

    DeliveryDateRangeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
