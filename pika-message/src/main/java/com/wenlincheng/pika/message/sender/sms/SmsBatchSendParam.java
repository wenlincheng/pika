package com.wenlincheng.pika.message.sender.sms;

import lombok.Data;

/**
 * 短信批量发送参数
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/2/2 10:31 下午
 */
@Data
public class SmsBatchSendParam {

    /**
     * 手机号码 JSON数组 必选
     * ["1590***0000","13500***000"]
     */
    private String phoneNumberJson;

    /**
     * 短信签名名称 必选
     * ["阿里云","阿里巴巴"]
     * 短信签名的个数必须与手机号码的个数相同、内容一一对应
     */
    private String signNameJson;

    /**
     * 短信模板ID 必选
     */
    private String templateCode;

    /**
     * 短信模板变量对应的实际值，JSON数组
     *
     * 关于模板中无参数的情况下该字段传参格式示例：[{},{}]（{}个数和群发的号码个数对应）
     * [{"name":"TemplateParamJson"},{"name":"TemplateParamJson"}]
     */
    private String templateParamJson;

    /**
     * 上行短信扩展码，JSON数组
     */
    private String smsUpExtendCode;

}
