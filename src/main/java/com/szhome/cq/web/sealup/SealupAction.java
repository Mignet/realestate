package com.szhome.cq.web.sealup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.plan.commons.Row;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.vo.OwnerInfoVo;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.BusDistrain;
import com.szhome.cq.utils.JsonUtil;
import com.szhome.cq.web.JsonResult;
public class SealupAction extends BaseDelegate{
	
	/**
	 * 
	 * getHolder:(获取权利人数据   转让方). 
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getHolder(Row row){
		List<Map<String,Object>> resultList = null;
		try {
			String proc_id = row.getString("proc_id");
			resultList = FacadeFactory.getRegisterFacade().getHistoryHolderMapListByProcId(proc_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.object2json(resultList);
				
	}
	/**
	 * 
	 * getRegInfo:获取登记信息. <br/>
	 *
	 * @author PANDA
	 * @return
	 * @since JDK 1.6
	 */
	public String getRegInfo(Row row){
		Map<String,Object> resultMap = null;
		String proc_id = row.getString("proc_id");
		try {
			resultMap = FacadeFactory.getOwnershipShiftRegFacade().getRegInfoMapByProcId(proc_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.object2json(resultMap);
	}
	/**
	 * 
	 * getHouseInfo:获取查封房地产信息. <br/>
	 *
	 * @author PANDA
	 * @return
	 * @since JDK 1.6
	 */
	public String getHouseInfo(Row row){
		String proc_id = row.getString("proc_id");
		String reg_code = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getReg_code();
		Map<String,Object> paraMap = new HashMap();
		paraMap.put("reg_code", reg_code);
		Map<String,Object> naturalInfoMap = null;
		try {
			naturalInfoMap = FacadeFactory.getRegisterFacade().getNaturalInfo(paraMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		resultList.add(naturalInfoMap);
		Map<String,Object> map = new HashMap<String,Object>();
		//记录了查询总数
		map.put("total", resultList.size());
		//记录了当前页的数据
		map.put("rows", resultList);
		return JsonUtil.object2json(map);
	}
	/**
	 * 
	 * saveRegInfo:保存登记信息. <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String saveRegInfo(Row row){
		String proc_id = row.getString("proc_id");
		BusDistrain distrain = new BusDistrain();
		distrain.setDis_off(row.getString("distrain.dis_off"));
		distrain.setDis_no(row.getString("distrain.dis_no"));
		distrain.setLaw_doc(row.getString("distrain.law_doc"));
		distrain.setLim_holder(row.getString("distrain.lim_holder"));
		distrain.setDis_date(row.getDate("distrain.dis_date"));
		distrain.setDis_limit(row.getString("distrain.dis_limit"));
		distrain.setDis_range(row.getString("distrain.dis_range"));
		distrain.setDis_type(row.getString("distrain.dis_type"));
		distrain.setStart_date(row.getDate("distrain.start_date"));
		distrain.setEnd_date(row.getDate("distrain.end_date"));
		distrain.setPre_con_no(row.getString("distrain.pre_con_no"));
		distrain.setService_name(row.getString("distrain.service_name"));
		distrain.setDis_per_tel(row.getString("distrain.dis_per_tel"));
		distrain.setWorkid(row.getString("distrain.workid"));
		distrain.setService_date(row.getDate("distrain.service_date"));
		distrain.setRemark(row.getString("distrain.remark"));
		OwnerInfoVo oivo = new OwnerInfoVo();
		oivo.setReg_station(row.getString("oivo.reg_station"));
		oivo.setReg_code(row.getString("oivo.reg_code"));
		oivo.setReg_type(row.getString("oivo.reg_type"));
		oivo.setProc_name(row.getString("oivo.proc_name"));
		try {
			String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			if(oivo!=null)
			{
				oivo.setBus_id(bus_id);
				oivo.setProc_id(proc_id);
				FacadeFactory.getApplicantFacade().saveRegistMess(oivo);
			}
			//String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			distrain.setBus_id(bus_id);
			FacadeFactory.getApplicantFacade().saveAttach(distrain);
			
		} catch (Exception e) {
			e.printStackTrace();
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "登记信息保存成功！", null).toJsonString());
	}
	
	 /**
     * 
     * getBus:根据传入流程实例id获取申请人信息. <br/>
     *
     * @author xuzz
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
	/**
	 * 
	 * getLastAttachById:根据id获取查封登记信息. <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String getLastAttachById(Row row)
	{
		//-------ajax---------
		String proc_id = row.getString("proc_id");
		String bus_id=FacadeFactory.getExaminationFacade().getRegid(proc_id);
		
		return "";
	}
	
	/**
	 * 
	 * getAttachById:根据id获取查封/解封登记信息. <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String getAttachById(Row row){
		//-------ajax---------
		String proc_id = row.getString("proc_id");
		String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
		BusDistrain dis = FacadeFactory.getApplicantFacade().getAttach(bus_id);
		if(dis == null){
			
			return null;
		}else{
		
	    return JsonUtil.object2json(dis);
		}
	    //return resultToJson(dis);
	}
	/**
	 * 
	 * getAttachByRegcode:根据登记编号获取查封信息用于解封登记,. <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String getUnAttachByRegcode(Row row){
		String reg_code = row.getString("reg_code");
		//查封类型
		String attachType = row.getString("attachType");
		List<Map<String,Object>> resultMap = FacadeFactory.getApplicantFacade().getAttachByRegcode(reg_code,attachType);
		if(resultMap == null){
			
			return null;
		}else{
		
	    return JsonUtil.object2json(resultMap);
		}
	}
	
	/**
	 * 
	 * getAttachByRegcode:根据登记编号获取查封信息用于解封登记,. <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String getAttachByRegcode(Row row){
		//-------ajax---------
		String reg_code = row.getString("reg_code");
		String attachType = row.getString("attachType");
		List<Map<String,Object>> resultMap = FacadeFactory.getApplicantFacade().getAttachByRegcode(reg_code,attachType);
		if(resultMap == null){
			return null;
		}else{
		
	    return JsonUtil.object2json(resultMap.get(0));
		}
	}
	
	/**
	 * 
	 * getAttachByRegUnitcode:根据登记单元编号获取查封信息用于续封，轮候查封转查封. <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String getAttachByRegUnitcode(Row row){
		//登记单元编号
		String reg_unit_code = row.getString("reg_unit_code");
		String attachType = row.getString("attachType");
		Map resultMap = FacadeFactory.getApplicantFacade().getAttachByRegUnitcode(reg_unit_code,attachType);
		if(resultMap == null){
			
			return null;
		}else{
		
	    return JsonUtil.object2json(resultMap);
		}
	}

	/**
	 * 
	 * saveUnAttach:保存解封，解除轮候查封登记信息,用于解封登记. <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String saveUnAttach(Row row){
		
		//-------ajax---------
		try {
			//List<Reg_relationship> list=getRegRelation();
			//deleteRegration();
			//getPreRegcode();
			//saveRegrelation();
			String bus_id = FacadeFactory.getExaminationFacade().getRegid(row.getString("proc_id"));
			BusDistrain distrain = new BusDistrain();
			distrain.setDis_off(row.getString("distrain.dis_off"));
			distrain.setDis_no(row.getString("distrain.dis_no"));
			distrain.setLaw_doc(row.getString("distrain.law_doc"));
			distrain.setLim_holder(row.getString("distrain.lim_holder"));
			distrain.setDis_date(row.getDate("distrain.dis_date"));
			distrain.setDis_limit(row.getString("distrain.dis_limit"));
			distrain.setDis_range(row.getString("distrain.dis_range"));
			distrain.setDis_type(row.getString("distrain.dis_type"));
			distrain.setStart_date(row.getDate("distrain.start_date"));
			distrain.setEnd_date(row.getDate("distrain.end_date"));
			distrain.setPre_con_no(row.getString("distrain.pre_con_no"));
			distrain.setService_name(row.getString("distrain.service_name"));
			distrain.setDis_per_tel(row.getString("distrain.dis_per_tel"));
			distrain.setWorkid(row.getString("distrain.workid"));
			distrain.setService_date(row.getDate("distrain.service_date"));
			distrain.setRemark(row.getString("distrain.remark"));
			distrain.setBus_id(bus_id);
			FacadeFactory.getApplicantFacade().saveAttach(distrain);
		} catch (Exception e) {
			e.printStackTrace();
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "查封信息保存成功！", null).toJsonString());
		
		
	}
	
	/**
	 * 
	 * saveAttach:保存查封登记信息. <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String saveAttach(Row row){
		String proc_id = row.getString("proc_id");
		BusDistrain distrain = new BusDistrain();
		distrain.setDis_off(row.getString("distrain.dis_off"));
		distrain.setDis_no(row.getString("distrain.dis_no"));
		distrain.setLaw_doc(row.getString("distrain.law_doc"));
		distrain.setLim_holder(row.getString("distrain.lim_holder"));
		distrain.setDis_date(row.getDate("distrain.dis_date"));
		distrain.setDis_limit(row.getString("distrain.dis_limit"));
		distrain.setDis_range(row.getString("distrain.dis_range"));
		distrain.setDis_type(row.getString("distrain.dis_type"));
		distrain.setStart_date(row.getDate("distrain.start_date"));
		distrain.setEnd_date(row.getDate("distrain.end_date"));
		distrain.setPre_con_no(row.getString("distrain.pre_con_no"));
		distrain.setService_name(row.getString("distrain.service_name"));
		distrain.setDis_per_tel(row.getString("distrain.dis_per_tel"));
		distrain.setWorkid(row.getString("distrain.workid"));
		distrain.setService_date(row.getDate("distrain.service_date"));
		distrain.setRemark(row.getString("distrain.remark"));
		try {
			String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			distrain.setBus_id(bus_id);
			FacadeFactory.getApplicantFacade().saveAttach(distrain);
		} catch (Exception e) {
			e.printStackTrace();
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "查封信息保存成功！", null).toJsonString());
		
		
	}
	

}

