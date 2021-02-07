package com.wenlincheng.pika.trade.api;

import com.wenlincheng.pika.trade.entity.form.order.PlaceOrderForm;
import com.wenlincheng.pika.trade.entity.po.TradeOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 主订单 服务类
 * </p>
 *
 * @author Pikaman
 * @since 2021-02-06
 */
public interface TradeOrderService extends IService<TradeOrder> {


    /**
     * 下单
     *
     * @param placeOrderForm 下单请求参数
     * @return java.lang.Boolean
     */
    Boolean placeOrder(PlaceOrderForm placeOrderForm);
}
