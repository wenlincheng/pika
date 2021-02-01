package com.wenlincheng.pika.common.core.enums;

/**
 * 编码枚举
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public enum EncodingEnum {
    UTF8("UTF-8"),
    GBK("GBK"),
    GB2312("GB2312"),
    ISO88591("ISO-8859-1");

    public final String type;

    EncodingEnum(String type) {
        this.type = type;
    }
}
