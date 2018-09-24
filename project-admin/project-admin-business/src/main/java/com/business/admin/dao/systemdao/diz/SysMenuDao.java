package com.business.admin.dao.systemdao.diz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.business.admin.entity.systementity.diz.SystemCommonEntity;
import com.business.admin.utils.LigerUiTree;

public interface SysMenuDao {
	
	//返回主菜单
	List<LigerUiTree> findMainMenu();
	//返回子菜单
	List<SystemCommonEntity> findSubclassMenu(String systemid);
	//返回图标表当中的信息
	List<SystemCommonEntity> findMainIcon();
	//获取角色信息
	List<Map<String, Object>> findRoleMessage();
	//获取用户角色的按钮的权限
	List<Map<String, Object>> findLimitsButton(String menuNo);
	
	//获取所有功能菜单的信息
	List<Map<String, Object>> findRoleMenuAccredit();
	//功能按钮信息
	List<Map<String, Object>> findMenuButtn(String accessNo);
	
	//查看该菜单是否已经授权的一个方法
	List<Map<String, String>> findQueryRoleAccreditTree(String roleid);
	//删除sysacl表当中所有相对应的角色的数据
	void deleteSysAcl(String roleID);
	//返回功能对应的按钮
	List<Map<String, Object>> findMenuAndButtn(String menuNo);
	//mybatis批量插入
	void insertAutoHome(List<Map<String, Object>> list);
	
	void insertAutoHomeMap(Map<String, Object> map);
	//mybatis 车系批量插入
	void insertCarSetAutoHome(@Param("templist") List<Map<String, Object>> carSetList, @Param("car_id") String car_id, @Param("set_type") String set_type, @Param("set_type_name") String set_type_name);
	//mybatis 车型号批量插入
	void insertCarModeAutoHome(@Param("templist") List<Map<String, Object>> carModemapList, @Param("set_id") String strmodel, @Param("type")  String type, @Param("type_name") String type_name);
	
	void insertTest(Map<String, Object> map);
	
	void quickAddButton(Map<String, Object> map);
	
	List<Map<String, Object>> getAddButton();
	
	
	
    
}