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
@Table(name = "BUS_MORTGAGE_REG")
public class Mortgage extends BaseDomain<Mortgage> {
	@Id
	private String mort_reg_id;
	private String bus_id;
	private String b_deleteflag;
	private String mort_type;
	private Double rel_orig_value;
	private Double mort_assure_right;
	private Double assess_price;
	private Double agreed_price;
	private Double sure_amount;
	private String mort_con_no;
	private Date mort_reg_date;
	private String rel_estate_name;
	private String borrower;
	private Date creditor_start_date;
	private Date creditor_end_date;
	private Date can_mort_date;
	private String orig_mort_reg_no;
	private String can_mort_reason;
	private String mort_port;
	private String loan_usage;
	private String assuer_range;
	private String mort_seq;
	private String max_amount;
	private String con_progress;

	public String getCon_progress() {
		return con_progress;
	}

	public void setCon_progress(String con_progress) {
		this.con_progress = con_progress;
	}

	public Mortgage(){
		super();
		this.t = Mortgage.class;
	}

	public Mortgage(String mort_reg_id){
		super();
		this.mort_reg_id = mort_reg_id;
	}

	public void setMort_reg_id(String mort_reg_id) {
		this.mort_reg_id = mort_reg_id;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setMort_type(String mort_type) {
		this.mort_type = mort_type;
	}

	public void setRel_orig_value(Double rel_orig_value) {
		this.rel_orig_value = rel_orig_value;
	}

	public void setMort_assure_right(Double mort_assure_right) {
		this.mort_assure_right = mort_assure_right;
	}

	public void setAssess_price(Double assess_price) {
		this.assess_price = assess_price;
	}

	public void setAgreed_price(Double agreed_price) {
		this.agreed_price = agreed_price;
	}

	public void setMort_con_no(String mort_con_no) {
		this.mort_con_no = mort_con_no;
	}

	public void setMort_reg_date(Date mort_reg_date) {
		this.mort_reg_date = mort_reg_date;
	}

	public void setRel_estate_name(String rel_estate_name) {
		this.rel_estate_name = rel_estate_name;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public void setCreditor_start_date(Date creditor_start_date) {
		this.creditor_start_date = creditor_start_date;
	}

	public void setCreditor_end_date(Date creditor_end_date) {
		this.creditor_end_date = creditor_end_date;
	}

	public void setCan_mort_date(Date can_mort_date) {
		this.can_mort_date = can_mort_date;
	}

	public void setOrig_mort_reg_no(String orig_mort_reg_no) {
		this.orig_mort_reg_no = orig_mort_reg_no;
	}

	public void setCan_mort_reason(String can_mort_reason) {
		this.can_mort_reason = can_mort_reason;
	}

	public void setMort_port(String mort_port) {
		this.mort_port = mort_port;
	}

	public void setLoan_usage(String loan_usage) {
		this.loan_usage = loan_usage;
	}

	public void setAssuer_range(String assuer_range) {
		this.assuer_range = assuer_range;
	}

	public void setMort_seq(String mort_seq) {
		this.mort_seq = mort_seq;
	}

	public void setMax_amount(String max_amount) {
		this.max_amount = max_amount;
	}

	public void setSure_amount(Double sure_amount) {
		this.sure_amount = sure_amount;
	}

	public String getMort_reg_id() {
		return mort_reg_id;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getMort_type() {
		return mort_type;
	}

	public Double getRel_orig_value() {
		return rel_orig_value;
	}

	public Double getMort_assure_right() {
		return mort_assure_right;
	}

	public Double getAssess_price() {
		return assess_price;
	}

	public Double getAgreed_price() {
		return agreed_price;
	}

	public String getMort_con_no() {
		return mort_con_no;
	}

	public Date getMort_reg_date() {
		return mort_reg_date;
	}

	public String getRel_estate_name() {
		return rel_estate_name;
	}

	public String getBorrower() {
		return borrower;
	}

	public Date getCreditor_start_date() {
		return creditor_start_date;
	}

	public Date getCreditor_end_date() {
		return creditor_end_date;
	}

	public Date getCan_mort_date() {
		return can_mort_date;
	}

	public String getOrig_mort_reg_no() {
		return orig_mort_reg_no;
	}

	public String getCan_mort_reason() {
		return can_mort_reason;
	}

	public String getMort_port() {
		return mort_port;
	}

	public String getLoan_usage() {
		return loan_usage;
	}

	public String getAssuer_range() {
		return assuer_range;
	}

	public String getMort_seq() {
		return mort_seq;
	}

	public String getMax_amount() {
		return max_amount;
	}

	public Double getSure_amount() {
		return sure_amount;
	}

}
