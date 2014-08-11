/**
 * Project Name:dxtx_re
 * File Name:DictFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-1-2����3:42:46
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
 * �ֵ�Facade
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
	private  DictItem dictItemDao;					//�ֵ���
	@Autowired
	private  DictClass dictClassDao;				//�ֵ�����
	
	@Autowired
	private  DicType dicTypeDao;					//�ֵ�����
	@Autowired
	private  DicItem dicItemDao;					//�ֵ���
	/**
	 * 
	 * ͨ��code��ȡ�ֵ���.
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
	 *  ��ȡ�ֵ���ͨ���ֵ�����Id  ���� map.put("class_id",class_id);
	 * @see com.szhome.cq.business.IDictFacade#getItemsByClassId(java.util.Map)
	 */
	@Transactional
	public List<Map<String, Object>> getItemsByClassId(Map map) {
		List<Map<String, Object>> dictItems = null;
		try {
			dictItems = identifierDao.queryMapListByKey("Dict.getItemsByClassId", map);
		} catch (Exception e) {
			throw new BusinessException("ͨ���ֵ�����ID��ȡ�ֵ���ʧ��"+e.getMessage());
		}
		return dictItems;
	}
	
	/**
	 * 
	 *  �����ֵ�����  ��һ��Map  map.set("dictClass",DictClass);
	 * @see com.szhome.cq.business.IDictFacade#saveClass(java.util.Map)
	 */
	@Transactional
	public Map saveClass(Map map) {
		DictClass dc = (DictClass)map.get("dictClass");
		try {
			dc.setClass_id(Integer.valueOf(dictClassDao.getSeqId()));
		} catch (Exception e) {
			throw new BusinessException("��ȡ�ֵ�����Seqidʧ��"+e.getMessage());
		}
		try {
			dictClassDao.save(dc);
		} catch (Exception e) {
			throw new BusinessException("�����ֵ�����ʧ��"+e.getMessage());
		}
		Map returnMap = new HashMap();
		returnMap.put("result", "success");
		
		return returnMap;
	}

	/**
	 * 
	 *  �����ֵ����Ҫ�����༭�ֵ����ͣ�
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
			throw new BusinessException("�����ֵ�����ʧ�ܣ�"+e.getMessage());
		}
		Map returnMap = new HashMap();
		returnMap.put("result", "success");
		returnMap.put("message", "���ݸ��³ɹ�");
		return returnMap;
	}

	/**
	 * 
	 *  ɾ���ֵ�����  ��ɾ���ֵ����ͼ�������ֵ���   ��ʱ����ֵ��δ��  ---�漰��ɾ���������ݣ�
	 * @see com.szhome.cq.business.IDictFacade#deleteClass(java.util.Map)
	 */
	@Transactional
	public Map deleteClass(Map map) {
		DictClass dc = new DictClass();
		dc.setClass_id(Double.valueOf(map.get("class_id").toString()));
		
		try {
			dictClassDao.delete(dc);
		} catch (Exception e) {
			throw new BusinessException("ɾ���ֵ����ͳ���"+e.getMessage());
		}
		Map resultMap = new HashMap();
		resultMap.put("result","success");
		return resultMap;
	}
	
	/**
	 * 
	 *  �����ֵ���
	 * @see com.szhome.cq.business.IDictFacade#saveItem(java.util.Map)
	 */
	public Map saveItem(Map map) {
		DictItem di = (DictItem)map.get("dictItem");
		try {
			di.setItem_id(Integer.valueOf(dictItemDao.getSeqId()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("��ȡ�ֵ���Seqidʧ��"+e.getMessage());
		}
		try {
			dictItemDao.save(di);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("�����ֵ���ʧ��"+e.getMessage());
		}
		Map returnMap = new HashMap();
		returnMap.put("result", "success");
		
		return returnMap;
		
	}
		
	/**
	 * ���ֵ�༭�������Ӧ�õ���̨���ݿ� ��ǰ�˴������޸ĺ͸��µ�
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
			
			//������������
			List news = (List)dataMap.get("inserted");
			DictItem di = null; 
			for (int i = 0; i < news.size(); i ++) {
				temp = new RowImpl((Map)news.get(i));
				di = new DictItem();
				try {
					di.setItem_id(Integer.valueOf(dictItemDao.getSeqId()));
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("��ȡ�ֵ���seq id����"+e.getMessage());
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
					throw new BusinessException("�����ֵ������"+e.getMessage());
				}
			}
			
			//�����޸�����
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
					throw new BusinessException("�����ֵ������"+e.getMessage());
				}
				
				//di.set
				//logger.info("update:" + temp);
				//getPlanSupportDao().update(temp, "plat_dict_item");
			}
			
			//����ɾ������
			List dels = (List)dataMap.get("deleted");
			for (int i = 0; i < dels.size(); i ++) {
				temp = new RowImpl((Map)dels.get(i));
				di = new DictItem();
				di.setItem_id(temp.getDouble("item_id"));
				try {
					dictItemDao.delete(di);
					
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("ɾ���ֵ������"+e.getMessage());
				}
			}
			
			result.put("success",true);
			return result;

		
	}
	/**
	 * 
	 *  ģ����ѯ�ֵ�����  ���� map.put("searchStr",searchStr);
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
	 *  �������ֵ���
	 * @see com.szhome.cq.business.IDictFacade#saveType(java.util.Map)
	 */
	public Map saveType(Map map) {
		DicType dc = (DicType)map.get("dictType");
		try {
			dc.setDic_type_id(dicTypeDao.getSeqId());
		} catch (Exception e) {
			throw new BusinessException("��ȡ�ֵ�����Seqidʧ��"+e.getMessage());
		}
		try {
			dicTypeDao.save(dc);
		} catch (Exception e) {
			throw new BusinessException("�����ֵ�����ʧ��"+e.getMessage());
		}
		Map returnMap = new HashMap();
		returnMap.put("result", "success");
		
		return returnMap;
	}
	/**
	 * 
	 *  ģ����ѯ�ֵ�����  ���� map.put("searchStr",searchStr);
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
	 *  �����ֵ����Ҫ�����༭�ֵ����ͣ�
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
			throw new BusinessException("�����ֵ�����ʧ�ܣ�"+e.getMessage());
		}
		Map returnMap = new HashMap();
		returnMap.put("result", "success");
		returnMap.put("message", "���ݸ��³ɹ�");
		return returnMap;
	}

	/**
	 * 
	 *  �������÷�����ʵ�ֹ��ܣ�ɾ���ֵ��� ����   ��ɾ���ֵ��.
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
			//throw new BusinessException("ɾ���ֵ����ͳ���"+e.getMessage());
			e.printStackTrace();
		}
		Map resultMap = new HashMap();
		resultMap.put("result","success");
		return resultMap;
	}

	/**
	 * ���ֵ�༭�������Ӧ�õ���̨���ݿ�
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
		
		//������������
		List news = (List)dataMap.get("inserted");
		DicItem di = null; 
		for (int i = 0; i < news.size(); i ++) {
			temp = new RowImpl((Map)news.get(i));
			di = new DicItem();
			try {
				di.setDic_item_id(dicItemDao.getSeqId());
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("��ȡ�ֵ���seq id����"+e.getMessage());
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
				throw new BusinessException("�����ֵ������"+e.getMessage());
			}
		}
		
		//�����޸�����
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
				throw new BusinessException("�����ֵ������"+e.getMessage());
			}
			
			//di.set
			//logger.info("update:" + temp);
			//getPlanSupportDao().update(temp, "plat_dict_item");
		}
		
		//����ɾ������
		List dels = (List)dataMap.get("deleted");
		for (int i = 0; i < dels.size(); i ++) {
			temp = new RowImpl((Map)dels.get(i));
			di = new DicItem();
			di.setDic_item_id(temp.getString("dic_item_id"));
			try {
				dicItemDao.delete(di);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("ɾ���ֵ������"+e.getMessage());
			}
		}
		
		result.put("success",true);
		return result;
	}

	/**
	 * 
	 * ͨ���ֵ�����ID��ȡ�ֵ���
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
			throw new BusinessException("ͨ���ֵ�����ID��ȡ�ֵ���ʧ��"+e.getMessage());
		}
		return dictItems;
	}

	
	/**
	 * 
	 * �����ֵ���
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
			throw new BusinessException("��ȡ�ֵ���Seqidʧ��"+e.getMessage());
		}
		try {
			dicItemDao.save(di);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("�����ֵ���ʧ��"+e.getMessage());
		}
		Map returnMap = new HashMap();
		returnMap.put("result", "success");
		
		return returnMap;
	}

	/**
	 * 
	 * ��ȡ�ֵ���
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
	 * ͨ���ֵ�code���ֵ�����code��ȡ�ֵ�value
	 *
	 * @author Joyon
	 * @param code		�ֵ���code
	 * @param dic_type_code  �ֵ�����code
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
			LogUtil.error("DictFacade.getDictItemNameByCodeAndTypeCode  ��ȡ�ֵ�������"+e.getMessage());
		}
		if(dicItem == null){
			return null;
		}
		return dicItem.getDic_item_value();
	}
	
	/**
	 * 
	 * ͨ���ֵ���code��ȡ�ֵ�������
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
				LogUtil.error("DictFacade.getDictitemByItemcode ͨ���ֵ���code��ȡ�ֵ����ݳ���  itemcode:"+dictitemcode+" error message:"+e.getMessage());
			}
		return dicItem;
	}

}


