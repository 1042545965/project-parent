package com.business.admin.dao.systemdao.vo;

import com.business.admin.entity.systementity.vo.SysMenu;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Long menuid);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long menuid);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}