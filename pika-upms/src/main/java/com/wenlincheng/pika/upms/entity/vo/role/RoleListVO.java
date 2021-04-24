package com.wenlincheng.pika.upms.entity.vo.role;

import com.wenlincheng.pika.common.core.base.vo.BaseVo;
import com.wenlincheng.pika.upms.entity.po.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色列表VO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "RoleListVO", description = "角色列表VO")
public class RoleListVO extends BaseVo<Role> {
    private static final long serialVersionUID = 59345947510627473L;

    public RoleListVO(Role role) {
        super(role);
    }

    @ApiModelProperty(value = "角色名")
    private String name;
    @ApiModelProperty(value = "角色代码")
    private String code;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "关联用户数")
    private Integer relateUserCount;
    @ApiModelProperty(value = "角色描述")
    private String description;
}
