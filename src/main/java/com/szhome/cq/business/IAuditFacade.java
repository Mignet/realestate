/**
 * Project Name:dxtx_re
 * File Name:IAtditFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2013-12-23下午7:24:06
 * Copyright (c) 2013, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.szhome.cq.business.vo.AuditVo;
import com.szhome.cq.domain.model.AcceptRule;
import com.szhome.process.entity.WorkItem;
import com.szhome.security.ext.UserInfo;

/**
 * ClassName:IAtditFacade <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-12-23 下午7:24:06 <br/>
 * @author   xuzz
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IAuditFacade {

	
	public List<AuditVo> getAuditInfo(Map m);
	public List<Map<String, Object>> checkData(String housecode);
	public Map getHouse(String housecode);
	/**
	 * 
	 * doApply:(受理). <br/>
	 *
	 * @author dxtx
	 * @param serialName
	 * @param procId
	 * @param fwbh
	 * @return
	 * @since JDK 1.6
	 */
	public Map doApply(UserInfo userInfo,String procname,String serialName,String procdefId,List<Map> list);
	
	  /**
	   * 
	   * getFormAndsubmitbtn:(初始化办公页面). <br/>
	   * @author xuzz
	   * @return
	   * @since JDK 1.6
	   */
	  public Map getFormAndsubmitbtn(UserInfo userInfo,Long procdefId,String activdefId);
		/**
		 * 
		 * selectRule:(查询受理规则). <br/>
		 * @author xuzz
		 * @return
		 * @since JDK 1.6
		 */

		public AcceptRule selectRule(AcceptRule acc);
		/**
		 * 
		 * saveDeleteRule:(删除或者增加受理规则). <br/>
		 * @author xuzz
		 * @return
		 * @since JDK 1.6
		 */

		public void saveDeleteRule(AcceptRule acc);
		/**
		 * 
		 * selectRuleByBusType:(根据业务类型查询受理规则). <br/>
		 * @author xuzz
		 * @return
		 * @since JDK 1.6
		 */

		public List<Map<String, Object>> selectRuleByBusType(String bustype);
		
		
		/**
		 * 
		 * TODO 查询宗地表信息.
		 * @see com.szhome.cq.business.IAuditFacade#getAuditInfo(java.util.Map)
		 */
		public List<AuditVo> getLandInfo(Map m);

		
		
		/**
		 * 
		 * TODO 查询建筑物信息.
		 * @see com.szhome.cq.business.IAuditFacade#getBuildInfo(java.util.Map)
		 */
		public List<AuditVo> getBuildInfo(Map m);
		
}


