package com.wenlincheng.pika.common.data.generator;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 代码生成配置
 *
 * @author Pikaman
 * @version 1.0.0
 * @date : 2021/1/1 10:10 上午上午
 */
@Data
@Component
@ConfigurationProperties(prefix = "gen")
public class GeneratorProperties {
    /**
     * 服务名称
     */
    private String serverName;

    /**
     * 数据库表
     */
    private String[] tableNames;

    /**
     * 作者
     */
    private String author;

    /**
     * 自定义包路径
     */
    private String packagePath;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 数据源
     */
    private Database database = new Database();

    @Data
    @NoArgsConstructor
    public static class Database {
        /**
         * 链接
         */
        private String url;

        /**
         * 用户名
         */
        private String username;

        /**
         * 密码
         */
        private String password;

    }
}
