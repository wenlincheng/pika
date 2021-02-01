package com.wenlincheng.pika.gateway.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 放行配置
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Configuration
@ConfigurationProperties(prefix = "gateway.ignored")
public class IgnoredUriProperties {

    private List<String> uris = new ArrayList<>();

    public List<String> getUris() {
        return uris;
    }

    public void setUris(List<String> uris) {
        this.uris = uris;
    }
}
