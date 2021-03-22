package com.wenlincheng.pika.auth.filter;

/**
 * TODO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */

import com.alibaba.fastjson.JSON;
import com.wenlincheng.pika.auth.feign.api.PermissionService;
import com.wenlincheng.pika.auth.feign.dto.Permission;
import com.wenlincheng.pika.common.core.constant.SecurityConstants;
import com.wenlincheng.pika.auth.exception.AuthErrorCodeEnum;
import com.wenlincheng.pika.auth.security.PikaGrantedAuthority;
import com.wenlincheng.pika.auth.manager.JwtTokenManager;
import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.common.core.exception.PikaException;
import com.wenlincheng.pika.common.core.redis.RedisUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.InvalidKeyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * JWT过滤器
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private static final String FILTER_APPLIED = "__spring_security_JWTAuthenticationFilter_filterApplied";

    @Autowired
    private JwtTokenManager tokenManager;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private PermissionService permissionService;

    // public JWTAuthenticationFilter(JwtTokenManager tokenManager, RedisUtils redisUtils, PermissionService permissionService) {
    //     this.tokenManager = tokenManager;
    //     this.redisUtils = redisUtils;
    //     this.permissionService = permissionService;
    // }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 解决同一请求，两次经过过滤器  原因：过滤器被加载WebSecurityConfig和spring都加载了
        if (request.getAttribute(FILTER_APPLIED) != null) {
            chain.doFilter(request, response);
            return;
        }
        request.setAttribute(FILTER_APPLIED, true);
        String token = request.getHeader(SecurityConstants.JWT_TOKEN_HEADER);
        if (StringUtils.isBlank(token) || !token.startsWith(SecurityConstants.JWT_TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if (authentication == null) {
            chain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    /**
     * 对token进行解析认证
     *
     * @param request 请求
     * @return UsernamePasswordAuthenticationToken
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.JWT_TOKEN_HEADER);
        if (StringUtils.isNotBlank(token) && token.startsWith(SecurityConstants.JWT_TOKEN_PREFIX)) {
            // 解析token
            try {
                Claims claims = tokenManager.parserToken(token);
                String username = claims.getSubject();
                Long userId = tokenManager.getUserIdByToken(token);
                String oldToken = redisUtils.get(SecurityConstants.JWT_TOKEN_REDIS_PREFIX + userId);
                if (StringUtils.isBlank(oldToken)) {
                    throw PikaException.construct(AuthErrorCodeEnum.TOKEN_LOGOUT).build();
                }
                if (!oldToken.equals(token.replace(SecurityConstants.JWT_TOKEN_PREFIX, ""))) {
                    throw PikaException.construct(AuthErrorCodeEnum.TOKEN_EXPIRED).build();
                }
                List<PikaGrantedAuthority> authorities = getUserAuthorities(userId);
                if (StringUtils.isNotEmpty(username)) {
                    User principal = new User(username, "", authorities);
                    return new UsernamePasswordAuthenticationToken(principal, null, authorities);
                }
            } catch (ExpiredJwtException e) {
                log.warn("Request to parse expired JWT : {} failed : {}", token, e.getMessage());
                throw PikaException.construct(AuthErrorCodeEnum.TOKEN_EXPIRED).build();
            } catch (UnsupportedJwtException e) {
                log.warn("Request to parse unsupported JWT : {} failed : {}", token, e.getMessage());
                throw PikaException.construct(AuthErrorCodeEnum.TOKEN_UNSUPPORTED).build();
            } catch (MalformedJwtException e) {
                log.warn("Request to parse malformed JWT : {} failed : {}", token, e.getMessage());
                throw PikaException.construct(AuthErrorCodeEnum.TOKEN_MALFORMED).build();
            } catch (InvalidKeyException e) {
                throw PikaException.construct(AuthErrorCodeEnum.TOKEN_INVALID_KEY).build();
            } catch (IllegalArgumentException e) {
                log.warn("Request to parse empty or null JWT : {} failed : {}", token, e.getMessage());
                throw PikaException.construct(AuthErrorCodeEnum.TOKEN_EMPTY).build();
            }

        }
        return null;
    }

    /**
     * 获取当前用户权限列表
     *
     * @param userId 用户id
     * @return List<PikaGrantedAuthority>
     */
    private List<PikaGrantedAuthority> getUserAuthorities(Long userId) {
        List<PikaGrantedAuthority> authorities = new ArrayList<>();
        String userPermissionListRedis = redisUtils.get(SecurityConstants.USER_PERMISSIONS_REDIS_KEY + userId);
        List<Permission> permissionList;
        if (StringUtils.isNotBlank(userPermissionListRedis)) {
            permissionList = JSON.parseArray(userPermissionListRedis, Permission.class);
        } else {
            Result<List<Permission>> permissionResult = permissionService.queryPermissionByUserId(userId);
            permissionList = permissionResult.getData();
            redisUtils.setEx(SecurityConstants.USER_PERMISSIONS_REDIS_KEY + userId, JSON.toJSONString(permissionList), 480, TimeUnit.MINUTES);
        }
        for (Permission permission : permissionList) {
            authorities.add(new PikaGrantedAuthority(permission.getCode(), permission.getMethod()));
        }
        return authorities;
    }

}
