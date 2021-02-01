package com.wenlincheng.pika.schedule.job.order;

import com.wenlincheng.pika.trade.dto.Order;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.dataflow.job.DataflowJob;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO
 *
 * 可通过DataflowJobConfiguration配置是否流式处理。
 * 流式处理数据只有fetchData方法的返回值为null或集合长度为0时，作业才停止抓取，否则作业将一直运行下去；
 * 非流式处理数据则只会在每次作业执行过程中执行一次fetchData方法和processData方法，随即完成本次作业。
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Component
public class OrderDataFlowJob implements DataflowJob<Order> {

    @Override
    public List<Order> fetchData(ShardingContext shardingContext) {
        // TODO 拉取数据 每次处理30条
        List<Order> orders = null;
        // SELECT * FROM order WHERE status = 0 Limit 0, 30
        return orders;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Order> list) {
        // TODO 处理数据后更新数据


    }
}
