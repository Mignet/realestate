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
@Table(name = "bd_reg_unit_status")
public class Bd_reg_unit_status extends BaseDomain<Bd_reg_unit_status> {
	@Id
	private String unit_sta_id;
	private String unit_type;
	private String unit_code;
	private String chg_type;
	private String buss_doing;
	private String status_book;
	private String status_estate;
	private String status_attach;
	private String status_mortgage;
	private String status_demurrer;
	private String status_easement;
	private String status_advnotice;
	private String status_note;
	private String status_cont;
	private String status_sale;
	private double times_baseinfo;
	private double times_useriht;
	private double times_ownership;
	private double times_attach;
	private double times_mortgage;
	private double times_demurrer;
	private double times_easement;
	private double times_advnotice;

	public Bd_reg_unit_status(){
		super();
		this.t = Bd_reg_unit_status.class;
	}

	public Bd_reg_unit_status(String unit_sta_id){
		super();
		this.unit_sta_id = unit_sta_id;
	}

	public void setUnit_sta_id(String unit_sta_id) {
		this.unit_sta_id = unit_sta_id;
	}

	public void setUnit_type(String unit_type) {
		this.unit_type = unit_type;
	}

	public void setUnit_code(String unit_code) {
		this.unit_code = unit_code;
	}

	public void setChg_type(String chg_type) {
		this.chg_type = chg_type;
	}

	public void setBuss_doing(String buss_doing) {
		this.buss_doing = buss_doing;
	}

	public void setStatus_book(String status_book) {
		this.status_book = status_book;
	}

	public void setStatus_estate(String status_estate) {
		this.status_estate = status_estate;
	}

	public void setStatus_attach(String status_attach) {
		this.status_attach = status_attach;
	}

	public void setStatus_mortgage(String status_mortgage) {
		this.status_mortgage = status_mortgage;
	}

	public void setStatus_demurrer(String status_demurrer) {
		this.status_demurrer = status_demurrer;
	}

	public void setStatus_easement(String status_easement) {
		this.status_easement = status_easement;
	}

	public void setStatus_advnotice(String status_advnotice) {
		this.status_advnotice = status_advnotice;
	}

	public void setStatus_note(String status_note) {
		this.status_note = status_note;
	}

	public void setStatus_cont(String status_cont) {
		this.status_cont = status_cont;
	}

	public void setStatus_sale(String status_sale) {
		this.status_sale = status_sale;
	}

	public void setTimes_baseinfo(double times_baseinfo) {
		this.times_baseinfo = times_baseinfo;
	}

	public void setTimes_useriht(double times_useriht) {
		this.times_useriht = times_useriht;
	}

	public void setTimes_ownership(double times_ownership) {
		this.times_ownership = times_ownership;
	}

	public void setTimes_attach(double times_attach) {
		this.times_attach = times_attach;
	}

	public void setTimes_mortgage(double times_mortgage) {
		this.times_mortgage = times_mortgage;
	}

	public void setTimes_demurrer(double times_demurrer) {
		this.times_demurrer = times_demurrer;
	}

	public void setTimes_easement(double times_easement) {
		this.times_easement = times_easement;
	}

	public void setTimes_advnotice(double times_advnotice) {
		this.times_advnotice = times_advnotice;
	}

	public String getUnit_sta_id() {
		return unit_sta_id;
	}

	public String getUnit_type() {
		return unit_type;
	}

	public String getUnit_code() {
		return unit_code;
	}

	public String getChg_type() {
		return chg_type;
	}

	public String getBuss_doing() {
		return buss_doing;
	}

	public String getStatus_book() {
		return status_book;
	}

	public String getStatus_estate() {
		return status_estate;
	}

	public String getStatus_attach() {
		return status_attach;
	}

	public String getStatus_mortgage() {
		return status_mortgage;
	}

	public String getStatus_demurrer() {
		return status_demurrer;
	}

	public String getStatus_easement() {
		return status_easement;
	}

	public String getStatus_advnotice() {
		return status_advnotice;
	}

	public String getStatus_note() {
		return status_note;
	}

	public String getStatus_cont() {
		return status_cont;
	}

	public String getStatus_sale() {
		return status_sale;
	}

	public double getTimes_baseinfo() {
		return times_baseinfo;
	}

	public double getTimes_useriht() {
		return times_useriht;
	}

	public double getTimes_ownership() {
		return times_ownership;
	}

	public double getTimes_attach() {
		return times_attach;
	}

	public double getTimes_mortgage() {
		return times_mortgage;
	}

	public double getTimes_demurrer() {
		return times_demurrer;
	}

	public double getTimes_easement() {
		return times_easement;
	}

	public double getTimes_advnotice() {
		return times_advnotice;
	}

}