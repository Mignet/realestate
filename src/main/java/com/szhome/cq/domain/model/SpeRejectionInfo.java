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
@Table(name = "SPE_REJECTION_INFO")
public class SpeRejectionInfo extends BaseDomain<SpeRejectionInfo> {
	@Id
	private String rej_id;
	private String b_deleteflag;
	private String rej_no;
	private String bus_id;
	private String bus_des;
	private String app;
	private Date app_date;
	private String law;
	private String rej_reason;
	private String rej_status;

	public SpeRejectionInfo(){
		super();
		this.t = SpeRejectionInfo.class;
	}

	public SpeRejectionInfo(String rej_id){
		super();
		this.rej_id = rej_id;
	}

	public void setRej_id(String rej_id) {
		this.rej_id = rej_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setRej_no(String rej_no) {
		this.rej_no = rej_no;
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

	public void setLaw(String law) {
		this.law = law;
	}

	public void setRej_reason(String rej_reason) {
		this.rej_reason = rej_reason;
	}

	public String getRej_id() {
		return rej_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getRej_no() {
		return rej_no;
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

	public String getLaw() {
		return law;
	}

	public String getRej_reason() {
		return rej_reason;
	}

	public String getRej_status() {
		return rej_status;
	}

	public void setRej_status(String rej_status) {
		this.rej_status = rej_status;
	}
  
}
