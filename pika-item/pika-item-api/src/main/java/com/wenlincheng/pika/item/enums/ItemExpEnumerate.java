package com.wenlincheng.pika.item.enums;

import com.wenlincheng.pika.common.core.exception.ErrorCode;
import com.wenlincheng.pika.common.core.exception.ErrorTypeEnum;
import lombok.Getter;

/**
 * 商品中心错误枚举
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum ItemExpEnumerate implements ErrorCode {

    ITEM_UNITS_FIND_FAIL(ErrorTypeEnum.BIZ_ERROR, 20710000, "单位查找失败"),
    ITEM_SEARCH_FAIL(ErrorTypeEnum.BIZ_ERROR, 20710001, "商品搜索异常"),
    OWNER_PARTNER_MATCH_FAIL(ErrorTypeEnum.BIZ_ERROR, 20710002, "商家信息丢失"),
    CURRENCY_RATE_MATCH_FAIL(ErrorTypeEnum.BIZ_ERROR, 20710003, "币种对应汇率为空"),

    ITEM_SEARCH_ITEM_BY_ID_ERROR(ErrorTypeEnum.SYSTEM_ERROR, 20710004, "通过id查询商品失败"),
    ITEM_CHANGE_ITEM_STATUS_ERROR(ErrorTypeEnum.SYSTEM_ERROR, 20710005, "修改商品状态失败"),
    ITEM_PULISH_ITEM_STATUS_BY_TIME_ERROR(ErrorTypeEnum.SYSTEM_ERROR, 20710006, "定时修改商品状态失败"),
    CATEGORY_DELETE_ERROR(ErrorTypeEnum.BIZ_ERROR, 20710007, "未传入后台类目id或前台类目id"),
    FRONT_CATEGORY_DELETE_EXIST_CATEGORY_ERROR(ErrorTypeEnum.BIZ_ERROR, 20710008, "该类目下存在后台类目,不允许删除"),
    CATEGORY_DELETE_EXIST_PRODUCT_ERROR(ErrorTypeEnum.BIZ_ERROR, 20710009, "该类目下存在产品,不允许删除"),
    CATEGORY_DELETE_EXIST_ITEM_ERROR(ErrorTypeEnum.BIZ_ERROR, 20710010, "该类目下存在商品,不允许删除"),

    PRODUCT_DELETE_EXIST_ITEM_ERROR(ErrorTypeEnum.BIZ_ERROR, 20710011, "该产品下存在商品,不允许删除"),

    SERVICE_ERROR(ErrorTypeEnum.SYSTEM_ERROR, 20710000, "商品服务异常");

    private ErrorTypeEnum type;
    private int code;
    private String msg;

    ItemExpEnumerate(ErrorTypeEnum type, int code, String msg) {
        this.type = type;
        this.code = code;
        this.msg = msg;
    }
}
