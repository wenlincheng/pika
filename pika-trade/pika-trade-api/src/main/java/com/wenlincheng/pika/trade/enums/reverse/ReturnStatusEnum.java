package com.wenlincheng.pika.trade.enums.reverse;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 退货状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum ReturnStatusEnum implements IEnum<String> {

    NO_RETURN("NO_RETURN", "无需退货", "无需退货"),
    WAIT_RETURN("WAIT_RETURN", "等待退货", "等待退货"),
    RETURNING("RETURNING", "退货中", "退货中"),
    RETURN_PART("RETURN_PART", "部分退货", "部分退货"),
    RETURN_FAILED("RETURN_FAILED", "退货失败", "退货失败"),
    ALL_RETURN("ALL_RETURN", "退货成功", "退货成功");

    private String value;

    private String name;

    private String help;

    ReturnStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
