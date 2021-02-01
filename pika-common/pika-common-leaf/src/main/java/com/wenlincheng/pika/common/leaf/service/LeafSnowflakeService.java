package com.wenlincheng.pika.common.leaf.service;

import com.wenlincheng.pika.common.core.exception.BaseException;
import com.wenlincheng.pika.common.leaf.model.SequenceConfig;

/**
 * 雪花算法id生成器
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public interface LeafSnowflakeService {

    /**
     * 生成分布式id
     *
     * @param key 业务标识
     * @throws BaseException
     * @return java.lang.Long
     */
    Long genId(String key);

}
