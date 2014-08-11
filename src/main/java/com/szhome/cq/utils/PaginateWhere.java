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
 * 分页查询where条件的拼装
 * 
 * 
 * 此类实现的思路是
 * 
 * 根据页面的输入的值和一些去承载这些输入值的一个类中被标志有WhereField Annotation的字段上的对应关系,
 * 这个类可以是任何类，包括Action, Domain里的model。
 * 
 * 判断输入项是否有输入值，来组装被标志WhereField Annotation上的数据,以此在组装查询分页的where部分.
 * 对于页面有下拉菜单的字段，建议添加一个 <option selected value="">请选择</option> 选项,务必保持value为空.
 *  
 * 
 * 具体的使用方法是,
 *	  PaginateWhere pageWhere = new PaginateWhere(Some.class,baseMap,paraMap); 
 * 具体参数请参考构造函数的说明
 * 
 * 
 * 然后获取where sql 和 查询时使用的参数:
 * sql的获取:      pageWhere.getWhereSql();
 * 查询参数的获取:  pageWhere.getParaMap();
 * 
 * 必须同时使用两个返回值，才会使得数据正确.
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
	 * 构造函数
	 * @param clazz 带有WhereField注解的class
	 * @param baseMap where查询语句基础部分，包括where,goup by,order by
	 * @param paraMap 从struts2的ActionContext.getContext().getParameters()取到的参数的Map
	 */
	public PaginateWhere(Class clazz, Map<WhereBase, String> baseMap, Map<String, Object> paraMap) {
		clazzz = clazz;
		this.baseMap = baseMap;
		notNullMap = getNotNullValueMap(paraMap);
	}

	/**
	 * 返回where sql语句
	 * @return
	 */
	public String getWhereSql() {
		return getConvertSql(baseMap, notNullMap, getAllWhereField(clazzz));
	}

	/**
	 * 返回查询需要的参数Map
	 * @return
	 */
	public Map<String, String> getParaMap() {
		return notNullMap;
	}

	/**
	 * 根据参数Map的数据拼装出最终的wheresql，在参数Map为空的情况下，只会生成简单的sql。
	 * 不为空的情况下，模板数据会被替换为条件sql。
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
	 * 获取所有被标注了WhereField注解的字段，对应的查询条件的sql
	 * @param clazz
	 * @return
	 */
	private Map<String, String> getAllWhereField(Class clazz) {
		
		String packHead = clazz.getPackage().getName().replaceAll(PACKAGE, "");   //替换掉具有相同部分包名，避免Key过长，影响get性能
		
		if(cache_map.containsKey(packHead+clazz.getName())){
			
			return cache_map.get(packHead+clazz.getName());                       //如果在Map已经存在了已经生产好的数据，直接返回                              
			
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
				cache_map.put(packHead+clazz.getName(), sqlMap);                       //往Map中添加生成好的数据，以备第二次使用
			}
			
			return sqlMap;
		}
	}

	/**
	 * 拼装 a.someValue = :someValue 等格式,对 in 型的需要做特殊处理
	 * @param express
	 * @param operator
	 * @param name
	 * @param clazz
	 * @return
	 */
	private String covertOperator(String express, Operator operator, String name, Class clazz) {
		Assert.isTrue(!StringUtils.isEmpty(express), "WhereField类型的注解中express不能为空!");

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
	 * 对于是in操作符的字段，不保留它的占位符，直接获取数据填充where sql 的字符串 
	 * 把字符串"abc,efg,abc" 转换成 "'abc','efg','abc'"
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
	 * 根据数据类型拼装 a.someValue = :someValue,这个表达式中的:someValue部分. 
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
	 * 获取所有value不为null或者""的Map,
	 * @param parameters  ActionContext.getContext().getParameters()
	 * @return
	 */
	private Map<String, String> getNotNullValueMap(Map<String, Object> parameters) {
		Assert.notNull(parameters, "被转换的参数Map为空!");

		Map<String, String> notNullValueMap = new HashMap<String, String>();

		Iterator<Entry<String, Object>> iterator = parameters.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			if (entry.getValue() != null) {
				if (entry.getValue().getClass().isArray()) { //对于数组类型的数据做了特殊的处理
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
	 * 转换数组类型的数据为字符串型， ["football","basketball","xxball"]，
	 * 变成 "football,basketball,xxball"，为了sql语句的做准备
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
	 * 拼装得到的sql模板，#{template}最终会被替换掉.
	 * @param base
	 * @return
	 */
	private String getWhereSqlTemplate(Map<WhereBase, String> base) {
		StringBuilder sb = new StringBuilder();
		sb.append(getWhereBaseSql(base)).append(SQL_EMPTY).append("#{template}").append(SQL_EMPTY) //模板部分最终会被替换掉
				.append(getGroupBySql(base)).append(SQL_EMPTY).append(getOrderBySql(base));
		return sb.toString();
	}

	/**
	 * 获取wherebase 部分的where语句部分sql，where语句必须存在
	 * @param base
	 * @return
	 */
	private String getWhereBaseSql(Map<WhereBase, String> base) {
		Assert.isTrue(!StringUtils.isEmpty(base.get(WhereBase.WHEREBASE)),
				"WhereBase.WHEREBASE的key对应的值不能为空,比如 where 2 > 1!");
		if (!StringUtils.isEmpty(base.get(WhereBase.WHEREBASE)))
			return base.get(WhereBase.WHEREBASE);
		return "";
	}

	/**
	 * 获取group by 部分的sql 
	 * @param base
	 * @return
	 */
	private String getGroupBySql(Map<WhereBase, String> base) {
		if (!StringUtils.isEmpty(base.get(WhereBase.GROUPBY)))
			return base.get(WhereBase.GROUPBY);
		return "";
	}

	/**
	 * 获取order by 部分的sql 
	 * @param base
	 * @return
	 */
	private String getOrderBySql(Map<WhereBase, String> base) {
		if (!StringUtils.isEmpty(base.get(WhereBase.ORDERBY)))
			return base.get(WhereBase.ORDERBY);
		return "";
	}

	/**
	 * 根据参数Map中每个值不为NULL或者""的key，去找出来的Map中找出每一个条件sql.
	 * 最总返回的是  and a.someValue = :someValue and b.anotherValue = :anotherValue....
	 * 这种格式的数据。
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
		 * 这里的paraMap必须从 ActionContext.getContext().getParameters()获取;
		 * 
		 * 这里只是为了做演示，自己构造的一个.
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
	 * 演示使用的类，今后会被删除
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

