package com.wenlincheng.pika.item.aspect;

import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 解决Sentinel服务降级造成分布式事务不生效问题
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/2/7 10:14 下午
 */
@Slf4j
@Aspect
@Component
public class TransactionAspect {

    @Before("execution(* com.wenlincheng.pika.item.service.*.*(..))")
    public void before(JoinPoint joinPoint) throws TransactionException {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        log.info("拦截到需要分布式事务的方法:{}", method.getName());
        // 可用Redis或者定时任务获取一个Key判断是否需要关闭分布式事务
        // GlobalTransaction tx = GlobalTransactionContext.getCurrentOrCreate();
        // tx.begin();
    }

    @AfterThrowing(throwing = "e", pointcut = "execution(* com.wenlincheng.pika.item.service.*.*(..))")
    public void afterThrowing(Throwable e) throws TransactionException {
        log.info("方法执行异常:{}", e.getMessage());
        if (StringUtils.isNotBlank(RootContext.getXID())) {
            GlobalTransactionContext.reload(RootContext.getXID()).rollback();
        }
    }

    @AfterReturning(returning = "result", pointcut = "execution(* com.wenlincheng.pika.item.service.*.*(..))")
    public void afterReturning(JoinPoint joinPoint, Object result) throws TransactionException {
        log.info("方法执行结束:{}", result);
        if (StringUtils.isNotBlank(RootContext.getXID())) {
            log.info("方法执行结束， 分布式事务id:{}", RootContext.getXID());
            // GlobalTransactionContext.reload(RootContext.getXID()).commit();
        }
    }



}
