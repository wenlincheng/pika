package com.wenlincheng.pika.gateway.web.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 用户注册事件的监听器
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Component
public class UserRegisterListener implements ApplicationListener<UserRegisterEvent> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        logger.info("[onApplicationEvent][监听到用户({}) 注册]", event.getUsername());
    }
}
