package com.web.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

public class WebUtils {
	   /**
     * 添加cookie
     * 
     * @param response
     * @param name Key
     * @param value Value
     * @param maxAge 有效时间
     * http://blog.csdn.net/haozhongjun/article/details/17239721 详解
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        //cookie.setDomain(".jsoft.me"); // cookie作用域,这个作用域不能乱加，我这样加会写入不了
        response.addCookie(cookie);
    }

    
    public void deleteCookie(HttpServletRequest request,HttpServletResponse response, String name) {
    	Cookie cookies[] = request.getCookies();  
        if (cookies != null)  
        {  
            for (int i = 0; i < cookies.length; i++)  
            {  
                if (cookies[i].getName().equals(name))  
                {  
                    //这边得用"",不能用null  
                	cookies[i].setPath("/");//设置成跟写入cookies一样的  
                	cookies[i].setMaxAge(0);  
                    response.addCookie(cookies[i]);  
                }  
            }  
        } 
	}
    
    
    /**
     * 通过Key获取Value
     * 
     * @param request
     * @param name Key
     * @return Value
     */
    public String getCookieValueByName(HttpServletRequest request, String name) {
        Map<String, String> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name)) {
            String cookieValue = (String) cookieMap.get(name);
            return cookieValue;
        } else {
            return null;
        }
    }
    
    
    
    /**
     * 检索所有Cookie封装到Map集合
     * 
     * @param request
     * @return
     */
    public static Map<String, String> readCookieMap(HttpServletRequest request) {
        Map<String, String> cookieMap = new HashMap<String, String>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        return cookieMap;
    }
    
    
    
    
    /**
     * 检索所有Cookie封装到Map集合
     * 
     * @param request
     * @return
     */
    public String getCookie(HttpServletRequest request , String name) {
    	 Cookie[] cookies = request.getCookies();
    	 if (!StringUtils.isEmpty(cookies)) {
    		 for(Cookie cookie : cookies){
     	        if(cookie.getName().equals(name)){
     	            String loginInfo = cookie.getValue();
     	            return loginInfo;
     	        }
     	     }
		}   
		return null;
    }
    
    
    /**
     * 获取放入session中的值
     * 
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getUserInfo(HttpServletRequest request , String key) {
    	String userid = getCookieValueByName(request , key);
    	HttpSession httpSession = request.getSession();
		Map<String, Object> map = (Map<String, Object>) httpSession.getAttribute(userid);
		return map;
    }
    
}
