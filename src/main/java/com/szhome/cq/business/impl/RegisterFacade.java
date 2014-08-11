/**
 * Project Name:dxtx_re
 * File Name:RegisterFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-1-8上午11:08:39
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.business.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plan.commons.Row;
import com.plan.commons.RowImpl;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IRegisterFacade;
import com.szhome.cq.domain.model.AcceptRule;
import com.szhome.cq.domain.model.Applicant;
import com.szhome.cq.domain.model.BusOwnership;
import com.szhome.cq.domain.model.BusType;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.Bususeright;
import com.szhome.cq.domain.model.Certificate;
import com.szhome.cq.domain.model.DictItem;
import com.szhome.cq.domain.model.Holder;
import com.szhome.cq.domain.model.HolderRelationship;
import com.szhome.cq.domain.model.House;
import com.szhome.cq.domain.model.HouseHistory;
import com.szhome.cq.domain.model.HouseTrend;
import com.szhome.cq.domain.model.Land;
import com.szhome.cq.domain.model.LandHistory;
import com.szhome.cq.domain.model.LandTrend;
import com.szhome.cq.domain.model.Mortgage;
import com.szhome.cq.domain.model.Reg_Advnotice;
import com.szhome.cq.domain.model.Reg_Demurrer;
import com.szhome.cq.domain.model.Reg_Distrain;
import com.szhome.cq.domain.model.Reg_Easement;
import com.szhome.cq.domain.model.Reg_Mortgage;
import com.szhome.cq.domain.model.Reg_Share;
import com.szhome.cq.domain.model.Reg_Useright;
import com.szhome.cq.domain.model.Reg_baseInfo;
import com.szhome.cq.domain.model.Reg_ownership;
import com.szhome.cq.domain.model.Reg_relationship;
import com.szhome.cq.domain.model.Remark;
import com.szhome.cq.utils.SequenceUtil;
import com.szhome.cq.utils.WbfConstants;

/**
 * 登记簿facade
 * Date:     2014-1-8 上午11:08:39
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class RegisterFacade implements IRegisterFacade{
	
	//public String  HOUSE_ONWERSHIP="102";
	//public String  HOUSE_ONWERSHIP="102";
	//public String  HOUSE_ONWERSHIP="102";
	//public String  HOUSE_ONWERSHIP="102";
	
	@Autowired
	private  DictItem dictItemDao;									//字典
	@Autowired
	private House houseDao;											//房屋
	@Autowired
	private Reg_baseInfo reg_baseInfoDao;							//登记簿（基本信息）
	@Autowired
	private BusType bus_typeDao;									//业务类型表
	@Autowired
	private Reg_ownership reg_ownershipDao;							//登记簿（所有权部分）
	@Autowired
	private Holder holderDao;										//权利人
	@Autowired
	private HolderRelationship holderRelationshipDao;				//权利人关联表
	@Autowired
	private Certificate certificateDao;								//房地产证
	@Autowired
	private Reg_Advnotice reg_AdvnoticeDao;							//登记簿（其它部分_预告登记）
	@Autowired
	private Remark remarkDao;										//登记簿（备注）
	@Autowired
	private Reg_Useright reg_UserightDao;							//登记簿（使用权部分）
	@Autowired
	private Reg_Mortgage reg_MortgageDao;							//登记簿（他项权力部分_抵押登记）
	@Autowired
	private Reg_Share reg_ShareDao;									//登记簿(共有部分)
	@Autowired
	private Reg_Easement reg_EasementDao;							//登记簿（他项权利部分_地役权登记）
	@Autowired
	private Reg_Demurrer reg_DemurrerDao;							//登记簿（其它部分_异议登记）
	@Autowired
	private Reg_Distrain reg_DistrainDao;							//登记簿（其它部分_查封登记）
	@Autowired
	private AcceptRule acceptRuleDao;                               //业务受理规则
	@Autowired
	private Mortgage mortDao;                                       //抵押权过程信息部分
	@Autowired
	private BusinessMain businessMainDao;							//业务主表 
	@Autowired
	private Applicant applicantDao;									//申请人表
	@Autowired
	private HouseHistory houseHistoryDao;							//房屋历史表	
	@Autowired
	private HouseTrend houseTrendDao;								//变更趋势表
	@Autowired
	private BusOwnership busOwnershipDao;							//登记信息表
	@Autowired
	private Reg_relationship reg_relationshipDao;					//登记单元关联表
	@Autowired
	private Land landDao;											//土地
	@Autowired
	private LandTrend landTrendDao;									//土地趋势
	@Autowired
	private LandHistory landHistoryDao;								//土地历史	
	@Autowired
	private Mortgage mortgageDao;									//抵押权登记信息
	
	@Autowired
	private Bususeright bususerightDao;								//使用权登记信息
	private String reg_code;										//登记编号
	 
	
	
	/**
	 * 
	 * 登记簿预览所有权部分.
	 * @param paraMap.put--	reg_code:登记编号
	 * @see com.szhome.cq.business.IRegisterFacade#registerPreview(java.util.Map)
	 * @author Joyon
	 */
	public Map<String, Object> registerPreview(Map<String, Object> paraMap) {
		paraMap.put("search_type", "登记簿预览");
		reg_code = paraMap.get("reg_code").toString();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Map<String,Object> naturalInfoMap = getNaturalInfo(paraMap);			//获取自然信息
		//获得赛地房地产证号 和权属类型
		String parcel_code = naturalInfoMap.get("PARCEL_CODE").toString();
		Reg_Useright reg_useright = getEffectiveReg_userightByRegUnitCode(parcel_code);
		if(reg_useright!=null){
			naturalInfoMap.put("PA_CER_NO", reg_useright.getCer_no());
			String userright_type = FacadeFactory.getDictFacade().getDictItemNameByCodeAndTypeCode(reg_useright.getUseright_type(), "014");
			naturalInfoMap.put("USERRIGHT_TYPE",userright_type);
		}
		resultMap.put("naturalInfo", naturalInfoMap);							//设置自然信息
		resultMap.put("ownershipInfo",getOwnershipInfo(paraMap));				//设置所有权信息
		return resultMap;
	}
	/**
	 * 
	 * getuserInfo:(登记簿预览使用权部分.). <br/>
	 * @author xuzz
	 * @param useMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getuserInfo(Map<String ,Object> useMap)
	{
		useMap.put("search_type", "登记簿预览");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String , Object> resultnatural=getNaturalInfo(useMap);
		Map<String , Object> resultuser=getuseInfo(useMap);
		resultuser.put("PARCEL_CODE", resultnatural.get("PARCEL_CODE"));
		resultuser.put("LAND_ADDRESS", resultnatural.get("LAND_ADDRESS"));
		resultMap.put("naturalInfo", resultnatural);					//设置自然信息
		resultMap.put("userInfo",resultuser);				//设置使用权权信息
		return resultMap;
	}
	
	/**
	 * getRegTypeByProdefId 通过当前登记编号 获取业务类型ID
	 * @param reg_code
	 * @author xuzz
	 * @return
	 */
	public Map getRegTypeByProdefId(Map map)
	{
		//Map<String, Object> map = new HashMap<String, Object>();
		//map.put("reg_code", reg_code);
		Map resultMap=bususerightDao.queryMapByKey("Register.getRegTypeByProdefId",map);
		if(resultMap!=null)
		{
			return  resultMap;
		}
		return  resultMap;
	}
	
	/**
	 * 
	 * getregisterInfo:(登记簿预览，通用.). <br/>
	 * @author xuzz
	 * @param useMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getregisterInfo(Map<String ,Object> map)
	{
		String bus_class_id = getBusTypeParentIdByRegId(map);
		String cur_bus_type_id = getBusTypeIdByRegIdMap(map);
		reg_code = map.get("reg_code").toString();
		map.put("search_type", "登记簿预览");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String , Object> resultnatural= null;
		//如果是抵押登记   会有多套情况   通过登记单元编号获取自然信息
		if(bus_class_id.equals(WbfConstants.MORTGAGE_RIGHT)){
			String reg_unit_code = map.get("reg_unit_code").toString();
			resultnatural = getNaturalInfoMapByRegUnitCode(reg_unit_code);
		}else{
			resultnatural = getNaturalInfo(map);
		}
		String typeid =getBusTypeParentIdByRegId(map);
		Map<String , Object> resultuser=null;
		Map<String , Object> resulthis=null;
		Map prodefMap= getRegTypeByProdefId(map);
		String prodefid="";
		if(prodefMap!=null)
		{
			prodefid=prodefMap.get("PRO_DEF_ID").toString();
		}
		//通过登记编号获取登记单元关联表数据
		Map<String , Object> relationMap= null; 
		if(bus_class_id.equals(WbfConstants.REALESTATE_CAN)||prodefid.equals(WbfConstants.UNDEMURRER)||prodefid.equals(WbfConstants.UNATTACH)||prodefid.equals(WbfConstants.REVOKEAPPROVAL))
		{
			relationMap = getRegUnitRelMapByRegId(map);
			map.put("reg_code", relationMap.get("LAST_REG_CODE").toString());
			if(relationMap!=null)
			{
				resultuser=  getPropInfo(map,bus_class_id);						//获取登记簿相关部分数据
				map.put("reg_code", relationMap.get("REG_CODE").toString());
				map.put("REG_UNIT_TYPE", relationMap.get("REG_UNIT_TYPE").toString());
				resulthis=getPropInfoHis("book",map,bus_class_id);
				if(resulthis==null)
				{
					resulthis=getPropInfoHis("",map,bus_class_id);
					if(resulthis==null)
					{
						resulthis=new HashMap<String, Object>();
						resulthis.put("REG_CODE", relationMap.get("REG_CODE").toString());
					}
				}
				resulthis.put("REG_CODE", relationMap.get("REG_CODE").toString());
				if(resulthis.get("DIS_DATE")!=null&&resulthis.get("DIS_DATE")!="")
				{
					resulthis.put("DIS_DATE", DateUtils.format((Date)resulthis.get("DIS_DATE"), "yyyy-MM-dd"));
				}
				if(!prodefid.equals(WbfConstants.REVOKEAPPROVAL))
				{
					resultMap.put("userInfoHis",resulthis);				//设置注销信息
				}
				
			}
		}
		else
		{
			//如果当前业务为抵押注销  或者 最高额抵押注销给的登记编号为上一次抵押编号
			relationMap = getRegUnitRelMapByRegId(map);
			if(cur_bus_type_id.equals(WbfConstants.MORTGAGE_CANCEL) || cur_bus_type_id.equals(WbfConstants.MAX_MORTGAGE_CANCEL)){
				String bus_id = getBusMainMapByRegId(map).get("BUS_ID").toString();
				String origregcode = getOrigMortRegCode(bus_id);
				map.put("reg_code", origregcode);
			}
			
			
			resultuser=  getPropInfo(map,bus_class_id);						//获取登记簿相关部分数据
			resultuser.put("REG_CODE", relationMap.get("REG_CODE").toString());
			
			//如果当前业务为抵押注销  或者 最高额抵押注销  所有权信息加上当前的登记编号做为  抵押注销编号  加上抵押注销时间  及抵押登簿人
			if(cur_bus_type_id.equals(WbfConstants.MORTGAGE_CANCEL) || cur_bus_type_id.equals(WbfConstants.MAX_MORTGAGE_CANCEL)){
				map.put("reg_code", reg_code);
				List<Reg_Mortgage> reg_MortgageList= reg_MortgageDao.getAll("where reg_code=:reg_code", map);
				//如果登簿中有抵押信息则添加登记时间 和登簿人   否则则置空
				if(reg_MortgageList.size()>0){
					String can_reg_date = DateUtils.format(reg_MortgageList.get(0).getReg_date(),"yyyy-MM-dd");
					resultuser.put("can_reg_date", can_reg_date);
					resultuser.put("can_recorder", reg_MortgageList.get(0).getRecorder());
				}else{
					resultuser.put("can_reg_date", "");
					resultuser.put("can_recorder", "");
				}
				resultuser.put("can_mort_no", reg_code);
				
			}
			
		}
		resultuser.put("PARCEL_CODE", resultnatural.get("PARCEL_CODE"));
		resultuser.put("LAND_ADDRESS", resultnatural.get("LAND_ADDRESS"));
		
		if(resultuser.get("START_DATE")!=null&&!resultuser.get("START_DATE").toString().trim().equals(""))
		{
			resultuser.put("START_DATE", DateUtils.format((Date)resultuser.get("START_DATE"), "yyyy-MM-dd"));
		}
		if(resultuser.get("END_DATE")!=null&&!resultuser.get("END_DATE").toString().trim().equals(""))
		{
			resultuser.put("END_DATE", DateUtils.format((Date)resultuser.get("END_DATE"), "yyyy-MM-dd"));
		}
		if(resultuser.get("DIS_DATE")!=null&&!resultuser.get("DIS_DATE").toString().trim().equals("")) 
		{
			resultuser.put("DIS_DATE", DateUtils.format((Date)resultuser.get("DIS_DATE"), "yyyy-MM-dd"));
		}
		if(resultuser.get("REG_DATE")!=null && !resultuser.get("REG_DATE").toString().trim().equals(""))
		{
			resultuser.put("REG_DATE", DateUtils.format((Date)resultuser.get("REG_DATE"), "yyyy-MM-dd"));
		}
		
		
		//获得赛地房地产证号 和权属类型
		String parcel_code = resultnatural.get("PARCEL_CODE").toString();
		Reg_Useright reg_useright = getEffectiveReg_userightByRegUnitCode(parcel_code);
		if(reg_useright!=null){
			resultnatural.put("PA_CER_NO", reg_useright.getCer_no());
			String userright_type = FacadeFactory.getDictFacade().getDictItemNameByCodeAndTypeCode(reg_useright.getUseright_type(), "014");
			resultnatural.put("USERRIGHT_TYPE",userright_type);
		}
		
		
		resultMap.put("naturalInfo", resultnatural);					//设置自然信息
		resultMap.put("userInfo",resultuser);				//设置使用权权信息
		return resultMap;
	}
	public Map<String,Object> registerSave(Map<String,Object> paraMap){
		
		Map<String,Object> resultMap = null;
		if(isRegisterSave(paraMap)){
			resultMap = new HashMap();
			resultMap.put("result", "exists");
			return resultMap;
		}
		//if(true){
			
			//return null;
		//}
		
		String busType = getBusTypeParentNameByRegId(paraMap);						//业务分类  总共有八大类
		if(busType.equals("房屋所有权登记")){
			resultMap = registerSaveOwnerShip(paraMap);
		}else if(busType.equals("使用权登记")){
			
		}else if(busType.equals("抵押登记")){
			
		}else if(busType.equals("地役权登记")){
			
		}else if(busType.equals("预告登记")){
			
		}else if(busType.equals("查封登记")){
			
		}else if(busType.equals("共有部分")){
			
		}else if(busType.equals("备注")){
			
		}
		
		return resultMap;		
	}
	
/**********************************     预览部分          *************************************/
	/**********************************     自然信息部分            *************************************/
	
	/**
	 * 
	 * getNaturalInfo:(获取自然信息 )
	 *
	 * @author Joyon
	 * @param paraMap.put--search_type:登记簿预览/登记簿查询	reg_code:登记编号
	 * @return
	 * @since JDK 1.6
	 */
	public  Map<String, Object> getNaturalInfo(Map<String, Object> paraMap){
		String search_type = checkRegTypeByRegId(paraMap);							//search_type:登记簿预览/登记簿查询  reg_code:登记编号
		Map<String, Object> resultMap = new HashMap<String,Object>();
		String key = null;
		String reg_unit_type = "";													//登记单元类型   房屋 宗地 楼宇
		String reg_unit_code = "";													//登记单元编号
		//Map<String,Object> naturalTypeMap = getRegNaturalTypeMapByRegCode(paraMap);
		//获取登记单元关联表数据   如果为空则输出日志
		Map<String,Object> regUnitTypeMap = getRegUnitRelMapByRegId(paraMap);
		if(regUnitTypeMap != null || regUnitTypeMap.isEmpty()){
			reg_unit_type = regUnitTypeMap.get("REG_UNIT_TYPE").toString();
			reg_unit_code = regUnitTypeMap.get("REG_UNIT_CODE").toString();
			if(reg_unit_code.equals("") || reg_unit_code == null){
				LogUtil.error("登记单元编号为空！");
				throw new BusinessException("登记单元编号为空！");
			}
		}else{
			LogUtil.error("登记单元关联表数据为空！");
			throw new BusinessException("登记单元关联表数据为空！");
		}
		//房屋
		if(reg_unit_type.equals(WbfConstants.HOUSE)){
			key = "Register.getNaturalInfoMapByHouseRegUnitCode";
		}else if(reg_unit_type.equals(WbfConstants.PARCEL)){
			key = "Register.getNaturalInfoMapByParcelRegUnitCode";
		}else if(reg_unit_type.equals(WbfConstants.BUILDING)){
			
		}
		paraMap.put("reg_unit_code", reg_unit_code);
		try {
			resultMap.putAll(dictItemDao.queryMapByKey(key, paraMap));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("获取自然信息失败"+e.getMessage());
		}
//		Map certificateMap = getCertificateNoMapByRegCode(paraMap);
//		if(certificateMap!=null && !certificateMap.isEmpty()){
//			resultMap.putAll(certificateMap);
//		}
		//获取房屋属性
		 Map house_attr_name_map = getHouseAttrNameMapByRegCode(paraMap);
		 if(house_attr_name_map != null && !house_attr_name_map.isEmpty()){
			 resultMap.putAll(house_attr_name_map);
		 }else{
			 LogUtil.error("RegisterFacade.getnaturalinfo() 房屋属性数据为空");
		 }
		 
		 String pacel_real_useage_name = FacadeFactory.getDictFacade().getDictItemNameByCodeAndTypeCode(resultMap.get("REAL_USAGE").toString(), "015");
		
		 resultMap.put("PA_REAL_USAGE_NAME", pacel_real_useage_name);
		 resultMap.putAll(paraMap);
		return resultMap;
	}
	
	/**
	 * 
	 * getNaturalInfoMapByRegUnitCode:(根据登记单元编号获取自然信息 )
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @return 自然信息Map
	 * @since JDK 1.6
	 */
	public  Map<String, Object> getNaturalInfoMapByRegUnitCode(String reg_unit_code){
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String,Object> paraMap =new HashMap<String,Object>();
		Map<String,Object> certificateMap = new HashMap<String,Object>();			//返回的房地产证信息  当前登记单元生效的房地产证号
		paraMap.put("reg_unit_code", reg_unit_code);
		String key = null;
		String reg_unit_type = "";													//登记单元类型   房屋 宗地 楼宇
		//获取登记单元关联表数据   如果为空则输出日志
		List<Reg_relationship> regRelationShipList = reg_relationshipDao.getAll(" where reg_unit_code=:reg_unit_code",paraMap);
		if(regRelationShipList!=null && !regRelationShipList.isEmpty()){
			reg_unit_type = regRelationShipList.get(0).getReg_unit_type();
			if(reg_unit_code.equals("") || reg_unit_code == null){
				LogUtil.error("登记单元编号为空！");
				throw new BusinessException("RegisterFacade.getNaturalInfoMapByRegUnitCode 登记单元编号为空！");
			}
		}else{
			LogUtil.error("RegisterFacade.getNaturalInfoMapByRegUnitCode:获取登记单元关联表中登记单元类型出错");
		}
		//房屋
		
		if(reg_unit_type.equals(WbfConstants.HOUSE)){
			key = "Register.getNaturalInfoMapByHouseRegUnitCode";
			Reg_ownership effictveOwnership = getEffectiveReg_OwnershipByRegUnitCode(reg_unit_code);
			if(effictveOwnership!=null){
				certificateMap.put("CER_NO",effictveOwnership.getCer_no());
			}else{
				LogUtil.error("RegisterFacade.getNaturalInfoMapByRegUnitCode 当前登记编号生效的登记簿所有权部分信息为空   reg_unit_code:"+reg_unit_code);
			}
			//certificateMap.put("CER_NO",getEffectiveReg_OwnershipByRegUnitCode(reg_unit_code).getCer_no());
		}else if(reg_unit_type.equals(WbfConstants.PARCEL)){
			key = "Register.getNaturalInfoMapByParcelRegUnitCode";
			Reg_Useright effictveUseright = getEffectiveReg_userightByRegUnitCode(reg_unit_code);
			if(effictveUseright!=null){
				certificateMap.put("CER_NO",effictveUseright.getCer_no());
			}else{
				LogUtil.error("RegisterFacade.getNaturalInfoMapByRegUnitCode 当前登记编号生效的登记簿使用权部分信息为空");
			}
		}else if(reg_unit_type.equals(WbfConstants.BUILDING)){
			
		}
		try {
			resultMap.putAll(dictItemDao.queryMapByKey(key, paraMap));
		} catch (Exception e) {
			LogUtil.error("RegisterFacade.getNaturalInfoMapByRegUnitCode：获取自然信息出错"+e.getMessage());
			throw new BusinessException("获取自然信息失败"+e.getMessage());
		}
		
		if(certificateMap!=null && !certificateMap.isEmpty()){
			resultMap.putAll(certificateMap);
		}
		
		return resultMap;
	}
	
	/**
	 * 
	 * checkRegTypeByRegId:(通过登记编号 检查当前执行的是登记簿预览 还是登记簿查询使用权部分). 
	 * @author xuzz
	 * @param paraMap	reg_code
	 * @return		登记簿预览/登记簿查询
	 * @since JDK 1.6
	 */
	public String checkuserByRegId(Map paraMap){
		String result = null;
		Map<String,Object> resultMap = null;
		
		try {
			resultMap = houseDao.queryMapByKey(
					"Register.getBkuserInfoByRegId", paraMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("获取登记簿使用权部分信息出错"+e.getMessage());
		}
		//所有权部分登记信息不存在  返回登记簿预览
		if(resultMap == null || resultMap.isEmpty()){
			result="登记簿预览";
		}else{
			result = "登记簿查询";
		}
		return result;
	}
	
	
	/**
	 * 
	 * getuserInfoByRegId:(通过登记编号获取 登记簿使用权部分信息). 
	 * @author xuzz
	 * @param paraMap reg_code 登记编号
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String, Object> getuserInfoByRegId(Map<String, Object> paraMap){
		Map<String, Object> bkOwnershipInfoMap = null;
		try {
			bkOwnershipInfoMap = houseDao.queryMapByKey("Register.getBkuserInfoByRegId", paraMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("获取登记簿使用权部分信息出错"+e.getMessage());
		}
		return bkOwnershipInfoMap;
	}
	/**
	 * 
	 * getPropInfoHis:(登记簿预览房地产证注销业务，用于查询注销信息). <br/>
	 * @author xuzz
	 * @param useMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getPropInfoHis(String book,Map<String, Object> useMap,String typeid)
	{
		String whereSql="";
		if(book!="")
		{
			whereSql = " where REG_CODE=:reg_code ";
		}
		Map<String, Object> registerMap = getBKInfoByRegId(getDataTableKeyByBusTypeID(typeid,book),whereSql,useMap);
		if(registerMap==null)
		{
			registerMap=getBKInfoByRegId(getDataTableKeyByBusTypeID(typeid,""),"",useMap);
			return registerMap;
		}
		registerMap.put("REG_DATE", DateUtils.format((Date)registerMap.get("REG_DATE"), "yyyy-MM-dd"));
		return registerMap;
	}
	
	/**
	 * 
	 * getPropInfo:(登记簿预览权属部分，公用). <br/>
	 * @author xuzz
	 * @param useMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getPropInfo(Map<String, Object> useMap,String bus_class_id)
	{
		String typeid="";
		typeid =getBusTypeParentIdByRegId(useMap);
		
		String cur_bus_type_id = getBusTypeIdByRegIdMap(useMap);			//当前业务ID
		String whereSql = " where REG_CODE=:reg_code ";
		//抵押登记簿 获取时 按照登记单元编号获取  已经在key 中写好 
		if(typeid.equals(WbfConstants.MORTGAGE_RIGHT)){
			whereSql="";
		}
		
		Map<String, Object> registerMap = getBKInfoByRegId(getDataTableKeyByBusTypeID(typeid,"book"),whereSql,useMap);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String holderkey="";
		String cerkey=""; 
		//设置登簿日期  及登簿人  
		//如果当前业务是 最高额抵押确定登记  把当前业务抵押编号登簿人和时间在最高额下面显示   需要拿到前一次业务的登簿人、登簿时间和抵押编号
		if(cur_bus_type_id.equals(WbfConstants.MAX_CONFIRM_REG)){
			if(registerMap==null||registerMap.isEmpty()){
				resultMap = getBKInfoByRegId(getDataTableKeyByBusTypeID(typeid,""),null,useMap);
				if(resultMap!=null)
				{
					
					resultMap.put("sure_reg_code",useMap.get("reg_code").toString());
					resultMap.put("sure_reg_date"," ");
					resultMap.put("sure_recorder"," ");
					holderkey = "Register.getAppAsHolderByRegId";										//登记簿预览时从申请人中查询  
					cerkey = "Register.getRightCertificateNo";											//登记簿预览时从房地产证表中查询  
				}
				
			}
			else
			{
				
				resultMap =registerMap;
				resultMap.put("sure_reg_code",useMap.get("reg_code").toString());
				resultMap.put("sure_reg_date",registerMap.get("REG_DATE").toString());
				resultMap.put("sure_recorder",registerMap.get("RECORDER").toString());
				holderkey = "Register.getHolderInfoByRegId";
				cerkey="Register.getRightCertificateNoFromBkOwnership";
			}
			//获取上一次抵押的抵押编号 登簿人和 登簿时间
			String bus_id = getBusMainMapByRegId(useMap).get("BUS_ID").toString();
			String orig_mort_reg_code  = getOrigMortRegCode(bus_id);
			//上一次抵押编号
			resultMap.put("mo_reg_code", orig_mort_reg_code);
			String tmp_reg_code = useMap.get("reg_code").toString();
			String tmp_reg_unit_code = useMap.get("reg_unit_code").toString();
			Reg_Mortgage reg_Mortgage = getRegMortgageByRegUnitCodeAndRegCode(tmp_reg_unit_code,orig_mort_reg_code);
			
			//上一次登记时间和登簿人
			resultMap.put("reg_date", reg_Mortgage.getReg_date());
			resultMap.put("recorder", reg_Mortgage.getRecorder());
			
		}else{
			if(registerMap==null||registerMap.isEmpty()){
				resultMap = getBKInfoByRegId(getDataTableKeyByBusTypeID(typeid,""),null,useMap);
				if(resultMap!=null)
				{
					resultMap.put("reg_date"," ");
					resultMap.put("recorder"," ");
					holderkey = "Register.getAppAsHolderByRegId";										//登记簿预览时从申请人中查询  
					cerkey = "Register.getRightCertificateNo";											//登记簿预览时从房地产证表中查询  
				}
				
			}
			else
			{
				//登记簿查询时有登记簿人和登记时间
				//Map<String,Object> userInfoMap = getuserInfoByRegId(useMap);
				resultMap =registerMap;
				holderkey = "Register.getHolderInfoByRegId";
				cerkey="Register.getRightCertificateNoFromBkOwnership";
			}
			
		}
		if(typeid.equals(""))
		{
			//getHistoryHolderMapListByProcId(useMap);
		}
		List<Map<String, Object>> holderList = null;
		List<Map<String, Object>> cerList = null;
		String reg_unit_code = useMap.get("reg_unit_code").toString();
		//如果是抵押登记   会有多套情况   通过登记单元编号获取自然信息
		if(typeid.equals(WbfConstants.MORTGAGE_RIGHT)){
			
			
			//如果登簿记中数据为空   则从过程数据中拿  申请人也是
			if(registerMap==null||registerMap.isEmpty()){
				List<Map<String,Object>> tmpholderList = houseDao.queryMapListByKey(holderkey, useMap);
				holderList = new ArrayList<Map<String,Object>>();
				//筛选出当前登记单元的申请人
				for(Map<String,Object> tmpMap:tmpholderList){
					Object tmpRegUnitCodeObj = tmpMap.get("REG_UNIT_CODE");
					//筛选出抵押权人
					Object tmpHolRelObj = tmpMap.get("HOL_REL");
					if(tmpHolRelObj!=null){
						if(tmpHolRelObj.toString().equals(WbfConstants.MORTGAGEE)){
							holderList.add(tmpMap);
							continue;
						}
					}
					//筛选出抵押人
					if(tmpRegUnitCodeObj!=null ){
						if(tmpRegUnitCodeObj.toString().equals(reg_unit_code)){
							holderList.add(tmpMap);
						}
					}
					
				}
			}
			else{	//如果已经登簿    则从权利人表中获取数据
				holderList = holderDao.queryMapListByKey("Register.getHolder", "where RIGHT_REL_ID=(select RIGHT_REL_ID from BK_RIGHT_REL where reg_code=:reg_code and book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=:reg_unit_code))", useMap);
			}
			//如果抵押编号为空时  则把当前的登记编号做为抵押编号
			if(resultMap.get("mo_reg_code")==null){
				resultMap.put("mo_reg_code", useMap.get("reg_code").toString());
			}
			
			//获取抵押类型名
			String MORT_TYPE = resultMap.get("MORT_TYPE").toString();
			MORT_TYPE = FacadeFactory.getDictFacade().getDictItemNameByCodeAndTypeCode(MORT_TYPE, "007");
			resultMap.put("MORT_TYPE", MORT_TYPE);
			
			try {
				resultMap.put(
						"hisRegInfo",
						getHistoryRegInfoMapByRegUnitCodeAndBusClassId(
								reg_unit_code, WbfConstants.MORTGAGE_RIGHT));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else{
			
			holderList = houseDao.queryMapListByKey(holderkey, useMap);
			cerList = houseDao.queryMapListByKey(cerkey, useMap);
			
			resultMap.put("rightCertificateNo", cerList);							//房地产信息
			resultMap.put("historyOwnershipInfo",getHistoryRegInfoMapByRegUnitCodeAndBusClassId(reg_unit_code,bus_class_id));  //设置所有权历史信息
		}
		Map<String,Object> busNameMap = getBusNameMapByRegCode(useMap);				//获取业务名
		if(busNameMap!=null){
			resultMap.putAll(busNameMap);
		}
		
		 
		//获取取得方式
		 Map get_mode_name_map = getGetModeNameMapByRegCode(useMap);
		 if(get_mode_name_map != null && !get_mode_name_map.isEmpty()){
			 resultMap.putAll(get_mode_name_map);
		 }else{
			 LogUtil.error("取得方式数据为空");
		 }
		resultMap.put("holder", holderList);										//获取权利人信息
		return resultMap;
	}
	
	
	
	/**
	 * 
	 * getuseInfo:(登记簿预览使用权部分). <br/>
	 * @author xuzz
	 * @param useMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getuseInfo(Map<String, Object> useMap)
	{
		String search_type = checkuserByRegId(useMap);							//search_type:登记簿预览/登记簿查询  reg_code:登记编号
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		//获取使用权信息
		try {
			resultMap = dictItemDao.queryMapByKey("Register.getuserInfoByRegId", useMap);
			if(resultMap == null || resultMap.isEmpty()){
				throw new BusinessException("获取使用权权信息出错 使用权信息为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("获取使用权信息出错"+e.getMessage());
		}
		//设置登簿日期  及登簿人  
		if(search_type.equals("登记簿预览")){
			resultMap.put("regDate"," ");
			resultMap.put("recorder"," ");
			
		}else if(search_type.equals("登记簿查询")){
			//登记簿查询时有登记簿人  和登记时间
			Map<String,Object> userInfoMap = getuserInfoByRegId(useMap);
			if(userInfoMap!=null)
			{
				resultMap.put("regDate",userInfoMap.get("REG_DATE").toString());
				resultMap.put("recorder",userInfoMap.get("RECORDER").toString());
			}

			
		}
		
		resultMap.put("holder", getHolderInfoByRegId(useMap));		//获取权利人信息
		resultMap.put("rightCertificateNo", getCertificateNoByRegId(useMap));	//房地产信息
		resultMap.put("historyOwnershipInfo",getHistoryOwnershipInfo(useMap)); //设置所有权历史信息
		return resultMap;
	}
	
	/**
	 * 
	 * getRegNaturalTypeMapByRegCode:(判断当前登记的自然类型是房屋还是宗地).
	 *
	 * @author Joyon
	 * @param paraMap  natural_type(宗地/房屋)  id(宗地ID/房屋ID) 
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getRegNaturalTypeMapByRegCode(Map<String,Object> paraMap){
		Map<String,Object> regUnitRelMap =  dictItemDao.queryMapByKey("Register.getBusRegunitrelMapByRegCode",paraMap);
		Map<String,Object> reusltMap = new HashMap<String,Object>();
		String natural_type = null;
		
		Object house_idObj = regUnitRelMap.get("HOUSE_ID");
		Object	parcel_idObj = regUnitRelMap.get("PARCEL_ID");
		//String house_id = regUnitRelMap.get("HOUSE_ID").toString();
		//String parcel_id = regUnitRelMap.get("PARCEL_ID").toString();
		if(house_idObj != null){
			natural_type = "房屋";
			reusltMap.put("id", house_idObj.toString());
		}
		if(parcel_idObj != null ){
			natural_type = "宗地";
			reusltMap.put("id", parcel_idObj.toString());
		}
		
		reusltMap.put("natural_type", natural_type);
		
		return reusltMap;
	}
	/**********************************       自然信息部分  结束          *************************************/
	
	
/**********************************     预览部分    结束 *************************************/
	
	
	
/**********************************     登记簿保存部分          *************************************/
	
	
	
	/**********************************       所有权部分            *************************************/
	
	
	/**
	 * 
			 * getOwnershipInfo:(获取所有权部分信息)
			 * 主要获取房屋信息和申请人信息
			 * @author Joyon
			 * @param paraMap search_type:登记簿预览/登记簿查询  reg_code:登记编号
			 * @return
			 * @since JDK 1.6
			 */
			public  Map<String, Object> getOwnershipInfo( Map<String, Object> paraMap){
				if(reg_code == null || reg_code .equals("")){
					reg_code = paraMap.get("reg_code").toString();
				}
				String search_type = checkRegTypeByRegId(paraMap);							//search_type:登记簿预览/登记簿查询  reg_code:登记编号
				
				
				 Map<String, Object> resultMap = new HashMap<String, Object>();
				 
				 
				 
				
				 Map bus_onwership_map = null;
				//获取所有权信息
				try {
					bus_onwership_map = dictItemDao.queryMapByKey("Register.getOwnershipInfoByRegId", paraMap);
					if(bus_onwership_map == null || bus_onwership_map.isEmpty()){
						LogUtil.error("获取所有权信息出错 所有权信息为空");
					}else{
						resultMap.putAll(bus_onwership_map);
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("获取所有权信息出错"+e.getMessage());
				}
				
				//获取业务类型
				 Map bus_name_map = getBusNameMapByRegCode(paraMap);
				 if(bus_name_map != null && !bus_name_map.isEmpty()){
					 resultMap.putAll(bus_name_map);
				 }else{
					 LogUtil.error("业务类型数据为空");
				 }
				 
				//获取房屋属性
				 Map house_attr_name_map = getHouseAttrNameMapByRegCode(paraMap);
				 if(house_attr_name_map != null && !house_attr_name_map.isEmpty()){
					 resultMap.putAll(house_attr_name_map);
				 }else{
					 LogUtil.error("房屋属性数据为空");
				 }
				 
				//获取取得方式
				 Map get_mode_name_map = getGetModeNameMapByRegCode(paraMap);
				 if(get_mode_name_map != null && !get_mode_name_map.isEmpty()){
					 resultMap.putAll(get_mode_name_map);
				 }else{
					 LogUtil.error("取得方式数据为空");
				 }
				 
				 
				 
				
				
				//添加房屋编号  以及房屋坐落
				Map<String,Object> houseMap =getHouseMapByRegCode(paraMap);
				String adv_house_code = null;
				String hoser_locaton = null;
				if(houseMap != null && !houseMap.isEmpty()){
					adv_house_code = houseMap.get("ADV_HOUSE_CODE").toString();
					hoser_locaton = houseMap.get("HOUSE_LOCATION").toString();
					resultMap.put("HOUSE_LOCATION", hoser_locaton);
					resultMap.put("ADV_HOUSE_CODE", adv_house_code);
				}
				
				//设置登簿日期  及登簿人  
				if(search_type.equals("登记簿预览")){
					resultMap.put("REG_DATE"," ");
					resultMap.put("recorder"," ");
					Certificate certificate = certificateDao.get("where bus_id=(select bus_id from bus_main where reg_code=:reg_code)", paraMap);
					if(certificate!=null){
						resultMap.put("EXCURSUS", certificate.getExcursus());
					}
				}else if(search_type.equals("登记簿查询")){
					//登记簿查询时有登记簿人  和登记时间
					Map<String,Object> bkOSInfoMap = getBkOwnershipInfoByRegId(paraMap);
					resultMap.put("REG_DATE",bkOSInfoMap.get("REG_DATE").toString());
					resultMap.put("recorder",bkOSInfoMap.get("RECORDER").toString());
					resultMap.put("EXCURSUS", bkOSInfoMap.get("EXCURSUS").toString());
					
				}
				
				resultMap.put("holder", getHolderInfoByRegId(paraMap));		//获取权利人信息
				resultMap.put("rightCertificateNo", getCertificateNoByRegId(paraMap));	//房地产信息
				resultMap.put("historyOwnershipInfo",getHistoryOwnershipInfo(paraMap)); //设置所有权历史信息
				return resultMap;
			}
			
			/**
			 * 
			 * getGetModeNameMapByRegCode:(获得取得方式的名字).
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			private Map getGetModeNameMapByRegCode(Map<String, Object> paraMap) throws BusinessException{
				String search_type = checkRegTypeByRegId(paraMap);							//search_type:登记簿预览/登记簿查询  reg_code:登记编号
				String key = "Register.getDicCodeNameFromDicItm";
				String whereSql = "";
				//登记簿预览时从基本信息中查
				if(search_type.equals("登记簿预览")){
					//通过登记编号从所有权登记信息中取得
					whereSql="where DIC_ITEM_CODE=(select GET_MODE from BUS_OWNERSHIP where bus_id=(select bus_id from bus_main where reg_code=:reg_code))";
				}else{
					whereSql="where  DIC_ITEM_CODE=(select GET_MODE from BK_OWNERSHIP where reg_code=:reg_code)";
				}
				Map resultMap = new HashMap<String,Object>();
				Map tmpMap = certificateDao.queryMapByKey(key, whereSql, paraMap);
				if(tmpMap != null && !tmpMap.isEmpty()){
					
					Object tempObject = tmpMap.get("DIC_ITEM_VALUE");
					if(tempObject!=null){
						resultMap.put("GET_MODE_NAME",tempObject.toString());
					}
				}
				
				
				return resultMap;
			}
			
			/**
			 * 
			 * getHouseAttrNameMapByRegCode:(获得房屋性质的名字). 
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			private Map getHouseAttrNameMapByRegCode(Map<String, Object> paraMap) throws BusinessException{
				
				String search_type = checkRegTypeByRegId(paraMap);							//search_type:登记簿预览/登记簿查询  reg_code:登记编号
				String key = "Register.getDicCodeNameFromDicItm";
				String whereSql = "";
				//登记簿预览时从基本信息中查
				if(search_type.equals("登记簿预览")){
					//通过登记编号从所有权登记信息中取得
					whereSql=" where DIC_ITEM_CODE=(select HOUSE_ATTR from BUS_OWNERSHIP where bus_id=(select bus_id from bus_main where reg_code=:reg_code))";
				}else{
					whereSql=" where  DIC_ITEM_CODE=(select HOUSE_ATTR from BK_OWNERSHIP where reg_code=:reg_code)";
				}
				
				Map resultMap = new HashMap<String,Object>();
				
				Map tmpMap = certificateDao.queryMapByKey(key, whereSql, paraMap);
				if(tmpMap!=null && !tmpMap.isEmpty()){
					Object tempObject =tmpMap.get("DIC_ITEM_VALUE");
					if(tempObject!=null){
						resultMap.put("HOUSE_ATTR_NAME",tempObject.toString());
					}
				}
				
				
				return resultMap;
			}
			/**
			 * 
			 * getHouseMapByRegCode:(通过登记编号获取房屋表信息).
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public Map<String,Object> getHouseMapByRegCode(Map<String,Object> paraMap){
				
				
				return  houseDao.queryMapByKey("Register.getHouseMapByRegCode", paraMap);
			}
			
			/**
			 * 
			 * getRegPreAplPerInfo:获取申请人信息通过登记编号
			 *
			 * @author Joyon
			 * @param 登记编号
			 * @return  
			 * @since JDK 1.6
			 */
			public List<Map<String, Object>> getAplPerInfoByRegId(Map<String, Object> paraMap){
				
				List<Map<String, Object>> resultList = null;
				try {
					resultList = dictItemDao.queryMapListByKey("Register.getAplPerInfoByRegId", paraMap);
					if(resultList == null || resultList.isEmpty()){

						LogUtil.error("申请人信息为空!");
					}
				} catch (Exception e) {
					throw new BusinessException("获取申请人信息出错"+e.getMessage());
				}
				
				return resultList;
			}
			
			/**
			 * 
			 * getHistoryOwnershipInfo:(获取历史业务为所有权部分的信息). 
			 *
			 * @author Joyon
			 * @param paraMap  paramMap 中put  reg_code  登记编号
			 * @return
			 * @since JDK 1.6
			 */
			public List<Map<String, Object>> getHistoryOwnershipInfo(Map<String,Object> paraMap) throws BusinessException{
				
				//获取历史  业务办理的   业务编号
				List<Map<String, Object>> hisBusIdList = null;
				String typeId = getBusTypeParentIdByRegId(paraMap);				//主业务类型  用来筛选当前登记单元  当前大业务下的历史记录
				
				 
			//1、获取当前登记单元   对应登记簿中数据      
				 //获取登记单元类型
				 String reg_unit_type = null;
				 String reg_unit_code = null;
				 Map<String,Object> regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
				 if(regUnitRelMap!=null){
					 reg_unit_type = regUnitRelMap.get("REG_UNIT_TYPE").toString();
					 reg_unit_code = regUnitRelMap.get("reg_unit_code").toString();
					 paraMap.put("reg_unit_code", reg_unit_code);
				 }else{
					 LogUtil.error("RegisterFacade.getHistoryOwnershipInfo 获取登记单元关联表数据 出错");
				 }
				 //根据登记单元类型  从所有权和使用权中获取历史业务信息
				 List<Map<String,Object>> hisRegUnitBkInfoMapList = null;								//从登记簿中取出 的list Map值 
				 String hisRegUnitBkInfoKey = "Register.getBkOwnership";								//key 默认值   默认从所有权中取
				 if(reg_unit_type.equals(WbfConstants.HOUSE)){
				 }else{
					 hisRegUnitBkInfoKey = "Register.getBkuserInfoByRegId";
				 }
				 //给历史业务数据 赋值   在下面进行循环对比 用
				 hisRegUnitBkInfoMapList = reg_UserightDao.queryMapListByKey(hisRegUnitBkInfoKey,"where book_code=(select book_code from bk_baseinfo where reg_unit_code=:reg_unit_code)",paraMap);
				
			//2、筛选出当前登记单元   所有权 或者   使用权中历史业务的数据     
				 
				//获取当前登记单元所有的历史登记业务编号Map list   从登记单元关联表中获取          以便 于和登记簿中数据对比       主要是筛选出当前登记簿预览时的业务
				 try {
					 hisBusIdList = houseDao.queryMapListByKey("Register.getHisBusIdByRegId", paraMap);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("获取业务历史登记编号出错"+e.getMessage());
				}
				 //历史登记编号为null时 不用进行任何操作直接返回null
				 if(hisBusIdList == null || hisBusIdList.isEmpty()){
					 return null;
				 }
				 
				 //循环筛选  登记簿 中存在的业务    和本次办理的业务
				 List<Map<String, Object>> hisOwnershipRegIdList = new ArrayList<Map<String, Object>>();	//用来存储属于 房屋所有权 业务类型的 登记编号
				 Map<String, Object> tempBusMainMap = null;
				 Map<String,Object> tempParaBusIdMap = null;												//用来获得历史业务信息 的参数 
				 String tempBusParentType=null;																//历史大业务类型
				 try {
					 for(Map<String, Object> tempMap:hisBusIdList){
						 String his_reg_code =tempMap.get("REG_CODE").toString();
						 //如果登记簿中登记编号   和   登记单元关联表中相等     或者    是当前业务的登记编号  则添加到历史登记信息中  
						 for(Map tmpHisRegUnitBkInfoMap:hisRegUnitBkInfoMapList){
							 if(tmpHisRegUnitBkInfoMap.get("REG_CODE").toString().equals(his_reg_code) || paraMap.get("reg_code").toString().equals(his_reg_code)){
								 hisOwnershipRegIdList.add(tempMap);	 
								 break;
							 }
							 
						 }
					 }
				 } catch (Exception e) {
						LogUtil.error("获取业务登记类型"+e.getMessage());
						throw new BusinessException("获取业务登记类型"+e.getMessage());
				 }
				 
			//3、历史业务数据 添加   登记编号 获取所有权人  登记时间  登记类型  
				 List<Map<String, Object>> hisOwnershipList = new ArrayList<Map<String, Object>>();
				 List<Map<String, Object>> holderList = null;
				 Map<String, Object> resultMap = null;
				 String busName = null;																		//业务类型名
				 for(Map<String, Object> tempRegIdMap:hisOwnershipRegIdList){
					 resultMap = new HashMap<String, Object>();
					
					 
					 Map<String, Object>  tempParaMap = new HashMap<String, Object> ();
					 String reg_code = tempRegIdMap.get("reg_code").toString();
					 tempParaMap.put("reg_code",reg_code);					//sql查询参数   reg_code登记编号
					 
					 holderList = getHolderInfoByRegId(tempParaMap);			//获取所有权人数据      如果为空则表示是  登记簿预览数据    需要从申请人中获取
					 
					 if(holderList == null || holderList.isEmpty()){
						 try {
							holderList = houseDao.queryMapListByKey(
									"Register.getAppAsHolderByRegId", tempParaMap);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException("申请人转为权利人失败"+e.getMessage());
						}
					 }
					 
					 busName = getRegTypeByRegCode(tempParaMap);
					 String typeid="";
					 typeid =getBusTypeParentIdByRegId(paraMap);
					 String key = getDataTableKeyByBusTypeID(typeid,"book");
					 Map<String, Object> registerMap = getBKInfoByRegId(key," where REG_CODE=:reg_code ",tempParaMap);
					 String regDate = null;											//登记时间字符串
					 //登簿信息为空   登记簿预览  时间设为空  
					 Date date =null;												//用来排序
					 if(registerMap != null && !registerMap.isEmpty()){
						 
						 date = (Date)registerMap.get("REG_DATE");
						 regDate = DateUtils.format(date, "yyyy-MM-dd HH:mm:ss");
					 }
					 
					 resultMap.put("holder", holderList);							//权利人赋值
					 resultMap.put("busName", busName);								//业务类型名赋值
					 resultMap.put("regDate", regDate);								//登记时间赋值
					 resultMap.put("regId", reg_code);	
					 resultMap.put("date", date);									//用来排序
					 //登记编号
					 hisOwnershipList.add(resultMap);								//添加到历史权利人信息中
				 }
				 
				 Map<String,Object> tmpMap = null;
				 Map<String,Object> maxMap = null;									//最先办的业务
				 int tmpLength = hisOwnershipList.size();							//历史数据长度
			//4、循环按时间排序     时间为空的往后排
				 for(int i=0;i<tmpLength;i++){
					 maxMap =hisOwnershipList.get(i);								//每次循环时  把当前的值做为最大值   筛选完后会给当前index下的值设为最大值
					 //把最大的放在第一个
					 for(int j=i;j<tmpLength;j++){
						// maxMap = hisOwnershipList.get(i);
						 if(hisOwnershipList.get(i).get("date")==null){
							 //如果最后一个 则不移动
							 if(i+1 == tmpLength){
								 continue;
							 }
							 if(i!=j){
								 maxMap = hisOwnershipList.get(j);
								 hisOwnershipList.set(j,hisOwnershipList.get(i));
								 hisOwnershipList.set(i,maxMap); 
							 }if(i==j){
								 maxMap = hisOwnershipList.get(i+1);
								 hisOwnershipList.set(i+1,hisOwnershipList.get(i));
								 hisOwnershipList.set(i,maxMap); 
							 }
							 
						 }else{
							 if(i+1 == tmpLength){
								 break;
							 }
							 if(i==j){
								 if(hisOwnershipList.get(i+1).get("date") == null){
									 continue;
								 }
								 
								 if(((Date)maxMap.get("date")).before(((Date)hisOwnershipList.get(i+1).get("date")))){
									 
								 }else{
									 maxMap = hisOwnershipList.get(i+1);
									 hisOwnershipList.set(i+1,hisOwnershipList.get(i));
									 hisOwnershipList.set(i,maxMap); 
								 }
							 }
							 if(i!=j){
								 if(hisOwnershipList.get(j).get("date") == null){
									 continue;
								 }
								 Date c_tmp_date = (Date)hisOwnershipList.get(j).get("date");
								 Date max_tmp_date = (Date)maxMap.get("date");
								 if((max_tmp_date).before(c_tmp_date)){
									 
								 }else{
									 maxMap = hisOwnershipList.get(j);
									 hisOwnershipList.set(j,hisOwnershipList.get(i));
									 hisOwnershipList.set(i,maxMap); 
								 }
								 
							 }
						 }
					 }
					 
				 }
				 
				return hisOwnershipList;
			}
			
			/**
			 * 
			 * getHistoryRegInfoMapByRegUnitCodeAndBusClassId:(按登记单元编号获和业务ID(八大业务)获取取历史业务的信息). 
			 *
			 * @author Joyon
			 * @param reg_unit_code(登记单元编号) bus_class_id(八大业务的字典项值)
			 * @return
			 * @since JDK 1.6
			 */
			public List<Map<String, Object>> getHistoryRegInfoMapByRegUnitCodeAndBusClassId(String reg_unit_code,String bus_class_id) throws BusinessException{
				
				//获取历史  业务办理的   业务编号
				List<Map<String, Object>> hisBusIdList = null;
				String typeId = bus_class_id;				//主业务类型  用来筛选当前登记单元  当前大业务下的历史记录
				
				Map<String,Object> paraMap = new HashMap<String,Object>();
				paraMap.put("reg_unit_code", reg_unit_code);
				//获取当前房屋ID下的所有的历史登记业务编号Map list
				List<Reg_relationship> hisRegRelationShipList = null;
				
				 try {
					 hisRegRelationShipList = reg_relationshipDao.getAll(" where reg_unit_code=:reg_unit_code",paraMap);
					 //hisBusIdList = houseDao.queryMapListByKey("Register.getHisBusIdByRegId", paraMap);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("获取业务历史登记编号出错"+e.getMessage());
				}
				 //历史登记编号为null时 不用进行任何操作直接返回null
				 if(hisRegRelationShipList == null || hisRegRelationShipList.isEmpty()){
					 return null;
				 }
				 
				 //循环筛选业务类型为   当前业务 的登记编号  
				 List<Map<String, Object>> hisRegIdList = new ArrayList<Map<String, Object>>();				//用来存储属于 当前 业务类型的 登记编号
				 //Map<String, Object> tempBusMainMap = null;													//存储历史业务信息Map
				// Map<String,Object> tempParaBusIdMap = null;												//用来获得历史业务信息 的参数 
				Map<String,Object> tempParaMap =null;								//临时sql的参数
				 String tempBusParentTypeID=null;																//历史大业务类型字典项code
				 try {
					 
					 for(Reg_relationship tempReg_relationship:hisRegRelationShipList){
						 tempParaMap = new HashMap<String,Object>();
						 String reg_code = tempReg_relationship.getReg_code();
						 tempParaMap.put("reg_code", reg_code);
						 //八大 业务登记类型    用来筛选历史业务
						 tempBusParentTypeID =getBusTypeParentIdByRegId(tempParaMap);
						 //筛选出当前业务类型的历史记录    如果业务类型的 父类型不是房屋所有权 continue   是房屋所有权 则把该登记编号加入到hisOwnershipRegIdList
						 if(!typeId.equals(tempBusParentTypeID)){
							 continue;
						 }
							 //通过业务编号获取业务登记编号
						 hisRegIdList.add(tempParaMap);				
						
					 }
				 } catch (Exception e) {
						LogUtil.error("获取业务登记类型"+e.getMessage());
						//throw new BusinessException("获取业务登记类型"+e.getMessage());
				 }
				 
				 //通过 房屋所有权业务 登记编号 获取所有权人  登记时间  登记类型
				 List<Map<String, Object>> hisRegInfoList = new ArrayList<Map<String, Object>>();			//历史业务登记信息
				 List<Map<String, Object>> holderList = null;
				 Map<String, Object> resultMap = null;
				 String busName = null;																		//业务类型名
				 for(Map<String, Object> tempRegIdMap:hisRegIdList){
					 resultMap = new HashMap<String, Object>();
					 
					
					 
					tempParaMap = new HashMap<String, Object> ();
					 String reg_code = tempRegIdMap.get("reg_code").toString();
					 tempParaMap.put("reg_code",reg_code);					//sql查询参数   reg_code登记编号
					 
					 holderList = getHolderInfoByRegId(tempParaMap);			//获取所有权人数据      如果为空则表示是  登记簿预览数据    需要从申请人中获取
					 
					 if(holderList == null || holderList.isEmpty()){
						 try {
							holderList = houseDao.queryMapListByKey(
									"Register.getAppAsHolderByRegId", tempParaMap);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException("申请人转为权利人失败"+e.getMessage());
						}
					 }
					 busName = getRegTypeByRegCode(tempParaMap);
					 //String typeid="";
					// typeid =getBusTypeParentIdByRegId(tempParaMap);
					 String key = getDataTableKeyByBusTypeID(typeId,"book");
					 String whereSql =" where REG_CODE=:reg_code ";
					 if(typeId.equals(WbfConstants.MORTGAGE_RIGHT)){
						 whereSql="";
						 tempParaMap.put("reg_unit_code", reg_unit_code);
					 }
					 Map<String, Object> registerMap = getBKInfoByRegId(key,whereSql,tempParaMap);
					 String regDate = null;
					 //登簿信息为空   登记簿预览  时间设为空  
					 Date date =null;
					 if(registerMap != null && !registerMap.isEmpty()){
						 
						 date = (Date)registerMap.get("REG_DATE");
						 regDate = DateUtils.format(date, "yyyy-MM-dd HH:mm:ss");
						 
						 //如果是抵押权登记 获取债权人
						 if(typeId.equals(WbfConstants.MORTGAGE_RIGHT)){
							 Object tmpBorrower = registerMap.get("BORROWER");
							 if(tmpBorrower!=null){
								 resultMap.put("borrower", tmpBorrower.toString());
							 }else{
								 resultMap.put("borrower", " ");
							 }
						 }
						 if(typeId.equals(WbfConstants.REG_ATTACH))
						 {
							 resultMap.put("dis_off", registerMap.get("DIS_OFF").toString());
							 if(registerMap.get("EFFECTIVE").toString().equals(WbfConstants.EFFECTIVE))
							 {
								 resultMap.put("state", "有效");
							 }
							 else
							 {
								 resultMap.put("state", "无效");
							 }
						 }
					 }else{
						 
						 //如果是抵押权登记 获取债权人
						 if(typeId.equals(WbfConstants.MORTGAGE_RIGHT)){
							 Mortgage mortgage = mortgageDao.get("where bus_id =(select bus_id from bus_main where reg_code=:reg_code)", tempParaMap);
							 if(mortgage!=null){
								 String borrower = mortgage.getBorrower();
								 resultMap.put("borrower", borrower);
							 }
							
						 }
						 else
						 {
							 key = getDataTableKeyByBusTypeID(typeId,"");
							 registerMap = getBKInfoByRegId(key,"",tempParaMap);
							 if(typeId.equals(WbfConstants.REG_ATTACH))
							 {
								 resultMap.put("dis_off", registerMap.get("DIS_OFF").toString());
								 resultMap.put("state", "无效");
							 }
						 }
					 }
					 
					 
					 resultMap.put("holder", holderList);							//权利人赋值
					 resultMap.put("busName", busName);								//业务类型名赋值
					 resultMap.put("regDate", regDate);								//登记时间赋值
					 resultMap.put("regId", reg_code);	
					 resultMap.put("date", date);									//用来排序
					 //登记编号
					 hisRegInfoList.add(resultMap);								//添加到历史权利人信息中
				 }
				 
				 Map<String,Object> tmpMap = null;
				 Map<String,Object> maxMap = null;									//最先办的业务
				 int tmpLength = hisRegInfoList.size();							//历史数据长度
				 
				 //循环按时间排序     时间为空的往后排
				 for(int i=0;i<tmpLength;i++){
					 maxMap =hisRegInfoList.get(i);								//每次循环时  把当前的值做为最大值   筛选完后会给当前index下的值设为最大值
					 //把最大的放在第一个
					 for(int j=i;j<tmpLength;j++){
						// maxMap = hisOwnershipList.get(i);
						 if(hisRegInfoList.get(i).get("date")==null){
							 //如果最后一个 则不移动
							 if(i+1 == tmpLength){
								 continue;
							 }
							 if(i!=j){
								 maxMap = hisRegInfoList.get(j);
								 hisRegInfoList.set(j,hisRegInfoList.get(i));
								 hisRegInfoList.set(i,maxMap); 
							 }if(i==j){
								 maxMap = hisRegInfoList.get(i+1);
								 hisRegInfoList.set(i+1,hisRegInfoList.get(i));
								 hisRegInfoList.set(i,maxMap); 
							 }
							 
						 }else{
							 if(i+1 == tmpLength){
								 break;
							 }
							 if(i==j){
								 if(hisRegInfoList.get(i+1).get("date") == null){
									 continue;
								 }
								 
								 if(((Date)maxMap.get("date")).before(((Date)hisRegInfoList.get(i+1).get("date")))){
									 
								 }else{
									 maxMap = hisRegInfoList.get(i+1);
									 hisRegInfoList.set(i+1,hisRegInfoList.get(i));
									 hisRegInfoList.set(i,maxMap); 
								 }
							 }
							 if(i!=j){
								 if(hisRegInfoList.get(j).get("date") == null){
									 continue;
								 }
								 Date c_tmp_date = (Date)hisRegInfoList.get(j).get("date");
								 Date max_tmp_date = (Date)maxMap.get("date");
								 if((max_tmp_date).before(c_tmp_date)){
									 
								 }else{
									 maxMap = hisRegInfoList.get(j);
									 hisRegInfoList.set(j,hisRegInfoList.get(i));
									 hisRegInfoList.set(i,maxMap); 
								 }
								 
							 }
						 }
					 }
					 
				 }
				 
				return hisRegInfoList;
			}
			
			/**
			 * 
			 * getDataTableKeyByBusTypeID:(获取业务类型父类ID，通过父类ID来查询相应的登记簿). <br/>
			 * @author xuzz
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public String getDataTableKeyByBusTypeID(String typeid,String booktype)
			{
				//抵押
				if(typeid.equals(WbfConstants.MORTGAGE_RIGHT))
				{
					if(booktype.equals("book"))
					{
						return "Register.getBkMortgageInfoByRegUnitCode";
					}
					else
					{
						return "Register.getMortgageInfoByRegId";
					}
					
				}
				//房屋所有权
				else if(typeid.equals(WbfConstants.HOUSE_ONWERSHIP))
				{
					if(booktype.equals("book"))
					{
						return "Register.getBkOwnershipInfoByRegId";
					}
					else
					{
						return "Register.getOwnershipInfoByRegId";
					}
					
				}
				//土地使用权
				else if(typeid.equals(WbfConstants.LAND_USERIGHT))
				{
					if(booktype.equals("book"))
					{
						return "Register.getBkuserInfoByRegId";
					}
					else
					{
						return "Register.getuserInfoByRegId";
					}
					
				}
				//地役权
				else if(typeid.equals("104"))
				{
					if(booktype.equals("book"))
					{
						return "";
					}
					else
					{
						return "";
					}
				}
				//预告登记
				else if(typeid.equals("105"))
				{
					if(booktype.equals("book"))
					{
						return "";
					}
					else
					{
						return "";
					}
				}
				//异议登记
				else if(typeid.equals("106"))
				{
					if(booktype.equals("book"))
					{
						return "Register.getBkDemurrerInfoByRegId";
					}
					else
					{
						return "Register.getDemurrerInfoByRegId";
					}
				}
				//查封登记
				else if(typeid.equals(WbfConstants.ATTACH_DISTRAIN))
				{
					if(booktype.equals("book"))
					{
						return "Register.getBkDistrainInfoByRegId";
					}
					else
					{
						return "Register.getattachInfoByRegId";
					}
				}
				//房地产注销
				else if(typeid.equals(WbfConstants.REALESTATE_CAN))
				{
					Map<String,Object> paraMap = new HashMap<String,Object>();
					paraMap.put("reg_code", reg_code);
					String unit_type =getRegUnitRelMapByRegId(paraMap).get("REG_UNIT_TYPE").toString();// reg_relationshipDao.get("where bus_id =(select bus_id from bus_main where reg_code=:reg_code)", paraMap).getReg_unit_type();
					if(booktype.equals("book"))
					{
					if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
							return "Register.getBkOwnershipInfoByRegId";
						}
					else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
						return "Register.getBkuserInfoByRegId";
					}
					}
					else
					{
						if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
							return "Register.getOwnershipInfoByRegId";
						}
						else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
							return "Register.getuserInfoByRegId";
						}
					}
				}
				//撤销核准
				else if(typeid.equals(WbfConstants.REVOKEAPPROVAL1))
				{
					Map<String,Object> paraMap = new HashMap<String,Object>();
					paraMap.put("reg_code", reg_code);
					String unit_type =getRegUnitRelMapByRegId(paraMap).get("REG_UNIT_TYPE").toString();// reg_relationshipDao.get("where bus_id =(select bus_id from bus_main where reg_code=:reg_code)", paraMap).getReg_unit_type();
					if(booktype.equals("book"))
					{
					if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
							return "Register.getBkOwnershipInfoByRegId";
						}
					else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
						return "Register.getBkuserInfoByRegId";
					}
					}
					else
					{
						if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
							return "Register.getOwnershipInfoByRegId";
						}
						else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
							return "Register.getuserInfoByRegId";
						}
					}
				}
				//更正登记   分土地更正登记  和房屋登记
				else if(typeid.equals(WbfConstants.CORRECTION))
				{
					Map<String,Object> paraMap = new HashMap<String,Object>();
					paraMap.put("reg_code", reg_code);
					String unit_type =getRegUnitRelMapByRegId(paraMap).get("REG_UNIT_TYPE").toString();// reg_relationshipDao.get("where bus_id =(select bus_id from bus_main where reg_code=:reg_code)", paraMap).getReg_unit_type();
					if(booktype.equals("book"))
					{
						if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
							return "Register.getBkOwnershipInfoByRegId";
						}else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
							return "Register.getBkuserInfoByRegId";
						}
						
					}
					else
					{
						if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
							return "Register.getOwnershipInfoByRegId";
						}else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
							return "Register.getuserInfoByRegId";
						}
					}
				}
				//补换发登记   分土地登记  和房屋登记
				else if(typeid.equals(WbfConstants.REISSUE))
				{
					Map<String,Object> paraMap = new HashMap<String,Object>();
					paraMap.put("reg_code", reg_code);
					String unit_type =getRegUnitRelMapByRegId(paraMap).get("REG_UNIT_TYPE").toString();// reg_relationshipDao.get("where bus_id =(select bus_id from bus_main where reg_code=:reg_code)", paraMap).getReg_unit_type();
					if(booktype.equals("book"))
					{
						if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
							return "Register.getBkOwnershipInfoByRegId";
						}else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
							return "Register.getBkuserInfoByRegId";
						}
						
					}
					else
					{
						if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
							return "Register.getOwnershipInfoByRegId";
						}else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
							return "Register.getuserInfoByRegId";
						}
					}
				}
				return typeid;
			}
			
			
			/**
			 * 
			 * getHolderInfo:(获取权利人信息). 
			 *
			 * @author Joyon
			 * @param paraMap reg_code 登记编号
			 * @return
			 * @since JDK 1.6
			 */
			public List<Map<String, Object>> getHolderInfoByRegId(Map paraMap){
				String search_type = checkRegTypeByRegId(paraMap);		
				List<Map<String, Object>> resultList = null;
				String key = null;
				if(search_type.equals("登记簿查询")){
					key = "Register.getHolderInfoByRegId";										//登记簿查询从权利人中查询权利人
				}else if(search_type.equals("登记簿预览")){
					key = "Register.getAppAsHolderByRegId";										//登记簿预览时从申请人中查询  
				}
				
				try {
				 resultList = houseDao.queryMapListByKey(key, paraMap);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("通过登记编号获取权利人出错"+e.getMessage());
				}
				return resultList;
			}
			
			/**
			 * 根据流程实例ID 获取当前 业务前一次业务的权利人信息
			 */
			public List<Map<String,Object>> getHistoryHolderMapListByProcId(String proc_id) throws BusinessException{
				Map<String,Object> paraMap = new HashMap<String,Object>();
				paraMap.put("proc_id", proc_id);
				return houseDao.queryMapListByKey("Register.getHistoryHolderMapListByProcId", paraMap);
			}
			
			/**
			 * 
			 * getEffictiveHolderMapListByRegUnitCode:(获取当前登记单元生效的权利人).
			 *
			 * @author Joyon
			 * @param reg_unit_code  hol_rel_code(权利人关系类型code   为""时，查询所有权利人   )
			 * @return
			 * @since JDK 1.6
			 */
			public List<Map<String,Object>> getEffictiveHolderMapListByRegUnitCode(String reg_unit_code,String hol_rel_code){
				Map<String,Object> paraMap = new HashMap<String,Object>();
				paraMap.put("reg_unit_code", reg_unit_code);
				//抵押时不适用这条 必须从登记簿中查询出有效的登记编号   再通过登记编号从权利人集合中查询数据
				//String whereSql ="where RIGHT_REL_ID =(select RIGHT_REL_ID from BK_RIGHT_REL where BOOK_CODE=(select BOOK_CODE from BK_BASEINFO where REG_UNIT_CODE=:reg_unit_code) and EFFECTIVE='"+EFFECTIVE+"')";
				String whereSql="";
				String reg_unit_type = null;
				String hol_rel_str="";
				if(hol_rel_code.equals("")){
					
				}else{
					hol_rel_str ="HOL_REL='"+hol_rel_code+"' and ";
				}
				try {
					reg_unit_type = reg_baseInfoDao.get("where reg_unit_code=:reg_unit_code", paraMap).getReg_unit_type();
				} catch (Exception e) {
					LogUtil.error("获取登记单元类型出错"+e.getMessage());
				}
				whereSql = "where "+hol_rel_str+" RIGHT_REL_ID =(select RIGHT_REL_ID from BK_RIGHT_REL where book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=:reg_unit_code) and EFFECTIVE='"+WbfConstants.EFFECTIVE+"' )";		
				return houseDao.queryMapListByKey("Register.getHolder",whereSql, paraMap);
			}
			
			
			/**
			 * 
			 * getRegTypeByRegId:(通过登记编号 获取登记类型).
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public String getRegTypeByRegCode(Map<String, Object> paraMap){
				Map<String, Object> regTypeMap = null;
				try {
					regTypeMap= houseDao.queryMapByKey("Register.getRegTypeByRegId", paraMap);
					if(regTypeMap == null || regTypeMap.isEmpty()){
						LogUtil.error("获取登记类型出错");
						throw new BusinessException("获取登记类型出错");
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("获取登记类型出错"+e.getMessage());
				}
				
				return regTypeMap.get("BUS_NAME").toString();
			}
			
			/**
			 * 
			 * getBusTypeParentNameByRegId:(通过登记编号获取  业务类型的父类型). 
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public String getBusTypeParentNameByRegId(Map<String, Object> paraMap){
				Map<String, Object> resutMap = null;
				try {
					resutMap = houseDao.queryMapByKey("Register.getBusTypeParentNameByRegId", paraMap); //参数为reg_code
					if(resutMap == null || resutMap.isEmpty()){
						throw new BusinessException("获取业务类型分类出错 业务类型分类为空");
					}
				} catch (Exception e) {
					throw new BusinessException("获取业务类型分类出错"+e.getMessage());
				}
				return resutMap.get("BUS_NAME").toString();
			}
			
			/**
			 * 
			 * getBusTypeParentIdByRegId:(通过登记编号获取  业务类型的父类型). 
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public String getBusTypeParentIdByRegId(Map<String, Object> paraMap){
				Map<String, Object> resutMap = null;
				try {
					resutMap = houseDao.queryMapByKey("Register.getBusTypeParentNameByRegId", paraMap); //参数为reg_code
					if(resutMap == null || resutMap.isEmpty()){
						return "";
						//throw new BusinessException("获取业务类型分类出错 业务类型分类为空");
					}
				} catch (Exception e) {
					throw new BusinessException("获取业务类型分类出错"+e.getMessage());
				}
				return resutMap.get("bus_type_id").toString();
			}
			
			/**
			 * 
			 * getBusTypeIdByRegIdMap:(通过登记编号Map获取业务类型的ID). 
			 *
			 * @author Joyon
			 * @param paraMap reg_code
			 * @return
			 * @since JDK 1.6
			 */
			public String getBusTypeIdByRegIdMap(Map<String, Object> paraMap){
					BusinessMain businessMain = businessMainDao.get("where reg_code=:reg_code", paraMap);
					if(businessMain !=null){
						return businessMain.getReg_type();
					}else{
						LogUtil.error("业务主表数据为空！");
					}
					return null;
			}
			
			
			/**
			 * 
			 * getBkOwnershipInfoByRegId:(通过登记编号获取 登记簿信息). 
			 * @author Joyon
			 * @param paraMap reg_code 登记编号
			 * @return
			 * @since JDK 1.6
			 */
			public Map<String, Object> getBkOwnershipInfoByRegId(Map<String, Object> paraMap){
				Map<String, Object> bkOwnershipInfoMap = null;
				try {
					bkOwnershipInfoMap = houseDao.queryMapByKey("Register.getBkOwnershipInfoByRegId","where reg_code=:reg_code", paraMap);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("获取登记簿所有权部分信息出错"+e.getMessage());
				}
				return bkOwnershipInfoMap;
			}
			
			
			
			/**
			 * 
			 * getBKInfoByRegId:(通过登记编号获取 登记簿信息). 
			 * @author Joyon
			 * @param paraMap reg_code 登记编号
			 * @return
			 * @since JDK 1.6
			 */
			public Map<String, Object> getBKInfoByRegId(String key,String sql,Map<String, Object> paraMap){
				Map<String, Object> bkOwnershipInfoMap = null;
				try {
					bkOwnershipInfoMap = houseDao.queryMapByKey(key, sql, paraMap);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("获取登记簿所有权部分信息出错"+e.getMessage());
				}
				return bkOwnershipInfoMap;
			}
			
			/**
			 * 
			 * getRightCertificateNo:(通过登记编号获取房地产证号)
			 * @author Joyon
			 * @param paraMap reg_code
			 * @return CER_NO
			 * @since JDK 1.6
			 */
			public List<Map<String, Object>> getCertificateNoByRegId(Map<String, Object> paraMap){
				String search_type = checkRegTypeByRegId(paraMap);		
				String key = null;
				if(search_type.equals("登记簿查询")){
					key = "Register.getRightCertificateNoFromBkOwnership";							//登记簿查询从登记簿所有权部分查询房地产证号
				}else if(search_type.equals("登记簿预览")){
					return null;
					//key = "Register.getRightCertificateNo";											//登记簿预览时从房地产证表中查询  
				}
				
				List<Map<String, Object>> resultList = null;
				try {
					resultList = houseDao.queryMapListByKey(key, paraMap);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("获取权证号信息出错"+e.getMessage());
				}
				return resultList;
			}
			
			
			/**
			 * 
			 * checkRegTypeByRegId:(通过登记编号 检查当前执行的是登记簿预览 还是登记簿查询). 
			 * @author Joyon
			 * @param paraMap	reg_code
			 * @return		登记簿预览/登记簿查询
			 * @since JDK 1.6
			 */
			public String checkRegTypeByRegId(Map paraMap){
				String result = null;
				Map<String,Object> resultMap = null;
				
				try {
					resultMap = houseDao.queryMapByKey(
							"Register.getBkOwnershipInfoByRegId","where reg_code=:reg_code", paraMap);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("获取登记簿所有权部分信息出错"+e.getMessage());
				}
				//所有权部分登记信息不存在  返回登记簿预览
				if(resultMap == null || resultMap.isEmpty()){
					result="登记簿预览";
				}else{
					result = "登记簿查询";
				}
				return result;
			}
			/**
			 * 
			 * save_Baseinfo:(保存登记簿基本信息，用于登簿). <br/>
			 * @author xuzz
			 * @param regUnitRelMap
			 * @param busMainMap
			 * @since JDK 1.6
			 */
			public Reg_baseInfo save_Baseinfo(Map<String,Object> regUnitRelMap,Map<String,Object> busMainMap)
			{
				
				//登记簿 基本信息  每个登记单元只能有一条数据  进来先根据登记单元编码判断   如登记簿基本信息中有数据  则返回这 条数据  如果无数据  则新增
				String reg_unit_code = regUnitRelMap.get("REG_UNIT_CODE").toString();
				
				//保存登记簿基本信息时   先通过登记单元编码  去数据库中取  如果已经存在      则返回当前存在的登记簿基本信息数据      如果不存在  则新增一条
				Reg_baseInfo reg_baseInfo = getReg_baseInfoByUnitCode(reg_unit_code);
				if(reg_baseInfo != null){
					return reg_baseInfo;
				}
				
				
				
				//保存登记簿基本信息
				//String houser_id = regUnitRelMap.get("HOUSE_ID").toString();
				//String parcel_id = getParcelMapByHouseId(houser_id).get("PARCEL_ID").toString();
				reg_baseInfo = new Reg_baseInfo();
				reg_baseInfo.setBook_code(String.valueOf(reg_baseInfoDao.getSeqId()));
				//reg_baseInfo.setHouse_id(houser_id);
				//reg_baseInfo.setParcel_id(parcel_id);
				reg_baseInfo.setReg_unit_type(regUnitRelMap.get("REG_UNIT_TYPE").toString());
				reg_baseInfo.setReg_unit_code(regUnitRelMap.get("REG_UNIT_CODE").toString());
				reg_baseInfo.setReg_organization(busMainMap.get("REG_STATION").toString());
				try {
					reg_baseInfoDao.save(reg_baseInfo);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("保存登记簿基本信息出错"+e.getMessage());
				}
				return reg_baseInfo;
			}
			/**
			 * 
			 * save_cerno:(保存房地产证号，用于登簿). <br/>
			 * @author xuzz
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public String save_cerno(Map<String,Object> paraMap)
			{
				Row row = new RowImpl();
				row.put("name", "房地产证号");
				String cer_no = FacadeFactory.getIdentifierFacade().getSerialNumber(row);
				if(cer_no != null && cer_no != ""){
					Certificate certificate = new Certificate();
					try {
						String cer_id = certificateDao.queryMapByKey("Register.getCertificateMapByRegCode",paraMap).get("CERTIFICATE_ID").toString();
						if(cer_id == null || cer_id == ""){
							throw new BusinessException("获取房地产证表ID 出错  ID为空");
						}
						certificate.setCertificate_id(cer_id);
						certificate = certificateDao.get(certificate);
						certificate.setCertificate_code(cer_no);
						certificateDao.update(certificate);
					} catch (Exception e) {
						e.printStackTrace();
						throw new BusinessException("获取房地产证表ID 出错  "+e.getMessage());
					}
					
				}
				return cer_no;
			}
			/**
			 * saveHolder 保存权利人
			 * @param paraMap
			 * @param reg_baseInfo
			 */
			public void saveHolder(Map<String,Object> paraMap,Reg_baseInfo reg_baseInfo)
			{
				
				String bus_class_id = paraMap.get("bus_class_id").toString();
				
				//保存权利人集合信息
				HolderRelationship holderRelationship = new HolderRelationship();
				holderRelationship.setRight_rel_id(String.valueOf(holderRelationshipDao.getSeqId()));
				holderRelationship.setBook_code(reg_baseInfo.getBook_code());
				holderRelationship.setReg_code(paraMap.get("reg_code").toString());
				
				//申请人list
				List<Map<String,Object>> appList = null;
				
				//如果当前业务为抵押权
				if(bus_class_id.equals(WbfConstants.MORTGAGE_RIGHT)){
					//抵押权业务时   权利人集合设为无效  
					holderRelationship.setEffective(WbfConstants.UNEFFECTIVE);
					
					paraMap.put("reg_unit_code", reg_baseInfo.getReg_unit_code());
					//根据当前登记单元编号获取权利人数据
					appList = holderRelationshipDao.queryMapListByKey("Register.getApplicaent","where reg_unit_code=:reg_unit_code and bus_id=:bus_id and hol_rel='"+WbfConstants.MORTGAGER+"'", paraMap);
					List<Map<String,Object>>  appMortgeMapList =null;
					String busTypeId = getBusTypeIdByRegIdMap(paraMap);						//通过当前登记编号 获取业务类型ID   用来判断是否属于抵押权转移登记
					//如果是抵押转移登记   则把抵押权受让方保存为抵押权人
					if(busTypeId.equals(WbfConstants.MORTGAGE_SHIFT) || busTypeId.equals(WbfConstants.MAX_MORTGAGE_SHIFT) ){
						appMortgeMapList = holderRelationshipDao.queryMapListByKey("Register.getApplicaent","where bus_id=:bus_id and hol_rel='"+WbfConstants.MORTGAGE_TRANSFEROREE+"'",paraMap);
						if(appMortgeMapList!=null && !appMortgeMapList.isEmpty()){
							//把受让方改为抵押权人并保存到权利人信息表
							for(int i=0;i<appMortgeMapList.size();i++){
								appMortgeMapList.get(i).put("HOL_REL", WbfConstants.MORTGAGEE);
							}
						}
						
					}else{
						//获取权利人类型(hol_rel)为抵押权人的申请人数据    063004
						 appMortgeMapList = holderRelationshipDao.queryMapListByKey("Register.getApplicaent","where bus_id=:bus_id and hol_rel='"+WbfConstants.MORTGAGEE+"'",paraMap);
					}
					//如果抵押权人不为空   则添加到申请人list中
					if(appMortgeMapList != null && !appMortgeMapList.isEmpty()){
						appList.addAll(appMortgeMapList);
					}
				}else{
					//先获取生效的权利人集合    改为失效      再把当前权利人集合改为生效      无生效权利人时不做变更
					HolderRelationship effHolderRelationship = getEffectiveholderRelationshipRegUnitCode(reg_baseInfo.getReg_unit_code());
					if(effHolderRelationship != null){
						effHolderRelationship.setEffective(WbfConstants.UNEFFECTIVE);
						holderRelationshipDao.update(effHolderRelationship);
					}
					
					//其它业务设置前权利人集合失效后    把当前权利人集合设为有效
					holderRelationship.setEffective(WbfConstants.EFFECTIVE);
					
					//其它不会出现多条情况业务的   获取申请人集合
					appList = getAplPerInfoByRegId(paraMap);	
				}
				
				try {
					holderRelationshipDao.save(holderRelationship);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("保存保存权利人集合信息出错"+e.getMessage());
				}
				
				Holder holder = null;
				try {
					if(appList.size()>0)
					{
						for (Map<String,Object> tempApp : appList) {
							holder = new Holder();
							holder.setHolder_id(String.valueOf(holderDao.getSeqId()));
							holder.setRight_rel_id(holderRelationship.getRight_rel_id());
							holder.setHol_type(tempApp.get("APP_TYPE").toString());
							holder.setHol_name(tempApp.get("APP_NAME").toString());
							holder.setHol_cer_type(tempApp.get("APP_CER_TYPE").toString());
							holder.setHol_cer_no(tempApp.get("APP_CER_NO").toString());
							holder.setHol_address(tempApp.get("APP_ADDRESS").toString());
							
							Object holRelObj = tempApp.get("HOL_REL");
							if(holRelObj !=null){
								holder.setHol_rel(holRelObj.toString());
							}
							
							//holder.setDepart_type(tempApp.get("DEPART_TYPE").toString());
							
							Object legalObject = tempApp.get("LEGAL_NAME");//.toString()
							
							//判断  如果法人为空 则不保存法人    
							if(legalObject!=null){
								holder.setLegal_name(legalObject.toString());
							}
							Object agent_name = tempApp.get("AGENT_NAME");
							if(agent_name != null){
								holder.setAgent_name(agent_name.toString());
							}
							
							Object agent_cer = tempApp.get("AGENT_CER");
							if(agent_cer != null){
								holder.setAgent_cer(agent_cer.toString());
							}
							
							Object agent_tel = tempApp.get("AGENT_TEL");
							if(agent_tel != null){
								holder.setAgent_tel(agent_tel.toString());
							}
							
							Object portion = tempApp.get("APP_PORT");
							if(portion != null){
								holder.setPortion(portion.toString());
							}
							//holder.setHis_holder_id(his_holder_id);
							
							holderDao.save(holder);
							
							//his_holder_id = holder.getHolder_id();							//迭代后历史权利人ID 为上一次保存的ID	
							//temp_count++;														//计数自增
					}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("保存保存权利人信息出错"+e.getMessage());
				}
			}
			/**
			 * 
			 * save_ownshipbook:(保存登记簿所有权部分，用于登簿). <br/>
			 * @author xuzz
			 * @param ownershipInfoMap
			 * @param paraMap
			 * @param reg_baseInfo
			 * @param busMainMap
			 * @param certificateDB
			 * @param cer_no
			 * @since JDK 1.6
			 */
			public void saveOwnshipBook(Map<String,Object> ownershipInfoMap,Map<String,Object> paraMap,Reg_baseInfo reg_baseInfo,Map<String,Object> busMainMap,Certificate certificateDB,String cer_no)
			{
				//先把当前登记单元生效的登记簿  改为无效 00    保存当前信息为有生效01   如果无生效记录   则只保存当前为生效的
				String proc_id =  busMainMap.get("PROC_ID").toString();
				Reg_ownership effReg_Ownership = getEffectiveReg_OwnershipByProcId(proc_id);
				if(effReg_Ownership != null){
					effReg_Ownership.setEffective(WbfConstants.UNEFFECTIVE);
					reg_ownershipDao.update(effReg_Ownership);
				}
				
				Reg_ownership   reg_ownership = new Reg_ownership();
				reg_ownership.setOwnership_id(String.valueOf(reg_ownershipDao.getSeqId()));
				reg_ownership.setReg_date(new Date());
				reg_ownership.setReg_code(paraMap.get("reg_code").toString());
				reg_ownership.setBook_code(reg_baseInfo.getBook_code());
				reg_ownership.setRecorder(paraMap.get("recorder").toString());
				reg_ownership.setReg_value(ownershipInfoMap.get("REG_VALUE").toString());
				reg_ownership.setProcdef_id(busMainMap.get("PRO_DEF_ID").toString());
				reg_ownership.setExcursus(certificateDB.getExcursus());
				reg_ownership.setCer_no(cer_no);
				
				
				Object tmpObj = ownershipInfoMap.get("GET_MODE");
				if(tmpObj!=null){
					reg_ownership.setGet_mode(tmpObj.toString());
				}
				
				tmpObj = ownershipInfoMap.get("LU_TERM");
				if(tmpObj!=null){
					reg_ownership.setLu_term(tmpObj.toString());
				}
				
				tmpObj = ownershipInfoMap.get("START_DATE");
				if(tmpObj!=null){
					reg_ownership.setStart_date((Date)ownershipInfoMap.get("START_DATE"));
				}
				
				tmpObj = ownershipInfoMap.get("END_DATE");
				if(tmpObj!=null){
					reg_ownership.setEnd_date((Date)ownershipInfoMap.get("END_DATE"));
				}
				
				tmpObj = ownershipInfoMap.get("HOUSE_USAGE");
				if(tmpObj!=null){
					reg_ownership.setHouse_usage(tmpObj.toString());
				}
				
				tmpObj = ownershipInfoMap.get("HOUSE_ATTR");
				if(tmpObj!=null){
					reg_ownership.setHouse_attr(tmpObj.toString());
				}
				reg_ownership.setEffective(WbfConstants.EFFECTIVE);
				//reg_ownership.setPre_reg_code(getPreRegCodeByRegId(paraMap));						//初始登记  无前登记编号
				try {
					reg_ownershipDao.save(reg_ownership);
				} catch (Exception e) {
					LogUtil.error("保存登记簿所有权部分信息出错"+e.getMessage());
					throw new BusinessException("保存登记簿所有权部分信息出错"+e.getMessage());
				}
				
				updateRegUnitState(paraMap);
				
				//save_holder(paraMap,reg_baseInfo);
			}
			
			
			
			/**
			 * 登簿成功后设置产权状态为有效
			 * @param paraMap  （reg_code,reg_unit_code）
			 */
			public void updateRegUnitState(Map paraMap)
			{
				String whereAndSql = "";								//有登记单元编号的加上登记单元编号条件
				if(paraMap.get("reg_unit_code")!=null){
					 whereAndSql = "and reg_unit_code=:reg_unit_code";
				}
				//登簿成功后设置产权状态为有效
				Reg_relationship regRelationship =  reg_relationshipDao.get("where reg_code=:reg_code "+whereAndSql,paraMap);
				if(regRelationship!=null){
					regRelationship.setReg_state(WbfConstants.EFFECTIVE);
					reg_relationshipDao.update(regRelationship);
				}else{
					LogUtil.error("修改登记单元产权状态出错出错");
				}
			}
			/**
			 * 
			 * save_userightbook:(保存登记簿使用权，登簿). <br/>
			 * @author xuzz
			 * @param allbookInfoMap
			 * @param paraMap
			 * @param reg_baseInfo
			 * @param busMainMap
			 * @param certificateDB
			 * @param cer_no
			 * @since JDK 1.6
			 */
			/**
			 * 
			 * saveOwnshipChange:(保存变更相关信息 插入到房屋历史表、修改房屋表、插入房屋趋势表).
			 *
			 * @author Joyon
			 * @param busMainMap
			 * @since JDK 1.6
			 */
			public void  saveHouseChange(Map<String,Object> busMainMap){
				String proc_id = busMainMap.get("proc_id").toString();
				String bus_id = busMainMap.get("BUS_ID").toString();
				String reg_code = busMainMap.get("REG_CODE").toString();
				Map<String,Object> paraMap = new HashMap<String,Object>();
				paraMap.put("bus_id",bus_id);			//查询数据的参数
				
				//获取变更项MapList
				List<Map<String,Object>> changeRecordMapList = FacadeFactory.getChangeRecordFacade().getChangeRecordMapListByProcId(proc_id);
				String tempChangeCode = null;						//变更记录项  code
				
				House newHouse = null;								//新房屋表数据
				//House hisHouse = null;								//新房屋表数据
				HouseHistory houseHistory = null;					//房屋历史表
				
				//如果变更数据项  表不为空
				if(changeRecordMapList != null){
					
//					//判断是否有房屋数据变更  有的话就获取到房屋表中数据
//					for(Map tempMap:changeRecordMapList){
//						tempChangeCode = tempMap.get("CHANGE_CODE").toString();
//						if(tempChangeCode.equals(WbfConstants.PRODUCT_NAME) || tempChangeCode.equals(WbfConstants.HOUSE_LOCATION) || tempChangeCode.equals(WbfConstants.BUILD_AREA) || tempChangeCode.equals(WbfConstants.TAONEI_AREA) || tempChangeCode.equals(WbfConstants.FLATLET_USAGE) || tempChangeCode.equals(WbfConstants.HOUSE_ATTR)){
//							newHouse = houseDao.get("where ADV_HOUSE_CODE = (select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=:bus_id)", paraMap);
//							break;
//						}
//						
//					}
					newHouse = houseDao.get("where ADV_HOUSE_CODE = (select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=:bus_id)", paraMap);
					//如果房屋信息不为空      copy一份到房屋历史表
//					if(newHouse!=null){
						houseHistory = new HouseHistory();
						houseHistoryDao.copyProperties(houseHistory, newHouse);
						houseHistory.setHouse_his_id(SequenceUtil.getGlobalSeqID());
						String last_bus_id = getLastBusIdByRegCode(reg_code);
						houseHistory.setLast_bus_id(last_bus_id);
						
						//保存一房屋历史表
						houseHistoryDao.save(houseHistory);
						
//					}
					
					/***** 保存变更趋势表数据  ****/
					String strOldDataItem = "";		//变更前数据
					String strNewDataItem = "";		//变更后数据
					String strTemp = "";				//用来判断加了几次
					int trendCount = 0;
					
					//获得变更前数据 strOldDataItem 变更后数据     如果有登记价格    更新申请表中的登记价格
					for(Map tempMap:changeRecordMapList){
						
						tempChangeCode = tempMap.get("CHANGE_CODE").toString();
						//tempMap.get("");
						if(tempChangeCode.equals(WbfConstants.PRODUCT_NAME)){
							newHouse.setPro_name(tempMap.get("NEW_DATA").toString());
							strTemp="房地产名称:";
						}else if(tempChangeCode.equals(WbfConstants.HOUSE_LOCATION)){
							newHouse.setHouse_location(tempMap.get("NEW_DATA").toString());
							strTemp="房屋坐落:";
						}else if(tempChangeCode.equals(WbfConstants.BUILD_AREA)){
							newHouse.setBuild_area(Double.valueOf(tempMap.get("NEW_DATA").toString()));
							strTemp="建筑面积:";
						}else if(tempChangeCode.equals(WbfConstants.TAONEI_AREA)){
							newHouse.setTaonei_area((Double.valueOf(tempMap.get("NEW_DATA").toString())));
							strTemp="套内面积:";
						}else if(tempChangeCode.equals(WbfConstants.FLATLET_USAGE)){
							newHouse.setFlatlet_usage((tempMap.get("NEW_DATA").toString()));
							strTemp="房屋用途:";
						}else if(tempChangeCode.equals(WbfConstants.HOUSE_ATTR)){
							newHouse.setHouse_kind((tempMap.get("NEW_DATA").toString()));
							strTemp="购房性质:";
						}else if(tempChangeCode.equals(WbfConstants.REG_VALUE)){
							strTemp="登记价格:";
							//如果有登记价格   更新所有权登记信息表登记价格    
							BusOwnership busOwnership = FacadeFactory.getCommonFacade().getBusOwnershipByProcId(proc_id);
							busOwnership.setReg_value((Float.valueOf(tempMap.get("NEW_DATA").toString())));
							busOwnershipDao.update(busOwnership);
							
						}else if(tempChangeCode.equals(WbfConstants.HOL_NAME)){
							strTemp="权利人名称:";
						}else if(tempChangeCode.equals(WbfConstants.HOL_CER_NO)){
							strTemp="身份证号码:";
						}
						
						
						//拼字符串
						if(trendCount>0){
							strOldDataItem+=(","+strTemp+tempMap.get("OLD_DATA").toString());
							
							strNewDataItem+=(","+strTemp+tempMap.get("NEW_DATA").toString());
						}else{
							strOldDataItem+=(strTemp+tempMap.get("OLD_DATA").toString());
							
							strNewDataItem+=(strTemp+tempMap.get("NEW_DATA").toString());
						}
						
						trendCount ++;
					}
					
					HouseTrend houseTrend = new HouseTrend();
					
					if(newHouse == null){
						newHouse = houseDao.get("where ADV_HOUSE_CODE = (select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=:bus_id)", paraMap);
					}
					
					houseTrend.setTrend_id(SequenceUtil.getGlobalSeqID());
					houseTrend.setBg_time(new Date());
					houseTrend.setPre_content(strOldDataItem);
					houseTrend.setNext_content(strNewDataItem);
					houseTrend.setFlatlet_code(newHouse.getAdv_house_code());
					if(houseHistory!=null){
						houseTrend.setHistory_id(houseHistory.getHouse_his_id());
					}
					houseTrend.setFlatlet_id(newHouse.getHouse_id());
					houseTrendDao.save(houseTrend);
					/**** 保存变更趋势表信息结束 ****/
					
					//修改房屋表数据
					houseDao.update(newHouse);
					
					//
				}
				 
				
			}
			
			/**
			 * 
			 * getLastBusIdByRegCode:(通过当前登记编号获取前一次业务ID).
			 *
			 * @author Joyon
			 * @param reg_code
			 * @return
			 * @since JDK 1.6
			 */
			private String getLastBusIdByRegCode(String reg_code) throws BusinessException{
				Map<String,Object> paraMap = new HashMap<String,Object>();
				paraMap.put("reg_code",reg_code);
				String whereSql = "where reg_code = (select LAST_REG_CODE from BUS_REGUNITREL where reg_code=:reg_code)";
				String last_bus_id = null;
				try {
					last_bus_id = reg_relationshipDao.get(whereSql, paraMap).getBus_id();
				} catch (Exception e) {
					LogUtil.error("RegisterFacade.getLastBusIdByRegCode  获取前业务ID出错"+e.getMessage());
				}
				return last_bus_id;
			}
			/**
			 * 
			 * saveOwnshipChange:(保存变更相关信息 插入到房屋历史表、修改房屋表、插入房屋趋势表).
			 *
			 * @author Joyon
			 * @param busMainMap
			 * @since JDK 1.6
			 */
			public void  saveParcelChange(Map<String,Object> busMainMap){
				String proc_id = busMainMap.get("proc_id").toString();
				String bus_id = busMainMap.get("BUS_ID").toString();
				String reg_code = busMainMap.get("REG_CODE").toString();
				Map<String,Object> paraMap = new HashMap<String,Object>();
				paraMap.put("bus_id",bus_id);			//查询数据的参数
				
				//获取变更项MapList
				List<Map<String,Object>> changeRecordMapList = FacadeFactory.getChangeRecordFacade().getChangeRecordMapListByProcId(proc_id);
				String tempChangeCode = null;						//变更记录项  code
				
				Land newLand = null;								//新房屋表数据
				//House hisHouse = null;								//新房屋表数据
				LandHistory landHistory = null;					//房屋历史表
				
				//如果变更数据项  表不为空
				if(changeRecordMapList != null){
					
					//判断是否有房屋数据变更  有的话就获取到房屋表中数据
//					for(Map tempMap:changeRecordMapList){
//						tempChangeCode = tempMap.get("CHANGE_CODE").toString();
//						if(tempChangeCode.equals(WbfConstants.PARCEL_CODE) || tempChangeCode.equals(WbfConstants.PARCEL_AREA) || tempChangeCode.equals(WbfConstants.REAL_USAGE) || tempChangeCode.equals(WbfConstants.LOCATION_AREA) || tempChangeCode.equals(WbfConstants.LAND_ADDRESS) || tempChangeCode.equals(WbfConstants.USE_PER)){
//							newLand = landDao.get("where PARCEL_CODE = (select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=:bus_id)", paraMap);
//							break;
//						}
//						
//					}
					
					//暂时全部保存到土地历史表中
					newLand = landDao.get("where PARCEL_CODE = (select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=:bus_id)", paraMap);
					//如果房屋信息不为空      copy一份到房屋历史表
					//if(newLand!=null){
						landHistory = new LandHistory();
						landHistoryDao.copyProperties(landHistory, newLand);
						landHistory.setParcel_his_id(SequenceUtil.getGlobalSeqID());
						String last_bus_id = getLastBusIdByRegCode(reg_code);
						landHistory.setLast_bus_id(last_bus_id);
						
						//保存一房屋历史表
						landHistoryDao.save(landHistory);
						
					//}
					
					/***** 保存变更趋势表数据  ****/
					String strOldDataItem = "";		//变更前数据
					String strNewDataItem = "";		//变更后数据
					String strTemp = "";				//用来判断加了几次
					int trendCount = 0;
					
					Bususeright bususeright = FacadeFactory.getCommonFacade().getBususerightByProcId(proc_id);		//用来更新过程表中的数据
					//获得变更前数据 strOldDataItem 变更后数据     如果有登记价格    更新申请表中的登记价格
					for(Map tempMap:changeRecordMapList){
						
						tempChangeCode = tempMap.get("CHANGE_CODE").toString();
						//tempMap.get("");
						if(tempChangeCode.equals(WbfConstants.PARCEL_CODE)){
							newLand.setParcel_code(tempMap.get("NEW_DATA").toString());
							strTemp="宗地号:";
						}else if(tempChangeCode.equals(WbfConstants.PARCEL_AREA)){
							newLand.setParcel_area(tempMap.get("NEW_DATA").toString());
							strTemp="宗地面积:";
						}else if(tempChangeCode.equals(WbfConstants.REAL_USAGE)){
							newLand.setReal_usage(tempMap.get("NEW_DATA").toString());
							strTemp="土地用途:";
						}else if(tempChangeCode.equals(WbfConstants.LOCATION_AREA)){						//所在区   暂时没有
							//newLand.setTaonei_area((Double.valueOf(tempMap.get("NEW_DATA").toString())));
							strTemp="所在区:";
						}else if(tempChangeCode.equals(WbfConstants.LAND_ADDRESS)){
							newLand.setLand_address((tempMap.get("NEW_DATA").toString()));
							strTemp="宗地坐落:";
						}else if(tempChangeCode.equals(WbfConstants.USE_PER)){
							newLand.setUse_per(tempMap.get("NEW_DATA").toString());
							strTemp="使用年限:";
						}else if(tempChangeCode.equals(WbfConstants.PAR_REG_VALUE)){
							strTemp="土地价款:";
							//如果有登记价格   更新所有权登记信息表登记价格    
							
							bususeright.setGet_price(Double.valueOf(tempMap.get("NEW_DATA").toString()));
							bususerightDao.update(bususeright);
							
						}else if(tempChangeCode.equals(WbfConstants.ADD_PARCEL_PRICE)){
							strTemp="补地价款:";
						}else if(tempChangeCode.equals(WbfConstants.HOL_NAME)){
							strTemp="权利人名称:";
						}else if(tempChangeCode.equals(WbfConstants.HOL_CER_NO)){
							strTemp="身份证号码:";
						}
						
						
						//拼字符串
						if(trendCount>0){
							strOldDataItem+=(","+strTemp+tempMap.get("OLD_DATA").toString());
							
							strNewDataItem+=(","+strTemp+tempMap.get("NEW_DATA").toString());
						}else{
							strOldDataItem+=(strTemp+tempMap.get("OLD_DATA").toString());
							
							strNewDataItem+=(strTemp+tempMap.get("NEW_DATA").toString());
						}
						
						trendCount ++;
					}
					LandTrend landTrend = new LandTrend();
					
					
					//未更改宗地基本信息的  则从宗地表中获取一次数据
					if(newLand == null){
						newLand = landDao.get("where PARCEL_CODE = (select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=:bus_id)", paraMap);
					}
					
					landTrend.setTrend_id(SequenceUtil.getGlobalSeqID());
					landTrend.setBg_time(new Date());
					landTrend.setPre_content(strOldDataItem);
					landTrend.setNext_content(strNewDataItem);
					landTrend.setParcel_code(newLand.getParcel_code());
					if(landHistory!=null){
						landTrend.setHistory_id(landHistory.getParcel_his_id());
					}
					landTrend.setParcel_id(newLand.getParcel_id());
					landTrendDao.save(landTrend);
					/**** 保存变更趋势表信息结束 ****/
					
					//修改宗地表数据
					landDao.update(newLand);
					
					//
				}
				 
				
			}
			/**
			 * 使用权登记簿，保存
			 * @param saveUserightbook
			 * @param paraMap
			 * @param reg_baseInfo
			 * @param busMainMap
			 * @param certificateDB
			 * @param cer_no
			 */
			public void saveUserightbook(Map<String,Object> allbookInfoMap,Map<String,Object> paraMap,Reg_baseInfo reg_baseInfo,Map<String,Object> busMainMap,Certificate certificateDB,String cer_no)
			{
				//获取登记簿中有效的数据      如果有改为无效   无则不做操作
				Reg_Useright effReguseright = getEffectiveReg_userightByRegUnitCode(reg_baseInfo.getReg_unit_code());
				if(effReguseright != null){
					effReguseright.setEffective(WbfConstants.UNEFFECTIVE);
					reg_UserightDao.update(effReguseright);
				}
				
				Reg_Useright reguseright=new Reg_Useright();
				//Reg_ownership   reg_ownership = new Reg_ownership();
				reguseright.setUseright_id(String.valueOf(reg_UserightDao.getSeqId()));
				reguseright.setReg_date(new Date());
				reguseright.setReg_code(paraMap.get("reg_code").toString());
				reguseright.setBook_code(reg_baseInfo.getBook_code());
				reguseright.setRecorder(paraMap.get("recorder").toString());
				reguseright.setReg_pri(allbookInfoMap.get("GET_PRICE").toString());
				reguseright.setProcdef_id(busMainMap.get("PRO_DEF_ID").toString());
				reguseright.setExcursus(certificateDB.getExcursus());
				reguseright.setCer_no(cer_no);				
				//reguseright.setGet_mode(ownershipInfoMap.get("GET_MODE").toString());
				reguseright.setLu_term(allbookInfoMap.get("USE_LIMIT").toString());
				reguseright.setStart_date((Date)allbookInfoMap.get("START_DATE"));
				reguseright.setEnd_date((Date)allbookInfoMap.get("END_DATE"));
				reguseright.setLand_use(allbookInfoMap.get("LAND_USE").toString());
				reguseright.setUseright_type(allbookInfoMap.get("USERIGHT_TYPE").toString());
				reguseright.setEffective(WbfConstants.EFFECTIVE);
				//reg_ownership.setPre_reg_code(getPreRegCodeByRegId(paraMap));						//初始登记  无前登记编号
				try {
					//throw new BusinessException("fffff");
					reg_UserightDao.save(reguseright);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("保存登记簿所有权部分信息出错"+e.getMessage());
				}
				updateRegUnitState(paraMap);
				//save_holder(paraMap,reg_baseInfo);
			}
			/**
			 * 
			 * save_realstateCan:(保存登记簿房地产证注销登记，登簿). <br/>
			 * @author xuzz
			 * @since JDK 1.6
			 */
			@Transactional
			public void save_realstateCan(Map<String,Object> allbookInfoMap,Map<String,Object> paraMap,Reg_baseInfo reg_baseInfo,Map<String,Object> busMainMap)
			{
				//使用权
				Reg_Useright reguseright=new Reg_Useright();
				//所有权
				Reg_ownership reg_ownership=new Reg_ownership();		
				//通过登记编号获取登记单元关联表数据
				Map<String , Object> relationMap= getRegUnitRelMapByRegId(paraMap);
				
				//paraMap.put("REG_UNIT_TYPE", relationMap.get("REG_UNIT_TYPE").toString());
				if(relationMap.get("REG_UNIT_TYPE").toString().equals(WbfConstants.PARCEL))
				{
					//获取登记簿中有效的数据      如果有改为无效   无则不做操作
					Reg_Useright effReguseright = getEffectiveReg_userightByRegUnitCode(reg_baseInfo.getReg_unit_code());
					if(effReguseright != null){
						effReguseright.setEffective(WbfConstants.UNEFFECTIVE);
						reg_UserightDao.update(effReguseright);
					}
					reguseright.setReason(allbookInfoMap.get("reason").toString());
					reguseright.setUseright_id(String.valueOf(reg_UserightDao.getSeqId()));
					reguseright.setReg_date(new Date());
					reguseright.setReg_code(paraMap.get("reg_code").toString());
					reguseright.setBook_code(reg_baseInfo.getBook_code());
					reguseright.setRecorder(paraMap.get("recorder").toString());
					reguseright.setEffective(WbfConstants.EFFECTIVE);
					reguseright.setProcdef_id(busMainMap.get("PRO_DEF_ID").toString());
					reg_ownership.setPre_reg_code(getPreRegCodeByRegId(paraMap));						//初始登记  无前登记编号
					try {
						//throw new BusinessException("fffff");
						reg_UserightDao.save(reguseright);
					} catch (Exception e) {
						e.printStackTrace();
						throw new BusinessException("保存登记簿所有权部分信息出错"+e.getMessage());
					}
				}
				else
				{
					String proc_id =  busMainMap.get("PROC_ID").toString();
					Reg_ownership effReg_Ownership = getEffectiveReg_OwnershipByProcId(proc_id);
					if(effReg_Ownership != null){
						effReg_Ownership.setEffective(WbfConstants.UNEFFECTIVE);
						reg_ownershipDao.update(effReg_Ownership);
					}
					//reg_ownership.copyProperties(reg_ownership, allbookInfoMap);
					reg_ownership.setReason(allbookInfoMap.get("reason").toString());
					reg_ownership.setOwnership_id(String.valueOf(reg_ownershipDao.getSeqId()));
					reg_ownership.setReg_date(new Date());
					reg_ownership.setReg_code(paraMap.get("reg_code").toString());
					reg_ownership.setBook_code(reg_baseInfo.getBook_code());
					reg_ownership.setRecorder(paraMap.get("recorder").toString());					
					reg_ownership.setEffective(WbfConstants.EFFECTIVE);
					reg_ownership.setProcdef_id(busMainMap.get("PRO_DEF_ID").toString());
					reg_ownership.setPre_reg_code(getPreRegCodeByRegId(paraMap));						//初始登记  无前登记编号
					try {
						reg_ownershipDao.save(reg_ownership);
					} catch (Exception e) {
						LogUtil.error("保存登记簿所有权部分信息出错"+e.getMessage());
						throw new BusinessException("保存登记簿所有权部分信息出错"+e.getMessage());
					}
				}
				updateRegUnitState(paraMap);
			}
			
			/**
			 * 
			 * saveRevokeapproval:(撤销核准登簿操作，将所有权或者使用权里面的有效数据改为无效). <br/>
			 * @author xuzz
			 * @since JDK 1.6
			 */
			@Transactional
			public void saveRevokeapproval(Map<String,Object> paraMap,Map<String,Object> busMainMap)
			{
				//使用权
				Reg_Useright reguseright=new Reg_Useright();
				//所有权
				Reg_ownership reg_ownership=new Reg_ownership();		
				//通过登记编号获取登记单元关联表数据
				Map<String , Object> relationMap= getRegUnitRelMapByRegId(paraMap);
				if(relationMap.get("REG_UNIT_TYPE").toString().equals(WbfConstants.PARCEL))
				{
					//获取登记簿中有效的数据      如果有改为无效   无则不做操作
					Reg_Useright effReguseright = getEffectiveReg_userightByRegUnitCode(relationMap.get("REG_UNIT_CODE").toString());
					if(effReguseright != null){
						effReguseright.setEffective(WbfConstants.UNEFFECTIVE);
						reg_UserightDao.update(effReguseright);
					}
				}
				else
				{
					String proc_id =  busMainMap.get("PROC_ID").toString();
					Reg_ownership effReg_Ownership = getEffectiveReg_OwnershipByProcId(proc_id);
					if(effReg_Ownership != null){
						effReg_Ownership.setEffective(WbfConstants.UNEFFECTIVE);
						reg_ownershipDao.update(effReg_Ownership);
					}	
				}
			}
			
			
			
			/**
			 * 
			 * saveDemurrerbook:(保存登记簿异议，登簿). <br/>
			 * @author xuzz
			 * @param allbookInfoMap
			 * @param paraMap
			 * @param reg_baseInfo
			 * @param busMainMap
			 * @param certificateDB
			 * @param cer_no  Reg_Demurrer reg_DemurrerDao
			 * @since JDK 1.6
			 */
			@Transactional
			public void saveDemurrerbook(Map<String,Object> allbookInfoMap,Reg_baseInfo reg_baseInfo,Map<String,Object> paraMap,Map<String,Object> busMainMap,Map<String,Object> regUnitMap)
			{
				Reg_Demurrer demurrer=new Reg_Demurrer();

				/*List<Map<String,Object>> all = getBKcerno(paraMap);
				if(all.size()>0)
				{
					for(Map tempMap:all){*/
				//获取当前登记单元生效的 房地产证号		
				String cerNo = null;
				try {
					cerNo = getEffectiveCerNoByRegUnitCode(reg_baseInfo
							.getReg_unit_code());
				} catch (Exception e) {
					LogUtil.error("RegisterFacade.save_mortgagebook 获取生效房地产证号出错"+e.getMessage());
				}
				demurrer.setDemurrer_id(reg_DemurrerDao.getSeqId());
				demurrer.setReg_code(paraMap.get("reg_code").toString());
				if(allbookInfoMap!=null)
				{
					demurrer.setDiss_item(allbookInfoMap.get("DISS_ITEM").toString());
				}
				demurrer.setRecorder(paraMap.get("recorder").toString());
				demurrer.setBook_code(reg_baseInfo.getBook_code());
				demurrer.setReg_date(new Date());
				demurrer.setCer_no(cerNo);
				demurrer.setPre_reg_code(getPreRegCodeByRegId(paraMap));
				demurrer.setEffective(WbfConstants.EFFECTIVE);
				demurrer.setProcdef_id(busMainMap.get("PRO_DEF_ID").toString());	
				try {
					updateRegUnitState(paraMap);
					reg_DemurrerDao.save(demurrer);
					if(!busMainMap.get("PRO_DEF_ID").toString().equals(WbfConstants.HDEMURRER)){
						updateBkDemurrerState(regUnitMap.get("LAST_REG_CODE").toString(),regUnitMap.get("REG_UNIT_CODE").toString(),WbfConstants.UNEFFECTIVE);
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("保存异议登记登记簿信息出错"+e.getMessage());
				}		
					//}
				//}
			}
			
			
			
			
			/**
			 * 
			 * saveArrachbook:(保存登记簿查封，登簿). <br/>
			 * @author xuzz
			 * @param allbookInfoMap
			 * @param paraMap
			 * @param reg_baseInfo
			 * @param busMainMap
			 * @param certificateDB
			 * @param cer_no  Reg_Distrain reg_DistrainDao
			 * @since JDK 1.6
			 */
			@Transactional
			public void saveArrachbook(Map<String,Object> allbookInfoMap,Reg_baseInfo reg_baseInfo,Map<String,Object> paraMap,Map<String,Object> busMainMap,Map<String,Object> regUnitMap)
			{
				Reg_Distrain regdistrain=new Reg_Distrain();
				
				Reg_Distrain distrain=new Reg_Distrain();
				//获取当前登记单元生效的 房地产证号		
				String cerNo = null;
				Map resultMap =new HashMap();
				try {
					cerNo = getEffectiveCerNoByRegUnitCode(reg_baseInfo
							.getReg_unit_code());
				} 
				catch (Exception e) {
					LogUtil.error("RegisterFacade.save_mortgagebook 获取生效房地产证号出错"+e.getMessage());
				}
				try {
							regdistrain.copyProperties(regdistrain, allbookInfoMap);
							regdistrain.setDistress_id(reg_DistrainDao.getSeqId());
							regdistrain.setReg_code(paraMap.get("reg_code").toString());
							regdistrain.setRecorder(paraMap.get("recorder").toString());
							regdistrain.setBook_code(reg_baseInfo.getBook_code());
							regdistrain.setReg_date(new Date());
							regdistrain.setExcursus(allbookInfoMap.get("REMARK").toString());
							regdistrain.setCer_no(cerNo);
							regdistrain.setPre_reg_code(regUnitMap.get("LAST_REG_CODE").toString());
							regdistrain.setEffective(WbfConstants.EFFECTIVE);
							regdistrain.setProcdef_id(busMainMap.get("PRO_DEF_ID").toString());	
							//续封,将查封业务标记为无效,,并且将该续封类型修改为查封
							if(allbookInfoMap.get("DIS_TYPE").toString().equals(WbfConstants.T_REATTACH))
							{
								resultMap= FacadeFactory.getApplicantFacade().getAttachByRegcode(reg_code,WbfConstants.T_REATTACH).get(0);
								distrain.copyProperties(distrain, resultMap);
								distrain.setEffective(WbfConstants.UNEFFECTIVE);
								reg_DistrainDao.update(distrain);
								regdistrain.setDis_type(WbfConstants.T_ATTACH);
								reg_DistrainDao.save(regdistrain);
								updateRegUnitState(paraMap);
							}
							//轮候查封转查封，将查封业务标记为无效，将该轮候查封业务修改为查封,只更新，不保存
							else if(allbookInfoMap.get("DIS_TYPE").toString().equals(WbfConstants.T_CHATTACH)||allbookInfoMap.get("DIS_TYPE").toString().equals(WbfConstants.T_UNCHATTACH))
							{
								Map resultAttach =new HashMap();
								Reg_Distrain distrainAttach=new Reg_Distrain();
								//轮候查封数据
								resultMap=FacadeFactory.getApplicantFacade().getAttachByRegcode(reg_code,WbfConstants.T_CHATTACH).get(0);
								distrain.copyProperties(distrain, resultMap);
								distrain.setEffective(WbfConstants.EFFECTIVE);
								distrain.setRecorder(paraMap.get("recorder").toString());
								distrain.setStart_date(new Date());
								Calendar curr = Calendar.getInstance(); 
								curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)+Integer.valueOf(distrain.getDis_limit())); 
								Date date=curr.getTime();
								distrain.setEnd_date(date);
								distrain.setReg_date(new Date());
								Reg_Distrain redistrain=new Reg_Distrain();
								redistrain.copyProperties(redistrain, resultMap);
								redistrain.setDis_type(WbfConstants.T_ATTACH);
								reg_DistrainDao.update(distrain);
								//查封数据
								resultAttach= FacadeFactory.getApplicantFacade().getAttachByRegcode(reg_code,WbfConstants.T_REATTACH).get(0);
								distrainAttach.copyProperties(distrainAttach, resultAttach);
								distrainAttach.setEffective(WbfConstants.UNEFFECTIVE);
								//regdistrain.setDis_type(WbfConstants.T_ATTACH);
								reg_DistrainDao.update(distrainAttach);
							}
							//解除轮候查封，将该轮候查封业务标记为无效
							else if(allbookInfoMap.get("DIS_TYPE").toString().equals(WbfConstants.T_CHATTACH))
							{
								resultMap=FacadeFactory.getApplicantFacade().getAttachByRegcode(reg_code,WbfConstants.T_UNREATTACH).get(0);
								distrain.copyProperties(distrain, resultMap);
								distrain.setEffective(WbfConstants.UNEFFECTIVE);
								reg_DistrainDao.update(distrain);
								reg_DistrainDao.save(regdistrain);
								updateRegUnitState(paraMap);
							}
							else
							{
								reg_DistrainDao.save(regdistrain);
								updateRegUnitState(paraMap);
								if(!busMainMap.get("PRO_DEF_ID").toString().equals(WbfConstants.ATTACH)&&allbookInfoMap.get("DIS_TYPE").toString().equals(WbfConstants.T_UNATTACH)){
								updateBkAttachState(regUnitMap.get("LAST_REG_CODE").toString(),regUnitMap.get("REG_UNIT_CODE").toString(),WbfConstants.UNEFFECTIVE);
							}
						}
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException("保存登记簿查封信息出错"+e.getMessage());
						}
			}
			
			/**
			 * 
			 * save_mortgagebook:保存抵押权登记簿信息. <br/>
			 * @author PANDA
			 * @param allbookInfoMap
			 * @param paraMap
			 * @param reg_baseInfo
			 * @param busMainMap
			 * @since JDK 1.6
			 */
			@Transactional
			public void save_mortgagebook(Map<String,Object> allbookInfoMap,Map<String,Object> paraMap,Map<String,Object> busMainMap,Reg_baseInfo regBaseInfo,Map<String,Object> regUnitMap) throws BusinessException{
				//保存抵押权信息到抵押登记簿中
				//List<Map<String,Object>> all = getBKcerno(paraMap)
				String bus_type_id = busMainMap.get("REG_TYPE").toString();		
				
				//获取当前登记单元生效的 房地产证号		
				String cerNo = null;
				try {
					cerNo = getEffectiveCerNoByRegUnitCode(regBaseInfo
							.getReg_unit_code());
				} catch (Exception e) {
					LogUtil.error("RegisterFacade.save_mortgagebook 获取生效房地产证号出错"+e.getMessage());
				}
				
				Reg_Mortgage mort = null;
				Object tempObj = null;
			    String limit = null;
				//获取上一个业务登记编号
				String lastregcode = null;
				Object tmpLastRegCodeObj = regUnitMap.get("LAST_REG_CODE");
				if(tmpLastRegCodeObj!=null){
					lastregcode =tmpLastRegCodeObj.toString();
				}
				
				//判断抵押起始日期是否非空，为空，则不进行任何操作
				if(allbookInfoMap.get("CREDITOR_START_DATE") != null){
				 limit = "从"+allbookInfoMap.get("CREDITOR_START_DATE").toString()+"到"+allbookInfoMap.get("CREDITOR_END_DATE").toString();
				}
				mort = new Reg_Mortgage();
				//设置登记簿主键id
				mort.setMort_id(reg_MortgageDao.getSeqId());
				//设置登记编号
                mort.setReg_code(paraMap.get("reg_code").toString());
                //设置抵押类型
                tempObj = allbookInfoMap.get("MORT_TYPE");
    			if(tempObj != null){
    				mort.setMort_type(tempObj.toString());
    			}
                //设置一般抵押
                mort.setGen_mort("");
                //设置最高额抵押
                mort.setMax_mort("");
                //设置被担保的主债权数额
                tempObj = allbookInfoMap.get("MORT_ASSURE_RIGHT");
    			if(tempObj != null){
    				mort.setAssure_amount(tempObj.toString());
    			}
                //设置担保范围
    			tempObj = allbookInfoMap.get("ASSUER_RANGE");
    			if(tempObj != null){
    				mort.setAssuer_range(tempObj.toString());
    			}             
				//设置债务履行期限
    			if(limit != null){
                mort.setDebt_dis_limit(limit);
    			}
                //设置房地产蒸好
    			mort.setCer_no(cerNo);
				//设置抵押顺位
    			tempObj = allbookInfoMap.get("MORT_SEQ");
    			if(tempObj != null){
    				mort.setMort_seq(tempObj.toString());
    			} 
				//设置登记时间
				mort.setReg_date(new Date());
				//设置登簿人
				mort.setRecorder(paraMap.get("recorder").toString());
				//设置登记簿id
    			mort.setBook_code(regBaseInfo.getBook_code());
				//设置最高额债权确定事实
    			tempObj = allbookInfoMap.get("MAX_AMOUNT");
    			if(tempObj != null){
    				mort.setMax_mort(tempObj.toString());
    				mort.setMax_amount(tempObj.toString());
    			} 
				//设置确定担保的债权数额
    			tempObj = allbookInfoMap.get("SURE_AMOUNT");
    			if(tempObj != null){
    				mort.setSure_amount(tempObj.toString());
    			} 
				//设置前登记编号
				mort.setPre_reg_code(lastregcode);
				//设置借款人
				tempObj = allbookInfoMap.get("BORROWER");
    			if(tempObj != null){
    				mort.setBorrower(tempObj.toString());
    			} 
				//设置流程定义id（登记类型）
    			tempObj = busMainMap.get("PRO_DEF_ID");
    			if(tempObj != null){
    				mort.setProcdef_id(tempObj.toString());
    			} 
    			//设置抵押记录为有效状态
    			//如果是抵押注销则设置无效  
    			if(bus_type_id.equals(WbfConstants.MAX_MORTGAGE_CANCEL) || bus_type_id.equals(WbfConstants.MORTGAGE_CANCEL)){
    				mort.setEffective(WbfConstants.UNEFFECTIVE);
    			}else{
    				mort.setEffective(WbfConstants.EFFECTIVE);
    			}
    		
				try {
					reg_MortgageDao.save(mort);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("保存登记簿抵押权部分信息出错"+e.getMessage());
				}
				String reg_unit_code = regBaseInfo.getReg_unit_code();
				paraMap.put("reg_unit_code", reg_unit_code);
				//更新登记关联表中产权状态
				updateRegUnitState(paraMap);
				
				//如果是抵押转移  则把上一条抵押记录失效
				
				if(bus_type_id.equals(WbfConstants.MORTGAGE_SHIFT) || bus_type_id.equals(WbfConstants.MORTGAGE_CHANGE) || bus_type_id.equals(WbfConstants.MAX_MORTGAGE_SHIFT) || bus_type_id.equals(WbfConstants.MAX_MORTGAGE_CHANGE)|| bus_type_id.equals(WbfConstants.MAX_CONFIRM_REG)|| bus_type_id.equals(WbfConstants.MAX_MORTGAGE_CANCEL)|| bus_type_id.equals(WbfConstants.MORTGAGE_CANCEL)){
					
					String orig_mort_reg_code = regUnitMap.get("LAST_REG_CODE").toString();
					//设置当前登记单元愿 抵押记录状态失效
					updateBkMortState(orig_mort_reg_code,reg_unit_code,WbfConstants.UNEFFECTIVE);
				}
			}
				
				
				
				
			
			
			/**
			 * 
			 * saveMortHoldertorel:抵押设立登记中，保存数据到权利人关联表中. <br/>
			 * @author PANDA
			 * @param m
			 * @since JDK 1.6
			 */
			public void saveMortgagerToHolder(Map m){
				//根据登记编号查询，权利人关联表中是否已存在数据
				List<Map<String, Object>> check = mortDao.queryMapListByKey("Register.getRelByRegcode",m);
				//获取参数中的登记编号
				String reg_code = m.get("reg_code").toString();
				//若不存在数据，新增记录
				if(check.isEmpty()){
					List<Map<String, Object>> regunitlist = mortDao.queryMapListByKey("Register.getRegunitList", m);
					HolderRelationship relship = null;
					Map map = null;
					//循环保存登记编号、登记簿id到权利人关联表中
					for(Map rel:regunitlist){
						relship = new HolderRelationship();
						//生成权利人关联表id
						relship.setRight_rel_id(holderRelationshipDao.getSeqId());
						//设置登记簿编号
						relship.setBook_code(rel.get("BOOK_CODE").toString());
						//设置登记编号
						relship.setReg_code(reg_code);
						//
						relship.setEffective(WbfConstants.EFFECTIVE);
						//执行保存操作
						holderRelationshipDao.save(relship);
						m.put("type",WbfConstants.MORTGAGER);
						m.put("code",rel.get("REG_UNIT_CODE").toString());
						Holder holder = null;
						     //根据登记单元编号、业务id、权属关系获取申请人信息
							List<Map<String, Object>> app = mortDao.queryMapListByKey("Register.getMortgagerFormApp", m);
							//循环保存申请人到权利人表中
							for(Map tempApp:app){
								//调用保存抵押申请人的方法
								saveMortAppToHolder(tempApp,relship.getRight_rel_id());
								}													
						}	
				}			
			}
			/**
			 * 
			 * saveMortAppToHolder:通用方法，保存抵押申请人到权利人表中. <br/>
			 * @author PANDA
			 * @param tempApp
			 * @param id
			 * @since JDK 1.6
			 */
			public void saveMortAppToHolder(Map tempApp,String id){
				Holder   holder = new Holder();
				//生成权利人主表id
				holder.setHolder_id(holderDao.getSeqId());
				//保存权利人关联表id
				holder.setRight_rel_id(id);
				//保存权利人类型
				holder.setHol_type(tempApp.get("APP_TYPE").toString());
				//保存权利人名称
				holder.setHol_name(tempApp.get("APP_NAME").toString());
				//保存权利人证件类型
				holder.setHol_cer_type(tempApp.get("APP_CER_TYPE").toString());
				//保存权利人证件编号
				holder.setHol_cer_no(tempApp.get("APP_CER_NO").toString());
				//保存权利人地址
				holder.setHol_address(tempApp.get("APP_ADDRESS").toString());
				//保存权利人关系
				holder.setHol_rel(tempApp.get("HOL_REL").toString());
				//保存法人名称
				Object legalObject = tempApp.get("LEGAL_NAME");//.toString() 
				if(legalObject!=null){
					holder.setLegal_name(legalObject.toString());
				}
				//保存代理人名称
				Object agent_name = tempApp.get("AGENT_NAME");
				if(agent_name != null){
					holder.setAgent_name(agent_name.toString());
				}
				//保存代理人证件号码
				Object agent_cer = tempApp.get("AGENT_CER");
				if(agent_cer != null){
					holder.setAgent_cer(agent_cer.toString());
				}
				//保存代理人电话
				Object agent_tel = tempApp.get("AGENT_TEL");
				if(agent_tel != null){
					holder.setAgent_tel(agent_tel.toString());
				}
				//保存代理人证件类型
				Object agent_cer_type = tempApp.get("AGENT_CER_TYPE");
				if(agent_cer_type != null){
					holder.setAgent_cer_type(agent_cer_type.toString());
				}
				//保存份额
				Object portion = tempApp.get("APP_PORT");
				if(portion != null){
					holder.setPortion(portion.toString());
				}						
				holderDao.save(holder);
			}
			/**
			 * 
			 * saveMortgageeToHolder:保存抵押权人到权利人表中. <br/>
			 * TODO(这里描述这个方法适用条件 – 可选).<br/>
			 * @author PANDA
			 * @param m
			 * @since JDK 1.6
			 */
			public void saveMortgageeToHolder(Map m){
				//根据登记编号查询，权利人关联表中是否已存在数据
				List<Map<String, Object>> check = mortDao.queryMapListByKey("Register.getRelByRegcode",m);
				//获取参数中的登记编号
				String reg_code = m.get("reg_code").toString();
				//若不存在数据，新增记录
				if(!check.isEmpty()){
					//
					List<Map<String, Object>> app = mortDao.queryMapListByKey("Register.getMortgageeFormApp", m);
					for(Map rel:check){
						      
						      m.put("type",WbfConstants.MORTGAGEE);
						     //根据登记单元编号、业务id、权属关系获取申请人信息
							
							//循环保存申请人到权利人表中
							for(Map tempApp:app){
								//调用保存抵押申请人的方法
								saveMortAppToHolder(tempApp,rel.get("RIGHT_REL_ID").toString());
								}													
						}	
				}			
			}
		
			
			/**
			 * 
			 * getBKcerno:从登记簿中，获取房地产证号和登记簿编号. <br/>
			 * @author PANDA
			 * @param m
			 * @return
			 * @since JDK 1.6
			 */
			public List<Map<String,Object>> getBKcerno(Map m){
				//根据业务id从登记单元关联表中获取登记单元编号，登记单元类型
				Map macon = new HashMap();
				macon.put("id", m.get("bus_id").toString());
				List<Map<String, Object>> regunitlist = mortDao.queryMapListByKey("Mortgage.getRegunitList", macon);
				List<Map<String, Object>> regesate = new ArrayList<Map<String, Object>>();
				Map resultList = null;	
				//条件集合
				String sql=" and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";
				for(Map map:regunitlist){
					String type = map.get("REG_UNIT_TYPE").toString();
					String code = map.get("REG_UNIT_CODE").toString();
					macon.put("code",code);
					//判断当前登记单元类型为房屋
					if(type.trim().equals(WbfConstants.REG_UNIT_HOUSE)){
						
						resultList = mortDao.queryMapByKey("Register.getBkOwnershipList",sql, macon); 
						
					}
					//判断当前登记单元类型为建筑物
					if(type.trim().equals(WbfConstants.REG_UNIT_BUILDING)){
						resultList = mortDao.queryMapByKey("Register.getBuildInfo",sql, macon);
						
					}
					//判断当前登记单元类型为土地
					if(type.trim().equals(WbfConstants.REG_UNIT_PARCEL)){				
						resultList = mortDao.queryMapByKey("Register.getBkUserightList",sql, macon);		
						
					}
					regesate.add(resultList);			
				}
				
				return regesate;
			}
	
			/**
			 * 
			 * getPrevRegcode:根据当前登记编号，获取前一个业务的登记编号. <br/>
			 * @author PANDA
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public String getPrevRegcode(Map<String,Object> paraMap ){
				
				BusinessMain bus = businessMainDao.queryDomainBykey("Register.getPrevRegcode",paraMap);
				if(bus == null){
					return "";
				}else{
				    return bus.getReg_code();
				}
			}
			
			/**
			 * 
			 * getRelRegcode:根据当前登记编号，获取前一个业务的登记编号,查询登记单元关联表. <br/>
			 * @author xuzz
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public String getRelRegcode(Map<String,Object> paraMap ){
				
				BusinessMain bus = businessMainDao.queryDomainBykey("Register.getRelByRegcode",paraMap);
				if(bus == null){
					return "";
				}else{
				    return bus.getReg_code();
				}
			}
			
			/**
			 * 
			 * saveRegister:(登记簿保存). <br/>
			 * @author xuzz
			 * @param paraMap
			 * @return
			 * @since JDK 1.6  getuserInfoByRegId
			 */
			public Map<String,Object> saveRegister(Map<String,Object> paraMap){
				reg_code = paraMap.get("reg_code").toString();
				//获取八大业务类型id
				String bus_class_id = getBusTypeParentIdByRegId(paraMap);
				//获取八大业务类型    子业务类型id
				String bus_class_sub_id = getBusTypeIdByRegIdMap(paraMap);
				paraMap.put("bus_class_id", bus_class_id);
				//返回的map
				Map<String,Object> resultMap = null;
				 
				//判断是否登簿   如果已经登簿返回  exists   可以重写该方法
				if(isRegisterSave(paraMap)){
					resultMap = new HashMap();
					resultMap.put("result", "exists");
					return resultMap;
				}
				
				//查询相应登记簿信息
				Map<String,Object> allbookInfoMap = getAllbookInfoMapByRegId(paraMap,getDataTableKeyByBusTypeID(bus_class_id,""));
				Map<String,Object> regUnitRelMap = null;
				//查询业务主表
				Map<String,Object> busMainMap = getBusMainMapByRegId(paraMap);
				paraMap.put("bus_id", busMainMap.get("BUS_ID").toString());						//把当前业务id put进参数中
				//查询房地产证表信息
				
				//登记簿基本信息表
				Reg_baseInfo reg_baseInfo = new Reg_baseInfo();
				//保存相应登记簿
				//保存登记簿基本信息
				//reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
				//判断并保存到相应登记簿
				if( bus_class_id.equals(WbfConstants.HOUSE_ONWERSHIP)){
					Certificate certificateDB = certificateDao.get("where bus_id=(select bus_id from bus_main where reg_code = :reg_code)", paraMap);
					//生成房地产证号  并保存到房地产证表中
					String cer_no= save_cerno(paraMap);
					//如果是变更登记  则先保存变更信息
					//regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
					List<Map<String,Object>> regUnitRelMapList = reg_relationshipDao.queryMapListByKey("Register.getRegUnitRelMapByRegId", paraMap);
					//如果是更正登记
					if(bus_class_sub_id.equals(WbfConstants.HOUSE_ONWERSHIP_CHANGE)){
						saveHouseChange(busMainMap);
					}
					//如果是二级转移登记  如果有楼花抵押   插入房地产证号到楼花抵押记录中     --如果有预查封业务  保存房地产证号到查封记录中
					else if(bus_class_sub_id.equals(WbfConstants.HOUSE_ONWERSHIP_SEC)){
						String reg_unit_code = regUnitRelMap.get("REG_UNIT_CODE").toString();
						List<Reg_Mortgage> mortgageList = getEffectiveReg_MortgageListByRegUnitCode(reg_unit_code);
						List<Reg_Distrain> distrainList = getEffectiveReg_DistrainListByRegUnitCode(reg_unit_code);
						//存在楼花抵押记录   保存楼花抵押数据
						if(mortgageList!=null && mortgageList.size()>0){
							for(Reg_Mortgage mortgage:mortgageList){
								mortgage.setCer_no(cer_no);
								reg_MortgageDao.update(mortgage);
							}
						}
						//存在预查封记录 保存房地产证号  到查封记录中
						if(distrainList!=null && distrainList.size()>0){
							for(Reg_Distrain distrain:distrainList){
								distrain.setCer_no(cer_no);
								distrain.setExcursus("二级转移 预查封转查封");
								reg_DistrainDao.update(distrain);
							}
						}
					}
					allbookInfoMap = getAllbookInfoMapByRegId(paraMap,getDataTableKeyByBusTypeID(bus_class_id,""));
					//保存房地产证号
					
					if(regUnitRelMapList!=null && !regUnitRelMapList.isEmpty()){
						//循环保存登簿信息  权利人
						for(Map<String,Object> tempRegUnitRelMap:regUnitRelMapList){
							reg_baseInfo= save_Baseinfo(tempRegUnitRelMap,busMainMap);
							saveOwnshipBook(allbookInfoMap, paraMap,reg_baseInfo,busMainMap,certificateDB,cer_no);
						}
					}
					
					//保存权利人集合以及权利人信息
					saveHolder(paraMap,reg_baseInfo);
				}else if (bus_class_id.equals(WbfConstants.LAND_USERIGHT)){
					Certificate certificateDB = certificateDao.get("where bus_id=(select bus_id from bus_main where reg_code = :reg_code)", paraMap);
					regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
					//保存登记簿基本信息
					reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
					if(bus_class_sub_id.equals(WbfConstants.LAND_CHANGE)){
						saveParcelChange(busMainMap);
					}
					allbookInfoMap = getAllbookInfoMapByRegId(paraMap,getDataTableKeyByBusTypeID(bus_class_id,""));
					//保存房地产证号
					String cer_no= save_cerno(paraMap);
					//reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
					saveUserightbook(allbookInfoMap, paraMap,reg_baseInfo,busMainMap,certificateDB,cer_no);
					//保存权利人集合以及权利人信息
					saveHolder(paraMap,reg_baseInfo);
				}else if(bus_class_id.equals(WbfConstants.MORTGAGE_RIGHT)){
					List<Map<String,Object>> regUnitRelMapList = reg_relationshipDao.queryMapListByKey("Register.getRegUnitRelMapByRegId", paraMap);
					
					if(regUnitRelMapList!=null && !regUnitRelMapList.isEmpty()){
						//循环保存登簿信息  权利人
						for(Map<String,Object> tempRegUnitRelMap:regUnitRelMapList){
							reg_baseInfo= save_Baseinfo(tempRegUnitRelMap,busMainMap);
							save_mortgagebook(allbookInfoMap, paraMap, busMainMap,reg_baseInfo,tempRegUnitRelMap);
							if(!(bus_class_sub_id.equals(WbfConstants.MORTGAGE_CANCEL) || bus_class_sub_id.equals(WbfConstants.MAX_MORTGAGE_CANCEL))){
								saveHolder(paraMap,reg_baseInfo);
							}
						}
					}else{
						LogUtil.error("RegisterFacade.saveRegiser 获取登记单元关联表数据失败，数据为空");
					}
					//保存抵押记录到登记簿
					//save_mortgagebook(allbookInfoMap, paraMap, busMainMap)
					
					//保存抵押人信息到权利人表中
					//saveMortgagerToHolder(paraMap);
					//保存抵押权人信息到权利人表中
					//saveMortgageeToHolder(paraMap);
				}else if(bus_class_id.equals(WbfConstants.ATTACH_DISTRAIN)){
					List<Map<String,Object>> regUnitRelMapList = reg_relationshipDao.queryMapListByKey("Register.getRegUnitRelMapByRegId", paraMap);
					for(Map<String,Object> tempRegUnitRelMap:regUnitRelMapList){
						reg_baseInfo= save_Baseinfo(tempRegUnitRelMap,busMainMap);
						saveArrachbook(allbookInfoMap, reg_baseInfo,paraMap, busMainMap,tempRegUnitRelMap);
						//saveHolder(paraMap,reg_baseInfo);
						
					}
					//regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
					//reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
					//saveArrachbook(allbookInfoMap,reg_baseInfo, paraMap, busMainMap,certificateDB);
				}
				else if(bus_class_id.equals(WbfConstants.DEMURRER)){
					List<Map<String,Object>> regUnitRelMapList = reg_relationshipDao.queryMapListByKey("Register.getRegUnitRelMapByRegId", paraMap);
					for(Map<String,Object> tempRegUnitRelMap:regUnitRelMapList){
						reg_baseInfo= save_Baseinfo(tempRegUnitRelMap,busMainMap);
						saveDemurrerbook(allbookInfoMap, reg_baseInfo,paraMap, busMainMap,tempRegUnitRelMap);
						saveHolder(paraMap,reg_baseInfo);
						
					}
					/*regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
					reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
					saveDemurrerbook(allbookInfoMap,reg_baseInfo, paraMap, busMainMap,certificateDB);
					//保存权利人集合以及权利人信息
					saveHolder(paraMap,reg_baseInfo);*/
				}
				else if(bus_class_id.equals(WbfConstants.CORRECTION)){				//更正登记  登簿时  按登记单元类型  判断从使用权中取还是从所有权中取
					Certificate certificateDB = certificateDao.get("where bus_id=(select bus_id from bus_main where reg_code = :reg_code)", paraMap);
					regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
					reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
					String reg_unit_type = regUnitRelMap.get("REG_UNIT_TYPE").toString();
					if(reg_unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){			//房屋更正登记
						
						saveHouseChange(busMainMap);
						
						allbookInfoMap = getAllbookInfoMapByRegId(paraMap,getDataTableKeyByBusTypeID(bus_class_id,""));
					
						//保存房地产证号
						String cer_no= save_cerno(paraMap);
						saveOwnshipBook(allbookInfoMap, paraMap,reg_baseInfo,busMainMap,certificateDB,cer_no);
						//保存权利人集合以及权利人信息
						saveHolder(paraMap,reg_baseInfo);
					}else if(reg_unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){	//土地更正登记
						saveParcelChange(busMainMap);
						allbookInfoMap = getAllbookInfoMapByRegId(paraMap,getDataTableKeyByBusTypeID(bus_class_id,""));
						//保存房地产证号
						String cer_no= save_cerno(paraMap);
						saveUserightbook(allbookInfoMap, paraMap,reg_baseInfo,busMainMap,certificateDB,cer_no);
						//保存权利人集合以及权利人信息
						saveHolder(paraMap,reg_baseInfo);
					}
				}else if(bus_class_id.equals(WbfConstants.REISSUE)){				//更正登记  登簿时  按登记单元类型  判断从使用权中取还是从所有权中取
					Certificate certificateDB = certificateDao.get("where bus_id=(select bus_id from bus_main where reg_code = :reg_code)", paraMap);
					regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
					reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
					String reg_unit_type = regUnitRelMap.get("REG_UNIT_TYPE").toString();
					if(reg_unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){			//房屋更正登记
						
						allbookInfoMap = getAllbookInfoMapByRegId(paraMap,getDataTableKeyByBusTypeID(bus_class_id,""));
						//保存房地产证号
						String cer_no= save_cerno(paraMap);
						saveOwnshipBook(allbookInfoMap, paraMap,reg_baseInfo,busMainMap,certificateDB,cer_no);
						//保存权利人集合以及权利人信息
						saveHolder(paraMap,reg_baseInfo);
					}else if(reg_unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){	//土地更正登记
						allbookInfoMap = getAllbookInfoMapByRegId(paraMap,getDataTableKeyByBusTypeID(bus_class_id,""));
						//保存房地产证号
						String cer_no= save_cerno(paraMap);
						saveUserightbook(allbookInfoMap, paraMap,reg_baseInfo,busMainMap,certificateDB,cer_no);
						//保存权利人集合以及权利人信息
						saveHolder(paraMap,reg_baseInfo);
					}
				}
				else if(bus_class_id.equals(WbfConstants.REALESTATE_CAN))
				{
					regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
					reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
					save_realstateCan(allbookInfoMap, paraMap,reg_baseInfo,busMainMap);
				}
				else if(bus_class_id.equals(WbfConstants.REVOKEAPPROVAL1))
				{
					regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
					//reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
					saveRevokeapproval(paraMap,busMainMap);
				}
				resultMap = new HashMap<String,Object>();
				resultMap.put("result", "success");
				return resultMap;
			}
			
			/**
			 * 
			 * registerSaveOwnerShip:(登记簿保存所有权部分).
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			private Map<String,Object> registerSaveOwnerShip(Map<String,Object> paraMap){
				Map<String,Object> resultMap = null;											//返回值 
				String regType = getRegTypeByRegCode(paraMap);
				if(regType == null || regType.equals("")){
					throw new BusinessException("登记类型为空");
				}
				if(regType.equals("房屋所有权初始登记")){
					resultMap = registerSaveOwnerShipInit(paraMap);
				}else if(regType.equals("房屋所有权变更登记")){
					
				}else if(regType.equals("房屋所有权二级转移登记")){
					resultMap = registerSaveOwnerShipChange(paraMap);
				}else if(regType.equals("房屋所有权三级转移登记")){
					resultMap = registerSaveOwnerShipChange(paraMap);
				}else if(regType.equals("房屋所有权注销登记")){
					
				}
				
				return resultMap;
			}
			
			public Map<String,Object> getParcelMapByHouseId(String house_id){
				Map<String,Object>  paraMap = new HashMap<String,Object>();
				paraMap.put("house_id", house_id);
				
				return houseDao.queryMapByKey("Register.getParcelMapByHouseId", paraMap);
			}
		
			/**
			 * 房屋所有权初始登记 登簿
			 */
			private Map<String,Object> registerSaveOwnerShipInit(Map<String,Object> paraMap){
				Map<String,Object> ownershipInfoMap = getOwnershipInfoMapByRegId(paraMap);
				Map<String,Object> regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
				Map<String,Object> busMainMap = getBusMainMapByRegId(paraMap);
				Certificate certificateDB = certificateDao.get("where bus_id=(select bus_id from bus_main where reg_code = :reg_code)", paraMap);
				//保存登记簿基本信息
				Reg_baseInfo reg_baseInfo = new Reg_baseInfo();
				reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
				
				String cer_no= save_cerno(paraMap);
				saveOwnshipBook(ownershipInfoMap, paraMap,reg_baseInfo,busMainMap,certificateDB,cer_no);
				saveHolder(paraMap,reg_baseInfo);
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("result", "success");
				return resultMap;
			}
			
			/**
			 * 
			 * getAllbookInfoMapByRegId:(通过登记编号获取登记信息表数据).
			 *
			 * @author xuzz
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public Map<String,Object> getAllbookInfoMapByRegId(Map<String,Object> paraMap,String key){
				Map<String,Object> resultMap = null;
				try {
					resultMap = dictItemDao.queryMapByKey(key, paraMap);
					if(resultMap == null || resultMap.isEmpty()){
						//throw new BusinessException("获取登记信息Map出错 登记信息Map数据为空");
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("获取登记信息Map出错"+e.getMessage());
				}
				return resultMap;
			}
			
			
		
			/**
			 * 
			 * getOwnershipInfoByRegId:(通过登记编号获取所有权登记信息表数据).
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public Map<String,Object> getOwnershipInfoMapByRegId(Map<String,Object> paraMap){
				Map<String,Object> resultMap = null;
				try {
					resultMap = dictItemDao.queryMapByKey("Register.getOwnershipInfoByRegId", paraMap);
					if(resultMap == null || resultMap.isEmpty()){
						throw new BusinessException("获取所有权登记信息Map出错 所有权登记信息Map数据为空");
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("获取所有权登记信息Map出错"+e.getMessage());
				}
				return resultMap;
			}
			
			/**
			 * 
			 * getRegUnitRelMapByRegId:(通过当前登记编号 登记单元关联表Map).
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public Map<String,Object> getRegUnitRelMapByRegId(Map<String,Object> paraMap){
				String whereSql = "";
				if(paraMap.get("reg_unit_code")!=null){
					whereSql =" and reg_unit_code=:reg_unit_code";
				}
				
				Map<String,Object> resultMap = null;
				try {
					resultMap = dictItemDao.queryMapByKey("Register.getRegUnitRelMapByRegId",whereSql, paraMap);
					if(resultMap == null || resultMap.isEmpty()){
						LogUtil.error("获取登记单元关联表Map出错 登记单元关联表Map数据为空");
						throw new BusinessException("获取登记单元关联表Map出错 登记单元关联表Map数据为空");
					}
				} catch (Exception e) {
						LogUtil.error("获取登记单元关联表出错"+e.getMessage());
				}
				return resultMap;
			}
			
			/**
			 * 
			 * getBusMainMapByRegId:(通过当前登记编号 业务主表Map). 
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public Map<String,Object> getBusMainMapByRegId(Map<String,Object> paraMap){
				Map<String,Object> resultMap = null;
				try {
					resultMap = dictItemDao.queryMapByKey("Register.getBusMainMapByRegId", paraMap);
					if(resultMap == null || resultMap.isEmpty()){
						throw new BusinessException("获取业务主表Map出错 业务主表Map出数据为空");
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("获取业务主表Map出错"+e.getMessage());
				}
				return resultMap;
			}
			/**
			 * 获取前一个业务登记编号，查询登记单元关联表
			 * @param paraMap
			 * @return
			 */
			public String getPreRegCodeByRegId(Map paraMap){
				Map<String,Object> resultMap = null;
				try {
					resultMap = dictItemDao.queryMapByKey("Register.getPreRegCodeByRegId", paraMap);
					if(resultMap == null || resultMap.isEmpty()){
						return "";
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("获取前登记编号出错"+e.getMessage());
				}
				return resultMap.get("LAST_REG_CODE").toString();
			}
			
			private String getHisHolderId(Map<String, Object> paraMap) {
		
				
				Map<String,Object> resultMap = null;
				try {
					resultMap = dictItemDao.queryMapByKey("Register.getHisHolderId", paraMap);
					if(resultMap == null || resultMap.isEmpty()){
						return "";
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("获取前权利人编号出错"+e.getMessage());
				}
				return resultMap.get("HOLDER_ID").toString();
			}
			
		/*****  所有权二级转移登记  登簿   ******/
			private Map<String,Object> registerSaveOwnerShipChange(Map<String, Object> paraMap){
				
				Map<String,Object> ownershipInfoMap = getOwnershipInfoMapByRegId(paraMap);
				Map<String,Object> regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
				Map<String,Object> busMainMap = getBusMainMapByRegId(paraMap);
				
				//保存登记簿基本信息
				Reg_baseInfo reg_baseInfo = new Reg_baseInfo();
				reg_baseInfo.setBook_code(String.valueOf(reg_baseInfoDao.getSeqId()));
				reg_baseInfo.setHouse_id(regUnitRelMap.get("HOUSE_ID").toString());
				//reg_baseInfo.setParcel_id(regUnitRelMap.get("PARCEL_ID").toString());
				reg_baseInfo.setReg_unit_type(regUnitRelMap.get("REG_UNIT_TYPE").toString());
				reg_baseInfo.setReg_unit_code(regUnitRelMap.get("REG_UNIT_CODE").toString());
				reg_baseInfo.setReg_organization(busMainMap.get("REG_STATION").toString());
				try {
					reg_baseInfoDao.save(reg_baseInfo);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("保存登记簿基本信息出错"+e.getMessage());
				}
				
				//登簿时生成房地产证号  并保存到房地产证表中
				Row row = new RowImpl();
				row.put("name", "房地产证号");
				String cer_no = FacadeFactory.getIdentifierFacade().getSerialNumber(row);
				if(cer_no != null && cer_no != ""){
					Certificate certificate = new Certificate();
					try {
						String cer_id = certificateDao.queryMapByKey("Register.getCertificateMapByRegCode",paraMap).get("CERTIFICATE_ID").toString();
						if(cer_id == null || cer_id == ""){
							throw new BusinessException("获取房地产证表ID 出错  ID为空");
						}
						certificate.setCertificate_id(cer_id);
						certificate = certificateDao.get(certificate);
						certificate.setCertificate_code(cer_no);
						certificateDao.update(certificate);
					} catch (Exception e) {
						e.printStackTrace();
						throw new BusinessException("获取房地产证表ID 出错  "+e.getMessage());
					}
					
				}
			 
				//保存登记簿所有权部分信息
				Reg_ownership   reg_ownership = new Reg_ownership();
				reg_ownership.setOwnership_id(String.valueOf(reg_ownershipDao.getSeqId()));
				reg_ownership.setReg_date(new Date());
				reg_ownership.setReg_code(paraMap.get("reg_code").toString());
				reg_ownership.setBook_code(reg_baseInfo.getBook_code());
				reg_ownership.setRecorder(paraMap.get("recorder").toString());
				reg_ownership.setReg_value(ownershipInfoMap.get("REG_VALUE").toString());
				reg_ownership.setProcdef_id(busMainMap.get("PRO_DEF_ID").toString());
				reg_ownership.setExcursus(ownershipInfoMap.get("EXCURSUS").toString());
				reg_ownership.setCer_no(cer_no);
				
				reg_ownership.setGet_mode(ownershipInfoMap.get("GET_MODE").toString());
				reg_ownership.setLu_term(ownershipInfoMap.get("LU_TERM").toString());
				reg_ownership.setStart_date((Date)ownershipInfoMap.get("START_DATE"));
				reg_ownership.setEnd_date((Date)ownershipInfoMap.get("END_DATE"));
				reg_ownership.setHouse_usage(ownershipInfoMap.get("HOUSE_USAGE").toString());
				reg_ownership.setHouse_attr(ownershipInfoMap.get("HOUSE_ATTR").toString());
				//reg_ownership.setPre_reg_code(getPreRegCodeByRegId(paraMap));						//初始登记  无前登记编号
				reg_ownership.setPre_reg_code(getPreRegCodeByRegId(paraMap));
				try {
					reg_ownershipDao.save(reg_ownership);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("保存登记簿所有权部分信息出错"+e.getMessage());
				}
				
				
				//保存权利人集合信息
				HolderRelationship holderRelationship = new HolderRelationship();
				holderRelationship.setRight_rel_id(String.valueOf(holderRelationshipDao.getSeqId()));
				holderRelationship.setBook_code(reg_baseInfo.getBook_code());
				holderRelationship.setReg_code(paraMap.get("reg_code").toString());
				try {
					holderRelationshipDao.save(holderRelationship);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("保存保存权利人集合信息出错"+e.getMessage());
				}
				
				
				//保存权利人信息
				//String his_holder_id = null;		//前权利人ID
				//int temp_count = 0;					//循环计数  用来获取权利人前ID  第一次从数据库中取   后面的都从前面一个里取
				List<Map<String,Object>> appList = getAplPerInfoByRegId(paraMap);					//变更权利人  并保存到权利人表
				Holder holder = null;
				
				try {
					
					for (Map<String,Object> tempApp : appList) {
							//if(temp_count == 0){
							//	his_holder_id = getHisHolderId(paraMap);
							//}
							holder = new Holder();
							holder.setHolder_id(String.valueOf(holderDao.getSeqId()));
							holder.setRight_rel_id(holderRelationship.getRight_rel_id());
							holder.setHol_type(tempApp.get("APP_TYPE").toString());
							holder.setHol_name(tempApp.get("APP_NAME").toString());
							holder.setHol_cer_type(tempApp.get("APP_CER_TYPE").toString());
							holder.setHol_cer_no(tempApp.get("APP_CER_NO").toString());
							holder.setHol_address(tempApp.get("APP_ADDRESS").toString());
							//holder.setDepart_type(tempApp.get("DEPART_TYPE").toString());		//暂时不保存单位性质
							Object legalObject = tempApp.get("LEGAL_NAME");//.toString()
							
							//判断  如果法人为空 则不保存法人    
							if(legalObject!=null){
								holder.setLegal_name(legalObject.toString());
							}
							
							//holder.setLegal_cer(tempApp.get("LEGAL_CER").toString());			//申请人表中  无法人身份证
							Object agent_name = tempApp.get("AGENT_NAME");
							if(agent_name != null){
								holder.setAgent_name(agent_name.toString());
							}
							
							Object agent_cer = tempApp.get("AGENT_CER");
							if(agent_cer != null){
								holder.setAgent_cer(agent_cer.toString());
							}
							
							Object agent_tel = tempApp.get("AGENT_TEL");
							if(agent_tel != null){
								holder.setAgent_tel(agent_tel.toString());
							}
							
							Object portion = tempApp.get("APP_PORT");
							if(portion != null){
								holder.setPortion(portion.toString());
							}
							
							//holder.setAgent_cer(tempApp.get("AGENT_CER").toString());
							//holder.setAgent_tel(tempApp.get("AGENT_TEL").toString());
							//holder.setPortion(tempApp.get("APP_PORT").toString());
							//holder.setHis_holder_id(his_holder_id);
							
							holderDao.save(holder);
							
							//his_holder_id = holder.getHolder_id();							//迭代后历史权利人ID 为上一次保存的ID	
							//temp_count++;														//计数自增
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("保存保存权利人信息出错"+e.getMessage());
				}
				
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("result", "success");
				return resultMap;
			}
			
			
			/**
			 * 
			 * getSecondTransferApp:(获取二次转移的受让方  筛选出未转让份额的权利人合并). 
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public List<Map<String,Object>> getSecondTransferApp(Map<String, Object> paraMap){
				//如果受让方份额为百分之百  则直接把受让方申请人返回
				//获取受让方信息
				paraMap.put("app_type", "受让方");
				List<Map<String,Object>> transfereeMapList = getAppMapListByAppTypeAndRegId(paraMap);
				
				//转让方信息
				paraMap.put("app_type", "转让方");
				List<Map<String,Object>> transferorMapList = getAppMapListByAppTypeAndRegId(paraMap);
				if(checkTransfereePortIsHundredPercent(paraMap)){
					return transfereeMapList;
				}else{
					List<Map<String,Object>> tempHolderAsAppMapList = null;
					try {
						tempHolderAsAppMapList = houseDao.queryMapListByKey("Register.getCurBusHouseHolderAsAPPMapListByRegId",paraMap);
					} catch (Exception e) {
						e.printStackTrace();
						throw new BusinessException("查询房屋所有权人信息出错"+e.getMessage());
					}
					
					//循环去年转让方   从权利人表中
					for(int i=0;i<tempHolderAsAppMapList.size();i++){
						Map<String,Object> tempMap = tempHolderAsAppMapList.get(i);
						
						String strTempHolderCerNo = tempMap.get("APP_CER_NO").toString();					//获取当前权利人的证件编号  用来与申请人  转让方信息进行对比   如果相同则清空此权利人 循环对比
						
						for(Map<String,Object> tempTransferor:transferorMapList){
							String strTempTransferorCerNo =  tempTransferor.get("APP_CER_NO").toString();  //转让方身份证
							//如果权利人与转让方身份证相等   则从list中删除当前权利人
							if(strTempHolderCerNo.equals(strTempTransferorCerNo)){
								tempHolderAsAppMapList.remove(i);
							}
						}
					}
					
					transfereeMapList.addAll(tempHolderAsAppMapList);										//合并受让方   与未转让份额的权利人  并返回
					
				}
				
				return transfereeMapList;
			}
			
			/**
			 * 
			 * getSecondTransferHolderMapListByProcId:(转移登记获取历史权利人)
			 *
			 * @author Joyon
			 * @param proc_id
			 * @return
			 * @since JDK 1.6
			 */
			public List<Map<String,Object>> getSecondTransferHolderMapListByProcId(String proc_id){
				
				return null;
			}
			
			public List<Map<String,Object>> getHolderMapListByHouseId(String house_id){
				
				return null;
			}
			
			/**
			 * 
			 * checkTransfereePortIsHundredPercent:(检查受让方的份额是否是百分之百)
			 *
			 * @author Joyon
			 * @since JDK 1.6
			 */
			private boolean checkTransfereePortIsHundredPercent(Map<String, Object> paraMap){
				boolean result = false;
				paraMap.put("app_type", "受让方");
				List<Map<String,Object>> transfereeMapList = getAppMapListByAppTypeAndRegId(paraMap);
				int portSum = 0;
				int startIndex = 0;
				int endIndex = 0;
				
				//循环出去受让方的份额 并进行相加
				for(Map<String,Object> tempMap:transfereeMapList){
					String strTempPort = tempMap.get("APP_PORT").toString();
					endIndex = strTempPort.indexOf("%");
					strTempPort = strTempPort.substring(startIndex, endIndex);
					int intTempPort = Integer.valueOf(strTempPort);
					portSum+=intTempPort;
				}
				
				//判断 如果份额之和等于100  则返回true
				if(portSum == 100){
					result = true;
				}
				return result;
			}
			
			/**
			 * 
			 * getAppMapListByAppTypeAndRegId:(根据登记编号 申请人类型 获取申请人信息). 
			 * @author Joyon
			 * @param paraMap reg_code app_type
			 * @return
			 * @since JDK 1.6
			 */
			public List<Map<String,Object>> getAppMapListByAppTypeAndRegId(Map<String, Object> paraMap){
				//paraMap.put("app_type", "受让方");
				List<Map<String,Object>> resultList = null;
				try {
					resultList = houseDao.queryMapListByKey("Register.getTransfereeMapListByRegId", paraMap);
					if(resultList == null || resultList.isEmpty()){
						throw new BusinessException("获取受方信息出错 受让信息为空");
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("获取受方信息出错"+e.getMessage());
				}
				return resultList;
			}
		/*****  所有权二级转移登记结束    ******/
	/**********************************       所有权部分  结束          *************************************/
/**********************************     保存部分  结束        *************************************/	
	
	/**
	 * 
	 * getProcid:(查询业务表的流程定义ID). <br/>
	 * @author xuzz
	 * @param procid
	 * @return
	 * @since JDK 1.6
	 */
	public String getProcid(String procid)
	{
		Map map=new HashMap();
		map.put("procid", procid);
		Map m= acceptRuleDao.queryMapByKey("Register.getProcid", " where proc_id=:procid", map);
		return m.get("bus_type_id").toString();
	}
	/**
	 * 检查登记单元是否可以受理
	 * @author peter
	 * @return
	 */
	public Map checkBusData(String reg_unit_code,String regtype,String bustype)
	{
		bustype= FacadeFactory.getRegisterFacade().getProcid(bustype);
		Map map=new HashMap();
		Map resultMap=new HashMap();
		map.put("bustype", bustype);
		Map regcodeMap=new HashMap();
		regcodeMap.put("reg_unit_code", reg_unit_code);
		String state="true";
		String statemes="<p style='color:blue;'>可以受理该登记单元！</p>";
		int precount=0;
		int limcount=0;
		int mescount=0;
		try
		{
			//查询该登记单元是否在途
			List<Map<String,Object>> checkBusStateMap=reg_baseInfoDao.queryMapListByKey("Register.checkBusState", "and M.REG_UNIT_CODE=:reg_unit_code", regcodeMap);
			if(checkBusStateMap.size()>0)
			{
				state="false";
				statemes="<p style='color:red;'>该登记单元存在在途业务未结束，请您先结束当前在途业务！<br/></p>";
				resultMap.put("statemes", statemes);
				resultMap.put("state", state);
				return resultMap;
			}
			//---------------------------------------根据配置的条件查询当前登记单元是否可以受理
			//根据当前业务定义ID查询规则表的限制条件，前置条件，提示条件等。
			List<AcceptRule> selectRule=acceptRuleDao.queryListByKey("PreAudit.selectRule", " where bus_type_id=:bustype", map);
			for(AcceptRule acceptrule: selectRule)
			{
				String busname= getParentByProcId(acceptrule.getRule().toString()).get("BUS_NAME").toString();
				String rule=acceptrule.getRule_type().toString();
				String sql=" where  REG_CODE in (SELECT distinct(REG_CODE)  FROM BUS_REGUNITREL r WHERE     BUS_ID IN (SELECT BUS_ID   FROM BUS_MAIN b WHERE b.PRO_DEF_ID =:prodefid) AND r.REG_STATE = '053002' AND REG_UNIT_CODE =:reg_unit_code)";
				Map bookmap=new HashMap();
				bookmap.put("reg_unit_code", reg_unit_code);
				//bookmap.put("sql", sql);
				bookmap.put("prodefid", acceptrule.getRule().toString());
				Map resultBookMap= getAllBook(bookmap,sql);
				
				//List<Map<String,Object>> listMap=getRegCode(reg_unit_code,acceptrule.getRule().toString());
				//前置条件
				if(rule.equals(WbfConstants.PREAUDIT))
				{
					
					System.out.println();
					if(resultBookMap!=null)
					{
						
					}
					else
					{
						state="false";
						statemes="<p style='color:red;'>缺少前置条件：<br/>"+busname+"</p>";
						break;
					}
				}
				//限制条件
				else if(rule.equals(WbfConstants.LIMIT))
				{
					System.out.println();
					if(resultBookMap==null)
					{
						
					}
					else
					{
						state="false";
						statemes="<p style='color:red;'>存在限制条件：<br/>"+busname+"</p>";
						break;
					}
				}
				//提示条件
				else if(rule.equals(WbfConstants.MESSAGE))
				{
					System.out.println();
					
					if(mescount==0)
					{
						statemes+="<p style='color:red;'>存在提示：<br/>"+busname+"</p>";
					}
					else
					{
						statemes+="<p style='color:red;'>"+busname+"</p>";
					}
				}
			}
			resultMap.put("statemes", statemes);
			resultMap.put("state", state);
		
		}catch(Exception err)
		{
			err.getMessage();
		}
		return resultMap;	
	}
	
	
	
	
	/**
	 * 根据流程定义ID获取流程定义父ID
	 * @param bus_type_id
	 * @return
	 */
	public Map getParentByProcId(String bus_type_id)
	{
		Map map=new HashMap();
		map.put("bus_type_id", bus_type_id);
		Map<String, Object> ParentMap=bus_typeDao.queryMapByKey("Register.getProcid", " where bus_type_id=:bus_type_id", map);
		if(ParentMap==null||ParentMap.isEmpty())
		{
			return null;
		}
		return ParentMap;
	}
	/**
	 * 通过登记单元编号和业务定义ID获取登记编号集合 
	 * @param regunitcode
	 * @param procdefid
	 * @return
	 */
	public List<Map<String,Object>> getRegCode(String regunitcode,String procdefid)
	{
		Map map=new HashMap();
		map.put("reg_unit_code", regunitcode);
		map.put("prodefid", procdefid);
		List<Map<String,Object>> listMap=bus_typeDao.queryMapListByKey("Register.getRegCode",map);
		return listMap;
	}
	
	/**
	 * 通过登记编号获取查询当前业务相关登记簿信息
	 * @param regcode
	 * @return
	 */
	public Map getAllBook(Map map,String appendsql)
	{
		String typeid="";
		typeid =getParentByProcId(map.get("prodefid").toString()).get("PARENT_ID").toString();
		if(typeid!=null)
		{
			Map<String, Object> registerMap = getBKInfoByRegId(getDataTableKeyByBusTypeID(typeid,"book"),appendsql,map);
			return registerMap;
		}
		return null;
	}
	
	/**
	 * 
	 * checkBus:(检查业务数据是否可以受理). <br/>
	 * @author xuzz
	 * @since JDK 1.6  
	 */
	/*public Map checkBusData1(String reg_code,String regtype,String bustype)
	{
		Map resultMap=new HashMap();
		Map map=new HashMap();
		map.put("bustype", bustype);
		Map regcodeMap=new HashMap();
		regcodeMap.put("regcode", reg_code);
		int count=0;
		String passage="";
		String passageState="true";
		String preaudit="";
		String preauditState="true";
		String message="";
		String limit="";
		String limitState="true";
		resultMap.put("passage", passage);
		resultMap.put("passageState", passageState);
		resultMap.put("preaudit", preaudit);
		resultMap.put("preauditState", preauditState);
		resultMap.put("limit", limit);
		resultMap.put("limitState", limitState);
		resultMap.put("message", message);
		int i=0;
		List<Map<String,Object>> checkBusStateMap=reg_baseInfoDao.queryMapListByKey("Register.checkBusState", "and M.REG_UNIT_CODE=:regcode", regcodeMap);
		if(checkBusStateMap.size()>0)
		{
			passage="该登记单元存在在途业务未结束，请您先结束当前在途业务！<br/>";
			passageState="false";
			resultMap.put("passage", passage);
			resultMap.put("passageState", passageState);	
		}
		else
		{
			Map ma=reg_baseInfoDao.queryMapByKey("Register.selectBaseInfo", " where house_id=:houseid", regcodeMap);
			if(ma!=null)
			{
				String bookcode= ma.get("BOOK_CODE").toString();
				//查找前置条件
				List<AcceptRule> listaccept= acceptRuleDao.queryListByKey("PreAudit.selectRule", " where bus_type_id=:bustype and rule_type=1", map);

				for(AcceptRule acceptrule: listaccept)
				{
				    Map parentid=selectparentid(acceptrule.getRule());
					Map m= selectAllReg(bookcode,parentid.get("PARENT_ID").toString());
					if(m!=null)
					{
						count++;
					}
					else
					{
						if(i==0)
						{
							preaudit+=parentid.get("BUS_NAME");
							i++;
						}	
						else
						{
							preaudit+=",<br/>"+parentid.get("BUS_NAME");
						}
						preaudit+=m.get("map");
						preauditState="false";
					}
				}
				if(count==listaccept.size())
				{
					preaudit="该登记单元可以受理该业务！<br/>";
					preauditState="true";
				}
				resultMap.put("preaudit", preaudit);
				resultMap.put("preauditState", preauditState);	
				count=0;
				//查找限制条件
				List<AcceptRule> listacceptlimit= acceptRuleDao.queryListByKey("PreAudit.selectRule", " where bus_type_id=:bustype and rule_type=0", map);
				i=0;
				for(AcceptRule acceptrule: listacceptlimit)
				{
					//String parentid=selectparentid(acceptrule.getRule()).get("PARENT_ID").toString();
					Map parentid=selectparentid(acceptrule.getRule());
					Map m= selectAllReg(bookcode,parentid.get("PARENT_ID").toString());
					if(m!=null)
					{
						//limit+=m.get("map");
						if(i==0)
						{
							limit+=parentid.get("BUS_NAME");
							i++;
						}	
						else
						{
							limit+=",<br/>"+parentid.get("BUS_NAME");
						}
						limitState="false";
					}
					else
					{
						count++;
					}
				}
				if(count==listacceptlimit.size())
				{
					limit="无查封记录！<br/>";
					limitState="true";
				}
				resultMap.put("limit", limit);
				resultMap.put("limitState", limitState);
				count=0;
				//查找提示条件
				List<AcceptRule> listacceptmessage= acceptRuleDao.queryListByKey("PreAudit.selectRule", " where bus_type_id=:bustype and rule_type=2", map);
				i=0;
				for(AcceptRule acceptrule: listacceptmessage)
				{
					Map parentid=selectparentid(acceptrule.getRule());
					Map m= selectAllReg(bookcode,parentid.get("PARENT_ID").toString());
					if(m!=null)
					{
						if(i==0)
						{
							message+=parentid.get("BUS_NAME");
							i++;
						}	
						else
						{
							message+=",<br/>"+parentid.get("BUS_NAME");
						}
					}
					else
					{
					}
				}
				resultMap.put("message", message);
			}	
			else
			{
				resultMap.put("limit", "无查封记录！<br/>");
				resultMap.put("limitState", "true");
				resultMap.put("preaudit", "该登记单元可以受理该业务！<br/>");
				resultMap.put("preauditState", "true");
			}
		}
		
		return resultMap;
		
	}*/
	/**
	 * 查询是否在途业务
	 * @param regcode
	 * @param prodefid
	 * @return
	 */
	public Map getRegUnitRel(String regcode,String prodefid)
	{
		Map map=new HashMap();
		map.put("regcode", regcode);
		map.put("procdefid", prodefid);
		return map;
		//Map resultMap=acceptRuleDao.queryMapByKey(key, whereSql, paramsObject);
	}
	
	
	/**
	 * 
	 * selectparentid:(获取业务类型表信息). <br/>
	 * @author xuzz
	 * @param parentid
	 * @return
	 * @since JDK 1.6
	 */
	public Map selectparentid(String bustypeid)
	{
		Map mapaccept=new HashMap();
		mapaccept.put("bustypeid", bustypeid);
		Map mapbustype=reg_baseInfoDao.queryMapByKey("Common.getProcName", " where BUS_TYPE_ID=:bustypeid", mapaccept);		
		return mapbustype; 
	}
	
	
	/**
	 * 
	 * selectAllReg:(查询所有登记簿信息). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 *//*
	public Map selectAllReg(String bookcode,String bookid)
	{
		Map map=new HashMap();
		Map m=new HashMap();
		map.put("bookcode", bookcode);
		//查询共有登记簿部分
		if(Integer.valueOf(bookid)==100)
		{
			List<Reg_Share> listshare=reg_ShareDao.queryListByKey("Register.getShare", " where book_code=:bookcode", map);
			if(listshare.size()>0)
			{
				for(Reg_Share regshare : listshare)
				{
					if(regshare.getPre_reg_code()!="")
					{
						Map s=new HashMap();
						s.put("regcode", regshare.getPre_reg_code());
						Map sharemap=reg_UserightDao.queryMapByKey("Register.getShare", " where Pre_reg_code=:regcode", s);
						if(sharemap!=null)
						{
							
						}
						else 
						{
							//String busname=selectparentid(regshare.getProcdef_id()).get("BUS_NAME").toString();
							m.put("map", regshare.getProcdef_id());
							return m;
						}	
					}
				}
			}
			else
			{
				return null;
			}
		}
		//查询使用权部分
		else if(Integer.valueOf(bookid)==101)
		{
			List<Reg_Useright> listuseright=reg_UserightDao.queryListByKey("Register.getUseright", " where book_code=:bookcode", map);
			if(listuseright.size()>0)
			{
				for(Reg_Useright reguseright : listuseright)
				{
					if(reguseright.getPre_reg_code()!="")
					{
							Map s=new HashMap();
							s.put("regcode", reguseright.getPre_reg_code());
							Map userightemap=reg_UserightDao.queryMapByKey("Register.getUseright", " where Pre_reg_code=:regcode", s);
							if(userightemap!=null)
							{
								
							}
							else 
							{
								//String busname=selectparentid(reguseright.getProcdef_id()).get("BUS_NAME").toString();
								m.put("map", reguseright.getProcdef_id());
								return m;
							}
					}
				}
			}
			else
			{
				return null;
			}
		}
		//查询所有权部分
		else if(Integer.valueOf(bookid).equals(WbfConstants.HOUSE_ONWERSHIP))
		{
			List<Reg_ownership> listregownership=reg_ownershipDao.queryListByKey("Register.getOwnership", " where book_code=:bookcode", map);
			if(listregownership.size()>0)
			{
				for(Reg_ownership regownership : listregownership)
				{
					if(regownership.getPre_reg_code()!="")
					{
						Map s=new HashMap();
						s.put("regcode", regownership.getReg_code());
						Map ownershipmap=reg_UserightDao.queryMapByKey("Register.getOwnership", " where Pre_reg_code=:regcode", s);
						if(ownershipmap!=null)
						{
							
						}
						else 
						{
							//String busname=selectparentid(regownership.getProcdef_id()).get("BUS_NAME").toString();
							m.put("map", regownership.getProcdef_id());
							return m;
						}
					}
					
				}
			}
			else
			{
				return null;
			}
		}
		//登记簿（他项权力部分_抵押登记）
		else if(Integer.valueOf(bookid).equals(WbfConstants.LAND_USERIGHT))
		{
			List<Reg_Mortgage> listmortgage=reg_MortgageDao.queryListByKey("Register.getMortgage", " where book_code=:bookcode", map);
			if(listmortgage.size()>0)
			{
				for(Reg_Mortgage regmortgage : listmortgage)
				{
					if(regmortgage.getPre_reg_code()!="")
					{
						Map s=new HashMap();
						s.put("regcode", regmortgage.getPre_reg_code());
						Map mortgagemap=reg_UserightDao.queryMapByKey("Register.getMortgage", " where Pre_reg_code=:regcode", s);
						if(mortgagemap!=null)
						{
							
						}
						else 
						{
							//String busname=selectparentid(regmortgage.getProcdef_id()).get("BUS_NAME").toString();
							m.put("map", regmortgage.getProcdef_id());
							return m;
						}
					}	
					
				}
			}
			else
			{
				return null;
			}
		}
		//登记簿（他项权利部分_地役权登记）
		else if(Integer.valueOf(bookid)==104)
		{
			List<Reg_Easement> listeasement=reg_EasementDao.queryListByKey("Register.getEasement", " where book_code=:bookcode", map);
			if(listeasement.size()>0)
			{
				for(Reg_Easement regeasement : listeasement)
				{
					if(regeasement.getPre_reg_code()!="")
					{
						Map s=new HashMap();
						s.put("regcode", regeasement.getPre_reg_code());
						Map easementmap=reg_UserightDao.queryMapByKey("Register.getEasement", " where Pre_reg_code=:regcode", s);
						if(easementmap!=null)
						{
							
						}
						else 
						{
							//String busname=selectparentid(regeasement.getProcdef_id()).get("BUS_NAME").toString();
							m.put("map", regeasement.getProcdef_id());
							return m;
						}
					}	
					
				}
			}
			else
			{
				return null;
			}
		}
		//登记簿（其它部分_预告登记）
				else if(Integer.valueOf(bookid)==105)
				{
					List<Reg_Advnotice> listadvnotice=reg_AdvnoticeDao.queryListByKey("Register.getAdvnotice", " where book_code=:bookcode", map);
					if(listadvnotice.size()>0)
					{
						for(Reg_Advnotice regadvnotice : listadvnotice)
						{
							if(regadvnotice.getPre_reg_code()!="")
							{
								Map s=new HashMap();
								s.put("regcode", regadvnotice.getPre_reg_code());
								Map gadvnoticemap=reg_UserightDao.queryMapByKey("Register.getAdvnotice", " where Pre_reg_code=:regcode", s);
								if(gadvnoticemap!=null)
								{
									
								}
								else 
								{
									//String busname=selectparentid(regadvnotice.getProcdef_id()).get("BUS_NAME").toString();
									m.put("map", regadvnotice.getProcdef_id());
									return m;
								}
							}
						}
					}
					else
					{
						return null;
					}
				}
		//登记簿（其它部分_异议登记）
				else if(Integer.valueOf(bookid)==106)
				{
					List<Reg_Demurrer> listdemurrer=reg_DemurrerDao.queryListByKey("Register.getDemurrer", " where book_code=:bookcode", map);
					if(listdemurrer.size()>0)
					{
						for(Reg_Demurrer regdemurrer : listdemurrer)
						{
							if(regdemurrer.getPre_reg_code()!="")
							{
								Map s=new HashMap();
								s.put("regcode", regdemurrer.getPre_reg_code());
								Map demurrermap=reg_UserightDao.queryMapByKey("Register.getDemurrer", " where Pre_reg_code=:regcode", s);
								if(demurrermap!=null)
								{
									
								}
								else 
								{
									//String busname=selectparentid(regdemurrer.getProcdef_id()).get("BUS_NAME").toString();
									m.put("map", regdemurrer.getProcdef_id());
									return m;
								}
							}
						}
					}
					else
					{
						return null;
					}
				}
		//登记簿（其它部分_查封登记）
				else if(Integer.valueOf(bookid)==107)
				{
					List<Reg_Distrain> listdistrain=reg_DistrainDao.queryListByKey("Register.getDistrain", " where book_code=:bookcode", map);
					if(listdistrain.size()>0)
					{
						for(Reg_Distrain regdistrain : listdistrain)
						{
							if(regdistrain.getPre_reg_code()!="")
							{
								Map s=new HashMap();
								s.put("regcode", regdistrain.getPre_reg_code());
								Map distrainmap=reg_UserightDao.queryMapByKey("Register.getDistrain", " where Pre_reg_code=:regcode", s);
								if(distrainmap!=null)
								{
									
								}
								else 
								{
									//String busname=selectparentid(regdistrain.getProcdef_id()).get("BUS_NAME").toString();
									m.put("map", regdistrain.getProcdef_id());
									return m;
								}
							}
						}
					}
					else
					{
						return null;
					}
				}
		
		
		return null;
	}*/
	
	
	/**
	 * 
	 * isRegisterSave:(判断是否已经登簿     已经登簿返回true   未登簿返回false  各大类只需重写 else if中的判断  就行).
	 *
	 * @author Joyon
	 * @param paraMap reg_code 登记编号  
	 * @return
	 * @since JDK 1.6
	 */
	public boolean isRegisterSave(Map paraMap){
		//返回结果
		boolean result = false;
		//从登记簿中获得的值    如果有返回True   无返回false
		Object tempObject = null;
		
		//父业务id
		//String bus_class_id = paraMap.get("bus_class_id").toString();
		String bus_class_id = getBusTypeParentIdByRegId(paraMap);
		if( bus_class_id.equals(WbfConstants.HOUSE_ONWERSHIP)){
			tempObject =reg_ownershipDao.get("where reg_code = :reg_code",paraMap);
		}else if(bus_class_id.equals(WbfConstants.LAND_USERIGHT)){
			tempObject =reg_UserightDao.get("where reg_code = :reg_code",paraMap);
		}else if(bus_class_id.equals(WbfConstants.MORTGAGE_RIGHT)){
			List tmpList =reg_MortgageDao.getAll("where reg_code = :reg_code",paraMap);
			if(tmpList.isEmpty() || tmpList==null){
				return false;
			}else{
				return true;
			}
		}else if(bus_class_id.equals(WbfConstants.CORRECTION))		//更正登记   如果是房 就到所有权表中查     如果是地则到使用权中查
		{
			
			String unit_type = getRegUnitRelMapByRegId(paraMap).get("REG_UNIT_TYPE").toString(); //reg_relationshipDao.get("where bus_id =(select bus_id from bus_main where reg_code=:reg_code)", paraMap).getReg_unit_type();
			if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
					tempObject =reg_ownershipDao.get("where reg_code = :reg_code",paraMap);
			}else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
				tempObject =reg_UserightDao.get("where reg_code = :reg_code",paraMap);
			}
				
		}
		else if(bus_class_id.equals(WbfConstants.REALESTATE_CAN))		//注销登记   如果是房 就到所有权表中查     如果是地则到使用权中查
		{
			
			String unit_type = getRegUnitRelMapByRegId(paraMap).get("REG_UNIT_TYPE").toString(); //reg_relationshipDao.get("where bus_id =(select bus_id from bus_main where reg_code=:reg_code)", paraMap).getReg_unit_type();
			if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
					
					tempObject =reg_UserightDao.get("where reg_code = :reg_code",paraMap);
			}else{
				tempObject =reg_ownershipDao.get("where reg_code = :reg_code",paraMap);
			}
				
		}
		else if(bus_class_id.equals(WbfConstants.REG_ATTACH))
		{
			List tempList =reg_DistrainDao.getAll("where reg_code = :reg_code",paraMap);
			if(tempList.size()>0)
			{
				return true;
			}
		}
		else if(bus_class_id.equals(WbfConstants.DEMURRER))
		{
			List tempList =reg_DemurrerDao.getAll("where reg_code = :reg_code",paraMap);
			if(tempList.size()>0)
			{
				return true;
			}
		}
		else if(bus_class_id.equals(WbfConstants.REVOKEAPPROVAL1))
		{
			Map map = getRegUnitRelMapByRegId(paraMap); 
			String unit_type= map.get("REG_UNIT_TYPE").toString();
			if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
					tempObject =reg_ownershipDao.get(" where reg_code = :LAST_REG_CODE and effective='"+WbfConstants.UNEFFECTIVE+"'",map);
			}else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
				tempObject =reg_UserightDao.get("where reg_code = :LAST_REG_CODE  and effective='"+WbfConstants.UNEFFECTIVE+"'",map);
			}
		}
		else if(bus_class_id.equals(WbfConstants.REISSUE))		//补证登记   如果是房 就到所有权表中查     如果是地则到使用权中查
		{
			
			String unit_type = getRegUnitRelMapByRegId(paraMap).get("REG_UNIT_TYPE").toString(); //reg_relationshipDao.get("where bus_id =(select bus_id from bus_main where reg_code=:reg_code)", paraMap).getReg_unit_type();
			if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
					tempObject =reg_ownershipDao.get("where reg_code = :reg_code",paraMap);
			}else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
				tempObject =reg_UserightDao.get("where reg_code = :reg_code",paraMap);
			}
				
		}
		
		if(tempObject!=null){
			result = true;
		}
		return result;
	}
	
	/**
	 * 
	 * 根据bus_id获取抵押权过程数据.
	 * @see com.szhome.cq.business.IRegisterFacade#getMortMess(java.util.Map)
	 */
	@Override
	public Map<String, Object> getMortMess(Map m) {
		Mortgage mort = mortDao.queryDomainBykey("Register.getMortByid", m);
		if( mort == null){			
			return null;			
		};
		String start = DateUtils.format(mort.getCreditor_start_date(), "yyyy年MM月dd日");
		String end = DateUtils.format(mort.getCreditor_end_date(), "yyyy年MM月dd日");
		String debt_dis_limit = "从"+start+"到"+end;
		Map map = new HashMap();
		map.put("mort_type", mort.getMort_type());
		map.put("assure_amount", mort.getMort_assure_right());
		map.put("assuer_range", mort.getAssuer_range());
		map.put("mort_seq", mort.getMort_seq());
		map.put("reg_date",DateUtils.format(com.szhome.cq.utils.DateUtils.getCurTime(),"yyyy年MM月dd日"));
		map.put("debt_dis_limit", debt_dis_limit);
		map.put("max_amount", mort.getMax_amount());
		map.put("sure_amount", mort.getSure_amount());
		map.put("borrower", mort.getBorrower());		
		
		return map;
	}
	
	/**
	 * 
	 * getReg_baseInfoByUnitCode:(通过登记单元编码获取登记簿基本信息).
	 *
	 * @author Joyon
	 * @param reg_unit_no
	 * @return
	 * @since JDK 1.6
	 */
	public Reg_baseInfo getReg_baseInfoByUnitCode(String reg_unit_no){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_unit_code", reg_unit_no);
		return reg_baseInfoDao.get("where reg_unit_code = :reg_unit_code", paraMap);
	}
	
	/**
	 * 
	 * getLastRegOnwershipMapByProcId:(通过流程实例ID获取上一次业务的登记簿所有权信息Map).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getLastRegOnwershipMapByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id",proc_id);
		
		String last_bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getLast_bus_id();
		paraMap.put("last_bus_id", last_bus_id);
		String reg_code = businessMainDao.get("where bus_id=:last_bus_id", paraMap).getReg_code();
		paraMap.put("reg_code",reg_code);
		
		return getOwnershipInfoMapByRegId(paraMap);
		 
		
	}
	
	/**
	 * 
	 * saveHistoryHolderToApp:(把历史权利人保存到申请表中). 
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> saveHistoryHolderToApp(String proc_id) throws BusinessException{
		String reg_unit_code = FacadeFactory.getCommonFacade().getReg_relationshipByProcId(proc_id).getReg_unit_code();
		List<Map<String,Object>> historyHolderMapList = getEffictiveHolderMapListByRegUnitCode(reg_unit_code,"");
		BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
		String bus_id = businessMain.getBus_id();
		
		Applicant applicant = null;
		Object tempObj = null;
		for(Map tempHolder : historyHolderMapList){
			
			applicant = new Applicant();
			applicant.setBus_id(bus_id);
			applicant.setApplicant_id(applicantDao.getSeqId());
			
			tempObj = tempHolder.get("HOL_NAME");
			if(tempObj != null){
				applicant.setApp_name(tempObj.toString());
			}
			
			tempObj = tempHolder.get("HOL_TYPE");
			if(tempObj != null){
				applicant.setApp_type(tempObj.toString());
			}
			
			tempObj = tempHolder.get("HOL_CER_TYPE");
			if(tempObj != null){
				applicant.setApp_cer_type(tempObj.toString());
			}
			
			tempObj = tempHolder.get("HOL_CER_NO");
			if(tempObj != null){
				applicant.setApp_cer_no(tempObj.toString());
			}
			
			tempObj = tempHolder.get("HOL_ADDRESS");
			if(tempObj != null){
				applicant.setApp_address(tempObj.toString());
			}
			
			tempObj = tempHolder.get("DEPART_TYPE");
			if(tempObj != null){
				applicant.setDepart_type(tempObj.toString());
			}
			
			tempObj = tempHolder.get("PORTION");
			if(tempObj != null){
				applicant.setApp_port(tempObj.toString());
			}
			
			tempObj = tempHolder.get("LEGAL_NAME");
			if(tempObj != null){
				applicant.setLegal_name(tempObj.toString());
			}
			
			tempObj = tempHolder.get("AGENT_NAME");
			if(tempObj != null){
				applicant.setAgent_name(tempObj.toString());
			}
			
			tempObj = tempHolder.get("AGENT_CER_TYPE");
			if(tempObj != null){
				applicant.setAgent_cer_type(tempObj.toString());
			}
			
			tempObj = tempHolder.get("AGENT_CER");
			if(tempObj != null){
				applicant.setAgent_cer(tempObj.toString());
			}
			
			tempObj = tempHolder.get("AGENT_TEL");
			if(tempObj != null){
				applicant.setAgent_tel(tempObj.toString());
			}
			
			applicantDao.save(applicant);
		}
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		resultMap.put("result", "success");
		
		return resultMap;
	}
	
	
	/**
	 * 
	 * getBusNameMapByRegCode:(通过登记编号获取业务类型名).
	 *
	 * @author Joyon
	 * @param paraMap  reg_code
	 * @return
	 * @since JDK 1.6
	 */
	private Map<String,Object> getBusNameMapByRegCode(Map paraMap) throws BusinessException{
		return businessMainDao.queryMapByKey("Register.getBusNameMapByRegCode", paraMap);
	}
	
	
	/**
	 * 
	 * getEffectiveReg_OwnershipByProcId:(通过流程实例ID获取有效的登记簿所有权部分信息)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public Reg_ownership getEffectiveReg_OwnershipByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		
		//暂时用这种方法获取有效数据
		//String tempWhereSql ="where reg_code=(select reg_code from bus_main where bus_id=(select last_bus_id from bus_main where proc_id=:proc_id))";
		
		//等库改过来后  用这条语句获取      通过房屋编码获取 有效的登记信息
		String realWhereSql ="where book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=(select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=(select bus_id from bus_main where proc_id=:proc_id))) and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";
		return reg_ownershipDao.get(realWhereSql, paraMap);
	}
	/**
	 * 
	 * getEffectiveReg_OwnershipByRegUnitCode:(获取当前登记单元编号下生效的所有权数据)
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @return Reg_Ownership
	 * @since JDK 1.6
	 */
	public Reg_ownership getEffectiveReg_OwnershipByRegUnitCode(String reg_unit_code) throws BusinessException{
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_unit_code", reg_unit_code);
		//等库改过来后  用这条语句获取      通过房屋编码获取 有效的登记信息
		String realWhereSql ="where book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=:reg_unit_code) and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";//(select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=(select bus_id from bus_main where proc_id=:proc_id)))";
		return reg_ownershipDao.get(realWhereSql, paraMap);
	}
	/**
	 * 
	 * 获取当前登记单元编号下生效的抵押权数据
	 *
	 * @author Joyon
	 * @param reg_unit_code 登记单元编号
	 * @return list
	 * @since JDK 1.6
	 */
	public List<Reg_Mortgage> getEffectiveReg_MortgageListByRegUnitCode(String reg_unit_code) throws BusinessException{
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_unit_code", reg_unit_code);
		//等库改过来后  用这条语句获取      通过房屋编码获取 有效的登记信息
		String realWhereSql ="where book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=:reg_unit_code) and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";//(select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=(select bus_id from bus_main where proc_id=:proc_id)))";
		return reg_MortgageDao.getAll(realWhereSql, paraMap);
	}
	/**
	 * 
	 * 获取当前登记单元编号下生效的查封数据
	 *
	 * @author Joyon
	 * @param reg_unit_code 登记单元编号
	 * @return list
	 * @since JDK 1.6
	 */
	public List<Reg_Distrain> getEffectiveReg_DistrainListByRegUnitCode(String reg_unit_code) throws BusinessException{
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_unit_code", reg_unit_code);
		//等库改过来后  用这条语句获取      通过房屋编码获取 有效的登记信息
		String realWhereSql ="where book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=:reg_unit_code) and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";//(select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=(select bus_id from bus_main where proc_id=:proc_id)))";
		return reg_DistrainDao.getAll(realWhereSql, paraMap);
	}
	/**
	 * 
	 * getEffectiveCerNoByRegUnitCode:(获取当前登记单元编号下生效的房地产证号)
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @return String  cer_no
	 * @since JDK 1.6
	 */
	public String getEffectiveCerNoByRegUnitCode(String reg_unit_code) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_unit_code", reg_unit_code);
		List<Reg_relationship> regRelationShipList = reg_relationshipDao.getAll("where reg_unit_code=:reg_unit_code",paraMap);
		String reg_unti_type = regRelationShipList.get(0).getReg_unit_type();
		if(reg_unti_type.equals(WbfConstants.HOUSE)){
			Reg_ownership reg_ownership = getEffectiveReg_OwnershipByRegUnitCode(reg_unit_code);
			if(reg_ownership!=null)
				return reg_ownership.getCer_no();
		}else if(reg_unti_type.equals(WbfConstants.PARCEL)){
			return getEffectiveReg_userightByRegUnitCode(reg_unit_code).getCer_no();
		}
		return null;
	}
	
	/**
	 * 
	 * getEffectiveReg_OwnershipMapByProcId:(通过流程实例ID获取有效的登记簿所有权部分信息)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getEffectiveReg_OwnershipMapByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		
		//暂时用这种方法获取有效数据
		//String tempWhereSql ="where reg_code=(select reg_code from bus_main where bus_id=(select last_bus_id from bus_main where proc_id=:proc_id))";
		//等库改过来后  用这条语句获取      通过房屋编码获取 有效的登记信息
		String realWhereSql ="where book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=(select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=(select bus_id from bus_main where proc_id=:proc_id))) and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";
		return reg_ownershipDao.queryMapByKey("Register.getBkOwnership", realWhereSql, paraMap);
	}
	/**
	 * 
	 * getEffectiveReg_userightMapByProcId:(通过流程实例ID获取有效的登记簿使用部分信息)
	 *
	 * @author Joyon
	 * @return  Map<String,Object>
	 * @since JDK 1.6
	 */
	public Map<String,Object> getEffectiveReg_userightMapByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		
		
		//暂时用这种方法获取有效数据
		String tempWhereSql ="where reg_code=(select reg_code from bus_main where bus_id=(select last_bus_id from bus_main where proc_id=:proc_id))";
		
		//等库改过来后  用这条语句获取      通过房屋编码获取 有效的登记信息
		String realWhereSql ="where book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=(select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=(select bus_id from bus_main where proc_id=:proc_id))) and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";
		return reg_ownershipDao.queryMapByKey("Register.getUseright", realWhereSql, paraMap);
	}
	
	/**
	 * 
	 * getEffectiveReg_userightByRegUnitCode:(获取当前登记单元编号下生效的使用权数据)
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @return Reg_Useright
	 * @since JDK 1.6
	 */
	public Reg_Useright getEffectiveReg_userightByRegUnitCode(String reg_unit_code) {
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_unit_code", reg_unit_code);
		//等库改过来后  用这条语句获取      通过房屋编码获取 有效的登记信息
		String realWhereSql ="where book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=:reg_unit_code) and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";//(select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=(select bus_id from bus_main where proc_id=:proc_id)))";
		return reg_UserightDao.get(realWhereSql, paraMap);
	}
	
	
	/**
	 * 
	 * getEffectiveholderRelationshipRegUnitCode:(获取当前登记单元编号 下生效的权利人集合信息).
	 * @author Joyon
	 * @param reg_unit_code
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public HolderRelationship getEffectiveholderRelationshipRegUnitCode(String reg_unit_code) throws BusinessException{
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_unit_code", reg_unit_code);
		//等库改过来后  用这条语句获取      通过房屋编码获取 有效的登记信息
		String realWhereSql ="where book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=:reg_unit_code) and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";//(select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=(select bus_id from bus_main where proc_id=:proc_id)))";
		return holderRelationshipDao.get(realWhereSql, paraMap);
	}
	/**
	 * 
	 * TODO 抵押登记簿预览时，获取页面左侧树结构.
	 * @see com.szhome.cq.business.IRegisterFacade#getRegBookInfoForMort(java.util.Map)
	 */
	public List<Map<String, Object>> getRegBookTreeForMort(Map m) {
		//根据业务id从登记单元关联表中获取登记单元编号，登记单元类型
				List<Map<String, Object>> regunitlist = mortDao.queryMapListByKey("Mortgage.getRegunitList", m);
				List<Map<String, Object>> regesate = new ArrayList<Map<String, Object>>();
				Map result = null;	
				int i = 0;
				String name = null;
				//条件集合
				Map macon = new HashMap();
				Map<String,Object> attributesMap = null;			//用来存储其它自定义属性到树节点为中    主要放登记单元编号
				for(Map map:regunitlist){
					attributesMap = new HashMap<String,Object>();
					String type = map.get("REG_UNIT_TYPE").toString();
					String code = map.get("REG_UNIT_CODE").toString();
					macon.put("code",code);
					attributesMap.put("code",code);
					//判断当前登记单元类型为房屋
					if(type.trim().equals(WbfConstants.REG_UNIT_HOUSE)){
						result = FacadeFactory.getMortgageFacade().getRegunitMess("Mortgage.getHouseInfo", macon);
						String buildingname = result.get("BUILDING_NAME").toString();
						String buildno = result.get("BUILD_NO").toString();
						String roomname = result.get("ROOMNAME").toString();
						String proname = result.get("PRO_NAME").toString();
						name = proname+buildingname+buildno+roomname;
						map.put("text", name);
						map.put("attributes",attributesMap);
					}
					//判断当前登记单元类型为建筑物
					if(type.trim().equals(WbfConstants.REG_UNIT_BUILDING)){
						String buildingname = result.get("BUILDING_NAME").toString();
						String buildno = result.get("BUILD_NO").toString();
						name = buildingname+buildno;
						map.put("text", name);
						map.put("attributes",attributesMap);
						result = FacadeFactory.getMortgageFacade().getRegunitMess("Mortgage.getBuildInfo", macon);
					}
					//判断当前登记单元类型为土地
					if(type.trim().equals(WbfConstants.REG_UNIT_PARCEL)){				
						result = FacadeFactory.getMortgageFacade().getRegunitMess("Mortgage.getLandInfo", macon);
						map.put("text", "宗地"+code);
						map.put("attributes",attributesMap);
					}
					
					map.put("id",i);
					map.put("children",setTreeChildrenForMort(attributesMap));
					i++;
					regesate.add(map);			
				}
				
				return regesate;
	}
	/**
	 * 
	 * setTreeChildrenForMort:登记簿预览时，向左侧树结构添加子节点. <br/>
	 * @author PANDA
	 * @return
	 * @since JDK 1.6
	 */
	private List<Map<String, Object>>  setTreeChildrenForMort(Map<String,Object> attributesMap){
		List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
		Map nature = new HashMap();
		nature.put("id","1");
		nature.put("text","自然信息");
		nature.put("attributes", attributesMap);
		children.add(nature);
		Map useRight = new HashMap();
		useRight.put("id","2");
		useRight.put("text","抵押权信息");
		useRight.put("attributes", attributesMap);
		children.add(useRight);
		return children;
	}
	
	/**
	 * 
	 * updateBkAttachState:(更新查封登记簿状态).
	 *
	 * @author xuzz
	 * @param reg_code	前登记编号
	 * @param reg_unit_code  登记单元编号
	 * @param state   要设置的状态
	 * @since JDK 1.6
	 */
	private void updateBkAttachState(String reg_code,String reg_unit_code,String state){
		if((reg_code == null || reg_code.equals("")) || (reg_unit_code == null || reg_unit_code.equals("")) || (state == null || state.equals(""))){
			//throw new BusinessException("RegisterFacade.updateBkMortState 条件为空 ");
			LogUtil.error("RegisterFacade.updateBkAttachState---更新查封登记簿状态  条件为空  reg_code："+reg_code+" reg_unit_code:"+reg_unit_code+" state:"+state);
			return;
		}else{
			Map<String,Object> paraMap = new HashMap<String,Object>();
			paraMap.put("reg_code", reg_code);
			paraMap.put("reg_unit_code", reg_unit_code);
			paraMap.put("state", state);
			//先从查封登记簿中获取   通过登记编号   和登记单元编号
			Reg_Distrain regDistrain = reg_DistrainDao.get("where reg_code=:reg_code and book_code=(select book_code from bk_baseinfo where reg_unit_code=:reg_unit_code)", paraMap);
			//更新状态
			regDistrain.setEffective(state);
			reg_DistrainDao.update(regDistrain);
		}
		 
	}
	/**
	 * 
	 * updateBkDemurrerState:(更新异议登记簿状态).
	 *
	 * @author xuzz
	 * @param reg_code	前登记编号
	 * @param reg_unit_code  登记单元编号
	 * @param state   要设置的状态
	 * @since JDK 1.6
	 */
	private void updateBkDemurrerState(String reg_code,String reg_unit_code,String state){
		if((reg_code == null || reg_code.equals("")) || (reg_unit_code == null || reg_unit_code.equals("")) || (state == null || state.equals(""))){
			//throw new BusinessException("RegisterFacade.updateBkMortState 条件为空 ");
			LogUtil.error("RegisterFacade.updateBkDemurrerState---更新异议登记簿状态  条件为空  reg_code："+reg_code+" reg_unit_code:"+reg_unit_code+" state:"+state);
			return;
		}else{
			Map<String,Object> paraMap = new HashMap<String,Object>();
			paraMap.put("reg_code", reg_code);
			paraMap.put("reg_unit_code", reg_unit_code);
			paraMap.put("state", state);
			//先从查封登记簿中获取   通过登记编号   和登记单元编号
			Reg_Demurrer regDemurrer = reg_DemurrerDao.get("where reg_code=:reg_code and book_code=(select book_code from bk_baseinfo where reg_unit_code=:reg_unit_code)", paraMap);
			//更新状态
			regDemurrer.setEffective(state);
			reg_DemurrerDao.update(regDemurrer);
		}
	}
	
	/**
	 * 
	 * updateBkMortState:(更新抵押权登记簿状态).
	 *
	 * @author Joyon
	 * @param reg_code	抵押编号
	 * @param reg_unit_code  抵押登记单元编号
	 * @param state   要设置的状态
	 * @since JDK 1.6
	 */
	private void updateBkMortState(String reg_code,String reg_unit_code,String state){
		if((reg_code == null || reg_code.equals("")) || (reg_unit_code == null || reg_unit_code.equals("")) || (state == null || state.equals(""))){
			//throw new BusinessException("RegisterFacade.updateBkMortState 条件为空 ");
			LogUtil.error("RegisterFacade.updateBkMortState---更新抵押登记簿状态  条件为空  reg_code："+reg_code+" reg_unit_code:"+reg_unit_code+" state:"+state);
			return;
		}else{
			Map<String,Object> paraMap = new HashMap<String,Object>();
			paraMap.put("reg_code", reg_code);
			paraMap.put("reg_unit_code", reg_unit_code);
			paraMap.put("state", state);
			//先从抵押登记簿中获取   通过抵押编号   和抵押登记单元编号
			Reg_Mortgage regMortage = reg_MortgageDao.get("where reg_code=:reg_code and book_code=(select book_code from bk_baseinfo where reg_unit_code=:reg_unit_code)", paraMap);
			//更新状态
			regMortage.setEffective(state);
			reg_MortgageDao.update(regMortage);
		}
		
	}
	
	
	/**
	 * 
	 * getOrigMortRegCode:(获取原抵押登记编号). 
	 * @author Joyon
	 * @param bus_id
	 * @return
	 * @since JDK 1.6
	 */
	private String getOrigMortRegCode(String bus_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id", bus_id);
		String origMortRegCode = null;
		List<Map<String,Object>> regUnitRelMapList = reg_MortgageDao.queryMapListByKey("Mortgage.getRegUnitRel","where bus_id=:bus_id",paraMap);
		if(!(regUnitRelMapList.size()==0)){
			origMortRegCode = regUnitRelMapList.get(0).get("LAST_REG_CODE").toString();
		}else{
			LogUtil.error("MortgageFacade.getOrigMortRegCode  登记单元表数据为空   ");
		}
		return origMortRegCode;
	}
	
	/**
	 * 
	 * getRegMortgageByRegUnitCodeAndRegCode:(通过登记单元编号和登记编号获取抵押登记簿信息). 
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @param reg_code
	 * @return
	 * @since JDK 1.6
	 */
	public Reg_Mortgage getRegMortgageByRegUnitCodeAndRegCode(String reg_unit_code,String reg_code){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_unit_code", reg_unit_code);
		paraMap.put("reg_code", reg_code);
		String whereSql = "where reg_code=:reg_code and book_code=(select book_code from BK_BASEINFO where reg_unit_code=:reg_unit_code)";
		Reg_Mortgage reg_Mortgage = null;
		try {
			reg_Mortgage = reg_MortgageDao.get(whereSql, paraMap);
		} catch (Exception e) {
			LogUtil.error("RegisterFacade.getRegMortgageByRegUnitCodeAndRegCode 获取抵押登记簿信息出错");
		}
		return reg_Mortgage;
	}
	@Override
	public List<Map<String, Object>> getCurrentHolderMapListByProcId(
			Map<String, Object> params) {
		return houseDao.queryMapListByKey("Register.getCurrentHolderMapListByProcId", params);
	}
	
}





