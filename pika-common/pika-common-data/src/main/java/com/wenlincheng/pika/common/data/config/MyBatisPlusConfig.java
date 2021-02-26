package com.wenlincheng.pika.common.data.config;

import com.baomidou.mybatisplus.extension.injector.methods.LogicDeleteByIdWithFill;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisPlus 配置
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Configuration
public class MyBatisPlusConfig {

    /**
     * 配置分页插件
     *
     * @return cPaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        log.debug("注册分页插件");
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }

    /**
     * 配置逻辑删除并且自动填充功能生效
     *
     * @return LogicDeleteByIdWithFill
     */
    @Bean
    public LogicDeleteByIdWithFill sqlInjector() {
        return new LogicDeleteByIdWithFill();
    }
    /**
     * 乐观锁插件
     *
     * @return OptimisticLockerInterceptor
     */
    @Bean
    public OptimisticLockerInterceptor versionInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
