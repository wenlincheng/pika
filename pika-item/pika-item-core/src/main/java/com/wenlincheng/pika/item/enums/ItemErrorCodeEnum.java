package com.wenlincheng.pika.item.enums;

import com.wenlincheng.pika.common.core.exception.ErrorCode;
import com.wenlincheng.pika.common.core.exception.ErrorTypeEnum;
import lombok.Getter;

/**
 * 商品中心错误码
 *
 * @author  wenlincheng
 * @date    2021/01/03 6:39 下午
 * @version 1.0
 */
@Getter
public enum ItemErrorCodeEnum implements ErrorCode {

    ITEM_NOT_EXIST_ERROR(ErrorTypeEnum.BIZ_ERROR,40000, "商品不存在"),
    ;

    private ErrorTypeEnum type;
    private int code;
    private String msg;

    ItemErrorCodeEnum(ErrorTypeEnum type, int code, String msg) {
        this.type = type;
        this.code = code;
        this.msg = msg;
    }
}
