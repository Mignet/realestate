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
@Table(name = "BK_MORTGAGE")
public class Reg_Mortgage extends BaseDomain<Reg_Mortgage> {
	private String recorder;
	private String max_amount;
	private String sure_amount;
	private String excursus;
	private String pre_reg_code;
	private String procdef_id;
	private String borrower;
	@Id
	private String mort_id;
	private String book_code;
	private String b_deleteflag;
	private String reg_code;
	private String mort_type;
	private String gen_mort;
	private String max_mort;
	private String assure_amount;
	private String assuer_range;
	private String debt_dis_limit;
	private String mort_no;
	private String cer_no;
	private String mort_seq;
	private Date reg_date;
	private String  effective;
	private String con_progress;

	public String getCon_progress() {
		return con_progress;
	}

	public void setCon_progress(String con_progress) {
		this.con_progress = con_progress;
	}

	public Reg_Mortgage(){
		super();
		this.t = Reg_Mortgage.class;
	}

	public Reg_Mortgage(String mort_id){
		super();
		this.mort_id = mort_id;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public void setMax_amount(String max_amount) {
		this.max_amount = max_amount;
	}

	public void setSure_amount(String sure_amount) {
		this.sure_amount = sure_amount;
	}

	public void setExcursus(String excursus) {
		this.excursus = excursus;
	}

	public void setPre_reg_code(String pre_reg_code) {
		this.pre_reg_code = pre_reg_code;
	}

	public void setProcdef_id(String procdef_id) {
		this.procdef_id = procdef_id;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public void setMort_id(String mort_id) {
		this.mort_id = mort_id;
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

	public void setMort_type(String mort_type) {
		this.mort_type = mort_type;
	}

	public void setGen_mort(String gen_mort) {
		this.gen_mort = gen_mort;
	}

	public void setMax_mort(String max_mort) {
		this.max_mort = max_mort;
	}

	public void setAssure_amount(String assure_amount) {
		this.assure_amount = assure_amount;
	}

	public void setAssuer_range(String assuer_range) {
		this.assuer_range = assuer_range;
	}

	public void setDebt_dis_limit(String debt_dis_limit) {
		this.debt_dis_limit = debt_dis_limit;
	}

	public void setMort_no(String mort_no) {
		this.mort_no = mort_no;
	}

	public void setCer_no(String cer_no) {
		this.cer_no = cer_no;
	}

	public void setMort_seq(String mort_seq) {
		this.mort_seq = mort_seq;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public String getRecorder() {
		return recorder;
	}

	public String getMax_amount() {
		return max_amount;
	}

	public String getSure_amount() {
		return sure_amount;
	}

	public String getExcursus() {
		return excursus;
	}

	public String getPre_reg_code() {
		return pre_reg_code;
	}

	public String getProcdef_id() {
		return procdef_id;
	}

	public String getBorrower() {
		return borrower;
	}

	public String getMort_id() {
		return mort_id;
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

	public String getMort_type() {
		return mort_type;
	}

	public String getGen_mort() {
		return gen_mort;
	}

	public String getMax_mort() {
		return max_mort;
	}

	public String getAssure_amount() {
		return assure_amount;
	}

	public String getAssuer_range() {
		return assuer_range;
	}

	public String getDebt_dis_limit() {
		return debt_dis_limit;
	}

	public String getMort_no() {
		return mort_no;
	}

	public String getCer_no() {
		return cer_no;
	}

	public String getMort_seq() {
		return mort_seq;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public String getEffective() {
		return effective;
	}

	public void setEffective(String effective) {
		this.effective = effective;
	}

}
