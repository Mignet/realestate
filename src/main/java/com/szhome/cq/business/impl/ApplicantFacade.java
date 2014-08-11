package com.szhome.cq.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.IApplicantFacade;
import com.szhome.cq.business.vo.OwnerInfoVo;
import com.szhome.cq.domain.model.Applicant;
import com.szhome.cq.domain.model.BusDistrain;
import com.szhome.cq.domain.model.BusOwnership;
import com.szhome.cq.domain.model.BusRemarkInfo;
import com.szhome.cq.domain.model.BusRevokeapproval;
import com.szhome.cq.domain.model.Busdemurrer;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.Bususeright;
import com.szhome.cq.domain.model.Certificate;
import com.szhome.cq.domain.model.House;
import com.szhome.cq.domain.model.Reg_relationship;
import com.szhome.cq.utils.WbfConstants;

/**
 * 申请人facade
 * 
 * @author Mignet
 */
@Component
@Transactional
@Scope("prototype")
public  class ApplicantFacade implements IApplicantFacade {

	@Autowired
	private Applicant appDao;
	@Autowired
	private BusinessMain bussMainDao;
	@Autowired
	private Certificate certificateDao;
	@Autowired
	private House houseDao;
	@Autowired
	private BusRemarkInfo remarkDao;
	@Autowired
	private BusOwnership osDao;
	@Autowired
	private Busdemurrer demurrerDao;
	@Autowired
	private BusRevokeapproval revokeapprovalDao;
	@Autowired
	private Bususeright busDao;
	@Autowired
	private BusDistrain distrainDao;
	@Autowired
	private Reg_relationship relationshipDao;
	
	/**
	 * 
	 * 根据流程实例id查询申请人信息集合.
	 *
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */		
	@Override
	public List<Map<String, Object>> getApptMessByLcslbid(String id) {
		Map m = new HashMap();
		m.put("id", id);
		List<Map<String, Object>> apps = appDao.queryMapListByKey("Applicant.queryInfoById","where bus_id=:id", m);
		return apps;
		
	}

   /**
    * 
    * 通过流程实例ID获取申请人List
    *
    * @author Joyon
    * @param proc_id
    * @return
    * @throws BusinessException
    * @since JDK 1.6
    */
	public List<Applicant> getApplicantListByProcId(String proc_id) throws BusinessException{
		Map paraMap = new HashMap();
		paraMap.put("proc_id", proc_id);
		List<Applicant> apps = appDao.getAll("where bus_id=(select bus_id from bus_main where proc_id=:proc_id)", paraMap);
		return apps;
		
	}
	
	/**
	 * 
	 * 通过流程实例ID  和权利人关系获取申请人
	 *
	 * @author Joyon
	 * @param proc_id
	 * @param hol_rel
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public List<Applicant> getApplicantListByProcidAndHolrel(String proc_id,String hol_rel) throws BusinessException{
		Map paraMap = new HashMap();
		paraMap.put("proc_id", proc_id);
		paraMap.put("hol_rel", hol_rel);
		List<Applicant> apps = appDao.getAll("where HOL_REL=:hol_rel and bus_id=(select bus_id from bus_main where proc_id=:proc_id)", paraMap);
		return apps;
		
	}

	/**
	 * 
	 *  根据传入流程实例ID获取业务主表以及登记单元关联表信息.
	 * @see com.szhome.cq.business.IBussMainFacade#getRegMainById(int)
	 * @author xuzz
	 */

	@Override
	public Map getRegMainById(String id) {
		
		Map m = new HashMap();
		m.put("proc_id", id);
		//String sql = " where proc_id=:proc_id)";
		Map resultmap=new HashMap();
		List<Map<String, Object>> mapList=osDao.queryMapListByKey("Applicant.getRegInfoByProid", m);
		if(mapList.size()>0)
		{
			return mapList.get(0);
		}
		return null;
	}
	
	
	
	
	/**
	 * 
	 *  根据传入参数获取登记信息.
	 * @see com.szhome.cq.business.IBussMainFacade#getRegistMessById(int)
	 */

