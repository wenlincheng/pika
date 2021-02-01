package com.wenlincheng.pika.trade.enums.reverse;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 沟通结果
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum CommunicateResultEnum implements IEnum<String> {

    BUYER_DISAGREE("BUYER_DISAGREE", "和商家沟通失败", "和商家沟通失败"),
    SELLER_DISAGREE("SELLER_DISAGREE", "和买家沟通失败", "和买家沟通失败"),
    BUYER_AGREE("BUYER_AGREE", "和买家达成一致", "和买家达成一致"),
    SELLER_AGREE("SELLER_AGREE", "和商家达成一致", "和商家达成一致"),
    BOTH_AGREE("BOTH_AGREE", "双方达成一致", "双方达成一致"),
    ;

    private String value;

    private String name;

    private String help;

    CommunicateResultEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
