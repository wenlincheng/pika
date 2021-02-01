package com.wenlincheng.pika.message.service;

import com.wenlincheng.pika.message.entity.po.SmsTemplate;
import com.wenlincheng.pika.message.mapper.SmsTemplateMapper;
import com.wenlincheng.pika.message.api.SmsTemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 短信模板 服务实现类
 * </p>
 *
 * @author Pikaman
 * @since 2021-01-24
 */
@Service
public class SmsTemplateServiceImpl extends ServiceImpl<SmsTemplateMapper, SmsTemplate> implements SmsTemplateService {

}
