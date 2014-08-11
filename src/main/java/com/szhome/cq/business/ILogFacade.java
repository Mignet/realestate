package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.springjdbc.annotation.Page;
import com.szhome.cq.domain.model.Operationlog;
import com.szhome.cq.domain.model.Systemserviceparam;

/**
 * ��־����Interface
 * @author  Mignet
 */
public interface ILogFacade {

	 /**
	   * �Ǽǲ�����־
	   * modify_remark: ****   
	   * modified by: Mignet
	   * @param logtypecode      ��־���ʹ���
	   * @param opercode         ����Ա����
	   * @param operobjname      ��������
	   * @param operobjvalue     ����ֵ
	   * @param action           ������Ϊ
	   * @param result           �������
	   * @param content          ��������
	   * @param operatorip       ����ԱIP
	   */
	void addOperationlog(String logtypecode, String opercode, String operobjname, String operobjvalue, String action,
			String result, String content, String operatorip);

	//��ѯ������־��Ϣ
	Page<Operationlog> queryOperationlogInfoPage(Map<String, Object> param, int pageNo, int pageSize);
	//������־��ʾ��ѯ��ϸ��Ϣ
	Operationlog getOperationlogInfoBylogid(String logid);

}

