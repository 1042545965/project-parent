package com.project.product.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //该注解的生命周期 RUNTIME:在运行时有效（即运行时保留）
@Target(ElementType.METHOD) //加元注解只作用于方法
public @interface MethodState {
	//方法名
	String method();
	//方法用途
	String value();
	//POST,还是GET请求
	String request() default "GET";
	//请求地址
	String url();
}
