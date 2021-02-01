package com.wenlincheng.pika.item.message.consume;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.messaging.SubscribableChannel;

/**
 * TODO
 *
 * @author : Pikaman
 * @version : 1.0.0
 * @date : 2021/1/26 11:12 下午
 */
public interface MySink {
    @Input("input1")
    SubscribableChannel input1();

    @Input("input2")
    SubscribableChannel input2();

    @Input("input3")
    SubscribableChannel input3();

    @Input("input4")
    SubscribableChannel input4();

    @Input("input5")
    PollableMessageSource input5();

}
