package com.bigeater.atom;

import com.bigeater.po.RolePo;
import com.bigeater.po.UserPo;

public interface AtomRoleService {

    RolePo getByPrimaryId(String logStr, Long id);

    RolePo getByRoleName(String logStr, String roleName);

    RolePo insertRole(String logStr, RolePo userPo);
}
