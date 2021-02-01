package com.wenlincheng.pika.common.core.enums;

/**
 * 第三方平台类型枚举
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public enum ThirdPlatformTypeEnum {
    UNKNOWN(0,"未知"),
    WE_CHAT(1,"微信"),
    QQ(2,"QQ"),
    WEI_BO(3,"微博");

    /**
     *  值
     **/
    private int value;

    /**
     * 描述
     */
    private String desc;

    /**
     * @param value 枚举值
     * @param desc  枚举描述
     */
    ThirdPlatformTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 根据枚举值获取枚举
     *
     * @param value  枚举值
     * @return com.wenlincheng.pika.common.enums.ThirdPlatformTypeEnum
     */
    public static ThirdPlatformTypeEnum valueOf(int value) {
        for (ThirdPlatformTypeEnum thirdPlatformType : values()) {
            if (thirdPlatformType.getValue() == value) {
                return thirdPlatformType;
            }
        }
        return UNKNOWN;
    }


}
