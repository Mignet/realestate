package com.szhome.cq.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.IConfigurFacade;
import com.szhome.cq.business.vo.Menu;
import com.szhome.cq.domain.model.BusType;
import com.szhome.cq.domain.model.ComLanguage;
import com.szhome.cq.domain.model.RecMatConfigure;
import com.szhome.cq.domain.model.TreeMenu;

/**
 * 配置信息
 * 
 * @author panda
 */
@Component
@Transactional
@Scope("prototype")
public  class ConfigurFacade implements IConfigurFacade {
	@Autowired
	private ComLanguage comlanDao;//常用语实体
	@Autowired
	private TreeMenu menuDao; //菜单实体
	@Autowired
	private RecMatConfigure recMatConDao;//接件材料配置表实体
	@Autowired
	private BusType bustypeDao;//业务类型实体
	
	/**
	 * 
	 * 保存单条常用语信息.
	 * @see com.szhome.cq.business.ICommonFacade#saveComLanguage(com.szhome.cq.domain.model.ComLanguage)
	 */
	@Override
	@Transactional
	public void saveComLanguage(ComLanguage c) {
		
		Map m=new HashMap();
		try {
		
		//	c.setCyyid(comlanDao.getSeqId());
			c.setLanguage_id(comlanDao.getSeqId());
			comlanDao.save(c);
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("错误信息:"+e.getMessage());
		}
		
	}
	/**
	 * 
	 * 更新单条常用语信息.
	 * @see com.szhome.cq.business.ICommonFacade#updateComLanguage(com.szhome.cq.domain.model.ComLanguage)
	 */
	@Override
	@Transactional
	public void updateComLanguage(ComLanguage c) {
		try {
			comlanDao.update(c);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("错误信息:"+e.getMessage());
		}
	}
	/**
	 * 
	 * 删除单条常用语信息.
	 * @see com.szhome.cq.business.ICommonFacade#delComLanguage(int)
	 */
	@Override
	@Transactional
	public void delComLanguage(String id) {
		try {
			comlanDao.delete(new ComLanguage(id));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("错误信息:"+e.getMessage());
		}
	}
	/**
	 * 
	 * 根据用户获取常用语信息.
	 * @see com.szhome.cq.business.ICommonFacade#getComLanByid(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> getComLanByid(Map m) {
		List<Map<String, Object>> lans = comlanDao.queryMapListByKey("Common.getComLanByid","where bus_type_id=:id and temp_type<>:temptype", m);
		return lans;
	}
	  /**
     * 
     * 保存单个菜单.
     * @see com.szhome.cq.business.ICommonFacade#saveMenu(com.szhome.cq.domain.model.Tree)
     */
	@Override
	@Transactional
	public void saveMenu(TreeMenu me) {
		Map m=new HashMap();
		m.put("menu_name",me.getMenu_name());
		TreeMenu menu = menuDao.get(" where menu_name=:menu_name", m);
		try {
		if(menu ==null ){
			me.setMenu_id(menuDao.getSeqId());
			menuDao.save(me);
		}
		else{
			me.setMenu_id(menu.getMenu_id());
			menuDao.update(me);
			
		}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("错误信息:"+e.getMessage());
		}
	}
	
