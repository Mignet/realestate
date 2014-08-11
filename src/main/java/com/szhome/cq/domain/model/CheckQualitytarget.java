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
@Table(name = "CHK_QUALITY_TARGET")
public class CheckQualitytarget extends BaseDomain<CheckQualitytarget> {
	@Id
	private String qua_tar_id;
	private String qua_index;
	private String b_deleteflag;

	public CheckQualitytarget(){
		super();
		this.t = CheckQualitytarget.class;
	}

	public CheckQualitytarget(String qua_tar_id){
		super();
		this.qua_tar_id = qua_tar_id;
	}

	public void setQua_tar_id(String qua_tar_id) {
		this.qua_tar_id = qua_tar_id;
	}

	public void setQua_index(String qua_index) {
		this.qua_index = qua_index;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public String getQua_tar_id() {
		return qua_tar_id;
	}

	public String getQua_index() {
		return qua_index;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

}

