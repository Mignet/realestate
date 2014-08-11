package com.szhome.cq.domain.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springjdbc.annotation.BaseDomain;
import com.springjdbc.annotation.Entity;
import com.springjdbc.annotation.Id;
import com.springjdbc.annotation.Table;

@Scope("prototype")
@Component
@Entity
@Table(name = "BUS_ALERT_NOTE")
public class PresaleReginfo extends BaseDomain<PresaleReginfo> {
	private String con_no;
	@Id
	private String pre_rec_id;
	private String bus_id;
	private String b_deleteflag;
	private String rel_estate_name;
	private String flatlet_id;
	private String rec_status;

	public PresaleReginfo(){
		super();
		this.t = PresaleReginfo.class;
	}

	public PresaleReginfo(String pre_rec_id){
		super();
		this.pre_rec_id = pre_rec_id;
	}

	public void setCon_no(String con_no) {
		this.con_no = con_no;
	}

	public void setPre_rec_id(String pre_rec_id) {
		this.pre_rec_id = pre_rec_id;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setRel_estate_name(String rel_estate_name) {
		this.rel_estate_name = rel_estate_name;
	}

	public void setFlatlet_id(String flatlet_id) {
		this.flatlet_id = flatlet_id;
	}

	public void setRec_status(String rec_status) {
		this.rec_status = rec_status;
	}

	public String getCon_no() {
		return con_no;
	}

	public String getPre_rec_id() {
		return pre_rec_id;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getRel_estate_name() {
		return rel_estate_name;
	}

	public String getFlatlet_id() {
		return flatlet_id;
	}

	public String getRec_status() {
		return rec_status;
	}

}

