package com.wenlincheng.pika.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wenlincheng.pika.upms.entity.po.UserRoleRel;
import com.wenlincheng.pika.upms.mapper.UserRoleRelMapper;
import com.wenlincheng.pika.upms.service.UserRoleRelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户和角色关系表 服务实现类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Service
public class UserRoleRelServiceImpl extends ServiceImpl<UserRoleRelMapper, UserRoleRel> implements UserRoleRelService {
    @Override
    public Set<Long> queryByUserId(Long userId) {
        QueryWrapper<UserRoleRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRoleRel::getUserId, userId);
        List<UserRoleRel> userRoleList = list(queryWrapper);
        return userRoleList.stream().map(UserRoleRel::getRoleId).collect(Collectors.toSet());
    }
}
