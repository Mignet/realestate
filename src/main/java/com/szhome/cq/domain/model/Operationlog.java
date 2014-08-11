package com.szhome.cq.domain.model;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springjdbc.annotation.BaseDomain;
import com.springjdbc.annotation.Entity;
import com.springjdbc.annotation.Id;
import com.springjdbc.annotation.Table;
import com.springjdbc.annotation.Transient;
import com.szhome.cq.web.annotation.Operator;
import com.szhome.cq.web.annotation.WhereField;

@Scope("prototype")
@Component
@Entity
@Table(name = "t_operationlog")
public class Operationlog extends BaseDomain<Operationlog> {
	@Id
	private String logid;
	@WhereField(express = "logtypecode", operator = Operator.EQUAL)
	private String logtypecode;
	@WhereField(express = "opercode", operator = Operator.LIKE)
	private String opercode;
	@WhereField(express = "operobjname", operator = Operator.LIKE)
	private String operobjname;
	@WhereField(express = "operobjvalue", operator = Operator.EQUAL)
	private String operobjvalue;
	@WhereField(express = "action", operator = Operator.LIKE)
	private String action;
	@WhereField(express = "opertime", operator = Operator.EQUALTHAN)
	private Date opertime;
	@WhereField(express = "opertime", operator = Operator.EQUALLESS)
	@Transient
	private Date endtime;
	@WhereField(express = "result", operator = Operator.EQUAL)
	private String result;
	@WhereField(express = "content", operator = Operator.LIKE)
	private String content;
//	private String serviceflownum;
	private String operatorip;
	@Transient
	private String opertimeshow;//操作时间展示

	public Operationlog() {
		this.t = Operationlog.class;
	}

	public Operationlog(String logid) {
		this.logid = logid;
	}

	/*public String getServiceflownum() {
		return this.serviceflownum;
	}
*/
	public String getOperatorip() {
		return this.operatorip;
	}
/*
	public void setServiceflownum(String serviceflownum) {
		this.serviceflownum = serviceflownum;
	}
*/
	public void setOperatorip(String operatorip) {
		this.operatorip = operatorip;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public void setLogtypecode(String logtypecode) {
		this.logtypecode = logtypecode;
	}

	public void setOpercode(String opercode) {
		this.opercode = opercode;
	}

	public void setOperobjname(String operobjname) {
		this.operobjname = operobjname;
	}

	public void setOperobjvalue(String operobjvalue) {
		this.operobjvalue = operobjvalue;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLogid() {
		return this.logid;
	}

	public String getLogtypecode() {
		return this.logtypecode;
	}

	public String getOpercode() {
		return this.opercode;
	}

	public String getOperobjname() {
		return this.operobjname;
	}

	public String getOperobjvalue() {
		return this.operobjvalue;
	}

	public String getAction() {
		return this.action;
	}

	public Date getOpertime() {
		return this.opertime;
	}

	public String getResult() {
		return this.result;
	}

	public String getContent() {
		return this.content;
	}

	public Date getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getOpertimeshow() {
		return this.opertimeshow;
	}

	public void setOpertimeshow(String opertimeshow) {
		this.opertimeshow = opertimeshow;
	}

}
