/**
 * Project Name:dxtx_re
 * File Name:AtditFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2013-12-23下午7:24:44
 * Copyright (c) 2013, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.szhome.cq.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plan.commons.Row;
import com.plan.commons.RowImpl;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IWorkflowFacade;
import com.szhome.cq.business.ResultMsg;
import com.szhome.cq.domain.model.BusType;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.Reg_relationship;
import com.szhome.cq.domain.model.SpeDelay;
import com.szhome.cq.domain.model.SpeRefundInfo;
import com.szhome.cq.domain.model.SpeRejectionInfo;
import com.szhome.cq.domain.model.SpeRespiteInfo;
import com.szhome.cq.domain.model.SpeSuspendInfo;
import com.szhome.cq.utils.Constants;
import com.szhome.cq.utils.ConvertTool;
import com.szhome.cq.utils.Util;
import com.szhome.cq.utils.WbfConstants;
import com.szhome.process.assign.AssignParticipant;
import com.szhome.process.assign.WorkflowUser;
import com.szhome.process.client.WorkflowClient;
import com.szhome.process.client.WorkflowClientFacade;
import com.szhome.process.client.WorkflowClientFactory;
import com.szhome.process.entity.Activity;
import com.szhome.process.entity.ActivityInst;
import com.szhome.process.entity.ParticipantConfig;
import com.szhome.process.entity.ProcessInst;
import com.szhome.process.entity.ProcessTask;
import com.szhome.process.entity.Transition;
import com.szhome.process.entity.WorkItem;
import com.szhome.process.exceptions.ProcessException;
import com.szhome.process.exceptions.TaskQueryException;
import com.szhome.process.service.WorkItemManager;
import com.szhome.security.ext.UserInfo;
import com.szhome.security.ext.UserService;

/**
 * 
 * 工作流facade
 * date: 2014-5-9 下午6:29:33 <br/>
 *
 * @author Joyon
 * @version 
 * @since JDK 1.6
 */
@Component
@Transactional
@Scope("prototype")
public class WorkflowFacade implements IWorkflowFacade {

//	/** 工作流客户端统一接口 */
	private WorkflowClientFacade workflowClientFacade = WorkflowClientFactory.getWorkflowClientFacade();
//	/** 工作流客户端接口*/
	private WorkflowClient workflowClient = workflowClientFacade.getWorkflowClient();
	
	@Autowired
	private BusinessMain businessMainDao;
	@Autowired
	private SpeRefundInfo sperefundDao;
	@Autowired
	private SpeRejectionInfo sperejectionDao;
	@Autowired
	private SpeSuspendInfo spesuspendDao;
	@Autowired
	private SpeDelay spedelayDao;
	@Autowired
	private SpeRespiteInfo sperespiteDao;
	@Autowired
	private Reg_relationship reg_relationshipDao;	
	
	@Override
	public WorkflowUser getWorkflowUser(UserInfo userInfo) {
		WorkflowUser user = new WorkflowUser();
		user.setUserId(userInfo.getUserID());
		user.setUserName(userInfo.getUserName()); 		
		return user;
	}
	
	private void setCurrentWorkflowUser(UserInfo userInfo) {
		//加上启动者的部门
		WorkflowUser wfuser =  getWorkflowUser(userInfo);
		wfuser.setAdminDeptId(userInfo.getOrganID());
		wfuser.setAdminDpetName(userInfo.getOrganName());
		workflowClient.setCurrentWorkflowUser(wfuser);
	}


