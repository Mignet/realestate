package com.szhome.cq.utils.domaingenerate;

/**
 * ����java�ļ��ӿ�
 * @author  Mignet
 */
public interface ITableFacade {

	/*
	 * �������ݿ�һ�����domain����. 
	 */
	void createSomeFiles(String[] tableName,String packPath);
	/*
	 * �������ݿ����еı��domain����.
	 */
	void createAllFiles(String packPath);
}

