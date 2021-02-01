package com.wenlincheng.pika.auth.security;

/**
 * TODO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public enum ScopesEnum {
    REFRESH_TOKEN;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
