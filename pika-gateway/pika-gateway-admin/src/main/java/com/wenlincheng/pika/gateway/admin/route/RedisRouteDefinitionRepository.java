package com.wenlincheng.pika.gateway.admin.route;

import com.wenlincheng.pika.gateway.admin.service.impl.RouteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 动态路由
 * 从存储器中读取路由信息
 *
 * Spring Cloud Gateway 中加载路由信息分别由以下几个类负责
 * PropertiesRouteDefinitionLocator：从配置文件中读取路由信息(如YML、Properties等)
 * RouteDefinitionRepository：从存储器中读取路由信息(如内存、配置中心、Redis、MySQL等)
 * DiscoveryClientRouteDefinitionLocator：从注册中心中读取路由信息(如Nacos、Eurka、Zookeeper等)
 * @author  wenlincheng
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    @Autowired
    private RouteServiceImpl routeServiceImpl;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.fromIterable(routeServiceImpl.getRouteDefinitions());
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(routeDefinition -> {
            routeServiceImpl.save(routeDefinition);
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            routeServiceImpl.delete(id);
            return Mono.empty();
        });
    }
}
