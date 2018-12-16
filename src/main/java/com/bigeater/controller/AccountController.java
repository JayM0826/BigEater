package com.bigeater.controller;

import com.bigeater.annotation.CheckParam;
import com.bigeater.annotation.LogParam;
import com.bigeater.annotation.Timer;
import com.bigeater.dto.UserDto;
import com.bigeater.po.UserPo;
import com.bigeater.service.UserService;
import com.bigeater.vo.BigEaterResult;
import com.bigeater.vo.UserVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author:J on 2018/12/15.
 */
@RestController
@RequestMapping("/account")
@ResponseBody
@Slf4j
public class AccountController {

    @Autowired
    private UserService userService;

    public static final Map<String, UserPo> USERS = new ConcurrentHashMap();

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "注册账户", notes = "localhost:8080/account/register")
    @LogParam(paramIndex = {0})
    @CheckParam(paramIndex = {0}, checkClass = {UserDto.class})
    @Timer
    public BigEaterResult<UserVo> register(@RequestBody UserDto user) {
        UserPo userPo = new UserPo();
        BeanUtils.copyProperties(user, userPo);
        userPo = userService.addUser(userPo);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userPo, userVo);
        return BigEaterResult.of(userVo);
    }
}
