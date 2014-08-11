package com.szhome.cq.business.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.springjdbc.annotation.Page;
import com.szhome.cq.business.ILogFacade;
import com.szhome.cq.domain.model.Operationlog;
import com.szhome.cq.domain.service.ServiceLog;
import com.szhome.cq.utils.PaginateWhere;
import com.szhome.cq.web.annotation.WhereBase;

/**
 * ��־����
 * @author  Mignet
 */
@Component
@Transactional
@Scope("prototype")
public class LogFacade implements ILogFacade {

	@Autowired
	private ServiceLog serviceLog;
	@Autowired
	private Operationlog operationlog;

	/**
	   * �Ǽǲ�����־
	   * @param logtypecode      ��־���ʹ���
	   * @param opercode         ����Ա����
	   * @param operobjname      ��������
	   * @param operobjvalue     ����ֵ
	   * @param action           ������Ϊ
	   * @param result           �������
	   * @param content          ��������
	   * @param operatorip       ����ԱIP
	   */
	@Override
	public void addOperationlog(String logtypecode, String opercode, String operobjname, String operobjvalue,
			String action, String result, String content, String operatorip) {
		this.serviceLog.addOperationlog(logtypecode, opercode, operobjname, operobjvalue, action, result, content,
				operatorip);

	}

	//��ѯ������־��Ϣ
	@Override
	public Page<Operationlog> queryOperationlogInfoPage(Map<String, Object> param, int pageNo, int pageSize) {
		Map<WhereBase, String> baseMap = new HashMap<WhereBase, String>();
		baseMap.put(WhereBase.WHEREBASE, "where 1=1");
		baseMap.put(WhereBase.ORDERBY, "order by tol.opertime desc");
		PaginateWhere paginateWhere = new PaginateWhere(Operationlog.class, baseMap, param);
		return this.operationlog.queryObjectPageBykeyForOracle("Sysmanage.queryOperationlogInfoPage", paginateWhere
				.getWhereSql(), paginateWhere.getParaMap(), Operationlog.class, pageNo, pageSize);
	}

	@Override
	public Operationlog getOperationlogInfoBylogid(String logid) {
		return this.operationlog.get(new Operationlog(logid));
	}
}

