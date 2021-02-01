package com.wenlincheng.pika.promotion.model;

/**
 * 参与优惠买家数据统计表
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class BuyerRuleStatistics {

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
     * 买家ID
     */
    private Long buyerUserId;

    /**
     * 使用次数
     */
    private Integer usedCount;

    /**
     * 乐观锁
     */
    private Long optVersion;
}
