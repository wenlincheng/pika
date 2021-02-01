package com.wenlincheng.pika.auth.client.api;

import com.wenlincheng.pika.auth.client.api.fallback.UserServiceFallbackFactory;
import com.wenlincheng.pika.auth.client.dto.Role;
import com.wenlincheng.pika.common.core.base.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 角色API
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@FeignClient(contextId = "roleService", value = "pika-upms", fallbackFactory = UserServiceFallbackFactory.class)
public interface RoleService {
    /**
     * 根据用户id查询角色列表信息
     *
     * @param userId 用户id
     * @return Result<List<Role>>
     */
    @GetMapping(value = "/role/user/{userId}")
    Result<List<Role>> getRolesByUserId(@PathVariable("userId") Long userId);

    /**
     * 查询所有激活的角色
     *
     * @return Result<List<Role>>
     */
    @GetMapping(value = "/role/list")
    Result<List<Role>> getAllRoles();

}
