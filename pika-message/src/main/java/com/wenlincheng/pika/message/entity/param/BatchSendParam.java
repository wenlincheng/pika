package com.wenlincheng.pika.message.entity.param;

import lombok.Data;

import java.util.List;

/**
 * 批量发送参数
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class BatchSendParam {
    /**
     * 模板Id
     */
    private String templateId;

    /**
     * 内容列表
     */
    private List<MessageTemplateParam> messageTemplateParams;

}
