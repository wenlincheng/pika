package com.wenlincheng.pika.gateway.admin.handler;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.gateway.admin.exception.GatewayErrorCodeEnum;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * Sentinel 限流处理类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/4/18 12:54 下午
 */
public class SentinelFallbackHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }
        if (!BlockException.isBlockException(ex)) {
            return Mono.error(ex);
        }
        DataBuffer buffer = exchange.getResponse().bufferFactory()
                .wrap(JSON.toJSONString(Result.fail(GatewayErrorCodeEnum.GATEWAY_LIMIT))
                        .getBytes(StandardCharsets.UTF_8));
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}
