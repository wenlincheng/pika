package com.wenlincheng.pika.upms.entity.vo.user;

import com.wenlincheng.pika.common.core.base.vo.BaseVo;
import com.wenlincheng.pika.upms.entity.po.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * 用户详情返回
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "UserDetailVO", description = "用户详情返回对象")
public class UserDetailVO extends BaseVo<User> {

    private static final long serialVersionUID = 8673896053467072184L;

    public UserDetailVO(User user) {
        super(user);
    }

    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "用户姓名")
    private String name;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "性别 0 未知 1 男 2 女")
    private Integer gender;
    @ApiModelProperty(value = "城市")
    private String city;
    @ApiModelProperty(value = "签名")
    private String signature;
    @ApiModelProperty(value = "用户简介")
    private String description;
    @ApiModelProperty(value = "用户类型 1 普通用户 2 管理员 3 官方号 4 马甲号")
    private Integer type;
    @ApiModelProperty(value = "用户角色")
    private Set<Long> roleIds;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "是否有效用户 0 否 1 是")
    private Integer enabled;
    @ApiModelProperty(value = "账号是否未过期 0 否 1 是")
    private Integer accountNonExpired;
    @ApiModelProperty(value = "密码是否未过期 0 否 1 是")
    private Integer credentialsNonExpired;
    @ApiModelProperty(value = "是否未锁定 0 否 1 是")
    private Integer accountNonLocked;
}
