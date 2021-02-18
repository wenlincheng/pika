package com.wenlincheng.pika.common.core.base.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wenlincheng.pika.common.core.exception.BaseException;
import com.wenlincheng.pika.common.core.exception.ErrorCode;
import com.wenlincheng.pika.common.core.exception.SystemErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 请求统一返回该类的对象
 *
 * @author  wenlincheng
 * @date    2019/11/24 12:47 下午
 * @version 1.0
 */
@ApiModel(description = "请求的统一返回模型")
@Getter
public class Result<T> {

    public static final int SUCCESSFUL_CODE = 200;
    public static final String SUCCESSFUL_MSG = "成功";

    @ApiModelProperty(value = "是否成功", required = true)
    private Boolean success;

    @ApiModelProperty(value = "状态码", required = true)
    private Integer code;

    @ApiModelProperty(value = "描述信息")
    private String msg;

    @ApiModelProperty(value = "返回时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public Result() {
        this.time = new Date();
    }

    /**
     * @param errorType 错误类型
     */
    public Result(ErrorCode errorType) {
        this.code = errorType.getCode();
        this.msg = errorType.getMsg();
        this.time = new Date();
    }

    /**
     * @param errorType 错误类型
     * @param data 返回数据
     */
    public Result(ErrorCode errorType, T data) {
        this(errorType);
        this.data = data;
    }

    /**
     * @param errorType 错误类型
     * @param msg 描述信息
     */
    public Result(ErrorCode errorType, String msg) {
        this(errorType);
        this.msg = msg;
    }

    /**
     * 内部使用，用于构造成功的结果
     *
     * @param code 状态码
     * @param msg  描述信息
     * @param data 返回数据
     */
    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.time = new Date();
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data 返回数据
     * @return Result
     */
    public static <E> Result<E>  success(E data) {
        return new Result<>(SUCCESSFUL_CODE, SUCCESSFUL_MSG, data);
    }

    /**
     * 快速创建成功结果
     *
     * @return Result
     */
    public static <E> Result<E> success() {
        return success(null);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @return Result
     */
    public static <E> Result<E> fail() {
        return new Result<>(SystemErrorCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @param baseException 系统异常
     * @return Result
     */
    public static <E> Result<E> fail(BaseException baseException) {
        return fail(baseException, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data 返回数据
     * @return Result
     */
    public static <E> Result<E> fail(BaseException baseException, E data) {
        return new Result<>(baseException.getErrorCode(), data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType 错误类型
     * @param data      返回数据
     * @return Result
     */
    public static <E> Result<E> fail(ErrorCode errorType, E data) {
        return new Result<>(errorType, data);
    }

    /**
     * 系统异常类并返回自定义描述信息
     *
     * @param errorType 错误类型
     * @param msg      描述信息
     * @return Result
     */
    public static <E> Result<E> fail(ErrorCode errorType, String msg) {
        return new Result<>(errorType, msg);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType 错误类型
     * @return Result
     */
    public static <E> Result<E> fail(ErrorCode errorType) {
        return new Result<>(errorType);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data  返回数据
     * @return Result
     */
    public static <E> Result<E> fail(E data) {
        return new Result<>(SystemErrorCodeEnum.SYSTEM_ERROR, data);
    }

}
