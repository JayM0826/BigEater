package com.bigeater.aspect;

import com.bigeater.annotation.CheckParam;
import com.bigeater.controller.AccountController;
import com.bigeater.dto.UserDto;
import com.bigeater.exception.IllegalException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author:J on 2018/12/15.
 */

@Slf4j
@Aspect
@Component
public class CheckParamAspect {
    @Pointcut("@annotation(com.bigeater.annotation.CheckParam)")
    public void check() {}

    /**
     * 前置通知
     */
    @Before("check()")
    public void doBeforeController(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        CheckParam action = method.getAnnotation(CheckParam.class);
        String methodName = signature.getMethod().getName();
        String canonicalName = joinPoint.getTarget().getClass().getCanonicalName();
        int[] paramIndex = action.paramIndex();
        Class<?>[] classes = action.checkClass();

        for (int i = 0; i < paramIndex.length; i++) {
            Object idsObj = joinPoint.getArgs()[paramIndex[i]];
            Class<?> clazz = classes[i];
            // TODO 后面做成注册形式的或者map去维护
            if (clazz.isAssignableFrom(UserDto.class)) {
                idsObj = (UserDto)idsObj;
                if (AccountController.USERS.containsKey(((UserDto) idsObj).getUsername())) {
                    throw new IllegalException(-1, String.format("用户名:%s 已经被使用", ((UserDto) idsObj).getUsername()), HttpStatus.OK);
                }
            }
        }
    }

}
