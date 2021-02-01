package com.wenlincheng.pika.gateway.admin.limiter;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.support.ConfigurationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 自定义限流器
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Configuration
public class DefaultRedisRateLimiter extends RedisRateLimiter {

    Config getDefaultConfig() {
        return super.getConfig().get("defaultFilters");
    }

    public DefaultRedisRateLimiter(ReactiveStringRedisTemplate redisTemplate, RedisScript<List<Long>> script, ConfigurationService configurationService) {
        super(redisTemplate, script, configurationService);
    }

    @Override
    public Mono<Response> isAllowed(String routeId, String id) {
        if (null == super.getConfig().get(routeId)) {
            getConfig().put(routeId, getDefaultConfig());
        }
        return super.isAllowed(routeId, id);
    }
}
