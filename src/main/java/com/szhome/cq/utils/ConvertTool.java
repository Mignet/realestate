package com.szhome.cq.utils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.plan.commons.Row;

public class ConvertTool {
	public static List<Row> map2Row(List<Map<String, Object>> src){
		return null;
	}
	
	/*
	 * ����ת����Map
	 */
	public static void object2MapWithoutNull(Object obj, Map map)throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int j = 0; j < fields.length; j++) {
			fields[j].setAccessible(true);// ����������������з���Ȩ�޼�� ���������õı�������Ϊprivate
			if (fields[j].get(obj) != null
				&& (((fields[j].get(obj) instanceof String) && !""
				.equals(fields[j].get(obj))) || ((fields[j]
				.get(obj) instanceof Integer)))) {
				map.put(fields[j].getName(), fields[j].get(obj));
			}
		}
	}
}

