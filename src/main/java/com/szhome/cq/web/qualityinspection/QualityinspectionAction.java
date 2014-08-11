
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
 * �������
 * @author   Joyon
 * 	 
 */
public class QualityinspectionAction extends BaseDelegate{
	/**
	 * 
	 * getTargetInfo:(��ȡÿ���µĳ��Ŀ��   ����Ŀ���  ��������). 
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
			LogUtil.error("QualityinspectionAction.getTargetInfo ��ȡ��Ŀ����Ϣ����"+e.getMessage());
		}
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * getCurMonthBusiness:(��ȡ��ǰ�����а������ҵ��).
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
			LogUtil.error("QualityinspectionAction.getCurMonthBusiness ��ȡ��Ŀ����Ϣ����"+e.getMessage());
		}
		return JsonUtil.object2json(resultMapList);
	} 
	
	/**
	 * 
	 * getUncheckSample:(��ȡδ��������).
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
			LogUtil.error("QualityinspectionAction.getUncheckSample ��ȡ��Ŀ����Ϣ����"+e.getMessage());
		}
		return JsonUtil.object2json(resultMapList);
	} 
	/**
	 * 
	 * saveChecksample:(����������Ϣ).
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
	    //���ǰ�˴�������list sizeС����   ֱ�ӷ���   �����κβ���
	    if(list.size()==0)
	    	return null;
	    try {
			FacadeFactory.getQualityinspectionFacade().saveChecksample(list);
			resultMap.put("result", "success");
		} catch (Exception e) {
			LogUtil.error("QualityinspectionAction.saveChecksample: ������������Ϣ����"+e.getMessage());
		}
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * startInspection:(����һ���������ʵ��).
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
	 * getCheckcedbusdata:(ͨ����ǰ������� ������ID   ��ȡ�����ҵ�����������). 
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
			LogUtil.error("QualityinspectionAction.getCheckcedbusdata :��ȡǰһ���ҵ�����ݳ���"+e.getMessage());
		}
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * getHistoryProcActivity:(��ȡ��ǰ���� ��ʷ��������̽ڵ�).
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
	 * getChkQualitytargetinfo:(��ȡ��������ָ����Ϣ).
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
			LogUtil.error("QualityinspectionAction.getChkQualitytargetinfo.��ȡ��������ָ����Ϣ����"+e.getMessage());
		}
		String str = JsonUtil.list2json(list);
		String tmpStr = "{\"total\":"+list.size()+",\"rows\":"+str+"}";
		
		return this.setActionJsonObject(tmpStr);
	}
	/**
	 * 
	 * applyChkQualitytargetEdit:(����������ָ��༭�������Ӧ�õ���̨���ݿ�).
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
	 * deleteChkQualitytarget:(ɾ����������ָ����Ϣ). 
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
			LogUtil.error("ɾ��ʧ��"+e.getMessage());
			result.put("result", "failed");
		}
		
		return JsonUtil.object2json(result);
	}
	
    /**
     * 
     * relQualitytarget:((��������ָ����������ټ�¼��). 
     *
     * @author Joyon
     * @return
     * @since JDK 1.6
     */
    public String relQualitytarget(Row row){
    	//�������Ǳ����� ҵ��id
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
			LogUtil.error("��������ָ�������"+e.getMessage());
		}
    	return null;
    }
    /**
     * 
     * getReledQualitydata:(��ȡ����ҵ�������� �������ٱ����Ѿ�����������ָ���� ).
     *
     * @author Joyon
     * @return
     * @since JDK 1.6
     */
    public String getReledQualitydata(Row row){
    	//-------ajax---------
    	//��ȡ������ҵ��  �ǵ�ǰ����ҵ��ID
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
     * querySamele:(������ɸѡ���������).
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
			LogUtil.error("QualityinspectionAction.querySamele ��������ѯ��������"+e.getMessage());
		}
    	//String str = JsonUtil.list2json(resultList);
    	return JsonUtil.object2json(resultList);
    }
    
    /**
	 * 
	 * getCurMonthStatistics:(���������Ǽ�����  ͳ�Ƶ�ǰ�µĳ������).
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
		//���������������ʱ
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
	 * getDaydetailStatistics:(��ȡÿ����ҵ������). 
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
		//���������������ʱ
//		if(resultList.size()==0 || resultList == null){
//			Map<String,Object> resultMap  = new HashMap<String,Object>();
//			resultMap.put("failed","1");
//			String str = JsonUtil.map2json(resultMap);
//			return this.setActionJsonObject(str);
//		}
		String str = JsonUtil.list2json(resultList);
		
		long end_time = System.currentTimeMillis();
		
		System.out.println("------��ѯ��ʱ:"+(end_time-start_time)+"ms ------");
		return this.setActionJsonObject(str);
	}
    
	/**
	 * 
	 * getRemindersBusiness:(��ȡ��Ҫ�߰��ҵ��).
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
    	//��ǰҳ  
        int intPage = Integer.parseInt((pageNumber == null || pageNumber == "0") ? "1":pageNumber);  
        //ÿҳ��ʾ����  
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
		
		System.out.println("------��ѯ��ʱ:"+(end_time-start_time)+"ms ------");
		return this.setActionJsonObject(str);
	}
	
	/**
	 * 
	 * reminde:(����).
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
	 * getUrgeStatistics:(��ȡ����ͳ������). 
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
	 * changeIserrorstate:(�޸��Ƿ��ǲ���� ).
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
			LogUtil.error("QualityinspectionAction.changeIserrorstate �޸��Ƿ��ǲ����״̬����:"+e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 * getIserrorstate:(��ȡ��ǰ����ҵ���Ƿ��ǲ���� ).
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
			throw new GeneralException("�쳣��Ϣ:"+e.getLocalizedMessage());
		}
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * calculateTarget:(���������). 
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
	 * ��������֪ͨ�����ݵ����ݿ���   ��������Ϣ��
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String saveCorrectnoticeContent(Row row){
		String correct_content = row.getString("correct_content");
		Map resultMap = new HashMap();
		try {
			//�����ݿ��л�ȡ��������Ϣ
			CheckBase checkBase = FacadeFactory.getQualityinspectionFacade()
					.getCheckBaseBycheckprocid(row.getString("proc_id"));
			//�����������Ϣ��Ϊ�� ��ֱ�ӷ��ر���ʧ��
			if(checkBase==null){
				LogUtil.error("QualityinspectionAction.saveCorrectnoticeContent ��������������ݿ����");
				resultMap.put("result", "failed");
				resultMap.put("message", "����ʧ�ܣ�����ϵ����Ա");
				return JsonUtil.map2json(resultMap);
			}
			checkBase.setChe_opinion(correct_content);
			//����
			FacadeFactory.getQualityinspectionFacade().updateCheckBase(
					checkBase);
		} catch (Exception e) {
			LogUtil.error("QualityinspectionAction.saveCorrectnoticeContent ��������������ݿ�����"+e.getMessage());
			resultMap.put("result", "failed");
			resultMap.put("message", "����ʧ�ܣ�����ϵ����Ա"+e.getMessage());
			return JsonUtil.map2json(resultMap);
		}
		resultMap.put("result", "success");
		resultMap.put("message", "����ɹ�");
		return JsonUtil.map2json(resultMap);
	}
	
	/**
	 * 
	 * ��ȡ��������
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
			//�����ݿ��л�ȡ��������Ϣ
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


