package com.wenlincheng.pika.common.core.log;

import com.wenlincheng.pika.common.core.log.aspect.SysLogAspect;
import com.wenlincheng.pika.common.core.log.event.SysLogListener;
import com.wenlincheng.pika.common.core.log.feign.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 日志自动配置
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@EnableAsync
@RequiredArgsConstructor
@ConditionalOnWebApplication
@Configuration(proxyBeanMethods = false)
public class LogAutoConfiguration {

	@Bean
	public SysLogListener sysLogListener(SysLogService sysLogService) {
		return new SysLogListener(sysLogService);
	}

	@Bean
	public SysLogAspect sysLogAspect() {
		return new SysLogAspect();
	}

}
