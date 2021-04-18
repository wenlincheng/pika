package com.wenlincheng.pika.upms.entity.vo.dict;

import com.wenlincheng.pika.common.core.base.vo.BaseVo;
import com.wenlincheng.pika.upms.entity.po.DictType;
import com.wenlincheng.pika.upms.entity.po.DictValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典值VO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/4/18 4:42 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "字典值VO", description = "字典值VO")
public class DictValueVO extends BaseVo<DictValue> {

    private static final long serialVersionUID = -5855316234597065800L;

    public DictValueVO() {

    }

    public DictValueVO(DictValue dictValue) {
        super(dictValue);
    }

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "值")
    private String value;

}
