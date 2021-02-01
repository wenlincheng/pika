package com.wenlincheng.pika.trade.enums.trade;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 订单类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum TradeOrderTypeEnum implements IEnum<String> {

    SALE("SALE", "销售订单", "销售订单"),
    PRE_SALE("PRE_SALE", "预售订单", "预售订单"),
    AUCTION("AUCTION", "拍卖订单", "拍卖订单"),
    GIFT("GIFT", "礼品订单", "礼品订单"),
    POINT_EXCHANGE("POINT_EXCHANGE", "积分商城订单", "积分商城订单"),
    PAY_OUT("PAY_OUT", "赔付订单", "赔付订单"),
    RETURN_EXCHANGE("RETURN_EXCHANGE", "换货订单", "换货订单"),
    ;

    private String value;

    private String name;

    private String help;

    TradeOrderTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }

}
