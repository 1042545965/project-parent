package com.business.admin.service.blog.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.admin.dao.blog.diz.BlogDao;
import com.business.admin.service.blog.BlogService;

@Service("BlogService")
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	@Override
	public List<Map<String, Object>> getAllBlog() {
		List<Map<String, Object>> list = blogDao.getAllBlog();
		return list;
	}

	@Override
	public Map<String, Object> getBlog(String blogid) {
		Map<String, Object> map = blogDao.getBlog(blogid);
		return map;
	}

	@Override
	public void updateBlog(Map<String, Object> map) {
		//cookie中获取当前操作人
		String userid = map.get("userid").toString();
		map.put("update_by", userid);
		map.put("update_time", new Date());
		blogDao.updateBlog(map);		
	}

}
