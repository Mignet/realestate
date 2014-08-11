/**
 * Project Name:dxtx_re
 * File Name:ICommonFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2013-12-26下午2:35:08
 * Copyright (c) 2013, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.List;

import com.szhome.cq.business.vo.AnnounceVO;
import com.szhome.cq.business.vo.ExamVo;
import com.szhome.cq.domain.model.Announcement;
import com.szhome.cq.domain.model.ExamFirst;
import com.szhome.cq.domain.model.ExamForth;
import com.szhome.cq.domain.model.ExamSecond;
import com.szhome.cq.domain.model.ExamThird;
import com.szhome.cq.domain.model.Examine;
import com.szhome.cq.domain.model.User;

/**
 * ClassName:ICommonFacade <br/>
 * 公共使用的ICommonFacade
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-12-26 下午2:35:08 <br/>
 * @author   panda
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IExaminationFacade {
	
	
	/**
	 * 
	 *  保存意见.
	 *  @author xuzz
	 * @see com.szhome.cq.business.ICommonFacade#saveExamine(com.szhome.cq.domain.model.Examine)
	 */
	public void saveExamine(Examine ex);
	
	/**
	 * 
	 * saveExaFirst:保存初审意见. <br/>
	 * @author PANDA
	 * @param ex
	 * @since JDK 1.6
	 */
	 public void saveExaFirst(ExamFirst ex);
	 /**
	  * 
	  * saveExaSecond:保存复审意见. <br/>
	  * @author PANDA
	  * @param ex
	  * @since JDK 1.6
	  */
	 
	 public void saveExaSecond(ExamSecond ex);
	 /**
	  * 
	  * saveExaThird:保存审核意见. <br/>
	  * @author PANDA
	  * @param ex
	  * @since JDK 1.6
	  */
	 
	 public void saveExaThird(ExamThird ex);
	 /**
	  * 
	  * saveExaForth:保存核准意见. <br/>
	  * @author PANDA
	  * @param ex
	  * @since JDK 1.6
	  */
	 
	 public void saveExaForth(ExamForth ex);
	 
		/**
		 * getExamineById 根据业务ID查询审批意见
		 * @author xuzz
		 * @param busid
		 * @return
		 */
		public List<Examine> getExamineById(String busid);
	 
	 /**
	  * 
	  * getExamByid:根据流程实例id获取审批表信息. <br/>
	  * @author PANDA
	  * @param map
	  * @return
	  * @since JDK 1.6
	  */	 
	  public ExamVo getExamByid(String id);
	  
	  /**
	   * 
	   * getRegid:根据流程节点获取登记编号. <br/>
	   * @author PANDA
	   * @param map
	   * @return
	   * @since JDK 1.6
	   */
	  public String getRegid(String id);
	  /**
	   * 
	   * getAnnounceByid:根据流程id获取公告及公告审核意见信息. <br/>
	   * @author PANDA
	   * @param id
	   * @return
	   * @since JDK 1.6
	   */
	  public AnnounceVO getAnnounceByid(String id);
	  
	  
	  /**
		 * 
		 * saveAnnounce:保存公告拟定信息. <br/>
		 * @author PANDA
		 * @param ex
		 * @since JDK 1.6
		 */
		 public void saveAnnounce(Announcement an);
		 
		 /**
		  * 
		  * saveAnnouncePub:保存公告发布信息. <br/>
		  * @author PANDA
		  * @param an
		  * @since JDK 1.6
		  */
		 public void saveAnnouncePub(Announcement an);
		 /**
		  * 
		  * getUserInfo:根据用户id获取用户信息. <br/>
		  * @author PANDA
		  * @return
		  * @since JDK 1.6
		  */
		 public User getUserInfo(double id);
		 
	  
		 /**
			 * 
			 * 通过业务ID 和意见类型获取意见
			 *
			 * @author Joyon
			 * @param bus_id
			 * @param opinion_type
			 * @return
			 * @since JDK 1.6
			 */
		public Examine getExamineBybusidAndOpiniontype(String bus_id,String opinion_type) throws BusinessException;
}


