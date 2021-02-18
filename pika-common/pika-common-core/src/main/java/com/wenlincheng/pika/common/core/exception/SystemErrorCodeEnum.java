package com.wenlincheng.pika.common.core.exception;

import lombok.Getter;

/**
 * 系统异常类型枚举
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum SystemErrorCodeEnum implements ErrorCode {

    SUCCESS(ErrorTypeEnum.SYSTEM_ERROR,200,"成功"),
    SYSTEM_ERROR(ErrorTypeEnum.SYSTEM_ERROR,500,"系统异常"),
    USER_NOT_LOGIN(ErrorTypeEnum.SYSTEM_ERROR,401, "用户未登录"),
    RESOURCE_NOT_FOUND(ErrorTypeEnum.SYSTEM_ERROR,404, "资源不存在"),
    SYSTEM_BUSY(ErrorTypeEnum.SYSTEM_ERROR,600, "系统繁忙,请稍候再试"),
    ARGUMENT_NOT_VALID(ErrorTypeEnum.SYSTEM_ERROR,20000, "请求参数校验不通过"),
    UPLOAD_FILE_SIZE_LIMIT(ErrorTypeEnum.SYSTEM_ERROR,20001, "上传文件大小超过限制"),
    DUPLICATE_PRIMARY_KEY(ErrorTypeEnum.SYSTEM_ERROR,30000,"唯一键冲突"),

    // 公共模块错误类型
    CLONE_OBJECT_FAIL(ErrorTypeEnum.SYSTEM_ERROR,41001, "对象复制失败"),
    CSV_PARSING_FAIL(ErrorTypeEnum.SYSTEM_ERROR,41002, "csv解析出错"),
    CSV_PROPERTIES_DO_NOT_SUPPORT(ErrorTypeEnum.SYSTEM_ERROR,41003, "csv对象属性不支持解析"),
    EXCEL_PARSING_FAIL(ErrorTypeEnum.SYSTEM_ERROR,41004, "excel解析出错"),
    SYS_LOG_SAVE_FAIL(ErrorTypeEnum.SYSTEM_ERROR,41005, "插入日志失败"),


    NULL_KEY_FAIL(ErrorTypeEnum.BIZ_ERROR,41005, "查询主键为空"),
    PAGE_FAIL(ErrorTypeEnum.BIZ_ERROR,41005, "查询列表失败"),
    CREATE_FAIL(ErrorTypeEnum.BIZ_ERROR,41005, "创建记录失败"),
    UPDATE_FAIL(ErrorTypeEnum.BIZ_ERROR,41005, "更新记录失败"),
    DELETE_FAIL(ErrorTypeEnum.BIZ_ERROR,41005, "删除记录失败"),
    QUERY_FAIL(ErrorTypeEnum.BIZ_ERROR,41005, "查询记录失败"),

    JWT_TOKEN_NULL_ERROR(ErrorTypeEnum.BIZ_ERROR,41005, "TOKEN为空")
    ;

    private ErrorTypeEnum type;
    private int code;
    private String msg;

    SystemErrorCodeEnum(ErrorTypeEnum type, int code, String msg) {
        this.type = type;
        this.code = code;
        this.msg = msg;
    }
}
