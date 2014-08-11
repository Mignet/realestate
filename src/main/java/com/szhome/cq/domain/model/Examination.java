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
@Table(name = "BDC_YWBL_SPYJ")
public class Examination extends BaseDomain<Examination> {
	private String csr;
	private String csyj;
	private Date cssj;
	private String fsr;
	private String fsyj;
	private Date fssj;
	private String hzr;
	private String hzyj;
	private Date hzsj;
	private String shr1;
	private String shyj;
	@Id
	private double spyjbid;
	private double lcslbid;
	private String b_deleteflag;
	private String yjlx;
	private String yjnr;
	private String shrbh;
	private Date shsj;

	public Examination(){
		super();
		this.t = Examination.class;
	}

	public Examination(double spyjbid){
		super();
		this.spyjbid = spyjbid;
	}

	public void setCsr(String csr) {
		this.csr = csr;
	}

	public void setCsyj(String csyj) {
		this.csyj = csyj;
	}

	public void setCssj(Date cssj) {
		this.cssj = cssj;
	}

	public void setFsr(String fsr) {
		this.fsr = fsr;
	}

	public void setFsyj(String fsyj) {
		this.fsyj = fsyj;
	}

	public void setFssj(Date fssj) {
		this.fssj = fssj;
	}

	public void setHzr(String hzr) {
		this.hzr = hzr;
	}

	public void setHzyj(String hzyj) {
		this.hzyj = hzyj;
	}

	public void setHzsj(Date hzsj) {
		this.hzsj = hzsj;
	}

	

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public void setSpyjbid(double spyjbid) {
		this.spyjbid = spyjbid;
	}

	public void setLcslbid(double lcslbid) {
		this.lcslbid = lcslbid;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setYjlx(String yjlx) {
		this.yjlx = yjlx;
	}

	public void setYjnr(String yjnr) {
		this.yjnr = yjnr;
	}

	public void setShrbh(String shrbh) {
		this.shrbh = shrbh;
	}

	public void setShsj(Date shsj) {
		this.shsj = shsj;
	}

	public String getCsr() {
		return csr;
	}

	public String getCsyj() {
		return csyj;
	}

	public Date getCssj() {
		return cssj;
	}

	public String getFsr() {
		return fsr;
	}

	public String getFsyj() {
		return fsyj;
	}

	public Date getFssj() {
		return fssj;
	}

	public String getHzr() {
		return hzr;
	}

	public String getHzyj() {
		return hzyj;
	}

	public Date getHzsj() {
		return hzsj;
	}


	public String getShyj() {
		return shyj;
	}

	public double getSpyjbid() {
		return spyjbid;
	}

	public double getLcslbid() {
		return lcslbid;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getYjlx() {
		return yjlx;
	}

	public String getYjnr() {
		return yjnr;
	}

	public String getShrbh() {
		return shrbh;
	}

	public Date getShsj() {
		return shsj;
	}

	public String getShr1() {
		return shr1;
	}

	public void setShr1(String shr1) {
		this.shr1 = shr1;
	}

}
