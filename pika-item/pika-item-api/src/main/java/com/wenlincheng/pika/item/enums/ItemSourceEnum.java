package com.wenlincheng.pika.item.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 商品来源
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum ItemSourceEnum implements IEnum<String> {

    SPU("SPU", "产品库", "产品库"),
    OTHER("OTHER", "其他途径", "其他途径")
    ;
    private String value;

    private String name;

    private String help;

    ItemSourceEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
