package com.wenlincheng.pika.upms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenlincheng.pika.upms.entity.form.role.RoleForm;
import com.wenlincheng.pika.upms.entity.query.role.RolePageQuery;
import com.wenlincheng.pika.upms.entity.po.SysRole;
import com.wenlincheng.pika.upms.entity.vo.role.RoleDetailVO;
import com.wenlincheng.pika.upms.entity.vo.role.RoleListVO;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
public interface RoleService extends IService<SysRole> {

    /**
     * 根据用户id查询用户拥有的角色
     *
     * @param userId 用户id
     * @return List<Role>
     */
    List<SysRole> listByUserId(Long userId);

    /**
     * 角色分页列表
     *
     * @param pageQuery       分页查询参数
     * @return IPage<RoleListVO>
     */
    IPage<RoleListVO> queryPageList(RolePageQuery pageQuery);

    /**
     * 获取角色资源ids
     *
     * @param id 角色id
     * @return RoleDetailVO
     */
    RoleDetailVO getRoleDetailById(Long id);

    /**
     * 新增角色
     *
     * @param roleForm 角色form
     * @return boolean
     */
    boolean addRole(RoleForm roleForm);

    /**
     * 根据id删除
     *
     * @param id 角色id
     * @return boolean
     */
    boolean deleteById(Long id);

    /**
     * 修改角色
     *
     * @param roleForm 角色form
     * @return boolean
     */
    boolean updateRole(RoleForm roleForm);
}
