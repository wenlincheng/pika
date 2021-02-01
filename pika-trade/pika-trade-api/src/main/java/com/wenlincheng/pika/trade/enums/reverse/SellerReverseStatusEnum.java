package com.wenlincheng.pika.trade.enums.reverse;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 商家售后单状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum SellerReverseStatusEnum implements IEnum<String> {

    APPLYING("APPLYING", "提交申请", "提交申请"),
    SELLER_PROCESS("SELLER_PROCESS", "商家审核", "商家审核"),
    BUYER_SEND_BACK("BUYER_SEND_BACK", "买家寄回商品", "买家寄回商品"),
    MERCHANT_REFUND("MERCHANT_REFUND", "商家退款", "商家退款"),
    FINISH("FINISH", "退款完成", "退款完成"),
    ;
    private String value;

    private String name;

    private String help;

    SellerReverseStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
