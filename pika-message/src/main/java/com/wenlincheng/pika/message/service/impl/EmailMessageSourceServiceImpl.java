package com.wenlincheng.pika.message.service.impl;

import com.wenlincheng.pika.message.entity.po.EmailSource;
import com.wenlincheng.pika.message.mapper.EmailSourceMapper;
import com.wenlincheng.pika.message.service.EmailMessageSourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 邮件服务器 服务实现类
 * </p>
 *
 * @author Pikaman
 * @since 2021-02-04
 */
@Service
public class EmailMessageSourceServiceImpl extends ServiceImpl<EmailSourceMapper, EmailSource> implements EmailMessageSourceService {

}
