package com.wenlincheng.pika.common.core.exception;

/**
 * 错误码
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public interface ErrorCode {
    /**
     * 错误类型
     *
     * @return ErrorTypeEnum
     */
    ErrorTypeEnum getType();

    /**
     * 错误码
     *
     * @return int
     */
    int getCode();

    /**
     * 错误信息
     *
     * @return String
     */
    String getMsg();
}
