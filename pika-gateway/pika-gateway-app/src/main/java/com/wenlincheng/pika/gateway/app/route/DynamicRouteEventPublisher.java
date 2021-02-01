package com.wenlincheng.pika.gateway.app.route;

import com.wenlincheng.pika.gateway.app.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 发布动态路由事件
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Component
public class DynamicRouteEventPublisher implements ApplicationEventPublisherAware {

    @Autowired
    private RouteService routeService;

    @Resource
    private RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    /**
     * 发布变更事件
     *
     */
    private void notifyChanged() {
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    /**
     * 增加路由
     *
     * @param definition 路由
     */
    public void add(RouteDefinition definition) {
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        routeService.save(definition);
        notifyChanged();
    }

    /**
     * 更新路由
     *
     * @param definition 路由
     * @return java.lang.String
     */
    public String update(RouteDefinition definition) {
        // 删除
        try {
            delete(definition.getId());
        } catch (Exception e) {
            return "更新路由失败 routeId: "+definition.getId();
        }
        // 新增
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            notifyChanged();
            routeService.update(definition);
            return "success";
        } catch (Exception e) {
            return "更新路由失败  fail";
        }
    }

    /**
     * 删除路由
     *
     * @param id 路由id
     * @return Mono<ResponseEntity<Object>>
     */
    public Mono<ResponseEntity<Object>> delete(String id) {
        return this.routeDefinitionWriter.delete(Mono.just(id)).then(Mono.defer(() -> {
            routeService.delete(id);
            return Mono.just(ResponseEntity.ok().build());
        })).onErrorResume((t) -> {
            return t instanceof NotFoundException;
        }, (t) -> {
            return Mono.just(ResponseEntity.notFound().build());
        });
    }
}
