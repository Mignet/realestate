/**
 * Project Name:dxtx_re
 * File Name:ICommonFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2013-12-26下午2:35:08
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
 * 公共使用的ICommonFacade
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-12-26 下午2:35:08 <br/>
 * @author   xuzz
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface ICommonFacade {
	
	/**
	 * 
	 * getInitInfo:(初始化缮证界面). <br/>
	 *
	 * @author xuzz
	 * @param map
	 * @return
	 * @since JDK 1.6
	 */
	public Map getInitInfo(Map map);
	
	/**
	 * 
	 * getBuildById:根据流程示例id获取建筑物信息. <br/>
	 *
	 * @author PANDA
	 * @param map
	 * @return
	 * @since JDK 1.6
	 */
	public List getBuildById(Map map);
	/**
	 * 
	 * getHouseById:根据流程实例id获取房屋信息. <br/>
	 * @author PANDA
	 * @param map
	 * @return
	 * @since JDK 1.6
	 */
	public List getHouseById(Map map);
	/**
	 * 
	 * getChildMatter:(查询子业务事项，用于配置业务事项). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public  List<BusMatter> getChildMatter(String parentid);
	/**
	 * 
	 * updateMatter:(更新业务事项). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public  String updateMatter(BusMatter matter);
	/**
	 * 
	 * deleteMatter:(删除业务事项). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public  String deleteMatter(String id);
	/**
	 * 
	 * getMatter:(业务事项详细). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public  BusMatter getMatter(String id);
	/**
	 * 
	 * isChildMatter:(查询是否存在子节点). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public  List<BusMatter> isChildMatter(String id);
	
	/**
	 * 
	 * getProMatter:(查询业务事项，用于配置业务事项). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public  List<BusMatter> getProMatter();
	
	/**
	 * 
	 * saveMatter:(保存业务事项，用于添加节点). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public  BusMatter saveMatter(BusMatter matter);
	
	/**
	 * 
	 * getLandById:根据流程实例id获取宗地信息. <br/>
	 * @author PANDA
	 * @param map
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getLandById(Map map);
	/**
	 * 
	 * getFdczByid:根据流程实例id获取房地产证信息. <br/>
	 * @author PANDA
	 * @param map
	 * @return
	 * @since JDK 1.6
	 */
	public Certificate getFdczByid(Map map);
	/**
	 * 
	 * updateCertificate:更新房地产证信息. <br/>
	 *
	 * @author PANDA
	 * @param c
	 * @since JDK 1.6
	 */
	public void updateCertificate(Certificate c);
	/**
	 * 
	 * saveCertificate:保存房地产证信息. <br/>
	 * @author PANDA
	 * @param c
	 * @since JDK 1.6
	 */
	public void saveCertificate(Certificate c);
	/**
	 * 
	 * saveTestpaper:保存组卷信息. <br/>
	 * @author PANDA
	 * @param t
	 * @since JDK 1.6
	 */
	 public void saveFileMess(Testpaper t);
	 /**
	  * 
	  * getFileByid:根据流程实例id获取组卷信息. <br/>
	  * @author PANDA
	  * @param map
	  * @return
	  * @since JDK 1.6
	  */
	 public Testpaper getFileByid(Map map);
	 /**
	  * 
	  * savePigeonhole:保存归档信息. <br/>
	  * @author PANDA
	  * @param p
	  * @since JDK 1.6
	  */ 
	 public void savePigeoMess(Pigeonhole p);
	 /**
	  * 
	  * getPigeByid:根据流程实例id获取归档信息. <br/>
	  * @author PANDA
	  * @param map
	  * @return
	  * @since JDK 1.6
	  */
	 
	 public Map getPigeByid(Map map);
	  /**
	   * 
	   * saveComLanguage:保存单条常用语信息. <br/>
	   * @author PANDA
	   * @param c
	   * @since JDK 1.6
	   */
	  public void saveComLanguage(ComLanguage c);
	  /**
	   * 
	   * updateComLanguage:更新单条常用语信息. <br/>
	   * @author PANDA
	   * @param c
	   * @since JDK 1.6
	   */
	  public void updateComLanguage(ComLanguage c);
	  /**
	   * 
	   * delComLanguage:删除单条常用语信息. <br/>
	   * @author PANDA
	   * @param c
	   * @since JDK 1.6
	   */
	  public void delComLanguage(int id);
	  
	  /**
	   * 
	   * getComLanByid:获取常用语信息. <br/>
	   * @author PANDA
	   * @param map
	   * @return
	   * @since JDK 1.6
	   */
	  public List<Map<String, Object>> getComLanByid();
	  /**
	   * 
	   * saveAnnounce:保存公告或公告审批信息. <br/>
	   * @author PANDA
	   * @param a
	   * @since JDK 1.6
	   */
	  public void saveAnnounce(Announcement a);
	 
	  /**
	   * 
	   * getAnnounceByid:获取公告或公告审批信息. <br/>
	   * @author PANDA
	   * @param map
	   * @return
	   * @since JDK 1.6
	   */  
	  public Announcement getAnnounceByid(Map map);
	  
	  public List<Map<String, Object>>  getRegPreCerInfo(String lcslbid);
	  
	  
	 /**
	  * 
	  * saveDispatch:保存发证信息. <br/>
	  * @author PANDA
	  * @param a
	  * @since JDK 1.6
	  */
	  public void saveDispatch(Certificate  c);
	  
	  /**
	   * 
	   * getRegtMessByLcslbid:发证界面：根据流程实例id获取登记信息. <br/>
	   * @author PANDA
	   * @param id
	   * @return
	   * @since JDK 1.6
	   */
	  
	  /**
	   * 
	   * getRecMaterial:从接件材料表中获取接件材料信息. <br/>
	   * @author PANDA
	   * @param id
	   * @return
	   * @since JDK 1.6
	   */
	  public List<Map<String, Object>> getRecMaterial(int id);
	  /**
	   * 
	   * getRMaterialCon:从接件材料配置表中获取信息. <br/>
	   * @author PANDA
	   * @param id
	   * @return
	   * @since JDK 1.6
	   */	  
	  public List<Map<String, Object>> getMaterialCon(int id);
	  
	  
	  /**
	   * 
	   * saveRecMaterial:保存单条接件材料配置表信息. <br/>
	   * @author PANDA
	   * @param rec
	   * @since JDK 1.6
	   */  
	  public void saveRecMaterial(RecMatConfigure rec);	  
	  /**
	   * 
	   * updateRecMaterial:更新单条接件材料配置表信息. <br/>
	   * @author PANDA
	   * @param rec
	   * @since JDK 1.6
	   */
	  public void updateRecMaterial(RecMatConfigure rec);
	  /**
	   * 
	   * delRecMaterial:删除单条接件材料配置表信息. <br/>
	   * @author PANDA
	   * @param id
	   * @since JDK 1.6
	   */
	  public void delRecMaterial(int id);
	  

		/**
		 * 
		 * getProcName:(查询流程定义以及流程定义，用于配置表单及报表配置). <br/>
		 * @author xuzz
		 * @return
		 * @since JDK 1.6
		 */
	  public  List<BusType> getProcName();
		/**
		 * 
		 * getProcName:(查询流程定义以及流程定义节点，用于配置表单及报表配置). <br/>
		 * @author xuzz
		 * @return
		 * @since JDK 1.6
		 */
	  public  List<ProcNode> getProcNode();
	  /**
	   * 
	   * saveRecMat:保存与业务相关的接件材料表信息. <br/>
	   * @author PANDA
	   * @param map
	   * @return
	   * @since JDK 1.6
	   */
	  public Map saveRecMat(Map map) ;

	  
		/**
		 * 
		 * getFormById:(根据流程定义节点查询表单及报表列表). <br/>
		 * @author xuzz
		 * @param nodeid
		 * @return
		 * @since JDK 1.6
		 */
		public  List<Map<String,Object>> getFormById(String nodeid,String bus_typeid);


	    /**
	     * 
	     * TODO 保存单个菜单.
	     * @see com.szhome.cq.business.ICommonFacade#saveMenu(com.szhome.cq.domain.model.Tree)
	     */
		void saveMenu(Menu me);

		/**
		 * updateMenu:更新单个菜单.. <br/>
		 * @author PANDA
		 * @param me
		 * @since JDK 1.6
		 */
		void updateMenu(Menu me);

		/**
		 * delMenu:删除单个菜单.. <br/>
		 *
		 * @author PANDA
		 * @param id
		 * @since JDK 1.6
		 */
		void delMenu(int id);

		/**
		 * getMenuList:获取菜单列表. <br/>
		 * @author PANDA
		 * @param id
		 * @return
		 * @since JDK 1.6
		 */
		List<Map<String, Object>> getMenuList(String id);

		/**
		 * getMenuChild:获取菜单子节点. <br/>
		 *
		 * @author PANDA
		 * @param id
		 * @return
		 * @since JDK 1.6
		 */
		List getMenuChild(int id);
		
		/**
		 * 
		 * insertNode:(插入是否选中节点上的表单). <br/>
		 * @author xuzz
		 * @param c
		 * @param bustypeid
		 * @since JDK 1.6
		 */
		public void insertNode(ConNodeRelation c,String bustypeid);
		/**
		 * 
		 * selectNode:(查询是否选中节点上的表单). <br/>
		 * @author xuzz
		 * @param c
		 * @param bustypeid
		 * @since JDK 1.6
		 */
		public int selectNode(ConNodeRelation c,String bustypeid);
		/**
		 * 
		 * selectNode:(删除是否选中节点上的表单). <br/>
		 * @author xuzz
		 * @param c
		 * @param bustypeid
		 * @since JDK 1.6
		 */
		public void deleteNode(ConNodeRelation c,String bustypeid);
		
		/**
		 * 
		 * deleteForm:(删除表单数据). <br/>
		 * @author xuzz
		 * @param con
		 * @since JDK 1.6
		 */
		public void deleteForm(String officeid);
		/**
		 * 
		 * updateForm:(更新表单数据). <br/>
		 * @author xuzz
		 * @param con
		 * @since JDK 1.6
		 */
		public void updateForm(ConOffice con);
		/**
		 * 
		 * saveForm:(保存表单数据). <br/>
		 * @author xuzz
		 * @param con
		 * @since JDK 1.6
		 */
		public void saveForm(ConOffice con);
		
		/**
		 * 
		 * getProcName:(查询流程定义以及流程定义节点，用于配置表单及报表配置). <br/>
		 * @author xuzz
		 * @return
		 * @since JDK 1.6
		 */
		public  List<ProcNode> getProcNodeById(String parentid);
		
		/**
		 *
		 * getBusIdByRegCode:(通过登记编号获取业务主表ID).
		 *
		 * @author Joyon
		 * @param regCode
		 * @return
		 * @since JDK 1.6
		 */
		public String getBusIdByRegCode(String regCode);
		
		/**
		 * 
		 * getCertificateInfo:(获取缮证页面信息).
		 *
		 * @author Joyon
		 * @param paraMap reg_code
		 * @return
		 * @since JDK 1.6
		 */
		public Map<String, Object> getCertificateInfo(Map<String, Object> paraMap);
		
		/**
		 * 
		 * getBusinessMainByProcId:(通过流程实例ID获取业务主表信息)
		 *
		 * @author Joyon
		 * @param paraMap
		 * @return
		 * @since JDK 1.6
		 */
		public BusinessMain getBusinessMainByProcId(String proc_id);
		
		/**
		 * 
		 * getHouseByProcId:(通过流程实例ID 获取房屋信息 只能在办理房屋相关业务时使用).
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public House getHouseByProcId(String proc_id);
		
		/**
		 * 
		 * getBuildingByProcId:(通过流程实例ID 获取楼宇信息   只能在办理房屋相关业务时使用).
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public Building getBuildingByProcId(String proc_id);
		
		
		/**
		 * 
		 * getLandByProcId:(通过流程实例ID 获取宗地信息). 
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public Land getLandByProcId(String proc_id);
		
		/**
		 * 
		 * getLandContractInfo:(获取土地合同信息).
		 *
		 * @author xuzz
		 * @return
		 * @since JDK 1.6
		 */
		public Map<String,Object> getLandContractInfo();
		
		/**
		 * 
		 * getContractInfoByProcId:(通过流程实例ID获取合同信息).
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public Map getContractInfoByProcId(String proc_id);
		
		/**
		 * 
		 * getCertificateByProcId:(通过流程实例ID获取房地产证信息). 
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public Certificate getCertificateByProcId(String proc_id);
		
		/**
		 * 
		 * getBusOwnershipByProcId:(通过流程ID获取所有权登记信息数据).
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public BusOwnership getBusOwnershipByProcId(String proc_id);
		
		/**
		 * 
		 * getReg_ownershipByProcId:(通过流程实例ID获取登记簿所有权部分信息). 
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public Reg_ownership getReg_ownershipByProcId(String proc_id);
		
		/**
		 * 
		 * getDeclarationFormInfo:(获取税费报表相关信息). 
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public Map<String,Object> getDeclarationFormInfo(String proc_id);
		
		/**
		 * 
		 * sendLandTax:(发送地税    ——————从地税接口获取数据---暂时用List代替).
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public Map<String,Object> sendLandTax(String proc_id);
		
		/**
		 * 
		 * 通过流程实例ID获取登记单元关联表数据
		 * @author Joyon
		 * @param proc_id  
		 * @return Reg_relationship  确定登记单元为一个的情况下使用   多个登记单元则使用getReg_relationshipListByProcId
		 * @throws BusinessException
		 * @since JDK 1.6
		 */
		public Reg_relationship getReg_relationshipByProcId(String proc_id) throws BusinessException;
		/**
		 * 
		 * 通过流程实例ID 获取登记单元关联表数据获 
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @throws BusinessException
		 * @since JDK 1.6
		 */
		public List<Reg_relationship> getReg_relationshipListByProcId(String proc_id) throws BusinessException;
		/**
		 * 
		 * getBusMainInfoMapByProcId:(通过流程实例Id  获取业务主表信息). 
		 *
		 * @author Joyon
		 * @param paraMap
		 * @return
		 * @since JDK 1.6
		 */
		public Map<String,Object> getBusMainInfoMapByProcId(String proc_id);
		
		/**
		 * 
		 * getBususerightByProcId:(通过流程实例获取使用权登记信息).
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
		 * saveBusinessMain:(保存或更新业务主表信息).
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
		 * getReg_UserightByProcId:(通过流程实例ID获取登记簿使用权部分信息). 
		 *
		 * @author Joyon
		 * @param proc_id
		 * @return
		 * @since JDK 1.6
		 */
		public Reg_Useright getReg_UserightByProcId(String proc_id);
		/**
		 * 
		 * 通过流程实例表ID获取业务主表信息.
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
		 * 通过业务类型ID  获取业务类型名
		 *
		 * @author Joyon
		 * @param bus_id
		 * @return
		 * @since JDK 1.6
		 */
		public String getBustypenameByBusid(String bus_id);
		
		/**
		 * 
		 * 获取业务类型
		 * 
		 *
		 * @author Joyon
		 * @param bus_type_id
		 * @return
		 * @since JDK 1.6
		 */
		public BusType getBustypeByBustypeid(String bus_type_id);
}


