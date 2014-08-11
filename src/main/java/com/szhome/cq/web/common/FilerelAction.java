/**
 * Project Name:dxtx_re
 * File Name:FileRelAction.java
 * Package Name:com.szhome.cq.web.common
 * Date:2014-6-4下午6:45:53
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
 * 文件关联 action
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FilerelAction extends BaseDelegate{
//	private String proc_id;					//流程实例ID
//	private String uploadId;				//上传附件ID
//	private String proc_node;				//流程节点
	
	/**
	 * 保存上传文件和意见表关联
	 */
	public String saveFileRel(Row row){
		String proc_id = row.getString("proc_id");
		String proc_node = row.getString("proc_node");
		String uploadId = row.getString("uploadId");
		String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id ).getBus_id();
		//1、先判断意见表中是否有数据  如果无数据保存一份进意见表  只保存业务ID  和意见类型--初审等
		Examine examine = FacadeFactory.getExaminationFacade().getExamineBybusidAndOpiniontype(bus_id, proc_node);
		if(examine == null){
			examine = new Examine();
			examine.setBus_id(bus_id);
			examine.setOpinion_type(proc_node);
			FacadeFactory.getExaminationFacade().saveExamine(examine);
			
			examine = FacadeFactory.getExaminationFacade().getExamineBybusidAndOpiniontype(bus_id, proc_node);
		}
		//2、保存进文件关联表
		FileRel fileRel = new FileRel();
		fileRel.setOpinion_id(examine.getOpinion_id());
		fileRel.setUploadid(uploadId);
		
		try {
			FacadeFactory.getFileRelFacade().saveFileRel(fileRel);
		} catch (Exception e) {
			LogUtil.error("FileRelAction.saveFileRel  保存文件关联表出错"+e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 * 删除文件关联表数据
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
			//先获取文件关联表数据  再删除 ---因为无法直接写sql删除  只能通过主键删除
			Examine examine = FacadeFactory.getExaminationFacade().getExamineBybusidAndOpiniontype(bus_id, proc_node);
			FileRel fileRel = FacadeFactory.getFileRelFacade().getFileRelByOpinionidAndUploadid(examine.getOpinion_id(),uploadId);
			FacadeFactory.getFileRelFacade().deleteFileRel(fileRel);
		} catch (Exception e) {
			LogUtil.error("删除意见文件关联表出错 :uploadId="+uploadId+" errorMessage:"+e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 * 获得文件预览--基本上只有图片预览
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
				resultMap.put("errorMessage","该环节未上传附件");
				return JsonUtil.map2json(resultMap);
			}
			fileRelList = FacadeFactory.getFileRelFacade().getFileRelListByOpinionId(examine.getOpinion_id());
			
			if(fileRelList==null){
				resultMap.put("errorMessage","该环节未上传附件");
				return JsonUtil.map2json(resultMap);
			}
		} catch (Exception e) {
			LogUtil.error("FilerelAction.getFileRelView 出错  "+e.getMessage());
		}
		
		//获取指定意见下的所有文件
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


