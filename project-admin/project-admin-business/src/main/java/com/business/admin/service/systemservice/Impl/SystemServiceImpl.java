package com.business.admin.service.systemservice.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.business.admin.dao.systemdao.diz.SysMenuDao;
import com.business.admin.dao.systemdao.vo.SysAclMapper;
import com.business.admin.dao.systemdao.vo.SysMenuMapper;
import com.business.admin.dao.systemdao.vo.SysRoleMapper;
import com.business.admin.dao.systemdao.vo.SysUserRoleMapper;
import com.business.admin.entity.systementity.diz.SystemCommonEntity;
import com.business.admin.entity.systementity.vo.SysAcl;
import com.business.admin.entity.systementity.vo.SysMenu;
import com.business.admin.entity.systementity.vo.SysRole;
import com.business.admin.entity.systementity.vo.SysUserRole;
import com.business.admin.service.systemservice.SystemService;
import com.business.admin.utils.BaseUtils;
import com.business.admin.utils.LigerUiTree;

@Service
public class SystemServiceImpl implements SystemService{
	@Autowired
	private SysMenuDao sysMenuDao;
	
	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Autowired 
	private SysRoleMapper sysRoleMapper;
	
	@Autowired 
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Autowired
	private SysAclMapper sysAclMapper;
	
	
	@Override
	public List<LigerUiTree> findMainMenu() {
		List<LigerUiTree> list = sysMenuDao.findMainMenu();
		return list;
	}


	@Override
	public List<SystemCommonEntity> findSubclassMenu(String systemid) {
		List<SystemCommonEntity> menuList = sysMenuDao.findSubclassMenu(systemid);
		return menuList;
	}


	@Override
	public List<SystemCommonEntity> findMainIcon() {
		List<SystemCommonEntity> menuList = sysMenuDao.findMainIcon();
		return menuList;
	}


	@Override
	public Map<String, Object> systemMenuUpdate(SysMenu sysMenu , String __status) {
		SysMenu sysMenuUpdate = new SysMenu();
		//如果是add那么进行增加操作
		if (__status.equals("add")) {
			sysMenu.setIsleaf(Long.valueOf("1"));
			sysMenu.setIsvisible(Long.valueOf("1"));
			sysMenu.setSystemid("s001");
			sysMenuMapper.insert(sysMenu);
		}else {
			sysMenuUpdate = sysMenuMapper.selectByPrimaryKey(sysMenu.getMenuid());
			sysMenuUpdate.setMenuicon(sysMenu.getMenuicon());
			sysMenuUpdate.setMenuname(sysMenu.getMenuname());
			sysMenuUpdate.setMenuno(sysMenu.getMenuno());
			sysMenuUpdate.setMenuorder(sysMenu.getMenuorder());
			sysMenuUpdate.setMenuparentno(sysMenu.getMenuparentno());
			sysMenuUpdate.setMenuurl(sysMenu.getMenuurl());
			sysMenuUpdate.setSystemid(sysMenu.getSystemid());
			sysMenuMapper.updateByPrimaryKeySelective(sysMenuUpdate);
		}
		List<SystemCommonEntity> menuList = sysMenuDao.findMainIcon();
		Map<String, Object> map = new HashMap<String, Object>();
		Integer total = menuList.size();
		map.put("Total", total.toString());
		map.put("Rows", menuList);
		return map;
	}


	@Override
	public Map<String, Object> systemMenuDelete(SysMenu sysMenu) {
		sysMenuMapper.deleteByPrimaryKey(sysMenu.getMenuid());
		List<SystemCommonEntity> menuList = sysMenuDao.findMainIcon();
		Map<String, Object> map = new HashMap<String, Object>();
		Integer total = menuList.size();
		map.put("Total", total.toString());
		map.put("Rows", menuList);
		return map;
	}


	@Override
	public Map<String, Object> systemRoleMessage() {
		Map<String, Object> mapRoleMessage = new HashMap<String, Object>();
		List<Map<String, Object>> roleList = sysMenuDao.findRoleMessage();
		mapRoleMessage.put("Rows", roleList);
		mapRoleMessage.put("Total", roleList.size());
		return mapRoleMessage;
	}


