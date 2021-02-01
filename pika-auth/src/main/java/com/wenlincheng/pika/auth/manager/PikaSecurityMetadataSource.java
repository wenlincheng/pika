package com.wenlincheng.pika.auth.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wenlincheng.pika.auth.client.api.PermissionService;
import com.wenlincheng.pika.auth.client.dto.Permission;
import com.wenlincheng.pika.common.core.redis.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.wenlincheng.pika.common.core.constant.SecurityConstants.PERMISSIONS_REDIS_KEY;

/**
 * 权限资源管理器
 * 为权限决断器提供支持
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Component
public class PikaSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 此方法判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 MyAccessDecisionManager的decide 方法，
     * 如果不在权限表中则放行。
     *
     * @param  o
     * @throws IllegalArgumentException 非法的参数
     * @return java.util.Collection<org.springframework.security.access.ConfigAttribute>
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        Collection<ConfigAttribute> configAttributes = new ArrayList<>();
        // 从redis中获取角色与权限数据
        String redisConfigAttributesPermission = redisUtils.get(PERMISSIONS_REDIS_KEY);
        if (StringUtils.isBlank(redisConfigAttributesPermission)) {
            // 查询所有可用的权限列表
            List<Permission> permissionList = permissionService.queryAllPermissions().getData();
            for (Permission permission : permissionList) {
                ConfigAttribute configAttribute = new SecurityConfig(permission.getPermission());
                configAttributes.add(configAttribute);
            }
            // 将权限存入redis
            redisUtils.setEx(PERMISSIONS_REDIS_KEY, JSON.toJSONString(permissionList), 480, TimeUnit.MINUTES);

        } else {
            JSONArray array = JSONObject.parseArray(redisConfigAttributesPermission);

            for (int i = 0; i < array.size(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ConfigAttribute configAttribute = new SecurityConfig(jsonObject.getString("url") + " " + jsonObject.getString("method"));
                configAttributes.add(configAttribute);
            }
        }

        // Object中包含用户请求request
        String url = ((FilterInvocation) o).getRequestUrl();
        String method = ((FilterInvocation) o).getRequest().getMethod();
        // REST风格  根据方法判断权限
        String authPath = url + " " + method;
        PathMatcher pathMatcher = new AntPathMatcher();
        for (ConfigAttribute configAttribute : configAttributes) {
            if (StringUtils.isNotBlank(configAttribute.getAttribute()) && pathMatcher.match(configAttribute.getAttribute(), authPath)) {
                return configAttributes;
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
