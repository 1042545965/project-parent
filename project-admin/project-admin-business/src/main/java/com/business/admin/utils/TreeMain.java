package com.business.admin.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;

public class TreeMain {
	
	public static void main(String[] args) {
		TreeMain treeMain = new TreeMain(); 
		treeMain.text_dkz();
	}
	
	
	public String text_dkz() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	Map<String, Object> map01 = new HashMap<String, Object>();
    	map01.put("id", "1");
    	map01.put("pid", "0");
    	map01.put("value", "一级");
    	Map<String, Object> map02 = new HashMap<String, Object>();
    	map02.put("id", "2");
    	map02.put("pid", "0");
    	map02.put("value", "一级");
    	Map<String, Object> map03 = new HashMap<String, Object>();
    	map03.put("id", "3");
    	map03.put("pid", "1");
    	map03.put("value", "二级子菜单01");
    	Map<String, Object> map04 = new HashMap<String, Object>();
    	map04.put("id", "4");
    	map04.put("pid", "2");
    	map04.put("value", "二级子菜单02");
    	Map<String, Object> map05 = new HashMap<String, Object>();
    	map05.put("id", "5");
    	map05.put("pid", "3");
    	map05.put("value", "三级子菜单01");
    	Map<String, Object> map06 = new HashMap<String, Object>();
    	map06.put("id", "6");
    	map06.put("pid", "5");
    	map06.put("value", "四级子菜单01");
    	list.add(map01);
    	list.add(map02);
    	list.add(map03);
    	list.add(map04);
    	list.add(map05);
    	list.add(map06);
    	new TreeMain().createComboTreeTree(list,"id","pid");
    	 Gson gson = new Gson(); 
    	 String jsonatr = gson.toJson(new TreeMain().createComboTreeTree(list,"id","pid"));  
    	System.out.println(jsonatr);
    	return jsonatr;
	}
	
	public List<Map<String, Object>> createComboTreeTree(List<Map<String, Object>> list, String fid , String cid) {  
		List<Map<String,Object>> comboTreeList  =new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {  
	        Map<String, Object> map = null;  
	        Map<String, Object> rolemap =  list.get(i);  
	        if (rolemap.get(fid).equals("0")) {  
	            map = new HashMap<String, Object>();  
	            //这里必须要将对象角色的id、name转换成ComboTree在页面的显示形式id、text  
	            //ComboTree,不是数据表格，没有在页面通过columns转换数据的属性  
	            for(Entry<String, Object> entry : list.get(i).entrySet()){
	    			map.put(entry.getKey(),  entry.getValue());
	    		}
	            List<Map<String, Object>> childrenList = createComboTreeChildren(list, rolemap.get(cid).toString() , fid , cid);
	            if (!childrenList.isEmpty()) {//不为空则不需要put进去
	            	 map.put("children", childrenList);  
				}
//	            map.put("children", childrenList);
	        }  
	        if (map != null)  
	            comboTreeList.add(map);  
	    }  
		return comboTreeList;
	}  
	
	
	/** 
	 * 递归设置role树 
	 * @param list 
	 * @param fid 
	 * @return 
	 */  
	private List<Map<String, Object>> createComboTreeChildren(List<Map<String, Object>> list, String id , String fid , String cid) {  
	    List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();  
	    for (int j = 0; j < list.size(); j++) {  
	        Map<String, Object> map = null;  
	        Map<String, Object> treeChild = list.get(j);  
	        if (treeChild.get(fid).equals(id)) {  
	            map = new HashMap<String, Object>();  
	            //这里必须要将对象角色的id、name转换成ComboTree在页面的显示形式id、text  
	            //ComboTree,不是数据表格，没有在页面通过columns转换数据的属性  
	            for(Entry<String, Object> entry : list.get(j).entrySet()){
	    			map.put(entry.getKey(),  entry.getValue());
	    		}
	            List<Map<String, Object>> childrenList = createComboTreeChildren(list, treeChild.get(cid).toString() , fid , cid);
	            if (!childrenList.isEmpty()) {
	            	map.put("children", childrenList);
				}
	        }  
	          
	        if (map != null)  
	            childList.add(map);  
	    }  
	    return childList;  
	}  
	
}
