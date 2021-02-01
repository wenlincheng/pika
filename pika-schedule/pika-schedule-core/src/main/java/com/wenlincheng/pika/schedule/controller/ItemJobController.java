package com.wenlincheng.pika.schedule.controller;

import cn.hutool.cron.CronUtil;
import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.schedule.util.CronUtils;
import org.apache.shardingsphere.elasticjob.infra.exception.JobSystemException;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.OneOffJobBootstrap;
import org.apache.shardingsphere.elasticjob.lite.internal.schedule.JobScheduler;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * TODO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@RequestMapping("/item")
@RestController
public class ItemJobController {


    @Resource(name = "")
    private OneOffJobBootstrap oneOffJobBootstrap;

    @GetMapping("/on-sale/{id}")
    public Result<Boolean> createItemAutoOnSaleJob(@PathVariable Long id) {

//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-job.xml");
//        ZookeeperRegistryCenter zookeeperRegistryCenter = context.getBean(ZookeeperRegistryCenter.class);
//        long now = System.currentTimeMillis();
//        for (int i = 0; i < 100; i++) {
//            String cron = CronUtils.getCron(new Date(now + (i + 1) * 50000));
//            JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder("dynamicDemoJob-" + i, cron, 2).build();
//            SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(coreConfig, DynamicAddJob.class.getCanonicalName());
//            JobScheduler jobScheduler = new JobScheduler(zookeeperRegistryCenter, LiteJobConfiguration.newBuilder(simpleJobConfig).build());
//            try {
//                jobScheduler.init();
//            }catch (JobSystemException e){
//                e.printStackTrace();
//            }
//        }

        return Result.success();
    }

}
