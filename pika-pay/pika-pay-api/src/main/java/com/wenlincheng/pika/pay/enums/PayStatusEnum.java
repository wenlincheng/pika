package com.wenlincheng.pika.pay.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 支付状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum PayStatusEnum implements IEnum<String> {

    CREATE("CREATE", "创建成功", "创建成功"),
    PAID("PAID", "支付成功", "支付成功"),
    FAILED("FAILED", "支付失败", "支付失败"),
    CLOSED("CLOSED", "订单关闭", "订单关闭"),
    TIMEOUT("TIMEOUT", "订单超时，可再次发起支付", "订单超时，可再次发起支付")
    ;
    private String value;

    private String name;

    private String help;

    PayStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
