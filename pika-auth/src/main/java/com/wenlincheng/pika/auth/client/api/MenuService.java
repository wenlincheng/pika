package com.wenlincheng.pika.auth.client.api;

import com.wenlincheng.pika.auth.client.api.fallback.MenuServiceFallbackFactory;
import com.wenlincheng.pika.auth.client.dto.Menu;
import com.wenlincheng.pika.common.core.base.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 菜单API
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@FeignClient(contextId = "menuService", value = "pika-upms", fallbackFactory = MenuServiceFallbackFactory.class)
public interface MenuService {

    /**
     * 根据用户id查询菜单权限列表
     *
     * @param userId 用户id
     * @return Result<List<Menu>>
     */
    @GetMapping(value = "/menu/perms/user/{userId}")
    Result<List<Menu>> queryPermsByUserId(@PathVariable("userId") Long userId);

    /**
     * 查询所有菜单权限列表
     *
     * @return Result<List<Menu>>
     */
    @GetMapping(value = "/menu/perms/list")
    Result<List<Menu>> queryPermsList();

    /**
     * 查询角色的菜单权限列表
     *
     * @param roleId 角色id
     * @return Result<List<Menu>>
     */
    @GetMapping(value = "/menu/perms/role/{roleId}")
    Result<List<Menu>> queryPermsByRoleId(@PathVariable("roleId") Long roleId);

}
