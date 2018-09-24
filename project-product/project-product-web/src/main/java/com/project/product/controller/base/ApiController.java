package com.project.product.controller.base;


import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.product.utils.FileAPI;

@Controller
@RequestMapping("/base")
public class ApiController {
	
	//第一步:在获取这个类名，其实就存在我的name当中
	
	//第二步获取所以的方法名称,其实就是注解 MethodState中的method参数
	@RequestMapping("/baseview")
	@ResponseBody
	public ModelAndView baseView(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("api/api.html");
		return mv;
	}
	
	@RequestMapping("/baseapi")
	@ResponseBody
	public Map<String,Object> baseApi(){
		FileAPI fileAPI = new FileAPI();
		List<Map<String,Object>> queryApiMap = fileAPI.queryApiInfoList(fileAPI.getAPI());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("queryApiMap", queryApiMap);
		return map;
	}
}
