package com.project.product.serviceimpl.customerservice.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.product.dao.customerdao.vo.StbCustomerMapper;
import com.project.product.entity.customerentity.vo.StbCustomer;
import com.project.product.service.customer.CustomerService;
import com.project.product.serviceimpl.redis.impl.PoolJedisClient;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private StbCustomerMapper stbCustomerMapper;
	//在这里注入redis
	@Autowired
	private PoolJedisClient poolJedisClient;
	
	@Override
	public StbCustomer customerFindCustomerAll() {
		// TODO Auto-generated method stub
		return stbCustomerMapper.selectByPrimaryKey(1L);
	}
	@Override
	public String customerTestRedis() {
		return poolJedisClient.get("rowkey");
	}
	@Override
	public Map<String, Object> customerTestMaps() {
		Map<String, Object> map = new HashMap<String, Object>();
		StbCustomer stbCustomer = new StbCustomer();
		stbCustomer.setCreateTime(new Date());
		stbCustomer.setDeleteStatus("1");
		stbCustomer.setUsername("hahha xulihua ");
		map.put("stbCustomer", stbCustomer);
		return map;
	}
	
}
