package com.wenlincheng.pika.common.core.log.aspect;


import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wenlincheng.pika.common.core.context.PikaUser;
import com.wenlincheng.pika.common.core.log.annotation.PikaLog;
import com.wenlincheng.pika.common.core.log.event.SysLogEvent;
import com.wenlincheng.pika.common.core.log.feign.dto.SysLog;
import com.wenlincheng.pika.common.core.log.enums.LogTypeEnum;
import com.wenlincheng.pika.common.core.util.SpringContextHolder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.wenlincheng.pika.common.core.constant.SecurityConstants.X_CLIENT_TOKEN_USER;

/**
 * 操作日志切面
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Aspect
@Slf4j
public class SysLogAspect {

	@Around("@annotation(pikaLog)")
	@SneakyThrows
	public Object around(ProceedingJoinPoint point, PikaLog pikaLog) {
		HttpServletRequest request = ((ServletRequestAttributes) Objects
				.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		SysLog sysLog = new SysLog();
		sysLog.setType(LogTypeEnum.NORMAL.getValue());
		sysLog.setClientIp(ServletUtil.getClientIP(request));
		sysLog.setUrl(request.getRequestURL().toString());
		sysLog.setUri(request.getRequestURI());
		sysLog.setParams(request.getQueryString());
		if (!ServletUtil.isGetMethod(request)) {
			sysLog.setParams(JSONObject.toJSONString(point.getArgs()));
		}
		sysLog.setMethod(request.getMethod());
		sysLog.setUserAgent(request.getHeader("user-agent"));
		sysLog.setTitle(pikaLog.value());

		// user
		String user = request.getHeader(X_CLIENT_TOKEN_USER);
		if (StringUtils.isNotBlank(user)) {
			PikaUser pikaUser = JSON.parseObject(user, PikaUser.class);
			sysLog.setUserId(pikaUser.getId());
			sysLog.setUsername(pikaUser.getUsername());
		}

		// 发送异步日志事件
		Long startTime = System.currentTimeMillis();
		Object obj;
		try {
			obj = point.proceed();
		} catch (Exception e) {
			sysLog.setType(LogTypeEnum.ERROR.getValue());
			sysLog.setExceptionMessage(e.getMessage());
			throw e;
		} finally {
			Long endTime = System.currentTimeMillis();
			sysLog.setTime(endTime - startTime);
			SpringContextHolder.publishEvent(new SysLogEvent(sysLog));
		}
		return obj;
	}

}
