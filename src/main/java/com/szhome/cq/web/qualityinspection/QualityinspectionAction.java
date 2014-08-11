
package com.szhome.cq.web.qualityinspection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.plan.commons.Row;
import com.plan.exceptions.GeneralException;
import com.springjdbc.annotation.Page;
import com.szhome.commons.exception.GeneralFailureException;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.CheckBase;
import com.szhome.cq.domain.model.CheckQualityrec;
import com.szhome.cq.domain.model.CheckQualitytarget;
import com.szhome.cq.utils.JsonUtil;

/**
 * 质量抽检
 * @author   Joyon
 * 	 
 */
public class QualityinspectionAction extends BaseDelegate{
	/**
	 * 
	 * getTargetInfo:(获取每个月的抽检目标   包括目标和  己完成情况). 
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getTargetInfo(Row row){
		Map<String, Object> resultMap = null;
		try {
			resultMap = FacadeFactory
					.getQualityinspectionFacade().getTargetInfo();
		} catch (Exception e) {
			LogUtil.error("QualityinspectionAction.getTargetInfo 获取月目标信息出错："+e.getMessage());
		}
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * getCurMonthBusiness:(获取当前月所有办理过的业务).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getCurMonthBusiness(Row row){
		
		List<Map<String, Object>> resultMapList = null;
		try {
			resultMapList = FacadeFactory
					.getQualityinspectionFacade().getCurMonthBusiness();
		} catch (Exception e) {
			LogUtil.error("QualityinspectionAction.getCurMonthBusiness 获取月目标信息出错："+e.getMessage());
		}
		return JsonUtil.object2json(resultMapList);
	} 
	
	/**
	 * 
	 * getUncheckSample:(获取未检查的样本).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getUncheckSample(Row row){
		List<Map<String, Object>> resultMapList = null;
		String search_str = row.getString("search_str");
    	JSONObject jsonObj =  JSONObject.fromObject(search_str);
    	Map strMap = (Map)jsonObj.toBean(jsonObj, Map.class);
		try {
			resultMapList = FacadeFactory
					.getQualityinspectionFacade().getUncheckSample(strMap);
		} catch (Exception e) {
			LogUtil.error("QualityinspectionAction.getUncheckSample 获取月目标信息出错："+e.getMessage());
		}
		return JsonUtil.object2json(resultMapList);
	} 
	/**
	 * 
	 * saveChecksample:(保存样本信息).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String saveChecksample(Row row){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result", "failed");
		
		String lis = row.getString("sample");
		JSONArray jsonArray = JSONArray.fromObject(lis);
	    List<Map<String,Object>> list = JSONArray.toList(jsonArray,Map.class);
	    //如果前端传过来的list size小于零   直接返回   不做任何操作
	    if(list.size()==0)
	    	return null;
	    try {
			FacadeFactory.getQualityinspectionFacade().saveChecksample(list);
			resultMap.put("result", "success");
		} catch (Exception e) {
			LogUtil.error("QualityinspectionAction.saveChecksample: 保存抽检样本信息出错："+e.getMessage());
		}
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * startInspection:(启动一个检查流程实例).
	 *
	 * @author Joyon
	 * @return
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public String startInspection(Row row) throws GeneralException{
		
		String lis = row.getString("selectedrow");
		JSONArray jsonArray = JSONArray.fromObject(lis);
		System.out.println("jsonarray:  " + jsonArray);
		List<Map<String,Object>> list = JSONArray.toList(jsonArray, Map.class);
		if(list.size()==0){
			return null;
		}
		//-------ajax---------
		Map<String,Object> m = FacadeFactory.getQualityinspectionFacade().startInspection(this.getOperatorInfo(),list);
//		JSONArray a = new JSONArray();
//		a.put(m);		
		return JsonUtil.object2json(m);//this.setActionJsonObject(a.toString());
	}
	
	
	/**
	 * 
	 * getCheckcedbusdata:(通过当前检查流程 的流程ID   获取被检查业务的流程数据). 
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public String getCheckcedbusdata(Row row){
		Map<String, Object> resultMap = null;
		String state = row.getString("state");
		String proc_id = row.getString("proc_id");
		try {
			resultMap = FacadeFactory
					.getQualityinspectionFacade().getCheckcedbusdataByProcid(
							proc_id,state);
		} catch (Exception e) {
			LogUtil.error("QualityinspectionAction.getCheckcedbusdata :获取前一检查业务数据出错"+e.getMessage());
		}
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * getHistoryProcActivity:(获取当前流程 历史办过的流程节点).
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getHistoryProcActivity(Row row){
		//-------ajax---------
		String str ="";
		String proc_id = row.getString("proc_id");
		List list = FacadeFactory.getQualityinspectionFacade().getHistoryprocactivityByProcid(proc_id);
		Map<String,Object> map = new HashMap<String,Object>();
		if(list!=null&&!list.isEmpty()){
			map.put("rows", list);
		}
		try {
			str = JsonUtil.object2json(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int strStart = 8;//str.indexOf("{\"rows\":");
		str = str.substring(strStart, str.length()-1);
		return this.setActionJsonObject(str);
	}
	/**
	 * 
	 * getChkQualitytargetinfo:(获取跟踪质量指标信息).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getChkQualitytargetinfo(Row row){
		//-------ajax---------
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List list = null;
		try {
			list = FacadeFactory.getQualityinspectionFacade().getChkQualitytargetList();
		} catch (Exception e) {
			LogUtil.error("QualityinspectionAction.getChkQualitytargetinfo.获取质量跟踪指标信息出错"+e.getMessage());
		}
		String str = JsonUtil.list2json(list);
		String tmpStr = "{\"total\":"+list.size()+",\"rows\":"+str+"}";
		
		return this.setActionJsonObject(tmpStr);
	}
	/**
	 * 
	 * applyChkQualitytargetEdit:(将质量跟踪指标编辑后的数据应用到后台数据库).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public String applyChkQualitytargetEdit(Row row) throws GeneralException{
		String userName = getOperatorInfo().getUserName();
		String datas = row.getString("datas");
		Map map = new HashMap();
		map.put("datas",datas);
		map.put("userName", userName);
		
		try {
			map = FacadeFactory.getQualityinspectionFacade().applyChkQualitytargetEdit(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.object2json(map);
	}
	
	/**
	 * 
	 * deleteChkQualitytarget:(删除质量跟踪指标信息). 
	 *
	 * @author Joyon
	 * @param chk
	 * @return
	 * @since JDK 1.6
	 */
	public String deleteChkQualitytarget(Row row){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("result", "success");
		String qua_tar_id = row.getString("qua_tar_id");
		CheckQualitytarget chk = new CheckQualitytarget();
		chk.setQua_tar_id(qua_tar_id);
		try {
			FacadeFactory.getQualityinspectionFacade().deleteChkQualitytarget(
					chk);
		} catch (Exception e) {
			LogUtil.error("删除失败"+e.getMessage());
			result.put("result", "failed");
		}
		
		return JsonUtil.object2json(result);
	}
	
