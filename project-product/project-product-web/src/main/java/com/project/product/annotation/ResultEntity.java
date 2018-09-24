package com.project.product.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResultEntity {
	  /**
     * api接口返回参数定义
     *
     * @return
     */
    Class<?> entity();
}
