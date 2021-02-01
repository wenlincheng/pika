package com.wenlincheng.pika.promotion.enums.scope;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 赠送类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum GiftMethodEnum implements IEnum<String> {

    ALL("ALL", "全部", "全部"),
    RANDOM("RANDOM", "随机", "随机");

    private String value;
    private String name;
    private String help;

    GiftMethodEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
