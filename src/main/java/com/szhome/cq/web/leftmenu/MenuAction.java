package com.szhome.cq.web.leftmenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.plan.commons.Row;
import com.plan.commons.RowImpl;
import com.plan.util.json.JsonParser;
import com.plan.util.json.JsonParserJsonlibImpl;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IMenuFacade;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.TreeMenu;
import com.szhome.cq.utils.JsonUtil;
import com.szhome.process.client.WorkflowClient;
import com.szhome.process.client.WorkflowClientFacade;
import com.szhome.process.client.WorkflowClientFactory;
import com.szhome.process.entity.WorkItem;

/**
 * ץȡ�˵�-��������
 * @author Mignet
 *
 */
public class MenuAction extends BaseDelegate{
	private WorkflowClientFacade workflowClientFacade = WorkflowClientFactory.getWorkflowClientFacade();
	private WorkflowClient workflowClient = workflowClientFacade.getWorkflowClient();
	
	private IMenuFacade treeFacade = FacadeFactory.getMenuFacade();
	
	 public String getTreeJson(Row row){
		 
		 String parent_id = row.getString("parent_id");
		 parent_id="-1";
		 List<Map<String, Object>> returnListTree = treeFacade.getTreeList("Tree.getBusTypeByTreeId",parent_id);
		
		 return JsonUtil.object2json(returnListTree);
		 
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
//				e.printStackTrace();
			}
			return s;
		}
		
		//�ݹ��ȡ�ӽڵ�
		public void  getFormChildrenJson(Row parentNode,List<TreeMenu> listtree,String parentID,Long wiId){
			List<Row> listTreeRow=new ArrayList<Row>(); 
			List<Row> treeChildren=new ArrayList<Row>();
			Row  treeRow = null;
			//List<Row> treeChildren=null;
			for(TreeMenu row:listtree){
				if(parentID!=null && parentID.trim().equals(row.getParent_id())){
					//if(row.getBusnode().contains(getWorkItemById(wiId))){
						treeRow=new RowImpl();					 
						treeRow.put("id",row.getMenu_id());
						 Map attributes = new HashMap();
						 attributes.put("url",row.getUrl());
						 treeRow.put("attributes",attributes);
						treeRow.put("parent_id", row.getParent_id());
						treeRow.put("iconCls", row.getIcon());
						treeRow.put("text", row.getMenu_name());
						treeChildren.add(treeRow);
						parentNode.put("children", treeChildren);
						getFormChildrenJson(treeRow,listtree, row.getMenu_id(),wiId);
					//}
				}
			}		
		}
		 
	/**
	 * ��ȡҵ����Լ�������
	 * @param row
	 * @return
	 */
	public String getFormTreeJson(Row row){
		String pageNumber = row.getString("pageNumber");
		String maxPageItems = row.getString("maxPageItems");
		List<TreeMenu> listtree = new ArrayList<TreeMenu>();
		//��ǰҳ  
        int intPage = Integer.parseInt((pageNumber == null || pageNumber == "0") ? "1":pageNumber);  
        //ÿҳ��ʾ����  
        int number = Integer.parseInt((maxPageItems == null || maxPageItems == "0") ? "10":maxPageItems);  
        //ÿҳ�Ŀ�ʼ��¼  ��һҳΪ1  �ڶ�ҳΪnumber +1   
        int start = (intPage-1)*number;  
           
		try {
		    //listtree = FacadeFactory.getMenuFacade().listTrees();
			//����Ȩ�޿���
			listtree = FacadeFactory.getMenuFacade().queryMenusByUserID(this.getOperatorInfo().getUserID());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		TreeMenu tree=null;
		List<Row> treeChildren=null;
		List<Map<String,Object>> listTreeRow=new ArrayList<Map<String,Object>>(); 
		Row treeRow=null;
		Row cRow=null;
		try {
			for(TreeMenu row1:listtree){	
				if(row1.getParent_id()!=null && row1.getParent_id().trim().equals("-1")){	
					
					//if(row1.getBusnode().contains(getWorkItemById(row.getLong("wiId")))){
					 treeRow=new RowImpl();
					 treeRow.put("id",row1.getMenu_id());
					 Map attributes = new HashMap();
					 attributes.put("url",row1.getUrl());
					 treeRow.put("attributes",attributes);
					 
					 treeRow.put("parent_id", row1.getParent_id());
					 treeRow.put("iconCls", row1.getIcon());
					 treeRow.put("text", row1.getMenu_name());
			        
			        //����ӽڵ�
					getFormChildrenJson(treeRow,listtree, row1.getMenu_id(),row.getLong("wiId"));
			        listTreeRow.add(treeRow);
					//}
					}
			}
		} catch (Exception e) {
			LogUtil.error("�������˵��������쳣��", e);
//			e.printStackTrace();
		}
		String str = "";
		try {
			JSONArray ja = new JSONArray(listTreeRow);
			str = ja.toString();
			//str = new JsonParserJsonlibImpl().toJson(listTreeRow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//        return SUCCESS; 
		return setActionJsonObject(str);
	}

}
