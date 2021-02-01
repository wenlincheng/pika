package com.wenlincheng.pika.message.model;

import lombok.Data;

/**
 * 邮件服务器
 *
 * @author : Pikaman
 * @version : 1.0.0
 * @date : 2021/1/24 3:38 下午
 */
@Data
public class EmailSenderSource {

    /**
     * 名称
     */
    private String name;
    
    /**
     * 用户
     */
    private String smtpUser;
    
    /**
     * 密码
     */
    private String smtpPassword;

    /**
     * 连接加密方案
     */
    private String securityType;

    /**
     * HOST
     */
    private String smtpHost;

    /**
     * 端口
     */
    private Integer smtpPort;

    /**
     * 优先级 值越小优先级越大
     */
    private Integer sequence;

}
