package com.business.admin.dao.systemdao.vo;

import com.business.admin.entity.systementity.vo.SysAcl;

public interface SysAclMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysAcl record);

    int insertSelective(SysAcl record);

    SysAcl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysAcl record);

    int updateByPrimaryKey(SysAcl record);
}