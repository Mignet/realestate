/**
 * Project Name:dxtx_re
 * File Name:IBookFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-03-20 ÏÂÎç5:10:12
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/
package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

/**
 * ClassName:IBookFacade <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-03-20 ÏÂÎç5:10:23 <br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IBookFacade {
	
/**
 * @param param
 * @return
 * @throws Exception
 */
public Map<String, Object> getBookNatualInfo(Map<String, Object> param) throws Exception;
/**
 * @param param
 * @return
 * @throws Exception
 */
public List<Map<String,Object>> getBookUserInfoLst(Map<String,Object> param) throws Exception;
/**
 * @param param
 * @return
 * @throws Exception
 */
public List<Map<String,Object>> getBookOwnershipInfoLst(Map<String,Object> param) throws Exception;
/**
 * @param param
 * @return
 * @throws Exception
 */
public List<Map<String,Object>> getBookHolderLst(Map<String,Object> param) throws Exception;
/**
 * @param param
 * @return
 * @throws Exception
 */
public List<Map<String,Object>> getBookMortInfoLst(Map<String,Object> param) throws Exception;
/**
 * @param param
 * @return
 * @throws Exception
 */
public List<Map<String,Object>> getBookAttachInfoLst(Map<String,Object> param) throws Exception;
/**
 * @param param
 * @return
 * @throws Exception
 */
public List<Map<String,Object>> getBookDissentInfoLst(Map<String,Object> param) throws Exception;
/**
 * @param param
 * @return
 * @throws Exception
 */
public List<Map<String,Object>> getBookEasementInfoLst(Map<String,Object> param) throws Exception;
/**
 * @param param
 * @return
 * @throws Exception
 */
public List<Map<String,Object>> getBookHouseAdvInfoLst(Map<String,Object> param) throws Exception;

/**
 * @param param
 * @return
 * @throws Exception
 */
public List<Map<String,Object>> getUserightHolderLst(Map<String, Object> sqlMap,Map<String,Object> paramMap) throws Exception;

/**
 * @param param
 * @return
 * @throws Exception
 */
public List<Map<String,Object>> getOwnershipHolderLst(Map<String, Object> sqlMap,Map<String,Object> paramMap) throws Exception;

/**
 * @param sqlMap
 * @param paramMap
 * @return
 * @throws Exception
 */
public List<Map<String, Object>> getUserightInfoLst(
		Map<String, Object> sqlMap,Map<String,Object> paramMap) throws Exception ;

/**
 * @param sqlMap
 * @param paramMap
 * @return
 * @throws Exception
 */
public List<Map<String, Object>> getOwnershipInfoLst(
		Map<String, Object> sqlMap,Map<String,Object> paramMap) throws Exception;

}

