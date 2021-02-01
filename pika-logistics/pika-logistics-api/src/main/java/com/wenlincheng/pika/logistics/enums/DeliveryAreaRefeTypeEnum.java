package com.wenlincheng.pika.logistics.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 包邮策略(包邮/自费)
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum DeliveryAreaRefeTypeEnum implements IEnum<String> {

    SHIPPING_FEE_TEMPLATE("SHIPPING_FEE_TEMPLATE", "运费模版", "运费模版"),
    FREE_SHIPPING_SETTING("FREE_SHIPPING_SETTING", "包邮设置", "包邮设置")
    ;
    private String value;

    private String name;

    private String help;

    DeliveryAreaRefeTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
