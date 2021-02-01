package com.wenlincheng.pika.upms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wenlincheng.pika.common.core.base.controller.BaseController;
import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.common.core.exception.BaseException;
import com.wenlincheng.pika.common.core.log.annotation.PikaLog;
import com.wenlincheng.pika.upms.entity.form.user.UserForm;
import com.wenlincheng.pika.upms.entity.form.user.UserPasswordForm;
import com.wenlincheng.pika.upms.entity.query.user.UserPageQuery;
import com.wenlincheng.pika.upms.entity.vo.user.UserDetailVO;
import com.wenlincheng.pika.upms.entity.vo.user.UserListVO;
import com.wenlincheng.pika.upms.exception.UserNotFoundException;
import com.wenlincheng.pika.upms.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "UserController", tags = "用户接口")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户分页列表", notes = "根据条件分页搜索用户信息", httpMethod = "GET")
    @GetMapping(value = "/page")
    public Result<IPage<UserListVO>> query(UserPageQuery pageQuery) {
        log.debug("query with pageQuery:{}", pageQuery);
        IPage<UserListVO> page = userService.queryPageList(pageQuery);

        return Result.success(page);
    }

    @PikaLog("新增用户")
    @ApiOperation(value = "新增用户", notes = "新增一个用户", httpMethod = "POST")
    @PostMapping()
    public Result<Boolean> add(@Valid @RequestBody UserForm userForm) {
        userForm.setCreateUserId(currentUserId());
        log.debug("add with userForm:{}", userForm);
        return Result.success(userService.addUser(userForm));
    }

    @PikaLog("修改用户")
    @ApiOperation(value = "修改用户", notes = "修改指定用户信息", httpMethod = "PUT")
    @PutMapping()
    public Result<Boolean> update(@Valid @RequestBody UserForm userForm) {
        userForm.setUpdateUserId(currentUserId());
        log.debug("update with userForm:{}", userForm);
        return Result.success(userService.updateUser(userForm));
    }

    @PikaLog("删除用户")
    @ApiOperation(value = "删除用户", notes = "根据id删除用户，逻辑删除")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        log.debug("delete with id:{}", id);
        return Result.success(userService.deleteById(id));
    }

    @PikaLog("修改密码")
    @ApiOperation(value = "修改密码", notes = "修改用户登录密码")
    @PutMapping(value = "/password")
    public Result<Boolean> updatePassword(@RequestBody UserPasswordForm passwordForm) {
        log.debug("updatePassword with id:{}", passwordForm);
        passwordForm.setId(currentUserId());
        passwordForm.setUpdateUserId(currentUserId());
        return Result.success(userService.updatePassword(passwordForm));
    }

    @PikaLog("重置密码")
    @ApiOperation(value = "重置密码", notes = "根据id重置用户登录密码")
    @GetMapping(value = "/resetPassword/{id}")
    public Result<Boolean> resetPassword(@PathVariable Long id) {
        log.debug("resetPassword with id:{}", id);
        UserPasswordForm passwordForm = new UserPasswordForm();
        passwordForm.setId(id);
        passwordForm.setUpdateUserId(currentUserId());
        return Result.success(userService.resetPassword(passwordForm));
    }

    @ApiOperation(value = "用户详情", notes = "【我的】页获取用户详情信息")
    @GetMapping(value = "/{id}")
    public Result<UserDetailVO> getUserById(@PathVariable Long id) throws BaseException {
        log.debug("get with id:{}", id);
        UserDetailVO userDetail = userService.queryUserDetail(id);
        if (userDetail == null) {
            throw new UserNotFoundException();
        }
        userDetail.setPassword("");
        return Result.success(userDetail);
    }

    @ApiOperation(value = "用户信息", notes = "【个人中心】获取用户详情信息")
    @GetMapping(value = "/info")
    public Result<UserDetailVO> getUserInfo() {
        Long userId = currentUserId();
        log.debug("get with userId:{}", userId);
        UserDetailVO userDetail = userService.queryUserDetail(userId);
        if (userDetail == null) {
            throw new UserNotFoundException();
        }
        userDetail.setPassword("");
        return Result.success(userDetail);
    }

    @ApiOperation(value = "获取用户信息", notes = "根据用户名获取用户信息")
    @GetMapping(value = "/username/{username}")
    public Result<UserDetailVO> getByUsername(@PathVariable String username) throws BaseException {
        log.debug("get with username:{}", username);
        UserDetailVO userDetail = userService.queryByUsername(username);
        if (userDetail == null) {
            throw new UserNotFoundException();
        }
        return Result.success(userDetail);
    }

}
