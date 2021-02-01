package com.wenlincheng.pika.upms.entity.form.group;

import com.wenlincheng.pika.common.core.base.form.BaseForm;
import com.wenlincheng.pika.upms.entity.po.SysGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户分组表单
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
@ApiModel
public class GroupForm extends BaseForm<SysGroup> {

    @NotBlank(message = "用户组父id不能为空")
    @ApiModelProperty(value = "用户组父id")
    private String parentId;

    @NotBlank(message = "用户组名称不能为空")
    @ApiModelProperty(value = "用户组名称")
    private String name;

    @ApiModelProperty(value = "用户组描述")
    private String description;
}
