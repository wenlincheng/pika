package com.wenlincheng.pika.gateway.app.limiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 自定义限流key|字段解析器
 * 从exchange对象中获取服务ID、请求ip、请求信息，用户信息等
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Component
public class RequestRateLimiterConfig {

    /**
     * 根据ip地址限流
     *
     * @return KeyResolver
     */
    @Bean
    @Primary
    public KeyResolver remoteAddressKeyResolver() {
        log.info("根据ip地址限流");
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    /**
     * 根据请求路径限流
     *
     * @return KeyResolver
     */
    @Bean
    public KeyResolver apiKeyResolver() {
        log.info("根据请求路径限流");
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }

    /**
     * 根据username限流
     *
     * @return KeyResolver
     */
    @Bean
    public KeyResolver userKeyResolver() {
        log.info("根据username限流");
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("username"));
    }
}
