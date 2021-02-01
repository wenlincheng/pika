package com.wenlincheng.pika.gateway.web.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * TODO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class UserRegisterEvent extends RemoteApplicationEvent {

    /**
     * 用户名
     */
    private String username;

    public UserRegisterEvent() {
    }

    public UserRegisterEvent(Object source, String originService, String destinationService, String username) {
        super(source, originService);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
