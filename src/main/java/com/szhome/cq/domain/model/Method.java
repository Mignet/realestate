package com.szhome.cq.domain.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springjdbc.annotation.BaseDomain;
import com.springjdbc.annotation.Entity;
import com.springjdbc.annotation.Id;
import com.springjdbc.annotation.Table;

@Scope("prototype")
@Component
@Entity
@Table(name = "T_METHOD")
public class Method extends BaseDomain<Method> {
	@Id
	private String mid;
	private String sid;
	private String mname;
	private String parameters;
	private String returntype;
	private String info;
	private String inlineflag;
	private String remark;

	public Method(){
		super();
		this.t = Method.class;
	}

	public Method(String mid){
		super();
		this.mid = mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public void setReturntype(String returntype) {
		this.returntype = returntype;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setInlineflag(String inlineflag) {
		this.inlineflag = inlineflag;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMid() {
		return mid;
	}

	public String getSid() {
		return sid;
	}

	public String getMname() {
		return mname;
	}

	public String getParameters() {
		return parameters;
	}

	public String getReturntype() {
		return returntype;
	}

	public String getInfo() {
		return info;
	}

	public String getInlineflag() {
		return inlineflag;
	}

	public String getRemark() {
		return remark;
	}

}
