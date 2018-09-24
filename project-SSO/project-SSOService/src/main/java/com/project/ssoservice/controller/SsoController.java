package com.project.ssoservice.controller;






import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.product.entity.customerentity.vo.StbCustomer;
import com.project.product.service.customer.CustomerService;


@Controller
@RequestMapping("/sso")
public class SsoController {
	
//	@Resource
//	private CustomerService customerService;
	
	@RequestMapping("/test")
	@ResponseBody
	public String test(){
//		StbCustomer stbCustomer = customerService.customerFindCustomerAll();
		return "success";
	}
	
	
	@RequestMapping("/testsessionone")
	@ResponseBody
	public String testSessionOne(HttpSession httpSession){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("name", "帅哥段");
		map.put("address", "湖南娄底");
		map.put("age", "24岁");
		httpSession.setAttribute("maptest", map);
		return "success_one";
	}
	
	
	@RequestMapping("/testsessiontow")
	@ResponseBody
	public String testSessionTow(HttpSession httpSession){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("name", "金大屌");
		map.put("address", "江苏苏州");
		map.put("age", "22岁");
		httpSession.setAttribute("maptest", map);
		return "success_tow";
	}
	
	
	@RequestMapping("/getsession")
	@ResponseBody
	public Map<String,Object> getSession(HttpSession httpSession){
		Map<String, Object> map = (Map<String, Object>) httpSession.getAttribute("maptest");
		return map;
	}
	
	//下面的这个方法实际上就是能够把别人的账号给挤下去的一个方法，值是怎么回复给上一个页面提示呢？这还要去研究一下
	@RequestMapping("/sessionremove")
	@ResponseBody
	public String sessionremove(HttpSession httpSession){
		//如果当前的key
		httpSession.removeAttribute("maptest");
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("name", "new金大屌");
		map.put("address", "new江苏苏州");
		map.put("age", "new22岁");
		httpSession.setAttribute("maptest", map);
		return "success";
	}
}
