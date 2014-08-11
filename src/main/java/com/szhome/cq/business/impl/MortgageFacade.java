package com.szhome.cq.business.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plan.exceptions.GeneralException;
import com.springjdbc.annotation.Page;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IMortgageFacade;
import com.szhome.cq.domain.model.Applicant;
import com.szhome.cq.domain.model.Certificate;
import com.szhome.cq.domain.model.HolderRelationship;
import com.szhome.cq.domain.model.Mortgage;
import com.szhome.cq.domain.model.Reg_Mortgage;
import com.szhome.cq.domain.model.Reg_relationship;
import com.szhome.cq.utils.SQLUtils;
import com.szhome.cq.utils.Util;
import com.szhome.cq.utils.WbfConstants;

/**
 * 业务主表
 * 
 * @author Mignet
 */
@Component
@Transactional
@Scope("prototype")
public  class MortgageFacade implements IMortgageFacade {

	@Autowired
	private Mortgage mortDao;	
	@Autowired
	private Applicant appDao;
	@Autowired
	private Certificate cerDao;
	@Autowired
	private Reg_Mortgage reg_MortgageDao;
	@Autowired
	private HolderRelationship holderRelationshipDao;				//权利人关联表
	@Autowired
	private Reg_relationship reg_relationshipDao;					//登记单元关联表
	
	//二元数组,数组值意义,1.数据表里的字段,2.实体对象对应字段,3.类型区分【1:代表字符串类型,2:代表时间类型,3:代表日期加时间,4:代表金额类型】
	static final String[][] mortOptions = {{"tab.reg_code","regcode","1"},
        {"tab.mortgagee","mortgagee","1"},
        {"tab.mortgager","mortgager","1"},
        {"tab.cer_no","cerno","1"},
        {"tab.reg_date","regdate","2"},
        {"tab.recorder","recorder","1"}
       };
			
