/**
 * Project Name:dxtx_re
 * File Name:ProjectAction.java
 * Package Name:com.szhome.cq.web.basedata
 * Date:2014-8-6下午3:21:31
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
 * Date:     2014-8-6 下午3:21:31 <br/>
 * @author   dxtx
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ProjectAction extends BaseDelegate{
	private IBaseDataFacade  basedataFacade = FacadeFactory.getBaseDataFacade(); 
	
	/**
	 * 查询建设项目列表（分页）
	 * 
	 * @param row 前端页面传递进来的查询参数，包含分页信息
	 * @return
	 * @throws Exception
	 */
	public String getProjectList(Row row) throws Exception {
		String searchStr = row.getString("searchStr");
		if(searchStr == null){
			row.put("searchStr", "");
		}
		//搜索字段：项目名称、楼名及栋号、坐落
		String pageNumber = row.getString("pageNumber");
		String maxPageItems = row.getString("maxPageItems");
    	//当前页  
        int intPage = Integer.parseInt((pageNumber == null || pageNumber == "0") ? "1":pageNumber);  
        //每页显示条数  
        int number = Integer.parseInt((maxPageItems == null || maxPageItems == "0") ? "10":maxPageItems);  
		Page resultList = basedataFacade.getProjectList(row, intPage, number);
		return pageResultToJson(resultList);
	}
	
	public String getProjectByID(Row row) throws Exception {
		String prj_id = row.getString("prj_id");
		return JsonUtil.object2json(basedataFacade.getProjectByID(prj_id));
	}
	
	/**
	 * 新建建设项目
	 * @param row 前端页面传递进来的需插入到表的数据
	 * @return 保存的数据
	 * @throws Exception
	 */
	public JsonResult saveProject(Row row) throws Exception {
		try {
			//验证建设项目信息
			JsonResult result = checkProject(row,0);
			if (!result.isSuccess()) {
				return result;
			}
			
			//保存建筑信息
			basedataFacade.saveProject(row);
			
			//保存关联
			
		} catch(GeneralException ge) {
			LogUtil.error(ge.getMessage(), ge);
			return new JsonResult(false, null, ge.getMessage());
		}
		
		return new JsonResult(true, "建设项目保存成功！", null);
	}
	

	/**
	 * 更新建筑基本信息。
	 * 
	 * @param row 前端页面传递进来的需插入到表的数据
	 * @return
	 * @throws Exception
	 */
	public JsonResult updateProject(Row row) throws Exception {
		
		try {
			//验证建筑信息
			JsonResult result = checkProject(row,1);
			if (!result.isSuccess()) {
				return result;
			}
			
			//更新建筑信息
			basedataFacade.updateProject(row);
		} catch(GeneralException ge) {
			LogUtil.error(ge.getMessage(), ge);
			return new JsonResult(false, null, ge.getMessage());
		}
		
		return new JsonResult(true, "更新建设项目信息成功！", "");
		
	}
	
	/**
	 * 验证建筑信息
	 * 1、建筑名不能重复
	 * @param row
	 * @param count 判断数量
	 * @return
	 * @throws Exception
	 */
	private JsonResult checkProject(Row row,int count) throws Exception {
		
		
	    /* 是否新增建筑
		 * 新增建筑跟所有数据比较
		 * 已存在建筑跟除自己之外的所有比较
		 * */
		 
			//判断建筑名是否重复
			boolean flag = basedataFacade.checkProject("project_name",row.getString("project_name"),count);
			if (!flag) {
				return new JsonResult(false, null, "建设项目[" + row.getString("project_name") + "]已经存在，请修改！");
			}
		
		return new JsonResult(true, null, null);
	}
	
	/**
	 * 删除建筑信息
	 * @param row
	 * @return
	 * @throws Exception
	 */
	public JsonResult deleteProject(Row row) throws Exception {
		
		String sProjectId = row.getString("prj_id");
		
		/*
		 * 删除建筑关联信息
		 */
		
		/*
		 * 删除建筑信息
		 */
		basedataFacade.deleteProject(sProjectId);
		
		return new JsonResult(true, "建设项目删除成功！", null);
	}

}

