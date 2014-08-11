package com.szhome.cq.web.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.web.servlet.ModelAndView;

import com.plan.commons.Row;
import com.plan.commons.RowImpl;
import com.plan.exceptions.GeneralException;
import com.plan.util.json.JsonParser;
import com.plan.util.json.tree.JsonTreeUtils;
import com.plan.web.JsonResult;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.ConOffice;
import com.szhome.cq.utils.JsonUtil;
import com.szhome.process.client.WorkflowClient;
import com.szhome.process.client.WorkflowClientFacade;
import com.szhome.process.client.WorkflowClientFactory;
import com.szhome.process.entity.WorkItem;

public class WorkWindowAction extends BaseDelegate {	
	private WorkflowClientFacade workflowClientFacade = WorkflowClientFactory.getWorkflowClientFacade();
	private WorkflowClient workflowClient = workflowClientFacade.getWorkflowClient();
	
//	private String parentId;
	private Long procdefId;
//	private String activdefId;
//	//ҵ����ID
//	private String wiId;
//	//�ڵ�ID
	private String nodeid;
	
	public ModelAndView frame(Row row){
		String procdefId = row.getString("procdefId");
		String nodeid = row.getString("nodeid");
		ModelAndView mv = new ModelAndView();
		mv.addObject("procdefId",procdefId);
		mv.addObject("nodeid",nodeid);
		mv.setViewName("common/work-window");
		return mv;
	}
	
	
	//ͨ��������ID��ȡ���̻����
	public String getWorkItemById(Long workItemId){
		String s="";
		WorkItem w=null;
		try{
			w = workflowClient.getWorkItemManager().getWorkItemById(workItemId);
			s=w.getActivName();
			System.out.println(s+"_______________________________________________________________");
		}catch(Exception e){
//			e.printStackTrace();
		}
		return s;
	}
	
	
	
	/**
	 * ��ʼ���칫ҳ��
	 * @return
	 * @throws GeneralException 
	 * @throws Exception
	 */
	public String  getFormTreeurl(Row row) throws GeneralException {
		Long procdefId = row.getLong("procdefId");
		String activdefId = row.getString("activdefId");
		//Map m=FacadeFactory.getAuditFacade().getFormAndsubmitbtn(this.getOperatorInfo(), procdefId, activdefId);
		Map m=FacadeFactory.getWorkwindowFacade().getFormAndsubmitbtn(this.getOperatorInfo(), procdefId, activdefId);
		
//		String url = String.valueOf(m.get("url"));
//		List list = (List)m.get("rows");
		JSONArray a = new JSONArray();
		a.put(m);
		//String str = a.toString();
		//String str = "[{\"url\":\""+url+"\",\"rows\":\""+new JSONArray(list).toString()+"\"}]";
		return this.setActionJsonObject(a.toString());
	}
	
	
	//�ݹ��ȡ�ӽڵ�
	
