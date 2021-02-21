package com.wenlincheng.pika.common.core.context;

import lombok.Data;

/**
 * 用户信息
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class PikaUser {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 商家id
     */
    private String merchantId;
}
