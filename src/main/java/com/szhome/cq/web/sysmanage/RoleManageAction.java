package com.szhome.cq.web.sysmanage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.plan.commons.Row;
import com.plan.commons.RowImpl;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IMenuFacade;
import com.szhome.cq.business.IRoleManageFacade;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.Role;
import com.szhome.cq.domain.model.TreeMenu;
import com.szhome.cq.utils.DateUtils;
import com.szhome.cq.utils.JsonUtil;
import com.szhome.cq.utils.Util;
import com.szhome.cq.web.JsonResult;
public class RoleManageAction extends BaseDelegate{
	IRoleManageFacade  rmFacade = FacadeFactory.getRoleManageFacade();
	IMenuFacade  menuFacade = FacadeFactory.getMenuFacade();
	
	//得到所有的角色信息
	public String getAllRoles(Row row){
		List<Role> list = rmFacade.queryAllRoles();
		for(Role r:list){
			r.setCreatedatestr(DateUtils.date2String(r.getCreatedate(),DateUtils.DATE_FORMAT_DEFAULT));
			r.setBegintimestr(DateUtils.date2String(r.getBegintime(),DateUtils.DATE_FORMAT_DEFAULT));
			r.setEndtimestr(DateUtils.date2String(r.getEndtime(),DateUtils.DATE_FORMAT_DEFAULT));
		}
		return JsonUtil.object2json(list);
	}
	