	@Override
	public List getWorkflowList(UserInfo userInfo) {
		WorkflowUser user = getWorkflowUser(userInfo);
		WorkflowClientFacade wfcfacade = WorkflowClientFactory.getWorkflowClientFacade();
		wfcfacade.setCurrentWorkflowUser(user);
		List taskList = new ArrayList();
		try {
			taskList = (List) WorkflowClientFactory
					.getWorkflowClientFacade().getWorkflowClient()
					.getProcessTaskMgr().queryUserTasks(user);
		} catch (TaskQueryException e) {
			e.printStackTrace();
			throw new BusinessException("获得待办事项出错:"+e.getMessage());
		}
		return taskList;
	}
	/**
	 * 
	 * TODO 通过接口获取流程参与者.
	 * @see com.szhome.cq.business.impl.IWorkflowFacade#getParticipantsOfWorkItem(com.szhome.security.ext.UserInfo, java.lang.Long, java.lang.String)
	 */
	@Override
	public List getParticipantsOfWorkItem(UserInfo userInfo,Long procdefId,String activdefId,Long procId,String btnName) {
		List<Row> listrow=new ArrayList<Row>();
		Row Participantrow;
		List Orglist=null;
		AssignParticipant ass=new AssignParticipant();
		ParticipantConfig participant=new ParticipantConfig();
		List Activitieslist=null;
		
		try{
			WorkflowUser user = getWorkflowUser(userInfo);
			workflowClientFacade.setCurrentWorkflowUser(user);
			com.szhome.process.entity.Processdef  processdef=new com.szhome.process.entity. Processdef();
			processdef =  workflowClientFacade.getWorkflowClient().getProcessdefManager().getProcessdef(procdefId);
			Activitieslist= processdef.getActivities();
			for(int i=0;i<Activitieslist.size();i++){
				Activity act=(Activity)Activitieslist.get(i);
				if(act.getActivityId().equals(activdefId)){
					//如果是质量抽检流程  的 负责人审批环节   下一环节参与者则需要动态获取
					if(procdefId == WbfConstants.QUALITY_INSPECTION){
						if(btnName.contains("提交")){
							//当前流程 活动实例节点
							//ActivityInst ai =workflowClientFacade.getWorkflowClient().getActivityManager().getActivityInstById(paramLong)(Long.valueOf(activdefId));
							ActivityInst ai = null;
							List  aiList = null;
							try {
								aiList = workflowClientFacade.getWorkflowClient().getActivityManager().getActivatedActivityInsts(procId);
								if(aiList.size()>0){
									ai = (ActivityInst)aiList.get(0);
								}
							} catch (NumberFormatException e) {
								
								// TODO Auto-generated catch block
								e.printStackTrace();
								
							} catch (ProcessException e) {
								
								// TODO Auto-generated catch block
								e.printStackTrace();
								
							}
							//检查环节时  提交科室负责人时更改流程提交人为 科室负责人
							if(ai.getName().equals("检查")){
								//UserInfo sessionUser = SecurityExtApi.getUserInfoByUserID(userInfo.getUserID());
								
								//List  tmpList = new UserService().getHrBureauLeadersByUserID("103683");
								//暂时是用admin登录测试   admin没有科室负责人  所以先写死为陈彦id
								List  userLeaderList = new UserService().getHrDeptLeadersByUserID("103683");//userInfo.getUserID());
								UserInfo leaderUser = null;
								if(userLeaderList.size()>0){
									leaderUser =(UserInfo) userLeaderList.get(0);
									Participantrow=new RowImpl();
									Participantrow.put("partiId",leaderUser.getUserID());
									Participantrow.put("partiName", leaderUser.getUserName()+" 科室负责人");
									Participantrow.put("partiType", "user");
									Participantrow.put("remark", "负责人");
									listrow.add(Participantrow);
									return listrow;
								}
								
								
								
							}else
							//如果是 负责负责人审批 节点   则动态设流程参与者
							if(ai.getName().equals("负责人审批")){
								//获取当前流程实例ID
								Long proc_id = ai.getProcId();
								ai.getCurrentUserId();
								//获取被检查流程的活动实例  的  承办人
								listrow = FacadeFactory.getQualityinspectionFacade().getCheckbusinessuseridByCheckprocid(proc_id.toString());
								//获取被检查流程实例  承办人部门  机构等信息
								//UserInfo sessionUser = SecurityExtApi.getUserInfoByUserID(checkprocUserid);
								
	//							Participantrow=new RowImpl();
	//							if(sessionUser!=null){
	//								
	//								Participantrow.put("partiId", checkprocUserid);
	//								Participantrow.put("partiName", sessionUser.getUserName());
	//								Participantrow.put("partiType", "user");
	//								Participantrow.put("remark", "承办人");
	//							}else{
	//								LogUtil.error("WorkflowFacade.getParticipantsOfWorkItem--QUALITY_INSPECTION 质量抽检流程动态增加参与者出错");
	//							}
	//							
	//							listrow.add(Participantrow);
								return listrow;
							}
						}
					}
					
					if(act.getParticipantConfig()!=null){				
					Orglist=act.getParticipantConfig().getOrglist();
						if(Orglist!=null){
							for(int j=0;j<Orglist.size();j++){
								Participantrow=new RowImpl();
								ass=(AssignParticipant)Orglist.get(j);
								Participantrow.put("partiId", ass.getPartiId());
								Participantrow.put("partiName", ass.getPartiName());
								Participantrow.put("partiType", ass.getPartiType());
								Participantrow.put("remark", ass.getRemark());
								listrow.add(Participantrow);
							}
						}
					}					
				}
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
			throw new BusinessException("获取下一环节参与者出错:"+e.getMessage());
		}
		return listrow;
	}
	/**
	 * 
	 * getAllqueryProcessde:(获取不动产登记下所有业务). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public List getAllqueryProcessde()
	{
		List list=new ArrayList();
		list= businessMainDao.queryMapListByKey("Common.getqueryProcessdef",null);
		return list;
	}
	
	/*public String getqueryProcessdefTree
	List list= workflowClient.getProcessdefManager().queryProcessdef("1049");
	
	
	
	@Override
	public List getqueryProcessdef(UserInfo userInfo,Long procdefId,String activdefId) {
		List<Row> listrow=new ArrayList<Row>();
		Row Participantrow;
		List Orglist=null;
		AssignParticipant ass=new AssignParticipant();
		ParticipantConfig participant=new ParticipantConfig();
		List Activitieslist=null;
		try{
			WorkflowUser user = getWorkflowUser(userInfo);
			workflowClientFacade.setCurrentWorkflowUser(user);
			com.szhome.process.entity.Processdef  processdef=new com.szhome.process.entity. Processdef();
			processdef =  workflowClientFacade.getWorkflowClient().getProcessdefManager().getProcessdef(procdefId);
		}
		catch(Exception e)
		{
			
		}
		return null;
	}*/
	
	

	@Override
	@Transactional
	public WorkItem createAndStartProcessInstance(UserInfo userInfo,String procdefId,String procName) {
		WorkItemManager workItemManager = workflowClient.getWorkItemManager();
		this.setCurrentWorkflowUser(userInfo);	
		ProcessInst pi;
		List list = new ArrayList();
		WorkItem item=new WorkItem();
		try {
			Long procdefid=Long.parseLong(procdefId);
			pi = workflowClientFacade.createProcessInstance(procdefid,procName);
			workflowClientFacade.startProcessInstance(pi);
			//pi.setStarterDeptId(userInfo.get)
			list = (List) workItemManager.getActivatedWorkItemsOfProcessInst(pi.getProcId());
			item=(WorkItem)list.get(0);
			String url=item.getUrlSpecify();
			if(url.contains("spec-work-window")){
				LogUtil.debug("特殊"+url);
				url = url.replace("spec-work-window.action", "spec-work-window!home.action");
			}else{
				url = url.replace(".action", ".jsp");
				url = url.replace("/dxtx_re", "/dxtx_re/jsp");
			}
			item.setUrlSpecify(url);
		} catch (ProcessException e) {
			e.printStackTrace();
			throw new BusinessException("根据流程实例ID启动流程实例出错:"+e.getMessage());
		}
		return item;
		
	}
	@Override
	@Transactional
	public List getsubmitbtn(UserInfo userInfo,Long procdefId,String activdefId){
		List Transitionslist=null;
		List<Row> listrow=new ArrayList<Row>();
		Row row1=null;
		this.setCurrentWorkflowUser(userInfo);	
		try{			
			com.szhome.process.entity.Processdef  processdef=new com.szhome.process.entity. Processdef();
			processdef =  workflowClientFacade.getWorkflowClient().getProcessdefManager().getProcessdef(procdefId);
			Transitionslist= processdef.getTransitions();
			for(int i=0;i<Transitionslist.size();i++){
				Transition tra=(Transition)Transitionslist.get(i);
				
				if(tra.getFrom().equals(activdefId)){
					String tr;
					tr= tra.getExpressionScript();
					if(tr!=null){
						int j=0;
						j=tr.lastIndexOf("=");
						tr=tr.substring(j+2, j+3);					
					}					
					row1=new RowImpl();
					row1.put("state", tr);
					row1.put("DisplayName", tra.getDisplayName());
					row1.put("To", tra.getTo());
					listrow.add(row1);
				}
			}
		}
		catch(Exception e){		
			e.printStackTrace();
		}
		return listrow;
	}
   ///***************************特殊流程*******************//
	
	@Override
	@Transactional
	public Map<String, Object> doApplyProcessAndBusinessCreate(UserInfo ui,String busId,
			String regCode,String specifyId, String procdefId, String procName) {
		Map<String,Object> retnMap = new HashMap<String,Object>();
		Map<String,Object> srimap = new HashMap<String,Object>();
		WorkItem wi=null;
		ResultMsg msg=null;
		try {
			boolean update = false;
			String message = "";
			msg = new ResultMsg();
			try {
				if(procdefId.equals(WbfConstants.REFUND)){
					srimap.clear();
					srimap.put("refund_id", specifyId);
					SpeRefundInfo sri = this.gainSpeRefundInfoData(srimap);
					sri.setRefund_status(WbfConstants.PROCONTHEMARCH);
					message = "退文";
					update = this.alterSpeRefundData(sri);
				}else if(procdefId.equals(WbfConstants.SUSPEND)){
					srimap.clear();
					srimap.put("sus_id", specifyId);
					SpeSuspendInfo sri = this.gainSpeSuspendInfoData(srimap);
					sri.setSus_status(WbfConstants.PROCONTHEMARCH);
					message = "挂起";
					update = this.alterSpeSuspendData(sri);
				}else if(procdefId.equals(WbfConstants.REJECTION)){
					srimap.clear();
					srimap.put("rej_id", specifyId);
					SpeRejectionInfo sri = this.gainSpeRejectionInfoData(srimap);
					sri.setRej_status(WbfConstants.PROCONTHEMARCH);
					message = "驳回";
					update = this.alterSpeRejectionData(sri);
				}else if(procdefId.equals(WbfConstants.RESPITE)){
					srimap.clear();
					srimap.put("res_id", specifyId);
					SpeRespiteInfo sri = this.gainSpeRespiteInfoData(srimap);
					sri.setRes_status(WbfConstants.PROCONTHEMARCH);
					message = "暂缓";
					update = this.alterSpeRespiteData(sri);
				}else if(procdefId.equals(WbfConstants.DELAY)){
					srimap.clear();
					srimap.put("delay_id", specifyId);
					SpeDelay sri = this.gainSpeDelayInfoData(srimap);
					sri.setDelay_status(WbfConstants.PROCONTHEMARCH);
					message = "延期";
					update = this.alterSpeDelayData(sri);
				}
			} catch (Exception e) {
				msg.setReturnMsg(message+"流程表更新状态失败！");
				msg.setReturnCode(ResultMsg.CODE_FAIL);
				throw e;
			}
			if(!update){
				msg.setReturnMsg(message+"流程表更新状态失败！");
				msg.setReturnCode(ResultMsg.CODE_FAIL);
			}
			wi = this.createAndStartProcessInstance(ui, procdefId, procName);
			//插入业务主表
			String procdefid= FacadeFactory.getRegisterFacade().getProcid(wi.getProcdefId().toString());
			msg= this.createWorkflowBusiness(regCode, busId, wi, procdefid);
			retnMap.put("msg", msg);
			retnMap.put("regCode", regCode);
			retnMap.put("busId", busId);
		} catch (Exception e) {
			retnMap.put("msg", msg);
			retnMap.put("regCode", regCode);
			retnMap.put("busId", busId);
			e.printStackTrace();
		}
		return retnMap;
	}
	
	@Override
	public ResultMsg createWorkflowBusiness(String regCode,String lastbusId,WorkItem wi,
			String procdefid) throws Exception {
		ResultMsg msg = new ResultMsg();
		BusinessMain b=new BusinessMain();	
		try {
			b.setProc_id(wi.getProcId().toString());
			b.setProc_name(wi.getProcName());
			b.setPro_def_id(procdefid);
			b.setLast_bus_id(lastbusId);//设置前业务ID
			b.setReg_type(procdefid);
			b.setReg_station(WbfConstants.FUTIAN);
			b.setReg_code(regCode);
			b.setBus_id(businessMainDao.getSeqId());
			//插入业务主表
			businessMainDao.save(b);
			msg.setReturnMsg("流程业务创建成功！");
			msg.setReturnCode(ResultMsg.CODE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setReturnCode(ResultMsg.CODE_FAIL);
			msg.setReturnMsg("创建工作流业务失败:");
			throw new BusinessException("创建工作流业务失败:"+e.getMessage());
		}
		return msg;
	}
	@Override
	public Map<String,Object> getBusIdAndRegCodeByProcdefId(Map<String,Object> paramsMap){
		return businessMainDao.queryMapByKey("Common.getBusMainDataBy", " where proc_id = :procId", paramsMap);
	}
	@Override
	public Map<String, Object> getRegCodeBybusId(Map<String, Object> paramsMap) {
		Map<String,Object> retnMap = null;
		String sqlkey = "Common.getBusMainDataBy";
		if(paramsMap.containsKey("busId") && Util.isNotNull2Empty(paramsMap.get("busId"))){
			retnMap = businessMainDao.queryMapByKey(sqlkey, " where bus_id = :busId", paramsMap);
		}else if(paramsMap.containsKey("regCode") && Util.isNotNull2Empty(paramsMap.get("regCode"))){
			retnMap = businessMainDao.queryMapByKey(sqlkey, " where reg_code = :regCode", paramsMap);
		}
		return retnMap;
	}
	
	@Override
	public Boolean alterSpeRefundData(SpeRefundInfo sri) throws Exception {
		boolean flag = false;
		try {
			if(!Util.notNullEmpty(sri.getRefund_id())){
				if(!Util.notNullEmpty(sri.getRefund_no())){
					//1.查询登记编号
					Row row = new RowImpl();
					row.put("name", Constants.SERIALNAME);
					String refund_no = FacadeFactory.getIdentifierFacade().getSerialNumber(row);
					sri.setRefund_no(refund_no);
				}
				sri.setRefund_id(sperefundDao.getSeqId());
				sperefundDao.save(sri);
			}else{
				sperefundDao.update(sri);
			}
			flag = true;
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	@Override
	public SpeRefundInfo gainSpeRefundInfoData(Map<String, Object> srimap) {
		String key = "SpeRefundInfo.getSpeRefundInfoDataBybusIdownerNo";
		if(srimap.containsKey("refund_id")){
			Object o = srimap.get("refund_id");
			if(Util.isNotNull2Empty(o)){
				key = "SpeRefundInfo.getSpeRefundInfoDataByRefundId";
			}
		}
		if(srimap.containsKey("refund_no") && (!srimap.containsKey("bus_id") || !Util.isNotNull2Empty(srimap.get("bus_id")))){
			Object o = srimap.get("refund_no");
			if(Util.isNotNull2Empty(o)){
				key = "SpeRefundInfo.getSpeRefundInfoDataByRefundNo";
			}
		}
		return sperefundDao.queryDomainBykey(key,srimap);
	}
	
	@Override
	public Map<String,Object> getPreProcIdByPreRegCodeFromBusMain(Map<String, Object> srimap) {
		Map<String,Object> resultMap = null;
		try {
			String last_reg_code = null;
			resultMap = reg_relationshipDao.queryMapByKey("Register.getPreRegCodeByRegId", srimap);
			if(resultMap == null || resultMap.isEmpty()){
				throw new BusinessException("正在进行的LAST_REG_CODE查询,结果为空");
			}
			if(Util.isNotNull2Empty(resultMap.get("LAST_REG_CODE")))
			 last_reg_code = resultMap.get("LAST_REG_CODE").toString();
			if(Util.notNullEmpty(last_reg_code)){
				srimap.clear();
				srimap.put("regCode", last_reg_code);
				resultMap = businessMainDao.queryMapByKey("Common.getBusMainDataBy"," where reg_code = :regCode",srimap);
				if(resultMap == null || resultMap.isEmpty()){
					throw new BusinessException("正在进行的PROC_ID查询,结果为空");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("获取前登记编号出错"+e.getMessage());
		}
		return resultMap;
	}

	@Override
	public Boolean alterSpeDelayData(SpeDelay sdy) throws Exception {
		boolean flag = false;
		try {
			if(!Util.notNullEmpty(sdy.getDelay_id())){
				if(!Util.notNullEmpty(sdy.getDelay_no()) || sdy.getDelay_no().indexOf("*****") != -1){
					//1.查询登记编号
					Row row = new RowImpl();
					row.put("name", Constants.SERIALNAME);
					String delay_no = FacadeFactory.getIdentifierFacade().getSerialNumber(row);
					sdy.setDelay_no(delay_no);
				}
				sdy.setDelay_id(spedelayDao.getSeqId());
				spedelayDao.save(sdy);
			}else{
				spedelayDao.update(sdy);
			}
			flag = true;
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	@Override
	public SpeDelay gainSpeDelayInfoData(Map<String, Object> srimap) {
		String key = "SpeDelay.getSpeDelayInfoDataBybusIdownerNo";
		if(srimap.containsKey("delay_id")){
			Object o = srimap.get("delay_id");
			if(Util.isNotNull2Empty(o)){
				key = "SpeDelay.getSpeDelayInfoDataByDelayId";
			}
		}
		if(srimap.containsKey("delay_no") && (!srimap.containsKey("bus_id") || !Util.isNotNull2Empty(srimap.get("bus_id")))){
			Object o = srimap.get("delay_no");
			if(Util.isNotNull2Empty(o)){
				key = "SpeDelay.getSpeDelayInfoDataByDelayNo";
			}
		}
		return spedelayDao.queryDomainBykey(key,srimap);
	}

	@Override
	public Boolean alterSpeSuspendData(SpeSuspendInfo sri) throws Exception {
		boolean flag = false;
		try {
			if(!Util.notNullEmpty(sri.getSus_id())){
				if(!Util.notNullEmpty(sri.getSus_no()) || sri.getSus_no().indexOf("*****") != -1){
					//1.查询登记编号
					Row row = new RowImpl();
					row.put("name", Constants.SERIALNAME);
					String sus_no = FacadeFactory.getIdentifierFacade().getSerialNumber(row);
					sri.setSus_no(sus_no);
				}
				sri.setSus_id(spesuspendDao.getSeqId());
				spesuspendDao.save(sri);
			}else{
				spesuspendDao.update(sri);
			}
			flag = true;
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	@Override
	public SpeSuspendInfo gainSpeSuspendInfoData(Map<String, Object> srimap) {
		String key = "SpeSuspendInfo.getSpeSuspendInfoDataBybusIdownerNo";
		if(srimap.containsKey("sus_id")){
			Object o = srimap.get("sus_id");
			if(Util.isNotNull2Empty(o)){
				key = "SpeSuspendInfo.getSpeSuspendInfoDataBySuspendId";
			}
		}
		if(srimap.containsKey("sus_no") && (!srimap.containsKey("bus_id") || !Util.isNotNull2Empty(srimap.get("bus_id")))){
			Object o = srimap.get("sus_no");
			if(Util.isNotNull2Empty(o)){
				key = "SpeSuspendInfo.getSpeSuspendInfoDataBySuspendNo";
			}
		}
		return spesuspendDao.queryDomainBykey(key,srimap);
	}

	@Override
	public Boolean alterSpeRejectionData(SpeRejectionInfo sri) throws Exception {
		boolean flag = false;
		try {
			if(!Util.notNullEmpty(sri.getRej_id())){
				if(!Util.notNullEmpty(sri.getRej_no())){
					//1.查询登记编号
					Row row = new RowImpl();
					row.put("name", Constants.SERIALNAME);
					String rej_no = FacadeFactory.getIdentifierFacade().getSerialNumber(row);
					sri.setRej_no(rej_no);
				}
				sri.setRej_id(sperejectionDao.getSeqId());
				sperejectionDao.save(sri);
			}else{
				sperejectionDao.update(sri);
			}
			flag = true;
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	@Override
	public SpeRejectionInfo gainSpeRejectionInfoData(Map<String, Object> srimap) {
		String key = "SpeRejectionInfo.getSpeRejectionInfoDataBybusIdownerNo";
		if(srimap.containsKey("rej_id")){
			Object o = srimap.get("rej_id");
			if(Util.isNotNull2Empty(o)){
				key = "SpeRejectionInfo.getSpeRejectionInfoDataByRejId";
			}
		}
		if(srimap.containsKey("rej_no") && (!srimap.containsKey("bus_id") || !Util.isNotNull2Empty(srimap.get("bus_id")))){
			Object o = srimap.get("rej_no");
			if(Util.isNotNull2Empty(o)){
				key = "SpeRejectionInfo.getSpeRejectionInfoDataByRejNo";
			}
		}
		return sperejectionDao.queryDomainBykey(key,srimap);
	}

	@Override
	public Boolean alterSpeRespiteData(SpeRespiteInfo sri) throws Exception {
		boolean flag = false;
		try {
			if(!Util.notNullEmpty(sri.getRes_id())){
				if(!Util.notNullEmpty(sri.getRes_no())){
					//1.查询登记编号
					Row row = new RowImpl();
					row.put("name", Constants.SERIALNAME);
					String res_no = FacadeFactory.getIdentifierFacade().getSerialNumber(row);
					sri.setRes_no(res_no);
				}
				sri.setRes_id(sperespiteDao.getSeqId());
				sperespiteDao.save(sri);
			}else{
				sperespiteDao.update(sri);
			}
			flag = true;
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	@Override
	public SpeRespiteInfo gainSpeRespiteInfoData(Map<String, Object> srimap) {
		String key = "SpeRespiteInfo.getSpeRespiteInfoDataBybusIdownerNo";
		if(srimap.containsKey("res_id")){
			Object o = srimap.get("res_id");
			if(Util.isNotNull2Empty(o)){
				key = "SpeRespiteInfo.getSpeRespiteInfoDataByResId";
			}
		}
		if(srimap.containsKey("res_no") && (!srimap.containsKey("bus_id") || !Util.isNotNull2Empty(srimap.get("bus_id")))){
			Object o = srimap.get("res_no");
			if(Util.isNotNull2Empty(o)){
				key = "SpeRespiteInfo.getSpeRespiteInfoDataByResNo";
			}
		}
		return sperespiteDao.queryDomainBykey(key,srimap);
	}
	@Override
	public List<SpeRefundInfo> gainSpeRefundInfoLst(
			Map<String, Object> srimap) {
		String key = null;
		String whereSql = " where 1=1 ";
		if(srimap.containsKey("bus_id")){
			Object o = srimap.get("bus_id");
			if(Util.isNotNull2Empty(o)){
				key = "SpeRefundInfo.querySpeRefundInfoListBy";
				whereSql = whereSql + " and bus_id = :bus_id";
			}
		}
		if(srimap.containsKey("proc_status")){
			Object o = srimap.get("proc_status");
			if(Util.isNotNull2Empty(o)){
				key = "SpeRefundInfo.querySpeRefundInfoListBy";
				whereSql = whereSql + " and refund_status = :proc_status";
			}
		}
		if(Util.notNullEmpty(key))
			return sperefundDao.queryListByKey(key, whereSql, srimap);
	    return new ArrayList<SpeRefundInfo>();
	}

	@Override
	public List<SpeRespiteInfo> gainSpeRespiteInfoLst(
			Map<String, Object> srimap) {
		String key = null;
		String whereSql = " where 1=1 ";
		if(srimap.containsKey("bus_id")){
			Object o = srimap.get("bus_id");
			if(Util.isNotNull2Empty(o)){
				key = "SpeRespiteInfo.querySpeRespiteInfoListBy";
				whereSql = whereSql + " and bus_id = :bus_id";
			}
		}
		if(srimap.containsKey("proc_status")){
			Object o = srimap.get("proc_status");
			if(Util.isNotNull2Empty(o)){
				key = "SpeRespiteInfo.querySpeRespiteInfoListBy";
				whereSql = whereSql + " and res_status = :proc_status";
			}
		}
		if(Util.notNullEmpty(key))
			return sperespiteDao.queryListByKey(key, whereSql, srimap);
	    return new ArrayList<SpeRespiteInfo>();
	}

	@Override
	public List<SpeRejectionInfo> gainSpeRejectionInfoLst(
			Map<String, Object> srimap) {
		String key = null;
		String whereSql = " where 1=1 ";
		if(srimap.containsKey("bus_id")){
			Object o = srimap.get("bus_id");
			if(Util.isNotNull2Empty(o)){
				key = "SpeRejectionInfo.querySpeRejectionInfoListBy";
				whereSql = whereSql + " and bus_id = :bus_id";
			}
		}
		if(srimap.containsKey("proc_status")){
			Object o = srimap.get("proc_status");
			if(Util.isNotNull2Empty(o)){
				key = "SpeRejectionInfo.querySpeRejectionInfoListBy";
				whereSql = whereSql + " and rej_status = :proc_status";
			}
		}
		if(Util.notNullEmpty(key))
			return sperejectionDao.queryListByKey(key, whereSql, srimap);
	    return new ArrayList<SpeRejectionInfo>();
	}

	@Override
	public List<SpeDelay> gainSpeDelayInfoLst(
			Map<String, Object> srimap) {
		String key = null;
		String whereSql = " where 1=1 ";
		if(srimap.containsKey("bus_id")){
			Object o = srimap.get("bus_id");
			if(Util.isNotNull2Empty(o)){
				key = "SpeDelay.querySpeDelayInfoListBy";
				whereSql = whereSql + " and bus_id = :bus_id";
			}
		}
		if(srimap.containsKey("proc_status")){
			Object o = srimap.get("proc_status");
			if(Util.isNotNull2Empty(o)){
				key = "SpeDelay.querySpeDelayInfoListBy";
				whereSql = whereSql + " and delay_status = :proc_status";
			}
		}
		if(Util.notNullEmpty(key))
			return spedelayDao.queryListByKey(key, whereSql, srimap);
	    return new ArrayList<SpeDelay>();
	}

	@Override
	public List<SpeSuspendInfo> gainSpeSuspendInfoLst(
			Map<String, Object> srimap) {
		String key = null;
		String whereSql = " where 1=1 ";
		if(srimap.containsKey("bus_id")){
			Object o = srimap.get("bus_id");
			if(Util.isNotNull2Empty(o)){
				key = "SpeSuspendInfo.querySpeSuspendInfoListBy";
				whereSql = whereSql + " and bus_id = :bus_id";
			}
		}
		if(srimap.containsKey("proc_status")){
			Object o = srimap.get("proc_status");
			if(Util.isNotNull2Empty(o)){
				key = "SpeSuspendInfo.querySpeSuspendInfoListBy";
				whereSql = whereSql + " and sus_status = :proc_status";
			}
		}
		if(Util.notNullEmpty(key))
			return spesuspendDao.queryListByKey(key, whereSql, srimap);
	    return new ArrayList<SpeSuspendInfo>();
	}
	///***************************特殊流程结束*******************//
	
	/**
	 * 
	 * 根据流程实例ID获取流程实例实体
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return 流程实例
	 * @since JDK 1.6
	 */
	public ProcessInst getProcessInstByProcid(String proc_id){
		Long procId = null;
		if(Util.notNullEmpty(proc_id)){
			procId = Long.valueOf(proc_id);
		}
		ProcessInst processInst = null;
		try {
			 processInst = workflowClient.getProcessInstanceManager().getProcessInstById(procId);
		} catch (ProcessException e) {
			LogUtil.error("WorkflowFacade.getProcessInstByProcid:获取流程实例对象出错"+e.getMessage());
		}
		return processInst;
	}



	
	
	/**
	 * 
	 * 通过流程 的流程ID  和 任务状态    获取流程数据
	 *
	 * @author Joyon
	 * @param proc_id
	 * @param state 状态   为空时默认为1
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getProctaskMapByProcidAndState(String proc_id,String state){
		if(!Util.notNullEmpty(state)){
			state = "1";
		}
		String checked_proc_id  = proc_id;
		//String checked_proc_id = FacadeFactory.getCommonFacade().getBusinessMainByBusid(bus_id).getProc_id();
		
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


}

