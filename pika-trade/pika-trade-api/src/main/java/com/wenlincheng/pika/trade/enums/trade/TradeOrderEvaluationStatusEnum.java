package com.wenlincheng.pika.trade.enums.trade;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 订单评价状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum TradeOrderEvaluationStatusEnum implements IEnum<String> {

    WAIT("WAIT", "待评价", "待评价"),
    EVALUATED("EVALUATED", "已评价", "已评价"),
    ADDITIONAL("ADDITIONAL", "已追评", "已追评"),
    ;

    private String value;

    private String name;

    private String help;

    TradeOrderEvaluationStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
