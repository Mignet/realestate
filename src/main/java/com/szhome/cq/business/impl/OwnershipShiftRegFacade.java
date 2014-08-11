/**
 * Project Name:dxtx_re
 * File Name:OwnershipShiftRegFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-1-22下午3:51:30
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.business.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IOwnershipShiftRegFacade;
import com.szhome.cq.domain.model.BusOwnership;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.Bususeright;
import com.szhome.cq.domain.model.Certificate;
import com.szhome.cq.domain.model.House;
import com.szhome.cq.domain.model.Land;

/**
 * 所有权转移登记facade
 * Date:     2014-1-22 下午3:51:30 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class OwnershipShiftRegFacade implements IOwnershipShiftRegFacade {
	@Autowired
	private House houseDao;											//房屋
	
	@Autowired
	private BusOwnership busOwnershipDao;							//所有权登记信息
	@Autowired
	private Land landDao;											//宗地信息
	@Autowired
	private Certificate certificateDao;								//房地产证信息
	@Autowired
	private BusinessMain businessMainDao;							//业务主表
	@Autowired
	private Bususeright bususerightDao;								//业务主表
	
	
	
	/**
	 * 
	 * getRegInfoMapByProcId:(获取登记信息). 
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getRegInfoMapByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		paraMap = houseDao.queryMapByKey("OwnershipShiftReg.getRegInfoMapByProcId", paraMap);
		Map<String,Object> ownershipMap = getOwnershipInfoMapByProcId(proc_id);
		Map<String,Object> certificateMap = FacadeFactory.getCertificateFacade().getCertificateMapByProcId(proc_id);
		if(ownershipMap != null && !ownershipMap.isEmpty()){
			paraMap.putAll(ownershipMap);
		}else{
			LogUtil.error("所有权登记信息为空！");
		}
		if(certificateMap!=null &&  !certificateMap.isEmpty()){
			paraMap.putAll(certificateMap);
		}else{
			LogUtil.error("房地产证信息为空！");
		}
		
		return paraMap;
	}
	
	/**
	 * 
	 * getLandShiftRegInfoMapByProcId:(获取使用权登记信息). 
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getLandShiftRegInfoMapByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		paraMap = FacadeFactory.getCommonFacade().getBusMainInfoMapByProcId(proc_id);
		Map<String,Object> userrigghtMap =  getBusUserightInfoMapByProcId(proc_id);
		Map<String,Object> certificateMap = FacadeFactory.getCertificateFacade().getCertificateMapByProcId(proc_id);
		if(userrigghtMap != null && !userrigghtMap.isEmpty()){
			paraMap.putAll(userrigghtMap);
		}else{
			LogUtil.error("所有权登记信息为空！");
		}
		if(certificateMap!=null &&  !certificateMap.isEmpty()){
			paraMap.putAll(certificateMap);
		}else{
			LogUtil.error("房地产证信息为空！");
		}
		return paraMap;
	}
	
	
	/**
	 * 
	 * getOwnershipInfoMapByProcId:(通过流程实例ID获取所有权登记信息)
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Map<String,Object> getOwnershipInfoMapByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		paraMap = houseDao.queryMapByKey("OwnershipShiftReg.getOwnershipInfoMapByProcId", paraMap);
		return paraMap;
	}
	
	/**
	 * 
	 * getBusUserightInfoMapByProcId:(通过流程实例ID获取使用权登记信息)
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Map<String,Object> getBusUserightInfoMapByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		paraMap = houseDao.queryMapByKey("OwnershipShiftReg.getUserightInfoMapByProcId", paraMap);
		return paraMap;
	}

	/*
	 * 保存登记信息      保存所有权登记信息   保存房地产证附记 保存登记点
	 */
	public Map<String,Object> saveRegInfo(Map<String,Object> paraMap) {
		BusOwnership busOwnership = (BusOwnership) paraMap.get("busOwnership");
		String bus_id=busOwnership.getBus_id();
		String proc_id = paraMap.get("proc_id").toString();
//		String reg_station = null;
//		if(paraMap.get("reg_station")!=null){
//			reg_station = paraMap.get("reg_station").toString();
//		}
		String reg_value = null;
		if(paraMap.get("reg_value")!=null){
			reg_value = paraMap.get("reg_value").toString();
		}
		String get_mode = null;
		if(paraMap.get("get_mode")!=null){
			get_mode = paraMap.get("get_mode").toString();
		}
		String house_attr = null;
		if(paraMap.get("house_attr")!=null){
			house_attr = paraMap.get("house_attr").toString();
		}
		String location_reg_unit = null;
		if(paraMap.get("location_reg_unit")!=null){
			location_reg_unit = paraMap.get("location_reg_unit").toString();
		}
		
		Map<String,Object> paraMap1 = new HashMap<String,Object>();
		paraMap1.put("bus_id", busOwnership.getBus_id());
		BusOwnership dbBusOwnership = busOwnershipDao.get("where bus_id=:bus_id",paraMap1);
		
		if(paraMap.get("excursus").toString().length()>0){
			String excursus = paraMap.get("excursus").toString();
			FacadeFactory.getCertificateFacade().saveorupdateExcursus(bus_id, excursus);
//			//保存房地产证附记
//			Certificate certificate = null;
//			certificate  = certificateDao.get("where bus_id=(select bus_id from bus_main where proc_id=:proc_id)", paraMap);
//			if(certificate == null){
//				certificate = new Certificate();
//				certificate.setCertificate_id(certificateDao.getSeqId());
//				certificate.setExcursus(paraMap.get("excursus").toString());
//				certificate.setBus_id(busOwnership.getBus_id());
//				certificateDao.save(certificate);
//			}else{
//				certificate.setExcursus(paraMap.get("excursus").toString());
//				certificateDao.update(certificate);
//			}
		}
		//如果数据中无数据  早进行新增   保存数据   如果 有 则更新  三个数据
		if(dbBusOwnership == null){
			
			//数据库里无数据  则是第一次进来    页面传过来的只有登记价格  和转移方式  加bus_id  
			dbBusOwnership = busOwnership;
			dbBusOwnership.setOwner_reg_id(busOwnershipDao.getSeqId());
			House house = FacadeFactory.getCommonFacade().getHouseByProcId(proc_id);
			Land land = getLandByProcId(proc_id);
			
			//从宗地表中获取年限和日期
			dbBusOwnership.setStart_date(land.getStart_date());
			dbBusOwnership.setEnd_date(land.getEnd_date());
			dbBusOwnership.setLu_term(land.getUse_per());
			
			//从房屋表中获取用途和属性
			dbBusOwnership.setHouse_usage(house.getFlatlet_usage());
			dbBusOwnership.setHouse_attr(house_attr);
			
			busOwnershipDao.save(dbBusOwnership);
		}else{
			dbBusOwnership.setReg_value(Float.valueOf(reg_value));
			dbBusOwnership.setGet_mode(get_mode);
			dbBusOwnership.setHouse_attr(house_attr);
			busOwnershipDao.update(dbBusOwnership);
		}
	
		
		//保存登记点到业务主表 更新主表
		
		BusinessMain businessMain = businessMainDao.get("where proc_id = :proc_id", paraMap);
		if(businessMain != null){
			//businessMain.setReg_station(reg_station);
			businessMain.setLocation_reg_unit(location_reg_unit);
			businessMainDao.update(businessMain);
		}
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result","success");
		
		return resultMap;
	}
	
	/**
	 * 
	 * getLandByProcId:(通过流程实例ID获取宗地信息)
	 */
	private Land getLandByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		String whereSql = " where parcel_id=(select parcel_id from est_building where BUILDING_ID=(select BUILDING_ID from est_house where ADV_HOUSE_CODE=(select REG_UNIT_CODE from BUS_REGUNITREL where bus_id =(select bus_id from bus_main where proc_id =:proc_id))))";
		
		return landDao.get(whereSql, paraMap);
	}
	
	/**
	 * 
	 * saveLandShipRegInfo:(保存土地使用权转移登记信息)
	 *
	 * @author Joyon
	 * @param businessMain
	 * @param bususeright
	 * @param certificate
	 * @since JDK 1.6
	 */
	public void saveLandShipRegInfo(BusinessMain businessMain,Bususeright bususeright,Certificate certificate) throws BusinessException{
		String bus_id=businessMain.getBus_id();
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id", bus_id);
		
		if(certificate!=null){
			Certificate dbCertificate = certificateDao.get("where bus_id=:bus_id", paraMap);
			if(dbCertificate==null){
				certificate.setCertificate_id(certificateDao.getSeqId());
			}else{
				certificate.setCertificate_id(dbCertificate.getCertificate_id());
			}
		}
		saveCertificate(certificate);
		saveBususeright(bususeright);
		FacadeFactory.getCommonFacade().saveBusinessMain(businessMain);
	}
	 
	/**
	 * 
	 * saveBususeright:(保存使用权部分信息). 
	 *
	 * @author Joyon
	 * @param bususeright
	 * @since JDK 1.6
	 */
	public void saveBususeright(Bususeright bususeright){
		if(bususeright == null){
			return;
		}
		Bususeright dbBususeright = bususerightDao.get(bususeright);
		if(dbBususeright == null){
			if(bususeright.getUseright_reg_id()==null){
				bususeright.setUseright_reg_id(bususerightDao.getSeqId());
			}
			
			bususerightDao.save(bususeright);
		}else{
			bususerightDao.copyProperties(dbBususeright, bususeright);
			bususerightDao.update(dbBususeright);
		}
	}
	
	public void saveCertificate(Certificate certificate){
		if(certificate == null){
			return;
		}
		Certificate dbCertificate = certificateDao.get(certificate);
		if(dbCertificate == null){
			if(certificate.getCertificate_id()==null){
				certificate.setCertificate_id(certificateDao.getSeqId());
			}
			certificateDao.save(certificate);
		}else{
			certificateDao.copyProperties(dbCertificate, certificate);
			certificateDao.update(dbCertificate);
		}
	}
}


