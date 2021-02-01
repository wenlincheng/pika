package com.wenlincheng.pika.auth.client.api.fallback;

import com.wenlincheng.pika.auth.client.api.MenuService;
import com.wenlincheng.pika.auth.client.dto.Menu;
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
public class MenuServiceFallbackFactory implements FallbackFactory<MenuService> {

    @Override
    public MenuService create(Throwable throwable) {
        return new MenuService() {
            @Override
            public Result<List<Menu>> queryPermsByUserId(Long userId) {
                log.error("查询用户:{} 的权限列表失败:{}", userId, throwable.getMessage());
                return Result.fail();
            }

            @Override
            public Result<List<Menu>> queryPermsList() {
                log.error("的权限列表失败:{}", throwable.getMessage());
                return Result.fail();
            }

            @Override
            public Result<List<Menu>> queryPermsByRoleId(Long roleId) {
                log.error("查询角色:{} 的权限列表失败:{}", roleId, throwable.getMessage());
                return Result.fail();
            }
        };
    }
}
