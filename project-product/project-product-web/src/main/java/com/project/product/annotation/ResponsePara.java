package com.project.product.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponsePara {
	/**
     * 字段
     */
    String field();
   
	/**
     * 字段含义
     */
    String name();
   
    /**
     * 参数类型
     */
    String type();
    /**
     * 返回最大长度
     */
    String maxlength();
    /**
     * 是否一定返回
     */
    boolean required();
}
