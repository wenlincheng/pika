package com.wenlincheng.pika.logistics.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 物流日志状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum WlOrderLogCodeEnum implements IEnum<String> {

    // 订单 相关
    ORDER_CREATED("ORDER_CREATED", "已下单", "已下单"),
    ORDER_DELIVERED("ORDER_DELIVERED", "已发货", "已发货"),

    // WMS 相关
    WMS_ACCEPT("WMS_ACCEPT", "已接单", "已接单"),
    WMS_REJECT("WMS_REJECT", "已拒单", "已拒单"),
    WMS_PRINT("WMS_PRINT", "已打印", "已打印"),
    WMS_PICK("WMS_PICK", "已捡货", "已捡货"),
    WMS_PACKAGE("WMS_PACKAGE", "已打包", "已打包"),
    WMS_TO_TMS("WMS_TO_TMS", "已配送", "已配送"),
    WMS_CANCEL("WMS_CANCEL", "已取消", "已取消"),

    // TMS 相关
    TMS_ACCEPTED("TMS_ACCEPT", "已揽收", "已揽收"),
    TMS_TRANSIT("TMS_TRANSIT", "运输中", "运输中"),
    TMS_DELIVERY("TMS_DELIVERY", "派送中", "派送中"),
    TMS_SIGNED("TMS_SIGN", "已签收", "已签收"),
    TMS_COLLECTED("TMS_COLLECTED", "代签收", "代签收"),
    TMS_REJECTED("TMS_REJECT", "拒签", "拒签"),
    TMS_ERROR("TMS_ERROR", "异常", "异常"),
    TMS_PAYED("TMS_PAYED", "支付成功", "支付成功（对COD订单有效"),

    // 系统 相关
    SYS_ERROR("SYS_ERROR", "系统异常", "系统异常");

    private String value;

    private String name;

    private String help;

    WlOrderLogCodeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
