package com.wenlincheng.pika.upms.controller;


import com.wenlincheng.pika.common.core.base.controller.BaseController;
import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.common.core.log.annotation.PikaLog;
import com.wenlincheng.pika.upms.entity.form.menu.MenuForm;
import com.wenlincheng.pika.upms.entity.po.Menu;
import com.wenlincheng.pika.upms.entity.vo.menu.MenuListVO;
import com.wenlincheng.pika.upms.entity.vo.menu.MenuRouter;
import com.wenlincheng.pika.upms.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 菜单 前端控制器
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Api(value = "MenuController", tags = "菜单接口")
@Slf4j
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "根据父id查询菜单列表", notes = "根据父id查询菜单列表", httpMethod = "GET")
    @GetMapping(value = "/list/{parentId}")
    public Result<List<MenuListVO>> queryListByParentId(@PathVariable Long parentId) {
        log.debug("query list by parentId: {}", parentId);
        List<MenuListVO> menuList = menuService.queryListByParentId(parentId);
        return Result.success(menuList);
    }

    @ApiOperation(value = "菜单树列表", notes = "菜单列表", httpMethod = "GET")
    @GetMapping(value = "/list")
    public Result<List<MenuListVO>> queryList() {
        log.debug("query list");
        List<MenuListVO> menuList = menuService.queryList();
        return Result.success(menuList);
    }

    @ApiOperation(value = "查询用户的菜单列表", notes = "根据用户id查询用户菜单列表", httpMethod = "GET")
    @GetMapping(value = "/list/user/{userId}")
    public Result<List<MenuListVO>> queryListByUserId(@PathVariable Long userId) {
        log.debug("query with userId:{}", userId);
        return Result.success(menuService.queryListByUserId(userId));
    }

    @ApiOperation(value = "查询菜单", notes = "根据id查询菜单", httpMethod = "GET")
    @GetMapping(value = "/{id}")
    public Result<Menu> queryById(@PathVariable Long id) {
        log.debug("query with id:{}", id);
        Menu sysMenu = menuService.queryById(id);
        return Result.success(sysMenu);
    }

    @PikaLog(value = "删除菜单")
    @ApiOperation(value = "删除菜单", notes = "根据id删除菜单", httpMethod = "DELETE")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> deleteById(@PathVariable Long id) {
        log.debug("query with id:{}", id);
        return Result.success(menuService.deleteById(id));
    }

    @PikaLog(value = "新增菜单")
    @ApiOperation(value = "新增菜单", notes = "新增菜单", httpMethod = "POST")
    @PostMapping()
    public Result<Boolean> addMenu(@Valid @RequestBody MenuForm menuForm) {
        log.debug("query with menuForm:{}", menuForm);
        return Result.success(menuService.addMenu(menuForm));
    }

    @PikaLog(value = "编辑菜单")
    @ApiOperation(value = "编辑菜单", notes = "编辑菜单", httpMethod = "PUT")
    @PutMapping()
    public Result<Boolean> updateMenu(@Valid @RequestBody MenuForm menuForm) {
        log.debug("query with menuForm:{}", menuForm);
        return Result.success(menuService.updateMenu(menuForm));
    }

    @ApiOperation(value = "根据用户id查询菜单权限列表", notes = "查询用户的菜单权限列表", httpMethod = "GET")
    @GetMapping(value = "/perms/user/{userId}")
    public Result<List<Menu>> queryPermsByUserId(@PathVariable Long userId) {
        log.debug("query with userId:{}", userId);
        return Result.success(menuService.queryPermsByUserId(userId));
    }

    @ApiOperation(value = "查询所有菜单权限列表", notes = "查询所有菜单权限列表", httpMethod = "GET")
    @GetMapping(value = "/perms/list")
    public Result<List<Menu>> queryAllPerms() {
        log.debug("query list");
        List<Menu> resourceList = menuService.queryAllPerms();
        return Result.success(resourceList);
    }

    @ApiOperation(value = "根据角色id查询菜单权限列表", notes = "查询角色的菜单权限列表", httpMethod = "GET")
    @GetMapping(value = "/perms/role/{roleId}")
    public Result<List<Menu>> queryPermsByRoleId(@PathVariable("roleId") Long roleId) {
        log.debug("query with roleId:{}", roleId);
        return Result.success(menuService.queryPermsByRoleId(roleId));
    }

    @ApiOperation(value = "动态菜单列表", notes = "后台管理页左侧动态展示用", httpMethod = "GET")
    @GetMapping(value = "/router")
    public Result<List<MenuRouter>> queryMenuNodeList() {
        log.debug("query menu node list");
        return Result.success(menuService.queryMenuNodeList());
    }
}