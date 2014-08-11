/**
 * Project Name:dxtx_re
 * File Name:MonitorTest.java
 * Package Name:com.szhome.cq.business
 * Date:2014-4-10ионГ9:23:53
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
 */

package com.szhome.cq.business;

import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IMonitorFacade;

import junit.framework.TestCase;

/**
 * ClassName:MonitorTest <br/>
 * Function: JUnit Test. <br/>
 * Date: 2014-4-10 ионГ9:23:53 <br/>
 * 
 * @author dxtx
 * @version
 * @since JDK 1.6
 * @see
 */
public class MonitorTest extends TestCase {

	private IMonitorFacade iaf = null;

	public void setUp() {
		iaf = FacadeFactory.getMonitorFacade();
	}

	public void testInitAllAPIs() {
		iaf.initAllAPIs();
	}
}

