package com.wenlincheng.pika.trade.enums;

import com.wenlincheng.pika.common.core.exception.ErrorCode;
import com.wenlincheng.pika.common.core.exception.ErrorTypeEnum;
import lombok.Getter;

/**
 * 订单业务异常类型枚举
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum OrderErrorCodeEnum implements ErrorCode {

    ERROR_ORDER_CAN_NOT_BE_PAYED(ErrorTypeEnum.BIZ_ERROR,440001, "订单支付失败"),
    ERROR_ORDER_HAS_CLOSED(ErrorTypeEnum.BIZ_ERROR,440002, "订单已关闭"),
    ERROR_ORDER_HAS_PAY(ErrorTypeEnum.BIZ_ERROR,440005, "订单已支付"),
    ERROR_ORDER_HAS_NOT_PAY(ErrorTypeEnum.BIZ_ERROR,440006, "订单未支付"),
    ERROR_ORDER_CAN_NOT_CANCEL(ErrorTypeEnum.BIZ_ERROR,440017, "该订单不允许被取消"),
    ERROR_ORDER_EXPRESS(ErrorTypeEnum.BIZ_ERROR,440006, "订单快递查询错误"),
    ERROR_ORDER_REFUND_PHONE_ERROR(ErrorTypeEnum.BIZ_ERROR,440009, "手机号不是退款手机号码,请核对手机号码"),
    ERROR_ORDER_STATUS_AND_SEND_EXPRESS_CODE_NOT_NULL(ErrorTypeEnum.BIZ_ERROR,440010, "发货快递单号不能为空"),
    ERROR_ORDER_DISABLE_REFUND(ErrorTypeEnum.BIZ_ERROR,440011, "订单不能退款,不是该环境下的订单"),
    ERROR_ORDER_HAS_REFUND(ErrorTypeEnum.BIZ_ERROR,440012, "订单已退款"),
    ERROR_ORDER_NOT_EXIST(ErrorTypeEnum.BIZ_ERROR,440013, "订单不存在或者已被删除"),
    ERROR_ORDER_HAS_EXIST(ErrorTypeEnum.BIZ_ERROR,440014, "已对该商品下单,请勿重复提交"),
    ERROR_ORDER_NOT_ALLOW_DELETE(ErrorTypeEnum.BIZ_ERROR,440015, "该订单不允许删除"),
    ERROR_ORDER_STATUS_CANNOT_MODIFIED(ErrorTypeEnum.BIZ_ERROR,440016, "该订单不允许被修改"),
    ERROR_ORDER_MODIFY_STATUS(ErrorTypeEnum.BIZ_ERROR,440017, "该订单不允许该操作,请重新修改订单状态"),
    ERROR_ALI_MINI_MODIFY_CHECK_BACK_PRODUCT_GET_FAIL(ErrorTypeEnum.BIZ_ERROR,440201, "修改订单失败，订单状态错误"),
    ERROR_ORDER_HAS_SUCCESS(ErrorTypeEnum.BIZ_ERROR,440019, "订单已交易完成"),
    ERROR_ORDER_PAY_ERROR(ErrorTypeEnum.BIZ_ERROR,440023, "订单支付失败");

    private ErrorTypeEnum type;
    private int code;
    private String msg;

    OrderErrorCodeEnum(ErrorTypeEnum type, int code, String msg) {
        this.type = type;
        this.code = code;
        this.msg = msg;
    }
}
