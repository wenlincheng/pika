package com.wenlincheng.pika.gateway.admin;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.util.StopWatch;

/**
 * 管理端网关服务
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.wenlincheng.pika"})
@EnableCircuitBreaker
@EnableMethodCache(basePackages = "com.wenlincheng.pika.gateway.admin")
@EnableCreateCacheAnnotation
@RemoteApplicationEventScan
@SpringBootApplication(scanBasePackages = {"com.alicp.jetcache.autoconfigure","com.wenlincheng.pika.gateway.admin"})
public class PikaAdminGatewayApplication {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("pika-gateway-admin 启动中...");
        new SpringApplicationBuilder(PikaAdminGatewayApplication.class)
                .web(WebApplicationType.REACTIVE)
                .listeners(new ApplicationPidFileWriter("pid/pika-gateway-admin.pid"))
                .run(args);
        stopWatch.stop();
        log.info("pika-gateway-admin 启动完成! 启动耗时 {} s", stopWatch.getTotalTimeSeconds());
        System.out.println("启动耗时 " + stopWatch.getTotalTimeSeconds() + "s");
    }

}
