package com.wenlincheng.pika.auth.client.api;

import com.wenlincheng.pika.auth.client.api.fallback.UserServiceFallbackFactory;
import com.wenlincheng.pika.auth.client.dto.User;
import com.wenlincheng.pika.common.core.base.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户API
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@FeignClient(contextId = "userService", value = "pika-upms", fallbackFactory = UserServiceFallbackFactory.class)
public interface UserService {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return Result<User>
     */
    @GetMapping(value = "/user/username/{username}")
    Result<User> getUserByUsername(@PathVariable("username") String username);
}
