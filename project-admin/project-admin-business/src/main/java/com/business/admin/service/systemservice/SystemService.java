package com.business.admin.service.systemservice;

import java.util.List;
import java.util.Map;

import com.business.admin.entity.systementity.diz.SystemCommonEntity;
import com.business.admin.entity.systementity.vo.SysMenu;
import com.business.admin.entity.systementity.vo.SysRole;
import com.business.admin.entity.systementity.vo.SysUserRole;
import com.business.admin.utils.LigerUiTree;


public interface SystemService {
		
		//返回主菜单一级按钮
		public List<LigerUiTree> findMainMenu();
		//返回子菜单
		public List<SystemCommonEntity> findSubclassMenu(String systemno);
		//返回图标表当中的信息
		public List<SystemCommonEntity> findMainIcon();
		//保存主页按钮的修改信息
		public Map<String, Object> systemMenuUpdate(SysMenu sysMenu , String __status);
		//删除menu
		public Map<String, Object> systemMenuDelete(SysMenu sysMenu);
		//获取角色信息
		public Map<String, Object> systemRoleMessage();
		//修改或者保存角色信息
		public Map<String, Object> systemRoleUpdateAndSave(SysRole sysRole, String __status);
		//系统登录时我获取的用户
		public SysUserRole findSysUserRole(String string);
		//获取用户角色的按钮的权限
		public Map<String, Object> findLimitsButton(String MenuNo);
		////获取所有功能菜单的一个树的信息
		public Map<String, Object> systemRoleMenuAccreditTree();
		//查看该菜单是否已经授权的一个方法
		public Map<String, Object> systemQueryRoleAccreditTree(String roleid);
		//保存菜单授权的一个方法
		public Map<String, Object> systemRoleAccreditSave(String dataJSON, String roleID);
		//返回功能对应的按钮
		public Map<String, Object> systemMenuAndButtn(String menuNo);
		//mybatis 批量插入
		public void insertAutoHome(List<Map<String, Object>> list);
		
		public void insertAutoHomeMap(Map<String, Object> map);
		//mybatis 车系批量插入
		public void insertCarSetAutoHome(List<Map<String, Object>> carSetList, String car_id, String set_type, String set_type_name);
		//mybatis 车型号批量插入
		public void insertCarModeAutoHome(List<Map<String, Object>> carModemapList, String string, String type, String type_name);
		
		public void insertTest(Map<String, Object> map);
		//快速添加
		public void quickAddButton(String menuNo);

		
//		public	<T>List<T>  queryMM();
}