	@Override
	public Map<String, Object> systemRoleUpdateAndSave(SysRole sysRole, String __status) {
		SysRole updateRole = new SysRole();
		if ("add".equals(__status)) {
			sysRole.setSystemid("s001");
			sysRoleMapper.insert(sysRole);
		}else {
			updateRole = sysRoleMapper.selectByPrimaryKey(sysRole.getId());
			updateRole.setDescription(sysRole.getDescription());
			updateRole.setName(sysRole.getName());
			sysRoleMapper.updateByPrimaryKey(updateRole);
		}
		Map<String, Object> mapRoleMessage = new HashMap<String, Object>();
		List<Map<String, Object>> roleList = sysMenuDao.findRoleMessage();
		mapRoleMessage.put("Rows", roleList);
		mapRoleMessage.put("Total", roleList.size());
		return mapRoleMessage;
	}


	@Override
	public SysUserRole findSysUserRole(String string) {
		SysUserRole sysUserRole = sysUserRoleMapper.selectByPrimaryKey(Long.valueOf(string));
		return sysUserRole;
	}


	@Override
	public Map<String, Object> findLimitsButton(String menuNo) {
		List<Map<String, Object>> limitsButtonList= sysMenuDao.findLimitsButton(menuNo);
		Map<String, Object> limitsButtonMap = new HashMap<String, Object>();
		limitsButtonMap.put("Data", limitsButtonList);
		limitsButtonMap.put("Message", " ");
		limitsButtonMap.put("IsError",false);
		return limitsButtonMap;
	}


	@Override
	public Map<String, Object> systemRoleMenuAccreditTree() {
		List<Map<String, Object>> roleMenuAccreditList = sysMenuDao.findRoleMenuAccredit();
		//这里应为还有一层还需要在循环一次
		for (int i = 0; i < roleMenuAccreditList.size(); i++) {
			String AccessNo = (String) roleMenuAccreditList.get(i).get("AccessNo");
			List<Map<String, Object>> menuButtnList =  sysMenuDao.findMenuButtn(AccessNo);
			for (int j = 0; j < menuButtnList.size(); j++) {
				if (menuButtnList.size()>0) {
					menuButtnList.get(j).put("AccessType", "button");
				}
			}
			roleMenuAccreditList.get(i).put("children", menuButtnList);
		}
		BaseUtils daseUtils = new BaseUtils();
		List<Map<String, Object>> roleMenuAccreditTreeList = daseUtils.ArrayToTreeData(roleMenuAccreditList, "AccessNo", "menuparentno");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Message", "");
		map.put("IsError", false);
		map.put("Data", roleMenuAccreditTreeList);
		return map;
	}


	@Override
	public Map<String, Object> systemQueryRoleAccreditTree(String roleid) {
		List<Map<String, String>> roleMenuAccreditList = sysMenuDao.findQueryRoleAccreditTree(roleid);
		List<Map<String, Object>> diclist = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < roleMenuAccreditList.size(); i++) {
			Map<String,Object> temp = new HashMap<String,Object>();
			if("sys_menu".equals(roleMenuAccreditList.get(i).get("privilegeaccess"))){
				//如果该privilegeaccess参数是sys_menu那么
				temp.put("MenuID",roleMenuAccreditList.get(i).get("privilegeaccesskey") );
				temp.put("BtnID", 0);
			}else{
				temp.put("MenuID",0);
				temp.put("BtnID", roleMenuAccreditList.get(i).get("privilegeaccesskey") );
			}
			diclist.add(temp);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Message", " ");
		map.put("IsError", false);
		map.put("Data", diclist);
		return map;
	}


