package com.wenlincheng.pika.upms.controller;


import com.wenlincheng.pika.common.core.base.controller.BaseController;
import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.upms.entity.po.Organization;
import com.wenlincheng.pika.upms.entity.form.organization.OrganizationForm;
import com.wenlincheng.pika.upms.entity.query.organization.OrganizationPageQuery;
import com.wenlincheng.pika.upms.service.OrganizationService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 组织架构 前端控制器
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Api(value = "OrganizationController", tags = "组织架构")
@Slf4j
@RestController
@RequestMapping("/group")
public class OrganizationController extends BaseController {

    @Autowired
    private OrganizationService organizationService;

    @ApiOperation(value = "查询组织架构列表", notes = "根据条件查询组织架构列表")
    @PostMapping("/page")
    public Result<List<Organization>> queryPageList(@Valid @RequestBody OrganizationPageQuery pageQuery) {
        log.debug("search with queryPageList:{}", pageQuery);
        return Result.success(organizationService.getPageList(pageQuery));
    }

    @ApiOperation(value = "根据父级id查询组织架构", notes = "根据父级id查询组织架构列表")
    @GetMapping("/parent/{id}")
    public Result<List<Organization>> queryByParentId(@PathVariable Long id) {
        log.debug("query with parent id:{}", id);
        return Result.success(organizationService.getByParentId(id));
    }

    @ApiOperation(value = "新增组织架构", notes = "新增组织架构")
    @PostMapping
    public Result<Boolean> addGroup(@Valid @RequestBody OrganizationForm groupForm) {
        log.debug("name:{}", groupForm);
        return Result.success(organizationService.add(groupForm.toPo(Organization.class)));
    }

    @ApiOperation(value = "删除组织架构", notes = "根据id删除对象")
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@PathVariable Long id) {
        return Result.success(organizationService.deleteById(id));
    }

    @ApiOperation(value = "修改组织架构", notes = "修改指定组织架构")
    @PutMapping("/{id}")
    public Result<Boolean> updateGroup(@PathVariable Long id, @Valid @RequestBody OrganizationForm groupForm) {
        Organization group = groupForm.toPo(Organization.class);
        group.setId(id);
        return Result.success(organizationService.updateGroup(group));
    }

    @ApiOperation(value = "查询组织架构", notes = "根据id获取指定组织架构")
    @GetMapping("/{id}")
    public Result<Organization> queryById(@PathVariable Long id) {
        log.debug("get with id:{}", id);
        return Result.success(organizationService.queryById(id));
    }
}
