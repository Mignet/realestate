/**
 * Project Name:dxtx_re
 * File Name:IWorkflowFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2013-12-27����5:42:56
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
 * Date:     2013-12-27 ����5:42:56 <br/>
 * @author   dxtx
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IWorkflowFacade {
	/**
	 * 
	 * getWorkflowUser:(��õ�ǰ�û�). <br/>
	 * @author xuzz
	 * @return
	 * @throws Exception
	 * @
	 */
	public WorkflowUser getWorkflowUser(UserInfo userInfo);
	/**
	 * 
	 * getworkflowList:(��ô�������). <br/>
	 * @author xuzz
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public List getWorkflowList(UserInfo userInfo);
	
	/**
	 * 
	 * getParticipantsOfWorkItem:(��ȡ��һ���ڲ�����). <br/>
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
	 * getsubmitbtn:(��̬�����ύ��ť). <br/>
	 *
	 * @author xuzz
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public List getsubmitbtn(UserInfo userInfo,Long procdefId,String activdefId);
	
	/**
	 * createAndStartProcessInstance:(��������ʵ��ID��������ʵ��). <br/>
	 * @author xuzz
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public WorkItem createAndStartProcessInstance(UserInfo userInfo,String procdefId,String procName);
	/**
	 * 
	 * getAllqueryProcessde:(��ȡ�������Ǽ�������ҵ��). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public List getAllqueryProcessde();
	///=======================��������============================================//
	/**
	 * alterSpeRefundData: �޸����ĵǼ���Ϣ
	 * @param sri
	 * @return
	 */
	public Boolean alterSpeRefundData(SpeRefundInfo sri) throws Exception;
	
	/**
	 * gainSpeRefundInfoData: ���������Ϣ
	 * @return
	 */
	public SpeRefundInfo gainSpeRefundInfoData(Map<String,Object> srimap);
	/**
	 * alterSpeDelayData: �޸����ڵǼ���Ϣ
	 * @param sri
	 * @return
	 */
	public Boolean alterSpeDelayData(SpeDelay sdy) throws Exception;
	
	/**
	 * gainSpeDelayInfoData: ���������Ϣ
	 * @return
	 */
	public SpeDelay gainSpeDelayInfoData(Map<String,Object> srimap);
	/**
	 * alterSpeSuspendData: �޸Ĺ���Ǽ���Ϣ
	 * @param sri
	 * @return
	 */
	public Boolean alterSpeSuspendData(SpeSuspendInfo sri) throws Exception;
	
	/**
	 * gainSpeSuspendInfoData: ��ù�����Ϣ
	 * @return
	 */
	public SpeSuspendInfo gainSpeSuspendInfoData(Map<String,Object> srimap);
	/**
	 * alterSpeRejectionData: �޸Ĳ��صǼ���Ϣ
	 * @param sri
	 * @return
	 */
	public Boolean alterSpeRejectionData(SpeRejectionInfo sri) throws Exception;
	
	/**
	 * gainSpeRejectionInfoData: ��ò�����Ϣ
	 * @return
	 */
	public SpeRejectionInfo gainSpeRejectionInfoData(Map<String,Object> srimap);
	/**
	 * alterSpeRespiteData: �޸��ݻ��Ǽ���Ϣ
	 * @param sri
	 * @return
	 */
	public Boolean alterSpeRespiteData(SpeRespiteInfo sri) throws Exception;
	
	/**
	 * gainSpeRespiteInfoData: ����ݻ���Ϣ
	 * @return
	 */
	public SpeRespiteInfo gainSpeRespiteInfoData(Map<String,Object> srimap);
	/**
	 * 
	 * getBusIdAndRegCodeByProcdefId:(��ȡҵ������ID�͵ǼǱ��ͨ�����̽ڵ�ID). <br/>
	 * @author Sam
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getBusIdAndRegCodeByProcdefId(Map<String,Object> paramsMap);
	
	/**
	 * 
	 * getBusIdAndRegCodeByProcdefId:(��ȡǰҵ��ǼǱ��ͨ��ǰҵ��ID). <br/>
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
	 * gainSpeRefundInfoLst ���������Ϣ�б�
	 * @param srimap
	 * @return
	 */
	public List<SpeRefundInfo> gainSpeRefundInfoLst(Map<String,Object> srimap);
	/**
	 * gainSpeRespiteInfoLst ����ݻ���Ϣ�б�
	 * @param srimap
	 * @return
	 */
	public List<SpeRespiteInfo> gainSpeRespiteInfoLst(Map<String,Object> srimap);
	/**
	 * gainSpeRejectionInfoLst ��ò�����Ϣ�б�
	 * @param srimap
	 * @return
	 */
	public List<SpeRejectionInfo> gainSpeRejectionInfoLst(Map<String,Object> srimap);
	/**
	 * gainSpeDelayInfoLst ���������Ϣ�б�
	 * @param srimap
	 * @return
	 */
	public List<SpeDelay> gainSpeDelayInfoLst(Map<String,Object> srimap);
	/**
	 * gainSpeSuspendInfoLst ��ù�����Ϣ�б�
	 * @param srimap
	 * @return
	 */
	public List<SpeSuspendInfo> gainSpeSuspendInfoLst(Map<String,Object> srimap);
	
	/**
	 * getPreProcIdByPreRegCodeFromBusMain �õ�����ID�ɵǼǹ������ǰ�ǼǱ�Ŵ�ҵ������ȡ��
	 * @param srimap
	 * @return
	 */
	public Map<String,Object> getPreProcIdByPreRegCodeFromBusMain(Map<String,Object> srimap);
	///=======================�������̽���==========================================//
	
	/**
	 * 
	 * ��������ʵ��ID��ȡ����ʵ��ʵ��
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return ����ʵ��
	 * @since JDK 1.6
	 */
	public ProcessInst getProcessInstByProcid(String proc_id);
	
	

	/**
	 * 
	 * ͨ������ ������ID  �� ����״̬    ��ȡ��������   --��Ҫ����������������
	 *
	 * @author Joyon
	 * @param proc_id
	 * @param state ״̬   Ϊ��ʱĬ��Ϊ1
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getProctaskMapByProcidAndState(String proc_id,String state);
}


