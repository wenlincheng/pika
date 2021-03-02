package com.wenlincheng.pika.upms.entity.form.organization;

import com.wenlincheng.pika.common.core.base.form.BaseForm;
import com.wenlincheng.pika.upms.entity.po.Organization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 组织表单
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
@ApiModel
public class OrganizationForm extends BaseForm<Organization> {

    private static final long serialVersionUID = 6876853823765194620L;

    @NotBlank(message = "组织父id不能为空")
    @ApiModelProperty(value = "组织父id")
    private String parentId;

    @NotBlank(message = "组织名称不能为空")
    @ApiModelProperty(value = "组织名称")
    private String name;

    @ApiModelProperty(value = "组织描述")
    private String description;
}
