package com.wenlincheng.pika.auth.client.api.fallback;

import com.wenlincheng.pika.auth.client.api.RoleService;
import com.wenlincheng.pika.auth.client.dto.Role;
import com.wenlincheng.pika.common.core.base.vo.Result;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 服务降级处理
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Component
public class RoleServiceFallbackFactory implements FallbackFactory<RoleService> {

    @Override
    public RoleService create(Throwable throwable) {
        return new RoleService() {
            @Override
            public Result<List<Role>> getRolesByUserId(Long userId) {
                log.error("查询用户:{} 的角色列表失败:{}", userId, throwable.getMessage());
                return Result.fail();
            }

            @Override
            public Result<List<Role>> getAllRoles() {
                log.error("查询角色列表失败:{}", throwable.getMessage());
                return Result.fail();
            }
        };
    }
}
