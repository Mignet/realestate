/**
 * Project Name:dxtx_re
 * File Name:AtditFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2013-12-23下午7:24:44
 * Copyright (c) 2013, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.szhome.cq.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plan.commons.Row;
import com.plan.commons.RowImpl;
import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IAuditFacade;
import com.szhome.cq.business.ResultMsg;
import com.szhome.cq.business.vo.AuditVo;
import com.szhome.cq.domain.model.AcceptRule;
import com.szhome.cq.domain.model.BusOwnership;
import com.szhome.cq.domain.model.BusType;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.Reg_relationship;
import com.szhome.cq.utils.WbfConstants;
import com.szhome.process.entity.WorkItem;
import com.szhome.security.ext.UserInfo;

/**
 * 
 * 前置审核facade
 * date: 2014-5-9 下午6:38:51 <br/>
 *
 * @author Joyon
 * @version 
 * @since JDK 1.6
 */
@Component
@Transactional
@Scope("prototype")
public class AuditFacade implements IAuditFacade {
	@Autowired
	private BusinessMain businessMainDao;
	@Autowired
	private Reg_relationship reg_relationshipDao;
	@Autowired
	private BusOwnership busOwnershipDao;
	@Autowired
	private AcceptRule acceptRuleDao;
	/**
	 * 
	 *  查询宗地表，房屋表，和楼宇表三张表信息.
	 * @see com.szhome.cq.business.IAuditFacade#getAuditInfo(java.util.Map)
	 */
	@Transactional
	public List<AuditVo> getAuditInfo(Map m)
	{
	
		String sql=" l.PARCEL_CODE like '%'||:parcelcode||'%' and  f.HOUSE_LOCATION like '%'||:houselocation||'%' and f.PRO_NAME like '%'||:proname||'%' and f.ADV_HOUSE_CODE like '%'||:housecode||'%'";
		return businessMainDao.queryObjectListByKey("PreAudit.getAuditInfo", sql, m, AuditVo.class);
	}
	
	/**
	 * 
	 * 查询宗地表信息.
	 * @see com.szhome.cq.business.IAuditFacade#getLandInfo(java.util.Map)
	 */
	@Transactional
	public List<AuditVo> getLandInfo(Map m)
	{
		String sql=" where parcel_code like '%'||:parcelcode||'%' and land_address like '%'||:location||'%' ";
		//List<AuditVo> listaudit=new ArrayList<AuditVo>();
		List<AuditVo> list= businessMainDao.queryObjectListByKey("PreAudit.getAllLandInfo", sql, m,AuditVo.class);
		return list;
	}
	/**
	 * 
	 *  查询建筑物信息.
	 * @see com.szhome.cq.business.IAuditFacade#getBuildInfo(java.util.Map)
	 */
	@Transactional
	public List<AuditVo> getBuildInfo(Map m)
	{
		String sql=" where l.parcel_code like '%'||:parcelcode||'%' and z.land_address like '%'||:location||'%' and l.COMMUNITY_NAME like '%'||:proname||'%' and l.BUILDING_CODE like '%'||:housecode||'%'";
		//List<AuditVo> listaudit=new ArrayList<AuditVo>();
		List<AuditVo> list= businessMainDao.queryObjectListByKey("PreAudit.getAllBuildInfo", sql, m,AuditVo.class);
		return list;
	}
	/**
	 * 
	 *  检查该房屋是否可以办理房屋初始登记业务
	 * @see com.szhome.cq.business.IAuditFacade#checkData(java.lang.String)
	 */
	@Transactional
	public List<Map<String, Object>> checkData(String housecode)
	{	
		Map m=new HashMap();
		m.put("housecode", housecode);		
		List<Map<String, Object>> map = businessMainDao.queryMapListByKey("PreAudit.checkData"," where J.housecode=':housecode'",m);
		return map;
	}
	/**
	 * 
	 *  根据房屋编码查询房屋相关信息.
	 * @see com.szhome.cq.business.IAuditFacade#getHouse(java.lang.String)
	 */
	@Transactional
	public Map getHouse(String housecode){
		Map m=new HashMap();
		m.put("housecode", housecode);
		Map map = businessMainDao.queryMapByKey("PreAudit.getHouse"," and f.housecode =:housecode",m);
		return map;
		//return businessMainDao.queryMapByKey(" where f.fwbh =':fwbh'", m);
	}
	
	
	
