package com.wenlincheng.pika.message.sender.sms;

import lombok.Data;

/**
 * 短信发送参数
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/2/2 10:31 下午
 */
@Data
public class SmsSendParam {

    private String regionId;

    /**
     * 手机号码 必选
     */
    private String phoneNumbers;

    /**
     * 短信签名名称 必选
     */
    private String signName;

    /**
     * 短信模板id 必选
     */
    private String templateCode;

    /**
     * 短信模板参数 JSON格式
     * {"code":"1111"}
     */
    private String templateParam;

    /**
     * 上行短信扩展码
     */
    private String smsUpExtendCode;

    /**
     * 外部流水扩展字段
     */
    private String outId;

}
