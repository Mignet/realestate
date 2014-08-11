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
 * ҵ������
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
	private HolderRelationship holderRelationshipDao;				//Ȩ���˹�����
	@Autowired
	private Reg_relationship reg_relationshipDao;					//�Ǽǵ�Ԫ������
	
	//��Ԫ����,����ֵ����,1.���ݱ�����ֶ�,2.ʵ������Ӧ�ֶ�,3.�������֡�1:�����ַ�������,2:����ʱ������,3:�������ڼ�ʱ��,4:���������͡�
	static final String[][] mortOptions = {{"tab.reg_code","regcode","1"},
        {"tab.mortgagee","mortgagee","1"},
        {"tab.mortgager","mortgager","1"},
        {"tab.cer_no","cerno","1"},
        {"tab.reg_date","regdate","2"},
        {"tab.recorder","recorder","1"}
       };
			
    /**
     * 
     * ��ȡ��Ѻ�Ǽ���Ϣ.
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
     * �����Ѻ�Ǽ���Ϣ.
     * @see com.szhome.cq.business.IMortgageFacade#saveMortMess(com.szhome.cq.domain.model.Mortgage)
     */
	@Override
	@Transactional
	public void saveMortMess(Mortgage mort) {
		Map map = new HashMap();
		map.put("id",mort.getBus_id());
		Mortgage mo = mortDao.get(" where bus_id=:id",map);
		
		//�������  ��copy����ʱ���id������û 
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
			throw new BusinessException("��Ѻ��Ϣ�������:"+e.getMessage());
		}
	
		
	}
	/**
	 * 
	 * ����������ѯע����Ѻ�Ǽ���Ϣ.
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
	 * ��ȡע����Ѻѡ�еĵǼǵ�Ԫ��Ϣ.
	 * @see com.szhome.cq.business.IMortgageFacade#getRegunitMess(java.lang.String)
	 */
	@Override
	public Map getRegunitMess(String key,Map m) {
		
		Map regunit = mortDao.queryMapByKey(key, m);
		return regunit;
	}
	/**
	 * 
	 * ��ȡ�Ǽǵ�Ԫ����.
	 * @see com.szhome.cq.business.IMortgageFacade#getRegunitList(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> getRegunitList(Map m) {
		//����ҵ��id�ӵǼǵ�Ԫ�������л�ȡ�Ǽǵ�Ԫ��ţ��Ǽǵ�Ԫ����
		List<Map<String, Object>> regunitlist = mortDao.queryMapListByKey("Mortgage.getRegunitList", m);
		List<Map<String, Object>> regesate = new ArrayList<Map<String, Object>>();
		Map resultList = null;			
		//��������
		Map macon = new HashMap();
		for(Map map:regunitlist){
			String type = map.get("REG_UNIT_TYPE").toString();
			String code = map.get("REG_UNIT_CODE").toString();
			macon.put("code",code);
			//�жϵ�ǰ�Ǽǵ�Ԫ����Ϊ����
			if(type.trim().equals(WbfConstants.REG_UNIT_HOUSE)){
				
				resultList = getRegunitMess("Mortgage.getHouseInfo", macon); 
				resultList.put("TYPE", type);
				resultList.put("CODE", code);
			}
			//�жϵ�ǰ�Ǽǵ�Ԫ����Ϊ������
			if(type.trim().equals(WbfConstants.REG_UNIT_BUILDING)){
				resultList = getRegunitMess("Mortgage.getBuildInfo", macon);
				resultList.put("TYPE", type);
				resultList.put("CODE", code);
			}
			//�жϵ�ǰ�Ǽǵ�Ԫ����Ϊ����
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
	 * ��������ʵ��id��ȡ�Ǽ���Ϣ.
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
	 * ��Ѻ�����Ǽ�ҵ���У���ȡȨ������Ϣ����.
	 * @see com.szhome.cq.business.IMortgageFacade#getMortgager(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> getMortgager(Map m){
		//��������˱����Ƿ��Ѵ��ڵ�Ѻ����Ϣ
		List  <Map<String, Object>> check = appDao.queryMapListByKey("Mortgage.getAppInfo", m);
		if(check.isEmpty()){
		//���Ƚ�Ȩ������Ϣ���浽�����˱���
		saveHolderToApp(m);	
		}
		//�ٴ�������л�ȡ�뵱ǰҵ���������������Ϣ
		List  <Map<String, Object>>  applist = appDao.queryMapListByKey("Mortgage.getAppInfo", m);
		
		return applist;
		
	}

	/**
	 * 
	 * getHolderList:��ȡ�벻ͬ�ǼǱ���������Ȩ���˼�����Ϣ. <br/>
	 * @author PANDA
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getHolderList(Map m){
		
		//��ȡ�Ǽǵ�Ԫ�������У��뵱ǰҵ����صĵǼǵ�Ԫ����
		List<Map<String, Object>> regunitlist = mortDao.queryMapListByKey("Mortgage.getRegunitList", m);
		List<Map<String, Object>> regesate = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> resultList = null;	
		Map macon = new HashMap();
		for(Map map:regunitlist){
			String code = map.get("REG_UNIT_CODE").toString();
			macon.put("code",code);
			//���ݵǼǵ�Ԫ���룬��ȡȨ���˼���
			resultList = FacadeFactory.getRegisterFacade().getEffictiveHolderMapListByRegUnitCode(code,"");
			//����õ�Ȩ���˼�����Ϣ�����ڼ�����
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
	  * saveHolder:����Ȩ���˵������˱�. <br/>
	  * @author PANDA
	  * @param holder
	  * @param tempObj
	  * @param id
	  * @since JDK 1.6
	  */
	public void saveHolder(Map holder,Object tempObj,String id ){
		Applicant app = new Applicant();
		//����ҵ������id
		app.setApplicant_id(appDao.getSeqId());
		//����ҵ��id
		app.setBus_id(id);
		//��������������
		tempObj = holder.get("HOL_NAME");
		if(tempObj != null){
		app.setApp_name(tempObj.toString());
		}
		//��������������
		tempObj = holder.get("HOL_TYPE");
		if(tempObj != null){
		app.setApp_type(tempObj.toString());
		}
		//����ݶ���Ϣ
		tempObj = holder.get("PORTION");
		if(tempObj != null){
		app.setApp_port(tempObj.toString());
		}
		
		//����������֤������
		tempObj = holder.get("HOL_CER_TYPE");
		if(tempObj != null){
		app.setApp_cer_type(tempObj.toString());
		}

		//����������֤�����
		tempObj = holder.get("HOL_CER_NO");
		if(tempObj != null){
		app.setApp_cer_no(tempObj.toString());
		}
		
		//���������˵�ַ
		tempObj = holder.get("HOL_ADDRESS");
		if(tempObj != null){
		app.setApp_address(tempObj.toString());
		}

		//������������ϵ�绰
		app.setApp_tel("");
		//���浥λ����
		tempObj = holder.get("DEPART_TYPE");
		if(tempObj != null){
		app.setDepart_type(tempObj.toString());
		}
		//���淨��
		tempObj = holder.get("LEGAL_NAME");
		if(tempObj != null){
		app.setLegal_name(tempObj.toString());
		}
		//�������������
		tempObj = holder.get("AGENT_NAME");
		if(tempObj != null){
		app.setAgent_name(tempObj.toString());
		}
		
		//�����������ϵ�绰
		tempObj = holder.get("AGENT_TEL");
		if(tempObj != null){
		app.setAgent_tel(tempObj.toString());
		}
		//���������֤������
		tempObj = holder.get("AGENT_CER");
		if(tempObj != null){
		app.setAgent_cer(tempObj.toString());
		}
			
		//���������֤������
		tempObj = holder.get("AGENT_CER_TYPE");
		if(tempObj != null){
		app.setAgent_cer_type(tempObj.toString());
		}
		//����Ǽǵ�Ԫ���
		tempObj = holder.get("REG_UNIT_CODE");
		if(tempObj != null){
		app.setReg_unit_code(tempObj.toString());
		}
		//��ʶȨ����Ϊ��Ѻ��
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
	 * saveHolderToApp:����Ȩ������Ϣ���������. <br/>
	 * @author PANDA
	 * @since JDK 1.6
	 */
	@Transactional
	public void  saveHolderToApp(Map m){
		
		//���û�ȡȨ���˼��ϵķ�������ȡ�뵱ǰ�Ǽǵ�Ԫ������Ȩ���˼���
		List<Map<String, Object>> holderList = getHolderList(m);
		
		Object tempObj = null;
		//ѭ������Ȩ���˼��ϣ��������浽�����˱���
		for(Map holder:holderList){
			holder.put("HOL_REL", m.get("type").toString());
			saveHolder(holder,tempObj,m.get("id").toString());
		}
	}
      
     /**
      * 
      * ��ȡ��ѺȨ����Ϣ.
      * @see com.szhome.cq.business.IMortgageFacade#getMortgagee(java.util.Map)
      */
	@Override
	public List<Map<String, Object>> getMortgagee(Map m) {
		
		List  <Map<String, Object>>  applist = appDao.queryMapListByKey("Mortgage.getAppInfo", m);
		return applist;
	}
	
	/**
	 * 
	 * getMortgageeTransferor:(��ȡ��ѺȨ��   ������̱��в�����   ��ӵǼǲ��л�ȡ). 
	 *
	 * @author Joyon
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getMortgageeTransferor(Map m){
		//��������˱����Ƿ��Ѵ��ڵ�Ѻ����Ϣ
				String bus_id = m.get("id").toString();
				List  <Map<String, Object>> check = appDao.queryMapListByKey("Mortgage.getAppInfo", m);
				if(check.isEmpty()){
					//��ȡԭ��Ѻ�ǼǱ�� 
					String orig_mort_reg_code = getOrigMortRegCode(bus_id);
					if(orig_mort_reg_code == null || orig_mort_reg_code.equals("")){
						LogUtil.error("MortgageFacade.saveBkMortgagerToBusMortgager ԭ��Ѻ�ǼǱ��Ϊ�գ�");
						return null;
					}
					m.put("orig_mort_reg_code", orig_mort_reg_code);
					
					//ͨ��ԭ��ѺȨ�ǼǱ��   ��ȡԭ��Ѻ�Ǽ��� ��ѺȨ��  �����浽��������  Ȩ���˹�ϵΪ��ѺȨ��ת�÷�        
					String wheresql = "where reg_code=:orig_mort_reg_code";
					
					//ծȨ�˶���Ǽǵ�Ԫ��ͬ  ����ֻ��Ҫ ȡһ�׾Ϳ���
					List<Map<String, Object>> holderList = null;
					List<HolderRelationship> holderRelationshipList = holderRelationshipDao.getAll(wheresql, m);
					if(holderRelationshipList.size()>0){
						String right_rel_id = holderRelationshipList.get(0).getRight_rel_id();
						m.put("right_rel_id", right_rel_id);
					}else{
						LogUtil.error("MortgageFacade.getMortgageeTransferor ����ԭ��Ѻ��� Ȩ���˹��������");
						return null;
					}
					try {
						wheresql = "where RIGHT_REL_ID =:right_rel_id and hol_rel='"+WbfConstants.MORTGAGEE+"'";
						holderList = appDao.queryMapListByKey(
								"Mortgage.getHolder", wheresql, m);
					} catch (Exception e) {
						LogUtil.error("MortgageFacade.getMortgageeTransferor��ȡ��Ѻת�÷�����:"+e.getMessage());
					}
					
					
					
					Object tempObj = null;
					//ѭ������Ȩ���˼��ϣ��������浽�����˱���
					for(Map holder:holderList){
						holder.put("HOL_REL", m.get("type").toString());
						saveHolder(holder,tempObj,m.get("id").toString());
					}
					
					//----����һ�ε�Ѻ��Ҳ���浽��������  ��������Ǽǵ�Ԫcode  ����Ϊ��Ѻ��
					//wheresql = "where RIGHT_REL_ID = (select RIGHT_REL_ID from BK_RIGHT_REL where reg_code=:orig_mort_reg_code) and hol_rel ='"+WbfConstants.MORTGAGER+"'";
					wheresql =" where r.reg_code=:orig_mort_reg_code and h.right_rel_id=r.right_rel_id and b.book_code=r.book_code and h.hol_rel='"+WbfConstants.MORTGAGER+"'";
					holderList = appDao.queryMapListByKey("Mortgage.getMortgagerFromBk",wheresql, m);
					for(Map holder:holderList){
						saveHolder(holder,tempObj,m.get("id").toString());
					}
				}
				//�ٴ�������л�ȡ�뵱ǰҵ���������������Ϣ
				List  <Map<String, Object>>  applist = appDao.queryMapListByKey("Mortgage.getAppInfo", m);
				
				
				
				return applist;
	}
	
	/**
	 * 
	 * saveBkMortgagerToBusMortgager:(��ǰ��Ѻ����µĵ�Ѻ�˱��浽�����˱���,�������������˵�Ѻ��  ���ٱ���  ����ֱ�ӷ���).
	 *
	 * @author Joyon
	 * @return �����˱��е�Ѻ��list����
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> saveBkMortgagerToBusMortgager(String bus_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id", bus_id);
		List<Map<String,Object>> dbAppMortgagerList = appDao.queryMapListByKey("Mortgage.getApplicant","where bus_id=:bus_id and hol_rel='"+WbfConstants.MORTGAGER+"'" ,paraMap);
		if(dbAppMortgagerList.isEmpty() || dbAppMortgagerList == null){
			//��ȡԭ��Ѻ�ǼǱ�� 
			String orig_mort_reg_code = getOrigMortRegCode(paraMap.get("bus_id").toString());
			if(orig_mort_reg_code == null || orig_mort_reg_code.equals("")){
				LogUtil.error("MortgageFacade.saveBkMortgagerToBusMortgager ԭ��Ѻ�ǼǱ��Ϊ�գ�");
				return null;
			}
			paraMap.put("orig_mort_reg_code", orig_mort_reg_code);
			//----����һ�ε�Ѻ��Ҳ���浽��������  ��������Ǽǵ�Ԫcode  ����Ϊ��Ѻ��
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
	 * getOrigMortRegCode:(��ȡԭ��Ѻ�ǼǱ��). 
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
			LogUtil.error("MortgageFacade.getOrigMortRegCode  �Ǽǵ�Ԫ������Ϊ��   ");
		}
		return origMortRegCode;
	}
	/**
	 * 
	 * saveBkMortgageeToBusMortgagee:(����ǰ��Ѻ����µĵ�ѺȨ�˵������˱���   ������������е�ѺȨ��  ���ٱ���).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return �������е�ѺȨ��list����
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> saveBkMortgageeToBusMortgagee(String bus_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id", bus_id);
		List<Map<String,Object>> dbAppMortgageeList = appDao.queryMapListByKey("Mortgage.getApplicant","where bus_id=:bus_id and hol_rel='"+WbfConstants.MORTGAGEE+"'" ,paraMap);
		if(dbAppMortgageeList.isEmpty() || dbAppMortgageeList == null){
			//��ȡԭ��Ѻ�ǼǱ�� 
			String orig_mort_reg_code = getOrigMortRegCode(bus_id);
			if(orig_mort_reg_code == null || orig_mort_reg_code.equals("")){
				LogUtil.error("MortgageFacade.saveBkMortgagerToBusMortgager ԭ��Ѻ�ǼǱ��Ϊ�գ�");
				return null;
			}
			paraMap.put("orig_mort_reg_code", orig_mort_reg_code);
			//----����һ�ε�Ѻ��Ҳ���浽��������  ��������Ǽǵ�Ԫcode  ����Ϊ��Ѻ��
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
	 * getMortCancelMortgager:(��ȡ��Ѻע�� ��Ѻ��). 
	 *
	 * @author Joyon
	 * @param bus_id
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getMortCancelMortgager(String bus_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id", bus_id);
		//��ȡ��Ѻ��
		List<Map<String,Object>> dbAppMortgagerList = appDao.queryMapListByKey("Mortgage.getApplicant","where bus_id=:bus_id and hol_rel='"+WbfConstants.MORTGAGER+"'" ,paraMap);
		//�����Ѻ��Ϊ��  �����ΰ�ҵ��Ǽǵĵ�Ѻ��ȡ�����浽�������    Ȼ���ٳ���
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
	 * saveBkMortgageeToBusMortgageeByOrigregcodeAndRegunitcode:(���ݵǼǵ�Ԫ��ź�ǰ��Ѻ���  �����Ѻ�˵��������).
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
	 * ����������ȡ���ڵ�Ѻ��¼�ĵ�Ѻ����Ϣ.
	 * @see com.szhome.cq.business.IMortgageFacade#getMortgagerSeted(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> getMortgagerSeted(Map m) {
		List<Map<String, Object>> resultList = appDao.queryMapListByKey("Mortgage.getAppInfo", m);
		if(resultList.isEmpty()){
		//��ȡ�Ǽǵ�Ԫ�������У��뵱ǰҵ����صĵǼǵ�Ԫ����
		List<Map<String, Object>> regunitlist = mortDao.queryMapListByKey("Mortgage.getbkMortInfo", m);
		List<Map<String, Object>> regesate = new ArrayList<Map<String, Object>>();
			
		//ѭ��ȡ����Ѻ����Ϣ
		for(Map mort:regunitlist){
			m.put("book_code",mort.get("BOOK_CODE").toString());
			m.put("code",mort.get("REG_CODE").toString());
			//���ݵǼǱ�š��Ǽǲ�code��Ȩ���˹�ϵ��ȡ��Ѻ����Ϣ
			regesate = mortDao.queryMapListByKey("Mortgage.getMortgagerForSet", m);
			Object tempObj = null;
			//ѭ�������Ѻ����Ϣ�������˱���
			if(!regesate.isEmpty()){
			
			for(Map holder:regesate){
				//���Ǽǵ�Ԫ��Ź�����Ѻ����Ϣ
				holder.put("REG_UNIT_CODE",mort.get("REG_UNIT_CODE").toString());
				//����Ȩ���˵������˱���
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
	 * saveBkMortageToBusMortgage:(��ǰ��Ѻ��ŵǼǲ��е�Ѻ��Ϣ���浽������Ϣ����   ���������Ϣ���м������򲻱���  ֱ�ӷ���).
	 *
	 * @author Joyon
	 * @param bus_id
	 * @return Mortgage
	 * @since JDK 1.6
	 */
	public Mortgage saveBkMortageToBusMortgage(String bus_id){
		String date_regexp = "^((d{4})|d{2})[-\\s]{1}[01]{1}d{1}[-\\s]{1}[0-3]{1}d{1}$";// ƥ������    
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id", bus_id);
		Mortgage dbMortgage = mortDao.get("where bus_id=:bus_id", paraMap);
		if(dbMortgage == null){
			String origMortRegCode =  getOrigMortRegCode(bus_id);				//ԭ��Ѻ�ǼǱ��
			paraMap.put("origMortRegCode", origMortRegCode);
			List<Reg_Mortgage> reg_MortgageList = reg_MortgageDao.getAll("where reg_code=:origMortRegCode", paraMap);
			if(reg_MortgageList.size()>0){
				dbMortgage = new Mortgage();
				Reg_Mortgage dbRegMortgage = reg_MortgageList.get(0);
				mortDao.copyProperties(dbMortgage, dbRegMortgage);
				
				//���ַ�������ƥ���������������
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
	 * ����������ȡ���ڵ�Ѻ��¼�ĵ�ѺȨ����Ϣ.
	 * @see com.szhome.cq.business.IMortgageFacade#getMortgageeSeted(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> getMortgageeSeted(Map m) {
		List<Map<String, Object>> resultList = appDao.queryMapListByKey("Mortgage.getAppInfo", m);
		if(resultList.isEmpty()){
			//��ȡ�Ǽǵ�Ԫ�������У��뵱ǰҵ����صĵǼǵ�Ԫ����
			List<Map<String, Object>> regunitlist = mortDao.queryMapListByKey("Mortgage.getbkMortInfo", m);
			List<Map<String, Object>> regesate = new ArrayList<Map<String, Object>>();
			
				m.put("book_code",regunitlist.get(0).get("BOOK_CODE").toString());
				m.put("code",regunitlist.get(0).get("REG_CODE").toString());
				//���ݵǼǱ�š��Ǽǲ�code��Ȩ���˹�ϵ��ȡ��Ѻ����Ϣ
				regesate = mortDao.queryMapListByKey("Mortgage.getMortgagerForSet", m);
				Object tempObj = null;
				//ѭ�������Ѻ����Ϣ�������˱���
				if(!regesate.isEmpty()){
				
				for(Map holder:regesate){
					//���Ǽǵ�Ԫ��Ź�����Ѻ����Ϣ
					holder.put("REG_UNIT_CODE",regunitlist.get(0).get("REG_UNIT_CODE").toString());
					//����Ȩ���˵������˱���
					saveHolder(holder,tempObj,m.get("id").toString());
				}
				
				
			}
			
			
		}
		 resultList = appDao.queryMapListByKey("Mortgage.getAppInfo", m);
		return resultList;
		
	}
	/**
	 * 
	 * ���淢����Ϣ.
	 * @see com.szhome.cq.business.IMortgageFacade#saveDispathcInfo(com.szhome.cq.domain.model.Certificate)
	 */
	@Transactional
	public void saveDispathcInfo(Certificate cer,List<Map> list,String id) {
		
		    String code = null;
		    String type = null;
		    //���巿�ز�֤��
		    String cerno = null;
		    //����ҵ������id
		    cer.setBus_id(id);
		    Map m = new HashMap();
		    //ѭ��ȡ��list�е�ֵ
			for(int i=0;i<list.size();i++){
				code = list.get(i).get("CODE").toString();
				type = list.get(i).get("TYPE").toString();
				//���ò�ѯ����
				m.put("code", code);
				m.put("type",type);
				m.put("effective",WbfConstants.EFFECTIVE);
				//�Ǽǵ�Ԫ����Ϊ����
				if(type.equals(WbfConstants.REG_UNIT_HOUSE)){
				 	
					cerno = FacadeFactory.getRegisterFacade().getEffectiveReg_OwnershipByRegUnitCode(code).getCer_no();
					
				}
				//�Ǽǵ�ԪΪ¥��
               if(type.equals(WbfConstants.REG_UNIT_BUILDING)){
				 	
            	    cerno = FacadeFactory.getRegisterFacade().getEffectiveReg_OwnershipByRegUnitCode(code).getCer_no();
					
				}
             //�Ǽǵ�ԪΪ����
                if(type.equals(WbfConstants.REG_UNIT_PARCEL)){
				 	
                	cerno =  FacadeFactory.getRegisterFacade().getEffectiveReg_userightByRegUnitCode(code).getCer_no();
                	
					
				}
              //���뷿�ز�֤��
             cer.setCertificate_code(cerno);
             //������Ϣ�����ز�֤����
             saveToCer(cer);
             
			}  
		
	}
	
	 /**
	  * 
	  * saveToCer:������Ϣ�����ز�֤����. <br/>
	  * @author PANDA
	  * @param cer
	  * @since JDK 1.6
	  */
     public void saveToCer(Certificate cer) {
		
		Map map = new HashMap();
		map.put("id",cer.getBus_id());
		map.put("code",cer.getCertificate_code());
		//����ҵ��id�ͷ��ز�֤�ţ��ж��Ƿ��ѱ���
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
			throw new BusinessException("������Ϣ�������:"+e.getMessage());
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

