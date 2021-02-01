package com.wenlincheng.pika.gateway.app.filter;

import com.alibaba.fastjson.JSON;
import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.gateway.app.client.api.AuthService;
import com.wenlincheng.pika.gateway.app.client.dto.AuthUser;
import com.wenlincheng.pika.gateway.app.exception.GatewayErrorCodeEnum;
import com.wenlincheng.pika.gateway.app.config.IgnoredUriProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static com.wenlincheng.pika.common.core.constant.SecurityConstants.*;

/**
 * 网关权限校验过滤器
 *
 * @author  wenlincheng
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Component
public class GatewayAccessFilter implements GlobalFilter {

    @Autowired
    private AuthService authService;

    @Autowired
    private IgnoredUriProperties ignoredUriProperties;

    /**
     * 1、判断uri是否需要网关签权，若不需要则放行，否则校验token
     * 2、判断请求是否携带token信息，若有进行鉴权，否则返回未授权
     * 3、判断用户是否有权限，若有权限则放行，否则返回未授权
     * @param exchange 服务网络交换器
     * @param chain    网关过滤链
     * @return reactor.core.publisher.Mono<java.lang.Void>
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String method = request.getMethodValue();
        String uri = request.getPath().value();
        log.debug("网关请求 uri:{},method:{},headers:{}", uri, method, request.getHeaders());
        // 1、判断uri是否需要网关签权，若不需要则放行
        if (ignoreAuthentication(uri)) {
            return chain.filter(exchange);
        }
        // 2、请求未携带token信息，返回未授权
        if (StringUtils.isBlank(token) || !token.startsWith(JWT_TOKEN_PREFIX)) {
            return unauthorized(exchange);
        }
        // 3、调用鉴权服务判断用户是否有权限，若有权限则放行
        AuthUser authUser = authDecide(token, uri, method);
        if (Objects.nonNull(authUser)) {
            Consumer<HttpHeaders> httpHeaders = httpHeader -> {
                httpHeader.set(X_CLIENT_TOKEN, "添加服务间的认证");
                httpHeader.set(X_CLIENT_TOKEN_USER, JSON.toJSONString(authUser));
            };
            ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
            return chain.filter(exchange.mutate().request(serverHttpRequest).build());
        }
        return unauthorized(exchange);
    }

    /**
     * 网关拒绝，返回401
     *
     * @param serverWebExchange 服务网络交换器
     * @return reactor.core.publisher.Mono<java.lang.Void>
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory()
                .wrap(JSON.toJSONString(Result.fail(GatewayErrorCodeEnum.GATEWAY_UNAUTHORIZED))
                .getBytes(StandardCharsets.UTF_8));
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    /**
     * 判断是否进行鉴权
     *
     * @param uri 链接
     * @return boolean
     */
    public boolean ignoreAuthentication(String uri) {
        return Stream.of(ignoredUriProperties.getUris().toArray()).anyMatch(ignoreUri -> new AntPathMatcher().match((String) ignoreUri, uri));
    }

    /**
     * 鉴权
     *
     * @param token 令牌
     * @param uri   请求路径
     * @param method 请求方法
     * @return AuthUser 用户信息
     */
    public AuthUser authDecide(String token, String uri, String method) {
        Result<AuthUser> authResult = authService.authDecide(token, uri, method);
        return authResult.getData();
    }

}
