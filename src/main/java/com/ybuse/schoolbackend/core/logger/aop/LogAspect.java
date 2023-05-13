package com.ybuse.schoolbackend.core.logger.aop;

import com.ybuse.schoolbackend.core.logger.util.Filter;
import com.ybuse.schoolbackend.core.logger.util.FilterUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 前置增强 打印日志切面
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class LogAspect {

    @Pointcut("@within(com.ybuse.schoolbackend.core.logger.annotation.PrintLog)")
    public void executePoint() {
    }

    @Before("executePoint()")
    public void before(JoinPoint joinPoint) {
        try {
            Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
            // 构造过滤器实例
            Filter filter = FilterUtil.of(joinPoint.getSignature());
            // 过滤参数
            Object[] objects = filter.filterArgs(joinPoint.getArgs());
            String[] tags = filter.getPrintLog().tags();
            logger.info("{}-before-类方法：{}", tags, joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            logger.info("{}-before-请求参数：{}", tags, Arrays.toString(objects));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[LogAspect]-出现了奇怪的错误");
            throw e;
        }
    }
}
