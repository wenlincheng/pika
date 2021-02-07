package com.wenlincheng.pika.trade.entity.form.order;

import lombok.Data;

/**
 * 订单商品明细
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/2/7 9:38 下午
 */
@Data
public class OrderItemForm {

    /**
     * 商品id
     */
    private Long itemId;

    /**
     * 商品销售SKU id
     */
    private Long saleSkuId;

    /**
     * 数量
     */
    private Integer num;

}
