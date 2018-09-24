package com.project.product.dao.customerdao.vo;

import com.project.product.entity.customerentity.vo.StbCustomer;

public interface StbCustomerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StbCustomer record);

    int insertSelective(StbCustomer record);

    StbCustomer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StbCustomer record);

    int updateByPrimaryKey(StbCustomer record);
}