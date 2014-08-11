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
@Table(name = "BK_EASEMENT")
public class Reg_Easement extends BaseDomain<Reg_Easement> {
	@Id
	private String ease_id;
	private String book_code;
	private String b_deleteflag;
	private String reg_code;
	private String ease_no;
	private String pra_dom_holder;
	private String pra_ser_holder;
	private String ease_set_item;
	private String cer_no;
	private Date reg_date;
	private String recorder;
	private String can_ease_no;
	private String can_ease_app;
	private Date can_ease_date;
	private String can_ease_rec;
	private String excursus;
	private String pre_reg_code;
	private String procdef_id;
	public void setProcdef_id(String procdef_id) {
		this.procdef_id = procdef_id;
	}
	public String getProcdef_id() {
		return procdef_id;
	}
	public Reg_Easement(){
		super();
		this.t = Reg_Easement.class;
	}

	public Reg_Easement(String ease_id){
		super();
		this.ease_id = ease_id;
	}

	public void setEase_id(String ease_id) {
		this.ease_id = ease_id;
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

	public void setEase_no(String ease_no) {
		this.ease_no = ease_no;
	}

	public void setPra_dom_holder(String pra_dom_holder) {
		this.pra_dom_holder = pra_dom_holder;
	}

	public void setPra_ser_holder(String pra_ser_holder) {
		this.pra_ser_holder = pra_ser_holder;
	}

	public void setEase_set_item(String ease_set_item) {
		this.ease_set_item = ease_set_item;
	}

	public void setCer_no(String cer_no) {
		this.cer_no = cer_no;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public void setCan_ease_no(String can_ease_no) {
		this.can_ease_no = can_ease_no;
	}

	public void setCan_ease_app(String can_ease_app) {
		this.can_ease_app = can_ease_app;
	}

	public void setCan_ease_date(Date can_ease_date) {
		this.can_ease_date = can_ease_date;
	}

	public void setCan_ease_rec(String can_ease_rec) {
		this.can_ease_rec = can_ease_rec;
	}

	public void setExcursus(String excursus) {
		this.excursus = excursus;
	}

	public void setPre_reg_code(String pre_reg_code) {
		this.pre_reg_code = pre_reg_code;
	}

	public String getEase_id() {
		return ease_id;
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

	public String getEase_no() {
		return ease_no;
	}

	public String getPra_dom_holder() {
		return pra_dom_holder;
	}

	public String getPra_ser_holder() {
		return pra_ser_holder;
	}

	public String getEase_set_item() {
		return ease_set_item;
	}

	public String getCer_no() {
		return cer_no;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public String getRecorder() {
		return recorder;
	}

	public String getCan_ease_no() {
		return can_ease_no;
	}

	public String getCan_ease_app() {
		return can_ease_app;
	}

	public Date getCan_ease_date() {
		return can_ease_date;
	}

	public String getCan_ease_rec() {
		return can_ease_rec;
	}

	public String getExcursus() {
		return excursus;
	}

	public String getPre_reg_code() {
		return pre_reg_code;
	}

}
