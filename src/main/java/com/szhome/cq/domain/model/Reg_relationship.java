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
@Table(name = "BUS_REGUNITREL")
public class Reg_relationship extends BaseDomain<Reg_relationship> {
	@Id
	private String regunitrel_id;
	private String bus_id;
	private String house_id;
	private String parcel_id;
	private String building_id;
	private String b_deleteflag;
	private String reg_unit_type;
	private String reg_unit_code;
	private String reg_code;
	private String last_reg_code;
	private String reg_state;
	public String getReg_code() {
		return reg_code;
	}

	public void setReg_code(String reg_code) {
		this.reg_code = reg_code;
	}

	public String getLast_reg_code() {
		return last_reg_code;
	}

	public void setLast_reg_code(String last_reg_code) {
		this.last_reg_code = last_reg_code;
	}

	public String getReg_state() {
		return reg_state;
	}

	public void setReg_state(String reg_state) {
		this.reg_state = reg_state;
	}

	public Reg_relationship(){
		super();
		this.t = Reg_relationship.class;
	}

	public Reg_relationship(String regunitrel_id){
		super();
		this.regunitrel_id = regunitrel_id;
	}

	public void setRegunitrel_id(String regunitrel_id) {
		this.regunitrel_id = regunitrel_id;
	}


	public void setHouse_id(String house_id) {
		this.house_id = house_id;
	}

	public void setParcel_id(String parcel_id) {
		this.parcel_id = parcel_id;
	}

	public void setBuilding_id(String building_id) {
		this.building_id = building_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setReg_unit_type(String reg_unit_type) {
		this.reg_unit_type = reg_unit_type;
	}

	public void setReg_unit_code(String reg_unit_code) {
		this.reg_unit_code = reg_unit_code;
	}

	public String getRegunitrel_id() {
		return regunitrel_id;
	}



	public String getHouse_id() {
		return house_id;
	}

	public String getParcel_id() {
		return parcel_id;
	}

	public String getBuilding_id() {
		return building_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getReg_unit_type() {
		return reg_unit_type;
	}

	public String getReg_unit_code() {
		return reg_unit_code;
	}

	public String getBus_id() {
		return bus_id;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

}
