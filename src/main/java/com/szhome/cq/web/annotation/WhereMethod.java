package com.szhome.cq.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 被用来注解需要where的方法.
 * @author Mignet
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WhereMethod {
	String baseWhere();
	String groupBy() default "";
    String orderBy() default "";
}

