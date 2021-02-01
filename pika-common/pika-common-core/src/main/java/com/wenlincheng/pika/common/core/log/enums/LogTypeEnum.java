package com.wenlincheng.pika.common.core.log.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 系统日志类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum LogTypeEnum implements IEnum<Integer> {

	NORMAL(1, "正常日志", "正常日志"),
	ERROR(2, "错误日志", "错误日志");

	private Integer value;
	private String name;
	private String help;

	LogTypeEnum(Integer value, String name, String help) {
		this.value = value;
		this.name = name;
		this.help = help;
	}

}
