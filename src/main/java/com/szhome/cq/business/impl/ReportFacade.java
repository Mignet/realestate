/**
 * Project Name:dxtx_re
 * File Name:ReportFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-1-2下午6:16:06
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.business.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.szhome.cq.business.IReportFacade;

/**
 * 报表facade
 * Date:     2014-1-2 下午6:16:06 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class ReportFacade implements IReportFacade {

	@Autowired
	private DataSource datasource;
	public Connection getConn() {
		Connection conn = null;
		try {
			conn = datasource.getConnection();
		} catch (SQLException e) {
		
			e.printStackTrace();
			
		}

		return conn;
	}

}