    /**
     * 
     * relQualitytarget:((关联质量指标项到质量跟踪记录表). 
     *
     * @author Joyon
     * @return
     * @since JDK 1.6
     */
    public String relQualitytarget(Row row){
    	//关联的是被检查的 业务id
    	String proc_id = row.getString("proc_id");
    	String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getLast_bus_id();
    	String qua_tar_id = row.getString("qua_tar_id");
    	String type = row.getString("type");
    	String activdefId = row.getString("activdefId");
        String activdefName = row.getString("activdefName");
       // Map map = row.getStringMap();
       
    	try {
			FacadeFactory.getQualityinspectionFacade().relQualitytarget(qua_tar_id, type, bus_id,activdefId,activdefName);
		} catch (Exception e) {
			LogUtil.error("关联质量指标项出错，"+e.getMessage());
		}
    	return null;
    }
    /**
     * 
     * getReledQualitydata:(获取本次业务检查流程 质量跟踪表中已经关联的质量指标项 ).
     *
     * @author Joyon
     * @return
     * @since JDK 1.6
     */
    public String getReledQualitydata(Row row){
    	//-------ajax---------
    	//获取关联的业务  是当前检查的业务ID
    	String proc_id = row.getString("proc_id");
    	String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getLast_bus_id();
    	String activdefId = row.getString("activdefId");
    	List<CheckQualityrec> resultList =  FacadeFactory.getQualityinspectionFacade().getCheckQualityrecListByBusid(bus_id,activdefId);
    	//String str = listToJson(resultList);
    	String str = JsonUtil.list2json(resultList);
    	return this.setActionJsonObject(str);
    }
    /**
     * 
     * querySamele:(按条件筛选出抽检样本).
     *
     * @author Joyon
     * @return
     * @since JDK 1.6
     */
    public String querySamele(Row row){
    	////-------ajax---------
    	String search_str = row.getString("search_str");
    	JSONObject jsonObj =  JSONObject.fromObject(search_str);
    	Map strMap = (Map)jsonObj.toBean(jsonObj, Map.class);
    	
    	List resultList = null;
    	try {
			resultList = FacadeFactory.getQualityinspectionFacade()
					.querySamele(strMap);
		} catch (Exception e) {
			LogUtil.error("QualityinspectionAction.querySamele 按条件查询样本出错："+e.getMessage());
		}
    	//String str = JsonUtil.list2json(resultList);
    	return JsonUtil.object2json(resultList);
    }
    
