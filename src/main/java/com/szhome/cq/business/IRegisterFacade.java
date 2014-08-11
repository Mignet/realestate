/**
 * Project Name:dxtx_re
 * File Name:IRegisterFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-1-8����11:04:55
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.szhome.cq.domain.model.Reg_Useright;
import com.szhome.cq.domain.model.Reg_ownership;

/**
 * ClassName:IRegisterFacade <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-8 ����11:04:55 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IRegisterFacade {
	/**
	 * 
	 * registerPreview:(�Ǽǲ�Ԥ��). 
	 *
	 * @author Joyon
	 * @param paramMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String, Object> registerPreview(Map<String, Object> paramMap);
	
	/**
	 * 
	 * getOwnershipInfo:(��ȡ����Ȩ��Ϣ).
	 * @author Joyon
	 * @param paraMap reg_id �ǼǱ��
	 * @return
	 * @since JDK 1.6
	 */
	public  Map<String, Object> getOwnershipInfo( Map<String, Object> paraMap);
	
	
	
	/**
	 * 
	 * getregisterInfo:(�Ǽǲ�Ԥ����ͨ��.). <br/>
	 * @author xuzz
	 * @param useMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getregisterInfo(Map<String ,Object> map);
	
	
	/**
	 * 
	 * getuserInfo:(�Ǽǲ�Ԥ��ʹ��Ȩ����.). <br/>
	 * @author xuzz
	 * @param useMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getuserInfo(Map<String ,Object> useMap);
	
	
	/**
	 * 
	 * registerSave:(�Ǽǲ���Ϣ����). 
	 *
	 * @author Joyon
	 * @param paraMap reg_id  �ǼǱ��
	 * @return
	 * @since JDK 1.6
	 */
	public  Map<String, Object> registerSave( Map<String, Object> paraMap);

	/**
	 * 
	 * checkBus:(���ҵ�������Ƿ��������). <br/>
	 * @author xuzz
	 * @since JDK 1.6  
	 */
	public Map checkBusData(String reg_code,String regtype,String bustype);
	
	
	/**
	 * 
	 * getProcid:(��ѯҵ�������̶���ID). <br/>
	 * @author xuzz
	 * @param procid
	 * @return
	 * @since JDK 1.6
	 */
	public String getProcid(String procid);

	
	/**
	 * 
	 * getBusTypeParentNameByRegId:(ͨ���ǼǱ�Ż�ȡ  ҵ�����͵ĸ�����). 
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public String getBusTypeParentNameByRegId(Map<String, Object> paraMap);
	
	
	/**
	 * 
	 * getNaturalInfo:(��ȡ��Ȼ��Ϣ )
	 *
	 * @author Joyon
	 * @param paraMap.put--	reg_code:�ǼǱ��
	 * @return
	 * @since JDK 1.6
	 */
	public  Map<String, Object> getNaturalInfo( Map<String, Object> paraMap);
	
	/**
	 * 
	 * getHolderInfo:(��ȡȨ������Ϣ). 
	 *
	 * @author Joyon
	 * @param paraMap reg_code �ǼǱ��
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getHolderInfoByRegId(Map paraMap);
	
	/**
	 * 
	 * getRegTypeByRegCode:(ͨ���ǼǱ�Ż�ȡ�Ǽ�������). 
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public String getRegTypeByRegCode(Map<String, Object> paraMap);
	
	/**
	 * ��������ʵ��ID ��ȡ��ǰ ҵ��ǰһ��ҵ���Ȩ������Ϣ
	 */
	public List<Map<String,Object>> getHistoryHolderMapListByProcId(String proc_id);
	
	/**
	 * 
	 * saveRegister:(�Ǽǲ�����). <br/>
	 * @author xuzz
	 * @param paraMap
	 * @return
	 * @since JDK 1.6  getuserInfoByRegId
	 */
	public Map<String,Object> saveRegister(Map<String,Object> paraMap);
	
	/**
	 * �ǲ��ɹ������ò�Ȩ״̬Ϊ��Ч
	 * @param paraMap  ��reg_code,reg_unit_code��
	 */
	public void updateRegUnitState(Map paraMap);
	
	/**
	 * 
	 * getMortMess:����bus_id��ȡ��ѺȨ������Ϣ. <br/>
	 * @author PANDA
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getMortMess(Map m);
	
	/**
	 * 
	 * getBusTypeParentIdByRegId:(ͨ���ǼǱ�Ż�ȡ  ҵ�����͵ĸ�����). 
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public String getBusTypeParentIdByRegId(Map<String, Object> paraMap);
	
	/**
	 * 
	 * getLastRegOnwershipMapByProcId:(ͨ������ʵ��ID��ȡ��һ��ҵ��ĵǼǲ�����Ȩ��ϢMap).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getLastRegOnwershipMapByProcId(String proc_id) throws BusinessException;
	
	/**
	 * 
	 * saveHistoryHolderToApp:(����ʷȨ���˱��浽�������). 
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> saveHistoryHolderToApp(String proc_id) throws BusinessException;
	

	/**
	 * 
	 * getEffectiveReg_OwnershipByProcId:(ͨ������ʵ��ID��ȡ��Ч�ĵǼǲ�����Ȩ������Ϣ)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public Reg_ownership getEffectiveReg_OwnershipByProcId(String proc_id) throws BusinessException;
	
	/**
	 * 
	 * getRegUnitRelMapByRegId:(ͨ����ǰ�ǼǱ�� �Ǽǵ�Ԫ������Map).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getRegUnitRelMapByRegId(Map<String,Object> paraMap);

	/**
	 * 
	 * getEffectiveReg_userightMapByProcId:(ͨ������ʵ��ID��ȡ��Ч�ĵǼǲ�ʹ��Ȩ������Ϣ)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getEffectiveReg_userightMapByProcId(String proc_id) throws BusinessException;
	
	/**
	 * 
	 * getEffectiveReg_userightByRegUnitCode:(��ȡ��ǰ�Ǽǵ�Ԫ�������Ч��ʹ��Ȩ����)
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @return Reg_Useright
	 * @since JDK 1.6
	 */
	public Reg_Useright getEffectiveReg_userightByRegUnitCode(String reg_unit_code);
	
	/**
	 * 
	 * getEffictiveHolderMapListByRegUnitCode:(��ȡ��ǰ�Ǽǵ�Ԫ��Ч��Ȩ����).
	 *
	 * @author Joyon
	 * @param reg_unit_code  hol_rel_code(Ȩ���˹�ϵ����code   Ϊ""ʱ����ѯ����Ȩ����   )
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getEffictiveHolderMapListByRegUnitCode(String reg_unit_code,String hol_rel_code);
	
	/**
	 * 
	 * getEffectiveReg_OwnershipMapByProcId:(ͨ������ʵ��ID��ȡ��Ч�ĵǼǲ�����Ȩ������Ϣ)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getEffectiveReg_OwnershipMapByProcId(String proc_id); 
	
	/**
	 * 
	 * getEffectiveReg_OwnershipByRegUnitCode:(��ȡ��ǰ�Ǽǵ�Ԫ�������Ч��ʹ��Ȩ����)
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @return Reg_Ownership
	 * @since JDK 1.6
	 */
	public Reg_ownership getEffectiveReg_OwnershipByRegUnitCode(String reg_unit_code);
	
	
	/**
	 * 
	 * getRegBookInfoForMort:��Ѻ�Ǽǲ�Ԥ��ʱ����ȡҳ��������ṹ <br/>
	 * @author PANDA
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */	
	public List<Map<String,Object>> getRegBookTreeForMort(Map m);
	
	/**
	 * 
	 * isRegisterSave:(�ж��Ƿ��Ѿ��ǲ�     �Ѿ��ǲ�����true   δ�ǲ�����false  ������ֻ����д else if�е��ж�  ����).
	 *
	 * @author Joyon
	 * @param paraMap reg_code �ǼǱ��  
	 * @return
	 * @since JDK 1.6
	 */
	public boolean isRegisterSave(Map paraMap);
	
	/**
	 * 
	 * getNaturalInfoMapByRegUnitCode:(���ݵǼǵ�Ԫ��Ż�ȡ��Ȼ��Ϣ )
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @return ��Ȼ��ϢMap
	 * @since JDK 1.6
	 */
	public  Map<String, Object> getNaturalInfoMapByRegUnitCode(String reg_unit_code);
	
	/**
	 * 
	 * getEffectiveCerNoByRegUnitCode:(��ȡ��ǰ�Ǽǵ�Ԫ�������Ч�ķ��ز�֤��)
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @return String  cer_no
	 * @since JDK 1.6
	 */
	public String getEffectiveCerNoByRegUnitCode(String reg_unit_code) throws BusinessException;
	
	/**
	 * ��������ʵ��ID ��ȡ��ǰҵ���Ȩ������Ϣ
	 */
	public List<Map<String,Object>> getCurrentHolderMapListByProcId(Map<String,Object> params);
}


