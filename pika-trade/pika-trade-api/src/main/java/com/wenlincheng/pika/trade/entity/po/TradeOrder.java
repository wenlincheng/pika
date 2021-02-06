package com.wenlincheng.pika.trade.entity.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import com.wenlincheng.pika.common.core.base.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 主订单
 * </p>
 *
 * @author Pikaman
 * @since 2021-02-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("trade_order")
public class TradeOrder extends BaseModel<TradeOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 卖家id
     */
    @TableField("seller_id")
    private Long sellerId;

    /**
     * 卖家名称
     */
    @TableField("seller_name")
    private String sellerName;

    /**
     * 买家id
     */
    @TableField("buyer_id")
    private Long buyerId;

    /**
     * 卖家名称
     */
    @TableField("buyer_name")
    private String buyerName;

    /**
     * 店铺code
     */
    @TableField("shop_code")
    private String shopCode;

    /**
     * 店铺名称
     */
    @TableField("shop_name")
    private String shopName;

    /**
     * 买家信息
     */
    @TableField("buyer_info")
    private String buyerInfo;

    /**
     * 收货人名称
     */
    @TableField("consignee_name")
    private String consigneeName;

    /**
     * 收货人手机
     */
    @TableField("consignee_phone")
    private String consigneePhone;

    /**
     * 收货地址末级地区码
     */
    @TableField("consignee_region_code")
    private String consigneeRegionCode;

    /**
     * 收货人地址
     */
    @TableField("consignee_full_address")
    private String consigneeFullAddress;

    /**
     * 下单时间
     */
    @TableField("order_time")
    private Date orderTime;

    /**
     * 订单类型
     */
    @TableField("order_type")
    private String orderType;

    /**
     * 订单状态
     */
    @TableField("order_status")
    private String orderStatus;

    /**
     * 订单总额（不包含优惠），即：实际总额 + 优惠金额
     */
    @TableField("origin_amount")
    private BigDecimal originAmount;

    /**
     * 成交金额，SUM(明细行成交金额) + 运费
     */
    @TableField("actual_amount")
    private BigDecimal actualAmount;

    /**
     * SUM(明细行货款金额)
     */
    @TableField("actual_goods_amount")
    private BigDecimal actualGoodsAmount;

    /**
     * SUM(明细行优惠金额)，包含商品优惠，订单优惠的分摊和平台优惠的分摊
     */
    @TableField("discount_amount")
    private BigDecimal discountAmount;

    /**
     * 已支付金额，取支付信息的支付金额和
     */
    @TableField("paid_amount")
    private BigDecimal paidAmount;

    /**
     * 原运费
     */
    @TableField("origin_shipping_fee")
    private BigDecimal originShippingFee;

    /**
     * 实际运费
     */
    @TableField("actual_shipping_fee")
    private BigDecimal actualShippingFee;

    /**
     * SUM(单品税费)
     */
    @TableField("tax_fee")
    private BigDecimal taxFee;

    /**
     * 订单退款金额
     */
    @TableField("refund_amount")
    private BigDecimal refundAmount;

    /**
     * 币种编码
     */
    @TableField("currency_code")
    private String currencyCode;

    /**
     * 优惠信息
     */
    @TableField("promotion_infos")
    private String promotionInfos;

    /**
     * 支付方式
     */
    @TableField("pay_type")
    private String payType;

    /**
     * 支付状态
     */
    @TableField("pay_status")
    private String payStatus;

    /**
     * 支付时间
     */
    @TableField("paid_time")
    private Date paidTime;

    /**
     * 也是超时关闭
     */
    @TableField("paid_expire_time")
    private Date paidExpireTime;

    /**
     * 履约单状态
     */
    @TableField("delivery_order_status")
    private String deliveryOrderStatus;

    /**
     * 发货通知单发货时回填该时间
     */
    @TableField("delivery_time")
    private Date deliveryTime;

    /**
     * 交易成功或关闭的时间
     */
    @TableField("finish_time")
    private Date finishTime;

    /**
     * 关闭原因
     */
    @TableField("close_reason")
    private String closeReason;

    /**
     * 买家备注
     */
    @TableField("buyer_remark")
    private String buyerRemark;

    /**
     * 卖家备注
     */
    @TableField("seller_remark")
    private String sellerRemark;

    /**
     * 是否退款中
     */
    @TableField("is_reverse_pending")
    private Integer isReversePending;

    /**
     * 是否已评价
     */
    @TableField("is_evaluated")
    private Integer isEvaluated;

    /**
     * 是否开启超期售后
     */
    @TableField("is_overdue_reverse")
    private Integer isOverdueReverse;

    /**
     * 编码
     */
    @TableField("code")
    private String code;

    @TableField("address_id")
    private Long addressId;

    /**
     * 平台备注
     */
    @TableField("platform_remark_list")
    private String platformRemarkList;

    /**
     * 发票状态
     */
    @TableField("invoice_status")
    private String invoiceStatus;

    /**
     * 评价状态
     */
    @TableField("evaluation_status")
    private String evaluationStatus;

    /**
     * 订单组id
     */
    @TableField("trade_order_group_id")
    private Long tradeOrderGroupId;

    /**
     * 优惠信息
     */
    @TableField("order_promotion_info")
    private String orderPromotionInfo;

    /**
     * 是否买家删除
     */
    @TableField("is_buyer_deleted")
    private Integer isBuyerDeleted;

    /**
     * 订单标记
     */
    @TableField("option")
    private String option;

    /**
     * 确认订单页购物车合计
     */
    @TableField("actual_face_amount")
    private BigDecimal actualFaceAmount;

    /**
     * 统计发货时效用
     */
    @TableField("prepare_delivery_time")
    private Date prepareDeliveryTime;

    /**
     * 统计发货时效用
     */
    @TableField("expected_delivery_time")
    private Date expectedDeliveryTime;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
