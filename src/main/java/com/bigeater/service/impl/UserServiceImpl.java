package com.bigeater.service.impl;

import com.bigeater.annotation.PreDb;
import com.bigeater.dao.UserPoMapper;
import com.bigeater.po.UserPo;
import com.bigeater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:J on 2018/12/16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserPoMapper mapper;

    @Override
    @PreDb(ctime = true, id = true)
    public UserPo addUser(UserPo userPo) {
        mapper.insert(userPo);
        return userPo;
    }
}
