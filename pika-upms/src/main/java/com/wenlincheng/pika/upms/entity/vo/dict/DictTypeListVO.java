package com.wenlincheng.pika.upms.entity.vo.dict;

import com.wenlincheng.pika.common.core.base.vo.BaseListVo;
import com.wenlincheng.pika.upms.entity.po.DictType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典类型列表VO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/4/18 4:42 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "字典类型列表VO", description = "字典类型列表VO")
public class DictTypeListVO extends BaseListVo<DictType> {

    private static final long serialVersionUID = -5855316234597065800L;

    public DictTypeListVO(DictType dictType) {
        super(dictType);
    }

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "字典值数量")
    private Integer valueNum;
}
