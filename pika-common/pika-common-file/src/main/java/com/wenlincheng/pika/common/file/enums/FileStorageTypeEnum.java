package com.wenlincheng.pika.common.file.enums;

import lombok.Getter;

/**
 * 文件存储类型枚举
 *
 * @author : wenlincheng
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum FileStorageTypeEnum {

    UNKNOWN(0,"--"),
    LOCAL(1, "本地"),
    QI_NIU(2,"七牛云"),
    ALI_YUN(3,"阿里云");

    private final int value;

    private final String desc;

    FileStorageTypeEnum (int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static FileStorageTypeEnum valueOf(int value) {
        for (FileStorageTypeEnum storageTypeEnum : values()) {
            if (storageTypeEnum.getValue() == value) {
                return storageTypeEnum;
            }
        }
        return UNKNOWN;
    }
}
