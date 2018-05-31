package com.design.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 系统操作日志
 */
@Target({ ElementType.METHOD, ElementType.TYPE })  
@Retention(RetentionPolicy.RUNTIME)  
@Documented 
public @interface SysLog {  
	/**
	  * 操作类型
	  * @Title: operType
	  * @return String    返回类型
	 */
    String operType() default "";    
    
    /**
	  * 操作对象
	  * @Title: operObject
	  * @return String    返回类型
	 */
	String operObject() default "";
	
	/**
	  * 操作路径
	  * @Title: operPath
	  * @return String    返回类型
	 */
	String operPath() default "";
	
	/**
	  * ip地址
	  * @Title: ipAddress
	  * @return String    返回类型
	 */
	String ipAddress() default "";
} 
