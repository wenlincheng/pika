package com.wenlincheng.pika.message.service.impl;

import com.wenlincheng.pika.message.entity.po.EmailUserSign;
import com.wenlincheng.pika.message.mapper.EmailUserSignMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenlincheng.pika.message.service.EmailUserSignService;
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
