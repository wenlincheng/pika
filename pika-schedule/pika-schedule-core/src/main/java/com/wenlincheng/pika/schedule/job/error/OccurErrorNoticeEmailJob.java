package com.wenlincheng.pika.schedule.job.error;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

/**
 * Email通知
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Component
public class OccurErrorNoticeEmailJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        throw new RuntimeException(String.format("An exception has occurred in Job, The parameter is %s", shardingContext.getShardingParameter()));
    }
}