    /**
     * 
     * 获取抵押登记信息.
     * @see com.szhome.cq.business.IMortgageFacade#getMortById(java.util.Map)
     */
	@Override
	public Mortgage getMortById(Map m) {
		
		Mortgage mo = mortDao.queryDomainBykey("Mortgage.getMortByid",m);
		if( mo == null){
			
			System.out.println();
		}
		return mo;
	}
    /**
     * 
     * 保存抵押登记信息.
     * @see com.szhome.cq.business.IMortgageFacade#saveMortMess(com.szhome.cq.domain.model.Mortgage)
     */
	@Override
	@Transactional
	public void saveMortMess(Mortgage mort) {
		Map map = new HashMap();
		map.put("id",mort.getBus_id());
		Mortgage mo = mortDao.get(" where bus_id=:id",map);
		
		//用来解决  在copy属性时会把id给覆盖没 
		String mort_reg_id = null;
		if(mo != null){
			mort_reg_id = mo.getMort_reg_id();
		}
		try {
			if( mo == null){
				
				mort.setMort_reg_id(mortDao.getSeqId());	
				mortDao.save(mort);
			}else{
				mortDao.copyProperties(mo, mort);
				
				if(mo.getMort_reg_id()==null || mo.getMort_reg_id().equals("mo")){
					mo.setMort_reg_id(mort_reg_id);
				}
				mortDao.update(mo);
				
			}
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("抵押信息保存出错:"+e.getMessage());
		}
	
		
	}
	/**
	 * 
	 * 根据条件查询注销抵押登记信息.
	 * @see com.szhome.cq.business.IMortgageFacade#getMortcanById(java.util.Map)
	 */
	@Override
	public Map getMortcanById(String id) {
		Map m = new HashMap();
		m.put("id",id);
		Map map = mortDao.queryMapByKey("Mortgage.getMortcanByid", m);
		return map;
	}
	/**
	 * 
	 * 获取注销抵押选中的登记单元信息.
	 * @see com.szhome.cq.business.IMortgageFacade#getRegunitMess(java.lang.String)
	 */
	@Override
	public Map getRegunitMess(String key,Map m) {
		
		Map regunit = mortDao.queryMapByKey(key, m);
		return regunit;
	}
	/**
	 * 
	 * 获取登记单元集合.
	 * @see com.szhome.cq.business.IMortgageFacade#getRegunitList(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> getRegunitList(Map m) {
		//根据业务id从登记单元关联表中获取登记单元编号，登记单元类型
		List<Map<String, Object>> regunitlist = mortDao.queryMapListByKey("Mortgage.getRegunitList", m);
		List<Map<String, Object>> regesate = new ArrayList<Map<String, Object>>();
		Map resultList = null;			
		//条件集合
		Map macon = new HashMap();
		for(Map map:regunitlist){
			String type = map.get("REG_UNIT_TYPE").toString();
			String code = map.get("REG_UNIT_CODE").toString();
			macon.put("code",code);
			//判断当前登记单元类型为房屋
			if(type.trim().equals(WbfConstants.REG_UNIT_HOUSE)){
				
				resultList = getRegunitMess("Mortgage.getHouseInfo", macon); 
				resultList.put("TYPE", type);
				resultList.put("CODE", code);
			}
			//判断当前登记单元类型为建筑物
			if(type.trim().equals(WbfConstants.REG_UNIT_BUILDING)){
				resultList = getRegunitMess("Mortgage.getBuildInfo", macon);
				resultList.put("TYPE", type);
				resultList.put("CODE", code);
			}
			//判断当前登记单元类型为土地
			if(type.trim().equals(WbfConstants.REG_UNIT_PARCEL)){				
				resultList = getRegunitMess("Mortgage.getLandInfo", macon);		
				resultList.put("TYPE", type);
				resultList.put("CODE", code);
			}
			regesate.add(resultList);			
		}
		
		return regesate;
	}
	/**
	 * 
	 * 根据流程实例id获取登记信息.
	 * @see com.szhome.cq.business.IMortgageFacade#getRegById(java.lang.String)
	 */
	@Override
	public Map getRegById(String id) {
		Map m = new HashMap();
		m.put("id",id);
		Map reg = mortDao.queryMapByKey("Mortgage.getRegInfo", m);
		
		return reg;
	}
	/**
	 * 
	 * 抵押设立登记业务中，获取权利人信息集合.
	 * @see com.szhome.cq.business.IMortgageFacade#getMortgager(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> getMortgager(Map m){
		//检查申请人表中是否已存在抵押人信息
		List  <Map<String, Object>> check = appDao.queryMapListByKey("Mortgage.getAppInfo", m);
		if(check.isEmpty()){
		//首先将权利人信息保存到申请人表中
		saveHolderToApp(m);	
		}
		//再从申请表中获取与当前业务关联的申请人信息
		List  <Map<String, Object>>  applist = appDao.queryMapListByKey("Mortgage.getAppInfo", m);
		
		return applist;
		
	}

	/**
	 * 
	 * getHolderList:获取与不同登记编号相关联的权利人集合信息. <br/>
	 * @author PANDA
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getHolderList(Map m){
		
		//获取登记单元关联表中，与当前业务相关的登记单元集合
		List<Map<String, Object>> regunitlist = mortDao.queryMapListByKey("Mortgage.getRegunitList", m);
		List<Map<String, Object>> regesate = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> resultList = null;	
		Map macon = new HashMap();
		for(Map map:regunitlist){
			String code = map.get("REG_UNIT_CODE").toString();
			macon.put("code",code);
			//根据登记单元编码，获取权利人集合
			resultList = FacadeFactory.getRegisterFacade().getEffictiveHolderMapListByRegUnitCode(code,"");
			//将获得的权利人集合信息保存在集合中
			if(!resultList.isEmpty()){
			for(Map holder:resultList){
				
				holder.put("REG_UNIT_CODE",code);
				
			}
			
			regesate.addAll(resultList);
			}
		}
		return regesate;
	}
	 /**
	  *  
	  * saveHolder:保存权利人到申请人表. <br/>
	  * @author PANDA
	  * @param holder
	  * @param tempObj
	  * @param id
	  * @since JDK 1.6
	  */
	public void saveHolder(Map holder,Object tempObj,String id ){
		Applicant app = new Applicant();
		//保存业务主表id
		app.setApplicant_id(appDao.getSeqId());
		//保存业务id
		app.setBus_id(id);
		//保存申请人名称
		tempObj = holder.get("HOL_NAME");
		if(tempObj != null){
		app.setApp_name(tempObj.toString());
		}
		//保存申请人类型
		tempObj = holder.get("HOL_TYPE");
		if(tempObj != null){
		app.setApp_type(tempObj.toString());
		}
		//保存份额信息
		tempObj = holder.get("PORTION");
		if(tempObj != null){
		app.setApp_port(tempObj.toString());
		}
		
		//保存申请人证件类型
		tempObj = holder.get("HOL_CER_TYPE");
		if(tempObj != null){
		app.setApp_cer_type(tempObj.toString());
		}

		//保存申请人证件编号
		tempObj = holder.get("HOL_CER_NO");
		if(tempObj != null){
		app.setApp_cer_no(tempObj.toString());
		}
		
		//保存申请人地址
		tempObj = holder.get("HOL_ADDRESS");
		if(tempObj != null){
		app.setApp_address(tempObj.toString());
		}

		//保存申请人联系电话
		app.setApp_tel("");
		//保存单位性质
		tempObj = holder.get("DEPART_TYPE");
		if(tempObj != null){
		app.setDepart_type(tempObj.toString());
		}
		//保存法人
		tempObj = holder.get("LEGAL_NAME");
		if(tempObj != null){
		app.setLegal_name(tempObj.toString());
		}
		//保存代理人名称
		tempObj = holder.get("AGENT_NAME");
		if(tempObj != null){
		app.setAgent_name(tempObj.toString());
		}
		
		//保存代理人联系电话
		tempObj = holder.get("AGENT_TEL");
		if(tempObj != null){
		app.setAgent_tel(tempObj.toString());
		}
		//保存代理人证件号码
		tempObj = holder.get("AGENT_CER");
		if(tempObj != null){
		app.setAgent_cer(tempObj.toString());
		}
			
		//保存代理人证件类型
		tempObj = holder.get("AGENT_CER_TYPE");
		if(tempObj != null){
		app.setAgent_cer_type(tempObj.toString());
		}
		//保存登记单元编号
		tempObj = holder.get("REG_UNIT_CODE");
		if(tempObj != null){
		app.setReg_unit_code(tempObj.toString());
		}
		//标识权利人为抵押人
		tempObj = holder.get("HOL_REL");
		if(tempObj != null){
		app.setHol_rel(tempObj.toString());
		}
		//app.setHol_rel(holder);
		 try {
			appDao.save(app);
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}			
		
		
	}
	/**
	 * 
	 * saveHolderToApp:保存权利人信息到申请表中. <br/>
	 * @author PANDA
	 * @since JDK 1.6
	 */
	@Transactional
	public void  saveHolderToApp(Map m){
		
		//调用获取权利人集合的方法，获取与当前登记单元关联的权利人集合
		List<Map<String, Object>> holderList = getHolderList(m);
		
		Object tempObj = null;
		//循环遍历权利人集合，逐条保存到申请人表中
		for(Map holder:holderList){
			holder.put("HOL_REL", m.get("type").toString());
			saveHolder(holder,tempObj,m.get("id").toString());
		}
	}
      
