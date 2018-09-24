package com.business.admin.service.blog;

import java.util.List;
import java.util.Map;

public interface BlogService {

	List<Map<String, Object>> getAllBlog();

	Map<String, Object> getBlog(String blogid);

	void updateBlog(Map<String, Object> map);

}
