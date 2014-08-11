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
 * ҵ������
 * @author panda
 *
 */
public interface IApplicantFacade {
	/**
	 * 
	 * getOnwershipById:��������ʵ��id��ѯ��������Ϣ����. <br/>
	 *
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */	
	public List<Map<String, Object>> getApptMessByLcslbid(String id);
	
	/**
	 * 
	 * getAppMessById:��������id��ȡ������������Ϣ. <br/>
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public Applicant getAppMessById(String id);
	/**
	 * 
	 * getAppMessByIdcard:(�������֤�Ų�ѯ��������). <br/>
	 *
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
 	public Applicant getAppMessByIdcard(String id);
	/**
	 * 
	 * saveApplicant:������������Ϣ. <br/>
	 *
	 * @author PANDA
	 * @param app
	 * @return
	 * @since JDK 1.6
	 */
	public void saveApplicant(Applicant app);
	
	
	   /**
	    * 
	    *  ��ȡ������׼��Ϣ.
	    * 
	    */
	   public BusRevokeapproval getRevokeapproval(String id);
	
	   /**
	    * 
	    *  ��ȡ��ע�Ǽ���Ϣ,����ע��.
	    * 
	    */
	public BusRemarkInfo getUnRemark(String reg_code);
	   /**
	    * 
	    *  ��ȡ��������Ǽ���Ϣ.
	    * 
	    */
	   public BusRemarkInfo getRemark(String id);
	
	
	   /**
	    * ���泷����׼��Ϣ.
	    * @param 
	    */
	   public void saveRevokeapproval(BusRevokeapproval b);
	   
	   /**
	    * 
	    *  ��ȡ��������.
	    * 
	    */
	   public Busdemurrer getDisItem(String id);
	   
	   /**
	    * 
	    *  ��ȡ�����Ϣ�����ڽ��Ǽ�,����ֺ���.
	    * 
	    */
	   public List<Map<String,Object>> getAttachByRegcode(String reg_code,String attachType);
	   
	   /**
	    * 
	    *  ��ȡ�����Ϣ��������ֺ���ת���.
	    * 
	    */
	   public Map getAttachByRegUnitcode(String reg_unit_code,String attachType);
	   
	/**
	 * 
	 * updateApplicant:������������Ϣ. <br/>
	 *
	 * @author PANDA
	 * @param app
	 * @return
	 * @since JDK 1.6
	 */
	   /**
	    * ���淿������Ǽ���Ϣ.
	    * @param b
	    */
	public void saveDemurrer(Busdemurrer b);
	
	   /**
	    * ���汸ע�Ǽ���Ϣ.
	    * @param b
	    */
	   public void saveRemark(BusRemarkInfo b);
	
	   
		/**
		 * 
		 *  ���ݴ�������ʵ��ID��ȡҵ�������Լ��Ǽǵ�Ԫ��������Ϣ.
		 * @see com.szhome.cq.business.IBussMainFacade#getRegMainById(int)
		 * @author xuzz
		 */
		public Map getRegMainById(String id);
	   
	   
	
	public void updateApplicant(Applicant app);
	/**
	 * 
	 * delApplicant:ɾ��������. <br/>
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public void delApplicant(String id);
	   /**
	    * 
	    *  ��ȡ����������Ϣ.
	    * 
	    */
	   public Busdemurrer getDemurrer(String id);
	
	/**
	 * 
	 * getOnwershipById:��������ʵ��id��ȡ�Ǽ���Ϣ����. <br/>
	 *
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public List<OwnerInfoVo> getOnwershipById(String id);
	/**
	 * 
	 * getRegistMessById:��������ʵ��id��ȡ�Ǽ���Ϣ. <br/>
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public Map getRegistMessById(String id);
	/**
	 * 
	 * saveRegistMess:����Ǽ���Ϣ. <br/>
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */	
	public void saveRegistMess(OwnerInfoVo reg);
	
	  /**
	    * 
	    *  ������Ǽ���Ϣ.
	    * @see com.szhome.cq.business.IApplicantFacade#saveAttach(com.szhome.cq.domain.model.BusOwnership)
	    */
	   public void saveAttach(BusDistrain b);
	
	   /**
	    * 
	    *  ��ȡ�����Ϣ.
	    * @see com.szhome.cq.business.IApplicantFacade#getAttach(java.lang.String)
	    */
	   public BusDistrain getAttach(String id);
	/**
	 * 
	 * saveCerRemark:���淿�ز�֤����. <br/>
	 * @author PANDA
	 * @param o
	 * @since JDK 1.6
	 */
	public void saveCerRemark(OwnerInfoVo o);
	
	/**
	 * 
	 * getProname:�������̽ڵ��ȡ��Ŀ����. <br/>
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public House  getProname(String id);
	
	/**
	 * 
	 * saveOwnership:��������Ȩ�Ǽ���Ϣ. <br/>
	 * @author PANDA
	 * @param b
	 * @since JDK 1.6
	 */
	public void saveOwnership(BusOwnership b);
	
	
	
	  /**
	    * 
	    *  ����ʹ��Ȩ�Ǽ���Ϣ.
	    * @see com.szhome.cq.business.IApplicantFacade#saveOwnership(com.szhome.cq.domain.model.BusOwnership)
	    */
	   public void saveUseright(Bususeright b);
	
	   /**
	    * 
	    *  ��ȡ����ʹ��Ȩ��Ϣ.
	    * @see com.szhome.cq.business.IApplicantFacade#getBusownership(java.lang.String)
	    */
	   public Bususeright getUseright(String id);
	
	/**
	 * 
	 * getBusownership:��ȡ��������Ȩ. <br/>
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public BusOwnership getBusownership(String id);
	
	/**
    * 
    * getApplicantListByProcId:(ͨ������ʵ��ID��ȡ������List)
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
	 * ͨ������ʵ��ID  ��Ȩ���˹�ϵ��ȡ������
	 *
	 * @author Joyon
	 * @param proc_id ����ʵ��ID
	 * @param hol_rel Ȩ���˹�ϵ
	 * @return ������list
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public List<Applicant> getApplicantListByProcidAndHolrel(String proc_id,String hol_rel) throws BusinessException;
   
}

