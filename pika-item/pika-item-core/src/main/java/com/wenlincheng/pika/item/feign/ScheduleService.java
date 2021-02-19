package com.wenlincheng.pika.item.feign;

import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.item.feign.fallback.ItemServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 定时任务
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@FeignClient(contextId = "scheduleService", value = "pika-schedule", fallbackFactory = ItemServiceFallbackFactory.class)
public interface ScheduleService {

    /**
     * 商品自动上架定时任务
     *
     * @param id 商品id
     * @return Result<Boolean>
     */
    @GetMapping("/job/item/onsale/{itemId}")
    Result<Boolean> autoOnSaleById(@PathVariable("itemId") Long id);

    /**
     * 取消商品自动上架定时任务
     *
     * @param id 商品id
     * @return Result<ItemDetail>
     */
    @GetMapping("/job/item/onsale/cancel/{itemId}")
    Result<Boolean> cancelAutoOnSaleById(@PathVariable("itemId") Long id);
}
