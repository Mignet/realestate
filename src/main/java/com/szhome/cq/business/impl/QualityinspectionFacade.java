/**
 * Project Name:dxtx_re
 * File Name:QualityinspectionFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-4-15上午11:08:08
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.business.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.plan.message.SendMessage;
import com.springjdbc.annotation.Page;
import com.szhome.commons.exception.GeneralFailureException;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IQualityinspectionFacade;
import com.szhome.cq.business.ResultMsg;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.CheckBase;
import com.szhome.cq.domain.model.CheckQualityrec;
import com.szhome.cq.domain.model.CheckQualitytarget;
import com.szhome.cq.domain.model.DicItem;
import com.szhome.cq.domain.model.Reg_relationship;
import com.szhome.cq.domain.model.UrgeRecord;
import com.szhome.cq.utils.ConvertTool;
import com.szhome.cq.utils.DateUtils;
import com.szhome.cq.utils.Util;
import com.szhome.cq.utils.WbfConstants;
import com.szhome.process.client.WorkflowClient;
import com.szhome.process.client.WorkflowClientFacade;
import com.szhome.process.client.WorkflowClientFactory;
import com.szhome.process.context.WorkflowApplicationContext;
import com.szhome.process.delegate.ParticipantDelegate;
import com.szhome.process.entity.ActivityInst;
import com.szhome.process.entity.Participant;
import com.szhome.process.entity.ProcessInst;
import com.szhome.process.entity.ProcessTask;
import com.szhome.process.entity.WorkItem;
import com.szhome.process.exceptions.ProcessException;
import com.szhome.security.ext.UserInfo;

/**
 * 质量督查facade
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class QualityinspectionFacade implements IQualityinspectionFacade {
	private String INSPECTIONED="1";
	private String UNINSPECTIONED="0";
	private String REG_FIRST= "10900004";  //产权登记中心登记一科
	private String REG_SECOND= "10900006"; //产权登记中心登记二科
	private String REG_THIRD= "10900005";  //产权登记中心登记三科
	private String REG_LONGHUA= "10013304"; //产权登记中心龙华登记点
	private String REG_GUANGMIN= "10013302"; //产权登记中心光明登记点
	private String REG_REC_DIS= "10013309"; //产权登记中心收发文科
	private String REG_PINGSHAN= "30000127"; //产权登记中心坪山登记点
	private String TOTAL = "total";				//合计行
//	/** 工作流客户端统一接口 */
	private WorkflowClientFacade workflowClientFacade = WorkflowClientFactory.getWorkflowClientFacade();
