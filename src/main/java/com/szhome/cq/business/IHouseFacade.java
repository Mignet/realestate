/**
 * Project Name:dxtx_re
 * File Name:IHouseFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-03-13����6:17:25
 * Copyright (c) 2013, All Rights Reserved.
 *
*/
package com.szhome.cq.business;

import java.util.List;
import java.util.Map;


/**
 * InterfaceName:IHouseFacade <br/>
 * Date:     2014-03-27 ����2:17:25 <br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IHouseFacade {
/**
 * �������������õ��ڵ���Ϣ
 * getParcelInfosByOptions <br/>
 * @author Sam
 * @param pmmap
 * @return
 * @throws Exception
 * @since    JDK 1.6
 */
 public List<Map<String,Object>> getHouseInfosByOptions(Map<String,Object> pmmap) throws Exception;
}

