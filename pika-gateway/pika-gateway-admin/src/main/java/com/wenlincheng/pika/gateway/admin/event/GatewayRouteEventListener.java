package com.wenlincheng.pika.gateway.admin.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenlincheng.pika.gateway.admin.route.DynamicRouteEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Component
public class GatewayRouteEventListener {

    @Autowired
    private DynamicRouteEventPublisher eventPublisher;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 监听网关添加事件
     *
     * @param event 事件
     */
    @EventListener(GatewayRouteAddEvent.class)
    public void onEvent(GatewayRouteAddEvent event) throws JsonProcessingException {
        log.info("监听到网关添加事件 {}", objectMapper.writeValueAsString(event));
        eventPublisher.add(event.getRouteDefinition());
    }

    /**
     * 监听网关修改事件
     *
     * @param event 事件
     */
    @EventListener(GatewayRouteUpdateEvent.class)
    public void onEvent(GatewayRouteUpdateEvent event) throws JsonProcessingException {
        log.info("监听到网关修改事件 {}", objectMapper.writeValueAsString(event));
        eventPublisher.update(event.getRouteDefinition());
    }

    /**
     * 监听网关删除事件
     *
     * @param event 事件
     */
    @EventListener(GatewayRouteDeleteEvent.class)
    public void onEvent(GatewayRouteDeleteEvent event) throws JsonProcessingException {
        log.info("监听到网关删除事件 {}", objectMapper.writeValueAsString(event));
        eventPublisher.delete(event.getRouteDefinition().getId());
    }

    /**
     * 监听网关刷新事件
     *
     * @param event 事件
     */
    @EventListener(GatewayRouteRefreshEvent.class)
    public void onEvent(GatewayRouteRefreshEvent event) throws JsonProcessingException {
        log.info("监听到网关刷新事件 {}", objectMapper.writeValueAsString(event));
        // 刷新网关

    }
}
