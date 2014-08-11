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
@Table(name = "BDC_YWBL_JJCLLB")
public class RecMaterial extends BaseDomain<RecMaterial> {
	@Id
	private double jjclbid;
	private double lcslbid;
	private double b_deleteflag;
	private String jjclmc;
	private String cllx;
	private String zl;
	private String ys;
	private String fs;
	private String jjr;
	private String jjsj;

	public RecMaterial(){
		super();
		this.t = RecMaterial.class;
	}

	public RecMaterial(double jjclbid){
		super();
		this.jjclbid = jjclbid;
	}

	public void setJjclbid(double jjclbid) {
		this.jjclbid = jjclbid;
	}

	public void setLcslbid(double lcslbid) {
		this.lcslbid = lcslbid;
	}

	public void setB_deleteflag(double b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setJjclmc(String jjclmc) {
		this.jjclmc = jjclmc;
	}

	public void setCllx(String cllx) {
		this.cllx = cllx;
	}

	public void setZl(String zl) {
		this.zl = zl;
	}

	public void setYs(String ys) {
		this.ys = ys;
	}

	public void setFs(String fs) {
		this.fs = fs;
	}

	public void setJjr(String jjr) {
		this.jjr = jjr;
	}

	public void setJjsj(String jjsj) {
		this.jjsj = jjsj;
	}

	public double getJjclbid() {
		return jjclbid;
	}

	public double getLcslbid() {
		return lcslbid;
	}

	public double getB_deleteflag() {
		return b_deleteflag;
	}

	public String getJjclmc() {
		return jjclmc;
	}

	public String getCllx() {
		return cllx;
	}

	public String getZl() {
		return zl;
	}

	public String getYs() {
		return ys;
	}

	public String getFs() {
		return fs;
	}

	public String getJjr() {
		return jjr;
	}

	public String getJjsj() {
		return jjsj;
	}

}
