package com.wenlincheng.pika.message.service.impl;

import com.wenlincheng.pika.message.entity.po.SmsSource;
import com.wenlincheng.pika.message.mapper.SmsSourceMapper;
import com.wenlincheng.pika.message.service.SmsMessageSourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 短信供应商 服务实现类
 * </p>
 *
 * @author Pikaman
 * @since 2021-02-04
 */
@Service
public class SmsSourceServiceImpl extends ServiceImpl<SmsSourceMapper, SmsSource> implements SmsMessageSourceService {

}
