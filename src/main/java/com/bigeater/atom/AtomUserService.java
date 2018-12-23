package com.bigeater.atom;

import com.bigeater.po.UserPo;

import java.util.Optional;

public interface AtomUserService {

    UserPo getByPrimaryId(String logStr, Long id);

    UserPo getByUsername(String logStr, String username);

    UserPo insertUser(String logStr, UserPo userPo);

}
