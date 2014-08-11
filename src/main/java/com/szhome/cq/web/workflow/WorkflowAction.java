package com.szhome.cq.web.workflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.plan.commons.Row;
import com.plan.commons.RowImpl;
import com.plan.exceptions.GeneralException;
import com.plan.util.json.JsonParserJsonlibImpl;
import com.szhome.commons.database.DataRecord;
import com.szhome.commons.exception.GeneralFailureException;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IWorkflowFacade;
import com.szhome.cq.business.ResultMsg;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.ConOffice;
import com.szhome.cq.domain.model.SpeDelay;
import com.szhome.cq.domain.model.SpeRefundInfo;
import com.szhome.cq.domain.model.SpeRejectionInfo;
import com.szhome.cq.domain.model.SpeRespiteInfo;
import com.szhome.cq.domain.model.SpeSuspendInfo;
import com.szhome.cq.utils.Constants;
import com.szhome.cq.utils.JsonUtil;
import com.szhome.cq.utils.Util;
import com.szhome.cq.utils.WbfConstants;
import com.szhome.cq.web.JsonResult;
import com.szhome.process.assign.AssignParticipant;
import com.szhome.process.assign.WorkflowUser;
import com.szhome.process.client.WorkflowClient;
import com.szhome.process.client.WorkflowClientFacade;
import com.szhome.process.client.WorkflowClientFactory;
import com.szhome.process.entity.CtrlData;
import com.szhome.process.entity.Procdef;
import com.szhome.process.entity.ProcessInst;
import com.szhome.process.entity.ProcessTask;
import com.szhome.process.entity.Transition;
import com.szhome.process.entity.WorkItem;
import com.szhome.process.exceptions.ProcessException;
import com.szhome.process.service.ProcessInstanceManager;
import com.szhome.process.service.WorkItemManager;

public class WorkflowAction extends BaseDelegate{
	
//	/** 工作流客户端统一接口 */
	private WorkflowClientFacade workflowClientFacade = WorkflowClientFactory.getWorkflowClientFacade();
//	/** 工作流客户端接口*/
	private WorkflowClient workflowClient = workflowClientFacade.getWorkflowClient();
	
