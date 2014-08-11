/**
 * Project Name:dxtx_re
 * File Name:ICommonFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2013-12-26����2:35:08
 * Copyright (c) 2013, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.szhome.cq.business.vo.Menu;
import com.szhome.cq.domain.model.Announcement;
import com.szhome.cq.domain.model.BusType;
import com.szhome.cq.domain.model.Certificate;
import com.szhome.cq.domain.model.ComLanguage;
import com.szhome.cq.domain.model.ConNodeRelation;
import com.szhome.cq.domain.model.ConOffice;
import com.szhome.cq.domain.model.Pigeonhole;
import com.szhome.cq.domain.model.ProcNode;
import com.szhome.cq.domain.model.RecMatConfigure;
import com.szhome.cq.domain.model.Reg_relationship;
import com.szhome.cq.domain.model.Testpaper;
import com.szhome.security.ext.UserInfo;

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
public interface ICertificateFacade {
	
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
	public Certificate getCertificateByid(Map map);
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
	     * @see com.szhome.cq.business.ICertificateFacade#saveMenu(com.szhome.cq.domain.model.Tree)
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
		 * getCertificateMapByProcId:(ͨ������ʵ��ID��ȡ���ز�֤��ϢMap)
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @throws BusinessException
		 * @since JDK 1.6
		 */
		public Map<String,Object> getCertificateMapByProcId(String proc_id) throws BusinessException;
		
		/**
		 * ���ݱ�Ų�ѯ���ز�������Ϣ.
		 * @see com.szhome.cq.business.ICommonFacade#getFdczByid(java.util.Map)
		 */
		public List<Certificate> getCertificateListByProcId(String proc_id);
		
		/**
		 * 
		 * ����ҵ��id ��ѯ��֤��Ȼ��Ϣ
		 *
		 * @author Joyon
		 * @param bus_id
		 * @return
		 * @since JDK 1.6
		 */
		public List getCertificateNaturalByBusid(String bus_id,UserInfo userInfo);
		

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
		public Certificate getCertificateByRegunitcodeAndBusid(String bus_id,String reg_unit_code);
		
		/**
		 * 
		 * ͨ��ҵ��ID  ��ȡ�Ǽǵ�Ԫ����������
		 *
		 * @author Joyon
		 * @param bus_id
		 * @return  List
		 * @since JDK 1.6
		 */
		public List<Reg_relationship> getReg_relationshipByBusid(String bus_id);
		
		
		/**
		  * 
		  * ��������ҵ��ķ��ز�֤����  ���ǵ��������  
		  *
		  * @author Joyon
		  * @param bus_id
		  * @param excursus
		  * @since JDK 1.6
		  */
		 public void saveorupdateExcursus(String bus_id,String excursus) throws BusinessException;

}


