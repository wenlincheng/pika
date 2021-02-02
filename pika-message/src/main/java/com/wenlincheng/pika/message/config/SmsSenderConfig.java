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

    private String regionId;

    private String accessKeyId;

    private String secret;

    private String version;

    private String action;

    private String domain;

    private String method;
}
