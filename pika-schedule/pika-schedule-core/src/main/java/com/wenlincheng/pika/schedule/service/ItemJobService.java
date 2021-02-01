package com.wenlincheng.pika.schedule.service;

/**
 * TODO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public interface ItemJobService {

    /**
     * 添加自动上架定时任务
     *
     * @param jobName
     * @param cron
     * @param shardCount
     * @param shardItem
     * @throws
     * @return void
     */
    void addItemAutoOnSaleJob(String jobName, String cron, int shardCount, String shardItem);
}
