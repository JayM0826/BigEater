package com.bigeater.service.impl;

import com.bigeater.annotation.LogParam;
import com.bigeater.annotation.PreDb;
import com.bigeater.atom.AtomUserService;
import com.bigeater.po.UserPo;
import com.bigeater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author:J on 2018/12/16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AtomUserService atomUserService;

    @Override
    @PreDb(ctime = true, id = true, paramIndex = 1)
    public UserPo addUser(String logStr, UserPo userPo) {
        return atomUserService.insertUser(logStr, userPo);
    }

    @Override
    @LogParam(paramIndex = {0, 1})
    public UserPo getUserByUsername(String logStr, String username) {
        return atomUserService.getByUsername(logStr, username);
    }
}
