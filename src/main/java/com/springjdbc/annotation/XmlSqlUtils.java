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
	 *            由 xml文件名称 + . + 文件中的sqlId 的值 组成
	 * @return sql
	 */
	public String getSqlFormXmlBykey(String key) {
		if (key == null || key.length() < 1)
			throw new AnnotationExceptoin("异常:参数为空 key:" + key);

		if (key.indexOf(".") > -1
				&& allSqlMapList.containsKey(key.substring(0, key.indexOf(".")))
				&& allSqlMapList.get(key.substring(0, key.indexOf("."))).containsKey(
						key.substring(key.indexOf(".") + 1))) {
			return (String) allSqlMapList.get(key.substring(0, key.indexOf("."))).get(
					key.substring(key.indexOf(".") + 1));
		} else {
			throw new AnnotationExceptoin("异常:传入的 " + key + " 无法匹配到sql语句，请检查 " + key + " 是否符合格式要求");
		}
	}

	public Map<String, Map<String, String>> getAllSqlFromXml() {
		return allSqlMapList;
	}

}

