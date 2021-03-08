package com.wenlincheng.pika.upms.controller;


import com.wenlincheng.pika.common.core.base.controller.BaseController;
import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.upms.entity.vo.region.RegionListVO;
import com.wenlincheng.pika.upms.entity.vo.region.RegionDetailVO;
import com.wenlincheng.pika.upms.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 地址区域表 前端控制器
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@RestController
@RequestMapping("/region")
@Api(tags = "区域接口")
public class RegionController extends BaseController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/level/{level}")
    @ApiOperation(value = "查询区域列表", notes = "根据地址级别获取地址列表", httpMethod = "GET")
    public Result<List<RegionListVO>> getAreaListByLevel(@PathVariable Integer level) {
        log.debug("query with level:{}", level);
        List<RegionListVO> regionList = regionService.getAreaListByLevel(level);
        return Result.success(regionList);
    }

    @GetMapping("/pid/{parentId}")
    @ApiOperation(value = "查询下级区域", notes = "获取下级别的地址列表", httpMethod = "GET")
    public Result<List<RegionListVO>> getAreaListByParentId(@PathVariable Long parentId) {
        log.debug("query with parentId:{}", parentId);
        List<RegionListVO> regionList = regionService.getAreaListByParentId(parentId);
        return Result.success(regionList);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "获取区域详情", notes = "根据地址id获取地址详情", httpMethod = "GET")
    public Result<RegionDetailVO> getAreaDetailById(@PathVariable Long id) {
        log.debug("query with id:{}", id);
        RegionDetailVO areaDetail = regionService.getAreaDetailById(id);
        return Result.success(areaDetail);
    }

}
