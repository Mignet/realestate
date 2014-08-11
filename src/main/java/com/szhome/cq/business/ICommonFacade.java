/**
 * Project Name:dxtx_re
 * File Name:ICommonFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2013-12-26����2:35:08
 * Copyright (c) 2013, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.vo.Menu;
import com.szhome.cq.domain.model.Announcement;
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
import com.szhome.cq.domain.model.House;
import com.szhome.cq.domain.model.Land;
import com.szhome.cq.domain.model.Pigeonhole;
import com.szhome.cq.domain.model.ProcNode;
import com.szhome.cq.domain.model.RecMatConfigure;
import com.szhome.cq.domain.model.Reg_Useright;
import com.szhome.cq.domain.model.Reg_ownership;
import com.szhome.cq.domain.model.Reg_relationship;
import com.szhome.cq.domain.model.Testpaper;

/**
 * ClassName:ICommonFacade <br/>
 * ����ʹ�õ�ICommonFacade
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-12-26 ����2:35:08 <br/>
 * @author   xuzz
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface ICommonFacade {
	
	/**
	 * 
	 * getInitInfo:(��ʼ����֤����). <br/>
	 *
	 * @author xuzz
	 * @param map
	 * @return
	 * @since JDK 1.6
	 */
	public Map getInitInfo(Map map);
	
	/**
	 * 
	 * getBuildById:��������ʾ��id��ȡ��������Ϣ. <br/>
	 *
	 * @author PANDA
	 * @param map
	 * @return
	 * @since JDK 1.6
	 */
	public List getBuildById(Map map);
	/**
	 * 
	 * getHouseById:��������ʵ��id��ȡ������Ϣ. <br/>
	 * @author PANDA
	 * @param map
	 * @return
	 * @since JDK 1.6
	 */
	public List getHouseById(Map map);
	/**
	 * 
	 * getChildMatter:(��ѯ��ҵ�������������ҵ������). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public  List<BusMatter> getChildMatter(String parentid);
	/**
	 * 
	 * updateMatter:(����ҵ������). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public  String updateMatter(BusMatter matter);
	/**
	 * 
	 * deleteMatter:(ɾ��ҵ������). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public  String deleteMatter(String id);
	/**
	 * 
	 * getMatter:(ҵ��������ϸ). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public  BusMatter getMatter(String id);
	/**
	 * 
	 * isChildMatter:(��ѯ�Ƿ�����ӽڵ�). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public  List<BusMatter> isChildMatter(String id);
	
	/**
	 * 
	 * getProMatter:(��ѯҵ�������������ҵ������). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public  List<BusMatter> getProMatter();
	
	/**
	 * 
	 * saveMatter:(����ҵ�����������ӽڵ�). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public  BusMatter saveMatter(BusMatter matter);
	
	/**
	 * 
	 * getLandById:��������ʵ��id��ȡ�ڵ���Ϣ. <br/>
	 * @author PANDA
	 * @param map
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getLandById(Map map);
	/**
	 * 
	 * getFdczByid:��������ʵ��id��ȡ���ز�֤��Ϣ. <br/>
	 * @author PANDA
	 * @param map
	 * @return
	 * @since JDK 1.6
	 */
	public Certificate getFdczByid(Map map);
	/**
	 * 
	 * updateCertificate:���·��ز�֤��Ϣ. <br/>
	 *
	 * @author PANDA
	 * @param c
	 * @since JDK 1.6
	 */
	public void updateCertificate(Certificate c);
	/**
	 * 
	 * saveCertificate:���淿�ز�֤��Ϣ. <br/>
	 * @author PANDA
	 * @param c
	 * @since JDK 1.6
	 */
	public void saveCertificate(Certificate c);
	/**
	 * 
	 * saveTestpaper:���������Ϣ. <br/>
	 * @author PANDA
	 * @param t
	 * @since JDK 1.6
	 */
	 public void saveFileMess(Testpaper t);
	 /**
	  * 
	  * getFileByid:��������ʵ��id��ȡ�����Ϣ. <br/>
	  * @author PANDA
	  * @param map
	  * @return
	  * @since JDK 1.6
	  */
	 public Testpaper getFileByid(Map map);
	 /**
	  * 
	  * savePigeonhole:����鵵��Ϣ. <br/>
	  * @author PANDA
	  * @param p
	  * @since JDK 1.6
	  */ 
	 public void savePigeoMess(Pigeonhole p);
	 /**
	  * 
	  * getPigeByid:��������ʵ��id��ȡ�鵵��Ϣ. <br/>
	  * @author PANDA
	  * @param map
	  * @return
	  * @since JDK 1.6
	  */
	 
	 public Map getPigeByid(Map map);
	  /**
	   * 
	   * saveComLanguage:���浥����������Ϣ. <br/>
	   * @author PANDA
	   * @param c
	   * @since JDK 1.6
	   */
	  public void saveComLanguage(ComLanguage c);
	  /**
	   * 
	   * updateComLanguage:���µ�����������Ϣ. <br/>
	   * @author PANDA
	   * @param c
	   * @since JDK 1.6
	   */
	  public void updateComLanguage(ComLanguage c);
	  /**
	   * 
	   * delComLanguage:ɾ��������������Ϣ. <br/>
	   * @author PANDA
	   * @param c
	   * @since JDK 1.6
	   */
	  public void delComLanguage(int id);
	  
	  /**
	   * 
	   * getComLanByid:��ȡ��������Ϣ. <br/>
	   * @author PANDA
	   * @param map
	   * @return
	   * @since JDK 1.6
	   */
	  public List<Map<String, Object>> getComLanByid();
	  /**
	   * 
	   * saveAnnounce:���湫��򹫸�������Ϣ. <br/>
	   * @author PANDA
	   * @param a
	   * @since JDK 1.6
	   */
	  public void saveAnnounce(Announcement a);
	 
	  /**
	   * 
	   * getAnnounceByid:��ȡ����򹫸�������Ϣ. <br/>
	   * @author PANDA
	   * @param map
	   * @return
	   * @since JDK 1.6
	   */  
	  public Announcement getAnnounceByid(Map map);
	  
	  public List<Map<String, Object>>  getRegPreCerInfo(String lcslbid);
	  
	  
	 /**
	  * 
	  * saveDispatch:���淢֤��Ϣ. <br/>
	  * @author PANDA
	  * @param a
	  * @since JDK 1.6
	  */
	  public void saveDispatch(Certificate  c);
	  
	  /**
	   * 
	   * getRegtMessByLcslbid:��֤���棺��������ʵ��id��ȡ�Ǽ���Ϣ. <br/>
	   * @author PANDA
	   * @param id
	   * @return
	   * @since JDK 1.6
	   */
	  
	  /**
	   * 
	   * getRecMaterial:�ӽӼ����ϱ��л�ȡ�Ӽ�������Ϣ. <br/>
	   * @author PANDA
	   * @param id
	   * @return
	   * @since JDK 1.6
	   */
	  public List<Map<String, Object>> getRecMaterial(int id);
	  /**
	   * 
	   * getRMaterialCon:�ӽӼ��������ñ��л�ȡ��Ϣ. <br/>
	   * @author PANDA
	   * @param id
	   * @return
	   * @since JDK 1.6
	   */	  
	  public List<Map<String, Object>> getMaterialCon(int id);
	  
	  
	  /**
	   * 
	   * saveRecMaterial:���浥���Ӽ��������ñ���Ϣ. <br/>
	   * @author PANDA
	   * @param rec
	   * @since JDK 1.6
	   */  
	  public void saveRecMaterial(RecMatConfigure rec);	  
	  /**
	   * 
	   * updateRecMaterial:���µ����Ӽ��������ñ���Ϣ. <br/>
	   * @author PANDA
	   * @param rec
	   * @since JDK 1.6
	   */
	  public void updateRecMaterial(RecMatConfigure rec);
	  /**
	   * 
	   * delRecMaterial:ɾ�������Ӽ��������ñ���Ϣ. <br/>
	   * @author PANDA
	   * @param id
	   * @since JDK 1.6
	   */
	  public void delRecMaterial(int id);
	  

		/**
		 * 
		 * getProcName:(��ѯ���̶����Լ����̶��壬�������ñ�����������). <br/>
		 * @author xuzz
		 * @return
		 * @since JDK 1.6
		 */
	  public  List<BusType> getProcName();
		/**
		 * 
		 * getProcName:(��ѯ���̶����Լ����̶���ڵ㣬�������ñ�����������). <br/>
		 * @author xuzz
		 * @return
		 * @since JDK 1.6
		 */
	  public  List<ProcNode> getProcNode();
	  /**
	   * 
	   * saveRecMat:������ҵ����صĽӼ����ϱ���Ϣ. <br/>
	   * @author PANDA
	   * @param map
	   * @return
	   * @since JDK 1.6
	   */
	  public Map saveRecMat(Map map) ;

	  
		/**
		 * 
		 * getFormById:(�������̶���ڵ��ѯ���������б�). <br/>
		 * @author xuzz
		 * @param nodeid
		 * @return
		 * @since JDK 1.6
		 */
		public  List<Map<String,Object>> getFormById(String nodeid,String bus_typeid);


	    /**
	     * 
	     * TODO ���浥���˵�.
	     * @see com.szhome.cq.business.ICommonFacade#saveMenu(com.szhome.cq.domain.model.Tree)
	     */
		void saveMenu(Menu me);

		/**
		 * updateMenu:���µ����˵�.. <br/>
		 * @author PANDA
		 * @param me
		 * @since JDK 1.6
		 */
		void updateMenu(Menu me);

		/**
		 * delMenu:ɾ�������˵�.. <br/>
		 *
		 * @author PANDA
		 * @param id
		 * @since JDK 1.6
		 */
		void delMenu(int id);

		/**
		 * getMenuList:��ȡ�˵��б�. <br/>
		 * @author PANDA
		 * @param id
		 * @return
		 * @since JDK 1.6
		 */
		List<Map<String, Object>> getMenuList(String id);

		/**
		 * getMenuChild:��ȡ�˵��ӽڵ�. <br/>
		 *
		 * @author PANDA
		 * @param id
		 * @return
		 * @since JDK 1.6
		 */
		List getMenuChild(int id);
		
		/**
		 * 
		 * insertNode:(�����Ƿ�ѡ�нڵ��ϵı�). <br/>
		 * @author xuzz
		 * @param c
		 * @param bustypeid
		 * @since JDK 1.6
		 */
		public void insertNode(ConNodeRelation c,String bustypeid);
		/**
		 * 
		 * selectNode:(��ѯ�Ƿ�ѡ�нڵ��ϵı�). <br/>
		 * @author xuzz
		 * @param c
		 * @param bustypeid
		 * @since JDK 1.6
		 */
		public int selectNode(ConNodeRelation c,String bustypeid);
		/**
		 * 
		 * selectNode:(ɾ���Ƿ�ѡ�нڵ��ϵı�). <br/>
		 * @author xuzz
		 * @param c
		 * @param bustypeid
		 * @since JDK 1.6
		 */
		public void deleteNode(ConNodeRelation c,String bustypeid);
		
		/**
		 * 
		 * deleteForm:(ɾ��������). <br/>
		 * @author xuzz
		 * @param con
		 * @since JDK 1.6
		 */
		public void deleteForm(String officeid);
		/**
		 * 
		 * updateForm:(���±�����). <br/>
		 * @author xuzz
		 * @param con
		 * @since JDK 1.6
		 */
		public void updateForm(ConOffice con);
		/**
		 * 
		 * saveForm:(���������). <br/>
		 * @author xuzz
		 * @param con
		 * @since JDK 1.6
		 */
		public void saveForm(ConOffice con);
		
		/**
		 * 
		 * getProcName:(��ѯ���̶����Լ����̶���ڵ㣬�������ñ�����������). <br/>
		 * @author xuzz
		 * @return
		 * @since JDK 1.6
		 */
		public  List<ProcNode> getProcNodeById(String parentid);
		
		/**
		 *
		 * getBusIdByRegCode:(ͨ���ǼǱ�Ż�ȡҵ������ID).
		 *
		 * @author Joyon
		 * @param regCode
		 * @return
		 * @since JDK 1.6
		 */
		public String getBusIdByRegCode(String regCode);
		
		/**
		 * 
		 * getCertificateInfo:(��ȡ��֤ҳ����Ϣ).
		 *
		 * @author Joyon
		 * @param paraMap reg_code
		 * @return
		 * @since JDK 1.6
		 */
		public Map<String, Object> getCertificateInfo(Map<String, Object> paraMap);
		
		/**
		 * 
		 * getBusinessMainByProcId:(ͨ������ʵ��ID��ȡҵ��������Ϣ)
		 *
		 * @author Joyon
		 * @param paraMap
		 * @return
		 * @since JDK 1.6
		 */
		public BusinessMain getBusinessMainByProcId(String proc_id);
		
		/**
		 * 
		 * getHouseByProcId:(ͨ������ʵ��ID ��ȡ������Ϣ ֻ���ڰ��������ҵ��ʱʹ��).
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public House getHouseByProcId(String proc_id);
		
		/**
		 * 
		 * getBuildingByProcId:(ͨ������ʵ��ID ��ȡ¥����Ϣ   ֻ���ڰ��������ҵ��ʱʹ��).
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public Building getBuildingByProcId(String proc_id);
		
		
		/**
		 * 
		 * getLandByProcId:(ͨ������ʵ��ID ��ȡ�ڵ���Ϣ). 
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public Land getLandByProcId(String proc_id);
		
		/**
		 * 
		 * getLandContractInfo:(��ȡ���غ�ͬ��Ϣ).
		 *
		 * @author xuzz
		 * @return
		 * @since JDK 1.6
		 */
		public Map<String,Object> getLandContractInfo();
		
		/**
		 * 
		 * getContractInfoByProcId:(ͨ������ʵ��ID��ȡ��ͬ��Ϣ).
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public Map getContractInfoByProcId(String proc_id);
		
		/**
		 * 
		 * getCertificateByProcId:(ͨ������ʵ��ID��ȡ���ز�֤��Ϣ). 
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public Certificate getCertificateByProcId(String proc_id);
		
		/**
		 * 
		 * getBusOwnershipByProcId:(ͨ������ID��ȡ����Ȩ�Ǽ���Ϣ����).
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public BusOwnership getBusOwnershipByProcId(String proc_id);
		
		/**
		 * 
		 * getReg_ownershipByProcId:(ͨ������ʵ��ID��ȡ�Ǽǲ�����Ȩ������Ϣ). 
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public Reg_ownership getReg_ownershipByProcId(String proc_id);
		
		/**
		 * 
		 * getDeclarationFormInfo:(��ȡ˰�ѱ��������Ϣ). 
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public Map<String,Object> getDeclarationFormInfo(String proc_id);
		
		/**
		 * 
		 * sendLandTax:(���͵�˰    �������������ӵ�˰�ӿڻ�ȡ����---��ʱ��List����).
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public Map<String,Object> sendLandTax(String proc_id);
		
		/**
		 * 
		 * ͨ������ʵ��ID��ȡ�Ǽǵ�Ԫ����������
		 * @author Joyon
		 * @param proc_id  
		 * @return Reg_relationship  ȷ���Ǽǵ�ԪΪһ���������ʹ��   ����Ǽǵ�Ԫ��ʹ��getReg_relationshipListByProcId
		 * @throws BusinessException
		 * @since JDK 1.6
		 */
		public Reg_relationship getReg_relationshipByProcId(String proc_id) throws BusinessException;
		/**
		 * 
		 * ͨ������ʵ��ID ��ȡ�Ǽǵ�Ԫ���������ݻ� 
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @throws BusinessException
		 * @since JDK 1.6
		 */
		public List<Reg_relationship> getReg_relationshipListByProcId(String proc_id) throws BusinessException;
		/**
		 * 
		 * getBusMainInfoMapByProcId:(ͨ������ʵ��Id  ��ȡҵ��������Ϣ). 
		 *
		 * @author Joyon
		 * @param paraMap
		 * @return
		 * @since JDK 1.6
		 */
		public Map<String,Object> getBusMainInfoMapByProcId(String proc_id);
		
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
		public Bususeright getBususerightByProcId(String proc_id) throws BusinessException;
		

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
		public void saveBusinessMain(BusinessMain businessMain) throws BusinessException;
		
		/**
		 * 
		 * getReg_UserightByProcId:(ͨ������ʵ��ID��ȡ�Ǽǲ�ʹ��Ȩ������Ϣ). 
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public Reg_Useright getReg_UserightByProcId(String proc_id);
		/**
		 * 
		 * ͨ������ʵ����ID��ȡҵ��������Ϣ.
		 * @see com.szhome.cq.business.ICommonFacade#getBusinessMainByProcId(java.util.Map)
		 * @exception BusinessException
		 */
		public BusinessMain getBusinessMainByBusid(String bus_id) throws BusinessException;
		
		/**
		 * saveBusType
		 * @param bt
		 * @return
		 * @throws Exception
		 */
		public Boolean saveBusType(BusType bt) throws Exception;

		/**
		 * 
		 * ͨ��ҵ������ID  ��ȡҵ��������
		 *
		 * @author Joyon
		 * @param bus_id
		 * @return
		 * @since JDK 1.6
		 */
		public String getBustypenameByBusid(String bus_id);
		
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
		public BusType getBustypeByBustypeid(String bus_type_id);
}


