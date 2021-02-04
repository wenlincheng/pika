package com.wenlincheng.pika.message.entity.param;

import lombok.Data;

/**
 * 单条发送参数
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class SingleSendParam {

    /**
     * 模板Id
     */
    private String templateId;

    /**
     * 消息模板参数
     */
    private MessageTemplateParam messageTemplateParam;
}