     /**
      * 
      * 获取抵押权人信息.
      * @see com.szhome.cq.business.IMortgageFacade#getMortgagee(java.util.Map)
      */
	@Override
	public List<Map<String, Object>> getMortgagee(Map m) {
		
		List  <Map<String, Object>>  applist = appDao.queryMapListByKey("Mortgage.getAppInfo", m);
		return applist;
	}
	
	/**
	 * 
	 * getMortgageeTransferor:(获取抵押权人   如果过程表中不存在   则从登记簿中获取). 
	 *
	 * @author Joyon
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getMortgageeTransferor(Map m){
		//检查申请人表中是否已存在抵押人信息
				String bus_id = m.get("id").toString();
				List  <Map<String, Object>> check = appDao.queryMapListByKey("Mortgage.getAppInfo", m);
				if(check.isEmpty()){
					//获取原抵押登记编号 
					String orig_mort_reg_code = getOrigMortRegCode(bus_id);
					if(orig_mort_reg_code == null || orig_mort_reg_code.equals("")){
						LogUtil.error("MortgageFacade.saveBkMortgagerToBusMortgager 原抵押登记编号为空！");
						return null;
					}
					m.put("orig_mort_reg_code", orig_mort_reg_code);
					
					//通过原抵押权登记编号   获取原抵押登记中 抵押权人  并保存到申请人中  权利人关系为抵押权人转让方        
					String wheresql = "where reg_code=:orig_mort_reg_code";
					
					//债权人多个登记单元相同  所以只需要 取一套就可以
					List<Map<String, Object>> holderList = null;
					List<HolderRelationship> holderRelationshipList = holderRelationshipDao.getAll(wheresql, m);
					if(holderRelationshipList.size()>0){
						String right_rel_id = holderRelationshipList.get(0).getRight_rel_id();
						m.put("right_rel_id", right_rel_id);
					}else{
						LogUtil.error("MortgageFacade.getMortgageeTransferor 根据原抵押编号 权利人关联表出错");
						return null;
					}
					try {
						wheresql = "where RIGHT_REL_ID =:right_rel_id and hol_rel='"+WbfConstants.MORTGAGEE+"'";
						holderList = appDao.queryMapListByKey(
								"Mortgage.getHolder", wheresql, m);
					} catch (Exception e) {
						LogUtil.error("MortgageFacade.getMortgageeTransferor获取抵押转让方出错:"+e.getMessage());
					}
					
					
					
					Object tempObj = null;
					//循环遍历权利人集合，逐条保存到申请人表中
					for(Map holder:holderList){
						holder.put("HOL_REL", m.get("type").toString());
						saveHolder(holder,tempObj,m.get("id").toString());
					}
					
					//----把上一次抵押人也保存到申请人中  查出包括登记单元code  并且为抵押人
					//wheresql = "where RIGHT_REL_ID = (select RIGHT_REL_ID from BK_RIGHT_REL where reg_code=:orig_mort_reg_code) and hol_rel ='"+WbfConstants.MORTGAGER+"'";
					wheresql =" where r.reg_code=:orig_mort_reg_code and h.right_rel_id=r.right_rel_id and b.book_code=r.book_code and h.hol_rel='"+WbfConstants.MORTGAGER+"'";
					holderList = appDao.queryMapListByKey("Mortgage.getMortgagerFromBk",wheresql, m);
					for(Map holder:holderList){
						saveHolder(holder,tempObj,m.get("id").toString());
					}
				}
				//再从申请表中获取与当前业务关联的申请人信息
				List  <Map<String, Object>>  applist = appDao.queryMapListByKey("Mortgage.getAppInfo", m);
				
				
				
				return applist;
	}
	
	/**
	 * 
	 * saveBkMortgagerToBusMortgager:(把前抵押编号下的抵押人保存到申请人表中,如果申请表中有了抵押人  则不再保存  而是直接返回).
	 *
	 * @author Joyon
	 * @return 申请人表中抵押人list集合
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> saveBkMortgagerToBusMortgager(String bus_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id", bus_id);
		List<Map<String,Object>> dbAppMortgagerList = appDao.queryMapListByKey("Mortgage.getApplicant","where bus_id=:bus_id and hol_rel='"+WbfConstants.MORTGAGER+"'" ,paraMap);
		if(dbAppMortgagerList.isEmpty() || dbAppMortgagerList == null){
			//获取原抵押登记编号 
			String orig_mort_reg_code = getOrigMortRegCode(paraMap.get("bus_id").toString());
			if(orig_mort_reg_code == null || orig_mort_reg_code.equals("")){
				LogUtil.error("MortgageFacade.saveBkMortgagerToBusMortgager 原抵押登记编号为空！");
				return null;
			}
			paraMap.put("orig_mort_reg_code", orig_mort_reg_code);
			//----把上一次抵押人也保存到申请人中  查出包括登记单元code  并且为抵押人
			//wheresql = "where RIGHT_REL_ID = (select RIGHT_REL_ID from BK_RIGHT_REL where reg_code=:orig_mort_reg_code) and hol_rel ='"+WbfConstants.MORTGAGER+"'";
			String wheresql =" where r.reg_code=:orig_mort_reg_code and h.right_rel_id=r.right_rel_id and b.book_code=r.book_code and h.hol_rel='"+WbfConstants.MORTGAGER+"'";
			List<Map<String,Object>> holderList = appDao.queryMapListByKey("Mortgage.getMortgagerFromBk",wheresql, paraMap);
			Object tempObj = null;
			for(Map holder:holderList){
				saveHolder(holder,tempObj,paraMap.get("bus_id").toString());
			}
			
			dbAppMortgagerList = appDao.queryMapListByKey("Mortgage.getApplicant","where bus_id=:bus_id and hol_rel='"+WbfConstants.MORTGAGER+"'" ,paraMap);
		}
		return dbAppMortgagerList;
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
		List<Map<String,Object>> regUnitRelMapList = appDao.queryMapListByKey("Mortgage.getRegUnitRel","where bus_id=:bus_id",paraMap);
		if(!(regUnitRelMapList.size()==0)){
			if(Util.isNotNull2Empty(regUnitRelMapList.get(0).get("LAST_REG_CODE")))
			origMortRegCode = regUnitRelMapList.get(0).get("LAST_REG_CODE").toString();
		}else{
			LogUtil.error("MortgageFacade.getOrigMortRegCode  登记单元表数据为空   ");
		}
		return origMortRegCode;
	}
	/**
	 * 
	 * saveBkMortgageeToBusMortgagee:(保存前抵押编号下的抵押权人到申请人表中   如果申请人中有抵押权人  则不再保存).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return 申请人中抵押权人list集合
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> saveBkMortgageeToBusMortgagee(String bus_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id", bus_id);
		List<Map<String,Object>> dbAppMortgageeList = appDao.queryMapListByKey("Mortgage.getApplicant","where bus_id=:bus_id and hol_rel='"+WbfConstants.MORTGAGEE+"'" ,paraMap);
		if(dbAppMortgageeList.isEmpty() || dbAppMortgageeList == null){
			//获取原抵押登记编号 
			String orig_mort_reg_code = getOrigMortRegCode(bus_id);
			if(orig_mort_reg_code == null || orig_mort_reg_code.equals("")){
				LogUtil.error("MortgageFacade.saveBkMortgagerToBusMortgager 原抵押登记编号为空！");
				return null;
			}
			paraMap.put("orig_mort_reg_code", orig_mort_reg_code);
			//----把上一次抵押人也保存到申请人中  查出包括登记单元code  并且为抵押人
			//wheresql = "where RIGHT_REL_ID = (select RIGHT_REL_ID from BK_RIGHT_REL where reg_code=:orig_mort_reg_code) and hol_rel ='"+WbfConstants.MORTGAGER+"'";
			String wheresql =" where r.reg_code=:orig_mort_reg_code and h.right_rel_id=r.right_rel_id and b.book_code=r.book_code and h.hol_rel='"+WbfConstants.MORTGAGEE+"'";
			List<Map<String,Object>> holderList = appDao.queryMapListByKey("Mortgage.getMortgagerFromBk",wheresql, paraMap);
			
			Object tempObj = null;
			if(holderList.size()>0){
				saveHolder(holderList.get(0),tempObj,paraMap.get("bus_id").toString());
			}
			
			dbAppMortgageeList = appDao.queryMapListByKey("Mortgage.getApplicant","where bus_id=:bus_id and hol_rel='"+WbfConstants.MORTGAGEE+"'" ,paraMap);
		}
		return dbAppMortgageeList;
	}
	
	/**
	 * 
	 * getMortCancelMortgager:(获取抵押注销 抵押人). 
	 *
	 * @author Joyon
	 * @param bus_id
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getMortCancelMortgager(String bus_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id", bus_id);
		//获取抵押人
		List<Map<String,Object>> dbAppMortgagerList = appDao.queryMapListByKey("Mortgage.getApplicant","where bus_id=:bus_id and hol_rel='"+WbfConstants.MORTGAGER+"'" ,paraMap);
		//如果抵押人为空  则把这次办业务登记的抵押人取出来存到申请表中    然后再出来
		if(dbAppMortgagerList.isEmpty() || dbAppMortgagerList == null){
			String wheresql = "where bus_id=:bus_id";
			List<Reg_relationship> reg_relationshipList = reg_relationshipDao.getAll(wheresql,paraMap);
			for(Reg_relationship tmpRegRelationship :reg_relationshipList){
				String origregcode = tmpRegRelationship.getLast_reg_code();
				String regunitcode = tmpRegRelationship.getReg_unit_code();
				saveBkMortgagerToBusMortgagerByOrigregcodeAndRegunitcode(origregcode,regunitcode,bus_id);
			}
			
			dbAppMortgagerList = appDao.queryMapListByKey("Mortgage.getApplicant","where bus_id=:bus_id and hol_rel='"+WbfConstants.MORTGAGER+"'" ,paraMap);
		}
		return dbAppMortgagerList;
	}
	
	/**
	 * 
	 * saveBkMortgageeToBusMortgageeByOrigregcodeAndRegunitcode:(根据登记单元编号和前抵押编号  保存抵押人到申请表中).
	 *
	 * @author Joyon
	 * @param origregcode
	 * @param regunitcode
	 * @param bus_id
	 * @since JDK 1.6
	 */
	private void saveBkMortgagerToBusMortgagerByOrigregcodeAndRegunitcode(String origregcode,String regunitcode,String bus_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("regunitcode", regunitcode);
		paraMap.put("origregcode", origregcode);
		
		String wheresql = "where hol_rel='"+WbfConstants.MORTGAGER+"' and  right_rel_id = (select right_rel_id from BK_RIGHT_REL where reg_code=:origregcode and book_code=(select book_code from BK_BASEINFO where reg_unit_code=:regunitcode))";
		List<Map<String,Object>> holderList = appDao.queryMapListByKey("Mortgage.getHolder",wheresql, paraMap);
		Object tempObj = null;
		for(int i=0;i<holderList.size();i++){
			holderList.get(i).put("REG_UNIT_CODE", regunitcode);
			saveHolder(holderList.get(0),tempObj,bus_id);
		}
		
	}
	
