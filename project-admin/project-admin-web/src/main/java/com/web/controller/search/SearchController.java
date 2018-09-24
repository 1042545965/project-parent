package com.web.controller.search;







import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.admin.service.searchservice.SearchService;
import com.business.admin.service.systemservice.SystemService;


@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping("/leadSearch")
	@ResponseBody
	public Map<String, Object> leadSearch(){
		Map<String, Object> testmap = searchService.findTestAllCustomer();
		return testmap;
	}
}
