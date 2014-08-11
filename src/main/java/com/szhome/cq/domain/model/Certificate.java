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
@Table(name = "BUS_EST_CERTIFICATE")
public class Certificate extends BaseDomain<Certificate> {
	@Id
	private String certificate_id;
	private String bus_id;
	private String b_deleteflag;
	private String certificate_code;
	private String certificate_type;
	private String excursus;

	private String cer_state;
	private String printer;
	private Date print_date;
	private Date rec_date;
	private String rec_person;
	private String rec_type;
	private String rec_cer_no;
	private String rec_cer_type;
	private String reg_unit_code;
	public Certificate(){
		super();
		this.t = Certificate.class;
	}

	public Certificate(String certificate_id){
		super();
		this.certificate_id = certificate_id;
	}

	public void setCertificate_id(String certificate_id) {
		this.certificate_id = certificate_id;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setCertificate_code(String certificate_code) {
		this.certificate_code = certificate_code;
	}

	public void setCertificate_type(String certificate_type) {
		this.certificate_type = certificate_type;
	}

	public void setExcursus(String excursus) {
		this.excursus = excursus;
	}

	public void setCer_state(String cer_state) {
		this.cer_state = cer_state;
	}

	public void setPrinter(String printer) {
		this.printer = printer;
	}

	public void setPrint_date(Date print_date) {
		this.print_date = print_date;
	}

	public void setRec_date(Date rec_date) {
		this.rec_date = rec_date;
	}

	public void setRec_person(String rec_person) {
		this.rec_person = rec_person;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public void setRec_cer_no(String rec_cer_no) {
		this.rec_cer_no = rec_cer_no;
	}

	public void setRec_cer_type(String rec_cer_type) {
		this.rec_cer_type = rec_cer_type;
	}

	public String getCertificate_id() {
		return certificate_id;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getCertificate_code() {
		return certificate_code;
	}

	public String getCertificate_type() {
		return certificate_type;
	}

	public String getExcursus() {
		return excursus;
	}

	public String getCer_state() {
		return cer_state;
	}

	public String getPrinter() {
		return printer;
	}

	public Date getPrint_date() {
		return print_date;
	}

	public Date getRec_date() {
		return rec_date;
	}

	public String getRec_person() {
		return rec_person;
	}

	public String getRec_type() {
		return rec_type;
	}

	public String getRec_cer_no() {
		return rec_cer_no;
	}

	public String getRec_cer_type() {
		return rec_cer_type;
	}
	public String getReg_unit_code() {
		return reg_unit_code;
	}

	public void setReg_unit_code(String reg_unit_code) {
		this.reg_unit_code = reg_unit_code;
	}

}
