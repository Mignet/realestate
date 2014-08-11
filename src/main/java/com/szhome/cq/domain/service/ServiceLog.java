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
 * 日志模块Service
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
				   "修改",
				   "1",
				   content,
				   "0000");
	}

	/**
	 * 登记操作日志
	 * modify_remark: ****   
	 * @param logtypecode      日志类型代码
	 * @param opercode         操作员代码
	 * @param operobjname      对象名称
	 * @param operobjvalue     对象值
	 * @param action           操作行为
	 * @param result           操作结果
	 * @param content          操作内容
	 * @param serviceflownum   业务流水号
	 * @param operatorip       操作员IP
	 */
	public void addOperationlog(String logtypecode, String opercode, String operobjname, String operobjvalue,
			String action, String result, String content, String operatorip) {
		Operationlog logtemp = new Operationlog();
		logtemp.setAction(action);//操作行为
		logtemp.setContent(content);//操作内容
		logtemp.setLogid(SequenceUtil.getGlobalSeqID());//日志标识
		logtemp.setLogtypecode(logtypecode);//日志类型代码
		logtemp.setOpercode(opercode);//操作员代码
		logtemp.setOperobjname(operobjname);//对象名称
		logtemp.setOpertime(DateUtils.getCurTime());//操作时间
		logtemp.setResult(result);//操作结果
		logtemp.setOperobjvalue(operobjvalue);//对象值
//		logtemp.setServiceflownum(serviceflownum);//业务流水号
		logtemp.setOperatorip(operatorip);//操作员IP
		this.operationlog.save(logtemp);
	}
	
}

