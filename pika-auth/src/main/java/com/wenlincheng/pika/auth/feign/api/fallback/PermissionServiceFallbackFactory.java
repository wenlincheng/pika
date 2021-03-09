package com.wenlincheng.pika.auth.feign.api.fallback;

import com.wenlincheng.pika.auth.feign.api.PermissionService;
import com.wenlincheng.pika.auth.feign.dto.Permission;
import com.wenlincheng.pika.common.core.base.vo.Result;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
public class PermissionServiceFallbackFactory implements FallbackFactory<PermissionService> {

    @Override
    public PermissionService create(Throwable throwable) {
        return new PermissionService() {
            @Override
            public Result<List<Permission>> queryPermissionByUserId(Long userId) {
                log.error("查询用户:{} 的权限列表失败:{}", userId, throwable.getMessage());
                return Result.success(new ArrayList<>());
            }

            @Override
            public Result<List<Permission>> queryAllPermissions() {
                log.error("查询权限列表失败:{}", throwable.getMessage());
                return Result.success(new ArrayList<>());
            }

            @Override
            public Result<List<Permission>> queryPermissionsByRoleId(Long roleId) {
                log.error("查询角色:{} 的权限列表失败:{}", roleId, throwable.getMessage());
                return Result.success(new ArrayList<>());
            }

            @Override
            public Result<Permission> queryPermissionByUrl(String requestUrl) {
                return Result.success(new Permission());
            }
        };
    }
}
