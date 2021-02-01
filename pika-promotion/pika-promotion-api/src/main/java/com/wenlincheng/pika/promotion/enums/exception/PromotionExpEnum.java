package com.wenlincheng.pika.promotion.enums.exception;

import com.wenlincheng.pika.common.core.exception.ErrorCode;
import com.wenlincheng.pika.common.core.exception.ErrorTypeEnum;
import lombok.Getter;

/**
 * 营销服务异常枚举
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum PromotionExpEnum implements ErrorCode {

    ACTIVITY_NOT_EXISTS(ErrorTypeEnum.BIZ_ERROR, 20810000, "活动不存在"),
    ACTIVITY_NO_LONGER_VALID(ErrorTypeEnum.BIZ_ERROR, 20810001, "活动已过有效期"),
    RULE_NOT_EXISTS(ErrorTypeEnum.BIZ_ERROR, 20810002, "优惠不存在"),
    RULE_NO_LONGER_VALID(ErrorTypeEnum.BIZ_ERROR, 20810003, "优惠券已过有效期"),
    SERVICE_ERROR(ErrorTypeEnum.SYSTEM_ERROR, 20710000, "优惠服务异常");

    private ErrorTypeEnum type;
    private int code;
    private String msg;

    PromotionExpEnum(ErrorTypeEnum type, int code, String msg) {
        this.type = type;
        this.code = code;
        this.msg = msg;
    }
}
