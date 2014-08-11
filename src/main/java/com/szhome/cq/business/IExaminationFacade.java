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
 * ����ʹ�õ�ICommonFacade
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-12-26 ����2:35:08 <br/>
 * @author   panda
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IExaminationFacade {
	
	
	/**
	 * 
	 *  �������.
	 *  @author xuzz
	 * @see com.szhome.cq.business.ICommonFacade#saveExamine(com.szhome.cq.domain.model.Examine)
	 */
	public void saveExamine(Examine ex);
	
	/**
	 * 
	 * saveExaFirst:����������. <br/>
	 * @author PANDA
	 * @param ex
	 * @since JDK 1.6
	 */
	 public void saveExaFirst(ExamFirst ex);
	 /**
	  * 
	  * saveExaSecond:���渴�����. <br/>
	  * @author PANDA
	  * @param ex
	  * @since JDK 1.6
	  */
	 
	 public void saveExaSecond(ExamSecond ex);
	 /**
	  * 
	  * saveExaThird:����������. <br/>
	  * @author PANDA
	  * @param ex
	  * @since JDK 1.6
	  */
	 
	 public void saveExaThird(ExamThird ex);
	 /**
	  * 
	  * saveExaForth:�����׼���. <br/>
	  * @author PANDA
	  * @param ex
	  * @since JDK 1.6
	  */
	 
	 public void saveExaForth(ExamForth ex);
	 
		/**
		 * getExamineById ����ҵ��ID��ѯ�������
		 * @author xuzz
		 * @param busid
		 * @return
		 */
		public List<Examine> getExamineById(String busid);
	 
	 /**
	  * 
	  * getExamByid:��������ʵ��id��ȡ��������Ϣ. <br/>
	  * @author PANDA
	  * @param map
	  * @return
	  * @since JDK 1.6
	  */	 
	  public ExamVo getExamByid(String id);
	  
	  /**
	   * 
	   * getRegid:�������̽ڵ��ȡ�ǼǱ��. <br/>
	   * @author PANDA
	   * @param map
	   * @return
	   * @since JDK 1.6
	   */
	  public String getRegid(String id);
	  /**
	   * 
	   * getAnnounceByid:��������id��ȡ���漰������������Ϣ. <br/>
	   * @author PANDA
	   * @param id
	   * @return
	   * @since JDK 1.6
	   */
	  public AnnounceVO getAnnounceByid(String id);
	  
	  
	  /**
		 * 
		 * saveAnnounce:���湫���ⶨ��Ϣ. <br/>
		 * @author PANDA
		 * @param ex
		 * @since JDK 1.6
		 */
		 public void saveAnnounce(Announcement an);
		 
		 /**
		  * 
		  * saveAnnouncePub:���湫�淢����Ϣ. <br/>
		  * @author PANDA
		  * @param an
		  * @since JDK 1.6
		  */
		 public void saveAnnouncePub(Announcement an);
		 /**
		  * 
		  * getUserInfo:�����û�id��ȡ�û���Ϣ. <br/>
		  * @author PANDA
		  * @return
		  * @since JDK 1.6
		  */
		 public User getUserInfo(double id);
		 
	  
		 /**
			 * 
			 * ͨ��ҵ��ID ��������ͻ�ȡ���
			 *
			 * @author Joyon
			 * @param bus_id
			 * @param opinion_type
			 * @return
			 * @since JDK 1.6
			 */
		public Examine getExamineBybusidAndOpiniontype(String bus_id,String opinion_type) throws BusinessException;
}


