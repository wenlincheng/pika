package com.wenlincheng.pika.message.service;

import com.wenlincheng.pika.message.pojo.param.BatchSendParam;
import com.wenlincheng.pika.message.pojo.param.SendParam;

/**
 * 消息发送服务
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public interface MessageSendService {

    /**
     * 发相同消息给不同人
     *
     * @param sendParam 发送参数
     * @return void
     */
    void send(SendParam sendParam);

    /**
     * 发不同消息给不同人
     *
     * @param sendParam 发送参数
     * @return void
     */
    void batchSend(BatchSendParam sendParam);

}
