package com.wenlincheng.pika.common.leaf.enums;

import com.wenlincheng.pika.common.core.exception.ErrorCode;
import com.wenlincheng.pika.common.core.exception.ErrorTypeEnum;
import lombok.Getter;

/**
 * TODO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum LeafErrorCodeEnum implements ErrorCode {

    SYSTEM_ERROR(ErrorTypeEnum.SYSTEM_ERROR, 10700001, "错误: 系统异常"),
    ID_GEN_ERROR(ErrorTypeEnum.BIZ_ERROR, 10700002, "错误: ID生成失败"),
    NOT_EXIST_LEAF_ALLOC_CONFIG_ERROR(ErrorTypeEnum.BIZ_ERROR, 10700003, "错误: 使用ID生成器之前请先配置序列(LeafAlloc)规则"),
    NOT_EXIST_SEQUENCE_CONFIG_CONFIG_ERROR(ErrorTypeEnum.BIZ_ERROR, 10700004, "错误: 使用ID生成器之前请先配置序列生成配置(SequenceConfig)"),

    ID_CACHE_INIT_FALSE(ErrorTypeEnum.SYSTEM_ERROR, 10700005, "错误: IDCache未初始化成功"), // -1
    ID_KEY_NOT_EXISTS(ErrorTypeEnum.SYSTEM_ERROR, 10700006, "错误: key不存在"), // -2
    ID_TWO_SEGMENTS_ARE_NULL(ErrorTypeEnum.SYSTEM_ERROR, 10700007, "错误: SegmentBuffer中的两个Segment均未从DB中装载时"), // -3
    LEAF_NOT_INIT_OK_ERROR(ErrorTypeEnum.SYSTEM_ERROR, 10700008, "错误: ID生成器未初始化完成"),
    ;

    private ErrorTypeEnum type;
    private int code;
    private String msg;

    LeafErrorCodeEnum(ErrorTypeEnum type, int code, String msg) {
        this.type = type;
        this.code = code;
        this.msg = msg;
    }
}
