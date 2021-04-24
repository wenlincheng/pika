package com.wenlincheng.pika.upms.entity.form.dict;

import com.wenlincheng.pika.common.core.base.form.BaseForm;
import com.wenlincheng.pika.upms.entity.po.DictType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 字典类型表单
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/4/18 4:38 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "字典类型表单", description = "字典类型表单")
public class DictTypeForm extends BaseForm<DictType> {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "名称")
    private String name;

    @NotBlank(message = "字典类型编码不能为空")
    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "描述")
    private String description;

    @NotEmpty(message = "字典值列表不能为空")
    @ApiModelProperty(value = "字典值列表")
    private List<DictValueForm> dictValues;
}
