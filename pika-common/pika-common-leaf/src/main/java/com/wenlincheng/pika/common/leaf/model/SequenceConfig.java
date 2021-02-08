package com.wenlincheng.pika.common.leaf.model;

import com.wenlincheng.pika.common.leaf.enums.SequenceTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 序列生成配置
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
@Accessors(chain = true)
public class SequenceConfig {

    /**
     * 业务标识
     */
    private String key;

    /**
     * 序列号类型
     */
    private String type;

    /**
     * 前缀
     */
    private String prefix;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 分隔符
     */
    private String separator;

    /**
     * 长度
     */
    private Integer size;

    /**
     * 日期格式
     */
    private String format;

    /**
     * 步长
     */
    private Integer step;
}
