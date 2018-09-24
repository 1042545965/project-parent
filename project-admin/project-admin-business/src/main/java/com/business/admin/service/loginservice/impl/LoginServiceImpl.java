package com.business.admin.service.loginservice.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.admin.dao.logindao.diz.LoginDao;
import com.business.admin.service.loginservice.LoginService;
import com.business.admin.utils.MD5Util;
import com.business.admin.utils.plugin.activerecord.MM;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDao loginDao;

	@SuppressWarnings("static-access")
	@Override
	public MM isCome(Map<String, String> map) {
		//首先基于MD5的一个加密和解密
		MD5Util md = new MD5Util();
		String password = map.get("password");
		String username = map.get("username");
		//根据用户名曲出用户的信息在来匹配密码是否正确
		Map<String, Object> usermap = loginDao.isCome(username);
		if (md.MD5Encode(password, null).equals(usermap.get("password"))) {
			usermap.put("error", false);//匹配成功
		}else {
			usermap.put("error", true);//匹配失败
		}
		MM mm = new MM();
		return mm.setColumns(usermap);
	}

	@Override
	public List<Map<String, Object>> findCarModel() {
		List<Map<String, Object>> list = loginDao.findCarModel();
		return list;
	}

	@Override
	public void updateCarModelList(List<Map<String, Object>> mapList) {
		loginDao.updateCarModelList(mapList);
	}

	@Override
	public void updateCarModel(Map<String, Object> map) {
		loginDao.updateCarModel(map);
		
	}

	@Override
	public List<Map<String, Object>> getUserAuth(String usercode) {
		return loginDao.getUserAuth(usercode);
	}

	@Override
	public void saveProvince(Map<String,Object> map) {
		loginDao.saveProvince(map);
	}

	@Override
	public void saveCity(Map<String, Object> map) {
		loginDao.saveCity(map);
	} 
	
}
