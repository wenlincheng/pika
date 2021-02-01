package com.wenlincheng.pika.item.entity.form.item;

import com.wenlincheng.pika.item.enums.AttrTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品属性集合
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class AttrValues {

    @ApiModelProperty(name = "属性类型")
    private AttrTypeEnum attrValType;

    @ApiModelProperty(name = "属性名称")
    private String name;

    @ApiModelProperty(name = "属性值")
    private List<Value> vals;

    @ApiModelProperty(name = "属性值组合集json")
    private String valsJson;
}
