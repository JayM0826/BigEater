package com.bigeater.atom.impl;

import com.bigeater.annotation.LogParam;
import com.bigeater.atom.AtomUserService;
import com.bigeater.dao.UserPoMapper;
import com.bigeater.po.UserPo;
import com.bigeater.po.UserPoExample;
import com.bigeater.util.IdUtil;
import com.bigeater.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * 描述:
 * 用户原子层服务
 *
 * @author J
 * @create 2018-12-17 23:13
 */
@Service
@Slf4j
public class AtomUserServiceImpl implements AtomUserService {

    @Autowired
    private UserPoMapper userPoMapper;

    @Override
    @LogParam(paramIndex = {0, 1})
    public UserPo getByPrimaryId(final String logStr, final Long id) {
        return userPoMapper.selectByPrimaryKey(id);
    }

    @Override
    @LogParam(paramIndex = {0, 1})
    public UserPo getByUsername(String logStr, String username) {
        UserPoExample example = new UserPoExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UserPo> userPoList = userPoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userPoList)) {
            return null;
        } else {
            return userPoList.get(0);
        }
    }

    @Override
    @LogParam(paramIndex = {0, 1})
    public UserPo insertUser(final String logStr, final UserPo userPo) {
        userPo.setId(IdUtil.instance.nextId());
        Long now = System.currentTimeMillis();
        userPo.setCtime(now);
        userPo.setCtime(now);
        int result = userPoMapper.insertSelective(userPo);

        if (result > 0) {
            return userPo;
        }
        return null;
    }
}
