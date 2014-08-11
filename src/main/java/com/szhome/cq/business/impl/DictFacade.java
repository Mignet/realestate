/**
 * Project Name:dxtx_re
 * File Name:DictFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-1-2下午3:42:46
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.business.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plan.commons.Row;
import com.plan.commons.RowImpl;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.IDictFacade;
import com.szhome.cq.domain.model.DicItem;
import com.szhome.cq.domain.model.DicType;
import com.szhome.cq.domain.model.DictClass;
import com.szhome.cq.domain.model.DictItem;
import com.szhome.cq.domain.model.Identifier;

/**
 * 字典Facade
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class DictFacade implements IDictFacade {
	@Autowired
	private Identifier identifierDao;
	@Autowired
	private  DictItem dictItemDao;					//字典项
	@Autowired
	private  DictClass dictClassDao;				//字典类型
	
	@Autowired
	private  DicType dicTypeDao;					//字典类型
	@Autowired
	private  DicItem dicItemDao;					//字典项
	/**
	 * 
	 * 通过code获取字典项.
	 * @see com.szhome.cq.business.IDictFacade#getDictByCode(java.util.Map)
	 */
	@Transactional
	public List<Map<String, Object>> getDictByCode(Map map) {
		List<Map<String, Object>> dicts = null;
		try {
			dicts = identifierDao.queryMapListByKey("Dict.getDictByCode", map);
		} catch (Exception e) {
		}
		return dicts;
	}

	/**
	 * 
	 *  获取字典项通过字典类型Id  传参 map.put("class_id",class_id);
	 * @see com.szhome.cq.business.IDictFacade#getItemsByClassId(java.util.Map)
	 */
	@Transactional
	public List<Map<String, Object>> getItemsByClassId(Map map) {
		List<Map<String, Object>> dictItems = null;
		try {
			dictItems = identifierDao.queryMapListByKey("Dict.getItemsByClassId", map);
		} catch (Exception e) {
			throw new BusinessException("通过字典类型ID获取字典项失败"+e.getMessage());
		}
		return dictItems;
	}
	
	/**
	 * 
	 *  保存字典类型  传一个Map  map.set("dictClass",DictClass);
	 * @see com.szhome.cq.business.IDictFacade#saveClass(java.util.Map)
	 */
	@Transactional
	public Map saveClass(Map map) {
		DictClass dc = (DictClass)map.get("dictClass");
		try {
			dc.setClass_id(Integer.valueOf(dictClassDao.getSeqId()));
		} catch (Exception e) {
			throw new BusinessException("获取字典类型Seqid失败"+e.getMessage());
		}
		try {
			dictClassDao.save(dc);
		} catch (Exception e) {
			throw new BusinessException("保存字典类型失败"+e.getMessage());
		}
		Map returnMap = new HashMap();
		returnMap.put("result", "success");
		
		return returnMap;
	}

	/**
	 * 
	 *  更新字典项（主要用来编辑字典类型）
	 * @see com.szhome.cq.business.IDictFacade#updateClass(java.util.Map)
	 */
	@Transactional
	public Map updateClass(Map map) {
		DictClass paraDc = (DictClass)map.get("dictClass");
		DictClass dc = dictClassDao.get(paraDc);
		dc.setClass_code(paraDc.getClass_code());
		dc.setClass_name(paraDc.getClass_name());
		dc.setEdit_date(paraDc.getEdit_date());
		dc.setEditor(paraDc.getEditor());
		try {
			dictClassDao.update(dc);
		} catch (Exception e) {
			throw new BusinessException("更新字典类型失败："+e.getMessage());
		}
		Map returnMap = new HashMap();
		returnMap.put("result", "success");
		returnMap.put("message", "数据更新成功");
		return returnMap;
	}

	/**
	 * 
	 *  删除字典类型  （删除字典类型及下面的字典项   暂时输出字典项还未做  ---涉及到删除多条数据）
	 * @see com.szhome.cq.business.IDictFacade#deleteClass(java.util.Map)
	 */
	@Transactional
	public Map deleteClass(Map map) {
		DictClass dc = new DictClass();
		dc.setClass_id(Double.valueOf(map.get("class_id").toString()));
		
		try {
			dictClassDao.delete(dc);
		} catch (Exception e) {
			throw new BusinessException("删除字典类型出错"+e.getMessage());
		}
		Map resultMap = new HashMap();
		resultMap.put("result","success");
		return resultMap;
	}
	
	/**
	 * 
	 *  保存字典项
	 * @see com.szhome.cq.business.IDictFacade#saveItem(java.util.Map)
	 */
	public Map saveItem(Map map) {
		DictItem di = (DictItem)map.get("dictItem");
		try {
			di.setItem_id(Integer.valueOf(dictItemDao.getSeqId()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("获取字典项Seqid失败"+e.getMessage());
		}
		try {
			dictItemDao.save(di);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("保存字典项失败"+e.getMessage());
		}
		Map returnMap = new HashMap();
		returnMap.put("result", "success");
		
		return returnMap;
		
	}
		
	/**
	 * 将字典编辑后的数据应用到后台数据库 从前端传过来修改和更新的
	 */
	@Transactional
	public Map applyEdit(Map paraMap) {
			Map result = new HashMap();
			
			String datas = paraMap.get("datas").toString();
			String userName = paraMap.get("userName").toString();
			//Object //logger;
			//logger.info("datas:" + datas);

			Map classMap = new HashMap<String, Class>();
			classMap.put("inserted", Map.class);
			classMap.put("deleted", Map.class);
			classMap.put("updated", Map.class);
			Map dataMap = (Map)JSONObject.toBean(JSONObject.fromObject(datas), Map.class, classMap);
			
			//logger.info(map.get("inserted"));
			//logger.info(map.get("deleted"));
			//logger.info(map.get("updated"));
			
			Row temp = null;
			
			//处理新增数据
			List news = (List)dataMap.get("inserted");
			DictItem di = null; 
			for (int i = 0; i < news.size(); i ++) {
				temp = new RowImpl((Map)news.get(i));
				di = new DictItem();
				try {
					di.setItem_id(Integer.valueOf(dictItemDao.getSeqId()));
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("获取字典项seq id出错"+e.getMessage());
				}
				di.setClass_id(temp.getDouble("class_id"));
				di.setEdit_date(new Date());
				di.setEditor(userName);
				di.setName(temp.getString("name"));
				di.setRemark(temp.getString("remark"));
				di.setTurn(temp.getDouble("turn"));
				di.setValid_flag(temp.getDouble("valid_flag"));
				di.setValue(temp.getString("value"));
				
				
				try {
					//logger.info("insert:" + temp);
					dictItemDao.save(di);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("保存字典项出错"+e.getMessage());
				}
			}
			
			//处理修改数据
			List upds = (List)dataMap.get("updated");
			for (int i = 0; i < upds.size(); i ++) {
				temp = new RowImpl((Map)upds.get(i));
				di = new DictItem();
				String item_id = temp.getString("item_id");
				try {
					di.setItem_id(temp.getDouble("item_id"));
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				di.setClass_id(temp.getDouble("class_id"));
				di.setEdit_date(new Date());
				di.setEditor(userName);
				di.setName(temp.getString("name"));
				di.setRemark(temp.getString("remark"));
				di.setTurn(temp.getDouble("turn"));
				di.setValid_flag(temp.getDouble("valid_flag"));
				di.setValue(temp.getString("value"));
				
				try {
					 dictItemDao.update(di);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("更新字典项出错"+e.getMessage());
				}
				
				//di.set
				//logger.info("update:" + temp);
				//getPlanSupportDao().update(temp, "plat_dict_item");
			}
			
			//处理删除数据
			List dels = (List)dataMap.get("deleted");
			for (int i = 0; i < dels.size(); i ++) {
				temp = new RowImpl((Map)dels.get(i));
				di = new DictItem();
				di.setItem_id(temp.getDouble("item_id"));
				try {
					dictItemDao.delete(di);
					
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("删除字典项出错"+e.getMessage());
				}
			}
			
			result.put("success",true);
			return result;

		
	}
	/**
	 * 
	 *  模糊查询字典类型  参数 map.put("searchStr",searchStr);
	 * @see com.szhome.cq.business.IDictFacade#searchDictClass(java.util.Map)
	 */
	@Transactional
	public List<Map<String, Object>> searchDictClass(Map map) {
		String searchStr = (String) map.get("searchStr");
		String key = "Dict.searchDictClass";
		searchStr = searchStr==null?"":searchStr;
		/*if(searchStr==null){
			key = "Dict.searchAllDictClass";
		}else{
			key = "Dict.searchDictClass";
		}*/
		searchStr = "%"+searchStr+"%";
		map.put("searchStr", searchStr);
		List<Map<String, Object>> returnList = null;
		try {
			returnList = identifierDao.queryMapListByKey(key, map);
		} catch (Exception e) {
			// : handle exception
		}
		 //select pdc.*, pd.product_name from plat_dict_class pdc left join plat_products pd on (pdc.product_id = pd.product_id) where pdc.product_id = ? and (pdc.class_name like '%:searchStr%' or pdc.class_code like '%:searchStr%')
		//String sql = "select pdc.*, pd.product_name from plat_dict_class pdc left join plat_products pd on (pdc.product_id = pd.product_id) where pdc.product_id = ? and (pdc.class_name like '%" + searchStr + "%' or pdc.class_code like '%" + searchStr + "%')";
		return returnList;
	}
	/**
	 * 
	 *  保存新字典项
	 * @see com.szhome.cq.business.IDictFacade#saveType(java.util.Map)
	 */
	public Map saveType(Map map) {
		DicType dc = (DicType)map.get("dictType");
		try {
			dc.setDic_type_id(dicTypeDao.getSeqId());
		} catch (Exception e) {
			throw new BusinessException("获取字典类型Seqid失败"+e.getMessage());
		}
		try {
			dicTypeDao.save(dc);
		} catch (Exception e) {
			throw new BusinessException("保存字典类型失败"+e.getMessage());
		}
		Map returnMap = new HashMap();
		returnMap.put("result", "success");
		
		return returnMap;
	}
	/**
	 * 
	 *  模糊查询字典类型  参数 map.put("searchStr",searchStr);
	 * @see com.szhome.cq.business.IDictFacade#searchDictClass(java.util.Map)
	 */
	public List<Map<String, Object>> searchDictType(Map paramMap) {
		
		String searchStr = (String) paramMap.get("searchStr");
		String key = "Dict.searchDictType";
		searchStr = searchStr==null?"":searchStr;
		/*if(searchStr==null){
			key = "Dict.searchAllDictClass";
		}else{
			key = "Dict.searchDictClass";
		}*/
		searchStr = "%"+searchStr+"%";
		paramMap.put("searchStr", searchStr);
		List<Map<String, Object>> returnList = null;
		try {
			returnList = dicTypeDao.queryMapListByKey(key, paramMap);
		} catch (Exception e) {
			// : handle exception
		}
		 //select pdc.*, pd.product_name from plat_dict_class pdc left join plat_products pd on (pdc.product_id = pd.product_id) where pdc.product_id = ? and (pdc.class_name like '%:searchStr%' or pdc.class_code like '%:searchStr%')
		//String sql = "select pdc.*, pd.product_name from plat_dict_class pdc left join plat_products pd on (pdc.product_id = pd.product_id) where pdc.product_id = ? and (pdc.class_name like '%" + searchStr + "%' or pdc.class_code like '%" + searchStr + "%')";
		return returnList;
	}

	/**
	 * 
	 *  更新字典项（主要用来编辑字典类型）
	 * @see com.szhome.cq.business.IDictFacade#updateClass(java.util.Map)
	 */
	public Map updateType(Map map) {
		
		DicType paraDc = (DicType)map.get("dictType");
		DicType dc = dicTypeDao.get(paraDc);
		dc.setDic_type_code(paraDc.getDic_type_code());
		dc.setDic_type_value(paraDc.getDic_type_value());
	
		try {
			dicTypeDao.update(dc);
		} catch (Exception e) {
			throw new BusinessException("更新字典类型失败："+e.getMessage());
		}
		Map returnMap = new HashMap();
		returnMap.put("result", "success");
		returnMap.put("message", "数据更新成功");
		return returnMap;
	}

	/**
	 * 
	 *  简单描述该方法的实现功能（删除字典项 类型   先删除字典项）.
	 * @see com.szhome.cq.business.IDictFacade#deleteType(java.util.Map)
	 */
	public Map deleteType(Map map) {
		String dic_type_id =map.get("dic_type_id").toString();
		DicType dc = new DicType();
		dc.setDic_type_id(dic_type_id);
		
		try {
			List<DicItem> itemList = (List<DicItem>) dicItemDao.getAll("where dic_type_id=:dic_type_id", map);
			if(itemList!=null && !itemList.isEmpty()){
				for(DicItem temp :itemList){
					dicItemDao.delete(temp);
				}
			}
			dicTypeDao.delete(dc);
		} catch (Exception e) {
			//throw new BusinessException("删除字典类型出错"+e.getMessage());
			e.printStackTrace();
		}
		Map resultMap = new HashMap();
		resultMap.put("result","success");
		return resultMap;
	}

	/**
	 * 将字典编辑后的数据应用到后台数据库
	 */
	public Map applyEditItem(Map paraMap) {
		
		Map result = new HashMap();
		
		String datas = paraMap.get("datas").toString();

		Map classMap = new HashMap<String, Class>();
		classMap.put("inserted", Map.class);
		classMap.put("deleted", Map.class);
		classMap.put("updated", Map.class);
		Map dataMap = (Map)JSONObject.toBean(JSONObject.fromObject(datas), Map.class, classMap);
		
		//logger.info(map.get("inserted"));
		//logger.info(map.get("deleted"));
		//logger.info(map.get("updated"));
		
		Row temp = null;
		
		//处理新增数据
		List news = (List)dataMap.get("inserted");
		DicItem di = null; 
		for (int i = 0; i < news.size(); i ++) {
			temp = new RowImpl((Map)news.get(i));
			di = new DicItem();
			try {
				di.setDic_item_id(dicItemDao.getSeqId());
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("获取字典项seq id出错"+e.getMessage());
			}
			di.setDic_type_id(temp.getString("dic_type_id"));
			di.setDic_item_code(temp.getString("dic_item_code"));
			di.setDic_item_value(temp.getString("dic_item_value"));
			di.setB_deleteflag(temp.getString("b_deleteflag"));
			
			
			try {
				//logger.info("insert:" + temp);
				dicItemDao.save(di);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("保存字典项出错"+e.getMessage());
			}
		}
		
		//处理修改数据
		List upds = (List)dataMap.get("updated");
		for (int i = 0; i < upds.size(); i ++) {
			temp = new RowImpl((Map)upds.get(i));
			di = new DicItem();
			try {
				di.setDic_item_id(temp.getString("dic_item_id"));
			
			di.setDic_type_id(temp.getString("dic_type_id"));
			di.setDic_item_code(temp.getString("dic_item_code"));
			di.setDic_item_value(temp.getString("dic_item_value"));
			di.setB_deleteflag(temp.getString("b_deleteflag"));
			
			
			dicItemDao.update(di);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("保存字典项出错"+e.getMessage());
			}
			
			//di.set
			//logger.info("update:" + temp);
			//getPlanSupportDao().update(temp, "plat_dict_item");
		}
		
		//处理删除数据
		List dels = (List)dataMap.get("deleted");
		for (int i = 0; i < dels.size(); i ++) {
			temp = new RowImpl((Map)dels.get(i));
			di = new DicItem();
			di.setDic_item_id(temp.getString("dic_item_id"));
			try {
				dicItemDao.delete(di);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("删除字典项出错"+e.getMessage());
			}
		}
		
		result.put("success",true);
		return result;
	}

	/**
	 * 
	 * 通过字典类型ID获取字典项
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getItemsByTypeId(Map paramMap) {
		
		List<Map<String, Object>> dictItems = null;
		try {
			dictItems = identifierDao.queryMapListByKey("Dict.getItemsByTypeId", paramMap);
		} catch (Exception e) {
			throw new BusinessException("通过字典类型ID获取字典项失败"+e.getMessage());
		}
		return dictItems;
	}

	
	/**
	 * 
	 * 保存字典项
	 *
	 * @author Joyon
	 * @param map
	 * @return
	 * @since JDK 1.6
	 */
	public Map saveDicItem(Map map) {
		
		DicItem di = (DicItem)map.get("dictItem");
		try {
			di.setDic_item_id(dicItemDao.getSeqId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("获取字典项Seqid失败"+e.getMessage());
		}
		try {
			dicItemDao.save(di);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("保存字典项失败"+e.getMessage());
		}
		Map returnMap = new HashMap();
		returnMap.put("result", "success");
		
		return returnMap;
	}

	/**
	 * 
	 * 获取字典项
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getNewDictByCode(Map paraMap) {
		
		List<Map<String, Object>> dicts = null;
		try {
			dicts = identifierDao.queryMapListByKey("Dict.getNewDictByCode", paraMap);
		} catch (Exception e) {
			// : handle exception
		}
		//identifierDao.
		//String sClassCode = row.getString("code");
		//List<Map<String, Object>> dicts = getPlanSupportDao().findRows("select name, value from plat_dict_item pdi left join plat_dict_class pdc on (pdi.class_id = pdc.class_id) where pdc.class_code = ? and valid_flag = 1 order by turn", new Object[]{sClassCode});
		return dicts;
	}
	
	/**
	 * 
	 * 通过字典code和字典类型code获取字典value
	 *
	 * @author Joyon
	 * @param code		字典项code
	 * @param dic_type_code  字典类型code
	 * @return
	 * @since JDK 1.6
	 */
	public String getDictItemNameByCodeAndTypeCode(String code,String dic_type_code){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("code", code);
		paraMap.put("dic_type_code", dic_type_code);
		String whereSql = " where DIC_ITEM_CODE=:code  and DIC_TYPE_ID =(select DIC_TYPE_ID from CFIG_DIC_TYPE where DIC_TYPE_CODE=:dic_type_code)";
		DicItem dicItem = null;
		try {
			dicItem = dicItemDao.get(whereSql, paraMap);
		} catch (Exception e) {
			LogUtil.error("DictFacade.getDictItemNameByCodeAndTypeCode  获取字典名出错"+e.getMessage());
		}
		if(dicItem == null){
			return null;
		}
		return dicItem.getDic_item_value();
	}
	
	/**
	 * 
	 * 通过字典项code获取字典项数据
	 *
	 * @author Joyon
	 * @param dictitemcode
	 * @return
	 * @since JDK 1.6
	 */
	public DicItem getDictitemByItemcode(String dictitemcode){
			Map paraMap = new HashMap();
			paraMap.put("dictitemcode", dictitemcode);
			DicItem dicItem = null;
			try {
				dicItem = dicItemDao.get(
						" where DIC_ITEM_CODE=:dictitemcode", paraMap);
			} catch (Exception e) {
				LogUtil.error("DictFacade.getDictitemByItemcode 通过字典项code获取字典数据出错  itemcode:"+dictitemcode+" error message:"+e.getMessage());
			}
		return dicItem;
	}

}


