package com.wenlincheng.pika.auth.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Jwt配置
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */

@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * 签名加密key
     */
    private String signingKey;

    /**
     * 令牌有效时间 单位：秒
     */
    private long tokenExpiration;

    /**
     * 刷新令牌有效时间 单位：秒
     */
    private Long refreshTokenExpiration;
}
