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
@Table(name = "BK_BASEINFO")
public class Reg_baseInfo extends BaseDomain<Reg_baseInfo> {
	@Id
	private String book_code;
	private String house_id;
	private String parcel_id;
	private String b_deleteflag;
	private String reg_organization;
	private String reg_unit_type;
	private String reg_unit_code;

	public Reg_baseInfo(){
		super();
		this.t = Reg_baseInfo.class;
	}

	public Reg_baseInfo(String book_code){
		super();
		this.book_code = book_code;
	}

	public void setBook_code(String book_code) {
		this.book_code = book_code;
	}

	public void setHouse_id(String house_id) {
		this.house_id = house_id;
	}

	public void setParcel_id(String parcel_id) {
		this.parcel_id = parcel_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setReg_organization(String reg_organization) {
		this.reg_organization = reg_organization;
	}

	public void setReg_unit_type(String reg_unit_type) {
		this.reg_unit_type = reg_unit_type;
	}

	

	public String getBook_code() {
		return book_code;
	}

	public String getHouse_id() {
		return house_id;
	}

	public String getParcel_id() {
		return parcel_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getReg_organization() {
		return reg_organization;
	}

	public String getReg_unit_type() {
		return reg_unit_type;
	}

	public String getReg_unit_code() {
		return reg_unit_code;
	}

	public void setReg_unit_code(String reg_unit_code) {
		this.reg_unit_code = reg_unit_code;
	}



}
