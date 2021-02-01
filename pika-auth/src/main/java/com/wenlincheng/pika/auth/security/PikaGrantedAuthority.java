package com.wenlincheng.pika.auth.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * 自定义权限标识
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class PikaGrantedAuthority implements GrantedAuthority {
    private static final long serialVersionUID = 4911046300625866871L;
    private String perms;
    private String method;

    public PikaGrantedAuthority(String perms, String method) {
        this.perms = perms;
        this.method = method;
    }

    @Override
    public String getAuthority() {
        return this.perms + " " + this.method;
    }
}
