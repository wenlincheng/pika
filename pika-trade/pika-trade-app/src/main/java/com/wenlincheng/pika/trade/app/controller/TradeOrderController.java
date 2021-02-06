package com.wenlincheng.pika.trade.app.controller;


import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.trade.api.TradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.wenlincheng.pika.common.core.base.controller.BaseController;

/**
 * <p>
 * 主订单 前端控制器
 * </p>
 *
 * @author Pikaman
 * @since 2021-02-06
 */
@RestController
@RequestMapping("/app/trade-order")
public class TradeOrderController extends BaseController {

    @Autowired
    private TradeOrderService tradeOrderService;

    @GetMapping("/place")
    public Result<Boolean> placeOrder() {
        tradeOrderService.placeOrder(1L);

        return Result.success(true);
    }
}
