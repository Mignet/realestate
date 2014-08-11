package com.szhome.cq.domain.model;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springjdbc.annotation.BaseDomain;
import com.springjdbc.annotation.Entity;
import com.springjdbc.annotation.Id;
import com.springjdbc.annotation.Table;

@Scope("prototype")
@Component
@Entity
@Table(name = "BDC_YWBL_SUOYQDJXX")
public class HouseOwnership extends BaseDomain<HouseOwnership> {
	@Id
	private double syqxxbid;
	private double lcslbid;
	private double b_deleteflag;
	private String fwbh;
	private String fdcmc;
	private String qdfs;
	private String fwzl;
	private String xmmc;
	private String dh;
	private String fh;
	private String fwjg;
	private double zcs;
	private double jzmj;
	private double tnjzmj;
	private double zybfjzmj;
	private String ghyt;
	private Date jgsj;
	private String fwxz;
	private Date djsj;
	private String fj;

	public HouseOwnership(){
		super();
		this.t = HouseOwnership.class;
	}

	public HouseOwnership(double syqxxbid){
		super();
		this.syqxxbid = syqxxbid;
	}

	public void setSyqxxbid(double syqxxbid) {
		this.syqxxbid = syqxxbid;
	}

	public void setLcslbid(double lcslbid) {
		this.lcslbid = lcslbid;
	}

	public void setB_deleteflag(double b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setFwbh(String fwbh) {
		this.fwbh = fwbh;
	}

	public void setFdcmc(String fdcmc) {
		this.fdcmc = fdcmc;
	}

	public void setQdfs(String qdfs) {
		this.qdfs = qdfs;
	}

	public void setFwzl(String fwzl) {
		this.fwzl = fwzl;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	public void setFh(String fh) {
		this.fh = fh;
	}

	public void setFwjg(String fwjg) {
		this.fwjg = fwjg;
	}

	public void setZcs(double zcs) {
		this.zcs = zcs;
	}

	public void setJzmj(double jzmj) {
		this.jzmj = jzmj;
	}

	public void setTnjzmj(double tnjzmj) {
		this.tnjzmj = tnjzmj;
	}

	public void setZybfjzmj(double zybfjzmj) {
		this.zybfjzmj = zybfjzmj;
	}

	public void setGhyt(String ghyt) {
		this.ghyt = ghyt;
	}

	public void setJgsj(Date jgsj) {
		this.jgsj = jgsj;
	}

	public void setFwxz(String fwxz) {
		this.fwxz = fwxz;
	}

	public void setDjsj(Date djsj) {
		this.djsj = djsj;
	}

	public void setFj(String fj) {
		this.fj = fj;
	}

	public double getSyqxxbid() {
		return syqxxbid;
	}

	public double getLcslbid() {
		return lcslbid;
	}

	public double getB_deleteflag() {
		return b_deleteflag;
	}

	public String getFwbh() {
		return fwbh;
	}

	public String getFdcmc() {
		return fdcmc;
	}

	public String getQdfs() {
		return qdfs;
	}

	public String getFwzl() {
		return fwzl;
	}

	public String getXmmc() {
		return xmmc;
	}

	public String getDh() {
		return dh;
	}

	public String getFh() {
		return fh;
	}

	public String getFwjg() {
		return fwjg;
	}

	public double getZcs() {
		return zcs;
	}

	public double getJzmj() {
		return jzmj;
	}

	public double getTnjzmj() {
		return tnjzmj;
	}

	public double getZybfjzmj() {
		return zybfjzmj;
	}

	public String getGhyt() {
		return ghyt;
	}

	public Date getJgsj() {
		return jgsj;
	}

	public String getFwxz() {
		return fwxz;
	}

	public Date getDjsj() {
		return djsj;
	}

	public String getFj() {
		return fj;
	}

}
