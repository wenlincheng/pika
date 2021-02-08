package com.wenlincheng.pika.common.leaf.service;

import com.wenlincheng.pika.common.core.exception.BaseException;
import com.wenlincheng.pika.common.leaf.model.SequenceConfig;

/**
 * 号段模式id生成器
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public interface LeafSegmentService {

    /**
     * 分布式有序id
     *
     * @param key 业务标识
     * @throws BaseException
     * @return java.lang.Long
     */
    Long genId(String key);

    /**
     * 日期自增流水号
     *
     * @param seqConfig 配置
     * @return java.lang.String
     */
    String genDateOrderlySeq(SequenceConfig seqConfig);

    /**
     * 日期流水号
     *
     * @param seqConfig 配置
     * @return java.lang.String
     */
    String genDateSeq(SequenceConfig seqConfig);

    /**
     * 自增流水号
     *
     * @param seqConfig 配置
     * @return java.lang.String
     */
    String genSeq(SequenceConfig seqConfig);
    
    /**
     * 生成随机编号
     * 
     * @param seqConfig 配置
     * @return java.lang.String
     */
    String genCode(SequenceConfig seqConfig);
}
