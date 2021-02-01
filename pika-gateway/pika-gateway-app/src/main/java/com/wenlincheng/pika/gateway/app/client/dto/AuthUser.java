package com.wenlincheng.pika.gateway.app.client.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class AuthUser implements Serializable {
    private static final long serialVersionUID = 971761826336901898L;
    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

}
