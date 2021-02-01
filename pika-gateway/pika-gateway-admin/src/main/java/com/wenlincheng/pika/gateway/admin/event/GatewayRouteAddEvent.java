package com.wenlincheng.pika.gateway.admin.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;

/**
 * 添加网关路由消息总线事件
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class GatewayRouteAddEvent extends RemoteApplicationEvent {

    public GatewayRouteAddEvent() {
    }

    /**
     * 网关路由
     */
    private RouteDefinition routeDefinition;

    public GatewayRouteAddEvent(Object source, String originService, String destinationService, RouteDefinition routeDefinition) {
        super(source, originService);
        this.routeDefinition = routeDefinition;
    }
}
