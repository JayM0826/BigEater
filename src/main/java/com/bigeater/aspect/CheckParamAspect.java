package com.bigeater.aspect;

import com.bigeater.annotation.CheckParam;
import com.bigeater.atom.AtomUserService;
import com.bigeater.controller.AccountController;
import com.bigeater.dao.UserPoMapper;
import com.bigeater.dto.UserDto;
import com.bigeater.exception.IllegalException;
import com.bigeater.po.UserPo;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;

@Slf4j
@Aspect
@Component
public class CheckParamAspect {

    @Autowired
    private AtomUserService atomUserService;

    @Pointcut("@annotation(com.bigeater.annotation.CheckParam)")
    public void check() {}

    @Before("check()")
    public void doBeforeController(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        CheckParam action = method.getAnnotation(CheckParam.class);
        int[] paramIndex = action.paramIndex();
        Class<?>[] classes = action.checkClass();

        for (int i = 0; i < paramIndex.length; i++) {
            Object idsObj = joinPoint.getArgs()[paramIndex[i]];
            Class<?> clazz = classes[i];
            // TODO 后面做成注册形式的或者map去维护
            if (clazz.isAssignableFrom(UserDto.class)) {
                UserPo userPo = atomUserService.getByUsername("CheckParamAspect", ((UserDto) idsObj).getUsername());
                if (userPo != null) {
                    throw new IllegalException(-1, String.format("用户名: %s 已经被使用", ((UserDto) idsObj).getUsername()), HttpStatus.OK);
                }
                continue;
            }

            if (clazz.isAssignableFrom(String.class)) {
                if (Strings.isNullOrEmpty((String) idsObj)) {
                    throw new IllegalException(-1, "字符串不包含任何信息", HttpStatus.OK);
                }
                continue;
            }
        }
    }

}
