package com.szhome.cq.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.szhome.cq.domain.model.Operationlog;
import com.szhome.cq.domain.model.Systemserviceparam;
import com.szhome.cq.utils.Constants;
import com.szhome.cq.utils.DateUtils;
import com.szhome.cq.utils.SequenceUtil;

/**
 * ��־ģ��Service
 * @author  Mignet
 */
@Component
@Scope("prototype")
@Transactional
public class ServiceLog {

	@Autowired
	private Operationlog operationlog;
	
	public void addLog(String operatorcode,String orderid,String content){
		this.addOperationlog("0000",
				   operatorcode,
				   "0000",
				   "0000",
				   "�޸�",
				   "1",
				   content,
				   "0000");
	}

	/**
	 * �Ǽǲ�����־
	 * modify_remark: ****   
	 * @param logtypecode      ��־���ʹ���
	 * @param opercode         ����Ա����
	 * @param operobjname      ��������
	 * @param operobjvalue     ����ֵ
	 * @param action           ������Ϊ
	 * @param result           �������
	 * @param content          ��������
	 * @param serviceflownum   ҵ����ˮ��
	 * @param operatorip       ����ԱIP
	 */
	public void addOperationlog(String logtypecode, String opercode, String operobjname, String operobjvalue,
			String action, String result, String content, String operatorip) {
		Operationlog logtemp = new Operationlog();
		logtemp.setAction(action);//������Ϊ
		logtemp.setContent(content);//��������
		logtemp.setLogid(SequenceUtil.getGlobalSeqID());//��־��ʶ
		logtemp.setLogtypecode(logtypecode);//��־���ʹ���
		logtemp.setOpercode(opercode);//����Ա����
		logtemp.setOperobjname(operobjname);//��������
		logtemp.setOpertime(DateUtils.getCurTime());//����ʱ��
		logtemp.setResult(result);//�������
		logtemp.setOperobjvalue(operobjvalue);//����ֵ
//		logtemp.setServiceflownum(serviceflownum);//ҵ����ˮ��
		logtemp.setOperatorip(operatorip);//����ԱIP
		this.operationlog.save(logtemp);
	}
	
}

