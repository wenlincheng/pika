package com.wenlincheng.pika.message.service.impl;


import com.wenlincheng.pika.message.entity.param.BatchSendParam;
import com.wenlincheng.pika.message.entity.param.SingleSendParam;
import com.wenlincheng.pika.message.sender.email.DefaultEmailSender;
import com.wenlincheng.pika.message.service.MessageSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发送消息实现
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Service
public class MessageSendServiceImpl implements MessageSendService {

    @Autowired
    private DefaultEmailSender emailSender;

    @Override
    public void send(SingleSendParam singleSendParam) {

    }


    private Boolean sendEmail() {


        return true;
    }

    @Override
    public void batchSend(BatchSendParam sendParam) {

    }
}
