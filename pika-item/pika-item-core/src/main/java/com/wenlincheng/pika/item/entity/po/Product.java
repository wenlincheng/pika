package com.wenlincheng.pika.item.entity.po;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.wenlincheng.pika.common.core.base.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 产品
 * </p>
 *
 * @author Pikaman
 * @since 2021-01-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("product")
public class Product extends BaseModel<Product> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 产品名称
     */
    @TableField("name")
    private String name;

    /**
     * 产品来源
     */
    @TableField("source")
    private String source;

    /**
     * 产品来源
     */
    @TableField("product_type")
    private String productType;

    /**
     * 产品类型
     */
    @TableField("sku_type")
    private String skuType;

    /**
     * 类目CODE
     */
    @TableField("category_code")
    private String categoryCode;

    /**
     * 类目路径
     */
    @TableField("cate_path")
    private String catePath;

    /**
     * 品牌CODE
     */
    @TableField("brand_code")
    private String brandCode;

    /**
     * 属性
     */
    @TableField("attr_vals")
    private String attrVals;

    /**
     * 主产品图片地址
     */
    @TableField("main_pic_url")
    private String mainPicUrl;

    /**
     * 产品图片列表
     */
    @TableField("pics_url")
    private String picsUrl;

    /**
     * 产品开票税率
     */
    @TableField("invoice_tax_rate")
    private BigDecimal invoiceTaxRate;

    /**
     * 币种编码
     */
    @TableField("currency_code")
    private String currencyCode;

    /**
     * 零售价
     */
    @TableField("origin_unit_price")
    private BigDecimal originUnitPrice;

    /**
     * 成本单价
     */
    @TableField("cost_unit_price")
    private BigDecimal costUnitPrice;

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
     * 物流方式
     */
    @TableField("delivery_type")
    private String deliveryType;

    /**
     * 产品用途
     */
    @TableField("purpose")
    private String purpose;

    /**
     * 产品描述
     */
    @TableField("description")
    private String description;

    /**
     * 产品状态
     */
    @TableField("data_status")
    private String dataStatus;

    /**
     * 编码
     */
    @TableField("code")
    private String code;

    /**
     * 业务所有者ID
     */
    @TableField("owner_id")
    private Long ownerId;

    /**
     * 开票税收编码ID
     */
    @TableField("invoice_tax_code_id")
    private Long invoiceTaxCodeId;

    /**
     * 最小单位ID
     */
    @TableField("base_unit_id")
    private Long baseUnitId;

    /**
     * 库存单位ID
     */
    @TableField("inv_unit_id")
    private Long invUnitId;

    /**
     * 运费模板ID
     */
    @TableField("shipping_fee_template_id")
    private Long shippingFeeTemplateId;

    /**
     * 英文名称
     */
    @TableField("en_name")
    private String enName;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
