package com.project.product.service.customer;

import java.util.List;
import java.util.Map;

import com.project.product.entity.customerentity.vo.StbCustomer;

public interface CustomerService {
	//查找所有用户
	StbCustomer customerFindCustomerAll();
	//测试redis
	String customerTestRedis();
	//测试map序列化
	Map<String, Object> customerTestMaps();

}
