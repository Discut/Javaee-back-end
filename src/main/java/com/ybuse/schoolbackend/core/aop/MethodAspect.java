package com.ybuse.schoolbackend.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(1)
public class MethodAspect {
    @Pointcut("@annotation(com.ybuse.schoolbackend.core.annotation.aop.LogMethod)")
    public void executePoint() {
    }

    @Before("executePoint()")
    public void before(JoinPoint joinPoint) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        logger.info("before-类方法：{}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("before-请求参数：{}", Arrays.toString(joinPoint.getArgs()));
    }
}
