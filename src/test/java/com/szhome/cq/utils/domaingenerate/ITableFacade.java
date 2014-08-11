package com.szhome.cq.utils.domaingenerate;

/**
 * 生成java文件接口
 * @author  Mignet
 */
public interface ITableFacade {

	/*
	 * 生成数据库一个表的domain对象. 
	 */
	void createSomeFiles(String[] tableName,String packPath);
	/*
	 * 生成数据库所有的表的domain对象.
	 */
	void createAllFiles(String packPath);
}

