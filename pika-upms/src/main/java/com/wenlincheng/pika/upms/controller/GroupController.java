package com.wenlincheng.pika.upms.controller;


import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.upms.entity.po.SysGroup;
import com.wenlincheng.pika.upms.entity.form.group.GroupForm;
import com.wenlincheng.pika.upms.entity.query.group.GroupPageQuery;
import com.wenlincheng.pika.upms.service.GroupService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 用户组 前端控制器
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Api(value = "GroupController", tags = "组织架构")
@Slf4j
@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @ApiOperation(value = "查询用户组列表", notes = "根据条件查询用户组列表")
    @PostMapping("/page")
    public Result<List<SysGroup>> queryPageList(@Valid @RequestBody GroupPageQuery pageQuery) {
        log.debug("search with queryPageList:{}", pageQuery);
        return Result.success(groupService.getPageList(pageQuery));
    }

    @ApiOperation(value = "根据父级id查询用户组", notes = "根据父级id查询用户组列表")
    @GetMapping("/parent/{id}")
    public Result<List<SysGroup>> queryByParentId(@PathVariable Long id) {
        log.debug("query with parent id:{}", id);
        return Result.success(groupService.getByParentId(id));
    }

    @ApiOperation(value = "新增用户组", notes = "新增用户组")
    @PostMapping
    public Result<Boolean> addGroup(@Valid @RequestBody GroupForm groupForm) {
        log.debug("name:{}", groupForm);
        return Result.success(groupService.add(groupForm.toPo(SysGroup.class)));
    }

    @ApiOperation(value = "删除用户组", notes = "根据id删除对象")
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@PathVariable Long id) {
        return Result.success(groupService.deleteById(id));
    }

    @ApiOperation(value = "修改用户组", notes = "修改指定用户组")
    @PutMapping("/{id}")
    public Result<Boolean> updateGroup(@PathVariable Long id, @Valid @RequestBody GroupForm groupForm) {
        SysGroup group = groupForm.toPo(SysGroup.class);
        group.setId(id);
        return Result.success(groupService.updateGroup(group));
    }

    @ApiOperation(value = "查询用户组", notes = "根据id获取指定用户组")
    @GetMapping("/{id}")
    public Result<SysGroup> queryById(@PathVariable Long id) {
        log.debug("get with id:{}", id);
        return Result.success(groupService.queryById(id));
    }
}
