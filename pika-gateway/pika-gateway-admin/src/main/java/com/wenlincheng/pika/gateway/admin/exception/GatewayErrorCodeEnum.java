package com.wenlincheng.pika.gateway.admin.exception;

import com.wenlincheng.pika.common.core.exception.ErrorCode;
import com.wenlincheng.pika.common.core.exception.ErrorTypeEnum;
import lombok.Getter;

/**
 * 异常类型枚举
 *
 * @author Pikaman
 * @version 1.0.0
 * @date : 2021/1/1 10:10 上午
 */
@Getter
public enum GatewayErrorCodeEnum implements ErrorCode {

    GATEWAY_NOT_FOUND_SERVICE(ErrorTypeEnum.SYSTEM_ERROR,10404, "服务未就绪"),
    GATEWAY_ERROR(ErrorTypeEnum.SYSTEM_ERROR,10500, "网关异常"),
    GATEWAY_CONNECT_TIME_OUT(ErrorTypeEnum.SYSTEM_ERROR,10503, "网关超时"),
    GATEWAY_UNAUTHORIZED(ErrorTypeEnum.SYSTEM_ERROR,10401, "没有访问权限"),
    GATEWAY_LIMIT(ErrorTypeEnum.SYSTEM_ERROR,10402, "请求超过限制，请稍后再试");

    private ErrorTypeEnum type;
    private int code;
    private String msg;

    GatewayErrorCodeEnum(ErrorTypeEnum type, int code, String msg) {
        this.type = type;
        this.code = code;
        this.msg = msg;
    }
}
