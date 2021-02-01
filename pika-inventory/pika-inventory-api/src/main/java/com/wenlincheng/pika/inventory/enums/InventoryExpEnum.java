package com.wenlincheng.pika.inventory.enums;

import com.wenlincheng.pika.common.core.exception.ErrorCode;
import com.wenlincheng.pika.common.core.exception.ErrorTypeEnum;
import lombok.Getter;

/**
 * 库存中心错误枚举
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum InventoryExpEnum implements ErrorCode {

    ALLOCATE_STATUS_RECEIVED_ERROR(ErrorTypeEnum.BIZ_ERROR,20720001, "调拨单状态不允许接单"),
    ALLOCATE_STATUS_REJECTED_ERROR(ErrorTypeEnum.BIZ_ERROR,20720002, "调拨单状态不允许拒单"),
    ALLOCATE_STATUS_REJECTED_NEED_REASON(ErrorTypeEnum.BIZ_ERROR,20720003, "调拨单拒单需要原因"),
    ALLOCATE_STATUS_STOCK_NEED_RECEIVED(ErrorTypeEnum.BIZ_ERROR,20720004, "调拨单操作库存需要先接单"),

    STOCK_TAKING_STATUS_CONFIRM_ERROR(ErrorTypeEnum.BIZ_ERROR,20720005, "盘点单已经确认过"),

    INVENTORY_OPERATE_MISSING_INVENTORY(ErrorTypeEnum.BIZ_ERROR, 20720006, "操作库存时无法找到库存记录"),
    INVENTORY_OPERATE_HAS_DONE(ErrorTypeEnum.BIZ_ERROR,20720007, "该操作记录已经执行！"),
    INVENTORY_UNIT_MISSING(ErrorTypeEnum.BIZ_ERROR,20720008, "库存单位异常！"),

    SALE_INVENTORY_OPERATE_MISSING_INVENTORY(ErrorTypeEnum.BIZ_ERROR,20720009, "操作销售库存时无法找到库存记录"),

    SALE_INVENTORY_NOT_ENOUGH(ErrorTypeEnum.BIZ_ERROR,20720010, "销售库存不足"),
    SALE_INVENTORY_INIT_PARAM_MISSING(ErrorTypeEnum.BIZ_ERROR,20720011, "销售库存初始化参数丢失"),
    SALE_INVENTORY_INIT_FAIL(ErrorTypeEnum.BIZ_ERROR,20720012, "销售库存初始化失败"),
    SALE_INVENTORY_INIT_INCREASE_FAIL(ErrorTypeEnum.BIZ_ERROR,20720013, "销售库存初始化库存数量失败"),

    SALE_INVENTORY_INIT_QUANTITY_ERROR(ErrorTypeEnum.BIZ_ERROR,20720014, "销售库存初始化库存数量不能为零"),

    SERVICE_ERROR(ErrorTypeEnum.SYSTEM_ERROR,20720000, "库存服务异常");

    private ErrorTypeEnum type;
    private int        code;
    private String     msg;

    InventoryExpEnum(ErrorTypeEnum type, int code, String msg) {
        this.type = type;
        this.code = code;
        this.msg = msg;
    }
}
