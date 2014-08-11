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
@Table(name = "CFIG_RECEIVAL")
public class RecMatConfigure extends BaseDomain<RecMatConfigure> {
	@Id
	private String cfig_receival_id;
	private String bus_type_id;
	private String b_deleteflag;
	private String cfig_rec_name;
	private String cfig_rec_type;
	private String cfig_rec_style;
	private String cfig_rec_copy;
	private String cfig_person;
	private Date cfig_date;
	private String rec_type_flag;
	private String cfig_page;

	public RecMatConfigure(){
		super();
		this.t = RecMatConfigure.class;
	}

	public RecMatConfigure(String cfig_receival_id){
		super();
		this.cfig_receival_id = cfig_receival_id;
	}

	public void setCfig_receival_id(String cfig_receival_id) {
		this.cfig_receival_id = cfig_receival_id;
	}

	public void setBus_type_id(String bus_type_id) {
		this.bus_type_id = bus_type_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setCfig_rec_name(String cfig_rec_name) {
		this.cfig_rec_name = cfig_rec_name;
	}

	public void setCfig_rec_type(String cfig_rec_type) {
		this.cfig_rec_type = cfig_rec_type;
	}

	public void setCfig_rec_style(String cfig_rec_style) {
		this.cfig_rec_style = cfig_rec_style;
	}

	public void setCfig_rec_copy(String cfig_rec_copy) {
		this.cfig_rec_copy = cfig_rec_copy;
	}

	public void setCfig_person(String cfig_person) {
		this.cfig_person = cfig_person;
	}

	public void setCfig_date(Date cfig_date) {
		this.cfig_date = cfig_date;
	}

	public void setRec_type_flag(String rec_type_flag) {
		this.rec_type_flag = rec_type_flag;
	}

	public void setCfig_page(String cfig_page) {
		this.cfig_page = cfig_page;
	}

	public String getCfig_receival_id() {
		return cfig_receival_id;
	}

	public String getBus_type_id() {
		return bus_type_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getCfig_rec_name() {
		return cfig_rec_name;
	}

	public String getCfig_rec_type() {
		return cfig_rec_type;
	}

	public String getCfig_rec_style() {
		return cfig_rec_style;
	}

	public String getCfig_rec_copy() {
		return cfig_rec_copy;
	}

	public String getCfig_person() {
		return cfig_person;
	}

	public Date getCfig_date() {
		return cfig_date;
	}

	public String getRec_type_flag() {
		return rec_type_flag;
	}

	public String getCfig_page() {
		return cfig_page;
	}

}

