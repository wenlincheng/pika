package com.wenlincheng.pika.auth.feign.api.fallback;

import com.wenlincheng.pika.auth.feign.api.UserService;
import com.wenlincheng.pika.common.core.base.vo.Result;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 服务降级处理
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Component
public class UserServiceFallbackFactory implements FallbackFactory<UserService> {

    @Override
    public UserService create(Throwable throwable) {
        return username -> {
            log.error("查询用户失败:{}", throwable.getMessage());
            return Result.success(null);
        };

    }
}
