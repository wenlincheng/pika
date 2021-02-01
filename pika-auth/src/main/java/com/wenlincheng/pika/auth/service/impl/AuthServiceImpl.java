package com.wenlincheng.pika.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.wenlincheng.pika.auth.client.api.PermissionService;
import com.wenlincheng.pika.auth.client.api.UserService;
import com.wenlincheng.pika.auth.client.dto.Permission;
import com.wenlincheng.pika.auth.client.dto.User;
import com.wenlincheng.pika.auth.entity.AuthUser;
import com.wenlincheng.pika.auth.exception.AuthErrorCodeEnum;
import com.wenlincheng.pika.auth.manager.JwtTokenManager;
import com.wenlincheng.pika.auth.service.AuthService;
import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.common.core.constant.SecurityConstants;
import com.wenlincheng.pika.common.core.exception.BaseException;
import com.wenlincheng.pika.common.core.exception.SystemErrorCodeEnum;
import com.wenlincheng.pika.common.core.redis.RedisUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.wenlincheng.pika.auth.exception.AuthErrorCodeEnum.USER_NOT_FOUND;
import static com.wenlincheng.pika.common.core.constant.SecurityConstants.PERMISSIONS_REDIS_KEY;

/**
 * 提供给网关的鉴权接口
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenManager jwtTokenManager;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean logout(String token) {
        AuthUser authUser = this.getUserInfoByToken(token);
        redisUtils.delete(SecurityConstants.JWT_TOKEN_REDIS_PREFIX + authUser.getId());
        return true;
    }

    @Override
    public AuthUser getUserInfoByToken(String token) {
        AuthUser authUser = new AuthUser();
        try {
            // 解析token
            Claims claims = jwtTokenManager.parserToken(token);
            // 获取用用户信息
            String username = claims.getSubject();
            Result<User> userResult = userService.getUserByUsername(username);
            if (userResult != null) {
                User user = userResult.getData();
                if (user != null) {
                    authUser.setId(user.getId());
                    authUser.setUsername(user.getUsername());
                    authUser.setName(user.getName());
                    authUser.setAvatar(user.getAvatar());
                    authUser.setStatus(user.getStatus());
                    authUser.setRoleList(user.getRoleList());
                }
            }

        } catch (ExpiredJwtException e) {
            throw new BaseException(AuthErrorCodeEnum.TOKEN_EXPIRED);
        } catch (Exception e) {
            throw new BaseException(AuthErrorCodeEnum.TOKEN_MALFORMED);
        }

        return authUser;
    }

    @Override
    public AuthUser authDecide(HttpServletRequestWrapper requestWrapper, String uri, String method) throws JwtException{
        String token = requestWrapper.getHeader(SecurityConstants.JWT_TOKEN_HEADER);
        if (StringUtils.isBlank(token) || !token.startsWith(SecurityConstants.JWT_TOKEN_PREFIX)) {
            throw new BaseException(AuthErrorCodeEnum.TOKEN_EMPTY);
        }
        Long userId = jwtTokenManager.getUserIdByToken(token);
        String redisToken = redisUtils.get(SecurityConstants.JWT_TOKEN_REDIS_PREFIX + userId);
        if (StringUtils.isBlank(redisToken)) {
            throw new BaseException(AuthErrorCodeEnum.TOKEN_LOGOUT);
        }
        if (!redisToken.equals(token.replace(SecurityConstants.JWT_TOKEN_PREFIX, ""))) {
            throw new BaseException(AuthErrorCodeEnum.TOKEN_EXPIRED);
        }
        // 获取用户认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        ConfigAttribute urlConfigAttribute = getPermissionByUrl(uri, method);
        if (Objects.isNull(urlConfigAttribute)) {
            throw new BaseException(SystemErrorCodeEnum.RESOURCE_NOT_FOUND);
        }
        if (!isMatch(urlConfigAttribute, authorities)) {
            throw new BaseException(AuthErrorCodeEnum.UNAUTHORIZED);
        }
        return getUserInfo(authentication.getName());
    }

    /**
     * 获取请求对应的权限
     *
     *
     * @param uri 请求链接
     * @param method 请求方法
     * @return ConfigAttribute
     */
    private ConfigAttribute getPermissionByUrl(String uri, String method) {
        List<Permission> permissionList;
        String allPermissionRedis = redisUtils.get(PERMISSIONS_REDIS_KEY);
        if (StringUtils.isBlank(allPermissionRedis)) {
            permissionList = permissionService.queryAllPermissions().getData();
            redisUtils.setEx(PERMISSIONS_REDIS_KEY, JSON.toJSONString(permissionList), 480, TimeUnit.MINUTES);
        } else {
            permissionList = JSON.parseArray(allPermissionRedis, Permission.class);
        }
        PathMatcher pathMatcher = new AntPathMatcher();
        for (Permission permission : permissionList) {
            if (pathMatcher.match(permission.getUri() + " " + permission.getMethod(), uri + " " + method)) {
               return new SecurityConfig(permission.getPermission());
            }
        }
        return null;
    }

    /**
     * 用户拥有权限与请求的资源进行对比
     *
     * @param urlConfigAttribute
     * @param authorities
     * @return boolean
     */
    public boolean isMatch(ConfigAttribute urlConfigAttribute, Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream().anyMatch(authority -> authority.getAuthority().equals(urlConfigAttribute.getAttribute()));
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return AuthUser
     */
    public AuthUser getUserInfo(String username) {
        Result<User> userResult = userService.getUserByUsername(username);
        User user = userResult.getData();
        if (Objects.isNull(user)) {
            throw new BaseException(USER_NOT_FOUND);
        }
        AuthUser authUser = new AuthUser();
        authUser.setId(user.getId());
        authUser.setUsername(user.getUsername());
        authUser.setName(user.getName());
        authUser.setAvatar(user.getAvatar());
        authUser.setStatus(user.getStatus());
        return authUser;
    }

    public static void main(String[] args) {
        PathMatcher pathMatcher = new AntPathMatcher();
        boolean match = pathMatcher.match("/menu/{id}" + " " + "GET", "/menu/101" + " " + "GET");
        System.out.println(match);
    }
}