	@Override
	public Map<String, Object> systemRoleAccreditSave(String dataJSON, String roleID) {
		//先删除该角色对应的所有的权限
		sysMenuDao.deleteSysAcl(roleID);
		List<Map> permissionsList = JSON.parseArray(dataJSON, Map.class);
		for (int i = 0; i < permissionsList.size(); i++) {
			// 是否分配权限
			boolean Permit = Boolean
					.valueOf(permissionsList.get(i).get("Permit") == null ? "false" : permissionsList.get(i)
							.get("Permit").toString());
			
			// 是否按钮
			boolean isButton = Integer
					.valueOf(permissionsList.get(i).get("BtnID") == null ? "0" : permissionsList.get(i).get(
							"BtnID").toString()) != 0;
			//用三元运算符判断是授权给了功能还是按钮
			String MenuID = permissionsList.get(i).get("MenuID") == null ? "0"
					: permissionsList.get(i).get("MenuID").toString();
			String BtnID = permissionsList.get(i).get("BtnID") == null ? "0"
					: permissionsList.get(i).get("BtnID").toString();
			
			if (Permit) {
				SysAcl sysAcl = new SysAcl();
				sysAcl.setPrivilegemaster("role");
				sysAcl.setPrivilegemasterkey(roleID);
				sysAcl.setPrivilegeaccess(isButton ? "sys_button" : "sys_menu");
				sysAcl.setPrivilegeoperation(isButton ? Long.valueOf(BtnID) :Long.valueOf(MenuID));
				sysAcl.setPrivilegeoperation(1L);
				sysAcl.setPrivilegeaccesskey(isButton ? BtnID : MenuID);;
				sysAclMapper.insert(sysAcl);
			}
		}
		
		List<Map<String, Object>> roleMenuAccreditList = sysMenuDao.findRoleMenuAccredit();
		//这里应为还有一层还需要在循环一次
		for (int i = 0; i < roleMenuAccreditList.size(); i++) {
			String AccessNo = (String) roleMenuAccreditList.get(i).get("AccessNo");
			List<Map<String, Object>> menuButtnList =  sysMenuDao.findMenuButtn(AccessNo);
			for (int j = 0; j < menuButtnList.size(); j++) {
				if (menuButtnList.size()>0) {
					menuButtnList.get(j).put("AccessType", "button");
				}
			}
			roleMenuAccreditList.get(i).put("children", menuButtnList);
		}
		BaseUtils daseUtils = new BaseUtils();
		List<Map<String, Object>> roleMenuAccreditTreeList = daseUtils.ArrayToTreeData(roleMenuAccreditList, "AccessNo", "menuparentno");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Message", "");
		map.put("IsError", false);
		map.put("Data", roleMenuAccreditTreeList);
		return map;
	}


	@Override
	public Map<String, Object> systemMenuAndButtn(String menuNo) {
	List<Map<String , Object>> mapList = sysMenuDao.findMenuAndButtn(menuNo);
	Map<String, Object> map = new HashMap<String, Object>();
	Integer Total = mapList.size();
	map.put("Total", Total);
	map.put("Rows", mapList);
	return map;
	}


	@Override
	public void insertAutoHome(List<Map<String, Object>> list) {
		sysMenuDao.insertAutoHome(list);
	}


	@Override
	public void insertAutoHomeMap(Map<String, Object> map) {
		sysMenuDao.insertAutoHomeMap(map);
	}





	@Override
	public void insertCarModeAutoHome(List<Map<String, Object>> carModemapList, String set_id, String type,
			String type_name) {
		sysMenuDao.insertCarModeAutoHome(carModemapList , set_id , type , type_name);
		
	}


	@Override
	public void insertCarSetAutoHome(List<Map<String, Object>> carSetList, String car_id, String set_type,
			String set_type_name) {
		sysMenuDao.insertCarSetAutoHome(carSetList , car_id , set_type , set_type_name);
	}


	@Override
	public void insertTest(Map<String, Object> map) {
		sysMenuDao.insertTest(map);
	}


	@Override
	public void quickAddButton(String menuNo) {
		List<Map<String, Object>> list = sysMenuDao.getAddButton();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			map.remove("btnid");
			map.put("menuno", menuNo);
			sysMenuDao.quickAddButton(map);
		}
		
	}


		
}
