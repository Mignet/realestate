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
@Table(name = "chk_base")
public class CheckBase extends BaseDomain<CheckBase> {
	private String check_proc_id;
	private String batch_no;
	private String reg_type;
	private String reg_station;
	private String che_person_name;
	private String dept_id;
	private String dept_name;
	private String proc_id;
	private String procdef_id;
	private String is_error;
	private String reg_type_name;
	private String location_reg_unit;
	@Id
	private String che_id;
	private String bus_id;
	private String b_deleteflag;
	private String reg_code;
	private Date che_date;
	private String che_person;
	private String che_result;
	private String che_opinion;
	private String che_type;
	private String pre_che_id;

	public CheckBase(){
		super();
		this.t = CheckBase.class;
	}

	public CheckBase(String che_id){
		super();
		this.che_id = che_id;
	}

	public void setCheck_proc_id(String check_proc_id) {
		this.check_proc_id = check_proc_id;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public void setReg_type(String reg_type) {
		this.reg_type = reg_type;
	}

	public void setReg_station(String reg_station) {
		this.reg_station = reg_station;
	}

	public void setChe_person_name(String che_person_name) {
		this.che_person_name = che_person_name;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public void setProc_id(String proc_id) {
		this.proc_id = proc_id;
	}

	public void setProcdef_id(String procdef_id) {
		this.procdef_id = procdef_id;
	}

	public void setIs_error(String is_error) {
		this.is_error = is_error;
	}

	public void setReg_type_name(String reg_type_name) {
		this.reg_type_name = reg_type_name;
	}

	public void setLocation_reg_unit(String location_reg_unit) {
		this.location_reg_unit = location_reg_unit;
	}

	public void setChe_id(String che_id) {
		this.che_id = che_id;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setReg_code(String reg_code) {
		this.reg_code = reg_code;
	}

	public void setChe_date(Date che_date) {
		this.che_date = che_date;
	}

	public void setChe_person(String che_person) {
		this.che_person = che_person;
	}

	public void setChe_result(String che_result) {
		this.che_result = che_result;
	}

	public void setChe_opinion(String che_opinion) {
		this.che_opinion = che_opinion;
	}

	public void setChe_type(String che_type) {
		this.che_type = che_type;
	}

	public void setPre_che_id(String pre_che_id) {
		this.pre_che_id = pre_che_id;
	}

	public String getCheck_proc_id() {
		return check_proc_id;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public String getReg_type() {
		return reg_type;
	}

	public String getReg_station() {
		return reg_station;
	}

	public String getChe_person_name() {
		return che_person_name;
	}

	public String getDept_id() {
		return dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public String getProc_id() {
		return proc_id;
	}

	public String getProcdef_id() {
		return procdef_id;
	}

	public String getIs_error() {
		return is_error;
	}

	public String getReg_type_name() {
		return reg_type_name;
	}

	public String getLocation_reg_unit() {
		return location_reg_unit;
	}

	public String getChe_id() {
		return che_id;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getReg_code() {
		return reg_code;
	}

	public Date getChe_date() {
		return che_date;
	}

	public String getChe_person() {
		return che_person;
	}

	public String getChe_result() {
		return che_result;
	}

	public String getChe_opinion() {
		return che_opinion;
	}

	public String getChe_type() {
		return che_type;
	}

	public String getPre_che_id() {
		return pre_che_id;
	}

}

