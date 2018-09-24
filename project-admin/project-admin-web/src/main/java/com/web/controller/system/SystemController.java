package com.web.controller.system;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.business.admin.entity.systementity.diz.SystemCommonEntity;
import com.business.admin.entity.systementity.vo.SysMenu;
import com.business.admin.entity.systementity.vo.SysRole;
import com.business.admin.entity.systementity.vo.SysUserRole;
import com.business.admin.service.systemservice.SystemService;
import com.business.admin.utils.LigerUiTree;




@Controller
@RequestMapping("/system")
public class SystemController {
	
	@Autowired
	private SystemService systemService;
	
	//进入项目的一个跳转
	@RequestMapping(value="/gotext")
	public ModelAndView gotext4(HttpSession httpSession) {
		SysUserRole sysUserRole = systemService.findSysUserRole("1");
		httpSession.setAttribute("sysUserRole", sysUserRole.getRoleid()); 
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main/index.html");
		return mv;
	}

	//这是一个公共的方法，获取角色对应的按钮
	@ResponseBody
	@RequestMapping(value="/limitsButton")
	public Map<String, Object> limitsButton(HttpSession httpSession , String MenuNo){
		Map<String, Object> map = systemService.findLimitsButton(MenuNo);
		return map;
	}
	
	
	
	
	
	
	@RequestMapping(value="/typography")
	public ModelAndView typography() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/menu.html");
		return mv;
	}
			
			/*===========================后台菜单管理stra==============================*/
			//返回主菜单一级按钮
			@ResponseBody
			@RequestMapping(value="/findMenu")
			public List<Map<String, Object>> findMenu() {
				List<LigerUiTree> menuList = systemService.findMainMenu();
				List<Map<String, Object>> r = new ArrayList<Map<String, Object>>();// 数据池，返回的数据
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("children", menuList);
				map.put("icon","../resource/icons/32X32/category.gif");
				map.put("text","主菜单");
				r.add(map);
				return r;
			}
		
	
		
		//返回子菜单
		@ResponseBody
		@RequestMapping(value="/findSubclassMenu")
		public Map<String, Object> findSubclassMenu(String systemno) {
			List<SystemCommonEntity> menuList = systemService.findSubclassMenu(systemno);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Rows", menuList);
			return map;
		}
	
		
	
		//返回图标
		@ResponseBody
		@RequestMapping(value="/findMainIcon")
		public Map<String, Object> findMainIcon() {
			List<SystemCommonEntity> menuList = systemService.findMainIcon();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Total", "386");
			map.put("Rows", menuList);
			return map;
		}
	
		//修改menu
		@ResponseBody
		@RequestMapping(value="/systemMenuUpdate")
		public Map<String, Object> systemMenuUpdate(SysMenu sysMenu , String __status) {
			Map<String, Object> map = systemService.systemMenuUpdate(sysMenu , __status);
			return map;
		}
		
		//删除menu
		@ResponseBody
		@RequestMapping(value="/systemMenuDelete")
		public Map<String, Object> systemMenuDelete(SysMenu sysMenu) {
			Map<String, Object> map = systemService.systemMenuDelete(sysMenu);
			return map;
		}
		
		//返回功能对应的按钮
		@ResponseBody
		@RequestMapping(value="/systemMenuAndButtn")
		public Map<String, Object> systemMenuAndButtn(String menuNo) {
			Map<String, Object> map = systemService.systemMenuAndButtn(menuNo);
			return map;
		}
		
		
		/*===========================后台菜单管理end==============================*/
		
		
		/*===========================后台角色star==============================*/
		//进入角色列表页面的跳转
		@RequestMapping(value="/sysRole")
		public ModelAndView sysRole() {
			ModelAndView mv = new ModelAndView();
			mv.addObject("MenuNo", "sysrole");
			mv.setViewName("system/sysRole.html");
			return mv;
		}
		
		//获取角色信息
		@ResponseBody
		@RequestMapping(value="/systemRoleMessage")
		public Map<String, Object> systemRoleMessage() {
			Map<String, Object> map = systemService.systemRoleMessage();
			return map;
		}
		
		//保存或者修改角色信息
		@ResponseBody
		@RequestMapping(value="/systemRoleUpdateAndSave")
		public Map<String, Object> systemRoleUpdateAndSave(SysRole sysRole , String __status) {
			Map<String, Object> map = systemService.systemRoleUpdateAndSave(sysRole , __status);
			return map;
		}
		
		
		
		
		/*=================================菜单角色授权表======================================*/
		
		
		//跳转到菜单角色授权的方法
		@RequestMapping(value="/roleMenuAccredit")
		public ModelAndView roleMenuAccredit() {
			ModelAndView mv = new ModelAndView();
			mv.addObject("MenuNo", "sysright");
			mv.setViewName("system/sysacl.html");
			return mv;
		}
		
		
		//获取所有功能菜单的一个树的信息
		@ResponseBody
		@RequestMapping(value="/roleMenuAccreditTree")
		public Map<String, Object> roleMenuAccreditTree() {
			Map<String, Object> map = systemService.systemRoleMenuAccreditTree();
			return map;
		}
		
		
		//查看该菜单是否已经授权的一个方法
		@ResponseBody
		@RequestMapping(value="/queryRoleAccreditTree")
		public Map<String, Object> queryRoleAccreditTree(String roleid) {
			Map<String, Object> map = systemService.systemQueryRoleAccreditTree(roleid);
			return map;
		}
		
		//保存菜单授权的一个方法
		@ResponseBody
		@RequestMapping(value="/roleAccreditSave")
		public Map<String, Object> roleAccreditSave(String DataJSON , String RoleID , String UserID) {
			Map<String, Object> map = systemService.systemRoleAccreditSave(DataJSON , RoleID);
			return null;
		}
		
		
		//快速保存菜单按钮
		@ResponseBody
		@RequestMapping(value="/quickAddButton")
		@Transactional
		public Map<String, Object> quickAddButton(@RequestParam String menuNo) {
			systemService.quickAddButton(menuNo);
			return null;
		}
		
		
}
