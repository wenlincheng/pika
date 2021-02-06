package com.wenlincheng.pika.item.entity.form.item;

import com.wenlincheng.pika.common.core.base.query.BasePageQuery;
import com.wenlincheng.pika.item.entity.po.Item;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品分页查询参数
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ItemPageQuery extends BasePageQuery<Item> {

    @ApiModelProperty(value = "商品名称")
    private String name;
    @ApiModelProperty(value = "商品编码")
    private String code;
    @ApiModelProperty(value = "商品状态")
    private String saleStatus;
}
