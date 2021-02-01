package com.wenlincheng.pika.inventory.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 入库单类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum OperateInTypeEnum implements IEnum<String> {

    PURCHASE_STOCK_IN("PURCHASE_STOCK_IN", "采购入库", "采购入库"),
    REVERSE_STOCK_IN("REVERSE_STOCK_IN", "销售退货入库", "销售退货入库"),
    ALLOT_STOCK_IN("ALLOT_STOCK_IN", "调拨入库", "调拨入库"),
    TAKING_STOCK_IN("TAKING_STOCK_IN", "盘点入库", "盘点入库"),
    REPLENISH_STOCK_IN("REPLENISH_STOCK_IN", "补货入库", "补货入库"),
    ARRIVED_STOCK_IN("ARRIVED_STOCK_IN", "生产到货入库", "生产到货入库"),
    MANUAL_STOCK_IN("MANUAL_STOCK_IN", "手工增加入库", "手工增加入库"),
    SYNC_STOCK_IN("SYNC_STOCK_IN", "库存同步增加", "库存同步增加"),
    OTHER_STOCK_IN("OTHER_STOCK_IN", "其他入库", "其他入库"),
    ;

    private String value;

    private String name;

    private String help;

    OperateInTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
