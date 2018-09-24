package com.business.admin.dao.logindao.diz;


import java.util.List;
import java.util.Map;

public interface LoginDao {

	Map<String, Object> isCome(String username);

	void updateCarModelList(List<Map<String, Object>> mapList);

	List<Map<String, Object>> findCarModel();

	void updateCarModel(Map<String, Object> map);

	List<Map<String, Object>> getUserAuth(String usercode);

	void saveProvince(Map<String, Object> map);

	void saveCity(Map<String, Object> map);

    
}