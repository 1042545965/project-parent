import static org.junit.Assert.*;

import org.junit.Test;

import com.business.admin.utils.jsonup.JsonUpGrap;

import gui.ava.html.image.generator.HtmlImageGenerator;

public class HtmlImg {

	@Test
	public void test() {
		  HtmlImageGenerator imageGenerator = new HtmlImageGenerator();    
		  //加载html源码内容  
		 // imageGenerator.loadUrl("https://www.baidu.com/"); 
		  String htmlstr = "<table width='654' cellpadding='0' cellspacing='0' bordercolor='#FFFFFF'><tr><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td></tr><tr><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td></tr><tr><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td></tr><tr><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td></tr></table>";
		  //加载html
		  imageGenerator.loadHtml(htmlstr);
		  // 保存到本地    
		  imageGenerator.saveAsImage("D:\\xinxi\\hello-world.png");  
		  // imageGenerator.saveAsHtmlWithMap("hello-world.html", "hello-world.png");  
	    }  
	
	
	
	@Test
	public void test2() {
		JsonUpGrap  jsonUpGrap = new JsonUpGrap();
		jsonUpGrap.getAllDom("https://www.baidu.com/");
	}  
	
	
	
	
	}


