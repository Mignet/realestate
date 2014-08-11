package com.szhome.cq.dbcp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OracleTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		DatabaseOracleUtil du =(DatabaseOracleUtil)ctx.getBean("databaseOracleUtil");
		du.getColumnsByTablename("tree");
		System.out.println(du.getPk("tree"));
	}
}