	/**
	 * 
	 * 根据条件获取存在抵押记录的抵押人信息.
	 * @see com.szhome.cq.business.IMortgageFacade#getMortgagerSeted(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> getMortgagerSeted(Map m) {
		List<Map<String, Object>> resultList = appDao.queryMapListByKey("Mortgage.getAppInfo", m);
		if(resultList.isEmpty()){
		//获取登记单元关联表中，与当前业务相关的登记单元集合
		List<Map<String, Object>> regunitlist = mortDao.queryMapListByKey("Mortgage.getbkMortInfo", m);
		List<Map<String, Object>> regesate = new ArrayList<Map<String, Object>>();
			
		//循环取出抵押人信息
		for(Map mort:regunitlist){
			m.put("book_code",mort.get("BOOK_CODE").toString());
			m.put("code",mort.get("REG_CODE").toString());
			//根据登记编号、登记簿code、权利人关系获取抵押人信息
			regesate = mortDao.queryMapListByKey("Mortgage.getMortgagerForSet", m);
			Object tempObj = null;
			//循环保存抵押人信息到申请人表中
			if(!regesate.isEmpty()){
			
			for(Map holder:regesate){
				//将登记单元编号关联抵押人信息
				holder.put("REG_UNIT_CODE",mort.get("REG_UNIT_CODE").toString());
				//保存权利人到申请人表中
				saveHolder(holder,tempObj,m.get("id").toString());
			}
			}
			
		}
		}
		 resultList = appDao.queryMapListByKey("Mortgage.getAppInfo", m);
		return resultList;
	}
	
	/**
	 * 
	 * saveBkMortageToBusMortgage:(把前抵押编号登记簿中抵押信息保存到过程信息表中   如果过程信息表中己存在则不保存  直接返回).
	 *
	 * @author Joyon
	 * @param bus_id
	 * @return Mortgage
	 * @since JDK 1.6
	 */
	public Mortgage saveBkMortageToBusMortgage(String bus_id){
		String date_regexp = "^((d{4})|d{2})[-\\s]{1}[01]{1}d{1}[-\\s]{1}[0-3]{1}d{1}$";// 匹配日期    
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id", bus_id);
		Mortgage dbMortgage = mortDao.get("where bus_id=:bus_id", paraMap);
		if(dbMortgage == null){
			String origMortRegCode =  getOrigMortRegCode(bus_id);				//原抵押登记编号
			paraMap.put("origMortRegCode", origMortRegCode);
			List<Reg_Mortgage> reg_MortgageList = reg_MortgageDao.getAll("where reg_code=:origMortRegCode", paraMap);
			if(reg_MortgageList.size()>0){
				dbMortgage = new Mortgage();
				Reg_Mortgage dbRegMortgage = reg_MortgageList.get(0);
				mortDao.copyProperties(dbMortgage, dbRegMortgage);
				
				//把字符串日期匹配出来保存进申请表
				String dateStr = dbRegMortgage.getDebt_dis_limit();
				
				 Pattern mpattern = Pattern.compile("(\\d{1,4}[-|\\/]\\d{1,2}[-|\\/]\\d{1,2}) ");
		    	  Matcher m = mpattern.matcher(dateStr);
		    	  int count = 0;
		    	  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    	  while(m.find()){ 
		    		  if(count == 0){
		    			try {
		    				dbMortgage.setCreditor_start_date(dateFormat.parse(m.group(1)));
							
						} catch (ParseException e) {
							e.printStackTrace();
							
						}
		    		  }else{
		    			try {
		    				dbMortgage.setCreditor_end_date(dateFormat.parse(m.group(1)));
								
						} catch (ParseException e) {
							e.printStackTrace();
							
						}
		    		  }
		    		  count++;
		    	  }
				//dbMortgage.setMort_assure_right(Double.valueOf(dbRegMortgage.getSure_amount()));
				dbMortgage.setBus_id(bus_id);
				dbMortgage.setMort_reg_id(mortDao.getSeqId());
				mortDao.save(dbMortgage);
			}
			dbMortgage = mortDao.get("where bus_id=:bus_id", paraMap);
		}
		
		return dbMortgage;
	}
	
