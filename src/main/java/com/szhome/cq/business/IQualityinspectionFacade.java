
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
 * 质量督查服务
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IQualityinspectionFacade {
	/**
	 * 
	 * getTargetInfo:(获得本月目标 包括目标完成量).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getTargetInfo();
	

	/**
	 * 
	 * getCurMonthBusiness:(获取当前月份所有办理过的的业务).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getCurMonthBusiness();
	
	/**
	 * 
	 * saveCheckbase:(保存抽检样本信息).
	 *
	 * @author Joyon
	 * @since JDK 1.6
	 */
	public void saveChecksample(List<Map<String,Object>> paraList) throws BusinessException;
	
	/**
	 * 
	 * getUncheckSample:(获取未检查的样本).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getUncheckSample(Map paraMap);
	
	/**
	 * 
	 * startInspection:(启动检查流程   ).
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
	 * getCheckcedbusdata:(通过当前检查流程 的流程ID   获取被检查业务的流程数据). 
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getCheckcedbusdataByProcid(String proc_id,String state);
	
	
	/**
	 * 
	 * getHistoryProcActivity:(获取当前流程 历史办过的流程节点).
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List getHistoryprocactivityByProcid(String proc_id);
	
	/**
	 * 
	 * deleteChkQualitytarget:(删除质量跟踪指标信息). 
	 *
	 * @author Joyon
	 * @param chk
	 * @return
	 * @since JDK 1.6
	 */
	public Map deleteChkQualitytarget(CheckQualitytarget chk) throws BusinessException;
	/**
	 * 
	 * applyChkQualitytargetEdit:(将质量跟踪指标编辑后的数据应用到后台数据库).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map applyChkQualitytargetEdit(Map paraMap) ;
	/**
	 * 
	 * getChkQualitytargetList:(获取质量跟踪指标信息List).
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<CheckQualitytarget> getChkQualitytargetList() throws BusinessException;
	
	/**
	 * 
	 * relQualitytarget:(关联质量指标项到质量跟踪记录表). 
	 *@param qua_tar_id 质量跟踪指标项id  type:checked/unchecked  关联/取消关联  bus_id:关联业务Id
	 * @author Joyon
	 * @since JDK 1.6
	 */
	public void relQualitytarget(String qua_tar_id,String type,String bus_id,String activdefId,String activdefName) throws BusinessException;
	/**
	 * 
	 * getCheckQualityrecListByBusid:(通过业务ID获取质量跟踪记录表信息). 
	 *
	 * @author Joyon
	 * @param bus_id
	 * @return
	 * @since JDK 1.6
	 */
	public List<CheckQualityrec> getCheckQualityrecListByBusid(String bus_id,String activdefId);
	
	/**
	 * 
	 * getCheckbusinessuseridByCheckprocid:(通过质量抽检流程实例ID获取 被检查业务的活动的业务id ).
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List getCheckbusinessuseridByCheckprocid(String proc_id);
	
	/**
	 * 
	 * querySamele:(通过查询条件查询).
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
	 * getCurMonthStatistics:(按部门名登记类型  统计当前月的抽检数据).
	 * @author Joyon
	 * @param reg_station 
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getCurMonthStatistics(String start_date,String end_date, String reg_station);
	
	/**
	 * 
	 * getCurMonthStatistics:(按条件获取细化到每天的  抽检统计报表 数据).
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getDaydetailStatistics(Map<String,Object> paraMap);
	
	
	/**
	 * 
	 * getRemindersBusiness:(获取需要催办的业务).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getRemindersBusiness(Map<String,Object> paraMap);
	
	/**
	 * 
	 * 获取需要催办的业务
	 * <b>分页<b>
	 * @author dxtx
	 * @param paraMap
	 * @param pageNo
	 * @param pageSize
	 * @return 催办列表
	 * @since JDK 1.6
	 */
	public Page<Map<String,Object>> getRemindersBusiness(Map<String,Object> paraMap,int pageNo, int pageSize);
	
	/**
	 * 
	 * reminde:(督办  保存一份信息到本地 并发送信息或邮件).
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
	 * getUrgeStatistics:(统计督办数据).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getUrgeStatistics(Map<String,Object> paraMap);
	

	/**
	 * 
	 * changeIserrorstate:(修改是否是登记文状态).
	 *
	 * @author Joyon
	 * @param bus_id  被检查业务的业务ID
	 * @param type	   是否选中  checked/unchecked	
	 * @param proc_id 当次检查流程的ID
	 * @since JDK 1.6
	 */
	public void changeIserrorstate(String bus_id,String type,String proc_id) throws BusinessException;
	/**
	 * 
	 * getCheckBaseBybusidandcheckprocid:(通过被检查业务的业务Id   和检查流程的流程实例ID获取惟一的检查基本信息). 
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
	 * calculateTarget:(抽检量计算). 
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> calculateTarget(Map<String,Object> paraMap)  throws BusinessException;
	
	/**
	 * 
	 * 通过检查流程的流程实例ID获取的检查基本信息 
	 *
	 * @author Joyon
	 * @param proc_id 检查流程  的流程实例id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public CheckBase getCheckBaseBycheckprocid(String proc_id) throws BusinessException;
	
	/**
	 * 
	 * 更新抽检基本信息表
	 *
	 * @author Joyon
	 * @param checkBase
	 * @since JDK 1.6
	 */
	public void updateCheckBase(CheckBase checkBase) throws BusinessException;
}


