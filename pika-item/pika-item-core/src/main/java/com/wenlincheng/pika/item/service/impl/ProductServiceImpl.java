package com.wenlincheng.pika.item.service.impl;

import com.wenlincheng.pika.item.entity.po.Product;
import com.wenlincheng.pika.item.mapper.ProductMapper;
import com.wenlincheng.pika.item.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品 服务实现类
 * </p>
 *
 * @author Pikaman
 * @since 2021-01-31
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
