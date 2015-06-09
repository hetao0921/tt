/**
 * 
 */
package com.fxdigital.analysis.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.apache.commons.lang3.ObjectUtils.Null;

/**
 * @author lizehua
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Generate {
	 public String[] returnType() default {"void",""};//post 和put 不用写
	 public Class[] params() default {} ;//post 和put  提交 第一个参数是 返回的类名如果是 List 第二个参数为空   如果为 List<E> 第二个参数为 E的全包名+类名
}
