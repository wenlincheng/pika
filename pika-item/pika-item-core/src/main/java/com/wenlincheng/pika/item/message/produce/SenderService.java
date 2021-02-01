package com.wenlincheng.pika.item.message.produce;

import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 消息发送服务
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Service
public class SenderService {

    @Autowired
    private MySource source;

    /**
     * TODO
     *
     * @param msg 消息
     * @throws Exception
     * @return void
     */
    public void send(String msg) throws Exception {
        source.output1().send(MessageBuilder.withPayload(msg).build());
    }

    /**
     * TODO
     *
     * @param msg
     * @param tag
     * @throws Exception
     * @return void
     */
    public <T> void sendWithTags(T msg, String tag) throws Exception {
        Message<T> message = MessageBuilder.createMessage(msg,
                new MessageHeaders(Stream.of(tag).collect(Collectors
                        .toMap(str -> MessageConst.PROPERTY_TAGS, String::toString))));
        source.output1().send(message);
    }

    /**
     * TODO
     *
     * @param msg
     * @param tag
     * @throws
     * @return void
     */
    public <T> void sendObject(T msg, String tag) throws Exception {
        Message<T> message = MessageBuilder.withPayload(msg)
                .setHeader(MessageConst.PROPERTY_TAGS, tag)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
        source.output1().send(message);
    }

    /**
     * 发送事务消息
     *
     * @param msg
     * @param num
     * @throws
     * @return void
     */
    public <T> void sendTransactionalMsg(T msg, int num) throws Exception {
        MessageBuilder<T> builder = MessageBuilder.withPayload(msg)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
        builder.setHeader("test", String.valueOf(num));
        builder.setHeader(RocketMQHeaders.TAGS, "binder");
        Message<T> message = builder.build();
        source.output2().send(message);
    }
}
