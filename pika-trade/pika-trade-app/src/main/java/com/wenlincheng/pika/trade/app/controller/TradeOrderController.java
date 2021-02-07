package com.wenlincheng.pika.trade.app.controller;


import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.trade.api.TradeOrderService;
import com.wenlincheng.pika.trade.entity.form.order.PlaceOrderForm;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/place")
    public Result<Boolean> placeOrder(@RequestBody PlaceOrderForm placeOrderForm) {
        // 参数检验

        tradeOrderService.placeOrder(placeOrderForm);

        return Result.success(true);
    }
}
