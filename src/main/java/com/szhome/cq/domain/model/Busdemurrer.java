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
@Table(name = "BUS_DEMURRER_REG")
public class Busdemurrer extends BaseDomain<Busdemurrer> {
	@Id
	private String diss_reg_id;
	private String bus_id;
	private String b_deleteflag;
	private String diss_item;
	private String remark;

	public Busdemurrer(){
		super();
		this.t = Busdemurrer.class;
	}

	public Busdemurrer(String diss_reg_id){
		super();
		this.diss_reg_id = diss_reg_id;
	}

	public void setDiss_reg_id(String diss_reg_id) {
		this.diss_reg_id = diss_reg_id;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}


	public void setDiss_item(String diss_item) {
		this.diss_item = diss_item;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDiss_reg_id() {
		return diss_reg_id;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}


	public String getDiss_item() {
		return diss_item;
	}


	public String getRemark() {
		return remark;
	}

}
