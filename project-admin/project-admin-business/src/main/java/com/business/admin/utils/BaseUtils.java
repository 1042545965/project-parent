package com.business.admin.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.util.StringUtils;

import com.business.admin.utils.httpclient.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;

public class BaseUtils {
	
	
	
	
	
	
	public List<Map<String , Object>> ArrayToTreeData (List<Map<String , Object>> mydata , String id , String pid) {
		Map<Object, Object> h = new HashMap<Object, Object>();// 数据索引
		List<Map<String, Object>> r = new ArrayList<Map<String, Object>>();// 数据池，返回的数据

		for (Map<String , Object> item : mydata) {
			if (!item.containsKey(id)) {
				continue;
			} else {
				h.put(item.get(id), item);
			}
		}

		for (Map<String , Object> item : mydata) {
			if (!item.containsKey(id)) {
				continue;
			}
			if (!item.containsKey(pid) || (item.get(pid) == null) || !h.containsKey(item.get(pid))) {
				r.add(item);
			} else {
				Map<String, List<Map<String, Object>>> pitem = (Map<String, List<Map<String, Object>>>) h.get(item.get(pid));
				if (!pitem.containsKey("children")) {
					List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
					children.add(item);
					pitem.put("children", children);
				} else {
					List<Map<String, Object>> children = pitem.get("children");
					children.add(item);
					pitem.put("children", children);
				}
			}
		}
		return r;
	}
	
	//String 转map
	@SuppressWarnings("unchecked")
	public TreeMap<String, Object> strToMap (String jsonstr) {
//		Map<String , String> map = new HashMap<String , String>();
		TreeMap<String, Object> map = null;
		try {
//			Gson gson = new Gson();
			Gson gson = new GsonBuilder()
			        .registerTypeAdapter(
			            new TypeToken<TreeMap<String, Object>>(){}.getType(), 
			            new JsonDeserializer<TreeMap<String, Object>>() {
			            @Override
			            public TreeMap<String, Object> deserialize(
			            JsonElement json, Type typeOfT, 
			            JsonDeserializationContext context) throws JsonParseException {

			                TreeMap<String, Object> treeMap = new TreeMap<>();
			                JsonObject jsonObject = json.getAsJsonObject();
			                Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
			                for (Map.Entry<String, JsonElement> entry : entrySet) {
			                    treeMap.put(entry.getKey(), entry.getValue());
			                }
			                return treeMap;
			            }
			        }).create();
			 map = gson.fromJson(jsonstr, new TypeToken<TreeMap<String, Object>>(){}.getType());
		} catch (Exception e) {
			
		}
		return map;
	}
	
		//Object 的json串转String
		@SuppressWarnings("unchecked")
		public String objecToString (Object object) {
			try {
				Gson gson = new Gson();
				return gson.toJson(object);
			} catch (Exception e) {
				return null;
			}
			
		}
			//String 类型的json 转list id:需要去除小数点的key
			@SuppressWarnings("unchecked")
			public List<Map<String, Object>> strToList (Object strJson , String id) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				try {
					Gson gson = new Gson();
					String str = gson.toJson(strJson);
					list = gson.fromJson(str, list.getClass());
					if (!StringUtils.isEmpty(id)) {
					for (int i = 0; i < list.size(); i++) {
						Double sa = Double.parseDouble(list.get(i).get(id).toString());
						list.get(i).put(id, sa.intValue());
						}
					}
					return list;
				} catch (Exception e) {
					return null;
				}
				
			}
			
			
}
