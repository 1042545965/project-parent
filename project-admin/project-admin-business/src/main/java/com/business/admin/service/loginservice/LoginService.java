package com.business.admin.service.loginservice;

import java.util.List;
import java.util.Map;

import com.business.admin.utils.plugin.activerecord.MM;


public interface LoginService {

	MM isCome(Map<String, String> map);

	List<Map<String, Object>> findCarModel();

	void updateCarModelList(List<Map<String, Object>> mapList);

	void updateCarModel(Map<String, Object> map);

	List<Map<String, Object>> getUserAuth(String usercode);

	void saveProvince(Map<String,Object> map);

	void saveCity(Map<String, Object> map);

}
