package com.wenlincheng.pika.message.service;

import com.wenlincheng.pika.message.entity.po.EmailUserSign;
import com.wenlincheng.pika.message.mapper.EmailUserSignMapper;
import com.wenlincheng.pika.message.api.EmailUserSignService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 邮件签名 服务实现类
 * </p>
 *
 * @author Pikaman
 * @since 2021-01-24
 */
@Service
public class EmailUserSignServiceImpl extends ServiceImpl<EmailUserSignMapper, EmailUserSign> implements EmailUserSignService {

}
