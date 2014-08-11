/**
 * Project Name:dxtx_re
 * File Name:PresaleFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-4-2����11:51:43
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.business.impl;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.springjdbc.annotation.Page;
import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IPresaleFacade;
import com.szhome.cq.domain.model.Applicant;
import com.szhome.cq.domain.model.BusType;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.Certificate;
import com.szhome.cq.domain.model.Contract;
import com.szhome.cq.domain.model.PresaleLicense;
import com.szhome.cq.domain.model.PresaleReginfo;
import com.szhome.cq.domain.model.Reg_relationship;
import com.szhome.cq.utils.Util;
import com.szhome.cq.utils.WbfConstants;

/**
 * Ԥ��facade
 * Date:     2014-4-2 ����11:51:43 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class PresaleFacade implements IPresaleFacade {
	@Autowired
	private PresaleReginfo presaleReginfoDao;			//Ԥ����Ϣ
	@Autowired
	private Reg_relationship reg_relationshipDao; 		//�Ǽǵ�Ԫ������
	@Autowired
	private Contract contractDao;						//��ͬ��Ϣ 
	@Autowired
	private PresaleLicense presaleLicenseDao;						//��ͬ��Ϣ 
	
	/**
	 * 
	 * ��ȡԤ�۱�����Ϣ
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getPreSaleInfo(String proc_id){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,Object> paraMap = new HashMap<String,Object>();
		String reg_code = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getReg_code();
		paraMap.put("reg_code",reg_code);
		
		//1������Ԥ�۱�����Ϣ
		PresaleReginfo presaleReginfo =  savePresaleInfo(proc_id);
		if(presaleReginfo!=null){
			resultMap.put("rec_status", presaleReginfo.getRec_status());
		}
		
		//2����ȡԤ����Ȼ��Ϣ  
		Map<String,Object> preNatural = getPreNaturalInfo(paraMap);
		if(preNatural!=null && !preNatural.isEmpty()){
			resultMap.putAll(preNatural);
		}
		//3��Ԥ�ۺ�ͬ��Ϣ
		Map<String,Object>  preContract = getPreContractFrominterface(null);
		if(preContract!=null && !preContract.isEmpty()){
			resultMap.putAll(preContract);
		}
		//4����ȡԤ�۷���Ϣ
//		List<Map<String,Object>> preSaler = getPreSaler();
//		if(preSaler!=null && !preSaler.isEmpty()){
//			resultMap.put("preSaler",preSaler);
//		}
		//5����ȡԤ�����֤��Ϣ
		Map<String,Object> preSaleLic = getPreSaleLicFrominterface(null);
		if(preSaleLic!=null && !preSaleLic.isEmpty()){
			resultMap.putAll(preSaleLic);
		}
		
		
		
		
		return resultMap;
	}
	
	
	
	public List<Map<String,Object>> getPreSaler(String proc_id){
		//1���ȴӵ�ǰ��������ȡ   ����� ֱ�ӷ���   ����ӽӿڻ�ȡ�����浽���������˱���
		List<Applicant> appMapList = FacadeFactory.getApplicantFacade().getApplicantListByProcidAndHolrel(proc_id,WbfConstants.PRE_SALER);
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>(); 
		//��appList  ת��Ϊmap  ����
		if(appMapList!=null && !appMapList.isEmpty()){
			Map<String,Object> tmpAppMap = null;
			for(Applicant app:appMapList){
				tmpAppMap = new HashMap<String,Object>();
					try {
						object2MapWithoutNull(app,tmpAppMap);
					} catch (IllegalArgumentException e) {
						
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					} catch (IllegalAccessException e) {
						
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
				
				resultList.add(tmpAppMap);
			}
		}
		
		return resultList;
	}
	
	
	
	/**
	 * 
	 * getPreNaturalInfo:(��ȡԤ����Ȼ��Ϣ).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	private Map<String,Object> getPreNaturalInfo(Map<String,Object> paraMap){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,Object> naturalInfoMap = FacadeFactory.getRegisterFacade().getNaturalInfo(paraMap);
		
		
		if(naturalInfoMap != null && !naturalInfoMap.isEmpty()){
			resultMap = naturalInfoMap;
		}
		
		
		return resultMap;
	}
	
	/**
	 * 
	 * ����Ԥ�۵Ǽ���Ϣ�����ؿ�
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	private PresaleReginfo savePresaleInfo(String proc_id){
		BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
		String bus_id = businessMain.getBus_id();
		String reg_code = businessMain.getReg_code();
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		paraMap.put("reg_code", reg_code);
		
		
		
		
		//8���鿴Ԥ����Ϣ�����Ƿ�������   ���û����  �򱣴�һ�ݵ�Ԥ�۱���
		PresaleReginfo dbPresaleReginfo = presaleReginfoDao.get("where bus_id=(select bus_id from bus_main where proc_id=:proc_id)",paraMap);
		if(dbPresaleReginfo==null){
			dbPresaleReginfo = new PresaleReginfo();
			dbPresaleReginfo.setPre_rec_id(presaleReginfoDao.getSeqId());
			dbPresaleReginfo.setBus_id(bus_id);
			
			Map<String,Object> naturalInfoMap = FacadeFactory.getRegisterFacade().getNaturalInfo(paraMap);
			if(naturalInfoMap!=null && !naturalInfoMap.isEmpty()){
				dbPresaleReginfo.setFlatlet_id(naturalInfoMap.get("ADV_HOUSE_CODE").toString());
				dbPresaleReginfo.setRel_estate_name(naturalInfoMap.get("PRO_NAME").toString());
				dbPresaleReginfo.setRec_status("��");
				presaleReginfoDao.save(dbPresaleReginfo);
			}
		}
		
		
		return dbPresaleReginfo;
	}
	
	/**
	 * 
	 * Ԥ�۱�������ǰ���ʱ����� ����һ���г��ӿ������ݵ�����
	 *
	 * @author Joyon
	 * @param proc_id
	 * @since JDK 1.6
	 */
	public void savePresaleTodb(String proc_id) throws BusinessException{
		BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
		Map paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id", businessMain.getBus_id());
		paraMap.put("proc_id", proc_id);
		//ͨ���г�ϵͳ�ӿ�  ��ȡ��ϢȻ�󱣴浽����
		//1������Ԥ�ۺ�ͬ��Ϣ
		Contract contract = getPreContractSaveTodb(paraMap);
		//2������Ԥ�����֤��Ϣ
			getPreSaleLicSaveTodb();
		//3��������Ŀ��Ϣ
			getProjectSaveTodb();
		//4�����淿����Ϣ
			getHouseSaveTodb();
		//5������Ԥ����
			getPrebyerSaveTodb(paraMap);
		//6������Ԥ�۷���Ϣ
			getPresalerSaveTodb(paraMap);
		//7���޸��г�ϵͳ�ķ���״̬Ϊ�������С�
			setPreSaleState(proc_id, "������");
			
		//8����Ԥ�ۺ�ͬ�ű���� Ԥ�۵Ǽ���Ϣ����
			String con_no = contract.getCon_no();
			//�޸�Ԥ�۱�����Ϣ  ״̬
			PresaleReginfo presaleReginfo = getPresaleReginfoByProcId(proc_id);
			if(presaleReginfo!=null){
				presaleReginfo.setCon_no(con_no);
				presaleReginfoDao.update(presaleReginfo);
				System.out.println("�޸�Ԥ����Ϣ�ǼǱ� Ԥ�ۺ�ͬ��");
			}
		//9 ���淿�ز�֤��Ϣ 
			saveCertificate(paraMap);
	}

	//1������Ԥ�۷���Ϣ������
	private void getPresalerSaveTodb(Map paraMap) {
		
		String bus_id = paraMap.get("bus_id").toString();
		
		List<Map<String,Object>> resultList = getPreSalerFrominterface(null);
		Applicant applicant = null;
		for(Map<String,Object> tmpMap:resultList){
			//��Map ת��ΪApplicant����
			try {
				applicant =(Applicant)convertMapToObj(Applicant.class,tmpMap);
				
			} catch (IntrospectionException e) {
				
				e.printStackTrace();
				
			} catch (IllegalAccessException e) {
				
				e.printStackTrace();
				
			} catch (InstantiationException e) {
				
				e.printStackTrace();
				
			} catch (InvocationTargetException e) {
				
				e.printStackTrace();
				
			}
			
			if(applicant!=null){
				applicant.setBus_id(bus_id);
				applicant.setHol_rel(WbfConstants.PRE_SALER);
				FacadeFactory.getApplicantFacade().saveApplicant(applicant);
			}
		}
		System.out.println("1������Ԥ�۷���Ϣ�����ݿ�");
	}
	//2������Ԥ����������
	private void getPrebyerSaveTodb(Map paraMap) {
		String bus_id = paraMap.get("bus_id").toString();
		//�ӽӿڻ�ȡԤ����   
		List<Map<String,Object>> resultList = getPreByerFrominterface(null);
		Applicant applicant = null;
		for(Map<String,Object> tmpMap:resultList){
			//��Map ת��ΪApplicant����
			try {
				applicant =(Applicant)convertMapToObj(Applicant.class,tmpMap);
				
			} catch (IntrospectionException e) {
				
				e.printStackTrace();
				
			} catch (IllegalAccessException e) {
				
				e.printStackTrace();
				
			} catch (InstantiationException e) {
				
				e.printStackTrace();
				
			} catch (InvocationTargetException e) {
				
				e.printStackTrace();
				
			}
			
			if(applicant!=null){
				applicant.setBus_id(bus_id);
				applicant.setHol_rel(WbfConstants.PRE_BUYER);
				FacadeFactory.getApplicantFacade().saveApplicant(applicant);
			}
		}
		System.out.println("2������Ԥ������Ϣ�����ݿ⵽���ݿ�");
	}
	//3�����淿����Ϣ������
	private void getHouseSaveTodb() {
		Map houseMap = getPresaleHouseFrominterface(null);
		// TODO Auto-generated method stub
		
		System.out.println("3�����淿����Ϣ�����ݿ�");
	}
	//4��������Ŀ��Ϣ������
	private void getProjectSaveTodb() {
		
		// TODO Auto-generated method stub
		System.out.println("4��������Ŀ��Ϣ�����ݿ�");
	}
	
	/**
	 * 
	 * ����Ǽǵ�Ԫ  ҵ��ID����֤��  ����֤������
	 *
	 * @author Joyon
	 * @param paraMap
	 * @since JDK 1.6
	 */
	private void saveCertificate(Map paraMap){
		String bus_id = paraMap.get("bus_id").toString();
		String proc_id = paraMap.get("proc_id").toString();
		String reg_unit_code = "";
		Certificate certificate = new Certificate();
		List reg_relationshipList = FacadeFactory.getCommonFacade().getReg_relationshipListByProcId(proc_id);
		if(reg_relationshipList.size()>0){
			reg_unit_code = ((Reg_relationship)reg_relationshipList.get(0)).getReg_unit_code();
		}
		certificate.setReg_unit_code(reg_unit_code);
		certificate.setBus_id(bus_id);
		
		FacadeFactory.getCertificateFacade().saveCertificate(certificate);
		//δʵ��   ����ҵ��ID   �Ǽǵ�Ԫ���뵽��֤��
		System.out.println("0�����淿�ز���Ϣ");
	}
	//5������Ԥ�����֤��Ϣ������
	private void getPreSaleLicSaveTodb() {
		
		PresaleLicense presaleLicense = new PresaleLicense();
		//�ӽӿڻ�ȡԤ�����֤��Ϣ
		Map presaleLicMap = getPreSaleLicFrominterface(null);
		//������Ϣ��Ԥ�����֤ʵ����
		presaleLicenseDao.copyProperties(presaleLicense, presaleLicMap);
		presaleLicense.setReg_no(Double.valueOf(presaleLicenseDao.getSeqId()));
		//����
		presaleLicenseDao.save(presaleLicense);
		
		System.out.println("5������Ԥ�����֤��Ϣ�����ݿ�");
	}
	//6������Ԥ�ۺ�ͬ��Ϣ������
	private Contract getPreContractSaveTodb(Map paraMap) {
		//�ӽӿ��л�ȡԤ�ۺ�ͬ��Ϣ 
		Map contractMap = getPreContractFrominterface(paraMap);
		Contract contract = new Contract();
		//�ѽӿ�����Ϣ���Ƶ���ͬ��Ϣʵ����
		contractDao.copyProperties(contract, contractMap);
		contract.setId(contractDao.getSeqId());
		contractDao.save(contract);
		// TODO Auto-generated method stub
		System.out.println("6������Ԥ�ۺ�ͬ��Ϣ");
		return contract;
	}
	
	
	/**
	 * 
	 * �ӽӿڻ�ȡԤ�۷���Ϣ
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	private List<Map<String,Object>> getPreSalerFrominterface(Map paraMap){
		//String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("app_name", "��Ƽ���");
		resultMap.put("legal_name", "�����");
		resultMap.put("app_cer_type", "Ӫҵִ��");
		resultMap.put("app_cer_no", "232312132123");
		
		resultMap.put("agent_name", "����");
		resultMap.put("agent_cer_type", "���֤");
		resultMap.put("agent_cer", "32121213212");
		resultMap.put("agent_tel", "13222222222");
		resultList.add(resultMap);
		
		
//		
		return resultList;
	}
	
	/**
	 * 
	 * �ӽӿڻ�ȡԤ�۷���Ϣ
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return list<Map>
	 * @since JDK 1.6
	 */
	private List<Map<String,Object>> getPreByerFrominterface(Map paraMap){
		List resultList = new ArrayList();
		Map<String,Object> tmpMap = new HashMap<String,Object>();
		tmpMap.put("applicant_id", "1");
		tmpMap.put("bus_id", "2");
		tmpMap.put("app_cer_type", "1");
		tmpMap.put("app_name", "���");
		tmpMap.put("app_type", "1");
		tmpMap.put("legal_name", "��ľ");
		tmpMap.put("app_cer_no", "1142324234242");
		tmpMap.put("app_port", "100%");
		tmpMap.put("app_address", "�����и�����");
		tmpMap.put("app_tel", "13111111111");
		tmpMap.put("agent_name", "");
		tmpMap.put("agent_cer", "");
		tmpMap.put("agent_tel", "");
		tmpMap.put("agent_cer_type", "");
		resultList.add(tmpMap);
		
		tmpMap = new HashMap<String,Object>();
		tmpMap.put("applicant_id", "1");
		tmpMap.put("bus_id", "2");
		tmpMap.put("app_cer_type", "1");
		tmpMap.put("app_name", "��˪");
		tmpMap.put("app_type", "1");
		tmpMap.put("legal_name", "�Ŷ�");
		tmpMap.put("app_cer_no", "1142324234242");
		tmpMap.put("app_port", "100%");
		tmpMap.put("app_address", "�������޺���");
		tmpMap.put("app_tel", "13111111111");
		tmpMap.put("agent_name", "");
		tmpMap.put("agent_cer", "");
		tmpMap.put("agent_tel", "");
		tmpMap.put("agent_cer_type", "");
		resultList.add(tmpMap);
		
		tmpMap = new HashMap<String,Object>();
		tmpMap.put("applicant_id", "1");
		tmpMap.put("bus_id", "2");
		tmpMap.put("app_cer_type", "1");
		tmpMap.put("app_name", "����");
		tmpMap.put("app_type", "1");
		tmpMap.put("legal_name", "����");
		tmpMap.put("app_cer_no", "1142324234242");
		tmpMap.put("app_port", "100%");
		tmpMap.put("app_address", "������������");
		tmpMap.put("app_tel", "13111111111");
		tmpMap.put("agent_name", "");
		tmpMap.put("agent_cer", "");
		tmpMap.put("agent_tel", "");
		tmpMap.put("agent_cer_type", "");
		resultList.add(tmpMap);
		
		return resultList;
	}
	
	/**
	 * 
	 * �ӽӿڻ�ȡԤ�ۺ�ͬ��Ϣ
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	private Map<String,Object> getPreContractFrominterface(Map map){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("con", com.szhome.cq.utils.DateUtils.getCurDateStr()+new Random().nextInt());
		resultMap.put("presale", "��Ƽ���");
		resultMap.put("prebuy","����");
		
//		resultMap.put("con_type", "Ԥ�ۺ�ͬ");
//		resultMap.put("con__date", new Date());
//		resultMap.put("con_district", "�޺���");
//		resultMap.put("pre_lic_no", "ysskzh00000001");
//		resultMap.put("con_area", "100");
//		resultMap.put("con_unit_price", "10000");
//		resultMap.put("con_price", "1000000");
//		resultMap.put("con_price_cn", "һ����");
//		resultMap.put("con_shift_method", "һ����");
//		resultMap.put("con_house_property", "��Ʒ��");
//		resultMap.put("CAD", new Date());
//		resultMap.put("is_equitable_mortgage", "¥������");
//		resultMap.put("is_need_notarize", "��֤");
//		resultMap.put("currency", "RMB");
//		resultMap.put("exchange_rate", "����");
//		resultMap.put("down-payment", "300000");
//		resultMap.put("payment_method", "�ֽ�");
//		resultMap.put("mortgage_years", "7��");
//		resultMap.put("enter_time", "new Date()");
//		resultMap.put("is_new_con", "��");
//		resultMap.put("is_need_add", "��");
//		resultMap.put("is_inspection", "��");
//		resultMap.put("join_qualification", "�����뻧�ʸ�");
//		resultMap.put("join_certificate_number", "��");
//		resultMap.put("join_date", "new Date()");
//		resultMap.put("join_number", "4");
//		resultMap.put("export", "��");
//		resultMap.put("cancel_person", "����Ա");
//		resultMap.put("cancel_date", new Date());
//		resultMap.put("cancel_reason", "������");
//		resultMap.put("con_no", "con0000000001");
		
		resultMap.put("con_type", "Ԥ�ۺ�ͬ");
		resultMap.put("con_date", new Date());
		resultMap.put("con_area", "�޺���");
		resultMap.put("pre_sale_no", "ysskzh00000001");
		resultMap.put("con_areas", Float.valueOf((float) 100.00));
		resultMap.put("con_unit_price", "10000");
		resultMap.put("con_pric", "1000000");
		resultMap.put("con_price_cn", "һ����");
		resultMap.put("shift_mode", "���ַ�");
		resultMap.put("house_buy_pro", "��Ʒ��");
		resultMap.put("con_sign_date", new Date());
		resultMap.put("is_mortgage", "¥������");
		resultMap.put("is_nota", "��֤");
		resultMap.put("currency", "RMB");
		resultMap.put("exchange_rate", "����");
		resultMap.put("down_payment", "300000");
		resultMap.put("payment_method", "�ֽ�");
		resultMap.put("mortgage_years", "7��");
		resultMap.put("occupation_date", new Date());
		resultMap.put("is_new_contract", "��");
		resultMap.put("is_sup_contract", "��");
		resultMap.put("is_cer", "��");
		resultMap.put("home_buyers_qua", "�����뻧�ʸ�");
		resultMap.put("home_buyers_cer_no", "��");
		resultMap.put("home_buyers_cer_date", new Date());
		resultMap.put("home_buyers_number", "4");
		resultMap.put("within_export", "��");
		resultMap.put("cancellation_managers", "����Ա");
		resultMap.put("cancellation_date", new Date());
		resultMap.put("cancellation_reason", "������");
		resultMap.put("con_no", "con0000000001");
		
		return resultMap;
	}
	
	/**
	 * 
	 * �ӽӿڻ�ȡԤ�����֤��Ϣ 
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	private Map<String,Object> getPreSaleLicFrominterface(Map paraMap){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("cerno", com.szhome.cq.utils.DateUtils.getCurDateStr()+new Random().nextInt());
		
		resultMap.put("PRE_CON_NO", "Ԥ�ۺ�ͬ��");
		resultMap.put("PRE_LIC_NO", "Ԥ�����֤��");
		resultMap.put("HOLDER_ID", Double.valueOf("100000.00"));
		resultMap.put("PRO_LOCATION", "��Ŀλ��");
		resultMap.put("PARCEL_NO", "�ڵغ�");
		resultMap.put("LAND_GRA_CON", "���س��ú�ͬ");
		resultMap.put("DEV_FIEM_NO", "������ҵ����֤���");
		resultMap.put("APPRO_PRE_AREA", Float.valueOf("100.0"));
		resultMap.put("APPRO_PRO_BUILD", "��׼Ԥ��¥��");
		resultMap.put("REMARK", "��ע");
		resultMap.put("PRE_LIC_TEXT", "Ԥ�����֤�ı�");
		return resultMap;
	}
	
	/**
	 * 
	 * �ӽӿڻ�ȡԤ�۵ķ�����Ϣ
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	private Map<String,Object> getPresaleHouseFrominterface(Map paraMap){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("reg_unit_code", "���ݱ��");
		resultMap.put("con_no", "��ͬ���");
		resultMap.put("flat_usage", "סլ");
		resultMap.put("area", "�޺���");
		resultMap.put("PRO_NAME", "Ǳ����԰");
		resultMap.put("parcel_address", "�޺����������22��");
		resultMap.put("build", "¥��");
		resultMap.put("build_no", "1");
		resultMap.put("ROOMNAME", "707");
		resultMap.put("BUILD_AREA", "108");
		resultMap.put("pre_con_no", "yshth000000001");
		resultMap.put("con_sign_date", new Date());
		resultMap.put("is_record", "��");
		
		
		//Ȩ������Ϣ
		List<Map> holderMapList = new ArrayList<Map>();
		Map holderMap = new HashMap();
		
		holderMap.put("APP_NAME", "¥��");
		holderMap.put("APP_CER_NO", "1");
		holderMap.put("APP_CER_TYPE", "707");
		holderMap.put("LEGAL_NAME", "108");
		holderMap.put("LEGAL_CER_NO", "yshth000000001");
		holderMap.put("APP_PORT", "707");
		holderMap.put("APP_TYPE", "707");
		
		holderMapList.add(holderMap);
		
		
		resultMap.put("holder", holderMapList);
		
		return resultMap;
	}
	
	/**
	 * 
	 * ��ȡ�г��ӿڻ�ȡ��ǰ���ز���״̬
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getRealEstateStateFrominterface(String reg_unit_code){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		resultMap.put("reg_unit_code", reg_unit_code);
		resultMap.put("state", "����");
		
		return resultMap;
	}
	
	/**
	 * 
	 * �����г��ӿ��޸ķ��ز�״̬
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @param state
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> setRealEstateStateFrominterface(String reg_unit_code,String state){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		resultMap.put("reg_unit_code", reg_unit_code);
		resultMap.put("state", state);
		return resultMap;
	}
	
	
	/**
	 * 
	 * ����Ԥ����Ϣ
	 *
	 * @author Joyon
	 * @param presaleReginfo
	 * @since JDK 1.6
	 */
	public void savePresaleReginfo(PresaleReginfo presaleReginfo){
		presaleReginfo.setPre_rec_id(presaleReginfoDao.getSeqId());
		presaleReginfoDao.save(presaleReginfo);
	}
	/**
	 * 
	 * ����Ԥ����Ϣ
	 *
	 * @author Joyon
	 * @param presaleReginfo
	 * @since JDK 1.6
	 */
	public void updatePresaleReginfo(PresaleReginfo presaleReginfo){
		PresaleReginfo dbPresaleReginfo = presaleReginfoDao.get(presaleReginfo);
		presaleReginfoDao.copyProperties(presaleReginfo, presaleReginfo);
		presaleReginfoDao.update(dbPresaleReginfo);
		
	}
	
	/**
     * ��һ�� Map ����ת��Ϊһ�� JavaBean
     * @param type Ҫת��������
     * @param map ��������ֵ�� map
     * @return ת�������� JavaBean ����
     * @throws IntrospectionException �������������ʧ��
     * @throws IllegalAccessException ���ʵ���� JavaBean ʧ��
     * @throws InstantiationException ���ʵ���� JavaBean ʧ��
     * @throws InvocationTargetException ����������Ե� setter ����ʧ��
     */ 
    public static Object convertMapToObj(Class type, Map map) 
            throws IntrospectionException, IllegalAccessException, 
            InstantiationException, InvocationTargetException { 
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // ��ȡ������ 
        Object obj = type.newInstance(); // ���� JavaBean ���� 
        // �� JavaBean ��������Ը�ֵ 
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
        for (int i = 0; i< propertyDescriptors.length; i++) { 
            PropertyDescriptor descriptor = propertyDescriptors[i]; 
            String propertyName = descriptor.getName(); 
            if (map.containsKey(propertyName)) { 
                // ����һ����� try ������������һ�����Ը�ֵʧ�ܵ�ʱ��Ͳ���Ӱ���������Ը�ֵ�� 
                Object value = map.get(propertyName); 
                Object[] args = new Object[1]; 
                args[0] = value; 
                descriptor.getWriteMethod().invoke(obj, args); 
            } 
        } 
        return obj; 
    } 
    
    
    /*
	 * ������ת����Map
	 */
	public static void object2MapWithoutNull(Object obj, Map map)throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int j = 0; j < fields.length; j++) {
			fields[j].setAccessible(true);// ����������������з���Ȩ�޼�� ���������õı�������Ϊprivate
			if (fields[j].get(obj) != null
				&& (((fields[j].get(obj) instanceof String) && !""
				.equals(fields[j].get(obj))) || ((fields[j]
				.get(obj) instanceof Integer)))) {
				map.put(fields[j].getName(), fields[j].get(obj));
			}
		}
	}
	
	/**
	 * 
	 * setPreSaleState:(�޸ı���״̬  Ϊ������).
	 *
	 * @author Joyon
	 * @since JDK 1.6
	 */
	public void setPreSaleState(String proc_id,String state) throws BusinessException{
		Reg_relationship reg_relationship = FacadeFactory.getCommonFacade().getReg_relationshipByProcId(proc_id);
		//��ȡ��ǰҵ��ķ��ز� �Ǽǵ�Ԫ��� 
		String reg_unit_code =reg_relationship.getReg_unit_code();
		if( state == null ||state.trim().equals("") ){
			state="��";
			//���Ԥ�۱���ҵ��   �޸ı���״̬Ϊ�����
			BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
			String bus_type_id = businessMain.getReg_type();
			if(bus_type_id.equals(WbfConstants.CANCEL_SALE_BAKUP)){
				state = "�����";
			}
		}
		
		//�޸�Ԥ�۱�����Ϣ  ״̬
		PresaleReginfo presaleReginfo = getPresaleReginfoByProcId(proc_id);
		if(presaleReginfo!=null){
			presaleReginfo.setRec_status(state);
			presaleReginfoDao.update(presaleReginfo);
		}
		//�޸ĵǼǵ�Ԫ�������в�Ȩ״̬��;ҵ��Ϊ��Ч
		if(state.equals("��") || state.equals("�����")){
			
			if(reg_relationship!=null){
				reg_relationship.setReg_state(WbfConstants.EFFECTIVE);
				reg_relationshipDao.update(reg_relationship);
			}
		}
		
		//�޸��г�ϵͳ�ķ���״̬
		setRealEstateStateFrominterface(reg_unit_code,state);
		System.out.println("���ýӿڡ�����    ����״̬���޸�:"+state);
	}
	
	/**
	 * 
	 * ��ȡԤ����Ϣ
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public PresaleReginfo getPresaleReginfoByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		PresaleReginfo presaleReginfo = presaleReginfoDao.get("where bus_id=(select bus_id from bus_main where proc_id=:proc_id)",paraMap);
		return presaleReginfo;
	}
	
	/**
	 * 
	 * �ж�Ԥ�۱���״̬�Ƿ��Ѿ��Ǳ���״̬ 
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public boolean isRecorded(String proc_id) throws BusinessException{
		boolean result = false;
		PresaleReginfo presaleReginfo = getPresaleReginfoByProcId(proc_id);
		if(presaleReginfo != null){
			if(presaleReginfo.getRec_status().equals("��") || presaleReginfo.getRec_status().equals("�����")){
				result =true;
			}
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getPresalesBypreRecId(Map<String,Object> sqlMap,
			Map<String, Object> valueMap) {
		/*String wheresql ="where 1=1 " + sqlMap.get("mortId").toString();
		return presaleReginfoDao.queryMapListByKey("Mortgage.getMortgageandContractInfos", wheresql, valueMap);*/
		boolean a102 = false;
		boolean a103 = false;
		if(Util.isNotNull2Empty(valueMap.get("subpreRecId0"))){
			a102 = true;
		}
		if(Util.isNotNull2Empty(valueMap.get("subpreRecId1"))){
			a103 = true;
		}
		List<Map<String, Object>> datas = new ArrayList<Map<String,Object>>();
		Map<String,Object> data1 = new HashMap<String,Object>();
		data1.put("PRE_REC_ID", "102");
		data1.put("PRO_NAME", "ͩ�ֹ�Ԣ");
		data1.put("PRE_LIC_NO","����֣�2011������002��");
		data1.put("CON_NO","��(��)��Ԥ����(2011)1863��");
		data1.put("ACON_DATE","2014-04-24");
		data1.put("AREC_DATE","2014-05-24");
		data1.put("PRESALER","��Ƽ���");
		data1.put("PREBUYER","����,����");
		data1.put("PARCEL_CODE", "parcel-00003");
		data1.put("LAND_ADDRESS", "���������ﶫ·");
		data1.put("BUILDING_NAME", "ͩ�ֹ�Ԣ");
		data1.put("BUILD_NO", "B��");
		data1.put("ROOMNAME", "303��");
		data1.put("HOUSE_TYPE", "������������");
		data1.put("FLATLET_USAGE", "סլ");
		data1.put("CON_PRIC", "5400000");
		data1.put("YBUILD_AREA", "125");
		data1.put("YTAONEI_AREA", "120");
		data1.put("BUILD_AREA", "121");
		data1.put("TAONEI_AREA", "116");
		data1.put("FT_GLEBE_AREA", "5");
		data1.put("REC_NOTE", "����,���ķ򸾷��޹�ͬ�Ʋ�����");
		data1.put("REC_STATE","��");
		if(a102)
		datas.add(data1);
		Map<String,Object> data2 = new HashMap<String,Object>();
		data2.put("PRE_REC_ID", "105");
		data2.put("PRO_NAME", "�ȿƻ�԰");
		data2.put("PRE_LIC_NO","����֣�2011������003��");
		data2.put("PRE_CON_NO","��(��)��Ԥ����(2011)1864��");
		data2.put("ACON_DATE","2013-04-24");
		data2.put("AREC_DATE","2014-05-24");
		data2.put("PRESALER","������");
		data2.put("PREBUYER","����,����");
		data2.put("PARCEL_CODE", "parcel-00003");
		data2.put("LAND_ADDRESS", "���������ﶫ·");
		data2.put("BUILDING_NAME", "ͩ�ֹ�Ԣ");
		data2.put("BUILD_NO", "B��");
		data2.put("ROOMNAME", "303��");
		data2.put("FLATLET_USAGE", "סլ");
		data2.put("HOUSE_TYPE", "��������");
		data2.put("CON_PRIC", "5400000");
		data2.put("YBUILD_AREA", "125");
		data2.put("YTAONEI_AREA", "120");
		data2.put("BUILD_AREA", "121");
		data2.put("TAONEI_AREA", "116");
		data2.put("FT_GLEBE_AREA", "5");
		data2.put("REC_NOTE", "����,���ķ򸾷��޹�ͬ�Ʋ�����");
		data2.put("REC_STATE","��");
		if(a103)
		datas.add(data2);
		return datas;
	}

	@Override
	public Page<Map<String, Object>> getAllPresalesByParam(
			Map<String, Object> paramMap) {
		Page<Map<String,Object>> rtnPag = null;
		List<Map<String, Object>> datas = new ArrayList<Map<String,Object>>();
		Map<String,Object> data1 = new HashMap<String,Object>();
		data1.put("PRE_REC_ID", "102");
		data1.put("PRO_NAME", "ͩ�ֹ�Ԣ");
		data1.put("PRE_LIC_NO","����֣�2011������002��");
		data1.put("PRE_CON_NO","��(��)��Ԥ����(2011)1863��");
		data1.put("ACON_DATE","2014-04-24");
		data1.put("AREC_DATE","2014-05-24");
		data1.put("PRESALER","��Ƽ���");
		data1.put("PREBUYER","����,����");
		data1.put("REC_STATE","��");
		Map<String,Object> data2 = new HashMap<String,Object>();
		data2.put("PRE_REC_ID", "105");
		data2.put("PRO_NAME", "�ȿƻ�԰");
		data2.put("PRE_LIC_NO","����֣�2011������003��");
		data2.put("PRE_CON_NO","��(��)��Ԥ����(2011)1864��");
		data2.put("ACON_DATE","2013-04-24");
		data2.put("AREC_DATE","2014-05-24");
		data2.put("PRESALER","������");
		data2.put("PREBUYER","����,����");
		data2.put("REC_STATE","��");
		datas.add(data1);
		datas.add(data2);
		rtnPag = new Page<Map<String,Object>>(datas, 1, 6, 2);
		return rtnPag;
	}
	
	/**
	 * 
	 * ��ȡԤ������Ϣ
	 *
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public List getPreBuyerInfo(String proc_id){
		
		
		List resultList = new ArrayList();
		resultList = FacadeFactory.getApplicantFacade().getApplicantListByProcidAndHolrel(proc_id, WbfConstants.PRE_BUYER);
//		Map<String,Object> tmpMap = new HashMap<String,Object>();
//		tmpMap.put("applicant_id", "1");
//		tmpMap.put("bus_id", "2");
//		tmpMap.put("app_cer_type", "1");
//		tmpMap.put("app_name", "���");
//		tmpMap.put("app_type", "1");
//		tmpMap.put("legal_name", "��ľ");
//		tmpMap.put("app_cer_no", "1142324234242");
//		tmpMap.put("app_port", "100%");
//		tmpMap.put("app_address", "�����и�����");
//		tmpMap.put("app_tel", "13111111111");
//		tmpMap.put("agent_name", "");
//		tmpMap.put("agent_cer", "");
//		tmpMap.put("agent_tel", "");
//		tmpMap.put("agent_cer_type", "");
//		resultList.add(tmpMap);
//		
//		tmpMap = new HashMap<String,Object>();
//		tmpMap.put("applicant_id", "1");
//		tmpMap.put("bus_id", "2");
//		tmpMap.put("app_cer_type", "1");
//		tmpMap.put("app_name", "��˪");
//		tmpMap.put("app_type", "1");
//		tmpMap.put("legal_name", "�Ŷ�");
//		tmpMap.put("app_cer_no", "1142324234242");
//		tmpMap.put("app_port", "100%");
//		tmpMap.put("app_address", "�������޺���");
//		tmpMap.put("app_tel", "13111111111");
//		tmpMap.put("agent_name", "");
//		tmpMap.put("agent_cer", "");
//		tmpMap.put("agent_tel", "");
//		tmpMap.put("agent_cer_type", "");
//		resultList.add(tmpMap);
//		
//		tmpMap = new HashMap<String,Object>();
//		tmpMap.put("applicant_id", "1");
//		tmpMap.put("bus_id", "2");
//		tmpMap.put("app_cer_type", "1");
//		tmpMap.put("app_name", "����");
//		tmpMap.put("app_type", "1");
//		tmpMap.put("legal_name", "����");
//		tmpMap.put("app_cer_no", "1142324234242");
//		tmpMap.put("app_port", "100%");
//		tmpMap.put("app_address", "������������");
//		tmpMap.put("app_tel", "13111111111");
//		tmpMap.put("agent_name", "");
//		tmpMap.put("agent_cer", "");
//		tmpMap.put("agent_tel", "");
//		tmpMap.put("agent_cer_type", "");
//		resultList.add(tmpMap);
		
		return resultList;
	}
}


