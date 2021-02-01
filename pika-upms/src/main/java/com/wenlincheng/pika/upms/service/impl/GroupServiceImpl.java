package com.wenlincheng.pika.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wenlincheng.pika.upms.entity.query.group.GroupPageQuery;
import com.wenlincheng.pika.upms.entity.po.SysGroup;
import com.wenlincheng.pika.upms.mapper.SysGroupMapper;
import com.wenlincheng.pika.upms.service.GroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户组表 服务实现类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Service
public class GroupServiceImpl extends ServiceImpl<SysGroupMapper, SysGroup> implements GroupService {

    @Autowired
    private SysGroupMapper groupMapper;

    @Override
    public List<SysGroup> getByParentId(Long parentId) {
        QueryWrapper<SysGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysGroup::getParentId, parentId);
        return groupMapper.selectList(queryWrapper);
    }

    @Override
    public boolean add(SysGroup group) {
        return this.add(group);
    }

    @Override
    public boolean deleteById(Long id) {
        return this.removeById(id);
    }

    @Override
    public List<SysGroup> getPageList(GroupPageQuery queryForm) {
        QueryWrapper<SysGroup> queryWrapper = queryForm.buildWrapper();
        queryWrapper.lambda().eq(SysGroup::getName, queryForm.getName());
        return this.list(queryWrapper);
    }

    @Override
    public boolean updateGroup(SysGroup group) {
        return this.updateById(group);
    }


    @Override
    public SysGroup queryById(Long id) {
        return this.getById(id);
    }

}
