package com.wenlincheng.pika.common.core.enums;


import lombok.Getter;

/**
 * 支付类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum PayTypeEnum {

    UNKNOWN(0, "--"),
	ALIPAY(1, "支付宝"),
	WECHAT(2, "微信支付"),
	FREE(3, "免费"),
	ALIPAY_RENT(4, "信用借还"),
	ACCOUNT(5, "账户"),
	ALIPAY_AND_ACCOUNT(6, "支付宝、账户"),
	WECHAT_AND_ACCOUNT(7, "微信、账户"),
	ALIPAY_HB(8, "花呗分期"),
	WECHAT_WAP(9, "微信公众号"),
	WECHAT_MINI(10,"微信小程序");

	private final int value;

	private final String desc;

	PayTypeEnum(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public static PayTypeEnum valueOf(int value) {
		for (PayTypeEnum payTypeEnum : PayTypeEnum.values()) {
			if (payTypeEnum.getValue() == value) {
				return payTypeEnum;
			}
		}
		return UNKNOWN;
	}
}