	IWorkflowFacade wfcade = FacadeFactory.getWorkflowFacade();
	private Long procdefId;
	private String nodeid;
	/**
	 * 
	 * getworkflowList:(获得待办事项). <br/>
	 *
	 * @author xuzz
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public String getworkflowList(Row row)  throws Exception {
		List<Row> rows=new ArrayList<Row>();
		Row retunRow=new RowImpl();
		try {
		List taskList = wfcade.getWorkflowList(this.getOperatorInfo()); // 调用工作流引擎获取任务列表 
			Object[] taskList1=null;
		 ProcessTask pt=new ProcessTask();
		retunRow.put("total", taskList.size());
		String s=null;
		for(int i=0;i<taskList.size();i++){
			row=new RowImpl();
			pt=(ProcessTask)taskList.get(i);
			row.put("activdefId", pt.getActivdefId());
			row.put("procName", pt.getProcName());
			row.put("partiId", pt.getPartiId());
			row.put("partiName", pt.getPartiName());
			row.put("partiType", pt.getPartiType());
			row.put("partiState", pt.getPartiState());
			row.put("partiSource", pt.getPartiSource());
			row.put("wiState", pt.getWiState());
			row.put("urlType", pt.getUrlType());
			row.put("dayType", pt.getDayType());
			row.put("startDate", pt.getStartDate());
			row.put("wiId", pt.getWiId());
			row.put("wiName", pt.getWiName());
			row.put("wiType", pt.getWiType());
			row.put("procId", pt.getProcId());
			row.put("activId", pt.getActivId());
			row.put("activName", pt.getActivName());
			row.put("procdefId", pt.getProcdefId());
			//row.put("procdefId",FacadeFactory.getRegisterFacade().getProcid(pt.getProcdefId().toString()));
			row.put("procdefName", pt.getProcdefName());
			row.put("moduleId", pt.getModuleId());
			s+=pt.getWiId()+",";
			rows.add(row);
		}
		System.out.println(s);
		retunRow.put("rows", rows);
		}
		catch(Exception e){				    	
		e.printStackTrace();
		}
		String str = "";
		try {
			str = new JsonParserJsonlibImpl().toJson(retunRow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return setActionJsonObject(str);
	}
	/**
	 * 
	 * getParticipantsOfWorkItem:(获取下一环节参与者). <br/>
	 *
	 * @author xuzz
	 * @param row
	 * @return
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public String getParticipantsOfWorkItem(Row row) throws GeneralException
	{
		//质量抽检  负责人审批环节  动态设环节人时  回退人也变了   为了解决回退时不能正确选择人
		String btn_name = row.getString("btn_name");
		if(btn_name==null || btn_name.equals("")){
			btn_name = null;
		}
		Long procdefId = row.getLong("procdefId");
		String activdefId = row.getString("activdefId");
		Long procId = row.getLong("procId");
		List<Row> listrow = wfcade.getParticipantsOfWorkItem(this.getOperatorInfo(),procdefId ,activdefId,procId,btn_name);
		return JsonUtil.object2json(listrow);
	}
	
	
	/**
	 * 
	 * getsubmitbtn:(动态加载提交按钮). <br/>
	 *
	 * @author xuzz
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public String getsubmitbtn(Row row){
		List Transitionslist=null;
		List<Row> listrow=new ArrayList<Row>();
		Long procdefId = row.getLong("procdefId");
		String activdefId = row.getString("activdefId");
		Long procId = row.getLong("procId");
		Row row1=null;
		try{			
			WorkflowUser user = wfcade.getWorkflowUser(this.getOperatorInfo());
			workflowClientFacade.setCurrentWorkflowUser(user);
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
		return JsonUtil.object2json(listrow);
	}
	
	
	/**
	 * 
	 * queryAllSysModule:(获取所有系统模块). <br/>
	 *
	 * @author xuzz
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public String queryAllSysModule(Row row)
	{
		List list=null;	
		try{
			workflowClientFacade.setCurrentWorkflowUser(wfcade.getWorkflowUser(this.getOperatorInfo()));
			list= workflowClient.getSysModuleManager().queryAllSysModule();
		}
		catch(Exception e){
		}
		return JsonUtil.object2json(list);
	}
	
	
	public String  getFormUrl(Row row){
		procdefId = row.getLong("procdefId");
		nodeid = row.getString("nodeid");
		List<ConOffice> listcoffice = new ArrayList<ConOffice>();
		Map map=new HashMap();
		map.put("bustype",FacadeFactory.getRegisterFacade().getProcid(procdefId.toString()));
		map.put("nodeid", nodeid);
		listcoffice = FacadeFactory.getWorkwindowFacade().getOfficeByNodeid(map);				
		return JsonUtil.object2json(listcoffice);
	}
	
		
	/**
	 * 
	 * getUrlSpecify:(获得表单url). <br/>
	 *
	 * @author xuzz
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public  String getUrlSpecify(Row row){
		WorkItem wi=new WorkItem();
		Long wiId = row.getLong("wiId");
		try {
			wi= WorkflowClientFactory.getWorkflowClientFacade().getWorkflowClient().getWorkItemManager().getWorkItemById(wiId);
		} catch (ProcessException e) {
			e.printStackTrace();
		}
		String url = wi.getUrlSpecify();
		//处理工作流配置的路径
		if(url.contains("spec-work-window")){
			LogUtil.debug("特殊"+url);
			url = url.replace("spec-work-window.action", "spec-work-window!home.action");
		}else{
			url = url.replace(".action", ".jsp");
			url = url.replace("/dxtx_re", "/dxtx_re/jsp");
		}
		return url;
	}
	/**
	 * 
	 * getworkflow:(获得业务办理数据). <br/>
	 *
	 * @author xuzz
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public String getworkflow(Row row1) {
		Row row=null;
		Long workItemId = row1.getLong("workItemId");
		List<Row> rows=new ArrayList<Row>();
		WorkItem wi=new WorkItem();
		try{
			wi= WorkflowClientFactory.getWorkflowClientFacade().getWorkflowClient().getWorkItemManager().getWorkItemById(workItemId);
			row.put("wiId", wi.getWiId());
			row.put("name", wi.getName());
			row.put("state", wi.getState());
			row.put("startDate", wi.getStartDate());
			row.put("urlSpecify", wi.getUrlSpecify());
			row.put("procId", wi.getProcId());
			row.put("procName", wi.getProcName());
			row.put("activId", wi.getActivId());
			row.put("activName", wi.getActivName());
			row.put("procdefId", wi.getProcdefId());
			row.put("procdefName", wi.getProcdefName());
			row.put("activdefId", wi.getActivdefId());
			row.put("activdefName", wi.getActivdefName());
			row.put("moduleId", wi.getModuleId());
			row.put("moduleName", wi.getModuleName());
			rows.add(row);
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		//System.out.println(rows);
		return JsonUtil.object2json(rows);
		
	}
	/**
	 * queryProcessdef:(获得所有流程定义). <br/>
	 * @author xuzz
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public String queryProcessdef(Row row){
		//queryAllSysModule();
		List<Row> rows=new ArrayList<Row>();
		Row taskRows=new RowImpl();	
		int count=0;
		try{
			List listquery= wfcade.getAllqueryProcessde();
			for(int j=0;j<listquery.size();j++)
			{
				Map map=(Map)listquery.get(j);
				List list= workflowClient.getProcessdefManager().queryProcessdef(map.get("module_id").toString());
				count+=list.size();
				Procdef pd=new Procdef();
				taskRows.put("total", count);
				for(int i=0;i<list.size();i++){
					row=new RowImpl();
					pd=(Procdef)list.get(i);
					row.put("procdefId", pd.getProcdefId());
					row.put("name", pd.getName());
					row.put("code",pd.getCode());
					row.put("versionsign", pd.getVersionsign());
					row.put("versiondesc", pd.getVersiondesc());
					row.put("status", pd.getStatus());
					row.put("xmlProcdef", pd.getXmlProcdef());
					row.put("createDate", pd.getCreateDate());
					row.put("lastEditDate", pd.getLastEditDate());
					row.put("author", pd.getAuthor());
					row.put("authorDept", pd.getAuthorDept());
					row.put("publisher", pd.getPublisher());
					row.put("publisherDept", pd.getPublisherDept());
					row.put("bizKey", pd.getBizKey());
					row.put("urlSpecify", pd.getUrlSpecify());
					row.put("urlSeeSpecify", pd.getUrlSeeSpecify());
					row.put("description", pd.getDescription());
					row.put("moduleId", pd.getModuleId());
					row.put("moduleName", pd.getModuleName());					
					rows.add(row);
				}
			}
			taskRows.put("rows", rows);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return JsonUtil.object2json(taskRows);
	}
	
	/**
	 * 
	 * getfinishworkflowList:(获取已办事项). <br/>
	 *
	 * @author xuzz
	 * @param row
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public Row getfinishworkflowList(Row row) throws Exception {
		List<Row> rows=new ArrayList<Row>();
		Row retunRow=new RowImpl();
		WorkflowUser user = wfcade.getWorkflowUser(this.getOperatorInfo());
		WorkflowClientFacade wfcfacade = WorkflowClientFactory.getWorkflowClientFacade();
		wfcfacade.setCurrentWorkflowUser(user);
		try {
			row.put("queryCondition", "1=1");
			row.put("maxPageItems", 20);
			Object[] taskList=null;
			//已办事项
			taskList =  WorkflowClientFactory.getWorkflowClientFacade().getWorkflowClient().getProcessTaskMgr().queryUserHistoryTasks4Paging(user, row);
			ProcessTask pt=new ProcessTask();
			if(taskList[0]!=null){
				List list=(List)taskList[0];
				retunRow.put("total", taskList[1]);
				for(int i=0;i<list.size();i++){
					row=new RowImpl();
					pt=(ProcessTask)list.get(i);
					row.put("procName", pt.getProcName());
					row.put("activId", pt.getActivId());
					row.put("activName", pt.getActivName());
					row.put("moduleName", pt.getModuleName());
					row.put("procdefName", pt.getProcdefName());
					row.put("moduleId", pt.getModuleId());				
					rows.add(row);
				}
				retunRow.put("rows", rows);
			}
		}catch(Exception e){
			LogUtil.error("获取已办事项ERROR:"+e.getMessage());
			//e.printStackTrace();
		}
		return retunRow;
	}

	

	
	/**
	 * 
	 * withdrawWorkItem:(将接收回来的任务退回到未接收前). <br/>
	 *
	 * @author xuzz
	 * @param row
	 * @throws GeneralFailureException
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public void withdrawWorkItem(Row row) throws GeneralException
	{
		//System.out.println(wiId+"wiId是这样的");
		// 设置工作流当前用户
		workflowClientFacade.setCurrentWorkflowUser( wfcade.getWorkflowUser(this.getOperatorInfo()));
		try
		{
			Long wiId = row.getLong("wiId");
			workflowClientFacade.withdrawWorkItem(wiId);
					}
		catch (ProcessException e)
		{
			
			e.printStackTrace();
			throw new GeneralException(e);
		}
	}
	
	
	/**
	 * 
	 * inceptWorkItem:(接收任务到自己名下). <br/>
	 *
	 * @author xuzz
	 * @param row
	 * @throws GeneralFailureException
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public void inceptWorkItem(Row row) throws GeneralFailureException, GeneralException
	{
		// 工作项ID
		//Long wiId = row.getLong(0);
		long wiId =70411;
		// 设置工作流当前用户
		workflowClientFacade.setCurrentWorkflowUser( wfcade.getWorkflowUser(this.getOperatorInfo()));
		try
		{
			workflowClientFacade.applyWorkItemToSelf(wiId);
			//System.out.println(wiId+"成功了————————————————————————————————————");
		}
		catch (ProcessException e)
		{
			e.printStackTrace();
			throw new GeneralFailureException(e);
		}
	}
	
	
	
	/**
	 * 
	 * WorkItemNextParamWithFacade:(测试完成工作项.提交). <br/>
	 *
	 * @author xuzz
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public String WorkItemNextParamWithFacade(Row row) {
		//Row row=null;
		Row resultRow=new RowImpl();
		//-------ajax---------
		try {
			WorkflowUser user =  wfcade.getWorkflowUser(this.getOperatorInfo());			
			WorkflowClientFacade wfcfacade = WorkflowClientFactory.getWorkflowClientFacade();
			//工作流客户端调用，必须设置当前使用流程的用户.
			wfcfacade.setCurrentWorkflowUser(user);						
			//如果下一阶段的任务来自于当前环节指定，在此设置下一阶段的参与者			
			AssignParticipant ap = null;	
			List aps = new ArrayList();
			String particpants = row.getString("particpants");
			JSONArray ja = JSONArray.fromObject(particpants);
			for(int i=0;i<ja.size();i++){
				ap = new AssignParticipant();
				JSONObject o = JSONObject.fromObject(ja.get(i));
				ap.setPartiId(o.getString("partiId"));
				ap.setPartiName(o.getString("partiName"));
				ap.setPartiType(o.getString("partiType"));
				aps.add(ap);
			}
			Map dataMap = new HashMap();				
			Long procId = row.getLong("procId");
			Long wiId = row.getLong("wiId");
			String state = row.getString("state");
			List<CtrlData> list= wfcfacade.getCtrlDataReturnCtrlDataList(procId);
			for(CtrlData ctr:list)
			{
				if(ctr.getDataType().equals("biz"))
				{
					dataMap.put(ctr.getXpath(), state);
				}
				else 
				{
					wfcfacade.setCtrlData(procId,ctr.getXpath(), aps);
				}
			}
			wfcfacade.setCtrlDataBath(procId, dataMap);	
			System.out.println("完成指定工作项.");
			if(aps.size()==0)
			{
				resultRow.put("sign","failed");
				return JsonUtil.object2json(resultRow);
			}
			//完成指定工作项.
			wfcfacade.finishWorkItem(wiId);
			resultRow.put("sign","success");
			System.out.println("完成了！");	
		} catch (Exception e) {
			resultRow.put("sign","failed");
			e.printStackTrace();
			System.out.println(e.getMessage()+"+++++++++++++++++++");
		}
		return JsonUtil.object2json(resultRow);
	}
	
	
	/**
	 * 
	 * WorkItemFinishedEndWithFacade:(测试完成工作项.归档). <br/>
	 *
	 * @author xuzz
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public String WorkItemFinishedEndWithFacade(Row row) {
		Map resultRow=new HashMap();
		Long wiId = row.getLong("wiId");
		try {			
			WorkflowUser user =  wfcade.getWorkflowUser(this.getOperatorInfo());			
			WorkflowClientFacade wfcfacade = WorkflowClientFactory.getWorkflowClientFacade();
			//工作流客户端调用，必须设置当前使用流程的用户.
			wfcfacade.setCurrentWorkflowUser(user);							
			wfcfacade.finishWorkItem(wiId);	
			resultRow.put("sign","success");
		} catch (Exception e) {
			resultRow.put("sign","failed");
			e.printStackTrace();
		}
		return JsonUtil.object2json(resultRow);
	}
	
	
	
	
	/**
	 * teminate:(终止流程实例). <br/>
	 * @author xuzz
	 * @param row
	 * @return
	 * @throws GeneralFailureException
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public boolean teminate(Row row) throws GeneralFailureException, GeneralException
	{
		WorkflowUser userInfo= wfcade.getWorkflowUser(this.getOperatorInfo());
		//System.out.println(procId+"procId++++++++++++++++++++++++++++++");
		WorkflowClientFacade workflowClientFacade = WorkflowClientFactory.getWorkflowClientFacade();
		WorkflowClient workflowClient = workflowClientFacade.getWorkflowClient();

		// 流程实例管理接口
		ProcessInstanceManager processInstanceManager = workflowClient.getProcessInstanceManager();
		boolean returnFlag = false;
		try
		{
			workflowClientFacade.setCurrentWorkflowUser(userInfo);
			Long procId = row.getLong("procId");
			// 终止实例
			processInstanceManager.terminateProcessInstance(Long.valueOf(procId.toString()));
			returnFlag = true;
		}
		catch (ProcessException e)
		{
			System.out.println(e.getMessage()+"我也报错了！！！！！！！！！！！");
			e.printStackTrace();
			throw new GeneralFailureException(e);
		}
		return returnFlag;
	}
	
	/**
	 *   	
	 * @param procId 流程实例ID
	 * @return 正在活动的工作项集（返回的结果集是一个WorkItem对象List）
	 * @throws GeneralFailureException
	 * 注意：
	 * 1、如果没有正在活动的工作项，将返回null，此处不做null处理留给上层调用者进行判断处理
	 */
	public String getWorkItemList(Row row) throws GeneralFailureException {
		WorkflowClientFacade workflowClientFacade = WorkflowClientFactory.getWorkflowClientFacade(); // 工作流客户端统一接口
		WorkflowClient workflowClient = workflowClientFacade.getWorkflowClient(); // 工作流客户端接口
		WorkItemManager workItemManager = workflowClient.getWorkItemManager(); // 工作项管理接口
		List list = null;
		Long procId = row.getLong("procId");
		try {
			list = (List) workItemManager.getActivatedWorkItemsOfProcessInst(procId);
		} catch (ProcessException e) {
			e.printStackTrace();
			throw new GeneralFailureException(e);
		}
		return JsonUtil.object2json(list);
	}

