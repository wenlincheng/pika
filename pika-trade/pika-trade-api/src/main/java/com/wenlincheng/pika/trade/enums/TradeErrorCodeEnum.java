package com.wenlincheng.pika.trade.enums;

import com.wenlincheng.pika.common.core.exception.ErrorCode;
import com.wenlincheng.pika.common.core.exception.ErrorTypeEnum;
import lombok.Getter;

/**
 * 交易中心错误码
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum TradeErrorCodeEnum implements ErrorCode {

    PLACE_ORDER_ERROR(ErrorTypeEnum.BIZ_ERROR,50100, "下单失败"),
    ;

    private ErrorTypeEnum type;
    private int code;
    private String msg;

    TradeErrorCodeEnum(ErrorTypeEnum type, int code, String msg) {
        this.type = type;
        this.code = code;
        this.msg = msg;
    }
}