    /**
	 * 
	 * getCurMonthStatistics:(按部门名登记类型  统计当前月的抽检数据).
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getCurMonthStatistics(Row row){
		//-------ajax---------
		String start_date = row.getString("start_date");
		String end_date = row.getString("end_date");
		String reg_station = row.getString("reg_station");
		List resultList = FacadeFactory.getQualityinspectionFacade().getCurMonthStatistics(start_date,end_date,reg_station);
		//如果数据中无数据时
//		if(resultList.size()==0 || resultList == null){
//			Map<String,Object> resultMap  = new HashMap<String,Object>();
//			resultMap.put("failed","1");
//			String str = JsonUtil.map2json(resultMap);
//			return this.setActionJsonObject(str);
//		}
		String str = JsonUtil.list2json(resultList);
		return this.setActionJsonObject(str);
	}
	
	/**
	 * 
	 * getDaydetailStatistics:(获取每天抽检业务详情). 
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getDaydetailStatistics(Row row){
		//-------ajax---------
		long start_time = System.currentTimeMillis();
		Map<String,Object> paraMap = new HashMap<String,Object>();
		String start_date = row.getString("start_date");
		String end_date = row.getString("end_date");
		String dept_id = row.getString("dept_id");
		String user_id = row.getString("user_id");
		String location = row.getString("location");
		paraMap.put("start_date", start_date);
		paraMap.put("end_date", end_date);
		paraMap.put("dept_id", dept_id);
		paraMap.put("user_id", user_id);
		paraMap.put("location", location);
		
		List resultList = FacadeFactory.getQualityinspectionFacade().getDaydetailStatistics(paraMap);
		//如果数据中无数据时
//		if(resultList.size()==0 || resultList == null){
//			Map<String,Object> resultMap  = new HashMap<String,Object>();
//			resultMap.put("failed","1");
//			String str = JsonUtil.map2json(resultMap);
//			return this.setActionJsonObject(str);
//		}
		String str = JsonUtil.list2json(resultList);
		
		long end_time = System.currentTimeMillis();
		
		System.out.println("------查询用时:"+(end_time-start_time)+"ms ------");
		return this.setActionJsonObject(str);
	}
    
	/**
	 * 
	 * getRemindersBusiness:(获取需要催办的业务).
	 *
	 * @author Joyon
	 * @return
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public String getRemindersBusiness(Row row) throws GeneralException{
		//-------ajax---------
		long start_time = System.currentTimeMillis();
		String search_str = row.getString("search_str");
		String pageNumber = row.getString("pageNumber");
		String maxPageItems = row.getString("maxPageItems");
    	JSONObject jsonObj =  JSONObject.fromObject(search_str);
    	Map strMap = (Map)jsonObj.toBean(jsonObj, Map.class);
    	//当前页  
        int intPage = Integer.parseInt((pageNumber == null || pageNumber == "0") ? "1":pageNumber);  
        //每页显示条数  
        int number = Integer.parseInt((maxPageItems == null || maxPageItems == "0") ? "10":maxPageItems);  
		Page resultList = null;
		try {
			resultList = FacadeFactory.getQualityinspectionFacade()
					.getRemindersBusiness(strMap, intPage, number);
		} catch (Exception e) {
			LogUtil.error("QualityinspectionAction.getRemindersBusiness:"+e.getMessage());
			throw new GeneralException("QualityinspectionAction.getRemindersBusiness:"+e.getMessage());
		}
		String str = pageResultToJson(resultList);
		
		long end_time = System.currentTimeMillis();
		
		System.out.println("------查询用时:"+(end_time-start_time)+"ms ------");
		return this.setActionJsonObject(str);
	}
	
	/**
	 * 
	 * reminde:(督办).
	 *
	 * @author Joyon
	 * @return
	 * @throws GeneralFailureException 
	 * @since JDK 1.6
	 */
	public String reminde(Row row) throws GeneralFailureException{
		String bus_id = row.getString("bus_id");
		Map<String, Object> paraMap = FacadeFactory.getQualityinspectionFacade().reminde(row);
		return JsonUtil.object2json(paraMap);
	}
	
	
	
