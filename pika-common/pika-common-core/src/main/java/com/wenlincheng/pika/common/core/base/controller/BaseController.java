package com.wenlincheng.pika.common.core.base.controller;

import com.wenlincheng.pika.common.core.session.PikaUser;
import com.wenlincheng.pika.common.core.session.UserSessionHolder;
import com.wenlincheng.pika.common.core.exception.PikaException;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

import static com.wenlincheng.pika.common.core.exception.SystemErrorCodeEnum.USER_NOT_LOGIN;

/**
 * 基础控制器
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
public abstract class BaseController {

    /**
     * 获取当前请求用户
     *
     * @return AuthUser
     */
    public PikaUser currentUser() throws PikaException {
        PikaUser pikaUser = UserSessionHolder.getInstance().getUser();
        if (Objects.isNull(pikaUser)) {
            throw PikaException.construct(USER_NOT_LOGIN).build();
        }
        return pikaUser;
    }

    /**
     * 获取当前请求用户id
     *
     * @return java.lang.Long
     */
    public Long currentUserId() {
        return currentUser().getId();
    }
}
