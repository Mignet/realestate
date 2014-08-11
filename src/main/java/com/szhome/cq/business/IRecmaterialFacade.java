/**
 * Project Name:dxtx_re
 * File Name:IRecmaterialFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-1-15����9:54:11
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.szhome.cq.domain.model.MaterialReplenish;
import com.szhome.cq.domain.model.RecScanner;
import com.szhome.security.ext.UserInfo;

/**
 * ClassName:IRecmaterialFacade <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-15 ����9:54:11 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IRecmaterialFacade {
	/**
	 * 
	 * getRecmaterialMapListByRegId:(ͨ���ǼǱ�Ż�ȡ��ǰҵ��ĽӼ�����).
	 *
	 * @author Joyon
	 * @param paraMap reg_id
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getRecmaterialMapListByRegId(Map<String,Object> paraMap);
	
	/**
	 * 
	 * applyEdit:(���ֽӼ����ϱ༭�������Ӧ�õ���̨���ݿ�). 
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map applyEdit(Map paraMap);
	
	/**
	 * 
	 * getScannerListMapByProcId:(ͨ������ʵ��ID��ȡ��ǰҵ��ɨ���List).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getScannerListMapByProcId(String proc_id);
	
	/**
	 * 
	 * getRecScanner:(��ȡ����ɨ������). 
	 *
	 * @author Joyon
	 * @param recScanner
	 * @return
	 * @since JDK 1.6
	 */
	public RecScanner getRecScanner(RecScanner recScanner);
	
	/**
	 * 
	 * modifyScanner:(�޸Ĳ���ɨ������). 
	 * @author Joyon
	 * @param recScanner
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object>  modifyRecScanner(RecScanner recScanner);
	
	/**
	 * 
	 * 
	 *
	 * @author Joyon
	 * @param rep_proc_id  ��Ҫ����������ID
	 * @since JDK 1.6
	 */
	public Map<String,Object> startMaterialReplenishProc(String rep_proc_id,UserInfo userInfo) throws BusinessException;
	
	/**
	 * 
	 * ���²���������Ϣ
	 *
	 * @author Joyon
	 * @param materialReplenish
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void updateMaterialReplenish(MaterialReplenish materialReplenish) throws BusinessException;
	

	/**
	 * 
	 * ���油��������Ϣ
	 *
	 * @author Joyon
	 * @param materialReplenish
	 * @since JDK 1.6
	 */
	public void saveMaterialReplenish(MaterialReplenish materialReplenish) throws BusinessException;
	
	
	/**
	 * 
	 * ͨ���ǼǱ�Ż�ȡ��������
	 *
	 * @author Joyon
	 * @param reg_code  ��Ҫ��������ҵ��ĵǼǱ��
	 * @since JDK 1.6
	 */
	public MaterialReplenish getMaterialReplenishByregcode(String reg_code) throws BusinessException;
	
	/**
	 * 
	 * ͨ���ǼǱ�Ż�ȡ��������ID
	 *
	 * @author Sam
	 * @param reg_code  ��Ҫ��������ҵ��ĵǼǱ��
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getMaterialIdListByregcode(Map<String,Object> paramMap) throws BusinessException;
}


