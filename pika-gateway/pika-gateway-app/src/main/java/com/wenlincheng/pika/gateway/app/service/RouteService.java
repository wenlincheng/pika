package com.wenlincheng.pika.gateway.app.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.Collection;

/**
 * 内存中路由
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public interface RouteService {
    /**
     * 从内存中加载路由进行初始化
     *
     * @return Collection<RouteDefinition>
     */
    Collection<RouteDefinition> getRouteDefinitions();

    /**
     * 在内存中添加路由
     *
     * @param routeDefinition 路由
     * @return boolean
     */
    boolean save(RouteDefinition routeDefinition);

    /**
     * 修改在内存中的路由
     *
     * @param routeDefinition 路由
     * @return boolean
     */
    boolean update(RouteDefinition routeDefinition);

    /**
     * 删除内存中的路由
     *
     * @param routeId
     * @return boolean
     */
    boolean delete(String routeId);
}
