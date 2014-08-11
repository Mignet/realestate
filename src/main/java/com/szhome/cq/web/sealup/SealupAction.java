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
	 * getHolder:(��ȡȨ��������   ת�÷�). 
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
	 * getRegInfo:��ȡ�Ǽ���Ϣ. <br/>
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
	 * getHouseInfo:��ȡ��ⷿ�ز���Ϣ. <br/>
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
		//��¼�˲�ѯ����
		map.put("total", resultList.size());
		//��¼�˵�ǰҳ������
		map.put("rows", resultList);
		return JsonUtil.object2json(map);
	}
	/**
	 * 
	 * saveRegInfo:����Ǽ���Ϣ. <br/>
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
		return this.setActionJsonObject(new JsonResult(true, "�Ǽ���Ϣ����ɹ���", null).toJsonString());
	}
	
	 /**
     * 
     * getBus:���ݴ�������ʵ��id��ȡ��������Ϣ. <br/>
     *
     * @author xuzz
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
		Map<String,Object> map = new HashMap<String,Object>();
		//��¼�˲�ѯ����
		map.put("total", apps.size());
		//��¼�˵�ǰҳ������
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
	 * getLastAttachById:����id��ȡ���Ǽ���Ϣ. <br/>
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
	 * getAttachById:����id��ȡ���/���Ǽ���Ϣ. <br/>
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
	 * getAttachByRegcode:���ݵǼǱ�Ż�ȡ�����Ϣ���ڽ��Ǽ�,. <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String getUnAttachByRegcode(Row row){
		String reg_code = row.getString("reg_code");
		//�������
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
	 * getAttachByRegcode:���ݵǼǱ�Ż�ȡ�����Ϣ���ڽ��Ǽ�,. <br/>
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
	 * getAttachByRegUnitcode:���ݵǼǵ�Ԫ��Ż�ȡ�����Ϣ�������⣬�ֺ���ת���. <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String getAttachByRegUnitcode(Row row){
		//�Ǽǵ�Ԫ���
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
	 * saveUnAttach:�����⣬����ֺ���Ǽ���Ϣ,���ڽ��Ǽ�. <br/>
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
		return this.setActionJsonObject(new JsonResult(true, "�����Ϣ����ɹ���", null).toJsonString());
		
		
	}
	
	/**
	 * 
	 * saveAttach:������Ǽ���Ϣ. <br/>
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
		return this.setActionJsonObject(new JsonResult(true, "�����Ϣ����ɹ���", null).toJsonString());
		
		
	}
	

}

