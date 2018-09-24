package com.project.product.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReturnCodePara {
	/**
     * 代码值
     */
	String code();
	/**
     * 代码值说明
     */
	String desc();
}
