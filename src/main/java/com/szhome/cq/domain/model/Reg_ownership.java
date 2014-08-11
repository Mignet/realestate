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
@Table(name = "bk_ownership")
public class Reg_ownership extends BaseDomain<Reg_ownership> {
	@Id
	private String ownership_id;
	private String book_code;
	private String b_deleteflag;
	private String reg_code;
	private String cer_no;
	private String reg_value;
	private Date reg_date;
	private String recorder;
	private String excursus;
	private String procdef_id;
	private String pre_reg_code;
	private String get_mode;
	private String lu_term;
	private Date start_date;
	private Date end_date;
	private String house_usage;
	private String house_attr;
	private String effective;
	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	public Reg_ownership(){
		super();
		this.t = Reg_ownership.class;
	}

	public Reg_ownership(String ownership_id){
		super();
		this.ownership_id = ownership_id;
	}

	public void setOwnership_id(String ownership_id) {
		this.ownership_id = ownership_id;
	}

	public void setBook_code(String book_code) {
		this.book_code = book_code;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setReg_code(String reg_code) {
		this.reg_code = reg_code;
	}

	public void setCer_no(String cer_no) {
		this.cer_no = cer_no;
	}

	public void setReg_value(String reg_value) {
		this.reg_value = reg_value;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public void setExcursus(String excursus) {
		this.excursus = excursus;
	}

	public void setProcdef_id(String procdef_id) {
		this.procdef_id = procdef_id;
	}

	public void setPre_reg_code(String pre_reg_code) {
		this.pre_reg_code = pre_reg_code;
	}

	public void setGet_mode(String get_mode) {
		this.get_mode = get_mode;
	}

	public void setLu_term(String lu_term) {
		this.lu_term = lu_term;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public void setHouse_usage(String house_usage) {
		this.house_usage = house_usage;
	}

	public void setHouse_attr(String house_attr) {
		this.house_attr = house_attr;
	}

	public void setEffective(String effective) {
		this.effective = effective;
	}

	public String getOwnership_id() {
		return ownership_id;
	}

	public String getBook_code() {
		return book_code;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getReg_code() {
		return reg_code;
	}

	public String getCer_no() {
		return cer_no;
	}

	public String getReg_value() {
		return reg_value;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public String getRecorder() {
		return recorder;
	}

	public String getExcursus() {
		return excursus;
	}

	public String getProcdef_id() {
		return procdef_id;
	}

	public String getPre_reg_code() {
		return pre_reg_code;
	}

	public String getGet_mode() {
		return get_mode;
	}

	public String getLu_term() {
		return lu_term;
	}

	public Date getStart_date() {
		return start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public String getHouse_usage() {
		return house_usage;
	}

	public String getHouse_attr() {
		return house_attr;
	}

	public String getEffective() {
		return effective;
	}

}
