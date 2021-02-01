package com.wenlincheng.pika.upms.event;

import lombok.Data;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;

/**
 * 网关路由刷新事件
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class GatewayRouteRefreshEvent extends RemoteApplicationEvent {

    public GatewayRouteRefreshEvent() {
    }

    public GatewayRouteRefreshEvent(Object source, String originService, String destinationService) {
        super(source, originService, destinationService);
    }
}
