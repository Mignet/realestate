package com.szhome.cq.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * ������ע����ҪƴװΪwhere����е�һ���ֵ��ֶ�
 * @author Mignet
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WhereField {
	String express();
	Operator operator();
}

