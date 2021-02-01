package com.wenlincheng.pika.common.core.log.feign.fallback;

import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.common.core.log.feign.SysLogService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 服务降级处理类
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Component
public class SysLogServiceFallbackFactory implements FallbackFactory<SysLogService> {
    @Override
    public SysLogService create(Throwable throwable) {

        return sysLog -> {
            log.error("添加日志失败:{}", throwable.getMessage());
            return Result.fail();
        };
    }
}
