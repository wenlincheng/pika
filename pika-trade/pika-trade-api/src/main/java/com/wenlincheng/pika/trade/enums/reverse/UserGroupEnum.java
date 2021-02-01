package com.wenlincheng.pika.trade.enums.reverse;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 用户群体
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum UserGroupEnum implements IEnum<String> {

    BUYER("BUYER", "买家", "买家"),
    SELLER("SELLER", "卖家", "卖家"),
    PLATFORM("PLATFORM", "平台", "平台"),
    SYSTEM("SYSTEM", "系统", "系统"),
    ;

    private String value;

    private String name;

    private String help;

    UserGroupEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
