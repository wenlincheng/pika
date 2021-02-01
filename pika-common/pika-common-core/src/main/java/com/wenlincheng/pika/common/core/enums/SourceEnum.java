package com.wenlincheng.pika.common.core.enums;


import lombok.Getter;

/**
 * 数据来源枚举
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum SourceEnum {

	UNKNOWN(0,"未知"),
	IOS(1,"iOS"),
	ANDROID(2,"Android"),
	ALI_MINI(3,"AlipayMini"),
	WECHAT_MINI(4,"WeChatMini"),
	H5(5,"H5");

	private int value;

	private String desc;

	SourceEnum(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public static SourceEnum valueOf(int value) {
		for (SourceEnum sourceEnum : values()) {
			if (sourceEnum.getValue() == value) {
				return sourceEnum;
			}
		}
		return UNKNOWN;
	}
}
