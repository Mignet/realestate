/**
 * Project Name:dxtx_re
 * File Name:PresaleFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-4-2上午11:51:43
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
 * 预售facade
 * Date:     2014-4-2 上午11:51:43 <br/>
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
	private PresaleReginfo presaleReginfoDao;			//预售信息
	@Autowired
	private Reg_relationship reg_relationshipDao; 		//登记单元关联表
	@Autowired
	private Contract contractDao;						//合同信息 
	@Autowired
	private PresaleLicense presaleLicenseDao;						//合同信息 
	
	/**
	 * 
	 * 获取预售备案信息
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
		
		//1、保存预售备案信息
		PresaleReginfo presaleReginfo =  savePresaleInfo(proc_id);
		if(presaleReginfo!=null){
			resultMap.put("rec_status", presaleReginfo.getRec_status());
		}
		
		//2、获取预售自然信息  
		Map<String,Object> preNatural = getPreNaturalInfo(paraMap);
		if(preNatural!=null && !preNatural.isEmpty()){
			resultMap.putAll(preNatural);
		}
		//3、预售合同信息
		Map<String,Object>  preContract = getPreContractFrominterface(null);
		if(preContract!=null && !preContract.isEmpty()){
			resultMap.putAll(preContract);
		}
		//4、获取预售方信息
//		List<Map<String,Object>> preSaler = getPreSaler();
//		if(preSaler!=null && !preSaler.isEmpty()){
//			resultMap.put("preSaler",preSaler);
//		}
		//5、获取预售许可证信息
		Map<String,Object> preSaleLic = getPreSaleLicFrominterface(null);
		if(preSaleLic!=null && !preSaleLic.isEmpty()){
			resultMap.putAll(preSaleLic);
		}
		
		
		
		
		return resultMap;
	}
	
	
	
	public List<Map<String,Object>> getPreSaler(String proc_id){
		//1、先从当前申请人中取   如果有 直接返回   无则从接口获取并保存到本地申请人表中
		List<Applicant> appMapList = FacadeFactory.getApplicantFacade().getApplicantListByProcidAndHolrel(proc_id,WbfConstants.PRE_SALER);
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>(); 
		//把appList  转换为map  返回
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
	 * getPreNaturalInfo:(获取预售自然信息).
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
	 * 保存预售登记信息到本地库
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
		
		
		
		
		//8、查看预售信息表中是否有数据   如果没数据  则保存一份到预售表中
		PresaleReginfo dbPresaleReginfo = presaleReginfoDao.get("where bus_id=(select bus_id from bus_main where proc_id=:proc_id)",paraMap);
		if(dbPresaleReginfo==null){
			dbPresaleReginfo = new PresaleReginfo();
			dbPresaleReginfo.setPre_rec_id(presaleReginfoDao.getSeqId());
			dbPresaleReginfo.setBus_id(bus_id);
			
			Map<String,Object> naturalInfoMap = FacadeFactory.getRegisterFacade().getNaturalInfo(paraMap);
			if(naturalInfoMap!=null && !naturalInfoMap.isEmpty()){
				dbPresaleReginfo.setFlatlet_id(naturalInfoMap.get("ADV_HOUSE_CODE").toString());
				dbPresaleReginfo.setRel_estate_name(naturalInfoMap.get("PRO_NAME").toString());
				dbPresaleReginfo.setRec_status("否");
				presaleReginfoDao.save(dbPresaleReginfo);
			}
		}
		
		
		return dbPresaleReginfo;
	}
	
	/**
	 * 
	 * 预售备案受理前审核时需调用 保存一份市场接口中数据到本地
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
		//通过市场系统接口  提取信息然后保存到本地
		//1、保存预售合同信息
		Contract contract = getPreContractSaveTodb(paraMap);
		//2、保存预售许可证信息
			getPreSaleLicSaveTodb();
		//3、保存项目信息
			getProjectSaveTodb();
		//4、保存房屋信息
			getHouseSaveTodb();
		//5、保存预购方
			getPrebyerSaveTodb(paraMap);
		//6、保存预售方信息
			getPresalerSaveTodb(paraMap);
		//7、修改市场系统的房屋状态为“备案中”
			setPreSaleState(proc_id, "备案中");
			
		//8、把预售合同号保存进 预售登记信息表中
			String con_no = contract.getCon_no();
			//修改预售备案信息  状态
			PresaleReginfo presaleReginfo = getPresaleReginfoByProcId(proc_id);
			if(presaleReginfo!=null){
				presaleReginfo.setCon_no(con_no);
				presaleReginfoDao.update(presaleReginfo);
				System.out.println("修改预售信息登记表 预售合同号");
			}
		//9 保存房地产证信息 
			saveCertificate(paraMap);
	}

	//1、保存预售方信息到本地
	private void getPresalerSaveTodb(Map paraMap) {
		
		String bus_id = paraMap.get("bus_id").toString();
		
		List<Map<String,Object>> resultList = getPreSalerFrominterface(null);
		Applicant applicant = null;
		for(Map<String,Object> tmpMap:resultList){
			//将Map 转换为Applicant对象
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
		System.out.println("1、保存预售方信息到数据库");
	}
	//2、保存预购方到本地
	private void getPrebyerSaveTodb(Map paraMap) {
		String bus_id = paraMap.get("bus_id").toString();
		//从接口获取预购方   
		List<Map<String,Object>> resultList = getPreByerFrominterface(null);
		Applicant applicant = null;
		for(Map<String,Object> tmpMap:resultList){
			//将Map 转换为Applicant对象
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
		System.out.println("2、保存预购方信息到数据库到数据库");
	}
	//3、保存房屋信息到本地
	private void getHouseSaveTodb() {
		Map houseMap = getPresaleHouseFrominterface(null);
		// TODO Auto-generated method stub
		
		System.out.println("3、保存房屋信息到数据库");
	}
	//4、保存项目信息到本地
	private void getProjectSaveTodb() {
		
		// TODO Auto-generated method stub
		System.out.println("4、保存项目信息到数据库");
	}
	
	/**
	 * 
	 * 保存登记单元  业务ID到缮证表  在缮证环节用
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
		//未实现   保存业务ID   登记单元编码到缮证表
		System.out.println("0、保存房地产信息");
	}
	//5、保存预售许可证信息到本地
	private void getPreSaleLicSaveTodb() {
		
		PresaleLicense presaleLicense = new PresaleLicense();
		//从接口获取预售许可证信息
		Map presaleLicMap = getPreSaleLicFrominterface(null);
		//复制信息到预售许可证实体中
		presaleLicenseDao.copyProperties(presaleLicense, presaleLicMap);
		presaleLicense.setReg_no(Double.valueOf(presaleLicenseDao.getSeqId()));
		//保存
		presaleLicenseDao.save(presaleLicense);
		
		System.out.println("5、保存预售许可证信息到数据库");
	}
	//6、保存预售合同信息到本地
	private Contract getPreContractSaveTodb(Map paraMap) {
		//从接口中获取预售合同信息 
		Map contractMap = getPreContractFrominterface(paraMap);
		Contract contract = new Contract();
		//把接口中信息复制到合同信息实体中
		contractDao.copyProperties(contract, contractMap);
		contract.setId(contractDao.getSeqId());
		contractDao.save(contract);
		// TODO Auto-generated method stub
		System.out.println("6、保存预售合同信息");
		return contract;
	}
	
	
	/**
	 * 
	 * 从接口获取预售方信息
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	private List<Map<String,Object>> getPreSalerFrominterface(Map paraMap){
		//String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("app_name", "万科集团");
		resultMap.put("legal_name", "张万科");
		resultMap.put("app_cer_type", "营业执照");
		resultMap.put("app_cer_no", "232312132123");
		
		resultMap.put("agent_name", "李四");
		resultMap.put("agent_cer_type", "身份证");
		resultMap.put("agent_cer", "32121213212");
		resultMap.put("agent_tel", "13222222222");
		resultList.add(resultMap);
		
		
//		
		return resultList;
	}
	
	/**
	 * 
	 * 从接口获取预售方信息
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
		tmpMap.put("app_name", "李果");
		tmpMap.put("app_type", "1");
		tmpMap.put("legal_name", "张木");
		tmpMap.put("app_cer_no", "1142324234242");
		tmpMap.put("app_port", "100%");
		tmpMap.put("app_address", "深圳市福田区");
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
		tmpMap.put("app_name", "无霜");
		tmpMap.put("app_type", "1");
		tmpMap.put("legal_name", "张二");
		tmpMap.put("app_cer_no", "1142324234242");
		tmpMap.put("app_port", "100%");
		tmpMap.put("app_address", "深圳市罗湖区");
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
		tmpMap.put("app_name", "程土");
		tmpMap.put("app_type", "1");
		tmpMap.put("legal_name", "张三");
		tmpMap.put("app_cer_no", "1142324234242");
		tmpMap.put("app_port", "100%");
		tmpMap.put("app_address", "深圳市盐田区");
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
	 * 从接口获取预售合同信息
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	private Map<String,Object> getPreContractFrominterface(Map map){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("con", com.szhome.cq.utils.DateUtils.getCurDateStr()+new Random().nextInt());
		resultMap.put("presale", "万科集团");
		resultMap.put("prebuy","张力");
		
//		resultMap.put("con_type", "预售合同");
//		resultMap.put("con__date", new Date());
//		resultMap.put("con_district", "罗湖区");
//		resultMap.put("pre_lic_no", "ysskzh00000001");
//		resultMap.put("con_area", "100");
//		resultMap.put("con_unit_price", "10000");
//		resultMap.put("con_price", "1000000");
//		resultMap.put("con_price_cn", "一百万");
//		resultMap.put("con_shift_method", "一百万");
//		resultMap.put("con_house_property", "商品房");
//		resultMap.put("CAD", new Date());
//		resultMap.put("is_equitable_mortgage", "楼花按揭");
//		resultMap.put("is_need_notarize", "公证");
//		resultMap.put("currency", "RMB");
//		resultMap.put("exchange_rate", "汇率");
//		resultMap.put("down-payment", "300000");
//		resultMap.put("payment_method", "现金");
//		resultMap.put("mortgage_years", "7年");
//		resultMap.put("enter_time", "new Date()");
//		resultMap.put("is_new_con", "否");
//		resultMap.put("is_need_add", "否");
//		resultMap.put("is_inspection", "是");
//		resultMap.put("join_qualification", "购房入户资格");
//		resultMap.put("join_certificate_number", "否");
//		resultMap.put("join_date", "new Date()");
//		resultMap.put("join_number", "4");
//		resultMap.put("export", "否");
//		resultMap.put("cancel_person", "管理员");
//		resultMap.put("cancel_date", new Date());
//		resultMap.put("cancel_reason", "己到期");
//		resultMap.put("con_no", "con0000000001");
		
		resultMap.put("con_type", "预售合同");
		resultMap.put("con_date", new Date());
		resultMap.put("con_area", "罗湖区");
		resultMap.put("pre_sale_no", "ysskzh00000001");
		resultMap.put("con_areas", Float.valueOf((float) 100.00));
		resultMap.put("con_unit_price", "10000");
		resultMap.put("con_pric", "1000000");
		resultMap.put("con_price_cn", "一百万");
		resultMap.put("shift_mode", "二手房");
		resultMap.put("house_buy_pro", "商品房");
		resultMap.put("con_sign_date", new Date());
		resultMap.put("is_mortgage", "楼花按揭");
		resultMap.put("is_nota", "公证");
		resultMap.put("currency", "RMB");
		resultMap.put("exchange_rate", "汇率");
		resultMap.put("down_payment", "300000");
		resultMap.put("payment_method", "现金");
		resultMap.put("mortgage_years", "7年");
		resultMap.put("occupation_date", new Date());
		resultMap.put("is_new_contract", "否");
		resultMap.put("is_sup_contract", "否");
		resultMap.put("is_cer", "是");
		resultMap.put("home_buyers_qua", "购房入户资格");
		resultMap.put("home_buyers_cer_no", "否");
		resultMap.put("home_buyers_cer_date", new Date());
		resultMap.put("home_buyers_number", "4");
		resultMap.put("within_export", "否");
		resultMap.put("cancellation_managers", "管理员");
		resultMap.put("cancellation_date", new Date());
		resultMap.put("cancellation_reason", "己到期");
		resultMap.put("con_no", "con0000000001");
		
		return resultMap;
	}
	
	/**
	 * 
	 * 从接口获取预售许可证信息 
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	private Map<String,Object> getPreSaleLicFrominterface(Map paraMap){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("cerno", com.szhome.cq.utils.DateUtils.getCurDateStr()+new Random().nextInt());
		
		resultMap.put("PRE_CON_NO", "预售合同号");
		resultMap.put("PRE_LIC_NO", "预售许可证号");
		resultMap.put("HOLDER_ID", Double.valueOf("100000.00"));
		resultMap.put("PRO_LOCATION", "项目位置");
		resultMap.put("PARCEL_NO", "宗地号");
		resultMap.put("LAND_GRA_CON", "土地出让合同");
		resultMap.put("DEV_FIEM_NO", "开发企业资质证书号");
		resultMap.put("APPRO_PRE_AREA", Float.valueOf("100.0"));
		resultMap.put("APPRO_PRO_BUILD", "批准预售楼栋");
		resultMap.put("REMARK", "备注");
		resultMap.put("PRE_LIC_TEXT", "预售许可证文本");
		return resultMap;
	}
	
	/**
	 * 
	 * 从接口获取预售的房产信息
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	private Map<String,Object> getPresaleHouseFrominterface(Map paraMap){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("reg_unit_code", "房屋编号");
		resultMap.put("con_no", "合同编号");
		resultMap.put("flat_usage", "住宅");
		resultMap.put("area", "罗湖区");
		resultMap.put("PRO_NAME", "潜龙花园");
		resultMap.put("parcel_address", "罗湖区北环大道22号");
		resultMap.put("build", "楼栋");
		resultMap.put("build_no", "1");
		resultMap.put("ROOMNAME", "707");
		resultMap.put("BUILD_AREA", "108");
		resultMap.put("pre_con_no", "yshth000000001");
		resultMap.put("con_sign_date", new Date());
		resultMap.put("is_record", "否");
		
		
		//权利人信息
		List<Map> holderMapList = new ArrayList<Map>();
		Map holderMap = new HashMap();
		
		holderMap.put("APP_NAME", "楼栋");
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
	 * 调取市场接口获取当前房地产的状态
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getRealEstateStateFrominterface(String reg_unit_code){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		resultMap.put("reg_unit_code", reg_unit_code);
		resultMap.put("state", "正常");
		
		return resultMap;
	}
	
	/**
	 * 
	 * 调用市场接口修改房地产状态
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
	 * 保存预售信息
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
	 * 更新预售信息
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
     * 将一个 Map 对象转化为一个 JavaBean
     * @param type 要转化的类型
     * @param map 包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InstantiationException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */ 
    public static Object convertMapToObj(Class type, Map map) 
            throws IntrospectionException, IllegalAccessException, 
            InstantiationException, InvocationTargetException { 
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性 
        Object obj = type.newInstance(); // 创建 JavaBean 对象 
        // 给 JavaBean 对象的属性赋值 
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
        for (int i = 0; i< propertyDescriptors.length; i++) { 
            PropertyDescriptor descriptor = propertyDescriptors[i]; 
            String propertyName = descriptor.getName(); 
            if (map.containsKey(propertyName)) { 
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。 
                Object value = map.get(propertyName); 
                Object[] args = new Object[1]; 
                args[0] = value; 
                descriptor.getWriteMethod().invoke(obj, args); 
            } 
        } 
        return obj; 
    } 
    
    
    /*
	 * 将对象转换成Map
	 */
	public static void object2MapWithoutNull(Object obj, Map map)throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int j = 0; j < fields.length; j++) {
			fields[j].setAccessible(true);// 设置这个变量不进行访问权限检查 在类里设置的变量可以为private
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
	 * setPreSaleState:(修改备案状态  为己备案).
	 *
	 * @author Joyon
	 * @since JDK 1.6
	 */
	public void setPreSaleState(String proc_id,String state) throws BusinessException{
		Reg_relationship reg_relationship = FacadeFactory.getCommonFacade().getReg_relationshipByProcId(proc_id);
		//获取当前业务的房地产 登记单元编号 
		String reg_unit_code =reg_relationship.getReg_unit_code();
		if( state == null ||state.trim().equals("") ){
			state="是";
			//解除预售备案业务   修改备案状态为己解除
			BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
			String bus_type_id = businessMain.getReg_type();
			if(bus_type_id.equals(WbfConstants.CANCEL_SALE_BAKUP)){
				state = "己解除";
			}
		}
		
		//修改预售备案信息  状态
		PresaleReginfo presaleReginfo = getPresaleReginfoByProcId(proc_id);
		if(presaleReginfo!=null){
			presaleReginfo.setRec_status(state);
			presaleReginfoDao.update(presaleReginfo);
		}
		//修改登记单元关联表中产权状态在途业务为有效
		if(state.equals("是") || state.equals("己解除")){
			
			if(reg_relationship!=null){
				reg_relationship.setReg_state(WbfConstants.EFFECTIVE);
				reg_relationshipDao.update(reg_relationship);
			}
		}
		
		//修改市场系统的房屋状态
		setRealEstateStateFrominterface(reg_unit_code,state);
		System.out.println("调用接口。。。    房屋状态己修改:"+state);
	}
	
	/**
	 * 
	 * 获取预售信息
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
	 * 判断预售备案状态是否已经是备案状态 
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
			if(presaleReginfo.getRec_status().equals("是") || presaleReginfo.getRec_status().equals("己解除")){
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
		data1.put("PRO_NAME", "桐林公寓");
		data1.put("PRE_LIC_NO","深房许字（2011）福田002号");
		data1.put("CON_NO","深(福)网预买字(2011)1863号");
		data1.put("ACON_DATE","2014-04-24");
		data1.put("AREC_DATE","2014-05-24");
		data1.put("PRESALER","万科集团");
		data1.put("PREBUYER","王五,李四");
		data1.put("PARCEL_CODE", "parcel-00003");
		data1.put("LAND_ADDRESS", "福田区福田东路");
		data1.put("BUILDING_NAME", "桐林公寓");
		data1.put("BUILD_NO", "B栋");
		data1.put("ROOMNAME", "303室");
		data1.put("HOUSE_TYPE", "三室两厅三卫");
		data1.put("FLATLET_USAGE", "住宅");
		data1.put("CON_PRIC", "5400000");
		data1.put("YBUILD_AREA", "125");
		data1.put("YTAONEI_AREA", "120");
		data1.put("BUILD_AREA", "121");
		data1.put("TAONEI_AREA", "116");
		data1.put("FT_GLEBE_AREA", "5");
		data1.put("REC_NOTE", "王五,李四夫妇夫妻共同财产备案");
		data1.put("REC_STATE","是");
		if(a102)
		datas.add(data1);
		Map<String,Object> data2 = new HashMap<String,Object>();
		data2.put("PRE_REC_ID", "105");
		data2.put("PRO_NAME", "先科花园");
		data2.put("PRE_LIC_NO","深房许字（2011）福田003号");
		data2.put("PRE_CON_NO","深(福)网预买字(2011)1864号");
		data2.put("ACON_DATE","2013-04-24");
		data2.put("AREC_DATE","2014-05-24");
		data2.put("PRESALER","华润集团");
		data2.put("PREBUYER","赵六,张三");
		data2.put("PARCEL_CODE", "parcel-00003");
		data2.put("LAND_ADDRESS", "福田区福田东路");
		data2.put("BUILDING_NAME", "桐林公寓");
		data2.put("BUILD_NO", "B栋");
		data2.put("ROOMNAME", "303室");
		data2.put("FLATLET_USAGE", "住宅");
		data2.put("HOUSE_TYPE", "三室两厅");
		data2.put("CON_PRIC", "5400000");
		data2.put("YBUILD_AREA", "125");
		data2.put("YTAONEI_AREA", "120");
		data2.put("BUILD_AREA", "121");
		data2.put("TAONEI_AREA", "116");
		data2.put("FT_GLEBE_AREA", "5");
		data2.put("REC_NOTE", "王五,李四夫妇夫妻共同财产备案");
		data2.put("REC_STATE","是");
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
		data1.put("PRO_NAME", "桐林公寓");
		data1.put("PRE_LIC_NO","深房许字（2011）福田002号");
		data1.put("PRE_CON_NO","深(福)网预买字(2011)1863号");
		data1.put("ACON_DATE","2014-04-24");
		data1.put("AREC_DATE","2014-05-24");
		data1.put("PRESALER","万科集团");
		data1.put("PREBUYER","王五,李四");
		data1.put("REC_STATE","是");
		Map<String,Object> data2 = new HashMap<String,Object>();
		data2.put("PRE_REC_ID", "105");
		data2.put("PRO_NAME", "先科花园");
		data2.put("PRE_LIC_NO","深房许字（2011）福田003号");
		data2.put("PRE_CON_NO","深(福)网预买字(2011)1864号");
		data2.put("ACON_DATE","2013-04-24");
		data2.put("AREC_DATE","2014-05-24");
		data2.put("PRESALER","华润集团");
		data2.put("PREBUYER","赵六,张三");
		data2.put("REC_STATE","是");
		datas.add(data1);
		datas.add(data2);
		rtnPag = new Page<Map<String,Object>>(datas, 1, 6, 2);
		return rtnPag;
	}
	
	/**
	 * 
	 * 获取预购方信息
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
//		tmpMap.put("app_name", "李果");
//		tmpMap.put("app_type", "1");
//		tmpMap.put("legal_name", "张木");
//		tmpMap.put("app_cer_no", "1142324234242");
//		tmpMap.put("app_port", "100%");
//		tmpMap.put("app_address", "深圳市福田区");
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
//		tmpMap.put("app_name", "无霜");
//		tmpMap.put("app_type", "1");
//		tmpMap.put("legal_name", "张二");
//		tmpMap.put("app_cer_no", "1142324234242");
//		tmpMap.put("app_port", "100%");
//		tmpMap.put("app_address", "深圳市罗湖区");
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
//		tmpMap.put("app_name", "程土");
//		tmpMap.put("app_type", "1");
//		tmpMap.put("legal_name", "张三");
//		tmpMap.put("app_cer_no", "1142324234242");
//		tmpMap.put("app_port", "100%");
//		tmpMap.put("app_address", "深圳市盐田区");
//		tmpMap.put("app_tel", "13111111111");
//		tmpMap.put("agent_name", "");
//		tmpMap.put("agent_cer", "");
//		tmpMap.put("agent_tel", "");
//		tmpMap.put("agent_cer_type", "");
//		resultList.add(tmpMap);
		
		return resultList;
	}
}


