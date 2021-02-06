package com.wenlincheng.pika.trade.feign.fallback;

import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.trade.feign.ItemService;
import com.wenlincheng.pika.trade.feign.dto.ItemDetail;
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
public class ItemServiceFallbackFactory implements FallbackFactory<ItemService> {

    @Override
    public ItemService create(Throwable throwable) {

        return new ItemService() {
            @Override
            public Result<ItemDetail> queryItemDetailById(Long id) {
                log.error("获取商品详情失败 id:{}, {}, {}", id, throwable.getMessage(), throwable.getStackTrace());
                return Result.fail();
            }

            @Override
            public Result<Boolean> autoOnSaleById(Long id) {
                log.error("商品自动上架失败：id:{}, {}, {}", id, throwable.getMessage(), throwable.getStackTrace());
                return Result.fail();
            }

            @Override
            public Result<Boolean> stock(Long id) {
                log.error("商品扣减库存失败：id:{}, {}, {}", id, throwable.getMessage(), throwable.getStackTrace());
                return Result.fail();
            }
        };
    }
}
