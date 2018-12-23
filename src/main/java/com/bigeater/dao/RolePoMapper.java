package com.bigeater.dao;

import com.bigeater.po.RolePo;
import com.bigeater.po.RolePoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePoMapper {
    long countByExample(RolePoExample example);

    int deleteByExample(RolePoExample example);

    int insert(RolePo record);

    int insertSelective(RolePo record);

    List<RolePo> selectByExample(RolePoExample example);

    int updateByExampleSelective(@Param("record") RolePo record, @Param("example") RolePoExample example);

    int updateByExample(@Param("record") RolePo record, @Param("example") RolePoExample example);
}