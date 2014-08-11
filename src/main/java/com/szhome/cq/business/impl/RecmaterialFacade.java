/**
 * Project Name:dxtx_re
 * File Name:RecmaterialFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-1-15上午9:55:03
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
import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IRecmaterialFacade;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.MaterialReplenish;
import com.szhome.cq.domain.model.RecMatConfigure;
import com.szhome.cq.domain.model.RecScanner;
import com.szhome.cq.domain.model.ReceptionMaterial;
import com.szhome.cq.utils.DateUtils;
import com.szhome.cq.utils.WbfConstants;
import com.szhome.process.entity.WorkItem;
import com.szhome.security.ext.UserInfo;

/**
 * 接件材料facade
 * Date:     2014-1-15 上午9:55:03 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class RecmaterialFacade implements IRecmaterialFacade {
	
	@Autowired
	private RecMatConfigure recMatConDao;								//接年材料配置实体
	@Autowired
	private ReceptionMaterial receptionMaterialDao;							//接件材料
	@Autowired
	private RecScanner recScannerDao;									//材料扫描件
	@Autowired
	private MaterialReplenish materialReplenishDao;						//补正材料
	@Autowired
	private BusinessMain businessMainDao;								//业务主表
	/**
	 * 
	 * 通过登记编号获取当前业务的接件材料.
	 *
	 * @author Joyon
	 * @param paraMap reg_code
	 * @return
	 * @since JDK 1.6
	 */
	@Transactional


	public List<Map<String,Object>> getRecmaterialMapListByRegId(Map<String,Object> paraMap){
		
		List<Map<String,Object>> resultList = null;
		String re_person = paraMap.get("re_person").toString();
		String bus_id = paraMap.get("bus_id").toString();
		String rec_type_flag = paraMap.get("rec_type_flag").toString();
		//String rec_type_flag  = paraMap.get("rec_type_flag").toString();
		try {
			resultList = receptionMaterialDao.queryMapListByKey("Recmaterial.getRecmaterialMapListByRegId"," and rec_type_flag=:rec_type_flag order by RECEIVAL_ID", paraMap);
			
			//如果是接件材料和发文材料    如果接件材料表中数据为空  则从配置表中保存到接件材料表中   补正材料不需要
			if(rec_type_flag.equals(WbfConstants.REC_TYPE_FLAG_RECEIVAL) || rec_type_flag.equals(WbfConstants.REC_TYPE_FLAG_DISPACH)){
				
				//如果接件材料表中数据为空  则从接件材料配置表中获取数据  并保存到接件材料表中  再取出来返回
				if(resultList == null || resultList.isEmpty()){
					//resultList = FacadeFactory.getRecMatConfigureFacade().getRecMatConAsRecMapListByRegCode(paraMap);
					
					List<RecMatConfigure> recMatConfigureList = FacadeFactory.getRecMatConfigureFacade().getRecMatConfigureListByRegCode(paraMap);
					ReceptionMaterial tempRecmaterial = null;
					for(RecMatConfigure cf:recMatConfigureList ){
						tempRecmaterial = new ReceptionMaterial();
						tempRecmaterial.setReceival_id(receptionMaterialDao.getSeqId());
						tempRecmaterial.setRe_name(cf.getCfig_rec_name());
						tempRecmaterial.setRe_type(cf.getCfig_rec_type());
						tempRecmaterial.setRe_style(cf.getCfig_rec_style());
						tempRecmaterial.setRe_page(cf.getCfig_page());
						tempRecmaterial.setRe_copy(cf.getCfig_rec_copy());
						tempRecmaterial.setRe_person(re_person);
						tempRecmaterial.setBus_id(bus_id);
						tempRecmaterial.setRe_date(new Date());
						tempRecmaterial.setRec_type_flag(cf.getRec_type_flag());
						receptionMaterialDao.save(tempRecmaterial);
					}
					resultList = receptionMaterialDao.queryMapListByKey("Recmaterial.getRecmaterialMapListByRegId"," and rec_type_flag=:rec_type_flag order by RECEIVAL_ID", paraMap);
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("获取接件材料数据出错"+e.getMessage());
		}
		Map<String,Object> tempMap = null;
		ReceptionMaterial receptionMaterial = null;
//		for(int i = 0;i<resultList.size();i++){
//			tempMap = resultList.get(i);
//			Date tempDate = (Date)tempMap.get("RE_DATE");
//			if(tempDate == null){
//				tempDate = new Date();
//			}
//			//Date tempDate = (Date);
//			tempMap.put("RE_DATE", org.apache.tools.ant.util.DateUtils.format(tempDate, "yyyy-MM-dd HH:mm:ss"));
//		}
		return resultList;
	}
	
	/**
	 * 将字接件材料编辑后的数据应用到后台数据库
	 */
	@Transactional
	public Map applyEdit(Map paraMap) {

			
			Map result = new HashMap();
			
			String datas = paraMap.get("datas").toString();
			String userName = paraMap.get("userName").toString();
			String reg_code = paraMap.get("reg_code").toString();
			String bus_id = FacadeFactory.getCommonFacade().getBusIdByRegCode(reg_code);
			String rec_type_flag= paraMap.get("rec_type_flag").toString();
			List<Map<String, Object>> tempRecList = null;							//判断接件材料表中是否有值   有值 则走下面  无值 则进行保存rows 中的数据
			try {
				tempRecList = receptionMaterialDao.queryMapListByKey("Recmaterial.getRecmaterialMapListByRegId"," and rec_type_flag=:rec_type_flag",paraMap);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
			Map classMap = new HashMap<String, Class>();
			classMap.put("inserted", Map.class);
			classMap.put("deleted", Map.class);
			classMap.put("updated", Map.class);
			classMap.put("rows", Map.class);
			Map dataMap = (Map)JSONObject.toBean(JSONObject.fromObject(datas), Map.class, classMap);
			Row temp = null;
			ReceptionMaterial receptionMaterial = null;
			
			
			//如果第一次过来则把rows里的所有数据保存到接件材料表中
			if(tempRecList == null || tempRecList.isEmpty()){
				List rows =  (List)dataMap.get("rows");
				for(int i = 0; i < rows.size(); i ++){
					temp = new RowImpl((Map)rows.get(i));
					
					receptionMaterial = new ReceptionMaterial();
					try {
						receptionMaterial.setReceival_id(receptionMaterialDao.getSeqId());
					} catch (Exception e) {
						e.printStackTrace();
						throw new BusinessException("获取字典项seq id出错"+e.getMessage());
					}
					receptionMaterial.setRe_date(DateUtils.getCurTime());
					receptionMaterial.setRe_person(userName);
					receptionMaterial.setBus_id(bus_id);
					receptionMaterial.setRe_name(temp.getString("RE_NAME"));
					receptionMaterial.setRe_type(temp.getString("RE_TYPE"));
					
					receptionMaterial.setRe_style(temp.getString("RE_STYLE"));
					receptionMaterial.setRe_page(temp.getString("RE_PAGE"));
					receptionMaterial.setRe_copy(temp.getString("RE_COPY"));
					receptionMaterial.setRec_type_flag(rec_type_flag);
					try {
						//logger.info("insert:" + temp);
						receptionMaterialDao.save(receptionMaterial);
					} catch (Exception e) {
						e.printStackTrace();
						throw new BusinessException("保存字典项出错"+e.getMessage());
					}
				}
				
				result.put("success",true);
				return result;
			}
			
			
			//处理新增数据
			List news = (List)dataMap.get("inserted");
			 
			for (int i = 0; i < news.size(); i ++) {
				temp = new RowImpl((Map)news.get(i));
				receptionMaterial = new ReceptionMaterial();
				try {
					receptionMaterial.setReceival_id(receptionMaterialDao.getSeqId());
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("获取字典项seq id出错"+e.getMessage());
				}
				receptionMaterial.setRe_date(DateUtils.getCurTime());
				receptionMaterial.setRe_person(userName);
				receptionMaterial.setBus_id(bus_id);
				receptionMaterial.setRe_name(temp.getString("RE_NAME"));
				receptionMaterial.setRe_type(temp.getString("RE_TYPE"));
				
				receptionMaterial.setRe_style(temp.getString("RE_STYLE"));
				receptionMaterial.setRe_page(temp.getString("RE_PAGE"));
				receptionMaterial.setRe_copy(temp.getString("RE_COPY"));
				receptionMaterial.setRec_type_flag(rec_type_flag);
				
				try {
					//logger.info("insert:" + temp);
					receptionMaterialDao.save(receptionMaterial);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("保存字典项出错"+e.getMessage());
				}
				
				
			}
			
			//处理修改数据
			List upds = (List)dataMap.get("updated");
			for (int i = 0; i < upds.size(); i ++) {
				temp = new RowImpl((Map)upds.get(i));
				receptionMaterial = new ReceptionMaterial();
				String item_id = temp.getString("item_id");
				
				receptionMaterial.setReceival_id(temp.getString("RECEIVAL_ID"));
				
				receptionMaterial.setRe_date(DateUtils.getCurTime());
				receptionMaterial.setRe_person(userName);
				receptionMaterial.setBus_id(bus_id);
				receptionMaterial.setRe_name(temp.getString("RE_NAME"));
				receptionMaterial.setRe_type(temp.getString("RE_TYPE"));
				
				receptionMaterial.setRe_style(temp.getString("RE_STYLE"));
				receptionMaterial.setRe_page(temp.getString("RE_PAGE"));
				receptionMaterial.setRe_copy(temp.getString("RE_COPY"));
				receptionMaterial.setRec_type_flag(rec_type_flag);
				try {
					receptionMaterialDao.update(receptionMaterial);
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
				receptionMaterial = new ReceptionMaterial();
				receptionMaterial.setReceival_id(temp.getString("RECEIVAL_ID"));
				try {
					receptionMaterialDao.delete(receptionMaterial);
					
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
	 * getScannerListMapByProcId:(通过流程实例ID获取当前业务扫描件List).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getScannerListMapByProcId(String proc_id) {
		//BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		List<Map<String,Object>> resultList = null;
		try {
			resultList = recScannerDao.queryMapListByKey("Recmaterial.getScannerListMapByProcId", paraMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	/**
	 * 
	 * modifyScanner:(修改材料扫描数据). 
	 * @author Joyon
	 * @param recScanner
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object>  modifyRecScanner(RecScanner recScanner){
		try {
			recScannerDao.update(recScanner);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result", "success");
		return resultMap;
	}
	
	/**
	 * 
	 * getRecScanner:(获取材料扫描数据). 
	 *
	 * @author Joyon
	 * @param recScanner
	 * @return
	 * @since JDK 1.6
	 */
	public RecScanner getRecScanner(RecScanner recScanner){
		return recScannerDao.get(recScanner);
	}
	/*
	@Transactional
	public void saveRecMaterial(RecMatConfigure rec) {
		
		Map m=new HashMap();
		m.put("mc",rec.getMc());
		RecMatConfigure r= recMatConDao.get(" where mc=:mc", m);
		try {
		if(r ==null ){
			rec.setJjclpzbid(recmarDao.getSeqId());
			recMatConDao.save(rec);
		}
		else{
			rec.setJjclpzbid(r.getJjclpzbid());
			recMatConDao.update(rec);
			
		}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("错误信息:"+e.getMessage());
		}
		
	}	
	
	/**
	 * 
	 * TODO 删除单条接件材料配置表信息.
	 * @see com.szhome.cq.business.ICommonFacade#delRecMaterial(int)
	 */
	/*
	@Override
	@Transactional
	public void delRecMaterial(int id) {
		
		try {
			recMatConDao.delete(new RecMatConfigure(id));
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("错误信息:"+e.getMessage());
			
		}
		
	}
	
	*/
	/**
	 * 
	 * TODO 更新单条接件材料配置表信息.
	 * @see com.szhome.cq.business.ICommonFacade#updateRecMaterial(com.szhome.cq.domain.model.RecMaterial)
	 */
	/*
	@Override
	@Transactional
	public void updateRecMaterial(RecMatConfigure rec) {
		
		try {
			recMatConDao.update(rec);
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("错误信息:"+e.getMessage());
			
		}
		
	}
	*/
	
	/**
	 * 
	 * 保存补正材料信息
	 *
	 * @author Joyon
	 * @param materialReplenish
	 * @since JDK 1.6
	 */
	public void saveMaterialReplenish(MaterialReplenish materialReplenish) throws BusinessException{
		if(materialReplenish.getId()==null){
			materialReplenish.setId(materialReplenishDao.getSeqId());
		}
		materialReplenishDao.save(materialReplenish);
	}
	
	/**
	 * 
	 * 通过登记编号获取补正材料
	 *
	 * @author Joyon
	 * @param reg_code  需要补正材料业务的登记编号
	 * @since JDK 1.6
	 */
	public MaterialReplenish getMaterialReplenishByregcode(String reg_code) throws BusinessException{
		Map paraMap = new HashMap();
		paraMap.put("reg_code", reg_code);
		return materialReplenishDao.get(" where reg_code=:reg_code",paraMap);
	}
	
	/**
	 * 
	 * 更新补正材料信息
	 *
	 * @author Joyon
	 * @param materialReplenish
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void updateMaterialReplenish(MaterialReplenish materialReplenish) throws BusinessException{
		materialReplenishDao.update(materialReplenish);
	}
	
	/**
	 * 
	 * 
	 *
	 * @author Joyon
	 * @param rep_proc_id  需要补正的流程ID
	 * @since JDK 1.6
	 */
	public Map<String,Object> startMaterialReplenishProc(String rep_proc_id,UserInfo userInfo) throws BusinessException{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		BusinessMain oldBusinessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(rep_proc_id);
		String procName = "补正材料("+oldBusinessMain.getProc_name()+")";
		
		WorkItem workItem = FacadeFactory.getWorkflowFacade().createAndStartProcessInstance(userInfo, WbfConstants.MATERIAL_REPLENISH_PRODEFID, procName);
	
		
		//插入业务主表   保存前一个业务ID到数据库
		saveReginfo(oldBusinessMain,workItem,procName);
		resultMap.put("workItem", workItem);
		return resultMap;
	}
	/**
	 * 
	 * 保存业务主表 信息
	 *
	 * @author Joyon
	 * @param oldBusinessMain
	 * @param workItem
	 * @param procName
	 * @since JDK 1.6
	 */
	private  void saveReginfo(BusinessMain oldBusinessMain,WorkItem workItem,String procName){
		BusinessMain businessMain = new BusinessMain();
		//1.查询登记编号
		Row row = new RowImpl();
		row.put("name", "登记编号");
		String regcode = FacadeFactory.getIdentifierFacade().getSerialNumber(row);
		
		businessMain.setBus_id(businessMainDao.getSeqId());
		businessMain.setProc_name(procName);
		businessMain.setLast_bus_id(oldBusinessMain.getBus_id());
		businessMain.setProc_id(workItem.getProcId().toString());
		businessMain.setPro_def_id(WbfConstants.MATERIAL_REPLENISH_PRODEFID);
		businessMain.setReg_code(regcode);
		businessMain.setReg_type(WbfConstants.MATERIAL_REPLENISH);
		
		businessMainDao.save(businessMain);
	}

	public List<Map<String, Object>> getMaterialIdListByregcode(Map<String,Object> paramMap)
			throws BusinessException {
		List<Map<String, Object>> recidLst =  businessMainDao.queryMapListByKey("Recmaterial.getRecIdMapListByRegId", paramMap);
		return recidLst;
	}
}


