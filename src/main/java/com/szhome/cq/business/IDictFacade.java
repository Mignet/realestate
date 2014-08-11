/**
 * Project Name:dxtx_re
 * File Name:IDictFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-1-2����3:30:02
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
 * Function: �ֵ�Facade <br/>
 * Date:     2014-1-2 ����3:30:02 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IDictFacade {

	/**
	 * �����ֵ�Code��ȡ�ֵ���
	 */
	public List<Map<String,Object>> getDictByCode(Map map);
	
	/**
	 * 
	 * getItemsByClassId:(����ֵ���ͨ���ֵ�����ID)
	 *
	 * @author Joyon
	 * @param map
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getItemsByClassId(Map map);
	

	/**
	 * �����ֵ���������
	 */
	public Map saveClass(Map map);
	
	/**
	 * �޸��ֵ�����
	 */
	public Map updateClass(Map map);
	
	
	/**
	 * 
	 * deleteClass:(ɾ���ֵ�����)
	 *
	 * @author Joyon
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public Map deleteClass(Map map);
	
	/**
	 * �����ֵ���
	 */
	public Map saveItem(Map map);
	
	/**
	 * ���ֵ�༭�������Ӧ�õ���̨���ݿ�
	 */
	public Map applyEdit(Map map) ;
	
	/**
	 * �����ֵ����ƺ��ֵ�Codeģ�������ֵ�����
	 */
	public List<Map<String, Object>> searchDictClass(Map map);
	
	
	
	
	
	/**
	 * �����ֵ���������
	 */
	public Map saveType(Map map);
	
	/**
	 * �����ֵ����ƺ��ֵ�Codeģ�������ֵ�����
	 */
	public List<Map<String, Object>> searchDictType(Map paramMap);
	
	/**
	 * �޸��ֵ�����
	 */
	public Map updateType(Map map);
	
	/**
	 * ɾ���ֵ�����
	 */
	public Map deleteType(Map map);

	/**
	 * ���ֵ�༭�������Ӧ�õ���̨���ݿ�
	 */
	public Map applyEditItem(Map map);
	
	/**
	 * 
	 * getItemsByTypeId:(ͨ���ֵ�����ID��ȡ�ֵ���)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getItemsByTypeId(Map paramMap);
	
	/**
	 * 
	 * saveDicItem:(�����ֵ���). 
	 *
	 * @author Joyon
	 * @param map
	 * @return
	 * @since JDK 1.6
	 */
	public Map saveDicItem(Map map);

	/**
	 * 
	 * getNewDictByCode:(��ȡ�ֵ���).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getNewDictByCode(Map paraMap);
	
	/**
	 * 
	 * getDictItemNameByCodeAndTypeCode:(ͨ���ֵ�code���ֵ�����code��ȡ�ֵ�value).
	 *
	 * @author Joyon
	 * @param code		�ֵ���code
	 * @param dic_type_code  �ֵ�����code
	 * @return
	 * @since JDK 1.6
	 */
	public String getDictItemNameByCodeAndTypeCode(String code,String dic_type_code);
	
	/**
	 * 
	 * ͨ���ֵ���code��ȡ�ֵ�������
	 *
	 * @author Joyon
	 * @param dictitemcode
	 * @return
	 * @since JDK 1.6
	 */
	public DicItem getDictitemByItemcode(String dictitemcode);
}


