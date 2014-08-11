package com.szhome.cq.web.annotation;

/**
 * 拼装sql语句时，需要的操作符
 * 
 * @author Mignet
 *
 */
public enum Operator {
	/**
	 * 等于
	 */
	EQUAL,

	/**
	 * 小于
	 */
	LESS,

	/**
	 * 大于
	 */
	THAN,

	/**
	 * 大于等于
	 */
	EQUALTHAN,
	/**
	 * 小于等于
	 */
	EQUALLESS,
	/**
	 * 全匹配
	 */
	LIKE,

	/**
	 * 左匹配
	 */
	LEFTLIKE,

	/**
	 * 右匹配
	 */
	RIGHTLIKE,
	/**
	 * in
	 */
	IN;

	public static String getSqlOperator(Operator operator) {
		String sqlOperator = null;
		switch (operator) {
		case EQUAL:
			sqlOperator = " = ";
			break;
		case LESS:
			sqlOperator = " < ";
			break;
		case THAN:
			sqlOperator = " > ";
			break;
		case EQUALTHAN:
			sqlOperator = " >= ";
			break;
		case EQUALLESS:
			sqlOperator = " <= ";
			break;
		case LIKE:
			sqlOperator = " like '%'|| #{replace} ||'%' ";
			break;
		case LEFTLIKE:
			sqlOperator = " like '%'|| #{replace} ";
			break;
		case RIGHTLIKE:
			sqlOperator = " like #{replace} ||'%' ";
			break;
		case IN:
			sqlOperator = " in ( #{replace} ) ";
			break;
		default:
			sqlOperator = "";
			break;
		}
		return sqlOperator;
	}
}

