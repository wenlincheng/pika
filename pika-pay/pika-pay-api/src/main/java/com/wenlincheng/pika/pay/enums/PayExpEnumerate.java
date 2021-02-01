package com.wenlincheng.pika.pay.enums;

import com.wenlincheng.pika.common.core.exception.ErrorCode;
import com.wenlincheng.pika.common.core.exception.ErrorTypeEnum;
import lombok.Getter;

/**
 * 支付中心异常枚举
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum PayExpEnumerate implements ErrorCode {
    //region common
    COMMON_HAS_CREATE_ORDER(ErrorTypeEnum.BIZ_ERROR, 204500001, "已经存在未支付的支付订单"),
    COMMON_HAS_SUCCESS_ORDER(ErrorTypeEnum.BIZ_ERROR, 204500002, "已经支付成功，请不要重复支付"),
    COMMON_HAS_FAIL_ORDER(ErrorTypeEnum.BIZ_ERROR, 204500003, "存在支付失败的订单，请先处理失败支付"),
    COMMON_PARAMS_MISSING(ErrorTypeEnum.BIZ_ERROR, 204500004, "支付单参数不全"),
    COMMON_PAID_MISSING(ErrorTypeEnum.BIZ_ERROR, 204500005, "找不到正向支付单"),
    COMMON_ORDER_MISSING(ErrorTypeEnum.BIZ_ERROR, 204500006, "订单不存在或者订单异常，请确认订单id"),

    COMMON_PAY_MISSING_PAYORDERID(ErrorTypeEnum.BIZ_ERROR, 204500007, "支付单ID不能为空"),

    COMMON_REFUND_MISS_PAYORDER(ErrorTypeEnum.BIZ_ERROR, 204500008, "找不到对应支付单"),

    //endregion

    //region alipay
    ALIPAY_CONFIG_MISSING(ErrorTypeEnum.BIZ_ERROR, 20450009, "支付宝配置文件不存在"),
    ALIPAY_PAGEPAY_FAIL(ErrorTypeEnum.BIZ_ERROR, 20450010, "支付宝页面支付失败"),
    ALIPAY_MISS_PAYORDER(ErrorTypeEnum.BIZ_ERROR, 20450011, "关闭或退款时找不到对应支付单"),
    ALIPAY_SERVICE_ERROR(ErrorTypeEnum.SYSTEM_ERROR, 20450012, "支付宝支付服务异常"),
    ALIPAY_CALLBACK_OUTTRADENO_ERROR(ErrorTypeEnum.SYSTEM_ERROR, 20450013, "商家交易号错误"),
    ALIPAY_CALLBACK_TOTALAMOUNT_ERROR(ErrorTypeEnum.SYSTEM_ERROR, 20450014, "返回金额不一致错误"),
    ALIPAY_CALLBACK_APPID_ERROR(ErrorTypeEnum.SYSTEM_ERROR, 20450015, "app_id不一致"),
    //endregion

    //region wxpay
    WXPAY_SERVICE_ERROR(ErrorTypeEnum.SYSTEM_ERROR, 20450016, "微信支付服务异常"),
    WXPAY_CONFIG_MISSING(ErrorTypeEnum.BIZ_ERROR, 20450017, "微信配置文件不存在"),
    WXPAY_PAGEPAY_FAIL(ErrorTypeEnum.BIZ_ERROR, 20450018, "微信页面支付失败"),
    WXPAY_REFUND_FAIL(ErrorTypeEnum.BIZ_ERROR, 20450019, "微信申请退款失败"),
    WXPAY_PAYQUERY_FAIL(ErrorTypeEnum.BIZ_ERROR, 20450020, "微信支付查询失败"),
    WXPAY_REFUNDQUERY_FAIL(ErrorTypeEnum.BIZ_ERROR, 20450021, "微信退款查询失败"),
    //endregion

    //region paypal
    PAYPAL_SERVICE_ERROR(ErrorTypeEnum.SYSTEM_ERROR, 20450022, "PAYPAL支付服务异常"),
    PAYPAL_CONFIG_MISSING(ErrorTypeEnum.BIZ_ERROR, 20450023, "PAYPAL配置文件不存在"),
    PAYPAL_PAGEPAY_FAIL(ErrorTypeEnum.BIZ_ERROR, 20450024, "PAYPAL页面支付失败"),
    PAYPAL_REFUND_FAIL(ErrorTypeEnum.BIZ_ERROR, 20450025, "PAYPAL申请退款失败"),
    PAYPAL_PAYQUERY_FAIL(ErrorTypeEnum.BIZ_ERROR, 20450026, "PAYPAL支付查询失败"),
    PAYPAL_REFUNDQUERY_FAIL(ErrorTypeEnum.BIZ_ERROR, 20450027, "PAYPAL退款查询失败"),
    PAYPAL_PAYNO_MISSING(ErrorTypeEnum.BIZ_ERROR, 20450028, "PAYPAL操作支付单不存在"),
    //endregion

    SERVICE_ERROR(ErrorTypeEnum.SYSTEM_ERROR, 204500000, "通用支付服务异常");


    private ErrorTypeEnum type;
    private int code;
    private String msg;

    PayExpEnumerate(ErrorTypeEnum type, int code, String msg) {
        this.type = type;
        this.code = code;
        this.msg = msg;
    }
}
