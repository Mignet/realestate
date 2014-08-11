package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.springjdbc.annotation.Page;
import com.szhome.cq.domain.model.Operationlog;
import com.szhome.cq.domain.model.Systemserviceparam;

/**
 * 日志管理Interface
 * @author  Mignet
 */
public interface ILogFacade {

	 /**
	   * 登记操作日志
	   * modify_remark: ****   
	   * modified by: Mignet
	   * @param logtypecode      日志类型代码
	   * @param opercode         操作员代码
	   * @param operobjname      对象名称
	   * @param operobjvalue     对象值
	   * @param action           操作行为
	   * @param result           操作结果
	   * @param content          操作内容
	   * @param operatorip       操作员IP
	   */
	void addOperationlog(String logtypecode, String opercode, String operobjname, String operobjvalue, String action,
			String result, String content, String operatorip);

	//查询操作日志信息
	Page<Operationlog> queryOperationlogInfoPage(Map<String, Object> param, int pageNo, int pageSize);
	//根据日志表示查询详细信息
	Operationlog getOperationlogInfoBylogid(String logid);

}

