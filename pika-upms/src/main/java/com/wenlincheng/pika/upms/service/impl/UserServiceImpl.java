package com.wenlincheng.pika.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenlincheng.pika.common.core.enums.YnEnum;
import com.wenlincheng.pika.common.core.exception.PikaException;
import com.wenlincheng.pika.upms.entity.form.user.UserForm;
import com.wenlincheng.pika.upms.entity.form.user.UserPasswordForm;
import com.wenlincheng.pika.upms.entity.po.SysRole;
import com.wenlincheng.pika.upms.entity.po.User;
import com.wenlincheng.pika.upms.entity.po.UserRoleRelation;
import com.wenlincheng.pika.upms.entity.query.user.UserPageQuery;
import com.wenlincheng.pika.upms.entity.vo.user.UserDetailVO;
import com.wenlincheng.pika.upms.entity.vo.user.UserListVO;
import com.wenlincheng.pika.upms.enums.UpmsErrorCodeEnum;
import com.wenlincheng.pika.upms.exception.UserNotFoundException;
import com.wenlincheng.pika.upms.mapper.UserMapper;
import com.wenlincheng.pika.upms.service.RoleService;
import com.wenlincheng.pika.upms.service.UserRoleRelationService;
import com.wenlincheng.pika.upms.service.UserService;
import com.wenlincheng.pika.upms.util.PasswordEncodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.wenlincheng.pika.upms.constant.UpmsConstants.INITIAL_PASSWORD;
import static com.wenlincheng.pika.upms.enums.UpmsErrorCodeEnum.USER_ADD_ERROR;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserRoleRelationService userRoleRelationService;
    @Autowired
    private RoleService roleService;

    @Override
    public IPage<UserListVO> queryPageList(UserPageQuery pageQuery) {
        QueryWrapper<User> queryWrapper = pageQuery.buildWrapper();
        queryWrapper.lambda().like(StringUtils.isNotBlank(pageQuery.getName()), User::getName, pageQuery.getName())
                .like(StringUtils.isNotBlank(pageQuery.getUsername()), User::getUsername, pageQuery.getUsername())
                .like(StringUtils.isNotBlank(pageQuery.getMobile()), User::getMobile, pageQuery.getMobile())
                .eq(pageQuery.getStatus() != null, User::getStatus, pageQuery.getStatus())
                .eq(User::getIsDeleted, YnEnum.NO.getValue());
        IPage<User> userPage = this.page(pageQuery.getPage(), queryWrapper);
        IPage<UserListVO> listPage = userPage.convert(UserListVO::new);
        for (UserListVO userListVO : listPage.getRecords()) {
            // 查询用户的角色id
            QueryWrapper<UserRoleRelation> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(UserRoleRelation::getUserId, userListVO.getId());
            List<UserRoleRelation> userRoleRelations = userRoleRelationService.list(wrapper);
            Set<Long> roleIds = userRoleRelations.stream().map(UserRoleRelation::getRoleId).collect(Collectors.toSet());
            if (roleIds.size() > 0) {
                List<SysRole> roleList = roleService.listByIds(roleIds);
                Set<String> roleNames = roleList.stream().map(SysRole::getName).collect(Collectors.toSet());
                userListVO.setRoleNames(roleNames);
            }
        }

        return listPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(UserForm userForm) {
        // 校验手机号
        QueryWrapper<User> mobileQueryWrapper = new QueryWrapper<>();
        mobileQueryWrapper.lambda().eq(User::getMobile, userForm.getMobile());
        if (Objects.nonNull(this.getOne(mobileQueryWrapper))) {
            throw PikaException.construct(USER_ADD_ERROR).appendMsg("手机号已经被注册").build();
        }
        // 校验手机号
        QueryWrapper<User> usernameQueryWrapper = new QueryWrapper<>();
        usernameQueryWrapper.lambda().eq(User::getUsername, userForm.getUsername());
        if (Objects.nonNull(this.getOne(usernameQueryWrapper))) {
            throw PikaException.construct(USER_ADD_ERROR).appendMsg("用户名已经被注册").build();
        }
        User user = userForm.toPo(User.class);
        user.setPassword(PasswordEncodeUtil.encode(INITIAL_PASSWORD));
        boolean add = this.save(user);
        // 添加角色
        if (add) {
            for (Long roleId : userForm.getRoleIds()) {
                UserRoleRelation userRole = new UserRoleRelation();
                userRole.setRoleId(roleId)
                        .setUserId(user.getId());
                userRoleRelationService.save(userRole);
            }
        }

        // 发送注册邮件

        return add;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(UserForm userForm) {
        User user = userForm.toPo(User.class);
        // 更新用户信息
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(User::getId, user.getId());
        user.setUpdateTime(new Date());
        boolean update = this.update(user, updateWrapper);

        // 删除旧角色
        QueryWrapper<UserRoleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRoleRelation::getUserId, user.getId());
        userRoleRelationService.remove(queryWrapper);

        // 添加新角色
        for (Long roleId : userForm.getRoleIds()) {
            UserRoleRelation userRole = new UserRoleRelation();
            userRole.setRoleId(roleId)
                    .setUserId(user.getId());
            userRoleRelationService.save(userRole);
        }
        return update;
    }

    @Override
    public boolean deleteById(Long id) {
        return this.removeById(id);
    }

    @Override
    public boolean resetPassword(UserPasswordForm passwordForm) {
        User user = passwordForm.toPo(User.class);
        user.setPassword(PasswordEncodeUtil.encode(INITIAL_PASSWORD))
                .setUpdateTime(new Date());
        return this.updateById(user);
    }

    @Override
    public boolean updatePassword(UserPasswordForm passwordForm) {
        User user = this.getById(passwordForm.getId());
        if (Objects.isNull(user)) {
            throw PikaException.construct(UpmsErrorCodeEnum.USER_NOT_FOUND).build();
        }
        if (!user.getPassword().equals(PasswordEncodeUtil.encode(passwordForm.getOldPassword()))) {
            throw PikaException.construct(UpmsErrorCodeEnum.PASSWORD_ERROR).build();
        }
        user = passwordForm.toPo(User.class);
        user.setPassword(PasswordEncodeUtil.encode(passwordForm.getPassword()));
        return this.updateById(user);
    }

    @Override
    public UserDetailVO queryUserDetail(Long id) throws PikaException {
        User user = this.getById(id);
        if (user == null) {
            throw UserNotFoundException.construct().build();
        }
        UserDetailVO userDetail = new UserDetailVO(user);
        // 查询角色
        QueryWrapper<UserRoleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRoleRelation::getUserId, id);
        List<UserRoleRelation> userRoleRelations = userRoleRelationService.list(queryWrapper);
        Set<Long> roleIds = userRoleRelations.stream()
                .map(UserRoleRelation::getRoleId)
                .collect(Collectors.toSet());
        userDetail.setRoleIds(roleIds);

        return userDetail;
    }

    @Override
    public UserDetailVO queryByUsername(String username) throws PikaException {
        User user = this.getOne(new QueryWrapper<User>().lambda()
                .eq(true, User::getUsername, username));
        if (user == null) {
            throw new UserNotFoundException();
        }

        return new UserDetailVO(user);
    }

}
