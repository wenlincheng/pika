package com.wenlincheng.pika.message.entity.param;

import lombok.Data;

import java.util.Map;

/**
 * 消息模板参数
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class MessageTemplateParam {

    /**
     * 接收者 多个，则用逗号分隔开
     */
    private String receiver;

    /**
     * 自定义参数（文案）
     */
    private Map<String, String> params;
}
