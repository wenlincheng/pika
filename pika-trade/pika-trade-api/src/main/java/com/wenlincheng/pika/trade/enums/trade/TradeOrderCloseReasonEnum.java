package com.wenlincheng.pika.trade.enums.trade;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 关闭原因
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum TradeOrderCloseReasonEnum implements IEnum<String> {

    TIMEOUT("TIMEOUT", "超时关闭", "超时关闭"),
    REVERSE("REVERSE", "售后关闭", "售后关闭"),

    // 买家关闭
    BUYER_NOT_WANT("BUYER_NOT_WANT", "不想要了", "不想要了"),
    BUYER_ORDER_NOT_WANT_BUY("BUYER_ORDER_NOT_WANT_BUY", "不想买了", "不想买了"),
    BUYER_FUND_OTHER_ITEM_LESS_PRICE("BUYER_FUND_OTHER_ITEM_LESS_PRICE", "发现用款更低价", "发现用款更低价"),
    BUYER_ITEM_ATTR_ERROR("BUYER_ITEM_ATTR_ERROR", "商品买错了（颜色、尺寸买错了）", "商品买错了（颜色、尺寸买错了）"),
    BUYER_COUPON_FORGET_USE("BUYER_COUPON_FORGET_USE", "忘记使用优惠券", "忘记使用优惠券"),
    BUYER_ADDRESS_ERROR("BUYER_ADDRESS_ERROR", "地址写错了", "地址写错了"),
    BUYER_DELIVERY_TIME_TOO_LONG("BUYER_DELIVERY_TIME_TOO_LONG", "发货时间太长", "发货时间太长"),
    BUYER_OTHER("BUYER_OTHER", "其他", "其他"),
    ;

    private String value;

    private String name;

    private String help;

    private static final String BUYER_CLOSE_PREFIX = "BUYER_";

    TradeOrderCloseReasonEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
