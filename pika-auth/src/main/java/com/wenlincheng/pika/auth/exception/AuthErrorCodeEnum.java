package com.wenlincheng.pika.auth.exception;

import com.wenlincheng.pika.common.core.exception.ErrorCode;
import com.wenlincheng.pika.common.core.exception.ErrorTypeEnum;
import lombok.Getter;

/**
 * 认证服务异常枚举
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum AuthErrorCodeEnum implements ErrorCode {
    AUTHORIZED_ERROR(ErrorTypeEnum.BIZ_ERROR,20401, "认证失败"),
    LOGIN_ERROR(ErrorTypeEnum.BIZ_ERROR,20401, "登陆失败，用户名或密码错误"),
    NO_TOKEN(ErrorTypeEnum.BIZ_ERROR,20401, "认证失败，缺少头部令牌"),
    TOKEN_MALFORMED(ErrorTypeEnum.BIZ_ERROR,20401, "认证失败，令牌格式错误"),
    TOKEN_UNSUPPORTED(ErrorTypeEnum.BIZ_ERROR,20401, "认证失败，不支持的令牌"),
    TOKEN_CREATE_FAILED(ErrorTypeEnum.BIZ_ERROR,20401, "登录失败，无法创建令牌"),
    TOKEN_INVALID_KEY(ErrorTypeEnum.BIZ_ERROR,20401, "认证失败，令牌签名错误"),
    TOKEN_EMPTY(ErrorTypeEnum.BIZ_ERROR,20401, "认证失败，令牌为空"),
    UNAUTHORIZED(ErrorTypeEnum.BIZ_ERROR,20403, "没有访问权限"),
    OPERATE_ERROR(ErrorTypeEnum.BIZ_ERROR,20405, "操作失败，请求操作的资源不存在"),
    TOKEN_EXPIRED(ErrorTypeEnum.BIZ_ERROR,20406, "登录已失效，请重新登录"),
    TOKEN_LOGOUT(ErrorTypeEnum.BIZ_ERROR,20407, "登录状态已被注销，请重新登录"),
    RESOURCE_CHANGED(ErrorTypeEnum.BIZ_ERROR,20408, "权限已变更，请重新登录"),
    ROLE_EMPTY(ErrorTypeEnum.BIZ_ERROR,20409, "认证失败，用户未分配角色"),
    AUTHORITY_EMPTY(ErrorTypeEnum.BIZ_ERROR,20409, "认证失败，用户未分配权限"),
    USER_NOT_FOUND(ErrorTypeEnum.BIZ_ERROR,20100, "用户不存在"),
    USER_DISABLED(ErrorTypeEnum.BIZ_ERROR,20101, "账户被禁用"),
    USERNAME_PASSWORD_WRONG(ErrorTypeEnum.BIZ_ERROR,20102, "用户名或密码错误"),
    USER_LIMIT_TIME_UP(ErrorTypeEnum.BIZ_ERROR,20103, "登录错误次数超过限制"),
    ROLE_NOT_FOUND(ErrorTypeEnum.BIZ_ERROR,20200, "角色未找到"),
    LOGIN_FAIL(ErrorTypeEnum.BIZ_ERROR,20300, "登录失败"),
    BAD_CREDENTIALS(ErrorTypeEnum.BIZ_ERROR,203001, "授权失败"),
    ;

    private ErrorTypeEnum type;
    private int code;
    private String msg;

    AuthErrorCodeEnum(ErrorTypeEnum type, int code, String msg) {
        this.type = type;
        this.code = code;
        this.msg = msg;
    }

}
