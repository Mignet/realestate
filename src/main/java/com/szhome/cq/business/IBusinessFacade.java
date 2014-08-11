/**
 * 用于业务办理过程的事项处理
 * Project Name:dxtx_re
 * File Name:IBusinessFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-07-25 上午11:27:12
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/
package com.szhome.cq.business;

import java.util.Map;

import com.szhome.cq.domain.model.BusScanner;

/**
 * @author Sam
 *
 */
public interface IBusinessFacade {

	/**
	 * 
	 */
	public void saveBusScanner(BusScanner bs) throws Exception;
	
	/**
	 * @param paramMap
	 * @throws Exception
	 */
	public void batch_saveBusScanners(Map<String,Object> paramMap) throws Exception;
	
	/**
	 * @param paramMap
	 */
	public BusScanner selectBusScannersby(Map<String,Object> paramMap);

}

