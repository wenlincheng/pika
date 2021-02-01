package com.wenlincheng.pika.common.data.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.wenlincheng.pika.common.leaf.service.LeafSnowflakeService;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义分布式id生成器
 * Snowflake算法
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Component
public class CustomIdGenerator implements IdentifierGenerator {

    @Autowired
    private LeafSnowflakeService leafSnowflakeService;

    @Override
    public Long nextId(Object entity) {
        // 可以将当前传入的class全类名来作为bizKey,或者提取参数来生成bizKey进行分布式Id调用生成.
        Class<?> entityClass = entity.getClass();
        String bizKey = entityClass.getName();
        // MetaObject metaObject = SystemMetaObject.forObject(entity);
        // String name = (String) metaObject.getValue("name");
        // 根据bizKey调用分布式ID生成
        return leafSnowflakeService.genId(bizKey);
    }
}
