package com.wenlincheng.pika.inventory.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 出库单类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum OperateOutTypeEnum implements IEnum<String> {

    PURCHASE_REVERSE_STOCK_OUT("PURCHASE_REVERSE_STOCK_OUT", "采购退货出库", "采购退货出库"),
    ALLOT_STOCK_OUT("ALLOT_STOCK_OUT", "调拨出库", "调拨出库"),
    TAKING_STOCK_OUT("TAKING_STOCK_OUT", "盘点出库", "盘点出库"),
    SALE_HOLD_STOCK_OUT("SALE_HOLD_STOCK_OUT", "销售锁定出库", "销售锁定出库"),
    SALE_OCC_STOCK_OUT("SALE_OCC_STOCK_OUT", "销售占用出库", "销售占用出库"),
    SALE_STOCK_OUT("SALE_STOCK_OUT", "销售出库", "销售出库"),
    REPLENISH_STOCK_OUT("REPLENISH_STOCK_OUT", "补货出库", "补货出库"),
    MANUAL_STOCK_OUT("MANUAL_STOCK_OUT", "手工减少出库", "手工减少出库"),
    SYNC_STOCK_OUT("SYNC_STOCK_OUT", "库存同步减少", "库存同步减少"),
    OTHER_STOCK_OUT("OTHER_STOCK_OUT", "其他出库", "其他出库"),
    ;

    private String value;

    private String name;

    private String help;

    OperateOutTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
