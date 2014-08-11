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
@Table(name = "BUS_ARCHIVES")
public class Pigeonhole extends BaseDomain<Pigeonhole> {
	@Id
	private String archives_id;
	private String bus_id;
	private String arbox_code;
	private String transfer;
	private Date transfer_date;
	private String arch_no;
	private String arch_handler;
	private String arch_handler_no;
	private Date arch_date;
	private String comments;
	private String b_deleteflag;

	public Pigeonhole(){
		super();
		this.t = Pigeonhole.class;
	}

	public Pigeonhole(String archives_id){
		super();
		this.archives_id = archives_id;
	}

	public void setArchives_id(String archives_id) {
		this.archives_id = archives_id;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setArbox_code(String arbox_code) {
		this.arbox_code = arbox_code;
	}

	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}

	public void setTransfer_date(Date transfer_date) {
		this.transfer_date = transfer_date;
	}

	public void setArch_no(String arch_no) {
		this.arch_no = arch_no;
	}

	public void setArch_handler(String arch_handler) {
		this.arch_handler = arch_handler;
	}

	public void setArch_handler_no(String arch_handler_no) {
		this.arch_handler_no = arch_handler_no;
	}

	public void setArch_date(Date arch_date) {
		this.arch_date = arch_date;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public String getArchives_id() {
		return archives_id;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getArbox_code() {
		return arbox_code;
	}

	public String getTransfer() {
		return transfer;
	}

	public Date getTransfer_date() {
		return transfer_date;
	}

	public String getArch_no() {
		return arch_no;
	}

	public String getArch_handler() {
		return arch_handler;
	}

	public String getArch_handler_no() {
		return arch_handler_no;
	}

	public Date getArch_date() {
		return arch_date;
	}

	public String getComments() {
		return comments;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

}
