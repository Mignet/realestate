/**
 * Project Name:dxtx_re
 * File Name:IParcelFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-03-13下午6:17:25
 * Copyright (c) 2013, All Rights Reserved.
 *
*/
package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

/**
 * InterfaceName:IParcelFacade <br/>
 * Date:     2014-03-13下午6:17:25 <br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IParcelFacade {
	
 /**
  * 得到所有宗地信息关联
  * getAllParcelInfo <br/>
  * @author Sam
  * @return
  * @throws Exception
  * @since    JDK 1.6
  */
public List<Map<String,Object>> getAllParcelInfo() throws Exception;

/**
 * 根据搜索条件得到宗地信息
 * getParcelInfosByOptions <br/>
 * @author Sam
 * @param pmmap
 * @return
 * @throws Exception
 * @since    JDK 1.6
 */
public List<Map<String,Object>> getParcelInfosByOptions(Map<String,Object> pmmap) throws Exception;
}

