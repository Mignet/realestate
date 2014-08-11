package com.springjdbc.annotation;

import java.util.Map;
import org.springframework.context.ApplicationContext;

public class XmlSqlUtils {
	private static ApplicationContext ac;
	private volatile static XmlSqlUtils uniqueInstance;
	private static Map<String, Map<String, String>> allSqlMapList;

	private static XmlSqlParse getXmlSqlUtils() {
		return (XmlSqlParse) ac.getBean("xmlSqlParse");
	}

	private XmlSqlUtils() {

	}

	public static XmlSqlUtils getInstance() {
		if (uniqueInstance == null) {
			synchronized (XmlSqlUtils.class) {
				if (uniqueInstance == null) {
					ac = ApplicationContextUtils.getApplicationContext();
					uniqueInstance = new XmlSqlUtils();
					allSqlMapList = getXmlSqlUtils().getAllSqlFromXmlFiles();
				}
			}
		}
		return uniqueInstance;
	}

	/**
	 * @param key
	 *            �� xml�ļ����� + . + �ļ��е�sqlId ��ֵ ���
	 * @return sql
	 */
	public String getSqlFormXmlBykey(String key) {
		if (key == null || key.length() < 1)
			throw new AnnotationExceptoin("�쳣:����Ϊ�� key:" + key);

		if (key.indexOf(".") > -1
				&& allSqlMapList.containsKey(key.substring(0, key.indexOf(".")))
				&& allSqlMapList.get(key.substring(0, key.indexOf("."))).containsKey(
						key.substring(key.indexOf(".") + 1))) {
			return (String) allSqlMapList.get(key.substring(0, key.indexOf("."))).get(
					key.substring(key.indexOf(".") + 1));
		} else {
			throw new AnnotationExceptoin("�쳣:����� " + key + " �޷�ƥ�䵽sql��䣬���� " + key + " �Ƿ���ϸ�ʽҪ��");
		}
	}

	public Map<String, Map<String, String>> getAllSqlFromXml() {
		return allSqlMapList;
	}

}

