/**
 * Project Name:dxtx_re
 * File Name:CommonFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2013-12-26����2:35:25
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
 * ����ʹ�õ�Facade����
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-12-26 ����2:35:25 <br/>
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
	private BusType busTypeDao;          //ҵ�����ͱ�
	@Autowired
	private ConNodeRelation conNodeRelationDao;   //���̽ڵ�칫ҳ�������
	@Autowired
	private ConOffice conOfficeDao;			//�����ӡ���ϣ��칫ҳ�棩
	@Autowired
	private ProcNode procNodeDao;			//���̽ڵ��
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
	private Reg_relationship reg_relationshipDao;					//�Ǽǵ�Ԫ������ʵ��
	@Autowired
	private BusOwnership busOwnershipDao;						//��������Ȩ��Ϣʵ��
	@Autowired
	private House  houseDao;   										//����ʵ��
	@Autowired
	private Land   landDao;											//���ر�ʵ��
	@Autowired
	private Certificate certificateDao;								//���ز�֤��ʵ��
	@Autowired
	private Applicant applicantcDao;								//�����˱�ʵ��
	@Autowired
	private RegisterBasicInfo registerBasicInfoDao; 				//�Ǽǲ�������Ϣ
	@Autowired
	private RegisterOwnershipPart registerOwnershipPartDao;			//�Ǽǲ�����Ȩ����
	@Autowired
	private RecMaterial  recmarDao;   //��ҵ����صĽӼ����ϱ���Ϣ
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
			houseMap.put("JGSJ",DateUtils.format((Date)houseMap.get("JGSJ"),"yyyy��MM��dd��"));
			m.put("house",houseMap);
		}
		Map certificateMap=businessMainDao.queryMapByKey("Common.getCertificateByProcId", map);
		if(certificateMap!=null){
			certificateMap.put("SZRQ",DateUtils.format((Date)certificateMap.get("SZRQ"),"yyyy��MM��dd��"));
			m.put("certificate",certificateMap);
		} 
		return m;
	}
	
	
	
	
	/**
	 * 
	 * ��ȡ�ֶ����ܱ�����.
	 * @see com.szhome.cq.business.ICommonFacade#getBuildById(java.util.Map)
	 */
	public List getBuildById(Map map)
	{
		return businessMainDao.queryMapListByKey("Common.getBuildById", " where lcslbid =:lcslbid)", map);
	}
	/**
	 * 
	 * ��ȡ�ֻ����ܱ�����.
	 * @see com.szhome.cq.business.ICommonFacade#getBuildById(java.util.Map)
	 */
	public List getHouseById(Map map){
		return businessMainDao.queryMapListByKey("Common.getHouseById", " where lcslbid =:lcslbid)", map);
	}
	/**
	 * 
	 * ��ȡ�ڵ���Ϣ.
	 * @see com.szhome.cq.business.ICommonFacade#getBuildById(java.util.Map)
	 */
	public Map<String,Object> getLandById(Map map){
		Map m=new HashMap();
		m= businessMainDao.queryMapByKey("Common.getLand", " where lcslbid =:lcslbid)", map);
		m.put("QSRQ",DateUtils.format((Date)m.get("QSRQ"),"yyyy��MM��dd��"));
		m.put("ZZRQ",DateUtils.format((Date)m.get("ZZRQ"),"yyyy��MM��dd��"));
		return m;
	}
	
	/**
	 * ���ݱ�Ų�ѯ���ز�������Ϣ.
	 * @see com.szhome.cq.business.ICommonFacade#getFdczByid(java.util.Map)
	 */
	public Certificate getCertificateByid(Map map){
		Certificate cer = certificateDao.queryDomainBykey("Certificate.getCertificateByid", map);
		if(cer == null){
			throw new BusinessException("������Ϣ:"+new Exception().getMessage());
			
		}
		
		return cer;
	}
	
	/**
	 * ���ݱ�Ų�ѯ���ز�������Ϣ.
	 * @see com.szhome.cq.business.ICommonFacade#getFdczByid(java.util.Map)
	 */
	public List<Certificate> getCertificateListByProcId(String proc_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		List<Certificate> cerList = certificateDao.getAll("where bus_id=(select bus_id from bus_main where proc_id=:proc_id)", paraMap);
		if(cerList == null ||cerList.isEmpty()){
			//throw new BusinessException("������Ϣ:"+new Exception().getMessage());
			LogUtil.error("CertificateFacade.getCertificateListByProcId ���ز�֤��Ϣ������Ϊ��");
		}
		return cerList;
	}
	
	
	/**
	 * 
	 * ���·��ز�֤��Ϣ  ����ʱע��  �ȴ����ݿ���ȡ����   
	 * @see com.szhome.cq.business.ICommonFacade#updateCertificate(com.szhome.cq.domain.model.Certificate)
	 */
	@Override
	@Transactional
	public void updateCertificate(Certificate c)
	{		
		Certificate dbCertificate = certificateDao.get(c);
		certificateDao.copyProperties(dbCertificate, c);		//��Ҫ���µ����� ���Ƶ������ݿ�ȡ���������ز�֤ʵ����   ����������ݿ�����Ϊ��
		try {
			certificateDao.update(dbCertificate);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("������Ϣ:"+e.getMessage());
		}
		
	}
	 /**
	  * 
	  * ��������ҵ��ķ��ز�֤����  ���ǵ��������  
	  *
	  * @author Joyon
	  * @param bus_id
	  * @param excursus
	  * @since JDK 1.6
	  */
	 public void saveorupdateExcursus(String bus_id,String excursus) throws BusinessException{
		 //�õ���ǰҵ��ĵǼǵ�Ԫ����������
		 List<Reg_relationship> regrelationshipList = FacadeFactory.getCertificateFacade().getReg_relationshipByBusid(bus_id);
		 //ѭ�������� 
		 Certificate dbCertificate = null;
		 for(Reg_relationship tmpRelationship :regrelationshipList){
			 dbCertificate =  FacadeFactory.getCertificateFacade().getCertificateByRegunitcodeAndBusid(tmpRelationship.getBus_id(), tmpRelationship.getReg_unit_code());
			 //�������������Ϊ��   ���߱���   �����߸���
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
	 * ���淿�ز�֤��Ϣ.
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
			throw new BusinessException("������Ϣ:"+e.getMessage());
		}
		
	}
	/**
	 * 
	 * TODO ���������Ϣ.
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
			throw new BusinessException("������Ϣ:"+e.getMessage());
		}
		
	}
	
	/**
	 *
	 * getBusIdByRegCode:(ͨ���ǼǱ�Ż�ȡҵ������ID).
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
				throw new BusinessException("��ȡҵ��������Ϣ���� ҵ��������ϢΪ��");
			}
		} catch (Exception e) {
			throw new BusinessException("��ȡҵ��������Ϣ����"+e.getMessage());
		}
		
		return busMainMap.get("BUS_ID").toString();
	}
	/**
	 * 
	 * TODO ��������ʵ��id��ȡ�����Ϣ.
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
				throw new BusinessException("��ȡ������Ϣ����"+e.getMessage());
			}
		return t;
	}
	/**
	 * 
	 * TODO ����鵵��Ϣ.
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
			throw new BusinessException("������Ϣ:"+e.getMessage());
		}
	}
	/**
	 * 
	 * TODO ��������ʵ��id��ȡ�鵵��Ϣ.
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
			row.put("name", "�鵵��");
			String arch_no = FacadeFactory.getIdentifierFacade().getSerialNumber(row);
			m.put("transfer", t.getArranger());//�ƽ���
			m.put("transfer_date", DateUtils.format(t.getArrange_time(),"yyyy-MM-dd HH:mm:ss"));
			m.put("arch_no", arch_no);																	//��ȡ�鵵��
			
			//throw new BusinessException("������Ϣ:"+e.getMessage());
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
	 * TODO ���浥����������Ϣ.
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
			throw new BusinessException("������Ϣ:"+e.getMessage());
		}
		
	}
	/**
	 * 
	 * TODO ���µ�����������Ϣ.
	 * @see com.szhome.cq.business.ICommonFacade#updateComLanguage(com.szhome.cq.domain.model.ComLanguage)
	 */
	@Override
	@Transactional
	public void updateComLanguage(ComLanguage c) {
		
		try {
			comlanDao.update(c);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("������Ϣ:"+e.getMessage());
		}
		
	}
	/**
	 * 
	 * TODO �����û���ȡ��������Ϣ.
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
     * TODO ������֤����Ϣ.
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
			throw new BusinessException("������Ϣ:"+e.getMessage());
		}
		
	}


    /**
     * 
     * TODO ��ȡ����ҵ��Ӽ�������Ϣ����ѡ��.
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
    * TODO ���ݵǼ����ͣ��ӽӼ��������ñ��л�ȡ��Ϣ.
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
	 * getProcName:(��ѯ���̶����Լ����̶��壬�������ñ�����������). <br/>
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
	 * getProcNodeById:(��ѯ���̶����Լ����̶���ڵ㣬�������ñ�����������). <br/>
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
	 * getProcNode:(��ѯ���̶����Լ����̶���ڵ㣬�������ñ�����������). <br/>
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
	 * getFormById:(�������̶���ڵ��ѯ���������б�). <br/>
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
				resultList.get(i).put("OFFICE_TYPE", "��");				
			}
			else{
				resultList.get(i).put("OFFICE_TYPE", "����");	
			}
			
			
		}
		return resultList;
	}
	




    /**
     * 
     * TODO ���浥���˵�.
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
			throw new BusinessException("������Ϣ:"+e.getMessage());
		}
		
	}
	
   /**
    * 
    * TODO ���µ����˵�.
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
			throw new BusinessException("������Ϣ:"+e.getMessage());
			
		}
		
	}


   /**
    * 
    * TODO ɾ�������˵�.
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
			throw new BusinessException("������Ϣ:"+e.getMessage());
			
		}
		
	}


	/**
	 * 
	 * saveForm:(���������). <br/>
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
				throw new BusinessException("������Ϣ:"+e.getMessage());
			}
	}
	
	/**
	 * 
	 * saveForm:(���±�����). <br/>
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
				throw new BusinessException("������Ϣ:"+e.getMessage());
			}
	}
	
	
	
	/**
	 * 
	 * saveForm:(ɾ��������). <br/>
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
				throw new BusinessException("������Ϣ:"+e.getMessage());
			}
	}
	
	
	/**
	 * 
	 * insertNode:(����ѡ�нڵ��ϵı�). <br/>
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
				throw new BusinessException("������Ϣ:"+e.getMessage());
			}
		}
	}
	/**
	 * 
	 * selectNode:(��ѯ�Ƿ�ѡ�нڵ��ϵı�). <br/>
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
	 * selectNode:(ɾ���Ƿ�ѡ�нڵ��ϵı�). <br/>
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
				throw new BusinessException("������Ϣ:"+e.getMessage());
			}
		}
	}
	



	@Override
	public void saveRecMaterial(RecMatConfigure rec) {
		
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * getCertificateMapByProcId:(ͨ������ʵ��ID��ȡ���ز�֤��ϢMap)
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
	 * getCertificateInfo:(��ȡ��֤ҳ����Ϣ).
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
	 * ����ҵ��id ��ѯ��֤��Ȼ��Ϣ
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
		String reg_code = businessMain.getReg_code();														//�ǼǱ��
		String location_unit = businessMain.getLocation_reg_unit();											//������
		String bus_type_name = FacadeFactory.getCommonFacade().getBustypenameByBusid(bus_id);				//ҵ��������
		paraMap.put("bus_id", bus_id);
		//��ȡ��ǰҵ��Ǽǵ�Ԫ������������
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
			//��ȡ��Ȼ��Ϣ
			tmpNaturalMap = FacadeFactory.getRegisterFacade().getNaturalInfoMapByRegUnitCode(tmpRegunitcode);
			//��ȡ���ز�֤��Ϣ
			tmpCertificate = getCertificateByRegunitcodeAndBusid(bus_id,tmpRegunitcode);
			//��֤״̬ 
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
			//��ȡ��ǰ�Ǽǵ�Ԫ��Ч��Ȩ����
			tmp_holder_list = FacadeFactory.getRegisterFacade().getEffictiveHolderMapListByRegUnitCode(tmpRegunitcode, "");
			
			//��ȡ��Ч�ķ��ز�֤��R
			tmp_effic_cer_no = FacadeFactory.getRegisterFacade().getEffectiveCerNoByRegUnitCode(tmpRegunitcode);
			tmpNaturalMap.put("REG_UNIT_CODE", tmpRegunitcode);
			tmpNaturalMap.put("REG_UNIT_TYPE", tmpRegrelationship.getReg_unit_type());
			tmpNaturalMap.put("REG_CODE", reg_code);											//�ǼǱ��
			tmpNaturalMap.put("BUS_TYPE_NAME", bus_type_name);									//ҵ��������
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
//			LogUtil.error("CertificateFacade.getCertificateNatural ��ȡҵ����֤��Ȼ��Ϣ���� bus_id:"+bus_id+"  error message:"+e.getMessage());
//		}
		return resultList;
	}
	
	/**
	 * 
	 * ͨ��ҵ��ID  ��ȡ�Ǽǵ�Ԫ����������
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
			LogUtil.error("CertificateFacade.getReg_relationshipByBusid bus_id:"+bus_id+" ��ȡ�Ǽǵ�Ԫ��������Ϣ����"+e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 
	 * ͨ���Ǽǵ�Ԫ��ź�ҵ��ID  ��ȡΨһ һ�����ز�֤��Ϣ
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
			LogUtil.error("CertificateFacade.getCertificateByRegunitcodeAndBusid  ��ȡ���ز�֤��Ϣ����  bus_id:"+bus_id+" reg_unit_code:"+reg_unit_code+" "+e.getMessage());
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


