package com.szhome.cq.utils.domaingenerate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 生成java文件接口的实现类
 * @author  Mignet
 */
@Scope("prototype")
@Service
public class TableToJavaFileFacade implements ITableFacade {

	private TableBean table = new TableBean();

	@Autowired
	private SetTableColumnService setTableColumnService;

	@Autowired
	private FileFactoryService fileFactoryService;

	private List<TableBean> listTables = new ArrayList<TableBean>();

	@Override
	@Transactional
	public void createSomeFiles(String[] tableName,String packPath) {
		for(String tname:tableName){
			this.table.setTable_name(tname);
			//listTables = this.tables.queryListByKey("Table.selectSomeTables",this.tables);
			listTables.add(table);
			createFile(packPath);
		}
	}

	@Override
	@Transactional
	public void createAllFiles(String packPath){
		//listTables = this.tables.queryListByKey("Table.selectAllTables", null);
		createFile(packPath);
	}

	private void createFile(String packPath){
		if(!listTables.isEmpty()){
			List<IFormatedTableBean> formatList = setTableColumnService.combinationTables(listTables, packPath);
			fileFactoryService.createFile(formatList);
		}
	}
}

