package com.szhome.cq.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springjdbc.annotation.BaseDomain;
import com.szhome.cq.utils.MapUtil;

/**
 * 公共域
 * 
 * @author Mignet
 * 
 */
@Scope("prototype")
@Component
public class PublicDomain extends BaseDomain<PublicDomain> {

	public PublicDomain() {
		super();
		this.t = PublicDomain.class;
	}

	/**
	 * 获取表主键
	 * 
	 * @return
	 */
	public String queryGlobalSeqID() {
		return super.queryObjectByKey("Common.getIDSeq", null, String.class);
	}

	/**
	 * 获取系统时间
	 * 
	 * @return
	 */
	public java.util.Date queryCurrentTime() {
		return super.queryObjectByKey("Common.getSystemTime", null,
				java.util.Date.class);
	}

	/**
	 * 检查表是否存在
	 * 
	 * @param tableName
	 * @return
	 */
	public boolean isExistTableName(String tableName) {
		boolean b = false;
		Map<String, String> paramObj = new HashMap<String, String>();
		paramObj.put("table_name", tableName);
		List<Map<String, Object>> list = super.queryMapListByKey(
				"Table.selectSomeTables", paramObj);
		if (list.size() > 0) {
			return true;
		} else {
			return b;
		}
	}

	/**
	 * 得到单表的所有列
	 * 
	 * @param tableName
	 * @return
	 */
	public List<Map<String, String>> getTableColumn(String tableName) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> paramObj = new HashMap<String, String>();
		paramObj.put("table_name", tableName);
		List<Map<String, Object>> list0 = super.queryMapListByKey(
				"Table.selectColumn", paramObj);
		for (Map map : list0) {
			Map<String, String> temp = new HashMap<String, String>();
			temp.put(String.valueOf(map.get("column_name")), String.valueOf(map
					.get("column_name")));
			list.add(temp);
		}
		return list;
	}

	/**
	 * 获取所有表
	 * 
	 * @return
	 */
	public List<Map<String, String>> getAllTables() {
		List<Map<String, Object>> list = super.queryMapListByKey(
				"Table.selectAllTables", null);
		List<Map<String, String>> rlist = null;
		if (list != null) {
			rlist = new ArrayList<Map<String, String>>();
			for (Map m : list) {
				Map<String, String> rm = m;
				rlist.add(rm);
			}
		}
		return rlist;
	}

}

