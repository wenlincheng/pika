package com.wenlincheng.pika.common.core.redis;

/**
 * 自定义 CacheErrorHandler 处理缓存读写的异常
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;


/**
 * 自定义 CacheErrorHandler 当缓存读写异常时,忽略异常
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class IgnoreExceptionCacheErrorHandler implements CacheErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(IgnoreExceptionCacheErrorHandler.class);

    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        log.error(exception.getMessage(), exception);
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
        log.error(exception.getMessage(), exception);
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        log.error(exception.getMessage(), exception);
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        log.error(exception.getMessage(), exception);
    }
}

