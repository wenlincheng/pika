package com.wenlincheng.pika.upms.entity.query.dict;

import com.wenlincheng.pika.common.core.base.query.BasePageQuery;
import com.wenlincheng.pika.upms.entity.po.DictType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典类型分页查询参数
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/4/18 4:36 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "字典类型分页查询参数", description = "字典类型分页查询参数")
public class DictTypePageQuery extends BasePageQuery<DictType> {

    @ApiModelProperty(value = "字典名称")
    private String name;

}
