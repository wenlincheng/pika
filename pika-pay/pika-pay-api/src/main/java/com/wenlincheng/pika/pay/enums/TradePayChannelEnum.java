package com.wenlincheng.pika.pay.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 支付渠道
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum TradePayChannelEnum implements IEnum<String> {

    CASH("CASH", "现金", "现金"),
    BANK_TRANSFER("BANK_TRANSFER", "银行转账", "银行转账"),
    BANK_DRAFT("BANK_DRAFT", "银行汇票", "银行汇票"),
    ALIPAY("ALIPAY", "支付宝支付", "支付宝支付"),
    WXPAY("WXPAY", "微信支付", "微信支付"),
    PAYPAL("PAYPAL", "PayPal", "PayPal"),
    WORLD_PAY("WORLD_PAY", "WorldPay", "WorldPay"),
    FUND_PAY("FUNDPAY", "余额支付", "余额支付"),
    SUBSTITUTE_PAY("SUBSTITUTE_PAY", "代收", "代收"),
    BOC("BOC", "中国银行", "中国银行"),
    BCM("BCM", "交通银行", "交通银行"),
    CCB("CCB", "中国建设银行", "中国建设银行"),
    ICBC("ICBC", "中国工商银行", "中国工商银行"),
    ABC("ABC", "中国农业银行", "中国农业银行"),
    PSBC("PSBC", "中国邮政储蓄银行", "中国邮政储蓄银行");

    private String value;
    private String name;
    private String help;

    TradePayChannelEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
