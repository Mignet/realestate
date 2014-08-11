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
@Table(name = "CHK_URGE")
public class UrgeRecord extends BaseDomain<UrgeRecord> {
	private String notice_type;
	private String time_limit;
	private String result;
	private Date deal_date;
	private String urge_number;
	@Id
	private String urge_id;
	private String bus_id;
	private String b_deleteflag;

	public UrgeRecord(){
		super();
		this.t = UrgeRecord.class;
	}

	public UrgeRecord(String urge_id){
		super();
		this.urge_id = urge_id;
	}

	public void setNotice_type(String notice_type) {
		this.notice_type = notice_type;
	}

	public void setTime_limit(String time_limit) {
		this.time_limit = time_limit;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setDeal_date(Date deal_date) {
		this.deal_date = deal_date;
	}

	public void setUrge_number(String urge_number) {
		this.urge_number = urge_number;
	}

	public void setUrge_id(String urge_id) {
		this.urge_id = urge_id;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public String getNotice_type() {
		return notice_type;
	}

	public String getTime_limit() {
		return time_limit;
	}

	public String getResult() {
		return result;
	}

	public Date getDeal_date() {
		return deal_date;
	}

	public String getUrge_number() {
		return urge_number;
	}

	public String getUrge_id() {
		return urge_id;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

}

