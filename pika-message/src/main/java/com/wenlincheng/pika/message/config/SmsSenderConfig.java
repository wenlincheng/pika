package com.wenlincheng.pika.message.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 短信服务器配置
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/2/2 10:00 下午
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "sms")
public class SmsSenderConfig {

    /**
     * 区域id
     */
    private String regionId;

    /**
     * AccessKey
     */
    private String accessKey;

    /**
     * AccessSecret
     */
    private String accessSecret;

    /**
     * 版本
     */
    private String version;

    /**
     * 短信服务器域名
     */
    private String domain;

}
