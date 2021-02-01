package com.wenlincheng.pika.upms.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 性别枚举
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum GenderEnum implements IEnum<String> {

	UNKNOWN("UNKNOWN","未知", ""),
	MALE("MALE","男", "男性"),
	FEMALE("FEMALE","女", "女性");

	private String value;
	private String name;
	private String help;

	GenderEnum(String value, String name, String help) {
		this.value = value;
		this.name = name;
		this.help = help;
	}

	/**
	 * 根据枚举值获取枚举
	 *
	 * @param value 枚举值
	 * @return GenderEnum
	 * @date 2021/1/1 10:10 上午
	 */
	public static GenderEnum value(String value) {
		for (GenderEnum genderEnum : values()) {
			if (genderEnum.getValue().equals(value)) {
				return genderEnum;
			}
		}
		return UNKNOWN;
	}

}
