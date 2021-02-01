package com.wenlincheng.pika.common.core.enums;

import java.io.Serializable;

/**
 * 枚举
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public interface IEnum<T extends Serializable> {

    /**
     * 枚举值
     *
     * @return T
     */
    T getValue();

    /**
     * 名称
     *
     * @return java.lang.String
     */
    String getName();

    /**
     * 描述
     *
     * @return java.lang.String
     */
    String getHelp();

}
