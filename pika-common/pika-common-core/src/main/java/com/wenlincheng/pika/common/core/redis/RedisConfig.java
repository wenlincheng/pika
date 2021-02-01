package com.wenlincheng.pika.common.core.redis;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Redis
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Override
    public CacheResolver cacheResolver() {
        // 通过Guava实现的自定义堆内存缓存管理器
        CacheManager caffeineCacheManager = new CaffeineCacheManager();
        CacheManager redisCacheManager = redisCacheManager();
        List<CacheManager> list = new ArrayList<>();
        // 优先读取堆内存缓存
        list.add(caffeineCacheManager);
        // 堆内存缓存读取不到该key时再读取redis缓存
        list.add(redisCacheManager);
        return new CustomCacheResolver(list);
    }

    /**
     * 添加自定义缓存异常处理
     * 当缓存读写异常时,忽略异常
     */
    @Override
    public CacheErrorHandler errorHandler() {
        return new IgnoreExceptionCacheErrorHandler();
    }

    @Bean
    public CacheManager redisCacheManager() {
        RedisSerializationContext.SerializationPair serializationPair =
                RedisSerializationContext.SerializationPair.fromSerializer(getRedisSerializer());
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(30))
                .serializeValuesWith(serializationPair);
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory))
                .cacheDefaults(redisCacheConfiguration).build();
    }

    private RedisSerializer<Object> getRedisSerializer(){
        return new GenericFastJsonRedisSerializer();
    }

}
