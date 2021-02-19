package com.wenlincheng.pika.item.feign.fallback;

import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.item.feign.ScheduleService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Component
public class ItemServiceFallbackFactory implements FallbackFactory<ScheduleService> {

    @Override
    public ScheduleService create(Throwable throwable) {

        return new ScheduleService() {
            @Override
            public Result<Boolean> autoOnSaleById(Long id) {
                log.error("商品自动上架失败：id:{}, {}, {}", id, throwable.getMessage(), throwable.getStackTrace());
                return Result.fail();
            }

            @Override
            public Result<Boolean> cancelAutoOnSaleById(Long id) {
                log.error("取消商品自动上架失败 id:{}, {}, {}", id, throwable.getMessage(), throwable.getStackTrace());
                return Result.fail();
            }
        };
    }
}
