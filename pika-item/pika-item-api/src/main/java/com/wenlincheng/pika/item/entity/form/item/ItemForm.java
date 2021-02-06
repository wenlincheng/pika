package com.wenlincheng.pika.item.entity.form.item;

import com.wenlincheng.pika.common.core.base.form.BaseForm;
import com.wenlincheng.pika.item.entity.po.Item;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品form
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ItemForm extends BaseForm<Item> {

    @ApiModelProperty(name = "商品名")
    private String name;

    @ApiModelProperty(name = "店铺id")
    private Integer shopId;

    @ApiModelProperty(name = "商品编码")
    private String itemNo;

}
