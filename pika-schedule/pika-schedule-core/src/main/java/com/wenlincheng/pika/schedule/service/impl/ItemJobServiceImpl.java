package com.wenlincheng.pika.schedule.service.impl;

import com.wenlincheng.pika.schedule.service.ItemJobService;
import org.springframework.stereotype.Service;

/**
 * 商品job
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Service
public class ItemJobServiceImpl implements ItemJobService {

    @Override
    public void addItemAutoOnSaleJob(String jobName, String cron, int shardCount, String shardItem) {


    }
}
