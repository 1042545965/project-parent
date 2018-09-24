package com.project.product.controller.customer;



import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.product.annotation.GetParameterEntity;
import com.project.product.annotation.MethodState;
import com.project.product.annotation.ResultEntity;
import com.project.product.entity.customerentity.vo.StbCustomer;
import com.project.product.service.customer.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Resource
	private CustomerService customerService;
	
	@MethodState(method="findCustomerAll",request="GET",url="/project-product-web/customer/findcustomerall", value = "获取返回的合同链接接口")
	@ResultEntity(entity=CustomerResult.class)
	@GetParameterEntity(entity=CustomerParameter.class)
	@RequestMapping("/findcustomerall")
	@ResponseBody
	public StbCustomer findCustomerAll() {
		StbCustomer stbCustomer = customerService.customerFindCustomerAll();
		return stbCustomer;
	}
	
	@MethodState(method="testRedis",request="GET",url="/project-product-web/customer/testRedis", value = "测试redis")
	@ResultEntity(entity=CustomerResult.class)
	@GetParameterEntity(entity=CustomerParameter.class)
	@RequestMapping("/testRedis")
	@ResponseBody
	public String testRedis() {
		String str = customerService.customerTestRedis();
		return str;
	}
	
	//测试map是否能够序列化
	@MethodState(method="testMaps",request="GET",url="/project-product-web/customer/testMaps", value = "测试map序列化")
	@ResultEntity(entity=CustomerResult.class)
	@GetParameterEntity(entity=CustomerParameter.class)
	@RequestMapping("/testMaps")
	@ResponseBody
	public Map<String, Object> testMaps() {
		Map<String, Object> map = customerService.customerTestMaps();
		return map;
	}
	
	
	//测试map是否能够序列化
		@RequestMapping("/sessiontext")
		@ResponseBody
		public Map<String, Object> sessiontext(HttpSession httpSession ,String username , String password) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("username", username);
			map.put("password", password);
			httpSession.setAttribute("userinfo", map);
			return map;
		}
	
}
