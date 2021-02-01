package com.wenlincheng.pika.common.core.log.feign;

import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.common.core.log.feign.dto.SysLog;
import com.wenlincheng.pika.common.core.log.feign.fallback.SysLogServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 系统日志API
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@FeignClient(contextId = "sysLogService", value = "pika-upms", fallbackFactory = SysLogServiceFallbackFactory.class)
public interface SysLogService {

    /**
     * 保存
     *
     * @param sysLog 日志
     * @return Result<Boolean>
     */
    @PostMapping("/log")
    Result<Boolean> saveLog(@RequestBody SysLog sysLog);

}

