package com.business.admin.utils.jsonup;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsonUpGrap {

    public static void main(String[] args) throws Exception{
    	JsonUpGrap dkz_Grap = new JsonUpGrap();//http://db.auto.sohu.com/home/
    	dkz_Grap.getNewsFromCarHome(1,"https://www.autohome.com.cn/spec/32014/");
    }

    //专门从汽车之家获取数据，需要在改
    public  String getNewsFromCarHome(int size,String baseUrl){
    	 Document doc ;
    	try {
    		doc = Jsoup.connect(baseUrl).get();//获取页面所以元素
    		 Elements links = doc.select(".cardetail-infor-car").select("li"); //获取所以的a标签
    		 for (int i = 0; i < links.size(); i++) {
    			 if (i==6) {
    				  links.get(i).select("span").remove();
    				System.out.println(links.get(i).text());
    				return links.get(i).text();
				}
    		 }
		} catch (Exception e) {
			return null;
		}
		return null;
    	
    }
    
  //专门从汽车之家获取数据，需要在改
    public  String getAllDom(String baseUrl){
    	 Document doc = null;
		try {
			 doc = Jsoup.connect(baseUrl).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 System.out.println(doc);
		return null;
    }

    
    
}
