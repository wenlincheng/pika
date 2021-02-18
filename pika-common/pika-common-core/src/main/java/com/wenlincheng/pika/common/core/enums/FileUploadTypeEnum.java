package com.wenlincheng.pika.common.core.enums;

/**
 * 文件上传类型枚举
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public enum FileUploadTypeEnum {
    
    COMMON(1,"公共文件","pika/file/"),
    IMAGE(2,"公共图片","pika/image/"),
    QRCODE(3,"二维码图片","pika/qrcode/"),
    CONTENT(4,"文章内容图片","pika/content/"),
    COVER(5,"文章封面图片","pika/cover/");
    
    /**
     * 值
     */
    private int value;

    /**
     * 描述
     */
    private String desc;

    /**
     * 存储路径
     */
    private String path;

    FileUploadTypeEnum(int value, String desc, String path) {
        this.value = value;
        this.desc = desc;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

}
