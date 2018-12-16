package com.bigeater.aspect;

import com.bigeater.annotation.LogParam;
import com.bigeater.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author:J on 2018/12/15.
 */
@Slf4j
@Aspect
@Component
public class LogParamAspect {

    @Pointcut("@annotation(com.bigeater.annotation.LogParam)")
    public void log() {
    }

    /**
     * 前置通知
     */
    @Before("log()")
    public void doBeforeController(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogParam action = method.getAnnotation(LogParam.class);
        String methodName = signature.getMethod().getName();
        String canonicalName = joinPoint.getTarget().getClass().getCanonicalName();
        int[] paramIndex = action.paramIndex();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.currentTimeMillis() + " ");
        stringBuilder.append(String.format("%s#%s ", canonicalName, methodName));
        for (int i = 0; i < paramIndex.length; i++) {
            Object idsObj = joinPoint.getArgs()[paramIndex[i]];
            stringBuilder.append(JsonUtil.toJson(idsObj) + " ");
        }
        log.info(stringBuilder.toString());
    }

    /**
     * 后置通知
     */
    @AfterReturning(pointcut = "log()", returning = "retValue")
    public void doAfterController(JoinPoint joinPoint, Object retValue) {
        log.info("retValue is:" + JsonUtil.serializeObject(retValue));
    }

}
