package com.wenlincheng.pika.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wenlincheng.pika.upms.entity.po.UserRoleRelation;
import com.wenlincheng.pika.upms.mapper.UserRoleRelationMapper;
import com.wenlincheng.pika.upms.service.UserRoleRelationService;
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
public class UserRoleRelationServiceImpl extends ServiceImpl<UserRoleRelationMapper, UserRoleRelation> implements UserRoleRelationService {
    @Override
    public Set<Long> queryByUserId(Long userId) {
        QueryWrapper<UserRoleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRoleRelation::getUserId, userId);
        List<UserRoleRelation> userRoleList = list(queryWrapper);
        return userRoleList.stream().map(UserRoleRelation::getRoleId).collect(Collectors.toSet());
    }
}
