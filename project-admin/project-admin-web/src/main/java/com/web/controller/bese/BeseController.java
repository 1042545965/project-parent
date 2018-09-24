package com.web.controller.bese;





import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.business.admin.entity.systementity.vo.SysUserRole;
import com.business.admin.service.systemservice.SystemService;
import com.business.admin.utils.httpclient.HttpClientUtil;




@Controller
public class BeseController {
	
	@Autowired
	private SystemService systemService;
	
	//进入项目的一个跳转
	@RequestMapping("/")
	public ModelAndView gotext4(HttpSession httpSession) {
		SysUserRole sysUserRole = systemService.findSysUserRole("1");
		httpSession.setAttribute("sysUserRole", sysUserRole.getRoleid()); 
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main/index.html");
		return mv;
	}
	
	//解析汽车之家的json数据存储到数据库
	@RequestMapping("/autoHomeJson")
	@ResponseBody
	@Transactional
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	public String autoHomeJson() {
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		TreeMap<String, Object> map = null ;
		String httpUrl = "https://www.autohome.com.cn/ashx/AjaxIndexCarFind.ashx?type=1";
		String httpStr = httpClientUtil.sendHttpGet(httpUrl);
		JSONObject jsonObject = JSON.parseObject(httpStr);
		JSONObject json = JSON.parseObject(jsonObject.getString("result"));
		JSONArray jsonArray = JSON.parseArray(json.getString("branditems"));
		List<Map<String,Object>> list = (List)jsonArray;
		systemService.insertAutoHome(list); //将车排插入表当中
		
		//在将型号插入另外的表当中，比如说奥迪有A6L 和A4L
		for (int i = 0; i < list.size(); i++) {
			carSet(list.get(i));
		}
		return map.get("branditems").toString();
	}
		
		//在将型号插入另外的表当中，比如说奥迪有A6L 和A4L
		@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
		private void carSet( Map<String,Object> map ) {
			HttpClientUtil httpClientUtil = new HttpClientUtil();
				String carSetUrl = "https://www.autohome.com.cn/ashx/AjaxIndexCarFind.ashx?type=3&value="+map.get("id");
				String carSetStr = httpClientUtil.sendHttpGet(carSetUrl);
				JSONObject carSetObject = JSON.parseObject(carSetStr);
				JSONObject carSetJson = JSON.parseObject(carSetObject.getString("result"));
				JSONArray carSetArray = JSON.parseArray(carSetJson.getString("factoryitems"));
				for (int i = 0; i < carSetArray.size(); i++) {
					String seriesitems = carSetArray.getJSONObject(i).getString("seriesitems");
					JSONArray seriesitemsArray = JSON.parseArray(seriesitems);
					List<Map<String,Object>> list = (List)seriesitemsArray;
					
					String car_id = map.get("id").toString();
					String set_type = carSetArray.getJSONObject(i).getString("id");
					String set_type_name = carSetArray.getJSONObject(i).getString("name");
					systemService.insertCarSetAutoHome(list , car_id , set_type , set_type_name);
					for (int j = 0; j < list.size(); j++) {
						carModel(list.get(j));
					}
				}
		}
	
	
		@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
		private void carModel(Map<String,Object> map) {
			HttpClientUtil httpClientUtil = new HttpClientUtil();
			String carMode = "https://www.autohome.com.cn/ashx/AjaxIndexCarFind.ashx?type=5&value="+map.get("id");
			String carModeStr = httpClientUtil.sendHttpGet(carMode);
			JSONObject carModeObject = JSON.parseObject(carModeStr);
			JSONObject carModeJson = JSON.parseObject(carModeObject.getString("result"));
			JSONArray carModeArray = JSON.parseArray(carModeJson.getString("yearitems"));
			
			for (int i = 0; i < carModeArray.size(); i++) {
				String specitems = carModeArray.getJSONObject(i).getString("specitems");
				JSONArray specitemsArray = JSON.parseArray(specitems);
				List<Map<String,Object>> list = (List)specitemsArray;
				
				String set_id =  map.get("id").toString();
				String type = carModeArray.getJSONObject(i).getString("id");
				String type_name =  carModeArray.getJSONObject(i).getString("name");
				systemService.insertCarModeAutoHome(list , set_id , type , type_name);
				
			}
		}
		
	
	@RequestMapping("/insertAutoHomeMap")
	@ResponseBody
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	@Transactional
	public List<Map<String,Object>> insertAutoHomeMap(Map<String, Object> map) {
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		String httpUrl = "https://www.autohome.com.cn/ashx/AjaxIndexCarFind.ashx?type=1";
		String httpStr = httpClientUtil.sendHttpGet(httpUrl);
		JSONObject jsonObject = JSON.parseObject(httpStr);
		System.out.println(jsonObject.getString("result"));
		JSONObject json = JSON.parseObject(jsonObject.getString("result"));
		System.out.println(json.getString("branditems"));
		JSONArray jsonArray = JSON.parseArray(json.getString("branditems"));
		List<Map<String,Object>> mapListJson = (List)jsonArray;
		for (int i = 0; i < mapListJson.size(); i++) {
			systemService.insertAutoHomeMap(mapListJson.get(i));
			systemService.insertTest(mapListJson.get(i));
		}
		return mapListJson;
	}
	
	
}
