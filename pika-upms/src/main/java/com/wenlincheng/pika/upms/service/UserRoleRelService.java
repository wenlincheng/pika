package com.wenlincheng.pika.upms.service;

import com.wenlincheng.pika.upms.entity.po.UserRoleRel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 用户和角色关系表 服务类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
public interface UserRoleRelService extends IService<UserRoleRel> {


    /**
     * 根据userId查询用户拥有角色id集合
     *
     * @param userId
     * @return
     */
    Set<Long> queryByUserId(Long userId);
}
