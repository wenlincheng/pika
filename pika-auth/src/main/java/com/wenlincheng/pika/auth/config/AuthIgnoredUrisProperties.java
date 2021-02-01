package com.wenlincheng.pika.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ignored")
public class AuthIgnoredUrisProperties {

    /**
     * 放行的路径
     */
    private List<String> uris;

}
