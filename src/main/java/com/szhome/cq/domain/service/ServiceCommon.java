package com.szhome.cq.domain.service;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.stereotype.Component;

import com.springjdbc.annotation.Entity;
import com.springjdbc.annotation.Table;
import com.springjdbc.annotation.Transient;

/**
 * 公共接口
 * @author Mignet
 */
@Component
@SuppressWarnings("unchecked")
public class ServiceCommon {

	/***
	 * 根据对象，获取批量插入时候的sql语句。
	 * @param obj
	 * @return
	 */
	public String getBatchInsertSqlStr(Object obj) {
		String sql = "insert into ";
		String str1 = "(";
		String str2 = "values(:";

		try{
		Class clazz = obj.getClass();
		String tablename = getTableName(clazz);
		if (tablename == null)
			return null;

		sql = sql + tablename;

		Field[] fields = clazz.getDeclaredFields();
		AccessibleObject.setAccessible(fields, true);
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			boolean tmpflag = field.isAnnotationPresent(Transient.class);
			if (!tmpflag) {

				String fieldName = field.getName();
				String fisrt = fieldName.substring(0, 1);
				String upFisrt = fisrt.toUpperCase();
				String geter = "get" + upFisrt + fieldName.substring(1);
				Method method = clazz.getMethod(geter, null);
				
				str1 = str1 + fieldName + ",";
				str2 = str2 + fieldName + ",:";
			}
		}

		str1 = str1.substring(0, str1.length() - 1) + ")";
		str2 = str2.substring(0, str2.length() - 2) + ")";

		sql = sql + str1 + " " + str2;
		}
		catch(Exception ex){
			sql="";
		}
		return sql;
	}
	
	private String getTableName(Class clazz) {
		boolean flag = clazz.isAnnotationPresent(Entity.class);
		if (!flag)
			return null;
		flag = clazz.isAnnotationPresent(Table.class);
		if (!flag)
			return null;
		Table table = (Table) clazz.getAnnotation(Table.class);
		String tablename = table.name();
		if ((tablename == null) || (tablename.length() < 1)) {
			tablename = clazz.getName();
		}
		return tablename;
	}
	
}

