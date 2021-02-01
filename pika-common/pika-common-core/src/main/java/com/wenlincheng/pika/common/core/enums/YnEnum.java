package com.wenlincheng.pika.common.core.enums;

/**
 * 是否枚举
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public enum YnEnum {

    NO(0, "否"),
    YES(1, "是");

    /**
     * 枚举值
     */
    private int value;

    /**
     * 枚举描述
     */
    private String desc;

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * @param value 枚举值
     * @param desc  枚举描述
     */
    YnEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 根据枚举值获取枚举
     *
     * @param value 枚举值
     * @return com.wenlincheng.pika.common.enums.YnEnum
     * @date 2021/1/1 10:10 上午
     */
    public static YnEnum valueOf(int value) {
        for (YnEnum ynEnum : values()) {
            if (ynEnum.getValue() == value) {
                return ynEnum;
            }
        }
        return NO;
    }



}
