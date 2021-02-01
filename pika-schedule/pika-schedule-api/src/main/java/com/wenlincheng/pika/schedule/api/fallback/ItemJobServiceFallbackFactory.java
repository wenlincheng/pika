package com.wenlincheng.pika.schedule.api.fallback;

import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.schedule.api.ItemJobService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 商品Fallback
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Component
public class ItemJobServiceFallbackFactory implements FallbackFactory<ItemJobService> {

    @Override
    public ItemJobService create(Throwable throwable) {

        return new ItemJobService() {

            @Override
            public Result<Boolean> createItemAutoOnSaleJob(Long id) {
                log.error("创建商品自动上架任务失败：id:{}, {}, {}", id, throwable.getMessage(), throwable.getStackTrace());
                return Result.fail();
            }
        };
    }
}
