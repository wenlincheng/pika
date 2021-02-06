package com.wenlincheng.pika.item;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.sankuai.inf.leaf.plugin.annotation.EnableLeafServer;
import com.wenlincheng.pika.item.message.consume.MySink;
import com.wenlincheng.pika.item.message.produce.MySource;
import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.StopWatch;

/**
 * 商品中心
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@EnableAutoDataSourceProxy
@EnableBinding({ MySource.class, MySink.class})
@EnableAsync
@EnableLeafServer
@EnableMethodCache(basePackages = "com.wenlincheng.pika")
@EnableCreateCacheAnnotation
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.wenlincheng.pika.common.core.log.feign"})
@MapperScan("com.wenlincheng.pika.item.mapper")
@SpringBootApplication
public class PikaItemApplication {

	public static void main(String[] args) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		log.info("pika-item 启动中...");
		new SpringApplicationBuilder(PikaItemApplication.class)
				.web(WebApplicationType.SERVLET)
				.listeners(new ApplicationPidFileWriter("pid/pika.item.pid"))
				.run(args);
		stopWatch.stop();
		log.info("pika-item 启动完成! 启动耗时 {} s", stopWatch.getTotalTimeSeconds());
		System.out.println("启动耗时 " + stopWatch.getTotalTimeSeconds() + "s");
	}

}
