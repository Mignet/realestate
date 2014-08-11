/**
 * Project Name:dxtx_re
 * File Name:BuildingAction.java
 * Package Name:com.szhome.cq.web.building
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
 * ClassName:BuildingAction <br/>
 * Date:     2014-8-6 下午3:21:31 <br/>
 * @author   dxtx
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class BuildingAction extends BaseDelegate{
	private IBaseDataFacade  basedataFacade = FacadeFactory.getBaseDataFacade(); 
	
	/**
	 * 查询建筑列表（分页）
	 * 
	 * @param row 前端页面传递进来的查询参数，包含分页信息
	 * @return
	 * @throws Exception
	 */
	public String getBuildingList(Row row) throws Exception {
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
		Page resultList = basedataFacade.getBuildingList(row, intPage, number);
		return pageResultToJson(resultList);
	}
	
	public String getBuildingByID(Row row) throws Exception {
		String bldg_id = row.getString("bldg_id");
		return JsonUtil.object2json(basedataFacade.getBuildingByID(bldg_id));
	}
	
	/**
	 * 新建建筑
	 * @param row 前端页面传递进来的需插入到表的数据
	 * @return 保存的数据
	 * @throws Exception
	 */
	public JsonResult saveBuilding(Row row) throws Exception {
		try {
			//验证建筑信息
			JsonResult result = checkBuilding(row,0);
			if (!result.isSuccess()) {
				return result;
			}
			
			//保存建筑信息
			basedataFacade.saveBuilding(row);
			
			//保存关联
			
		} catch(GeneralException ge) {
			LogUtil.error(ge.getMessage(), ge);
			return new JsonResult(false, null, ge.getMessage());
		}
		
		return new JsonResult(true, "建筑保存成功！", null);
	}
	

	/**
	 * 更新建筑基本信息。
	 * 
	 * @param row 前端页面传递进来的需插入到表的数据
	 * @return
	 * @throws Exception
	 */
	public JsonResult updateBuilding(Row row) throws Exception {
		
		try {
			//验证建筑信息
			JsonResult result = checkBuilding(row,1);
			if (!result.isSuccess()) {
				return result;
			}
			
			//更新建筑信息
			basedataFacade.updateBuilding(row);
		} catch(GeneralException ge) {
			LogUtil.error(ge.getMessage(), ge);
			return new JsonResult(false, null, ge.getMessage());
		}
		
		return new JsonResult(true, "更新建筑信息成功！", "");
		
	}
	
	/**
	 * 验证建筑信息
	 * 1、建筑名不能重复
	 * @param row
	 * @param count 判断数量
	 * @return
	 * @throws Exception
	 */
	private JsonResult checkBuilding(Row row,int count) throws Exception {
		
			//判断楼名及栋号是否重复
			boolean flag = basedataFacade.checkBuilding("bldg_name_no",row.getString("bldg_name_no"),count);
			if (!flag) {
				return new JsonResult(false, null, "楼名及栋号[" + row.getString("bldg_name_no") + "]已经存在，请修改！");
			}
		
		return new JsonResult(true, null, null);
	}
	
	/**
	 * 删除建筑信息
	 * @param row
	 * @return
	 * @throws Exception
	 */
	public JsonResult deleteBuilding(Row row) throws Exception {
		
		String sBuildingId = row.getString("bldg_id");
		
		/*
		 * 删除建筑关联信息
		 */
		
		/*
		 * 删除建筑信息
		 */
		basedataFacade.deleteBuilding(sBuildingId);
		
		return new JsonResult(true, "建筑删除成功！", null);
	}

}

