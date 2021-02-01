package com.wenlincheng.pika.common.core.exception;

/**
 * 错误类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public enum ErrorTypeEnum {
    SYSTEM_ERROR("系统错误"),
    GENERIC_ERROR("普通错误"),
    REMOTE_ERROR("远程错误"),
    BIZ_ERROR("业务错误"),
    SECURITY_ERROR("权限错误"),
    LOGIC_ERROR("逻辑错误"),
    ;

    public String type;

    ErrorTypeEnum(String type) {
        this.type = type;
    }
}
