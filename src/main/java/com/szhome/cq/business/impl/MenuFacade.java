package com.szhome.cq.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableMap;
import com.springjdbc.annotation.Page;
import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.IMenuFacade;
import com.szhome.cq.domain.model.RoleRMenu;
import com.szhome.cq.domain.model.TreeMenu;
import com.szhome.cq.utils.DateUtils;
import com.szhome.cq.utils.Util;

/**
 * 菜单facade
 * 
 * @author Mignet
 */
@Component
@Transactional
@Scope("prototype")
public class MenuFacade implements IMenuFacade {

	@Autowired
	private TreeMenu treeDao;
	@Autowired
	private RoleRMenu rmDao;
	
	/**
	 * 
	 * 获取树
	 * @see com.szhome.cq.business.IMenuFacade#listTrees()
	 */
	@Override
	public List<TreeMenu> listTrees() {
		return treeDao.queryObjectListByKey("Menu.allMenus", null, TreeMenu.class);
	}
	/**
	 * 
	 * 获取分页树
	 * @see com.szhome.cq.business.IMenuFacade#listTrees(int, int)
	 */
	@Override
	public Page<TreeMenu> listTrees(int start, int number) {
		return treeDao.getPage(start, number);
	}
	
	/**
	 * 
	 * 通过根节点ID 获取树
	 *
	 * @author Joyon
	 * @param String parent_id ,String key (key 是获取树的sql 例Tree.getTreeByParentId)
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getTreeByParentId(String key,String parent_id){
		if(key == null || key == ""){
			key = "Menu.getMenuByParentId";
		}
		List<Map<String, Object>> listTree = null;
		Map map = new HashMap();
		map.put("parent_id", parent_id);
		try {
			listTree = treeDao.queryMapListByKey(key, map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("获取树失败"+e.getMessage());
		}
		return listTree;
	}
	/**
	 * 
	 * getTreeJson (获取树JSON List  需要在前台用jsonArray转成json格式   参考 TreeAction.getTreeJson() ).
	 * @param   String key 是写好的格式为    :select tree_id id,parent_id,url,tree_name text from tree where parent_id=:parent_id  的sql语句的key   String parent_id:根节点
	 * @see com.szhome.cq.business.IMenuFacade#getTreeJson(java.lang.String, java.lang.String)
	 */
	public List<Map<String, Object>> getTreeChildren(String key,String parent_id){
		//map.put("parent_id", "-1");
		List<Map<String, Object>> listTree = getTreeByParentId(key,parent_id);	//获取根节点下的node
		
		List<Map<String,Object>> returnListTree = new ArrayList<Map<String,Object>>(); 	//要返回的listTree
		
		for(Map tempMap:listTree){	
			String tempNodeId = tempMap.get("ID").toString();
			//判断当前节点是否有子节点  有子节点则继续循环
			if(getTreeByParentId(key,tempNodeId)!=null){
				
				tempMap.put("children",getTreeChildren(key,tempNodeId));
			}
			
			returnListTree.add(tempMap);
		}
		
		
		return returnListTree;
	}
	
	/**
	 * 
	 * getTreeJson (获取树JSON List  需要在前台用jsonArray转成json格式   参考 TreeAction.getTreeJson() ).
	 * @param   String key 是写好的格式为    :select tree_id id,parent_id,url,tree_name text from tree where parent_id=:parent_id  的sql语句的key   String parent_id:根节点
	 * @see com.szhome.cq.business.IMenuFacade#getTreeJson(java.lang.String, java.lang.String)
	 */
	public List<Map<String, Object>> getTreeList(String key,String parent_id){
		Map map = new HashMap();
		map.put("parent_id", parent_id);
		//Map rootTree = treeDao.queryMapByKey("Tree.getTreeByTreeId", map);
		
		List<Map<String, Object>> listTree = null;
		/*
		if(rootTree == null || rootTree.isEmpty()){
			listTree = getTreeChildren(key,parent_id);
		}else{
		
			listTree = new ArrayList<Map<String, Object>>();
			rootTree.put("children",getTreeChildren(key,parent_id));
			listTree.add(rootTree);
		}
		*/
		//listTree = new ArrayList<Map<String, Object>>();
		listTree = getTreeChildren(key,parent_id);
		
		return listTree;
	}

	@Override
	public List<TreeMenu> queryMenusByUserID(String userid) {
		return treeDao.queryListByKey("Menu.getMenusByUserId", ImmutableMap.of("userid",userid));
	}

	@Override
	public List<TreeMenu> queryMenusByRoleID(String roleid) {
		return treeDao.queryListByKey("Menu.getMenusByRoleId", ImmutableMap.of("roleid",roleid));
	}

	@Override
	@Transactional
	public void saveRoleMenus(String roleid, String menuids) {
		//1,删除原有关系
		rmDao.jdbcUpdateByKey("Menu.deleteAllRoleMenus",ImmutableMap.of("roleid",roleid));
		//2,筛选menuid
		// 针对包含一级菜单二级菜单,每次添加到待关联列表
		// 针对二级菜单，父菜单没加入的，添加到待关联列表
		List<String> pMenus = new ArrayList<String>();
		if(Util.notNullEmpty(menuids))
		for(String menuid:menuids.split(",")){
			if(!pMenus.contains(menuid)){
				pMenus.add(menuid);
			}
			String parentId = treeDao.get(new TreeMenu(menuid)).getParent_id();
			if(!pMenus.contains(parentId)&&!"-1".equals(parentId)){
				pMenus.add(parentId);
			}
		}
		//3,绑定角色与菜单
		for(String menuid:pMenus){
			RoleRMenu rm = new RoleRMenu();
			rm.setMenuid(menuid);
			rm.setRoleid(roleid);
			rm.setCreator("999999999");//系统管理员
			rm.setCreatedate(DateUtils.getCurTime());
			rmDao.save(rm);
		}
	}
	@Override
	public boolean delRoleMenus(String roleid) throws Exception {
		boolean flag = false;
		try {
			//删除角色与菜单的关系
			rmDao.jdbcUpdateByKey("Menu.deleteAllRoleMenus",ImmutableMap.of("roleid",roleid));
			flag = true;
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

}

