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
@Table(name = "bus_applicant")
public class Applicant extends BaseDomain<Applicant> {
	private String hol_rel;
	private String app_pic;
	private String age_pic;
	@Id
	private String applicant_id;
	private String bus_id;
	private String b_deleteflag;
	private String app_name;
	private String app_type;
	private String app_port;
	private String app_cer_type;
	private String app_cer_no;
	private String app_address;
	private String app_tel;
	private String depart_type;
	private String legal_name;
	private String agent_name;
	private String agent_tel;
	private String agent_cer;
	private String agent_cer_type;
	private String reg_unit_code;

	public Applicant(){
		super();
		this.t = Applicant.class;
	}

	public Applicant(String applicant_id){
		super();
		this.applicant_id = applicant_id;
	}

	public void setHol_rel(String hol_rel) {
		this.hol_rel = hol_rel;
	}

	public void setApp_pic(String app_pic) {
		this.app_pic = app_pic;
	}

	public void setAge_pic(String age_pic) {
		this.age_pic = age_pic;
	}

	public void setApplicant_id(String applicant_id) {
		this.applicant_id = applicant_id;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public void setApp_type(String app_type) {
		this.app_type = app_type;
	}

	public void setApp_port(String app_port) {
		this.app_port = app_port;
	}

	public void setApp_cer_type(String app_cer_type) {
		this.app_cer_type = app_cer_type;
	}

	public void setApp_cer_no(String app_cer_no) {
		this.app_cer_no = app_cer_no;
	}

	public void setApp_address(String app_address) {
		this.app_address = app_address;
	}

	public void setApp_tel(String app_tel) {
		this.app_tel = app_tel;
	}

	public void setDepart_type(String depart_type) {
		this.depart_type = depart_type;
	}

	public void setLegal_name(String legal_name) {
		this.legal_name = legal_name;
	}

	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}

	public void setAgent_tel(String agent_tel) {
		this.agent_tel = agent_tel;
	}

	public void setAgent_cer(String agent_cer) {
		this.agent_cer = agent_cer;
	}

	public void setAgent_cer_type(String agent_cer_type) {
		this.agent_cer_type = agent_cer_type;
	}

	public void setReg_unit_code(String reg_unit_code) {
		this.reg_unit_code = reg_unit_code;
	}

	public String getHol_rel() {
		return hol_rel;
	}

	public String getApp_pic() {
		return app_pic;
	}

	public String getAge_pic() {
		return age_pic;
	}

	public String getApplicant_id() {
		return applicant_id;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getApp_name() {
		return app_name;
	}

	public String getApp_type() {
		return app_type;
	}

	public String getApp_port() {
		return app_port;
	}

	public String getApp_cer_type() {
		return app_cer_type;
	}

	public String getApp_cer_no() {
		return app_cer_no;
	}

	public String getApp_address() {
		return app_address;
	}

	public String getApp_tel() {
		return app_tel;
	}

	public String getDepart_type() {
		return depart_type;
	}

	public String getLegal_name() {
		return legal_name;
	}

	public String getAgent_name() {
		return agent_name;
	}

	public String getAgent_tel() {
		return agent_tel;
	}

	public String getAgent_cer() {
		return agent_cer;
	}

	public String getAgent_cer_type() {
		return agent_cer_type;
	}

	public String getReg_unit_code() {
		return reg_unit_code;
	}

}

