package com.wenlincheng.pika.inventory.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import com.wenlincheng.pika.common.core.exception.ErrorCode;
import com.wenlincheng.pika.common.core.exception.ErrorTypeEnum;
import lombok.Getter;

/**
 * 库存调拨单状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum AllocateStatusEnum implements IEnum<String> {

    WAITING("WAITING", "未完成", "未完成"),
    RECEIVED("RECEIVED", "已接单", "已接单"),
    REJECTED("REJECTED", "已拒绝", "已拒绝"),
    FINISHED("FINISHED", "已完成", "已完成")
    ;

    private String value;
    private String name;
    private String help;

    AllocateStatusEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
