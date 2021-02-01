package com.wenlincheng.pika.auth.entity;

import com.wenlincheng.pika.auth.client.dto.Permission;
import com.wenlincheng.pika.auth.client.dto.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 包含权限信息的用户
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
@ApiModel(value = "AuthUser", description = "用户信息")
public class AuthUser implements Serializable {
    private static final long serialVersionUID = 1881148047044074349L;

    private Long id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "昵称")
    private String name;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "角色列表")
    private List<Role> roleList;
    @ApiModelProperty(value = "角色id")
    private List<Long> roleIds;
    @ApiModelProperty(value = "权限")
    private List<Permission> permissionList;
}
