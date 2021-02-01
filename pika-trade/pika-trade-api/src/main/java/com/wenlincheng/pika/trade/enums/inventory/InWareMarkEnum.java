package com.wenlincheng.pika.trade.enums.inventory;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 入库标识
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum InWareMarkEnum implements IEnum<String> {

    ALL("ALL", "全部入库", "全部入库"),
    NO_MATCH_RECEIVABLE("NO_MATCH_RECEIVABLE", "预收不符", "预收不符"),
    ;

    private String value;

    private String name;

    private String help;

    InWareMarkEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
