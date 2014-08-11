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
 * 公共使用的ICommonFacade
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-21 下午2:35:08 <br/>
 * @author   panda
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IConfigurFacade {
	
	 /**
	   * 
	   * saveComLanguage:保存单条常用语信息. <br/>
	   * @author PANDA
	   * @param c
	   * @since JDK 1.6
	   */
	  public void saveComLanguage(ComLanguage c);
	  /**
	   * 
	   * updateComLanguage:更新单条常用语信息. <br/>
	   * @author PANDA
	   * @param c
	   * @since JDK 1.6
	   */
	  public void updateComLanguage(ComLanguage c);
	  /**
	   * 
	   * delComLanguage:删除单条常用语信息. <br/>
	   * @author PANDA
	   * @param c
	   * @since JDK 1.6
	   */
	  public void delComLanguage(String id);
	  
	  /**
	   * 
	   * getComLanByid:根据条件获取模版信息. <br/>
	   * @author PANDA
	   * @param map
	   * @return
	   * @since JDK 1.6
	   */
	  public List<Map<String, Object>> getComLanByid(Map m);
	  /**
	   * 
	   * getRMaterialCon:从接件材料配置表中获取信息. <br/>
	   * @author PANDA
	   * @param id
	 * @param rec_type_flag 
	   * @return
	   * @since JDK 1.6
	   */	  
	  public List<Map<String, Object>> getMaterialCon(String id, String rec_type_flag);
	  
	  
	  /**
	   * 
	   * saveRecMaterial:保存单条接件材料配置表信息. <br/>R
	   * @author PANDA
	   * @param rec
	   * @since JDK 1.6
	   */  
	  public void saveRecMaterial(RecMatConfigure rec);	  
	  /**
	   * 
	   * updateRecMaterial:更新单条接件材料配置表信息. <br/>
	   * @author PANDA
	   * @param rec
	   * @since JDK 1.6
	   */
	  public void updateRecMaterial(RecMatConfigure rec);
	  /**
	   * 
	   * delRecMaterial:删除单条接件材料配置表信息. <br/>
	   * @author PANDA
	   * @param id
	   * @since JDK 1.6
	   */
	  public void delRecMaterial(String id);
	  /**
	     * 
	     * TODO 保存单个菜单.
	     * @see com.szhome.cq.business.ICommonFacade#saveMenu(com.szhome.cq.domain.model.Tree)
	     */
		void saveMenu(TreeMenu me);

		/**
		 * updateMenu:更新单个菜单.. <br/>
		 * @author PANDA
		 * @param me
		 * @since JDK 1.6
		 */
		void updateMenu(TreeMenu me);

		/**
		 * delMenu:删除单个菜单.. <br/>
		 *
		 * @author PANDA
		 * @param id
		 * @since JDK 1.6
		 */
		void delMenu(String id);

		/**
		 * getMenuList:获取菜单列表. <br/>
		 * @author PANDA
		 * @param id
		 * @return
		 * @since JDK 1.6
		 */
		List<Map<String, Object>> getMenuList(String id);
		
		
		/**
	     * 
	     * TODO获取本地业务定义列表.
	     * @see com.szhome.cq.business.ICommonFacade#getBusProcList(int)
	     */
		public List<Map<String, Object>> getBusProcList(String id);
		
		/**
		 * 
		 * getMenuById:(这里用一句话描述这个方法的作用). <br/>
		 * @author PANDA
		 * @return
		 * @since JDK 1.6
		 */
		public TreeMenu getMenuById(Map m);

		/**
		 * getMenuChild:获取菜单子节点. <br/>
		 *
		 * @author PANDA
		 * @param id
		 * @return
		 * @since JDK 1.6
		 */
		List getMenuChild(String id);
		
		
		/**
		 * 
		 * getBusTypeByName:根据登记类型获取表格主键id. <br/>
		 * @author PANDA
		 * @param name
		 * @return
		 * @since JDK 1.6
		 */
		public BusType getBusTypeByName(Map m);
		/**
		   * 
		   * getComLan:根据业务类型获取模版信息. <br/>
		   * @author PANDA
		   * @param map
		   * @return
		   * @since JDK 1.6
		   */
		  public List<Map<String, Object>> getComLan(Map m);
	
		  /**
			 * 
			 * getCount:获取父id等于id的总记录数. <br/>
			 * @author PANDA
			 * @param id
			 * @return
			 * @since JDK 1.6
			 */
			public String getCount(String id);
		   /**
		    * 	
		    * menuMoveup:菜单上移. <br/>
		    * @author PANDA
		    * @since JDK 1.6
		    */
		   public void menuMoveup(TreeMenu menu);	
		   
		   /**
		    * 
		    * menuMovedown:菜单下移. <br/>
		    * @author PANDA
		    * @since JDK 1.6
		    */
		   public void menuMovedown(TreeMenu menu);
		
			
			
}

