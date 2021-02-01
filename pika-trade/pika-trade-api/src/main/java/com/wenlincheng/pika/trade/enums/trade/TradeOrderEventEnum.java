package com.wenlincheng.pika.trade.enums.trade;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 订单状态变更事件
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum TradeOrderEventEnum implements IEnum<String> {

    EVENT_CREATE_ORDER("EVENT_CREATE_ORDER", "创建订单", "创建订单事件"),
    EVENT_PAYMENT("EVENT_PAYMENT", "付款", "付款事件"),
    EVENT_DELIVERY("EVENT_DELIVERY", "发货", "发货事件"),
    EVENT_RECEIVING("EVENT_RECEIVING", "收货", "收货事件"),
    EVENT_SPLIT("EVENT_SPLIT", "拆单", "拆单事件"),
    EVENT_CLOSE("EVENT_CLOSE", "关闭", "关闭事件"),
    EVENT_FINISH("EVENT_FINISH", "订单完成", "订单完成事件"),
    EVENT_UPDATE_INFO("EVENT_UPDATE_INFO", "普通信息修改", "普通信息修改"),
    EVENT_UPDATE_STATUS("EVENT_UPDATE_STATUS", "普通状态修改", "普通状态修改（没有实际业务含义）"),
    EVENT_TEMP_RELEASE("EVENT_TEMP_RELEASE", "临时放行", "临时放行"),
    ;

    private String value;

    private String name;

    private String help;

    TradeOrderEventEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