	/**
	 * 
	 *  插入登记单元关联表信息和所有权登记信息.
	 * @see com.szhome.cq.business.IAuditFacade#insertReg_Info(java.util.Map)
	 */
	@Transactional
	public ResultMsg insertRegInfo(String regcode,List<Map> list,WorkItem wi,String procdefid){
		ResultMsg msg = new ResultMsg();
		Reg_relationship r=new Reg_relationship();
		BusinessMain b=new BusinessMain();	
		String lastbusid="";
		try {
			b.setProc_id(wi.getProcId().toString());
			b.setProc_name(wi.getProcName());
			b.setPro_def_id(procdefid);
			//b.setLast_bus_id(lastbusid);
			b.setReg_type(procdefid);
			b.setReg_station(WbfConstants.FUTIAN);
			if(procdefid.equals(WbfConstants.UNATTACH)||procdefid.equals(WbfConstants.UNMORTRE))
			{
				//r.setLast_reg_code(list.get(0).get("lastregcode").toString());
			}
			b.setReg_code(regcode);
			b.setBus_id(businessMainDao.getSeqId());
			
			//插入业务主表
			businessMainDao.save(b);
			
			for(int i=0;i<list.size();i++)
			{
				String code=list.get(i).get("code").toString();
				Map map=new HashMap();
				map.put("houseid", code);
				if(r.getLast_reg_code()==null||r.getLast_reg_code()=="")
				{
					//3.查找前一个业务ID
					lastbusid= getBusid(list.get(i));
				}
				r.setReg_code(regcode);
				r.setReg_state(WbfConstants.PROCESSING);
				r.setLast_reg_code(lastbusid);
				//4.插入登记单元关联表
				r.setReg_unit_type(list.get(i).get("reg_type").toString());
				r.setReg_unit_code(code);			
				r.setRegunitrel_id(reg_relationshipDao.getSeqId());
				r.setBus_id(b.getBus_id());
				reg_relationshipDao.save(r);
			}
			
			//如果是预售备案业务 则先从市场接口获取数据保存到本地
			BusType busType = FacadeFactory.getCommonFacade().getBustypeByBustypeid(b.getReg_type());
			if(busType!=null){
				if(busType.getParent_id().equals(WbfConstants.BAKUP)){
					FacadeFactory.getPresaleFacade().savePresaleTodb(b.getProc_id());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setReturnCode(ResultMsg.CODE_FAIL);
			msg.setReturnMsg(e.getMessage());
			throw new BusinessException("插入登记单元关联表信息和所有权登记信息出错:"+e.getMessage());
//			return msg;
		}
		msg.setReturnCode(ResultMsg.CODE_SUCCESS);
		return msg;
	}
	/**
	 * 受理启动流程，保存数据
	 */
	@Override
	@Transactional
	public Map doApply(UserInfo userInfo,String procname,String serialName,String procdefId,List<Map> list) {
		Map result = new HashMap();
		try
		{
			//1.查询登记编号
			Row row = new RowImpl();
			row.put("name", serialName);
			String regcode = FacadeFactory.getIdentifierFacade().getSerialNumber(row);
			String procName="";
			if(list.size()>1)
			{
				procName=procname+"("+list.get(0).get("proname").toString()+"--"+list.get(0).get("code").toString()+"等)";
			}
			else
			{
				procName=procname+"("+list.get(0).get("proname").toString()+"--"+list.get(0).get("code").toString()+")";
			}
			//2.启动
			WorkItem wi = FacadeFactory.getWorkflowFacade().createAndStartProcessInstance(userInfo, procdefId, procName);
			//4.插入业务主表以及业务与登记单元关联表
			String procdefid= FacadeFactory.getRegisterFacade().getProcid(wi.getProcdefId().toString());
			ResultMsg msg=new ResultMsg();
			msg= this.insertRegInfo(regcode,list,wi,procdefid);	
			result.put("regcode", regcode);
			result.put("workItem", wi);
		}catch(Exception err)
		{
			err.getMessage();
		}
		return result;
	}
	
	
	/**
	 * 获取前个业务登记编号
	 * @param map
	 * @return
	 */
	public String getBusid(Map map)
	{
		String key="";
		String sql=" where EFFECTIVE='"+WbfConstants.EFFECTIVE+"' and book_code in (select book_code from  BK_BASEINFO where REG_UNIT_CODE=:code)";
		if(map.get("reg_type").toString().equals(WbfConstants.PARCEL))
		{
			key="Register.getBkuserInfoByRegId";
			//Map resultmap= acceptRuleDao.queryMapByKey("Register.getBkuserInfoByRegId", sql, map);
		}
		else
		{
			key="Register.getBkOwnership";
			//Map resultmap= acceptRuleDao.queryMapByKey("Register.getBkOwnership", sql, map);
			
		}
		Map resultmap= acceptRuleDao.queryMapByKey(key, sql, map);
		if(resultmap==null||resultmap.isEmpty())
		{
			return "";
		}
		return resultmap.get("REG_CODE").toString();
	}
	/**
	 * 初始化办公页面
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	public Map getFormAndsubmitbtn(UserInfo userInfo,Long procdefId,String activdefId){
		Map result = new HashMap();
		//IFrame地址栏url
		//Map m=FacadeFactory.getFormFacade().getFormTreeurl(formid);
//		if(m!=null)
//		{
//			result.put("url",m.get("url").toString());
//		}
		//动态加载按钮
		List list=FacadeFactory.getWorkflowFacade().getsubmitbtn(userInfo, procdefId, activdefId);
		if(list!=null&&!list.isEmpty()){
			result.put("rows", list);
		}
		return result;
	}
	/**
	 * 
	 * 删除或者增加受理规则)
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public void saveDeleteRule(AcceptRule acc)
	{
		AcceptRule a= selectRule(acc);
		if(a==null)
		{
			acc.setAcc_rule_id(String.valueOf(acceptRuleDao.getSeqId()));
			acceptRuleDao.save(acc);
		}
		else
		{		
			acceptRuleDao.delete(new AcceptRule(a.getAcc_rule_id()));
		}
		
	}
	/**
	 * 
	 * 查询受理规则)
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public AcceptRule selectRule(AcceptRule acc)
	{
		Map map=new HashMap();
		map.put("bustype", acc.getBus_type_id());
		map.put("ruleid", acc.getRule());
		map.put("ruletype", acc.getRule_type());
		AcceptRule a= acceptRuleDao.get(" where Bus_type_id=:bustype and Rule=:ruleid and Rule_type=:ruletype", map);
		return a;
	}
	/**
	 * 
	 * 根据业务类型查询受理规则
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public List<Map<String, Object>> selectRuleByBusType(String bustype)
	{
		List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
		Map map=new HashMap();
		map.put("bustype", bustype);
		listmap = acceptRuleDao.queryMapListByKey("PreAudit.selectRule", " where Bus_type_id=:bustype", map);		
		return listmap;
	}
	
	
	
}

