/**
 * 
 */
package com.szhome.cq.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Mignet
 * @createTime：2009-7-3 下午06:07:34
 * @description: POJO对象转化为Map对象
 */
public class MapUtil {
	@SuppressWarnings("unchecked")
	public static  <T>  Map turnToMap(T obj) {
		Map hashMap = new HashMap();
		try {
			Class classType = obj.getClass();
			Field[] fields = classType.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				String fieldName = field.getName();
				String firstLetter = fieldName.substring(0, 1).toUpperCase(); // 获得和属性对应的getXXX()方法的名字
				String getMethodName = "get" + firstLetter
						+ fieldName.substring(1); // 获得和属性对应的setXXX()方法的名字
				Method getMethod = classType.getMethod(getMethodName,
						new Class[] {}); // 获得和属性对应的setXXX()方法
				Object value = getMethod.invoke(obj, new Object[] {});
				if (value != null)
					hashMap.put(fieldName.toLowerCase(), value.toString());
				else
					hashMap.put(fieldName.toLowerCase(), "");
			}
		} catch (Throwable e) {
			System.err.println("----------转化失败---" + e);
		}
		return hashMap;
	}

	@SuppressWarnings("unchecked")
	public static List<Map<String, String>> turnToMapList(List<Object> objList) {
		List<Map<String, String>> list2 = new ArrayList<Map<String, String>>();

		for (int i = 0; i < objList.size(); i++) {
			Object obj = objList.get(i);
			Map<String, String> map1 = MapUtil.turnToMap(obj);// POJO转化为MAP
			list2.add(map1);
		}
		return list2;
	}

	/**
	 * string 数组转换为 MAP
	 * 
	 * @param keys
	 * @param values
	 * @return
	 */
	public static Map<String, Object> string2Map(String[] keys, Object[] values) {
		if (keys == null || values == null || keys.length != values.length)
			return null;
		Map<String, Object> pmap = new HashMap<String, Object>();
		for (int i = 0, j = keys.length; i < j; i++) {
			pmap.put(keys[i], values[i]);
		}
		return pmap;

	}

	/**
	 * string 转换 为 Map
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static Map<String, Object> string2Map(String key, Object value) {
		return string2Map(new String[] { key }, new Object[] { value });
	}

}