	@Override
	public Map getRegistMessById(String id) {
		
		Map m = new HashMap();
		m.put("proc_id", id);
		//String sql = " where proc_id=:proc_id)";
		Map resultmap=new HashMap();
		List<Map<String, Object>> map=osDao.queryMapListByKey("Applicant.getRegInfoByProid", m);
		if(map.size()>0)
		{
			m.clear();
			m.put("id", map.get(0).get("bus_id").toString());
			List<Map<String, Object>> mapcer=osDao.queryMapListByKey("Applicant.getDisByid", m);
			if(mapcer.size()>0)
			{
				if(mapcer.get(0).get("EXCURSUS")!=null)
				{
					resultmap.put("excursus", mapcer.get(0).get("EXCURSUS").toString());
				}
				else
				{
					resultmap.put("excursus", "");
				}
			}
			else
			{
				resultmap.put("excursus", "");
			}
			resultmap.put("RegInfo", map.get(0));
		m.put("id", id);
		String sql = " where proc_id=:id)";
		//OwnerInfoVo o = osDao.queryObjectByKey("Ownership.queryInfoById",sql, m, OwnerInfoVo.class);
		List<Map<String, Object>> listOwnerInfo = osDao.queryMapListByKey("Ownership.queryInfoById",sql, m);
		if(listOwnerInfo.size()<=0){
			
			//LogUtil.error("登记信息所有权部分信息为空！");
		//	throw new BusinessException("保存登记信息出错:"+new Exception().getMessage());
			System.out.println("-------");
			
		}
		}
		return resultmap;
	}
    /**
     * 
     *  保存登记信息（可选）.
     * @see com.szhome.cq.business.IBussMainFacade#saveRegistMess(com.szhome.cq.business.vo.OwnerInfoVo)
     */
	@Override
	@Transactional
	public void saveRegistMess(OwnerInfoVo reg) {		
		Map map = new HashMap();
		map.put("id",reg.getBus_id());
		BusinessMain bu = bussMainDao.get(" where bus_id=:id",map);
		BusinessMain b = new BusinessMain();
		try {
			if(bu == null){
//				b.setReg_code(reg.getReg_code());
//				b.setReg_station(reg.getReg_station());
//				b.setReg_type(reg.getReg_type());
//				b.setProc_name(reg.getProc_name());	
//				b.setProc_id(reg.getProc_id());
				bussMainDao.copyProperties(b, reg);
				b.setBus_id(bussMainDao.getSeqId());	
				bussMainDao.save(b);				
			}else{
				
//				bu.setReg_code(reg.getReg_code());
//				bu.setReg_station(reg.getReg_station());
//				bu.setReg_type(reg.getReg_type());
//				bu.setProc_name(reg.getProc_name());	
//				bu.setProc_id(reg.getProc_id());
				bussMainDao.copyProperties(bu, reg);
				bussMainDao.update(bu);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("保存登记信息出错:"+e.getMessage());
			
		}
	
	}
   /**
    * 
    *  根据流程实例id获取登记信息集合.
    * @see com.szhome.cq.business.IApplicantFacade#getOnwershipById(int)
    */
	
	@Override
	public List<OwnerInfoVo> getOnwershipById(String id) {
		Map m = new HashMap();
		m.put("id", id);
		String sql = "where z.lcslbid=:id";
		return osDao.queryObjectListByKey("Ownership.queryInfoById",sql, m, OwnerInfoVo.class);
	}
	
    /**
     * 
     *  保存单条申请人信息.
     * @see com.szhome.cq.business.IApplicantFacade#saveApplicant(com.szhome.cq.domain.model.Applicant)
     */
	@Override
	@Transactional
	public void saveApplicant(Applicant app) {
		app.setApplicant_id(appDao.getSeqId());
		
		try {
			appDao.save(app);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("保存申请人信息出错:"+e.getMessage());
		}
	}
	
	
   /**
    * 
    *  更新单条申请人信息.
    * @see com.szhome.cq.business.IApplicantFacade#updateApplicant(com.szhome.cq.domain.model.Applicant)
    */
   @Override
   @Transactional
   public void updateApplicant(Applicant app) {	   
	   try {
		   appDao.update(app);
		} catch (Exception e) {
		
			throw new BusinessException("更新申请人信息出错:"+e.getMessage());
			
		}
    }
   
   
   /**
    * 
    *  删除单条申请人记录.
    * @see com.szhome.cq.business.IApplicantFacade#delApplicant(int)
    */
   @Override
   @Transactional
   public void delApplicant(String id) {   
	   try {
		   appDao.delete(new Applicant(id));
		} catch (Exception e) {		
			throw new BusinessException("删除申请人信息出错:"+e.getMessage());			
		}	
   }
   /**
    * 
    *  根据主键id获取单条申请人信息.
    * @see com.szhome.cq.business.IApplicantFacade#getAppMessById(int)
    */
   @Override
   public Applicant getAppMessById(String id) {
	 
	   return appDao.get(new Applicant(id));
   }
   /**
    * 
    *  根据身份证号查询单条数据.
    * @see com.szhome.cq.business.IApplicantFacade#getAppMessByIdcard(int)
    */
   @Override
   public Applicant getAppMessByIdcard(String id) {
	   Map m = new HashMap();
		m.put("id", id);
		return appDao.queryObjectByKey("Applicant.queryAppByIdcaid", m, Applicant.class);
   }
   /**
    * 
    *  保存房地产证附记.
    * @see com.szhome.cq.business.IApplicantFacade#saveExcursus(com.szhome.cq.domain.model.Certificate)
    */

   @Override
   @Transactional
   public void saveCerRemark(OwnerInfoVo o) {
	  
	   Certificate c = new Certificate();
	   Map m=new HashMap();
		m.put("id",o.getBus_id());
	   Certificate ce = certificateDao.queryDomainBykey("Applicant.getDisByid", m);
			try {
			if(ce == null){
				 if(o.getExcursus() != null){
					   c.setExcursus(o.getExcursus());
					   c.setBus_id(o.getBus_id());
					   c.setCertificate_id(certificateDao.getSeqId());					   
				   }
				 certificateDao.save(c);
				
			}else{
				   c.setExcursus(o.getExcursus());
				   c.setBus_id(o.getBus_id());
				   c.setCertificate_id(ce.getCertificate_id());
				certificateDao.update(c);
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("保存登记信息出错:"+e.getMessage());
			
		}
	   
   }

  /**
   * 
   *  获取登记簿房屋信息.
   * @see com.szhome.cq.business.IApplicantFacade#getProname(java.lang.String)
   */
   @Override
   public House getProname(String id) {
	   String proname = "";
	   Map m = new HashMap();
		m.put("id", id);
		House h = houseDao.queryDomainBykey("Applicant.getHouseByid", m);
		if( h == null){
			
			 
			System.out.println("--------");
		}
	   return h;
   }

   /**
    * 
    *  保存所有权登记信息.
    * @see com.szhome.cq.business.IApplicantFacade#saveOwnership(com.szhome.cq.domain.model.BusOwnership)
    */
   @Override
   @Transactional
   public void saveOwnership(BusOwnership b) {
	   Map m=new HashMap();
		m.put("id",b.getBus_id());
		BusOwnership bos = osDao.get(" where bus_id=:id",m);
			try {
			if(bos == null){	
			     b.setOwner_reg_id(osDao.getSeqId());
			     osDao.save(b);
				
			}else{
			    b.setOwner_reg_id(bos.getOwner_reg_id());
			    osDao.update(b);
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("保存房屋所有权登记信息出错:"+e.getMessage());
			
		}
	   
   }
   /**
    * 保存房屋异议登记信息.
    * @param b
    */
   @Override
   @Transactional
   public void saveDemurrer(Busdemurrer b) {
	   Map m=new HashMap();
		m.put("id",b.getBus_id());
		Busdemurrer bos = demurrerDao.get(" where bus_id=:id",m);
			try {
			if(bos == null){	
			     b.setDiss_reg_id(demurrerDao.getSeqId());
			     demurrerDao.save(b);
				
			}else{
			    b.setDiss_reg_id(bos.getDiss_reg_id());
			    demurrerDao.update(b);
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("保存房屋所有权登记信息出错:"+e.getMessage());
			
		}
	   
   }
   /**
    * 保存备注登记信息.
    * @param 
    */
   @Override
   @Transactional
   public void saveRemark(BusRemarkInfo b) {
	   Map m=new HashMap();
		m.put("id",b.getBus_id());
		BusRemarkInfo remark = remarkDao.get(" where bus_id=:id",m);
			try {
			if(remark == null){	
				b.setRemark_id(remarkDao.getSeqId());
			     remarkDao.save(b);
				
			}else{
			    b.setRemark_id(remark.getRemark_id());
			    remarkDao.update(b);
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("保存备注登记信息出错:"+e.getMessage());
			
		}
	   
   }
   
   /**
    * 保存撤销核准信息.
    * @param 
    */
   @Transactional
   public void saveRevokeapproval(BusRevokeapproval b) {
	    Map m=new HashMap();
		m.put("id",b.getBus_id());
		try {
			BusRevokeapproval revokeapproval = revokeapprovalDao.get(" where bus_id=:id",m);
			if(revokeapproval == null){	
				b.setId(revokeapprovalDao.getSeqId());
				revokeapprovalDao.save(b);
				
			}else{
			    b.setId(revokeapproval.getId());
			    revokeapprovalDao.update(b);
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("保存撤销核准信息出错:"+e.getMessage());
			
		}
	   
   }
   
   /**
    * 
    *  获取备注登记信息.
    * 
    */
   @Override
   public BusRemarkInfo getRemark(String id) {
	   
	   Map m = new HashMap();
		m.put("id", id);
		BusRemarkInfo remark = remarkDao.queryDomainBykey("Applicant.getRemarkByid", m);
		if(remark == null){
		//	throw new BusinessException("保存登记信息出错:"+new Exception().getMessage());
			System.out.println();			
		}
	   return remark;
   }
   /**
    * 
    *  获取备注登记信息,用于注销.
    * 
    */
   @Override
   public BusRemarkInfo getUnRemark(String reg_code) {
	   
	   Map m = new HashMap();
	   //String sql=" and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";
	   m.put("reg_code", reg_code);
		List<Map<String,Object>> ListMapRemark = remarkDao.queryMapListByKey("Applicant.getRemarkByRegCode","", m);
		if(!(ListMapRemark.size()>0))
		{
			return null;
		}
		Map Mapdemurrer=ListMapRemark.get(0);
		BusRemarkInfo remark=new BusRemarkInfo();
		remark.copyProperties(remark, Mapdemurrer);
		if(remark == null)
		{
		//	throw new BusinessException("保存登记信息出错:"+new Exception().getMessage());
			System.out.println();			
		}
	   return remark;
   }
   
   /**
    * 
    *  获取房屋异议登记信息.
    * 
    */
   @Override
   public Busdemurrer getDemurrer(String id) {
	   
	   Map m = new HashMap();
		m.put("id", id);
		Busdemurrer demurrer = demurrerDao.queryDomainBykey("Applicant.getdemurrerByid", m);
		if(demurrer == null){
		//	throw new BusinessException("保存登记信息出错:"+new Exception().getMessage());
			System.out.println();			
		}
	   return demurrer;
   }
   
   /**
    * 
    *  获取撤销核准信息.
    * 
    */
   @Override
   public BusRevokeapproval getRevokeapproval(String id) {
	   
	   Map m = new HashMap();
		m.put("id", id);
		BusRevokeapproval revokeapproval = revokeapprovalDao.queryDomainBykey("Applicant.getRevokeapprovalByid", m);
		if(revokeapproval == null){
		//	throw new BusinessException("保存登记信息出错:"+new Exception().getMessage());
			System.out.println();			
		}
	   return revokeapproval;
   }
   
   /**
    * 
    *  获取异议事项.
    * 
    */
   @Override
   public Busdemurrer getDisItem(String reg_code) {
	   
	   Map m = new HashMap();
	   String sql=" and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";
		m.put("reg_code", reg_code);
		List<Map<String,Object>> ListMapdemurrer = demurrerDao.queryMapListByKey("Applicant.getDisItemByid",sql, m);
		if(!(ListMapdemurrer.size()>0))
		{
			return null;
		}
		Map Mapdemurrer=ListMapdemurrer.get(0);
		Busdemurrer demurrer=new Busdemurrer();
		demurrer.setDiss_item(Mapdemurrer.get("DISS_ITEM").toString());
		if(demurrer == null){
		//	throw new BusinessException("保存登记信息出错:"+new Exception().getMessage());
			System.out.println();			
		}
	   return demurrer;
   }
   
   
   /**
    * 
    *  获取查封信息，用于解封登记,解除轮候查封.
    * 
    */
   @Override
   public List<Map<String,Object>> getAttachByRegcode(String reg_code,String attachType) 
   {
	   String key="";
	   String sql="";
	   Map m = new HashMap();
	   Map mapdis=new HashMap();
	   m.put("reg_code", reg_code);
	   //解封
	   if(attachType.equals(WbfConstants.T_UNATTACH))
	   {
		   key="Applicant.getDistrainByid";
		   sql=" and DIS_TYPE='"+WbfConstants.T_ATTACH+"' and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";
	   }
	   //解除轮候查封
	   else if(attachType.equals(WbfConstants.T_UNREATTACH))
	   {
		   key="Applicant.getReAttachByRegUnitCode";
		   sql=" and DIS_TYPE='"+WbfConstants.T_LASTATTACH+"' and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";
	   }
	   //续封
	   else if(attachType.equals(WbfConstants.T_REATTACH))
	   {
		   key="Applicant.getReAttachByRegUnitCode";
		   sql=" and DIS_TYPE='"+WbfConstants.T_ATTACH+"' and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";
	   }
	   //轮候查封转查封
	   else if(attachType.equals(WbfConstants.T_CHATTACH)||attachType.equals(WbfConstants.T_UNCHATTACH))
	   {
		   key="Applicant.getReAttachByRegUnitCode";
		   sql=" and DIS_TYPE='"+WbfConstants.T_LASTATTACH+"' and EFFECTIVE='"+WbfConstants.EFFECTIVE+"' order by reg_date desc";
		   List<Map<String,Object>> Mapdistrain = demurrerDao.queryMapListByKey(key,sql, m);
			if(Mapdistrain.size() >0){
				List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>>();
				mapdis=Mapdistrain.get(0);
				listmap.add(mapdis);
				System.out.println();
				return listmap;
			}
	   }
		List<Map<String,Object>> Mapdistrain = demurrerDao.queryMapListByKey(key,sql, m);
		if(Mapdistrain.size() >0){
			mapdis=Mapdistrain.get(0);
			System.out.println();			
		}
	   return Mapdistrain;
   }
   /**
    * 根据登记编号获取登记单元关联表数据
    * @param regcode
    * @return
    */
   public List<Reg_relationship> getRegRelation(String regcode)
   {
	   try
	   {
		   Map map=new HashMap();
		   map.put("regcode", regcode);
		   List<Reg_relationship> list=relationshipDao.queryListByKey("Applicant.getRegRelation", " where reg_code=:regcode", map);
		   if(list.size()>0)
		   {
			   return list;
		   }
	   }
	   catch (Exception e) {
			e.printStackTrace();
		}
	   return null;
   }
   /**
    * 根据登记编号删除登记单元关联表数据
    * @param regcode
    * @return
    */
   public String deleteRegration(String id)
   {
	   try
	   {
		   relationshipDao.delete(new Reg_relationship(id));
		   return "0";
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
		   return "1";
	   }
   }
   /**
    * 查找所需业务的前登记编号
    * @param map
    * @return
    */
   public String getPreRegcode(Map map)
   {
	   String key="";
	   String sql="";
	   //解封
	   if(map.get(0).equals(WbfConstants.T_UNATTACH))
	   {
		   key="Applicant.getDistrainByid";
		   sql=" and DIS_TYPE='"+WbfConstants.T_ATTACH+"' and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";
	   }
	   //解除轮候查封
	   else if(map.get(0).equals(WbfConstants.T_UNREATTACH))
	   {
		   key="Applicant.getReAttachByRegUnitCode";
		   sql=" and DIS_TYPE='"+WbfConstants.T_LASTATTACH+"' and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";
	   }
	   //轮候查封转查封
	   else if(map.get(0).equals(WbfConstants.T_REATTACH))
	   {
		   sql=" and o.EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";
		   if(map.get(1).equals(WbfConstants.PARCEL))
		   {
			   key="Register.getBkOwnershipList";
		   }
		   else
		   {
			   key="Register.getBkUserightList";
		   }
	   }
	   List<Map<String,Object>> Mapdistrain = demurrerDao.queryMapListByKey(key,sql, map);
	   if(Mapdistrain.size()>0)
	   {
		   return Mapdistrain.get(0).get("reg_code").toString();
	   }
	   return null;
   }
   /**
    * 保存登记单元关联表数据
    * @param regcode
    * @return
    */
   public String saveRegrelation(Reg_relationship r)
   {
	   try
	   {
		   relationshipDao.save(r);
		   return "0";
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
		   return "1";
	   }
   }
   /**
    * 
    *  获取查封信息，续封和轮候查封转查封.
    * 
    */
   @Override
   public Map getAttachByRegUnitcode(String reg_unit_code,String attachType) 
   {
	   String key="";
	   String sql="";
	   //续封
	   if(attachType.equals(WbfConstants.T_REATTACH))
	   {
		   key="Applicant.getReAttachByRegUnitCode";
		   sql=" and DIS_TYPE='"+WbfConstants.T_ATTACH+"' and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";
	   }
	   //轮候查封转查封
	   else if(attachType.equals(WbfConstants.T_CHATTACH))
	   {
		   key="Applicant.getReAttachByRegUnitCode";
		   sql=" and DIS_TYPE='"+WbfConstants.T_ATTACH+"' and EFFECTIVE='"+WbfConstants.EFFECTIVE+"' order by reg_date desc";
	   }
	   //查封和轮候查封
	   else if(attachType.equals(WbfConstants.T_ATTACH)||attachType.equals(WbfConstants.T_LASTATTACH))
	   {
		   
	   }
	   	Map m = new HashMap();
		m.put("reg_unit_code", reg_unit_code);
		Map Mapdistrain = demurrerDao.queryMapByKey(key,sql,m);
		if(Mapdistrain == null){
			System.out.println();			
		}
	   return Mapdistrain;
   }
   
   /**
    * 
    *  保存查封登记信息.
    * @see com.szhome.cq.business.IApplicantFacade#saveAttach(com.szhome.cq.domain.model.BusOwnership)
    */
   @Override
   @Transactional
   public void saveAttach(BusDistrain b) {
	   Map m=new HashMap();
		m.put("id",b.getBus_id());
		BusDistrain dis = distrainDao.get(" where bus_id=:id",m);
			try {
			if(dis == null){
				//b=new Bususeright();
			    b.setDis_reg_id(distrainDao.getSeqId());
			    distrainDao.save(b);
				
			}else{
			    b.setDis_reg_id(dis.getDis_reg_id());
			    distrainDao.update(b);
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("保存土地使用权登记信息出错:"+e.getMessage());
			
		}
	   
   }
   
   /**
    * 
    *  获取查封信息.
    * @see com.szhome.cq.business.IApplicantFacade#getAttach(java.lang.String)
    */
   @Override
   public BusDistrain getAttach(String id) {
	   
	   Map m = new HashMap();
		m.put("id", id);
		BusDistrain dis = distrainDao.queryDomainBykey("Applicant.getattachByid", m);
		if(dis == null){
		//	throw new BusinessException("保存登记信息出错:"+new Exception().getMessage());
			System.out.println("-------");			
		}
	   return dis;
   }
   
   
   
   /**
    * 
    *  保存使用权登记信息.
    * @see com.szhome.cq.business.IApplicantFacade#saveOwnership(com.szhome.cq.domain.model.BusOwnership)
    */
   @Override
   @Transactional
   public void saveUseright(Bususeright b) {
	   Map m=new HashMap();
		m.put("id",b.getBus_id());
		Bususeright bos = busDao.get(" where bus_id=:id",m);
			try {
			if(bos == null){
				//b=new Bususeright();
			    b.setUseright_reg_id(busDao.getSeqId());
			    busDao.save(b);
				
			}else{
			    b.setUseright_reg_id(bos.getUseright_reg_id());
			    busDao.update(b);
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("保存土地使用权登记信息出错:"+e.getMessage());
			
		}
	   
   }
   
   /**
    * 
    *  获取土地使用权信息.
    * @see com.szhome.cq.business.IApplicantFacade#getBusownership(java.lang.String)
    */
   @Override
   public Bususeright getUseright(String id) {
	   
	   Map m = new HashMap();
		m.put("id", id);
		Bususeright bos = busDao.queryDomainBykey("Applicant.getuserightByid", m);
		if(bos == null){
		//	throw new BusinessException("保存登记信息出错:"+new Exception().getMessage());
			System.out.println("-------");			
		}
	   return bos;
   }
   

   /**
    * 
    *  获取房屋所有权信息.
    * @see com.szhome.cq.business.IApplicantFacade#getBusownership(java.lang.String)
    */
   @Override
   public BusOwnership getBusownership(String id) {
	   
	   Map m = new HashMap();
		m.put("id", id);
		BusOwnership bos = osDao.queryDomainBykey("Applicant.getOwnershipByid", m);
		if(bos == null){
		//	throw new BusinessException("保存登记信息出错:"+new Exception().getMessage());
			System.out.println();			
		}
	   return bos;
   }














}

