package com.szhome.cq.domain.model;

import java.util.Date;
import java.sql.Blob;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springjdbc.annotation.BaseDomain;
import com.springjdbc.annotation.Entity;
import com.springjdbc.annotation.Id;
import com.springjdbc.annotation.Table;

@Scope("prototype")
@Component
@Entity
@Table(name = "BK_CERTIFICATE")
public class Bk_Certificate extends BaseDomain<Bk_Certificate> {
	@Id
	private String certificate_id;
	private String reg_code;
	private String cer_type;
	private String cer_code;
	private Blob cer_doc;
	private String password;
	private Date pri_date;

	public Bk_Certificate(){
		super();
		this.t = Bk_Certificate.class;
	}

	public Bk_Certificate(String certificate_id){
		super();
		this.certificate_id = certificate_id;
	}

	public void setCertificate_id(String certificate_id) {
		this.certificate_id = certificate_id;
	}

	public void setReg_code(String reg_code) {
		this.reg_code = reg_code;
	}

	public void setCer_type(String cer_type) {
		this.cer_type = cer_type;
	}

	public void setCer_code(String cer_code) {
		this.cer_code = cer_code;
	}

	public void setCer_doc(Blob cer_doc) {
		this.cer_doc = cer_doc;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPri_date(Date pri_date) {
		this.pri_date = pri_date;
	}

	public String getCertificate_id() {
		return certificate_id;
	}

	public String getReg_code() {
		return reg_code;
	}

	public String getCer_type() {
		return cer_type;
	}

	public String getCer_code() {
		return cer_code;
	}

	public Blob getCer_doc() {
		return cer_doc;
	}

	public String getPassword() {
		return password;
	}

	public Date getPri_date() {
		return pri_date;
	}

}
