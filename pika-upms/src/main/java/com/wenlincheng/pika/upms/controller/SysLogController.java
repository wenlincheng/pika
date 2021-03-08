package com.wenlincheng.pika.upms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wenlincheng.pika.common.core.base.controller.BaseController;
import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.common.core.enums.YnEnum;
import com.wenlincheng.pika.upms.entity.query.log.LogPageQuery;
import com.wenlincheng.pika.upms.entity.po.SysLog;
import com.wenlincheng.pika.upms.entity.vo.log.LogDetailVO;
import com.wenlincheng.pika.upms.entity.vo.log.LogListVO;
import com.wenlincheng.pika.upms.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * <p>
 * 系统操作日志表 前端控制器
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Api(value = "SysLogController", tags = "操作日志接口")
@RestController
@RequestMapping("/log")
public class SysLogController extends BaseController {

    @Autowired
    private SysLogService sysLogService;

    @ApiOperation(value = "日志分页列表", notes = "根据条件分页搜索日志信息", httpMethod = "GET")
    @GetMapping(value = "/page")
    public Result<IPage<LogListVO>> query(LogPageQuery pageQuery) {
        log.debug("query with pageQuery:{}", pageQuery);
        IPage<LogListVO> page = sysLogService.queryPageList(pageQuery);
        return Result.success(page);
    }

    @ApiOperation(value = "保存日志", notes = "保存日志", httpMethod = "POST")
    @PostMapping
    public Result<Boolean> add(@Valid @RequestBody SysLog sysLog) {
        sysLog.setIsDeleted(YnEnum.NO.getValue());
        Date now = new Date();
        sysLog.setCreateTime(now);
        return Result.success(sysLogService.save(sysLog));
    }

    @ApiOperation(value = "日志详情", notes = "根据id获取日志详情", httpMethod = "GET")
    @GetMapping("/{id}")
    public Result<LogDetailVO> getLogDetail(@PathVariable Long id) {
        return Result.success(sysLogService.getDetailById(id));
    }

}
