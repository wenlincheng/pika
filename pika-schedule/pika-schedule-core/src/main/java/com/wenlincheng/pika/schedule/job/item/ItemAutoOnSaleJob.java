package com.wenlincheng.pika.schedule.job.item;

import com.wenlincheng.pika.schedule.client.ItemService;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 商品自动上架
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Component
public class ItemAutoOnSaleJob implements SimpleJob {

    @Autowired
    private ItemService itemService;

    @Override
    public void execute(ShardingContext shardingContext) {
        String parameter = shardingContext.getJobParameter();
        System.out.println("111111");
        // itemService.autoOnSaleById(1L);
    }
}
