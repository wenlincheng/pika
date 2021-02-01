package com.wenlincheng.pika.common.core.redis;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 自定义CacheResolver
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class CustomCacheResolver implements CacheResolver, InitializingBean {

    @Nullable
    private List<CacheManager> cacheManagerList;

    public CustomCacheResolver(){
    }
    public CustomCacheResolver(List<CacheManager> cacheManagerList){
        this.cacheManagerList = cacheManagerList;
    }

    public void setCacheManagerList(@Nullable List<CacheManager> cacheManagerList) {
        this.cacheManagerList = cacheManagerList;
    }
    public List<CacheManager> getCacheManagerList() {
        return cacheManagerList;
    }

    @Override
    public void afterPropertiesSet()  {
        Assert.notNull(this.cacheManagerList, "CacheManager is required");
    }

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        Collection<String> cacheNames = getCacheNames(context);
        if (cacheNames == null) {
            return Collections.emptyList();
        }
        Collection<Cache> result = new ArrayList<>();
        for(CacheManager cacheManager : getCacheManagerList()){
            for (String cacheName : cacheNames) {
                Cache cache = cacheManager.getCache(cacheName);
                if (cache == null) {
                    throw new IllegalArgumentException("Cannot find cache named '" +
                            cacheName + "' for " + context.getOperation());
                }
                result.add(cache);
            }
        }
        return result;
    }

    private Collection<String> getCacheNames(CacheOperationInvocationContext<?> context){
        return context.getOperation().getCacheNames();
    }
}

