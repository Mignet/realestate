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
import com.szhome.cq.business.ICommonFacade;
import com.szhome.cq.business.vo.Menu;
import com.szhome.cq.business.vo.Tax;
import com.szhome.cq.domain.model.Announcement;
import com.szhome.cq.domain.model.Applicant;
import com.szhome.cq.domain.model.Building;
import com.szhome.cq.domain.model.BusMatter;
import com.szhome.cq.domain.model.BusOwnership;
import com.szhome.cq.domain.model.BusType;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.Bususeright;
import com.szhome.cq.domain.model.Certificate;
import com.szhome.cq.domain.model.ComLanguage;
import com.szhome.cq.domain.model.ConNodeRelation;
import com.szhome.cq.domain.model.ConOffice;
import com.szhome.cq.domain.model.Contract;
import com.szhome.cq.domain.model.DeclarationForm;
import com.szhome.cq.domain.model.ExamSecond;
import com.szhome.cq.domain.model.House;
import com.szhome.cq.domain.model.Land;
import com.szhome.cq.domain.model.Pigeonhole;
import com.szhome.cq.domain.model.ProcNode;
import com.szhome.cq.domain.model.RecMatConfigure;
import com.szhome.cq.domain.model.RecMaterial;
import com.szhome.cq.domain.model.Reg_Useright;
import com.szhome.cq.domain.model.Reg_ownership;
import com.szhome.cq.domain.model.Reg_relationship;
import com.szhome.cq.domain.model.RegisterBasicInfo;
import com.szhome.cq.domain.model.RegisterOwnershipPart;
import com.szhome.cq.domain.model.Testpaper;
import com.szhome.cq.domain.model.TreeMenu;

