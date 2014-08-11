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
@Table(name = "bd_reg_obj_chg")
public class Bd_reg_obj_chg extends BaseDomain<Bd_reg_obj_chg> {
	private String obj_chg_id;
	private String pre_id;
	private String reg_unit_type;
	private String bg_type;
	private String next_id;
	private Date bg_op_time;
	private String bg_op_user;

	public Bd_reg_obj_chg(){
		super();
		this.t = Bd_reg_obj_chg.class;
	}

	public void setObj_chg_id(String obj_chg_id) {
		this.obj_chg_id = obj_chg_id;
	}

	public void setPre_id(String pre_id) {
		this.pre_id = pre_id;
	}

	public void setReg_unit_type(String reg_unit_type) {
		this.reg_unit_type = reg_unit_type;
	}

	public void setBg_type(String bg_type) {
		this.bg_type = bg_type;
	}

	public void setNext_id(String next_id) {
		this.next_id = next_id;
	}

	public void setBg_op_time(Date bg_op_time) {
		this.bg_op_time = bg_op_time;
	}

	public void setBg_op_user(String bg_op_user) {
		this.bg_op_user = bg_op_user;
	}

	public String getObj_chg_id() {
		return obj_chg_id;
	}

	public String getPre_id() {
		return pre_id;
	}

	public String getReg_unit_type() {
		return reg_unit_type;
	}

	public String getBg_type() {
		return bg_type;
	}

	public String getNext_id() {
		return next_id;
	}

	public Date getBg_op_time() {
		return bg_op_time;
	}

	public String getBg_op_user() {
		return bg_op_user;
	}

}