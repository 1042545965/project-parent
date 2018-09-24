package com.project.product.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

 //对api定义的参数进行标注
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiPara {
	 /**
     * 是否为必要参数
     */
    boolean required();

    /**
     * 参数名称
     */
    String name();

    /**
     * 参数注释
     */
    String desc();
    /**
     * 参数类型
     */
    String type();
    /**
     * 最大长度
     */
    String maxlength();
}
