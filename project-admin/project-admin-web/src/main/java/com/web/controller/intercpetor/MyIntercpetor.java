package com.web.controller.intercpetor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.web.utils.WebUtils;

public class MyIntercpetor implements HandlerInterceptor {
	//请求执行方法之前执行 返回值是false就进行拦截
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
    		WebUtils  webUtils = new WebUtils();
    		String userid = webUtils.getCookie(request, "userid");//获取cookie中的userid
            HttpSession session = request.getSession();
            if(session.getAttribute(userid)!=null){  
                //登陆成功的用户  
                return true;  
            }else{  
               //没有登陆，转向登陆界面  
//              request.getRequestDispatcher("/WEB-INF/views/login.html?ctxPath="+requestURI).forward(request, response);  
            	 response.sendRedirect(request.getContextPath()+"/login/come");
            	return false;  
            }  
         
    }
    	//执行方法之后执行
	   /* @Override
	    public void postHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler,
	            ModelAndView modelAndView) throws Exception {
	        System.out.println("MyIntercpetor.postHandle()");
	    }*/
    	/*//最终执行的方法
	    @Override
	    public void afterCompletion(HttpServletRequest request,
	            HttpServletResponse response, Object handler, Exception ex)
	            throws Exception {
	        System.out.println("MyIntercpetor.afterCompletion()");
	        
	    }*/

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}