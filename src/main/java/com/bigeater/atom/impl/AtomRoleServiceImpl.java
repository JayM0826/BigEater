package com.bigeater.atom.impl;

import com.bigeater.annotation.CheckParam;
import com.bigeater.annotation.LogParam;
import com.bigeater.atom.AtomRoleService;
import com.bigeater.dao.RolePoMapper;
import com.bigeater.po.RolePo;
import com.bigeater.po.RolePoExample;
import com.bigeater.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * 描述:
 *
 * @author J
 * @create 2018-12-23 21:49
 */
public class AtomRoleServiceImpl implements AtomRoleService {


    @Autowired
    private RolePoMapper roleMapper;

    @Override
    @LogParam(paramIndex = {0, 1})
    public RolePo getByPrimaryId(String logStr, Long id) {
        return null;
    }

    @Override
    @LogParam(paramIndex = {0, 1})
    @CheckParam(paramIndex = {1}, checkClass = {String.class})
    public RolePo getByRoleName(String logStr, String roleName) {

        RolePoExample example = new RolePoExample();
        example.createCriteria().andNameEqualTo(roleName);
        List<RolePo> rolePoList = roleMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(rolePoList)) {
            return null;
        }

        return rolePoList.get(0);
    }

    @Override
    @LogParam(paramIndex = {0, 1})
    public RolePo insertRole(String logStr, RolePo rolePo) {
        if (rolePo == null) {
            return null;
        }

        rolePo.setId(IdUtil.instance.nextId());
        Long now = System.currentTimeMillis();
        rolePo.setCtime(now);
        rolePo.setCtime(now);
        int result = roleMapper.insertSelective(rolePo);

        if (result > 0) {
            return rolePo;
        }

        return null;
    }
}
