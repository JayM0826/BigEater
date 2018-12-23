package com.bigeater.controller;

import com.bigeater.annotation.CheckParam;
import com.bigeater.annotation.LogParam;
import com.bigeater.annotation.Timer;
import com.bigeater.dto.UserDto;
import com.bigeater.po.UserPo;
import com.bigeater.service.UserService;
import com.bigeater.vo.BigEaterResult;
import com.bigeater.vo.UserVo;
import com.google.common.base.Strings;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "注册账户", notes = "localhost:8080/account/register")
    @LogParam(paramIndex = {0})
    @CheckParam(paramIndex = {0}, checkClass = {UserDto.class})
    @Timer
    public BigEaterResult<UserVo> register(@RequestBody UserDto user) {
        // 注册的时候传过来的密码就是md5进行加密的了
        UserPo userPo = new UserPo();
        BeanUtils.copyProperties(user, userPo);
        userPo.setPassword(passwordEncoder.encode(user.getPassword()));

        userPo  = userService.addUser("AccountController#register", userPo);
        if (userPo != null) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(userPo, userVo);
            return BigEaterResult.of(userVo);
        }
        return BigEaterResult.ofError(-1, "register fail");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录", notes = "localhost:8080/account/login")
    @LogParam(paramIndex = {0})
    @Timer
    public BigEaterResult login(@RequestBody UserDto user) {

        String username = user.getUsername();
        if (Strings.isNullOrEmpty(username)) {
            return BigEaterResult.ofError(404, "用户名不存在");
        }

        UserPo userPo = userService.getUserByUsername("AccountController#login", username);
        if (userPo == null) {
            return BigEaterResult.ofError(404, "用户名不存在");
        }

        return BigEaterResult.of(null);
    }

}
