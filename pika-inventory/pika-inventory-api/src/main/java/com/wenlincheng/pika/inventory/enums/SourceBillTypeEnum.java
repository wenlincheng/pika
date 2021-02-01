package com.wenlincheng.pika.inventory.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 单据类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum SourceBillTypeEnum implements IEnum<String> {

    ARRIVAL_ORDER("ARRIVAL_ORDER", "生产到货单", "生产到货单"),
    ARRIVAL_REVERSE("ARRIVAL_REVERSE", "生产到货退单", "生产到货退单"),
    REPLENISH("REPLENISH", "补货单", "补货单"),
    PURCHASE_ORDER("PURCHASE_ORDER", "采购订单", "采购订单"),
    PURCHASE_REVERSE("PURCHASE_REVERSE", "采购退货订单", "采购退货订单"),
    SALE_ORDER("SALE_ORDER", "销售订单", "销售订单"),
    SALE_ORDER_SELL("SALE_ORDER_SELL", "商家订单", "商家订单"),
    SALE_REVERSE("SALE_REVERSE", "销售订单退货", "销售订单退货"),
    DELIVERY_ORDER("DELIVERY_ORDER", "发货单", "发货单"),
    STOCK_TAKING("STOCK_TAKING", "盘点单", "盘点单"),
    STOCK_ALLOCATE("STOCK_ALLOCATE", "调拨单", "调拨单"),
    PICKING_LIST("PICKING_LIST", "生产领料单", "生产领料单"),
    STOCK_IN_ORDER("STOCK_IN_ORDER", "入库单", "入库单"),
    STOCK_OUT_ORDER("STOCK_OUT_ORDER", "出库单", "出库单"),
    NO_ORDER("NO_ORDER", "无单据", "库存操作,不依赖单据"),
    ;

    private String value;
    private String name;
    private String help;

    SourceBillTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
