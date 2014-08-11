package com.szhome.cq.utils.domaingenerate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.szhome.cq.dbcp.DatabaseOracleUtil;

/**
 * 组装表对象字段服务类
 * @author  Mignet
 */
@Scope("prototype")
@Component
public class SetTableColumnService {

	@Autowired
	private TableColumnBean columnBean;

	/**
	 * 组装表对象字段
	 * @param listTables
	 * @param packPath
	 * @return 
	 * List<IFormatedTableBean>
	 */
	public List<IFormatedTableBean> combinationTables(List<TableBean> listTables,String packPath){

		List<IFormatedTableBean> tList = new ArrayList<IFormatedTableBean>();
		for (TableBean table : listTables) {

			columnBean.setTable_name(table.getTable_name());

			//获取每个表的主键
			List<Map<String, Object>> pList =new ArrayList<Map<String,Object>>();//this.columnBean.queryMapListByKey("Table.selectPrimaryKey",this.columnBean);
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("column_name", getPKByTablename(table.getTable_name()));
			pList.add(m);
			table.setPrimaryKeyList(pList);

			//获取表的每个字段信息
			List<Map<String, Object>> clist = getColumnList(table.getTable_name());//this.columnBean.queryMapListByKey("Table.selectColumn",this.columnBean);
			table.setTableColumnBeanList(clist);
			table.setPackageInfom(packPath);
			
			//获取表的字段备注
			tList.add(table);
		}
		return tList;
	}
	/**
	 * column and pk need to replace 
	 * @param tableName
	 * @return
	 */
	private List<Map<String, Object>> getColumnList(String tableName) {
		ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		DatabaseOracleUtil databaseUtil = (DatabaseOracleUtil) beanFactory
				.getBean("databaseOracleUtil");
		return databaseUtil.getColumnList(tableName);
		
	}

	private String getPKByTablename(String tableName) {
		ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		DatabaseOracleUtil databaseUtil = (DatabaseOracleUtil) beanFactory
				.getBean("databaseOracleUtil");
		return databaseUtil.getPk(tableName);
	}
}

