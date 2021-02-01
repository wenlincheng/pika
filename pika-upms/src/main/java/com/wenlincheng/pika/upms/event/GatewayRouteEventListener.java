package com.wenlincheng.pika.upms.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bus.event.AckRemoteApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 消息确认
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Component
public class GatewayRouteEventListener {

    @EventListener(AckRemoteApplicationEvent.class)
    public void onAckEvent(AckRemoteApplicationEvent event) {
        log.info("[消息确认]消费者端: " + event.getDestinationService() + ", 服务消费确认 ackId :" + event.getAckId());
    }
}
