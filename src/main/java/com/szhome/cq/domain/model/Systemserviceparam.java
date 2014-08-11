package com.szhome.cq.domain.model;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springjdbc.annotation.BaseDomain;
import com.springjdbc.annotation.Entity;
import com.springjdbc.annotation.Id;
import com.springjdbc.annotation.Table;
import com.szhome.cq.web.annotation.Operator;
import com.szhome.cq.web.annotation.WhereField;

@Scope("prototype")
@Component
@Entity
@Table(name = "t_systemserviceparam")
public class Systemserviceparam extends BaseDomain<Systemserviceparam> {
	@Id
	private String paramid;

	@WhereField(express = "tss.paramcode", operator = Operator.LIKE)
	private String paramcode;

	@WhereField(express = "tss.paramname", operator = Operator.LIKE)
	private String paramname;

	private String paramvalue;

	@WhereField(express = "tss.paramtypecode", operator = Operator.EQUAL)
	private String paramtypecode;

	@WhereField(express = "tss.noperatorcode", operator = Operator.EQUAL)
	private String noperatorcode;

	private String operatorcode;

	private Date createtime;

	@WhereField(express = "tss.startdate", operator = Operator.EQUALTHAN)
	private Date startdate;

	@WhereField(express = "tss.enddate", operator = Operator.EQUALLESS)
	private Date enddate;

	@WhereField(express = "tss.iseffect", operator = Operator.LIKE)
	private String iseffect;

	private String remark;
	private String constantval;

	public String getConstantval() {
		return constantval;
	}

	public void setConstantval(String constantval) {
		this.constantval = constantval;
	}

	public Systemserviceparam() {
		super();
		this.t = Systemserviceparam.class;
	}

	public Systemserviceparam(String paramid) {
		super();
		this.paramid = paramid;
	}

	public void setParamid(String paramid) {
		this.paramid = paramid;
	}

	public void setParamcode(String paramcode) {
		this.paramcode = paramcode;
	}

	public void setParamname(String paramname) {
		this.paramname = paramname;
	}

	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}

	public void setParamtypecode(String paramtypecode) {
		this.paramtypecode = paramtypecode;
	}

	public void setNoperatorcode(String noperatorcode) {
		this.noperatorcode = noperatorcode;
	}

	public void setOperatorcode(String operatorcode) {
		this.operatorcode = operatorcode;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public void setIseffect(String iseffect) {
		this.iseffect = iseffect;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getParamid() {
		return paramid;
	}

	public String getParamcode() {
		return paramcode;
	}

	public String getParamname() {
		return paramname;
	}

	public String getParamvalue() {
		return paramvalue;
	}

	public String getParamtypecode() {
		return paramtypecode;
	}

	public String getNoperatorcode() {
		return noperatorcode;
	}

	public String getOperatorcode() {
		return operatorcode;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public Date getStartdate() {
		return startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public String getIseffect() {
		return iseffect;
	}

	public String getRemark() {
		return remark;
	}

}
