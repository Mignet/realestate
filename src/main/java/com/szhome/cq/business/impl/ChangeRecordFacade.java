/**
 * Project Name:dxtx_re
 * File Name:DictFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-1-2下午3:42:46
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.business.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plan.commons.Row;
import com.plan.commons.RowImpl;
import com.plan.web.JsonResult;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IChangeRecordFacade;
import com.szhome.cq.business.IDictFacade;
import com.szhome.cq.domain.model.BusOwnership;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.Bususeright;
import com.szhome.cq.domain.model.ChangeRecord;
import com.szhome.cq.domain.model.DicItem;
import com.szhome.cq.domain.model.DicType;
import com.szhome.cq.domain.model.DictClass;
import com.szhome.cq.domain.model.DictItem;
import com.szhome.cq.domain.model.Identifier;
import com.szhome.cq.domain.model.Reg_Useright;
import com.szhome.cq.domain.model.Reg_ownership;

/**
 * 变更更正记录facade
 * Date:     2014-1-2 下午3:42:46 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class ChangeRecordFacade implements IChangeRecordFacade {
	@Autowired
	private Identifier identifierDao;
	@Autowired
	private  DictItem dictItemDao;					//字典项
	@Autowired
	private  DictClass dictClassDao;				//字典类型
	
	@Autowired
	private  DicType dicTypeDao;					//字典类型
	@Autowired
	private  DicItem dicItemDao;					//字典项
	@Autowired
	private  ChangeRecord changeRecordDao;			//变更记录
	@Autowired
	private BusOwnership busOwnershipDao;			//所有权登记倣
	@Autowired
	private Bususeright bususerightDao;				//使用权登记信息	
	
	
	
	/**
	 * 
	 * 保存编辑数据. 
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return Map
	 * @since JDK 1.6
	 */
	public Map applyEdit(Map paraMap) {

		
	
			Map result = new HashMap();
			
			String datas = paraMap.get("datas").toString();
			String bus_id = paraMap.get("bus_id").toString();
			//Object //logger;
			//logger.info("datas:" + datas);

			Map classMap = new HashMap<String, Class>();
			classMap.put("inserted", Map.class);
			classMap.put("deleted", Map.class);
			classMap.put("updated", Map.class);
			Map dataMap = (Map)JSONObject.toBean(JSONObject.fromObject(datas), Map.class, classMap);
			
			//logger.info(map.get("inserted"));
			//logger.info(map.get("deleted"));
			//logger.info(map.get("updated"));
			
			Row temp = null;
			
			//处理新增数据
			List news = (List)dataMap.get("inserted");
			
			ChangeRecord changeRecord = null; 
			for (int i = 0; i < news.size(); i ++) {
				temp = new RowImpl((Map)news.get(i));
				changeRecord = new ChangeRecord();
				try {
					changeRecord.setId(changeRecordDao.getSeqId());
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("获取字典项seq id出错"+e.getMessage());
				}
				
				changeRecord.setChange_code(temp.getString("CHANGE_CODE"));
				changeRecord.setOld_data(temp.getString("OLD_DATA"));
				changeRecord.setNew_data(temp.getString("NEW_DATA"));
				changeRecord.setChange_date(new Date());
				changeRecord.setBus_id(bus_id);
				
				try {
					//logger.info("insert:" + temp);
					changeRecordDao.save(changeRecord);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("保存字典项出错"+e.getMessage());
				}
			}
			
			//处理修改数据
			List upds = (List)dataMap.get("updated");
			for (int i = 0; i < upds.size(); i ++) {
				temp = new RowImpl((Map)upds.get(i));
				changeRecord = new ChangeRecord();
				String id = temp.getString("ID");
				try {
					changeRecord.setId(id);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				changeRecord.setChange_code(temp.getString("CHANGE_CODE"));
				changeRecord.setOld_data(temp.getString("OLD_DATA"));
				changeRecord.setNew_data(temp.getString("NEW_DATA"));
				changeRecord.setChange_date(new Date());
				changeRecord.setBus_id(bus_id);
				
				try {
					changeRecordDao.update(changeRecord);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("更新字典项出错"+e.getMessage());
				}
				
				//di.set
				//logger.info("update:" + temp);
				//getPlanSupportDao().update(temp, "plat_dict_item");
			}
			
			//处理删除数据
			List dels = (List)dataMap.get("deleted");
			for (int i = 0; i < dels.size(); i ++) {
				temp = new RowImpl((Map)dels.get(i));
				changeRecord = new ChangeRecord();
				changeRecord.setId(temp.getString("ID"));
				try {
					changeRecordDao.delete(changeRecord);
					
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("删除字典项出错"+e.getMessage());
				}
			}
			
			result.put("success",true);
			return result;

		
	}


	/**
	 * 
	 * 获取变更信息Map list.
	 *
	 * @author Joyon
	 * @param proc_id  流程实例ID
	 * @return List<Map<String,Object>>
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getChangeRecordMapListByProcId(String proc_id) {
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		List<Map<String, Object>> resultMapList = null;
		try {
			resultMapList = changeRecordDao
					.queryMapListByKey("ChangeRecord.getChangeReccordByProcId",
							paraMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMapList;
	}


	/**
	 * 
	 * 按当前业务Id 和变更项code获取变更数据List.
	 *
	 * @author Joyon
	 * @param code 变更项code
	 * @param bus_id  当前业务ID
	 * @since JDK 1.6
	 */
	public List<ChangeRecord> getChangeRecordListByCodeAndBusId(String code, String bus_id) throws BusinessException{
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		
		paraMap.put("code",code);
		paraMap.put("bus_id", bus_id);
		
		return changeRecordDao.getAll("where bus_id=:bus_id and change_code =:code", paraMap);
		
	}
	
	
	/**
	 * 
	 * 保存变更数据
	 * @author Joyon
	 * @param changeRecocrd   需要保存的变更项实体
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void saveChangeRecord(ChangeRecord changeRecocrd) throws BusinessException{
		if(changeRecocrd.getId() == null){
			changeRecocrd.setId(changeRecordDao.getSeqId());
		}
		changeRecocrd.setChange_date(new Date());
		changeRecordDao.save(changeRecocrd);
	}
	
	/**
	 * 
	 * 更新变更数据
	 * @author Joyon
	 * @param changeRecocrd   需要更新的changerecocrd
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void updateChangeRecord(ChangeRecord changeRecocrd) throws BusinessException{
		
		changeRecocrd.setChange_date(new Date());
		changeRecordDao.update(changeRecocrd);
	}
	
	/**
	 * 
	 * 保存前登记簿吕有交往的数据到登记信息中
	 *
	 * @author Joyon
	 * @param proc_id  流程实例ID
	 * @since JDK 1.6
	 */
	public void saveBKOwnerShipToBusOwnerShip(String proc_id) throws BusinessException{
		
		//首先查询登记信息中是否有数据    有数据则不进行任何操作   无数据 保存
		BusinessMain busMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
		BusOwnership busOnwerShip = null;
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id", busMain.getBus_id());
		busOnwerShip =busOwnershipDao.get("where bus_id=:bus_id",paraMap);
		
		//如果有数据  直接结束    
		if(busOnwerShip !=null){
			return;
		}
		
		
		//无数据  进行copy后  保存
		busOnwerShip = new BusOwnership();
		
		Reg_ownership reg_Ownership = FacadeFactory.getRegisterFacade().getEffectiveReg_OwnershipByProcId(proc_id);
		
		busOwnershipDao.copyProperties(busOnwerShip, reg_Ownership);
		busOnwerShip.setOwner_reg_id(busOwnershipDao.getSeqId());
		busOnwerShip.setBus_id(busMain.getBus_id());
		busOnwerShip.setExcursus("");
		busOwnershipDao.save(busOnwerShip);
	}
	
	/**
	 * 
	 * 保存前登记簿使用权部分的数据到登记信息中
	 *
	 * @author Joyon
	 * @param proc_id  流程实例ID
	 * @since JDK 1.6
	 */
	public void saveBKUserightShipToBusUserightShip(String proc_id) throws BusinessException{
				//首先查询登记信息中是否有数据    有数据则不进行任何操作   无数据 保存
				BusinessMain busMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
				Bususeright bususeright = null;
				Map<String,Object> paraMap = new HashMap<String,Object>();
				paraMap.put("bus_id", busMain.getBus_id());
				bususeright =bususerightDao.get("where bus_id=:bus_id",paraMap);
				
				//如果有数据  直接结束    
				if(bususeright !=null){
					return;
				}
				
				
				//无数据  进行copy后  保存
				bususeright = new Bususeright();
				
				
				String reg_unit_code = "";
				try {
					reg_unit_code = FacadeFactory.getCommonFacade()
							.getReg_relationshipByProcId(proc_id)
							.getReg_unit_code();
				} catch (Exception e) {
					LogUtil.error("获得登记单元关联表数据出错"+e.getMessage());
				}
				Reg_Useright reg_Useright = FacadeFactory.getRegisterFacade().getEffectiveReg_userightByRegUnitCode(reg_unit_code);
				
				bususerightDao.copyProperties(bususeright, reg_Useright);
				bususeright.setUseright_reg_id(bususerightDao.getSeqId());
				bususeright.setBus_id(busMain.getBus_id());
				bususeright.setExcursus("");
				bususeright.setUse_limit(reg_Useright.getLu_term());
				bususeright.setGet_price(Double.valueOf(reg_Useright.getReg_pri()));
				bususerightDao.save(bususeright);
	}

	
	/**
	 * 
	 * 获取登记信息
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getRegInfoMapByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		paraMap = FacadeFactory.getCommonFacade().getBusMainInfoMapByProcId(proc_id);
		Map<String,Object> certificateMap = FacadeFactory.getCertificateFacade().getCertificateMapByProcId(proc_id);
		if(certificateMap!=null &&  !certificateMap.isEmpty()){
			paraMap.putAll(certificateMap);
		}else{
			LogUtil.error("房地产证信息为空！");
		}
		
		return paraMap;
	}
	
	
	
	
	

}


