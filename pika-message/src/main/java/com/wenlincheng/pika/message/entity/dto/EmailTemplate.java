package com.wenlincheng.pika.message.entity.dto;

import com.wenlincheng.pika.message.config.EmailSenderConfig;
import lombok.Data;

/**
 * 邮件模板
 *
 * @author : Pikaman
 * @version : 1.0.0
 * @date : 2021/1/24 3:29 下午
 */
@Data
public class EmailTemplate {

    /**
     * 模板名称
     */
    private String name;
    
    /**
     * 邮件标题
     */
    private String tile;

    /**
     * 内容
     */
    private String body;

    /**
     * 指定的邮箱服务器
     */
    private EmailSenderConfig emailSource;
}
