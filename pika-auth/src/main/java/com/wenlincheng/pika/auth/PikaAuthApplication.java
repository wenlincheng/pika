package com.wenlincheng.pika.auth;

import com.wenlincheng.pika.auth.config.AuthIgnoredUrisProperties;
import com.wenlincheng.pika.auth.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.util.StopWatch;

/**
 * 认证服务
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.wenlincheng.pika"})
@EnableWebSecurity
public class PikaAuthApplication {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("pika-auth 启动中...");
        ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(PikaAuthApplication.class)
                .web(WebApplicationType.SERVLET)
                .listeners(new ApplicationPidFileWriter("pid/pika.auth.pid"))
                .run(args);
        // 将Context设置到SpringUtil中
        SpringUtil.setApplicationContext(configurableApplicationContext);
        stopWatch.stop();
        log.info("pika-auth 启动完成! 启动耗时 {} s", stopWatch.getTotalTimeSeconds());
        System.out.println("启动耗时 " + stopWatch.getTotalTimeSeconds() + "s");
    }


}
