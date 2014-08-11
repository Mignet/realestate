package com.szhome.cq.web.mortgage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.json.JSONArray;

import com.plan.commons.Row;
import com.plan.exceptions.GeneralException;
import com.plan.web.JsonResult;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.vo.OwnerInfoVo;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.Certificate;
import com.szhome.cq.domain.model.Mortgage;
import com.szhome.cq.utils.JsonUtil;
import com.szhome.cq.utils.WbfConstants;

/**
 * 抵押业务
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MorsetupAction extends BaseDelegate{
	
//	private String proc_id;
//	private Mortgage mort;
//	private OwnerInfoVo oivo;
//	//申请人类型
//	private String apptype;
	//设置抵押人类型的值
	private static final String mortgager = WbfConstants.MORTGAGER;
	//设置抵押权人类型的值
	private static final String mortgagee = WbfConstants.MORTGAGEE;
//	//房地产证表实体
//	private Certificate cer;
//	
//	//登记单元集合
//	private String regUnitList;
	
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
			resultMap = FacadeFactory.getMortgageFacade().getRegById(proc_id);
			if(resultMap.isEmpty()){
				
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.object2json(resultMap);
	}
	/**
	 * 
	 * getHouseInfo:获取抵押房地产信息. <br/>
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
			naturalInfoMap = FacadeFactory.getRegisterFacade().getNaturalInfo(
					paraMap);
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
	 * @author PANDA
	 * @return
	 * @since JDK 1.6
	 */
	public String saveRegInfo(Row row){
		String proc_id = row.getString("proc_id");
		try {
			String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			OwnerInfoVo oivo = new OwnerInfoVo();
			oivo.setReg_station(row.getString("oivo.reg_station"));
			oivo.setReg_code(row.getString("oivo.reg_code"));
			oivo.setReg_type(row.getString("oivo.reg_type"));
			oivo.setProc_name(row.getString("oivo.proc_name"));
			oivo.setLocation_reg_unit(row.getString("oivo.location_reg_unit"));
			oivo.setBus_id(bus_id);
			oivo.setProc_id(proc_id);
			FacadeFactory.getApplicantFacade().saveRegistMess(oivo);
		} catch (Exception e) {
			e.printStackTrace();
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "登记信息保存成功！", null).toJsonString());
	}
	
	 /**
     * 
     * getAppMessage:根据传入流程实例id获取申请人信息. <br/>
     *
     * @author PANDA
     * @return
     * @since JDK 1.6
     */
	public String getAppMessage(Row row){
		//获取流程实例表id
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
	 * getMortById:根据id获取抵押登记信息. <br/>
	 * @author PANDA
	 * @return
	 * @since JDK 1.6
	 */
	public String getMortById(Row row){
		String proc_id = row.getString("proc_id");
		String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
		Map m = new HashMap();
		m.put("id", bus_id);	
		Mortgage mo = FacadeFactory.getMortgageFacade().getMortById(m);
		if(mo == null){
			
			return this.setActionJsonObject(new JsonResult(true, "抵押信息不存在！", null).toJsonString());	
		}
		
	    return JsonUtil.object2json(mo);
	}
	
	
	/**
	 * 
	 * saveMortMess:保存抵押登记信息. <br/>
	 * @author PANDA
	 * @return
	 * @since JDK 1.6
	 */
	public String saveMortMess(Row row){
		String proc_id = row.getString("proc_id");
		try {
			String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			Mortgage mort = new Mortgage();
			mort.setMort_type(row.getString("mort.mort_type"));
			mort.setLoan_usage(row.getString("mort.loan_usage"));
			mort.setMort_con_no(row.getString("mort.mort_con_no"));
			mort.setRel_orig_value(row.getDouble("mort.rel_orig_value"));
			mort.setMort_assure_right(row.getDouble("mort.mort_assure_right"));
			mort.setAssess_price(row.getDouble("mort.assess_price"));
			mort.setAgreed_price(row.getDouble("mort.agreed_price"));
			mort.setMort_seq(row.getString("mort.mort_seq"));
			mort.setCreditor_start_date(row.getDate("mort.creditor_start_date"));
			mort.setCreditor_end_date(row.getDate("mort.creditor_end_date"));
			mort.setBorrower(row.getString("mort.borrower"));
			mort.setMort_port(row.getString("mort.mort_port"));
			mort.setAssuer_range(row.getString("mort.assuer_range"));
			mort.setMax_amount(row.getString("mort.max_amount"));
			mort.setSure_amount(row.getDouble("mort.sure_amount"));
			mort.setBus_id(bus_id);
			FacadeFactory.getMortgageFacade().saveMortMess(mort);
		} catch (Exception e) {
			e.printStackTrace();
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "抵押信息保存成功！", null).toJsonString());
		
		
	}
	/**
	 * 
	 * getMortcanMess:获取注销抵押登记信息. <br/>
	 * @author PANDA
	 * @return
	 * @since JDK 1.6
	 */
	public String getMortcanMess(Row row){
		Map<String,Object> resultMap = null;
		String proc_id = row.getString("proc_id");
		try {
			String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			resultMap = FacadeFactory.getMortgageFacade().getMortcanById(bus_id);
			if(resultMap.isEmpty()){
				return this.setActionJsonObject(new JsonResult(true, "查询失败！", null).toJsonString());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.object2json(resultMap);
	}
    /**
     * 
     * getRegunitMess:获取选择的登记单元信息. <br/>
     * @author PANDA
     * @return
     * @since JDK 1.6
     */
	public String getRegunitMess(Row row){
		//-------ajax---------
		//获取流程实例表id
		String proc_id = row.getString("proc_id");
		String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
		Map m = new HashMap();
		m.put("id",bus_id);
		String str = "";
		List<Map<String, Object>> apps = FacadeFactory.getMortgageFacade().getRegunitList(m);
		if(apps.isEmpty()){
			return this.setActionJsonObject(new JsonResult(true, "数据不存在！", null).toJsonString());
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
		return this.setActionJsonObject(str);

	}
	
	/**
	 * 
	 * getMortgager:获取抵押人信息. <br/>
	 * @author PANDA
	 * @return
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public String getMortapp(Row row) throws GeneralException{
		String proc_id = row.getString("proc_id");
		String str;
		Map<String,Object> map = null;
		try {
			String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			Map m = new HashMap();
			m.put("id", bus_id);
			str = "";
			List<Map<String, Object>> apps = null;
			String apptype = row.getString("apptype");
			//根据apptype判断应该获取抵押人信息，还是抵押权人信息
			if (apptype.equals(mortgager)) {
				m.put("type", mortgager);
				apps = FacadeFactory.getMortgageFacade().getMortgager(m);
			} else if (apptype.equals(mortgagee)) {
				m.put("type", mortgagee);

				apps = FacadeFactory.getMortgageFacade().getMortgagee(m);
				//抵押权转让方
			} else if (apptype.equals(WbfConstants.MORTGAGE_TRANSFEROR)) {
				m.put("type", WbfConstants.MORTGAGE_TRANSFEROR);
				apps = FacadeFactory.getMortgageFacade()
						.getMortgageeTransferor(m);

				//抵押权受让方
			} else if (apptype.equals(WbfConstants.MORTGAGE_TRANSFEROREE)) {
				m.put("type", WbfConstants.MORTGAGE_TRANSFEROREE);
				apps = FacadeFactory.getMortgageFacade().getMortgagee(m);
			}
			map = new HashMap<String, Object>();
			if (apps != null) {
				//记录了查询总数
				map.put("total", apps.size());
				//记录了当前页的数据
				map.put("rows", apps);
			}
		} catch (Exception e) {
			LogUtil.error("MoresetUpAction.getMortapp:"+e.getMessage());
			throw new GeneralException("MoresetUpAction.getMortapp:"+e.getMessage());
		}
		try {
			str = JsonUtil.object2json(map);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.setActionJsonObject(str);
	}
	/**
	 * 
	 * getMortappForSet:获取已存在抵押记录的抵押人和抵押权人信息. <br/>
	 * @author PANDA
	 * @return
	 * @since JDK 1.6
	 */
	public String getMortappForSet(Row row){
		String proc_id = row.getString("proc_id");
		BusinessMain bus = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
		String code = "DJBH-2014030500407";
		Map m = new HashMap();
		m.put("id",bus.getBus_id());
		//设置前登记编号
		m.put("code",code);
		String str = "";
		List<Map<String, Object>> apps = null;
		String apptype = row.getString("apptype");
		//根据apptype判断应该获取抵押人信息，还是抵押权人信息
		if(apptype.equals(mortgager)){
			m.put("type",mortgager);
			apps = FacadeFactory.getMortgageFacade().getMortgagerSeted(m);
		};
		if(apptype.equals(mortgagee)){
			m.put("type",mortgagee);
			
			apps = FacadeFactory.getMortgageFacade().getMortgageeSeted(m);
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
		return this.setActionJsonObject(str);
	}
	
	/**
	 * 
	 * saveDispathcInfo:保存发文信息. <br/>
	 * @author PANDA
	 * @return
	 * @since JDK 1.6
	 */
	public String saveDispathcInfo(Row row){
		String proc_id = row.getString("proc_id");
		try {
			String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			String lis = row.getString("regUnitList");
			JSONArray jsonArray = JSONArray.fromObject(lis);
		    List<Map> list = JSONArray.toList(jsonArray, Map.class);
		    Certificate cer = new Certificate();
		    cer.setRec_person(row.getString("cer.rec_person"));
		    cer.setRec_cer_no(row.getString("cer.rec_cer_no"));
		    cer.setRec_type(row.getString("cer.rec_type"));
		    cer.setRec_cer_type(row.getString("cer.rec_cer_type"));
		    cer.setRec_date(row.getDate("cer.rec_date"));
		    FacadeFactory.getMortgageFacade().saveDispathcInfo(cer, list, bus_id);
				
		} catch (Exception e) {
			e.printStackTrace();
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "发证信息保存成功！", null).toJsonString());
	}
	
	/**
	 * 
	 * getChangeMortgager:(获取变更抵押人). 
	 *
	 * @author Joyon
	 * @return
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public String getChangeMortgager(Row row) throws GeneralException{
		List<Map<String,Object>> resultList = null;
		try {
			String proc_id = row.getString("proc_id");
			String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id();
			resultList = FacadeFactory.getMortgageFacade().saveBkMortgagerToBusMortgager(bus_id);
		} catch (Exception e) {
			LogUtil.error("MorsetupAction.getChangeMortgager"+e.getMessage());
			throw new GeneralException("MorsetupAction.getChangeMortgager"+e.getMessage());
		}
		return JsonUtil.object2json(resultList);
	}
	/**
	 * 
	 * getChangeMortgagee:(获取变更抵押权人).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getChangeMortgagee(Row row){
		String proc_id = row.getString("proc_id");
		String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id();
		List<Map<String,Object>> resultList = FacadeFactory.getMortgageFacade().saveBkMortgageeToBusMortgagee(bus_id);
		return JsonUtil.object2json(resultList);
	}
	
	/**
	 * 
	 * getChangeMortgage:获取抵押权变更抵押信息 <br/>
	 * @author PANDA
	 * @return
	 * @since JDK 1.6
	 */
	public String getChangeMortgage(Row row){
		String proc_id = row.getString("proc_id");
		String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id();
		Mortgage mortgage = null;
		try {
			mortgage = FacadeFactory.getMortgageFacade()
					.saveBkMortageToBusMortgage(bus_id);
		} catch (Exception e) {
			LogUtil.error("MorsetupAction.getChangeMort  获取抵押信息出错 "+e.getMessage());
		}
		if(mortgage == null){
			return this.setActionJsonObject(new JsonResult(true, "抵押信息不存在！", null).toJsonString());
		}
		
	    return JsonUtil.object2json(mortgage);
	}
	
	/**
	 * 
	 * getMortCancelMortgager:(获取抵押注销抵押人). 
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getMortCancelMortgager(Row row){
		String proc_id = row.getString("proc_id");
		String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id();
		List<Map<String,Object>> resultList = null;
		try {
			resultList = FacadeFactory.getMortgageFacade()
					.getMortCancelMortgager(bus_id);
		} catch (Exception e) {
			LogUtil.error("MorsetupAction.getMortCancelMortgager 获取抵押注销抵押人出错:"+e.getMessage());
		}
		return JsonUtil.object2json(resultList);
	}
	
}


