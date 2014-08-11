/**
 * Project Name:dxtx_re
 * File Name:HouseAction.java
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
 * ClassName:HouseAction <br/>
 * Date:     2014-8-6 ����3:21:31 <br/>
 * @author   dxtx
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HouseAction extends BaseDelegate{
	
private IBaseDataFacade  basedataFacade = FacadeFactory.getBaseDataFacade(); 
	
	/**
	 * ��ѯ�����б���ҳ��
	 * 
	 * @param row ǰ��ҳ�洫�ݽ����Ĳ�ѯ������������ҳ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public String getHouseList(Row row) throws Exception {
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
		Page resultList = basedataFacade.getHouseList(row, intPage, number);
		return pageResultToJson(resultList);
	}
	
	public String getHouseByID(Row row) throws Exception {
		String house_id = row.getString("ho_id");
		return JsonUtil.object2json(basedataFacade.getHouseByID(house_id));
	}
	
	/**
	 * �½�����
	 * @param row ǰ��ҳ�洫�ݽ���������뵽�������
	 * @return ���������
	 * @throws Exception
	 */
	public JsonResult saveHouse(Row row) throws Exception {
		//try {
			//��֤������Ϣ
			/*JsonResult result = checkHouse(row,0);
			if (!result.isSuccess()) {
				return result;
			}*/
			
			//���淿����Ϣ
			basedataFacade.saveHouse(row);
			
			//�������
			
		/*} catch(GeneralException ge) {
			LogUtil.error(ge.getMessage(), ge);
			return new JsonResult(false, null, ge.getMessage());
		}*/
		
		return new JsonResult(true, "���ݱ���ɹ���", null);
	}
	

	/**
	 * ���·��ݻ�����Ϣ��
	 * 
	 * @param row ǰ��ҳ�洫�ݽ���������뵽�������
	 * @return
	 * @throws Exception
	 */
	public JsonResult updateHouse(Row row) throws Exception {
		
		//try {
			//��֤������Ϣ
			//JsonResult result = checkHouse(row,1);
			/*if (!result.isSuccess()) {
				return result;
			}*/
			
			//���·�����Ϣ
			basedataFacade.updateHouse(row);
		/*} catch(GeneralException ge) {
			LogUtil.error(ge.getMessage(), ge);
			return new JsonResult(false, null, ge.getMessage());
		}*/
		
		return new JsonResult(true, "���·�����Ϣ�ɹ���", "");
		
	}
	
/*	*//**
	 * ��֤������Ϣ
	 * 1�������������ظ�
	 * @param row
	 * @param count �ж�����
	 * @return
	 * @throws Exception
	 *//*
	private JsonResult checkHouse(Row row,int count) throws Exception {
		
		
		 * �Ƿ���������
		 * �������ݸ��������ݱȽ�
		 * �Ѵ��ڷ��ݸ����Լ�֮������бȽ�
		 
			//�жϷ������Ƿ��ظ�
			boolean flag = basedataFacade.checkHouse("project_name",row.getString("project_name"),count);
			if (!flag) {
				return new JsonResult(false, null, "������[" + row.getString("project_name") + "]�Ѿ����ڣ����޸ģ�");
			}
		
		return new JsonResult(true, null, null);
	}*/
	
	/**
	 * ɾ��������Ϣ
	 * @param row
	 * @return
	 * @throws Exception
	 */
	public JsonResult deleteHouse(Row row) throws Exception {
		
		String sHouseId = row.getString("house_id");
		
		/*
		 * ɾ�����ݹ�����Ϣ
		 */
		
		/*
		 * ɾ��������Ϣ
		 */
		basedataFacade.deleteHouse(sHouseId);
		
		return new JsonResult(true, "����ɾ���ɹ���", null);
	}
	

}
