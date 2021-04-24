package com.wenlincheng.pika.upms.entity.query.role;

import com.wenlincheng.pika.common.core.base.query.BasePageQuery;
import com.wenlincheng.pika.upms.entity.po.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色分页查询参数
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "RoleQueryForm", description = "角色分页查询参数")
public class RolePageQuery extends BasePageQuery<Role> {

    @ApiModelProperty(value = "角色名")
    private String name;
    @ApiModelProperty(value = "角色代码")
    private String code;
    @ApiModelProperty(value = "角色状态")
    private String status;
}
