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
@Table(name = "bdc_djb_syqbf")
public class RegisterOwnershipPart extends BaseDomain<RegisterOwnershipPart> {
	private double procdefid;
	@Id
	private double djbsyqbfbid;
	private double qlrjhbid;
	private double djbjbxxbid;
	private double b_deleteflag;
	private String djbh;
	private String gyqk;
	private String syqzh;
	private String djjg;
	private String djsj;
	private String dbr;
	private String fj;

	public RegisterOwnershipPart(){
		super();
		this.t = RegisterOwnershipPart.class;
	}

	public RegisterOwnershipPart(double djbsyqbfbid){
		super();
		this.djbsyqbfbid = djbsyqbfbid;
	}

	public void setProcdefid(double procdefid) {
		this.procdefid = procdefid;
	}

	public void setDjbsyqbfbid(double djbsyqbfbid) {
		this.djbsyqbfbid = djbsyqbfbid;
	}

	public void setQlrjhbid(double qlrjhbid) {
		this.qlrjhbid = qlrjhbid;
	}

	public void setDjbjbxxbid(double djbjbxxbid) {
		this.djbjbxxbid = djbjbxxbid;
	}

	public void setB_deleteflag(double b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setDjbh(String djbh) {
		this.djbh = djbh;
	}

	public void setGyqk(String gyqk) {
		this.gyqk = gyqk;
	}

	public void setSyqzh(String syqzh) {
		this.syqzh = syqzh;
	}

	public void setDjjg(String djjg) {
		this.djjg = djjg;
	}

	public void setDjsj(String djsj) {
		this.djsj = djsj;
	}

	public void setDbr(String dbr) {
		this.dbr = dbr;
	}

	public void setFj(String fj) {
		this.fj = fj;
	}

	public double getProcdefid() {
		return procdefid;
	}

	public double getDjbsyqbfbid() {
		return djbsyqbfbid;
	}

	public double getQlrjhbid() {
		return qlrjhbid;
	}

	public double getDjbjbxxbid() {
		return djbjbxxbid;
	}

	public double getB_deleteflag() {
		return b_deleteflag;
	}

	public String getDjbh() {
		return djbh;
	}

	public String getGyqk() {
		return gyqk;
	}

	public String getSyqzh() {
		return syqzh;
	}

	public String getDjjg() {
		return djjg;
	}

	public String getDjsj() {
		return djsj;
	}

	public String getDbr() {
		return dbr;
	}

	public String getFj() {
		return fj;
	}

}