//	/** 工作流客户端接口*/
	private WorkflowClient workflowClient = workflowClientFacade.getWorkflowClient();
	@Autowired
	private BusinessMain businessMainDao;
	@Autowired
	private CheckBase checkBaseDao;									//抽检基本信息
	@Autowired
	private CheckQualitytarget chkQualitytargetDao;					//质量跟踪指标信息
	@Autowired
	private CheckQualityrec checkQualityrecDao;						//质量跟踪记录表信息
	@Autowired
	private UrgeRecord urgeRecordDao;								//督办记录表
	
	/**
	 * 
	 * calculateTarget:(抽检量计算). 
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> calculateTarget(Map<String,Object> paraMap)  throws BusinessException{
		if(paraMap == null){
			paraMap = new HashMap<String,Object>();
			String monthStr = org.apache.tools.ant.util.DateUtils.format(new Date(), "yyyy-mm");
			paraMap.put("monthStr", monthStr);
		}
		//1、获取部门当月办文量  及收文量
			List<Map<String,Object>> deptbusinesslist = businessMainDao.queryMapListByKey("Qualityinspection.getDeptbusinessBymonth", paraMap);
		//2、获取部门当月 抽检量
			List<Map<String,Object>> depttargetlist = businessMainDao.queryMapListByKey("Qualityinspection.getDepttargetBymonth", paraMap);
		//3、拼装  
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		//获取月工作日
		int monthworkday = getMonthWorkdaycount(paraMap.get("monthStr").toString());
		Map<String,Object> tmpResultMap = null;
		//1、收发文 科
		tmpResultMap = getcalculateTargetMap(this.REG_REC_DIS,deptbusinesslist,depttargetlist,monthworkday);
		tmpResultMap.put("DEPT_NAME", "收发文科");
		resultList.add(tmpResultMap);
		
		//2、登记一科
		tmpResultMap = getcalculateTargetMap(this.REG_FIRST,deptbusinesslist,depttargetlist,monthworkday);
		tmpResultMap.put("DEPT_NAME", "登记一科");
		resultList.add(tmpResultMap);
		//3、登记二科
		tmpResultMap = getcalculateTargetMap(this.REG_SECOND,deptbusinesslist,depttargetlist,monthworkday);
		tmpResultMap.put("DEPT_NAME", "登记二科");
		resultList.add(tmpResultMap);
		//4、登记三科
		tmpResultMap = getcalculateTargetMap(this.REG_THIRD,deptbusinesslist,depttargetlist,monthworkday);
		tmpResultMap.put("DEPT_NAME", "登记三科");
		resultList.add(tmpResultMap);
		//5、龙岗登记科
		
		//6、宝安登记科
		
		//7、布吉登记科
		
		//8、龙华登记科
		tmpResultMap = getcalculateTargetMap(this.REG_LONGHUA,deptbusinesslist,depttargetlist,monthworkday);
		tmpResultMap.put("DEPT_NAME", "龙华登记科");
		resultList.add(tmpResultMap);
		//9、坪山登记科
		tmpResultMap = getcalculateTargetMap(this.REG_PINGSHAN,deptbusinesslist,depttargetlist,monthworkday);
		tmpResultMap.put("DEPT_NAME", "坪山登记科");
		resultList.add(tmpResultMap);
		//10光明登记科
		tmpResultMap = getcalculateTargetMap(this.REG_GUANGMIN,deptbusinesslist,depttargetlist,monthworkday);
		tmpResultMap.put("DEPT_NAME", "光明登记科");
		resultList.add(tmpResultMap);
		
		return resultList;
	}
	
	/**
	 * 
	 * getcalculateTargetMap:(筛选每个部门的相关数据).
	 *
	 * @author Joyon
	 * @param dept_id
	 * @param deptbusinesslist
	 * @param depttargetlist
	 * @param workday
	 * @return
	 * @since JDK 1.6
	 */
	private Map<String,Object> getcalculateTargetMap(String dept_id,List<Map<String,Object>> deptbusinesslist,List<Map<String,Object>> depttargetlist,int workday) throws BusinessException{
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("WORKDAY", workday);
		int per_count = 0;				//部门月收文量*0.03
		int dept_target_count=0;		//部门月抽检量
		Map<String,Object> tmpMap = null;
		//筛选部门当月办文 量
		for(int i=0;i<deptbusinesslist.size();i++){
			tmpMap = deptbusinesslist.get(i);
			if(tmpMap.get("STARTER_DEPT_ID")!=null){
				if(tmpMap.get("STARTER_DEPT_ID").toString().equals(dept_id)){
					per_count = Float.valueOf(tmpMap.get("PER_COUNT").toString()).intValue();
					resultMap.putAll(tmpMap);
					resultMap.put("PER_COUNT", per_count);
					resultMap.put("PER_COUNT_WORKDAY", per_count/workday);
					
				}
			}
		}
		//筛选部门抽检量统计
		for(int i=0;i<depttargetlist.size();i++){
			tmpMap = depttargetlist.get(i);
			if(tmpMap.get("STARTER_DEPT_ID")!=null){
				if(tmpMap.get("STARTER_DEPT_ID").toString().equals(dept_id)){
					dept_target_count = Float.valueOf(tmpMap.get("DEPT_TARGET_COUNT").toString()).intValue();
					resultMap.putAll(tmpMap);
					resultMap.put("DEPT_TARGET_COUNT_WORKDAY", dept_target_count/workday);
					resultMap.put("DEPT_TARGET_COUNT", calculateTargeBuRule(dept_target_count));
					
				}
			}
		}
		if(resultMap.get("DEPT_COUNT")==null){
			resultMap.put("DEPT_COUNT",0);
		}
		if(resultMap.get("PER_COUNT_WORKDAY")==null){
			resultMap.put("PER_COUNT_WORKDAY",0);
		}
		if(resultMap.get("PER_COUNT")==null){
			resultMap.put("PER_COUNT",0);
		}
		if(resultMap.get("DEPT_TARGET_COUNT_WORKDAY")==null){
			resultMap.put("DEPT_TARGET_COUNT_WORKDAY",0);
		}
		if(resultMap.get("DEPT_TARGET_COUNT")==null){
			resultMap.put("DEPT_TARGET_COUNT",0);
		}
		
		
		return resultMap;
	}
	
	/**
	 * 
	 * 目标计算规则
	 *
	 * @author Joyon
	 * @param target
	 * @return
	 * @since JDK 1.6
	 */
	private int calculateTargeBuRule(int target){
		target = target*1000;
		return target*3/100;
	}
	
	/**
	 * 
	 * getTargetInfo:(获得本月目标 包括目标完成量).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getTargetInfo(){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("month_target", getMonthtarget(null));
		resultMap.put("inspectioned", getInspectioned());
		
		return resultMap;
	}
	
	/**
	 * 
	 * getMonthtarget:(获取月目标量).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public int getMonthtarget(String dateStr){
		int monthtarget = 0;
		//默认为上一月的收文量
		String whereSql = "and to_char(pi.create_date, 'YYYY-MM') = to_char(add_months(SYSDATE, -1), 'YYYY-MM')";
		if(dateStr!=null && !dateStr.equals("") && !dateStr.equals("total")){
			//dateStr = dateStr.substring(0, 8);
			//格式化为上一个月的年月字符串
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = sdf.parse(dateStr);
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.add(Calendar.MONTH, -1);
				date = c.getTime();
				sdf.applyPattern("yyyy-MM");
				dateStr = sdf.format(date);
			} catch (ParseException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			whereSql = "and to_char(pi.create_date, 'YYYY-MM') = '"+dateStr+"'";
		}
		Map<String,Object> monthtargetMap =  businessMainDao.queryMapByKey("Qualityinspection.getMonthtarget",whereSql, null);
		if(monthtargetMap!=null && !monthtargetMap.isEmpty()){
			monthtarget = Integer.valueOf(monthtargetMap.get("MONTH_TARGET").toString())*100;
			monthtarget = monthtarget*3/100;												//月目标为上月总抽文量的0.03
		}
		return monthtarget;
	}
	
	/**
	 * 
	 * getInspectioned:(获取本月已经抽检的文的数量).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public int getInspectioned(){
		Map<String,Object> countMap = checkBaseDao.queryMapByKey("Qualityinspection.getInspectioned", null);
		if(countMap.isEmpty() || countMap==null)
			return 0;
		String count  = countMap.get("inspectioned").toString();
		return Integer.valueOf(count);
	}
	
	/**
	 * 
	 * getCurMonthBusiness:(获取当前月份所有办理过的的业务).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getCurMonthBusiness(){
		
		//1、业务主表中获取所有 的业务
		List<Map<String, Object>> resultMapList = new ArrayList<Map<String,Object>>();
		List<BusinessMain> businessMainList = businessMainDao.getAll();
		
		
		
		//2、通过业务主表中流程实例ID  循环调接口获取流程表中数据    
		Long tmp_proc_id = null;
		ProcessInst tmpProcessInst = null;
		Map<String,Object> tmpResultMap = null;		//返回的Map   用来循环添加到 resultMapList
		Date curDate = new Date();					//当前日期   用来判断  当前流程是否属于这个月 
		int curMonth = curDate.getMonth();			//当前月份   用来判断  当前流程是否属于这个月 	
		int curYear = curDate.getYear();			//当前年份   用来判断  当前流程是否属于这个月 
		for(BusinessMain tmpBusinessMain :businessMainList){
			 tmp_proc_id = Long.valueOf(tmpBusinessMain.getProc_id());
			 
			 try {
				 tmpProcessInst = workflowClientFacade.getWorkflowClient().getProcessInstanceManager().getProcessInstById(tmp_proc_id);
		//4、按启动时间筛选出当月的流程		
				 if(tmpProcessInst != null){
					 if(!(tmpProcessInst.getStartDate().getMonth() == curMonth && tmpProcessInst.getStartDate().getYear() ==curYear)){
						 continue;
					 }else{
						 tmpResultMap = new HashMap<String,Object>();
						 //businessMainDao.copyProperties(tmpResultMap, tmpProcessInst);
						 try {
							 ConvertTool.object2MapWithoutNull(tmpProcessInst, tmpResultMap);
						} catch (IllegalArgumentException e) {
							
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						} catch (IllegalAccessException e) {
							
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
						 tmpResultMap.put("reg_code", tmpBusinessMain.getReg_code());
						 tmpResultMap.put("bus_id", tmpBusinessMain.getBus_id());
		//6、加上是否己检查
						 if(curBusinessisChecked(tmpBusinessMain.getBus_id())){
							 tmpResultMap.put("inspectioned", "是");
						 } else{
							 tmpResultMap.put("inspectioned", "否");
						 }
		//7、获取当前环节
						//获取当前流程实例下活动的
							List aiList = workflowClientFacade.getWorkflowClient().getActivityManager().queryActivityInstsByState(tmpProcessInst.getProcId(),"1");
							if(aiList.size()>0){
								Object tmpObject =aiList.get(0);
								ActivityInst ai = (ActivityInst)tmpObject;
								tmpResultMap.put("activeName", ai.getName());
							}else{
							}	 
						 //加入返回结果
						 resultMapList.add(tmpResultMap);
					 }
					 
				 }
			} catch (ProcessException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		
		return resultMapList;
	}
	
	/**
	 * 
	 * getUncheckSample:(获取未检查的样本).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getUncheckSample(Map paraMap){
		//筛选
		List<Map<String, Object>> resultMapList =  new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> relMapList = null;
		
		String whereSql = " and c.che_date is null";
		
		//代表是复检查询
		if(paraMap!=null){
				
			
			if(paraMap.get("type")!=null){
				whereSql = " and c.che_date is not null";
			}
			//筛选条件部门
			if(paraMap.get("dept")!=null){
				String dept = paraMap.get("dept").toString().trim();
				if(!dept.equals("")){
					if(whereSql.contains("where")){
						whereSql+=" and DEPT_ID=:dept";
					}else{
						whereSql=" where DEPT_ID=:dept";
					}
				}
			}
			
			//筛选条件用户
			if(paraMap.get("person_id")!=null){
				String person_id = paraMap.get("person_id").toString().trim();
				if(!person_id.equals("")){
					if(whereSql.contains("where")){
						whereSql+=" and CHE_PERSON=:person_id";
					}else{
						whereSql=" where CHE_PERSON=:person_id";
					}
				}
			}
			//筛选条件  业务描述  模糊查询
			if(paraMap.get("ch_start_date")!=null){
				String ch_start_date = paraMap.get("ch_start_date").toString().trim();
				String ch_end_date = paraMap.get("ch_end_date").toString().trim();
				if(!ch_start_date.equals("") && !ch_end_date.equals("")){
					if(whereSql.contains("where")){
						whereSql+=" and che_date  between to_date('"+ch_start_date+"','yyyy-mm-dd ')  and to_date('"+ch_end_date+"','yyyy-mm-dd')+1";
								
					}else{
						whereSql=" where che_date  between to_date('"+ch_start_date+"','yyyy-mm-dd ')  and to_date('"+ch_end_date+"','yyyy-mm-dd')+1";
					}
				}else if(!ch_start_date.equals("")){
					if(whereSql.contains("where")){
						whereSql+=" and che_date >= to_date('"+ch_start_date+"','yyyy-mm-dd ')";
					}else{
						whereSql=" where che_date >= to_date('"+ch_start_date+"','yyyy-mm-dd ')";
					}
				}else if(!ch_end_date.equals("")){
					if(whereSql.contains("where")){
						whereSql+=" and che_date <= to_date('"+ch_end_date+"','yyyy-mm-dd ')";
					}else{
						whereSql=" where che_date <= to_date('"+ch_end_date+"','yyyy-mm-dd ')";
					}
				}
			}
		
		}
		 try {
	//1、从抽检基本信息中取出所有che_date为空的数据数据  与业务主表关联
			 relMapList = businessMainDao.queryMapListByKey("Qualityinspection.getUncheckSample",whereSql,paraMap);
			 if(relMapList.size()>0){
	//2、根据取出数据的流程实例ID获取流程数据 
				 Long tmpProcid = null;
				 ProcessInst tmpProcessInst = null;
				 Map<String,Object> tmpResultMap = null;
				 for(Map<String,Object> tmpRelMap:relMapList){
					 tmpProcid = Long.valueOf(tmpRelMap.get("PROC_ID").toString());
//					 try {
						tmpProcessInst = workflowClientFacade
								.getWorkflowClient()
								.getProcessInstanceManager()
								.getProcessInstById(tmpProcid);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
					if(tmpProcessInst != null){
						 tmpResultMap = new HashMap<String,Object>();
						 ConvertTool.object2MapWithoutNull(tmpProcessInst, tmpResultMap);
						 tmpResultMap.putAll(tmpRelMap);
	//3、获取当前环节
						 //获取当前流程实例下活动的
						 List aiList = workflowClientFacade.getWorkflowClient().getActivityManager().queryActivityInstsByState(tmpProcessInst.getProcId(),"1");
						 if(aiList.size()>0){
							Object tmpObject =aiList.get(0);
							ActivityInst ai = (ActivityInst)tmpObject;
							//过滤掉受理环节业务
							if(ai.getName().endsWith("受理"))
								continue;
							tmpResultMap.put("activeName", ai.getName());
						 }else{
						 }	
						 
						 resultMapList.add(tmpResultMap);
					 }
				 }
			 }
			 
		 //businessMainDao.queryMapPageBykeyForOracle(key, paramsObject, pageNo, pageSize)
		} catch (Exception e) {
			LogUtil.error("Qualityinspection.getUncheckSample :获取未检查的样本数据出错:"+e.getMessage());
		}
		return resultMapList;
	}
	
	/**
	 * 
	 * saveCheckbase:(保存抽检样本信息).
	 *
	 * @author Joyon
	 * @since JDK 1.6
	 */
	public void saveChecksample(List<Map<String,Object>> paraList) throws BusinessException{
		String batchNo = getCheckbatchNo();
		String bus_id = null;
		String reg_code = null;
		String checkType = null;
		Object tmpChedate = null;
		CheckBase checkBase = null;
		if(paraList.size()>0){
			for(Map<String,Object> tmpMap : paraList){
				checkBase = new CheckBase();
				 bus_id = tmpMap.get("bus_id").toString();
				 reg_code = tmpMap.get("reg_code").toString();
				//判断 如果已经检查过  则代表是复检02  否则为01抽检     判断map 中有检查时间  则为已经检查    无检查则未检查
				 tmpChedate = tmpMap.get("inspectioned");
				 checkType = tmpChedate.toString().equals("否")?"01":"02";
				 
				 checkBase.setChe_id(checkBaseDao.getSeqId());
				 checkBase.setBus_id(bus_id);
				 checkBase.setChe_type(checkType);
				 checkBase.setReg_code(reg_code);
				 checkBase.setBatch_no(batchNo);
				 checkBaseDao.save(checkBase);
			}
		}
	}
	
	/**
	 * 
	 * startInspection:(启动检查流程   ).
	 *
	 * @author Joyon
	 * @param userInfo
	 * @param list
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> startInspection(UserInfo userInfo,List<Map<String,Object>> list){
		Map<String,Object> result = new HashMap<String,Object>();
		try
		{
			//1.查询登记编号
			
			Row row = new RowImpl();
			row.put("name", "登记编号");
			String regcode = FacadeFactory.getIdentifierFacade().getSerialNumber(row);
			//Long lastProcid = null;
			String procdefId = "1290";					//质量督查流程定义ID 
			String procName="";
			Map<String,Object> dataMap = null;
			if(list.size()>0)
			{
				dataMap = list.get(0);
				procName="办文抽检("+dataMap.get("procName").toString()+")";
				//lastProcid = Long.valueOf(dataMap.get("PROC_ID").toString());
				//procdefId=dataMap.get("PROCDEF_ID").toString();
			}
			//2.启动
			WorkItem wi = FacadeFactory.getWorkflowFacade().createAndStartProcessInstance(userInfo, procdefId, procName);
			//4.插入业务主表以及业务与登记单元关联表
			//String procdefid= wi.getProcdefId().toString();
			ResultMsg msg=new ResultMsg();
			
			//5、把被检查业务的流程设置为挂起状态
			//暂时未设置
			//获取当前流程实例下活动的
//			List aiList = workflowClientFacade.getWorkflowClient().getActivityManager().queryActivityInstsByState(lastProcid,"1");
//			if(aiList.size()>0){
//				Object tmpObject =aiList.get(0);
//				ActivityInst ai = (ActivityInst)tmpObject;
//				workflowClientFacade.getWorkflowClient().getActivityManager().suspendActivityInst(ai.getActivId());
//			}
			msg= this.insertRegInfo(regcode,list,wi,procdefId,userInfo);	
			
			
			result.put("regcode", regcode);
			result.put("workItem", wi);
		}catch(Exception err)
		{
			err.getMessage();
		}
		return result;
	}
	
	/**
	 * 
	 * 插入检查基本信息信息和所有权登记信息.
	 * @see com.szhome.cq.business.IAuditFacade#insertReg_Info(java.util.Map)
	 */
	@Transactional
	public ResultMsg insertRegInfo(String regcode,List<Map<String,Object>> list,WorkItem wi,String procdefid,UserInfo userInfo){
		ResultMsg msg = new ResultMsg();
		Reg_relationship r=new Reg_relationship();
		BusinessMain b=new BusinessMain();	
		CheckBase checkBase = new CheckBase();
		String lastbusid=list.get(0).get("BUS_ID").toString();             //上一个业务ID为抽检被流程的ID
		Map<String,Object> paraMap = new HashMap<String,Object>();
		Map webDatamap = list.get(0);
		//前台复检页面传过来的参数
		if(webDatamap.get("type")!=null){
			paraMap.put("CHE_ID",checkBaseDao.getSeqId());
		}else{
			paraMap.put("CHE_ID", list.get(0).get("CHE_ID").toString());								   //用来更新当前检查流程的检查时间
		}
		try {
			b.setLast_bus_id(lastbusid);
			b.setProc_id(wi.getProcId().toString());
			b.setProc_name(wi.getProcName());
			b.setPro_def_id(procdefid);
			//b.setLast_bus_id(lastbusid);
			b.setReg_type(procdefid);
			b.setReg_station(WbfConstants.FUTIAN);
			if(procdefid.equals(WbfConstants.UNATTACH)||procdefid.equals(WbfConstants.UNMORTRE))
			{
				//r.setLast_reg_code(list.get(0).get("lastregcode").toString());
			}
			b.setReg_code(regcode);
			b.setBus_id(businessMainDao.getSeqId());
			
			//插入业务主表
			businessMainDao.save(b);
			
			//更新检查基本信息表  检查时间  以及一些业务数据
			checkBase = checkBaseDao.get("where CHE_ID=:CHE_ID",paraMap);
			if(checkBase!=null){
				checkBase.setChe_date(new Date());
				BusinessMain busMain = FacadeFactory.getCommonFacade().getBusinessMainByBusid(checkBase.getBus_id());
				
				checkBaseDao.copyProperties(checkBase, busMain);
				checkBase.setReg_type_name(getBustypenameByBustyeid(busMain.getReg_type()));
				checkBase.setProcdef_id(busMain.getPro_def_id());
				checkBase.setChe_person(userInfo.getUserID());
				checkBase.setChe_person_name(userInfo.getUserName());
				checkBase.setDept_id(userInfo.getOrganID());
				checkBase.setDept_name(userInfo.getOrganName());
				checkBase.setCheck_proc_id(wi.getProcId().toString());		//用这一次启动的流程实例ID  和检查业务的业务ID来定位 惟一一条检查记录
				//默认为正常文
				checkBase.setIs_error("0");
				checkBaseDao.update(checkBase);
			}else{
				checkBase = new CheckBase();
				checkBase.setChe_id(paraMap.get("CHE_ID").toString());
				
				checkBase.setChe_date(new Date());
				BusinessMain busMain = FacadeFactory.getCommonFacade().getBusinessMainByBusid(lastbusid);
				checkBaseDao.copyProperties(checkBase, busMain);
				checkBase.setReg_type_name(getBustypenameByBustyeid(busMain.getReg_type()));
				checkBase.setProcdef_id(busMain.getPro_def_id());
				checkBase.setChe_person(userInfo.getUserID());
				checkBase.setChe_person_name(userInfo.getUserName());
				checkBase.setDept_id(userInfo.getOrganID());
				checkBase.setDept_name(userInfo.getOrganName());
				checkBase.setCheck_proc_id(wi.getProcId().toString());		//用这一次启动的流程实例ID  和检查业务的业务ID来定位 惟一一条检查记录
				//默认为正常文
				checkBase.setIs_error("0");
				checkBase.setChe_type("02");
				
				//获取被检查业务  前一次检查id
				CheckBase lastCheckBase = getLastCheckBase(busMain.getBus_id());
				if(lastCheckBase!=null){
					checkBase.setPre_che_id(lastCheckBase.getChe_id());
				}else{
					LogUtil.error("QualityinspectionFacade.insertRegInfo 获取前一次检查业务ID出错  前一次检查业务为空！");
				}
				
				checkBaseDao.save(checkBase);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			msg.setReturnCode(ResultMsg.CODE_FAIL);
			msg.setReturnMsg(e.getMessage());
			throw new BusinessException("插入登记单元关联表信息和所有权登记信息出错:"+e.getMessage());
//			return msg;
		}
		msg.setReturnCode(ResultMsg.CODE_SUCCESS);
		return msg;
	}
	
	/**
	 * 获取业务上一次检查信息
	 * @param bus_id
	 * @return
	 */
	private CheckBase getLastCheckBase(String bus_id) {
			Map<String,Object> paraMap = new HashMap<String,Object>();
			paraMap.put("bus_id", bus_id);
			List<CheckBase> listCheckBase = checkBaseDao.getAll("where bus_id=:bus_id and che_date is not null order by che_date desc", paraMap);
			if(listCheckBase.size()>0){
				return listCheckBase.get(0);
			}
		
		return null;
		
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
	public Map<String,Object> getCheckcedbusdataByProcid(String proc_id,String state){
		if(!Util.notNullEmpty(state)){
			state = "9";
		}
		String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getLast_bus_id();
		String checked_proc_id = FacadeFactory.getCommonFacade().getBusinessMainByBusid(bus_id).getProc_id();
		
		//workflowClientFacade.getWorkflowClient().getProcessInstanceManager().getProcessInstById(paramLong)
		Map<String, Object> resultMap = new HashMap();
		
		try {
			//aiList = workflowClientFacade.getWorkflowClient().getActivityManager().getActivatedActivityInsts(Long.valueOf(check_proc_id));
			Row paraRow = new RowImpl();
			//paraRow.put("state", "9");						//工作项状态为1  启动状态
			paraRow.put("state", state);						//工作项状态为1  启动状态
			paraRow.put("proc_id", checked_proc_id);
			Object[] processTaskObj= workflowClientFacade.getWorkflowClient().getProcessTaskMgr().queryTask4PaginInGeneral(paraRow);
			//workflowClientFacade.getWorkflowClient().getProcessTaskMgr().getProcessTask(paramLong1, paramLong2)
			//返回已经办理过的工作项
			Row participantrow = null;
			if(processTaskObj.length>0){
				 List list = (ArrayList)processTaskObj[0];
				 ProcessTask processTask = null;
				 WorkItem wi = null;
				 //把历史工作项的执行人返回
				 for(int i=0;i<list.size();i++){
					 processTask = (ProcessTask)list.get(i);
					 if(processTask!=null){
						 try {
							ConvertTool.object2MapWithoutNull(processTask, resultMap);
							resultMap.put("procId", processTask.getProcId());
							resultMap.put("wiId", processTask.getWiId());
							resultMap.put("procdefId", processTask.getProcdefId());
							resultMap.put("activdefId", processTask.getActivdefId());
						} catch (IllegalArgumentException e) {
							
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						} catch (IllegalAccessException e) {
							
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
						 break;
					 }else{
						 continue;
					 }
					 
				 }
			}
		} catch (NumberFormatException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (ProcessException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
		return resultMap;
	}
	
	/**
	 * 
	 * getHistoryProcActivity:(获取当前流程 历史办过的流程节点).
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List getHistoryprocactivityByProcid(String proc_id){
		List resultList = null;
		try {
			resultList = workflowClientFacade.getWorkflowClient().getActivityManager().queryActivityInstsByState(Long.valueOf(proc_id),"9");
			//去掉开始环节
			ActivityInst ai = null;
			for(int i=0;i<resultList.size();i++){
				ai = (ActivityInst) resultList.get(i);
				if(ai.getType().equals("start")){
					resultList.remove(i);
				}
				
			}
			
			//workflowClientFacade.getWorkflowClient().getWorkItemManager().que
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			
		} catch (ProcessException e) {
			
			e.printStackTrace();
			
		}
		return resultList;
	}
	
	/**
	 * 
	 * getCheckBaseListByBusId:(通过业务ID  获取抽检基本信息). 
	 *
	 * @author Joyon
	 * @param bus_id
	 * @return
	 * @since JDK 1.6
	 */
	public List<CheckBase> getCheckBaseListByBusId(String bus_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id", bus_id);
		List<CheckBase> resultList = checkBaseDao.getAll("where bus_id=:bus_id",paraMap);
		return resultList;
		
	}
	
	/**
	 * 
	 * curBusinessisChecked:(判断当前业务是否已经抽检  true 是已经检查  false/未检查).
	 *
	 * @author Joyon
	 * @param bus_id
	 * @return
	 * @since JDK 1.6
	 */
	public boolean curBusinessisChecked(String bus_id){
		boolean result = true;
		List<CheckBase> resultList = getCheckBaseListByBusId(bus_id);
		if(resultList.size()==0){
			return false;
		//有多个  表示已经检查过  返回已经检查过 
		}else if(resultList.size()>1){
			return true;
		}
		for(CheckBase tmpCheckbase :resultList){
			if(tmpCheckbase.getChe_date()==null){
				return false;
			}
		}
		return result;
	}
	
	/**
	 * 
	 * getChkQualitytargetList:(获取质量跟踪指标信息List).
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<CheckQualitytarget> getChkQualitytargetList() throws BusinessException{
		List<CheckQualitytarget> resultList = new ArrayList<CheckQualitytarget>();
		List<CheckQualitytarget> tmpList = null;
		try {
			tmpList = chkQualitytargetDao.getAll();
		} catch (Exception e) {
			LogUtil.info("获取质量跟踪指标为空");
		}
		if(tmpList != null){
			if(tmpList.size()>0 && !tmpList.isEmpty()){
				resultList = tmpList;
			}
		}
		return resultList;
	}
	
	
	/**
	 * 
	 * applyChkQualitytargetEdit:(将质量跟踪指标编辑后的数据应用到后台数据库).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map applyChkQualitytargetEdit(Map paraMap) {

		
		Map result = new HashMap();
		
		String datas = paraMap.get("datas").toString();

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
		CheckQualitytarget chk = null; 
		for (int i = 0; i < news.size(); i ++) {
			temp = new RowImpl((Map)news.get(i));
			chk = new CheckQualitytarget();
			try {
				chk.setQua_tar_id(chkQualitytargetDao.getSeqId());
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("获取字典项seq id出错"+e.getMessage());
			}
			
			chk.setQua_index(temp.getString("qua_index"));
			chk.setB_deleteflag("0");
			try {
				//logger.info("insert:" + temp);
				chkQualitytargetDao.save(chk);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("保存质量跟踪指标出错"+e.getMessage());
			}
		}
		
		//处理修改数据
		List upds = (List)dataMap.get("updated");
		for (int i = 0; i < upds.size(); i ++) {
			temp = new RowImpl((Map)upds.get(i));
			chk = new CheckQualitytarget();
			try {
				chk.setQua_tar_id(temp.getString("qua_tar_id"));
				chk.setQua_index(temp.getString("qua_index"));
				chk.setB_deleteflag("0");
				chkQualitytargetDao.update(chk);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("保存质量跟踪指标出错"+e.getMessage());
			}
			
			//di.set
			//logger.info("update:" + temp);
			//getPlanSupportDao().update(temp, "plat_dict_item");
		}
		
		result.put("success",true);
		return result;
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
	public Map deleteChkQualitytarget(CheckQualitytarget chk) throws BusinessException{
		Map result = new HashMap();
		chkQualitytargetDao.delete(chk);
		result.put("success", true);
		return result;
	}
	
	/**
	 * 
	 * getCheckbatchNo:(获取批次号  每天序列从1开始  暂时未完成  ). 
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getCheckbatchNo(){
		String code = "";
		try {
			Row row = new RowImpl();
			row.put("name", "抽检批次号");
			 code  = FacadeFactory.getIdentifierFacade().getSerialNumber(row);
		} catch (Exception e) {
			LogUtil.error("获取抽检批次号出错："+e.getMessage());
		}
		return code;
	}
	/**
	 * 
	 * relQualitytarget:(关联质量指标项到质量跟踪记录表). 
	 *@param qua_tar_id 质量跟踪指标项id  type:checked/unchecked  关联/取消关联  bus_id:关联业务Id
	 * @author Joyon
	 * @since JDK 1.6
	 */
	public void relQualitytarget(String qua_tar_id,String type,String bus_id,String activdefId,String activdefName) throws BusinessException{
		if(type.equals("checked")){
			
			CheckQualityrec cqr = new CheckQualityrec();
			cqr.setQua_rec_id(checkQualityrecDao.getSeqId());
			cqr.setBus_id(bus_id);
			cqr.setQua_no(qua_tar_id);
			cqr.setEvaluate_time(new Date());
			cqr.setActivdef_id(activdefId);
			cqr.setProc_node_name(activdefName);
			checkQualityrecDao.save(cqr);
			
		}else if(type.equals("unchecked")){
			Map<String,Object> paraMap = new HashMap<String,Object>();
			paraMap.put("qua_tar_id", qua_tar_id);
			paraMap.put("bus_id", bus_id);
			paraMap.put("activdefId", activdefId);
			CheckQualityrec cqr = checkQualityrecDao.get(" where QUA_NO=:qua_tar_id and bus_id=:bus_id and ACTIVDEF_ID=:activdefId", paraMap);
			checkQualityrecDao.delete(cqr);
		}
	}
	
	/**
	 * 
	 * getCheckQualityrecListByBusid:(通过业务ID获取质量跟踪记录表信息). 
	 *
	 * @author Joyon
	 * @param bus_id
	 * @return
	 * @since JDK 1.6
	 */
	public List<CheckQualityrec> getCheckQualityrecListByBusid(String bus_id,String activdefId){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id", bus_id);
		paraMap.put("activdefId", activdefId);
		List<CheckQualityrec> resultList= null;
		try {
			resultList = checkQualityrecDao.getAll(" where bus_id=:bus_id and ACTIVDEF_ID=:activdefId", paraMap);
		} catch (Exception e) {
			LogUtil.error("QualityinspectionFacade.getCheckQualityrecListByBusid--获取已经关联的指标项出错:"+e.getMessage());
		}
		return resultList;
	}
	/**
	 * 
	 * getCheckbusinessuseridByCheckprocid:(通过质量抽检流程实例ID获取 被检查业务的活动的业务id ).
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List getCheckbusinessuseridByCheckprocid(String proc_id){
		//1、获取被检查流程的流程实例ID
		String check_bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getLast_bus_id();
		String check_proc_id = FacadeFactory.getCommonFacade().getBusinessMainByBusid(check_bus_id).getProc_id();
		//2、获取被检查流程活动的活动实例列表
		List  aiList = null;
		ActivityInst activityInsts = null;
		try {
			//aiList = workflowClientFacade.getWorkflowClient().getActivityManager().getActivatedActivityInsts(Long.valueOf(check_proc_id));
			Row paraRow = new RowImpl();
			paraRow.put("state", "9");						//工作项状态为1  启动状态
			paraRow.put("proc_id", check_proc_id);
			Object[] processTaskObj= workflowClientFacade.getWorkflowClient().getProcessTaskMgr().queryTask4PaginInGeneral(paraRow);
			//workflowClientFacade.getWorkflowClient().getProcessTaskMgr().getProcessTask(paramLong1, paramLong2)
			//返回已经办理过的工作项
			List resultRowList = new ArrayList();
			Row participantrow = null;
			if(processTaskObj.length>0){
				 List list = (ArrayList)processTaskObj[0];
				 ProcessTask processTask = null;
				 WorkItem wi = null;
				 //把历史工作项的执行人返回
				 for(int i=0;i<list.size();i++){
					 processTask = (ProcessTask)list.get(i);
					 wi = workflowClientFacade.getWorkflowClient().getWorkItemManager().getWorkItemById(processTask.getWiId());
					 if(wi==null)
						 continue;
					 participantrow=new RowImpl();
					 participantrow.put("partiId", wi.getExecUserId());
					 participantrow.put("partiName",wi.getExecUserName()+"  "+wi.getName());
					 participantrow.put("partiType", "user");
					 participantrow.put("remark", wi.getName());
					 resultRowList.add(participantrow);
				 }
				 //ProcessTask processTask = (ProcessTask)list.get(0);
				 //return processTask.getPartiId();
				 return resultRowList;
			}
//			if(aiList.size()>0){
//				activityInsts = (ActivityInst)aiList.get(0);
//				return activityInsts.getCurrentUserId();
//			}
		} catch (NumberFormatException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (ProcessException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
			
		return null;
		
	}
	/**
	 * 
	 * querySamele:(通过查询条件查询).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @throws ParseException 
	 * @since JDK 1.6
	 */
	public List querySamele(Map paraMap) throws ParseException{
		long start_time = System.currentTimeMillis();
		//筛选条件登记编号
		
//		List testList = businessMainDao.queryListByKey("Qualityinspection.getdeptuser", null);
//		System.out.print("bbb");
//		if(true){
//			
//			return null;
//		}
		
		String businessWheresql = "where reg_type!="+WbfConstants.QUALITY_INSPECTION;
		if(paraMap.get("reg_code")!=null){
			String reg_code = paraMap.get("reg_code").toString().trim();
			if(!reg_code.equals("")){
				businessWheresql="where reg_code=:reg_code";
			}
			
		}
		//筛选条件登记类型
		if(paraMap.get("reg_type")!=null){
			String reg_type = paraMap.get("reg_type").toString().trim();
			if(!reg_type.equals("")){
				if(businessWheresql.contains("where")){
					businessWheresql+=" and reg_type=:reg_type";
				}else{
					businessWheresql=" where reg_type=:reg_type";
				}
			}
		}
		//筛选条件房地产所属区
		if(paraMap.get("location_reg_unit")!=null){
			String location_reg_unit = paraMap.get("location_reg_unit").toString().trim();
			if(!location_reg_unit.equals("")){
				if(businessWheresql.contains("where")){
					businessWheresql+=" and location_reg_unit=:location_reg_unit";
				}else{
					businessWheresql=" where location_reg_unit=:location_reg_unit";
				}
			}
		}
		//筛选条件  业务描述  模糊查询
		if(paraMap.get("bus_detail")!=null){
			String bus_detail = paraMap.get("bus_detail").toString().trim();
			if(!bus_detail.equals("")){
				if(businessWheresql.contains("where")){
					businessWheresql+=" and proc_name like '%'||:bus_detail||'%'";
				}else{
					businessWheresql=" where proc_name like '%'||:bus_detail||'%'";
				}
			}
		}
		
		//筛选是否己抽检
		String inspection_state = "";
		if(paraMap.get("inspection_state")!=null){
			inspection_state = paraMap.get("inspection_state").toString().trim();
		}
		//1、业务主表中获取所有 的业务
				List<Map<String, Object>> resultMapList = new ArrayList<Map<String,Object>>();
				List<BusinessMain> businessMainList = businessMainDao.getAll(businessWheresql,paraMap);
				
				
				
				//2、通过业务主表中流程实例ID  循环调接口获取流程表中数据    
				Long tmp_proc_id = null;
				ProcessInst tmpProcessInst = null;
				Map<String,Object> tmpResultMap = null;		//返回的Map   用来循环添加到 resultMapList
				Date curDate = new Date();					//当前日期   用来判断  当前流程是否属于这个月 
				int curMonth = curDate.getMonth();			//当前月份   用来判断  当前流程是否属于这个月 	
				int curYear = curDate.getYear();			//当前年份   用来判断  当前流程是否属于这个月 
				for(BusinessMain tmpBusinessMain :businessMainList){
					tmpResultMap = new HashMap<String,Object>();
				//6、加上是否己检查
					 if(curBusinessisChecked(tmpBusinessMain.getBus_id())){
						 if(inspection_state.equals(this.UNINSPECTIONED)){
							 continue;
						 }
						 tmpResultMap.put("inspectioned", "是");
					 }else{
						 if(inspection_state.equals(this.INSPECTIONED)){
							 continue;
						 }
						 tmpResultMap.put("inspectioned", "否");
					 }
					
					 tmp_proc_id = Long.valueOf(tmpBusinessMain.getProc_id());
					 
					 try {
						 tmpProcessInst = workflowClientFacade.getWorkflowClient().getProcessInstanceManager().getProcessInstById(tmp_proc_id);
				//4、按启动时间筛选出当月的流程		
						 if(tmpProcessInst != null){
							 if(paraMap.get("start_date")!=null || paraMap.get("end_date")!=null){
								 Object starObj = paraMap.get("start_date");
								 Object endObj = paraMap.get("end_date");
								 String start="";
								 String end = "";
								 Date startDate = null;
								 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								 Date endDate = null;
								 if(starObj!=null && endObj!=null){
									 
									 start = starObj.toString();
									 end = endObj.toString();
									 if(!start.equals("") && !end.equals("")){
										 startDate=sdf.parse(start);
										 endDate = sdf.parse(end);
									
										 //如果起始日期和终止日期写反  则换下
										 if(startDate.after(endDate)){
											 Date tmpDate = null;
											 tmpDate = startDate;
											 startDate = endDate;
											 endDate = tmpDate;
										 }
										 if(tmpProcessInst.getStartDate().before(endDate) && tmpProcessInst.getStartDate().after(startDate)){
											 
										 }else{
											 continue;
										 }
									 }else if(!start.equals("")){
										 start = starObj.toString();
										 startDate=sdf.parse(start);
										 if(tmpProcessInst.getStartDate().before(startDate))
											 continue;
									 }else if(!end.equals("")){
										 end = endObj.toString();
										 endDate = sdf.parse(end);
										 if(tmpProcessInst.getStartDate().after(endDate))
											 continue;
									 }else if(!(tmpProcessInst.getStartDate().getMonth() == curMonth && tmpProcessInst.getStartDate().getYear() ==curYear)){
										 continue;
									 }
							 	}
							 }
							 
								 
								 //businessMainDao.copyProperties(tmpResultMap, tmpProcessInst);
								 try {
									 ConvertTool.object2MapWithoutNull(tmpProcessInst, tmpResultMap);
								} catch (IllegalArgumentException e) {
									
									// TODO Auto-generated catch block
									e.printStackTrace();
									
								} catch (IllegalAccessException e) {
									
									// TODO Auto-generated catch block
									e.printStackTrace();
									
								}
								 tmpResultMap.put("reg_code", tmpBusinessMain.getReg_code());
								 tmpResultMap.put("bus_id", tmpBusinessMain.getBus_id());
				
				//7、获取当前环节
								//获取当前流程实例下活动的
									List aiList = workflowClientFacade.getWorkflowClient().getActivityManager().queryActivityInstsByState(tmpProcessInst.getProcId(),"1");
									if(aiList.size()>0){
										Object tmpObject =aiList.get(0);
										ActivityInst ai = (ActivityInst)tmpObject;
										tmpResultMap.put("activeName", ai.getName());
									}else{
									}	 
								 //加入返回结果
								 resultMapList.add(tmpResultMap);
							 
							 
						 }
					 } catch (ProcessException e) {
						
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
				}
				
				long end_time = System.currentTimeMillis();
				System.out.println("---------------------查询用时："+(end_time-start_time)+"ms-----------------");
				return resultMapList;
	}
	
	/**
	 * 
	 * getBustypenameByBustyeid:(通过业务类型id获取业务类型名).
	 *
	 * @author Joyon
	 * @param bus_type_id
	 * @return
	 * @since JDK 1.6
	 */
	public String getBustypenameByBustyeid(String bus_type_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_type_id", bus_type_id);
		Map<String, Object> cfigbustypeMap = null;
		try {
			 cfigbustypeMap = checkBaseDao.queryMapByKey("Qualityinspection.getCfigbustype","where bus_type_id=:bus_type_id", paraMap);
			 if(cfigbustypeMap!=null){
				 return cfigbustypeMap.get("BUS_NAME").toString();
			 }
		} catch (Exception e) {
			LogUtil.error("QualityinspectionFacade.getBustypenameByBustyeid 获取业务类型名出错:"+e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 * getBustypenameByBustyeid:(获取当前业务类型 父类型id).
	 *
	 * @author Joyon
	 * @param bus_type_id
	 * @return
	 * @since JDK 1.6
	 */
	public String getBustypeParentidByBustyeid(String bus_type_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_type_id", bus_type_id);
		Map<String, Object> cfigbustypeMap = null;
		String whereSql=" where bus_type_id=(select parent_id from cfig_bus_type where bus_type_id=:bus_type_id)";
		try {
			 cfigbustypeMap = checkBaseDao.queryMapByKey("Qualityinspection.getCfigbustype",whereSql,paraMap);
			 if(cfigbustypeMap!=null){
				 return cfigbustypeMap.get("BUS_TYPE_ID").toString();
			 }
		} catch (Exception e) {
			LogUtil.error("QualityinspectionFacade.getBustypenameByBustyeid 获取业务类型出错:"+e.getMessage());
		}
		return null;
	}
	/**
	 * 
	 * getCurMonthStatistics:(按部门名登记类型  统计当前月的抽检数据).
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getCurMonthStatistics(String start_date,String end_date,String reg_station){
		List<Map<String,Object>> dbList  = null;
		List<Map<String,Object>> resultList  = new ArrayList<Map<String,Object>>();
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_station", reg_station);
		paraMap.put("start_date", start_date);
		paraMap.put("end_date", end_date);
		String whereSql = "where to_char(CHE_DATE, 'YYYY-MM') = to_char(add_months(SYSDATE, 0), 'YYYY-MM') order by dept_id";
		if(start_date!=null && end_date!=null){
			if(!start_date.equals("") && !end_date.equals("")){
				//如果起始时间大于结束时间则互换时间
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date s_Date = sm.parse(start_date);
					Date e_Date =sm.parse(end_date);
					if(s_Date.after(e_Date)){
						String tmp_str = start_date;
						start_date = end_date;
						end_date = tmp_str;
					}
				} catch (ParseException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				whereSql=" where che_date  between to_date('"+start_date+"','yyyy-mm-dd ')  and to_date('"+end_date+"','yyyy-mm-dd')+1";
			}else if(!start_date.equals("") && end_date.equals("")){
				whereSql=" where che_date  >= to_date('"+start_date+"','yyyy-mm-dd ')";
			}else if(start_date.equals("") && !end_date.equals("")){
				whereSql=" where che_date  <= to_date('"+end_date+"','yyyy-mm-dd ')";
			}
		}
		if(Util.notNullEmpty(reg_station)){
			if(whereSql.contains("where")){
				whereSql +=" and reg_station  = :reg_station";
			}else{
				whereSql+=" where reg_station  = :reg_station";
			}
		}
		try {
			dbList = checkBaseDao.queryMapListByKey("Qualityinspection.getCurMonthStatistics",whereSql, paraMap);
//			if(dbList.size()==0){
//				return null;
//			}
			
			Map tmpResultMap  = null;
			//登记点  业务  拼数据
			//1、收发文 科
			tmpResultMap = getCurMonthStatisticsMap(dbList,this.REG_REC_DIS,start_date);
			tmpResultMap.put("DEPT_NAME", "收发文科");
			resultList.add(tmpResultMap);
			
			//2、登记一科
			tmpResultMap = getCurMonthStatisticsMap(dbList,this.REG_FIRST,start_date);
			tmpResultMap.put("DEPT_NAME", "登记一科");
			resultList.add(tmpResultMap);
			//3、登记二科
			tmpResultMap = getCurMonthStatisticsMap(dbList,this.REG_SECOND,start_date);
			tmpResultMap.put("DEPT_NAME", "登记二科");
			resultList.add(tmpResultMap);
			//4、登记三科
			tmpResultMap = getCurMonthStatisticsMap(dbList,this.REG_THIRD,start_date);
			tmpResultMap.put("DEPT_NAME", "登记三科");
			resultList.add(tmpResultMap);
			//5、龙岗登记科
			
			//6、宝安登记科
			
			//7、布吉登记科
			
			//8、龙华登记科
			tmpResultMap = getCurMonthStatisticsMap(dbList,this.REG_LONGHUA,start_date);
			tmpResultMap.put("DEPT_NAME", "龙华登记科");
			resultList.add(tmpResultMap);
			//9、坪山登记科
			tmpResultMap = getCurMonthStatisticsMap(dbList,this.REG_PINGSHAN,start_date);
			tmpResultMap.put("DEPT_NAME", "坪山登记科");
			resultList.add(tmpResultMap);
			//10光明登记科
			tmpResultMap = getCurMonthStatisticsMap(dbList,this.REG_GUANGMIN,start_date);
			tmpResultMap.put("DEPT_NAME", "光明登记科");
			resultList.add(tmpResultMap);
			//11、合计
			tmpResultMap = getCurMonthStatisticsMap(dbList,this.TOTAL,start_date);
			tmpResultMap.put("DEPT_NAME", "合计");
			resultList.add(tmpResultMap);
		} catch (Exception e) {
			LogUtil.error("统计查询当前月数据出出错"+e.getMessage());
		}
		
		//按月份加上目标
//		int tmp_target_num = 0;
////		for(int i=0;i<resultList.size()-2;i++){
////			Map tmpMap = resultList.get(i);
////			String tmptargetnumstr = tmpMap.get("TARGET_NUM").toString();
////			
////			tmp_target_num+=Integer.valueOf(tmptargetnumstr);
////		}
//		tmp_target_num = getMonthtarget(start_date); 
//		//最后一个是合计  赋值
//		resultList.get(resultList.size()-1).put("TARGET_NUM", tmp_target_num);
		return resultList;
	}
	
	/**
	 * 
	 * getCurMonthStatisticsMap:(获取部门检查数据   从统计数据中筛选出符合格式的数据).
	 *
	 * @author Joyon
	 * @param dbList
	 * @param reg_dept_id
	 * @return
	 * @since JDK 1.6
	 */
	private Map<String,Object> getCurMonthStatisticsMap(List<Map<String,Object>> dbList,String reg_dept_id,String dateStr){
		String tmpRegtype=null;
		Map<String,Object> tmpResultMap  = new HashMap<String,Object>();
		if(dbList==null || dbList.size()==0){
			//1、窗口收文总量 和 抽检量
			//如果没有数据  则添加零
			if(tmpResultMap.get("DEPT_ALL_TOTAL")==null){
				tmpResultMap.put("DEPT_ALL_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("DEPT_ALL_ERROR")==null){
				tmpResultMap.put("DEPT_ALL_ERROR", "&nbsp;");
			}
			
			//2、初始登记 总量 和 抽检量
			//如果没有数据  则添加零
			if(tmpResultMap.get("ONW_INIT_TOTAL")==null){
				tmpResultMap.put("ONW_INIT_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("ONW_INIT_NUM")==null){
				tmpResultMap.put("ONW_INIT_NUM", "&nbsp;");
			}
			
			//3、二级转移总量 和 抽检量
			//如果没有数据  则添加零
			if(tmpResultMap.get("ONW_SEC_TOTAL")==null){
				tmpResultMap.put("ONW_SEC_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("ONW_SEC_NUM")==null){
				tmpResultMap.put("ONW_SEC_NUM", "&nbsp;");
			}
			//4、三级转移总量 和 抽检量
			//如果没有数据  则添加零
			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
				tmpResultMap.put("ONW_THR_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("ONW_THR_NUM")==null){
				tmpResultMap.put("ONW_THR_NUM", "&nbsp;");
			}
			
			
			//5、预售抵押
			int premortgage_ot_total=0;
			int premortgage_ot_num=0;
			if(tmpResultMap.get("PRE_MORTGAGE_TOTAL")==null){
				tmpResultMap.put("PRE_MORTGAGE_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("PRE_MORTGAGE_NUM")==null){
				tmpResultMap.put("PRE_MORTGAGE_NUM", "&nbsp;");
			}
//			tmpResultMap.put("PRE_MORTGAGE_TOTAL",premortgage_ot_total);
//			tmpResultMap.put("PRE_MORTGAGE_NUM",premortgage_ot_num);
			//6、现售房抵押
			int mortgage_ot_total=0;
			int mortgage_ot_num=0;
			if(tmpResultMap.get("MORTGAGE_TOTAL")==null){
				tmpResultMap.put("MORTGAGE_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("MORTGAGE_NUM")==null){
				tmpResultMap.put("MORTGAGE_NUM", "&nbsp;");
			}
//			tmpResultMap.put("MORTGAGE_TOTAL",mortgage_ot_total);
//			tmpResultMap.put("MORTGAGE_NUM",mortgage_ot_num);
			//如果没有数据  则添加零
//			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
//				tmpResultMap.put("ONW_THR_TOTAL", "&nbsp;");
//			}if(tmpResultMap.get("ONW_THR_NUM")==null){
//				tmpResultMap.put("ONW_THR_NUM", "&nbsp;");
//			}
			//7、变更及其它    --涉及多个业务   需要每个都重新
			int change_ot_total=0;
			int change_ot_num=0;
			if(tmpResultMap.get("CHANGE_OT_TOTAL")==null){
				tmpResultMap.put("CHANGE_OT_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("CHANGE_OT_NUM")==null){
				tmpResultMap.put("CHANGE_OT_NUM", "&nbsp;");
			}
//			tmpResultMap.put("CHANGE_OT_TOTAL",change_ot_total);
//			tmpResultMap.put("CHANGE_OT_NUM",change_ot_num);
//			//如果没有数据  则添加零
//			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
//				tmpResultMap.put("ONW_THR_TOTAL", "&nbsp;");
//			}if(tmpResultMap.get("ONW_THR_NUM")==null){
//				tmpResultMap.put("ONW_THR_NUM", "&nbsp;");
//			}
			//8、安居房换证
			
			//如果没有数据  则添加零
			if(tmpResultMap.get("ANJU_TOTAL")==null){
				tmpResultMap.put("ANJU_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("ANJU_TNUM")==null){
				tmpResultMap.put("ANJU_TNUM", "&nbsp;");
			}
			//9、预售合同备案
			//如果没有数据  则添加零
			if(tmpResultMap.get("PRE_SALE_TOTAL")==null){
				tmpResultMap.put("PRE_SALE_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("PRE_SALE_NUM")==null){
				tmpResultMap.put("PRE_SALE_NUM", "&nbsp;");
			}
			
			//10、产权综合办文
			int est_com_total=0;
			int est_com_num=0;
			if(tmpResultMap.get("EST_COM_TOTAL")==null){
				tmpResultMap.put("EST_COM_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("EST_COM_NUM")==null){
				tmpResultMap.put("EST_COM_NUM", "&nbsp;");
			}
//			tmpResultMap.put("EST_COM_TOTAL",est_com_total);
//			tmpResultMap.put("EST_COM_NUM",est_com_num);
			//11、两规登记
			int two_reg_total=0;
			int two_reg_num=0;
			if(tmpResultMap.get("TWO_REG_TOTAL")==null){
				tmpResultMap.put("TWO_REG_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("TWO_REG_NUM")==null){
				tmpResultMap.put("TWO_REG_NUM", "&nbsp;");
			}
//			tmpResultMap.put("TWO_REG_TOTAL",two_reg_total);
//			tmpResultMap.put("TWO_REG_NUM",two_reg_num);
			//12、其它登记
			int oter_total=0;
			int other_num=0;
			if(tmpResultMap.get("OTER_TOTAL")==null){
				tmpResultMap.put("OTER_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("OTHER_NUM")==null){
				tmpResultMap.put("OTHER_NUM", "&nbsp;");
			}
//			tmpResultMap.put("OTER_TOTAL",oter_total);
//			tmpResultMap.put("OTHER_NUM",other_num);
			//13、合计
			if(reg_dept_id.equals(this.TOTAL)){
				tmpResultMap.put("TARGET_NUM",getMonthtarget(dateStr));
				for(Map<String,Object> tmpMap :dbList){
						tmpResultMap.put("ACTUAL_NUM",tmpMap.get("ALL_TOTAL").toString());
						break;
				}
			}else{
				for(Map<String,Object> tmpMap :dbList){
					if(tmpMap.get("DEPT_ID") == null){
						continue;
					}
					if(reg_dept_id.equals(tmpMap.get("DEPT_ID").toString())){
						tmpResultMap.put("ACTUAL_NUM",tmpMap.get("DEPT_ALL_TOTAL").toString());
						break;
					}
				}
				
				tmpResultMap.put("TARGET_NUM",getMonthtarget(dateStr)/7);
			}
			if(tmpResultMap.get("ACTUAL_NUM")==null){
				tmpResultMap.put("ACTUAL_NUM", "&nbsp;");
			}
			
		}else{
			//1、窗口收文总量 和 抽检量
			for(Map<String,Object> tmpMap :dbList){
				if(tmpMap.get("DEPT_ID") == null){
					continue;
				}
				if(reg_dept_id.equals(this.TOTAL)){
					tmpResultMap.put("DEPT_ALL_TOTAL",tmpMap.get("ALL_TOTAL").toString());
					tmpResultMap.put("DEPT_ALL_ERROR",tmpMap.get("ALL_ERROR").toString());
					break;
				}else if(reg_dept_id.equals(tmpMap.get("DEPT_ID").toString())){
					tmpResultMap.put("DEPT_ALL_TOTAL",tmpMap.get("DEPT_ALL_TOTAL").toString());
					tmpResultMap.put("DEPT_ALL_ERROR",tmpMap.get("DEPT_ALL_ERROR").toString());
					break;
				}
			}
			//如果没有数据  则添加零
			if(tmpResultMap.get("DEPT_ALL_TOTAL")==null){
				tmpResultMap.put("DEPT_ALL_TOTAL", "0");
			}if(tmpResultMap.get("DEPT_ALL_ERROR")==null){
				tmpResultMap.put("DEPT_ALL_ERROR", "0");
			}
			
			//2、初始登记 总量 和 抽检量
			for(Map<String,Object> tmpMap :dbList){
				if(tmpMap.get("DEPT_ID") == null){
					continue;
				}
				if(reg_dept_id.equals(this.TOTAL)){
					if(WbfConstants.HOUSE_ONWERSHIP_INIT.equals(tmpMap.get("REG_TYPE").toString())){
						tmpResultMap.put("ONW_INIT_TOTAL",tmpMap.get("BUS_TOTAL").toString());
						tmpResultMap.put("ONW_INIT_NUM",tmpMap.get("BUS_TOTAL_ERROR").toString());
						break;
					}
				}else if(reg_dept_id.equals(tmpMap.get("DEPT_ID").toString())){
					if(WbfConstants.HOUSE_ONWERSHIP_INIT.equals(tmpMap.get("REG_TYPE").toString())){
						tmpResultMap.put("ONW_INIT_TOTAL",tmpMap.get("TOTAL").toString());
						tmpResultMap.put("ONW_INIT_NUM",tmpMap.get("ERROR_NUM").toString());
						break;
					}
				}
			}
			//如果没有数据  则添加零
			if(tmpResultMap.get("ONW_INIT_TOTAL")==null){
				tmpResultMap.put("ONW_INIT_TOTAL", "0");
			}if(tmpResultMap.get("ONW_INIT_NUM")==null){
				tmpResultMap.put("ONW_INIT_NUM", "0");
			}
			
			//3、二级转移总量 和 抽检量
			for(Map<String,Object> tmpMap :dbList){
				if(tmpMap.get("DEPT_ID") == null){
					continue;
				}
				if(reg_dept_id.equals(this.TOTAL)){
					if(WbfConstants.HOUSE_ONWERSHIP_SEC.equals(tmpMap.get("REG_TYPE").toString())){
						tmpResultMap.put("ONW_SEC_TOTAL",tmpMap.get("BUS_TOTAL").toString());
						tmpResultMap.put("ONW_SEC_NUM",tmpMap.get("BUS_TOTAL_ERROR").toString());
						break;
					}
				}else if(reg_dept_id.equals(tmpMap.get("DEPT_ID").toString())){
					if(WbfConstants.HOUSE_ONWERSHIP_SEC.equals(tmpMap.get("REG_TYPE").toString())){
						tmpResultMap.put("ONW_SEC_TOTAL",tmpMap.get("TOTAL").toString());
						tmpResultMap.put("ONW_SEC_NUM",tmpMap.get("ERROR_NUM").toString());
						break;
					}
				}
			}
			//如果没有数据  则添加零
			if(tmpResultMap.get("ONW_SEC_TOTAL")==null){
				tmpResultMap.put("ONW_SEC_TOTAL", "0");
			}if(tmpResultMap.get("ONW_SEC_NUM")==null){
				tmpResultMap.put("ONW_SEC_NUM", "0");
			}
			//4、三级转移总量 和 抽检量
			for(Map<String,Object> tmpMap :dbList){
				if(tmpMap.get("DEPT_ID") == null){
					continue;
				}
				if(reg_dept_id.equals(this.TOTAL)){
					if(WbfConstants.HOUSE_ONWERSHIP_THR.equals(tmpMap.get("REG_TYPE").toString())){
						tmpResultMap.put("ONW_THR_TOTAL",tmpMap.get("BUS_TOTAL").toString());
						tmpResultMap.put("ONW_THR_NUM",tmpMap.get("BUS_TOTAL_ERROR").toString());
						break;
					}
				}else if(reg_dept_id.equals(tmpMap.get("DEPT_ID").toString())){
					if(WbfConstants.HOUSE_ONWERSHIP_THR.equals(tmpMap.get("REG_TYPE").toString())){
						tmpResultMap.put("ONW_THR_TOTAL",tmpMap.get("TOTAL").toString());
						tmpResultMap.put("ONW_THR_NUM",tmpMap.get("ERROR_NUM").toString());
						break;
					}
				}
			}
			//如果没有数据  则添加零
			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
				tmpResultMap.put("ONW_THR_TOTAL", "0");
			}if(tmpResultMap.get("ONW_THR_NUM")==null){
				tmpResultMap.put("ONW_THR_NUM", "0");
			}
			
			
			//5、预售抵押
			int premortgage_ot_total=0;
			int premortgage_ot_num=0;
			if(tmpResultMap.get("PRE_MORTGAGE_TOTAL")==null){
				tmpResultMap.put("PRE_MORTGAGE_TOTAL", "0");
			}if(tmpResultMap.get("PRE_MORTGAGE_NUM")==null){
				tmpResultMap.put("PRE_MORTGAGE_NUM", "0");
			}
//			tmpResultMap.put("PRE_MORTGAGE_TOTAL",premortgage_ot_total);
//			tmpResultMap.put("PRE_MORTGAGE_NUM",premortgage_ot_num);
			//6、现售房抵押
			int mortgage_ot_total=0;
			int mortgage_ot_num=0;
			for(Map<String,Object> tmpMap :dbList){
				if(tmpMap.get("DEPT_ID") == null){
					continue;
				}
				if(reg_dept_id.equals(this.TOTAL)){
					//所有抵押下面的 的业务
					if(WbfConstants.MORTGAGE_RIGHT.equals(getBustypeParentidByBustyeid(tmpMap.get("REG_TYPE").toString()))){
						mortgage_ot_total+=Integer.valueOf(tmpMap.get("BUS_TOTAL").toString());
						mortgage_ot_num+=Integer.valueOf(tmpMap.get("BUS_TOTAL_ERROR").toString());
					}
				}else if(reg_dept_id.equals(tmpMap.get("DEPT_ID").toString())){
					if(WbfConstants.MORTGAGE_RIGHT.equals(getBustypeParentidByBustyeid(tmpMap.get("REG_TYPE").toString()))){
						mortgage_ot_total+=Integer.valueOf(tmpMap.get("TOTAL").toString());
						mortgage_ot_num+=Integer.valueOf(tmpMap.get("ERROR_NUM").toString());
					}
				}
			}
			if(tmpResultMap.get("MORTGAGE_TOTAL")==null){
				tmpResultMap.put("MORTGAGE_TOTAL", "0");
			}if(tmpResultMap.get("MORTGAGE_NUM")==null){
				tmpResultMap.put("MORTGAGE_NUM", "0");
			}
//			tmpResultMap.put("MORTGAGE_TOTAL",mortgage_ot_total);
//			tmpResultMap.put("MORTGAGE_NUM",mortgage_ot_num);
			//如果没有数据  则添加零
//			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
//				tmpResultMap.put("ONW_THR_TOTAL", "0");
//			}if(tmpResultMap.get("ONW_THR_NUM")==null){
//				tmpResultMap.put("ONW_THR_NUM", "0");
//			}
			//7、变更及其它    --涉及多个业务   需要每个都重新
			int change_ot_total=0;
			int change_ot_num=0;
			for(Map<String,Object> tmpMap :dbList){
				if(tmpMap.get("DEPT_ID") == null){
					continue;
				}
				if(reg_dept_id.equals(this.TOTAL)){
					tmpRegtype = tmpMap.get("REG_TYPE").toString();
					if(WbfConstants.HOUSE_ONWERSHIP_CHANGE.equals(tmpRegtype) || WbfConstants.LAND_CHANGE.equals(tmpRegtype)){
						change_ot_total += Integer.valueOf(tmpMap.get("BUS_TOTAL").toString());
						change_ot_num+=Integer.valueOf(tmpMap.get("BUS_TOTAL_ERROR").toString());
					}
				}else if(reg_dept_id.equals(tmpMap.get("DEPT_ID").toString())){
					if(WbfConstants.HOUSE_ONWERSHIP_CHANGE.equals(tmpRegtype) || WbfConstants.LAND_CHANGE.equals(tmpRegtype)){
						change_ot_total += Integer.valueOf(tmpMap.get("TOTAL").toString());
						change_ot_num+=Integer.valueOf(tmpMap.get("ERROR_NUM").toString());
					}
				}
			}
			if(tmpResultMap.get("CHANGE_OT_TOTAL")==null){
				tmpResultMap.put("CHANGE_OT_TOTAL", "0");
			}if(tmpResultMap.get("CHANGE_OT_NUM")==null){
				tmpResultMap.put("CHANGE_OT_NUM", "0");
			}
//			tmpResultMap.put("CHANGE_OT_TOTAL",change_ot_total);
//			tmpResultMap.put("CHANGE_OT_NUM",change_ot_num);
//			//如果没有数据  则添加零
//			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
//				tmpResultMap.put("ONW_THR_TOTAL", "0");
//			}if(tmpResultMap.get("ONW_THR_NUM")==null){
//				tmpResultMap.put("ONW_THR_NUM", "0");
//			}
			//8、安居房换证
			
			//如果没有数据  则添加零
			if(tmpResultMap.get("ANJU_TOTAL")==null){
				tmpResultMap.put("ANJU_TOTAL", "0");
			}if(tmpResultMap.get("ANJU_TNUM")==null){
				tmpResultMap.put("ANJU_TNUM", "0");
			}
			//9、预售合同备案
			for(Map<String,Object> tmpMap :dbList){
				if(tmpMap.get("DEPT_ID") == null){
					continue;
				}
				if(reg_dept_id.equals(this.TOTAL)){
					if(WbfConstants.PRE_SALE_BAKUP.equals(tmpMap.get("REG_TYPE").toString())){
						tmpResultMap.put("PRE_SALE_TOTAL",tmpMap.get("BUS_TOTAL").toString());
						tmpResultMap.put("PRE_SALE_NUM",tmpMap.get("BUS_TOTAL_ERROR").toString());
						break;
					}
				}else if(reg_dept_id.equals(tmpMap.get("DEPT_ID").toString())){
					if(WbfConstants.PRE_SALE_BAKUP.equals(tmpMap.get("REG_TYPE").toString())){
						tmpResultMap.put("PRE_SALE_TOTAL",tmpMap.get("TOTAL").toString());
						tmpResultMap.put("PRE_SALE_NUM",tmpMap.get("ERROR_NUM").toString());
						break;
					}
				}
			}
			//如果没有数据  则添加零
			if(tmpResultMap.get("PRE_SALE_TOTAL")==null){
				tmpResultMap.put("PRE_SALE_TOTAL", "0");
			}if(tmpResultMap.get("PRE_SALE_NUM")==null){
				tmpResultMap.put("PRE_SALE_NUM", "0");
			}
			
			//10、产权综合办文
			int est_com_total=0;
			int est_com_num=0;
			if(tmpResultMap.get("EST_COM_TOTAL")==null){
				tmpResultMap.put("EST_COM_TOTAL", "0");
			}if(tmpResultMap.get("EST_COM_NUM")==null){
				tmpResultMap.put("EST_COM_NUM", "0");
			}
//			tmpResultMap.put("EST_COM_TOTAL",est_com_total);
//			tmpResultMap.put("EST_COM_NUM",est_com_num);
			//11、两规登记
			int two_reg_total=0;
			int two_reg_num=0;
			if(tmpResultMap.get("TWO_REG_TOTAL")==null){
				tmpResultMap.put("TWO_REG_TOTAL", "0");
			}if(tmpResultMap.get("TWO_REG_NUM")==null){
				tmpResultMap.put("TWO_REG_NUM", "0");
			}
//			tmpResultMap.put("TWO_REG_TOTAL",two_reg_total);
//			tmpResultMap.put("TWO_REG_NUM",two_reg_num);
			//12、其它登记
			int oter_total=0;
			int other_num=0;
			if(tmpResultMap.get("OTER_TOTAL")==null){
				tmpResultMap.put("OTER_TOTAL", "0");
			}if(tmpResultMap.get("OTHER_NUM")==null){
				tmpResultMap.put("OTHER_NUM", "0");
			}
//			tmpResultMap.put("OTER_TOTAL",oter_total);
//			tmpResultMap.put("OTHER_NUM",other_num);
			//13、合计
			if(reg_dept_id.equals(this.TOTAL)){
				tmpResultMap.put("TARGET_NUM",getMonthtarget(dateStr));
				for(Map<String,Object> tmpMap :dbList){
						tmpResultMap.put("ACTUAL_NUM",tmpMap.get("ALL_TOTAL").toString());
						break;
				}
			}else{
				for(Map<String,Object> tmpMap :dbList){
					if(tmpMap.get("DEPT_ID") == null){
						continue;
					}
					if(reg_dept_id.equals(tmpMap.get("DEPT_ID").toString())){
						tmpResultMap.put("ACTUAL_NUM",tmpMap.get("DEPT_ALL_TOTAL").toString());
						break;
					}
				}
				
				tmpResultMap.put("TARGET_NUM",getMonthtarget(dateStr)/7);
			}
			if(tmpResultMap.get("ACTUAL_NUM")==null){
				tmpResultMap.put("ACTUAL_NUM", "0");
			}
			
		}
		return tmpResultMap;
	}
	
	
	/**
	 * 
	 * getCurMonthStatistics:(按条件获取细化到每天的  抽检统计报表 数据).
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getDaydetailStatistics(Map<String,Object> paraMap){
		List<Map<String,Object>> dbList  = null;
		List<Map<String,Object>> resultList  = new ArrayList<Map<String,Object>>();
		String whereSql ="where to_char(CHE_DATE, 'YYYY-MM') = to_char(add_months(SYSDATE, 0), 'YYYY-MM')";
		String conditionSql = "";
		//没有参数时不进来   筛选部门
		if(paraMap.get("dept_id")!=null){
//			String start_date = paraMap.get("start_date").toString();
//			String end_date = paraMap.get("end_date").toString();
			String dept_id = paraMap.get("dept_id").toString().trim();
			if(!dept_id.equals("")){
				conditionSql = " and dept_id=:dept_id";
			}
			//如果有用户ID  则是按用户筛选
		}else if(paraMap.get("user_id")!=null){
//			String start_date = paraMap.get("start_date").toString();
//			String end_date = paraMap.get("end_date").toString();
			String user_id = paraMap.get("user_id").toString().trim();
			if(!user_id.equals("")){
				conditionSql = " and CHE_PERSON=:user_id";
			}
		//如果房地产所属区不为空
		}else if(paraMap.get("location")!=null){
			String location = paraMap.get("location").toString().trim();
			if(!location.equals("")){
				conditionSql = " and LOCATION_REG_UNIT=:location";
			}
		}
		
		//如果时间不为空才进行下面操作
		if(paraMap.get("start_date")!=null){
			String start_date = paraMap.get("start_date").toString().trim();
			String end_date = paraMap.get("end_date").toString().trim();
			if(!start_date.equals("") && !end_date.equals("")){
				//如果起始时间大于结束时间则互换时间
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date s_Date = sm.parse(start_date);
					Date e_Date =sm.parse(end_date);
					if(s_Date.after(e_Date)){
						String tmp_str = start_date;
						start_date = end_date;
						end_date = tmp_str;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				whereSql=" where che_date  between to_date('"+start_date+"','yyyy-mm-dd ')  and to_date('"+end_date+"','yyyy-mm-dd')+1";
			}else if(!start_date.equals("") && end_date.equals("")){
				whereSql=" where che_date  >= to_date('"+start_date+"','yyyy-mm-dd ')";
			}else if(start_date.equals("") && !end_date.equals("")){			//结束日期当月的数据
				whereSql=" where che_date between trunc(to_date('"+end_date+"','yyyy-mm-dd'),'MONTH')  and to_date('"+end_date+"','yyyy-mm-dd ')";
			}
		}
		try {
			dbList = checkBaseDao.queryMapListByKey("Qualityinspection.getDaydetailStatistics",whereSql+conditionSql, paraMap);
//			if(dbList.size()==0){
//				return null;
//			}
			
			Map tmpResultMap  = null;
			//登记点  业务  拼数据
//			//1、收发文 科
//			tmpResultMap = getCurMonthStatisticsMap(dbList,this.REG_REC_DIS);
//			tmpResultMap.put("DEPT_NAME", "收发文科");
//			resultList.add(tmpResultMap);
//			
			
			
			//1、循环筛选每天业务 办理详情 默认为当前月日期
			List<Date> datelist = DateUtils.getAllTheDateOftheMonth(new Date());
			if(paraMap.get("start_date")!=null || paraMap.get("end_date")!=null){
				Object startObj = paraMap.get("start_date");
				Object endObj = paraMap.get("end_date");
				 String start = "";
				  String end = "";
				  Date dBegin = null;
				  Date dEnd = null;
				  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if(!startObj.equals("") && !endObj .equals("")){
					start = startObj.toString();
					end = endObj.toString();
					dBegin = sdf.parse(start);
					dEnd = sdf.parse(end);
					datelist = DateUtils.findDates(dBegin, dEnd);
				}else if(!endObj .equals("")){					//只有繥日期   取结束日期当前月第一天到结束日期
					end = endObj.toString();
					dEnd = sdf.parse(end);
					Calendar calendar = new GregorianCalendar();
					//Date date = DateUtils.getFirstDayOfMonth(2014, 5);
					calendar.setTime(dEnd);
					int year = calendar.get(Calendar.YEAR);
					int month =  calendar.get(Calendar.MONTH)+1;
					dBegin = DateUtils.getFirstDayOfMonth(year, month);
					datelist = DateUtils.findDates(dBegin, dEnd);
				}else if(!startObj.equals("") ){
					start = startObj.toString();
					dBegin = sdf.parse(start);
					dEnd = new Date();
					datelist = DateUtils.findDates(dBegin, dEnd);
				}
				
			}
			//获取当前月所有日期 ---默认为当前月日期
			
			for(Date date: datelist){
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
				String dateStr = sim.format(date);
				tmpResultMap = getDaydetailStatisticsMap(dbList,dateStr);		//循环获取每天详情
				tmpResultMap.put("DAY", dateStr);					//加上当前天日期
				resultList.add(tmpResultMap);
			}
			
			//2、合计
			tmpResultMap = getDaydetailStatisticsMap(dbList,this.TOTAL);
			tmpResultMap.put("DAY", "合计");
			resultList.add(tmpResultMap);
		} catch (Exception e) {
			LogUtil.error("统计查询当前月数据出出错"+e.getMessage());
		}
		
		//给最后统计加上值 
//		int tmp_target_num = 0;
//		for(int i=0;i<resultList.size()-2;i++){
//			Map tmpMap = resultList.get(i);
//			String tmptargetnumstr = tmpMap.get("TARGET_NUM").toString();
//			
//			tmp_target_num+=Integer.valueOf(tmptargetnumstr);
//		}
		//最后一个是合计  赋值
//		resultList.get(resultList.size()-1).put("TARGET_NUM", tmp_target_num);
		//每个月的总目标量
		if(paraMap.get("start_date")!=null){
			
			String start_date = paraMap.get("start_date").toString().trim();
			
			resultList.get(resultList.size()-1).put("TARGET_NUM", getMonthtarget(start_date));
		}
		return resultList;
	}
	
	/**
	 * 
	 * getDaydetailStatisticsMap:(获取部门检查数据   从统计数据中筛选出符合格式的数据).
	 *
	 * @author Joyon
	 * @param dbList
	 * @param reg_dept_id
	 * @return
	 * @since JDK 1.6
	 */
	private Map<String,Object> getDaydetailStatisticsMap(List<Map<String,Object>> dbList,String dateStr){
		String tmpRegtype=null;
		Map<String,Object> tmpResultMap  = new HashMap<String,Object>();
		if(dbList==null || dbList.size()==0){
			//1、窗口收文总量 和 抽检量
			//如果没有数据  则添加零
			if(tmpResultMap.get("WINDOW_ALL_TOTAL")==null){
				tmpResultMap.put("WINDOW_ALL_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("WINDOW_ALL_ERROR")==null){
				tmpResultMap.put("WINDOW_ALL_ERROR", "&nbsp;");
			}
			
			//2、初始登记 总量 和 抽检量
			//如果没有数据  则添加零
			if(tmpResultMap.get("ONW_INIT_TOTAL")==null){
				tmpResultMap.put("ONW_INIT_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("ONW_INIT_NUM")==null){
				tmpResultMap.put("ONW_INIT_NUM", "&nbsp;");
			}
			
			//3、二级转移总量 和 抽检量
			//如果没有数据  则添加零
			if(tmpResultMap.get("ONW_SEC_TOTAL")==null){
				tmpResultMap.put("ONW_SEC_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("ONW_SEC_NUM")==null){
				tmpResultMap.put("ONW_SEC_NUM", "&nbsp;");
			}
			//4、三级转移总量 和 抽检量
			//如果没有数据  则添加零
			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
				tmpResultMap.put("ONW_THR_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("ONW_THR_NUM")==null){
				tmpResultMap.put("ONW_THR_NUM", "&nbsp;");
			}
			
			
			//5、预售抵押
			int premortgage_ot_total=0;
			int premortgage_ot_num=0;
			if(tmpResultMap.get("PRE_MORTGAGE_TOTAL")==null){
				tmpResultMap.put("PRE_MORTGAGE_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("PRE_MORTGAGE_NUM")==null){
				tmpResultMap.put("PRE_MORTGAGE_NUM", "&nbsp;");
			}
//			tmpResultMap.put("PRE_MORTGAGE_TOTAL",premortgage_ot_total);
//			tmpResultMap.put("PRE_MORTGAGE_NUM",premortgage_ot_num);
			//6、现售房抵押
			int mortgage_ot_total=0;
			int mortgage_ot_num=0;
			if(tmpResultMap.get("MORTGAGE_TOTAL")==null){
				tmpResultMap.put("MORTGAGE_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("MORTGAGE_NUM")==null){
				tmpResultMap.put("MORTGAGE_NUM", "&nbsp;");
			}
//			tmpResultMap.put("MORTGAGE_TOTAL",mortgage_ot_total);
//			tmpResultMap.put("MORTGAGE_NUM",mortgage_ot_num);
			//如果没有数据  则添加零
//			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
//				tmpResultMap.put("ONW_THR_TOTAL", "&nbsp;");
//			}if(tmpResultMap.get("ONW_THR_NUM")==null){
//				tmpResultMap.put("ONW_THR_NUM", "&nbsp;");
//			}
			//7、变更及其它    --涉及多个业务   需要每个都重新
			int change_ot_total=0;
			int change_ot_num=0;
			if(tmpResultMap.get("CHANGE_OT_TOTAL")==null){
				tmpResultMap.put("CHANGE_OT_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("CHANGE_OT_NUM")==null){
				tmpResultMap.put("CHANGE_OT_NUM", "&nbsp;");
			}
//			tmpResultMap.put("CHANGE_OT_TOTAL",change_ot_total);
//			tmpResultMap.put("CHANGE_OT_NUM",change_ot_num);
//			//如果没有数据  则添加零
//			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
//				tmpResultMap.put("ONW_THR_TOTAL", "&nbsp;");
//			}if(tmpResultMap.get("ONW_THR_NUM")==null){
//				tmpResultMap.put("ONW_THR_NUM", "&nbsp;");
//			}
			//8、安居房换证
			
			//如果没有数据  则添加零
			if(tmpResultMap.get("ANJU_TOTAL")==null){
				tmpResultMap.put("ANJU_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("ANJU_TNUM")==null){
				tmpResultMap.put("ANJU_TNUM", "&nbsp;");
			}
			//9、预售合同备案
			//如果没有数据  则添加零
			if(tmpResultMap.get("PRE_SALE_TOTAL")==null){
				tmpResultMap.put("PRE_SALE_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("PRE_SALE_NUM")==null){
				tmpResultMap.put("PRE_SALE_NUM", "&nbsp;");
			}
			
			//10、产权综合办文
			int est_com_total=0;
			int est_com_num=0;
			if(tmpResultMap.get("EST_COM_TOTAL")==null){
				tmpResultMap.put("EST_COM_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("EST_COM_NUM")==null){
				tmpResultMap.put("EST_COM_NUM", "&nbsp;");
			}
//			tmpResultMap.put("EST_COM_TOTAL",est_com_total);
//			tmpResultMap.put("EST_COM_NUM",est_com_num);
			//11、两规登记
			int two_reg_total=0;
			int two_reg_num=0;
			if(tmpResultMap.get("TWO_REG_TOTAL")==null){
				tmpResultMap.put("TWO_REG_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("TWO_REG_NUM")==null){
				tmpResultMap.put("TWO_REG_NUM", "&nbsp;");
			}
//			tmpResultMap.put("TWO_REG_TOTAL",two_reg_total);
//			tmpResultMap.put("TWO_REG_NUM",two_reg_num);
			//12、其它登记
			int oter_total=0;
			int other_num=0;
			if(tmpResultMap.get("OTER_TOTAL")==null){
				tmpResultMap.put("OTER_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("OTHER_NUM")==null){
				tmpResultMap.put("OTHER_NUM", "&nbsp;");
			}
//			tmpResultMap.put("OTER_TOTAL",oter_total);
//			tmpResultMap.put("OTHER_NUM",other_num);
			//13、合计
			if(dateStr.equals(this.TOTAL)){
				tmpResultMap.put("TARGET_NUM",getMonthtarget(dateStr));
				for(Map<String,Object> tmpMap :dbList){
						tmpResultMap.put("ACTUAL_NUM",tmpMap.get("ALL_TOTAL").toString());
						tmpResultMap.put("ACTUAL_NUM",tmpMap.get("ALL_ERROR").toString());
						break;
				}
			}else{
				tmpResultMap.put("TARGET_NUM",getMonthtarget(dateStr)/22);
			}
			if(tmpResultMap.get("ACTUAL_NUM")==null){
				tmpResultMap.put("ACTUAL_NUM", "&nbsp;");
			}if(tmpResultMap.get("ACTUAL_ERROR_NUM")==null){
				tmpResultMap.put("ACTUAL_ERROR_NUM", "&nbsp;");
			}
			
		}else{
			//1、窗口收文总量 和 抽检量
			for(Map<String,Object> tmpMap :dbList){
				if(dateStr.equals(this.TOTAL)){
					tmpResultMap.put("WINDOW_ALL_TOTAL",tmpMap.get("ALL_TOTAL").toString());
					tmpResultMap.put("WINDOW_ALL_ERROR",tmpMap.get("ALL_ERROR").toString());
					break;
				}else if(dateStr.equals(tmpMap.get("C_DATE").toString())){
					tmpResultMap.put("WINDOW_ALL_TOTAL",tmpMap.get("DAY_TOTAL").toString());
					tmpResultMap.put("WINDOW_ALL_ERROR",tmpMap.get("DAY_ERROR_NUM").toString());
					break;
				}
			}
			//如果没有数据  则添加零
			if(tmpResultMap.get("WINDOW_ALL_TOTAL")==null){
				tmpResultMap.put("WINDOW_ALL_TOTAL", "0");
			}if(tmpResultMap.get("WINDOW_ALL_ERROR")==null){
				tmpResultMap.put("WINDOW_ALL_ERROR", "0");
			}
			
			//2、初始登记 总量 和 抽检量
			for(Map<String,Object> tmpMap :dbList){
				if(dateStr.equals(this.TOTAL)){
					if(WbfConstants.HOUSE_ONWERSHIP_INIT.equals(tmpMap.get("REG_TYPE").toString())){
						tmpResultMap.put("ONW_INIT_TOTAL",tmpMap.get("BUS_TOTAL").toString());
						tmpResultMap.put("ONW_INIT_NUM",tmpMap.get("BUS_TOTAL_ERROR").toString());
						break;
					}
				}else if(dateStr.equals(tmpMap.get("C_DATE").toString())){
					if(WbfConstants.HOUSE_ONWERSHIP_INIT.equals(tmpMap.get("REG_TYPE").toString())){
						tmpResultMap.put("ONW_INIT_TOTAL",tmpMap.get("TOTAL").toString());
						tmpResultMap.put("ONW_INIT_NUM",tmpMap.get("ERROR_NUM").toString());
						break;
					}
				}
			}
			//如果没有数据  则添加零
			if(tmpResultMap.get("ONW_INIT_TOTAL")==null){
				tmpResultMap.put("ONW_INIT_TOTAL", "0");
			}if(tmpResultMap.get("ONW_INIT_NUM")==null){
				tmpResultMap.put("ONW_INIT_NUM", "0");
			}
			
			//3、二级转移总量 和 抽检量
			for(Map<String,Object> tmpMap :dbList){
				if(dateStr.equals(this.TOTAL)){
					if(WbfConstants.HOUSE_ONWERSHIP_SEC.equals(tmpMap.get("REG_TYPE").toString())){
						tmpResultMap.put("ONW_SEC_TOTAL",tmpMap.get("BUS_TOTAL").toString());
						tmpResultMap.put("ONW_SEC_NUM",tmpMap.get("BUS_TOTAL_ERROR").toString());
						break;
					}
				}else if(dateStr.equals(tmpMap.get("C_DATE").toString())){
					if(WbfConstants.HOUSE_ONWERSHIP_SEC.equals(tmpMap.get("REG_TYPE").toString())){
						tmpResultMap.put("ONW_SEC_TOTAL",tmpMap.get("TOTAL").toString());
						tmpResultMap.put("ONW_SEC_NUM",tmpMap.get("ERROR_NUM").toString());
						break;
					}
				}
			}
			//如果没有数据  则添加零
			if(tmpResultMap.get("ONW_SEC_TOTAL")==null){
				tmpResultMap.put("ONW_SEC_TOTAL", "0");
			}if(tmpResultMap.get("ONW_SEC_NUM")==null){
				tmpResultMap.put("ONW_SEC_NUM", "0");
			}
			//4、三级转移总量 和 抽检量
			for(Map<String,Object> tmpMap :dbList){
				if(dateStr.equals(this.TOTAL)){
					if(WbfConstants.HOUSE_ONWERSHIP_THR.equals(tmpMap.get("REG_TYPE").toString())){
						tmpResultMap.put("ONW_THR_TOTAL",tmpMap.get("BUS_TOTAL").toString());
						tmpResultMap.put("ONW_THR_NUM",tmpMap.get("BUS_TOTAL_ERROR").toString());
						break;
					}
				}else if(dateStr.equals(tmpMap.get("C_DATE").toString())){
					if(WbfConstants.HOUSE_ONWERSHIP_THR.equals(tmpMap.get("REG_TYPE").toString())){
						tmpResultMap.put("ONW_THR_TOTAL",tmpMap.get("TOTAL").toString());
						tmpResultMap.put("ONW_THR_NUM",tmpMap.get("ERROR_NUM").toString());
						break;
					}
				}
			}
			//如果没有数据  则添加零
			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
				tmpResultMap.put("ONW_THR_TOTAL", "0");
			}if(tmpResultMap.get("ONW_THR_NUM")==null){
				tmpResultMap.put("ONW_THR_NUM", "0");
			}
			
			
			//5、预售抵押
			int premortgage_ot_total=0;
			int premortgage_ot_num=0;
			if(tmpResultMap.get("PRE_MORTGAGE_TOTAL")==null){
				tmpResultMap.put("PRE_MORTGAGE_TOTAL", "0");
			}if(tmpResultMap.get("PRE_MORTGAGE_NUM")==null){
				tmpResultMap.put("PRE_MORTGAGE_NUM", "0");
			}
//			tmpResultMap.put("PRE_MORTGAGE_TOTAL",premortgage_ot_total);
//			tmpResultMap.put("PRE_MORTGAGE_NUM",premortgage_ot_num);
			//6、现售房抵押
			int mortgage_ot_total=0;
			int mortgage_ot_num=0;
			for(Map<String,Object> tmpMap :dbList){
				if(dateStr.equals(this.TOTAL)){
					//所有抵押下面的 的业务
					if(WbfConstants.MORTGAGE_RIGHT.equals(getBustypeParentidByBustyeid(tmpMap.get("REG_TYPE").toString()))){
						mortgage_ot_total+=Integer.valueOf(tmpMap.get("BUS_TOTAL").toString());
						mortgage_ot_num+=Integer.valueOf(tmpMap.get("BUS_TOTAL_ERROR").toString());
					}
				}else if(dateStr.equals(tmpMap.get("C_DATE").toString())){
					if(WbfConstants.MORTGAGE_RIGHT.equals(getBustypeParentidByBustyeid(tmpMap.get("REG_TYPE").toString()))){
						mortgage_ot_total+=Integer.valueOf(tmpMap.get("TOTAL").toString());
						mortgage_ot_num+=Integer.valueOf(tmpMap.get("ERROR_NUM").toString());
					}
				}
			}
			if(tmpResultMap.get("MORTGAGE_TOTAL")==null){
				tmpResultMap.put("MORTGAGE_TOTAL", "0");
			}if(tmpResultMap.get("MORTGAGE_NUM")==null){
				tmpResultMap.put("MORTGAGE_NUM", "0");
			}
//			tmpResultMap.put("MORTGAGE_TOTAL",mortgage_ot_total);
//			tmpResultMap.put("MORTGAGE_NUM",mortgage_ot_num);
			//如果没有数据  则添加零
//			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
//				tmpResultMap.put("ONW_THR_TOTAL", "0");
//			}if(tmpResultMap.get("ONW_THR_NUM")==null){
//				tmpResultMap.put("ONW_THR_NUM", "0");
//			}
			//7、变更及其它    --涉及多个业务   需要每个都重新
			int change_ot_total=0;
			int change_ot_num=0;
			for(Map<String,Object> tmpMap :dbList){
				if(dateStr.equals(this.TOTAL)){
					tmpRegtype = tmpMap.get("REG_TYPE").toString();
					if(WbfConstants.HOUSE_ONWERSHIP_CHANGE.equals(tmpRegtype) || WbfConstants.LAND_CHANGE.equals(tmpRegtype)){
						change_ot_total += Integer.valueOf(tmpMap.get("BUS_TOTAL").toString());
						change_ot_num+=Integer.valueOf(tmpMap.get("BUS_TOTAL_ERROR").toString());
					}
				}else if(dateStr.equals(tmpMap.get("C_DATE").toString())){
					if(WbfConstants.HOUSE_ONWERSHIP_CHANGE.equals(tmpRegtype) || WbfConstants.LAND_CHANGE.equals(tmpRegtype)){
						change_ot_total += Integer.valueOf(tmpMap.get("TOTAL").toString());
						change_ot_num+=Integer.valueOf(tmpMap.get("ERROR_NUM").toString());
					}
				}
			}
			if(tmpResultMap.get("CHANGE_OT_TOTAL")==null){
				tmpResultMap.put("CHANGE_OT_TOTAL", "0");
			}if(tmpResultMap.get("CHANGE_OT_NUM")==null){
				tmpResultMap.put("CHANGE_OT_NUM", "0");
			}
//			tmpResultMap.put("CHANGE_OT_TOTAL",change_ot_total);
//			tmpResultMap.put("CHANGE_OT_NUM",change_ot_num);
//			//如果没有数据  则添加零
//			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
//				tmpResultMap.put("ONW_THR_TOTAL", "0");
//			}if(tmpResultMap.get("ONW_THR_NUM")==null){
//				tmpResultMap.put("ONW_THR_NUM", "0");
//			}
			//8、安居房换证
			
			//如果没有数据  则添加零
			if(tmpResultMap.get("ANJU_TOTAL")==null){
				tmpResultMap.put("ANJU_TOTAL", "0");
			}if(tmpResultMap.get("ANJU_TNUM")==null){
				tmpResultMap.put("ANJU_TNUM", "0");
			}
			//9、预售合同备案
			for(Map<String,Object> tmpMap :dbList){
				if(dateStr.equals(this.TOTAL)){
					if(WbfConstants.PRE_SALE_BAKUP.equals(tmpMap.get("REG_TYPE").toString())){
						tmpResultMap.put("PRE_SALE_TOTAL",tmpMap.get("BUS_TOTAL").toString());
						tmpResultMap.put("PRE_SALE_NUM",tmpMap.get("BUS_TOTAL_ERROR").toString());
						break;
					}
				}else if(dateStr.equals(tmpMap.get("C_DATE").toString())){
					if(WbfConstants.PRE_SALE_BAKUP.equals(tmpMap.get("REG_TYPE").toString())){
						tmpResultMap.put("PRE_SALE_TOTAL",tmpMap.get("TOTAL").toString());
						tmpResultMap.put("PRE_SALE_NUM",tmpMap.get("ERROR_NUM").toString());
						break;
					}
				}
			}
			//如果没有数据  则添加零
			if(tmpResultMap.get("PRE_SALE_TOTAL")==null){
				tmpResultMap.put("PRE_SALE_TOTAL", "0");
			}if(tmpResultMap.get("PRE_SALE_NUM")==null){
				tmpResultMap.put("PRE_SALE_NUM", "0");
			}
			
			//10、产权综合办文
			int est_com_total=0;
			int est_com_num=0;
			if(tmpResultMap.get("EST_COM_TOTAL")==null){
				tmpResultMap.put("EST_COM_TOTAL", "0");
			}if(tmpResultMap.get("EST_COM_NUM")==null){
				tmpResultMap.put("EST_COM_NUM", "0");
			}
//			tmpResultMap.put("EST_COM_TOTAL",est_com_total);
//			tmpResultMap.put("EST_COM_NUM",est_com_num);
			//11、两规登记
			int two_reg_total=0;
			int two_reg_num=0;
			if(tmpResultMap.get("TWO_REG_TOTAL")==null){
				tmpResultMap.put("TWO_REG_TOTAL", "0");
			}if(tmpResultMap.get("TWO_REG_NUM")==null){
				tmpResultMap.put("TWO_REG_NUM", "0");
			}
//			tmpResultMap.put("TWO_REG_TOTAL",two_reg_total);
//			tmpResultMap.put("TWO_REG_NUM",two_reg_num);
			//12、其它登记
			int oter_total=0;
			int other_num=0;
			if(tmpResultMap.get("OTER_TOTAL")==null){
				tmpResultMap.put("OTER_TOTAL", "0");
			}if(tmpResultMap.get("OTHER_NUM")==null){
				tmpResultMap.put("OTHER_NUM", "0");
			}
//			tmpResultMap.put("OTER_TOTAL",oter_total);
//			tmpResultMap.put("OTHER_NUM",other_num);
			//13、合计
			if(dateStr.equals(this.TOTAL)){
				tmpResultMap.put("TARGET_NUM",getMonthtarget(dateStr));
				for(Map<String,Object> tmpMap :dbList){
						tmpResultMap.put("ACTUAL_NUM",tmpMap.get("ALL_TOTAL").toString());
						tmpResultMap.put("ACTUAL_ERROR_NUM",tmpMap.get("ALL_ERROR").toString());
						break;
				}
			}else{
				for(Map<String,Object> tmpMap :dbList){
					if(dateStr.equals(tmpMap.get("C_DATE").toString())){
						tmpResultMap.put("ACTUAL_NUM",tmpMap.get("DAY_TOTAL").toString());
						tmpResultMap.put("ACTUAL_ERROR_NUM",tmpMap.get("DAY_ERROR_NUM").toString());
						break;
					}
				}
				
				tmpResultMap.put("TARGET_NUM",getMonthtarget(dateStr)/22);
			}
			if(tmpResultMap.get("ACTUAL_NUM")==null){
				tmpResultMap.put("ACTUAL_NUM", "0");
			}
			if(tmpResultMap.get("ACTUAL_ERROR_NUM")==null){
				tmpResultMap.put("ACTUAL_ERROR_NUM", "0");
			}
			
		}
		return tmpResultMap;
	}
	
	

	/**
	 * 
	 * getRemindersBusiness:(获取需要催办的业务).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getRemindersBusiness(Map<String,Object> paraMap){
		String businessWheresql = "";//"where reg_type!="+WbfConstants.QUALITY_INSPECTION;
		if(paraMap!=null){
			String person_id = paraMap.get("person_id").toString().trim();
			String app_date = paraMap.get("app_date").toString().trim();
			//如果核准时间和  承办人同时存在    在数据库中是无法查出来的  有互斥条件     需要通过手动逻辑判断
			if(!person_id.equals("") && !app_date.equals("")){
				//to do 未实现      要么先查出在核准日期内的   要么先查出承办人的
				businessWheresql=" and wi.WI_STATE='1' and   wi.PARTI_ID=:person_id";
			}//筛选用户
			else if(!person_id.equals("")){
				
				//if(!person_id.equals("")){
					businessWheresql=" and wi.WI_STATE='1' and   wi.PARTI_ID=:person_id";
				//}
				
			}
			//筛选核准时间
			else if(!app_date.equals("")){
				
				//if(!app_date.equals("")){
					businessWheresql="  and  wi.WI_NAME ='核准'  where to_char(wi.END_DATE,'yyyy-mm-dd')=to_char(to_date(:app_date,'yyyy-mm-dd'),'yyyy-mm-dd')";
				//}
				
			}
			//如果没有承办人  和核准时间     需要加上和工作项表  内连条件   因为需要拿到有活动工作项的流程    通过条件  wi.state=1来筛选
			if(!businessWheresql.contains("and")){
				businessWheresql=" and wi.WI_STATE='1'";
			}
			
			//按受理日期筛选
			if(paraMap.get("acc_date")!=null){
				String acc_date = paraMap.get("acc_date").toString().trim();
				if(!acc_date.equals("")){
					if(businessWheresql.contains("where")){
						businessWheresql+=" and to_char(pi.START_DATE,'yyyy-mm-dd')=to_char(to_date(:acc_date,'yyyy-mm-dd'),'yyyy-mm-dd')";
					}else{
						businessWheresql+=" where to_char(pi.START_DATE,'yyyy-mm-dd')=to_char(to_date(:acc_date,'yyyy-mm-dd'),'yyyy-mm-dd')";
					}
				}
				
			}
			
			if(paraMap.get("reg_code")!=null){
				String reg_code = paraMap.get("reg_code").toString().trim();
				if(!reg_code.equals("")){
					if(businessWheresql.contains("where")){
						businessWheresql+=" and b.reg_code=:reg_code";
					}else{
						businessWheresql+=" where b.reg_code=:reg_code";
					}
				}
				
			}
			//筛选条件登记类型
			if(paraMap.get("reg_type")!=null){
				String reg_type = paraMap.get("reg_type").toString().trim();
				if(!reg_type.equals("")){
					if(businessWheresql.contains("where")){
						businessWheresql+=" and b.reg_type=:reg_type";
					}else{
						businessWheresql+=" where b.reg_type=:reg_type";
					}
				}
			}
			//筛选条件房地产所属区
//		if(paraMap.get("location_reg_unit")!=null){
//			String location_reg_unit = paraMap.get("location_reg_unit").toString();
//			if(!location_reg_unit.equals("")){
//				if(businessWheresql.contains("where")){
//					businessWheresql+=" and location_reg_unit=:location_reg_unit";
//				}else{
//					businessWheresql=" where location_reg_unit=:location_reg_unit";
//				}
//			}
//		}
				//筛选条件  业务描述  模糊查询
				if(paraMap.get("bus_detail")!=null){
					String bus_detail = paraMap.get("bus_detail").toString().trim();
					if(!bus_detail.equals("")){
						if(businessWheresql.contains("where")){
							businessWheresql+=" and b.proc_name like '%'||:bus_detail||'%'";
						}else{
							businessWheresql+=" where b.proc_name like '%'||:bus_detail||'%'";
						}
					}
				}
				
				//筛选条件  抽检环节  模糊查询
				if(paraMap.get("ins_process_node")!=null){
					String ins_process_node = paraMap.get("ins_process_node").toString().trim();
					if(!ins_process_node.equals("")){
						if(businessWheresql.contains("where")){
							businessWheresql+=" and wi.WI_NAME like '%'||:ins_process_node||'%'";
						}else{
							businessWheresql+=" where wi.WI_NAME like '%'||:ins_process_node||'%'";
						}
					}
				}
				
				//筛选条件  抽检环节  模糊查询
				if(paraMap.get("node_remaining_time")!=null){
					String node_remaining_time = paraMap.get("node_remaining_time").toString().trim();
					if(!node_remaining_time.equals("")){
						if(businessWheresql.contains("where")){
							businessWheresql+=" and wi.REMAIN_DAYS <to_number(:ins_process_node)";
						}else{
							businessWheresql+=" where wi.REMAIN_DAYS < to_number(:ins_process_node)";
						}
					}
				}
				
				
		}else{
				businessWheresql=" and wi.WI_STATE='1'";
		}//筛选条件  if end 
		
	
		
		
		//1、业务主表中获取所有 的业务
		List<Map<String, Object>> resultMapList = new ArrayList<Map<String,Object>>();
		//List<BusinessMain> businessMainList = businessMainDao.getAll(businessWheresql,paraMap);
		
		List<Map<String, Object>> dbMapList = businessMainDao.queryMapListByKey("Qualityinspection.getRemindersBusiness",businessWheresql,paraMap);
		
		
		
		for(Map<String,Object> temp :dbMapList){
				temp.put("activName", temp.get("WI_NAME"));
				temp.put("wiId", temp.get("WI_ID"));
				temp.put("url", temp.get("URL_SPECIFY"));
				temp.put("procdefId", temp.get("PROCDEF_ID"));
				temp.put("activdefId", temp.get("ACTIVDEF_ID"));
				temp.put("procId", temp.get("PROC_ID"));
				temp.put("pi_duration", temp.get("PI_DURATION"));
				temp.put("node_duration", temp.get("DURATION"));
				temp.put("node_remain", temp.get("REMIND_DATE"));
				temp.put("node_start_date", temp.get("START_DATE_STR"));
				temp.put("procstate", temp.get("PI_STATE"));
				temp.put("user_id", temp.get("PARTI_ID"));
				temp.put("user_name", temp.get("PARTI_NAME"));
				resultMapList.add(temp);
			
		}
		
		//2、通过业务主表中流程实例ID  循环调接口获取流程表中数据    
//		Long tmp_proc_id = null;
//		ProcessInst tmpProcessInst = null;
//		Map<String,Object> tmpResultMap = null;		//返回的Map   用来循环添加到 resultMapList
//		Date curDate = new Date();					//当前日期   用来判断  当前流程是否属于这个月 
//		int curMonth = curDate.getMonth();			//当前月份   用来判断  当前流程是否属于这个月 	
//		int curYear = curDate.getYear();			//当前年份   用来判断  当前流程是否属于这个月 
//		ParticipantDelegate pd =  (ParticipantDelegate) WorkflowApplicationContext.getBean("participantDelegate");  //获取流程参与者delegegate
//		for(BusinessMain tmpBusinessMain :businessMainList){
//			 tmp_proc_id = Long.valueOf(tmpBusinessMain.getProc_id());
//			 
//			 try {
//				 tmpProcessInst = workflowClientFacade.getWorkflowClient().getProcessInstanceManager().getProcessInstById(tmp_proc_id);
//		//4、按启动时间筛选出当月的流程		
//				 if(tmpProcessInst != null){
//						 tmpResultMap = new HashMap<String,Object>();
//						 //businessMainDao.copyProperties(tmpResultMap, tmpProcessInst);
//						 try {
//							BaseAction.object2MapWithoutNull(tmpProcessInst, tmpResultMap);
//						} catch (IllegalArgumentException e) {
//							
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//							
//						} catch (IllegalAccessException e) {
//							
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//							
//						}
//						 tmpResultMap.put("reg_code", tmpBusinessMain.getReg_code());
//						 tmpResultMap.put("bus_id", tmpBusinessMain.getBus_id());
//						 
//		//6、获取当前工作项id
//						List workitemList =	workflowClientFacade.getWorkflowClient().getWorkItemManager().getActivatedWorkItemsOfProcessInst(tmpProcessInst.getProcId());
//						if(workitemList.size()>0){
//							Object tmpObject =workitemList.get(0);
//							WorkItem workitem = (WorkItem)tmpObject;
//							//查看流程详细需要的字段
//							tmpResultMap.put("activName", workitem.getActivName());
//							tmpResultMap.put("wiId",workitem.getWiId());
//							tmpResultMap.put("url", workitem.getUrlSpecify());
//							tmpResultMap.put("procdefId", workitem.getProcdefId());
//							tmpResultMap.put("activdefId",workitem.getActivdefId());
//							tmpResultMap.put("procId", workitem.getProcId());
//							tmpResultMap.put("node_duration", workitem.getDuration());			//环节总时限
//							tmpResultMap.put("node_remain",workitem.getRemainDays());			//环节剩余时间
//							
//							String node_start_date = "";
//							if(workitem.getStartDate()!=null){
//								node_start_date = org.apache.tools.ant.util.DateUtils.format(workitem.getStartDate(), "yyyy-MM-dd");
//							}
//							tmpResultMap.put("node_start_date", node_start_date);				//环节开始时间
//							
//							//获取流程任务 
//							
//							try {
//								List list = pd.getAllParticipantsOfWorkItem(workitem.getWiId());
//								if(list.size()>0){
//									Participant participant = (Participant) list.get(0);
//									tmpResultMap.put("user_id", participant.getPartiId());
//									 tmpResultMap.put("user_name", participant.getPartiName());
//								}
//							} catch (ProcessException e) {
//								
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//								
//							}
//						}else{
//							//无活动节点的  表示已经办完业务    ----暂时业务主表中无可查询当前业务 是否已经办完   只能从流程接口中获取
//							continue;
//						}	
//						
//						
//						
//						//加入返回结果
//						 resultMapList.add(tmpResultMap);
////						 //测试  只加载一条数据
////						 if(true){
////							 return resultMapList;
////						 }
//					 
//				 }
//			} catch (ProcessException e) {
//				
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				
//			}
//		}
		
		return resultMapList;
	}
	
	/**
	 * 
	 * (获取需要催办的业务).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public Page<Map<String,Object>> getRemindersBusiness(Map<String,Object> paraMap,int pageNo, int pageSize){
		String businessWheresql = "";//"where reg_type!="+WbfConstants.QUALITY_INSPECTION;
		if(paraMap!=null){
			String person_id = paraMap.get("person_id").toString().trim();
			String app_date = paraMap.get("app_date").toString().trim();
			//如果核准时间和  承办人同时存在    在数据库中是无法查出来的  有互斥条件     需要通过手动逻辑判断
			if(!person_id.equals("") && !app_date.equals("")){
				//to do 未实现      要么先查出在核准日期内的   要么先查出承办人的
				businessWheresql=" and wi.WI_STATE='1' and   wi.PARTI_ID=:person_id";
			}//筛选用户
			else if(!person_id.equals("")){
				
				//if(!person_id.equals("")){
				businessWheresql=" and wi.WI_STATE='1' and   wi.PARTI_ID=:person_id";
				//}
				
			}
			//筛选核准时间
			else if(!app_date.equals("")){
				
				//if(!app_date.equals("")){
				businessWheresql="  and  wi.WI_NAME ='核准'  where to_char(wi.END_DATE,'yyyy-mm-dd')=to_char(to_date(:app_date,'yyyy-mm-dd'),'yyyy-mm-dd')";
				//}
				
			}
			//如果没有承办人  和核准时间     需要加上和工作项表  内连条件   因为需要拿到有活动工作项的流程    通过条件  wi.state=1来筛选
			if(!businessWheresql.contains("and")){
				businessWheresql=" and wi.WI_STATE='1'";
			}
			
			//按受理日期筛选
			if(paraMap.get("acc_date")!=null){
				String acc_date = paraMap.get("acc_date").toString().trim();
				if(!acc_date.equals("")){
					if(businessWheresql.contains("where")){
						businessWheresql+=" and to_char(pi.START_DATE,'yyyy-mm-dd')=to_char(to_date(:acc_date,'yyyy-mm-dd'),'yyyy-mm-dd')";
					}else{
						businessWheresql+=" where to_char(pi.START_DATE,'yyyy-mm-dd')=to_char(to_date(:acc_date,'yyyy-mm-dd'),'yyyy-mm-dd')";
					}
				}
				
			}
			
			if(paraMap.get("reg_code")!=null){
				String reg_code = paraMap.get("reg_code").toString().trim();
				if(!reg_code.equals("")){
					if(businessWheresql.contains("where")){
						businessWheresql+=" and b.reg_code=:reg_code";
					}else{
						businessWheresql+=" where b.reg_code=:reg_code";
					}
				}
				
			}
			//筛选条件登记类型
			if(paraMap.get("reg_type")!=null){
				String reg_type = paraMap.get("reg_type").toString().trim();
				if(!reg_type.equals("")){
					if(businessWheresql.contains("where")){
						businessWheresql+=" and b.reg_type=:reg_type";
					}else{
						businessWheresql+=" where b.reg_type=:reg_type";
					}
				}
			}
			//筛选条件房地产所属区
//		if(paraMap.get("location_reg_unit")!=null){
//			String location_reg_unit = paraMap.get("location_reg_unit").toString();
//			if(!location_reg_unit.equals("")){
//				if(businessWheresql.contains("where")){
//					businessWheresql+=" and location_reg_unit=:location_reg_unit";
//				}else{
//					businessWheresql=" where location_reg_unit=:location_reg_unit";
//				}
//			}
//		}
			//筛选条件  业务描述  模糊查询
			if(paraMap.get("bus_detail")!=null){
				String bus_detail = paraMap.get("bus_detail").toString().trim();
				if(!bus_detail.equals("")){
					if(businessWheresql.contains("where")){
						businessWheresql+=" and b.proc_name like '%'||:bus_detail||'%'";
					}else{
						businessWheresql+=" where b.proc_name like '%'||:bus_detail||'%'";
					}
				}
			}
			
			//筛选条件  抽检环节  模糊查询
			if(paraMap.get("ins_process_node")!=null){
				String ins_process_node = paraMap.get("ins_process_node").toString().trim();
				if(!ins_process_node.equals("")){
					if(businessWheresql.contains("where")){
						businessWheresql+=" and wi.WI_NAME like '%'||:ins_process_node||'%'";
					}else{
						businessWheresql+=" where wi.WI_NAME like '%'||:ins_process_node||'%'";
					}
				}
			}
			
			//筛选条件  抽检环节  模糊查询
			if(paraMap.get("node_remaining_time")!=null){
				String node_remaining_time = paraMap.get("node_remaining_time").toString().trim();
				if(!node_remaining_time.equals("")){
					if(businessWheresql.contains("where")){
						businessWheresql+=" and wi.REMAIN_DAYS <to_number(:ins_process_node)";
					}else{
						businessWheresql+=" where wi.REMAIN_DAYS < to_number(:ins_process_node)";
					}
				}
			}
			
			
		}else{
			businessWheresql=" and wi.WI_STATE='1'";
		}//筛选条件  if end 
		
		
		
		
		//1、业务主表中获取所有 的业务
		List<Map<String, Object>> resultMapList = new ArrayList<Map<String,Object>>();
		//List<BusinessMain> businessMainList = businessMainDao.getAll(businessWheresql,paraMap);
		
		Page<Map<String, Object>> dbMapList = businessMainDao.queryMapPageBykeyForOracle("Qualityinspection.getRemindersBusiness",businessWheresql,paraMap,pageNo, pageSize);
		
		
		
		for(Map<String,Object> temp :dbMapList.getList()){
			temp.put("activName", temp.get("WI_NAME"));
			temp.put("wiId", temp.get("WI_ID"));
			temp.put("url", temp.get("URL_SPECIFY"));
			temp.put("procdefId", temp.get("PROCDEF_ID"));
			temp.put("activdefId", temp.get("ACTIVDEF_ID"));
			temp.put("procId", temp.get("PROC_ID"));
			temp.put("pi_duration", temp.get("PI_DURATION"));
			temp.put("node_duration", temp.get("DURATION"));
			temp.put("node_remain", temp.get("REMIND_DATE"));
			temp.put("node_start_date", temp.get("START_DATE_STR"));
			temp.put("procstate", temp.get("PI_STATE"));
			temp.put("user_id", temp.get("PARTI_ID"));
			temp.put("user_name", temp.get("PARTI_NAME"));
			resultMapList.add(temp);
		}
		
		Page re = new Page(resultMapList,dbMapList.getPageNo(),dbMapList.getPageSize(),dbMapList.getTotalSize());
		return re;
	}
	
	/**
	 * 
	 * reminde:(督办  保存一份信息到本地 并发送信息或邮件).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @throws GeneralFailureException 
	 * @since JDK 1.6
	 */
	public Map<String,Object> reminde(Map paraMap) throws GeneralFailureException{
		//1、创建短信API实例 
		SendMessage sendMsg = new SendMessage(); 
		String userId = paraMap.get("user_id").toString();
		String message = paraMap.get("message").toString();
		String message_type = paraMap.get("message_type").toString();
		String bus_id = paraMap.get("bus_id").toString();
		String is_re = paraMap.get("is_re").toString();
		
		//sendMsg.setMobileTel("13128944811");
		//2、如果需要定时发送，设置短信定时发送的发送时间 
		//sendMsg.setSendDateTime("2007-01-04 03:25:01"); 
		//3、发送短信给多个用户 
		//String sendIds=sendMsg.sendMessage2("13128944811", "test短信"); 
		//3、发送短信给一个用户 
		//String sendId=sendMsg.sendMessage2("13128944811", "你这是要干什么的？"); 
		//4、查询短信发送状态。-1，发送失败，1，发送成功，0，正在发送。 
		int status = 0;
		if(message_type.equals("message")){
			String sendId = sendMsg.sendMessageToUsers2(userId, message);
			status=sendMsg.getSendMessageResult(sendId); 
		}else{
			
		}
		
		//只要不发送失败 则保存数据进数据库 1成功  0 发送中  -1 发送失败
		if(status!=-1){
			UrgeRecord dbUrgeRecord = urgeRecordDao.get(" where bus_id=:bus_id",paraMap);
			if(dbUrgeRecord == null){
				dbUrgeRecord = new UrgeRecord();
				dbUrgeRecord.setUrge_id(urgeRecordDao.getSeqId());
				dbUrgeRecord.setBus_id(bus_id);
				dbUrgeRecord.setNotice_type(message_type);
				dbUrgeRecord.setDeal_date(new Date());
				dbUrgeRecord.setUrge_number("1");
				
				urgeRecordDao.save(dbUrgeRecord);
			}else{
				int num = Integer.valueOf(dbUrgeRecord.getUrge_number())+1;
				dbUrgeRecord.setUrge_number(String.valueOf(num));
				dbUrgeRecord.setDeal_date(new Date());
				urgeRecordDao.update(dbUrgeRecord);
			}
		}
		
//		System.out.println("短信改善状态："+status);
		//5、查询短信接收人的回复内容 
//		String receive=sendMsg.getResponseMessage(sendId);
//		System.out.println("短信回复内容："+receive);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result", "success");
		return resultMap;
	}
	
	/**
	 * 
	 * getUrgeStatistics:(统计督办数据).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getUrgeStatistics(Map<String,Object> paraMap){
		List<Map<String, Object>> resultList = null;
		String whereSql = "";
		String key="Qualityinspection.getUrgeStatistics";
		//如果参数map  不为空  则进行Sql语句封装
		if(paraMap!=null && !paraMap.isEmpty()){
			String reg_type = paraMap.get("reg_type").toString().trim();
			String acc_start_date = paraMap.get("acc_start_date").toString().trim();
			String acc_end_date = paraMap.get("acc_end_date").toString().trim();
			String reg_code = paraMap.get("reg_code").toString().trim();
			String bus_detail = paraMap.get("bus_detail").toString().trim();
			String app_start_date = paraMap.get("app_start_date").toString().trim();
			String app_end_date = paraMap.get("app_end_date").toString().trim();
			String person_id = paraMap.get("person_id").toString().trim();
			//String ins_process_node = paraMap.get("ins_process_node").toString();
			//String node_remaining_time = paraMap.get("node_remaining_time").toString();
			String remaining_time = paraMap.get("remaining_time").toString().trim();
			if(!person_id.equals("") ||!reg_type.equals("") || !acc_start_date.equals("")|| !acc_end_date.equals("") || !reg_code.equals("")|| !bus_detail.equals("")|| !app_start_date.equals("")|| !app_end_date.equals("")|| !remaining_time.equals("")){
				 
                //筛选出办理人办过的所有数据		  
                if(!person_id.equals("")){
					whereSql=" inner join platform.WF_WORK_ITEM ww  on b.proc_id=ww.proc_id and ww.exec_user_id=:person_id " ;
  				}
				if(!reg_type.equals("")){
					if(whereSql.contains("where")){
						whereSql+=" and b.reg_type=:reg_type";
					}else{
						whereSql+=" where b.reg_type=:reg_type";
					}
				}
				if(!reg_code.equals("")){
					if(whereSql.contains("where")){
						whereSql+=" and b.reg_code=:reg_code";
					}else{
						whereSql+=" where b.reg_code=:reg_code";
					}
				}
				if(!bus_detail.equals("")){
					if(whereSql.contains("where")){
						whereSql+=" and b.PROC_NAME like '%'||:bus_detail||'%'";
					}else{
						whereSql+=" where b.PROC_NAME like '%'||:bus_detail||'%'";
					}
				}
				
				//筛选受理日期
				//whereSql+=" left join BUS_ARCHIVES a on b.bus_id=a.bus_id left join platform.wf_process_inst pi on b.proc_id = pi.proc_id";
				if(!acc_start_date.equals("") && !acc_end_date.equals("")){
					if(whereSql.contains("where")){
						whereSql+=" and pi.START_DATE between to_date('"+acc_start_date+"','yyyy-mm-dd ')  and to_date('"+acc_end_date+"','yyyy-mm-dd')+1";
					}else{
						whereSql+=" where pi.START_DATE between to_date('"+acc_start_date+"','yyyy-mm-dd ')  and to_date('"+acc_end_date+"','yyyy-mm-dd')+1";
					}
				}else if(!acc_start_date.equals("")){
					if(whereSql.contains("where")){
						whereSql+=" and pi.START_DATE >= to_date('"+acc_start_date+"','yyyy-mm-dd ') ";
					}else{
						whereSql+=" where pi.START_DATE >= to_date('"+acc_start_date+"','yyyy-mm-dd ') ";
					}
				}else if(!acc_end_date.equals("")){
					if(whereSql.contains("where")){
						whereSql+=" and pi.START_DATE <= to_date('"+acc_end_date+"','yyyy-mm-dd ') ";
					}else{
						whereSql+=" where pi.START_DATE <= to_date('"+acc_end_date+"','yyyy-mm-dd ') ";
					}
				}
				
				//筛选核准日期
				if(!app_start_date.equals("") && !app_end_date.equals("")){
					if(whereSql.contains("where")){
						whereSql+=" and w.END_DATE between to_date('"+app_start_date+"','yyyy-mm-dd ')  and to_date('"+app_end_date+"','yyyy-mm-dd')+1";
					}else{
						whereSql+=" where w.END_DATE between to_date('"+app_start_date+"','yyyy-mm-dd ')  and to_date('"+app_end_date+"','yyyy-mm-dd')+1";
					}
				}else if(!app_start_date.equals("")){
					if(whereSql.contains("where")){
						whereSql+=" and w.END_DATE >= to_date('"+app_start_date+"','yyyy-mm-dd ') ";
					}else{
						whereSql+=" where w.END_DATE >= to_date('"+app_start_date+"','yyyy-mm-dd ') ";
					}
				}else if(!app_end_date.equals("")){
					if(whereSql.contains("where")){
						whereSql+=" and w.END_DATE <= to_date('"+app_end_date+"','yyyy-mm-dd ') ";
					}else{
						whereSql+=" where w.END_DATE <= to_date('"+app_end_date+"','yyyy-mm-dd ') ";
					}
				}
				
				if(!remaining_time.equals("")){
					if(whereSql.contains("where")){
						whereSql+=" and pi.REMAIN_DAYS<TO_NUMBER(:remaining_time)*24*60";
					}else{
						whereSql+=" where pi.REMAIN_DAYS<TO_NUMBER(:remaining_time)*24*60";
					}
				}
				whereSql+=")";
				//whereSql+=" left join SPE_REFUND_INFO s on b.bus_id = s.bus_id  order by b.bus_id)";
				key = "Qualityinspection.getUrgeStatisticsCondition";
			}
		}
			
	  try {
		 resultList = urgeRecordDao.queryMapListByKey(key,whereSql, paraMap);
	} catch (Exception e) {
		LogUtil.error("获取督查统计信息出错："+e.getMessage());
	}
		return resultList;
	}
	
	/**
	 * 
	 * changeIserrorstate:(修改是否是登记文状态).
	 *
	 * @author Joyon
	 * @param bus_id  被检查业务的业务ID
	 * @param type	   是否选中  checked/unchecked	
	 * @param proc_id 当次检查流程的ID
	 * @since JDK 1.6
	 */
	public void changeIserrorstate(String bus_id,String type,String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id",bus_id);
		paraMap.put("proc_id",proc_id);
		CheckBase dbCheckBase = checkBaseDao.get("where bus_id=:bus_id and CHECK_PROC_ID=:proc_id",paraMap);
		if(dbCheckBase!=null){
			
			if(type.equals("checked")){
				dbCheckBase.setIs_error("1");
			}else if(type.equals("unchecked")){
				dbCheckBase.setIs_error("0");
			}
			checkBaseDao.update(dbCheckBase);
		}else{
			LogUtil.error("QualityinspectionFacade.changeIserrorstate更新是否是差错文 状态出错");
		}
	
	}
	/**
	 * 
	 * getCheckBaseBybusidandcheckprocid:(通过被检查业务的业务Id   和检查流程的流程实例ID获取惟一的检查基本信息). 
	 *
	 * @author Joyon
	 * @param bus_id
	 * @param proc_id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public CheckBase getCheckBaseBybusidandcheckprocid(String bus_id,String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id",bus_id);
		paraMap.put("proc_id",proc_id);
		CheckBase dbCheckBase = checkBaseDao.get("where bus_id=:bus_id and CHECK_PROC_ID=:proc_id",paraMap);
		return dbCheckBase;
	}
	/**
	 * 
	 * 通过检查流程的流程实例ID获取的检查基本信息 
	 *
	 * @author Joyon
	 * @param proc_id 检查流程  的流程实例id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public CheckBase getCheckBaseBycheckprocid(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id",proc_id);
		CheckBase dbCheckBase = checkBaseDao.get("where CHECK_PROC_ID=:proc_id",paraMap);
		return dbCheckBase;
	}
	
	/**
	 * 
	 * 更新抽检基本信息表
	 *
	 * @author Joyon
	 * @param checkBase
	 * @since JDK 1.6
	 */
	public void updateCheckBase(CheckBase checkBase) throws BusinessException{
		
		checkBaseDao.update(checkBase);
	}
	/**
	 * 
	 * 获取某一个月的工作日 天数
	 *
	 * @author Joyon
	 * @param dateStr yyyy-MM
	 * @return
	 * @since JDK 1.6
	 */
	private int getMonthWorkdaycount(String dateStr){
//		if(!dateStr.matches("\\d{4}-\\d{2}")){
//			LogUtil.error("qualityinspectionFacade.getMonthWorkdaycount 传入的dateStr格式出错:"+dateStr+" 格式为yyyy-mm");
//			return 0;
//		}
		int count = 0;
		
		int month = Integer.parseInt(dateStr.substring(5,dateStr.length()));
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(dateStr.substring(0, 4)));
		cal.set(Calendar.MONTH,  month - 1);
		cal.set(Calendar.DATE, 1);

		while(cal.get(Calendar.MONTH) < month){
			int day = cal.get(Calendar.DAY_OF_WEEK);
			
			if(!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)){
				count++;
			}
			
			cal.add(Calendar.DATE, 1);
		}
		return count;
	}
}


