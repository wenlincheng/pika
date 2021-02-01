package com.wenlincheng.pika.message.pojo.param;

import lombok.Data;

/**
 * 发送参数
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class SendParam {

    /**
     * 模板Id
     */
    private String templateId;

    /**
     * 消息参数
     */
    private MessageParam messageParam;
}
