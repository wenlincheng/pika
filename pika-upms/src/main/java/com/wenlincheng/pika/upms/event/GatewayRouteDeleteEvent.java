package com.wenlincheng.pika.upms.event;

import lombok.Data;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;

/**
 * 网关路由删除事件
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class GatewayRouteDeleteEvent extends RemoteApplicationEvent {

    public GatewayRouteDeleteEvent() {
    }

    /**
     * 网关路由
     */
    private RouteDefinition routeDefinition;

    public GatewayRouteDeleteEvent(Object source, String originService, String destinationService, RouteDefinition routeDefinition) {
        super(source, originService, destinationService);
        this.routeDefinition = routeDefinition;
    }
}
