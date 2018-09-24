package com.web.controller.blog;











import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.admin.service.blog.BlogService;
import com.business.admin.utils.base.CoCoController;
import com.web.utils.WebUtils;




@Controller
@RequestMapping("/blog")
public class BlogController extends CoCoController {
	
		@Autowired 
		private BlogService blogService;
		//进入项目的一个跳转
		@RequestMapping("/toBlog")
		public String toBlog(Model model , String menuno) {
			model.addAttribute("MenuNo", menuno);
			return "blog/blog.html";
		}
		
		//进入项目的一个跳转
		@RequestMapping("/toBlogSub")
		public String toBlogSub(@RequestParam String blogid , Model model) {
			Map<String, Object> map = blogService.getBlog(blogid);
			model.addAttribute("BlogInfo", map);
			return "blog/blogsub.html";
		}
		
		//获取所有的博客
		@RequestMapping("/getAllBlog")
		@ResponseBody
		public Map<String, Object> getAllBlog() {
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				List<Map<String, Object>> list = blogService.getAllBlog();
				map.put("Rows", list);
				return getSuccessMessage(map);
			} catch (Exception e) {
				return getErrorMessage("操作失败");
			}
		}
		
		//获取所有的博客
		@RequestMapping("/updateBlog")
		@ResponseBody
		public Map<String, Object> updateBlog(HttpServletRequest request) {
			try {
				Map<String, Object> map = getAllParam(request);
				WebUtils  webUtils = new WebUtils();
				String userid = webUtils.getCookieValueByName(request, "userid");
				map.put("userid", userid);
				blogService.updateBlog(map);
				return getSuccessMessage("保存成功");
			} catch (Exception e) {
				return getErrorMessage("操作失败");
			}
		}
		
}
