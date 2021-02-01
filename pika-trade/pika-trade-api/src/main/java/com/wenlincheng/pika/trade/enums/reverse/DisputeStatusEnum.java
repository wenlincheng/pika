package com.wenlincheng.pika.trade.enums.reverse;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 纠纷单状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum DisputeStatusEnum implements IEnum<String> {

    WAIT_HANDLE("WAIT_HANDLE", "待处理", "待处理"),
    HANDLING("HANDLING", "处理中", "处理中"),
    ACCEPT("ACCEPT", "处理完成", "处理完成");

    private String value;

    private String name;

    private String help;

    DisputeStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
