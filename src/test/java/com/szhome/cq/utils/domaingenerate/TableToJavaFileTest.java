package com.szhome.cq.utils.domaingenerate;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 生成数据库中的表所对应的modle对象的单元测试
 * @author Mignet
 *
 */
public class TableToJavaFileTest extends TestCase {

	private static final int SEC_TO_MIU = 1000;

	private ITableFacade aService;

	public void setUp() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		this.aService = (ITableFacade)ac.getBean("tableToJavaFileFacade");
//		wbfOptFacade = (IWbfOptFacade)ac.getBean("wbfOptFacade");
	}

	//生成数据库中所有的表的领域对象的java文件.
	//不需要生成全部文件时，可以注释掉该方法
	/*
	public void testCreateAllFiles() { 
		long start = System.currentTimeMillis();
		//传入包名.
		aService.createAllFiles("com.szhome.cq.domain.model");
		long end = System.currentTimeMillis();
		System.out.println("用时:       "+(end - start)/1000);  
	}
	*/
	//生成单个表的领域对象的java文件。 
	public void testCreateSomeFiles() {
		long start = System.currentTimeMillis();
		//传入表名与包名.

		//aService.createSomeFiles(new String[] {"SPE_REJECTION_INFO"}, "com.szhome.cq.domain.model");
		//aService.createSomeFiles(new String[] {"SPE_RESPITE_INFO"}, "com.szhome.cq.domain.model");
		//aService.createSomeFiles(new String[] {"SPE_SUSPEND_INFO"}, "com.szhome.cq.domain.model");
		//aService.createSomeFiles(new String[] {"SPE_DELAY"}, "com.szhome.cq.domain.model");
	      aService.createSomeFiles(new String[] {"bd_building"}, "com.szhome.cq.domain.model");
		//aService.createSomeFiles(new String[] {"BUS_MORTGAGE_REG"}, "com.szhome.cq.domain.model");
		//aService.createSomeFiles(new String[] {"CFIG_RECEIVAL"}, "com.szhome.cq.domain.model");


		long end = System.currentTimeMillis();
		System.out.println("用时:       " + (end - start) +"s");
	}


}

