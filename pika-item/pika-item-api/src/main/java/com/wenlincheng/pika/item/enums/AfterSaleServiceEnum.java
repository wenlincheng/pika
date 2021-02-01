package com.wenlincheng.pika.item.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 售后服务
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum AfterSaleServiceEnum implements IEnum<String> {
    AS_INVOICE("AS_INVOICE", "提供发票", "提供发票"),
    AS_REVERSE("AS_REVERSE", "支持退换货", "支持退换货");

    private String value;

    private String name;

    private String help;

    AfterSaleServiceEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
