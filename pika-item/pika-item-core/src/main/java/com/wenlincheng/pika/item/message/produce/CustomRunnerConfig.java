package com.wenlincheng.pika.item.message.produce;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Component
public class CustomRunnerConfig {

    @Bean
    public CustomRunner customRunner() {
        return new CustomRunner("output1");
    }

    @Bean
    public CustomRunner customRunner2() {
        return new CustomRunner("output3");
    }

    @Bean
    public CustomRunnerWithTransactional customRunnerWithTransactional() {
        return new CustomRunnerWithTransactional();
    }
}
