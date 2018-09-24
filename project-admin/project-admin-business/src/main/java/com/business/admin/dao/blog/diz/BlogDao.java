package com.business.admin.dao.blog.diz;

import java.util.List;
import java.util.Map;

public interface BlogDao {

	List<Map<String, Object>> getAllBlog();

	Map<String, Object> getBlog(String blogid);

	void updateBlog(Map<String, Object> map);
	
}