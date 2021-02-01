package com.wenlincheng.pika.upms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenlincheng.pika.common.core.exception.BaseException;
import com.wenlincheng.pika.upms.entity.form.menu.MenuForm;
import com.wenlincheng.pika.upms.entity.query.menu.MenuPageQuery;
import com.wenlincheng.pika.upms.entity.po.SysMenu;
import com.wenlincheng.pika.upms.entity.vo.menu.MenuListVO;
import com.wenlincheng.pika.upms.entity.vo.menu.MenuRouter;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
public interface MenuService extends IService<SysMenu> {

    /**
     * 分页查询
     *
     * @param pageQuery    分页查询参数
     * @return IPage<MenuListVO>
     */
    IPage<MenuListVO> queryPageList(MenuPageQuery pageQuery);

    /**
     * 查询所有菜单列表
     *
     * @return List<MenuListVO>
     */
    List<MenuListVO> queryList();

    /**
     * 查询用户菜单列表
     * @param userId 用户id
     * @return List<MenuListVO>
     */
    List<MenuListVO> queryListByUserId(Long userId);

    /**
     * 根据id查询
     *
     * @param id 菜单id
     * @return SysMenu
     */
    SysMenu queryById(Long id);

    /**
     * 根据id删除
     *
     * @param id 菜单id
     * @throws BaseException 异常
     * @return boolean
     */
    boolean deleteById(Long id) throws BaseException;

    /**
     * 新增菜单
     *
     * @param menuForm 菜单form
     * @return boolean
     */
    boolean addMenu(MenuForm menuForm);

    /**
     * 修改菜单
     * 
     * @param menuForm 菜单form
     * @return boolean
     */
    boolean updateMenu(MenuForm menuForm);

    /**
     * 查询角色的权限列表
     *
     * @param roleId 角色id
     * @return List<SysMenu>
     */
    List<SysMenu> queryPermsByRoleId(Long roleId);

    /**
     * 查询用户的权限列表
     *
     * @param userId 用户id
     * @return List<SysMenu>
     */
    List<SysMenu> queryPermsByUserId(Long userId);

    /**
     * 查询所有权限
     *
     * @return List<SysMenu>
     */
    List<SysMenu> queryAllPerms();

    /**
     * 查询所有菜单节点列表
     *
     * @return List<MenuNode>
     */
    List<MenuRouter> queryMenuNodeList();
}
