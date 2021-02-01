package com.wenlincheng.pika.upms.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 用户类型枚举
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum UserTypeEnum implements IEnum<String> {

    UNKNOWN("UNKNOWN","未知", "未知"),
    CUSTOMER("CUSTOMER","顾客", "C端用户账号"),
    ADMIN("ADMIN","管理员", "平台、商家等管理员账号");

    private String value;
    private String name;
    private String help;

    UserTypeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
    /**
     * 根据枚举值获取枚举
     *
     * @param value  枚举值
     * @return UserTypeEnum
     */
    public static UserTypeEnum value(String value) {
        for (UserTypeEnum userType : values()) {
            if (userType.getValue().equals(value)) {
                return userType;
            }
        }
        return UNKNOWN;
    }
}
