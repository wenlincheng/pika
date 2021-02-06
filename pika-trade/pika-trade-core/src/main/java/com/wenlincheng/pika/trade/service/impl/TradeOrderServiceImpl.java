package com.wenlincheng.pika.trade.service.impl;

import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.trade.api.TradeOrderService;
import com.wenlincheng.pika.trade.entity.po.TradeOrder;
import com.wenlincheng.pika.trade.feign.ItemService;
import com.wenlincheng.pika.trade.mapper.TradeOrderMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 主订单 服务实现类
 * </p>
 *
 * @author Pikaman
 * @since 2021-02-06
 */
@Service
public class TradeOrderServiceImpl extends ServiceImpl<TradeOrderMapper, TradeOrder> implements TradeOrderService {

    @Autowired
    private ItemService itemService;

    @Override
    @GlobalTransactional(name = "placeOrder", rollbackFor = Exception.class)
    public Boolean placeOrder(Long itemId) {
        TradeOrder tradeOrder = new TradeOrder();

        this.save(tradeOrder);

        Result<Boolean> stock = itemService.stock(itemId);

        return null;
    }
}
