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
@Table(name = "SPE_REFUND_INFO")
public class SpeRefundInfo extends BaseDomain<SpeRefundInfo> {
	@Id
	private String refund_id;
	private String b_deleteflag;
	private String refund_no;
	private String reg_app;
	private Date notice_date;
	private String reg_item;
	private String refund_reason;
	private String refund_app;
	private String refund_app_idno;
	private String refund_status;
	private String notice_note;
	private String bus_id;

	public SpeRefundInfo(){
		super();
		this.t = SpeRefundInfo.class;
	}

	public SpeRefundInfo(String refund_id){
		super();
		this.refund_id = refund_id;
	}

	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setRefund_no(String refund_no) {
		this.refund_no = refund_no;
	}

	public void setReg_app(String reg_app) {
		this.reg_app = reg_app;
	}

	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}

	public void setReg_item(String reg_item) {
		this.reg_item = reg_item;
	}

	public void setRefund_reason(String refund_reason) {
		this.refund_reason = refund_reason;
	}

	public void setRefund_app(String refund_app) {
		this.refund_app = refund_app;
	}

	public void setRefund_app_idno(String refund_app_idno) {
		this.refund_app_idno = refund_app_idno;
	}

	

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public String getRefund_id() {
		return refund_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getRefund_no() {
		return refund_no;
	}

	public String getReg_app() {
		return reg_app;
	}

	public Date getNotice_date() {
		return notice_date;
	}

	public String getReg_item() {
		return reg_item;
	}

	public String getRefund_reason() {
		return refund_reason;
	}

	public String getRefund_app() {
		return refund_app;
	}

	public String getRefund_app_idno() {
		return refund_app_idno;
	}

	public String getBus_id() {
		return bus_id;
	}
	public String getRefund_status() {
		return refund_status;
	}

	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}

	public String getNotice_note() {
		return notice_note;
	}

	public void setNotice_note(String notice_note) {
		this.notice_note = notice_note;
	}

}
