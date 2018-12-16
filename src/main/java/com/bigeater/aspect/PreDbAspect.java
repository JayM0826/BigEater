package com.bigeater.aspect;

import com.bigeater.annotation.PreDb;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author:J on 2018/12/16.
 */
@Slf4j
@Aspect
@Component
public class PreDbAspect {

    @Pointcut("@annotation(com.bigeater.annotation.PreDb)")
    public void time() {
    }

    /**
     * 前置通知
     */
    @Before("time()")
    public void doBeforeController(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        PreDb action = method.getAnnotation(PreDb.class);
        int paramIndex = action.paramIndex();
        Object obj = joinPoint.getArgs()[paramIndex];
        Long now = System.currentTimeMillis();

        if (action.mtime()) {
            Field mtime = obj.getClass().getDeclaredField("mtime");
            mtime.setAccessible(true);
            mtime.set(obj, Long.valueOf(now));
        }

        if (action.mtime()) {
            Field ctime = obj.getClass().getDeclaredField("ctime");
            ctime.setAccessible(true);
            ctime.set(obj, Long.valueOf(now));
        }
    }


}
