package com.wenlincheng.pika.upms.enums;

import com.wenlincheng.pika.common.core.exception.ErrorCode;
import com.wenlincheng.pika.common.core.exception.ErrorTypeEnum;
import lombok.Getter;

/**
 * 用户错误码
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum UpmsErrorCodeEnum implements ErrorCode {

    USER_NOT_FOUND(ErrorTypeEnum.BIZ_ERROR,30100, "用户未找到"),
    USER_ERROR_DETAIL(ErrorTypeEnum.BIZ_ERROR,30101, "获取用户详情失败"),
    ROLE_NOT_FOUND(ErrorTypeEnum.BIZ_ERROR,30102, "角色未找到"),
    MENU_HAS_CHILDREN(ErrorTypeEnum.BIZ_ERROR,30103, "菜单下有关联子菜单"),
    MENU_REL_ROLE_DELETE_ERROR(ErrorTypeEnum.BIZ_ERROR,30104, "有角色与菜单关联"),
    ROLE_REL_USER_DELETE_ERROR(ErrorTypeEnum.BIZ_ERROR,30105, "有用户与角色关联"),
    PASSWORD_ERROR(ErrorTypeEnum.BIZ_ERROR,30106, "密码错误"),
    MOBILE_EXIST_ERROR(ErrorTypeEnum.BIZ_ERROR,30107, "手机号已经被注册"),
    USERNAME_EXIST_ERROR(ErrorTypeEnum.BIZ_ERROR,30107, "用户名已经被注册"),
    ;

    private ErrorTypeEnum type;
    private int code;
    private String msg;

    UpmsErrorCodeEnum(ErrorTypeEnum type, int code, String msg) {
        this.type = type;
        this.code = code;
        this.msg = msg;
    }
}
