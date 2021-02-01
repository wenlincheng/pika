package com.wenlincheng.pika.message;

import com.sankuai.inf.leaf.plugin.annotation.EnableLeafServer;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.util.StopWatch;

@Slf4j
@EnableLeafServer
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.wenlincheng.pika.common.core.log.feign"})
@MapperScan("com.wenlincheng.pika.message.mapper")
@SpringBootApplication
public class PikaMessageApplication {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("pika-message 启动中...");
        new SpringApplicationBuilder(PikaMessageApplication.class)
                .web(WebApplicationType.SERVLET)
                .listeners(new ApplicationPidFileWriter("pid/pika.message.pid"))
                .run(args);
        stopWatch.stop();
        log.info("pika-message 启动完成! 启动耗时 {} s", stopWatch.getTotalTimeSeconds());
        System.out.println("启动耗时 " + stopWatch.getTotalTimeSeconds() + "s");
    }

}
