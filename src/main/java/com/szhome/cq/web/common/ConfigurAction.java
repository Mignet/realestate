/**
 * Project Name:dxtx_re
 * File Name:DictAction.java
 * Package Name:com.szhome.cq.web.common
 * Date:2014-1-2����3:22:30
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.web.common;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.json.JSONArray;

import com.plan.commons.Row;
import com.plan.web.JsonResult;
import com.szhome.commons.log.LogUtil;
import com.szhome.commons.util.DateUtil;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.BusType;
import com.szhome.cq.domain.model.ComLanguage;
import com.szhome.cq.domain.model.RecMatConfigure;
import com.szhome.cq.domain.model.TreeMenu;
import com.szhome.cq.utils.DateUtils;
import com.szhome.cq.utils.JsonUtil;


/**
 * DictAction�������ֵ���. <br/>
 * Date:     2014-1-2 ����3:22:30 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ConfigurAction extends BaseDelegate{
	
	 /**
	  * 
	  * saveMenu:����˵�. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String saveMenu(Row row){
		 //-------ajax---------
		 Map m = new HashMap();
		 TreeMenu menu = new TreeMenu();
			menu.setMenu_name(row.getString("menu.menu_name"));
			menu.setParent_id(row.getString("menu.parent_id"));
			menu.setUrl(row.getString("menu.url"));
			menu.setCreator(row.getString("menu.creator"));
			menu.setCreate_date(DateUtils.string2Date(row.getString("menu.create_date"), DateUtils.DATE_FORMAT_DEFAULT));
			menu.setMenu_order(row.getString("menu.menu_order"));
		 m.put("menuname",menu.getMenu_name());
		 TreeMenu me = FacadeFactory.getConfigurFacade().getMenuById(m);
		 if(me != null){
			 
			 return this.setActionJsonObject(new JsonResult(true, "�˵������ظ�,���޸ģ�", null).toJsonString());
		 }
		 
		 try {  menu.setIcon("ge");
				FacadeFactory.getConfigurFacade().saveMenu(menu);	
			} catch(Exception e) {
				LogUtil.error(e.getMessage(), e);
				return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
			}
			return this.setActionJsonObject(new JsonResult(true, "�˵�����ɹ���", null).toJsonString());	 
	 }
	 
	 /**
	  * 
	  * updateMenu:���²˵�. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String updateMenu(Row row){
		 //-------ajax---------
		 TreeMenu menu = new TreeMenu();
		 	menu.setMenu_id(row.getString("menu_id"));
			menu.setMenu_name(row.getString("menu.menu_name"));
			menu.setParent_id(row.getString("menu.parent_id"));
			menu.setUrl(row.getString("menu.url"));
			menu.setCreator(row.getString("menu.creator"));
			//2014-7-31 Sam Modify
			//menu.setCreate_date(row.getDate("menu.create_date"));
			menu.setCreate_date(DateUtils.string2Date(row.getString("menu.create_date"),DateUtils.DATE_FORMAT_CHINA_DEFAULT));
			menu.setMenu_order(row.getString("menu.menu_order"));
		 try {  menu.setIcon("ge");
				FacadeFactory.getConfigurFacade().updateMenu(menu);	
			} catch(Exception e) {
				LogUtil.error(e.getMessage(), e);
				return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
			}
			return this.setActionJsonObject(new JsonResult(true, "�˵����³ɹ���", null).toJsonString());	 
	 }	         
	 /**
	  * 
	  * delMenu:ɾ���˵���. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String delMenu(Row row){
		 
		 //-------ajax---------
			String id = (String)row.getString("id");
			
			List m = FacadeFactory.getConfigurFacade().getMenuChild(id);
			if(m.size() != 0){
				
				return this.setActionJsonObject(new JsonResult(true, "�ýڵ�����ӽڵ㣬����ɾ���ӽڵ㡣", null).toJsonString());
				
			}
			 try {
					FacadeFactory.getConfigurFacade().delMenu(id);	
				} catch(Exception e) {
					LogUtil.error(e.getMessage(), e);
					return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
				}
				return this.setActionJsonObject(new JsonResult(true, "�˵�ɾ���ɹ���", null).toJsonString());	
	 };
	 
	 
	 /**
	  * 
	  * getMenuList:��ȡ�˵����б�. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String getMenuList(Row row){
			//-------ajax---------
			//��ȡ����ʵ����id
			String id = (String)row.getString("id");
			String str = "";
			List<Map<String, Object>> apps = FacadeFactory.getConfigurFacade().getMenuList(id);
			/*Map<String,Object> map = new HashMap<String,Object>();
			//��¼�˲�ѯ����
			map.put("total", apps.size());
			//��¼�˵�ǰҳ������
			map.put("rows", apps);*/
			/*JSONArray list = JSONArray.fromObject(apps);
			str = list.toString();*/
			
			//System.out.println(str);
			return this.setActionJsonObject(JsonUtil.list2json(apps).toLowerCase(Locale.CHINESE));

		}
	 /**
	  * 
	  * getBusProcList:��ȡ����ҵ�����б�. <br/>
	  * @author xuzz
	  * @return
	  * @since JDK 1.6
	  */
	 public String getBusProcList(Row row){
			//-------ajax---------
			//��ȡ����ʵ����id
			String id = (String)row.getString("id");;
			String str = "";
			List<Map<String, Object>> apps = FacadeFactory.getConfigurFacade().getBusProcList(id);
			/*Map<String,Object> map = new HashMap<String,Object>();
			//��¼�˲�ѯ����
			map.put("total", apps.size());
			//��¼�˵�ǰҳ������
			map.put("rows", apps);*/
			JSONArray list = JSONArray.fromObject(apps);
			str = list.toString();
			System.out.println(str);
			return this.setActionJsonObject(str.toLowerCase(Locale.CHINESE));

		}
	 
	
	 /**
	  * 
	  * saveComLanguage:���浥��������. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String saveComLanguage(Row row){
		 //-------ajax---------
		 ComLanguage co = new ComLanguage();
			co.setBus_type_id(row.getString("co.bus_type_id"));
			co.setLanguage_name(row.getString("co.language_name"));
			co.setTemp_type(row.getString("co.temp_type"));
			co.setLanguage_content(row.getString("co.language_content"));
		 try {
				FacadeFactory.getConfigurFacade().saveComLanguage(co);	
			} catch(Exception e) {
				LogUtil.error(e.getMessage(), e);
				return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
			}
			return this.setActionJsonObject(new JsonResult(true, "�����ﱣ��ɹ���", null).toJsonString());	 
	 }
	 
	 /**
	  * 
	  * updateComLanguage:���µ���������. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String updateComLanguage(Row row){	
		 //-------ajax---------
		 ComLanguage co = new ComLanguage();
			co.setBus_type_id(row.getString("co.bus_type_id"));
			co.setLanguage_name(row.getString("co.language_name"));
			co.setTemp_type(row.getString("co.temp_type"));
			co.setLanguage_content(row.getString("co.language_content"));
			try 
			{
				FacadeFactory.getConfigurFacade().updateComLanguage(co);			
			} 
			catch (Exception e) {
				e.printStackTrace();
				return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
			}	
			return this.setActionJsonObject(new JsonResult(true, "��������³ɹ���", null).toJsonString());
	 } 
	 /**
	  * 
	  * delComLanguage:ɾ������������. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String delComLanguage(Row row){
		 //-------ajax---------
			String id = (String)row.getString("language_id");
			 try {
				FacadeFactory.getConfigurFacade().delComLanguage(id);
			} catch (Exception e) {

				e.printStackTrace();
				return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
			}
			
			 return this.setActionJsonObject(new JsonResult(true, "ɾ��������ɹ���", null).toJsonString());			 
	 }
	 
	 /**
	  * 
	  * getComLanById:��ȡģ����Ϣ. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String getComLanById(Row row){
			//-------ajax---------
			//��ȡ����ʵ����id
			//int lcslbid = new Integer((String)this.getRequest().getParameter("lcslbid"));
			String str = "";
			String id = null;
			Map m = new HashMap();
			m.put("procid", row.getString("procdefId"));
			BusType bustype = FacadeFactory.getConfigurFacade().getBusTypeByName(m);
			
			if(bustype != null){
				
				id = bustype.getBus_type_id();
			}else{
				
				return this.setActionJsonObject(new JsonResult(true, "�����ڴ�����¼��", null).toJsonString());
			}
			Map ma = new HashMap();
			ma.put("id", id);
			ma.put("temptype", row.getString("temptype"));
			List<Map<String, Object>> apps = FacadeFactory.getConfigurFacade().getComLanByid(ma);
			Map<String,Object> map = new HashMap<String,Object>();
			//��¼�˲�ѯ����
			map.put("total", apps.size());
			//��¼�˵�ǰҳ������
			map.put("rows", apps);
			try {
				str = JsonUtil.map2json(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return this.setActionJsonObject(str);
		 }
	 /**
	  * 
	  * getComLanBy:��ȡ����ģ����Ϣ. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String getComLan(Row row){
		 //-------ajax---------
			//��ȡ����ʵ����id
			//int lcslbid = new Integer((String)this.getRequest().getParameter("lcslbid"));
			String str = "";
		
			Map ma = new HashMap();
			ma.put("id", row.getString("bus_type_id"));
			//ma.put("temptype", temptype);
			List<Map<String, Object>> apps = FacadeFactory.getConfigurFacade().getComLan(ma);
			Map<String,Object> map = new HashMap<String,Object>();
			//��¼�˲�ѯ����
			map.put("total", apps.size());
			//��¼�˵�ǰҳ������
			map.put("rows", apps);
			try {
				str = JsonUtil.map2json(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return this.setActionJsonObject(str);
		 
		 
		 
		 
	 }
	 
	 
	 /**
	  * 
	  * getRecMaterial:��ȡ�Ӽ����ϱ�������Ϣ. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String getRMaterialCon(Row row){
			//-------ajax---------
			
			//��ȡ����ʵ����id
		String str = "";
		String rec_type_flag = row.getString("rec_type_flag");
		String bus_type_id = row.getString("bus_type_id");
			List<Map<String, Object>> apps = FacadeFactory.getConfigurFacade().getMaterialCon(bus_type_id,rec_type_flag);
			Map<String,Object> map = new HashMap<String,Object>();
			//��¼�˲�ѯ����
			map.put("total", apps.size());
			//��¼�˵�ǰҳ������
			map.put("rows", apps);
			try {
				str = JsonUtil.map2json(map);
				System.out.println(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return this.setActionJsonObject(str);

		}
	 
	 /**
	  * 
	  * saveRecMaterial:����Ӽ����ϱ�������Ϣ. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String saveRecMaterialCon(Row row){
		 //-------ajax---------
		 RecMatConfigure rmc = new RecMatConfigure();
			rmc.setCfig_rec_name(row.getString("rmc.cfig_rec_name"));
			rmc.setCfig_rec_type(row.getString("rmc.cfig_rec_type"));
			rmc.setCfig_rec_style(row.getString("rmc.cfig_rec_style"));
			rmc.setCfig_rec_copy(row.getString("rmc.cfig_rec_copy"));
			rmc.setBus_type_id(row.getString("rmc.bus_type_id"));
			rmc.setCfig_page(row.getString("rmc.cfig_page"));
			rmc.setCfig_person(row.getString("rmc.cfig_person"));
			rmc.setCfig_date(row.getDate("rmc.cfig_date"));
			rmc.setRec_type_flag(row.getString("rmc.rec_type_flag"));
		 try {
				FacadeFactory.getConfigurFacade().saveRecMaterial(rmc);	
			} catch(Exception e) {
				LogUtil.error(e.getMessage(), e);
				return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
			}
			return this.setActionJsonObject(new JsonResult(true, "����ɹ���", null).toJsonString());	 
	 }
	 /**
	  * 
	  * updateRecMaterial:���½Ӽ����ϱ�������Ϣ. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 
	 public String updateRecMaterialCon(Row row){
		 //-------ajax---------
		 RecMatConfigure rmc = new RecMatConfigure();
			rmc.setCfig_rec_name(row.getString("rmc.cfig_rec_name"));
			rmc.setCfig_rec_type(row.getString("rmc.cfig_rec_type"));
			rmc.setCfig_rec_style(row.getString("rmc.cfig_rec_style"));
			rmc.setCfig_rec_copy(row.getString("rmc.cfig_rec_copy"));
			rmc.setCfig_page(row.getString("rmc.cfig_page"));
			rmc.setCfig_person(row.getString("rmc.cfig_person"));
			rmc.setCfig_date(row.getDate("rmc.cfig_date"));
			rmc.setRec_type_flag(row.getString("rmc.rec_type_flag"));
			rmc.setBus_type_id(row.getString("rmc.bus_type_id"));
		 try {
				FacadeFactory.getConfigurFacade().updateRecMaterial(rmc);	
			} catch(Exception e) {
				LogUtil.error(e.getMessage(), e);
				return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
			}
			return this.setActionJsonObject(new JsonResult(true, "���³ɹ���", null).toJsonString());	 
	 }
	 /**
	  * 
	  * delRecMaterial:ɾ���Ӽ����ϱ�������Ϣ. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */		 
	 public String delRecMaterialCon(Row row){
		 //-------ajax---------
		String id = row.getString("cfig_receival_id");
		 try {
				FacadeFactory.getConfigurFacade().delRecMaterial(id);	
			} catch(Exception e) {
				LogUtil.error(e.getMessage(), e);
				return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
			}
			return this.setActionJsonObject(new JsonResult(true, "����ɾ���ɹ���", null).toJsonString());	 
	 }
   /**
    * 
    * getCount:��ȡ�˵��ܼ�¼��. <br/>
    * @author PANDA
    * @return
    * @since JDK 1.6
    */
    public String getCount(Row row){
    	String count = FacadeFactory.getConfigurFacade().getCount(row.getString("menu.parent_id"));
    	Map m = new HashMap();
    	m.put("count", count);
    	return JsonUtil.map2json(m);
    	
    }
    /**
     * 
     * menuMoveup:�˵�����. <br/>
     * @author PANDA
     * @return
     * @since JDK 1.6
     */
    public String menuMoveup(Row row){
    	    TreeMenu menu = new TreeMenu();
			menu.setParent_id(row.getString("menu.parent_id"));
			menu.setMenu_order(row.getString("menu.menu_order"));
			menu.setMenu_id(row.getString("menu.menu_id"));
    	try {
			FacadeFactory.getConfigurFacade().menuMoveup(menu);
		} catch (Exception e) {
			LogUtil.error(e.getMessage(), e);
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
   	    return this.setActionJsonObject(new JsonResult(true, "�����ɹ���", null).toJsonString());	
    }
    
    /**
     * 
     * menuMovedown:�˵�����. <br/>
     * @author PANDA
     * @return
     * @since JDK 1.6
     */
    public String menuMovedown(Row row){
   	TreeMenu menu = new TreeMenu();
	menu.setParent_id(row.getString("menu.parent_id"));
	menu.setMenu_order(row.getString("menu.menu_order"));
	menu.setMenu_id(row.getString("menu.menu_id"));
 	try {   
// 		   String maxorder = FacadeFactory.getConfigurFacade().getCount(menu.getParent_id());
// 	        if(menu.getMenu_order().equals(maxorder)){
// 	        	
// 	        	
// 	        	return this.setActionJsonObject(new JsonResult(true, "�����ɹ���", null).toJsonString());	
// 	        	
// 	        }
			FacadeFactory.getConfigurFacade().menuMovedown(menu);
		} catch (Exception e) {
			
			LogUtil.error(e.getMessage(), e);
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
			
		}
	return this.setActionJsonObject(new JsonResult(true, "�����ɹ���", null).toJsonString());
    }

}


