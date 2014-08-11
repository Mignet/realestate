package com.szhome.cq.business.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.szhome.cq.business.IBookFacade;
import com.szhome.cq.domain.model.Bk_Certificate;
import com.szhome.cq.domain.model.Holder;
import com.szhome.cq.domain.model.Reg_Advnotice;
import com.szhome.cq.domain.model.Reg_Demurrer;
import com.szhome.cq.domain.model.Reg_Distrain;
import com.szhome.cq.domain.model.Reg_Easement;
import com.szhome.cq.domain.model.Reg_Mortgage;
import com.szhome.cq.domain.model.Reg_Useright;
import com.szhome.cq.domain.model.Reg_ownership;
import com.szhome.cq.utils.WbfConstants;
import com.szhome.cq.utils.WebUtil;
import com.springjdbc.annotation.BaseDomain;

/**
 * ClassName:BookFacade <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-03-20 ÏÂÎç5:15:39 <br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class BookFacade implements IBookFacade {

	@Autowired
	Reg_ownership ownershipDao;
	@Autowired
	Reg_Useright  userightDao;
	@Autowired
	Reg_Mortgage  mortDao;
	@Autowired
	Reg_Distrain  distDao;
	@Autowired
	Reg_Demurrer  dmrDao;
	@Autowired
	Holder        holderDao;
	@Autowired
	Reg_Advnotice advDao;
	@Autowired
	Bk_Certificate cerdao;
	@Autowired
	Reg_Easement easeDao;
	
	@Override
	public Map<String, Object> getBookNatualInfo(Map<String, Object> param)
			throws Exception {
		Map<String,Object> returnMap = null;
		String reg_unit_type = null;
		if(param != null){
			reg_unit_type = (String)param.get("reg_unit_type");
			if(reg_unit_type.equals(WbfConstants.HOUSE)){
				returnMap = ownershipDao.queryMapByKey("Book.getBookHouseBaseInfo", param);
			}else if(reg_unit_type.equals(WbfConstants.PARCEL)){
				returnMap = userightDao.queryMapByKey("Book.getBookParcelBaseInfo", param);
			}
			if(returnMap!=null){
				returnMap.put("CER_NO", WebUtil.getCerCode(cerdao,returnMap));
			}
			
			
		}
		return returnMap;
	}


	@Override
	public List<Map<String, Object>> getBookUserInfoLst(
			Map<String, Object> param) throws Exception {
        List<Map<String,Object>> returnLst = null; 
        if(param != null){
        	returnLst = userightDao.queryMapListByKey("Book.getBookUserRightInfoLst", param);
		}
		return returnLst;
	}

	@Override
	public List<Map<String, Object>> getBookHolderLst(Map<String, Object> param)
			throws Exception {
		List<Map<String,Object>> returnLst = null; 
		String reg_unit_type = null;
        if(param != null){
        	reg_unit_type = (String)param.get("reg_unit_type");
        	if(reg_unit_type.equals(WbfConstants.PARCEL))
        	  returnLst = holderDao.queryMapListByKey("Book.getBookHolderUserightLst", param);
        	else if(reg_unit_type.equals(WbfConstants.HOUSE))
        	  returnLst = holderDao.queryMapListByKey("Book.getBookHolderOwnershipLst", param);
		}
        if(returnLst!=null){
        	for(int i=0;i<returnLst.size();i++){
        	   Map<String,Object> tempMap = returnLst.get(i);
        	   tempMap.put("CER_NO", WebUtil.getCerCode(cerdao,tempMap));
        	}
		}
		return returnLst;
	}

	@Override
	public List<Map<String, Object>> getBookMortInfoLst(
			Map<String, Object> param) throws Exception {
		List<Map<String,Object>> returnLst = null; 
		String reg_unit_type = null;
        if(param != null){
        	reg_unit_type = (String)param.get("reg_unit_type");
        	if(reg_unit_type.equals(WbfConstants.PARCEL))
        	    returnLst = mortDao.queryMapListByKey("Book.getBookMortInfoLst", param);
        	else if(reg_unit_type.equals(WbfConstants.HOUSE))
        		returnLst = mortDao.queryMapListByKey("Book.getBookHouseMortInfoLst", param);	
		}
		return returnLst;
	}

	@Override
	public List<Map<String, Object>> getBookEasementInfoLst(
			Map<String, Object> param) throws Exception {
		List<Map<String,Object>> returnLst = null; 
		String reg_unit_type = null;
        if(param != null){
        	reg_unit_type = (String)param.get("reg_unit_type");
        	if(reg_unit_type.equals(WbfConstants.PARCEL))
        	    returnLst = easeDao.queryMapListByKey("Book.getBookEasementInfoLst", param);
        	else if(reg_unit_type.equals(WbfConstants.HOUSE))
        		returnLst = easeDao.queryMapListByKey("Book.getBookHouseEasementInfoLst", param);	
		}
		return returnLst;
	}
	@Override
	public List<Map<String, Object>> getBookAttachInfoLst(
			Map<String, Object> param) throws Exception {
		List<Map<String,Object>> returnLst = null; 
		String reg_unit_type = null;
        if(param != null){
        	reg_unit_type = (String)param.get("reg_unit_type");
        	if(reg_unit_type.equals(WbfConstants.PARCEL))
        	    returnLst = distDao.queryMapListByKey("Book.getBookAttachInfoLst", param);
        	else if(reg_unit_type.equals(WbfConstants.HOUSE))
        		returnLst = distDao.queryMapListByKey("Book.getBookHouseAttachInfoLst", param);	
		}
		return returnLst;
	}

	@Override
	public List<Map<String, Object>> getBookDissentInfoLst(
			Map<String, Object> param) throws Exception {
		List<Map<String,Object>> returnLst = null; 
		String reg_unit_type = null;
        if(param != null){
        	reg_unit_type = (String)param.get("reg_unit_type");
        	if(reg_unit_type.equals(WbfConstants.PARCEL))
        	    returnLst = dmrDao.queryMapListByKey("Book.getBookDissentInfoLst", param);
        	else if(reg_unit_type.equals(WbfConstants.HOUSE))
        		returnLst = dmrDao.queryMapListByKey("Book.getBookHouseDissentInfoLst", param);	
		}
		return returnLst;
	}

	@Override
	public List<Map<String, Object>> getBookOwnershipInfoLst(
			Map<String, Object> param) throws Exception {
		  List<Map<String,Object>> returnLst = null; 
	        if(param != null){
	        	returnLst = ownershipDao.queryMapListByKey("Book.getBookOwnershipInfoLst", param);
			}
		  return returnLst;
	}

	@Override
	public List<Map<String, Object>> getBookHouseAdvInfoLst(
			Map<String, Object> param) throws Exception {
		List<Map<String,Object>> returnLst = null; 
        if(param != null){
        	returnLst = advDao.queryMapListByKey("Book.getBookHouseAdvInfoLst", param);
		}
	    return returnLst;
	}


	@Override
	public List<Map<String, Object>> getUserightHolderLst(
			Map<String, Object> sqlMap,Map<String,Object> paramMap) throws Exception {
		List<Map<String,Object>> returnLst = null; 
		String wheresql = "where 1=1 " + sqlMap.get("userightId").toString();
        if(paramMap != null){
        	returnLst = userightDao.queryMapListByKey("Userright.getHolderUserightLst",wheresql, paramMap);
		}
	    return returnLst;
	}
	
	
	
	@Override
	public List<Map<String, Object>> getOwnershipHolderLst(
			Map<String, Object> sqlMap,Map<String,Object> paramMap) throws Exception {
		List<Map<String,Object>> returnLst = null; 
		String wheresql = "where 1=1 " + sqlMap.get("ownershipId").toString();
		if(paramMap != null){
			returnLst = ownershipDao.queryMapListByKey("Ownership.getHolderOwnershipLst",wheresql, paramMap);
		}
		return returnLst;
	}
	@Override
	public List<Map<String, Object>> getUserightInfoLst(
			Map<String, Object> sqlMap,Map<String,Object> paramMap) throws Exception {
		List<Map<String,Object>> returnLst = null; 
		String wheresql = "where 1=1 " + sqlMap.get("bookcode").toString();
        if(paramMap != null){
        	returnLst = userightDao.queryMapListByKey("Book.getUserightinfo",wheresql, paramMap);
		}
	    return returnLst;
	}
	@Override
	public List<Map<String, Object>> getOwnershipInfoLst(
			Map<String, Object> sqlMap,Map<String,Object> paramMap) throws Exception {
		List<Map<String,Object>> returnLst = null; 
		String wheresql = "where 1=1 " + sqlMap.get("bookcode").toString();
		if(paramMap != null){
			returnLst = ownershipDao.queryMapListByKey("Book.getOwnershipinfo",wheresql, paramMap);
		}
		return returnLst;
	}


	

}

