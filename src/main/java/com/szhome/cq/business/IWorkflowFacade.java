/**
 * Project Name:dxtx_re
 * File Name:IWorkflowFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2013-12-27下午5:42:56
 * Copyright (c) 2013, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.szhome.cq.domain.model.SpeDelay;
import com.szhome.cq.domain.model.SpeRefundInfo;
import com.szhome.cq.domain.model.SpeRejectionInfo;
import com.szhome.cq.domain.model.SpeRespiteInfo;
import com.szhome.cq.domain.model.SpeSuspendInfo;
import com.szhome.process.assign.WorkflowUser;
import com.szhome.process.entity.ProcessInst;
import com.szhome.process.entity.WorkItem;
import com.szhome.security.ext.UserInfo;

/**
 * ClassName:IWorkflowFacade <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-12-27 下午5:42:56 <br/>
 * @author   dxtx
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IWorkflowFacade {
	/**
	 * 
	 * getWorkflowUser:(获得当前用户). <br/>
	 * @author xuzz
	 * @return
	 * @throws Exception
	 * @
	 */
	public WorkflowUser getWorkflowUser(UserInfo userInfo);
	/**
	 * 
	 * getworkflowList:(获得待办事项). <br/>
	 * @author xuzz
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public List getWorkflowList(UserInfo userInfo);
	
	/**
	 * 
	 * getParticipantsOfWorkItem:(获取下一环节参与者). <br/>
	 *
	 * @author xuzz
	 * @param procId 
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public List getParticipantsOfWorkItem(UserInfo userInfo,Long procdefId,String activdefId, Long procId,String btnName);

	
	/**
	 * 
	 * getsubmitbtn:(动态加载提交按钮). <br/>
	 *
	 * @author xuzz
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public List getsubmitbtn(UserInfo userInfo,Long procdefId,String activdefId);
	
	/**
	 * createAndStartProcessInstance:(根据流程实例ID启动流程实例). <br/>
	 * @author xuzz
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public WorkItem createAndStartProcessInstance(UserInfo userInfo,String procdefId,String procName);
	/**
	 * 
	 * getAllqueryProcessde:(获取不动产登记下所有业务). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public List getAllqueryProcessde();
	///=======================特殊流程============================================//
	/**
	 * alterSpeRefundData: 修改退文登记信息
	 * @param sri
	 * @return
	 */
	public Boolean alterSpeRefundData(SpeRefundInfo sri) throws Exception;
	
	/**
	 * gainSpeRefundInfoData: 获得退文信息
	 * @return
	 */
	public SpeRefundInfo gainSpeRefundInfoData(Map<String,Object> srimap);
	/**
	 * alterSpeDelayData: 修改延期登记信息
	 * @param sri
	 * @return
	 */
	public Boolean alterSpeDelayData(SpeDelay sdy) throws Exception;
	
	/**
	 * gainSpeDelayInfoData: 获得延期信息
	 * @return
	 */
	public SpeDelay gainSpeDelayInfoData(Map<String,Object> srimap);
	/**
	 * alterSpeSuspendData: 修改挂起登记信息
	 * @param sri
	 * @return
	 */
	public Boolean alterSpeSuspendData(SpeSuspendInfo sri) throws Exception;
	
	/**
	 * gainSpeSuspendInfoData: 获得挂起信息
	 * @return
	 */
	public SpeSuspendInfo gainSpeSuspendInfoData(Map<String,Object> srimap);
	/**
	 * alterSpeRejectionData: 修改驳回登记信息
	 * @param sri
	 * @return
	 */
	public Boolean alterSpeRejectionData(SpeRejectionInfo sri) throws Exception;
	
	/**
	 * gainSpeRejectionInfoData: 获得驳回信息
	 * @return
	 */
	public SpeRejectionInfo gainSpeRejectionInfoData(Map<String,Object> srimap);
	/**
	 * alterSpeRespiteData: 修改暂缓登记信息
	 * @param sri
	 * @return
	 */
	public Boolean alterSpeRespiteData(SpeRespiteInfo sri) throws Exception;
	
	/**
	 * gainSpeRespiteInfoData: 获得暂缓信息
	 * @return
	 */
	public SpeRespiteInfo gainSpeRespiteInfoData(Map<String,Object> srimap);
	/**
	 * 
	 * getBusIdAndRegCodeByProcdefId:(获取业务主表ID和登记编号通过流程节点ID). <br/>
	 * @author Sam
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getBusIdAndRegCodeByProcdefId(Map<String,Object> paramsMap);
	
	/**
	 * 
	 * getBusIdAndRegCodeByProcdefId:(获取前业务登记编号通过前业务ID). <br/>
	 * @author Sam
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getRegCodeBybusId(Map<String,Object> paramsMap);
	
	
	/**
	 * @param regCode
	 * @param wi
	 * @param procdefid
	 * @param busId
	 * @return
	 * @throws Exception
	 */
	public ResultMsg createWorkflowBusiness(String regCode,String lastbusId,WorkItem wi,String procdefid) throws Exception;
	
	/**
	 * doApplyProcessAndBusinessCreate
	 * @param ui
	 * @param busId
	 * @param regCode
	 * @param procdefId
	 * @param procName
	 * @return
	 */
	public Map<String,Object> doApplyProcessAndBusinessCreate(UserInfo ui,String busId,String regCode,String specifyId,String procdefId,String procName);
	
	/**
	 * gainSpeRefundInfoLst 获得退文信息列表
	 * @param srimap
	 * @return
	 */
	public List<SpeRefundInfo> gainSpeRefundInfoLst(Map<String,Object> srimap);
	/**
	 * gainSpeRespiteInfoLst 获得暂缓信息列表
	 * @param srimap
	 * @return
	 */
	public List<SpeRespiteInfo> gainSpeRespiteInfoLst(Map<String,Object> srimap);
	/**
	 * gainSpeRejectionInfoLst 获得驳回信息列表
	 * @param srimap
	 * @return
	 */
	public List<SpeRejectionInfo> gainSpeRejectionInfoLst(Map<String,Object> srimap);
	/**
	 * gainSpeDelayInfoLst 获得延期信息列表
	 * @param srimap
	 * @return
	 */
	public List<SpeDelay> gainSpeDelayInfoLst(Map<String,Object> srimap);
	/**
	 * gainSpeSuspendInfoLst 获得挂起信息列表
	 * @param srimap
	 * @return
	 */
	public List<SpeSuspendInfo> gainSpeSuspendInfoLst(Map<String,Object> srimap);
	
	/**
	 * getPreProcIdByPreRegCodeFromBusMain 得到流程ID由登记关联表的前登记编号从业务主表取得
	 * @param srimap
	 * @return
	 */
	public Map<String,Object> getPreProcIdByPreRegCodeFromBusMain(Map<String,Object> srimap);
	///=======================特殊流程结束==========================================//
	
	/**
	 * 
	 * 根据流程实例ID获取流程实例实体
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return 流程实例
	 * @since JDK 1.6
	 */
	public ProcessInst getProcessInstByProcid(String proc_id);
	
	

	/**
	 * 
	 * 通过流程 的流程ID  和 任务状态    获取流程数据   --主要用来启动特殊流程
	 *
	 * @author Joyon
	 * @param proc_id
	 * @param state 状态   为空时默认为1
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getProctaskMapByProcidAndState(String proc_id,String state);
}


