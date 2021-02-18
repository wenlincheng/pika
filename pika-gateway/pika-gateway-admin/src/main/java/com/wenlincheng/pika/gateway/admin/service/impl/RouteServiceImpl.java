package com.wenlincheng.pika.gateway.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.wenlincheng.pika.common.core.exception.BaseException;
import com.wenlincheng.pika.common.core.redis.RedisUtils;
import com.wenlincheng.pika.gateway.admin.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

import static com.wenlincheng.pika.common.core.constant.CommonRedisKeyConstants.GATEWAY_ROUTES_ADMIN;
import static com.wenlincheng.pika.gateway.admin.enums.GatewayErrorCodeEnum.GATEWAY_LOAD_ROUTE_ERROR;

/**
 * 路由
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RedisUtils redisUtils;

    @CreateCache(name = GATEWAY_ROUTES_ADMIN, cacheType = CacheType.REMOTE)
    private Cache<String, RouteDefinition> gatewayRouteCache;

    private Map<String, RouteDefinition> routeDefinitions = new HashMap<>();

    @PostConstruct
    private void loadRouteDefinition() {
        log.info("开始初使化路由");
        // 从Redis缓存中加载路由到内存
        Set<String> gatewayKeys = redisUtils.keys(GATEWAY_ROUTES_ADMIN + "*");
        if (CollectionUtils.isEmpty(gatewayKeys)) {
            log.error("初始化路由异常，无法从缓存中加载数据");
            throw BaseException.construct(GATEWAY_LOAD_ROUTE_ERROR).build();
        }
        gatewayKeys.forEach(routeKey -> {
            String routeStr = redisUtils.get(routeKey);
            RouteDefinition routeDefinition = JSON.parseObject(routeStr, RouteDefinition.class);
            routeDefinitions.put(routeKey.replace(GATEWAY_ROUTES_ADMIN, StringUtils.EMPTY),routeDefinition);
        });

//        Set<String> gatewayKeyIds = gatewayKeys.stream().map(key -> key.replace(GATEWAY_ROUTES_ADMIN, StringUtils.EMPTY)).collect(Collectors.toSet());
//        Map<String, RouteDefinition> allRoutes = gatewayRouteCache.getAll(gatewayKeyIds);
//        // 以下代码原因是，jetcache将RouteDefinition返序列化后，uri发生变化，未初使化，导致路由异常，以下代码是重新初使化uri
//        allRoutes.values().forEach(routeDefinition -> {
//            try {
//                routeDefinition.setUri(new URI(routeDefinition.getUri().toASCIIString()));
//            } catch (URISyntaxException e) {
//                log.error("初始化路由异常：", e);
//            }
//        });
//        routeDefinitions.putAll(allRoutes);

        log.info("共初始化路由 {} 条", routeDefinitions.size());
    }

    @Override
    public Collection<RouteDefinition> getRouteDefinitions() {
        return routeDefinitions.values();
    }

    @Override
    public boolean save(RouteDefinition routeDefinition) {
        routeDefinitions.put(routeDefinition.getId(), routeDefinition);
        log.info("新增路由：{},共{}条", routeDefinition, routeDefinitions.size());
        return true;
    }

    @Override
    public boolean update(RouteDefinition routeDefinition) {
        RouteDefinition remove = routeDefinitions.remove(routeDefinition.getId());
        if (Objects.nonNull(remove)) {
            routeDefinitions.put(routeDefinition.getId(), routeDefinition);
        }
        log.info("修改路由：{},共{}条", routeDefinition, routeDefinitions.size());
        return false;
    }

    @Override
    public boolean delete(String routeId) {
        routeDefinitions.remove(routeId);
        log.info("删除路由：{},共{}条", routeId, routeDefinitions.size());
        return true;
    }
}
