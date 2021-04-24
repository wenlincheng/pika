package com.wenlincheng.pika.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenlincheng.pika.common.core.enums.DataStatusEnum;
import com.wenlincheng.pika.common.core.enums.YnEnum;
import com.wenlincheng.pika.common.core.exception.PikaException;
import com.wenlincheng.pika.common.core.redis.RedisUtils;
import com.wenlincheng.pika.upms.entity.form.role.RoleForm;
import com.wenlincheng.pika.upms.entity.query.role.RolePageQuery;
import com.wenlincheng.pika.upms.entity.po.RoleMenuRel;
import com.wenlincheng.pika.upms.entity.po.Role;
import com.wenlincheng.pika.upms.entity.po.UserRoleRel;
import com.wenlincheng.pika.upms.entity.vo.role.RoleDetailVO;
import com.wenlincheng.pika.upms.entity.vo.role.RoleListVO;
import com.wenlincheng.pika.upms.mapper.RoleMapper;
import com.wenlincheng.pika.upms.service.RoleMenuRelService;
import com.wenlincheng.pika.upms.service.RoleService;
import com.wenlincheng.pika.upms.service.UserRoleRelService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.wenlincheng.pika.common.core.constant.SecurityConstants.USER_PERMISSIONS_REDIS_KEY;
import static com.wenlincheng.pika.upms.enums.UpmsErrorCodeEnum.ROLE_REL_USER_DELETE_ERROR;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    UserRoleRelService userRoleService;
    @Autowired
    RoleMenuRelService roleMenuService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public List<Role> listByUserId(Long userId) {
        List<Role> roleList = new ArrayList<>();
        Set<Long> roleIds = userRoleService.queryByUserId(userId);
        if (roleIds.size() > 0) {
            QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().in(Role::getId, roleIds)
                    .eq(Role::getStatus, DataStatusEnum.ENABLE.getValue());
            roleList = this.list(queryWrapper);
        }

        return roleList;
    }

    @Override
    public IPage<RoleListVO> queryPageList(RolePageQuery pageQuery) {
        QueryWrapper<Role> queryWrapper = pageQuery.buildWrapper();
        queryWrapper.lambda().like(StringUtils.isNotBlank(pageQuery.getName()), Role::getName,pageQuery.getName())
                .like(StringUtils.isNotBlank(pageQuery.getCode()), Role::getCode,pageQuery.getCode())
                .eq(pageQuery.getStatus() != null, Role::getStatus,pageQuery.getStatus());

        IPage<Role> rolePage = this.page(pageQuery.getPage(), queryWrapper);
        IPage<RoleListVO> roleListVOIPage = rolePage.convert(RoleListVO::new);
        // 统计角色关联的用户数
        for (RoleListVO roleListVO : roleListVOIPage.getRecords()) {
            QueryWrapper<UserRoleRel> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(UserRoleRel::getRoleId, roleListVO.getId());
            int count = userRoleService.count(wrapper);
            roleListVO.setRelateUserCount(count);
        }

        return roleListVOIPage;
    }

    @Override
    public RoleDetailVO getRoleDetailById(Long id) {
        Role role = this.getById(id);
        RoleDetailVO roleDetailVO = new RoleDetailVO(role);
        QueryWrapper<RoleMenuRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleMenuRel::getRoleId, id);
        List<RoleMenuRel> roleResourceRelationList = roleMenuService.list(queryWrapper);
        List<Long> resourceIds = roleResourceRelationList.stream().map(RoleMenuRel::getMenuId).collect(Collectors.toList());
        roleDetailVO.setMenuIdList(resourceIds);

        return roleDetailVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addRole(RoleForm roleForm) {
        Role role = roleForm.toPo(Role.class);
        boolean save = this.save(role);
        if (save) {
            saveRoleMenuRelation(roleForm);
        }
        return save;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteById(Long id) {
        QueryWrapper<UserRoleRel> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.lambda().eq(UserRoleRel::getRoleId, id);
        List<UserRoleRel> userRoleList = userRoleService.list(userRoleQueryWrapper);
        if (CollectionUtils.isNotEmpty(userRoleList)) {
            throw PikaException.construct(ROLE_REL_USER_DELETE_ERROR).build();
        }
        boolean remove = this.removeById(id);
        if (remove) {
            QueryWrapper<RoleMenuRel> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(RoleMenuRel::getRoleId, id);
            roleMenuService.remove(queryWrapper);
        }
        return remove;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRole(RoleForm roleForm) {
        QueryWrapper<RoleMenuRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleMenuRel::getRoleId, roleForm.getId());
        roleMenuService.remove(queryWrapper);
        boolean update = this.updateById(roleForm.toPo(Role.class));
        if (update) {
            saveRoleMenuRelation(roleForm);
            clearRolePermissionsCache(roleForm.getId());
        }
        return update;
    }

    /**
     * 保存角色菜单关联关系
     *
     * @param roleForm 角色
     * @return void
     */
    private void saveRoleMenuRelation(RoleForm roleForm) {
        for (Long menuId : roleForm.getMenuIdList()) {
            RoleMenuRel roleMenu = new RoleMenuRel();
            roleMenu.setMenuId(menuId)
                    .setRoleId(roleForm.getId());
            roleMenuService.save(roleMenu);
        }
    }

    /**
     * 清除拥有该角色用户的权限缓存
     *
     * @param roleId 角色id
     * @return void
     */
    private void clearRolePermissionsCache(Long roleId) {
        QueryWrapper<UserRoleRel> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.lambda().eq(UserRoleRel::getRoleId, roleId);
        List<UserRoleRel> userRoleList = userRoleService.list(userRoleQueryWrapper);
        for (UserRoleRel userRole : userRoleList) {
            redisUtils.delete(USER_PERMISSIONS_REDIS_KEY + userRole.getUserId());
        }
    }

}
