package com.wenlincheng.pika.trade.enums.pay;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 支付方式
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum TradePayMethodEnum implements IEnum<String> {

    ONLINE("ONLINE", "在线支付", "在线支付"),
    JIFEI("JIFEI", "积分支付", "积分支付"),
    HONGBAO("HONGBAO", "红包支付", "红包支付"),
    REALTIME("REALTIME", "合同支付", "合同支付"),
    OFFLINE("OFFLINE", "线下支付", "线下支付"),
    COD("COD", "货到付款", "货到付款")
    ;
    private String value;

    private String name;

    private String help;

    TradePayMethodEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
