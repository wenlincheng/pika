package com.wenlincheng.pika.upms.controller;


import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.upms.entity.vo.region.AreaListVO;
import com.wenlincheng.pika.upms.entity.vo.region.AreaDetailVO;
import com.wenlincheng.pika.upms.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RestController
@RequestMapping("/area")
@Api(tags = "地址区域接口")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping("/level/{level}")
    @ApiOperation(value = "查询地址区域", notes = "根据地址级别获取地址列表", httpMethod = "GET")
    public Result<List<AreaListVO>> getAreaListByLevel(@PathVariable Integer level) {

        List<AreaListVO> areaList = areaService.getAreaListByLevel(level);

        return Result.success(areaList);
    }

    @GetMapping("/pid/{pid}")
    @ApiOperation(value = "查询下级地址区域", notes = "获取下级别的地址列表", httpMethod = "GET")
    public Result<List<AreaListVO>> getAreaListByParentId(@PathVariable Long pid) {
        List<AreaListVO> areaList = areaService.getAreaListByParentId(pid);

        return Result.success(areaList);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "获取地址详情", notes = "根据地址id获取地址详情", httpMethod = "GET")
    public Result<AreaDetailVO> getAreaDetailById(@PathVariable Long id) {
        AreaDetailVO areaDetail = areaService.getAreaDetailById(id);

        return Result.success(areaDetail);
    }

}
