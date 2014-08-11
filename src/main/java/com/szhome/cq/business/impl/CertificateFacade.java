/**
 * Project Name:dxtx_re
 * File Name:CommonFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2013-12-26下午2:35:25
 * Copyright (c) 2013, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.szhome.cq.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.tools.ant.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;





import com.plan.commons.Row;
import com.plan.commons.RowImpl;
import com.plan.exceptions.GeneralException;
import com.plan.web.JsonResult;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.ICertificateFacade;
import com.szhome.cq.business.ICommonFacade;
import com.szhome.cq.business.vo.Menu;
import com.szhome.cq.business.vo.OwnerInfoVo;
import com.szhome.cq.domain.model.Announcement;
import com.szhome.cq.domain.model.Applicant;
import com.szhome.cq.domain.model.BusOwnership;
import com.szhome.cq.domain.model.BusType;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.Certificate;
import com.szhome.cq.domain.model.ComLanguage;
import com.szhome.cq.domain.model.ConNodeRelation;
import com.szhome.cq.domain.model.ConOffice;
import com.szhome.cq.domain.model.DictItem;
import com.szhome.cq.domain.model.ExamSecond;
import com.szhome.cq.domain.model.House;
import com.szhome.cq.domain.model.Land;
import com.szhome.cq.domain.model.Pigeonhole;
import com.szhome.cq.domain.model.ProcNode;
import com.szhome.cq.domain.model.RecMatConfigure;
import com.szhome.cq.domain.model.RecMaterial;
import com.szhome.cq.domain.model.Reg_ownership;
import com.szhome.cq.domain.model.Reg_relationship;
import com.szhome.cq.domain.model.RegisterBasicInfo;
import com.szhome.cq.domain.model.RegisterOwnershipPart;
import com.szhome.cq.domain.model.Testpaper;
import com.szhome.cq.utils.Constants;
import com.szhome.cq.utils.WbfConstants;
import com.szhome.security.ext.UserInfo;

/**
 * 
 * 公共使用的Facade服务
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-12-26 下午2:35:25 <br/>
 * @author   xuzz
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class CertificateFacade implements ICertificateFacade {
	@Autowired
	private BusinessMain businessMainDao;
	@Autowired
	private BusType busTypeDao;          //业务类型表
	@Autowired
	private ConNodeRelation conNodeRelationDao;   //流程节点办公页面关联表
	@Autowired
	private ConOffice conOfficeDao;			//表单或打印材料（办公页面）
	@Autowired
	private ProcNode procNodeDao;			//流程节点表
	@Autowired
	private Testpaper paperDao;
	@Autowired
	private Pigeonhole pigeoDao;
	@Autowired
	private ExamSecond examDao;
	@Autowired
	private ComLanguage comlanDao;
	@Autowired
	private Announcement announceDao;	
	@Autowired
	private Reg_relationship reg_relationshipDao;					//登记单元关联表实体
	@Autowired
	private BusOwnership busOwnershipDao;						//房屋所有权信息实体
	@Autowired
	private House  houseDao;   										//房屋实体
	@Autowired
	private Land   landDao;											//土地表实体
	@Autowired
	private Certificate certificateDao;								//房地产证表实体
	@Autowired
	private Applicant applicantcDao;								//申请人表实体
	@Autowired
	private RegisterBasicInfo registerBasicInfoDao; 				//登记簿基本信息
	@Autowired
	private RegisterOwnershipPart registerOwnershipPartDao;			//登记簿所有权部分
	@Autowired
	private RecMaterial  recmarDao;   //与业务相关的接件材料表信息
	@Autowired
	private RecMatConfigure recMatConDao;
	@Autowired
	private Menu menuDao;
	
	
	
	
	public Map getInitInfo(Map map){
		Map m=new HashMap();
		Map businessMainMap=businessMainDao.queryMapByKey("Common.getBusinessByProcId", map);
		if(businessMainMap!=null){
			m.put("businessMain",businessMainMap);
		}
		Map landMap=businessMainDao.queryMapByKey("Common.getLandById", " where lcslbid =:lcslbid)", map);
		if(landMap!=null){
			m.put("land",landMap);
		}
		Map houseMap=businessMainDao.queryMapByKey("Common.getHouseById", " where lcslbid =:lcslbid)", map);
		if(houseMap!=null){
			houseMap.put("JGSJ",DateUtils.format((Date)houseMap.get("JGSJ"),"yyyy年MM月dd日"));
			m.put("house",houseMap);
		}
		Map certificateMap=businessMainDao.queryMapByKey("Common.getCertificateByProcId", map);
		if(certificateMap!=null){
			certificateMap.put("SZRQ",DateUtils.format((Date)certificateMap.get("SZRQ"),"yyyy年MM月dd日"));
			m.put("certificate",certificateMap);
		} 
		return m;
	}
	
	
	
	
	/**
	 * 
	 * 获取分栋汇总表数据.
	 * @see com.szhome.cq.business.ICommonFacade#getBuildById(java.util.Map)
	 */
	public List getBuildById(Map map)
	{
		return businessMainDao.queryMapListByKey("Common.getBuildById", " where lcslbid =:lcslbid)", map);
	}
	/**
	 * 
	 * 获取分户汇总表数据.
	 * @see com.szhome.cq.business.ICommonFacade#getBuildById(java.util.Map)
	 */
	public List getHouseById(Map map){
		return businessMainDao.queryMapListByKey("Common.getHouseById", " where lcslbid =:lcslbid)", map);
	}
	/**
	 * 
	 * 获取宗地信息.
	 * @see com.szhome.cq.business.ICommonFacade#getBuildById(java.util.Map)
	 */
	public Map<String,Object> getLandById(Map map){
		Map m=new HashMap();
		m= businessMainDao.queryMapByKey("Common.getLand", " where lcslbid =:lcslbid)", map);
		m.put("QSRQ",DateUtils.format((Date)m.get("QSRQ"),"yyyy年MM月dd日"));
		m.put("ZZRQ",DateUtils.format((Date)m.get("ZZRQ"),"yyyy年MM月dd日"));
		return m;
	}
	
	/**
	 * 根据编号查询房地产具体信息.
	 * @see com.szhome.cq.business.ICommonFacade#getFdczByid(java.util.Map)
	 */
	public Certificate getCertificateByid(Map map){
		Certificate cer = certificateDao.queryDomainBykey("Certificate.getCertificateByid", map);
		if(cer == null){
			throw new BusinessException("错误信息:"+new Exception().getMessage());
			
		}
		
		return cer;
	}
	
	/**
	 * 根据编号查询房地产具体信息.
	 * @see com.szhome.cq.business.ICommonFacade#getFdczByid(java.util.Map)
	 */
	public List<Certificate> getCertificateListByProcId(String proc_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		List<Certificate> cerList = certificateDao.getAll("where bus_id=(select bus_id from bus_main where proc_id=:proc_id)", paraMap);
		if(cerList == null ||cerList.isEmpty()){
			//throw new BusinessException("错误信息:"+new Exception().getMessage());
			LogUtil.error("CertificateFacade.getCertificateListByProcId 房地产证信息表数据为空");
		}
		return cerList;
	}
	
	
	/**
	 * 
	 * 更新房地产证信息  更新时注意  先从数据库中取数据   
	 * @see com.szhome.cq.business.ICommonFacade#updateCertificate(com.szhome.cq.domain.model.Certificate)
	 */
	@Override
	@Transactional
	public void updateCertificate(Certificate c)
	{		
		Certificate dbCertificate = certificateDao.get(c);
		certificateDao.copyProperties(dbCertificate, c);		//把要更新的数据 复制到从数据库取出来看房地产证实体中   避免更新数据库数据为空
		try {
			certificateDao.update(dbCertificate);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("错误信息:"+e.getMessage());
		}
		
	}
	 /**
	  * 
	  * 保存或更新业务的房地产证附记  考虑到多套情况  
	  *
	  * @author Joyon
	  * @param bus_id
	  * @param excursus
	  * @since JDK 1.6
	  */
	 public void saveorupdateExcursus(String bus_id,String excursus) throws BusinessException{
		 //拿到当前业务的登记单元关联表数据
		 List<Reg_relationship> regrelationshipList = FacadeFactory.getCertificateFacade().getReg_relationshipByBusid(bus_id);
		 //循环拿数据 
		 Certificate dbCertificate = null;
		 for(Reg_relationship tmpRelationship :regrelationshipList){
			 dbCertificate =  FacadeFactory.getCertificateFacade().getCertificateByRegunitcodeAndBusid(tmpRelationship.getBus_id(), tmpRelationship.getReg_unit_code());
			 //如果数据里数据为空   则走保存   否则走更新
			 if(dbCertificate==null){
				 dbCertificate = new Certificate();
				 dbCertificate.setExcursus(excursus);
				 dbCertificate.setBus_id(bus_id);
				 dbCertificate.setReg_unit_code(tmpRelationship.getReg_unit_code());
				 FacadeFactory.getCertificateFacade().saveCertificate(dbCertificate);
			 }else{
				 dbCertificate.setExcursus(excursus);
				 dbCertificate.setReg_unit_code(tmpRelationship.getReg_unit_code());
				 FacadeFactory.getCertificateFacade().updateCertificate(dbCertificate);
			 }
		 }
		// return null;
	 }
	/**
	 * 
	 * 保存房地产证信息.
	 * @see com.szhome.cq.business.ICommonFacade#updateCertificate(com.szhome.cq.domain.model.Certificate)
	 */
	@Override
	@Transactional
	public void saveCertificate(Certificate c)
	{
		Certificate ce= certificateDao.get(c);
		try {
			if(ce==null){
				c.setCertificate_id(String.valueOf(certificateDao.getSeqId()));
				certificateDao.save(c);
			}
			else{
				certificateDao.save(ce);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("错误信息:"+e.getMessage());
		}
		
	}
	/**
	 * 
	 * TODO 保存组卷信息.
	 * @see com.szhome.cq.business.ICommonFacade#saveFileMess(com.szhome.cq.domain.model.Testpaper)
	 */
	@Override
	@Transactional
	public void saveFileMess(Testpaper t) {
		
		Map m=new HashMap();
		m.put("bus_id", t.getBus_id());
		Testpaper te= paperDao.get(" where bus_id=:bus_id", m);
		try {
		if(te==null){
			t.setArrange_id(paperDao.getSeqId());
			paperDao.save(t);
		}
		else{
			t.setArrange_id(te.getArrange_id());
			paperDao.update(t);
			
		}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("错误信息:"+e.getMessage());
		}
		
	}
	
	/**
	 *
	 * getBusIdByRegCode:(通过登记编号获取业务主表ID).
	 *
	 * @author Joyon
	 * @param regCode
	 * @return
	 * @since JDK 1.6
	 */
	public String getBusIdByRegCode(String regCode){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_code", regCode);
		Map<String,Object> busMainMap = null;
		try {
			busMainMap = paperDao.queryMapByKey(
					"Common.getBusMainInfoByRegCode", paraMap);
			if(busMainMap == null || busMainMap.isEmpty()){
				throw new BusinessException("获取业务主表信息出错 业务主表信息为空");
			}
		} catch (Exception e) {
			throw new BusinessException("获取业务主表信息出错"+e.getMessage());
		}
		
		return busMainMap.get("BUS_ID").toString();
	}
	/**
	 * 
	 * TODO 根据流程实例id获取组卷信息.
	 * @see com.szhome.cq.business.ICommonFacade#getFileByid(java.util.Map)
	 */
	@Override
	public Testpaper getFileByid(Map map) {
		
		// TODO Auto-generated method stub
		Testpaper t = new Testpaper();
	
			try {
				t = paperDao
						.get(" where bus_id=(select bus_id from bus_main where reg_code=:reg_code)",
								map);
			} catch (Exception e) {
				throw new BusinessException("获取组卷表信息出错"+e.getMessage());
			}
		return t;
	}
	/**
	 * 
	 * TODO 保存归档信息.
	 * @see com.szhome.cq.business.ICommonFacade#savePigeoMess(com.szhome.cq.domain.model.Pigeonhole)
	 */
	@Override
	@Transactional
	public void savePigeoMess(Pigeonhole p) {
		Map m=new HashMap();
		m.put("bus_id",p.getBus_id());
		Pigeonhole pi= pigeoDao.get(" where bus_id=:bus_id", m);
		try {
		if(pi==null){
			p.setArchives_id(pigeoDao.getSeqId());
			pigeoDao.save(p);
		}
		else{
			p.setArchives_id(pi.getArchives_id());
			pigeoDao.update(p);
			
		}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("错误信息:"+e.getMessage());
		}
	}
	/**
	 * 
	 * TODO 根据流程实例id获取归档信息.
	 * @see com.szhome.cq.business.ICommonFacade#getPigeByid(java.util.Map)
	 */
	@Override
	public Map getPigeByid(Map map) {
		Map<String,Object> m = new HashMap<String,Object>();
		
		Pigeonhole p = pigeoDao.get(" where bus_id=(select bus_id from bus_main where reg_code=:reg_code)", map);
		Testpaper t  = paperDao.get(" where bus_id=(select bus_id from bus_main where reg_code=:reg_code)", map);
		//int lcslbid = Integer.parseInt(map.get("lcslbid").toString());
		//OwnerInfoVo oivo = FacadeFactory.getApplicantFacade().getRegistMessById(lcslbid);
		
		
		Exception e  = new Exception();
		
		if(p == null){
			Row row = new RowImpl();
			row.put("name", "归档号");
			String arch_no = FacadeFactory.getIdentifierFacade().getSerialNumber(row);
			m.put("transfer", t.getArranger());//移接人
			m.put("transfer_date", DateUtils.format(t.getArrange_time(),"yyyy-MM-dd HH:mm:ss"));
			m.put("arch_no", arch_no);																	//获取归档号
			
			//throw new BusinessException("错误信息:"+e.getMessage());
		}else{
			//m.put("djbh",b.getDjbh());
			m.put("arbox_code", p.getArbox_code());
			m.put("transfer",p.getTransfer());
			m.put("transfer_date",DateUtils.format(p.getTransfer_date(),"yyyy-MM-dd HH:mm:ss"));
			m.put("arch_no",p.getArch_no());
			m.put("arch_handler",p.getArch_handler());
			m.put("arch_handler_no", p.getArch_handler_no());
			m.put("bus_id", p.getBus_id());
			m.put("arch_date",DateUtils.format(p.getArch_date(),"yyyy-MM-dd HH:mm:ss"));
			
		};
		
	

	 return m ;
	}
	
	/**
	 * 
	 * TODO 保存单条常用语信息.
	 * @see com.szhome.cq.business.ICommonFacade#saveComLanguage(com.szhome.cq.domain.model.ComLanguage)
	 */
	@Override
	@Transactional
	public void saveComLanguage(ComLanguage c) {
		
		Map m=new HashMap();
		//m.put("djlx_id",c.getDjlx_id());
		//ComLanguage co= comlanDao.get(" where lcslbid=:lcslbid", m);
		try {
		
		//	c.setCyyid(comlanDao.getSeqId());
			comlanDao.save(c);
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("错误信息:"+e.getMessage());
		}
		
	}
	/**
	 * 
	 * TODO 更新单条常用语信息.
	 * @see com.szhome.cq.business.ICommonFacade#updateComLanguage(com.szhome.cq.domain.model.ComLanguage)
	 */
	@Override
	@Transactional
	public void updateComLanguage(ComLanguage c) {
		
		try {
			comlanDao.update(c);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("错误信息:"+e.getMessage());
		}
		
	}
	/**
	 * 
	 * TODO 根据用户获取常用语信息.
	 * @see com.szhome.cq.business.ICommonFacade#getComLanByid(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> getComLanByid() {
		
		//return comlanDao.queryMapByKey("Common.getHouseById", " where lcslbid =:lcslbid)", m);
		Map m = new HashMap();
		//m.put("ywlxid", id);
		List<Map<String, Object>> lans = comlanDao.queryMapListByKey("Common.getComLanByid","where 1 = 1", m);
		return lans;
		
		
		
	}
	
	
			
			
		

    /**
     * 
     * TODO 保存领证人信息.
     * @see com.szhome.cq.business.ICommonFacade#saveDispatch(com.szhome.cq.domain.model.Certificate)
     */

	@Override
	@Transactional
	public void saveDispatch(Certificate c) {
		
		Map m=new HashMap();
		m.put("busid",c.getBus_id());
		Certificate cer= getCertificateByid(m);;
		try {
		if(cer==null){
			c.setCertificate_id(String.valueOf(certificateDao.getSeqId()));
			certificateDao.save(c);
		}
		else{
			c.setCertificate_id(cer.getCertificate_id());
			c.setCertificate_code(cer.getCertificate_code());
			c.setPrinter(cer.getPrinter());
			c.setPrint_date(cer.getPrint_date());
		//	c.setComment(cer.getComment());
			c.setCer_state(cer.getCer_state());
			certificateDao.update(c);
			
		}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("错误信息:"+e.getMessage());
		}
		
	}


    /**
     * 
     * TODO 获取具体业务接件材料信息（可选）.
     * @see com.szhome.cq.business.ICommonFacade#getRecMaterial(int)
     */
	@Override
	public List<Map<String, Object>> getRecMaterial(int id) {
		
		Map m = new HashMap();
		m.put("lcslbid", id);
		List<Map<String, Object>> mats = recmarDao.queryMapListByKey("Common.getRecmatByid","where lcslbid=:lcslbid", m);
		return mats;
	}

   /**
    * 
    * TODO 根据登记类型，从接件材料配置表中获取信息.
    * @see com.szhome.cq.business.ICommonFacade#getRMaterialCon(int)
    */
	@Override
	public List<Map<String, Object>> getMaterialCon(int id) {
		Map m = new HashMap();
		m.put("ywlxid", id);
		List<Map<String, Object>> mats = recmarDao.queryMapListByKey("Common.getRecmatConByid","where ywlxid=:ywlxid", m);
		return mats;
	}

   
	
	
	/**
	 * 
	 * getProcName:(查询流程定义以及流程定义，用于配置表单及报表配置). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public  List<BusType> getProcName()
	{
		return busTypeDao.queryObjectListByKey("Common.getProcName", null, BusType.class);
	}
	/**
	 * 
	 * getProcNodeById:(查询流程定义以及流程定义节点，用于配置表单及报表配置). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public  List<ProcNode> getProcNodeById(String parentid)
	{
		Map map=new HashMap();
		map.put("parentid", parentid);
		return procNodeDao.queryListByKey("Common.getProcNodeById", " where b.BUS_TYPE_ID=:parentid)", map);
	}
	
	/**
	 * 
	 * getProcNode:(查询流程定义以及流程定义节点，用于配置表单及报表配置). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public  List<ProcNode> getProcNode()
	{
		return procNodeDao.queryObjectListByKey("Common.getProcNode", null, ProcNode.class);
	}
	
	
	/**
	 * 
	 * getFormById:(根据流程定义节点查询表单及报表列表). <br/>
	 * @author xuzz
	 * @param nodeid
	 * @return
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public  List<Map<String,Object>> getFormById(String nodeid,String bus_typeid)
	{
		Map m=new HashMap();
		m.put("nodeid", nodeid);
		m.put("bus_typeid", bus_typeid);
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> listcon=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>>();
		if(bus_typeid==null||bus_typeid.equals(""))
		{		
			resultList=conOfficeDao.queryMapListByKey("Common.getFrom", null,m);
		}
		else if((bus_typeid!=null||bus_typeid!="")&&nodeid.equals(""))
		{			
			resultList=conOfficeDao.queryMapListByKey("Common.getFrom", " where BUS_TYPE_ID=:bus_typeid",m);
		}
		else
		{
			listcon=conOfficeDao.queryMapListByKey("Common.getFrom", " where BUS_TYPE_ID=:bus_typeid",m);
			if((bus_typeid!=null||bus_typeid!="")&&nodeid!="")
			{
				listmap=conOfficeDao.queryMapListByKey("Common.getFormById", " where P.NODE_ID=:nodeid and P.BUS_TYPE_ID=:bus_typeid",m);
				if(listmap.size()>0)
				{
					StringBuilder keys = new StringBuilder();
					for(Map mi:listmap){
						keys.append("["+mi.get("OFFICE_ID")).append("],");
					}
					for(Map mo:listcon){
						if(keys.toString().contains("["+mo.get("OFFICE_ID").toString()+"]")){
							mo.put("CK", "true");
						}else{
							mo.put("CK", "");								
						}	
						resultList.add(mo);
					}
				}
				else
				{
					resultList=listcon;
				}
			}
			
		}
		for(int i=0;i<resultList.size();i++)
		{
			if(resultList.get(i).get("OFFICE_TYPE").equals("0"))
			{
				resultList.get(i).put("OFFICE_TYPE", "表单");				
			}
			else{
				resultList.get(i).put("OFFICE_TYPE", "报表");	
			}
			
			
		}
		return resultList;
	}
	




    /**
     * 
     * TODO 保存单个菜单.
     * @see com.szhome.cq.business.ICommonFacade#saveMenu(com.szhome.cq.domain.model.Tree)
     */
	@Override
	@Transactional
	public void saveMenu(Menu me) {
		
		Map m=new HashMap();
		m.put("tree_name",me.getTree_name());
		Menu menu = menuDao.get(" where tree_name=:tree_name", m);
		try {
		if(menu ==null ){
			//me.setTree_id(treeDao.getSeqId());
			menuDao.save(me);
		}
		else{
			//rec.setJjclpzbid(r.getJjclpzbid());
			me.setTree_id(menu.getTree_id());
			menuDao.update(me);
			
		}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("错误信息:"+e.getMessage());
		}
		
	}
	
   /**
    * 
    * TODO 更新单个菜单.
    * @see com.szhome.cq.business.ICommonFacade#updateMenu(com.szhome.cq.domain.model.Tree)
    */
	@Override
	@Transactional
	public void updateMenu(Menu me) {
		
		try {
			menuDao.update(me);
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("错误信息:"+e.getMessage());
			
		}
		
	}


   /**
    * 
    * TODO 删除单个菜单.
    * @see com.szhome.cq.business.ICommonFacade#delMenu(com.szhome.cq.domain.model.Tree)
    */
	@Override
	@Transactional
	public void delMenu(int id) {
		
	
		try {
			menuDao.delete(new Menu(id));
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("错误信息:"+e.getMessage());
			
		}
		
	}


	/**
	 * 
	 * saveForm:(保存表单数据). <br/>
	 * @author xuzz
	 * @param con
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public void saveForm(ConOffice con)
	{
			try {
				con.setOffice_id(String.valueOf(conOfficeDao.getSeqId()));
				conOfficeDao.save(con);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("错误信息:"+e.getMessage());
			}
	}
	
	/**
	 * 
	 * saveForm:(更新表单数据). <br/>
	 * @author xuzz
	 * @param con
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public void updateForm(ConOffice con)
	{
			try {
				conOfficeDao.update(con);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("错误信息:"+e.getMessage());
			}
	}
	
	
	
	/**
	 * 
	 * saveForm:(删除表单数据). <br/>
	 * @author xuzz
	 * @param con
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public void deleteForm(String officeid)
	{
			try {	
				conOfficeDao.delete(new ConOffice(officeid));
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("错误信息:"+e.getMessage());
			}
	}
	
	
	/**
	 * 
	 * insertNode:(插入选中节点上的表单). <br/>
	 * @author xuzz
	 * @param c
	 * @param bustypeid
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public void insertNode(ConNodeRelation c,String bustypeid)
	{
		int i=selectNode(c,bustypeid);
		if(i==0)
		{
			try {
				c.setNode_ofi_rel(String.valueOf(conNodeRelationDao.getSeqId()));
				conNodeRelationDao.save(c);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("错误信息:"+e.getMessage());
			}
		}
	}
	/**
	 * 
	 * selectNode:(查询是否选中节点上的表单). <br/>
	 * @author xuzz
	 * @param c
	 * @param bustypeid
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public int selectNode(ConNodeRelation c,String bustypeid)
	{
		Map m=new HashMap();
		m.put("nodeid", c.getNode_id());
		m.put("officeid", c.getOffice_id()); 
		m.put("bustypeid", bustypeid);
		Map ma= conNodeRelationDao.queryMapByKey("Common.getFormById", " where C.BUS_TYPE_ID=:bustypeid and N.OFFICE_ID=:officeid and P.NODE_ID=:nodeid", m);
		if(ma==null)
		{
			return 0;			
		}
		c.setNode_ofi_rel(String.valueOf(ma.get("Node_ofi_rel")));
		return 1;
	}
	/**
	 * 
	 * selectNode:(删除是否选中节点上的表单). <br/>
	 * @author xuzz
	 * @param c
	 * @param bustypeid
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public void deleteNode(ConNodeRelation c,String bustypeid)
	{
		int i=selectNode(c,bustypeid);
		if(i==1)
		{
			try {				
				conNodeRelationDao.delete(new ConNodeRelation(c.getNode_ofi_rel()));
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("错误信息:"+e.getMessage());
			}
		}
	}
	



	@Override
	public void saveRecMaterial(RecMatConfigure rec) {
		
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * getCertificateMapByProcId:(通过流程实例ID获取房地产证信息Map)
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Map<String,Object> getCertificateMapByProcId(String proc_id) throws BusinessException{
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		List<Map<String,Object>> mapList = certificateDao.queryMapListByKey("Certificate.getCertificateMapByProcId", paraMap);
		if(mapList!=null && mapList.size()>0){
			return mapList.get(0);
		}
		return null;
		
	}


	@Override
	public void delRecMaterial(int id) {
		
		// TODO Auto-generated method stub
		
	}
	/**
	 * 
	 * getCertificateInfo:(获取缮证页面信息).
	 *
	 * @author Joyon
	 * @param paraMap reg_code
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String, Object> getCertificateInfo(Map<String, Object> paraMap){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("naturalInfo",FacadeFactory.getRegisterFacade().getNaturalInfo(paraMap));
		resultMap.put("holders", FacadeFactory.getRegisterFacade().getHolderInfoByRegId(paraMap));
		return resultMap;
	}
	
	/**
	 * 
	 * 根据业务id 查询缮证自然信息
	 *
	 * @author Joyon
	 * @param bus_id
	 * @return
	 * @since JDK 1.6
	 */
	public List getCertificateNaturalByBusid(String bus_id,UserInfo userInfo){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		List resultList = new ArrayList();
		BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByBusid(bus_id);
		String reg_code = businessMain.getReg_code();														//登记编号
		String location_unit = businessMain.getLocation_reg_unit();											//所属区
		String bus_type_name = FacadeFactory.getCommonFacade().getBustypenameByBusid(bus_id);				//业务类型名
		paraMap.put("bus_id", bus_id);
		//获取当前业务登记单元关联表中数据
		Map<String,Object> tmpNaturalMap = null;
		Certificate tmpCertificate = null;
		List<Reg_relationship> reg_relationList = getReg_relationshipByBusid(bus_id);
		String tmpRegunitcode = null;
		String tmp_effic_cer_no = null;
		String tmp_reg_unit_type = null;
		String tmp_est_name = null;
		List tmp_holder_list = null;
		String reg_value = null;
		for(Reg_relationship tmpRegrelationship:reg_relationList){
			tmpRegunitcode = tmpRegrelationship.getReg_unit_code();
			tmp_reg_unit_type = tmpRegrelationship.getReg_unit_type();
			//tmpNaturalMap = new HashMap<String,Object>();
			//获取自然信息
			tmpNaturalMap = FacadeFactory.getRegisterFacade().getNaturalInfoMapByRegUnitCode(tmpRegunitcode);
			//获取房地产证信息
			tmpCertificate = getCertificateByRegunitcodeAndBusid(bus_id,tmpRegunitcode);
			//缮证状态 
			if(tmpCertificate!=null){
				tmpNaturalMap.put("CER_STATE", tmpCertificate.getCer_state());
				tmpNaturalMap.put("PRINT_DATE", tmpCertificate.getPrint_date());
				tmpNaturalMap.put("PRINTER", tmpCertificate.getPrinter());
				tmpNaturalMap.put("EXCURSUS", tmpCertificate.getExcursus());
			}else{
				tmpNaturalMap.put("PRINT_DATE", new Date());
				tmpNaturalMap.put("PRINTER", userInfo.getUserName());
			}
			if(tmp_reg_unit_type.equals(WbfConstants.HOUSE)){
				tmp_est_name = tmpNaturalMap.get("BUILDING_NAME").toString()+tmpNaturalMap.get("BUILD_NO").toString()+tmpNaturalMap.get("ROOMNAME").toString();
				Reg_ownership reg_ownership = FacadeFactory.getRegisterFacade().getEffectiveReg_OwnershipByRegUnitCode(tmpRegunitcode);
				if(reg_ownership!=null){
					reg_value = reg_ownership.getReg_value();
				}
			}else{
				tmp_est_name = "";
				reg_value = FacadeFactory.getRegisterFacade().getEffectiveReg_userightByRegUnitCode(tmpRegunitcode).getReg_pri();
			}
			//获取当前登记单元生效的权利人
			tmp_holder_list = FacadeFactory.getRegisterFacade().getEffictiveHolderMapListByRegUnitCode(tmpRegunitcode, "");
			
			//获取生效的房地产证号R
			tmp_effic_cer_no = FacadeFactory.getRegisterFacade().getEffectiveCerNoByRegUnitCode(tmpRegunitcode);
			tmpNaturalMap.put("REG_UNIT_CODE", tmpRegunitcode);
			tmpNaturalMap.put("REG_UNIT_TYPE", tmpRegrelationship.getReg_unit_type());
			tmpNaturalMap.put("REG_CODE", reg_code);											//登记编号
			tmpNaturalMap.put("BUS_TYPE_NAME", bus_type_name);									//业务类型名
			tmpNaturalMap.put("CER_NO", tmp_effic_cer_no);
			tmpNaturalMap.put("LOCATION_REG_UNIT", location_unit);
			tmpNaturalMap.put("EST_NAME", tmp_est_name);
			tmpNaturalMap.put("REG_VALUE", reg_value);
			tmpNaturalMap.put("holder", tmp_holder_list);
			resultList.add(tmpNaturalMap);
		}
		
//		try {
//			resultList = certificateDao.queryMapListByKey(
//					"Certificate.getCertificateNatural",
//					" where r.bus_id=:bus_id", paraMap);
//		} catch (Exception e) {
//			LogUtil.error("CertificateFacade.getCertificateNatural 获取业务缮证自然信息出错 bus_id:"+bus_id+"  error message:"+e.getMessage());
//		}
		return resultList;
	}
	
	/**
	 * 
	 * 通过业务ID  获取登记单元关联表数据
	 *
	 * @author Joyon
	 * @param bus_id
	 * @return  List
	 * @since JDK 1.6
	 */
	public List<Reg_relationship> getReg_relationshipByBusid(String bus_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id", bus_id);
		List resultList = null;
		try {
			 resultList = reg_relationshipDao.getAll(
					" where bus_id=:bus_id", paraMap);
		} catch (Exception e) {
			LogUtil.error("CertificateFacade.getReg_relationshipByBusid bus_id:"+bus_id+" 获取登记单元关联表信息出错："+e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 
	 * 通过登记单元编号和业务ID  获取唯一 一条房地产证信息
	 *
	 * @author Joyon
	 * @param bus_id
	 * @param reg_unit_code
	 * @return
	 * @since JDK 1.6
	 */
	public Certificate getCertificateByRegunitcodeAndBusid(String bus_id,String reg_unit_code){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id", bus_id);
		paraMap.put("reg_unit_code", reg_unit_code);
		Certificate resultCertificate = null;
		try {
			resultCertificate =	certificateDao.get(
					" where bus_id=:bus_id and reg_unit_code=:reg_unit_code",
					paraMap);
		} catch (Exception e) {
			LogUtil.error("CertificateFacade.getCertificateByRegunitcodeAndBusid  获取房地产证信息出错  bus_id:"+bus_id+" reg_unit_code:"+reg_unit_code+" "+e.getMessage());
		}
		return resultCertificate;
	}


	@Override
	public List<Map<String, Object>> getRegPreCerInfo(String lcslbid) {
		
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public void updateRecMaterial(RecMatConfigure rec) {
		
		// TODO Auto-generated method stub
		
	}




	@Override
	public Map saveRecMat(Map map) {
		
		// TODO Auto-generated method stub
		return null;
	}








	@Override
	public void saveAnnounce(Announcement a) {
		
		// TODO Auto-generated method stub
		
	}




	@Override
	public Announcement getAnnounceByid(Map map) {
		
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public void delComLanguage(int id) {
		
		// TODO Auto-generated method stub
		
	}
	
	
}


