package com.wenlincheng.pika.trade.enums.trade;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 售后状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum TradeReverseStatusEnum implements IEnum<String> {

    REVERSING("REVERSING", "售后中", "售后中"),
    REVERSED("REVERSED", "售后完成", "售后完成"),
    ;

    private String value;

    private String name;

    private String help;

    TradeReverseStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
