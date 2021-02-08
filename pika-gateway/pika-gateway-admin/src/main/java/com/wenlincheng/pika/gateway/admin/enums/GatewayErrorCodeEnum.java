package com.wenlincheng.pika.gateway.admin.enums;

import com.wenlincheng.pika.common.core.exception.ErrorCode;
import com.wenlincheng.pika.common.core.exception.ErrorTypeEnum;
import lombok.Getter;

/**
 * 网关错误枚举
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/2/8 9:57 下午
 */
@Getter
public enum GatewayErrorCodeEnum implements ErrorCode {

    GATEWAY_LOAD_ROUTE_ERROR(ErrorTypeEnum.BIZ_ERROR, 10100, "网关路由加载失败")
    ;

    private ErrorTypeEnum type;
    private int code;
    private String msg;

    GatewayErrorCodeEnum(ErrorTypeEnum type, int code, String msg) {
        this.type = type;
        this.code = code;
        this.msg = msg;
    }
}
