package com.wenlincheng.pika.upms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wenlincheng.pika.common.core.base.controller.BaseController;
import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.common.core.log.annotation.PikaLog;
import com.wenlincheng.pika.upms.entity.form.gateway.GatewayRouteForm;
import com.wenlincheng.pika.upms.entity.query.gateway.GatewayRoutePageQuery;
import com.wenlincheng.pika.upms.entity.po.GatewayRoute;
import com.wenlincheng.pika.upms.entity.vo.gateway.GatewayRouteVO;
import com.wenlincheng.pika.upms.service.GatewayRouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 网关路由表 前端控制器
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@RestController
@RequestMapping("/gateway")
@Slf4j
@Api(value = "GatewayRouteController", tags = "网关路由接口")
public class GatewayRouteController extends BaseController {

    @Autowired
    private GatewayRouteService gatewayRoutService;

    @PikaLog(value = "新增路由")
    @ApiOperation(value = "新增路由", notes = "新增一个网关路由")
    @PostMapping()
    public Result<Boolean> add(@Valid @RequestBody GatewayRouteForm gatewayRoutForm) {
        log.info("name: {}", gatewayRoutForm);
        GatewayRoute gatewayRout = gatewayRoutForm.toPo(GatewayRoute.class);
        return Result.success(gatewayRoutService.add(gatewayRout));
    }

    @PikaLog(value = "删除路由")
    @ApiOperation(value = "删除路由", notes = "根据url的id来指定删除对象")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> delete(@PathVariable String id) {
        log.debug("delete with id:{}", id);
        return Result.success(gatewayRoutService.delete(id));
    }

    @PikaLog(value = "修改路由")
    @ApiOperation(value = "修改路由", notes = "修改指定网关路由信息")
    @PutMapping()
    public Result<Boolean> update(@Valid @RequestBody GatewayRouteForm gatewayRoutForm) {
        log.debug("update form:{}", gatewayRoutForm);
        GatewayRoute gatewayRout = gatewayRoutForm.toPo(GatewayRoute.class);
        return Result.success(gatewayRoutService.update(gatewayRout));
    }

    @ApiOperation(value = "查询路由", notes = "根据id获取指定网关路由信息")
    @GetMapping(value = "/{id}")
    public Result<GatewayRouteVO> query(@PathVariable Long id) {
        log.info("query with id:{}", id);
        return Result.success(new GatewayRouteVO(gatewayRoutService.get(id)));
    }

    @ApiOperation(value = "路由分页列表", notes = "根据条件查询网关路由信息")
    @GetMapping(value = "/page")
    public Result<IPage<GatewayRouteVO>> page(GatewayRoutePageQuery pageQuery) {
        log.debug("query form:{}", pageQuery);
        IPage<GatewayRouteVO> pageList = gatewayRoutService.pageList(pageQuery);
        return Result.success(pageList);
    }

    @PikaLog(value = "刷新路由")
    @ApiOperation(value = "刷新路由", notes = "将所以网关路由重新加载到缓存中")
    @PostMapping(value = "/refresh")
    public Result<Boolean> refresh() {
        return Result.success(gatewayRoutService.refresh());
    }

}
