package com.wenlincheng.pika.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenlincheng.pika.common.core.exception.PikaException;
import com.wenlincheng.pika.common.core.redis.RedisUtils;
import com.wenlincheng.pika.upms.entity.form.menu.MenuForm;
import com.wenlincheng.pika.upms.entity.po.Menu;
import com.wenlincheng.pika.upms.entity.po.RoleMenuRel;
import com.wenlincheng.pika.upms.entity.po.UserRoleRel;
import com.wenlincheng.pika.upms.entity.vo.menu.MenuListVO;
import com.wenlincheng.pika.upms.entity.vo.menu.MenuRouter;
import com.wenlincheng.pika.upms.entity.vo.menu.RouterMeta;
import com.wenlincheng.pika.upms.enums.SysMenuTypeEnum;
import com.wenlincheng.pika.upms.mapper.MenuMapper;
import com.wenlincheng.pika.upms.service.MenuService;
import com.wenlincheng.pika.upms.service.RoleMenuRelService;
import com.wenlincheng.pika.upms.service.UserRoleRelService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.wenlincheng.pika.common.core.constant.SecurityConstants.PERMISSIONS_REDIS_KEY;
import static com.wenlincheng.pika.common.core.constant.SecurityConstants.USER_PERMISSIONS_REDIS_KEY;
import static com.wenlincheng.pika.upms.enums.UpmsErrorCodeEnum.MENU_HAS_CHILDREN;
import static com.wenlincheng.pika.upms.enums.UpmsErrorCodeEnum.MENU_REL_ROLE_DELETE_ERROR;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private RoleMenuRelService roleMenuService;
    @Autowired
    private UserRoleRelService userRoleService;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public List<MenuListVO> queryList() {
        return getChildren(0L);
    }

    @Override
    public List<MenuListVO> queryListByParentId(Long parentId) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Menu::getParentId, parentId)
                .orderByDesc(parentId == 0, Menu::getSequence)
                .orderByAsc(parentId != 0, Menu::getSequence);
        List<Menu> menuList = this.list(queryWrapper);
        List<MenuListVO> menuListVOS = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(menuList)) {
            menuList.forEach(menu -> {
                MenuListVO menuListVO = new MenuListVO(menu);
                QueryWrapper<Menu> countWrapper = new QueryWrapper<>();
                countWrapper.lambda().eq(Menu::getParentId, menu.getId());
                int count = this.count(countWrapper);
                menuListVO.setHasChildren(count > 0);
                menuListVOS.add(menuListVO);
            });
        }
        return menuListVOS;
    }

    @Override
    public List<MenuListVO> queryListByUserId(Long userId) {
        List<MenuListVO> menuListVOList = new ArrayList<>();
        Set<Long> menuIds = queryUserMenuIds(userId);
        if (CollectionUtils.isNotEmpty(menuIds)) {
            QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
            menuQueryWrapper.lambda()
                    .eq(Menu::getParentId, 0L)
                    .in(Menu::getId, menuIds);
            List<Menu> menuList = this.list(menuQueryWrapper);
            menuList.forEach(menu -> {
                MenuListVO menuListVO = new MenuListVO(menu);
                menuListVO.setChildren(getChildren(menuListVO.getId()));
                menuListVOList.add(menuListVO);
            });
        }
        return menuListVOList;
    }

    @Override
    public Menu queryById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean deleteById(Long id) throws PikaException {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Menu::getParentId, id);
        List<Menu> menuList = this.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(menuList)) {
            throw PikaException.construct(MENU_HAS_CHILDREN).build();
        }
        QueryWrapper<RoleMenuRel> roleMenuQueryWrapper = new QueryWrapper<>();
        roleMenuQueryWrapper.lambda().eq(RoleMenuRel::getMenuId, id);
        List<RoleMenuRel> roleMenuList = roleMenuService.list(roleMenuQueryWrapper);
        if (CollectionUtils.isNotEmpty(roleMenuList)) {
            throw PikaException.construct(MENU_REL_ROLE_DELETE_ERROR).build();
        }
        boolean remove = this.removeById(id);
        if (remove) {
            clearUserPermissionsCache(id);
            clearAllPermissionsCache();
        }
        return remove;
    }

    /**
     * 清除拥有该菜单用户的权限缓存
     *
     * @param menuId 菜单id
     * @return void
     */
    private void clearUserPermissionsCache(Long menuId) {
        QueryWrapper<RoleMenuRel> roleMenuQueryWrapper = new QueryWrapper<>();
        roleMenuQueryWrapper.lambda().eq(RoleMenuRel::getMenuId, menuId);
        List<RoleMenuRel> roleMenuList = roleMenuService.list(roleMenuQueryWrapper);
        for (RoleMenuRel roleMenu : roleMenuList) {
            QueryWrapper<UserRoleRel> userRoleQueryWrapper = new QueryWrapper<>();
            userRoleQueryWrapper.lambda().eq(UserRoleRel::getRoleId, roleMenu.getRoleId());
            List<UserRoleRel> userRoleList = userRoleService.list(userRoleQueryWrapper);
            for (UserRoleRel userRole : userRoleList) {
                redisUtils.delete(USER_PERMISSIONS_REDIS_KEY + userRole.getUserId());
            }
        }
    }

    /**
     * 清除所有的权限缓存
     *
     * @return void
     */
    private void clearAllPermissionsCache() {
        redisUtils.delete(PERMISSIONS_REDIS_KEY);
    }

    @Override
    public boolean addMenu(MenuForm menuForm) {
        Menu sysMenu = menuForm.toPo(Menu.class);
        boolean save = this.save(sysMenu);
        if (save) {
            clearAllPermissionsCache();
        }
        return save;
    }

    @Override
    public boolean updateMenu(MenuForm menuForm) {
        Menu sysMenu = menuForm.toPo(Menu.class);
        boolean update = this.updateById(sysMenu);
        if (update) {
            clearUserPermissionsCache(menuForm.getId());
            clearAllPermissionsCache();
        }
        return update;
    }

    @Override
    public List<Menu> queryPermsByRoleId(Long roleId) {
        QueryWrapper<RoleMenuRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleMenuRel::getRoleId, roleId);
        List<RoleMenuRel> roleMenuRelationList = roleMenuService.list(queryWrapper);
        Set<Long> menuIds = roleMenuRelationList.stream().map(RoleMenuRel::getMenuId).collect(Collectors.toSet());
        List<Menu> menuList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(menuIds)) {
            menuList = this.listByIds(menuIds);
        }
        return menuList;
    }

    @Override
    public List<Menu> queryPermsByUserId(Long userId) {
        List<Menu> menuList = new ArrayList<>();
        Set<Long> menuIds = queryUserMenuIds(userId);
        if (CollectionUtils.isNotEmpty(menuIds)) {
            QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
            menuQueryWrapper.lambda()
                    .eq(Menu::getType, SysMenuTypeEnum.BUTTON.getValue())
                    .in(Menu::getId, menuIds);
            menuList = this.list(menuQueryWrapper);
        }

        return menuList;
    }

    /**
     * 查询用户菜单ids
     *
     * @param userId 用户id
     * @return java.util.Set<java.lang.Long>
     */
    private Set<Long> queryUserMenuIds(Long userId) {
        QueryWrapper<UserRoleRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRoleRel::getUserId, userId);
        List<UserRoleRel> userRoleRelationList = userRoleService.list(queryWrapper);
        // 用户的所有角色
        Set<Long> roleIds = userRoleRelationList.stream().map(UserRoleRel::getRoleId).collect(Collectors.toSet());
        if (CollectionUtils.isNotEmpty(roleIds)) {
            QueryWrapper<RoleMenuRel> roleMenuQueryWrapper = new QueryWrapper<>();
            roleMenuQueryWrapper.lambda().in(RoleMenuRel::getRoleId, roleIds);
            List<RoleMenuRel> roleMenuRelationList = roleMenuService.list(roleMenuQueryWrapper);
            // 用户的所有权限
            if (CollectionUtils.isNotEmpty(roleMenuRelationList)) {
                return roleMenuRelationList.stream().map(RoleMenuRel::getMenuId).collect(Collectors.toSet());
            }
        }
        return null;
    }

    @Override
    public List<Menu> queryAllPerms() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Menu::getType, SysMenuTypeEnum.BUTTON.getValue());
        return this.list(queryWrapper);
    }

    @Override
    public List<MenuRouter> queryMenuNodeList() {
        return getNodeChildren(0L);
    }

    private List<MenuListVO> getChildren(Long parentId) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Menu::getParentId, parentId)
                .orderByAsc(Menu::getSequence);
        List<Menu> menuList = this.list(queryWrapper);

        if (CollectionUtils.isEmpty(menuList)) {
            return new ArrayList<>();
        }
        List<MenuListVO> list = new ArrayList<>();
        menuList.forEach(menu -> {
            MenuListVO menuListVO = new MenuListVO(menu);
            menuListVO.setChildren(getChildren(menu.getId()));
            list.add(menuListVO);
        });

        return list;
    }

    /**
     * 查询菜单节点
     *
     * @param parentId 父级id
     * @return List<MenuNode>
     */
    @SuppressWarnings("unchecked")
    private List<MenuRouter> getNodeChildren(Long parentId) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Menu::getParentId, parentId)
                .notIn(Menu::getType, SysMenuTypeEnum.BUTTON.getValue())
                .orderByDesc(parentId == 0, Menu::getSequence)
                .orderByAsc(parentId > 0, Menu::getSequence);
        List<Menu> sysMenuList = this.list(queryWrapper);

        if (CollectionUtils.isEmpty(sysMenuList)) {
            return new ArrayList<>();
        }
        List<MenuRouter> menuRouters = new ArrayList<>();
        sysMenuList.forEach(sysMenu -> {
            MenuRouter menuRouter = new MenuRouter();
            menuRouter.setName(sysMenu.getName());
            menuRouter.setPath(sysMenu.getUri());
            RouterMeta meta = new RouterMeta();
            meta.setIcon(sysMenu.getIcon());
            meta.setTitle(sysMenu.getName());
            meta.setNoCache(true);
            meta.setRoles(findRoleByMenuId(sysMenu.getId()));
            menuRouter.setMeta(meta);
            List<MenuRouter> nodeChildren = getNodeChildren(sysMenu.getId());
            menuRouter.setChildren(nodeChildren);
            if (SysMenuTypeEnum.DIR.getValue().equals(sysMenu.getType()) && sysMenu.getParentId() == 0) {
                menuRouter.setComponent("Layout");
                menuRouter.setAlwaysShow(true);
                menuRouter.setHidden(false);
                if (CollectionUtils.isNotEmpty(nodeChildren)) {
                    menuRouter.setRedirect(nodeChildren.get(0).getPath());
                }
            } else {
                menuRouter.setComponent(sysMenu.getUri());
            }
            menuRouters.add(menuRouter);
        });
        return menuRouters;
    }

    /**
     * 查询菜单关联的角色id列表
     *
     * @param menuId 菜单id
     * @return java.util.Set<java.lang.Long>
     */
    private Set<Long> findRoleByMenuId(Long menuId) {
        QueryWrapper<RoleMenuRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleMenuRel::getMenuId, menuId);
        List<RoleMenuRel> roleMenuList = roleMenuService.list(queryWrapper);
        return roleMenuList.stream()
                .map(RoleMenuRel::getRoleId)
                .collect(Collectors.toSet());
    }

}
