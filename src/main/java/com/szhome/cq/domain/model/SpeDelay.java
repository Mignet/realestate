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
@Table(name = "SPE_DELAY")
public class SpeDelay extends BaseDomain<SpeDelay> {
	@Id
	private String delay_id;
	private String b_deleteflag;
	private String delay_no;
	private String delay_app_part;
	private String delay_app;
	private String reg_app;
	private String bus_id;
	private String bus_des;
	private double total_limit;
	private double remainder_days;
	private double app_days;
	private double approval_days;
	private String isappanddays;
	private String delay_type;
	private String monitor;
	private String delay_reason;
	private String delay_status;

	public SpeDelay(){
		super();
		this.t = SpeDelay.class;
	}

	public SpeDelay(String delay_id){
		super();
		this.delay_id = delay_id;
	}

	public void setDelay_id(String delay_id) {
		this.delay_id = delay_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setDelay_no(String delay_no) {
		this.delay_no = delay_no;
	}

	public void setDelay_app_part(String delay_app_part) {
		this.delay_app_part = delay_app_part;
	}

	public void setDelay_app(String delay_app) {
		this.delay_app = delay_app;
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

	public void setTotal_limit(double total_limit) {
		this.total_limit = total_limit;
	}

	public void setRemainder_days(double remainder_days) {
		this.remainder_days = remainder_days;
	}

	public void setApp_days(double app_days) {
		this.app_days = app_days;
	}

	public void setApproval_days(double approval_days) {
		this.approval_days = approval_days;
	}
	
	public String getIsappanddays() {
		return isappanddays;
	}

	public void setIsappanddays(String isappanddays) {
		this.isappanddays = isappanddays;
	}

	public void setDelay_type(String delay_type) {
		this.delay_type = delay_type;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public void setDelay_reason(String delay_reason) {
		this.delay_reason = delay_reason;
	}

	public void setDelay_status(String delay_status) {
		this.delay_status = delay_status;
	}

	public String getDelay_id() {
		return delay_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getDelay_no() {
		return delay_no;
	}

	public String getDelay_app_part() {
		return delay_app_part;
	}

	public String getDelay_app() {
		return delay_app;
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

	public double getTotal_limit() {
		return total_limit;
	}

	public double getRemainder_days() {
		return remainder_days;
	}

	public double getApp_days() {
		return app_days;
	}

	public double getApproval_days() {
		return approval_days;
	}

	public String getDelay_type() {
		return delay_type;
	}

	public String getMonitor() {
		return monitor;
	}

	public String getDelay_reason() {
		return delay_reason;
	}

	public String getDelay_status() {
		return delay_status;
	}

}
