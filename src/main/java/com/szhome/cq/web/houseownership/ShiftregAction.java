/**
 * Project Name:dxtx_re
 * File Name:ShiftregAction.java
 * Package Name:com.szhome.cq.web.personalOffice.houseOwnership.ownershipShiftReg
 * Date:2014-1-22上午11:46:33
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.web.houseownership;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plan.commons.Row;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.BusOwnership;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.Bususeright;
import com.szhome.cq.domain.model.Certificate;
import com.szhome.cq.utils.JsonUtil;

/**
 * ClassName:ShiftregAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-22 上午11:46:33 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ShiftregAction extends BaseDelegate{
	
	/**
	 * 
	 * getHolder:(获取权利人数据   转让方). 
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getHolder(Row row){
		//登记单元编号 
		String reg_unit_code = "";
		String proc_id = row.getString("proc_id");
		List<Map<String,Object>> resultList = null;
		try {
			reg_unit_code =FacadeFactory.getCommonFacade().getReg_relationshipByProcId(proc_id).getReg_unit_code();
			resultList = FacadeFactory.getRegisterFacade().getEffictiveHolderMapListByRegUnitCode(reg_unit_code,"");
		} catch (Exception e) {
			LogUtil.error("获取权利人数据出错，"+e.getMessage());
		}
		return JsonUtil.object2json(resultList);
				
	}
	
	public String getRegInfo(Row row){
		Map<String,Object> resultMap = null;
		String proc_id = row.getString("proc_id");
		try {
			resultMap = FacadeFactory.getOwnershipShiftRegFacade().getRegInfoMapByProcId(proc_id);
			
//			List<Map<String,Object>> naturalInfoMapList = new ArrayList<Map<String,Object>>();
//			if(naturalInfoMap != null && !naturalInfoMap.isEmpty()){
//				//naturalInfoMapList.add(naturalInfoMap);
//				//naturalInfoMap = new HashMap<String,Object>();
//				//naturalInfoMap.put("rows", naturalInfoMapList);
//				//naturalInfoMap.put("total", "1");
//				resultMap.put("naturalInfo",naturalInfoMap);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.object2json(resultMap);
	}
	
	public String getHouseInfo(Row row){
		String proc_id = row.getString("proc_id");
		String reg_code = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getReg_code();
		Map<String,Object> paraMap = new HashMap();
		paraMap.put("reg_code", reg_code);
		Map<String,Object> naturalInfoMap = null;
		try {
			naturalInfoMap = FacadeFactory.getRegisterFacade().getNaturalInfo(
					paraMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		resultList.add(naturalInfoMap);
		return JsonUtil.object2json(resultList);
	}
	
	public String saveRegInfo(Row row){
		String proc_id = row.getString("proc_id");
		String excursus = row.getString("excursus");
		String get_mode = row.getString("get_mode");
		String reg_value = row.getString("reg_value");
		String house_attr = row.getString("house_attr");
		String reg_station = row.getString("reg_station");
		String location_reg_unit = row.getString("location_reg_unit");
		String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id();
		BusOwnership busOwnership = new BusOwnership();
		if(excursus !=null && excursus.length()>0){
			busOwnership.setExcursus(excursus);
			busOwnership.setGet_mode(get_mode);
			busOwnership.setReg_value(Float.valueOf(reg_value));
			busOwnership.setHouse_attr(house_attr);
		}else{
			busOwnership.setGet_mode(get_mode);
			busOwnership.setReg_value(Float.valueOf(reg_value));
			busOwnership.setHouse_attr(house_attr);
		}
		
		busOwnership.setBus_id(bus_id);
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("busOwnership", busOwnership);
		paraMap.put("proc_id", proc_id);
		paraMap.put("reg_station", reg_station);
		paraMap.put("reg_value", reg_value);
		paraMap.put("get_mode", get_mode);
		paraMap.put("excursus", excursus);
		paraMap.put("house_attr",house_attr);
		paraMap.put("location_reg_unit",location_reg_unit);
		Map resultMap = null;
		try {
			 resultMap = FacadeFactory.getOwnershipShiftRegFacade()
					.saveRegInfo(paraMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * saveLandShiftRegInfo:(保存土地使用权登记信息).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String saveLandShiftRegInfo(Row row){
		String proc_id = row.getString("proc_id");
		String reg_value = row.getString("reg_value");
		String excursus = row.getString("excursus");
		String location_reg_unit = row.getString("location_reg_unit");
		BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
		String bus_id = businessMain.getBus_id();
		Bususeright bususeright = FacadeFactory.getCommonFacade().getBususerightByProcId(proc_id);
		
		//第一次保存操作
		if( bususeright == null){
			bususeright = new Bususeright();
			bususeright.setBus_id(bus_id);
			
		}
		bususeright.setGet_price(Double.valueOf(reg_value));
		Certificate certificate =  null;
		if(excursus!=null && excursus.length()>0){
			certificate = new Certificate();
			certificate.setExcursus(excursus);
			certificate.setBus_id(bus_id);
		}
		//保存所在区
		businessMain.setLocation_reg_unit(location_reg_unit);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result", "保存成功！");
		try {
			FacadeFactory.getOwnershipShiftRegFacade().saveLandShipRegInfo(
					businessMain, bususeright, certificate);
		} catch (Exception e) {
			LogUtil.error("ShiftregAction.saveLandShiftRegInfo():保存土地使用权登记信息出错"+e.getMessage());
			resultMap.put("result", "保存失败！后台处理数据出错。");
			
		}
			
		
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * getLandShiftRegInfo:获取土地使用权).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getLandShiftRegInfo(Row row){
		Map<String, Object> resultMap = null;
		String proc_id = row.getString("proc_id");
		try {
			resultMap = FacadeFactory.getOwnershipShiftRegFacade().getLandShiftRegInfoMapByProcId(proc_id);
		} catch (Exception e) {
			LogUtil.error("获取"+e.getMessage());
		}
		return JsonUtil.object2json(resultMap);
	}	

}


