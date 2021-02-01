package com.wenlincheng.pika.common.core.log.event;


import com.wenlincheng.pika.common.core.log.feign.SysLogService;
import com.wenlincheng.pika.common.core.log.feign.dto.SysLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * 异步监听日志事件
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@RequiredArgsConstructor
public class SysLogListener {

	private final SysLogService sysLogService;

	@Value(value = "${spring.application.name}")
	private String appName;

	@Async
	@Order
	@EventListener(SysLogEvent.class)
	public void saveSysLog(SysLogEvent event) {
		SysLog sysLog = (SysLog) event.getSource();
		sysLog.setServiceId(appName);
		sysLogService.saveLog(sysLog);
	}

}
