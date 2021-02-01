package com.wenlincheng.pika.auth.client.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wenlincheng
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -7908276502172033137L;
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
    @ApiModelProperty(value = "权限列表")
    private List<Permission> permissionList;
}