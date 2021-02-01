package com.wenlincheng.pika.promotion.model;

import java.math.BigDecimal;

/**
 * 规则数据统计表
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class RuleStatistics {

    private Long id;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 规则ID
     */
    private Long ruleId;

    /**
     * 优惠券总数
     */
    private Integer ruleTotalNum;

    /**
     * 领取人数
     */
    private Integer receivedUserNum;

    /**
     * 领取张数
     */
    private Integer receivedNum;

    /**
     * 已经使用人数
     */
    private Integer usedUserNum;

    /**
     * 已经使用张数
     */
    private Integer usedNum;

    /**
     * 领取率
     */
    private BigDecimal receivedRate;

    /**
     * 使用率
     */
    private BigDecimal usedRate;

    /**
     * 关联订单
     */
    private Integer relatedOrderNum;

    /**
     * 支付金额
     */
    private BigDecimal relatedOrderAmount;

    /**
     * 参与商品数
     */
    private Integer relatedItemNum;
}
