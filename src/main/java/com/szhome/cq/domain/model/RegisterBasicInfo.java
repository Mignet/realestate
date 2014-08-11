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
@Table(name = "bdc_djb_jbxx")
public class RegisterBasicInfo extends BaseDomain<RegisterBasicInfo> {
	@Id
	private double djbjbxxbid;
	private double fwbid;
	private double zdbid;
	private double b_deleteflag;
	private String djjg;
	private String djblx;
	private String djbbh;
	private String zdbh;
	private String xmmc;
	private String fwbh;

	public RegisterBasicInfo(){
		super();
		this.t = RegisterBasicInfo.class;
	}

	public RegisterBasicInfo(double djbjbxxbid){
		super();
		this.djbjbxxbid = djbjbxxbid;
	}

	public void setDjbjbxxbid(double djbjbxxbid) {
		this.djbjbxxbid = djbjbxxbid;
	}

	public void setFwbid(double fwbid) {
		this.fwbid = fwbid;
	}

	public void setZdbid(double zdbid) {
		this.zdbid = zdbid;
	}

	public void setB_deleteflag(double b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setDjjg(String djjg) {
		this.djjg = djjg;
	}

	public void setDjblx(String djblx) {
		this.djblx = djblx;
	}

	public void setDjbbh(String djbbh) {
		this.djbbh = djbbh;
	}

	public void setZdbh(String zdbh) {
		this.zdbh = zdbh;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public void setFwbh(String fwbh) {
		this.fwbh = fwbh;
	}

	public double getDjbjbxxbid() {
		return djbjbxxbid;
	}

	public double getFwbid() {
		return fwbid;
	}

	public double getZdbid() {
		return zdbid;
	}

	public double getB_deleteflag() {
		return b_deleteflag;
	}

	public String getDjjg() {
		return djjg;
	}

	public String getDjblx() {
		return djblx;
	}

	public String getDjbbh() {
		return djbbh;
	}

	public String getZdbh() {
		return zdbh;
	}

	public String getXmmc() {
		return xmmc;
	}

	public String getFwbh() {
		return fwbh;
	}

}
