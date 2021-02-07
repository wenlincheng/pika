package com.wenlincheng.pika.trade.entity.form.order;

import com.wenlincheng.pika.common.core.base.form.BaseForm;
import com.wenlincheng.pika.trade.entity.po.TradeOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 下单表单
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/2/7 9:35 下午
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PlaceOrderForm extends BaseForm<TradeOrder> {

    private static final long serialVersionUID = -4188112956406063557L;

    /**
     * 订单商品
     */
    private List<OrderItemForm> orderItemList;
}
