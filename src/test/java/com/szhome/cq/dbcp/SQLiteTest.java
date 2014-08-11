package com.szhome.cq.dbcp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SQLiteTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		DatabaseSqliteUtil du =(DatabaseSqliteUtil)ctx.getBean("databaseSqliteUtil");
		du.getColumnsByTablename("t_role");
	}
}

