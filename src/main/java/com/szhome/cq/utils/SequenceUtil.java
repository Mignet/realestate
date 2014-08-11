package com.szhome.cq.utils;

import java.util.List;
import java.util.Map;

import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.domain.PublicDomain;

/**
 * ��ȡsequenceid
 * @author Mignet
 *
 */
public class SequenceUtil {

	private static PublicDomain sd = FacadeFactory.getPublicDomain();

	/***********************
	 * ��ȡȫ�ֱ�ʶ���Ĺ�����̬����
	 * �޲���
	 *
	 */
	public static String getGlobalSeqID() {
		return sd.queryGlobalSeqID();
	}

	/**
	 * �жϵ�ǰ���Ƿ���������ݿ���
	 * @param ����
	 * @return false :��ʾ�����ڣ�true:��ʾ����
	 * */
	public static boolean isExistTable(String tableName) {
		return sd.isExistTableName(tableName);
	}

	/**
	 * ȡ��ָ�������µ������ֶ�
	 * @param ����
	 * @return ����ֶ�����
	 * */

	public static List<Map<String, String>> getColumn(String tableName) {
		return sd.getTableColumn(tableName);
	}

	/**
	 * ��ѯ���еı�
	 * */
	public static List<Map<String, String>> getAllTable() {
		return sd.getAllTables();
	}

}

