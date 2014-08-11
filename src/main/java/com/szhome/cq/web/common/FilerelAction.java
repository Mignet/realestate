/**
 * Project Name:dxtx_re
 * File Name:FileRelAction.java
 * Package Name:com.szhome.cq.web.common
 * Date:2014-6-4����6:45:53
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.web.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plan.commons.Row;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.Examine;
import com.szhome.cq.domain.model.FileRel;
import com.szhome.cq.utils.JsonUtil;

/**
 * �ļ����� action
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FilerelAction extends BaseDelegate{
//	private String proc_id;					//����ʵ��ID
//	private String uploadId;				//�ϴ�����ID
//	private String proc_node;				//���̽ڵ�
	
	/**
	 * �����ϴ��ļ�����������
	 */
	public String saveFileRel(Row row){
		String proc_id = row.getString("proc_id");
		String proc_node = row.getString("proc_node");
		String uploadId = row.getString("uploadId");
		String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id ).getBus_id();
		//1�����ж���������Ƿ�������  ��������ݱ���һ�ݽ������  ֻ����ҵ��ID  ���������--�����
		Examine examine = FacadeFactory.getExaminationFacade().getExamineBybusidAndOpiniontype(bus_id, proc_node);
		if(examine == null){
			examine = new Examine();
			examine.setBus_id(bus_id);
			examine.setOpinion_type(proc_node);
			FacadeFactory.getExaminationFacade().saveExamine(examine);
			
			examine = FacadeFactory.getExaminationFacade().getExamineBybusidAndOpiniontype(bus_id, proc_node);
		}
		//2��������ļ�������
		FileRel fileRel = new FileRel();
		fileRel.setOpinion_id(examine.getOpinion_id());
		fileRel.setUploadid(uploadId);
		
		try {
			FacadeFactory.getFileRelFacade().saveFileRel(fileRel);
		} catch (Exception e) {
			LogUtil.error("FileRelAction.saveFileRel  �����ļ����������"+e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 * ɾ���ļ�����������
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String delFileRel(Row row){
		String proc_id = row.getString("proc_id");
		String proc_node = row.getString("proc_node");
		String uploadId = row.getString("uploadId");
		try {
			String bus_id = FacadeFactory.getCommonFacade()
					.getBusinessMainByProcId(proc_id).getBus_id();
			//�Ȼ�ȡ�ļ�����������  ��ɾ�� ---��Ϊ�޷�ֱ��дsqlɾ��  ֻ��ͨ������ɾ��
			Examine examine = FacadeFactory.getExaminationFacade().getExamineBybusidAndOpiniontype(bus_id, proc_node);
			FileRel fileRel = FacadeFactory.getFileRelFacade().getFileRelByOpinionidAndUploadid(examine.getOpinion_id(),uploadId);
			FacadeFactory.getFileRelFacade().deleteFileRel(fileRel);
		} catch (Exception e) {
			LogUtil.error("ɾ������ļ���������� :uploadId="+uploadId+" errorMessage:"+e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 * ����ļ�Ԥ��--������ֻ��ͼƬԤ��
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getFileRelView(Row row){
		
		
		Map resultMap = new HashMap();
		List<FileRel> fileRelList = null;
		String proc_id = row.getString("proc_id");
		String proc_node = row.getString("proc_node");
		try {
			String bus_id = FacadeFactory.getCommonFacade()
					.getBusinessMainByProcId(proc_id).getBus_id();
			Examine examine = FacadeFactory.getExaminationFacade().getExamineBybusidAndOpiniontype(bus_id, proc_node);
			
			if(examine==null){
				resultMap.put("errorMessage","�û���δ�ϴ�����");
				return JsonUtil.map2json(resultMap);
			}
			fileRelList = FacadeFactory.getFileRelFacade().getFileRelListByOpinionId(examine.getOpinion_id());
			
			if(fileRelList==null){
				resultMap.put("errorMessage","�û���δ�ϴ�����");
				return JsonUtil.map2json(resultMap);
			}
		} catch (Exception e) {
			LogUtil.error("FilerelAction.getFileRelView ����  "+e.getMessage());
		}
		
		//��ȡָ������µ������ļ�
		String idList = "";
		String uploadId ="";
		for(FileRel tmpFileRel:fileRelList){
			if(idList.trim().length()==0){
				idList=tmpFileRel.getUploadid();
				uploadId = tmpFileRel.getUploadid();
			}else{
				idList+=","+tmpFileRel.getUploadid();
			}
		}
		resultMap.put("idList", idList);
		resultMap.put("uploadId", uploadId);
		return JsonUtil.map2json(resultMap);
	}
	
}


