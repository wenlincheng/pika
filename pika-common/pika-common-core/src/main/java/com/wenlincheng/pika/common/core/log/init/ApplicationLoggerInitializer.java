package com.wenlincheng.pika.common.core.log.init;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 通过环境变量的形式注入 logging.file 自动维护 Spring Boot Admin Logger Viewer
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class ApplicationLoggerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();

        String appName = environment.getProperty("spring.application.name");

        String logBase = environment.getProperty("LOGGING_PATH", "logs");
        // 直接加载日志
        System.setProperty("logging.file.name", String.format("%s/%s/debug.log", logBase, appName));

    }
}
