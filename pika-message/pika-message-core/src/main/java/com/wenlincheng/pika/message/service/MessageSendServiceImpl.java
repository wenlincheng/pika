package com.wenlincheng.pika.message.service;

import com.wenlincheng.pika.message.pojo.param.BatchSendParam;
import com.wenlincheng.pika.message.pojo.param.SendParam;
import com.wenlincheng.pika.message.api.MessageSendService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 发送消息实现
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@DubboService(version = "1.0.0", protocol = "dubbo")
public class MessageSendServiceImpl implements MessageSendService {

    @Override
    public void send(SendParam sendParam) {

    }

    @Override
    public void batchSend(BatchSendParam sendParam) {

    }
}
