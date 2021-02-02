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

    private String regionId;

    private String phoneNumbers;

    private String signName;

    private String templateCode;

    private String templateParam;

    private String smsUpExtendCode;

    private String outId;

}
