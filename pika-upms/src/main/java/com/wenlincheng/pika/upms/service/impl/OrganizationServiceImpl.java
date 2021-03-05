package com.wenlincheng.pika.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wenlincheng.pika.upms.entity.query.organization.OrganizationPageQuery;
import com.wenlincheng.pika.upms.entity.po.Organization;
import com.wenlincheng.pika.upms.mapper.OrganizationMapper;
import com.wenlincheng.pika.upms.service.OrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 组织表 服务实现类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    @Override
    public List<Organization> getByParentId(Long parentId) {
        QueryWrapper<Organization> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Organization::getParentId, parentId);
        return this.list(queryWrapper);
    }

    @Override
    public boolean add(Organization group) {
        return this.add(group);
    }

    @Override
    public boolean deleteById(Long id) {
        return this.removeById(id);
    }

    @Override
    public List<Organization> getPageList(OrganizationPageQuery queryForm) {
        QueryWrapper<Organization> queryWrapper = queryForm.buildWrapper();
        queryWrapper.lambda().eq(Organization::getName, queryForm.getName());
        return this.list(queryWrapper);
    }

    @Override
    public boolean updateGroup(Organization group) {
        return this.updateById(group);
    }


    @Override
    public Organization queryById(Long id) {
        return this.getById(id);
    }

}
