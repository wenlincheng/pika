package com.wenlincheng.pika.auth.client.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * URI
     */
    private String uri;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 授权标识
     */
    private String code;

    /**
     * 返回权限编码
     *
     * @return java.lang.String
     */
    public String getPermission() {
        return this.getCode() + " " + this.getMethod();
    }
}
