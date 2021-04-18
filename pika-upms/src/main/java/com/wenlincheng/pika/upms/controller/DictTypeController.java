package com.wenlincheng.pika.upms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.common.core.log.annotation.PikaLog;
import com.wenlincheng.pika.upms.entity.form.dict.DictTypeForm;
import com.wenlincheng.pika.upms.entity.form.role.RoleForm;
import com.wenlincheng.pika.upms.entity.query.dict.DictTypePageQuery;
import com.wenlincheng.pika.upms.entity.query.log.LogPageQuery;
import com.wenlincheng.pika.upms.entity.vo.dict.DictTypeListVO;
import com.wenlincheng.pika.upms.entity.vo.dict.DictTypeVO;
import com.wenlincheng.pika.upms.entity.vo.log.LogListVO;
import com.wenlincheng.pika.upms.entity.vo.role.RoleDetailVO;
import com.wenlincheng.pika.upms.service.DictTypeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wenlincheng.pika.common.core.base.controller.BaseController;

import javax.validation.Valid;

/**
 * <p>
 * 数据字典类型 前端控制器
 * </p>
 *
 * @author Pikaman
 * @since 2021-04-18
 */
@Slf4j
@RestController
@RequestMapping("/dict-type")
public class DictTypeController extends BaseController {

    @Autowired
    private DictTypeService dictTypeService;

    @ApiOperation(value = "数据字典分页", notes = "数据字典分页", httpMethod = "GET")
    @GetMapping(value = "/page")
    public Result<IPage<DictTypeListVO>> queryPage(DictTypePageQuery pageQuery) {
        log.debug("queryPage with pageQuery:{}", pageQuery);
        IPage<DictTypeListVO> page = dictTypeService.queryPageList(pageQuery);
        return Result.success(page);
    }

    @PikaLog(module = "系统管理-数据字典", value = "新增字典类型",  method = "addDictType")
    @ApiOperation(value = "新增字典类型", notes = "新增字典类型", httpMethod = "POST")
    @PostMapping()
    public Result<Boolean> addDictType(@Valid @RequestBody DictTypeForm dictTypeForm) {
        log.debug("addDictType with dictTypeForm:{}", dictTypeForm);
        return Result.success(dictTypeService.addDictType(dictTypeForm));
    }

    @PikaLog(module = "系统管理-数据字典", value = "编辑字典类型", method = "updateDictType")
    @ApiOperation(value = "编辑字典类型", notes = "编辑字典类型", httpMethod = "PUT")
    @PutMapping()
    public Result<Boolean> updateDictType(@Valid @RequestBody DictTypeForm dictTypeForm) {
        log.debug("updateDictType with dictTypeForm:{}", dictTypeForm);
        return Result.success(dictTypeService.updateDictType(dictTypeForm));
    }

    @ApiOperation(value = "查询字典类型", notes = "根据id查询字典类型", httpMethod = "GET")
    @GetMapping(value = "/{id}")
    public Result<DictTypeVO> getRoleDetail(@PathVariable("id") Long id) {
        log.debug("query with id:{}", id);
        return Result.success(dictTypeService.getDictType(id));
    }

    @PikaLog(module = "系统管理-数据字典", value = "根据id删除字典类型", method = "deleteDictType")
    @ApiOperation(value = "删除字典类型", notes = "根据id字典类型", httpMethod = "DELETE")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> deleteDictType(@PathVariable Long id) {
        log.debug("deleteDictType with id:{}", id);
        return Result.success(dictTypeService.deleteDictType(id));
    }

}
