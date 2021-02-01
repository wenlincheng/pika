package com.wenlincheng.pika.inventory.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 库区选项
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum WarehouseRegionOptionEnum implements IEnum<String> {

    TAG("TAG", "标签", "标签"),
    ORDER_TYPE("ORDER_TYPE", "订单类型", "订单类型"),
    MEMBER("MEMBER", "会员", "会员"),
    RETAILER("RETAILER", "门店", "门店"),
    DISTRIBUTOR("DISTRIBUTOR", "经销商", "经销商");

    private String value;

    private String name;

    private String help;

    WarehouseRegionOptionEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
