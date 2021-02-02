package com.wenlincheng.pika.message.sender.sms;

/**
 * 短信发送
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/2/2 10:25 下午
 */
public interface SmsSender {

    /**
     * 单条发送
     *
     * @param sendParam 发送参数
     */
    void sendSms(SmsSendParam sendParam);

    /**
     * 批量发送
     *
     * @param batchSendParam 批量发送参数
     */
    void sendBatchSms(SmsBatchSendParam batchSendParam);
}
