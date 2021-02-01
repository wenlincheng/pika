package com.wenlincheng.pika.auth.manager;

import com.wenlincheng.pika.auth.security.PikaGrantedAuthority;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Objects;

import static com.wenlincheng.pika.common.core.constant.SecurityConstants.LOGIN_URL;

/**
 * 权限最终判断器
 * 判断用户拥有的角色是否有资源访问权限
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Service
public class PikaAccessDecisionManager implements AccessDecisionManager {

    /**
     * 判定当前请求路径是否拥有访问权限
     *
     * @param authentication
     * @param object
     * @param configAttributes
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     * @return void
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // 没有权限时，直接跳出方法
        if(Objects.isNull(configAttributes)){
            return;
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        String url, method;
        AntPathRequestMatcher matcher;
        // 鉴权
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority instanceof PikaGrantedAuthority) {
                PikaGrantedAuthority grantedAuthority = (PikaGrantedAuthority) authority;
                url = grantedAuthority.getPerms();
                method = grantedAuthority.getMethod();
                matcher = new AntPathRequestMatcher(url);
                if (matcher.matches(request)) {
                    // 当权限表权限的 method 为 ALL 时表示拥有此路径的所有请求方式权利
                    if (method.equals(request.getMethod()) || "ALL".equals(method)) {
                        return;
                    }
                }
            } else if (authority.getAuthority().equals("ROLE_ANONYMOUS")){
                // 未登录只允许访问 login 页面
                matcher = new AntPathRequestMatcher(LOGIN_URL);
                if (matcher.matches(request)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("您没有访问权限");
    }


    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
