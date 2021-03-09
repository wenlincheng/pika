package com.wenlincheng.pika.auth.feign.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 3298646735214425861L;
    private Long id;
    private String code;
    private String name;
    private String description;
    private List<Permission> permissionList;
    private List<Menu> menuList;
    private Date createTime;
    private Date updateTime;
}
