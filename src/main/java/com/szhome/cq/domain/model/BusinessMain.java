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
@Table(name = "BUS_MAIN")
public class BusinessMain extends BaseDomain<BusinessMain> {
	private String location_reg_unit;
	@Id
	private String bus_id;
	private String dis_reg_id;
	private String b_deleteflag;
	private String proc_id;
	private String proc_name;
	private String pro_def_id;
	private String last_bus_id;
	private String reg_type;
	private String reg_code;
	private String reg_station;
	private String is_upload_sup;
	private String state_sup;
	private String state_inner;
	private String state_outter;
	private String reg_state;
	private String is_valid;
	private String bus_nature;

	public BusinessMain(){
		super();
		this.t = BusinessMain.class;
	}

	public BusinessMain(String bus_id){
		super();
		this.bus_id = bus_id;
	}

	public void setLocation_reg_unit(String location_reg_unit) {
		this.location_reg_unit = location_reg_unit;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setDis_reg_id(String dis_reg_id) {
		this.dis_reg_id = dis_reg_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setProc_id(String proc_id) {
		this.proc_id = proc_id;
	}

	public void setProc_name(String proc_name) {
		this.proc_name = proc_name;
	}

	public void setPro_def_id(String pro_def_id) {
		this.pro_def_id = pro_def_id;
	}

	public void setLast_bus_id(String last_bus_id) {
		this.last_bus_id = last_bus_id;
	}

	public void setReg_type(String reg_type) {
		this.reg_type = reg_type;
	}

	public void setReg_code(String reg_code) {
		this.reg_code = reg_code;
	}

	public void setReg_station(String reg_station) {
		this.reg_station = reg_station;
	}

	public void setIs_upload_sup(String is_upload_sup) {
		this.is_upload_sup = is_upload_sup;
	}

	public void setState_sup(String state_sup) {
		this.state_sup = state_sup;
	}

	public void setState_inner(String state_inner) {
		this.state_inner = state_inner;
	}

	public void setState_outter(String state_outter) {
		this.state_outter = state_outter;
	}

	public void setReg_state(String reg_state) {
		this.reg_state = reg_state;
	}

	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}

	public void setBus_nature(String bus_nature) {
		this.bus_nature = bus_nature;
	}

	public String getLocation_reg_unit() {
		return location_reg_unit;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getDis_reg_id() {
		return dis_reg_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getProc_id() {
		return proc_id;
	}

	public String getProc_name() {
		return proc_name;
	}

	public String getPro_def_id() {
		return pro_def_id;
	}

	public String getLast_bus_id() {
		return last_bus_id;
	}

	public String getReg_type() {
		return reg_type;
	}

	public String getReg_code() {
		return reg_code;
	}

	public String getReg_station() {
		return reg_station;
	}

	public String getIs_upload_sup() {
		return is_upload_sup;
	}

	public String getState_sup() {
		return state_sup;
	}

	public String getState_inner() {
		return state_inner;
	}

	public String getState_outter() {
		return state_outter;
	}

	public String getReg_state() {
		return reg_state;
	}

	public String getIs_valid() {
		return is_valid;
	}

	public String getBus_nature() {
		return bus_nature;
	}

}
