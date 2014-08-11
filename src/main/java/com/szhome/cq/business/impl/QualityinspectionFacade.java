/**
 * Project Name:dxtx_re
 * File Name:QualityinspectionFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-4-15����11:08:08
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
 * ��������facade
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
	private String REG_FIRST= "10900004";  //��Ȩ�Ǽ����ĵǼ�һ��
	private String REG_SECOND= "10900006"; //��Ȩ�Ǽ����ĵǼǶ���
	private String REG_THIRD= "10900005";  //��Ȩ�Ǽ����ĵǼ�����
	private String REG_LONGHUA= "10013304"; //��Ȩ�Ǽ����������Ǽǵ�
	private String REG_GUANGMIN= "10013302"; //��Ȩ�Ǽ����Ĺ����Ǽǵ�
	private String REG_REC_DIS= "10013309"; //��Ȩ�Ǽ������շ��Ŀ�
	private String REG_PINGSHAN= "30000127"; //��Ȩ�Ǽ�����ƺɽ�Ǽǵ�
	private String TOTAL = "total";				//�ϼ���
//	/** �������ͻ���ͳһ�ӿ� */
	private WorkflowClientFacade workflowClientFacade = WorkflowClientFactory.getWorkflowClientFacade();
//	/** �������ͻ��˽ӿ�*/
	private WorkflowClient workflowClient = workflowClientFacade.getWorkflowClient();
	@Autowired
	private BusinessMain businessMainDao;
	@Autowired
	private CheckBase checkBaseDao;									//��������Ϣ
	@Autowired
	private CheckQualitytarget chkQualitytargetDao;					//��������ָ����Ϣ
	@Autowired
	private CheckQualityrec checkQualityrecDao;						//�������ټ�¼����Ϣ
	@Autowired
	private UrgeRecord urgeRecordDao;								//�����¼��
	
	/**
	 * 
	 * calculateTarget:(���������). 
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
		//1����ȡ���ŵ��°�����  ��������
			List<Map<String,Object>> deptbusinesslist = businessMainDao.queryMapListByKey("Qualityinspection.getDeptbusinessBymonth", paraMap);
		//2����ȡ���ŵ��� �����
			List<Map<String,Object>> depttargetlist = businessMainDao.queryMapListByKey("Qualityinspection.getDepttargetBymonth", paraMap);
		//3��ƴװ  
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		//��ȡ�¹�����
		int monthworkday = getMonthWorkdaycount(paraMap.get("monthStr").toString());
		Map<String,Object> tmpResultMap = null;
		//1���շ��� ��
		tmpResultMap = getcalculateTargetMap(this.REG_REC_DIS,deptbusinesslist,depttargetlist,monthworkday);
		tmpResultMap.put("DEPT_NAME", "�շ��Ŀ�");
		resultList.add(tmpResultMap);
		
		//2���Ǽ�һ��
		tmpResultMap = getcalculateTargetMap(this.REG_FIRST,deptbusinesslist,depttargetlist,monthworkday);
		tmpResultMap.put("DEPT_NAME", "�Ǽ�һ��");
		resultList.add(tmpResultMap);
		//3���ǼǶ���
		tmpResultMap = getcalculateTargetMap(this.REG_SECOND,deptbusinesslist,depttargetlist,monthworkday);
		tmpResultMap.put("DEPT_NAME", "�ǼǶ���");
		resultList.add(tmpResultMap);
		//4���Ǽ�����
		tmpResultMap = getcalculateTargetMap(this.REG_THIRD,deptbusinesslist,depttargetlist,monthworkday);
		tmpResultMap.put("DEPT_NAME", "�Ǽ�����");
		resultList.add(tmpResultMap);
		//5�����ڵǼǿ�
		
		//6�������Ǽǿ�
		
		//7�������Ǽǿ�
		
		//8�������Ǽǿ�
		tmpResultMap = getcalculateTargetMap(this.REG_LONGHUA,deptbusinesslist,depttargetlist,monthworkday);
		tmpResultMap.put("DEPT_NAME", "�����Ǽǿ�");
		resultList.add(tmpResultMap);
		//9��ƺɽ�Ǽǿ�
		tmpResultMap = getcalculateTargetMap(this.REG_PINGSHAN,deptbusinesslist,depttargetlist,monthworkday);
		tmpResultMap.put("DEPT_NAME", "ƺɽ�Ǽǿ�");
		resultList.add(tmpResultMap);
		//10�����Ǽǿ�
		tmpResultMap = getcalculateTargetMap(this.REG_GUANGMIN,deptbusinesslist,depttargetlist,monthworkday);
		tmpResultMap.put("DEPT_NAME", "�����Ǽǿ�");
		resultList.add(tmpResultMap);
		
		return resultList;
	}
	
	/**
	 * 
	 * getcalculateTargetMap:(ɸѡÿ�����ŵ��������).
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
		int per_count = 0;				//������������*0.03
		int dept_target_count=0;		//�����³����
		Map<String,Object> tmpMap = null;
		//ɸѡ���ŵ��°��� ��
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
		//ɸѡ���ų����ͳ��
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
	 * Ŀ��������
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
	 * getTargetInfo:(��ñ���Ŀ�� ����Ŀ�������).
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
	 * getMonthtarget:(��ȡ��Ŀ����).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public int getMonthtarget(String dateStr){
		int monthtarget = 0;
		//Ĭ��Ϊ��һ�µ�������
		String whereSql = "and to_char(pi.create_date, 'YYYY-MM') = to_char(add_months(SYSDATE, -1), 'YYYY-MM')";
		if(dateStr!=null && !dateStr.equals("") && !dateStr.equals("total")){
			//dateStr = dateStr.substring(0, 8);
			//��ʽ��Ϊ��һ���µ������ַ���
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
			monthtarget = monthtarget*3/100;												//��Ŀ��Ϊ�����ܳ�������0.03
		}
		return monthtarget;
	}
	
	/**
	 * 
	 * getInspectioned:(��ȡ�����Ѿ������ĵ�����).
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
	 * getCurMonthBusiness:(��ȡ��ǰ�·����а�����ĵ�ҵ��).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getCurMonthBusiness(){
		
		//1��ҵ�������л�ȡ���� ��ҵ��
		List<Map<String, Object>> resultMapList = new ArrayList<Map<String,Object>>();
		List<BusinessMain> businessMainList = businessMainDao.getAll();
		
		
		
		//2��ͨ��ҵ������������ʵ��ID  ѭ�����ӿڻ�ȡ���̱�������    
		Long tmp_proc_id = null;
		ProcessInst tmpProcessInst = null;
		Map<String,Object> tmpResultMap = null;		//���ص�Map   ����ѭ����ӵ� resultMapList
		Date curDate = new Date();					//��ǰ����   �����ж�  ��ǰ�����Ƿ���������� 
		int curMonth = curDate.getMonth();			//��ǰ�·�   �����ж�  ��ǰ�����Ƿ���������� 	
		int curYear = curDate.getYear();			//��ǰ���   �����ж�  ��ǰ�����Ƿ���������� 
		for(BusinessMain tmpBusinessMain :businessMainList){
			 tmp_proc_id = Long.valueOf(tmpBusinessMain.getProc_id());
			 
			 try {
				 tmpProcessInst = workflowClientFacade.getWorkflowClient().getProcessInstanceManager().getProcessInstById(tmp_proc_id);
		//4��������ʱ��ɸѡ�����µ�����		
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
		//6�������Ƿ񼺼��
						 if(curBusinessisChecked(tmpBusinessMain.getBus_id())){
							 tmpResultMap.put("inspectioned", "��");
						 } else{
							 tmpResultMap.put("inspectioned", "��");
						 }
		//7����ȡ��ǰ����
						//��ȡ��ǰ����ʵ���»��
							List aiList = workflowClientFacade.getWorkflowClient().getActivityManager().queryActivityInstsByState(tmpProcessInst.getProcId(),"1");
							if(aiList.size()>0){
								Object tmpObject =aiList.get(0);
								ActivityInst ai = (ActivityInst)tmpObject;
								tmpResultMap.put("activeName", ai.getName());
							}else{
							}	 
						 //���뷵�ؽ��
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
	 * getUncheckSample:(��ȡδ��������).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getUncheckSample(Map paraMap){
		//ɸѡ
		List<Map<String, Object>> resultMapList =  new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> relMapList = null;
		
		String whereSql = " and c.che_date is null";
		
		//�����Ǹ����ѯ
		if(paraMap!=null){
				
			
			if(paraMap.get("type")!=null){
				whereSql = " and c.che_date is not null";
			}
			//ɸѡ��������
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
			
			//ɸѡ�����û�
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
			//ɸѡ����  ҵ������  ģ����ѯ
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
	//1���ӳ�������Ϣ��ȡ������che_dateΪ�յ���������  ��ҵ���������
			 relMapList = businessMainDao.queryMapListByKey("Qualityinspection.getUncheckSample",whereSql,paraMap);
			 if(relMapList.size()>0){
	//2������ȡ�����ݵ�����ʵ��ID��ȡ�������� 
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
	//3����ȡ��ǰ����
						 //��ȡ��ǰ����ʵ���»��
						 List aiList = workflowClientFacade.getWorkflowClient().getActivityManager().queryActivityInstsByState(tmpProcessInst.getProcId(),"1");
						 if(aiList.size()>0){
							Object tmpObject =aiList.get(0);
							ActivityInst ai = (ActivityInst)tmpObject;
							//���˵�������ҵ��
							if(ai.getName().endsWith("����"))
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
			LogUtil.error("Qualityinspection.getUncheckSample :��ȡδ�����������ݳ���:"+e.getMessage());
		}
		return resultMapList;
	}
	
	/**
	 * 
	 * saveCheckbase:(������������Ϣ).
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
				//�ж� ����Ѿ�����  ������Ǹ���02  ����Ϊ01���     �ж�map ���м��ʱ��  ��Ϊ�Ѿ����    �޼����δ���
				 tmpChedate = tmpMap.get("inspectioned");
				 checkType = tmpChedate.toString().equals("��")?"01":"02";
				 
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
	 * startInspection:(�����������   ).
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
			//1.��ѯ�ǼǱ��
			
			Row row = new RowImpl();
			row.put("name", "�ǼǱ��");
			String regcode = FacadeFactory.getIdentifierFacade().getSerialNumber(row);
			//Long lastProcid = null;
			String procdefId = "1290";					//�����������̶���ID 
			String procName="";
			Map<String,Object> dataMap = null;
			if(list.size()>0)
			{
				dataMap = list.get(0);
				procName="���ĳ��("+dataMap.get("procName").toString()+")";
				//lastProcid = Long.valueOf(dataMap.get("PROC_ID").toString());
				//procdefId=dataMap.get("PROCDEF_ID").toString();
			}
			//2.����
			WorkItem wi = FacadeFactory.getWorkflowFacade().createAndStartProcessInstance(userInfo, procdefId, procName);
			//4.����ҵ�������Լ�ҵ����Ǽǵ�Ԫ������
			//String procdefid= wi.getProcdefId().toString();
			ResultMsg msg=new ResultMsg();
			
			//5���ѱ����ҵ�����������Ϊ����״̬
			//��ʱδ����
			//��ȡ��ǰ����ʵ���»��
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
	 * �����������Ϣ��Ϣ������Ȩ�Ǽ���Ϣ.
	 * @see com.szhome.cq.business.IAuditFacade#insertReg_Info(java.util.Map)
	 */
	@Transactional
	public ResultMsg insertRegInfo(String regcode,List<Map<String,Object>> list,WorkItem wi,String procdefid,UserInfo userInfo){
		ResultMsg msg = new ResultMsg();
		Reg_relationship r=new Reg_relationship();
		BusinessMain b=new BusinessMain();	
		CheckBase checkBase = new CheckBase();
		String lastbusid=list.get(0).get("BUS_ID").toString();             //��һ��ҵ��IDΪ��챻���̵�ID
		Map<String,Object> paraMap = new HashMap<String,Object>();
		Map webDatamap = list.get(0);
		//ǰ̨����ҳ�洫�����Ĳ���
		if(webDatamap.get("type")!=null){
			paraMap.put("CHE_ID",checkBaseDao.getSeqId());
		}else{
			paraMap.put("CHE_ID", list.get(0).get("CHE_ID").toString());								   //�������µ�ǰ������̵ļ��ʱ��
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
			
			//����ҵ������
			businessMainDao.save(b);
			
			//���¼�������Ϣ��  ���ʱ��  �Լ�һЩҵ������
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
				checkBase.setCheck_proc_id(wi.getProcId().toString());		//����һ������������ʵ��ID  �ͼ��ҵ���ҵ��ID����λ Ωһһ������¼
				//Ĭ��Ϊ������
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
				checkBase.setCheck_proc_id(wi.getProcId().toString());		//����һ������������ʵ��ID  �ͼ��ҵ���ҵ��ID����λ Ωһһ������¼
				//Ĭ��Ϊ������
				checkBase.setIs_error("0");
				checkBase.setChe_type("02");
				
				//��ȡ�����ҵ��  ǰһ�μ��id
				CheckBase lastCheckBase = getLastCheckBase(busMain.getBus_id());
				if(lastCheckBase!=null){
					checkBase.setPre_che_id(lastCheckBase.getChe_id());
				}else{
					LogUtil.error("QualityinspectionFacade.insertRegInfo ��ȡǰһ�μ��ҵ��ID����  ǰһ�μ��ҵ��Ϊ�գ�");
				}
				
				checkBaseDao.save(checkBase);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			msg.setReturnCode(ResultMsg.CODE_FAIL);
			msg.setReturnMsg(e.getMessage());
			throw new BusinessException("����Ǽǵ�Ԫ��������Ϣ������Ȩ�Ǽ���Ϣ����:"+e.getMessage());
//			return msg;
		}
		msg.setReturnCode(ResultMsg.CODE_SUCCESS);
		return msg;
	}
	
	/**
	 * ��ȡҵ����һ�μ����Ϣ
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
	 * getCheckcedbusdata:(ͨ����ǰ������� ������ID   ��ȡ�����ҵ�����������). 
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
			//paraRow.put("state", "9");						//������״̬Ϊ1  ����״̬
			paraRow.put("state", state);						//������״̬Ϊ1  ����״̬
			paraRow.put("proc_id", checked_proc_id);
			Object[] processTaskObj= workflowClientFacade.getWorkflowClient().getProcessTaskMgr().queryTask4PaginInGeneral(paraRow);
			//workflowClientFacade.getWorkflowClient().getProcessTaskMgr().getProcessTask(paramLong1, paramLong2)
			//�����Ѿ�������Ĺ�����
			Row participantrow = null;
			if(processTaskObj.length>0){
				 List list = (ArrayList)processTaskObj[0];
				 ProcessTask processTask = null;
				 WorkItem wi = null;
				 //����ʷ�������ִ���˷���
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
	 * getHistoryProcActivity:(��ȡ��ǰ���� ��ʷ��������̽ڵ�).
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List getHistoryprocactivityByProcid(String proc_id){
		List resultList = null;
		try {
			resultList = workflowClientFacade.getWorkflowClient().getActivityManager().queryActivityInstsByState(Long.valueOf(proc_id),"9");
			//ȥ����ʼ����
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
	 * getCheckBaseListByBusId:(ͨ��ҵ��ID  ��ȡ��������Ϣ). 
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
	 * curBusinessisChecked:(�жϵ�ǰҵ���Ƿ��Ѿ����  true ���Ѿ����  false/δ���).
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
		//�ж��  ��ʾ�Ѿ�����  �����Ѿ����� 
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
	 * getChkQualitytargetList:(��ȡ��������ָ����ϢList).
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
			LogUtil.info("��ȡ��������ָ��Ϊ��");
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
	 * applyChkQualitytargetEdit:(����������ָ��༭�������Ӧ�õ���̨���ݿ�).
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
		
		//������������
		List news = (List)dataMap.get("inserted");
		CheckQualitytarget chk = null; 
		for (int i = 0; i < news.size(); i ++) {
			temp = new RowImpl((Map)news.get(i));
			chk = new CheckQualitytarget();
			try {
				chk.setQua_tar_id(chkQualitytargetDao.getSeqId());
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("��ȡ�ֵ���seq id����"+e.getMessage());
			}
			
			chk.setQua_index(temp.getString("qua_index"));
			chk.setB_deleteflag("0");
			try {
				//logger.info("insert:" + temp);
				chkQualitytargetDao.save(chk);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("������������ָ�����"+e.getMessage());
			}
		}
		
		//�����޸�����
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
				throw new BusinessException("������������ָ�����"+e.getMessage());
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
	 * deleteChkQualitytarget:(ɾ����������ָ����Ϣ). 
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
	 * getCheckbatchNo:(��ȡ���κ�  ÿ�����д�1��ʼ  ��ʱδ���  ). 
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getCheckbatchNo(){
		String code = "";
		try {
			Row row = new RowImpl();
			row.put("name", "������κ�");
			 code  = FacadeFactory.getIdentifierFacade().getSerialNumber(row);
		} catch (Exception e) {
			LogUtil.error("��ȡ������κų���"+e.getMessage());
		}
		return code;
	}
	/**
	 * 
	 * relQualitytarget:(��������ָ����������ټ�¼��). 
	 *@param qua_tar_id ��������ָ����id  type:checked/unchecked  ����/ȡ������  bus_id:����ҵ��Id
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
	 * getCheckQualityrecListByBusid:(ͨ��ҵ��ID��ȡ�������ټ�¼����Ϣ). 
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
			LogUtil.error("QualityinspectionFacade.getCheckQualityrecListByBusid--��ȡ�Ѿ�������ָ�������:"+e.getMessage());
		}
		return resultList;
	}
	/**
	 * 
	 * getCheckbusinessuseridByCheckprocid:(ͨ�������������ʵ��ID��ȡ �����ҵ��Ļ��ҵ��id ).
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List getCheckbusinessuseridByCheckprocid(String proc_id){
		//1����ȡ��������̵�����ʵ��ID
		String check_bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getLast_bus_id();
		String check_proc_id = FacadeFactory.getCommonFacade().getBusinessMainByBusid(check_bus_id).getProc_id();
		//2����ȡ��������̻�Ļʵ���б�
		List  aiList = null;
		ActivityInst activityInsts = null;
		try {
			//aiList = workflowClientFacade.getWorkflowClient().getActivityManager().getActivatedActivityInsts(Long.valueOf(check_proc_id));
			Row paraRow = new RowImpl();
			paraRow.put("state", "9");						//������״̬Ϊ1  ����״̬
			paraRow.put("proc_id", check_proc_id);
			Object[] processTaskObj= workflowClientFacade.getWorkflowClient().getProcessTaskMgr().queryTask4PaginInGeneral(paraRow);
			//workflowClientFacade.getWorkflowClient().getProcessTaskMgr().getProcessTask(paramLong1, paramLong2)
			//�����Ѿ�������Ĺ�����
			List resultRowList = new ArrayList();
			Row participantrow = null;
			if(processTaskObj.length>0){
				 List list = (ArrayList)processTaskObj[0];
				 ProcessTask processTask = null;
				 WorkItem wi = null;
				 //����ʷ�������ִ���˷���
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
	 * querySamele:(ͨ����ѯ������ѯ).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @throws ParseException 
	 * @since JDK 1.6
	 */
	public List querySamele(Map paraMap) throws ParseException{
		long start_time = System.currentTimeMillis();
		//ɸѡ�����ǼǱ��
		
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
		//ɸѡ�����Ǽ�����
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
		//ɸѡ�������ز�������
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
		//ɸѡ����  ҵ������  ģ����ѯ
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
		
		//ɸѡ�Ƿ񼺳��
		String inspection_state = "";
		if(paraMap.get("inspection_state")!=null){
			inspection_state = paraMap.get("inspection_state").toString().trim();
		}
		//1��ҵ�������л�ȡ���� ��ҵ��
				List<Map<String, Object>> resultMapList = new ArrayList<Map<String,Object>>();
				List<BusinessMain> businessMainList = businessMainDao.getAll(businessWheresql,paraMap);
				
				
				
				//2��ͨ��ҵ������������ʵ��ID  ѭ�����ӿڻ�ȡ���̱�������    
				Long tmp_proc_id = null;
				ProcessInst tmpProcessInst = null;
				Map<String,Object> tmpResultMap = null;		//���ص�Map   ����ѭ����ӵ� resultMapList
				Date curDate = new Date();					//��ǰ����   �����ж�  ��ǰ�����Ƿ���������� 
				int curMonth = curDate.getMonth();			//��ǰ�·�   �����ж�  ��ǰ�����Ƿ���������� 	
				int curYear = curDate.getYear();			//��ǰ���   �����ж�  ��ǰ�����Ƿ���������� 
				for(BusinessMain tmpBusinessMain :businessMainList){
					tmpResultMap = new HashMap<String,Object>();
				//6�������Ƿ񼺼��
					 if(curBusinessisChecked(tmpBusinessMain.getBus_id())){
						 if(inspection_state.equals(this.UNINSPECTIONED)){
							 continue;
						 }
						 tmpResultMap.put("inspectioned", "��");
					 }else{
						 if(inspection_state.equals(this.INSPECTIONED)){
							 continue;
						 }
						 tmpResultMap.put("inspectioned", "��");
					 }
					
					 tmp_proc_id = Long.valueOf(tmpBusinessMain.getProc_id());
					 
					 try {
						 tmpProcessInst = workflowClientFacade.getWorkflowClient().getProcessInstanceManager().getProcessInstById(tmp_proc_id);
				//4��������ʱ��ɸѡ�����µ�����		
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
									
										 //�����ʼ���ں���ֹ����д��  ����
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
				
				//7����ȡ��ǰ����
								//��ȡ��ǰ����ʵ���»��
									List aiList = workflowClientFacade.getWorkflowClient().getActivityManager().queryActivityInstsByState(tmpProcessInst.getProcId(),"1");
									if(aiList.size()>0){
										Object tmpObject =aiList.get(0);
										ActivityInst ai = (ActivityInst)tmpObject;
										tmpResultMap.put("activeName", ai.getName());
									}else{
									}	 
								 //���뷵�ؽ��
								 resultMapList.add(tmpResultMap);
							 
							 
						 }
					 } catch (ProcessException e) {
						
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
				}
				
				long end_time = System.currentTimeMillis();
				System.out.println("---------------------��ѯ��ʱ��"+(end_time-start_time)+"ms-----------------");
				return resultMapList;
	}
	
	/**
	 * 
	 * getBustypenameByBustyeid:(ͨ��ҵ������id��ȡҵ��������).
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
			LogUtil.error("QualityinspectionFacade.getBustypenameByBustyeid ��ȡҵ������������:"+e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 * getBustypenameByBustyeid:(��ȡ��ǰҵ������ ������id).
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
			LogUtil.error("QualityinspectionFacade.getBustypenameByBustyeid ��ȡҵ�����ͳ���:"+e.getMessage());
		}
		return null;
	}
	/**
	 * 
	 * getCurMonthStatistics:(���������Ǽ�����  ͳ�Ƶ�ǰ�µĳ������).
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
				//�����ʼʱ����ڽ���ʱ���򻥻�ʱ��
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
			//�Ǽǵ�  ҵ��  ƴ����
			//1���շ��� ��
			tmpResultMap = getCurMonthStatisticsMap(dbList,this.REG_REC_DIS,start_date);
			tmpResultMap.put("DEPT_NAME", "�շ��Ŀ�");
			resultList.add(tmpResultMap);
			
			//2���Ǽ�һ��
			tmpResultMap = getCurMonthStatisticsMap(dbList,this.REG_FIRST,start_date);
			tmpResultMap.put("DEPT_NAME", "�Ǽ�һ��");
			resultList.add(tmpResultMap);
			//3���ǼǶ���
			tmpResultMap = getCurMonthStatisticsMap(dbList,this.REG_SECOND,start_date);
			tmpResultMap.put("DEPT_NAME", "�ǼǶ���");
			resultList.add(tmpResultMap);
			//4���Ǽ�����
			tmpResultMap = getCurMonthStatisticsMap(dbList,this.REG_THIRD,start_date);
			tmpResultMap.put("DEPT_NAME", "�Ǽ�����");
			resultList.add(tmpResultMap);
			//5�����ڵǼǿ�
			
			//6�������Ǽǿ�
			
			//7�������Ǽǿ�
			
			//8�������Ǽǿ�
			tmpResultMap = getCurMonthStatisticsMap(dbList,this.REG_LONGHUA,start_date);
			tmpResultMap.put("DEPT_NAME", "�����Ǽǿ�");
			resultList.add(tmpResultMap);
			//9��ƺɽ�Ǽǿ�
			tmpResultMap = getCurMonthStatisticsMap(dbList,this.REG_PINGSHAN,start_date);
			tmpResultMap.put("DEPT_NAME", "ƺɽ�Ǽǿ�");
			resultList.add(tmpResultMap);
			//10�����Ǽǿ�
			tmpResultMap = getCurMonthStatisticsMap(dbList,this.REG_GUANGMIN,start_date);
			tmpResultMap.put("DEPT_NAME", "�����Ǽǿ�");
			resultList.add(tmpResultMap);
			//11���ϼ�
			tmpResultMap = getCurMonthStatisticsMap(dbList,this.TOTAL,start_date);
			tmpResultMap.put("DEPT_NAME", "�ϼ�");
			resultList.add(tmpResultMap);
		} catch (Exception e) {
			LogUtil.error("ͳ�Ʋ�ѯ��ǰ�����ݳ�����"+e.getMessage());
		}
		
		//���·ݼ���Ŀ��
//		int tmp_target_num = 0;
////		for(int i=0;i<resultList.size()-2;i++){
////			Map tmpMap = resultList.get(i);
////			String tmptargetnumstr = tmpMap.get("TARGET_NUM").toString();
////			
////			tmp_target_num+=Integer.valueOf(tmptargetnumstr);
////		}
//		tmp_target_num = getMonthtarget(start_date); 
//		//���һ���Ǻϼ�  ��ֵ
//		resultList.get(resultList.size()-1).put("TARGET_NUM", tmp_target_num);
		return resultList;
	}
	
	/**
	 * 
	 * getCurMonthStatisticsMap:(��ȡ���ż������   ��ͳ��������ɸѡ�����ϸ�ʽ������).
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
			//1�������������� �� �����
			//���û������  �������
			if(tmpResultMap.get("DEPT_ALL_TOTAL")==null){
				tmpResultMap.put("DEPT_ALL_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("DEPT_ALL_ERROR")==null){
				tmpResultMap.put("DEPT_ALL_ERROR", "&nbsp;");
			}
			
			//2����ʼ�Ǽ� ���� �� �����
			//���û������  �������
			if(tmpResultMap.get("ONW_INIT_TOTAL")==null){
				tmpResultMap.put("ONW_INIT_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("ONW_INIT_NUM")==null){
				tmpResultMap.put("ONW_INIT_NUM", "&nbsp;");
			}
			
			//3������ת������ �� �����
			//���û������  �������
			if(tmpResultMap.get("ONW_SEC_TOTAL")==null){
				tmpResultMap.put("ONW_SEC_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("ONW_SEC_NUM")==null){
				tmpResultMap.put("ONW_SEC_NUM", "&nbsp;");
			}
			//4������ת������ �� �����
			//���û������  �������
			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
				tmpResultMap.put("ONW_THR_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("ONW_THR_NUM")==null){
				tmpResultMap.put("ONW_THR_NUM", "&nbsp;");
			}
			
			
			//5��Ԥ�۵�Ѻ
			int premortgage_ot_total=0;
			int premortgage_ot_num=0;
			if(tmpResultMap.get("PRE_MORTGAGE_TOTAL")==null){
				tmpResultMap.put("PRE_MORTGAGE_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("PRE_MORTGAGE_NUM")==null){
				tmpResultMap.put("PRE_MORTGAGE_NUM", "&nbsp;");
			}
//			tmpResultMap.put("PRE_MORTGAGE_TOTAL",premortgage_ot_total);
//			tmpResultMap.put("PRE_MORTGAGE_NUM",premortgage_ot_num);
			//6�����۷���Ѻ
			int mortgage_ot_total=0;
			int mortgage_ot_num=0;
			if(tmpResultMap.get("MORTGAGE_TOTAL")==null){
				tmpResultMap.put("MORTGAGE_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("MORTGAGE_NUM")==null){
				tmpResultMap.put("MORTGAGE_NUM", "&nbsp;");
			}
//			tmpResultMap.put("MORTGAGE_TOTAL",mortgage_ot_total);
//			tmpResultMap.put("MORTGAGE_NUM",mortgage_ot_num);
			//���û������  �������
//			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
//				tmpResultMap.put("ONW_THR_TOTAL", "&nbsp;");
//			}if(tmpResultMap.get("ONW_THR_NUM")==null){
//				tmpResultMap.put("ONW_THR_NUM", "&nbsp;");
//			}
			//7�����������    --�漰���ҵ��   ��Ҫÿ��������
			int change_ot_total=0;
			int change_ot_num=0;
			if(tmpResultMap.get("CHANGE_OT_TOTAL")==null){
				tmpResultMap.put("CHANGE_OT_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("CHANGE_OT_NUM")==null){
				tmpResultMap.put("CHANGE_OT_NUM", "&nbsp;");
			}
//			tmpResultMap.put("CHANGE_OT_TOTAL",change_ot_total);
//			tmpResultMap.put("CHANGE_OT_NUM",change_ot_num);
//			//���û������  �������
//			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
//				tmpResultMap.put("ONW_THR_TOTAL", "&nbsp;");
//			}if(tmpResultMap.get("ONW_THR_NUM")==null){
//				tmpResultMap.put("ONW_THR_NUM", "&nbsp;");
//			}
			//8�����ӷ���֤
			
			//���û������  �������
			if(tmpResultMap.get("ANJU_TOTAL")==null){
				tmpResultMap.put("ANJU_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("ANJU_TNUM")==null){
				tmpResultMap.put("ANJU_TNUM", "&nbsp;");
			}
			//9��Ԥ�ۺ�ͬ����
			//���û������  �������
			if(tmpResultMap.get("PRE_SALE_TOTAL")==null){
				tmpResultMap.put("PRE_SALE_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("PRE_SALE_NUM")==null){
				tmpResultMap.put("PRE_SALE_NUM", "&nbsp;");
			}
			
			//10����Ȩ�ۺϰ���
			int est_com_total=0;
			int est_com_num=0;
			if(tmpResultMap.get("EST_COM_TOTAL")==null){
				tmpResultMap.put("EST_COM_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("EST_COM_NUM")==null){
				tmpResultMap.put("EST_COM_NUM", "&nbsp;");
			}
//			tmpResultMap.put("EST_COM_TOTAL",est_com_total);
//			tmpResultMap.put("EST_COM_NUM",est_com_num);
			//11������Ǽ�
			int two_reg_total=0;
			int two_reg_num=0;
			if(tmpResultMap.get("TWO_REG_TOTAL")==null){
				tmpResultMap.put("TWO_REG_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("TWO_REG_NUM")==null){
				tmpResultMap.put("TWO_REG_NUM", "&nbsp;");
			}
//			tmpResultMap.put("TWO_REG_TOTAL",two_reg_total);
//			tmpResultMap.put("TWO_REG_NUM",two_reg_num);
			//12�������Ǽ�
			int oter_total=0;
			int other_num=0;
			if(tmpResultMap.get("OTER_TOTAL")==null){
				tmpResultMap.put("OTER_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("OTHER_NUM")==null){
				tmpResultMap.put("OTHER_NUM", "&nbsp;");
			}
//			tmpResultMap.put("OTER_TOTAL",oter_total);
//			tmpResultMap.put("OTHER_NUM",other_num);
			//13���ϼ�
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
			//1�������������� �� �����
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
			//���û������  �������
			if(tmpResultMap.get("DEPT_ALL_TOTAL")==null){
				tmpResultMap.put("DEPT_ALL_TOTAL", "0");
			}if(tmpResultMap.get("DEPT_ALL_ERROR")==null){
				tmpResultMap.put("DEPT_ALL_ERROR", "0");
			}
			
			//2����ʼ�Ǽ� ���� �� �����
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
			//���û������  �������
			if(tmpResultMap.get("ONW_INIT_TOTAL")==null){
				tmpResultMap.put("ONW_INIT_TOTAL", "0");
			}if(tmpResultMap.get("ONW_INIT_NUM")==null){
				tmpResultMap.put("ONW_INIT_NUM", "0");
			}
			
			//3������ת������ �� �����
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
			//���û������  �������
			if(tmpResultMap.get("ONW_SEC_TOTAL")==null){
				tmpResultMap.put("ONW_SEC_TOTAL", "0");
			}if(tmpResultMap.get("ONW_SEC_NUM")==null){
				tmpResultMap.put("ONW_SEC_NUM", "0");
			}
			//4������ת������ �� �����
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
			//���û������  �������
			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
				tmpResultMap.put("ONW_THR_TOTAL", "0");
			}if(tmpResultMap.get("ONW_THR_NUM")==null){
				tmpResultMap.put("ONW_THR_NUM", "0");
			}
			
			
			//5��Ԥ�۵�Ѻ
			int premortgage_ot_total=0;
			int premortgage_ot_num=0;
			if(tmpResultMap.get("PRE_MORTGAGE_TOTAL")==null){
				tmpResultMap.put("PRE_MORTGAGE_TOTAL", "0");
			}if(tmpResultMap.get("PRE_MORTGAGE_NUM")==null){
				tmpResultMap.put("PRE_MORTGAGE_NUM", "0");
			}
//			tmpResultMap.put("PRE_MORTGAGE_TOTAL",premortgage_ot_total);
//			tmpResultMap.put("PRE_MORTGAGE_NUM",premortgage_ot_num);
			//6�����۷���Ѻ
			int mortgage_ot_total=0;
			int mortgage_ot_num=0;
			for(Map<String,Object> tmpMap :dbList){
				if(tmpMap.get("DEPT_ID") == null){
					continue;
				}
				if(reg_dept_id.equals(this.TOTAL)){
					//���е�Ѻ����� ��ҵ��
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
			//���û������  �������
//			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
//				tmpResultMap.put("ONW_THR_TOTAL", "0");
//			}if(tmpResultMap.get("ONW_THR_NUM")==null){
//				tmpResultMap.put("ONW_THR_NUM", "0");
//			}
			//7�����������    --�漰���ҵ��   ��Ҫÿ��������
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
//			//���û������  �������
//			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
//				tmpResultMap.put("ONW_THR_TOTAL", "0");
//			}if(tmpResultMap.get("ONW_THR_NUM")==null){
//				tmpResultMap.put("ONW_THR_NUM", "0");
//			}
			//8�����ӷ���֤
			
			//���û������  �������
			if(tmpResultMap.get("ANJU_TOTAL")==null){
				tmpResultMap.put("ANJU_TOTAL", "0");
			}if(tmpResultMap.get("ANJU_TNUM")==null){
				tmpResultMap.put("ANJU_TNUM", "0");
			}
			//9��Ԥ�ۺ�ͬ����
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
			//���û������  �������
			if(tmpResultMap.get("PRE_SALE_TOTAL")==null){
				tmpResultMap.put("PRE_SALE_TOTAL", "0");
			}if(tmpResultMap.get("PRE_SALE_NUM")==null){
				tmpResultMap.put("PRE_SALE_NUM", "0");
			}
			
			//10����Ȩ�ۺϰ���
			int est_com_total=0;
			int est_com_num=0;
			if(tmpResultMap.get("EST_COM_TOTAL")==null){
				tmpResultMap.put("EST_COM_TOTAL", "0");
			}if(tmpResultMap.get("EST_COM_NUM")==null){
				tmpResultMap.put("EST_COM_NUM", "0");
			}
//			tmpResultMap.put("EST_COM_TOTAL",est_com_total);
//			tmpResultMap.put("EST_COM_NUM",est_com_num);
			//11������Ǽ�
			int two_reg_total=0;
			int two_reg_num=0;
			if(tmpResultMap.get("TWO_REG_TOTAL")==null){
				tmpResultMap.put("TWO_REG_TOTAL", "0");
			}if(tmpResultMap.get("TWO_REG_NUM")==null){
				tmpResultMap.put("TWO_REG_NUM", "0");
			}
//			tmpResultMap.put("TWO_REG_TOTAL",two_reg_total);
//			tmpResultMap.put("TWO_REG_NUM",two_reg_num);
			//12�������Ǽ�
			int oter_total=0;
			int other_num=0;
			if(tmpResultMap.get("OTER_TOTAL")==null){
				tmpResultMap.put("OTER_TOTAL", "0");
			}if(tmpResultMap.get("OTHER_NUM")==null){
				tmpResultMap.put("OTHER_NUM", "0");
			}
//			tmpResultMap.put("OTER_TOTAL",oter_total);
//			tmpResultMap.put("OTHER_NUM",other_num);
			//13���ϼ�
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
	 * getCurMonthStatistics:(��������ȡϸ����ÿ���  ���ͳ�Ʊ��� ����).
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getDaydetailStatistics(Map<String,Object> paraMap){
		List<Map<String,Object>> dbList  = null;
		List<Map<String,Object>> resultList  = new ArrayList<Map<String,Object>>();
		String whereSql ="where to_char(CHE_DATE, 'YYYY-MM') = to_char(add_months(SYSDATE, 0), 'YYYY-MM')";
		String conditionSql = "";
		//û�в���ʱ������   ɸѡ����
		if(paraMap.get("dept_id")!=null){
//			String start_date = paraMap.get("start_date").toString();
//			String end_date = paraMap.get("end_date").toString();
			String dept_id = paraMap.get("dept_id").toString().trim();
			if(!dept_id.equals("")){
				conditionSql = " and dept_id=:dept_id";
			}
			//������û�ID  ���ǰ��û�ɸѡ
		}else if(paraMap.get("user_id")!=null){
//			String start_date = paraMap.get("start_date").toString();
//			String end_date = paraMap.get("end_date").toString();
			String user_id = paraMap.get("user_id").toString().trim();
			if(!user_id.equals("")){
				conditionSql = " and CHE_PERSON=:user_id";
			}
		//������ز���������Ϊ��
		}else if(paraMap.get("location")!=null){
			String location = paraMap.get("location").toString().trim();
			if(!location.equals("")){
				conditionSql = " and LOCATION_REG_UNIT=:location";
			}
		}
		
		//���ʱ�䲻Ϊ�ղŽ����������
		if(paraMap.get("start_date")!=null){
			String start_date = paraMap.get("start_date").toString().trim();
			String end_date = paraMap.get("end_date").toString().trim();
			if(!start_date.equals("") && !end_date.equals("")){
				//�����ʼʱ����ڽ���ʱ���򻥻�ʱ��
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
			}else if(start_date.equals("") && !end_date.equals("")){			//�������ڵ��µ�����
				whereSql=" where che_date between trunc(to_date('"+end_date+"','yyyy-mm-dd'),'MONTH')  and to_date('"+end_date+"','yyyy-mm-dd ')";
			}
		}
		try {
			dbList = checkBaseDao.queryMapListByKey("Qualityinspection.getDaydetailStatistics",whereSql+conditionSql, paraMap);
//			if(dbList.size()==0){
//				return null;
//			}
			
			Map tmpResultMap  = null;
			//�Ǽǵ�  ҵ��  ƴ����
//			//1���շ��� ��
//			tmpResultMap = getCurMonthStatisticsMap(dbList,this.REG_REC_DIS);
//			tmpResultMap.put("DEPT_NAME", "�շ��Ŀ�");
//			resultList.add(tmpResultMap);
//			
			
			
			//1��ѭ��ɸѡÿ��ҵ�� �������� Ĭ��Ϊ��ǰ������
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
				}else if(!endObj .equals("")){					//ֻ���G����   ȡ�������ڵ�ǰ�µ�һ�쵽��������
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
			//��ȡ��ǰ���������� ---Ĭ��Ϊ��ǰ������
			
			for(Date date: datelist){
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
				String dateStr = sim.format(date);
				tmpResultMap = getDaydetailStatisticsMap(dbList,dateStr);		//ѭ����ȡÿ������
				tmpResultMap.put("DAY", dateStr);					//���ϵ�ǰ������
				resultList.add(tmpResultMap);
			}
			
			//2���ϼ�
			tmpResultMap = getDaydetailStatisticsMap(dbList,this.TOTAL);
			tmpResultMap.put("DAY", "�ϼ�");
			resultList.add(tmpResultMap);
		} catch (Exception e) {
			LogUtil.error("ͳ�Ʋ�ѯ��ǰ�����ݳ�����"+e.getMessage());
		}
		
		//�����ͳ�Ƽ���ֵ 
//		int tmp_target_num = 0;
//		for(int i=0;i<resultList.size()-2;i++){
//			Map tmpMap = resultList.get(i);
//			String tmptargetnumstr = tmpMap.get("TARGET_NUM").toString();
//			
//			tmp_target_num+=Integer.valueOf(tmptargetnumstr);
//		}
		//���һ���Ǻϼ�  ��ֵ
//		resultList.get(resultList.size()-1).put("TARGET_NUM", tmp_target_num);
		//ÿ���µ���Ŀ����
		if(paraMap.get("start_date")!=null){
			
			String start_date = paraMap.get("start_date").toString().trim();
			
			resultList.get(resultList.size()-1).put("TARGET_NUM", getMonthtarget(start_date));
		}
		return resultList;
	}
	
	/**
	 * 
	 * getDaydetailStatisticsMap:(��ȡ���ż������   ��ͳ��������ɸѡ�����ϸ�ʽ������).
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
			//1�������������� �� �����
			//���û������  �������
			if(tmpResultMap.get("WINDOW_ALL_TOTAL")==null){
				tmpResultMap.put("WINDOW_ALL_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("WINDOW_ALL_ERROR")==null){
				tmpResultMap.put("WINDOW_ALL_ERROR", "&nbsp;");
			}
			
			//2����ʼ�Ǽ� ���� �� �����
			//���û������  �������
			if(tmpResultMap.get("ONW_INIT_TOTAL")==null){
				tmpResultMap.put("ONW_INIT_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("ONW_INIT_NUM")==null){
				tmpResultMap.put("ONW_INIT_NUM", "&nbsp;");
			}
			
			//3������ת������ �� �����
			//���û������  �������
			if(tmpResultMap.get("ONW_SEC_TOTAL")==null){
				tmpResultMap.put("ONW_SEC_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("ONW_SEC_NUM")==null){
				tmpResultMap.put("ONW_SEC_NUM", "&nbsp;");
			}
			//4������ת������ �� �����
			//���û������  �������
			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
				tmpResultMap.put("ONW_THR_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("ONW_THR_NUM")==null){
				tmpResultMap.put("ONW_THR_NUM", "&nbsp;");
			}
			
			
			//5��Ԥ�۵�Ѻ
			int premortgage_ot_total=0;
			int premortgage_ot_num=0;
			if(tmpResultMap.get("PRE_MORTGAGE_TOTAL")==null){
				tmpResultMap.put("PRE_MORTGAGE_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("PRE_MORTGAGE_NUM")==null){
				tmpResultMap.put("PRE_MORTGAGE_NUM", "&nbsp;");
			}
//			tmpResultMap.put("PRE_MORTGAGE_TOTAL",premortgage_ot_total);
//			tmpResultMap.put("PRE_MORTGAGE_NUM",premortgage_ot_num);
			//6�����۷���Ѻ
			int mortgage_ot_total=0;
			int mortgage_ot_num=0;
			if(tmpResultMap.get("MORTGAGE_TOTAL")==null){
				tmpResultMap.put("MORTGAGE_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("MORTGAGE_NUM")==null){
				tmpResultMap.put("MORTGAGE_NUM", "&nbsp;");
			}
//			tmpResultMap.put("MORTGAGE_TOTAL",mortgage_ot_total);
//			tmpResultMap.put("MORTGAGE_NUM",mortgage_ot_num);
			//���û������  �������
//			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
//				tmpResultMap.put("ONW_THR_TOTAL", "&nbsp;");
//			}if(tmpResultMap.get("ONW_THR_NUM")==null){
//				tmpResultMap.put("ONW_THR_NUM", "&nbsp;");
//			}
			//7�����������    --�漰���ҵ��   ��Ҫÿ��������
			int change_ot_total=0;
			int change_ot_num=0;
			if(tmpResultMap.get("CHANGE_OT_TOTAL")==null){
				tmpResultMap.put("CHANGE_OT_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("CHANGE_OT_NUM")==null){
				tmpResultMap.put("CHANGE_OT_NUM", "&nbsp;");
			}
//			tmpResultMap.put("CHANGE_OT_TOTAL",change_ot_total);
//			tmpResultMap.put("CHANGE_OT_NUM",change_ot_num);
//			//���û������  �������
//			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
//				tmpResultMap.put("ONW_THR_TOTAL", "&nbsp;");
//			}if(tmpResultMap.get("ONW_THR_NUM")==null){
//				tmpResultMap.put("ONW_THR_NUM", "&nbsp;");
//			}
			//8�����ӷ���֤
			
			//���û������  �������
			if(tmpResultMap.get("ANJU_TOTAL")==null){
				tmpResultMap.put("ANJU_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("ANJU_TNUM")==null){
				tmpResultMap.put("ANJU_TNUM", "&nbsp;");
			}
			//9��Ԥ�ۺ�ͬ����
			//���û������  �������
			if(tmpResultMap.get("PRE_SALE_TOTAL")==null){
				tmpResultMap.put("PRE_SALE_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("PRE_SALE_NUM")==null){
				tmpResultMap.put("PRE_SALE_NUM", "&nbsp;");
			}
			
			//10����Ȩ�ۺϰ���
			int est_com_total=0;
			int est_com_num=0;
			if(tmpResultMap.get("EST_COM_TOTAL")==null){
				tmpResultMap.put("EST_COM_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("EST_COM_NUM")==null){
				tmpResultMap.put("EST_COM_NUM", "&nbsp;");
			}
//			tmpResultMap.put("EST_COM_TOTAL",est_com_total);
//			tmpResultMap.put("EST_COM_NUM",est_com_num);
			//11������Ǽ�
			int two_reg_total=0;
			int two_reg_num=0;
			if(tmpResultMap.get("TWO_REG_TOTAL")==null){
				tmpResultMap.put("TWO_REG_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("TWO_REG_NUM")==null){
				tmpResultMap.put("TWO_REG_NUM", "&nbsp;");
			}
//			tmpResultMap.put("TWO_REG_TOTAL",two_reg_total);
//			tmpResultMap.put("TWO_REG_NUM",two_reg_num);
			//12�������Ǽ�
			int oter_total=0;
			int other_num=0;
			if(tmpResultMap.get("OTER_TOTAL")==null){
				tmpResultMap.put("OTER_TOTAL", "&nbsp;");
			}if(tmpResultMap.get("OTHER_NUM")==null){
				tmpResultMap.put("OTHER_NUM", "&nbsp;");
			}
//			tmpResultMap.put("OTER_TOTAL",oter_total);
//			tmpResultMap.put("OTHER_NUM",other_num);
			//13���ϼ�
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
			//1�������������� �� �����
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
			//���û������  �������
			if(tmpResultMap.get("WINDOW_ALL_TOTAL")==null){
				tmpResultMap.put("WINDOW_ALL_TOTAL", "0");
			}if(tmpResultMap.get("WINDOW_ALL_ERROR")==null){
				tmpResultMap.put("WINDOW_ALL_ERROR", "0");
			}
			
			//2����ʼ�Ǽ� ���� �� �����
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
			//���û������  �������
			if(tmpResultMap.get("ONW_INIT_TOTAL")==null){
				tmpResultMap.put("ONW_INIT_TOTAL", "0");
			}if(tmpResultMap.get("ONW_INIT_NUM")==null){
				tmpResultMap.put("ONW_INIT_NUM", "0");
			}
			
			//3������ת������ �� �����
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
			//���û������  �������
			if(tmpResultMap.get("ONW_SEC_TOTAL")==null){
				tmpResultMap.put("ONW_SEC_TOTAL", "0");
			}if(tmpResultMap.get("ONW_SEC_NUM")==null){
				tmpResultMap.put("ONW_SEC_NUM", "0");
			}
			//4������ת������ �� �����
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
			//���û������  �������
			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
				tmpResultMap.put("ONW_THR_TOTAL", "0");
			}if(tmpResultMap.get("ONW_THR_NUM")==null){
				tmpResultMap.put("ONW_THR_NUM", "0");
			}
			
			
			//5��Ԥ�۵�Ѻ
			int premortgage_ot_total=0;
			int premortgage_ot_num=0;
			if(tmpResultMap.get("PRE_MORTGAGE_TOTAL")==null){
				tmpResultMap.put("PRE_MORTGAGE_TOTAL", "0");
			}if(tmpResultMap.get("PRE_MORTGAGE_NUM")==null){
				tmpResultMap.put("PRE_MORTGAGE_NUM", "0");
			}
//			tmpResultMap.put("PRE_MORTGAGE_TOTAL",premortgage_ot_total);
//			tmpResultMap.put("PRE_MORTGAGE_NUM",premortgage_ot_num);
			//6�����۷���Ѻ
			int mortgage_ot_total=0;
			int mortgage_ot_num=0;
			for(Map<String,Object> tmpMap :dbList){
				if(dateStr.equals(this.TOTAL)){
					//���е�Ѻ����� ��ҵ��
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
			//���û������  �������
//			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
//				tmpResultMap.put("ONW_THR_TOTAL", "0");
//			}if(tmpResultMap.get("ONW_THR_NUM")==null){
//				tmpResultMap.put("ONW_THR_NUM", "0");
//			}
			//7�����������    --�漰���ҵ��   ��Ҫÿ��������
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
//			//���û������  �������
//			if(tmpResultMap.get("ONW_THR_TOTAL")==null){
//				tmpResultMap.put("ONW_THR_TOTAL", "0");
//			}if(tmpResultMap.get("ONW_THR_NUM")==null){
//				tmpResultMap.put("ONW_THR_NUM", "0");
//			}
			//8�����ӷ���֤
			
			//���û������  �������
			if(tmpResultMap.get("ANJU_TOTAL")==null){
				tmpResultMap.put("ANJU_TOTAL", "0");
			}if(tmpResultMap.get("ANJU_TNUM")==null){
				tmpResultMap.put("ANJU_TNUM", "0");
			}
			//9��Ԥ�ۺ�ͬ����
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
			//���û������  �������
			if(tmpResultMap.get("PRE_SALE_TOTAL")==null){
				tmpResultMap.put("PRE_SALE_TOTAL", "0");
			}if(tmpResultMap.get("PRE_SALE_NUM")==null){
				tmpResultMap.put("PRE_SALE_NUM", "0");
			}
			
			//10����Ȩ�ۺϰ���
			int est_com_total=0;
			int est_com_num=0;
			if(tmpResultMap.get("EST_COM_TOTAL")==null){
				tmpResultMap.put("EST_COM_TOTAL", "0");
			}if(tmpResultMap.get("EST_COM_NUM")==null){
				tmpResultMap.put("EST_COM_NUM", "0");
			}
//			tmpResultMap.put("EST_COM_TOTAL",est_com_total);
//			tmpResultMap.put("EST_COM_NUM",est_com_num);
			//11������Ǽ�
			int two_reg_total=0;
			int two_reg_num=0;
			if(tmpResultMap.get("TWO_REG_TOTAL")==null){
				tmpResultMap.put("TWO_REG_TOTAL", "0");
			}if(tmpResultMap.get("TWO_REG_NUM")==null){
				tmpResultMap.put("TWO_REG_NUM", "0");
			}
//			tmpResultMap.put("TWO_REG_TOTAL",two_reg_total);
//			tmpResultMap.put("TWO_REG_NUM",two_reg_num);
			//12�������Ǽ�
			int oter_total=0;
			int other_num=0;
			if(tmpResultMap.get("OTER_TOTAL")==null){
				tmpResultMap.put("OTER_TOTAL", "0");
			}if(tmpResultMap.get("OTHER_NUM")==null){
				tmpResultMap.put("OTHER_NUM", "0");
			}
//			tmpResultMap.put("OTER_TOTAL",oter_total);
//			tmpResultMap.put("OTHER_NUM",other_num);
			//13���ϼ�
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
	 * getRemindersBusiness:(��ȡ��Ҫ�߰��ҵ��).
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
			//�����׼ʱ���  �а���ͬʱ����    �����ݿ������޷��������  �л�������     ��Ҫͨ���ֶ��߼��ж�
			if(!person_id.equals("") && !app_date.equals("")){
				//to do δʵ��      Ҫô�Ȳ���ں�׼�����ڵ�   Ҫô�Ȳ���а��˵�
				businessWheresql=" and wi.WI_STATE='1' and   wi.PARTI_ID=:person_id";
			}//ɸѡ�û�
			else if(!person_id.equals("")){
				
				//if(!person_id.equals("")){
					businessWheresql=" and wi.WI_STATE='1' and   wi.PARTI_ID=:person_id";
				//}
				
			}
			//ɸѡ��׼ʱ��
			else if(!app_date.equals("")){
				
				//if(!app_date.equals("")){
					businessWheresql="  and  wi.WI_NAME ='��׼'  where to_char(wi.END_DATE,'yyyy-mm-dd')=to_char(to_date(:app_date,'yyyy-mm-dd'),'yyyy-mm-dd')";
				//}
				
			}
			//���û�га���  �ͺ�׼ʱ��     ��Ҫ���Ϻ͹������  ��������   ��Ϊ��Ҫ�õ��л�����������    ͨ������  wi.state=1��ɸѡ
			if(!businessWheresql.contains("and")){
				businessWheresql=" and wi.WI_STATE='1'";
			}
			
			//����������ɸѡ
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
			//ɸѡ�����Ǽ�����
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
			//ɸѡ�������ز�������
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
				//ɸѡ����  ҵ������  ģ����ѯ
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
				
				//ɸѡ����  ��컷��  ģ����ѯ
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
				
				//ɸѡ����  ��컷��  ģ����ѯ
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
		}//ɸѡ����  if end 
		
	
		
		
		//1��ҵ�������л�ȡ���� ��ҵ��
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
		
		//2��ͨ��ҵ������������ʵ��ID  ѭ�����ӿڻ�ȡ���̱�������    
//		Long tmp_proc_id = null;
//		ProcessInst tmpProcessInst = null;
//		Map<String,Object> tmpResultMap = null;		//���ص�Map   ����ѭ����ӵ� resultMapList
//		Date curDate = new Date();					//��ǰ����   �����ж�  ��ǰ�����Ƿ���������� 
//		int curMonth = curDate.getMonth();			//��ǰ�·�   �����ж�  ��ǰ�����Ƿ���������� 	
//		int curYear = curDate.getYear();			//��ǰ���   �����ж�  ��ǰ�����Ƿ���������� 
//		ParticipantDelegate pd =  (ParticipantDelegate) WorkflowApplicationContext.getBean("participantDelegate");  //��ȡ���̲�����delegegate
//		for(BusinessMain tmpBusinessMain :businessMainList){
//			 tmp_proc_id = Long.valueOf(tmpBusinessMain.getProc_id());
//			 
//			 try {
//				 tmpProcessInst = workflowClientFacade.getWorkflowClient().getProcessInstanceManager().getProcessInstById(tmp_proc_id);
//		//4��������ʱ��ɸѡ�����µ�����		
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
//		//6����ȡ��ǰ������id
//						List workitemList =	workflowClientFacade.getWorkflowClient().getWorkItemManager().getActivatedWorkItemsOfProcessInst(tmpProcessInst.getProcId());
//						if(workitemList.size()>0){
//							Object tmpObject =workitemList.get(0);
//							WorkItem workitem = (WorkItem)tmpObject;
//							//�鿴������ϸ��Ҫ���ֶ�
//							tmpResultMap.put("activName", workitem.getActivName());
//							tmpResultMap.put("wiId",workitem.getWiId());
//							tmpResultMap.put("url", workitem.getUrlSpecify());
//							tmpResultMap.put("procdefId", workitem.getProcdefId());
//							tmpResultMap.put("activdefId",workitem.getActivdefId());
//							tmpResultMap.put("procId", workitem.getProcId());
//							tmpResultMap.put("node_duration", workitem.getDuration());			//������ʱ��
//							tmpResultMap.put("node_remain",workitem.getRemainDays());			//����ʣ��ʱ��
//							
//							String node_start_date = "";
//							if(workitem.getStartDate()!=null){
//								node_start_date = org.apache.tools.ant.util.DateUtils.format(workitem.getStartDate(), "yyyy-MM-dd");
//							}
//							tmpResultMap.put("node_start_date", node_start_date);				//���ڿ�ʼʱ��
//							
//							//��ȡ�������� 
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
//							//�޻�ڵ��  ��ʾ�Ѿ�����ҵ��    ----��ʱҵ���������޿ɲ�ѯ��ǰҵ�� �Ƿ��Ѿ�����   ֻ�ܴ����̽ӿ��л�ȡ
//							continue;
//						}	
//						
//						
//						
//						//���뷵�ؽ��
//						 resultMapList.add(tmpResultMap);
////						 //����  ֻ����һ������
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
	 * (��ȡ��Ҫ�߰��ҵ��).
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
			//�����׼ʱ���  �а���ͬʱ����    �����ݿ������޷��������  �л�������     ��Ҫͨ���ֶ��߼��ж�
			if(!person_id.equals("") && !app_date.equals("")){
				//to do δʵ��      Ҫô�Ȳ���ں�׼�����ڵ�   Ҫô�Ȳ���а��˵�
				businessWheresql=" and wi.WI_STATE='1' and   wi.PARTI_ID=:person_id";
			}//ɸѡ�û�
			else if(!person_id.equals("")){
				
				//if(!person_id.equals("")){
				businessWheresql=" and wi.WI_STATE='1' and   wi.PARTI_ID=:person_id";
				//}
				
			}
			//ɸѡ��׼ʱ��
			else if(!app_date.equals("")){
				
				//if(!app_date.equals("")){
				businessWheresql="  and  wi.WI_NAME ='��׼'  where to_char(wi.END_DATE,'yyyy-mm-dd')=to_char(to_date(:app_date,'yyyy-mm-dd'),'yyyy-mm-dd')";
				//}
				
			}
			//���û�га���  �ͺ�׼ʱ��     ��Ҫ���Ϻ͹������  ��������   ��Ϊ��Ҫ�õ��л�����������    ͨ������  wi.state=1��ɸѡ
			if(!businessWheresql.contains("and")){
				businessWheresql=" and wi.WI_STATE='1'";
			}
			
			//����������ɸѡ
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
			//ɸѡ�����Ǽ�����
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
			//ɸѡ�������ز�������
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
			//ɸѡ����  ҵ������  ģ����ѯ
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
			
			//ɸѡ����  ��컷��  ģ����ѯ
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
			
			//ɸѡ����  ��컷��  ģ����ѯ
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
		}//ɸѡ����  if end 
		
		
		
		
		//1��ҵ�������л�ȡ���� ��ҵ��
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
	 * reminde:(����  ����һ����Ϣ������ ��������Ϣ���ʼ�).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @throws GeneralFailureException 
	 * @since JDK 1.6
	 */
	public Map<String,Object> reminde(Map paraMap) throws GeneralFailureException{
		//1����������APIʵ�� 
		SendMessage sendMsg = new SendMessage(); 
		String userId = paraMap.get("user_id").toString();
		String message = paraMap.get("message").toString();
		String message_type = paraMap.get("message_type").toString();
		String bus_id = paraMap.get("bus_id").toString();
		String is_re = paraMap.get("is_re").toString();
		
		//sendMsg.setMobileTel("13128944811");
		//2�������Ҫ��ʱ���ͣ����ö��Ŷ�ʱ���͵ķ���ʱ�� 
		//sendMsg.setSendDateTime("2007-01-04 03:25:01"); 
		//3�����Ͷ��Ÿ�����û� 
		//String sendIds=sendMsg.sendMessage2("13128944811", "test����"); 
		//3�����Ͷ��Ÿ�һ���û� 
		//String sendId=sendMsg.sendMessage2("13128944811", "������Ҫ��ʲô�ģ�"); 
		//4����ѯ���ŷ���״̬��-1������ʧ�ܣ�1�����ͳɹ���0�����ڷ��͡� 
		int status = 0;
		if(message_type.equals("message")){
			String sendId = sendMsg.sendMessageToUsers2(userId, message);
			status=sendMsg.getSendMessageResult(sendId); 
		}else{
			
		}
		
		//ֻҪ������ʧ�� �򱣴����ݽ����ݿ� 1�ɹ�  0 ������  -1 ����ʧ��
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
		
//		System.out.println("���Ÿ���״̬��"+status);
		//5����ѯ���Ž����˵Ļظ����� 
//		String receive=sendMsg.getResponseMessage(sendId);
//		System.out.println("���Żظ����ݣ�"+receive);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result", "success");
		return resultMap;
	}
	
	/**
	 * 
	 * getUrgeStatistics:(ͳ�ƶ�������).
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
		//�������map  ��Ϊ��  �����Sql����װ
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
				 
                //ɸѡ�������˰������������		  
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
				
				//ɸѡ��������
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
				
				//ɸѡ��׼����
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
		LogUtil.error("��ȡ����ͳ����Ϣ����"+e.getMessage());
	}
		return resultList;
	}
	
	/**
	 * 
	 * changeIserrorstate:(�޸��Ƿ��ǵǼ���״̬).
	 *
	 * @author Joyon
	 * @param bus_id  �����ҵ���ҵ��ID
	 * @param type	   �Ƿ�ѡ��  checked/unchecked	
	 * @param proc_id ���μ�����̵�ID
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
			LogUtil.error("QualityinspectionFacade.changeIserrorstate�����Ƿ��ǲ���� ״̬����");
		}
	
	}
	/**
	 * 
	 * getCheckBaseBybusidandcheckprocid:(ͨ�������ҵ���ҵ��Id   �ͼ�����̵�����ʵ��ID��ȡΩһ�ļ�������Ϣ). 
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
	 * ͨ��������̵�����ʵ��ID��ȡ�ļ�������Ϣ 
	 *
	 * @author Joyon
	 * @param proc_id �������  ������ʵ��id
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
	 * ���³�������Ϣ��
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
	 * ��ȡĳһ���µĹ����� ����
	 *
	 * @author Joyon
	 * @param dateStr yyyy-MM
	 * @return
	 * @since JDK 1.6
	 */
	private int getMonthWorkdaycount(String dateStr){
//		if(!dateStr.matches("\\d{4}-\\d{2}")){
//			LogUtil.error("qualityinspectionFacade.getMonthWorkdaycount �����dateStr��ʽ����:"+dateStr+" ��ʽΪyyyy-mm");
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


