package com.bigeater.service;

import com.bigeater.po.UserPo;

import java.util.Optional;

/**
 * @author:J on 2018/12/16.
 */
public interface UserService {

    UserPo addUser(String logStr, UserPo userPo);

    UserPo getUserByUsername(String logStr, String username);

}
