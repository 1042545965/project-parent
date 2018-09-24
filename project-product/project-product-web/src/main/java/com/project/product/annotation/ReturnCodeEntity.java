package com.project.product.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReturnCodeEntity {
	  /**
     * api接口对于该接口报错信息的返回说明
     *
     * @return
     */
    Class<?> entity();
}
