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
 * @createTime��2009-7-3 ����06:07:34
 * @description: POJO����ת��ΪMap����
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
				String firstLetter = fieldName.substring(0, 1).toUpperCase(); // ��ú����Զ�Ӧ��getXXX()����������
				String getMethodName = "get" + firstLetter
						+ fieldName.substring(1); // ��ú����Զ�Ӧ��setXXX()����������
				Method getMethod = classType.getMethod(getMethodName,
						new Class[] {}); // ��ú����Զ�Ӧ��setXXX()����
				Object value = getMethod.invoke(obj, new Object[] {});
				if (value != null)
					hashMap.put(fieldName.toLowerCase(), value.toString());
				else
					hashMap.put(fieldName.toLowerCase(), "");
			}
		} catch (Throwable e) {
			System.err.println("----------ת��ʧ��---" + e);
		}
		return hashMap;
	}

	@SuppressWarnings("unchecked")
	public static List<Map<String, String>> turnToMapList(List<Object> objList) {
		List<Map<String, String>> list2 = new ArrayList<Map<String, String>>();

		for (int i = 0; i < objList.size(); i++) {
			Object obj = objList.get(i);
			Map<String, String> map1 = MapUtil.turnToMap(obj);// POJOת��ΪMAP
			list2.add(map1);
		}
		return list2;
	}

	/**
	 * string ����ת��Ϊ MAP
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
	 * string ת�� Ϊ Map
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static Map<String, Object> string2Map(String key, Object value) {
		return string2Map(new String[] { key }, new Object[] { value });
	}

}

