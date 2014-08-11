/**
 * Project Name:dxtx_re
 * File Name:HouseAction.java
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
 * ClassName:HouseAction <br/>
 * Date:     2014-8-6 下午3:21:31 <br/>
 * @author   dxtx
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HouseAction extends BaseDelegate{
	
private IBaseDataFacade  basedataFacade = FacadeFactory.getBaseDataFacade(); 
	
	/**
	 * 查询房屋列表（分页）
	 * 
	 * @param row 前端页面传递进来的查询参数，包含分页信息
	 * @return
	 * @throws Exception
	 */
	public String getHouseList(Row row) throws Exception {
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
		Page resultList = basedataFacade.getHouseList(row, intPage, number);
		return pageResultToJson(resultList);
	}
	
	public String getHouseByID(Row row) throws Exception {
		String house_id = row.getString("ho_id");
		return JsonUtil.object2json(basedataFacade.getHouseByID(house_id));
	}
	
	/**
	 * 新建房屋
	 * @param row 前端页面传递进来的需插入到表的数据
	 * @return 保存的数据
	 * @throws Exception
	 */
	public JsonResult saveHouse(Row row) throws Exception {
		//try {
			//验证房屋信息
			/*JsonResult result = checkHouse(row,0);
			if (!result.isSuccess()) {
				return result;
			}*/
			
			//保存房屋信息
			basedataFacade.saveHouse(row);
			
			//保存关联
			
		/*} catch(GeneralException ge) {
			LogUtil.error(ge.getMessage(), ge);
			return new JsonResult(false, null, ge.getMessage());
		}*/
		
		return new JsonResult(true, "房屋保存成功！", null);
	}
	

	/**
	 * 更新房屋基本信息。
	 * 
	 * @param row 前端页面传递进来的需插入到表的数据
	 * @return
	 * @throws Exception
	 */
	public JsonResult updateHouse(Row row) throws Exception {
		
		//try {
			//验证房屋信息
			//JsonResult result = checkHouse(row,1);
			/*if (!result.isSuccess()) {
				return result;
			}*/
			
			//更新房屋信息
			basedataFacade.updateHouse(row);
		/*} catch(GeneralException ge) {
			LogUtil.error(ge.getMessage(), ge);
			return new JsonResult(false, null, ge.getMessage());
		}*/
		
		return new JsonResult(true, "更新房屋信息成功！", "");
		
	}
	
/*	*//**
	 * 验证房屋信息
	 * 1、房屋名不能重复
	 * @param row
	 * @param count 判断数量
	 * @return
	 * @throws Exception
	 *//*
	private JsonResult checkHouse(Row row,int count) throws Exception {
		
		
		 * 是否新增房屋
		 * 新增房屋跟所有数据比较
		 * 已存在房屋跟除自己之外的所有比较
		 
			//判断房屋名是否重复
			boolean flag = basedataFacade.checkHouse("project_name",row.getString("project_name"),count);
			if (!flag) {
				return new JsonResult(false, null, "房屋名[" + row.getString("project_name") + "]已经存在，请修改！");
			}
		
		return new JsonResult(true, null, null);
	}*/
	
	/**
	 * 删除房屋信息
	 * @param row
	 * @return
	 * @throws Exception
	 */
	public JsonResult deleteHouse(Row row) throws Exception {
		
		String sHouseId = row.getString("house_id");
		
		/*
		 * 删除房屋关联信息
		 */
		
		/*
		 * 删除房屋信息
		 */
		basedataFacade.deleteHouse(sHouseId);
		
		return new JsonResult(true, "房屋删除成功！", null);
	}
	

}
