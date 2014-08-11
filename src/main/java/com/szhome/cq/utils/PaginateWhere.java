package com.szhome.cq.utils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.szhome.cq.web.annotation.Operator;
import com.szhome.cq.web.annotation.WhereBase;
import com.szhome.cq.web.annotation.WhereField;

/**
 * ��ҳ��ѯwhere������ƴװ
 * 
 * 
 * ����ʵ�ֵ�˼·��
 * 
 * ����ҳ��������ֵ��һЩȥ������Щ����ֵ��һ�����б���־��WhereField Annotation���ֶ��ϵĶ�Ӧ��ϵ,
 * �����������κ��࣬����Action, Domain���model��
 * 
 * �ж��������Ƿ�������ֵ������װ����־WhereField Annotation�ϵ�����,�Դ�����װ��ѯ��ҳ��where����.
 * ����ҳ���������˵����ֶΣ��������һ�� <option selected value="">��ѡ��</option> ѡ��,��ر���valueΪ��.
 *  
 * 
 * �����ʹ�÷�����,
 *	  PaginateWhere pageWhere = new PaginateWhere(Some.class,baseMap,paraMap); 
 * ���������ο����캯����˵��
 * 
 * 
 * Ȼ���ȡwhere sql �� ��ѯʱʹ�õĲ���:
 * sql�Ļ�ȡ:      pageWhere.getWhereSql();
 * ��ѯ�����Ļ�ȡ:  pageWhere.getParaMap();
 * 
 * ����ͬʱʹ����������ֵ���Ż�ʹ��������ȷ.
 * 
 * 
 * 
 * @author Mignet
 *
 */
@SuppressWarnings("unchecked")
public class PaginateWhere {
	private static final String SQL_EMPTY = "  ";
	private static final String AND = " and ";
	private static final String PACKAGE = "com\\.dxtx\\.realestate\\.";
	private static Map<String,Map<String, String>> cache_map = new ConcurrentHashMap<String,Map<String, String>>();
	
	
	private Class clazzz;
	private Map<WhereBase, String> baseMap;
	private Map<String, String> notNullMap;

	/**
	 * ���캯��
	 * @param clazz ����WhereFieldע���class
	 * @param baseMap where��ѯ���������֣�����where,goup by,order by
	 * @param paraMap ��struts2��ActionContext.getContext().getParameters()ȡ���Ĳ�����Map
	 */
	public PaginateWhere(Class clazz, Map<WhereBase, String> baseMap, Map<String, Object> paraMap) {
		clazzz = clazz;
		this.baseMap = baseMap;
		notNullMap = getNotNullValueMap(paraMap);
	}

	/**
	 * ����where sql���
	 * @return
	 */
	public String getWhereSql() {
		return getConvertSql(baseMap, notNullMap, getAllWhereField(clazzz));
	}

	/**
	 * ���ز�ѯ��Ҫ�Ĳ���Map
	 * @return
	 */
	public Map<String, String> getParaMap() {
		return notNullMap;
	}

	/**
	 * ���ݲ���Map������ƴװ�����յ�wheresql���ڲ���MapΪ�յ�����£�ֻ�����ɼ򵥵�sql��
	 * ��Ϊ�յ�����£�ģ�����ݻᱻ�滻Ϊ����sql��
	 * @param base
	 * @param notNullPara
	 * @param filedMap
	 * @return
	 */
	private String getConvertSql(Map<WhereBase, String> base, Map<String, String> notNullPara,
			Map<String, String> filedMap) {
		if (notNullPara.isEmpty())
			return getWhereSqlTemplate(base).replace("#{template}", "");
		else
			return getWhereSqlTemplate(base).replace("#{template}", getFiledSql(notNullPara, filedMap));
	}

	/**
	 * ��ȡ���б���ע��WhereFieldע����ֶΣ���Ӧ�Ĳ�ѯ������sql
	 * @param clazz
	 * @return
	 */
	private Map<String, String> getAllWhereField(Class clazz) {
		
		String packHead = clazz.getPackage().getName().replaceAll(PACKAGE, "");   //�滻��������ͬ���ְ���������Key������Ӱ��get����
		
		if(cache_map.containsKey(packHead+clazz.getName())){
			
			return cache_map.get(packHead+clazz.getName());                       //�����Map�Ѿ��������Ѿ������õ����ݣ�ֱ�ӷ���                              
			
		}else{
			Map<String, String> sqlMap = new HashMap<String, String>();

			Field[] fieldArray = clazz.getDeclaredFields();
			
			boolean canCache = true;

			for (Field element : fieldArray) {
				if (element.isAnnotationPresent(WhereField.class)) {

					WhereField whereField = element.getAnnotation(WhereField.class);
					
					if(Operator.IN == whereField.operator()){
						canCache = false;
					}
					
					sqlMap.put(element.getName(), covertOperator(whereField.express(), whereField.operator(), element
							.getName(), element.getType()));
				}
			}
			if(canCache){
				cache_map.put(packHead+clazz.getName(), sqlMap);                       //��Map��������ɺõ����ݣ��Ա��ڶ���ʹ��
			}
			
			return sqlMap;
		}
	}

