package com.wenlincheng.pika.message.sender.email;

import com.wenlincheng.pika.message.dto.EmailPoster;
import com.wenlincheng.pika.message.model.EmailTemplate;

import java.util.Map;

/**
 * TODO
 *
 * @author : Pikaman
 * @version : 1.0.0
 * @date : 2021/1/24 3:23 下午
 */
public interface EmailSender {
    
    /**
     * 直接发送
     *
     * @param emailPoster 邮件消息
     * @return java.lang.Boolean
     */
    Boolean send(EmailPoster emailPoster);

    /**
     * 模板发送
     *
     * @param emailTemplate 消息模板
     * @param objectData 消息参数
     * @param sendTo  接收 多个逗号分隔
     * @param copyTo  抄送 多个逗号分隔
     * @return java.lang.Boolean
     */
    Boolean sendTemplate(EmailTemplate emailTemplate, Map<String, Object> objectData, String sendTo,  String copyTo);

}