   /**
    * 
    * 更新单个菜单.
    * @see com.szhome.cq.business.ICommonFacade#updateMenu(com.szhome.cq.domain.model.Tree)
    */
	@Override
	@Transactional
	public void updateMenu(TreeMenu me) {
		try {
			menuDao.update(me);
		} catch (Exception e) {
			
			//  Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("错误信息:"+e.getMessage());
		}
	}
   /**
    * 
    * 删除menu
    * 
    * @see com.szhome.cq.business.ICommonFacade#delMenu(com.szhome.cq.domain.model.Tree)
    */
	@Override
	@Transactional
	public void delMenu(String id) {
		try {
			menuDao.delete(new TreeMenu(id));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("错误信息:"+e.getMessage());
		}
	}
    /**
     * 
     * 获取菜单列表.
     * @see com.szhome.cq.business.ICommonFacade#getMenuList(int)
     */
	@Override
	public List<Map<String, Object>> getMenuList(String id) {
        long parentId = -1;
		String sParentId = id;
		if (sParentId != null && !sParentId.equals("")) {
			parentId = Long.parseLong(sParentId);
		}
		Map m = new HashMap();
		m.put("id", parentId);
		List<Map<String, Object>> trs = menuDao.queryMapListByKey("Common.getMenuList","where parent_id =:id order by menu_order asc", m);
		//List trs1 = treeDao.queryListByKey("Common.getMenuList","where parent_id =:id", m);
		System.out.println("trs:"+trs);	
		return trs;
	}
	
