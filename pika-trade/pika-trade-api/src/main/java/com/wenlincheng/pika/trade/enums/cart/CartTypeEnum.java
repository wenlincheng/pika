package com.wenlincheng.pika.trade.enums.cart;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 购物车类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum CartTypeEnum implements IEnum<String> {

    NORMAL("NORMAL", "普通订单", "普通订单"),
    TRIAL("TRIAL", "试用装订单", "试用装订单"),
    FREE_GOODS("FREE_GOODS", "免费商品订单", "免费商品订单"),
    INTERNAL("INTERNAL", "内部订单", "内部订单"),
    ;

    private String value;
    private String name;
    private String help;

    CartTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
