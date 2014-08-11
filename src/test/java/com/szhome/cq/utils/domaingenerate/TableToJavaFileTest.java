package com.szhome.cq.utils.domaingenerate;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * �������ݿ��еı�����Ӧ��modle����ĵ�Ԫ����
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

	//�������ݿ������еı����������java�ļ�.
	//����Ҫ����ȫ���ļ�ʱ������ע�͵��÷���
	/*
	public void testCreateAllFiles() { 
		long start = System.currentTimeMillis();
		//�������.
		aService.createAllFiles("com.szhome.cq.domain.model");
		long end = System.currentTimeMillis();
		System.out.println("��ʱ:       "+(end - start)/1000);  
	}
	*/
	//���ɵ��������������java�ļ��� 
	public void testCreateSomeFiles() {
		long start = System.currentTimeMillis();
		//������������.

		//aService.createSomeFiles(new String[] {"SPE_REJECTION_INFO"}, "com.szhome.cq.domain.model");
		//aService.createSomeFiles(new String[] {"SPE_RESPITE_INFO"}, "com.szhome.cq.domain.model");
		//aService.createSomeFiles(new String[] {"SPE_SUSPEND_INFO"}, "com.szhome.cq.domain.model");
		//aService.createSomeFiles(new String[] {"SPE_DELAY"}, "com.szhome.cq.domain.model");
	      aService.createSomeFiles(new String[] {"bd_building"}, "com.szhome.cq.domain.model");
		//aService.createSomeFiles(new String[] {"BUS_MORTGAGE_REG"}, "com.szhome.cq.domain.model");
		//aService.createSomeFiles(new String[] {"CFIG_RECEIVAL"}, "com.szhome.cq.domain.model");


		long end = System.currentTimeMillis();
		System.out.println("��ʱ:       " + (end - start) +"s");
	}


}

