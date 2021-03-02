package com.wenlincheng.pika.upms.service;

import com.wenlincheng.pika.upms.entity.query.organization.OrganizationPageQuery;
import com.wenlincheng.pika.upms.entity.po.Organization;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户组 服务类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
public interface OrganizationService extends IService<Organization> {

    /**
     * 查询用户组
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
     * @param group 用户组
     * @return boolean
     */
    boolean add(Organization group);

    /**
     * 修改用户组信息
     *
     * @param group 用户组
     * @return boolean
     */
    boolean updateGroup(Organization group);

    /**
     * 根据id删除用户组
     *
     * @param id 用户组id
     * @return boolean
     */
    boolean deleteById(Long id);

    /**
     * 根据用户组id
     *
     * @param id 用户组id
     * @return SysGroup
     */
    Organization queryById(Long id);
}