	/**
	 * 通过流程实例Id和新流程实例名来更改流程实例名称
	 * @param procId 流程实例ID
	 * @param title 流程实例名
	 * @throws GeneralFailureException
	 */
	public void updateProcessInstanceName(Row row) throws GeneralFailureException {
		ProcessInstanceManager processInstanceManager = workflowClient.getProcessInstanceManager(); // 流程实例管理接口
		Long procId = row.getLong("procId");
		String title = row.getString("title");
		try {
			
			// 通过流程实例Id和新流程实例名来更改流程实例名称
			processInstanceManager.updateProcessInstanceName(procId,title);
		} catch (ProcessException e) {
			//System.out.println("我抛出了异常"+e.getMessage());
			e.printStackTrace();
			throw new GeneralFailureException(e);
		}
	}
	/**
	 * createAndStartProcessInstance:(根据流程实例ID启动流程实例). <br/>
	 * @author xuzz
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public String createAndStartProcessInstance(Row row){	
		List<Row> listrow=new ArrayList<Row>();
		WorkItem wi=null;
		Long procdefId = row.getLong("procdefId");
		String procName = row.getString("procName");
		try{
			wi = wfcade.createAndStartProcessInstance(this.getOperatorInfo(), String.valueOf(procdefId), procName);
		}catch(Exception e){	
			e.printStackTrace();			
		}
		return JsonUtil.object2json(wi);
	}
	

	/**
	 * 启动流程实例[未使用]
	 * 
	 * @param processInst 流程实例
	 * @throws GeneralFailureException
	 * @throws GeneralException 
	 */
	@Deprecated
	public void startProcessInstance(ProcessInst processInst, DataRecord rec) throws GeneralFailureException, GeneralException
	{
		WorkflowUser user =  wfcade.getWorkflowUser(this.getOperatorInfo());
		workflowClientFacade.setCurrentWorkflowUser(user);
		// 流程实例管理接口
		try
		{
			// 待同步流程数据
			Map bizDatas = (Map) rec.getObject("bizDatas");
			if (null == bizDatas)
			{
				bizDatas = new HashMap();
			}
			// 调用流程客户端门面（facade）设置流程数据
			workflowClientFacade.setCtrlDataBath(processInst, bizDatas);
			workflowClientFacade.startProcessInstance(processInst);
		}
		catch (ProcessException e)
		{
			e.printStackTrace();
			throw new GeneralFailureException(e);
		}
	}
	