	/**
	 * 
	 * getUrgeStatistics:(获取督办统计数据). 
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getUrgeStatistics(Row row){
		String search_str = row.getString("search_str");
    	JSONObject jsonObj =  JSONObject.fromObject(search_str);
    	Map strMap = (Map)jsonObj.toBean(jsonObj, Map.class);
//    	request.getSession().setAttribute("strMap", strMap);
		//Map paraMap = getParameterMap(getRequest());
		List resultList  = FacadeFactory.getQualityinspectionFacade().getUrgeStatistics(strMap);
		return JsonUtil.object2json(resultList);
	}
	
	/**
	 * 
	 * changeIserrorstate:(修改是否是差错文 ).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String changeIserrorstate(Row row){
		String proc_id = row.getString("proc_id");
		String type = row.getString("type");
		String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getLast_bus_id();
		try {
			FacadeFactory.getQualityinspectionFacade().changeIserrorstate(
					bus_id, type, proc_id);
		} catch (Exception e) {
			LogUtil.error("QualityinspectionAction.changeIserrorstate 修改是否是差错文状态出错:"+e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 * getIserrorstate:(获取当前检查的业务是否是差错文 ).
	 *
	 * @author Joyon
	 * @return
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public String getIserrorstate(Row row) throws GeneralException{
		String proc_id = row.getString("proc_id");
		String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getLast_bus_id();
		CheckBase checkBase = null;
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			checkBase =	FacadeFactory.getQualityinspectionFacade().getCheckBaseBybusidandcheckprocid(bus_id, proc_id);
			resultMap.put("is_error", checkBase==null?"":checkBase.getIs_error());
		} catch (Exception e) {
			LogUtil.error("QualityinspectionFacde.getIserrorstate");
			throw new GeneralException("异常信息:"+e.getLocalizedMessage());
		}
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * calculateTarget:(抽检量计算). 
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String calculateTarget(Row row){
		//String monthStr = row.getString("monthStr");
		//int i = 0;
		List resultList = FacadeFactory.getQualityinspectionFacade().calculateTarget(row);
		return JsonUtil.object2json(resultList);
	}

	/**
	 * 
	 * 保存整改通知书内容到数据库中   检查基本信息表
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String saveCorrectnoticeContent(Row row){
		String correct_content = row.getString("correct_content");
		Map resultMap = new HashMap();
		try {
			//从数据库中获取抽检基本信息
			CheckBase checkBase = FacadeFactory.getQualityinspectionFacade()
					.getCheckBaseBycheckprocid(row.getString("proc_id"));
			//如果检查基本信息表为空 则直接返回保存失败
			if(checkBase==null){
				LogUtil.error("QualityinspectionAction.saveCorrectnoticeContent 保存抽检意见到数据库出错");
				resultMap.put("result", "failed");
				resultMap.put("message", "保存失败！请联系管理员");
				return JsonUtil.map2json(resultMap);
			}
			checkBase.setChe_opinion(correct_content);
			//更新
			FacadeFactory.getQualityinspectionFacade().updateCheckBase(
					checkBase);
		} catch (Exception e) {
			LogUtil.error("QualityinspectionAction.saveCorrectnoticeContent 保存抽检意见到数据库库出错"+e.getMessage());
			resultMap.put("result", "failed");
			resultMap.put("message", "保存失败！请联系管理员"+e.getMessage());
			return JsonUtil.map2json(resultMap);
		}
		resultMap.put("result", "success");
		resultMap.put("message", "保存成功");
		return JsonUtil.map2json(resultMap);
	}
	
	/**
	 * 
	 * 获取整改内容
	 *
	 * @author Joyon
	 * @param row
	 * @return
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public String getCorrectnoticeContent(Row row) throws GeneralException{
		Map resultMap = new HashMap();
		try {
			//从数据库中获取抽检基本信息
			CheckBase checkBase = FacadeFactory.getQualityinspectionFacade()
					.getCheckBaseBycheckprocid(row.getString("proc_id"));
			if (checkBase!=null && checkBase.getChe_opinion() != null) {
				resultMap.put("correct_content", checkBase.getChe_opinion());
			}
		} catch (Exception e) {
			LogUtil.error("QualityinspectionAction.getCorrectnoticeContent:"+e.getMessage());
			throw new GeneralException("QualityinspectionAction.getCorrectnoticeContent:"+e.getMessage());
		}
		return JsonUtil.map2json(resultMap);
	}

}


