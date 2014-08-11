/**
 * Project Name:dxtx_re
 * File Name:DictAction.java
 * Package Name:com.szhome.cq.web.common
 * Date:2014-1-2下午3:22:30
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.web.common;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.plan.commons.Row;
import com.plan.exceptions.GeneralException;
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;
import com.sun.org.apache.xml.internal.serializer.utils.Utils;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IDictFacade;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.DicItem;
import com.szhome.cq.domain.model.DicType;
import com.szhome.cq.domain.model.DictClass;
import com.szhome.cq.domain.model.DictItem;
import com.szhome.cq.utils.JsonUtil;

/**
 * ClassName:DictAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-2 下午3:22:30 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class DictAction extends BaseDelegate{
	
//	private String class_code;					//字典项类型code
//		
//	private String searchStr;					//字典类型模糊查询字串  可以是code  或者类型名
//	
//	private String class_id;					//字典类型Id	
//	
//	private String class_name;					//字典类型名
//	
//	private DictClass dictClass;				//字典类型		
//	
//	private DicType dicType;					//字典类型
//	
//	private DicItem dicItem;					//字典项
	
	private IDictFacade dictFacade=FacadeFactory.getDictFacade();
	
	/**
	 * 
	 * getDictByCode:(通过  code 获取字典项)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getDictByCode(Row row){
		
		String str = "";
		//-------ajax---------
		Map paraMap = new HashMap();
		
		String class_code = row.getString("code");
		paraMap.put("class_code", class_code);
		List<Map<String, Object>> dicts = dictFacade.getDictByCode(paraMap);
		//return listResultToJson(dicts);
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(dicts!=null&&!dicts.isEmpty()){
			//map.put("total", dicts.size());
			map.put("rows", dicts);
		}
		try {
			str = JsonUtil.object2json(map);
			int startIndex = str.indexOf("\"rows\":")+"\"rows\":".length();
			int endIndex = str.length()-1;
			str = str.substring(startIndex, endIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.setActionJsonObject(str.toLowerCase());
	}
	
	/**
	 * 
	 * getNewDictByCode:(获取字典项通过  code)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getNewDictByCode(Row row){
		
		String str = "";
		//-------ajax---------
		Map paraMap = new HashMap();
		
		String dic_type_code = row.getString("classCode");
		if(!com.szhome.cq.utils.Util.notNullEmpty(dic_type_code)){
			dic_type_code = row.getString("code");
		}
		paraMap.put("dic_type_code", dic_type_code);
		List<Map<String, Object>> dicts = dictFacade.getNewDictByCode(paraMap);
		//return listResultToJson(dicts);
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(dicts!=null&&!dicts.isEmpty()){
			//map.put("total", dicts.size());
			map.put("rows", dicts);
		}else{
			return null;
		}
		try {
			str = JsonUtil.object2json(map);
			int startIndex = str.indexOf("\"rows\":")+"\"rows\":".length();
			int endIndex = str.length()-1;
			str = str.substring(startIndex, endIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.setActionJsonObject(str.toLowerCase());
	}
	
	/**
	 * 
	 * searchDictClass:(模糊查询字典项)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String searchDictClass(Row row){
		String str = "";
		//String searchStr = null;
		//-------ajax---------
//		Map paramMap = new HashMap();
//		paramMap.put("searchStr", searchStr);
		List<Map<String, Object>> dicts = dictFacade.searchDictClass(row);
		Map<String,Object> map = new HashMap<String,Object>();
		if(dicts!=null&&!dicts.isEmpty()){
			map.put("total", dicts.size());
			map.put("rows", dicts);
		}
		try {
			str = JsonUtil.object2json(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.setActionJsonObject(str.toLowerCase());
	}
	
	/**
	 * 
	 * getItemsByClassId:(通过字典类型ID获取字典项)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getItemsByClassId(Row row){
		String str = "";
		//-------ajax---------
//		Map paramMap = new HashMap();
//		paramMap.put("class_id", class_id);
		List<Map<String, Object>> dictItems = dictFacade.getItemsByClassId(row);
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(dictItems!=null&&!dictItems.isEmpty()){
			map.put("total", dictItems.size());
			map.put("rows", dictItems);
		}
		try {
			str = JsonUtil.object2json(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.setActionJsonObject(str.toLowerCase());
	}
	
	/**
	 * 将字典编辑后的数据应用到后台数据库
	 * @throws GeneralException 
	 */
	public String applyEdit(Row row) throws GeneralException{
		String userName = getOperatorInfo().getUserName();
		String datas = row.getString("datas");
		Map map = new HashMap();
		map.put("datas",datas);
		map.put("userName", userName);
		
		try {
			map = dictFacade.applyEdit(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.object2json(map);
	}
	
	/**
	 * 
	 * TODO 保存字典类型  传一个Map  map.set("dictClass",DictClass);
	 * @see com.szhome.cq.business.IDictFacade#saveClass(java.util.Map)
	 */
	public String saveClass(Row row){
		//-------ajax---------
		Map map = new HashMap();
		DictClass dictClass = new DictClass();
		String class_name = row.getString("class_name");
		String class_code = row.getString("class_code");
		String remark = row.getString("remark");
		dictClass.setClass_code(class_code);
		dictClass.setClass_name(class_name);
		dictClass.setRemark(remark);
		map.put("dictClass",dictClass);
		map = dictFacade.saveClass(map);
		return JsonUtil.object2json(map);
	}
	
	/**
	 * 
	 * TODO 更新字典项（主要用来编辑字典类型）
	 * @throws GeneralException 
	 * @see com.szhome.cq.business.IDictFacade#updateClass(java.util.Map)
	 */
	public String updateClass(Row row) throws GeneralException{
		Map map = new HashMap();
		DictClass dictClass = new DictClass();
		String class_id = row.getString("class_id");
		String class_name = row.getString("class_name");
		String class_code = row.getString("class_code");
		String remark = row.getString("remark");
		dictClass.setClass_id(Double.valueOf(class_id));
		dictClass.setClass_code(class_code);
		dictClass.setClass_name(class_name);
		dictClass.setEdit_date(new Date());
		dictClass.setEditor(getOperatorInfo().getUserName());		//当前登陆的用户
		dictClass.setRemark(remark);
		map.put("dictClass",dictClass);
		map = dictFacade.updateClass(map);
		return JsonUtil.object2json(map);
	}
	
	/**
	 * 
	 * TODO 删除字典类型  （删除字典类型及下面的字典项   暂时输出字典项还未做  ---涉及到删除多条数据）
	 * @see com.szhome.cq.business.IDictFacade#deleteClass(java.util.Map)
	 */
	public String deleteClass(Row row){
		Map map = new HashMap();
		String class_id = row.getString("class_id");
		map.put("class_id",class_id);
		map = dictFacade.deleteClass(map);
		return JsonUtil.object2json(map);
		
	}
	
	/**
	 * 
	 * saveItem:(保存字典项).
	 * @author Joyon
	 * @return
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public String saveItem(Row row) throws GeneralException{
		
		//-------ajax---------
		Map map = new HashMap();
	
		
		DictItem dictItem = new DictItem();
		String name = row.getString("name");
		String value = row.getString("value");
		String remark = row.getString("remark");
		String class_id = row.getString("class_id");
		String turn = row.getString("turn");
		String valid_flag = row.getString("valid_flag");
		dictItem.setName(name);
		dictItem.setValue(value);
		dictItem.setClass_id(Double.valueOf(class_id));
		dictItem.setTurn(Double.valueOf(turn));
		dictItem.setValid_flag(Double.valueOf(valid_flag));
		dictItem.setEdit_date(new Date());
		dictItem.setEditor(getOperatorInfo().getUserName());
		dictItem.setRemark(remark);
		
		map.put("dictItem",dictItem);
		map = dictFacade.saveItem(map);
		return JsonUtil.object2json(map);
	
	}
	
	
	
	/*********************************     新字典项方法       *************************/
	/** TODO 保存字典类型  传一个Map  map.set("dictClass",DictClass);
	 * @see com.szhome.cq.business.IDictFacade#saveClass(java.util.Map)
	 */
	public String saveType(Row row){
		//-------ajax---------
		Map map = new HashMap();
		DicType dicType = new DicType();
		String dict_type_value = row.getString("dict_type_value");
		String dict_type_code = row.getString("dict_type_code");
		String dict_type_des = row.getString("dict_type_des");
		
		dicType.setDic_type_code(dict_type_code);
		dicType.setDic_type_value(dict_type_value);
		dicType.setDic_type_des(dict_type_des);
		
		map.put("dictType",dicType);
		map = dictFacade.saveType(map);
		return JsonUtil.object2json(map);
		
	}
	
	public String updateType(Row row){
		Map map = new HashMap();
		DicType dicType = new DicType();
		String dic_type_id = row.getString("dic_type_id");
		String dic_type_value = row.getString("dic_type_value");
		String dic_type_code = row.getString("dic_type_code");
		String dic_type_des = row.getString("dic_type_des");
		
		
		dicType.setDic_type_id(dic_type_id);
		dicType.setDic_type_code(dic_type_code);
		dicType.setDic_type_value(dic_type_value);
		dicType.setDic_type_des(dic_type_des);
		map.put("dictType",dicType);
		map = dictFacade.updateType(map);
		return JsonUtil.object2json(map);
	}
	
	//删除字典类型
	public String deleteType(Row row){
		Map map = new HashMap();
		String dic_type_id = row.getString("dic_type_id");
		map.put("dic_type_id",dic_type_id);
		map = dictFacade.deleteType(map);
		return JsonUtil.object2json(map);
	}
	
	/**
	 * 
	 * searchDictClass:(模糊查询字典项)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String searchDictType(Row row){
		String str = "";
		//String searchStr = null;
		//-------ajax---------
//		Map paramMap = new HashMap();
//		paramMap.put("searchStr", searchStr);
		List<Map<String, Object>> dicts = dictFacade.searchDictType(row);
		Map<String,Object> map = new HashMap<String,Object>();
		if(dicts!=null&&!dicts.isEmpty()){
			map.put("total", dicts.size());
			map.put("rows", dicts);
		}
		try {
			str = JsonUtil.object2json(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.setActionJsonObject(str.toLowerCase());
	}
	
	/**
	 * 将字典编辑后的数据应用到后台数据库
	 * @throws GeneralException 
	 */
	public String applyEditItem(Row row) throws GeneralException{
		String userName = getOperatorInfo().getUserName();
		String datas = row.getString("datas");
		Map map = new HashMap();
		map.put("datas",datas);
		map.put("userName", userName);
		
		try {
			map = dictFacade.applyEditItem(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.object2json(map);
	}
	
	/**
	 * 
	 * getItemsByTypeId:(通过字典类型ID获取字典项)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getItemsByTypeId(Row row){
		String str = "";
		//-------ajax---------
		String dic_type_id = row.getString("dic_type_id");
		Map paramMap = new HashMap();
		paramMap.put("type_id", dic_type_id);
		List<Map<String, Object>> dictItems = dictFacade.getItemsByTypeId(paramMap);
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(dictItems!=null&&!dictItems.isEmpty()){
			map.put("total", dictItems.size());
			map.put("rows", dictItems);
		}
		try {
			str = JsonUtil.object2json(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.setActionJsonObject(str.toLowerCase());
	}
	
	/**
	 * saveItem:(保存字典项).
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String saveDicItem(Row row){
		
		//-------ajax---------
		Map map = new HashMap();
	
		
		DicItem dictItem = new DicItem();
		String dic_item_value = row.getString("dic_item_value");
		String dic_item_code = row.getString("dic_item_code");
		String dic_type_id = row.getString("dic_type_id");
		String b_deleteflag = row.getString("b_deleteflag");
		
		dictItem.setDic_item_code(dic_item_code);
		dictItem.setDic_item_value(dic_item_value);
		dictItem.setDic_type_id(dic_type_id);
		dictItem.setB_deleteflag(b_deleteflag);
		
		map.put("dictItem",dictItem);
		map = dictFacade.saveDicItem(map);
		return JsonUtil.object2json(map);
	
	}
	
	/**
	 * 
	 * 通过字典项code获取字典value
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getDictitemnameBycode(Row row){
		String dictitemcode = row.getString("code");
		DicItem dicItem = FacadeFactory.getDictFacade().getDictitemByItemcode(dictitemcode);
		Map resultMap = new HashMap<String,Object>();
		resultMap.put("dictitemvalue", dicItem.getDic_item_value());
		return JsonUtil.map2json(resultMap);
	}

}


