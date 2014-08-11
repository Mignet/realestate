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
@Table(name = "BK_DEMURRER")
public class Reg_Demurrer extends BaseDomain<Reg_Demurrer> {
	@Id
	private String demurrer_id;
	private String book_code;
	private String b_deleteflag;
	private String reg_code;
	private String cer_no;
	private String diss_item;
	private Date reg_date;
	private String recorder;
	private String excursus;
	private String pre_reg_code;
	private String procdef_id;
	private String effective;
	public void setProcdef_id(String procdef_id) {
		this.procdef_id = procdef_id;
	}
	public String getProcdef_id() {
		return procdef_id;
	}
	public Reg_Demurrer(){
		super();
		this.t = Reg_Demurrer.class;
	}

	public Reg_Demurrer(String demurrer_id){
		super();
		this.demurrer_id = demurrer_id;
	}

	public void setDemurrer_id(String demurrer_id) {
		this.demurrer_id = demurrer_id;
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

	public void setDiss_item(String diss_item) {
		this.diss_item = diss_item;
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

	public void setPre_reg_code(String pre_reg_code) {
		this.pre_reg_code = pre_reg_code;
	}

	public String getDemurrer_id() {
		return demurrer_id;
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

	public String getDiss_item() {
		return diss_item;
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

	public String getPre_reg_code() {
		return pre_reg_code;
	}
	public String getEffective() {
		return effective;
	}
	public void setEffective(String effective) {
		this.effective = effective;
	}

}