	/**
     * 
     * 获取本地业务定义列表.
     * @see com.szhome.cq.business.ICommonFacade#getBusProcList(int)
     */
	@Override
	public List<Map<String, Object>> getBusProcList(String id) {
        long parentId = -1;	
		String sParentId = id;
		if (sParentId != null && !sParentId.equals("")) {
			parentId = Long.parseLong(sParentId);
		}
		Map m = new HashMap();
		m.put("id", parentId);
		List<Map<String, Object>> trs = menuDao.queryMapListByKey("Common.getBusProcList","where parent_id =:id", m);
		//List trs1 = treeDao.queryListByKey("Common.getMenuList","where parent_id =:id", m);
		System.out.println("trs:"+trs);	
		return trs;
	}
	/**
	 * 
	 * 根据id获取菜单列表.
	 * @see com.szhome.cq.business.IConfigurFacade#getMenuById(java.util.Map)
	 */
	@Override
	public TreeMenu getMenuById(Map m){
		TreeMenu me = menuDao.get(" where menu_name=:menuname",m);
	  if(me == null){
		  System.out.println();
	  }
		return me;
	}
    /**
     * 
     *  获取菜单子节点.
     * @see com.szhome.cq.business.ICommonFacade#getMenuChild(int)
     */
	@Override
	public List getMenuChild(String id) {
		Map m = new HashMap();
		m.put("id",id);
		List ts = menuDao.queryMapListByKey("Common.getMenuChildList", "where parent_id =:id order by menu_order asc", m);
		if(ts == null){
			throw new BusinessException("错误信息:"+new Exception().getMessage());
		}
		return ts;
	}
	/**
	    * 
	    * 根据登记类型，从接件材料配置表中获取信息.
	    * @see com.szhome.cq.business.ICommonFacade#getRMaterialCon(int)
	    */
		@Override
		public List<Map<String, Object>> getMaterialCon(String id,String rec_type_flag) {
			Map m = new HashMap();
			m.put("id", id);
			m.put("rec_type_flag", rec_type_flag);
			List<Map<String, Object>> mats = recMatConDao.queryMapListByKey("Common.getRecmatConByid","where bus_type_id=:id and rec_type_flag=:rec_type_flag", m);
			return mats;
		}
        /**
         * 
         *  保存单条接件材料表配置信息.
         * @see com.szhome.cq.business.IConfigurFacade#saveRecMaterial(com.szhome.cq.domain.model.RecMatConfigure)
         */
		@Override
		@Transactional
		public void saveRecMaterial(RecMatConfigure rec) {
			
			Map m=new HashMap();
			
			try {

				rec.setCfig_receival_id(recMatConDao.getSeqId());
				recMatConDao.save(rec);
			
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("错误信息:"+e.getMessage());
			}
		}
		/**
		 * 
		 * 更新单条接件材料表配置信息..
		 * @see com.szhome.cq.business.IConfigurFacade#updateRecMaterial(com.szhome.cq.domain.model.RecMatConfigure)
		 */
		@Override
		@Transactional
		public void updateRecMaterial(RecMatConfigure rec) {
			try {
				recMatConDao.update(rec);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("错误信息:"+e.getMessage());
			}
			
		}
		/**
		 * 
		 * 删除单条接件材料表配置信息..
		 * @see com.szhome.cq.business.IConfigurFacade#delRecMaterial(int)
		 */
		@Override
		@Transactional
		public void delRecMaterial(String id) {
			
			try {
				recMatConDao.delete(new RecMatConfigure(id));
			} catch (Exception e) {
				
				//  Auto-generated catch block
				e.printStackTrace();
				throw new BusinessException("错误信息:"+e.getMessage());
			}
		}
		/**
		 * 
		 * 获取登记类型相关信息.
		 * @see com.szhome.cq.business.IConfigurFacade#getBusTypeByName(java.util.Map)
		 */
		@Override
		public BusType getBusTypeByName(Map m) {
			BusType bus = bustypeDao.get(" where proc_id=:procid",m);
			if(bus == null){
				System.out.println();
			}
			return bus;
		}
		/**
		 * 
		 * 获取所有意见模版.
		 * @see com.szhome.cq.business.IConfigurFacade#getComLan(java.util.Map)
		 */
		@Override
		public List<Map<String, Object>> getComLan(Map m) {
			
			List<Map<String, Object>> lans = comlanDao.queryMapListByKey("Common.getComLanByid","where bus_type_id=:id", m);
			if(lans == null){
				
				
				System.out.println();
			}
			return lans;
		}
		/**
		 * 
		 * 获取总记录数）.
		 * @see com.szhome.cq.business.IConfigurFacade#getCount(java.lang.String)
		 */
		@Override
		public String getCount(String id) {
			Map m = new HashMap();
			m.put("id", id);			
			String count = menuDao.queryObjectByKey("Common.getMenucount", m, String.class);
			return count;
		}
		/**
		 * 
		 * 菜单上移.
		 * @see com.szhome.cq.business.IConfigurFacade#menuMoveup()
		 */
		@Override
		@Transactional
		public void menuMoveup(TreeMenu me) {
			//获取当前行的顺序号
			String menu_order = me.getMenu_order();
			int neworder = Integer.valueOf(menu_order)-1;
			Map map = new HashMap();
			map.put("id",me.getMenu_id());
			
			TreeMenu tr = menuDao.get(" where menu_id=:id",map);

			//查询上一个菜单信息
			map.put("parent_id", me.getParent_id());
			map.put("menu_order", String.valueOf(neworder));
			TreeMenu tr1 = menuDao.get(" where parent_id=:parent_id and menu_order=:menu_order",map);
			tr.setMenu_order(String.valueOf(neworder));
			tr1.setMenu_order(me.getMenu_order());
			try {
				menuDao.update(tr1);
				menuDao.update(tr);
			} catch (Exception e) {
				
				//  Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		/**
		 * 
		 * 菜单下移.
		 * @see com.szhome.cq.business.IConfigurFacade#menuMovedown()
		 */
		@Override
		@Transactional
		public void menuMovedown(TreeMenu me) {
			String maxorder = getCount(me.getParent_id());
			if(!maxorder.equals(me.getMenu_order())){
				//获取当前行的顺序号
				String menu_order = me.getMenu_order();
				int neworder = Integer.valueOf(menu_order)+1;
				Map map = new HashMap();
				map.put("id",me.getMenu_id());
				
				TreeMenu tr = menuDao.get(" where menu_id=:id",map);

				//查询上一个菜单信息
				map.put("parent_id", me.getParent_id());
				map.put("menu_order", String.valueOf(neworder));
				TreeMenu tr1 = menuDao.get(" where parent_id=:parent_id and menu_order=:menu_order",map);
				tr.setMenu_order(String.valueOf(neworder));
				tr1.setMenu_order(me.getMenu_order());
				try {
					menuDao.update(tr1);
					menuDao.update(tr);
				} catch (Exception e) {
					
					//  Auto-generated catch block
					e.printStackTrace();
					
				}
				
				
				
			}
			
			
		}


}

