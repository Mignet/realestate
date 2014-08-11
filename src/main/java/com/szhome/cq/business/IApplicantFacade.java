package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.szhome.cq.business.vo.OwnerInfoVo;
import com.szhome.cq.domain.model.Applicant;
import com.szhome.cq.domain.model.BusDistrain;
import com.szhome.cq.domain.model.BusOwnership;
import com.szhome.cq.domain.model.BusRemarkInfo;
import com.szhome.cq.domain.model.BusRevokeapproval;
import com.szhome.cq.domain.model.Busdemurrer;
import com.szhome.cq.domain.model.Bususeright;
import com.szhome.cq.domain.model.House;

/**
 * 业务主表
 * @author panda
 *
 */
public interface IApplicantFacade {
	/**
	 * 
	 * getOnwershipById:根据流程实例id查询申请人信息集合. <br/>
	 *
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */	
	public List<Map<String, Object>> getApptMessByLcslbid(String id);
	
	/**
	 * 
	 * getAppMessById:根据主键id获取单条申请人信息. <br/>
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public Applicant getAppMessById(String id);
	/**
	 * 
	 * getAppMessByIdcard:(根据身份证号查询单条数据). <br/>
	 *
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
 	public Applicant getAppMessByIdcard(String id);
	/**
	 * 
	 * saveApplicant:保存申请人信息. <br/>
	 *
	 * @author PANDA
	 * @param app
	 * @return
	 * @since JDK 1.6
	 */
	public void saveApplicant(Applicant app);
	
	
	   /**
	    * 
	    *  获取撤销核准信息.
	    * 
	    */
	   public BusRevokeapproval getRevokeapproval(String id);
	
	   /**
	    * 
	    *  获取备注登记信息,用于注销.
	    * 
	    */
	public BusRemarkInfo getUnRemark(String reg_code);
	   /**
	    * 
	    *  获取房屋异议登记信息.
	    * 
	    */
	   public BusRemarkInfo getRemark(String id);
	
	
	   /**
	    * 保存撤销核准信息.
	    * @param 
	    */
	   public void saveRevokeapproval(BusRevokeapproval b);
	   
	   /**
	    * 
	    *  获取异议事项.
	    * 
	    */
	   public Busdemurrer getDisItem(String id);
	   
	   /**
	    * 
	    *  获取查封信息，用于解封登记,解除轮候查封.
	    * 
	    */
	   public List<Map<String,Object>> getAttachByRegcode(String reg_code,String attachType);
	   
	   /**
	    * 
	    *  获取查封信息，续封和轮候查封转查封.
	    * 
	    */
	   public Map getAttachByRegUnitcode(String reg_unit_code,String attachType);
	   
	/**
	 * 
	 * updateApplicant:更新申请人信息. <br/>
	 *
	 * @author PANDA
	 * @param app
	 * @return
	 * @since JDK 1.6
	 */
	   /**
	    * 保存房屋异议登记信息.
	    * @param b
	    */
	public void saveDemurrer(Busdemurrer b);
	
	   /**
	    * 保存备注登记信息.
	    * @param b
	    */
	   public void saveRemark(BusRemarkInfo b);
	
	   
		/**
		 * 
		 *  根据传入流程实例ID获取业务主表以及登记单元关联表信息.
		 * @see com.szhome.cq.business.IBussMainFacade#getRegMainById(int)
		 * @author xuzz
		 */
		public Map getRegMainById(String id);
	   
	   
	
	public void updateApplicant(Applicant app);
	/**
	 * 
	 * delApplicant:删除申请人. <br/>
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public void delApplicant(String id);
	   /**
	    * 
	    *  获取房屋异议信息.
	    * 
	    */
	   public Busdemurrer getDemurrer(String id);
	
	/**
	 * 
	 * getOnwershipById:根据流程实例id获取登记信息集合. <br/>
	 *
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public List<OwnerInfoVo> getOnwershipById(String id);
	/**
	 * 
	 * getRegistMessById:根据流程实例id获取登记信息. <br/>
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public Map getRegistMessById(String id);
	/**
	 * 
	 * saveRegistMess:保存登记信息. <br/>
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */	
	public void saveRegistMess(OwnerInfoVo reg);
	
	  /**
	    * 
	    *  保存查封登记信息.
	    * @see com.szhome.cq.business.IApplicantFacade#saveAttach(com.szhome.cq.domain.model.BusOwnership)
	    */
	   public void saveAttach(BusDistrain b);
	
	   /**
	    * 
	    *  获取查封信息.
	    * @see com.szhome.cq.business.IApplicantFacade#getAttach(java.lang.String)
	    */
	   public BusDistrain getAttach(String id);
	/**
	 * 
	 * saveCerRemark:保存房地产证附记. <br/>
	 * @author PANDA
	 * @param o
	 * @since JDK 1.6
	 */
	public void saveCerRemark(OwnerInfoVo o);
	
	/**
	 * 
	 * getProname:根据流程节点获取项目名称. <br/>
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public House  getProname(String id);
	
	/**
	 * 
	 * saveOwnership:保存所有权登记信息. <br/>
	 * @author PANDA
	 * @param b
	 * @since JDK 1.6
	 */
	public void saveOwnership(BusOwnership b);
	
	
	
	  /**
	    * 
	    *  保存使用权登记信息.
	    * @see com.szhome.cq.business.IApplicantFacade#saveOwnership(com.szhome.cq.domain.model.BusOwnership)
	    */
	   public void saveUseright(Bususeright b);
	
	   /**
	    * 
	    *  获取土地使用权信息.
	    * @see com.szhome.cq.business.IApplicantFacade#getBusownership(java.lang.String)
	    */
	   public Bususeright getUseright(String id);
	
	/**
	 * 
	 * getBusownership:获取房屋所有权. <br/>
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public BusOwnership getBusownership(String id);
	
	/**
    * 
    * getApplicantListByProcId:(通过流程实例ID获取申请人List)
    *
    * @author Joyon
    * @param proc_id
    * @return
    * @throws BusinessException
    * @since JDK 1.6
    */
	public List<Applicant> getApplicantListByProcId(String proc_id) throws BusinessException;
	
	/**
	 * 
	 * 通过流程实例ID  和权利人关系获取申请人
	 *
	 * @author Joyon
	 * @param proc_id 流程实例ID
	 * @param hol_rel 权利人关系
	 * @return 申请人list
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public List<Applicant> getApplicantListByProcidAndHolrel(String proc_id,String hol_rel) throws BusinessException;
   
}

