package com.wenlincheng.pika.upms.service;

import com.wenlincheng.pika.upms.entity.query.organization.OrganizationPageQuery;
import com.wenlincheng.pika.upms.entity.po.Organization;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 组织 服务类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
public interface OrganizationService extends IService<Organization> {

    /**
     * 查询组织
     *
     * @param pageQuery 分页查询参数
     * @return List<SysGroup>
     */
    List<Organization> getPageList(OrganizationPageQuery pageQuery);

    /**
     * 根据父级id查询列表
     *
     * @param parentId 父级id
     * @return List<SysGroup>
     */
    List<Organization> getByParentId(Long parentId);

    /**
     * 添加
     *
     * @param group 组织
     * @return boolean
     */
    boolean add(Organization group);

    /**
     * 修改组织信息
     *
     * @param group 组织
     * @return boolean
     */
    boolean updateGroup(Organization group);

    /**
     * 根据id删除组织
     *
     * @param id 组织id
     * @return boolean
     */
    boolean deleteById(Long id);

    /**
     * 根据组织id
     *
     * @param id 组织id
     * @return SysGroup
     */
    Organization queryById(Long id);
}