	//得到所有的角色生成树结构数据
	public String getAllRoles2GrowTree(Row row){
		List<Role> list = rmFacade.queryAllRoles();
		JSONArray ja = new JSONArray(PackTreeII(list,false));
		String str = null;
		try {
			str = ja.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
        logger.info("getAllRoles2GrowTree str"+str);
		return this.setActionJsonObject(str);
	}
	//得到所有的角色生成树结构数据
	public String getAllRoles2GrowTreeByUserId(Row row){
		String userid = row.getString("userid");
		Map<String,String> param = new HashMap<String, String>();
		param.put("userid",userid);
		List<Map<String,Object>> list = rmFacade.getAllCheckRolesByUserId(param);
		JSONArray ja = new JSONArray(PackTreeIII(list,true));
		String str = null;
		try {
			str = ja.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getAllRoles2GrowTree str"+str);
		return this.setActionJsonObject(str);
	}
	//授权
	public String saveRM(Row row){
		//-------ajax---------
		String roleid = row.getString("roleid");
		String menuids = row.getString("menuids");
		try {
			FacadeFactory.getMenuFacade().saveRoleMenus(roleid,menuids);
		} catch (Exception e) {
			e.printStackTrace();
			return this.setActionJsonObject(new JsonResult(false,"授权失败！",e.getMessage()).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "授权成功！").toJsonString());
	}
	
	//获得所有菜单
	public String getAllMenus(Row row){
		List<TreeMenu> menuList = new ArrayList<TreeMenu>();
		try {
			menuList = FacadeFactory.getMenuFacade().listTrees();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String str = "";
		try {
			JSONArray ja = new JSONArray(PackTree(menuList,false));
			str = ja.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getAllMenus str:"+str);
		return setActionJsonObject(str);
	}
	
	//获得角色关联菜单
	public String getCheckedMenus(Row row){
		List<TreeMenu> menuList = new ArrayList<TreeMenu>();
		String roleid = row.getString("roleid");
		try {
			menuList = FacadeFactory.getMenuFacade().queryMenusByRoleID(roleid);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String str = "";
		try {
			JSONArray ja = new JSONArray(PackTree(menuList,true));
			str = ja.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("str:"+str);
		return setActionJsonObject(str);
	}

	private List<Map<String,Object>> PackTree(List<TreeMenu> menuList,boolean checkflag){
		List<Map<String,Object>> listTreeRow=new ArrayList<Map<String,Object>>(); 
		Row treeRow=null;
		for(TreeMenu row1:menuList){	
			if(row1.getParent_id()!=null && row1.getParent_id().trim().equals("-1")){	
				 treeRow=new RowImpl();
				 treeRow.put("id",row1.getMenu_id());
				 treeRow.put("url",row1.getUrl());
				 treeRow.put("parent_id", row1.getParent_id());
				 treeRow.put("iconCls", row1.getIcon());
				 treeRow.put("text", row1.getMenu_name());
               /* if(checkflag && "true".equals(row1.getCheckflag())){
                	treeRow.put("checked", true);
                }*/
                //添加子节点
				getFormChildrenJson(treeRow,menuList, row1.getMenu_id(),checkflag);
                listTreeRow.add(treeRow);
				}
		}
		return listTreeRow;
	}
	private List<Map<String,Object>> PackTreeII(List<Role> roleList,boolean checkflag){
		List<Map<String,Object>> listRoleRow=new ArrayList<Map<String,Object>>(); 
		List<Row> treeChildren=new ArrayList<Row>();
		Row treeRow1 = null;
		for(Role row1:roleList){	
			treeRow1=new RowImpl();
			treeRow1.put("id",row1.getRoleid());
			treeRow1.put("parent_id", "0");
			treeRow1.put("text", row1.getRolename());
			listRoleRow.add(treeRow1);
		}
		return listRoleRow;
	}
	private List<Map<String,Object>> PackTreeIII(List<Map<String,Object>> roleList,boolean checkflag){
		List<Map<String,Object>> listRoleRow=new ArrayList<Map<String,Object>>(); 
		List<Row> treeChildren=new ArrayList<Row>();
		Row treeRow1 = null;
		for(Map<String,Object> row1:roleList){	
			treeRow1=new RowImpl();
			treeRow1.put("id",row1.get("roleid"));
			treeRow1.put("parent_id", "0");
			treeRow1.put("text",row1.get("rolename"));
			if(checkflag && "true".equals( row1.get("checkflag"))){
            	treeRow1.put("checked", true);
            }
			listRoleRow.add(treeRow1);
		}
		return listRoleRow;
	}
	/**
	 * getFormChildrenJson:递归获取子节点. <br/>
	 * 遍历树上的结点
	 * 如果该结点的父结点等于传入的父结点ID
	 * 那么将该结点加入到父节点的children
	 * @author dxtx
	 * @param parentNode 父结点
	 * @param listtree
	 * @param parentID 父结点ID
	 * @param wiId
	 * @since JDK 1.6
	 */
	private void  getFormChildrenJson(Row parentNode,List<TreeMenu> menuList,String parentID,boolean checkflag){
		List<Row> treeChildren=new ArrayList<Row>();
		Row  treeRow = null;
		for(TreeMenu row:menuList){
			if(parentID!=null && parentID.trim().equals(row.getParent_id())){
					treeRow=new RowImpl();					 
					treeRow.put("id",row.getMenu_id());
					treeRow.put("url",row.getUrl());
					treeRow.put("parent_id", row.getParent_id());
					treeRow.put("iconCls", row.getIcon());
					treeRow.put("text", row.getMenu_name());
					if(checkflag && "true".equals(row.getCheckflag())){
	                	treeRow.put("checked", true);
	                }
					treeChildren.add(treeRow);
					parentNode.put("children", treeChildren);
					getFormChildrenJson(treeRow,menuList, row.getMenu_id(),checkflag);
			}
		}		
	}
	//新增和更新角色
	public String updateandAddRole(Row row){
		    boolean flag = false;
		    Role role = new Role();
		    String roleid = row.getString("roleid");
		    String rolename = row.getString("rolename");
		    String operatorcode = row.getString("operatorcode");
		    String createdatestr = row.getString("createdatestr");
		    String keepflag = row.getString("keepflag");
		    String effectflag = row.getString("effectflag");
		    String begintimestr = row.getString("begintimestr");
		    String endtimestr = row.getString("endtimestr");
		    String attribute = row.getString("attribute");
		    String remark = row.getString("remark");
		    role.setRolename(rolename);
		    role.setOperatorcode(operatorcode);
		    role.setCreatedate(DateUtils.string2Date(createdatestr, DateUtils.DATE_FORMAT_DEFAULT));
		    role.setKeepflag(keepflag);
		    role.setEffectflag(effectflag);
		    role.setBegintime(DateUtils.string2Date(begintimestr, DateUtils.DATE_FORMAT_DEFAULT));
		    role.setEndtime(DateUtils.string2Date(endtimestr, DateUtils.DATE_FORMAT_DEFAULT));
		    role.setAttribute(attribute);
		    role.setRemark(remark);
		    String msg="";
		    try {
				if(Util.notNullEmpty(roleid)){
					role.setRoleid(roleid);
					flag = rmFacade.updateRole(role);
					msg = "角色更新成功！";
				}else{
				    flag = rmFacade.addRole(role);
				    msg = "角色增加成功！";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return this.setActionJsonObject(new JsonResult(flag, msg).toJsonString());
	}
	
	//删除角色
	public String deleteRole(Row row){
		    boolean flag = false;
		    Role role = new Role();
		    String roleid = row.getString("roleid");
		    role.setRoleid(roleid);
		    String msg="";
		    try {
				flag = menuFacade.delRoleMenus(roleid);
				flag = rmFacade.delRole(role);
				msg = "角色删除成功！";
			} catch (Exception e) {
				e.printStackTrace();
			}
		    return this.setActionJsonObject(new JsonResult(flag, msg).toJsonString());
	}
	
}

