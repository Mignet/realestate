/**
 * Project Name:dxtx_re
 * File Name:IBaseDataFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-8-6下午4:57:21
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import com.plan.commons.Row;
import com.plan.exceptions.GeneralException;
import com.springjdbc.annotation.Page;
import com.szhome.cq.domain.model.Bd_building;
import com.szhome.cq.domain.model.Bd_house;
import com.szhome.cq.domain.model.Bd_project;
import com.szhome.cq.domain.model.Bd_rise;

/**
 * ClassName:IBaseDataFacade <br/>
 * Function: 楼盘表服务 <br/>
 * Date:     2014-8-6 下午4:57:21 <br/>
 * @author   dxtx
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IBaseDataFacade {
	/**
	 * 根据搜索参数获取<b>分页</b>建设项目信息
	 * @param number 每页数量
	 * @param intPage 第几页
	 * @param row */
	public Page<Bd_project> getProjectList(Row row, int intPage, int number) throws GeneralException;;

	public void saveProject(Row datas);

	public void updateProject(Row row);
	
	public boolean checkProject(String key, String value,int count);

	public void deleteProject(String bldg_id);

	public Bd_project getProjectByID(String bldg_id);
	
	/**
	 * 根据搜索参数获取<b>分页</b>建筑物信息
	 * @param number 每页数量
	 * @param intPage 第几页
	 * @param row 
	 * @throws GeneralException */
	public Page<Bd_building> getBuildingList(Row row, int intPage, int number) throws GeneralException;
	
	public void saveBuilding(Row datas);
	
	public void updateBuilding(Row row);
	
	public boolean checkBuilding(String key, String value,int count);
	
	public void deleteBuilding(String bldg_id);
	
	public Bd_building getBuildingByID(String bldg_id);
	
	/**
	 * 根据搜索参数获取<b>分页</b>逻辑栋信息
	 * @param number 每页数量
	 * @param intPage 第几页
	 * @param row */
	public Page<Bd_rise> getRiseList(Row row, int intPage, int number);
	
	public void saveRise(Row datas);
	
	public void updateRise(Row row);
	
	public boolean checkRise(String key, String value,int count);
	
	public void deleteRise(String bldg_id);
	
	public Bd_rise getRiseByID(String bldg_id);
	/**
	 * 根据搜索参数获取<b>分页</b>房屋信息
	 * @param number 每页数量
	 * @param intPage 第几页
	 */
	public Page<Bd_house> getHouseList(Row row, int intPage, int number)throws GeneralException;
	
	public void saveHouse(Row datas);
	
	public void updateHouse(Row row);
	
	public void deleteHouse(String house_id);
	
	public Bd_house getHouseByID(String house_id);
	
}