	/**
	 * 
	 * 根据条件获取存在抵押记录的抵押权人信息.
	 * @see com.szhome.cq.business.IMortgageFacade#getMortgageeSeted(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> getMortgageeSeted(Map m) {
		List<Map<String, Object>> resultList = appDao.queryMapListByKey("Mortgage.getAppInfo", m);
		if(resultList.isEmpty()){
			//获取登记单元关联表中，与当前业务相关的登记单元集合
			List<Map<String, Object>> regunitlist = mortDao.queryMapListByKey("Mortgage.getbkMortInfo", m);
			List<Map<String, Object>> regesate = new ArrayList<Map<String, Object>>();
			
				m.put("book_code",regunitlist.get(0).get("BOOK_CODE").toString());
				m.put("code",regunitlist.get(0).get("REG_CODE").toString());
				//根据登记编号、登记簿code、权利人关系获取抵押人信息
				regesate = mortDao.queryMapListByKey("Mortgage.getMortgagerForSet", m);
				Object tempObj = null;
				//循环保存抵押人信息到申请人表中
				if(!regesate.isEmpty()){
				
				for(Map holder:regesate){
					//将登记单元编号关联抵押人信息
					holder.put("REG_UNIT_CODE",regunitlist.get(0).get("REG_UNIT_CODE").toString());
					//保存权利人到申请人表中
					saveHolder(holder,tempObj,m.get("id").toString());
				}
				
				
			}
			
			
		}
		 resultList = appDao.queryMapListByKey("Mortgage.getAppInfo", m);
		return resultList;
		
	}
	/**
	 * 
	 * 保存发文信息.
	 * @see com.szhome.cq.business.IMortgageFacade#saveDispathcInfo(com.szhome.cq.domain.model.Certificate)
	 */
	@Transactional
	public void saveDispathcInfo(Certificate cer,List<Map> list,String id) {
		
		    String code = null;
		    String type = null;
		    //定义房地产证号
		    String cerno = null;
		    //设置业务主键id
		    cer.setBus_id(id);
		    Map m = new HashMap();
		    //循环取出list中的值
			for(int i=0;i<list.size();i++){
				code = list.get(i).get("CODE").toString();
				type = list.get(i).get("TYPE").toString();
				//设置查询条件
				m.put("code", code);
				m.put("type",type);
				m.put("effective",WbfConstants.EFFECTIVE);
				//登记单元类型为房屋
				if(type.equals(WbfConstants.REG_UNIT_HOUSE)){
				 	
					cerno = FacadeFactory.getRegisterFacade().getEffectiveReg_OwnershipByRegUnitCode(code).getCer_no();
					
				}
				//登记单元为楼宇
               if(type.equals(WbfConstants.REG_UNIT_BUILDING)){
				 	
            	    cerno = FacadeFactory.getRegisterFacade().getEffectiveReg_OwnershipByRegUnitCode(code).getCer_no();
					
				}
             //登记单元为土地
                if(type.equals(WbfConstants.REG_UNIT_PARCEL)){
				 	
                	cerno =  FacadeFactory.getRegisterFacade().getEffectiveReg_userightByRegUnitCode(code).getCer_no();
                	
					
				}
              //键入房地产证号
             cer.setCertificate_code(cerno);
             //保存信息到房地产证表中
             saveToCer(cer);
             
			}  
		
	}
	
