package com.business.admin.utils.base;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;






/**
 * 
    * @ClassName: CocoController
    * @Description: TODO(控制器基类)
    * @author Remiel_Mercy xuefei_fly@126.com
    * @date 2017年8月3日 上午10:16:11
 */
public abstract class CoCoController {
		
		//返回成功对象(JSON)
		public Map<String, Object> getSuccess(String Message){
			Map<String, Object> mm=new HashMap<String, Object>();
			mm.put("IsError", false);
			mm.put("Message", Message);
			mm.put("Data", null);
			return mm;
		}
		//返回成功对象(JSON)
		public  Map<String, Object> getSuccessMessage(Object data) {
			Map<String, Object> mm=new HashMap<String, Object>();
			mm.put("IsError", false);
			mm.put("Message", "");
			mm.put("Data", data);
			return mm;
		}
	
		//返回失败对象(JSON)
		public  Map<String, Object> getErrorMessage(String Message) {
			Map<String, Object> mm=new HashMap<String, Object>();
			mm.put("IsError", true);
			mm.put("Message", Message);
			return mm;
		}
		//从request中获取所有参数。
		public  Map<String, Object> getInsertParam(HttpServletRequest request) {
			Map<String, Object> record = new HashMap<String, Object>();
			Enumeration<String> enume = request.getParameterNames();
			while (enume.hasMoreElements()) {
				String name = enume.nextElement().toString();
				if (StrKit.notBlank(request.getParameter(name))) {
					record.put(name, request.getParameter(name));
				}
			}
			return record;
		}
		
		//从request中获取所有参数,包括为空的参数
		public  Map<String, Object> getAllParam(HttpServletRequest request) {
			Map<String, Object> record = new HashMap<String, Object>();
			Enumeration<String> enume = request.getParameterNames();
			while (enume.hasMoreElements()) {
				String name = enume.nextElement().toString();
				record.put(name, request.getParameter(name));
			}
			return record;
		}
		
		//从request中获取所有参数,且排除某某参数。
		public static Map<String, Object> getUpdateParam(HttpServletRequest request, String... removeParam) {
			Map<String, Object> record = new HashMap<String, Object>();
			Enumeration<String> enume = request.getParameterNames();
			while (enume.hasMoreElements()) {
				String name = enume.nextElement().toString();
				boolean istrue = false;
				for (int i = 0; i < removeParam.length; i++) {
					if (name.equalsIgnoreCase(removeParam[i])) {
						istrue = true;
						continue;
					}
				}
				if (!istrue) {
					record.put(name, request.getParameter(name));
				}
			}
			return record;
		}
}
