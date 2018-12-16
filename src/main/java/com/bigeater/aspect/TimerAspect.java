package com.bigeater.aspect;

import com.google.common.base.Stopwatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 打日志统计方法执行时间实现
 *
 * @author wangchao
 * @version 创建时间 2017/7/23
 */
@Aspect
@Component
public class TimerAspect {

    private static Logger log = LoggerFactory.getLogger("timer");

    @Pointcut("@annotation(com.bigeater.annotation.Timer)")
    public void timer() {
    }

    @Around("timer()")
    public Object timerAction(ProceedingJoinPoint joinPoint) throws Throwable {
        // 注解参数
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        String canonicalName = joinPoint.getTarget().getClass().getCanonicalName();

        // 计时开始
        Stopwatch stopwatch = Stopwatch.createStarted();

        // 执行代理方法
        Object retVal = joinPoint.proceed();

        // 计时结束
        log.info("{} act=timer method={}#{} exectime={}", System.currentTimeMillis(), canonicalName, methodName, stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
        return retVal;
    }
}
