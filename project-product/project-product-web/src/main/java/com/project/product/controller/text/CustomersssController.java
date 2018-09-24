package com.project.product.controller.text;



import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.product.annotation.GetParameterEntity;
import com.project.product.annotation.MethodState;
import com.project.product.annotation.ResultEntity;
import com.project.product.entity.customerentity.vo.StbCustomer;
import com.project.product.service.customer.CustomerService;

@Controller
@RequestMapping("/customertext")
public class CustomersssController {
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
}
