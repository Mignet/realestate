/**
 * Project Name:dxtx_re
 * File Name:ProjectAction.java
 * Package Name:com.szhome.cq.web.basedata
 * Date:2014-8-6����3:21:31
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.web.basedata;

import com.plan.commons.Row;
import com.plan.exceptions.GeneralException;
import com.plan.web.JsonResult;
import com.springjdbc.annotation.Page;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IBaseDataFacade;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.utils.JsonUtil;

/**
 * ClassName:ProjectAction <br/>
 * Date:     2014-8-6 ����3:21:31 <br/>
 * @author   dxtx
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ProjectAction extends BaseDelegate{
	private IBaseDataFacade  basedataFacade = FacadeFactory.getBaseDataFacade(); 
	
	/**
	 * ��ѯ������Ŀ�б���ҳ��
	 * 
	 * @param row ǰ��ҳ�洫�ݽ����Ĳ�ѯ������������ҳ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public String getProjectList(Row row) throws Exception {
		String searchStr = row.getString("searchStr");
		if(searchStr == null){
			row.put("searchStr", "");
		}
		//�����ֶΣ���Ŀ���ơ�¥�������š�����
		String pageNumber = row.getString("pageNumber");
		String maxPageItems = row.getString("maxPageItems");
    	//��ǰҳ  
        int intPage = Integer.parseInt((pageNumber == null || pageNumber == "0") ? "1":pageNumber);  
        //ÿҳ��ʾ����  
        int number = Integer.parseInt((maxPageItems == null || maxPageItems == "0") ? "10":maxPageItems);  
		Page resultList = basedataFacade.getProjectList(row, intPage, number);
		return pageResultToJson(resultList);
	}
	
	public String getProjectByID(Row row) throws Exception {
		String prj_id = row.getString("prj_id");
		return JsonUtil.object2json(basedataFacade.getProjectByID(prj_id));
	}
	
	/**
	 * �½�������Ŀ
	 * @param row ǰ��ҳ�洫�ݽ���������뵽�������
	 * @return ���������
	 * @throws Exception
	 */
	public JsonResult saveProject(Row row) throws Exception {
		try {
			//��֤������Ŀ��Ϣ
			JsonResult result = checkProject(row,0);
			if (!result.isSuccess()) {
				return result;
			}
			
			//���潨����Ϣ
			basedataFacade.saveProject(row);
			
			//�������
			
		} catch(GeneralException ge) {
			LogUtil.error(ge.getMessage(), ge);
			return new JsonResult(false, null, ge.getMessage());
		}
		
		return new JsonResult(true, "������Ŀ����ɹ���", null);
	}
	

	/**
	 * ���½���������Ϣ��
	 * 
	 * @param row ǰ��ҳ�洫�ݽ���������뵽�������
	 * @return
	 * @throws Exception
	 */
	public JsonResult updateProject(Row row) throws Exception {
		
		try {
			//��֤������Ϣ
			JsonResult result = checkProject(row,1);
			if (!result.isSuccess()) {
				return result;
			}
			
			//���½�����Ϣ
			basedataFacade.updateProject(row);
		} catch(GeneralException ge) {
			LogUtil.error(ge.getMessage(), ge);
			return new JsonResult(false, null, ge.getMessage());
		}
		
		return new JsonResult(true, "���½�����Ŀ��Ϣ�ɹ���", "");
		
	}
	
	/**
	 * ��֤������Ϣ
	 * 1�������������ظ�
	 * @param row
	 * @param count �ж�����
	 * @return
	 * @throws Exception
	 */
	private JsonResult checkProject(Row row,int count) throws Exception {
		
		
	    /* �Ƿ���������
		 * �����������������ݱȽ�
		 * �Ѵ��ڽ��������Լ�֮������бȽ�
		 * */
		 
			//�жϽ������Ƿ��ظ�
			boolean flag = basedataFacade.checkProject("project_name",row.getString("project_name"),count);
			if (!flag) {
				return new JsonResult(false, null, "������Ŀ[" + row.getString("project_name") + "]�Ѿ����ڣ����޸ģ�");
			}
		
		return new JsonResult(true, null, null);
	}
	
	/**
	 * ɾ��������Ϣ
	 * @param row
	 * @return
	 * @throws Exception
	 */
	public JsonResult deleteProject(Row row) throws Exception {
		
		String sProjectId = row.getString("prj_id");
		
		/*
		 * ɾ������������Ϣ
		 */
		
		/*
		 * ɾ��������Ϣ
		 */
		basedataFacade.deleteProject(sProjectId);
		
		return new JsonResult(true, "������Ŀɾ���ɹ���", null);
	}

}