    //*********特殊流程处理******************//
	/**
	 * doApplyProcess
	 * @return
	 */
	public String doApplyProcess(Row row){
		Map<String,Object> result = null;
		WorkItem wi=null;
		ResultMsg msg=null;
		String errorMessage = "流程启动失败!";
		boolean flag = false;
		try {
			Long procdefId = row.getLong("procdefId");
			String procName = row.getString("procName");
			String busId = row.getString("busId");
			String regCode = row.getString("regCode");
			String specifyId = row.getString("specifyId");
			result = wfcade.doApplyProcessAndBusinessCreate(this.getOperatorInfo(), busId, regCode,specifyId,String.valueOf(procdefId), procName);
			msg = (ResultMsg)result.get("msg");
	        if(msg.getReturnCode().equals(ResultMsg.CODE_SUCCESS)){
	        	flag = true;
	        	msg.setReturnMsg("流程启动成功！");
	        }
	        logger.info("doApplyProcess:"+msg.getReturnMsg());
		}catch(Exception e){
			logger.error("doApplyProcess: <USER EXCEPTION>"+msg.getReturnMsg()+"<SYSTEM EXCEPTION>"+e.getMessage(), e);
		}
		return setActionJsonObject(new JsonResult(flag, msg.getReturnMsg(),errorMessage).toJsonString());
	}
	/**
	 * getBusIdAndRegCode
	 * @return
	 */
	public String getBusIdAndRegCode(Row row){
		String str = null;
		Map<String,Object> rernMap = null;
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		Long procId = row.getLong("procId");
		paramsMap.put("procId", procId);
		try {
			rernMap = wfcade.getBusIdAndRegCodeByProcdefId(paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rernMap!=null && rernMap.size() > 0){
		    str =JsonUtil.map2json(rernMap);
		}
		return setActionJsonObject(str);
	}
	/**
	 * getRegCodeByBusId
	 * @return
	 */
	public String getRegCodeByBusId(Row row){
		String str = null;
		Map<String,Object> rernMap = null;
//		Map<String,Object> paramsMap = new HashMap<String,Object>();
//		paramsMap.put("busId", busId);
		try {
			rernMap = wfcade.getRegCodeBybusId(row);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rernMap!=null && rernMap.size() > 0){
		    str =JsonUtil.map2json(rernMap);
		}
		return setActionJsonObject(str);
	}
	//=========退文信息========//
	/**
	 * loadRefundData
	 * @return
	 */
	public String loadRefundData(Row row){
		String str = null;
		SpeRefundInfo srefundi = new SpeRefundInfo();
		srefundi.setBus_id(row.getString("srefundi.bus_id"));
		srefundi.setRefund_no(row.getString("srefundi.refund_no"));
		Map<String,Object> srimap = Util.ConvertObjToMap(srefundi);
		SpeRefundInfo sri = wfcade.gainSpeRefundInfoData(srimap);
		try {
			str = JsonUtil.object2json(sri);
			logger.info("saveRefundData str:"+str);
		} catch (Exception e) {
			logger.error("saveRefundData str:"+str,e);
		}
		return setActionJsonObject(str);
	}
	/**
	 * saveRefundData
	 * @return
	 */
	public String modifyRefundData(Row row){
		boolean flag = false;
		String operType = Constants.ADD;
		String tipMessage = null;
		SpeRefundInfo srefundi = new SpeRefundInfo();
		srefundi.setBus_id(row.getString("srefundi.bus_id"));
		srefundi.setRefund_id(row.getString("srefundi.refund_id"));
		srefundi.setRefund_no(row.getString("srefundi.refund_no"));
		srefundi.setRefund_status(row.getString("srefundi.refund_status"));
		srefundi.setReg_item(row.getString("srefundi.reg_item"));
		srefundi.setReg_app(row.getString("srefundi.reg_app"));
		srefundi.setNotice_date(row.getDate("srefundi.notice_date"));
		srefundi.setRefund_reason(row.getString("srefundi.refund_reason"));
		srefundi.setRefund_app(row.getString("srefundi.refund_app"));
		srefundi.setRefund_app_idno(row.getString("srefundi.refund_app_idno"));
		srefundi.setNotice_note(row.getString("srefundi.notice_note"));
		Map<String,Object> entryMap = Util.ConvertObjToMap(srefundi);
		if(Util.notNullEmpty(srefundi.getRefund_id()))
			operType = Constants.UPDATE;
		try {
			flag = wfcade.alterSpeRefundData(srefundi);
			if(flag){
				tipMessage = "退文信息保存成功！";
				entryMap = Util.ConvertObjToMap(srefundi);
			}else
				tipMessage = "保存失败";
		} catch (Exception e) {
			logger.error("退文信息保存失败", e);
			tipMessage = "保存失败";
		}
		return setActionJsonObject(new JsonResult(flag, tipMessage,operType,0).toJsonString(entryMap, new String[]{"bus_id","refund_id","refund_no"}));
	}
	/**
	 * loadRefundLstForFirst 加载退文信息列表得到第一条退文记录
	 * @return
	 */
	public String loadRefundLstForFirst(Row row){
		String str = null;
		SpeRefundInfo sri = null;
		SpeRefundInfo srefundi = new SpeRefundInfo();
		srefundi.setBus_id(row.getString("srefundi.bus_id"));
		Map<String,Object> srimap = Util.ConvertObjToMap(srefundi);
		srimap.put("proc_status", WbfConstants.PROCTOBECREATE);
		List<SpeRefundInfo> refundLst = wfcade.gainSpeRefundInfoLst(srimap);
		if(refundLst.size() > 0)
			sri = refundLst.get(0);
		else{
			sri = new SpeRefundInfo();
		}
		try {
			str = JsonUtil.object2json(sri);
			logger.info("loadRefundLstForFirst str:"+str);
		} catch (Exception e) {
			logger.error("loadRefundLstForFirst str:"+str,e);
		}
		return setActionJsonObject(str);
	}
	//=========延期信息========//
	public String loadDelayData(Row row){
		String str = null;
		SpeDelay sdelayi = new SpeDelay();
		sdelayi.setBus_id(row.getString("sdelayi.bus_id"));
		sdelayi.setDelay_no(row.getString("sdelayi.delay_no"));
		Map<String,Object> srimap = Util.ConvertObjToMap(sdelayi);
		SpeDelay sri = wfcade.gainSpeDelayInfoData(srimap);
		try {
			str = JsonUtil.object2json(sri);
			logger.info("loadDelayData str:"+str);
		} catch (Exception e) {
			logger.error("loadDelayData str:"+str,e);
		}
		return setActionJsonObject(str);
	}
	public String modifyDelayData(Row row){
		boolean flag = false;
		String operType = Constants.ADD;
		String tipMessage = null;
		SpeDelay sdelayi = new SpeDelay();
		sdelayi.setBus_id(row.getString("sdelayi.bus_id"));
		sdelayi.setDelay_id(row.getString("sdelayi.delay_id"));
		sdelayi.setDelay_status(row.getString("sdelayi.delay_status"));
		sdelayi.setDelay_no(row.getString("sdelayi.delay_no"));
		sdelayi.setReg_app(row.getString("sdelayi.reg_app"));
		sdelayi.setBus_des(row.getString("sdelayi.bus_des"));
		sdelayi.setDelay_app_part(row.getString("sdelayi.delay_app_part"));
		sdelayi.setDelay_app(row.getString("sdelayi.delay_app"));
		sdelayi.setTotal_limit(row.getDouble("sdelayi.total_limit"));
		sdelayi.setRemainder_days(row.getDouble("sdelayi.remainder_days"));
		sdelayi.setIsappanddays(row.getString("sdelayi.isappanddays"));
		sdelayi.setApp_days(row.getDouble("sdelayi.app_days"));
		sdelayi.setApproval_days(row.getDouble("sdelayi.approval_days"));
		sdelayi.setDelay_type(row.getString("sdelayi.delay_type"));
		sdelayi.setMonitor(row.getString("sdelayi.monitor"));
		sdelayi.setDelay_reason(row.getString("sdelayi.delay_reason"));
		Map<String,Object> entryMap = Util.ConvertObjToMap(sdelayi);
		if(Util.notNullEmpty(sdelayi.getDelay_id()))
			operType = Constants.UPDATE;
		try {
			flag = wfcade.alterSpeDelayData(sdelayi);
			if(flag){
				tipMessage = "延期信息保存成功！";
				entryMap = Util.ConvertObjToMap(sdelayi);
			}else
				tipMessage = "保存失败";
		} catch (Exception e) {
			logger.error("延期信息保存失败", e);
			tipMessage = "保存失败";
		}
		return setActionJsonObject(new JsonResult(flag, tipMessage,operType,0).toJsonString(entryMap, new String[]{"bus_id","delay_id","delay_no"}));
	}

	/**
	 * loadDelayLstForFirst 加载延期信息列表得到第一条延期记录
	 * @return
	 */
	public String loadDelayLstForFirst(Row row){
		String str = null;
		SpeDelay sri = null;
		SpeDelay sdelayi = new SpeDelay();
		sdelayi.setBus_id(row.getString("sdelayi.bus_id"));
		Map<String,Object> srimap = Util.ConvertObjToMap(sdelayi);
		srimap.put("proc_status", WbfConstants.PROCTOBECREATE);
		List<SpeDelay> delayLst = wfcade.gainSpeDelayInfoLst(srimap);
		if(delayLst.size() > 0)
			sri = delayLst.get(0);
		else{
			sri = new SpeDelay();
		}
		try {
			str = JsonUtil.object2json(sri);
			logger.info("loadDelayLstForFirst str:"+str);
		} catch (Exception e) {
			logger.error("loadDelayLstForFirst str:"+str,e);
		}
		return setActionJsonObject(str);
	}
	/**
	 * loadDelayLst 加载延期信息列表
	 * @return
	 */
	public String loadDelayLst(Row row){
		String str = null;
		SpeDelay sri = null;
		SpeDelay sdelayi = new SpeDelay();
		sdelayi.setBus_id(row.getString("sdelayi.bus_id"));
		sdelayi.setDelay_status(row.getString("sdelayi.delay_status"));
		Map<String,Object> srimap = Util.ConvertObjToMap(sdelayi);
		srimap.put("proc_status", srimap.get("delay_status"));
		List<SpeDelay> delayLst = wfcade.gainSpeDelayInfoLst(srimap);
		try {
			str = JsonUtil.list2json(delayLst);
			logger.info("loadDelayLst str:"+str);
		} catch (Exception e) {
			logger.error("loadDelayLst str:"+str,e);
		}
		return setActionJsonObject(str);
	}
	/**
	 * getPreProcIdByPreRegCodeFromBusMain 得到流程ID由登记关联表的前登记编号从业务主表取得
	 * @return
	 */
	public String getPreProcIdByPreRegCodeFromBusMain(Row row){
		String str = null;
		Map<String,Object> rernMap = null;
//		Map<String,Object> paramsMap = new HashMap<String,Object>();
//		paramsMap.put("reg_code", regCode);
		rernMap = wfcade.getPreProcIdByPreRegCodeFromBusMain(row);
		try {
			str = JsonUtil.map2json(rernMap);
			logger.info("getPreProcIdByPreRegCodeFromBusMain str:"+str);
		} catch (Exception e) {
			logger.error("getPreProcIdByPreRegCodeFromBusMain str:"+str,e);
		}
		return setActionJsonObject(str);
	}
	//=========挂起信息========//
	public String loadSuspendData(Row row){
		String str = null;
		SpeSuspendInfo ssuspendi = new SpeSuspendInfo();
		ssuspendi.setBus_id(row.getString("ssuspendi.bus_id"));
		ssuspendi.setSus_no(row.getString("ssuspendi.sus_no"));
		Map<String,Object> srimap = Util.ConvertObjToMap(ssuspendi);
		SpeSuspendInfo sri = wfcade.gainSpeSuspendInfoData(srimap);
		try {
			str = JsonUtil.object2json(sri);
			logger.info("loadSuspendData str:"+str);
		} catch (Exception e) {
			logger.error("loadSuspendData str:"+str,e);
		}
		return setActionJsonObject(str);
	}
	public String modifySuspendData(Row row){
		boolean flag = false;
		String operType = Constants.ADD;
		String tipMessage = null;
		SpeSuspendInfo ssuspendi = new SpeSuspendInfo();
		ssuspendi.setBus_id(row.getString("ssuspendi.bus_id"));
		ssuspendi.setSus_id(row.getString("ssuspendi.sus_id"));
		ssuspendi.setSus_status(row.getString("ssuspendi.sus_status"));
		ssuspendi.setSus_no(row.getString("ssuspendi.sus_no"));
		ssuspendi.setReg_app(row.getString("ssuspendi.reg_app"));
		ssuspendi.setBus_des(row.getString("ssuspendi.bus_des"));
		ssuspendi.setApp_off(row.getString("ssuspendi.app_off"));
		ssuspendi.setSus_app(row.getString("ssuspendi.sus_app"));
		ssuspendi.setSus_reason(row.getString("ssuspendi.sus_reason"));
		Map<String,Object> entryMap = Util.ConvertObjToMap(ssuspendi);
		if(Util.notNullEmpty(ssuspendi.getSus_id()))
			operType = Constants.UPDATE;
		try {
			flag = wfcade.alterSpeSuspendData(ssuspendi);
			if(flag){
				tipMessage = "挂起信息保存成功！";
				entryMap = Util.ConvertObjToMap(ssuspendi);
			}else
				tipMessage = "保存失败";
		} catch (Exception e) {
			logger.error("挂起信息保存失败", e);
			tipMessage = "保存失败";
		}
		return setActionJsonObject(new JsonResult(flag, tipMessage,operType,0).toJsonString(entryMap, new String[]{"bus_id","sus_id","sus_no"}));
	}
	
	/**
	 * loadSuspendLstForFirst 加载挂起信息列表得到第一条挂起记录
	 * @return
	 */
	public String loadSuspendLstForFirst(Row row){
		String str = null;
		SpeSuspendInfo sri = null;
//		Map<String,Object> srimap = Util.ConvertObjToMap(ssuspendi);
		row.put("proc_status", WbfConstants.PROCTOBECREATE);
		List<SpeSuspendInfo> susLst = wfcade.gainSpeSuspendInfoLst(row);
		if(susLst.size() > 0)
			sri = susLst.get(0);
		else{
			sri = new SpeSuspendInfo();
		}
		try {
			str = JsonUtil.object2json(sri);
			logger.info("loadSuspendLstForFirst str:"+str);
		} catch (Exception e) {
			logger.error("loadSuspendLstForFirst str:"+str,e);
		}
		return setActionJsonObject(str);
	}
	/**
	 * loadSuspendLst 加载挂起信息列表
	 * @return
	 */
	public String loadSuspendLst(Row row){
		String str = null;
//		Map<String,Object> srimap = Util.ConvertObjToMap(ssuspendi);
		row.put("proc_status", row.get("sus_status"));
		List<SpeSuspendInfo> susLst = wfcade.gainSpeSuspendInfoLst(row);
		try {
			str = JsonUtil.list2json(susLst);
			logger.info("loadSuspendLst str:"+str);
		} catch (Exception e) {
			logger.error("loadSuspendLst str:"+str,e);
		}
		return setActionJsonObject(str);
	}
	//=========驳回信息========//
	public String loadRejectionData(Row row){
		String str = null;
		Map<String,Object> srimap = Util.ConvertObjToMap(row,"srejectioni");
		SpeRejectionInfo sri = wfcade.gainSpeRejectionInfoData(srimap);
		try {
			str = JsonUtil.object2json(sri);
			logger.info("loadRejectionData str:"+str);
		} catch (Exception e) {
			logger.error("loadRejectionData str:"+str,e);
		}
		return setActionJsonObject(str);
	}
	public String modifyRejectionData(Row row){
		boolean flag = false;
		String tipMessage = null;
		String operType = Constants.ADD;
		SpeRejectionInfo srejectioni = new SpeRejectionInfo();
		srejectioni.setBus_id(row.getString("srejectioni.bus_id"));
		srejectioni.setRej_id(row.getString("srejectioni.rej_id"));
		srejectioni.setRej_no(row.getString("srejectioni.rej_no"));
		srejectioni.setApp_date(row.getDate("srejectioni.app_date"));
		srejectioni.setRej_status(row.getString("srejectioni.rej_status"));
		srejectioni.setLaw(row.getString("srejectioni.law"));
		srejectioni.setApp(row.getString("srejectioni.app"));
		srejectioni.setBus_des(row.getString("srejectioni.bus_des"));
		srejectioni.setRej_reason(row.getString("srejectioni.rej_reason"));
		Map<String,Object> entryMap = Util.ConvertObjToMap(srejectioni);
		if(Util.notNullEmpty(srejectioni.getRej_id()))
			operType = Constants.UPDATE;
		try {
			flag = wfcade.alterSpeRejectionData(srejectioni);
			if(flag){
				tipMessage = "驳回信息保存成功！";
				entryMap = Util.ConvertObjToMap(srejectioni);
			}else
				tipMessage = "保存失败";
		} catch (Exception e) {
			logger.error("驳回信息保存失败", e);
			tipMessage = "保存失败";
		}
		return setActionJsonObject(new JsonResult(flag, tipMessage,operType,0).toJsonString(entryMap, new String[]{"bus_id","rej_id","rej_no"}));
	}
	
	/**
	 * loadRejectionLstForFirst 加载驳回信息列表得到第一条驳回记录
	 * @return
	 */
	public String loadRejectionLstForFirst(Row row){
		String str = null;
		SpeRejectionInfo sri = null;
//		Map<String,Object> srimap = Util.ConvertObjToMap(srejectioni);
		row.put("proc_status", WbfConstants.PROCTOBECREATE);
		List<SpeRejectionInfo> rejLst = wfcade.gainSpeRejectionInfoLst(row);
		if(rejLst.size() > 0)
			sri = rejLst.get(0);
		else{
			sri = new SpeRejectionInfo();
		}
		try {
			str = JsonUtil.object2json(sri);
			logger.info("loadRejectionLstForFirst str:"+str);
		} catch (Exception e) {
			logger.error("loadRejectionLstForFirst str:"+str,e);
		}
		return setActionJsonObject(str);
	}
	//=========暂缓信息========//
	public String loadRespiteData(Row row){
		String str = null;
//		Map<String,Object> srimap = Util.ConvertObjToMap(srespitei);
		try {
			SpeRespiteInfo sri = wfcade.gainSpeRespiteInfoData(row);
			str = JsonUtil.object2json(sri);
			logger.info("loadRespiteData str:"+str);
		} catch (Exception e) {
			logger.error("loadRespiteData str:"+str,e);
		}
		return setActionJsonObject(str);
	}
	public String modifyRespiteData(Row row){
		boolean flag = false;
		String tipMessage = null;
		String operType = Constants.ADD;
		SpeRespiteInfo srespitei = new SpeRespiteInfo();
		srespitei.setBus_id(row.getString("srespitei.bus_id"));
		srespitei.setRes_id(row.getString("srespitei.res_id"));
		srespitei.setRes_no(row.getString("srespitei.res_no"));
		srespitei.setRes_status(row.getString("srespitei.res_status"));
		srespitei.setApp_date(row.getDate("srespitei.app_date"));
		srespitei.setApp(row.getString("srespitei.app"));
		srespitei.setBus_des(row.getString("srespitei.bus_des"));
		srespitei.setRes_reason(row.getString("srespitei.res_reason"));
		Map<String,Object> entryMap = Util.ConvertObjToMap(srespitei);
		if(Util.notNullEmpty(srespitei.getRes_id()))
			operType = Constants.UPDATE;
		try {
			flag = wfcade.alterSpeRespiteData(srespitei);
			if(flag){
				tipMessage = "暂缓信息保存成功！";
				entryMap = Util.ConvertObjToMap(srespitei);
			}else
				tipMessage = "保存失败";
		} catch (Exception e) {
			logger.error("暂缓信息保存失败", e);
			tipMessage = "保存失败";
		}
		return setActionJsonObject(new JsonResult(flag, tipMessage ,operType,0).toJsonString(entryMap, new String[]{"bus_id","res_id","res_no"}));
	}
	/**
	 * loadRespiteLstForFirst 加载暂缓信息列表得到第一条暂缓记录
	 * @return
	 */
	public String loadRespiteLstForFirst(Row row){
		String str = null;
		SpeRespiteInfo sri = null;
//		Map<String,Object> srimap = Util.ConvertObjToMap(srespitei);
		row.put("proc_status", WbfConstants.PROCTOBECREATE);
		List<SpeRespiteInfo> resLst = wfcade.gainSpeRespiteInfoLst(row);
		if(resLst.size() > 0)
			sri = resLst.get(0);
		else{
			sri = new SpeRespiteInfo();
		}
		try {
			str = JsonUtil.object2json(sri);
			logger.info("loadRespiteLstForFirst str:"+str);
		} catch (Exception e) {
			logger.error("loadRespiteLstForFirst str:"+str,e);
		}
		return setActionJsonObject(str);
	}
	//*********特殊流程处理结束**************//
}

