package com.wenlincheng.pika.common.leaf.service.impl;

import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.service.SnowflakeService;
import com.wenlincheng.pika.common.core.exception.BaseException;
import com.wenlincheng.pika.common.leaf.service.LeafSegmentService;
import com.wenlincheng.pika.common.leaf.service.LeafSnowflakeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.wenlincheng.pika.common.leaf.enums.LeafErrorCodeEnum.ID_GEN_ERROR;

/**
 * Snowflake
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Service
public class LeafSnowflakeServiceImpl implements LeafSnowflakeService {

    @Autowired
    private SnowflakeService snowflakeService;

    @Override
    public Long genId(String key) {
        Result result = snowflakeService.getId(key);
        long id = result.getId();
        if (id <= 0) {
            throw BaseException.construct(ID_GEN_ERROR).build();
        }
        return id;
    }
}
