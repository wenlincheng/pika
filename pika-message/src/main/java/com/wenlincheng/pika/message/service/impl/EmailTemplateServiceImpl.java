package com.wenlincheng.pika.message.service.impl;

import com.wenlincheng.pika.message.entity.po.EmailTemplate;
import com.wenlincheng.pika.message.mapper.EmailTemplateMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenlincheng.pika.message.service.EmailTemplateService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 邮件模板 服务实现类
 * </p>
 *
 * @author Pikaman
 * @since 2021-01-24
 */
@Service
public class EmailTemplateServiceImpl extends ServiceImpl<EmailTemplateMapper, EmailTemplate> implements EmailTemplateService {

}
