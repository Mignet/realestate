/**
 * Project Name:dxtx_re
 * File Name:LoginFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2013-12-24上午9:50:33
 * Copyright (c) 2013,dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableMap;
import com.plan.commons.Row;
import com.plan.exceptions.GeneralException;
import com.plan.util.SQLUtils;
import com.plan.util.StringUtils;
import com.springjdbc.annotation.Page;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.IBaseDataFacade;
import com.szhome.cq.domain.model.Bd_building;
import com.szhome.cq.domain.model.Bd_house;
import com.szhome.cq.domain.model.Bd_project;
import com.szhome.cq.domain.model.Bd_rise;
import com.szhome.cq.utils.SequenceUtil;

/**
 * 不动产楼盘表数据服务Facade
 * Date:     2013-12-24 上午9:50:33 <br/>
 * @author   dxtx
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class BaseDataFacade implements IBaseDataFacade {
	@Autowired
	private Bd_building buldDao;
	@Autowired
	private Bd_project projectDao;
	@Autowired
	private Bd_house houseDao;
	
	private String parseCondition(Row row) throws GeneralException{
		String queryCondition = row.getString("queryCondition");
		String actualConditons = "";
		if(!StringUtils.isEmpty(queryCondition)){
			List parsedList = new ArrayList();
			actualConditons = SQLUtils.parseDynamicConditions(queryCondition, row, parsedList);
			actualConditons = actualConditons.trim();
			if (actualConditons.startsWith("and ")) {
				actualConditons = actualConditons.substring(3);
			}
			actualConditons = StringUtils.replaceStrWithArray(actualConditons, "?", parsedList.toArray());
			LogUtil.debug(actualConditons);
		}
		if(!StringUtils.isEmpty(actualConditons)){
			actualConditons =" and " +actualConditons;
		}
		return actualConditons;
	}
	@Override
	public Page getBuildingList(Row row ,int intPage, int number) throws GeneralException {
		String actualConditons = parseCondition(row);
		actualConditons += " order by input_date";
		return buldDao.queryDomainPageBykeyForOracle("BaseData.getBuildingBySearch",actualConditons,row,intPage,number);
	}

	@Override
	public void saveBuilding(Row datas) {
		Bd_building t = new Bd_building();
		t.copyProperties(t, datas);
		t.setBldg_id(SequenceUtil.getGlobalSeqID());
		buldDao.save(t);
	}

	@Override
	public void updateBuilding(Row datas) {
		String bldg_id = datas.getString("bldg_id");
		Bd_building t = new Bd_building();//buldDao.get(new Bd_building(bldg_id));
		t.copyProperties(t, datas);
		buldDao.update(t);
	}

	@Override
	public boolean checkBuilding(String key, String value,int count) {
		List<Bd_building> list = buldDao.getAll(" where "+key+" = :"+key, ImmutableMap.of(key,value));
		if(list==null || list.size() <= count){
			return true;
		}
		return false;
	}

	@Override
	public void deleteBuilding(String bldg_id) {
		buldDao.delete(new Bd_building(bldg_id));
	}

	@Override
	public Bd_building getBuildingByID(String bldg_id) {
		return buldDao.get(new Bd_building(bldg_id));
	}

	@Override
	public Page<Bd_project> getProjectList(Row row, int intPage, int number) throws GeneralException {
		String actualConditons = parseCondition(row);
		actualConditons += " order by prj_id";
		return projectDao.queryDomainPageBykeyForOracle("BaseData.getProjectBySearch",actualConditons,row,intPage,number);
	}

	@Override
	public void saveProject(Row datas) {
		Bd_project p = new Bd_project();
		p.copyProperties(p, datas);
		p.setPrj_id(SequenceUtil.getGlobalSeqID());
		projectDao.save(p);
	}

	@Override
	public void updateProject(Row row) {
		
		//String prj_id = row.getString("prj_id");
		Bd_project p = new Bd_project();//buldDao.get(new Bd_building(bldg_id));
		p.copyProperties(p, row);
		projectDao.update(p);
		
	}

	@Override
	public void deleteProject(String prj_id) {
		
		projectDao.delete(new Bd_project(prj_id));
		
	}

	@Override
	public Bd_project getProjectByID(String prj_id) {
		return projectDao.get(new Bd_project(prj_id));
	}

	@Override
	public Page<Bd_rise> getRiseList(Row row, int intPage, int number) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRise(Row datas) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRise(Row row) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkRise(String key, String value, int count) {
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteRise(String bldg_id) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public Bd_rise getRiseByID(String bldg_id) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Bd_house> getHouseList(Row row, int intPage, int number)throws GeneralException 
	{
		String actualConditons = parseCondition(row);
		actualConditons += " order by ho_id";
		return houseDao.queryDomainPageBykeyForOracle("BaseData.getHouseBySearch",actualConditons,row,intPage,number);
	}

	@Override
	public void saveHouse(Row datas) {
		Bd_house t = new Bd_house();
		t.copyProperties(t, datas);
		t.setHo_id(SequenceUtil.getGlobalSeqID());
		houseDao.save(t);
	}

	@Override
	public void updateHouse(Row datas) {
		String house_id = datas.getString("ho_id");
		Bd_house t = new Bd_house();//buldDao.get(new Bd_building(bldg_id));
		t.copyProperties(t, datas);
		houseDao.update(t);
	}

	@Override
	public void deleteHouse(String house_id) {
		
		houseDao.delete(new Bd_house(house_id));
		
	}

	@Override
	public Bd_house getHouseByID(String house_id) {
		return houseDao.get(new Bd_house(house_id));
	}

	@Override
	public boolean checkProject(String key, String value, int count) {
		List<Bd_project> list = projectDao.getAll(" where "+key+" = :"+key, ImmutableMap.of(key,value));
		if(list==null || list.size() <= count){
			return true;
		}
		return false;
	} 

}


