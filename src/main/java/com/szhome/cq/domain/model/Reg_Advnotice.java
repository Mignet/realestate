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
@Table(name = "BK_ADVNOTICE")
public class Reg_Advnotice extends BaseDomain<Reg_Advnotice> {
	@Id
	private String adv_id;
	private String book_code;
	private String b_deleteflag;
	private String reg_code;
	private String adv_type;
	private String cer_no;
	private String adv_no;
	private Date reg_date;
	private String recorder;
	private String can_adv_no;
	private String can_adv_app;
	private Date can_adv_date;
	private String can_adv_rec;
	private String excursus;
	private String pre_reg_code;
	private String procdef_id;
	public void setProcdef_id(String procdef_id) {
		this.procdef_id = procdef_id;
	}
	public String getProcdef_id() {
		return procdef_id;
	}
	public Reg_Advnotice(){
		super();
		this.t = Reg_Advnotice.class;
	}

	public Reg_Advnotice(String adv_id){
		super();
		this.adv_id = adv_id;
	}

	public void setAdv_id(String adv_id) {
		this.adv_id = adv_id;
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

	public void setAdv_type(String adv_type) {
		this.adv_type = adv_type;
	}

	public void setCer_no(String cer_no) {
		this.cer_no = cer_no;
	}

	public void setAdv_no(String adv_no) {
		this.adv_no = adv_no;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public void setCan_adv_no(String can_adv_no) {
		this.can_adv_no = can_adv_no;
	}

	public void setCan_adv_app(String can_adv_app) {
		this.can_adv_app = can_adv_app;
	}

	public void setCan_adv_date(Date can_adv_date) {
		this.can_adv_date = can_adv_date;
	}

	public void setCan_adv_rec(String can_adv_rec) {
		this.can_adv_rec = can_adv_rec;
	}

	public void setExcursus(String excursus) {
		this.excursus = excursus;
	}

	public void setPre_reg_code(String pre_reg_code) {
		this.pre_reg_code = pre_reg_code;
	}

	public String getAdv_id() {
		return adv_id;
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

	public String getAdv_type() {
		return adv_type;
	}

	public String getCer_no() {
		return cer_no;
	}

	public String getAdv_no() {
		return adv_no;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public String getRecorder() {
		return recorder;
	}

	public String getCan_adv_no() {
		return can_adv_no;
	}

	public String getCan_adv_app() {
		return can_adv_app;
	}

	public Date getCan_adv_date() {
		return can_adv_date;
	}

	public String getCan_adv_rec() {
		return can_adv_rec;
	}

	public String getExcursus() {
		return excursus;
	}

	public String getPre_reg_code() {
		return pre_reg_code;
	}

}
