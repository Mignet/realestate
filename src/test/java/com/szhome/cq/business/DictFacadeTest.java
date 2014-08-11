/**
 * Project Name:dxtx_re
 * File Name:DictFacadeTest.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-6-23上午10:57:40
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IDictFacade;
import com.szhome.cq.utils.JsonUtil;

/**
 * ClassName:DictFacadeTest <br/>
 * Function: 测试字典服务. <br/>
 * Date:     2014-6-23 上午10:57:40 <br/>
 * @author   dxtx
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class DictFacadeTest extends TestCase {
	
	private IDictFacade iaf = null;
	private Map map = new HashMap();

	public void setUp() {
		iaf = FacadeFactory.getDictFacade();
		map.put("class_code", "house_usage");
	}

	public void testGetDictByCode() {
		List<Map<String, Object>> list = iaf.getDictByCode(map);
		System.out.println(JsonUtil.object2json(list));
	}

	public void testGetItemsByClassId() {
		fail("Not yet implemented");
	}

	public void testSaveClass() {
		fail("Not yet implemented");
	}

	public void testUpdateClass() {
		fail("Not yet implemented");
	}

	public void testDeleteClass() {
		fail("Not yet implemented");
	}

	public void testSaveItem() {
		fail("Not yet implemented");
	}

	public void testApplyEdit() {
		fail("Not yet implemented");
	}

	public void testSearchDictClass() {
		fail("Not yet implemented");
	}

	public void testSaveType() {
		fail("Not yet implemented");
	}

	public void testSearchDictType() {
		fail("Not yet implemented");
	}

	public void testUpdateType() {
		fail("Not yet implemented");
	}

	public void testDeleteType() {
		fail("Not yet implemented");
	}

	public void testApplyEditItem() {
		fail("Not yet implemented");
	}

	public void testGetItemsByTypeId() {
		fail("Not yet implemented");
	}

	public void testSaveDicItem() {
		fail("Not yet implemented");
	}

	public void testGetNewDictByCode() {
		fail("Not yet implemented");
	}

	public void testGetDictItemNameByCodeAndTypeCode() {
		fail("Not yet implemented");
	}

	public void testGetDictitemByItemcode() {
		fail("Not yet implemented");
	}

}


