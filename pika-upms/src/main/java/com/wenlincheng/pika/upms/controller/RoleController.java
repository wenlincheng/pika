package com.wenlincheng.pika.upms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wenlincheng.pika.common.core.base.controller.BaseController;
import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.common.core.enums.DataStatusEnum;
import com.wenlincheng.pika.common.core.enums.YnEnum;
import com.wenlincheng.pika.common.core.log.annotation.PikaLog;
import com.wenlincheng.pika.upms.entity.form.role.RoleForm;
import com.wenlincheng.pika.upms.entity.query.role.RolePageQuery;
import com.wenlincheng.pika.upms.entity.po.Role;
import com.wenlincheng.pika.upms.entity.vo.role.RoleDetailVO;
import com.wenlincheng.pika.upms.entity.vo.role.RoleListVO;
import com.wenlincheng.pika.upms.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@RestController
@RequestMapping("/role")
@Api(value = "RoleController", tags = "角色接口")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "角色分页列表", notes = "分页查询", httpMethod = "GET")
    @GetMapping(value = "/page")
    public Result<IPage<RoleListVO>> queryPage(RolePageQuery pageQuery) {
        log.debug("query with pageQuery:{}", pageQuery);
        IPage<RoleListVO> page = roleService.queryPageList(pageQuery);
        return Result.success(page);
    }

    @ApiOperation(value = "查询用户所有角色列表", notes = "其他服务使用", httpMethod = "GET")
    @GetMapping("/user/{userId}")
    public Result<List<Role>> listByUserId(@PathVariable Long userId){
        log.debug("query with userId:{}", userId);
        List<Role> roles = roleService.listByUserId(userId);
        return Result.success(roles);
    }

    @ApiOperation(value = "查询所有角色列表", notes = "查询所有激活的角色列表", httpMethod = "GET")
    @GetMapping(value = "/list")
    public Result<List<Role>> getAllRoles() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Role::getStatus, DataStatusEnum.ENABLE.getValue());
        List<Role> roleList = roleService.list(queryWrapper);
        return Result.success(roleList);
    }

    @ApiOperation(value = "查询角色详情", notes = "查询角色分配的资源id列表", httpMethod = "GET")
    @GetMapping(value = "/{id}")
    public Result<RoleDetailVO> getRoleDetail(@PathVariable("id") Long id) {
        log.debug("query with id:{}", id);
        RoleDetailVO roleDetailVO = roleService.getRoleDetailById(id);
        return Result.success(roleDetailVO);
    }

    @PikaLog(module = "系统管理-角色管理", value = "删除角色", method = "deleteById")
    @ApiOperation(value = "删除角色", notes = "根据id查询角色", httpMethod = "DELETE")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> deleteById(@PathVariable Long id) {
        log.debug("query with id:{}", id);
        return Result.success(roleService.deleteById(id));
    }

    @PikaLog(module = "系统管理-角色管理", value = "新增角色",  method = "addRole")
    @ApiOperation(value = "新增角色", notes = "新增角色", httpMethod = "POST")
    @PostMapping()
    public Result<Boolean> addRole(@Valid @RequestBody RoleForm roleForm) {
        log.debug("query with roleForm:{}", roleForm);
        return Result.success(roleService.addRole(roleForm));
    }

    @PikaLog(module = "系统管理-角色管理", value = "编辑角色", method = "updateRole")
    @ApiOperation(value = "编辑角色", notes = "编辑角色", httpMethod = "PUT")
    @PutMapping()
    public Result<Boolean> updateRole(@Valid @RequestBody RoleForm roleForm) {
        log.debug("query with roleForm:{}", roleForm);
        return Result.success(roleService.updateRole(roleForm));
    }
}
