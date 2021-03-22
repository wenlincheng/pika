package com.wenlincheng.pika.auth.service.impl;

import com.wenlincheng.pika.auth.feign.api.PermissionService;
import com.wenlincheng.pika.auth.feign.api.RoleService;
import com.wenlincheng.pika.auth.feign.api.UserService;
import com.wenlincheng.pika.auth.feign.dto.Permission;
import com.wenlincheng.pika.auth.feign.dto.Role;
import com.wenlincheng.pika.auth.feign.dto.User;
import com.wenlincheng.pika.common.core.constant.SecurityConstants;
import com.wenlincheng.pika.auth.entity.AuthUser;
import com.wenlincheng.pika.auth.security.AuthUserDetails;
import com.wenlincheng.pika.common.core.exception.PikaException;
import com.wenlincheng.pika.common.core.redis.RedisUtils;
import com.wenlincheng.pika.common.core.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static com.wenlincheng.pika.auth.exception.AuthErrorCodeEnum.ROLE_EMPTY;
import static com.wenlincheng.pika.auth.exception.AuthErrorCodeEnum.USER_LIMIT_TIME_UP;

/**
 * 实现用户信息获取接口
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    /**
     * 登录重试时间
     */
    @Value("${security.loginAfterTime}")
    private Integer loginAfterTime;

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 实现用户信息查询方法 让DaoAuthenticationProvider 获取到数据库获中用户数据
     *
     * @param username 用户名
     * @throws UsernameNotFoundException 用户名未找到
     * @return org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 登录失败次数超过限制
        String loginFail = SecurityConstants.LOGIN_FAIL_FLAG_REDIS_PREFIX + username;
        String value = redisUtils.get(loginFail);
        if (StringUtils.isNotBlank(value)) {
            throw new UsernameNotFoundException(loginAfterTime+"分钟后再试");
        }
        // 查询用户信息
        User user = userService.getUserByUsername(username).getData();
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        AuthUser authUser = new AuthUser();
        authUser.setId(user.getId());
        authUser.setUsername(user.getUsername());
        authUser.setPassword(user.getPassword());
        authUser.setStatus(user.getStatus());
        // 查询用户角色列表
        List<Role> roleList = roleService.getRolesByUserId(user.getId()).getData();
        if(CollectionUtils.isEmpty(roleList)){
            throw PikaException.construct(ROLE_EMPTY).build();
        }
        authUser.setRoleList(roleList);
        // 查询用户权限列表
        List<Permission> permissionList = permissionService.queryPermissionByUserId(user.getId()).getData();
        authUser.setPermissionList(permissionList);
        return new AuthUserDetails(authUser);
    }

}
