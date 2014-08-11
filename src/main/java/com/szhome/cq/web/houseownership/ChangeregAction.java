/**
 * Project Name:dxtx_re
 * File Name:acceptanceAction.java
 * Package Name:com.szhome.cq.web.personalOffice.houseOwnership.ownershipShiftReg
 * Date:2014-1-22����11:46:33
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
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.BusOwnership;
import com.szhome.cq.domain.model.Certificate;
import com.szhome.cq.domain.model.ChangeRecord;
import com.szhome.cq.utils.JsonUtil;

/**
 * ClassName:acceptanceAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-22 ����11:46:33 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ChangeregAction extends BaseDelegate{
	
	/**
	 * 
	 * getHolder:(��ȡȨ��������   ת�÷�). 
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
     * getAppMessage:���ݴ�������ʵ��id��ȡ��������Ϣ. <br/>
     *
     * @author PANDA
     * @return
     * @since JDK 1.6
     */
	public String getAppMessage(Row row){
		//-------ajax---------
		//��ȡ����ʵ����id
		//int proc_id = new Integer((String)this.getRequest().getParameter("proc_id"));
		String proc_id = row.getString("proc_id");
		String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
		String str = "";
		List<Map<String, Object>> apps = FacadeFactory.getApplicantFacade().getApptMessByLcslbid(bus_id);
		
		//����������б�Ϊ�� ���Ȩ�������ݱ��浽�������   ��һ�������ֻ���
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
		//��¼�˲�ѯ����
		map.put("total", apps.size());
		//��¼�˵�ǰҳ������
		map.put("rows", apps);
		try {
			str =JsonUtil.object2json(map);
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
	
	
	//��ȡ���ǰ��Ϣ
	public String getChangeOldInfo(Row row){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String proc_id = row.getString("proc_id");
		//��ȡ��Ȼ��Ϣ
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
		
		//��ȡȨ���� 
		List<Map<String,Object>> resultList = null;
		try {
			resultList = FacadeFactory.getRegisterFacade().getHistoryHolderMapListByProcId(proc_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//��ȡ�Ǽǲ�����Ϣ
		Map<String,Object> regsiter_book_map = null;
		try {
			regsiter_book_map = FacadeFactory.getRegisterFacade()
					.getLastRegOnwershipMapByProcId(proc_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		resultMap.put("regBookInfo", regsiter_book_map);
		resultMap.put("naturaInfo", naturalInfoMap);
		resultMap.put("holder", resultList);
		
		return JsonUtil.object2json(resultMap); 
	}
	
	public String saveRegInfo(Row row){
		String proc_id = row.getString("proc_id");
		String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id();
		String get_mode = row.getString("get_mode");
		String reg_value = row.getString("reg_value");
		String excursus = row.getString("excursus");
		String reg_station = row.getString("reg_station");
		BusOwnership busOwnership = new BusOwnership();
		if(excursus!=null && excursus!=""){
			busOwnership.setExcursus(excursus);
		}else{
			busOwnership.setGet_mode(get_mode);
			busOwnership.setReg_value(Float.valueOf(reg_value));
		}
		
		busOwnership.setBus_id(bus_id);
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("busOwnership", busOwnership);
		paraMap.put("proc_id", proc_id);
		paraMap.put("reg_station", reg_station);
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
	 * saveExcursus:(���淿�ز�֤����).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String saveExcursus(Row row){
		String proc_id = row.getString("proc_id");
		String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id();
		String excursus = row.getString("excursus");
		Certificate certificate = new Certificate();
		certificate.setExcursus(excursus);
		certificate.setBus_id(bus_id);
		
		try {
			FacadeFactory.getCertificateFacade().saveCertificate(certificate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result","success");
		
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * getChangeRecord:(��ȡ�����������¼����). 
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
	 * applyEditChangeRecord:(����༭�ı����¼����).
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
	 * saveChangeRecord:(������������   ��ȡ��ǰҵ���µ�    ��code��������    �Ƚ���old_data  ��new_data,�������   �͸���   ���򱣴�)
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
		
		
		//��ȡ��ǰҵ���µ�    ��code��������    �������   ����    ������  �ͱ���
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
	 * saveBKOwnerShipToBusOwnerShip:(����ǰ�Ǽǲ����н��������ݵ��Ǽ���Ϣ��).
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

}


