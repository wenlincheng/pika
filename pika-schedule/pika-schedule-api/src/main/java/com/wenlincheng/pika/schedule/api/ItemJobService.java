package com.wenlincheng.pika.schedule.api;

import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.schedule.api.fallback.ItemJobServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 商品Job服务
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@FeignClient(contextId = "itemJobService", value = "pika-schedule", fallbackFactory = ItemJobServiceFallbackFactory.class)
public interface ItemJobService {

    /**
     * 商品自动上架定时任务
     *
     * @param id 商品id
     * @return Result<Boolean>
     */
    @GetMapping("/item/on-sale/{id}")
    Result<Boolean> createItemAutoOnSaleJob(@PathVariable Long id);

}
