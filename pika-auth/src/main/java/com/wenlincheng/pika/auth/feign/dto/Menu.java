package com.wenlincheng.pika.auth.feign.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜单
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = 4259893100600326000L;
    private Long id;

    /**
     * 父菜单id
     */
    private Long parentId;

    /**
     * 菜单类型
     */
    private Integer type;

    /**
     * 菜单路径
     */
    private String url;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单名称
     */
    private String name;
}
