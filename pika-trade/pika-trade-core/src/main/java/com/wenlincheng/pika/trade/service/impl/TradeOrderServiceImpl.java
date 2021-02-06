package com.wenlincheng.pika.trade.service.impl;

import com.wenlincheng.pika.trade.api.TradeOrderService;
import com.wenlincheng.pika.trade.entity.po.TradeOrder;
import com.wenlincheng.pika.trade.mapper.TradeOrderMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
