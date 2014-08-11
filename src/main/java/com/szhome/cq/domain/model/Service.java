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
@Table(name = "T_SERVICE")
public class Service extends BaseDomain<Service> {
	@Id
	private String sid;
	private String sname;
	private String category;
	private String regflag;
	private String info;
	private String remark;

	public Service(){
		super();
		this.t = Service.class;
	}

	public Service(String sid){
		super();
		this.sid = sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setRegflag(String regflag) {
		this.regflag = regflag;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSid() {
		return sid;
	}

	public String getSname() {
		return sname;
	}

	public String getCategory() {
		return category;
	}

	public String getRegflag() {
		return regflag;
	}

	public String getInfo() {
		return info;
	}

	public String getRemark() {
		return remark;
	}

}
