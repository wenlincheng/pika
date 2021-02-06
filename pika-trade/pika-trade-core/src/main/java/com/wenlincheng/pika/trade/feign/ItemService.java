package com.wenlincheng.pika.trade.feign;

import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.trade.feign.dto.ItemDetail;
import com.wenlincheng.pika.trade.feign.fallback.ItemServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 商品服务
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@FeignClient(contextId = "itemService", value = "pika-item"/*, fallbackFactory = ItemServiceFallbackFactory.class*/)
public interface ItemService {

    /**
     * 查询商品详情
     *
     * @param id 商品id
     * @return Result<ItemDetail>
     */
    @GetMapping("/item/detail/{itemId}")
    Result<ItemDetail> queryItemDetailById(@PathVariable("itemId") Long id);

    /**
     * 商品自动上架定时任务
     *
     * @param id 商品id
     * @return Result<Boolean>
     */
    @GetMapping("/item/onsale/{itemId}")
    Result<Boolean> autoOnSaleById(@PathVariable("itemId") Long id);

    @GetMapping("/item/stock/{itemId}")
    Result<Boolean> stock(@PathVariable("itemId") Long id);


}
