package com.wenlincheng.pika.upms.entity.form.menu;

import com.wenlincheng.pika.common.core.base.form.BaseForm;
import com.wenlincheng.pika.upms.entity.po.SysMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * TODO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
@ApiModel
public class MenuForm extends BaseForm<SysMenu> {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "父级id")
    private Long parentId;
    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "类型")
    private Integer type;
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "URI")
    private String uri;
    @ApiModelProperty(value = "请求方法")
    private String method;
    @ApiModelProperty(value = "授权标识")
    private String code;
    @ApiModelProperty(value = "排序")
    private Integer sequence;
}
