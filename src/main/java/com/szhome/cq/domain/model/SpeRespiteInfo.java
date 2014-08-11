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
@Table(name = "SPE_RESPITE_INFO")
public class SpeRespiteInfo extends BaseDomain<SpeRespiteInfo> {
	@Id
	private String res_id;
	private String b_deleteflag;
	private String res_no;
	private String bus_id;
	private String bus_des;
	private String app;
	private Date app_date;
	private String res_reason;
	private String res_status;
	private String law;

	public SpeRespiteInfo(){
		super();
		this.t = SpeRespiteInfo.class;
	}

	public SpeRespiteInfo(String res_id){
		super();
		this.res_id = res_id;
	}

	public void setRes_id(String res_id) {
		this.res_id = res_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setRes_no(String res_no) {
		this.res_no = res_no;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setBus_des(String bus_des) {
		this.bus_des = bus_des;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public void setApp_date(Date app_date) {
		this.app_date = app_date;
	}

	public void setRes_reason(String res_reason) {
		this.res_reason = res_reason;
	}

	public void setLaw(String law) {
		this.law = law;
	}

	public String getRes_id() {
		return res_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getRes_no() {
		return res_no;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getBus_des() {
		return bus_des;
	}

	public String getApp() {
		return app;
	}

	public Date getApp_date() {
		return app_date;
	}

	public String getRes_reason() {
		return res_reason;
	}

	public String getLaw() {
		return law;
	}

	public String getRes_status() {
		return res_status;
	}

	public void setRes_status(String res_status) {
		this.res_status = res_status;
	}

}
