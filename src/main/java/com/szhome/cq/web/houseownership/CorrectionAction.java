/**
 * Project Name:dxtx_re
 * File Name:acceptanceAction.java  更正登记申请表action
 * Package Name:com.szhome.cq.web.personalOffice.correction
 * Date:2014-1-22上午11:46:33
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.web.houseownership;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.plan.commons.Row;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.ChangeRecord;
import com.szhome.cq.utils.JsonUtil;
import com.szhome.cq.utils.Util;
import com.szhome.cq.utils.WbfConstants;

/**
 * ClassName:acceptanceAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-22 上午11:46:33 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CorrectionAction extends BaseDelegate{
	
	public String getRegInfo(Row row){
		Map<String,Object> resultMap = null;
		String proc_id = row.getString("proc_id");
		try {
			resultMap = FacadeFactory.getChangeRecordFacade().getRegInfoMapByProcId(proc_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * getHolder:(获取权利人数据   转让方). 
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getHolder(Row row){
		List<Map<String,Object>> resultList = null;
		String proc_id = row.getString("proc_id");
		try {
			resultList = FacadeFactory.getRegisterFacade().getHistoryHolderMapListByProcId(proc_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.object2json(resultList);
	}
	/**
	 * 
	 * getHolder:(获取权利人数据  受让方). 
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getNowHolder(Row row){
		List<Map<String,Object>> resultList = null;
		Map<String,Object> paramsMap = Util.ConvertObjToMap(row, "proc_id");
		try {
		     resultList = new ArrayList<Map<String,Object>>();
			 for(int i=0;i<3;i++){
				     Map<String,Object> holderMP = new HashMap<String,Object>();
				     holderMP.put("RIGHT_REL_ID", "6"+i);
				     holderMP.put("HOLDER_ID", "5"+i);
					 holderMP.put("HOL_NAME", "张三"+i);
					 holderMP.put("HOL_TYPE", "01900"+i);
					 holderMP.put("CER_TYPE", "06301"+i);
					 holderMP.put("CER_NO", "2556622332111"+i);
					 holderMP.put("POSITION", (1+i)+"0%");
					 holderMP.put("ADDRESS", "深圳市罗湖区北环大道102"+i+"号");
					 holderMP.put("LEGAL_NAME", "李四"+i);
					 holderMP.put("HOL_REL", "06310"+i);
					 holderMP.put("REG_UNIT_ID", "5"+i);
					 holderMP.put("REG_UNIT_TYPE", "00900"+i);
					 resultList.add(holderMP); 
			 }
			//resultList = FacadeFactory.getRegisterFacade().getCurrentHolderMapListByProcId(paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.object2json(resultList);
		
	}
	
	 /**
     * 
     * getBus:根据传入流程实例id获取申请人信息. <br/>
     *
     * @author PANDA
     * @return
     * @since JDK 1.6
     */
	public String getAppMessage(Row row){
		//-------ajax---------
		//获取流程实例表id
		//int proc_id = new Integer((String)this.getRequest().getParameter("proc_id"));
		String proc_id = row.getString("proc_id");
		String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
		String str = "";
		List<Map<String, Object>> apps = FacadeFactory.getApplicantFacade().getApptMessByLcslbid(bus_id);
		
		//如果申请人中表为空 则把权利人数据保存到申请表中   少一申请人手机号
		if(apps == null || apps.isEmpty()){
			try {
				FacadeFactory.getRegisterFacade().saveHistoryHolderToApp(
						proc_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			apps = FacadeFactory.getApplicantFacade().getApptMessByLcslbid(bus_id);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		//记录了查询总数
		map.put("total", apps.size());
		//记录了当前页的数据
		map.put("rows", apps);
		try {
			str = JsonUtil.object2json(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.setActionJsonObject(str.toLowerCase(Locale.CHINESE));

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
	
	
	//获取变更前信息
	public String getChangeOldInfo(Row row){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String proc_id = row.getString("proc_id");
		//获取自然信息
		String reg_code = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getReg_code();
		Map<String,Object> paraMap = new HashMap();
		paraMap.put("reg_code", reg_code);
		Map<String,Object> naturalInfoMap = null;
		try {
			naturalInfoMap = FacadeFactory.getRegisterFacade().getNaturalInfo(
					paraMap);
		} catch (Exception e) {
			LogUtil.error("自然信息 为空");
		}
		
		//获取权利人 
		List<Map<String,Object>> resultList = null;
		try {
			resultList = FacadeFactory.getRegisterFacade().getHistoryHolderMapListByProcId(proc_id);
		} catch (Exception e) {
			LogUtil.error("权利人信息  为空");
		}
		
		//登记单元关联表信息
		Map<String,Object> regunit_map = null;
		try {
			regunit_map = FacadeFactory.getRegisterFacade().getRegUnitRelMapByRegId(paraMap);
		} catch (Exception e) {
			LogUtil.error("获取登记单元关联表信息  为空");
		}
		String reg_unit_type = regunit_map.get("REG_UNIT_TYPE").toString();
		
		//获取登记簿所有权部分中信息
		Map<String,Object> regsiter_book_map = null;
		
		
		if(reg_unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
			try {
				regsiter_book_map = FacadeFactory.getRegisterFacade()
						.getEffectiveReg_OwnershipMapByProcId(proc_id);
			} catch (Exception e) {
				LogUtil.error("获取登记簿所有权部分信息  为空");
			}
			
		}else if(reg_unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
			try {
				regsiter_book_map = FacadeFactory.getRegisterFacade().getEffectiveReg_userightMapByProcId(proc_id);
				regsiter_book_map.put("REG_VALUE", regsiter_book_map.get("REG_PRI").toString());
			} catch (Exception e) {
				LogUtil.error("获取登记簿所有权部分信息  为空");
			}
		}
		
		resultMap.put("reg_unit_type", reg_unit_type);
		resultMap.put("regBookInfo", regsiter_book_map);
		resultMap.put("naturaInfo", naturalInfoMap);
		resultMap.put("holder", resultList);
		
		return JsonUtil.object2json(resultMap); 
	}
	
	/**
	 * 
	 * saveRegInfo:(保存登记信息). <br/>
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String saveRegInfo(Row row){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		String proc_id = row.getString("proc_id");
		String location_reg_unit = row.getString("location_reg_unit");
		//String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id();
		BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
		businessMain.setLocation_reg_unit(location_reg_unit);
		paraMap.put("proc_id", proc_id);
		Map resultMap = new HashMap();
		resultMap.put("result", "success");
		try {
			FacadeFactory.getCommonFacade().saveBusinessMain(businessMain);
		} catch (Exception e) {
			resultMap.put("result", "fail");
			LogUtil.error("AcceptanceAction.saveRegInfo() 保存或更新业务主表信息出错"+e.getMessage());
		}
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * saveExcursus:(保存房地产证附记).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String saveExcursus(Row row){
		String proc_id = row.getString("proc_id");
		String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id();
		String excursus = row.getString("excursus");
		
		try {
			FacadeFactory.getCertificateFacade().saveorupdateExcursus(bus_id, excursus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result","success");
		
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * getChangeRecord:(获取变更、更正记录数据). 
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getChangeRecord(Row row){
		String proc_id = row.getString("proc_id");
		List<Map<String,Object>> resultMapList = FacadeFactory.getChangeRecordFacade().getChangeRecordMapListByProcId(proc_id);
		
		return JsonUtil.object2json(resultMapList);
	}
	
	/**
	 * 
	 * applyEditChangeRecord:(保存编辑的变更记录数据).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String applyEditChangeRecord(Row row){
		String datas = row.getString("datas");
		String proc_id = row.getString("proc_id");
		Map map = new HashMap();
		
		map.put("datas",datas);
		map.put("bus_id", FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id());
		
		try {
			map = FacadeFactory.getChangeRecordFacade().applyEdit(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.object2json(map);
	}
	
	/**
	 * 
	 * saveChangeRecord:(保存变更数据项   获取当前业务下的    该code的数据项    比较下old_data  和new_data,如果存在   就更新   否则保存)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String saveChangeRecord(Row row){
		String code = row.getString("code");
		String old_data = row.getString("old_data");
		String new_data = row.getString("new_data");
		String proc_id = row.getString("proc_id");
		String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id();
		
		ChangeRecord changeRecord = null;
		
		
		//获取当前业务下的    该code的数据项    如果存在   更新    不存在  就保存
		List<ChangeRecord> changeRecordList = null;
		
		try{
			
			changeRecordList = FacadeFactory.getChangeRecordFacade().getChangeRecordListByCodeAndBusId(code,bus_id);
			if(changeRecordList != null  || !changeRecordList.isEmpty()){
				for(ChangeRecord temp : changeRecordList){
					if(temp.getOld_data().equals(old_data) || temp.getNew_data().equals(old_data)){
						changeRecord = temp;
					}
				}
			}
			
			if(changeRecord == null){
				changeRecord = new ChangeRecord();
				changeRecord.setChange_code(code);
				changeRecord.setOld_data(old_data);
				changeRecord.setNew_data(new_data);
				changeRecord.setBus_id(bus_id);
				FacadeFactory.getChangeRecordFacade().saveChangeRecord(changeRecord);
				
			}else{
				//changeRecord.setOld_data(old_data);
				changeRecord.setNew_data(new_data);
				
				FacadeFactory.getChangeRecordFacade().updateChangeRecord(changeRecord);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result", "success");
		
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * saveBKOwnerShipToBusOwnerShip:(保存前登记簿吕有交往的数据到登记信息中).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @since JDK 1.6
	 */
	public String saveBKOwnerShipToBusOwnerShip(Row row){
		String proc_id = row.getString("proc_id");
		FacadeFactory.getChangeRecordFacade().saveBKOwnerShipToBusOwnerShip(proc_id);
		return null;
	}
	
	/**
	 * 
	 * saveBKUserightShipToBusUserightShip:(保存前登记簿吕有交往的数据到登记信息中).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @since JDK 1.6
	 */
	public String saveBKUserightShipToBusUserightShip(Row row){
		String proc_id = row.getString("proc_id");
		FacadeFactory.getChangeRecordFacade().saveBKUserightShipToBusUserightShip(proc_id);
		return null;
	}

}


