/**
 * Project Name:dxtx_re
 * File Name:IDictFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-1-2下午3:30:02
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plan.commons.Row;
import com.plan.web.JsonResult;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.domain.model.DicItem;
import com.szhome.cq.domain.model.DictItem;

/**
 * ClassName:IDictFacade <br/>
 * Function: 字典Facade <br/>
 * Date:     2014-1-2 下午3:30:02 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IDictFacade {

	/**
	 * 根据字典Code获取字典项
	 */
	public List<Map<String,Object>> getDictByCode(Map map);
	
	/**
	 * 
	 * getItemsByClassId:(获得字典项通过字典类型ID)
	 *
	 * @author Joyon
	 * @param map
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getItemsByClassId(Map map);
	

	/**
	 * 保存字典类型数据
	 */
	public Map saveClass(Map map);
	
	/**
	 * 修改字典类型
	 */
	public Map updateClass(Map map);
	
	
	/**
	 * 
	 * deleteClass:(删除字典类型)
	 *
	 * @author Joyon
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public Map deleteClass(Map map);
	
	/**
	 * 保存字典项
	 */
	public Map saveItem(Map map);
	
	/**
	 * 将字典编辑后的数据应用到后台数据库
	 */
	public Map applyEdit(Map map) ;
	
	/**
	 * 根据字典名称和字典Code模糊检索字典数据
	 */
	public List<Map<String, Object>> searchDictClass(Map map);
	
	
	
	
	
	/**
	 * 保存字典类型数据
	 */
	public Map saveType(Map map);
	
	/**
	 * 根据字典名称和字典Code模糊检索字典数据
	 */
	public List<Map<String, Object>> searchDictType(Map paramMap);
	
	/**
	 * 修改字典类型
	 */
	public Map updateType(Map map);
	
	/**
	 * 删除字典类型
	 */
	public Map deleteType(Map map);

	/**
	 * 将字典编辑后的数据应用到后台数据库
	 */
	public Map applyEditItem(Map map);
	
	/**
	 * 
	 * getItemsByTypeId:(通过字典类型ID获取字典项)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getItemsByTypeId(Map paramMap);
	
	/**
	 * 
	 * saveDicItem:(保存字典项). 
	 *
	 * @author Joyon
	 * @param map
	 * @return
	 * @since JDK 1.6
	 */
	public Map saveDicItem(Map map);

	/**
	 * 
	 * getNewDictByCode:(获取字典项).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getNewDictByCode(Map paraMap);
	
	/**
	 * 
	 * getDictItemNameByCodeAndTypeCode:(通过字典code和字典类型code获取字典value).
	 *
	 * @author Joyon
	 * @param code		字典项code
	 * @param dic_type_code  字典类型code
	 * @return
	 * @since JDK 1.6
	 */
	public String getDictItemNameByCodeAndTypeCode(String code,String dic_type_code);
	
	/**
	 * 
	 * 通过字典项code获取字典项数据
	 *
	 * @author Joyon
	 * @param dictitemcode
	 * @return
	 * @since JDK 1.6
	 */
	public DicItem getDictitemByItemcode(String dictitemcode);
}


