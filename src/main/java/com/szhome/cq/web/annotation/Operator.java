package com.szhome.cq.web.annotation;

/**
 * ƴװsql���ʱ����Ҫ�Ĳ�����
 * 
 * @author Mignet
 *
 */
public enum Operator {
	/**
	 * ����
	 */
	EQUAL,

	/**
	 * С��
	 */
	LESS,

	/**
	 * ����
	 */
	THAN,

	/**
	 * ���ڵ���
	 */
	EQUALTHAN,
	/**
	 * С�ڵ���
	 */
	EQUALLESS,
	/**
	 * ȫƥ��
	 */
	LIKE,

	/**
	 * ��ƥ��
	 */
	LEFTLIKE,

	/**
	 * ��ƥ��
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

