package com.wenlincheng.pika.inventory.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 盘点单状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum StockTakingStatusEnum implements IEnum<String> {

    WAITING("WAITING", "待确认", "待确认"),
    CONFIRMED("CONFIRMED", "已确认", "已确认"),
    REJECTED("REJECTED", "已拒绝", "已拒绝"),
    FINISHED("FINISHED", "已完成", "已完成")
    ;

    private String value;

    private String name;

    private String help;

    StockTakingStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
