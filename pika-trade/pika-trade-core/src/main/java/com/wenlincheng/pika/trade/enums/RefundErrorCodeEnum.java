package com.wenlincheng.pika.trade.enums;

import com.wenlincheng.pika.common.core.exception.ErrorCode;
import com.wenlincheng.pika.common.core.exception.ErrorTypeEnum;
import lombok.Getter;

/**
 * 退款业务异常枚举
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum RefundErrorCodeEnum implements ErrorCode {

    ERROR_REFUND_MONEY(ErrorTypeEnum.BIZ_ERROR,440018, "退款失败,退款金额大于能退金额,请重新输入"),
    ERROR_REFUND_FAIL(ErrorTypeEnum.BIZ_ERROR,440007, "订单退款失败"),
    ;

    private ErrorTypeEnum type;
    private int code;
    private String msg;

    RefundErrorCodeEnum(ErrorTypeEnum type, int code, String msg) {
        this.type = type;
        this.code = code;
        this.msg = msg;
    }
}
