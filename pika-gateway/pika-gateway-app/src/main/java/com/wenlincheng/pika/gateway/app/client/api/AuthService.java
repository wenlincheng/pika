package com.wenlincheng.pika.gateway.app.client.api;

import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.gateway.app.client.api.fallback.AuthServiceFallbackFactory;
import com.wenlincheng.pika.gateway.app.client.dto.AuthUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 鉴权API
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@FeignClient(contextId = "authService", value = "pika-auth", fallbackFactory = AuthServiceFallbackFactory.class)
public interface AuthService {

    /**
     * 调用签权服务，判断用户是否有权限
     *
     * @param token     令牌
     * @param uri       请求路径
     * @param method    请求方法
     * @return Result<AuthUser>
     */
    @GetMapping(value = "/auth/token/permit")
    Result<AuthUser> authDecide(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestParam("uri") String uri, @RequestParam("method") String method);

}
