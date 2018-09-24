package com.project.product.service.dubbostar;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MgmtSerivceBootStrap {
	public static void main(String[] args) throws Exception {
		
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
//		context.start();
//		while(true){
//			Thread.sleep(Long.MAX_VALUE);
//		}
		com.alibaba.dubbo.container.Main.main(args); 
	}
}
