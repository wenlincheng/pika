package com.wenlincheng.pika.trade.enums.trade;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 发票状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum TradeInvoiceStatusEnum implements IEnum<String> {

    WAIT_SUBMIT("WAIT_SUBMIT", "待提交开票信息", "待提交开票信息"),
    WAIT_INVOICING("WAIT_INVOICING", "待商家开票", "待商家开票"),
    FINISH("FINISH", "开票完成", "开票完成"),
    CLOSE("CLOSE", "开票关闭", "开票关闭"),
    ;

    private String value;

    private String name;

    private String help;

    TradeInvoiceStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
