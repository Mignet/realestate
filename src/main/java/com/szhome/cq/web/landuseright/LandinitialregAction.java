package com.szhome.cq.web.landuseright;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.plan.commons.Row;
import com.plan.util.json.JsonParserJsonlibImpl;
import com.plan.web.JsonResult;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.vo.OwnerInfoVo;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.Applicant;
import com.szhome.cq.domain.model.Bususeright;
import com.szhome.cq.utils.DateUtils;
import com.szhome.cq.utils.JsonUtil;
public class LandinitialregAction extends BaseDelegate{
//	private BusinessMain bus;
//	private String proc_id;
//	private OwnerInfoVo oivo;
//	private Applicant app;
//	private Certificate c;
//	private Bususeright use;
	
	/**
	 * 
	 * getRegMessage:根据流程实例id获取登记信息
	 *
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String getRegMessage(Row row){
		String proc_id = row.getString("proc_id");
		Map map = FacadeFactory.getApplicantFacade().getRegistMessById(proc_id );
	    return JsonUtil.map2json(map);
	}
	/**
	 * 
	 * saveRegMessage:保存登记信息
	 *
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	@Deprecated
	public String saveRegMessage(Row row){
		String proc_id = row.getString("proc_id");
		try {
			String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			OwnerInfoVo oivo = new OwnerInfoVo();
			oivo .setReg_code(row.getString("oivo.reg_code"));
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
		return this.setActionJsonObject(new JsonResult(true, "保存登记信息成功！", null).toJsonString());
	}
    /**
     * 
     * getAppMessage:根据传入流程实例id获取申请人信息. <br/>
     *
     * @author xuzz
     * @return
     * @since JDK 1.6
     */
	public String getAppMessage(Row row){
		String proc_id = row.getString("proc_id");
		//获取流程实例表id
		//int proc_id = new Integer((String)this.getRequest().getParameter("proc_id"));
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
	 * 保存单条申请人信息. <br/>
	 *
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String saveApplicant(Row row)  {
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
		try {
			String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			app.setBus_id(bus_id);
			FacadeFactory.getApplicantFacade().saveApplicant(app);
		} catch (Exception e) {
			e.printStackTrace();
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "添加申请人成功！", null).toJsonString());
	}
	/**
	 * 
	 * 更新单条申请人信息. <br/>
	 *
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String updateApplicant(Row row)  {
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
			try 
			{
				FacadeFactory.getApplicantFacade().updateApplicant(app);			
			} 
			catch (Exception e) {
				e.printStackTrace();
				return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
			}	
			return this.setActionJsonObject(new JsonResult(true, "更新申请人成功！", null).toJsonString());	
	}
	/**
	 * 
	 * 删除单条申请人信息. <br/>
	 *
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String deleteApplicant(Row row)  {
		String id = row.getString("applicant_id");
		 try {
			FacadeFactory.getApplicantFacade().delApplicant(id);
		} catch (Exception e) {

			e.printStackTrace();
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		
		 return this.setActionJsonObject(new JsonResult(true, "删除申请人成功！", null).toJsonString());	
	}
	/**
	 * 
	 * 保存房地产证附记. <br/>
	 *
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String saveCerRemark(Row row)  {
		String proc_id = row.getString("proc_id");
		OwnerInfoVo oivo = new OwnerInfoVo();
		oivo.setExcursus(row.getString("excursus"));
		try {
			String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			oivo.setBus_id(bus_id);
			FacadeFactory.getCertificateFacade().saveorupdateExcursus(oivo.getBus_id(), oivo.getExcursus());
			Bususeright use =new Bususeright();
			use.setLand_use(row.getString("land_use"));
			use.setGet_price(row.getDouble("get_price"));
			use.setUseright_type(row.getString("useright_type"));
			use.setUse_limit(row.getString("use_limit"));
			use.setStart_date(DateUtils.string2Date(row.getString("start_date"),"yyyy-MM-dd"));
			use.setEnd_date(DateUtils.string2Date(row.getString("end_date"),"yyyy-MM-dd"));
			//			FacadeFactory.getApplicantFacade().saveCerRemark(oivo);
			use .setBus_id(bus_id);
			FacadeFactory.getApplicantFacade().saveUseright(use);
		} catch (Exception e) {
			e.printStackTrace();
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "保存成功！", null).toJsonString());
	}
	/**
	 * 
	 * saveUseright:保存土地使用权登记信息到过程表中. <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String saveUseright(Row row){
		String proc_id = row.getString("proc_id");
		Bususeright use =new Bususeright();
		use.setLand_use(row.getString("use.land_use"));
		use.setGet_price(row.getDouble("use.get_price"));
		use.setUseright_type(row.getString("use.useright_type"));
		use.setUse_limit(row.getString("use.use_limit"));
		use.setStart_date(row.getDate("use.start_date"));
		use.setEnd_date(row.getDate("use.end_date"));
		try {
			String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			use.setBus_id(bus_id);
			if(use==null)
			{
				use=new Bususeright();
			}
			String reason = row.getString("reason");
			if(reason!=null)
			{
				use.setReason(reason);
			}
			FacadeFactory.getApplicantFacade().saveUseright(use);
			//saveCerRemark();
		} catch (Exception e) {
			e.printStackTrace();
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "土地使用权信息保存成功！", null).toJsonString());
		
		
	}
	
	/**
	 * 
	 * getUseright:获取土地使用权登记信息到过程表中. <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String getUseright(Row row){
		String proc_id = row.getString("proc_id");
		//获取流程实例表id
		String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
		String str = "";
		Bususeright bos = FacadeFactory.getApplicantFacade().getUseright(bus_id);
		try {
			if(bos==null)
			{
				return this.setActionJsonObject(new JsonResult(true, "土地使用权信息为空！", null).toJsonString());
			}
			str = new JsonParserJsonlibImpl().toJson(str);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   return JsonUtil.object2json(bos);
		//return JsonUtil.object2json(bos);
	}
}

