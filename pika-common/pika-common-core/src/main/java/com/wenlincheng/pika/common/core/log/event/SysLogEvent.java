package com.wenlincheng.pika.common.core.log.event;

import com.wenlincheng.pika.common.core.log.feign.dto.SysLog;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


/**
 * 系统日志事件
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
@Setter
public class SysLogEvent extends ApplicationEvent {

	private static final long serialVersionUID = 5755117037752428031L;

	public SysLogEvent(SysLog source) {
		super(source);
	}

}
