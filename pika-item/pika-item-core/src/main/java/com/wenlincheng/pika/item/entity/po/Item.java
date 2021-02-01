package com.wenlincheng.pika.item.entity.po;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;

import com.wenlincheng.pika.common.core.base.model.BaseModel;
import com.wenlincheng.pika.common.data.annotation.PikaModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("item")
@PikaModel.Code(prefix = "I", size = 12, format = "yyyyMMdd", initial = 10000, step = 1)
public class Item extends BaseModel<Item> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 卖家ID
     */
    @TableField("seller_id")
    private Long sellerId;

    /**
     * 商品名称
     */
    @TableField("name")
    private String name;

    /**
     * 商品简称
     */
    @TableField("simple_name")
    private String simpleName;

    /**
     * 商品来源
     */
    @TableField("source")
    private String source;

    /**
     * 产品编码
     */
    @TableField("product_code")
    private String productCode;

    /**
     * 店铺CODE
     */
    @TableField("shop_code")
    private String shopCode;

    /**
     * 类目CODE
     */
    @TableField("category_code")
    private String categoryCode;

    /**
     * 品牌CODE
     */
    @TableField("brand_code")
    private String brandCode;

    /**
     * 规格型号
     */
    @TableField("spec")
    private String spec;

    /**
     * 销售属性
     */
    @TableField("sale_attr_vals")
    private String saleAttrVals;

    /**
     * 商品其他属性
     */
    @TableField("normal_attr_vals")
    private String normalAttrVals;

    /**
     * 主图url
     */
    @TableField("main_pic_url")
    private String mainPicUrl;

    /**
     * 商品图片集url
     */
    @TableField("pics_url")
    private String picsUrl;

    /**
     * 启用状态(数据状态)
     */
    @TableField("item_status")
    private String itemStatus;

    /**
     * 商品销售状态
     */
    @TableField("sale_status")
    private String saleStatus;

    /**
     * 商品审核状态
     */
    @TableField("audit_status")
    private String auditStatus;

    /**
     * 商品税率
     */
    @TableField("tax_rate")
    private BigDecimal taxRate;

    /**
     * 币种编码
     */
    @TableField("currency_code")
    private String currencyCode;

    /**
     * 最低划线价
     */
    @TableField("min_line_price")
    private BigDecimal minLinePrice;

    /**
     * 最大划线价
     */
    @TableField("max_line_price")
    private BigDecimal maxLinePrice;

    /**
     * 最小单价
     */
    @TableField("min_unit_price")
    private BigDecimal minUnitPrice;

    /**
     * 最大单价
     */
    @TableField("max_unit_price")
    private BigDecimal maxUnitPrice;

    /**
     * 48,件|4,盒|1,箱
     */
    @TableField("unit_convert")
    private String unitConvert;

    /**
     * 库存扣减方式
     */
    @TableField("inventory_reduce_setting")
    private String inventoryReduceSetting;

    /**
     * 提货方式
     */
    @TableField("pickup_type")
    private String pickupType;

    /**
     * 商品描述
     */
    @TableField("description")
    private String description;

    /**
     * 商品详情
     */
    @TableField("detail_html")
    private String detailHtml;

    /**
     * 售后服务
     */
    @TableField("after_sale_services")
    private String afterSaleServices;

    /**
     * 用,分隔
     */
    @TableField("tags")
    private String tags;

    /**
     * 定时发布
     */
    @TableField("is_publish_by_time")
    private Integer isPublishByTime;

    /**
     * 发布时间
     */
    @TableField("publish_time")
    private Date publishTime;

    /**
     * 渠道编码
     */
    @TableField("channel_code")
    private String channelCode;

    /**
     * 外部id
     */
    @TableField("out_id")
    private String outId;

    /**
     * 首次上架时间
     */
    @TableField("first_up_sale_date")
    private Date firstUpSaleDate;

    /**
     * 最近上架时间
     */
    @TableField("last_up_sale_date")
    private Date lastUpSaleDate;

    /**
     * 编码
     */
    @TableField(value = "code", fill = FieldFill.INSERT)
    private String code;

    /**
     * 商品系列ID
     */
    @TableField("series_id")
    private Long seriesId;

    /**
     * 产品ID
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 开票税收编码ID
     */
    @TableField("invoice_tax_code_id")
    private Long invoiceTaxCodeId;

    /**
     * 基础单位ID
     */
    @TableField("base_unit_id")
    private Long baseUnitId;

    /**
     * 库存单位ID
     */
    @TableField("inv_unit_id")
    private Long invUnitId;

    /**
     * 运费模板id
     */
    @TableField("shipping_fee_template_id")
    private Long shippingFeeTemplateId;

    /**
     * SEO
     */
    @TableField("seo_id")
    private Long seoId;

    /**
     * 商家渠道ID
     */
    @TableField("channel_id")
    private Long channelId;

    /**
     * 一口价
     */
    @TableField("fixed_price")
    private BigDecimal fixedPrice;

    /**
     * 是否为周期购商品
     */
    @TableField("is_cycle_buy")
    private Integer isCycleBuy;

    /**
     * 是否单笔订单限购
     */
    @TableField("is_single_order_limit_buy")
    private Integer isSingleOrderLimitBuy;

    /**
     * 单笔订单限购数量
     */
    @TableField("single_order_limit_buy_number")
    private Integer singleOrderLimitBuyNumber;

    /**
     * 商品推荐Id集合
     */
    @TableField("recommend_item_ids")
    private String recommendItemIds;

    /**
     * 是否赠品
     */
    @TableField("is_gifted")
    private Integer isGifted;

    /**
     * 是否一口价商品
     */
    @TableField("is_fixed")
    private Integer isFixed;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
