package com.szhome.cq.web.houseownership;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.plan.commons.Row;
import com.plan.exceptions.GeneralException;
import com.plan.web.JsonResult;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.vo.OwnerInfoVo;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.Applicant;
import com.szhome.cq.domain.model.BusOwnership;
import com.szhome.cq.domain.model.BusRemarkInfo;
import com.szhome.cq.domain.model.BusRevokeapproval;
import com.szhome.cq.domain.model.Busdemurrer;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.Certificate;
import com.szhome.cq.utils.DateUtils;
import com.szhome.cq.utils.JsonUtil;
import com.szhome.cq.utils.WbfConstants;
public class InitialregAction extends BaseDelegate{

	/**
	 * 
	 * saveRegMessage:����Ǽ���Ϣ
	 *
	 * @author PANDA
	 * @return
	 * @since JDK 1.6
	 */
	public String saveRegMessage(Row row){
		String proc_id = row.getString("proc_id");
		try {
			String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			OwnerInfoVo oivo = new OwnerInfoVo();
			oivo.setReg_code(row.getString("oivo.reg_code"));
			oivo.setReg_type(row.getString("oivo.reg_type"));
			oivo.setReg_station(row.getString("oivo.reg_station"));
			oivo.setProc_name(row.getString("oivo.proc_name"));
			oivo.setBus_id(bus_id);
			oivo.setProc_id(proc_id);
			FacadeFactory.getApplicantFacade().saveRegistMess(oivo);
		} catch (Exception e) {
			e.printStackTrace();
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "�Ǽ���Ϣ����ɹ���", null).toJsonString());
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
		String proc_id = row.getString("proc_id");
		//��ȡ����ʵ����id
		//int proc_id = new Integer((String)this.getRequest().getParameter("proc_id"));
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
	 * ���浥����������Ϣ. <br/>
	 *
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String saveApplicant(Row row)  {
		//-------ajax---------
		String proc_id = row.getString("proc_id");
		Applicant app = new Applicant();
		app.setHol_rel(row.getString("app.hol_rel"));
		app.setAgent_tel(row.getString("app.agent_tel"));
		app.setAgent_cer(row.getString("app.agent_cer"));
		app.setAgent_cer_type(row.getString("app.agent_cer_type"));
		app.setAgent_name(row.getString("app.agent_name"));
		app.setApp_tel(row.getString("app.app_tel"));
		app.setApp_address(row.getString("app.app_address"));
		app.setApp_port(row.getString("app.app_port"));
		app.setApp_cer_no(row.getString("app.app_cer_no"));
		app.setLegal_name(row.getString("app.legal_name"));
		app.setApp_cer_type(row.getString("app.app_cer_type"));
		app.setApp_type(row.getString("app.app_type"));
		app.setApp_name(row.getString("app.app_name"));
		app.setApp_pic(row.getString("app.app_pic"));
		app.setAge_pic(row.getString("app.age_pic"));
		try {
			String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			app.setBus_id(bus_id);
			FacadeFactory.getApplicantFacade().saveApplicant(app);
		} catch (Exception e) {
			e.printStackTrace();
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "��������˳ɹ���", null).toJsonString());
	}
	/**
	 * 
	 * ���µ�����������Ϣ. <br/>
	 *
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String updateApplicant(Row row)  {
		//-------ajax---------
		
		Applicant app = new Applicant();
		app.setApplicant_id(row.getString("app.applicant_id"));
		app.setBus_id(row.getString("app.bus_id"));
		app.setHol_rel(row.getString("app.hol_rel"));
		app.setAgent_tel(row.getString("app.agent_tel"));
		app.setAgent_cer(row.getString("app.agent_cer"));
		app.setAgent_cer_type(row.getString("app.agent_cer_type"));
		app.setAgent_name(row.getString("app.agent_name"));
		app.setApp_tel(row.getString("app.app_tel"));
		app.setApp_address(row.getString("app.app_address"));
		app.setApp_port(row.getString("app.app_port"));
		app.setApp_cer_no(row.getString("app.app_cer_no"));
		app.setLegal_name(row.getString("app.legal_name"));
		app.setApp_cer_type(row.getString("app.app_cer_type"));
		app.setApp_type(row.getString("app.app_type"));
		app.setApp_name(row.getString("app.app_name"));
		app.setApp_pic(row.getString("app.app_pic"));
		app.setAge_pic(row.getString("app.age_pic"));
			try 
			{
				FacadeFactory.getApplicantFacade().updateApplicant(app);			
			} 
			catch (Exception e) {
				e.printStackTrace();
				return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
			}	
			return this.setActionJsonObject(new JsonResult(true, "���������˳ɹ���", null).toJsonString());	
	}
	/**
	 * 
	 * ɾ��������������Ϣ. <br/>
	 *
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String deleteApplicant(Row row)  {
		//-------ajax---------
		String id = row.getString("applicant_id");
		 try {
			FacadeFactory.getApplicantFacade().delApplicant(id);
		} catch (Exception e) {

			e.printStackTrace();
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		
		 return this.setActionJsonObject(new JsonResult(true, "ɾ�������˳ɹ���", null).toJsonString());	
	}
	/**
	 * 
	 * ���淿�ز�֤����. <br/>
	 *
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String saveCerRemark(Row row)  {
		//-------ajax---------
		String proc_id = row.getString("proc_id");
		try {
			String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			OwnerInfoVo oivo = new OwnerInfoVo();
			oivo.setBus_id(bus_id);
			oivo.setExcursus(row.getString("oivo.excursus"));
			FacadeFactory.getApplicantFacade().saveCerRemark(oivo);
		} catch (Exception e) {
			e.printStackTrace();
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "���ز�֤���Ǳ���ɹ���", null).toJsonString());
	}
	/**
	 * 
	 * saveOwnership:���淿������Ȩ�Ǽ���Ϣ�����̱���. <br/>
	 * @author PANDA
	 * @return
	 * @since JDK 1.6
	 */
	public String saveOwnership(Row row){
		//-------ajax---------
		String bus_id = null;
		OwnerInfoVo oivo = new OwnerInfoVo();
		oivo.setExcursus(row.getString("excursus"));
		String proc_id = row.getString("proc_id");
		String reason = row.getString("reason");
		try {
			bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
		} catch (Exception e) {
			return this.setActionJsonObject(new JsonResult(false,"ҵ������id������", null).toJsonString());
		}
		//SimpleDateFormat   df   =new   SimpleDateFormat("yyyy-MM-dd");   
		BusOwnership bos = new BusOwnership();
		bos.setReg_value(row.getFloat("reg_value"));
		bos.setGet_mode(row.getString("get_mode"));
		bos.setLu_term(row.getString("lu_term"));
		bos.setStart_date(DateUtils.string2Date(row.getString("start_date"), "yyyy-MM-dd"));
		bos.setEnd_date(DateUtils.string2Date(row.getString("end_date"), "yyyy-MM-dd"));
		bos.setHouse_attr(row.getString("house_attr"));
		bos.setHouse_usage(row.getString("house_usage"));
		try {
			if(bos==null)
			{
				bos=new BusOwnership();
			}
			else
			{
				oivo.setBus_id(bus_id);
				FacadeFactory.getApplicantFacade().saveCerRemark(oivo);
			}
			if(reason!=null||reason!="")
			{
				bos.setReason(reason);
			}
			bos.setBus_id(bus_id);
			FacadeFactory.getApplicantFacade().saveOwnership(bos);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "����ɹ���", null).toJsonString());
	}
	
	
	/**
	 * 
	 * saveDemurrer:���淿�ݵ�����Ǽ���Ϣ. <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String saveDemurrer(Row row){
		//-------ajax---------
		String bus_id = null;
		String proc_id = row.getString("proc_id");
		String disitem = row.getString("disitem");
		try {
			bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
		} catch (Exception e) {
			return this.setActionJsonObject(new JsonResult(false,"ҵ������id������", null).toJsonString());
		}
		Busdemurrer demurrer = new Busdemurrer();
		try {
			if(demurrer==null)
			{
				demurrer=new Busdemurrer();
			}
			//demurrer.setBus_id(bus_id);
			if(disitem!=null||disitem!="")
			{
				demurrer.setDiss_item(disitem);
			}
			demurrer.setBus_id(bus_id);
			FacadeFactory.getApplicantFacade().saveDemurrer(demurrer);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "��������Ǽ���Ϣ����ɹ���", null).toJsonString());
	}
	
	/**
	 * 
	 * saveRevokeapproval:���泷����׼��Ϣ. <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String saveRevokeapproval(Row row){
		//-------ajax---------
		String bus_id = null;
		String proc_id = row.getString("proc_id");
		String revo = row.getString("revo");
		BusRevokeapproval revokeapproval = new BusRevokeapproval();
		try {
			Map map = FacadeFactory.getApplicantFacade().getRegMainById(proc_id);
			bus_id=map.get("BUS_ID").toString();
			revokeapproval.setCan_reason(revo);
			revokeapproval.setBus_id(bus_id);
			revokeapproval.setEffective(WbfConstants.EFFECTIVE);
			revokeapproval.setReg_code(map.get("REG_CODE").toString());
			revokeapproval.setPre_reg_code(map.get("LAST_REG_CODE").toString());
			FacadeFactory.getApplicantFacade().saveRevokeapproval(revokeapproval);			
			
		} catch (Exception e) {
			e.printStackTrace();
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "������׼��Ϣ����ɹ���", null).toJsonString());
	}
	
	
	
	/**
	 * 
	 * saveDemurrer:���汸ע�Ǽ���Ϣ. <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String saveRemark(Row row){
		//-------ajax---------
		String bus_id = null;
		String proc_id = row.getString("proc_id");
		try {
			//List<Map<String,Object>> regUnitRelMapList = reg_relationshipDao.queryMapListByKey("Register.getRegUnitRelMapByRegId", paraMap);
			Map map = FacadeFactory.getApplicantFacade().getRegMainById(proc_id);
			if(map==null)
			{
				return this.setActionJsonObject(new JsonResult(false, "���������ݣ�", null).toJsonString());
			}
			bus_id=map.get("BUS_ID").toString();
			BusRemarkInfo remarkInfo = new BusRemarkInfo();
			remarkInfo.setRemark_type(row.getString("remarkInfo.remark_type"));
			remarkInfo.setRecorder(row.getString("remarkInfo.recorder"));
			remarkInfo.setReg_date(row.getDate("remarkInfo.reg_date"));
			remarkInfo.setRemark_office(row.getString("remarkInfo.remark_office"));
			remarkInfo.setRemark_no(row.getString("remarkInfo.remark_no"));
			remarkInfo.setRemark_person(row.getString("remarkInfo.remark_person"));
			remarkInfo.setRemark_content(row.getString("remarkInfo.remark_content"));
			if(remarkInfo==null||map.get("PRO_DEF_ID").toString().equals(WbfConstants.UNREMARK)||map.get("PRO_DEF_ID").toString().equals(WbfConstants.JUDREMARK))
			{
				remarkInfo=new BusRemarkInfo();
			}
			if(map.get("PRO_DEF_ID").toString().equals(WbfConstants.UNREMARK)||map.get("PRO_DEF_ID").toString().equals(WbfConstants.JUDREMARK))
			{
				String reg_code=map.get("reg_code").toString();
				BusRemarkInfo remark = FacadeFactory.getApplicantFacade().getUnRemark(reg_code);
				remark.setEffective(WbfConstants.UNEFFECTIVE);
				FacadeFactory.getApplicantFacade().saveRemark(remark);
				FacadeFactory.getRegisterFacade().updateRegUnitState(map);
				remarkInfo=new BusRemarkInfo();
				remarkInfo.setRecorder(getOperatorInfo().getUserName());
				remarkInfo.setReg_date(DateUtils.getCurTime());
			}
			if(map.get("LAST_REG_CODE")!=null)
			{
				remarkInfo.setPre_reg_code(map.get("LAST_REG_CODE").toString());
			}
			remarkInfo.setBus_id(bus_id);
			remarkInfo.setEffective(WbfConstants.EFFECTIVE);
			remarkInfo.setReg_code(map.get("REG_CODE").toString());
			
			remarkInfo.setProcdef_id(map.get("PRO_DEF_ID").toString());
			FacadeFactory.getApplicantFacade().saveRemark(remarkInfo);			
			
		} catch (Exception e) {
			e.printStackTrace();
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "��ע��Ϣ����ɹ���", null).toJsonString());
	}
	
	/**
	 * ��ȡ����Ǽ���Ϣ
	 * @return
	 */
	public String getDemurrer(Row row){
		//-------ajax---------
		String proc_id = row.getString("proc_id");
		//��ȡ����ʵ����id
		String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
		String str = "";
		Busdemurrer bos = FacadeFactory.getApplicantFacade().getDemurrer(bus_id);
		if(bos == null){
			
			return null;
		}else{
		
	    return JsonUtil.object2json(bos);
		}
	}
	/**
	 * ��ȡ������׼��Ϣ
	 * @return
	 */
	public String getRevokeapproval(Row row){
		//-------ajax---------
		String proc_id = row.getString("proc_id");
		//��ȡ����ʵ����id
		String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
		String str = "";
		BusRevokeapproval revokeapproval = FacadeFactory.getApplicantFacade().getRevokeapproval(bus_id);
		if(revokeapproval == null){
			
			return null;
		}else{
		
	    return JsonUtil.object2json(revokeapproval);
		}
	}
	
	/**
	 * ��ȡ��ע��Ϣ
	 * @return
	 * @throws GeneralException 
	 */
	public String getRemark(Row row) throws GeneralException{
		//-------ajax---------
		String proc_id = row.getString("proc_id");
		//��ȡ����ʵ����id
		String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
		String str = "";
		BusRemarkInfo remark = FacadeFactory.getApplicantFacade().getRemark(bus_id);
		str=getOperatorInfo().getUserName();
		if(remark == null){
			remark =new BusRemarkInfo();
			remark.setRecorder(str);
			remark.setReg_date(DateUtils.getCurTime());
		}
		return JsonUtil.object2json(remark);
	}
	/**
	 * ��ȡ��һ��ҵ��ע��Ϣ
	 * @return
	 */
	public String getUnRemark(Row row)
	{
		//-------ajax---------
		String reg_code = row.getString("reg_code");
		BusRemarkInfo remark = FacadeFactory.getApplicantFacade().getUnRemark(reg_code);
		if(remark == null){
			
			return null;
		}else{
		
	    return JsonUtil.object2json(remark);
		}
	}
	
	
	/**
	 * ��ȡ��һ��ҵ��������Ϣ
	 * @return
	 */
	public String getDisItem(Row row)
	{
		//-------ajax---------
		//��ȡ����ʵ����id
		//String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
		String str = "";
		String reg_code = row.getString("reg_code");
		Busdemurrer bos = FacadeFactory.getApplicantFacade().getDisItem(reg_code);
		if(bos == null){
			
			return null;
		}else{
		
	    return JsonUtil.object2json(bos);
		}
	}
	
	public String getBusownership(Row row){
		String proc_id = row.getString("proc_id");
		//��ȡ����ʵ����id
		String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
		BusOwnership bos = FacadeFactory.getApplicantFacade().getBusownership(bus_id);
		if(bos == null){
			
			return null;
		}else{
		
	    return JsonUtil.object2json(bos);
		}
	}
}

