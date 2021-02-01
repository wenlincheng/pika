package com.wenlincheng.pika.trade.enums.reverse;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * TODO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum ReturnReasonEnum implements IEnum<String> {

    QUALITY_PROBLEM("QUALITY_PROBLEM", "质量问题", "质量问题"),
    NOT_LIKE("NOT_LIKE", "拍错/多拍/不喜欢", "拍错/多拍/不喜欢"),
    NOT_SAME("NOT_SAME", "商品描述不符", "商品描述不符"),
    WRONG_GOODS("WRONG_GOODS", "商家发错货", "商家发错货"),
    FAKE_GOODS("FAKE_GOODS", " 假货", " 假货"),
    BROKEN("BROKEN", "商品破损/少件", "商品破损/少件"),
    OTHER("OTHER", "其他", "其他")
    ;

    private String value;

    private String name;

    private String help;

    ReturnReasonEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
