package com.szhome.cq.dbcp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySqlTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		DatabaseMysqlUtil du =(DatabaseMysqlUtil)ctx.getBean("databaseMysqlUtil");
		du.getColumnsByTablename("t_user");
		System.out.println(du.getPk("t_user"));
	}
}

