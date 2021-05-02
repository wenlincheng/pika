package com.wenlincheng.pika.item.entity.vo.item;

import com.wenlincheng.pika.common.core.base.vo.BasePageVo;
import com.wenlincheng.pika.item.entity.po.Item;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商品列表
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ItemPageVO extends BasePageVo<Item> {
    private static final long serialVersionUID = -2934584182948690165L;

    public ItemPageVO(Item item) {
        super(item);
    }
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "编码")
    private String code;
    @ApiModelProperty(value = "主图")
    private String mainPicUrl;
    @ApiModelProperty(value = "销售状态")
    private String saleStatus;
    @ApiModelProperty(value = "最低划线价")
    private BigDecimal minLinePrice;
    @ApiModelProperty(value = "最大划线价")
    private BigDecimal maxLinePrice;
    @ApiModelProperty(value = "最小单价")
    private BigDecimal minUnitPrice;
    @ApiModelProperty(value = "最大单价")
    private BigDecimal maxUnitPrice;
}
