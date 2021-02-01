package com.wenlincheng.pika.item.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 商品用途(销售/采购)
 * TODO 实际用途未知
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum ItemPurposeEnum implements IEnum<String> {

    SALE("SALE", "销售", "销售"),
    PURCHASE("PURCHASE", "采购", "采购")
    ;
    private String value;

    private String name;

    private String help;

    ItemPurposeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
