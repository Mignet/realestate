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
@Table(name = "BUS_REMARK_INFO")
public class BusRemarkInfo extends BaseDomain<BusRemarkInfo> {
	@Id
	private String remark_id;
	private String bus_id;
	private String b_deleteflag;
	private String remark_office;
	private String remark_no;
	private String remark_content;
	private String procdef_id;
	private String effective;
	private String recorder;
	private Date reg_date;
	private String reg_code;
	private String pre_reg_code;
	private String remark_type;
	private String remark_person;
	public String getRemark_type() {
		return remark_type;
	}

	public void setRemark_type(String remark_type) {
		this.remark_type = remark_type;
	}


	public String getRemark_person() {
		return remark_person;
	}

	public void setRemark_person(String remark_person) {
		this.remark_person = remark_person;
	}

	public BusRemarkInfo(){
		super();
		this.t = BusRemarkInfo.class;
	}

	public BusRemarkInfo(String remark_id){
		super();
		this.remark_id = remark_id;
	}

	public void setRemark_id(String remark_id) {
		this.remark_id = remark_id;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setRemark_office(String remark_office) {
		this.remark_office = remark_office;
	}

	public void setRemark_no(String remark_no) {
		this.remark_no = remark_no;
	}

	public void setRemark_content(String remark_content) {
		this.remark_content = remark_content;
	}

	public void setProcdef_id(String procdef_id) {
		this.procdef_id = procdef_id;
	}

	public void setEffective(String effective) {
		this.effective = effective;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public void setReg_code(String reg_code) {
		this.reg_code = reg_code;
	}

	public void setPre_reg_code(String pre_reg_code) {
		this.pre_reg_code = pre_reg_code;
	}

	public String getRemark_id() {
		return remark_id;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getRemark_office() {
		return remark_office;
	}

	public String getRemark_no() {
		return remark_no;
	}

	public String getRemark_content() {
		return remark_content;
	}

	public String getProcdef_id() {
		return procdef_id;
	}

	public String getEffective() {
		return effective;
	}

	public String getRecorder() {
		return recorder;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public String getReg_code() {
		return reg_code;
	}

	public String getPre_reg_code() {
		return pre_reg_code;
	}

}