/**
 * ����ʹ�õ�Facade����
 * Function:  ADD FUNCTION. <br/>
 * Reason:	  ADD REASON. <br/>
 * Date:     2013-12-26 ����2:35:25 <br/>
 * @author   xuzz
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class CommonFacade implements ICommonFacade {
	@Autowired
	private BusinessMain businessMainDao;
	@Autowired
	private BusType busTypeDao;          //ҵ�����ͱ�
	@Autowired
	private BusMatter busMatterDao;          //ҵ�������б�
	
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
	private Building   buildingDao;									//¥���ʵ��
	@Autowired
	private Certificate certificateDao;								//���ز�֤��ʵ��
	@Autowired
	private Applicant applicantcDao;								//�����˱�ʵ��								//Ȩ���˼��ϱ�ʵ��
	@Autowired
	private RegisterBasicInfo registerBasicInfoDao; 				//�Ǽǲ�������Ϣ
	@Autowired
	private RegisterOwnershipPart registerOwnershipPartDao;			//�Ǽǲ�����Ȩ����
	@Autowired
	private RecMaterial  recmarDao;   //��ҵ����صĽӼ����ϱ���Ϣ
	@Autowired
	private RecMatConfigure recMatConDao;
	@Autowired
	private TreeMenu treeDao;	
	@Autowired
	private Menu menuDao;
	@Autowired
	private Contract contractDao;									//��ͬ��Ϣ
	@Autowired
	private Reg_ownership reg_ownershipDao;							//�Ǽǲ� ����Ȩ����
	
	@Autowired
	private DeclarationForm declarationFormDao;						//����˰���걨����Ϣ
	
	@Autowired
	private Bususeright bususerightDao;								//ʹ��Ȩ�Ǽ���Ϣ	
	@Autowired
	private Reg_Useright reg_UserightDao;							//ʹ��Ȩ�Ǽǲ���Ϣ
	
	
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
	{   //List<Map<String, Object>> li = businessMainDao.queryMapListByKey("Common.getBuildById", " r.bus_id =:bus_id)", map);
		return businessMainDao.queryMapListByKey("Common.getBuildById", " r.bus_id =:bus_id) co where b.building_code  = co.building_code", map);
	}
	/**
	 * 
	 * ��ȡ�ֻ����ܱ�����.
	 * @see com.szhome.cq.business.ICommonFacade#getBuildById(java.util.Map)
	 */
	public List getHouseById(Map map){
		return businessMainDao.queryMapListByKey("Common.getHouseById", "  r.bus_id =:bus_id", map);
	}
	/**
	 * 
	 * ��ȡ�ڵ���Ϣ.
	 * @see com.szhome.cq.business.ICommonFacade#getBuildById(java.util.Map)
	 */
	public Map<String,Object> getLandById(Map map){
		Map m=new HashMap();
		m= businessMainDao.queryMapByKey("Common.getLandById", " r.bus_id =:bus_id) co where b.building_code  = co.building_code ) pa where p.PARCEL_ID=pa.PARCEL_ID and p.PARCEL_ID = ba.PARCEL_ID and ba.BOOK_CODE = u.BOOK_CODE", map);
		if(m==null){
			
			System.out.println();
		}
		m.put("start_date",DateUtils.format((Date)m.get("start_date"),"yyyy��MM��dd��"));
		m.put("end_date",DateUtils.format((Date)m.get("end_date"),"yyyy��MM��dd��"));
		return m;
	}
	
	/**
	 * ���ݱ�Ų�ѯ���ز�������Ϣ.
	 * @see com.szhome.cq.business.ICommonFacade#getFdczByid(java.util.Map)
	 */
	public Certificate getFdczByid(Map map){
		Certificate cer = certificateDao.queryDomainBykey("Common.getDisByid", map);
		if(cer == null){
			
			throw new BusinessException("������Ϣ:"+new Exception().getMessage());
			
		}
		
		return cer;
	}
	
	/**
	 * 
	 * ���·��ز�֤��Ϣ.
	 * @see com.szhome.cq.business.ICommonFacade#updateCertificate(com.szhome.cq.domain.model.Certificate)
	 */
	@Override
	@Transactional
	public void updateCertificate(Certificate c)
	{		
		try {
			certificateDao.update(c);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("������Ϣ:"+e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * ���淿�ز�֤��Ϣ.
	 * @see com.szhome.cq.business.ICommonFacade#updateCertificate(com.szhome.cq.domain.model.Certificate)
	 */
	@Override
	@Transactional
	public void saveCertificate(Certificate ce)
	{	
		Map m=new HashMap();
		//String bus_id=c.getBus_id();
		//m.put("bus_id",bus_id);
		//Certificate ce= certificateDao.get(c);
		try {
		if(ce.getCertificate_id()==null){
			ce.setCertificate_id(String.valueOf(certificateDao.getSeqId()));
			//ce.setCertificate_id(certificate_id)
			certificateDao.save(ce);
		}
		else{
//			ce.setPrinter(c.getPrinter());
//			ce.setCer_state(c.getCer_state());
//			ce.setPrint_date(c.getPrint_date());
//			ce.setCertificate_type(c.getCertificate_type());
			certificateDao.save(ce);
			
		}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("������Ϣ:"+e.getMessage());
		}
		
	}
	/**
	 * 
	 *  ���������Ϣ.
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
			t.setArrange_time(new Date());
			paperDao.save(t);
		}
		else{
			t.setArrange_id(te.getArrange_id());
			t.setArrange_time(new Date());
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
	 *  ��������ʵ��id��ȡ�����Ϣ.
	 * @see com.szhome.cq.business.ICommonFacade#getFileByid(java.util.Map)
	 */
	@Override
	public Testpaper getFileByid(Map map) {
		
		//  Auto-generated method stub
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
	 *  ����鵵��Ϣ.
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
	 *  ��������ʵ��id��ȡ�鵵��Ϣ.
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
	 *  ���浥����������Ϣ.
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
	 *  ���µ�����������Ϣ.
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
	 *  �����û���ȡ��������Ϣ.
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
     *  ������֤����Ϣ.
     * @see com.szhome.cq.business.ICommonFacade#saveDispatch(com.szhome.cq.domain.model.Certificate)
     */

	@Override
	@Transactional
	public void saveDispatch(Certificate c) {
		
		Map m=new HashMap();
		m.put("busid",c.getBus_id());
		Certificate cer= certificateDao.queryDomainBykey("Certificate.getCertificateByid", m);
		try {
		if(cer==null){
			c.setCertificate_id(certificateDao.getSeqId());
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
     *  ��ȡ����ҵ��Ӽ�������Ϣ����ѡ��.
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
    *  ���ݵǼ����ͣ��ӽӼ��������ñ��л�ȡ��Ϣ.
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
	 * getProMatter:(��ѯҵ�������������ҵ������). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public  List<BusMatter> getProMatter()
	{
		return busMatterDao.queryObjectListByKey("Common.getProMatter", null, BusMatter.class);
	}
	/**
	 * 
	 * getChildMatter:(��ѯ��ҵ�������������ҵ������). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public  List<BusMatter> getChildMatter(String parentid)
	{
		Map map=new HashMap();
		map.put("parentid", parentid);
		return busMatterDao.queryListByKey("Common.getProMatter", " where Parent_id=:parentid order by sort asc", map);
	}
	/**
	 * 
	 * getMatter:(ҵ��������ϸ). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public  BusMatter getMatter(String id)
	{
		Map map=new HashMap();
		map.put("id", id);
		return busMatterDao.get("where id=:id", map);//("Common.getProMatter", " where Parent_id=:parentid order by sort asc", map);
	}
	
	/**
	 * 
	 * isChildMatter:(��ѯ�Ƿ�����ӽڵ�). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public  List<BusMatter> isChildMatter(String id)
	{
		Map map=new HashMap();
		map.put("id", id);
		return busMatterDao.queryListByKey("Common.getChildMatter", map);//("Common.getProMatter", " where Parent_id=:parentid order by sort asc", map);
	}
	/**
	 * 
	 * deleteMatter:(ɾ��ҵ������). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public  String deleteMatter(String id)
	{
		BusMatter matter=new BusMatter();
		matter.setId(id);
		String result="0";
		List<BusMatter> listmatter=isChildMatter(id);
		try {
			if(!(listmatter.size()>0))
			{
				busMatterDao.delete(matter);
				result="1";
			}
		} 
		catch (Exception e) {
			
			e.printStackTrace();
			throw new BusinessException("������Ϣ:"+e.getMessage());
		}
		return result;
		//return busMatterDao.jdbcUpdateByKey("Common.getProMatter", map);//(, " where Parent_id=:parentid order by sort asc", map);
	}
	/**
	 * 
	 * updateMatter:(����ҵ������). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public  String updateMatter(BusMatter matter)
	{
		String result="0";
		try {
			busMatterDao.update(matter);
			result="1";
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("������Ϣ:"+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 
	 * saveMatter:(����ҵ�����������ӽڵ�). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	@Override
	@Transactional
	public  BusMatter saveMatter(BusMatter matter)
	{
		Map map=new HashMap();
		map.put("bus_type_id", matter.getParent_id());
		List<BusMatter> ma = busMatterDao.queryListByKey("Common.getProMatter", " where parent_id=:bus_type_id order by bus_type_id desc", map);
		matter.setId(busMatterDao.getSeqId());
		if(ma!=null&&ma.size()>0)
		{
			matter.setBus_type_id(getBusTypeId(ma.get(0).getBus_type_id()));
		}
		else
		{
			matter.setBus_type_id(matter.getParent_id()+"01");
		}
		try {
			busMatterDao.save(matter);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("������Ϣ:"+e.getMessage());
		}
		return matter;
	}
	/*
	 * �Զ�����bustypeid����������ҵ���������ڵ�
	 */
	private String getBusTypeId(String typeid)
	{
		typeid="1"+typeid;
		int num=Integer.valueOf(typeid).intValue();
		num=num+1;
		String returnStr=String.valueOf(num);
		returnStr=returnStr.substring(1);
		typeid.subSequence(1,typeid.length() -1);//(typeid.length() -1, typeid.length()-1);
		return returnStr;
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
		return procNodeDao.queryListByKey("Common.getProcNodeById", " where b.BUS_TYPE_ID=:parentid) order by p.order_id asc", map);
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
		else if((bus_typeid!=null||bus_typeid!="")&&(nodeid == null || nodeid.equals("")))
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
     *  ���浥���˵�.
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
    *  ���µ����˵�.
    * @see com.szhome.cq.business.ICommonFacade#updateMenu(com.szhome.cq.domain.model.Tree)
    */
	@Override
	@Transactional
	public void updateMenu(Menu me) {
		
		try {
			menuDao.update(me);
		} catch (Exception e) {
			
			//  Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("������Ϣ:"+e.getMessage());
			
		}
		
	}


   /**
    * 
    *  ɾ�������˵�.
    * @see com.szhome.cq.business.ICommonFacade#delMenu(com.szhome.cq.domain.model.Tree)
    */
	@Override
	@Transactional
	public void delMenu(int id) {
		
	
		try {
			menuDao.delete(new Menu(id));
		} catch (Exception e) {
			
			//  Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("������Ϣ:"+e.getMessage());
			
		}
		
	}


    /**
     * 
     * ��ȡ�˵��б�.
     * @see com.szhome.cq.business.ICommonFacade#getMenuList(int)
     */

	@Override
	public List<Map<String, Object>> getMenuList(String id) {
		
        long parentId = -1;	
		String sParentId = id;
		if (sParentId != null && !sParentId.equals("")) {
			parentId = Long.parseLong(sParentId);
		}
		Map m = new HashMap();
		m.put("id", parentId);
		List<Map<String, Object>> trs = treeDao.queryMapListByKey("Common.getMenuList","where parent_id =:id", m);
		//List trs1 = treeDao.queryListByKey("Common.getMenuList","where parent_id =:id", m);
		System.out.println("trs:"+trs);
		//System.out.println("trs1:"+trs1);
		
		return trs;
	}


    /**
     * 
     *  ��ȡ�˵��ӽڵ�.
     * @see com.szhome.cq.business.ICommonFacade#getMenuChild(int)
     */

	@Override
	public List getMenuChild(int id) {
		
		Map m = new HashMap();
		m.put("id",id);
		List ts = treeDao.queryMapListByKey("Common.getMenuChildList", "where parent_id =:id", m);
		if(ts == null){
			
			throw new BusinessException("������Ϣ:"+new Exception().getMessage());
			
		}
		
		return ts;
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
		
		//  Auto-generated method stub
		
	}




	@Override
	public void delRecMaterial(int id) {
		
		//  Auto-generated method stub
		
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
		Map naturalInfoMap = FacadeFactory.getRegisterFacade().getNaturalInfo(paraMap);
		Date COMPLEION_DATE = (Date)naturalInfoMap.get("COMPLEION_DATE");
		if(COMPLEION_DATE!=null){
			naturalInfoMap.put("COMPLEION_DATE", DateUtils.format(COMPLEION_DATE,"yyyy-MM-dd"));
		}
		
		Map reg_ownershipMap = getReg_ownershipMapByRegCode(paraMap);
		if(reg_ownershipMap != null){
			naturalInfoMap.putAll(reg_ownershipMap);
		}
		resultMap.put("naturalInfo",naturalInfoMap);
		resultMap.put("holders", FacadeFactory.getRegisterFacade().getHolderInfoByRegId(paraMap));
		resultMap.put("regInfo", getBusMainInfoMapByRegCode(paraMap)); 
		
		Map<String,Object> certificateMap = new HashMap<String,Object>();
		try {
			certificateMap = houseDao.queryMapByKey(
					"Common.getCertificateInfoByRegCode", paraMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//�����֤��Ϊ��  ���ȡ��ǰ��½�û���
		if(certificateMap.get("PRINTER") == null){
			certificateMap.put("PRINTER", paraMap.get("userName"));
			certificateMap.put("PRINT_DATE", com.szhome.cq.utils.DateUtils.getCurTimeStr());
		}else{
			Date tempDate = (Date)certificateMap.get("PRINT_DATE");
			certificateMap.put("PRINT_DATE", DateUtils.format(tempDate,"yyyy-MM-dd HH:mm:ss"));
		}
		resultMap.put("certificateInfo", certificateMap);
		return resultMap;
	}

	/**
	 * 
	 * getBusMainInfoMapByRegCode:(ͨ���ǼǱ�� ��ȡҵ��������Ϣ). 
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getBusMainInfoMapByRegCode(Map<String,Object> paraMap){
		Map<String,Object> resultMap = new HashMap();
		resultMap = houseDao.queryMapByKey("Common.getBusMainInfoByRegCode", paraMap);
		return resultMap;
	}

	@Override
	public List<Map<String, Object>> getRegPreCerInfo(String lcslbid) {
		
		//  Auto-generated method stub
		return null;
	}




	@Override
	public void updateRecMaterial(RecMatConfigure rec) {
		
		//  Auto-generated method stub
		
	}




	@Override
	public Map saveRecMat(Map map) {
		
		//  Auto-generated method stub
		return null;
	}








	@Override
	public void saveAnnounce(Announcement a) {
		
		//  Auto-generated method stub
		
	}




	@Override
	public Announcement getAnnounceByid(Map map) {
		
		//  Auto-generated method stub
		return null;
	}




	/**
	 * 
	 *  ͨ������ʵ����ID��ȡҵ��������Ϣ.
	 * @see com.szhome.cq.business.ICommonFacade#getBusinessMainByProcId(java.util.Map)
	 * @exception BusinessException
	 */
	public BusinessMain getBusinessMainByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		return businessMainDao.get("where proc_id=:proc_id",paraMap);
	}
	/**
	 * 
	 * ͨ������ʵ����ID��ȡҵ��������Ϣ.
	 * @see com.szhome.cq.business.ICommonFacade#getBusinessMainByProcId(java.util.Map)
	 * @exception BusinessException
	 */
	public BusinessMain getBusinessMainByBusid(String bus_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id", bus_id);
		return businessMainDao.get("where bus_id=:bus_id",paraMap);
	}
	
	/**
	 * 
	 * getBusMainInfoMapByProcId:(ͨ������ʵ��Id  ��ȡҵ��������Ϣ). 
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getBusMainInfoMapByProcId(String proc_id){
		Map<String,Object> resultMap = new HashMap();
		resultMap.put("proc_id", proc_id);
		String whereSql=" where proc_id=:proc_id";
		resultMap = houseDao.queryMapByKey("Common.getBusMain",whereSql, resultMap);
		return resultMap;
	}




	/**
	 * 
	 * ͨ������ʵ��ID��ȡ������Ϣ ֻ���ڰ��������ҵ��ʱʹ��
	 * @see com.szhome.cq.business.ICommonFacade#getHouseByProcId(java.lang.String)
	 */
	public House getHouseByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		String whereSql = " where ADV_HOUSE_CODE=(select REG_UNIT_CODE from BUS_REGUNITREL where bus_id =(select bus_id from bus_main where proc_id =:proc_id))";
		return houseDao.get(whereSql, paraMap);
	}



	/*
	 * ͨ������ʵ��ID��ȡ¥����Ϣ    ֻ���ڰ��������ҵ��ʱʹ��
	 */
	public Building getBuildingByProcId(String proc_id) throws BusinessException{
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		String whereSql = " where BUILDING_ID=(select BUILDING_ID from est_house where house_id=(select house_id from BUS_REGUNITREL where bus_id =(select bus_id from bus_main where proc_id =:proc_id)))";
		return buildingDao.get(whereSql, paraMap);
	}



	/*
	 * ͨ������ʵ��ID��ȡ¥����Ϣ    ֻ���ڰ��������ҵ��ʱʹ��
	 */
	public Land getLandByProcId(String proc_id) throws BusinessException{
		return null;
	}




	@Override
	public void delComLanguage(int id) {
		
		//  Auto-generated method stub
		
	}

	/**
	 * 
	 * getLandContractInfo:(��ȡ���غ�ͬ��Ϣ).
	 *
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getLandContractInfo()
	{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			resultMap = contractDao.queryMapByKey(
					"Common.getLandContractInfo","");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return resultMap;
	}


	/**
	 * 
	 * getContractInfoByProcId:(ͨ������ʵ��ID��ȡ��ͬ��Ϣ).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getContractInfoByProcId(String proc_id) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		BusinessMain busMain =  getBusinessMainByProcId(proc_id);
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		
		paraMap.put("reg_code", busMain.getReg_code());
		
		Map naturalInfoMap = FacadeFactory.getRegisterFacade().getNaturalInfo(paraMap);
		
		paraMap.put("bus_id", busMain.getBus_id());
		
		//contractDao.get("select * from BDC_CONTRACT_INFO where house_id=(select house_id from bus_regunitrel where bus_id=:bus_id)", paraMap);
		Map contractMap = null;
		try {
			contractMap = contractDao.queryMapByKey(
					"Common.getContractInfoByBusId", paraMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resultMap.putAll(contractMap);
		resultMap.putAll(naturalInfoMap);
		return resultMap;
	}
	
	/**
	 * 
	 * getCertificateByProcId:(ͨ������ʵ��ID��ȡ���ز�֤��Ϣ). 
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Certificate getCertificateByProcId(String proc_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		Certificate certificate = null;
		try {
		   certificate = certificateDao
					.get("where bus_id=(select bus_id from bus_main where proc_id = :proc_id)",
							paraMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return certificate;
	}
	
	/**
	 * 
	 * getBusOwnershipByProcId:(ͨ������ID��ȡ����Ȩ�Ǽ���Ϣ����).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public BusOwnership getBusOwnershipByProcId(String proc_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		BusOwnership busOwnership = null;
		try {
			 busOwnership = busOwnershipDao
					.get("where bus_id=(select bus_id from bus_main where proc_id = :proc_id)",
							paraMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return busOwnership;
	}
	
	/**
	 * 
	 * getReg_ownershipByProcId:(ͨ������ʵ��ID��ȡ�Ǽǲ�����Ȩ������Ϣ). 
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Reg_ownership getReg_ownershipByProcId(String proc_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		Reg_ownership reg_ownership = null;
		try {
			reg_ownership = reg_ownershipDao
					.get("where reg_code=(select reg_code from bus_main where proc_id = :proc_id)",
							paraMap);
		} catch (Exception e) {
			LogUtil.error("CommonFacade.getReg_ownershipByProcId ��ȡ����Ȩ������Ϣ����"+e.getMessage());
		}
		return reg_ownership;
	}
	
	/**
	 * 
	 * getReg_UserightByProcId:(ͨ������ʵ��ID��ȡ�Ǽǲ�ʹ��Ȩ������Ϣ). 
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Reg_Useright getReg_UserightByProcId(String proc_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		Reg_Useright reg_Useright = null;
		try {
			reg_Useright = reg_UserightDao
					.get("where reg_code=(select reg_code from bus_main where proc_id = :proc_id)",
							paraMap);
		} catch (Exception e) {
			LogUtil.error("CommonFacade.getReg_UserightByProcId ��ȡʹ��Ȩ������Ϣ����"+e.getMessage());
		}
		return reg_Useright;
	}
	
	/**
	 * 
	 * getReg_ownershipMapByRegCode:(��ȡ�Ǽǲ�����Ȩ����)
	 *
	 * @author Joyon
	 * @param paraMap reg_code
	 * @return
	 * @since JDK 1.6
	 */
	 
	public Map<String,Object> getReg_ownershipMapByRegCode(Map<String,Object> paraMap){
		Map<String,Object> reg_ownership = null;
		try {
			reg_ownership = reg_ownershipDao.queryMapByKey("Common.getReg_ownershipMapByRegCode",paraMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reg_ownership;
	}
	
	/**
	 * 
	 * getApplicantMapListByProcId:(ͨ������ʵ��ID��ȡ��������Ϣ).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getApplicantMapListByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap();
		paraMap.put("proc_id", proc_id);
		return reg_ownershipDao.queryMapListByKey("Common.getAppcaliant","where bus_id=(select bus_id from bus_main where proc_id=:proc_id)", paraMap);
		
	}
	
	/**
	 * 
	 * getDeclarationFormInfo:(��ȡ˰�ѱ��������Ϣ). 
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getDeclarationFormInfo(String proc_id){
		Map<String,Object> resultMap = new HashMap();
		Map<String,Object> paraMap = new HashMap();
		
		BusinessMain busMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
		
		if(busMain == null){
			return null;
		}
		
		paraMap.put("reg_code",busMain.getReg_code());
		
		Map<String,Object> naturalMap = FacadeFactory.getRegisterFacade().getNaturalInfo(paraMap);
		
		//�����Ȼ��Ϣ
		if(naturalMap!=null){
			resultMap.putAll(naturalMap);
			resultMap.put("naturalInfo", naturalMap);
		}
		
		resultMap.put("REG_CODE", busMain.getReg_code());
		resultMap.put("REG_TYPE", busMain.getReg_type());
		
		
		//��ȡ��ʷȨ����
		List<Map<String,Object>> historyHolderMapList = FacadeFactory.getRegisterFacade().getHistoryHolderMapListByProcId(proc_id);
		
		//�����ʷȨ���ˣ�ת�÷���
		if(historyHolderMapList != null){
			resultMap.put("historyHolderInfoList", historyHolderMapList);
		}
		
		
		List<Map<String,Object>> applicantMapList = getApplicantMapListByProcId(proc_id);
		
		//���������
		if(applicantMapList !=null){
			resultMap.put("applicantInfoList", applicantMapList);
		}
		
		Map<String,Object> declarationFormMap = getDeclarationFormMapByProcId(proc_id);
		if(declarationFormMap == null){
			
			DeclarationForm declarationForm = new DeclarationForm();
			declarationForm.setDec_id(declarationFormDao.getSeqId());
			declarationForm.setBus_id(busMain.getBus_id());
			declarationForm.setParcel_no(naturalMap.get("PARCEL_CODE").toString());
			declarationForm.setBuilding_no(naturalMap.get("BUILDING_NAME").toString()+naturalMap.get("BUILD_NO").toString());
			declarationForm.setUnit_no(naturalMap.get("ROOMNAME").toString());
			declarationForm.setUse(naturalMap.get("FLATLET_USAGE").toString());
			declarationForm.setBuild_area(Double.valueOf(naturalMap.get("BUILD_AREA").toString()));
			declarationForm.setInside_space(Double.valueOf(naturalMap.get("TAONEI_AREA").toString()));
			declarationForm.setLand_location(naturalMap.get("LAND_ADDRESS").toString());
			
			if(historyHolderMapList!=null && !historyHolderMapList.isEmpty()){
				declarationForm.setTransferor_name(historyHolderMapList.get(0).get("HOL_NAME").toString());
				declarationForm.setPayer_type(historyHolderMapList.get(0).get("HOL_TYPE").toString());
				//declarationForm.setTransferor_share(Double.valueOf(historyHolderMapList.get(0).get("PORTION").toString()));
			}
			
			
			declarationForm.setTransferee_name(applicantMapList.get(0).get("APP_NAME").toString());
			//declarationForm.setTransferee_share(Double.valueOf(applicantMapList.get(0).get("APP_PORT").toString()));
			
			declarationForm.setCer_type(applicantMapList.get(0).get("APP_CER_TYPE").toString());
			declarationForm.setCer_no(applicantMapList.get(0).get("APP_CER_NO").toString());
			declarationForm.setTransferee_phonenum(applicantMapList.get(0).get("APP_TEL").toString());
			
			declarationFormDao.save(declarationForm);
			declarationFormMap = getDeclarationFormMapByProcId(proc_id);
			
		}
		
		resultMap.putAll(declarationFormMap);
		
		return resultMap;
	}
	
	public Map<String,Object> getDeclarationFormMapByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap();
		paraMap.put("proc_id", proc_id);
		return reg_ownershipDao.queryMapByKey("Common.getDeclarationForm","where bus_id=(select bus_id from bus_main where proc_id=:proc_id)", paraMap);
		
	}
	
	
	/**
	 * 
	 * sendLandTax:(���͵�˰    �������������ӵ�˰�ӿڻ�ȡ����---��ʱ��List����).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> sendLandTax(String proc_id){
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,Object> tempMap = null;
		for(int i=0;i<3;i++){
			tempMap = new HashMap<String,Object>();
			tempMap.put("p_type", "ת�÷�");
			tempMap.put("p_name", "��ʮ");
			tempMap.put("p_cer_type", "���֤���ڵأ�");
			tempMap.put("p_cer_no", "440301196603052531");
			tempMap.put("p_tax_type", "Ӫҵ˰");
			tempMap.put("p_tax_item", "���˷���ͨס������");
			tempMap.put("p_price", "4213000");
			tempMap.put("p_deducted_price", "2450000");
			tempMap.put("p_tax_rate", "5%");
			tempMap.put("p_tax", "32121");
			tempMap.put("p_deducted_tax", "32121");
			tempMap.put("p_payment_tax", "0.00");
			
			mapList.add(tempMap);
		}
		List<Tax> taxList = new ArrayList<Tax>();
		Tax tempTax = null;
		for(int i=0;i<3;i++){
			tempTax = new Tax();
			
			tempTax.setP_type("ת�÷�");
			tempTax.setP_name("��ʮ");
			tempTax.setP_cer_type("���֤���ڵأ�");
			tempTax.setP_cer_no("440301196603052531");
			tempTax.setP_tax_type("Ӫҵ˰");
			tempTax.setP_tax_item( "���˷���ͨס������");
			tempTax.setP_price("4213000");
			tempTax.setP_deducted_price("2450000");
			tempTax.setP_tax_rate("5%");
			tempTax.setP_tax("32121");
			tempTax.setP_deducted_tax("32121");
			tempTax.setP_payment_tax("0.00");
			
			taxList.add(tempTax);
		}
		
		List<Map> paymentList = new ArrayList<Map>();
		Map tempPayment = null;
		for(int i=0;i<3;i++){
			tempPayment = new HashMap();
			
			tempPayment.put("payment_type", "ת�÷�����������");
			tempPayment.put("currency", "�����");
			tempPayment.put("money", "425.12");
			
			paymentList.add(tempPayment);
		}
		
		resultMap.put("result", mapList);
		resultMap.put("taxList", taxList);
		resultMap.put("paymentList", paymentList);
		return resultMap;
	}
	
	/**
	 * 
	 * getReg_relationshipByProcId:(ͨ������ʵ��ID��ȡ�Ǽǵ�Ԫ����������). 
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Reg_relationship getReg_relationshipByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		return reg_relationshipDao.get("where bus_id=(select bus_id from bus_main where proc_id=:proc_id)", paraMap);
	}
	/**
	 * 
	 * ͨ������ʵ��ID ��ȡ�Ǽǵ�Ԫ���������ݻ� 
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public List<Reg_relationship> getReg_relationshipListByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		return reg_relationshipDao.getAll("where bus_id=(select bus_id from bus_main where proc_id=:proc_id)", paraMap);
	}
	/**
	 * 
	 * getBususerightByProcId:(ͨ������ʵ����ȡʹ��Ȩ�Ǽ���Ϣ).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Bususeright getBususerightByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		return bususerightDao.get("where bus_id=(select bus_id from bus_main where proc_id=:proc_id)", paraMap);
	}
	

	/**
	 * 
	 * saveBusinessMain:(��������ҵ��������Ϣ).
	 *
	 * @author Joyon
	 * @param businessMain
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void saveBusinessMain(BusinessMain businessMain) throws BusinessException{
		BusinessMain dbBusinessMain = null;
		//�ȴ����ݿ���ȡ    ���dbbusinessMaiΪ�����߱���    ��Ϊ�����߸��²���
		dbBusinessMain = businessMainDao.get(businessMain);
		if(dbBusinessMain == null){
			businessMain.setBus_id(businessMainDao.getSeqId());
			businessMainDao.save(businessMain);
		}else{
			businessMainDao.copyProperties(dbBusinessMain, businessMain);
			businessMainDao.update(dbBusinessMain);
		}
	}

	@Override
	public Boolean saveBusType(BusType bt) throws Exception {
		boolean flag = false;
		try {
			bt.setBus_type_id(busTypeDao.getSeqId());
			busTypeDao.save(bt);
			flag = true;
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}
	
	/**
	 * 
	 * ͨ��ҵ������ID  ��ȡҵ��������
	 *
	 * @author Joyon
	 * @param bus_id
	 * @return
	 * @since JDK 1.6
	 */
	public String getBustypenameByBusid(String bus_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id",bus_id);
		String bus_type_name = null;
		try {
			BusType busType = busTypeDao.get(" where bus_type_id = (select reg_type from bus_main where bus_id=:bus_id) ",paraMap);
			
			if(busType!=null){
				bus_type_name = busType.getBus_name();
			}
		} catch (Exception e) {
			LogUtil.error("CommonFacade.getBustypenameByBusid bus_id:"+bus_id +" ��ȡҵ������������:"+e.getMessage());
		}
		return bus_type_name;
	}

	/**
	 * 
	 * ��ȡҵ������
	 * 
	 *
	 * @author Joyon
	 * @param bus_type_id
	 * @return
	 * @since JDK 1.6
	 */
	public BusType getBustypeByBustypeid(String bus_type_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_type_id",bus_type_id);
		BusType busType = busTypeDao.get(" where bus_type_id = :bus_type_id ",paraMap);
		return busType;
	}

	
	
}