	 /**
	  * 
	  * saveToCer:保存信息到房地产证表中. <br/>
	  * @author PANDA
	  * @param cer
	  * @since JDK 1.6
	  */
     public void saveToCer(Certificate cer) {
		
		Map map = new HashMap();
		map.put("id",cer.getBus_id());
		map.put("code",cer.getCertificate_code());
		//根据业务id和房地产证号，判断是否已保存
		Certificate ce = cerDao.get(" where bus_id=:id and certificate_code=:code",map);
		try {
			if( ce == null){
				
				cer.setCertificate_id(cerDao.getSeqId());	
				cerDao.save(cer);
			}else{
				
				cer.setCertificate_id(ce.getCertificate_id());
				cerDao.update(cer);
				
			}
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("发文信息保存出错:"+e.getMessage());
		}
		
	}
	public Page getAllMortsByParam(
			Map<String, Object> paramMap) {
		String sqlwhere = null;
		int pageNo = 1;
		int pageSize = 3;
		Map<String,Object> resultMap = SQLUtils.growWhereStatment(Util.ConvertObjToMap(paramMap.get("DXTXOBJECT")), mortOptions,null);
		Map<String,Object> fieldMap = null;
		if(resultMap!=null){
			sqlwhere = resultMap.get("SQLWHERELIKE").toString();
			fieldMap = (Map<String, Object>) resultMap.get("OPTIONS");
		}
		if(Util.isNotNull2Empty(paramMap.get("PAGENO"))){
			try {
				pageNo = Integer.parseInt((paramMap.get("PAGENO").toString() == null || paramMap.get("PAGENO").toString().equals("0"))?"1":paramMap.get("PAGENO").toString());
			} catch (Exception e) {
				pageNo = 1;
			}
		}
		if(Util.isNotNull2Empty(paramMap.get("PAGESIZE"))){
			try {
				pageSize = Integer.parseInt((paramMap.get("PAGESIZE").toString() == null || paramMap.get("PAGESIZE").toString().equals("0")?"10":paramMap.get("PAGESIZE").toString()));
			} catch (Exception e) {
				pageSize = 10;
			}
		}
		return mortDao.queryMapPageBykeyForOracle("Mortgage.getAllMortgageInfos", sqlwhere, fieldMap,pageNo,pageSize);
	}
	@Override
	public List<Map<String, Object>> getMortsBymortId(Map<String,Object> sqlMap,
			Map<String, Object> valueMap) {
		String wheresql ="where 1=1 " + sqlMap.get("mortId").toString();
		return mortDao.queryMapListByKey("Mortgage.getMortgageandContractInfos", wheresql, valueMap);
	}
	
}

