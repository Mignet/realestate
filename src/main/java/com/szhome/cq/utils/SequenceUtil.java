package com.szhome.cq.utils;

import java.util.List;
import java.util.Map;

import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.domain.PublicDomain;

/**
 * 获取sequenceid
 * @author Mignet
 *
 */
public class SequenceUtil {

	private static PublicDomain sd = FacadeFactory.getPublicDomain();

	/***********************
	 * 获取全局标识符的公共静态方法
	 * 无参数
	 *
	 */
	public static String getGlobalSeqID() {
		return sd.queryGlobalSeqID();
	}

	/**
	 * 判断当前表是否存在于数据库中
	 * @param 表名
	 * @return false :表示不存在，true:表示存在
	 * */
	public static boolean isExistTable(String tableName) {
		return sd.isExistTableName(tableName);
	}

	/**
	 * 取出指定表名下的所有字段
	 * @param 表名
	 * @return 表的字段名称
	 * */

	public static List<Map<String, String>> getColumn(String tableName) {
		return sd.getTableColumn(tableName);
	}

	/**
	 * 查询所有的表
	 * */
	public static List<Map<String, String>> getAllTable() {
		return sd.getAllTables();
	}

}