	/**
	 * ƴװ a.someValue = :someValue �ȸ�ʽ,�� in �͵���Ҫ�����⴦��
	 * @param express
	 * @param operator
	 * @param name
	 * @param clazz
	 * @return
	 */
	private String covertOperator(String express, Operator operator, String name, Class clazz) {
		Assert.isTrue(!StringUtils.isEmpty(express), "WhereField���͵�ע����express����Ϊ��!");

		StringBuilder sb = new StringBuilder();
		sb.append(express);
		if (operator == Operator.IN) {
			sb.append(Operator.getSqlOperator(operator).replace("#{replace}", getInPart(name)));
		} else if (operator == Operator.LIKE || operator == Operator.LEFTLIKE || operator == Operator.RIGHTLIKE) {
			sb.append(Operator.getSqlOperator(operator).replace("#{replace}", getAfterPart(name, clazz, true)));
		} else {
			sb.append(Operator.getSqlOperator(operator)).append(getAfterPart(name, clazz, false));
		}

		return sb.toString();
	}

	/**
	 * ������in���������ֶΣ�����������ռλ����ֱ�ӻ�ȡ�������where sql ���ַ��� 
	 * ���ַ���"abc,efg,abc" ת���� "'abc','efg','abc'"
	 * @param name
	 * @return
	 */
	private String getInPart(String name) {
		if (!StringUtils.isEmpty(notNullMap.get(name))) {
			String[] inArray = StringUtils.split(notNullMap.get(name), ',');
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < inArray.length; i++) {
				if (!StringUtils.isEmpty(inArray[i].trim())) {
					sb.append("'").append(inArray[i]).append("'");
					if (i != inArray.length - 1) {
						sb.append(',');
					}
				}
			}
			return sb.toString();
		} else
			return "";
	}

	/**
	 * ������������ƴװ a.someValue = :someValue,������ʽ�е�:someValue����. 
	 * @param name
	 * @param clazz
	 * @return
	 */
	private String getAfterPart(String name, Class clazz, boolean isLike) {
		if (clazz.isAssignableFrom(java.sql.Date.class) && !isLike)
			return new StringBuilder("to_date(").append(":").append(name).append(",'yyyy-mm-dd hh24:mi:ss')")
					.toString();
		else
			return new StringBuilder(":").append(name).toString();
	}

	/**
	 * ��ȡ����value��Ϊnull����""��Map,
	 * @param parameters  ActionContext.getContext().getParameters()
	 * @return
	 */
	private Map<String, String> getNotNullValueMap(Map<String, Object> parameters) {
		Assert.notNull(parameters, "��ת���Ĳ���MapΪ��!");

		Map<String, String> notNullValueMap = new HashMap<String, String>();

		Iterator<Entry<String, Object>> iterator = parameters.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			if (entry.getValue() != null) {
				if (entry.getValue().getClass().isArray()) { //�����������͵�������������Ĵ���
					String[] entryValue = (String[]) entry.getValue();
					if (!ArrayUtils.isEmpty(entryValue) && !StringUtils.isEmpty(getArrayString(entryValue))) {
						notNullValueMap.put(entry.getKey().substring(entry.getKey().lastIndexOf('.') + 1),
								getArrayString(entryValue));
					}
				} else {
					if (!StringUtils.isEmpty(((String) entry.getValue()).trim())) {
						notNullValueMap.put(entry.getKey().substring(entry.getKey().lastIndexOf('.') + 1),
								(String) entry.getValue());
					}
				}
			}
		}
		return notNullValueMap;
	}

	/**
	 * ת���������͵�����Ϊ�ַ����ͣ� ["football","basketball","xxball"]��
	 * ��� "football,basketball,xxball"��Ϊ��sql������׼��
	 * 
	 * @param entryValue
	 * @param isIn
	 * @return
	 */
	private String getArrayString(String[] entryValue) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < entryValue.length; i++) {
			if (!StringUtils.isEmpty(entryValue[i].trim())) {
				sb.append(entryValue[i]);
				if (i != entryValue.length - 1) {
					sb.append(',');
				}
			}
		}
		return sb.toString();
	}

	/**
	 * ƴװ�õ���sqlģ�壬#{template}���ջᱻ�滻��.
	 * @param base
	 * @return
	 */
	private String getWhereSqlTemplate(Map<WhereBase, String> base) {
		StringBuilder sb = new StringBuilder();
		sb.append(getWhereBaseSql(base)).append(SQL_EMPTY).append("#{template}").append(SQL_EMPTY) //ģ�岿�����ջᱻ�滻��
				.append(getGroupBySql(base)).append(SQL_EMPTY).append(getOrderBySql(base));
		return sb.toString();
	}

	/**
	 * ��ȡwherebase ���ֵ�where��䲿��sql��where���������
	 * @param base
	 * @return
	 */
	private String getWhereBaseSql(Map<WhereBase, String> base) {
		Assert.isTrue(!StringUtils.isEmpty(base.get(WhereBase.WHEREBASE)),
				"WhereBase.WHEREBASE��key��Ӧ��ֵ����Ϊ��,���� where 2 > 1!");
		if (!StringUtils.isEmpty(base.get(WhereBase.WHEREBASE)))
			return base.get(WhereBase.WHEREBASE);
		return "";
	}

	/**
	 * ��ȡgroup by ���ֵ�sql 
	 * @param base
	 * @return
	 */
	private String getGroupBySql(Map<WhereBase, String> base) {
		if (!StringUtils.isEmpty(base.get(WhereBase.GROUPBY)))
			return base.get(WhereBase.GROUPBY);
		return "";
	}

	/**
	 * ��ȡorder by ���ֵ�sql 
	 * @param base
	 * @return
	 */
	private String getOrderBySql(Map<WhereBase, String> base) {
		if (!StringUtils.isEmpty(base.get(WhereBase.ORDERBY)))
			return base.get(WhereBase.ORDERBY);
		return "";
	}

	/**
	 * ���ݲ���Map��ÿ��ֵ��ΪNULL����""��key��ȥ�ҳ�����Map���ҳ�ÿһ������sql.
	 * ���ܷ��ص���  and a.someValue = :someValue and b.anotherValue = :anotherValue....
	 * ���ָ�ʽ�����ݡ�
	 * @param notNullPara
	 * @param filedMap
	 * @return
	 */
	private String getFiledSql(Map<String, String> notNullPara, Map<String, String> filedMap) {
		StringBuilder sb = new StringBuilder();
		Iterator<Entry<String, String>> iterator = notNullPara.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			if (filedMap.containsKey(entry.getKey()) && (!StringUtils.isEmpty(filedMap.get(entry.getKey())))) {
				sb.append(AND).append(filedMap.get(entry.getKey()));
			}
		}
		return sb.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Map<WhereBase, String> baseMap = new HashMap<WhereBase, String>();
		baseMap.put(WhereBase.WHEREBASE, "where a.customerid = b.customerid ");
		baseMap.put(WhereBase.GROUPBY, "group by a.customerid,a.developmanid");
		baseMap.put(WhereBase.ORDERBY, "order by a.customerid");

		/**
		 * �����paraMap����� ActionContext.getContext().getParameters()��ȡ;
		 * 
		 * ����ֻ��Ϊ������ʾ���Լ������һ��.
		 */
		Map<String, Object> paraMap = new HashMap<String, Object>();

		String[] xxv = new String[] { "1", "2", "3" };

		paraMap.put("customer.developmanid", "123");
		paraMap.put("developmanname", "    ");
		paraMap.put("linkmanname", "zhang");
		paraMap.put("linkmantel", null);
		paraMap.put("createDate", "2009-05-02");
		paraMap.put("remark", xxv);
		paraMap.put("loveoov", "");
		paraMap.put("more", "more");

		PaginateWhere xx = new PaginateWhere(WhereSql.class, baseMap, paraMap);

		System.out.println(xx.getWhereSql());
		System.out.println(xx.getWhereSql());
		xx.getParaMap();

	}

	/**
	 * ��ʾʹ�õ��࣬���ᱻɾ��
	 * @author Mignet
	 *
	 */
	private class WhereSql {

		@WhereField(express = "developmanid", operator = Operator.LIKE)
		private String developmanid;

		@WhereField(express = "developmanname", operator = Operator.LIKE)
		private String developmanname;

		@WhereField(express = "linkmanname", operator = Operator.EQUAL)
		private String linkmanname;

		@WhereField(express = "linkmantel", operator = Operator.LEFTLIKE)
		private String linkmantel;

		@WhereField(express = "createDate", operator = Operator.EQUALTHAN)
		private Date createDate;

		@WhereField(express = "remark", operator = Operator.IN)
		private String[] remark;

		private Date createtime;
		private float calcfee;

	}
}

