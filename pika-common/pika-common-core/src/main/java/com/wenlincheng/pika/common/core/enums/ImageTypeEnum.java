package com.wenlincheng.pika.common.core.enums;

import lombok.Data;

/**
 * 图片类型枚举
 *
 * @author : wenlincheng
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public enum ImageTypeEnum {
    UNKNOWN(0,"--"),
    PNG(1,"png"),
    JPG(2, "jpg");

    /**
     * 值
     */
    private int value;

    /**
     * 描述
     */
    private String desc;


    ImageTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static ImageTypeEnum get(String desc) {
        for (ImageTypeEnum value : ImageTypeEnum.values()) {
            if (value.getDesc().equals(desc)) {
                return value;
            }
        }
        return UNKNOWN;
    }

    /**
     * 根据枚举值获取枚举
     *
     * @param value 枚举值
     * @return com.wenlincheng.pika.common.enums.ImageTypeEnum
     */
    public static ImageTypeEnum valueOf(int value) {
        for (ImageTypeEnum imageType : ImageTypeEnum.values()) {
            if (imageType.getValue() == value) {
                return imageType;
            }
        }
        return UNKNOWN;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