			public void  getFormChildrenJson(Row parentNode,List<ConOffice> listtree,int parentID){
				Row  treeRow = new RowImpl();
				List<Row> treeChildren=new ArrayList();
				List<ConOffice> listcoffice = new ArrayList<ConOffice>();
				Map map=new HashMap();
				map.put("bustype",FacadeFactory.getRegisterFacade().getProcid(procdefId.toString()));
				map.put("nodeid", nodeid);
				listcoffice = FacadeFactory.getWorkwindowFacade().getOfficeByNodeid(map);				
				for(ConOffice row:listcoffice){
					if(parentID == Integer.valueOf(row.getOffice_type())){
							treeRow=new RowImpl();					 
							treeRow.put("id",row.getOffice_id());
							Map attributes = new HashMap();
							attributes.put("url",row.getOffice_url());
							treeRow.put("attributes",attributes);
							treeRow.put("parent_id", row.getOffice_type());
							treeRow.put("iconCls", row.getBus_type_id());
							treeRow.put("text", row.getOffice_name());
							treeChildren.add(treeRow);
							parentNode.put("children", treeChildren);
							//getFormChildrenJson(treeRow,listtree, parentID);
						}
				}		
			}
			
			
			public String  getFormUrl(Row row){
				procdefId = row.getLong("procdefId");
				nodeid = row.getString("nodeid");
				Map resultMap=new HashMap();
				Row  resultrow = new RowImpl();
				List<ConOffice> listcoffice = new ArrayList<ConOffice>();
				Map map=new HashMap();
				map.put("bustype",FacadeFactory.getRegisterFacade().getProcid(procdefId.toString()));
				map.put("nodeid", nodeid);
				listcoffice = FacadeFactory.getWorkwindowFacade().getOfficeByNodeid(map);
				return JsonUtil.object2json(listcoffice);
			}
		/**
		 * ��ȡҵ����Լ�������
		 * @param row
		 * @return
		 */
		public String getOfficeByNodeid(Row row){
			//-------ajax---------
			String pageNumber = row.getString("pageNumber");
			String maxPageItems = row.getString("maxPageItems");
			if(row.get("procdefId").equals("undefined")||row.get("nodeid").equals("undefined"))
			{
				return null;
			}
			procdefId = row.getLong("procdefId");
			nodeid = row.getString("nodeid");
			List<ConOffice> listtree = new ArrayList<ConOffice>();
			//��ǰҳ  
	        int intPage = Integer.parseInt((pageNumber == null || pageNumber == "0") ? "1":pageNumber);  
	        //ÿҳ��ʾ����  
	        int number = Integer.parseInt((maxPageItems == null || maxPageItems == "0") ? "10":maxPageItems);  
	        //ÿҳ�Ŀ�ʼ��¼  ��һҳΪ1  �ڶ�ҳΪnumber +1   
	        int start = (intPage-1)*number;  
	           
			try {
				//listtree = FacadeFactory.getWorkwindowFacade().getOfficeByNodeid();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			List<Row> treeChildren=new ArrayList();
			List<Map<String,Object>> listTreeRow=new ArrayList<Map<String,Object>>(); 
			Row treeRow=null;
			for(int i=0;i<2;i++){						
				treeRow=new RowImpl();
				String type="";
				if(i==0)
				{
					type="��";
				}
				else
				{
					type="��ӡ����";
				}
				 treeRow.put("id",i);
				 Map attributes = new HashMap();
				 attributes.put("url","");
				 treeRow.put("attributes",attributes);
				 treeRow.put("parent_id", i);
				 treeRow.put("iconCls", "");
				 treeRow.put("text",type );
                //����ӽڵ�
				 getFormChildrenJson(treeRow,listtree,i);
                listTreeRow.add(treeRow);	 
			}
			String str = "";
			try {
				JSONArray ja = new JSONArray(listTreeRow);
				str = ja.toString();
				//str = new JsonParserJsonlibImpl().toJson(listTreeRow);
			} catch (Exception e) {
				e.printStackTrace();
			}
//			System.out.println("str:"+str);
			return setActionJsonObject(str);
		}
	
	
	
	
	
	/**
	 * ��ȡ��Ʒ������
	 * @param row
	 * @return
	 * @throws Exception
	 */
	public List<Row> getProductTreeJson() throws Exception {
		//List<Row> listtree = getPlanSupportDao().findRows("select form_id id,parent_id,url,text,icon iconCls from tree union select -1 id, null form_id, '��Ʒ' text from dual", new Object[]{});
		//List<Row> listtree = getPlanSupportDao().findRows("select form_id id,parent_id,url,form_name text,icon iconCls from FORM_REPORT_ADDRESS ", new Object[]{});
		List<Row> listtree=null;
		List<Row> treeChildren=null;
		List<Row> listTreeRow=new ArrayList<Row>(); 
		Row treeRow=null;
		Row cRow=null;
		for(Row row1:listtree){	
			if( row1.getInt("parent_id") == -1){
	                
                //����ӽڵ�
				 getChildrenJson(treeRow,listtree, row1.getInt("id"));
                listTreeRow.add(treeRow);
				}
		}
		return listTreeRow;
	}
	//�ݹ��ȡ�ӽڵ�
	public void  getChildrenJson(Row parentNode,List<Row> listtree,int parentID) throws Exception{
		List<Row> listTreeRow=new ArrayList<Row>(); 
		List<Row> treeChildren=new ArrayList<Row>();
		Row  treeRow = null;
		//List<Row> treeChildren=null;
		for(Row row:listtree){
			
			if(parentID == row.getInt("parent_id")){
				treeRow=new RowImpl();					 
				treeRow.put("id",row.getInt("id"));
				treeRow.put("url",row.getString("url"));
				treeRow.put("parent_id", row.getInt("parent_id"));
				treeRow.put("iconCls", row.getString("iconCls"));
				treeRow.put("text", row.getString("text"));
				
				treeChildren.add(treeRow);
				parentNode.put("children", treeChildren);
				
				getChildrenJson(treeRow,listtree, row.getInt("id"));
				
			}
		}		
	}	
	
public String getProductTreeJson1(Row row) throws Exception {
		
		List<Row> listtree = null;//getPlanSupportDao().findRows("select form_id id,parent_id,url,form_name text from FORM_REPORT_ADDRESS union select -1 id, null parent_id,null url, '������' text from dual", new Object[]{});
		return "[" + JsonTreeUtils.createTrees("-1", listtree) + "]";
	}	
	
	
	/**
	 * ����ID��ȡ����������Ϣ
	 * @param row
	 * @return
	 * @throws Exception
	 */
	public String getProductById(Row row) throws Exception {
		String sParentId = row.getString("id");
		
		return "";
	}
	
	/**
	 * �����������
	 * @param row
	 * @return
	 * @throws Exception
	 */
	public JsonResult saveProduct(Row row){
		String parentId = row.getString("parentId");
		if (parentId == null || parentId.equals("")) {
			row.put("parent_id", "-1");
		}		
		//�ж������Ƿ񱣴�ɹ�
		try {
			
		} catch(Exception ge) {
			LogUtil.error(ge.getMessage(), ge);
			return new JsonResult(false, ge.getMessage(), null);
		}
		
		return new JsonResult(true, "�˵�����ɹ���", null);
	}
	
	/**
	 * �������
	 * code�����ظ�
	 * @param row
	 * @return
	 * @throws GeneralException 
	 */
	private JsonResult checkProduct(Row row) throws GeneralException {
		
		//�ж��Ƿ����product_id
		Object oProductId = row.getObject("product_id");
		if (oProductId == null || oProductId.equals("")) {
			int count =0;//= getPlanSupportDao().findInt("select count(1) from FORM_REPORT_ADDRESS where code = ?", new Object[]{row.getString("code")});
			if (count != 0) {
				return new JsonResult(false, null, "��Ʒ����[" + row.getString("code") + "]�Ѿ����ڣ����޸ģ�");
			}
		} else {
			int count =0;// getPlanSupportDao().findInt("select count(1) from FORM_REPORT_ADDRESS where code = ? and product_id != ?", new Object[]{row.getString("code"), row.getLong("product_id")});
			if (count != 0) {
				return new JsonResult(false, null, "��Ʒ����[" + row.getString("code") + "]�Ѿ����ڣ����޸ģ�");
			}
		}
		
		return new JsonResult(true, null, null);
	}
	
	/**
	 * �޸Ĳ�Ʒ
	 * @param row
	 * @return
	 * @throws Exception
	 */
	public JsonResult updateProduct(Row row) throws Exception {
		
		//���������Ч��
//		JsonResult result = checkProduct(row);
//		if (!result.isSuccess()) {
//			return result;
//		}
		
		//�ж������Ƿ񱣴�ɹ�
		try {
			//getPlanSupportDao().update(row, "FORM_REPORT_ADDRESS");
		} catch(Exception ge) {
			LogUtil.error(ge.getMessage(), ge);
			return new JsonResult(false, ge.getMessage(), null);
		}
		
		return new JsonResult(true, "�˵��޸ĳɹ���", null);
	}
	
	/**
	 * ɾ����Ʒ
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public JsonResult deleteProduct(Row row) throws Exception {
		String sProductId = row.getString("id");
		
		//�ж��Ƿ�����ӽڵ�
		int count=0;//= getPlanSupportDao().findInt("select count(1) from FORM_REPORT_ADDRESS where parent_id = ?", new Object[]{sProductId});
		if (count != 0) {
			return new JsonResult(false, null, "�ýڵ�����ӽڵ㣬����ɾ���ӽڵ㡣");
		}
		
		//ɾ���ڵ�
		try {
			//getPlanSupportDao().delete("delete from FORM_REPORT_ADDRESS where form_id = ?", new Object[]{sProductId});
		} catch(Exception ge) {
			LogUtil.error(ge.getMessage(), ge);
			return new JsonResult(false, ge.getMessage(), null);
		}
	
		return new JsonResult(true, "��Ʒɾ���ɹ���", null);
	}

}
