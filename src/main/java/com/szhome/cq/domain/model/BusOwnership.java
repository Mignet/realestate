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
@Table(name = "BUS_OWNERSHIP")
public class BusOwnership extends BaseDomain<BusOwnership> {
	@Id
	private String owner_reg_id;
	private String bus_id;
	private String b_deleteflag;
	private float reg_value;
	private String get_mode;
	private String lu_term;
	private Date start_date;
	private Date end_date;
	private String house_usage;
	private String house_attr;
	private String excursus;
	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public BusOwnership(){
		super();
		this.t = BusOwnership.class;
	}

	public BusOwnership(String owner_reg_id){
		super();
		this.owner_reg_id = owner_reg_id;
	}

	public void setOwner_reg_id(String owner_reg_id) {
		this.owner_reg_id = owner_reg_id;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setReg_value(float reg_value) {
		this.reg_value = reg_value;
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

	public void setExcursus(String excursus) {
		this.excursus = excursus;
	}

	public String getOwner_reg_id() {
		return owner_reg_id;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public float getReg_value() {
		return reg_value;
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

	public String getExcursus() {
		return excursus;
	}

}
