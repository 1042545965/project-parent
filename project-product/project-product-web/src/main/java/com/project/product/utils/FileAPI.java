package com.project.product.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.project.product.annotation.ApiPara;
import com.project.product.annotation.GetParameterEntity;
import com.project.product.annotation.MethodState;
import com.project.product.annotation.ResponsePara;
import com.project.product.annotation.ResultEntity;

//获取我配置在.xml文件当中的内容
public class FileAPI {
	public List<Map<String,Object>> getAPI(){
		List<Map<String,Object>> apilist=new ArrayList<Map<String,Object>>();
		try {
			String basepath = this.getClass().getResource("/api").getPath();
			System.out.println(basepath);
			File sqldirectory = new File(basepath);
			File[] datasqlfiles = sqldirectory.listFiles();
			if (datasqlfiles.length > 0) {
				for (int i = 0; i < datasqlfiles.length; i++) {
					File file = datasqlfiles[i];
					SAXReader saxReader = new SAXReader();
					Document doc = saxReader.read(file);
					Element root = doc.getRootElement();
					if ("docs".equals(root.getName())) {
						System.out.println("******正在缓存api映射类******");
						List<Element> apidoc = root.elements("doc");
						for (Element e : apidoc) {
							String namespace=e.attributeValue("namespace");
							String name=e.attributeValue("name");
							Map<String,Object> m=new HashMap<String,Object>();
							m.put("namespace", namespace);
							m.put("name", name);
							apilist.add(m);
						}
					}
				}
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return apilist;
	}
	
	//获取所有的自定义注解并且解析封装成为json数组
	public List<Map<String,Object>> queryApiInfoList(List<Map<String,Object>> apiList){
		List<Map<String,Object>> apiInfoList=new ArrayList<Map<String,Object>>();
		try {
		for (int i = 0; i < apiList.size(); i++) {
			String namespace = (String) apiList.get(i).get("namespace");
			String name = (String) apiList.get(i).get("name");
			Class<?> cls = Class.forName(namespace);
			//静态加载类
			Method[] methods=cls.getDeclaredMethods();
			
			Map<String,Object> aaaMap=new HashMap<String,Object>();
			List<Map<String,Object>> methodList=new ArrayList<Map<String,Object>>();
			for (Method method : methods) {
				
				MethodState MethodStateapi = (MethodState) method.getAnnotation(MethodState.class);
				if(MethodStateapi!=null){
					Map<String,Object> methodMap=new HashMap<String,Object>();
					methodMap.put("method", MethodStateapi.method());
					methodMap.put("value", MethodStateapi.value());
					methodMap.put("request", MethodStateapi.request());
					methodMap.put("url", MethodStateapi.url());
					methodList.add(methodMap);
				}
				
				ResultEntity resultEntityapi = (ResultEntity) method.getAnnotation(ResultEntity.class);
				Map<String,Object> map = new HashMap<String,Object>();
				if (resultEntityapi !=null) {
					Field[] fields = resultEntityapi.entity().getFields();
					List<Map<String,Object>> resuletList=new ArrayList<Map<String,Object>>();
					for (Field field : fields) {
						ResponsePara responseParaApi = (ResponsePara) field.getAnnotation(ResponsePara.class);
						if (responseParaApi !=null) {
							Map<String,Object> resultMap=new HashMap<String,Object>();
							resultMap.put("field", responseParaApi.field());
							resultMap.put("name", responseParaApi.name());
							resultMap.put("type", responseParaApi.type());
							resultMap.put("maxlength", responseParaApi.maxlength());
							resultMap.put("required", responseParaApi.required());
							resuletList.add(resultMap);
						}
					}
					map.put("resuletList", resuletList);
					methodList.add(map);
				}
				
				GetParameterEntity getParameterEntityapi = (GetParameterEntity) method.getAnnotation(GetParameterEntity.class);
					Map<String,Object> paramemap = new HashMap<String,Object>();
				if (getParameterEntityapi !=null) {
					Field[] fields = getParameterEntityapi.entity().getFields();
					List<Map<String,Object>> parameList=new ArrayList<Map<String,Object>>();
					for (Field field : fields) {
						ApiPara apiPara = (ApiPara) field.getAnnotation(ApiPara.class);
						if (apiPara !=null) {
							Map<String,Object> parameMap=new HashMap<String,Object>();
							parameMap.put("desc", apiPara.desc());
							parameMap.put("name", apiPara.name());
							parameMap.put("type", apiPara.type());
							parameMap.put("maxlength", apiPara.maxlength());
							parameMap.put("required", apiPara.required());
							parameList.add(parameMap);
						}
					}
					paramemap.put("parameList", parameList);
					methodList.add(paramemap);
				}
				aaaMap.put("methodList", methodList);
				aaaMap.put("name", name);
			}
			apiInfoList.add(aaaMap);
		}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return apiInfoList;
	}
	
	
	
}
