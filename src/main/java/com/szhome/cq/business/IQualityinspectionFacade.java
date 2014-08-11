
package com.szhome.cq.business;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.springjdbc.annotation.Page;
import com.szhome.commons.exception.GeneralFailureException;
import com.szhome.cq.domain.model.CheckBase;
import com.szhome.cq.domain.model.CheckQualityrec;
import com.szhome.cq.domain.model.CheckQualitytarget;
import com.szhome.security.ext.UserInfo;

/**
 * �����������
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IQualityinspectionFacade {
	/**
	 * 
	 * getTargetInfo:(��ñ���Ŀ�� ����Ŀ�������).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getTargetInfo();
	

	/**
	 * 
	 * getCurMonthBusiness:(��ȡ��ǰ�·����а�����ĵ�ҵ��).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getCurMonthBusiness();
	
	/**
	 * 
	 * saveCheckbase:(������������Ϣ).
	 *
	 * @author Joyon
	 * @since JDK 1.6
	 */
	public void saveChecksample(List<Map<String,Object>> paraList) throws BusinessException;
	
	/**
	 * 
	 * getUncheckSample:(��ȡδ��������).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getUncheckSample(Map paraMap);
	
	/**
	 * 
	 * startInspection:(�����������   ).
	 *
	 * @author Joyon
	 * @param userInfo
	 * @param list
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> startInspection(UserInfo userInfo,List<Map<String,Object>> list);
	
	/**
	 * 
	 * getCheckcedbusdata:(ͨ����ǰ������� ������ID   ��ȡ�����ҵ�����������). 
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getCheckcedbusdataByProcid(String proc_id,String state);
	
	
	/**
	 * 
	 * getHistoryProcActivity:(��ȡ��ǰ���� ��ʷ��������̽ڵ�).
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List getHistoryprocactivityByProcid(String proc_id);
	
	/**
	 * 
	 * deleteChkQualitytarget:(ɾ����������ָ����Ϣ). 
	 *
	 * @author Joyon
	 * @param chk
	 * @return
	 * @since JDK 1.6
	 */
	public Map deleteChkQualitytarget(CheckQualitytarget chk) throws BusinessException;
	/**
	 * 
	 * applyChkQualitytargetEdit:(����������ָ��༭�������Ӧ�õ���̨���ݿ�).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map applyChkQualitytargetEdit(Map paraMap) ;
	/**
	 * 
	 * getChkQualitytargetList:(��ȡ��������ָ����ϢList).
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<CheckQualitytarget> getChkQualitytargetList() throws BusinessException;
	
	/**
	 * 
	 * relQualitytarget:(��������ָ����������ټ�¼��). 
	 *@param qua_tar_id ��������ָ����id  type:checked/unchecked  ����/ȡ������  bus_id:����ҵ��Id
	 * @author Joyon
	 * @since JDK 1.6
	 */
	public void relQualitytarget(String qua_tar_id,String type,String bus_id,String activdefId,String activdefName) throws BusinessException;
	/**
	 * 
	 * getCheckQualityrecListByBusid:(ͨ��ҵ��ID��ȡ�������ټ�¼����Ϣ). 
	 *
	 * @author Joyon
	 * @param bus_id
	 * @return
	 * @since JDK 1.6
	 */
	public List<CheckQualityrec> getCheckQualityrecListByBusid(String bus_id,String activdefId);
	
	/**
	 * 
	 * getCheckbusinessuseridByCheckprocid:(ͨ�������������ʵ��ID��ȡ �����ҵ��Ļ��ҵ��id ).
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List getCheckbusinessuseridByCheckprocid(String proc_id);
	
	/**
	 * 
	 * querySamele:(ͨ����ѯ������ѯ).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @throws ParseException 
	 * @since JDK 1.6
	 */
	public List querySamele(Map paraMap) throws ParseException;
	
	/**
	 * 
	 * getCurMonthStatistics:(���������Ǽ�����  ͳ�Ƶ�ǰ�µĳ������).
	 * @author Joyon
	 * @param reg_station 
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getCurMonthStatistics(String start_date,String end_date, String reg_station);
	
	/**
	 * 
	 * getCurMonthStatistics:(��������ȡϸ����ÿ���  ���ͳ�Ʊ��� ����).
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getDaydetailStatistics(Map<String,Object> paraMap);
	
	
	/**
	 * 
	 * getRemindersBusiness:(��ȡ��Ҫ�߰��ҵ��).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getRemindersBusiness(Map<String,Object> paraMap);
	
	/**
	 * 
	 * ��ȡ��Ҫ�߰��ҵ��
	 * <b>��ҳ<b>
	 * @author dxtx
	 * @param paraMap
	 * @param pageNo
	 * @param pageSize
	 * @return �߰��б�
	 * @since JDK 1.6
	 */
	public Page<Map<String,Object>> getRemindersBusiness(Map<String,Object> paraMap,int pageNo, int pageSize);
	
	/**
	 * 
	 * reminde:(����  ����һ����Ϣ������ ��������Ϣ���ʼ�).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @throws GeneralFailureException 
	 * @since JDK 1.6
	 */
	public Map<String,Object> reminde(Map paraMap) throws GeneralFailureException;
	
	/**
	 * 
	 * getUrgeStatistics:(ͳ�ƶ�������).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getUrgeStatistics(Map<String,Object> paraMap);
	

	/**
	 * 
	 * changeIserrorstate:(�޸��Ƿ��ǵǼ���״̬).
	 *
	 * @author Joyon
	 * @param bus_id  �����ҵ���ҵ��ID
	 * @param type	   �Ƿ�ѡ��  checked/unchecked	
	 * @param proc_id ���μ�����̵�ID
	 * @since JDK 1.6
	 */
	public void changeIserrorstate(String bus_id,String type,String proc_id) throws BusinessException;
	/**
	 * 
	 * getCheckBaseBybusidandcheckprocid:(ͨ�������ҵ���ҵ��Id   �ͼ�����̵�����ʵ��ID��ȡΩһ�ļ�������Ϣ). 
	 *
	 * @author Joyon
	 * @param bus_id
	 * @param proc_id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public CheckBase getCheckBaseBybusidandcheckprocid(String bus_id,String proc_id) throws BusinessException;
	

	/**
	 * 
	 * calculateTarget:(���������). 
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> calculateTarget(Map<String,Object> paraMap)  throws BusinessException;
	
	/**
	 * 
	 * ͨ��������̵�����ʵ��ID��ȡ�ļ�������Ϣ 
	 *
	 * @author Joyon
	 * @param proc_id �������  ������ʵ��id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public CheckBase getCheckBaseBycheckprocid(String proc_id) throws BusinessException;
	
	/**
	 * 
	 * ���³�������Ϣ��
	 *
	 * @author Joyon
	 * @param checkBase
	 * @since JDK 1.6
	 */
	public void updateCheckBase(CheckBase checkBase) throws BusinessException;
}


