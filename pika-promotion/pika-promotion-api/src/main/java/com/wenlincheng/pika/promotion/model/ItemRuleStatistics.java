package com.wenlincheng.pika.promotion.model;

import java.math.BigDecimal;

/**
 * 参与优惠商品数据统计表
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class ItemRuleStatistics {

    private Long id;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 优惠规则ID
     */
    private Long ruleId;

    /**
     * 商品ID
     */
    private Long itemId;


    /**
     * 商品SKU ID
     */
    private Long skuId;

    /**
     * 折扣单价
     */
    private BigDecimal discountUnitPrice;

    /**
     * 已经使用人数
     */
    private Integer usedUserNum;

    /**
     * 已经使用张数
     */
    private Integer usedNum;

    /**
     * 关联订单数
     */
    private Integer relatedOrderNum;

    /**
     * 支付金额
     */
    private BigDecimal relatedOrderAmount;

    /**
     * 乐观锁
     */
    private Long optVersion;
}
