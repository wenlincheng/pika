package com.wenlincheng.pika.item.entity.vo.item;

import com.wenlincheng.pika.common.core.base.vo.BaseVo;
import com.wenlincheng.pika.item.entity.po.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商品详情VO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ItemDetailStepOneVO extends BaseVo<Item> {

    public ItemDetailStepOneVO(Item item) {
        super(item);
    }

    /**
     * 商品名
     */
    private String name;

    /**
     * 商品价格
     */
    private BigDecimal price;
}
