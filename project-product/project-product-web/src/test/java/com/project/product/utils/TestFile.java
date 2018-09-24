/*package com.project.product.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
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
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import com.alibaba.dubbo.container.Main;
import com.project.product.annotation.ApiPara;
import com.project.product.annotation.GetParameterEntity;
import com.project.product.annotation.MethodState;
import com.project.product.annotation.ResponsePara;
import com.project.product.annotation.ResultEntity;



public class TestFile {

	@Test
	public void test() {
		Map<String,Object> apiInfo=new HashMap<String,Object>();
		FileAPI fileAPI = new FileAPI();
		List<Map<String,Object>> apiList = fileAPI.getAPI();
		try {
		for (int i = 0; i < apiList.size(); i++) {
			String namespace = (String) apiList.get(i).get("namespace");
				Class<?> cls = Class.forName(namespace);//静态加载类
				Method[] methods=cls.getDeclaredMethods();
				List<Map<String,Object>> methodsList=new ArrayList<Map<String,Object>>();
				for (Method method : methods) {
					MethodState MethodStateapi = (MethodState) method.getAnnotation(MethodState.class);
					if(MethodStateapi!=null){
						Map<String,Object> methodMap=new HashMap<String,Object>();
						methodMap.put("method", MethodStateapi.method());
						methodMap.put("value", MethodStateapi.value());
						methodMap.put("request", MethodStateapi.request());
						methodMap.put("url", MethodStateapi.url());
						methodsList.add(methodMap);
					}
					
					//获取方法里的entity在继续解析这个返回类
					ResultEntity resultEntityapi = (ResultEntity) method.getAnnotation(ResultEntity.class);
					if (resultEntityapi !=null) {
						Field[] fields = resultEntityapi.entity().getFields();
						List<Map<String,Object>> resultList=new ArrayList<Map<String,Object>>();
						for (Field field : fields) {
							ResponsePara responseParaApi = (ResponsePara) field.getAnnotation(ResponsePara.class);
							if (responseParaApi !=null) {
								Map<String,Object> resultMap=new HashMap<String,Object>();
								resultMap.put("field", responseParaApi.field());
								resultMap.put("name", responseParaApi.name());
								resultMap.put("type", responseParaApi.type());
								resultMap.put("maxlength", responseParaApi.maxlength());
								resultMap.put("required", responseParaApi.required());
								resultList.add(resultMap);
							}
						}
						apiInfo.put("resultList", resultList);
					}
					GetParameterEntity getParameterEntityApi = (GetParameterEntity) method.getAnnotation(GetParameterEntity.class);
					if (getParameterEntityApi != null) {
						List<Map<String,Object>> parameterList=new ArrayList<Map<String,Object>>();
						Field[] parameterFields = getParameterEntityApi.entity().getFields();
						for (Field parameterField : parameterFields) {
							ApiPara apiPara = (ApiPara) parameterField.getAnnotation(ApiPara.class);
							Map<String,Object> parametersMap=new HashMap<String,Object>();
							if (apiPara != null) {
								 parametersMap.put("name", apiPara.name());
								 parametersMap.put("required", apiPara.required());
								 parametersMap.put("desc", apiPara.desc());
								 parametersMap.put("maxlength", apiPara.maxlength());
								 parametersMap.put("type", apiPara.type());
								 parameterList.add(parametersMap);
							}
						}
						apiInfo.put("resultList", parameterList);
					}
				}
				apiInfo.put("methodsList", methodsList);
			} 
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
*/