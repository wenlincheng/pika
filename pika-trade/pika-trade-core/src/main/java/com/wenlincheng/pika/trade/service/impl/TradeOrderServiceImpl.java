package com.wenlincheng.pika.trade.service.impl;

import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.common.core.exception.BaseException;
import com.wenlincheng.pika.trade.api.TradeOrderService;
import com.wenlincheng.pika.trade.entity.form.order.PlaceOrderForm;
import com.wenlincheng.pika.trade.entity.po.TradeOrder;
import com.wenlincheng.pika.trade.feign.ItemService;
import com.wenlincheng.pika.trade.mapper.TradeOrderMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.wenlincheng.pika.trade.enums.TradeErrorCodeEnum.PLACE_ORDER_ERROR;

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
    public Boolean placeOrder(PlaceOrderForm placeOrderForm) {
        TradeOrder tradeOrder = new TradeOrder();

        // 创建订单
        this.save(tradeOrder);

        if (CollectionUtils.isEmpty(placeOrderForm.getOrderItemList())) {
            throw BaseException.construct(PLACE_ORDER_ERROR).appendMsg("请选择商品").build();
        }
        // 扣减库存
        placeOrderForm.getOrderItemList().forEach(orderItemForm -> {
            itemService.reduceStock(orderItemForm.getSaleSkuId());
        });

        return true;
    }
}
