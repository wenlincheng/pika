package com.wenlincheng.pika.item.entity.form.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品属性
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class AttrValue {

    @ApiModelProperty(name = "属性名称")
    private String name;

    @ApiModelProperty(name = "属性值")
    private String val;

    @ApiModelProperty(name = "属性值图片url")
    private String picUrl;
}
