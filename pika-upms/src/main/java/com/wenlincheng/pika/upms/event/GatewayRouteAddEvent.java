package com.wenlincheng.pika.upms.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;

/**
 * 网关路由添加事件
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
        super(source, originService, destinationService);
        this.routeDefinition = routeDefinition;
    }
}
