package com.wenlincheng.pika.upms.entity.vo.user;


import com.wenlincheng.pika.common.core.base.vo.BaseVo;
import com.wenlincheng.pika.upms.entity.po.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * 用户列表
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserListVO extends BaseVo<User> {

    public UserListVO(User user) {
        super(user);
    }
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "性别")
    private Integer gender;
    @ApiModelProperty(value = "类型")
    private Integer type;
    @ApiModelProperty(value = "城市")
    private String city;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "角色列表")
    private Set<String> roleNames;
}
