package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.szhome.cq.business.vo.Menu;
import com.szhome.cq.domain.model.BusType;
import com.szhome.cq.domain.model.ComLanguage;
import com.szhome.cq.domain.model.RecMatConfigure;
import com.szhome.cq.domain.model.TreeMenu;

/**
 * 
 * ����ʹ�õ�ICommonFacade
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-21 ����2:35:08 <br/>
 * @author   panda
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IConfigurFacade {
	
	 /**
	   * 
	   * saveComLanguage:���浥����������Ϣ. <br/>
	   * @author PANDA
	   * @param c
	   * @since JDK 1.6
	   */
	  public void saveComLanguage(ComLanguage c);
	  /**
	   * 
	   * updateComLanguage:���µ�����������Ϣ. <br/>
	   * @author PANDA
	   * @param c
	   * @since JDK 1.6
	   */
	  public void updateComLanguage(ComLanguage c);
	  /**
	   * 
	   * delComLanguage:ɾ��������������Ϣ. <br/>
	   * @author PANDA
	   * @param c
	   * @since JDK 1.6
	   */
	  public void delComLanguage(String id);
	  
	  /**
	   * 
	   * getComLanByid:����������ȡģ����Ϣ. <br/>
	   * @author PANDA
	   * @param map
	   * @return
	   * @since JDK 1.6
	   */
	  public List<Map<String, Object>> getComLanByid(Map m);
	  /**
	   * 
	   * getRMaterialCon:�ӽӼ��������ñ��л�ȡ��Ϣ. <br/>
	   * @author PANDA
	   * @param id
	 * @param rec_type_flag 
	   * @return
	   * @since JDK 1.6
	   */	  
	  public List<Map<String, Object>> getMaterialCon(String id, String rec_type_flag);
	  
	  
	  /**
	   * 
	   * saveRecMaterial:���浥���Ӽ��������ñ���Ϣ. <br/>R
	   * @author PANDA
	   * @param rec
	   * @since JDK 1.6
	   */  
	  public void saveRecMaterial(RecMatConfigure rec);	  
	  /**
	   * 
	   * updateRecMaterial:���µ����Ӽ��������ñ���Ϣ. <br/>
	   * @author PANDA
	   * @param rec
	   * @since JDK 1.6
	   */
	  public void updateRecMaterial(RecMatConfigure rec);
	  /**
	   * 
	   * delRecMaterial:ɾ�������Ӽ��������ñ���Ϣ. <br/>
	   * @author PANDA
	   * @param id
	   * @since JDK 1.6
	   */
	  public void delRecMaterial(String id);
	  /**
	     * 
	     * TODO ���浥���˵�.
	     * @see com.szhome.cq.business.ICommonFacade#saveMenu(com.szhome.cq.domain.model.Tree)
	     */
		void saveMenu(TreeMenu me);

		/**
		 * updateMenu:���µ����˵�.. <br/>
		 * @author PANDA
		 * @param me
		 * @since JDK 1.6
		 */
		void updateMenu(TreeMenu me);

		/**
		 * delMenu:ɾ�������˵�.. <br/>
		 *
		 * @author PANDA
		 * @param id
		 * @since JDK 1.6
		 */
		void delMenu(String id);

		/**
		 * getMenuList:��ȡ�˵��б�. <br/>
		 * @author PANDA
		 * @param id
		 * @return
		 * @since JDK 1.6
		 */
		List<Map<String, Object>> getMenuList(String id);
		
		
		/**
	     * 
	     * TODO��ȡ����ҵ�����б�.
	     * @see com.szhome.cq.business.ICommonFacade#getBusProcList(int)
	     */
		public List<Map<String, Object>> getBusProcList(String id);
		
		/**
		 * 
		 * getMenuById:(������һ�仰�����������������). <br/>
		 * @author PANDA
		 * @return
		 * @since JDK 1.6
		 */
		public TreeMenu getMenuById(Map m);

		/**
		 * getMenuChild:��ȡ�˵��ӽڵ�. <br/>
		 *
		 * @author PANDA
		 * @param id
		 * @return
		 * @since JDK 1.6
		 */
		List getMenuChild(String id);
		
		
		/**
		 * 
		 * getBusTypeByName:���ݵǼ����ͻ�ȡ�������id. <br/>
		 * @author PANDA
		 * @param name
		 * @return
		 * @since JDK 1.6
		 */
		public BusType getBusTypeByName(Map m);
		/**
		   * 
		   * getComLan:����ҵ�����ͻ�ȡģ����Ϣ. <br/>
		   * @author PANDA
		   * @param map
		   * @return
		   * @since JDK 1.6
		   */
		  public List<Map<String, Object>> getComLan(Map m);
	
		  /**
			 * 
			 * getCount:��ȡ��id����id���ܼ�¼��. <br/>
			 * @author PANDA
			 * @param id
			 * @return
			 * @since JDK 1.6
			 */
			public String getCount(String id);
		   /**
		    * 	
		    * menuMoveup:�˵�����. <br/>
		    * @author PANDA
		    * @since JDK 1.6
		    */
		   public void menuMoveup(TreeMenu menu);	
		   
		   /**
		    * 
		    * menuMovedown:�˵�����. <br/>
		    * @author PANDA
		    * @since JDK 1.6
		    */
		   public void menuMovedown(TreeMenu menu);
		
			
			
}

