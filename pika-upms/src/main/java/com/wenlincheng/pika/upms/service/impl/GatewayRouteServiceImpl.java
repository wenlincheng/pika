package com.wenlincheng.pika.upms.service.impl;

import com.alibaba.fastjson.JSON;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenlincheng.pika.common.core.enums.YnEnum;
import com.wenlincheng.pika.common.core.exception.PikaException;
import com.wenlincheng.pika.common.core.redis.RedisUtils;
import com.wenlincheng.pika.upms.constant.UpmsConstants;
import com.wenlincheng.pika.upms.entity.po.GatewayRoute;
import com.wenlincheng.pika.upms.entity.query.gateway.GatewayRoutePageQuery;
import com.wenlincheng.pika.upms.entity.vo.gateway.GatewayRouteVO;
import com.wenlincheng.pika.upms.event.GatewayRouteAddEvent;
import com.wenlincheng.pika.upms.event.GatewayRouteDeleteEvent;
import com.wenlincheng.pika.upms.event.GatewayRouteRefreshEvent;
import com.wenlincheng.pika.upms.event.GatewayRouteUpdateEvent;
import com.wenlincheng.pika.upms.mapper.GatewayRouteMapper;
import com.wenlincheng.pika.upms.service.GatewayRouteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.bus.ServiceMatcher;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import static com.wenlincheng.pika.common.core.constant.CommonRedisKeyConstants.GATEWAY_ROUTES_ADMIN;


/**
 * <p>
 * 网关路由表 服务实现类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Service
public class GatewayRouteServiceImpl extends ServiceImpl<GatewayRouteMapper, GatewayRoute> implements GatewayRouteService {

    @CreateCache(name = GATEWAY_ROUTES_ADMIN, cacheType = CacheType.REMOTE)
    private Cache<String, RouteDefinition> gatewayRouteCache;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private ServiceMatcher busServiceMatcher;

    @Override
    public boolean add(GatewayRoute gatewayRoute) {
        boolean isSuccess = this.save(gatewayRoute);
        RouteDefinition routeDefinition = gatewayRouteToRouteDefinition(gatewayRoute);
        redisUtils.set(GATEWAY_ROUTES_ADMIN + gatewayRoute.getRouteId(), JSON.toJSONString(routeDefinition));
        // 发布添加路由事件
        eventPublisher.publishEvent(new GatewayRouteAddEvent(this, busServiceMatcher.getServiceId(), UpmsConstants.GATEWAY_ROUTE_ADMIN_BUS_ID, routeDefinition));
        return isSuccess;
    }

    @Override
    public boolean delete(String id) {
        GatewayRoute gatewayRoute = this.getById(id);
        redisUtils.delete(GATEWAY_ROUTES_ADMIN + gatewayRoute.getRouteId());
        RouteDefinition routeDefinition = gatewayRouteToRouteDefinition(gatewayRoute);
        // 发布删除路由事件
        eventPublisher.publishEvent(new GatewayRouteDeleteEvent(this, busServiceMatcher.getServiceId(), UpmsConstants.GATEWAY_ROUTE_ADMIN_BUS_ID, routeDefinition));
        return this.removeById(id);
    }

    @Override
    public boolean update(GatewayRoute gatewayRoute) {
        boolean isSuccess = this.updateById(gatewayRoute);
        RouteDefinition routeDefinition = gatewayRouteToRouteDefinition(gatewayRoute);
        redisUtils.set(GATEWAY_ROUTES_ADMIN + gatewayRoute.getRouteId(), JSON.toJSONString(routeDefinition));
        // 发布更新路由事件
        eventPublisher.publishEvent(new GatewayRouteUpdateEvent(this, busServiceMatcher.getServiceId(), UpmsConstants.GATEWAY_ROUTE_ADMIN_BUS_ID, routeDefinition));
        return isSuccess;
    }

    @Override
    public GatewayRoute get(Long id) {
        return this.getById(id);
    }

    @Override
    public IPage<GatewayRouteVO> pageList(GatewayRoutePageQuery queryForm) {
        QueryWrapper<GatewayRoute> queryWrapper = queryForm.buildWrapper();
        queryWrapper.lambda()
                .eq(GatewayRoute::getIsDeleted, YnEnum.NO.getValue())
                .eq(StringUtils.isNotBlank(queryForm.getUri()), GatewayRoute::getUri, queryForm.getUri());
        IPage<GatewayRoute> routePage = this.page(queryForm.getPage(), queryWrapper);

        return routePage.convert(GatewayRouteVO::new);
    }

    @Override
    @PostConstruct
    public boolean refresh() {
        QueryWrapper<GatewayRoute> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(GatewayRoute::getStatus, YnEnum.YES.getValue())
                .eq(GatewayRoute::getIsDeleted, YnEnum.NO.getValue());
        List<GatewayRoute> gatewayRoutes = this.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(gatewayRoutes)) {
            gatewayRoutes.forEach(gatewayRoute -> {
                redisUtils.delete(GATEWAY_ROUTES_ADMIN + gatewayRoute.getRouteId());
                redisUtils.set(GATEWAY_ROUTES_ADMIN + gatewayRoute.getRouteId(), JSON.toJSONString(gatewayRouteToRouteDefinition(gatewayRoute)));
            });
        }
        // 通知网关刷新路由
        eventPublisher.publishEvent(new GatewayRouteRefreshEvent(this, busServiceMatcher.getServiceId(), UpmsConstants.GATEWAY_ROUTE_ADMIN_BUS_ID));
        log.info("刷新网关路由成功!");
        return true;
    }

    /**
     * 将GatewayRoute对象转换为网关需要的RouteDefinition对象
     *
     * @param gatewayRoute 路由
     * @return RouteDefinition
     */
    private RouteDefinition gatewayRouteToRouteDefinition(GatewayRoute gatewayRoute) {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(gatewayRoute.getRouteId());
        routeDefinition.setOrder(gatewayRoute.getSeq());
        routeDefinition.setUri(URI.create(gatewayRoute.getUri()));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            routeDefinition.setFilters(objectMapper.readValue(gatewayRoute.getFilters(), new TypeReference<List<FilterDefinition>>() {}));
            routeDefinition.setPredicates(objectMapper.readValue(gatewayRoute.getPredicates(), new TypeReference<List<PredicateDefinition>>() {}));
        } catch (IOException e) {
            log.error("网关路由转换失败 {}", e.getMessage(), e.getCause());
            throw PikaException.construct(e).build();
        }
        return routeDefinition;
    }

}
