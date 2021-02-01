package com.wenlincheng.pika.auth.client.api;

import com.wenlincheng.pika.auth.client.api.fallback.PermissionServiceFallbackFactory;
import com.wenlincheng.pika.auth.client.dto.Permission;
import com.wenlincheng.pika.common.core.base.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 权限API
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@FeignClient(contextId = "permissionService", value = "pika-upms", fallbackFactory = PermissionServiceFallbackFactory.class)
public interface PermissionService {

    /**
     * 根据用户id查询权限列表信息
     *
     * @param userId 用户id
     * @return Result<List<Permission>>
     */
    @GetMapping(value = "/menu/perms/user/{userId}")
    Result<List<Permission>> queryPermissionByUserId(@PathVariable("userId") Long userId);

    /**
     * 查询所有激活的权限列表
     *
     * @return Result<List<Permission>>
     */
    @GetMapping(value = "/menu/perms/list")
    Result<List<Permission>> queryAllPermissions();

    /**
     * 查询角色激活的权限列表
     *
     * @param roleId 角色id
     * @return Result<List<Permission>>
     */
    @GetMapping(value = "/menu/perms/role/{roleId}")
    Result<List<Permission>> queryPermissionsByRoleId(@PathVariable("roleId") Long roleId);

    /**
     * 根据请求URL获取权限详情
     *
     * @param url 请求url
     * @return Result<Permission>
     */
    @GetMapping(value = "/menu/perms/url/{url}")
    Result<Permission> queryPermissionByUrl(@PathVariable("url") String url);
}
