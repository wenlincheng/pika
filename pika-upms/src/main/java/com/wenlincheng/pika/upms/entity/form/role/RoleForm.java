package com.wenlincheng.pika.upms.entity.form.role;

import com.wenlincheng.pika.common.core.base.form.BaseForm;
import com.wenlincheng.pika.upms.entity.po.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 角色form
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
@ApiModel
public class RoleForm extends BaseForm<SysRole> {

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "编码")
    private String code;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "菜单id列表")
    private List<Long> menuIdList;
}
