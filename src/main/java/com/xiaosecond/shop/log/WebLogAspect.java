package com.xiaosecond.shop.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import java.util.Arrays;


@Slf4j
@Aspect
@Component
public class WebLogAspect {

    /**
     * 切入点
     */
    @Pointcut("execution(public * com.xiaosecond.shop.controller..*.*(..))")
    public void controllerLog(){}


    /**
     * 前置增强 在切入点的方法run之前要干的
     * @param joinPoint
     */
    @Before("controllerLog()")
    public void logBeforeController(JoinPoint joinPoint) {
        //下面这个getSignature().getDeclaringTypeName()是获取包+类名的   然后后面的joinPoint.getSignature.getName()获取了方法名
        log.info("################URL : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        //参数
        log.info("################PARAM : " + Arrays.toString(joinPoint.getArgs()));
    }

}
