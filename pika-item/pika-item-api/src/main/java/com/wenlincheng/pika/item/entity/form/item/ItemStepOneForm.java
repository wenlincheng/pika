package com.wenlincheng.pika.item.entity.form.item;

import com.wenlincheng.pika.common.core.base.form.BaseForm;
import com.wenlincheng.pika.item.entity.po.Item;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品表单Step1
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "商品表单Step1", description = "商品表单Step1")
public class ItemStepOneForm extends BaseForm<Item> {

    // 基本信息
    @ApiModelProperty(value = "商品名称")
    private String name;
    @ApiModelProperty(value = "商品主图")
    private String mainPicUrl;

    // 规格属性

    // SKU


}
