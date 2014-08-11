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
@Table(name = "BUS_NOTICE")
public class Announcement extends BaseDomain<Announcement> {
	@Id
	private String notice_id;
	private String bus_id;
	private String b_deleteflag;
	private String notice_code;
	private String notice_person;
	private String notice_content;
	private Date noticie_date;
	public Double notice_limit;
	private String notice_pub_off;
	private Date notice_pub_time;
	

	private String pub_name_date;
	private Date pub_date;
	private String notice_pro_person;
	private Date notice_pro_time;
	

	public Announcement(){
		super();
		this.t = Announcement.class;
	}

	public Announcement(String notice_id){
		super();
		this.notice_id = notice_id;
	}

	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}

	public Date getNotice_pub_time() {
		return notice_pub_time;
	}

	public void setNotice_pub_time(Date notice_pub_time) {
		this.notice_pub_time = notice_pub_time;
	}
	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setNotice_code(String notice_code) {
		this.notice_code = notice_code;
	}

	public void setNotice_person(String notice_person) {
		this.notice_person = notice_person;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public void setNoticie_date(Date noticie_date) {
		this.noticie_date = noticie_date;
	}

	public void setNotice_limit(Double notice_limit) {
		this.notice_limit = notice_limit;
	}

	public void setNotice_pub_off(String notice_pub_off) {
		this.notice_pub_off = notice_pub_off;
	}

	

	public void setPub_name_date(String pub_name_date) {
		this.pub_name_date = pub_name_date;
	}

	public void setPub_date(Date pub_date) {
		this.pub_date = pub_date;
	}

	

	public String getNotice_id() {
		return notice_id;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getNotice_code() {
		return notice_code;
	}

	public String getNotice_person() {
		return notice_person;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public Date getNoticie_date() {
		return noticie_date;
	}

	public Double getNotice_limit() {
		return notice_limit;
	}

	public String getNotice_pub_off() {
		return notice_pub_off;
	}

	

	public String getPub_name_date() {
		return pub_name_date;
	}

	public Date getPub_date() {
		return pub_date;
	}

	public String getNotice_pro_person() {
		return notice_pro_person;
	}

	public void setNotice_pro_person(String notice_pro_person) {
		this.notice_pro_person = notice_pro_person;
	}

	public Date getNotice_pro_time() {
		return notice_pro_time;
	}

	public void setNotice_pro_time(Date notice_pro_time) {
		this.notice_pro_time = notice_pro_time;
	}

	

}
