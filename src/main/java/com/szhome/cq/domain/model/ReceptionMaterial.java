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
@Table(name = "BUS_RECEIVAL")
public class ReceptionMaterial extends BaseDomain<ReceptionMaterial> {
	private String rec_type_flag;
	@Id
	private String receival_id;
	private String bus_id;
	private String b_deleteflag;
	private String re_name;
	private String re_type;
	private String re_style;
	private String re_page;
	private String re_copy;
	private String re_person;
	private Date re_date;

	public ReceptionMaterial(){
		super();
		this.t = ReceptionMaterial.class;
	}

	public ReceptionMaterial(String receival_id){
		super();
		this.receival_id = receival_id;
	}

	public void setRec_type_flag(String rec_type_flag) {
		this.rec_type_flag = rec_type_flag;
	}

	public void setReceival_id(String receival_id) {
		this.receival_id = receival_id;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setRe_name(String re_name) {
		this.re_name = re_name;
	}

	public void setRe_type(String re_type) {
		this.re_type = re_type;
	}

	public void setRe_style(String re_style) {
		this.re_style = re_style;
	}

	public void setRe_page(String re_page) {
		this.re_page = re_page;
	}

	public void setRe_copy(String re_copy) {
		this.re_copy = re_copy;
	}

	public void setRe_person(String re_person) {
		this.re_person = re_person;
	}

	public void setRe_date(Date re_date) {
		this.re_date = re_date;
	}

	public String getRec_type_flag() {
		return rec_type_flag;
	}

	public String getReceival_id() {
		return receival_id;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getRe_name() {
		return re_name;
	}

	public String getRe_type() {
		return re_type;
	}

	public String getRe_style() {
		return re_style;
	}

	public String getRe_page() {
		return re_page;
	}

	public String getRe_copy() {
		return re_copy;
	}

	public String getRe_person() {
		return re_person;
	}

	public Date getRe_date() {
		return re_date;
	}

}

