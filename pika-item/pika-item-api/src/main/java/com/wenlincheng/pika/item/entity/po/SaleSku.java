package com.wenlincheng.pika.item.entity.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wenlincheng.pika.common.core.base.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 销售SKU
 * </p>
 *
 * @author Pikaman
 * @since 2021-01-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sale_sku")
public class SaleSku extends BaseModel<SaleSku> {

    private static final long serialVersionUID = 1L;

    /**
     * 卖家ID
     */
    @TableField("seller_id")
    private Long sellerId;

    /**
     * 商品ID
     */
    @TableField("item_id")
    private Long itemId;

    /**
     * 产品 SKU编码
     */
    @TableField("inv_sku_code")
    private String invSkuCode;

    /**
     * 销售sku名称
     */
    @TableField("name")
    private String name;

    /**
     * 销售sku简称
     */
    @TableField("simple_name")
    private String simpleName;

    /**
     * 编码
     */
    @TableField("code")
    private String code;

    /**
     * 条形码
     */
    @TableField("bar_code")
    private String barCode;

    /**
     * 内部简码
     */
    @TableField("short_code")
    private String shortCode;

    /**
     * sku图片url
     */
    @TableField("sku_pic_url")
    private String skuPicUrl;

    /**
     * 规格型号
     */
    @TableField("spec")
    private String spec;

    /**
     * 销售属性值
     */
    @TableField("sale_attr_vals")
    private String saleAttrVals;

    /**
     * 2XL|蓝色, 用于页面上选择属性后定位一个具体的SKU
     */
    @TableField("attr_label")
    private String attrLabel;

    /**
     * 销售单价
     */
    @TableField("sale_unit_price")
    private BigDecimal saleUnitPrice;

    /**
     * 指导价、吊牌价
     */
    @TableField("market_unit_price")
    private BigDecimal marketUnitPrice;

    /**
     * 成本价
     */
    @TableField("cost_unit_price")
    private BigDecimal costUnitPrice;

    /**
     * 币种编码
     */
    @TableField("currency_code")
    private String currencyCode;

    /**
     * 已售库存
     */
    @TableField("has_sold_num")
    private Integer hasSoldNum;

    /**
     * 已占库存
     */
    @TableField("has_blocked_num")
    private Integer hasBlockedNum;

    /**
     * 外部编码
     */
    @TableField("out_id")
    private String outId;

    /**
     * 重量
     */
    @TableField("weight")
    private BigDecimal weight;

    /**
     * 销售单位ID
     */
    @TableField("sale_unit_id")
    private Long saleUnitId;

    /**
     * 是否一口价商品SKU
     */
    @TableField("is_fixed_sku")
    private Integer isFixedSku;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
