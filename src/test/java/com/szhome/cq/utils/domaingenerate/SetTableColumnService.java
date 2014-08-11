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
 * ��װ������ֶη�����
 * @author  Mignet
 */
@Scope("prototype")
@Component
public class SetTableColumnService {

	@Autowired
	private TableColumnBean columnBean;

	/**
	 * ��װ������ֶ�
	 * @param listTables
	 * @param packPath
	 * @return 
	 * List<IFormatedTableBean>
	 */
	public List<IFormatedTableBean> combinationTables(List<TableBean> listTables,String packPath){

		List<IFormatedTableBean> tList = new ArrayList<IFormatedTableBean>();
		for (TableBean table : listTables) {

			columnBean.setTable_name(table.getTable_name());

			//��ȡÿ���������
			List<Map<String, Object>> pList =new ArrayList<Map<String,Object>>();//this.columnBean.queryMapListByKey("Table.selectPrimaryKey",this.columnBean);
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("column_name", getPKByTablename(table.getTable_name()));
			pList.add(m);
			table.setPrimaryKeyList(pList);

			//��ȡ���ÿ���ֶ���Ϣ
			List<Map<String, Object>> clist = getColumnList(table.getTable_name());//this.columnBean.queryMapListByKey("Table.selectColumn",this.columnBean);
			table.setTableColumnBeanList(clist);
			table.setPackageInfom(packPath);
			
			//��ȡ����ֶα�ע
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

