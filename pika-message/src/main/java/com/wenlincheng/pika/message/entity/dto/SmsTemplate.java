package com.wenlincheng.pika.message.entity.dto;

import lombok.Data;

/**
 * 短信模板
 *
 * @author : Pikaman
 * @version : 1.0.0
 * @date : 2021/1/24 9:11 下午
 */
@Data
public class SmsTemplate {

    /**
     * 模板编号
     */
    private String code;

    /**
     * 短信模板类型
     * @see com.wenlincheng.pika.message.enums.SMSTemplateTypeEnum
     */
    private String type;

    /**
     * 模板内容
     */
    private String content;

    /**
     * 验证码有效时间 单位：秒
     */
    private String time;
}
