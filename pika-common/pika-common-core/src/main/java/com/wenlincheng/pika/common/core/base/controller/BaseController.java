package com.wenlincheng.pika.common.core.base.controller;

import com.wenlincheng.pika.common.core.context.PikaUser;
import com.wenlincheng.pika.common.core.context.UserContextHolder;
import com.wenlincheng.pika.common.core.exception.PikaException;
import lombok.extern.slf4j.Slf4j;

/**
 * 基础控制器
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
public class BaseController {

    /**
     * 获取当前请求用户
     *
     * @return AuthUser
     */
    public PikaUser currentUser() throws PikaException {
        return UserContextHolder.getInstance().getUser();
    }

    /**
     * 获取当前请求用户id
     *
     * @return java.lang.Long
     */
    public Long currentUserId() {
        return UserContextHolder.getInstance().getUserId();
    }
}
