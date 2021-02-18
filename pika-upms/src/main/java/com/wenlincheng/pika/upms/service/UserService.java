package com.wenlincheng.pika.upms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenlincheng.pika.common.core.exception.PikaException;
import com.wenlincheng.pika.upms.entity.form.user.UserForm;
import com.wenlincheng.pika.upms.entity.form.user.UserPasswordForm;
import com.wenlincheng.pika.upms.entity.query.user.UserPageQuery;
import com.wenlincheng.pika.upms.entity.po.User;
import com.wenlincheng.pika.upms.entity.vo.user.UserDetailVO;
import com.wenlincheng.pika.upms.entity.vo.user.UserListVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
public interface UserService extends IService<User> {

    /**
     * 分页查询用户列表
     *
     * @param pageQuery    分页查询参数
     * @return IPage<UserListVO>
     */
    IPage<UserListVO> queryPageList(UserPageQuery pageQuery);

    /**
     * 根据用户id获取用户详情
     * 
     * @param id                用户id
     * @throws PikaException    异常
     * @return UserDetailVO
     */
    UserDetailVO queryUserDetail(Long id) throws PikaException;

    /**
     * 根据用户名获取用户信息
     *
     * @param username          用户名
     * @throws PikaException    异常
     * @return UserDetailVO
     */
    UserDetailVO queryByUsername(String username) throws PikaException;

    /**
     * 添加用户信息
     *
     * @param user 添加参数
     * @return boolean
     */
    boolean addUser(UserForm user);

    /**
     * 更新用户信息
     *
     * @param user 更新参数
     * @return boolean
     */
    boolean updateUser(UserForm user);

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return boolean
     */
    boolean deleteById(Long id);

    /**
     * 重置用户密码
     *
     * @param passwordForm 重置密码参数
     * @return boolean
     */
    boolean resetPassword(UserPasswordForm passwordForm);

    /**
     * 用户修改密码
     *
     * @param passwordForm 修改密码参数
     * @return boolean
     */
    boolean updatePassword(UserPasswordForm passwordForm);
}
