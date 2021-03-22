package com.wenlincheng.pika.gateway.admin.client.api.fallback;

import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.gateway.admin.client.api.AuthService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 熔断降级
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Component
public class AuthServiceFallbackFactory implements FallbackFactory<AuthService> {
    @Override
    public AuthService create(Throwable throwable) {
        return (token, uri, method) -> {
            log.error("鉴权失败 {} ", throwable.getMessage());
            return Result.fail();
        };
    }
}
