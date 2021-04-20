package com.wenlincheng.pika.upms.entity.form.dict;

import com.wenlincheng.pika.common.core.base.form.BaseForm;
import com.wenlincheng.pika.upms.entity.po.DictType;
import com.wenlincheng.pika.upms.entity.po.DictValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典值表单
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/4/18 4:38 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "字典值表单", description = "字典值表单")
public class DictValueForm extends BaseForm<DictValue> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典类型id")
    private Long dictTypeId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "值")
    private String value;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}
