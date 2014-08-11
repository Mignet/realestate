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
@Table(name = "SPE_SUSPEND_INFO")
public class SpeSuspendInfo extends BaseDomain<SpeSuspendInfo> {
	@Id
	private String sus_id;
	private String b_deleteflag;
	private String sus_no;
	private String reg_app;
	private String bus_id;
	private String bus_des;
	private String app_off;
	private String sus_app;
	private String sus_reason;
	private String sus_status;

	public SpeSuspendInfo(){
		super();
		this.t = SpeSuspendInfo.class;
	}

	public SpeSuspendInfo(String sus_id){
		super();
		this.sus_id = sus_id;
	}

	public void setSus_id(String sus_id) {
		this.sus_id = sus_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}

	public void setReg_app(String reg_app) {
		this.reg_app = reg_app;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setBus_des(String bus_des) {
		this.bus_des = bus_des;
	}

	public void setApp_off(String app_off) {
		this.app_off = app_off;
	}

	public void setSus_app(String sus_app) {
		this.sus_app = sus_app;
	}

	public void setSus_reason(String sus_reason) {
		this.sus_reason = sus_reason;
	}

	public void setSus_status(String sus_status) {
		this.sus_status = sus_status;
	}

	public String getSus_id() {
		return sus_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getSus_no() {
		return sus_no;
	}

	public String getReg_app() {
		return reg_app;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getBus_des() {
		return bus_des;
	}

	public String getApp_off() {
		return app_off;
	}

	public String getSus_app() {
		return sus_app;
	}

	public String getSus_reason() {
		return sus_reason;
	}

	public String getSus_status() {
		return sus_status;
	}

}
